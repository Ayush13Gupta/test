package testscripts;

import org.testng.annotations.Test;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.TestConfigurations;
import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.SeleniumTestParameters;

public class Star2Smoke extends TestConfigurations {

	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
			"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_E2ECreatesample(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();

		tearDownTestRunner(testParameters, driverScript);
	}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_StarLogin(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for Loging into STAR2 Application");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_AddUserCorpRole(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for Creating a new user with Corporate Role");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_AddUserDivisionEngRole(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for Creating a new user with divisionEng Role");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_PasswordReset(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for PasswordRestFunctionality");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_DataExport(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for Data Export");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_PlanningSheetReport(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for Planning Sheet Report");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
	"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
public void tC_ScanShipmentImport(SeleniumTestParameters testParameters) {
testParameters.setCurrentTestDescription("Test for Shipment Import Validation");
DriverScript driverScript = new DriverScript(testParameters);
driverScript.driveTestExecution();

tearDownTestRunner(testParameters, driverScript);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_E2ECreatePOFp(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_E2ECreatePOCmt(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_TSStoODSValidation(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	
		
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
			"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_RMSValidationPO(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS against TSS PO creation");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);

	}
	
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
			"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateASN(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for Creating a ASN from GTN");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		
		tearDownTestRunner(testParameters, driverScript);
		}
	

	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_RMSValidationASN(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS against TSS PO creation");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);

		}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateInvoice(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for Creating a Invoice from GTN against PO");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		
		tearDownTestRunner(testParameters, driverScript);
		}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerInvoiceGTNDB(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate DB values in GTN_LAND against Invoice created");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void TC_VerInvGTNDB(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate DB values in raw_land database against Invoice created manually through CSV file");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);

	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_VerCreateManualInv(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Upload Manual Invoice csv file from Entry Management application");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerManualInvRLDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in raw_land database against Invoice created manually through CSV file");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
		}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerDLTInvoiceDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in DLT database against Invoice created");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);

	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerDLTInvoiceStatusDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate status in DLT database against Invoice created");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerTransferDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS database against Transfer created");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);

	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerTransferRMSLandDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS_LAND database against Transfer created");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerTranDataDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS database against TranData table");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerTranDataRMSLandDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS_LAND database against TranData table");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerFilTranDataRMSLandDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in RMS_LAND database against TranDataFiltered table");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerInvMovDLTDB(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test to validate DB values in DLT database against Inventory_Movement table");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_executeAFJob(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Execute job from AirFlow application");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_execAFSndInvToFlxprt(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Execute job from AirFlow application");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreatePOFrstSale(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}

	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreatePOFrstSaleCMT(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateASNFirstSaleFP(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a ASN from GTN");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateInvoiceFirstSaleFP(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for Creating a Invoice from GTN against PO");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		
		tearDownTestRunner(testParameters, driverScript);
		}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateASNFirstSaleCMT(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a ASN from GTN");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateInvoiceFirstSaleCMT(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for Creating a Invoice from GTN against PO");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_ApproveFSInv(SeleniumTestParameters testParameters) {
		testParameters.setCurrentTestDescription("Test for approving the First Sale Invoice");
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_RMSValidationPOFSFP(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate DB values in RMS against TSS PO creation");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_RMSValidationPOFSCMT(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate DB values in RMS against TSS PO creation");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_IPMassEdit(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Edit Classification in IP");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_IPClassificationUpdt(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for update Classification in IP");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_IPLandDBVer(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate DB values in IP_LAND against IP application");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreatePONonFrstSale5100(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a TSS PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateMultiLinePONfs(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a multi line TSS Non first-sale PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateMultiLinePOFp(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a multi line TSS Full Package PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_CreateMultiLinePOCmt(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for Creating a multi line TSS CMT PO");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_EMVerUI(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test for UI Validation and Error Code validation of a single Entry in Entry management");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "API", dataProviderClass = TestConfigurations.class)
	public void tC_VerFPLandEntryDB(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate DB values in FP_LAND.ENTRY against Entry data");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	tearDownTestRunner(testParameters, driverScript);
	
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_VerEMEntry(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate Entry search and values against FP_LAND.ENTRY table in Entry management application");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_VerEMTblHdrs(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate UI of the tables in Entry management");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_VerEMRevalidate(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate all status change and Re-Validate functionality of single Entry in Entry management");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_VerEMAprvDrawback(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate Approve Drawback in Entry management");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
	@Test(groups = { "SMOKE", "UI", "New", "LandingPage",
		"PO" }, dataProvider = "DesktopBrowsers", dataProviderClass = TestConfigurations.class)
	public void tC_VerEMCloseDrawback(SeleniumTestParameters testParameters) {
	testParameters.setCurrentTestDescription("Test to validate Close functionality in Entry management");
	DriverScript driverScript = new DriverScript(testParameters);
	driverScript.driveTestExecution();
	
	tearDownTestRunner(testParameters, driverScript);
	}
	
*/
}
