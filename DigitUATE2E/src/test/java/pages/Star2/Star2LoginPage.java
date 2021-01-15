package pages.Star2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;

public class Star2LoginPage extends ReusableLibrary {
	
	public Star2LoginPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
		
		
		
	}

	@FindBy(id="LoginNameTb")
	public static WebElement Email;
	

	@FindBy(id="PasswordTb")
	public static WebElement Password;
	
	@FindBy(id="LoginButton")
	public static WebElement Submit;
	
	@FindBy(xpath="//*[id='CPCurrentDivision']/span/span]")
	public static WebElement Division;
	
	@FindBy(xpath="//img[@id='searchButton']")
	public static WebElement srchBtn;
	
	

}
