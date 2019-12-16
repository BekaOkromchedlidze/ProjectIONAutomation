package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneratedTestData {
	
Properties properties;
public FileInputStream fis;
public FileOutputStream out;

	public GeneratedTestData()
	{
		File src = new File("./Config/GeneratedTestData.properties");

		
		try
		{
			// Create InputStream and load the properties file
			fis = new FileInputStream(src);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e)
		{
			System.out.println("Not able to load the GeneratedTestData properties File " + e.getMessage());
		}
	}
	
	public String getData(String keyToSearch) {
		return properties.getProperty(keyToSearch);
	}
	
	public void setData(String key, String value) {
		
		try {
			// Set the new property and save the file
			FileOutputStream out = new FileOutputStream("./Config/GeneratedTestData.properties");
			properties.setProperty(key, value);
			properties.store(out, null);
			out.close();
			
			// Read newly saved file and load the properties
			File src = new File("./Config/GeneratedTestData.properties");
			fis = new FileInputStream(src);
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
