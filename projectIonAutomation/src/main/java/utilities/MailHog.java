package utilities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mailhog.Items;
import mailhog.MailhogResponse;

public class MailHog {
	static ConfigDataProvider config = new ConfigDataProvider();

	public MailHog() {
		RestAssured.useRelaxedHTTPSValidation();
	}

	public static String getEmailDeeplink(String email) {
		/*
		 * Create a 60 second Loop. Every 10 seconds check for keycloak email in
		 * mailhog. If email is found, get the deeplink from email body and break the
		 * loop.
		 */
		String deeplinkURL = null;
		long loopStart = System.currentTimeMillis();
		long loopEnd = loopStart + 20 * 1000;
		while (System.currentTimeMillis() < loopEnd) {
			// Get Emails
			List<Items> emailsList = null;
			emailsList = MailHog.getEmailsContainingString(email);

			for (Items emailItem : emailsList) {
				System.out.println("Email(s) found. Now iterating through each email and looking for a deeplink.");
				// Find email sent from 'Customer Keycloak'. This is the domain of keycloak emails
				if (emailItem.getContent().getHeaders().getFrom().get(0).contains("Customer Keycloak")) {
					// Get email content body
					String messageBody = emailItem.getContent().getBody();

					// Get deeplink in the email body
					Pattern p = Pattern.compile("https://keycloak.$environment.ion.comhar.local.*".replace("$environment", config.getDataFromConfig("currentEnvironment").toLowerCase()));
					Matcher matcher = p.matcher(messageBody);
					while (matcher.find()) {
						System.out.println("Email found");
						deeplinkURL = matcher.group().trim();
					}
					break;
				}
			}
			try {
				if (deeplinkURL != null) {
					break;
				}
				System.out.println("No emails found containing \nSleeping for 10 seconds");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// If no emails with deeplink found > throw a null pointer exception
		if (deeplinkURL == null) {
			throw new NullPointerException("Deeplink email not found.");
		}

		return deeplinkURL;
	}

	public static String getEmailJsonArrayString(String email) {
		// Set eir proxy properties
		System.setProperty("https.proxyHost", "bcproxy.eircom.ie");
		System.setProperty("https.proxyPort", "8080");

		RestAssured.useRelaxedHTTPSValidation();

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://mailhog.$environment.ion.comhar.local".replace("$environment", config.getDataFromConfig("currentEnvironment"));

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		String username = config.getDataFromConfig(config.getDataFromConfig("currentEnvironment").toLowerCase() + "Mailhog_USER");
		String password = config.getDataFromConfig(config.getDataFromConfig("currentEnvironment").toLowerCase() + "Mailhog_PASS");
		RequestSpecification httpRequest = RestAssured.given().auth().basic(username, password);

		// Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("/api/v2/search?kind=containing&query=" + email.trim());

		// Response.asString method will directly return the content of the body
		// as String.
		String jsonString = response.asString();
		return jsonString;

	}

	public static List<Items> getEmailsContainingString(String string) {
		String emailJsonArray = MailHog.getEmailJsonArrayString(string);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		MailhogResponse mailhogResponse = null;
		try {
			mailhogResponse = objectMapper.readValue(emailJsonArray, MailhogResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Items> emailsList = mailhogResponse.getItems();
		return emailsList;
	}

}
//	https://mailhog.e2e.ion.comhar.local/api/v2/search?kind=to&query=b.ok%2B2019-11-04_12-54-5435@eir.ie