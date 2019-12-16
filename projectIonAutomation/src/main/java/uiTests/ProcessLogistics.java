package uiTests;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import logistics.Kubernetes;
import logistics.Logistics;
import utilities.MariaDB;

public class ProcessLogistics extends BaseTest {
	
	Kubernetes kubernetes;
	Logistics logistics;

	@Test (dependsOnMethods = { "uiTests.E2eOrder.placeSinglelineOrder" })
	public void processSinglelineLogistics(ITestContext iTestContext) {
		
		List<String> orderNumbers = Arrays.asList(iTestContext.getAttribute("orderNumberSingleline").toString().split(","));

		processLogistics(orderNumbers);
	}
	
	@Test (dependsOnMethods = { "uiTests.E2eOrder.placeMultilineOrder" })
	public void processMultilineLogistics(ITestContext iTestContext) {
		
		List<String> orderNumbers = Arrays.asList(iTestContext.getAttribute("orderNumberMultiline").toString().split(","));

		processLogistics(orderNumbers);

	}
	
	@Test (dependsOnMethods = { "uiTests.E2eOrder.placeCrossSellOrder" })
	public void processCrossSellLogistics(ITestContext iTestContext) {
		
		List<String> orderNumbers = Arrays.asList(iTestContext.getAttribute("orderNumberCrossSell").toString().split(","));

		processLogistics(orderNumbers);

	}
	
	@Test (dependsOnMethods = { "uiTests.ProcessLogistics.processSinglelineLogistics" })
	public void provisioningCheckSingleline(ITestContext iTestContext) throws SQLException, InterruptedException {
		
		MariaDB mariaDB = new MariaDB();
		String query = mariaDB.getProvisioningCheckQuery(iTestContext.getAttribute("orderNumberSingleline").toString());
		ResultSet rs = mariaDB.getQueryResultSet(query);
		
		checkProvisioning(mariaDB, query, rs);
		
		excel.recordProvisioningComplete("Singleline");

	}
	
	@Test (dependsOnMethods = { "uiTests.ProcessLogistics.processMultilineLogistics" })
	public void provisioningCheckMultiline(ITestContext iTestContext) throws SQLException, InterruptedException {
		
		MariaDB mariaDB = new MariaDB();
		String query = mariaDB.getProvisioningCheckQuery(iTestContext.getAttribute("orderNumberMultiline").toString());
		ResultSet rs = mariaDB.getQueryResultSet(query);
		
		checkProvisioning(mariaDB, query, rs);
		
		excel.recordProvisioningComplete("Multiline");


	}
	
	@Test (dependsOnMethods = { "uiTests.ProcessLogistics.processCrossSellLogistics" })
	public void provisioningCheckCrossSell(ITestContext iTestContext) throws SQLException, InterruptedException {
		
		MariaDB mariaDB = new MariaDB();
		String query = mariaDB.getProvisioningCheckQuery(iTestContext.getAttribute("orderNumberCrossSell").toString());
		ResultSet rs = mariaDB.getQueryResultSet(query);
		
		checkProvisioning(mariaDB, query, rs);
		
		excel.recordProvisioningComplete("Singleline");

	}
	
	
	@BeforeClass
	public void startDriver() {
		kubernetes = new Kubernetes();
		logistics = new Logistics(kubernetes);
		
		if (currentEnvironment.equalsIgnoreCase("Perf")) {
			kubernetes.executeCommand("kubectl config use-context perf-context");
		}
	}

	@AfterClass
	public void closeDriver() {
		kubernetes.closeSshConnection();
	}
	
	
	// This method will get ORD files for a given list of ordernumbers, generate CNF and send to logistics for processing.
	@SuppressWarnings("deprecation")
	private void processLogistics(List<String> orderNumbers) {
		
		if (logistics.getMultipleORDWorkFolder(orderNumbers).length() == 0 && logistics.getMultipleORDOutboundFolder(orderNumbers).length() == 0) {
			logger.fail("ORD file not found.");
			Thread.currentThread().stop();
		}

		logger.info("Retreiving ORD file.");
		if (logistics.getMultipleORDWorkFolder(orderNumbers).length() != 0)
		{
			System.out.println(logistics.getMultipleORDWorkFolder(orderNumbers));
			logistics.saveORD(logistics.getMultipleORDWorkFolder(orderNumbers));

		} else {
			System.out.println(logistics.getMultipleORDOutboundFolder(orderNumbers));
			logistics.saveORD(logistics.getMultipleORDOutboundFolder(orderNumbers));

		}
		logger.info("ORD file successfully retrieved.");
		try {
			logger.info("Generating CNF file...");
			// If End to End environment tab is selected, get simpack from E2EInventry sheet in google doc. Else get from PerfInventory sheet
			if (currentEnvironment.matches("E2E"))
			{
				logistics.generateCNF(logistics.getOrdPath(), "E2EInventory");
			}else if (currentEnvironment.matches("Perf"))
			{
				logistics.generateCNF(logistics.getOrdPath(), "PerfInventory");
			} else {
				throw new NullPointerException("Current Environment in Config.properties: " + currentEnvironment + " - Not Recognised");
			}
			logger.info("CNF generated.");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.fail(e1);
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.fail(e1);
		}
		
		logger.info("Sending CNF file to Logistics...");
		System.out.println(logistics.getCnfPath());
		logistics.sendCNF(logistics.getCnfPath());
		logger.info("CNF file sent.");
	}
	
	// This method will check a db result set for provisioning status of each
	// service item.
	private void checkProvisioning(MariaDB mariaDB, String query, ResultSet rs)
			throws SQLException, InterruptedException {
		// Will be used for time based while loops for doing periodic DB check
		long loopStart;
		long loopEnd;
		int numOfRows = 0;

		loopStart = System.currentTimeMillis();
		loopEnd = loopStart + 20 * 1000;
		while (System.currentTimeMillis() < loopEnd) {
			// Get number of rows which will be used in a for loop
			rs = mariaDB.getQueryResultSet(query);
			numOfRows = mariaDB.getNumberRows(rs);
			System.out.println(numOfRows);

			// IF number of rows > 0, cnf file has been processed and provisioning request
			// has reached Provisioning Facade. Break the loop.
			// If number of rows = 0. cnf file has not been processed. Try again in 5
			// seconds. If after 30 seconds rows = 0, fail the test.
			if (numOfRows > 0) {
				break;
			}
			System.out.println("Query has returned no results. Will retry in 5 seconds.");
			Thread.sleep(5000);
		}
		if (numOfRows < 1) {
			throw new AssertionError("The SQL Query is returning no results.\nCNF file has not been processed.");
		}

		// For each row, get the status of the provisioning request
		for (int i = 1; i <= numOfRows; i++) {
			rs.absolute(i);

			// IF completed, continue
			if (rs.getString("service_status").contentEquals("COMPLETED")) {
				System.out.println(rs.getString("service_action") + ": COMPLETED");
				logger.info(rs.getString("service_action") + ": COMPLETED");
				continue;

				// IF ERROR, fail the test
			} else if (rs.getString("service_status").contentEquals("ERROR")) {
				throw new AssertionError("PROVISIONING ERROR: " + "\nProvisioning Service ID: "
						+ rs.getString("PF.Service.id") + "\nSubscription Service ID: " + rs.getString("SM.Service.id")
						+ "\nMSISDN: " + rs.getString("MSISDN") + "\nService Action: " + rs.getString("service_action")
						+ "\nError Code: " + rs.getString("error_code") + "\nError Description: "
						+ rs.getString("error_description"));

				// IF SENT, retry every 10 seconds. After 1 minute, fail the test for timeout
			} else if (rs.getString("service_status").contentEquals("SENT")) {
				loopStart = System.currentTimeMillis();
				loopEnd = loopStart + 120 * 1000;
				while (System.currentTimeMillis() < loopEnd) {

					// Sleep for 10 seconds and get new result set
					System.out.println("Request Status is \"SENT\", retrying in 10 seconds...");
					Thread.sleep(10000);
					rs = mariaDB.getQueryResultSet(query);
					rs.absolute(i);

					// IF still "SENT" then continue, if "COMPLETED" then break and continue to next
					// row, if "ERROR" fail test
					if (rs.getString("service_status").contentEquals("COMPLETED")) {
						System.out.println(rs.getString("service_action") + ": COMPLETED");
						logger.info(rs.getString("service_action") + ": COMPLETED");
						break;
					} else if (rs.getString("service_status").contentEquals("ERROR")) {
						System.out.println(rs.getString("service_action") + ": ERROR");
					} else {
						continue;
					}
				}
				// IF after 30 seconds (4 tries), status = "SENT" then timeout and fail test,
				if (rs.getString("service_status").contentEquals("SENT")) {
					System.out.println("TIMEOUT: FAIL TEST");
					throw new AssertionError("Timeout Error: Request is stuck in \"SENT\" status.");
				}
			}
		}
	}

}
