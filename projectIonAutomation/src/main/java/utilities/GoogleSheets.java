package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheets {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.dir");
	public Sheets service;
	private String spreadsheetId;
	private String range;

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/googlesheetsCredentials.json";

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = GoogleSheets.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	/**
	 * Prints the names and majors of students in a sample spreadsheet:
	 * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	 */
	public GoogleSheets(String spreadsheetId, String range) throws IOException, GeneralSecurityException {
		this.spreadsheetId = spreadsheetId;
		this.range = range;

		// Set eir proxy properties
		System.setProperty("https.proxyHost", "bcproxy.eircom.ie");
		System.setProperty("https.proxyPort", "8080");

		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		// spreadsheetId = "1s5jEgK9KEh_p5NSXidK213DvSIVGTWlHR2k-1RoCt1Q";
		// range = "Inventory!A1:D";
		service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

	}

	// Function to bring back number of rows
	public int getNumRows() {

		int numberRows = 0;
		try {
			numberRows = service.spreadsheets().values().get(spreadsheetId, range).execute().getValues().size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e1) {
			numberRows = 0;
		}
		return numberRows;
	}

	// Function to get sheet content
	public List<List<Object>> getSheetContent() throws IOException {

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();

		return values;
	}
	
	// Function to get sheet content for row range x to y
		public List<List<Object>> getSheetRowRangeContent(int startIndex, int endIndex) throws IOException {
			String rowRange = "Inventory!A" + 1 + ":D" + endIndex;
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, rowRange).execute();
			List<List<Object>> values = response.getValues();

			return values;
		}

	// Function to return cell string
	public String getCellString(int row, int column) {
		String cellString = null;
		try {
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = response.getValues();
			cellString = values.get(row).get(column).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cellString;
	}

/*	// Function to return row string
	public String getRowString(int row, List<List<Object>> values) {
		String rowString = null;

		rowString = values.get(row).toString();

		return rowString.replace("[", "").replace("]", "").replaceAll(" ", "");
	}
*/	
/*	// Function to return row range string
		public String getRowRangeString(int startIndex, int endIndex, List<List<Object>> values) {
			String rowString = null;

			rowString = values.get(row).toString();

			return rowString.replace("[", "").replace("]", "").replaceAll(" ", "");
		}
*/
	// Function to delete a range of row(s)
	public void deleteRowRange(Integer sheetID, Integer startIndex, Integer endIndex) {
		BatchUpdateSpreadsheetRequest content = new BatchUpdateSpreadsheetRequest();
		Request request = new Request().setDeleteDimension(new DeleteDimensionRequest().setRange(new DimensionRange()
				.setSheetId(sheetID).setDimension("ROWS").setStartIndex(startIndex).setEndIndex(endIndex)));
		List<Request> requests = new ArrayList<Request>();
		requests.add(request);
		content.setRequests(requests);
		System.out.println("Row(s) " + startIndex + ":" + endIndex + " has been deleted.");

		try {
			service.spreadsheets().batchUpdate(spreadsheetId, content).execute();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}