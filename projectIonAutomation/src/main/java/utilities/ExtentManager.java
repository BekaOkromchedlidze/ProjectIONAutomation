package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager
{
	 private static ExtentReports report;
	    private static String reportFileName = "ExtentReport_" + Helper.GetCurrentTimeStampMilliseconds() + ".html";
	    private static String reportPath = System.getProperty("user.dir")+ "\\ExtentReport\\" + reportFileName;

	    public static ExtentReports getInstance() {
	        if (report == null)
	            createInstance();
	        return report;
	    }

	    //Create an extent report instance
	    public static ExtentReports createInstance() {
	    	System.out.println("REPORT CREATED IN: "+ reportPath);
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
		/*
		 * htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		 * htmlReporter.config().setChartVisibilityOnOpen(true);
		 * htmlReporter.config().setTheme(Theme.STANDARD);
		 * htmlReporter.config().setDocumentTitle(fileName);
		 * htmlReporter.config().setEncoding("utf-8");
		 * htmlReporter.config().setReportName(fileName);
		 */
	        report = new ExtentReports();
	        report.attachReporter(htmlReporter);

	        return report;
	    }
}
