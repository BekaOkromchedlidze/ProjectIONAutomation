package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ConfigDataProvider
{

	Properties properties;
	
	public ConfigDataProvider()
	{
		File src = new File("./Config/Config.properties");
		
		try
		{
			FileInputStream fis = new FileInputStream(src);
			
			properties = new Properties();
			
			properties.load(fis);
		} catch (Exception e)
		{
			System.out.println("Not able to load the Config File " + e.getMessage());
		}
	}
	
	public String getDataFromConfig(String keyToSearch) {
		if (properties.getProperty(keyToSearch).equalsIgnoreCase("null")) {
			throw new NullPointerException(keyToSearch + " property not found in Config.properties file.");
		} else {
			return properties.getProperty(keyToSearch);
		}
	}
	
	public String getE2eWordpressURL() {
		return properties.getProperty("e2eWordpressURL");
	}
	
	public String getE2eEShopURL() {
		return properties.getProperty("e2eEshopURL");
	}
}
