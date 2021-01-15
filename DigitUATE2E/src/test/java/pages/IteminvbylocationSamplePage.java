package pages;

import java.awt.Menu;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.selenium.CraftDriver;

public class IteminvbylocationSamplePage extends ReusableLibrary {
	
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	
	//CraftDriver driver1;
	
	public IteminvbylocationSamplePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		//this.driver1 = super.driver;
		PageFactory.initElements(driver.getWebDriver(), this);
	}

	 
	public static By ItemInventoryByLocation=By.xpath("//b[text()='Item Inventory by Location']");
	
	
	public static By item=By.xpath("//span[text()='Item:']/parent::div/following-sibling::input[4]");
	public static By bussinessUnit=By.xpath("//select[@id='dataForm:listView:filterId:field0value1']");
	
	public static By locationClass=By.xpath("//span[text()='Location class:']/parent::div/following-sibling::select");
	public static By LocClass= By.xpath("//select[@id='locnClassfield40dc_1']");
	public static By inventorytype=By.xpath("//span[text()='Inventory type:']/parent::div/following-sibling::select");
    public static By expandButton=By.xpath("//input[@class='fltrHidden']");
    
	public static By applyButton=By.xpath("(//input[@title='Apply'])[1]");
	public static By apply=By.xpath("(//input[@title='Apply'])[2]");
	
	public static By text=By.xpath("//td[@class='advtbl_col advtbl_body_col tdshow']");
	
	public static By close=By.xpath("(//img[@class='x-tool-close'])[2]");  
	public static By pickLocations=By.xpath("(//div[@class='wt-search-item'])[1]"); 
	
	public static By quickFilter = By.xpath("//span[text()='Quick filter']"); 
	public static By saveFilter  = By.xpath("//a[@id='filterId_li1']"); 
	
	public static By location    = By.xpath("(//select[starts-with(@id,'locnClassfield40dc')])[1]"); 
	
	public static By pickDetZone=By.xpath("//select[@name='dataForm:filterDetailId:field120value1']");
	public static By applyfilter=By.xpath("//input[contains(@name,'dataForm:applyFltrBtnPopupAjax')]"); 
	
	public static By selectLocation=By.xpath("(//input[@type='checkbox'])[2]");
	public static By itemAssigntoLocation=By.xpath("//input[@value='Assign Item to Location']");
	
	public static By selectCheckbox=By.xpath("//tr[@class='advtbl_row -dg_tr']/td/input[@type='checkbox']");
	public static By adjustInventory=By.xpath("//div[@id='Actions_child']//a[@title='Adjust Inventory']");
	public static By newqnt=By.xpath("//input[@id='dataForm:NewQty']");
	
	public static By add=By.xpath("//td[@id='workareafootertd']/div/div/div/input[@value='Add']");
	public static By save=By.xpath("//td[@id='workareafootertd']/div/div/div/input[@value='Save']");
	
	public static By refresh=By.xpath("//img[@class='x-tool-refresh']");
    public static By waitforRfrsh=By.xpath("//body[contains(@style,'wait;')]");
    public static By onHandQty=By.xpath("//span[contains(@id,'onHandQuantity')]");
}
