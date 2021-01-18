package pages.Star2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;

import supportLibraries.Utility_Functions;

public class Star2HomePage extends ReusableLibrary {
	
	public Star2HomePage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
		
	}
	
	
	//**Locators for Add new User functionality
	
	@FindBy(id="LoginButton")
	public static WebElement Submit;
	
	@FindBy(xpath = "//p[contains(.,'Division:')]")
	public static WebElement DivionText;
	
	@FindBy(xpath="//*[id='CPCurrentDivision']/span/span]")
	public static WebElement Division;
	
	@FindBy(xpath="//img[@id='searchButton']")
	public static WebElement srchBtn;
	
	//@FindBy(xpath="//*[@id='Menu']/li[5]/span")
	@FindBy(xpath = "//span[contains(.,'System')]")
	public static WebElement System;
	
		
	@FindBy(linkText="User Administration")
	public static WebElement UserAdministration;
		
	
	@FindBy(id="AddNewButton")
	public static WebElement Adduser;
	
	@FindBy(xpath="//*[@id='WindowPopupAdd_wnd_title']")
	public static WebElement Addpopup;
	
	@FindBy(xpath="//*[@id='WindowPopupAdd']/iframe")
	public static WebElement Addpopup1;
	
	@FindBy(xpath="//*[@id='LoginNameTb']")
	public static WebElement Email;
	
	
	@FindBy(xpath="//*[@id='TopContainer']/div[4]/span/span/span[1]")
	public static WebElement roletable;
	
	@FindBy(xpath="//*[@id='RoleDdl_listbox']/li[1]")
	public static WebElement CoporateRoleold;
	
	@FindBy(xpath="//*[@id='47aeb8ac-c8d1-42da-9a12-e81b3c6614a1']")
	public static WebElement CoporateRole;
	@FindBy(xpath="//*[@id='MessageContainer']")
	public static WebElement UserCreatedMessage;
	
		
	@FindBy(xpath="//*[@id='RoleDdl_listbox']/li[2]")
	public static WebElement divisionEngRole;
	
	@FindBy(xpath="//*[@id='DIAssignedDivId']/span/span/span[1]")
	public static WebElement divisionbox;
	
	@FindBy(xpath="//*[@id='DIAssignedDivIdDdl_listbox']/li[1]")
	public static WebElement division;
	
	@FindBy(xpath = "//button[@id='CloseButton']")
	public static WebElement addUserClosebutton;
	@FindBy(xpath = "//button[@id='CloseButton']")
	public static WebElement userAdminClosebutton;
	
	
	//**Locators for PasswordRest
	
	@FindBy(id="PasswordTb")
	public static WebElement password;
	
	@FindBy(id="SaveButton")
	public static WebElement save;
	
	@FindBy(id="CloseButton")
	public static WebElement Close;
	
	//@FindBy(xpath="//span[@id='LogOutTooltip']")
	
	//@FindBy(xpath = "//a[@id='LogOutShortcut']")
	@FindBy(xpath = "//span[@title='Log Out']")
	public static WebElement logout;
	
	
	@FindBy(id="OldPasswordTb")
	public static WebElement OldPassword;
	
	@FindBy(id="NewPasswordTb")
	public static WebElement NewPassword;
	
	@FindBy(id="ConfirmPasswordTb")
	public static WebElement ConfirmPassword;
	

	@FindBy(id="ResetPasswordButton")
	public static WebElement PasswordButton;
	
	
	
	//**Locators for DataExportScreen
	
	
	@FindBy(xpath="//span[contains(.,'File')]")
	public static WebElement File;
	
	
	@FindBy(xpath="//a[contains(.,'Data Export/Update')]")
	public static WebElement DataExport;
	
	@FindBy(xpath="//*[@id='TopContainer']/div[2]/span/span/span[1]")
	public static WebElement Startwotableold;
	@FindBy(xpath = "(//span[contains(.,'— Select table —')])[3]")
	public static WebElement Startwotable;

	@FindBy(xpath="//*[@id='AtlasTableDdl_listbox']/li[1]")
	public static WebElement Startwotablename;
	
	@FindBy(xpath = "//li[contains(.,'AccountingOperandDefaults')]")
	public static WebElement Starttwoname;
	
	@FindBy(id="DownButton")
	public static WebElement ExportToExcel;
		
	//@FindBy(xpath = "//div[contains(@id,'MessageContainer')]")
	@FindBy(xpath = "(//div[contains(.,'Done downloading')])[4]")
	public static WebElement DownlaodMessage;
	@FindBy(xpath = "//*[@id='MessageContainer']")
	public static WebElement ExportDownlaodMessage;
	
	
	//**Locators for ImportFunctionality
	
	@FindBy(xpath = "//span[contains(@class,'k-input')]")
	public static WebElement DivisionDropDown;
	@FindBy(xpath = "//li[contains(.,'24 - Haggen (Production)')]")
	public static WebElement Division24;
	
	
	@FindBy(xpath = "//span[contains(.,'Data')]")
	public static WebElement Data;
	@FindBy(xpath = "//span[contains(.,'Scan Shipments')]")
	public static WebElement shipment;
	@FindBy(xpath = "//a[@href='/Data/Import?type=1']")
	public static WebElement shipmentImport;
	//@FindBy(xpath = "(//span[contains(.,'Show only most current')])[3]")
	
	@FindBy(xpath = "//*[@id='TopGridBar']/div[1]/span[1]/span/span[1]")
		public static WebElement showAllweekdropdown;
	@FindBy(xpath = "//li[contains(.,'Show all weeks')]")
	public static WebElement showAllweeks;
	@FindBy(xpath = "//td[contains(.,'VMSHPM24_2017.DWN')]")
	public static WebElement ShipmentFile;
	@FindBy(xpath = "//td[contains(.,'VMSHPM24_2017.DWN')]")
	public static WebElement ShipmentCheckBox;
	
	
	
	
	@FindBy(xpath = "//a[@href='/Data/Import?type=2']")
	public static WebElement importShip;
	
	@FindBy(xpath = "//a[@href='/Data/Import?type=1']")
	public static WebElement importShipSubMenu;
	
	@FindBy(xpath = "//span[contains(.,'Receivings')]")
	public static WebElement importReceving;
	
	@FindBy(xpath = "//a[@href='/Data/Import?type=2']")
	public static WebElement importrecevingsubmenu;
	
	@FindBy(xpath = "//span[contains(.,'Sales/Accounting')]")
	public static WebElement importSalesAccounting;
	
	@FindBy(xpath = "(//a[contains(.,'Import')])[3]")
	public static WebElement importSales;
	
	@FindBy(xpath = "//a[@class='k-link'][contains(.,'Import Accounting Data')]")
	public static WebElement importData;
	
	@FindBy(xpath = "//a[@class='k-link'][contains(.,'Import Payroll')]")
	public static WebElement importRegisters;
	
	@FindBy(xpath = "//a[@class='k-link'][contains(.,'Import Payroll')]")
	public static WebElement Payrollimport;
	
	@FindBy(xpath="//input[@type='checkbox']")
	public static WebElement Filecheckbox;
	
	@FindBy(id="StartButton")
	public static WebElement Start;
	
	//@FindBy(id="btnConfirmImport")
	//@FindBy(xpath = "//span[contains(.,'Import Confirmation')]")
	@FindBy(xpath = "//button[@id='btnConfirmImport']")
	public static WebElement Import;
	
	//@FindBy(xpath="//div[@class='progress-bar']")
	@FindBy(xpath = "//div[contains(@class,'progress progress-striped active')]")
	public static WebElement ImportShipmentProgressBar;
	
	
	//**locators for PlanningSheet
	
	
	@FindBy(xpath="//span[contains(.,'Planning')]")
	//*[@id="Menu_mn_active"]/span
	public static WebElement Planning;
	
	@FindBy(xpath="//a[@href='/Planning/PlanningSheet']")
	public static WebElement Planningsheet;
	
	@FindBy(xpath="//*[@id='TopContainer']/div[2]/span/span/span[1]")
	public static WebElement yeartable;
	
	//*[@id="YearDdl_listbox"]/li[1]
	
	@FindBy(xpath="//*[@id='YearDdl_listbox']/li[1]")
	public static WebElement year;
	
	
	
			
	
	@FindBy(xpath = "(//span[@unselectable='on'][contains(.,'— Select week —')])[3]")
	public static WebElement weekbox;
		
	@FindBy(xpath="//li[contains(.,'21')]")
	public static WebElement week;
	
	
	//*[@id="TopContainer"]/div[2]/span/span/span[1]
	//*[@id="YearDdl_listbox"]/li[1]
	//*[@id="WeekDiv"]/span/span/span[1]
	//*[@id="WeekDdl_listbox"]/li[1]
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
