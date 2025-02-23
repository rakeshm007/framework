package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

// Ensure the ExtentReports library is added to the project dependencies.
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Initialize Extent Reports
    public static void setupReport() {
        // Set the path for the generated report
        htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // You can also set system info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    // Get the ExtentReports instance
    public static ExtentReports getExtent() {
        return extent;
    }

    // Set the ThreadLocal ExtentTest instance for thread safety (for parallel execution)
    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    // Get the ThreadLocal ExtentTest
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    // Flush the report to save it
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}