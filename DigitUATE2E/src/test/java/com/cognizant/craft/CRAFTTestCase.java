package com.cognizant.craft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cognizant.framework.DataBaseOperation;
import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Settings;
import com.cognizant.framework.TestCaseBean;
import com.cognizant.framework.Util;
import com.cognizant.framework.selenium.ResultSummaryManager;
import com.cognizant.framework.selenium.SeleniumTestParameters;

import supportLibraries.Utility_Functions;
import supportLibraries.XmlGenerator;

/**
 * Abstract base class for all the test cases to be automated
 * 
 * @author Cognizant
 */
public abstract class CRAFTTestCase {
    /**
     * The current scenario
     */
    protected String currentScenario;
    /**
     * The current test case
     */

    /**
     * The {@link Properties} object with settings loaded from the framework
     * properties file
     */
    protected Properties properties;
    protected String currentTestcase;

    private ResultSummaryManager resultSummaryManager = ResultSummaryManager.getInstance();

    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReport;
    protected static ExtentTest extentTest;

    protected static String TEST_STATUS;

    public static int countReRunFailedTestCase = 1;
    public static boolean rerun = false;
    public static int rerunCounter = 0;
    public static boolean sResponseMessage;

    public static int getCounter() {
	return rerunCounter;
    }

    public static void updateCounter() {
	rerunCounter++;
    }

    public static boolean getRerun() {
	return rerun;
    }

    public static void toggleRerun() {
	rerun = !rerun;
    }

    public static void updateReRun() {
	rerun = true;
    }

    /**
     * Function to do the required framework setup activities before executing the
     * overall test suite
     * 
     * @param testContext The TestNG {@link ITestContext} of the current test suite
     */
    @BeforeSuite
    public void setUpTestSuite(ITestContext testContext) {
	resultSummaryManager.setRelativePath();
	resultSummaryManager.initializeTestBatch(testContext.getSuite().getName());

	int nThreads;
	if ("false".equalsIgnoreCase(testContext.getSuite().getParallel())) {
	    nThreads = 1;
	} else {
	    nThreads = testContext.getCurrentXmlTest().getThreadCount();
	}

	// Note: Separate threads may be spawned through usage of DataProvider
	// testContext.getSuite().getXmlSuite().getDataProviderThreadCount();
	// This will be at test case level (multiple instances on same test case
	// in parallel)
	// This level of threading will not be reflected in the summary report

	resultSummaryManager.initializeSummaryReport(nThreads);
	resultSummaryManager.setupErrorLog();
	generateExtentReports();
	generateExtentReportCI();
    }

    /**
     * Function to do the required framework setup activities before executing each
     * test case
     */
    @BeforeMethod
    public void setUpTestRunner() {
	FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
	if (frameworkParameters.getStopExecution()) {
	    tearDownTestSuite();

	    // Throwing TestNG SkipException within a configuration method
	    // causes all subsequent test methods to be skipped/aborted
	    throw new SkipException("Test execution terminated by user! All subsequent tests aborted...");
	}
    }

    /**
     * Function to do the required framework teardown activities after executing
     * each test case
     * 
     * @param testParameters The {@link SeleniumTestParameters} object passed from
     *                       the test case
     * @param driverScript   The {@link DriverScript} object passed from the test
     *                       case
     */

    public static List<String> failedTestCase = new ArrayList<String>();
    public static String currentPacakage;
    public static String currentTestCase;

    protected synchronized void tearDownTestRunner(SeleniumTestParameters testParameters, DriverScript driverScript) {
	TestCaseBean testCaseBean = new TestCaseBean();
	String testReportName = driverScript.getReportName();
	String executionTime = driverScript.getExecutionTime();
	String testStatus = driverScript.getTestStatus();

	TEST_STATUS = testStatus;// Added this code to extract test case status for linking e2e automation

	resultSummaryManager.updateResultSummary(testParameters, testReportName, executionTime, testStatus);

//	Utility_Functions.xAddJsonObj("Orders[0]", testParameters.getCurrentTestcase(),
//		resultSummaryManager.getResultPath());
//	Utility_Functions.xAddJsonObj("Orders[0]", testParameters.getCurrentTestcase() + "_Status", testStatus);
	/* DB-Updating reports to database */
	DataBaseOperation dbOperation = new DataBaseOperation();
	dbOperation.initializeTestParameters(testParameters);
	dbOperation.updateMongoDB("Run Manager", testCaseBean, executionTime, testStatus);
	currentPacakage = testParameters.getCurrentScenario();
	currentTestCase=testParameters.getCurrentTestcase();
	if ("Failed".equalsIgnoreCase(testStatus)) {
	    failedTestCase.add(
		    "testscripts." + testParameters.getCurrentScenario() + "." + testParameters.getCurrentTestcase());
	    Assert.fail(driverScript.getFailureDescription());

	}
    }

    /**
     * Function to do the required framework teardown activities after executing the
     * overall test suite
     */
    @AfterSuite
    public void tearDownTestSuite() {
	resultSummaryManager.wrapUp(true);
	extentReport.flush();
	resultSummaryManager.launchResultSummary();

	if (currentTestCase.equalsIgnoreCase("TC_01")) {

	    Utility_Functions.copyResultsIntoShareFolder("Results",
		    "\\\\\\cifs_public\\public\\00016_public\\UATResults");
	    Utility_Functions.copyResultsIntoShareFolder("src\\test\\resources\\Datatables",
		    "\\\\\\cifs_public\\public\\00016_public\\UATResults");
	}
	try {
	    if (getCounter() < Integer.parseInt(System.getProperty("ReRunTimes"))) {
		System.out.println("ReRunCount is set as per the value passed from Jenkin:::");
		updateReRun();
		updateCounter();
		XmlGenerator.reRunFailedTestCases();
	    }
	} catch (Exception e) {
	    properties = Settings.getInstance();
	    if (getCounter() < Integer.parseInt(properties.getProperty("ReRunTimes"))) {
		updateReRun();
		updateCounter();
		XmlGenerator.reRunFailedTestCases();
	    }
	}

    }

    /**
     * Function to set Extent Report Path within Framework and initialze extent
     * objects
     */
    private void generateExtentReports() {
	Properties properties = Settings.getInstance();
	htmlReporter = new ExtentHtmlReporter(resultSummaryManager.getReportPath() + Util.getFileSeparator()
		+ "Extent Result" + Util.getFileSeparator() + "ExtentReport.html");
	extentReport = new ExtentReports();
	extentReport.attachReporter(htmlReporter);
	extentReport.setSystemInfo("Project Name", properties.getProperty("ProjectName"));
	extentReport.setSystemInfo("Framework", "CRAFT Maven");
	extentReport.setSystemInfo("Framework Version", "3.2");
	extentReport.setSystemInfo("Author", "Cognizant");

	htmlReporter.config().setDocumentTitle("CRAFT Extent Report");
	htmlReporter.config().setReportName("Extent Report for CRAFT");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTheme(Theme.STANDARD);
    }
    
    private void generateExtentReportCI() {
    	Properties properties = Settings.getInstance();
    	new File("./src/test/resources" + Util.getFileSeparator() + "Extent Result" + Util.getFileSeparator()).mkdir();
    	htmlReporter = new ExtentHtmlReporter("./src/test/resources" + Util.getFileSeparator()
    		+ "Extent Result" + Util.getFileSeparator() + "ExtentReport.html");
    	extentReport = new ExtentReports();
    	extentReport.attachReporter(htmlReporter);
    	extentReport.setSystemInfo("Project Name", properties.getProperty("ProjectName"));
    	extentReport.setSystemInfo("Framework", "CRAFT Maven");
    	extentReport.setSystemInfo("Framework Version", "3.2");
    	extentReport.setSystemInfo("Author", "Cognizant");

    	htmlReporter.config().setDocumentTitle("CRAFT Extent Report");
    	htmlReporter.config().setReportName("Extent Report for CRAFT");
    	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    	htmlReporter.config().setTheme(Theme.STANDARD);
        }

}