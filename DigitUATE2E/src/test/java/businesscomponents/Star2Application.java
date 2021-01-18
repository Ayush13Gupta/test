package businesscomponents;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.craft.DriverScript;
import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.Status;


import pages.Star2.*;
import supportLibraries.Utility_Functions;

/**
 * Business Components Class for validating functionalities related to Login
 * Page
 *
 * @author Cognizant Team
 *
 */

public class Star2Application extends ReusableLibrary {
    /**
     * Constructor to initialize the component library
     *
     * @param scriptHelper The {@link ScriptHelper} object passed from the
     *                     {@link DriverScript}
     */
    public Star2Application(ScriptHelper scriptHelper) {
	super(scriptHelper);
	PageFactory.initElements(driver.getWebDriver(), this);
    }

      static String URL = "";
      
    /**
     * Validating the launching the browser functionality
     *
     * @author Cognizant Team
     *
     */
     
   Star2LoginPage starlogin = new Star2LoginPage(scriptHelper);
    Star2HomePage starhome = new Star2HomePage(scriptHelper);
    
    
    public void launchApplication1() {
    	// String environment = initializeEnvironment();

    	String App = dataTable.getData(currentDataSheet, "Application");
    	switch (App) {
    	
    	case ("STAR"):
    	    URL = properties.getProperty("StarUrl");
    	    driver.get(URL);
    	    break;
    	case ("ABC"):
    	    URL = properties.getProperty("GTNURL");
    	    driver.get(URL);
    	    break;
    	       
    	}
    	report.updateTestLog("Invoke Application", "Invoke the application under test @ " + URL, Status.PASS);

    	// driver.get(properties.getProperty("ApplicationDevr1Url"));

        }
    
    public void starLogin()
    {
		
	    	String Uname = properties.getProperty("StarLoginUserName");
	    	String pwd = properties.getProperty("StarLoginPassword");
	    	
	    	Utility_Functions.xSendKeys(Star2LoginPage.Email, Uname);
	    	Utility_Functions.xSendKeys(Star2LoginPage.Password, pwd);
	    	Utility_Functions.xClick(Star2LoginPage.Submit);
	    	
	    	//Verify if user is successfully loggedIn
	   	    	
	        Utility_Functions.xIsDisplayed(Star2HomePage.DivionText);
	    	
	    	report.updateTestLog("StarLogin", "Verify User is able to log into STAR2 application", Status.PASS);
	        	
    }
   
  
 
    public void addUserAsCorporateRole()
    {
    	String Lname = dataTable.getData("General_Data", "Loginname");
    	String pwd = dataTable.getData("General_Data", "Password");
    	
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.System,25);
    	//Utility_Functions.xMouseOver(driver,StarHomePage.System);
    	Utility_Functions.xClick(Star2HomePage.System);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.UserAdministration,25);
    	Utility_Functions.xClick(Star2HomePage.UserAdministration);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Adduser,25);
    	Utility_Functions.xClick(Star2HomePage.Adduser);
    	System.out.println("------Switching to to Frame-----");
    
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Addpopup1,25);
    	driver.switchTo().frame(Star2HomePage.Addpopup1);
      	Utility_Functions.xSendKeys(Star2HomePage.Email, Lname);
      	//Utility_Functions.xWaitForElementClickable(driver,StarHomePage.roletable,50);
    	
      	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.roletable, 25);
      	Utility_Functions.xClick(driver, Star2HomePage.roletable, true);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.CoporateRoleold,25);
	   
	   	Utility_Functions.xClick(Star2HomePage.CoporateRoleold);
	   	Utility_Functions.xSendKeys(Star2HomePage.password, pwd);
	   	Utility_Functions.xClick(Star2HomePage.save);
	   	Utility_Functions.xWaitForElementVisible(driver, Star2HomePage.UserCreatedMessage, 25);
	   	Utility_Functions.xClick(Star2HomePage.addUserClosebutton);
	   	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.userAdminClosebutton, 50);
		Utility_Functions.xMoveToElment(Star2HomePage.userAdminClosebutton,driver);
	   	Utility_Functions.xMouseClick(driver,Star2HomePage.userAdminClosebutton);

    	report.updateTestLog("Addnewuser", "Verify Add New User functionality with Corporaterole", Status.PASS);
    	
    }
    
    public void addUserAsdivisionRole()
    {
    	String Lname = dataTable.getData("General_Data", "Loginname");
    	String pwd = dataTable.getData("General_Data", "Password");
    	
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.System,25);
    	
    	Utility_Functions.xClick(Star2HomePage.System);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.UserAdministration,25);
    	Utility_Functions.xClick(Star2HomePage.UserAdministration);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Adduser,25);
    	Utility_Functions.xClick(Star2HomePage.Adduser);
    	System.out.println("-----Switching to to Frame-----");
    
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Addpopup1,25);
    	driver.switchTo().frame(Star2HomePage.Addpopup1);
      	Utility_Functions.xSendKeys(Star2HomePage.Email, Lname);
      	//Utility_Functions.xWaitForElementClickable(driver,StarHomePage.roletable,50);
    	
      	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.roletable, 50);
      	Utility_Functions.xClick(Star2HomePage.roletable);
      	Utility_Functions.xWaitForElementClickable(driver, Star2HomePage.divisionEngRole, 55);
    	Utility_Functions.xClick(Star2HomePage.divisionEngRole);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.divisionbox, 50);
    	Utility_Functions.xClick(Star2HomePage.divisionbox);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.division, 50);
    	Utility_Functions.xClick(Star2HomePage.division);
    	Utility_Functions.xSendKeys(Star2HomePage.password, pwd);
    	Utility_Functions.xClick(Star2HomePage.save); 
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.UserCreatedMessage, 50);
    	Utility_Functions.xIsDisplayed(Star2HomePage.UserCreatedMessage);
    	Utility_Functions.xClick(Star2HomePage.addUserClosebutton);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.userAdminClosebutton, 50);
	   	Utility_Functions.xClick(Star2HomePage.userAdminClosebutton);
    	//add messageContainer;
    /*	Utility_Functions.xWaitForElementVisible(driver, StarHomePage.logout, 1);
    	Utility_Functions.xClick(StarHomePage.logout);*/
    	report.updateTestLog("Addnewuser", "Verify Add New User functionality with DivisionEng Role", Status.PASS);
    	
    }
    
    public void passworResetFunctionality()
    {
    	String Lname = dataTable.getData("General_Data", "Loginname");
    	String Oldpwd = dataTable.getData("General_Data", "Password");
    	String Newpwd = dataTable.getData("General_Data", "NewPassword");
    	String Confirmpwd = dataTable.getData("General_Data", "ConfirmPassword");
    	
    	Utility_Functions.xSendKeys(Star2LoginPage.Email,Lname);
    	Utility_Functions.xSendKeys(Star2LoginPage.Password,Oldpwd);
    	Utility_Functions.xClick(Star2LoginPage.Submit);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.OldPassword, 50);
    	Utility_Functions.xSendKeys(Star2HomePage.OldPassword,Oldpwd);
    	Utility_Functions.xSendKeys(Star2HomePage.NewPassword,Newpwd);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.ConfirmPassword, 50);
    	Utility_Functions.xSendKeys(Star2HomePage.ConfirmPassword,Confirmpwd);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.PasswordButton, 50);
    	Utility_Functions.xClick(Star2HomePage.PasswordButton);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.DivionText, 50);
    	 Utility_Functions.xIsDisplayed(Star2HomePage.DivionText);
    	//Utility_Functions.xWaitForElementToBeClickable(driver, Star2HomePage.logout, 50);
    	System.out.println("----Password Reset Successfully------");
    	
    	report.updateTestLog("PasswordResetFunctionality", "Verify PasswrodRestFunctionality for the first Time login", Status.PASS);
    	
    }
    
    public void dataExportScreenValidation()
    {
    	String ExpectedValue= "Done downloading";
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.File,25);
    	Utility_Functions.xClick(driver, Star2HomePage.File);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.DataExport,25);
    	Utility_Functions.xClick(Star2HomePage.DataExport);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.Startwotable,50);
    	Utility_Functions.xClick(Star2HomePage.Startwotable);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.Startwotablename,50);
    	Utility_Functions.xClick(Star2HomePage.Starttwoname);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.ExportToExcel,25);
    	Utility_Functions.xClick(Star2HomePage.ExportToExcel);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.DownlaodMessage,50);
    	Utility_Functions.xIsDisplayed(Star2HomePage.DownlaodMessage);
    	String Actualmesage = Star2HomePage.ExportDownlaodMessage.getText();
    	
    	System.out.println("________value:"+Actualmesage+"__________");
    	
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.Close,25);
    	Utility_Functions.xClick(Star2HomePage.Close);
    	Utility_Functions.xAssertEquals(report, ExpectedValue, Actualmesage, "Assertion");
    	
    	report.updateTestLog("DataExportScreenValidation", "Verify DataExport Functionality", Status.PASS);
    }
    
    public void shipmentImportValidation()
    {
   
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.DivisionDropDown,25);
    	
    	Utility_Functions.xClick(Star2HomePage.DivisionDropDown);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Division24,25);
    	Utility_Functions.xClick(Star2HomePage.Division24);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Data,25);
    	Utility_Functions.xClick(Star2HomePage.Data);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.shipment,25);
    	Utility_Functions.xClick(Star2HomePage.shipment);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.shipmentImport,25);
    	Utility_Functions.xClick(Star2HomePage.shipmentImport);
    	
    	
    	System.out.println("*****going to click  dropdown");
    	Utility_Functions.xWaitForLoad(driver, 5);
    	Utility_Functions.xWaitForElementPresent(driver,Star2HomePage.showAllweekdropdown,80);
    	//Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.weekbox, 80);
    	Utility_Functions.xMouseDoubleClick(driver, Star2HomePage.showAllweekdropdown);
    	//Utility_Functions.xSendKeyBoardkeys(Star2HomePage.weekbox, Keys.ARROW_DOWN);
    	Utility_Functions.xMouseClick(driver, Star2HomePage.showAllweekdropdown);
    	//Utility_Functions.xClick(Star2HomePage.showAllweekdropdown);
    	System.out.println("*****clicked  dropdown");
    	Utility_Functions.xWaitForElementClickable(driver,Star2HomePage.showAllweeks,80);
    	//Utility_Functions.xClick(Star2HomePage.showAllweeks);
    	Utility_Functions.xMouseDoubleClick(driver, Star2HomePage.showAllweeks);
    	Utility_Functions.xMouseClick(driver, Star2HomePage.showAllweeks);
    	System.out.println("*****selected value from  dropdown");
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.ShipmentFile,80);
    	System.out.println("*****waiting for file");
    	Utility_Functions.xWaitForElementToBeClickable(driver,Star2HomePage.ShipmentFile,80);
    	if (Utility_Functions.xIsDisplayed(Star2HomePage.ShipmentFile))
    	{
    		
    		Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Filecheckbox,80);
    		Utility_Functions.xMouseDoubleClick(driver,Star2HomePage.Filecheckbox);
    		Utility_Functions.xMouseClick(driver,Star2HomePage.Filecheckbox);
    	    System.out.println("*****checkbox is selected");
    	}

		Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Start,80);
    	Utility_Functions.xClick(Star2HomePage.Start); 
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Import,80);
    	Utility_Functions.xClick(Star2HomePage.Import); 
    	System.out.println("*****importconfirmation clicked");
    	Utility_Functions.xIsDisplayed(Star2HomePage.ImportShipmentProgressBar);
    	Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Close,80);
    	Utility_Functions.xClick(Star2HomePage.Close); 
    	report.updateTestLog("shipmentImport", "Verify Import functionality for Shipment", Status.PASS);
   	
    	/*
    	Utility_Functions.xClick(Star2HomePage.Filecheckbox);
    	Utility_Functions.xClick(Star2HomePage.Import);
    	Utility_Functions.xClick(Star2HomePage.Start);
    	Utility_Functions.xIsDisplayed(Star2HomePage.ProgressBar);*/
    }
    
    
     public void planningSheetReportGeneration()
     {
    	 Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Planning,50);
    	 Utility_Functions.xClick(driver, Star2HomePage.Planning);
    	 Utility_Functions.xWaitForElementVisible(driver,Star2HomePage.Planningsheet,25);
    	 Utility_Functions.xClick(Star2HomePage.Planningsheet);
    	 Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.yeartable,50);
       	
    	Utility_Functions.xClick(Star2HomePage.yeartable);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.year,50);
     	Utility_Functions.xClick(Star2HomePage.year);
     	Utility_Functions.xWaitForLoad(driver,5);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.weekbox,80);
    	//Utility_Functions.xMouseDoubleClick(driver, Star2HomePage.weekbox);
    	//Utility_Functions.xSendKeyBoardkeys(Star2HomePage.weekbox, Keys.ARROW_DOWN);
    	Utility_Functions.xMouseClick(driver, Star2HomePage.weekbox);
    	Utility_Functions.xMouseClick(driver, Star2HomePage.weekbox);
    	//Utility_Functions.xClick(Star2HomePage.weekbox);
    	System.out.println("************clicked weekbox");
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.week,50);
    	System.out.println("******Week Selected******");
    	Utility_Functions.xClick(Star2HomePage.week);
     	Utility_Functions.xClick(Star2HomePage.Start);
     	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.DownlaodMessage,100);
     	Utility_Functions.xIsDisplayed(Star2HomePage.DownlaodMessage);
    	Utility_Functions.xWaitForElementPresent(driver, Star2HomePage.Close,25);
     	Utility_Functions.xClick(Star2HomePage.Close);
     	
     	report.updateTestLog("PlanningSheetRport", "Verify Planningsheetreportgeneration Functionality", Status.PASS);
    	 
     }
    
    
    public void starLogout()
    {
    	System.out.println("--------------inside logout method------");
    	Utility_Functions.xWaitForElementVisible(driver, Star2HomePage.logout,80);
    	Utility_Functions.xMoveToElment(Star2HomePage.logout, driver);
    	System.out.println("--------------moved to element------");
    	Utility_Functions.xMouseClick(driver,Star2HomePage.logout);
    
    	report.updateTestLog("StarLogout", "Verify User is able to log out STAR2 application", Status.PASS);
    }    
    
    
    

    
}