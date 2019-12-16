package utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentTest;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Helper {

	public static void captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(src, new File("./Screenshots/" + GetCurrentTimeStampMilliseconds() + ".png"));

			System.out.println("Screenshot Captured.");
		} catch (IOException e) {
			System.out.println("Unable to capture screenshot " + e.getMessage());
		}
	}

	public static String GetCurrentTimeStampMilliseconds() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ms");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	// Check for element's visibility
	public static boolean isElementVisible(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.MILLISECONDS);

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getXpathOfWebElement(WebElement element) {
		return element.toString().substring(element.toString().indexOf("xpath: ") + 7, element.toString().length() - 1);
	}

	public static void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	public static void saveScreenshotToExtentReport(WebDriver driver, ExtentTest logger) {
		// Take Screenshot of entire page by AShot
		Screenshot entirePageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(driver);

		String base64Screenshot = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(entirePageScreenShot.getImage(), "PNG", bos);
			byte[] imageBytes = bos.toByteArray();

			Base64.Encoder encoder = Base64.getEncoder();
			base64Screenshot = encoder.encodeToString(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Take base64Screenshot screenshot.
		try {
//			base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			logger.addScreenCaptureFromBase64String(base64Screenshot);
		} catch (NullPointerException e) {
			System.out.println("Driver not initialised, Screenshot not captured.");
		}
	}

	public static String getBase64Screenshot(WebDriver driver) {
		// Take Screenshot of entire page by AShot
		Screenshot entirePageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(driver);

		String base64Screenshot = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(entirePageScreenShot.getImage(), "PNG", bos);
			byte[] imageBytes = bos.toByteArray();

			Base64.Encoder encoder = Base64.getEncoder();
			base64Screenshot = encoder.encodeToString(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return base64Screenshot;
	}

}
