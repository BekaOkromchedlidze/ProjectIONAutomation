package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilities.ConfigDataProvider;

public class MariaDB {

	static ConfigDataProvider config = new ConfigDataProvider();
	static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	static String DB_URL;
	static String USER;
	static String PASS;


	public MariaDB() {
		// JDBC driver name and database URL
		// Database credentials
		try {
			DB_URL = config.getDataFromConfig(config.getDataFromConfig("currentEnvironment").toLowerCase() + "DB_URL");
			USER = config.getDataFromConfig(config.getDataFromConfig("currentEnvironment").toLowerCase() + "DB_USER");
			PASS = config.getDataFromConfig(config.getDataFromConfig("currentEnvironment").toLowerCase() + "DB_PASS");
		} catch (NullPointerException e) {
			throw new NullPointerException(config.getDataFromConfig("currentEnvironment") + " environment's MariaDB details not found in Config.properties file.");
		}
		
	}



	
	public ResultSet getQueryResultSet(String query) throws SQLException {
				Connection conn = null;
				Statement stmt = null;
				
					// STEP 2: Register JDBC driver

					// STEP 3: Open a connection
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
//					System.out.println("Connected to the database.");
					// STEP 4: Execute a query
					//System.out.println("Query...");
					stmt = conn.createStatement();

					// Execute the SQL Query. Store results in ResultSet
					stmt.executeQuery(query);
					ResultSet rs = stmt.executeQuery(query);
//					ResultSetMetaData md = rs.getMetaData();
		
				// STEP 3: Close resources
				try {
					if (stmt != null) {
						conn.close();
					}
				} catch (SQLException se) {
				} // do nothing
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException se) {
					se.printStackTrace();
				} 
//				System.out.println("Disconnected from the database.");
				return rs;
		
	}
	
	public int getNumberRows(ResultSet rs){
	    try{
	       if(rs.last()){
	          return rs.getRow();
	       } else {
	           return 0; 
	       }
	    } catch (Exception e){
	       System.out.println("Error getting row count");
	       e.printStackTrace();
	    }
	    return 0;
	}
	
	public String getProvisioningCheckQuery(String orderNumber){
	    String query = "Select\n" + 
				"    `eir-order-management-backend`.order_request.id As `OM.id`,\n" + 
				"    `eir-order-management-backend`.order_request.reference,\n" + 
				"    `eir-order-management-backend`.order_request.owner_uuid,\n" + 
				"    `eir-order-management-backend`.order_request.status,\n" + 
				"    `SM.service`.name As MSISDN,\n" + 
				"    `PF.service`.service_action,\n" + 
				"    `PF.service`.service_status,\n" + 
				"    `PF.service`.error_code,\n" + 
				"    `PF.service`.error_description,\n" + 
				"    `eir-product-catalog-backend`.offer.description As offer_description,\n" + 
				"    `eir-product-catalog-backend`.offer.name As offer_name,\n" + 
				"    `SM.service`.id As `SM.Service.id`,\n" + 
				"    `PF.service`.id As `PF.Service.id`,\n" + 
				"    `eir-order-management-backend`.order_request.created_date\n" + 
				"From\n" + 
				"    `eir-order-management-backend`.order_request Inner Join\n" + 
				"    `eir-order-management-backend`.service On `eir-order-management-backend`.service.order_id =\n" + 
				"            `eir-order-management-backend`.order_request.id Inner Join\n" + 
				"    `eir-subscription-management-backend`.service `SM.service` On\n" + 
				"            `SM.service`.id = `eir-order-management-backend`.service.service_id Inner Join\n" + 
				"    `eir-provisioning-facade-backend`.service `PF.service` On `PF.service`.service_id = `SM.service`.id Inner Join\n" + 
				"    `eir-product-catalog-backend`.sub_offer On `eir-product-catalog-backend`.sub_offer.id =\n" + 
				"            `SM.service`.catalog_sub_offer_id Inner Join\n" + 
				"    `eir-product-catalog-backend`.offer On `eir-product-catalog-backend`.sub_offer.offer_id =\n" + 
				"            `eir-product-catalog-backend`.offer.id\n" + 
				"            \n" + 
				"Where `eir-order-management-backend`.order_request.reference = '$orderNumber'";
	    return query.replace("$orderNumber", orderNumber);
	}
}
