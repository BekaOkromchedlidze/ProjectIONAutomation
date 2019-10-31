package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper
{

	
	public static void captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try
		{
			FileHandler.copy(src, new File("./Screenshots/" + GetCurrentTimeStampMilliseconds() + ".png"));
			
			System.out.println("Screenshot Captured.");
		} catch (IOException e)
		{
			System.out.println("Unable to capture screenshot " + e.getMessage());
		}
	}
	
	public static String GetCurrentTimeStampMilliseconds() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(
                "yyyy-MM-dd_HH-mm-ms");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
