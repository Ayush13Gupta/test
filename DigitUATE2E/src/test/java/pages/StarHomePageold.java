package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;

public class StarHomePageold extends ReusableLibrary {
	
	public StarHomePageold(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
		
		
		
	}

	//**Locators for Add new User functionality
	
	@FindBy(id="LoginButton")
	public static WebElement Submit;
	
	@FindBy(xpath="//*[id='CPCurrentDivision']/span/span]")
	public static WebElement Division;
	
	@FindBy(xpath="//img[@id='searchButton']")
	public static WebElement srchBtn;
	
	//@FindBy(xpath="//*[@id='Menu']/li[5]/span")
	@FindBy(xpath = "//span[contains(.,'System')]")
	public static WebElement System;
	
	@FindBy(xpath="//a[@href ='/system/UserAdmin']")
	public static WebElement UserAdmin;
	
	//@FindBy(xpath="//a[text(),'User Administration']")
	//public static WebElement UserAdministration;
	
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
	
	
	//**Locators for PasswordRest
	
	@FindBy(id="PasswordTb")
	public static WebElement password;
	
	@FindBy(id="SaveButton")
	public static WebElement save;
	
	@FindBy(id="CloseButton")
	public static WebElement Close;
	
	@FindBy(xpath="//*[id='LogOutTooltip']")
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
	
	//@FindBy(xpath="//span[text(),'File']")
	@FindBy(xpath="//span[contains(.,'File')]")
	public static WebElement File;
	
	//@FindBy(xpath="//a[text(),'Data Export/Update']")
	@FindBy(xpath="//a[contains(.,'Data Export/Update')]")
	public static WebElement DataExport;
	
	@FindBy(xpath="//*[@id='TopContainer']/div[2]/span/span/span[1]")
	//@FindBy(xpath="//span[@class='k-input']/[2]")
	public static WebElement Startwotable;

	@FindBy(xpath="//*[@id='AtlasTableDdl_listbox']/li[1]")
	public static WebElement Startwotablename;
	
	@FindBy(id="DownButton")
	public static WebElement ExportToExcel;
		
	@FindBy(xpath="//div[@id,'MessageContainer']")
	public static WebElement DownlaodMessage;
	
	
	//**Locators for ImportFunctionality
	
	@FindBy(xpath="//span[text(),'Data']")
	public static WebElement Data;
	
	@FindBy(xpath="//a[text(),'Import Payroll']")
	public static WebElement Payrollimport;
	
	@FindBy(xpath="//input[@type='checkbox']")
	public static WebElement Filecheckbox;
	
	@FindBy(id="StartButton")
	public static WebElement Start;
	
	@FindBy(id="btnConfirmImport")
	public static WebElement Import;
	
	@FindBy(xpath="//div[@class='progress-bar']")
	public static WebElement ProgressBar;
	
	
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
	
	//*[@id="WeekDiv"]/span/span/span[1]
	
		
	@FindBy(xpath = "(//span[contains(.,'— Select week —')])[3]")
	public static WebElement weekbox;
		

	
	@FindBy(xpath="//*[@id='WeekDdl_listbox']/li[1]")
	public static WebElement week;
	
	
	//*[@id="TopContainer"]/div[2]/span/span/span[1]
	//*[@id="YearDdl_listbox"]/li[1]
	//*[@id="WeekDiv"]/span/span/span[1]
	//*[@id="WeekDdl_listbox"]/li[1]
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
