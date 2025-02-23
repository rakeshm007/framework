package ui.tests;

import utils.ExtentManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestReportExample {

    @BeforeSuite
    public void setupReport() {
        ExtentManager.setupReport();
    }

    @Test
    public void sampleTest1() {
        ExtentManager.setTest(ExtentManager.getExtent().createTest("Sample Test 1"));
        ExtentManager.getTest().log(com.aventstack.extentreports.Status.PASS, "Sample Test 1 Passed");
    }

    @Test
    public void sampleTest2() {
        ExtentManager.setTest(ExtentManager.getExtent().createTest("Sample Test 2"));
        ExtentManager.getTest().log(com.aventstack.extentreports.Status.FAIL, "Sample Test 2 Failed");
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.flushReport();
    }
}