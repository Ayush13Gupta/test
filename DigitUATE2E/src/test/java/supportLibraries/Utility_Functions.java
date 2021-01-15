package supportLibraries;

import static org.junit.Assert.assertThat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
//import org.apache.tools.ant.types.resources.selectors.InstanceOf;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.expression.spel.ast.TypeReference;
import org.testng.Assert;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Report;
import com.cognizant.framework.Status;
import com.cognizant.framework.Util;
import com.cognizant.framework.WhitelistingPath;
import com.cognizant.framework.selenium.CraftDriver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.WriteContext;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import java.util.function.Predicate;

/*import freemarker.core.ReturnInstruction.Return;
import supportlibraries.api.FileZipper;*/
@SuppressWarnings("unused")
public class Utility_Functions extends ReusableLibrary {

	public Utility_Functions(ScriptHelper scriptHelper) {
		super(scriptHelper);

	}

	/*
	 * ******************************************************************* Function
	 * Name: xHighlight Author : Cognizant Team Highlight the element Parameters:
	 * driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	/*
	 * ******************************************************************* Function
	 * Name: Random String Function Author : Cognizant Team Purpose :
	 * ******************************************************************
	 */
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static boolean collapseicon(CraftDriver driver, String xpath) {
		try {
			List<WebElement> expandicons = Utility_Functions.findElementsByCSSSelector(driver, xpath);
			System.out.println(expandicons.size());
			int expandicons_count = expandicons.size();
			for (int i = expandicons_count - 1; i >= 0; i--) {
				expandicons.get(i).click();
				expandicons = Utility_Functions.findElementsByCSSSelector(driver, xpath);
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i).toLowerCase(), rs.getString(i));
			}
			list.add(row);
		}

		return list;
	}

	

	public static List<HashMap<String, Object>> convertResultSetToListN(ResultSet rs, List<String> columns) throws SQLException {
		//ResultSetMetaData md = rs.getMetaData();
		//int columns = md.getColumnCount();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>();
			for (int i = 1; i <= columns.size(); ++i) {
				row.put(columns.get(i-1), rs.getString(i));
			}
			list.add(row);
		}

		return list;
	}
	public static List<Object> convertResultSetToList(Statement stmt, String sql) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		List<Object> list = new ArrayList<Object>();

		while (rs.next()) {

			list.add(rs.getString("item_id"));
		}

		return list;
	}

	public static Date convertStrToDate(String date) {
		SimpleDateFormat stf = new SimpleDateFormat("MM/dd/yyyy");
		Date val = null;
		try {
			val = stf.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return val;
	}

	public static void copyResultsIntoShareFolder(String srcdir, String destdir) {

		String reportPath = System.getProperty("user.dir");
		reportPath = reportPath + "\\" + srcdir;
		File srcDir = new File(reportPath);
		File destDir = new File(destdir);
		try {
			FileUtils.copyDirectoryToDirectory(srcDir, destDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String DateAdd(String effectiveDate, int days) {
		// effectiveDate = effectiveDate.split("T")[0];
		DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			// Date date = new
			// SimpleDateFormat("yyyy-M-d").parse(effectiveDate);
			// effectiveDate = df1.format(effectiveDate);
			c.setTime(df1.parse(effectiveDate));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		c.add(Calendar.DATE, days);
		return df1.format(c.getTime());
	}
	
	@SuppressWarnings("deprecation")
	public static String DateAdd(String effectiveDate, int days, String dateFormat) {
		// effectiveDate = effectiveDate.split("T")[0];
//		DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat  df2 = new SimpleDateFormat(dateFormat);
		String newDate= null;
		String newt= null;
		
		Calendar c = Calendar.getInstance();
		try {
			// Date date = new
			// SimpleDateFormat("yyyy-M-d").parse(effectiveDate);
			// effectiveDate = df1.format(effectiveDate);
			c.setTime(df2.parse(effectiveDate));
			c.add(Calendar.DATE, days);
			newDate= df2.format(c.getTime());
//			Date dtDob = new Date(nextDay);
//			newDate = sdf.format(dtDob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	return newDate;
	}

	public static String dateAddition(String date_claim, int days) {
		// effectiveDate = effectiveDate.split("T")[0];
		DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			// Date date = new
			// SimpleDateFormat("yyyy-M-d").parse(effectiveDate);
			// effectiveDate = df1.format(effectiveDate);
			c.setTime(df1.parse(date_claim));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		c.add(Calendar.DATE, days);

		System.out.println(df1.format(c.getTime()));

		return df1.format(c.getTime());
	}

	public static boolean findElementBylinkText(CraftDriver driver, String element_linkText) {
		int count = driver.findElements(By.linkText(element_linkText)).size();
		if (count == 0) {
			System.out.println("The element is not found " + element_linkText);
			return false;
		}

		System.out.println("The element is present " + element_linkText);
		return true;
	}

	public static WebElement findElementByXpath(CraftDriver driver, String element_xpath) {
		xWaitForElementPresent(driver, By.xpath(element_xpath), 2);
		return driver.findElement(By.xpath(element_xpath));
	}

	public static WebElement findElementByXpath(CraftDriver driver, String element_xpath, int timewait) {
		xWaitForElementPresent(driver, By.xpath(element_xpath), timewait);
		return driver.findElement(By.xpath(element_xpath));
	}

	/**
	 * Method to find element by xpath String
	 *
	 * @param driver            Pass driver instance
	 * @param webElenmentString pass string value of element
	 * @param toBeReplced       pass String to be replaced within WebElmentString
	 * @param replacedBy        Pass string which will replace
	 * @return
	 */
	public static WebElement findElementByXpath(CraftDriver driver, String webElenmentString, String toBeReplced,
			String replacedBy) {
		try {
			By el = By.xpath(webElenmentString.replace(toBeReplced, replacedBy));
			xWaitForElementPresent(driver, el, 80);
			xWaitForElementClickable(driver, el, 10);
			return driver.findElement(el);
		} catch (Exception e) {
			return null;
		}

	}

	public static WebElement findElementByXpath(CraftDriver driver, String webElenmentString, String toBeReplced,
			String replacedBy, int timewait) {
		try {
			By el = By.xpath(webElenmentString.replace(toBeReplced, replacedBy));
			xWaitForElementPresent(driver, el, timewait);
			return driver.findElement(el);
		} catch (Exception e) {

			return null;
		}

	}

	public static boolean findElementByXpath(Report report, CraftDriver driver, String element_xpath) {
		int count = 0;
		count = driver.findElements(By.xpath(element_xpath)).size();
		if (count == 0) {
			System.out.println("The element is not found " + element_xpath);
			return false;
		}
		System.out.println("The element is present" + element_xpath);
		return true;
	}

	public static List<WebElement> findElementsByCSSSelector(CraftDriver driver, String xpath) {
		try {
			return driver.findElements(By.cssSelector(xpath));
		} catch (Exception e) {
			System.out.println("Elements not found " + xpath);
			return null;
		}

	}

	// public static boolean loginToPage(CraftDriver driver, WebElement
	// UserName,
	// WebElement Password, )
	public static List<WebElement> findElementsByTagName(WebElement Ele, String tagName) {
		try {
			return Ele.findElements(By.tagName(tagName));
		} catch (Exception e) {
			System.out.println("Elements not found with tag name" + tagName);
			return null;
		}

	}

	/*
	 * ******************************************************************* Function
	 * Name: inputData Author : Cognizant Team Inputs the data: tagID, value
	 * ******************************************************************
	 */

	public static List<WebElement> findElementsByXpath(CraftDriver driver, String element_xpath) {

		return driver.findElements(By.xpath(element_xpath));
	}

	/*
	 * ******************************************************************* Function
	 * Name: findWebElementByClassName Author : CBRE SF Automation Purpose : Element
	 * finder by ClassName
	 * ******************************************************************
	 */
	public static WebElement findWebElementByClassName(CraftDriver driver, String string) {
		return driver.findElement(By.className(string));
	}

	// ******************************************************************
	/*
	 * Function Name: findWebElementById Author : Cognizant Team Element finder by
	 * ID ******************************************************************
	 */
	public static WebElement findWebElementById(CraftDriver driver, String string) {
		return driver.findElement(By.id(string));
	}

	/*
	 * ******************************************************************* Function
	 * Name: findWebElementByName Author : CBRE SF Automation Purpose : Element
	 * finder by Name
	 * ******************************************************************
	 */
	public static WebElement findWebElementByName(CraftDriver driver, String string) {
		return driver.findElement(By.name(string));
	}

	/**
	 * Method to get Attribute value from a List of Hash Map of WebElement
	 *
	 * @param elmentList pass a list List<HashMap<String, WebElement>>
	 * @param rowNum     Pass List Index number
	 * @param columName  Pass Key Value of Hash Map
	 * @param attr       Pass Attribute Name e.g. value, title
	 * @return
	 */
	public static String getAttrVal(List<HashMap<String, WebElement>> elmentList, int rowNum, String columName,
			String attr) {

		String val = elmentList.get(rowNum).get(columName).getAttribute(attr);
		return val;
	}

	/**
	 * Function to get column Name the their Index For CheckBox column Name is
	 * default CheckBox
	 *
	 * @param tbHeaderXpath Pass table Xpath
	 * @return columNameIndex
	 */
	public static HashMap<String, String> getColumnIndex(CraftDriver driver, String tbHeaderXpath) {

		List<WebElement> tbcolumn = Utility_Functions.findElementsByXpath(driver, tbHeaderXpath + "/td/child::*");
		HashMap<String, String> columNameIndex = getWebElNameNdIndex(tbcolumn, 1);
		return columNameIndex;

	}
	public static String getpageTitle(CraftDriver driver) {
	
		
		return driver.getTitle();
	}
	
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static ArrayList<String> getDataFromList(List<HashMap<String, String>> dataList, String srcTypKey,
			String srchTypVal, String keyName) {

		ArrayList<String> ar = new ArrayList<String>();
		for (HashMap<String, String> map : dataList) {
			if (map.get(srcTypKey).toUpperCase().contains(srchTypVal.toUpperCase())) {
				String val = map.get(keyName);
				ar.add(val);

			}

		}
		return ar;

	}

	public static List<HashMap<String, String>> getDataListFromList(List<HashMap<String, String>> dataList,
			String specificSrchKey, String srchTypVal) {
		List<HashMap<String, String>> iList = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> map : dataList) {
			if (map.get(specificSrchKey).toUpperCase().contains(srchTypVal.toUpperCase())) {
				iList.add(map);
			}

		}
		return iList;

	}

	public static List<HashMap<String, String>> getDataListFromList(List<HashMap<String, String>> dataList,
			String specificSrchKey, String srchTypVal, String specificSrchKey1, String srchTypVal1) {
		List<HashMap<String, String>> iList = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> map : dataList) {
			if (map.get(specificSrchKey).toUpperCase().contains(srchTypVal.toUpperCase())
					&& map.get(specificSrchKey1).toUpperCase().contains(srchTypVal1.toUpperCase())) {
				iList.add(map);
			}

		}
		return iList;

	}

	public static WebElement getElmentFromList(List<HashMap<String, WebElement>> elmentList, int rowNum,
			String columName) {
		WebElement val = elmentList.get(rowNum).get(columName);
		return val;
	}

	/*
	 * Function will fetch the data form validation board and retrieve all the
	 * validation present with validation id
	 */
	public static List<List<String>> getErrorTableData(CraftDriver driver) {
		Utility_Functions.xWaitForElementDisappear(driver, By.cssSelector("span[id *='status.start']"), 60);
		String xpathToTable = "//table[@id='q2iForm:validationTableRndr']/tbody/tr";
		List<List<String>> errorTable = new ArrayList<List<String>>();
		int rowCount = driver.findElements(By.xpath(xpathToTable)).size();
		int columnCount = driver.findElements(By.xpath(xpathToTable + "[1]/td")).size();
		for (int i = 1; i <= rowCount; i++) {
			ArrayList<String> row = new ArrayList<String>();
			for (int j = 1; j <= columnCount; j++) {
				String xpathToElement = xpathToTable + "[" + i + "]/td[" + j + "]";
				String value;
				List<WebElement> rowValue = driver.findElements(By.xpath(xpathToElement));
				if (j == 1) {

					value = driver.findElements(By.xpath(xpathToElement + "/img")).get(0).getAttribute("src")
							.toString();
					if (value.contains("validation"))
						value = value.substring(value.lastIndexOf('-') + 1, value.lastIndexOf('.'));
					else
						value = "10";

				} else
					value = rowValue.get(0).getText();
				row.add(value);
			}
			errorTable.add(row);
		}

		return errorTable;
	}

	public static String getSelectedText(WebElement e) {
		Select dropdown = new Select(e);
		if (dropdown.getOptions().size() <= 0) {
			timeWait(1);
		}
		return dropdown.getFirstSelectedOption().getText();
	}

	/**
	 * Funtion ot Get properties value from DBQueries property fiile
	 *
	 * @param queryName pass name {Look into DBQueries file}
	 * @return query String
	 */
	public static String getSQlQuery(String queryName) {

		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();

		if (frameworkParameters.getRelativePath() == null) {
			throw new FrameworkException("FrameworkParameters.relativePath is not set!");
		}

		Properties properties = new Properties();

		try {
			String encryptedGlobalPropertiesPath = WhitelistingPath
					.cleanStringForFilePath(frameworkParameters.getRelativePath() + Util.getFileSeparator() + "src"
							+ Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources"
							+ Util.getFileSeparator() + "DBQueries.properties");
			properties.load(new FileInputStream(encryptedGlobalPropertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("FileNotFoundException while loading the DBQueries Settings file");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("IOException while loading the DBQueries Settings file");
		}

		return properties.getProperty(queryName);
	}

	/**
	 * Function to get all WebElments of a Table
	 *
	 * @param rowcount       Number of Row which are not empty
	 *                       {@link getTblRowCount}
	 * @param tblXpathString Pass String of table header xpath
	 * @param tblRow         Pass String of Table row xpath
	 * @param columIndex     Pass HashMap of Column and index {@link getColumnIndex}
	 * @return
	 *
	 */
	public static List<HashMap<String, WebElement>> getTblElments(CraftDriver driver, int rowcount,
			String tblXpathString, String tblRow, HashMap<String, String> columIndex) {
		List<HashMap<String, WebElement>> tbleElments = new ArrayList<HashMap<String, WebElement>>();

		for (int i = 0; i <= rowcount; i++) {
			HashMap<String, WebElement> rowElements = new HashMap<String, WebElement>();
			for (String key : columIndex.keySet()) {
				String tbleRow = tblXpathString + tblRow.replace("_rNum", Integer.toString(i + 1))
						+ "/td[_cNum]/child::*".replace("_cNum", columIndex.get(key));

				List<WebElement> wbe = Utility_Functions.findElementsByXpath(driver, tbleRow);
				for (WebElement e : wbe) {

					String atr = e.getAttribute("type");
					if (e.getTagName().equals("input") && (e.getAttribute("type").contains("text")
							|| e.getAttribute("type").contains("checkbox"))) {

						WebElement el = Utility_Functions.findElementByXpath(driver,
								tbleRow.replace("*", "input[@type='" + atr + "']"));

						rowElements.put(key, el);

					} else if (e.getTagName().equals("a")) {

						WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "a"));

						rowElements.put(key + "a", el);
					} else if (e.getTagName().equals("img")) {
						WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "img"));

						rowElements.put(key + "img", el);
					} else if (e.getTagName().equals("select")) {
						WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "select"));

						rowElements.put(key, el);
					}

					else if (e.getTagName().equals("text()")) {
						WebElement el = Utility_Functions.findElementByXpath(driver, tbleRow.replace("*", "text()"));

						rowElements.put(key, el);
					}

				}

			}
			if (!rowElements.isEmpty()) {
				tbleElments.add(rowElements);

			}

		}
		return tbleElments;

	}

	/**
	 * Method to Get total Row number which has data, method will append string to
	 * get column value //*[@src or @keyinfo or((@value or @title)
	 * and @type!='hidden')]" in xpath to check row count *
	 *
	 * @param driver
	 * @param tbleColumXpathStr Pass Table Column Xpath string
	 * @return
	 */
	public static int getTblRowCount(CraftDriver driver, String tbleColumXpathStr) {
		tbleColumXpathStr = tbleColumXpathStr + "//*[@keyinfo or((@value or @title) and @type!='hidden')]";
		List<WebElement> tbRows = Utility_Functions.findElementsByXpath(driver, tbleColumXpathStr);
		return tbRows.size();

	}

	public static String getText(CraftDriver driver, By element) {
		return driver.findElement(element).getText();
	}

	/*
	 * ******************************************************************* Function
	 * Name: xSelectRadio Author : Cognizant Team Selects the radio button : driver,
	 * webelement ******************************************************************
	 */

	public static String getText(CraftDriver driver, WebElement element) {
		xHighlight(driver, element, "red");
		return (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();", element);

	}

	public static String getText(HashMap<String, WebElement> elements, String objName, String attributeName) {
		return elements.get(objName).getAttribute(attributeName);
	}

	public static String getText(WebElement element) {
		return element.getText().trim();
	}

	public static String getText(WebElement element, CraftDriver driver) {
		xScrollIntoView(driver, element);
		xHighlight(driver, element, "green");
		return element.getText().trim();
	}

	public static String getText(WebElement element, String Attribute) {
		try {
			return element.getAttribute(Attribute);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(xExptnsMsg(e.getMessage()));
			return null;
		}

	}

	public static String getValFromList(List<HashMap<String, String>> dataList, String srcTypKey, String srchTypVal,
			String keyName) {
		String val = "";
		for (HashMap<String, String> map : dataList) {
			if (map.get(srcTypKey).toUpperCase().contains(srchTypVal.toUpperCase())) {
				val = map.get(keyName);
				break;
			}

		}
		return val;

	}

	public static HashMap<String, String> getWebElNameNdIndex(List<WebElement> listWebEl, int startIndex) {
		int i = startIndex;
		HashMap<String, String> columNameIndex = new HashMap<String, String>();
		int lastIndex = 0;
		for (WebElement webElement : listWebEl) {

			String tagname = webElement.getTagName();
			if (webElement.getTagName().contains("input")) {
				columNameIndex.put("CheckBox", Integer.toString(i));
				i++;
			} else if (webElement.getTagName().contains("label")) {
				columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
				i++;
			} else if (webElement.getTagName().contains("span")) {
				columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
				i++;
			} else if (webElement.getTagName().contains("h3")) {
				columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
				i++;
			} else if (webElement.getTagName().contains("th")) {
				columNameIndex.put(Utility_Functions.getText(webElement).trim(), Integer.toString(i));
				i++;
			}
		}
		return columNameIndex;
	}

	public static void HtmlZoomOut(CraftDriver driver, int zoomOutlevel) {
		WebElement html = driver.findElement(By.tagName("html"));
		// html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
		for (int i = 0; i < zoomOutlevel; i++) {
			html.sendKeys(Keys.chord(org.openqa.selenium.Keys.CONTROL, org.openqa.selenium.Keys.SUBTRACT));
		}
		if (zoomOutlevel == 0)
			html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
		Utility_Functions.timeWait(1);
	}

	// implicit wait
	public static void impWait(CraftDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public static boolean isAlert(CraftDriver driver) {

		try {

			Alert al = driver.switchTo().alert();
			String a = al.getText();
			System.out.println(a);
			al.accept();
			return true;

		} catch (NoAlertPresentException e) {

			return false;

		}

	}

	/**
	 * Method to Accept Alert
	 *
	 * @param driver
	 * @return
	 */
	public static String isAlertPresent(CraftDriver driver) {

		try {

			Alert al = driver.switchTo().alert();
			String a = al.getText();
			System.out.println(a);
			al.accept();
			return a;

		} catch (NoAlertPresentException e) {

			return null;

		}

	}

	public static boolean isAttributePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * Utility method to check whether element is exist
	 *
	 * @param driver
	 * @param el     Pass By element
	 * @return
	 */
	public static Boolean isExist(CraftDriver driver, By el) {

		try {
			driver.findElement(el);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Element Not exist");
			return false;

		}
	}

	/*
	 * Function to Navigate to the top menu with Hover properties*
	 */
	public static boolean navigateToPage_using_TopMenu(CraftDriver driver, WebElement Element1, WebElement Element2) {
		xmouseOver(driver, Element1);
		Utility_Functions.timeWait(5);
		Utility_Functions.xSendKeys(driver, Element2, Keys.ENTER);
		return true;
	}

	public static int NumofDigits(int Number) {
		int Count = 0;

		for (Count = 0; Number > 0; Number = Number / 10) {
			Count = Count + 1;
		}
		return Count - 1;
	}

	public static void Onkeydown(WebElement el) {
		el.sendKeys(Keys.ARROW_DOWN);

	}

	public static String parseDateFormat(String inDate) {
		String Date = inDate;
		// System.out.println(Date.length());
		if (Date.length() <= 8) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = new SimpleDateFormat("MM/dd/yy").parse(inDate);
				Date = dateFormat.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Date;
	}
	/*
	 * @Purpose : Find Element by CSS Selector function
	 */

	public static Map<String, String> parseUrlEncodedString(String val) {
		Map<String, String> mapVal = new HashMap<String, String>();
		String[] splitStrng = val.split("&");
		for (String string : splitStrng) {
			String[] str = string.split("=");
			mapVal.put(str[0], str.length > 1 ? str[1] : "");

		}

		return mapVal;
	}

	/*
	 * Web Element detail sniffer: give it the same xpath to get details
	 */
	public static void printElementDetailsByXPath(CraftDriver driver, String xpath) {
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		if (list.size() > 0) {
			WebElement tar = list.get(0);
			System.out.println("[" + xpath + "]:\nEnabled:" + tar.isEnabled() + "\tSelected:" + tar.isSelected()
					+ "\tDisplayed:" + tar.isDisplayed() + "\tSize:" + list.size());
		} else {
			System.out.println("Could not find any element based on XPath:[" + xpath + "]");
		}
	}

	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.####");
		return Double.valueOf(twoDForm.format(d));
	}

	public static StringSelection setClipBoradData(String str) {
		StringSelection ss = new StringSelection(str);
		// copy the above string to clip board
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		return ss;

	}

	/*
	 * ******************************************************************* Function
	 * Name: timeWait, impWait, xWaitForElementVisible, xWaitForElementPresent,
	 * xWaitForElementClickable, xWaitForElementAttribute Author : Team Cognizant :
	 * Hover and action Parameters: webelement, driver
	 * ******************************************************************
	 */
	// Static wait for specified time
	public static void timeWait(int time) {
		try {
			long result = time * 1000;
			Thread.sleep(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void validateAndClickInformation(CraftDriver driver, String ExpectedValidationText) {
		Utility_Functions.xWaitForElementDisappear(driver, By.cssSelector("span[id *='status.start']"), 60);
		String xpathToTable = "//table[@id='q2iForm:validationTableRndr']/tbody/tr/td[2]";
		List<WebElement> informations = driver.findElements(By.xpath(xpathToTable));
		boolean found = false;
		for (WebElement info : informations) {
			System.out.println(info.getText());
			if (info.getText().contains(ExpectedValidationText)) {
				found = true;
				xClick(driver, info, false);
				Utility_Functions.xWaitForElementDisappear(driver, By.id("workbench:status.start"), 30);
				break;
			}
		}

		if (!found) {
			System.out.println("unable to find validations");
		}

	}

	public static boolean validateErrorMessage(CraftDriver driver) {
		Utility_Functions.xWaitForElementDisappear(driver, By.cssSelector("span[id *='status.start']"), 60);
		List<List<String>> messageTable = getErrorTableData(driver);
		int errorCount = 0;
		for (int i = 0; i < messageTable.size(); i++) {
			if (Integer.parseInt(messageTable.get(i).get(0)) >= 200) {
				errorCount++;
			} else {

			}

			// }
		}
		if (errorCount >= 1) {
			System.out.println("Failed with more than 1 validation error");
			return false;
		} else {
			return true;
		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: validateFieldMatch Author : Cognizant Team Validates field match:
	 * driver, webelement, expression value
	 * ******************************************************************
	 */
	public static boolean validateFieldMatch(CraftDriver driver, WebElement tar, String expValue) {
		String val = tar.getAttribute("value");
		if (val.equalsIgnoreCase(expValue)) {
			xHighlight(driver, tar, "green");
			return true;
		} else {
			xHighlight(driver, tar, "red");
			return false;
		}
	}

	public static boolean validateFieldMatch(Report report, WebElement tar, String expValue) {
		String val = tar.getAttribute("value");
		if (val.equalsIgnoreCase(expValue)) {
			report.updateTestLog("VerifyTextContains",
					"Expected text '" + expValue + "' is matching With Actual Text '" + val + "'", Status.PASS);
			return true;
		} else {

			return false;
		}
	}

	public static boolean validateFieldMatch(WebElement tar, String expValue) {
		String val = tar.getAttribute("value");
		if (val.equalsIgnoreCase(expValue)) {

			return true;
		} else {

			return false;
		}
	}

	public static boolean validateLinks(List<WebElement> list, String webElementText) {
		for (WebElement element : list) {
			if (element.getText().equals(webElementText)) {
				return true;
			}
		}
		return false;
	}

	public static void xAddJsonObj(String path, String key, Object value) {

		File f = new File("src/test/resources/Datatables/RunTimeData.json");

		WriteContext wctx;
		try {
			wctx = JsonPath.parse(f);
			wctx.put("$." + path, key, value);

			PrintWriter pw = new PrintWriter("src/test/resources/Datatables/RunTimeData.json");
			pw.write(wctx.jsonString());
			pw.flush();
			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Function to Add Day to String Date
	 *
	 * @param days   Number of days
	 * @param action ADD/MINUS
	 * @param date   String date
	 * @return
	 */
	public static String xAddOrMinusDays(int days, String action, String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		if (action.toUpperCase().contains("ADD")) {

			LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.plusDays(days);

			return dtf.format(d).toString();
		} else {
			LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.minusDays(days);
			return dtf.format(d).toString();
		}

	}

	/**
	 * Function to Add/minus Months to String Date
	 *
	 * @param days   Number of days
	 * @param action ADD/MINUS
	 * @param date   String date
	 * @return
	 */
	public static String xAddOrMinusMonths(int months, String action, String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		if (action.toUpperCase().contains("ADD")) {

			LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.plusMonths(months);

			return dtf.format(d).toString();
		} else {
			LocalDate d = convertStrToDate(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.minusMonths(months);

			return dtf.format(d).toString();
		}

	}

	public static void xAppendJsonObj(String path, Object value) {

		File f = new File("src/test/resources/Datatables/RunTimeData.json");

		WriteContext wctx;
		try {
			ReadContext rd = JsonPath.parse(f);
			Object r = rd.read("$." + path);
			System.out.println("r " + r);
			wctx = JsonPath.parse(f);
			wctx.set("$." + path, value);
			r = rd.read("$." + path);

			PrintWriter pw = new PrintWriter("src/test/resources/Datatables/RunTimeData.json");
			pw.write(wctx.jsonString());
			pw.flush();
			pw.close();
			System.out.println(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void xCreateXmlPayLoad( String value) {

		
		try {


			PrintWriter pw = new PrintWriter("src/test/resources/Datatables/StoreCap.xml");
			pw.write(value);
			pw.flush();
			pw.close();
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void xAssertEquals(Report report, double expValue, double actualVal, double delta, String CstmMsg) {
		double del = roundTwoDecimals(Math.abs(expValue - actualVal));
		if (del == delta || del == 0.000) {
			Assert.assertEquals(expValue, expValue, CstmMsg);
			if (del > 0) {
				report.updateTestLog("Validate ", CstmMsg + "Expected text '" + Double.toString(expValue)
						+ "' is matching With Actual Text '" + Double.toString(actualVal) + "'" + "With delta: '0.0001",
						Status.PASS);
			} else {

				report.updateTestLog("Validate ", CstmMsg + "Expected text '" + Double.toString(expValue)
						+ "' is matching With Actual Text '" + Double.toString(actualVal) + "'", Status.PASS);
			}
		} else {
			Assert.assertEquals(actualVal, expValue, delta, CstmMsg);
			report.updateTestLog("Validate ", CstmMsg + "Expected text '" + Double.toString(expValue)
					+ "' is matching With Actual Text '" + Double.toString(actualVal) + "'", Status.PASS);
		}

	}

	/**
	 * Assert two String value are equal
	 *
	 * @param report   to log value in report
	 * @param expValue pass expected value
	 * @param Actual   Pass expected value
	 * @param CstmMsg  pass Custom Message
	 */
	public static void xAssertEquals(Report report, Object expValue, Object Actual, String CstmMsg) {
		Assert.assertEquals(Actual, expValue, CstmMsg);
		report.updateTestLog("xAssertEquals",
				CstmMsg + "Expected val '" + expValue + "' is matching With Actual val '" + Actual + "'", Status.PASS);

	}
	
	public static void xAssertEquals( Object expValue, Object Actual) {
		Assert.assertEquals(Actual, expValue);
		}

	/**
	 * Assert two String value are equal
	 *
	 * @param report    to log value in report
	 * @param expValue  pass expected value
	 * @param actualVal Pass expected value
	 * @param CstmMsg   pass Custom Message
	 */
	public static void xAssertEquals(Report report, String expValue, String actualVal, String CstmMsg) {
		if (actualVal.contains("$")) {
			actualVal = actualVal.replace("$", "").trim();
		}
		Assert.assertEquals(actualVal, expValue, CstmMsg);
		report.updateTestLog("VerifyVal",
				CstmMsg + " Expected text '" + expValue + "' is matching With Actual Text '" + actualVal + "'",
				Status.PASS);

	}
	public static void xAssertTrue(Report report,boolean condition,String CstmMsg) 
	{
		Assert.assertTrue(condition,CstmMsg );
		report.updateTestLog("VerifyVal",
				CstmMsg + " Expected text is matching With Actual Text ",Status.PASS);
	}
	public static void xAssertFalse(Report report,boolean condition,String CstmMsg) 
	{
		Assert.assertFalse(condition,CstmMsg );
		report.updateTestLog("VerifyVal",
				CstmMsg + " Expected text is not matching With Actual Text ",Status.PASS);
	}

	@SuppressWarnings("null")
	public static void xAssertEquals(Report report, String expValue, WebElement elmentForActualVal, String CstmMsg) {
		String actual = getText(elmentForActualVal);
		actual = (actual == null && actual.isEmpty()) ? "" : actual.trim();
		Assert.assertEquals(actual.toUpperCase(), expValue.toUpperCase(), CstmMsg);
		report.updateTestLog("Assert",
				CstmMsg + "Expected text '" + expValue + "' is matching With Actual Text '" + actual + "'",
				Status.PASS);

	}

	public static void xAssertEquals(Report report, String expValue, WebElement elmentForActualVal, String attributeNam,
			String CstmMsg) {
		String actual = getText(elmentForActualVal, attributeNam);
		actual = actual.isEmpty() ? "" : actual;
		Assert.assertEquals(actual, expValue, CstmMsg);
		report.updateTestLog("xAssertEquals",
				CstmMsg + "Expected text '" + expValue + "' is matching With Actual Text '" + actual + "'",
				Status.PASS);

	}
	


	public static void xAssertThat(Report report, Object expValue1, Object expValue2, Object actual, String CstmMsg) {
		try {
			assertThat(actual, Matchers.anyOf(Matchers.is(expValue1), Matchers.is(expValue2)));

			report.updateTestLog("xAssertThat", CstmMsg + " Expected val is '" + expValue1 + " OR " + expValue2
					+ "'  matching With Actual val '" + actual + "'", Status.PASS);

		} catch (AssertionError e) {
			report.updateTestLog("Value ", CstmMsg + actual, Status.PASS);
		}

	}
	/*
	 * ******************************************************************* Function
	 * Name: xClickButton Author : Cognizant Team Added to click on Button items of
	 * bootstrap : driver, webelement, highlight
	 * ******************************************************************
	 */

	public static void xCheckChkBxOfTbl(CraftDriver driver, List<HashMap<String, WebElement>> elmentList,
			String columName, String compareVal) {
		WebElement e = null;
		for (HashMap<String, WebElement> hashMap : elmentList) {
			String a = getText(hashMap.get(columName), "title");
			String b = getText(hashMap.get(columName), "value");
			String c = (a != null) ? a : b;
			if (c.equalsIgnoreCase(compareVal)) {
				e = hashMap.get("CheckBox");

				xClick(driver, e);
				break;
			}

		}

	}

	/**
	 * Function to Check check box of Table row when Table is divided into two
	 * tables
	 *
	 * @param elList1    List of WebElment of table which has @columName
	 *                   and @compareVal
	 * @param elList2    List of WebElment of second table that has check box
	 * @param columName  Column Header Name
	 * @param compareVal Value to search in first table
	 */
	public static void xCheckChkBxOfTbl(List<HashMap<String, WebElement>> elList1,
			List<HashMap<String, WebElement>> elList2, String columName, String compareVal) {
		WebElement e = null;
		int i = 0;
		for (HashMap<String, WebElement> hashMap : elList1) {
			String a = getText(hashMap.get(columName), "title");
			String b = getText(hashMap.get(columName), "value");
			String c = (a != null) ? a : b;
			if (c.equalsIgnoreCase(compareVal)) {
				e = getElmentFromList(elList2, i, "CheckBox");
				xClick(e);
				break;
			}
			i++;

		}

	}

	public static boolean xClick(CraftDriver driver, By el) {

		xWaitForElementClickable(driver, el, 20);
		xHighlight(driver, el, "green");
		driver.findElement(el).click();
		return true;
	}

	public static boolean xClick(CraftDriver driver, Report report, By el, String custMsg) {

		xClick(driver, el);
		report.updateTestLog("Click", custMsg, Status.PASS);
		return true;
	}

	public static void xClick(CraftDriver driver, Report report, WebElement el, String CustomMsg) {

		xWaitForElementClickable(driver, el, 10);
		xHighlight(driver, el, "yellow");
		el.click();
		report.updateTestLog("Click", CustomMsg, Status.PASS);

	}

	public static void xClick(CraftDriver driver, WebElement el) {

		xWaitForElementClickable(driver, el, 10);
		xHighlight(driver, el, "green");
		el.click();

	}

	/*
	 * ******************************************************************* Function
	 * Name: xClick Author : Cognizant Team Click on element with or without
	 * highlight Parameters: driver, webelement, highlight = true/false
	 * ******************************************************************
	 */
	public static boolean xClick(CraftDriver driver, WebElement el, boolean highlight) {

		if (highlight == true) {
			// if the element needs to be highlighted before click.
			xHighlight(driver, el, "yellow");
		}

		el.click();

		// timeWait(2);
		return true;
	}

	public static void xClick(Report report, WebElement el, String CustomMsg) {

		el.click();
		report.updateTestLog("Click", CustomMsg, Status.PASS);

	}

	/*
	 * ******************************************************************* Function
	 * Name: xClick Author : Cognizant Team Click on element with or without
	 * highlight Parameters: driver, webelement, highlight = true/false
	 * ******************************************************************
	 */
	public static boolean xClick(WebElement el) {

		el.click();

		// timeWait(2);
		return true;
	}

	/**
	 * Method to click again on parent element to get child element
	 *
	 * @param secndEl pass child element
	 * @param firstEl pass parent element
	 */
	public static void xClickAgainIfNotFound(CraftDriver driver, By secndEl, By firstEl) {
		if (!Utility_Functions.xIsDisplayed(driver.findElement(secndEl))) {
			Utility_Functions.xClick(driver.findElement(firstEl));
		}
	}

	/**
	 * Method to click again on parent element to get child element
	 *
	 * @param secndEl pass child element
	 * @param firstEl pass parent element
	 */
	public static void xClickAgainIfNotFound(WebElement secndEl, WebElement firstEl) {
		if (!Utility_Functions.xIsDisplayed(secndEl)) {
			Utility_Functions.xClick(firstEl);
		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: findWebElementBylinkText Author : Cognizant Team Purpose : Element
	 * finder by linkText
	 * ******************************************************************
	 */

	public static void xClickAltrntElmnt(CraftDriver driver, By el1, By el2) {
		try {
			driver.findElement(el1).click();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			driver.findElement(el2).click();
		}
	}
	/*
	 * ******************************************************************* Function
	 * Name: findWebElementByXpath Author : Cognizant Team : Element finder by xpath
	 * ******************************************************************
	 */

	public static void xClickAltrntElmnt(WebElement el1, WebElement el2) {
		try {
			el1.click();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			el2.click();
		}
	}
	/*
	 * ******************************************************************* Function
	 * Name: findWebElementBylinkText Author : Cognizant Team Purpose : Element
	 * finder by linkText
	 * ******************************************************************
	 */

	public static void xClickButton(CraftDriver driver, WebElement el, boolean highlight) {
		Utility_Functions.xSendKeys(driver, el, Keys.ENTER);
		try {
			Utility_Functions.xClick(driver, el, highlight);
		} catch (Exception e) {
			// Stale elements should land here and be harmless
		}
	}

	public static void xClickElementFromList(List<WebElement> WebElements, String ElementToBeClicked) {
		try {
			for (WebElement element : WebElements) {
				if (element.getText().equalsIgnoreCase(ElementToBeClicked)) {
					element.click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	 * ******************************************************************* Function
	 * Name: Verify whether the element is present from the list or not
	 * ******************************************************************
	 */

	public static String xClickgetTextofFirstElementfromList(List<WebElement> list) {
		String text = null;
		boolean isStatus = false;
		if (list.isEmpty()) {
			System.out.println("List is Empty:::");
			return null;
		} else {
			while (!isStatus) {
				for (WebElement elememt : list) {
					text = elememt.getText();
					elememt.click();
					isStatus = true;
					break;
				}
			}
			return text;
		}
	}

	public static void xClickHiddenElement(CraftDriver driver, By element) {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("arguments[0].click()", driver.findElement(element));
	}

	public static void xClickHiddenElement(CraftDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("arguments[0].click()", element);
	}

	public static void xClickIfAvlbl(CraftDriver driver, Report report, WebElement el, String cstMsg) {
		if (xIsDisplayed(el)) {
			xClick(driver, report, el, cstMsg);
		}

	}

	public static void xClickIfAvlbl(CraftDriver driver, WebElement el) {
		if (xIsDisplayed(el)) {
			xClick(driver, el);
		}

	}

	public static void xClickIfAvlbl(CraftDriver driver, By el) {

		if (xIsDisplayed(driver, el)) {
			xClick(driver, el);
		}

	}

	// ***********************************************************

	public static void xclickOnFirstElementfromList(List<WebElement> list) {
		Utility_Functions.timeWait(1);
		if (list.isEmpty()) {
			System.out.println("List is Empty:::");
			return;
		} else {
			for (WebElement elememt : list) {
				elememt.click();
				return;
			}
		}
	}

	// ***********************************************************

	// wait for the the expected xpath count to be present

	/*
	 * public void xWaitForXpathCount(CraftDriver driver, String xpath, int
	 * timeWait) { WebDriverWait wait = new WebDriverWait(driver.getWebDriver(),
	 * timeWait);
	 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	 * List<WebElement> elementsList=driver.findElements(By.xpath(xpath));
	 * while(elementsList.size()>0){ }
	 *
	 * }
	 */

	public static String xClickRandomElement(List<WebElement> list) {
		String text = null;
		boolean isStatus = false;
		Random random = new Random();
		Utility_Functions.timeWait(1);
		if (list.isEmpty()) {
			System.out.println("List is Empty:::");
			return null;
		} else {
			while (!isStatus) {
				for (WebElement elememt : list) {
					int randomValue = random.nextInt(list.size());
					text = elememt.getText();
					list.get(randomValue).click();
					isStatus = true;
					break;
				}
			}
			return text;
		}
	}

	/**
	 * Opens a new window based on click. Built on the assumption that only 1 window
	 * opens per link click
	 *
	 * @param driver     - active webdriver
	 * @param tar        - target webelement, applies xClickButton(driver,tar,true)
	 * @param waitInSecs
	 */
	public static void xClickSwitchNewWindow(CraftDriver driver, WebElement tar, int waitInSecs) {
		Set<String> baseWindows = driver.getWindowHandles();
		Utility_Functions.xClickButton(driver, tar, true);
		Utility_Functions.xWaitUntilnoOfWindows(driver, baseWindows.size() + 1, waitInSecs * 100);
		Set<String> newWindows = driver.getWindowHandles();
		for (String window : newWindows) {
			if (!baseWindows.contains(window)) {
				driver.switchTo().window(window);
				return;
			}
		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: xClickVisibleListElement Author : Cognizant Team Purpose : Click the
	 * visible element field: driver, List<WebElement>
	 * ******************************************************************
	 */
	public static void xClickVisibleListElement(CraftDriver driver, List<WebElement> eleList) {

		for (WebElement element : eleList) {
			if (element.isDisplayed()) {
				xClickHiddenElement(driver, element);
				break;
			}
		}

	}

	public static void xCloseCurrentWin(CraftDriver driver) {
		driver.close();

	}

	// Purpose: switch to windows with specific title
	public static boolean xContainsSwitch(CraftDriver driver, String title) {
		timeWait(2);
		Set<String> windows = driver.getWindowHandles();
		for (Iterator<String> i = windows.iterator(); i.hasNext();) {
			String window = i.next();
			driver.switchTo().window(window);
			if (driver.getTitle().contains(title)) {
				return true;
			}
		}
		return false;
	}

	public static boolean xContainsValue(CraftDriver driver, String title) {
		if (driver.getTitle().contains(title)) {
			return true;
		}

		return false;
	}

	/**
	 *
	 * Method to connect Oracle DB and return statement
	 *
	 * @param DBName
	 * @param UserName
	 * @param Pwd
	 * @return
	 */
	public static Statement xDBConntion(String DBName, String UserName, String Pwd) {
		String url = "";
		switch (DBName) {
		case "RMSStg":
			url = "jdbc:oracle:thin:@scan-stg01-exacs.opc.lllint.com:1521/stgmom01.phxsnrexarl.ovcnphxinv01.oraclevcn.com";
			break;
		case "OSBDev":
			url = "jdbc:oracle:thin:@stg-rac-sox-scan.lululemoninternal.com:1521/OMSSTG.lululemoninternal.com";
			break;
		case "WMSTor":
			url = "jdbc:oracle:thin:@acs-wmsto-db-01.cabxdqhbfe52.us-east-1.rds.amazonaws.com:1521/WMSTOS1";
			break;
		case "WMSCA":
			//url = "jdbc:oracle:thin:@stg-wms-scan-03.lululemoninternal.com:1521/WHSMSTG3.lululemoninternal.com";
			url = "jdbc:oracle:thin:@abs-wmsde-db-01.cl3p6bqn2pe7.us-west-2.rds.amazonaws.com:1521/WMSDELS1";
			
			break;
		case "WMSWC":
			url = "jdbc:oracle:thin:@stg-wms-scan-02.lululemoninternal.com:1521/WHSMSTG2SVC.lululemoninternal.com";
			break;
		case "WMSEC":
			url = "jdbc:oracle:thin:@stg-wms-scan.lululemoninternal.com:1521/whsmstgsvc.lululemoninternal.com";
			break;
		case "AUSDC":
			url = "jdbc:oracle:thin:@stg-wms-scan-04:1521/WHSMSTG4.lululemoninternal.com";
			break;
		case "OMSStg":
			// url =
			// "jdbc:oracle:thin:@stg-rac-sox-scan.lululemoninternal.com:1521/OMSSTG.lululemoninternal.com";
			url = "jdbc:oracle:thin:@lbs-prfomsdbscan.lululemoninternal.com:1521/OMSPERF.lululemoninternal.com";
			break;
		case "POSTgre":
//			url = "jdbc:postgresql://stg-ods.cluster-ro-clxpxja4gsqb.us-east-1.rds.amazonaws.com:5432/ods";
//			url = "jdbc:postgresql://trade-dev-ods.cluster-c5xyfrgbunvh.us-east-1.rds.amazonaws.com:5432/ods";
			//trade-qas-ods.cluster-c5xyfrgbunvh.us-east-1.rds.amazonaws.com
			url = "jdbc:postgresql://trade-qas-ods.cluster-c5xyfrgbunvh.us-east-1.rds.amazonaws.com:5432/ods";
		
			break;
		case "ReSaStg":
			url = "jdbc:oracle:thin:@scan-stg01-exacs.opc.lllint.com:1521/stgmom01.us2.oraclecloud.com";
			break;
		case "StgELA":
			url = "jdbc:oracle:thin:@stg-ela-db-scan.lululemoninternal.com:1521/elastg.lululemoninternal.com";
			break;

		default:
			break;

		}

		// Load Oracle jdbc driver
		try {
//			Class.forName("org.postgresql.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, UserName, Pwd);
			// Create Statement Object
			Statement stmt = con.createStatement();
			return stmt;
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
			return null;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	// Delete Cache
	public static void xDelCache() throws IOException {
		Runtime.getRuntime().exec("cmd /c /windows/system32/RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
	}

	public static int xElementPresentFromList(ArrayList<String> ArrayList, String ElementPresent) {
		int count = 0;
		try {
			for (int i = 0; i < ArrayList.size(); i++) {
				if (ArrayList.get(i).equals(ElementPresent)) {
					count++;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return count;
	}

	public static void xEnterText(CraftDriver driver, WebElement el, String strVal) {
		xWaitForElementClickable(driver, el, 10);
		el.click();
		el.sendKeys(strVal);
	}

	public static void xEraseField(WebElement el) {
		String entry = el.getAttribute("value");
		for (int i = 0; i < entry.length(); i++) {
			el.sendKeys(Keys.BACK_SPACE);
		}
	}

	/**
	 *
	 * Method to upload file using ROBOT class
	 *
	 * @param String
	 * @author Cognizant
	 *
	 */
	public static String xExptnsMsg(String msg) {
		try {
			String m[] = msg.split("\n");
			System.out.println(m[0]);
			return m[0];

		} catch (Exception e) {
			return e.getMessage();
		}

	}

	/**
	 * Method to First element using string and then click and log in report
	 *
	 * @param driver       Pass the Driver instance
	 * @param report       Pass report instance
	 * @param webElString  Pass String of Element for xpath
	 * @param toBeReplaced Pass String which be replaced in webElString
	 * @param replacedBy   Pass String name which will replace toBeReplaced String
	 * @param CustomMsg    pass custom Massage
	 */
	public static void xfindElAndClick(CraftDriver driver, Report report, String webElString, String toBeReplaced,
			String replacedBy, String CustomMsg) {

		WebElement el = findElementByXpath(driver, webElString, toBeReplaced, replacedBy);
		if (el != null) {
			xClick(driver, el);
			report.updateTestLog("Click", CustomMsg, Status.PASS);
		}

	}

	/**
	 * Method to First element using string and then click
	 *
	 * @param driver       Pass the Driver instance
	 * @param webElString  Pass String of Element for xpath
	 * @param toBeReplaced Pass String which be replaced in webElString
	 * @param replacedBy   Pass String name which will replace toBeReplaced String
	 * @param CustomMsg    pass custom Massage
	 */

	public static void xfindElAndClick(CraftDriver driver, String webElString, String toBeReplaced, String replacedBy) {

		WebElement el = findElementByXpath(driver, webElString, toBeReplaced, replacedBy);
		if (el != null) {
			xClick(driver, el);
		}
	}

	/**
	 * Method to First element using string and then click
	 *
	 * @param driver       Pass the Driver instance
	 * @param webElString  Pass String of Element for xpath
	 * @param toBeReplaced Pass String which be replaced in webElString
	 * @param replacedBy   Pass String name which will replace toBeReplaced String
	 * @param CustomMsg    pass custom Massage
	 */

	public static void xfindElAndClick(CraftDriver driver, String webElString, String toBeReplaced, String replacedBy,
			int waitTime) {

		WebElement el = findElementByXpath(driver, webElString, toBeReplaced, replacedBy, waitTime);
		if (el != null) {
			xClick(driver, el);
		}
	}
	/*
	 * ******************************************************************* Function
	 * Name: inputValueWithReport Author : Cognizant Team : Inputs the value with in
	 * the report: tagID, value, label
	 * ******************************************************************
	 */

	/**
	 * Method to find element using xpath String and Send Key
	 *
	 * @param driver       Driver instance
	 * @param textBoxS     xpath string of text box
	 * @param toBeReplaced tring to be replaced in typo value xpath
	 * @param replacedBy   String to by replaced by
	 * @param strVal       Input text
	 */
	public static void xfindElandSendKey(CraftDriver driver, String textBoxS, String toBeReplaced, String replacedBy,
			String strVal) {
		WebElement el = findElementByXpath(driver, textBoxS, toBeReplaced, replacedBy);
		xSendKeys(driver, el, strVal);

	}

	/**
	 * Method to find element using xpath string and then select drop down value
	 * using visibleName
	 *
	 * @param driver
	 * @param webElString
	 * @param toBeReplaced
	 * @param replacedBy
	 * @param visibleName
	 */
	public static void xFindElNdSelectDropdownByName(CraftDriver driver, String webElString, String toBeReplaced,
			String replacedBy, String visibleName) {
		if (visibleName != "") {

			WebElement e = findElementByXpath(driver, webElString, toBeReplaced, replacedBy, 10);
			if (e != null) {
				xSelectDropdownByName(driver, e, visibleName);
			}

		}
	}

	public static int xfindTbRwNumByVal(List<HashMap<String, WebElement>> elmentList, String compareVal) {
		WebElement e = null;
		int i = 0;
		for (HashMap<String, WebElement> hashMap : elmentList) {

			for (String k : hashMap.keySet()) {
				if (hashMap.get(k).getAttribute("title").equalsIgnoreCase(compareVal)
						|| hashMap.get(k).getAttribute("value").equalsIgnoreCase(compareVal)) {
					e = hashMap.get("CheckBox");
					xClick(e);
					break;
				}

			}
			i++;

		}

		return i;

	}

	public static String xformatVal(String val, String formatter) {
		val = (val == null) ? "0.0" : val;
		DecimalFormat df = new DecimalFormat(formatter);
		double d1 = Double.parseDouble((val.isEmpty()) ? formatter : val);
		return df.format((d1));
	}


	/*
	 * ******************************************************************* Function
	 * Name: xSelectDropdown,xSelectDropdownByName Author : Cognizant Team
	 * Automation Purpose : Hover and action Parameters: webelement, index
	 * ******************************************************************
	 */
	// Select dropdown by Index

	public static String xGenerateAlphaNumericString() {
		int count = 10;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String xGet4Decimal(String val) {
		DecimalFormat df = new DecimalFormat("#0.0000");
		double d1 = Double.parseDouble((val.isEmpty()) ? "0.0000" : val);
		return df.format((d1));
	}

	/**
	 * Method to Add days to System Date in MM-dd-YYYY format
	 *
	 * @param adddays
	 * @return
	 */
	public static String xgetDate(int adddays) {
		LocalDate localDate = LocalDate.now().plusDays(adddays);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");

		return dtf.format(localDate).toString();
	}

	/**
	 *
	 * Method to Connect with JSON file and return Value for specific Key
	 *
	 * @param Key Name of Key
	 * @return Object
	 */
	public static String xGetJsonData(String Key) {
		try {

			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			return (String) jo.get(Key);
		} catch (IOException e) {

			return e.getMessage();
		} catch (org.json.simple.parser.ParseException e) {

			return e.getMessage();
		}
		// return Key;

	}

	@SuppressWarnings("unchecked")
	public static List<HashMap<String, Object>> xGetJsonList(String Key) {
		try {
			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			return (List<HashMap<String, Object>>) jo.get(Key);

		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> xGetJsonMap(String Key) {
		try {
			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			return (HashMap<String, String>) jo.get(Key);

		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public static String xGetJsonMapKey(String Key, String mapKey) {
		try {
			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;

			HashMap<String, String> jmap = new HashMap<String, String>();
			jmap = (HashMap<String, String>) jo.get(Key);

			return jmap.get(mapKey);

		} catch (Exception e) {
			return null;
		}

	}

	public static String xGetTextofFirstElementfromList(List<WebElement> list) {
		String text = null;
		boolean isStatus = false;
		if (list.isEmpty()) {
			System.out.println("List is Empty:::");
			return null;
		} else {
			while (!isStatus) {
				for (WebElement elememt : list) {
					text = elememt.getText();
					isStatus = true;
					break;
				}
			}
			return text;
		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: xClickVisibleListElement Author : Cognizant Team Purpose : Click the
	 * visible element field: driver, List<WebElement>
	 * ******************************************************************
	 */
	public static String xGetTextVisibleListElement(CraftDriver driver, List<WebElement> eleList) {

		for (WebElement element : eleList) {
			if (element.isDisplayed()) {
				// xClick(driver, element, true);

				return element.getText();
			}
		}
		return null;

	}

	public static void xHighlight(CraftDriver driver, By element, String color) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", driver.findElement(element),
					"border: 2px solid " + color + ";");

		}
	}

	public static void xHighlight(CraftDriver driver, WebElement element, String color) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"border: 2px solid " + color + ";");

		}
	}

	public static boolean xHoverElementclicks(WebElement el, CraftDriver driver) {
		Actions builder = new Actions(driver.getWebDriver());
		builder.moveToElement(el).build().perform();
		timeWait(2);
		// builder.click().perform();
		return true;
	}

	public static boolean xHoverElementClk(WebElement el, CraftDriver driver) {

		Actions builder = new Actions(driver.getWebDriver());
		builder.moveToElement(el).click().perform();
		return true;
	}

	public static boolean xHoverElementDblClk(WebElement el, CraftDriver driver) {

		Actions builder = new Actions(driver.getWebDriver());
		builder.moveToElement(el).doubleClick().perform();
		timeWait(1);
		return true;
	}

	public static boolean xHoverElementInvisibleclicks(WebElement el, CraftDriver driver) {
		Actions builder = new Actions(driver.getWebDriver());
		builder.moveToElement(el).build().perform();
		timeWait(2);
		builder.click().perform();
		return true;
	}
	public static boolean actionKey(Keys key, CraftDriver driver) {
		Actions builder = new Actions(driver.getWebDriver());
		builder.sendKeys(key).build().perform();
		timeWait(2);
		
		return true;
	}
	/**
	 * Method : If Element is visible then Click
	 *
	 * @param el
	 * @return
	 */
	public static Boolean xIfEldisplayClick(CraftDriver driver, WebElement el) {
		try {
			if (el.isDisplayed()) {

				xClick(el);
				System.out.println("Element  found");
				return true;
			} else {
				System.out.println("Element not found");
				return false;

			}
		} catch (NoSuchElementException e) {

			System.out.println("Element not found");
			return false;
		}
	}

	public static Boolean xIsDisplayed(CraftDriver driver, By el) {
		try {
			if (driver.findElement(el).isDisplayed()) {

				return true;
			} else {

				return false;

			}
		}catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {

			return false;

		}
	}

	/**
	 * Utility Method to Check whether Element is displayed or not
	 *
	 * @param el
	 * @return
	 */
	public static Boolean xIsDisplayed(WebElement el) {
		try {
			if (el.isDisplayed()) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Utility Method to Check whether Element is displayed or not
	 * 
	 * @param el
	 * @return
	 */
	public static Boolean xIsDisplayed(WebElement el, int reTry) {
		boolean foo = false;
		for (int i = 0; i < reTry; i++) {
			try {
				if (el.isDisplayed()) {

					foo = true;
				} else {
					foo = false;
					timeWait(1);
				}
			} catch (Exception e) {
				foo = false;
				timeWait(1);
			}
		}
		return foo;
	}

	/*
	 * ******************************************************************* Function
	 * Name: xClickVisibleListElement Author : Cognizant Team Purpose : Click the
	 * visible element field: driver, List<WebElement>
	 * ******************************************************************
	 */
	public static void xIsElementDisplayed(Report report, WebElement el, String Msg) {

		if (el.isDisplayed()) {
			report.updateTestLog("IsElement displayed", Msg, Status.PASS);

		} else {

			report.updateTestLog("IsElement displayed", Msg, Status.FAIL);
		}
	}

	/*
	 * Purpose: get the current system date
	 */

	public static void xMouseClick(CraftDriver driver, WebElement el) {

		String mouseScript = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseScript, el);
	}

	public static boolean xMouseDoubleClick(CraftDriver driver, WebElement el) {

		Actions builder = new Actions(driver.getWebDriver());
		builder.moveToElement(el).doubleClick().perform();
		return true;
	}

	public static void xmouseOut(CraftDriver driver, WebElement el) {
		String mouseOffScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initEvent('mouseout', true, false); arguments[0].dispatchEvent(evObj);"
				+ "} else if(document.createEventObject) { arguments[0].fireEvent('onmouseout');}";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseOffScript, el);
	}

	public static void xMouseOut(CraftDriver driver, WebElement el) {
		String mouseOffScript = "$(arguments[0]).mouseout();";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseOffScript, el);
	}

	/*
	 * ******************************************************************* Function
	 * Name: xmouseOver Author : Cognizant Team
	 * xHoverElement,xHoverElementDblClk,xHoverElementclicks : Hover and action
	 * Parameters: webelement, driver
	 * ******************************************************************
	 */
	public static void xmouseOver(CraftDriver driver, WebElement el) {
		String mouseOverScript = "if(document.createEvent){" + "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initEvent('mouseover', true, false); " + "arguments[0].dispatchEvent(evObj);" + "} "
				+ "else if(document.createEventObject) { " + "arguments[0].fireEvent('onmouseover');" + "}";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseOverScript, el);
	}

	public static void xMouseOver(CraftDriver driver, WebElement el) {
		String mouseOverScript = "$(arguments[0]).mouseover();";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseOverScript, el);
	}

	public static boolean xMoveToElment(WebElement el, CraftDriver driver) {

		Actions builder = new Actions(driver.getWebDriver());
		builder.moveToElement(el).perform();
		return true;
	}

	public static int xRandomFunction() {
		Random random = new Random();
		int value = random.nextInt(9999999) + 1000000;
		return value;
	}

	/*
	 *
	 */

	public static int xRandomFunction(int MaxNum) {
		Random random = new Random();
		int value = (int) (random.nextInt(MaxNum) + Math.pow(10, NumofDigits(MaxNum)));
		return value;
	}

	public static Object xReadJson(String path) {
		try {

			File f = new File("src/test/resources/Datatables/RunTimeData.json");
			Object obj = null;
			ReadContext wctx;
			try {
				wctx = JsonPath.parse(f);
				obj = wctx.read("$." + path);

				return obj;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				obj = null;
			}
			return obj;

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public static String xGetJsonAsString(String path) {

		File f = new File("src/test/resources/Datatables/RunTimeData.json");
		String obj = null;
		ReadContext wctx;
		try {
			wctx = JsonPath.parse(f);
			obj = wctx.read("$." + path).toString();

			return obj;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> xGetJsonAsMap(String path) {

		File f = new File("src/test/resources/Datatables/RunTimeData.json");
		ObjectMapper mapper = new ObjectMapper();
		Configuration.setDefaults(new Configuration.Defaults() {

			private final JsonProvider jsonProvider = new JacksonJsonProvider();
			private final MappingProvider mappingProvider = new JacksonMappingProvider();

			@Override
			public JsonProvider jsonProvider() {
				return jsonProvider;
			}

			@Override
			public MappingProvider mappingProvider() {
				return mappingProvider;
			}

			@Override
			public Set<Option> options() {
				return EnumSet.noneOf(Option.class);
			}
		});

		TypeRef<Map<String, String>> typeRef = new TypeRef<Map<String, String>>() {
		};
		Map<String, String> obj = new HashMap<String, String>();
		ReadContext wctx;
		try {
			obj = JsonPath.parse(f).read(path, Map.class);
			obj = JsonPath.parse(f).read(path, typeRef);
			wctx = JsonPath.parse(f);

			return obj;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static void xsaveJsonData(String key, Object obj) {
		try {

			List<JSONObject> jsonObj = new ArrayList<JSONObject>();
			jsonObj.addAll((Collection<? extends JSONObject>) obj);

			JSONObject jo = new JSONObject();
			jo.put(key, jsonObj);
			PrintWriter pw = new PrintWriter("C:\\Users\\gsamota\\Downloads\\material.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * purpose: Login to a page with username, password and click btn
	 */

	public static void xScrollIntoView(CraftDriver driver, WebElement el) {

		String mouseScript = "arguments[0].scrollIntoView();";

		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseScript, el);
		xHighlight(driver, el, "yellow");

	}

	public static void xScrollIntoView(CraftDriver driver, By el) {

		String mouseScript = "arguments[0].scrollIntoView();";

		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseScript, driver.findElement(el));
		xHighlight(driver, el, "yellow");

	}

	public static void xScrollIntoViewClck(CraftDriver driver, WebElement el) {

		String mouseScript = "arguments[0].scrollIntoView();";
		String mouseScript1 = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseScript, el);
		js.executeScript(mouseScript1, el);
	}

	/*
	 * public static void ZipResults(String reportPath) { // TODO Auto-generated
	 * method stub FileZipper appZip = new FileZipper();
	 * appZip.ZipFiles(reportPath); }
	 */

	public static void xScrollPage(CraftDriver driver) {
		String mouseScript = "window.scrollTo(document.body.scrollHeight,0);";
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript(mouseScript);
	}

	public static void xScrollToViewSendKeys(CraftDriver driver, WebElement el, String strVal) {
		xScrollIntoView(driver, el);
		xHighlight(driver, el, "yellow");
		xWaitForElementVisible(driver, el, 10);
		el.click();
		el.clear();
		el.sendKeys(strVal);
		// timeWait(1);

	}

	/*
	 * ******************************************************************* Function
	 * Name: xScrollWindow Author : Cognizant Team Hover and action Parameters:
	 * driver ******************************************************************
	 */
	public static void xScrollWindow(CraftDriver driver) {
		for (int timeout = 0;; timeout++) {
			if (timeout >= 5) {
				break;
			}
			JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
			js.executeScript("window.scrollBy(0,400)", "");
			timeWait(2);
		}
	}

	public static void xScrollWindowOnce(CraftDriver driver) {
		for (int timeout = 0;; timeout++) {
			if (timeout >= 5) {
				break;
			}
			JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
			js.executeScript("window.scrollBy(0,200)", "");
			timeWait(2);
		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: xHighlight Author : Cognizant Team Purpose : Highlight the element
	 * Parameters: driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	/*
	 * public boolean validatePDFViewer(){ boolean isPdfPresent = false; String
	 * pdfPageSource = driver.getPageSource(); switch(envParameters.getBrowser()){
	 * case INTERNET_EXPLORER: if(pdfPageSource.equals("")) isPdfPresent=true;
	 * break; case FIREFOX: if(pdfPageSource.contains("pdfViewer"))
	 * isPdfPresent=true; break; case CHROME:
	 * if(pdfPageSource.contains("application/pdf")) isPdfPresent=true; break;
	 * default: break; }
	 *
	 * return isPdfPresent; }
	 */

	/*
	 * ******************************************************************* Function
	 * Name: xHighlight Author : Cognizant Team Purpose : Highlight the element
	 * Parameters: driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	/**
	 * Use this for scrolling directly to an object (javascript scroll). Will
	 * attempt to put that object near the top of the screen.
	 *
	 * @param driver
	 * @param el
	 */
	public static void xScrollWindowToElement(CraftDriver driver, WebElement el) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", el);
			timeWait(2);
		} catch (Exception e) {
			// Added to prevent false erroring for referencing a "hidden" object
			System.out.println("Internal error: " + e.getMessage() + "\nContinuing after error.");
		}
	}

	public static void xScrollWindowTop(CraftDriver driver) {
		// for (int timeout = 0;; timeout++) {
		// if (timeout >= 5) {
		// break;
		// }
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("window.scroll(0,0)", "");
		timeWait(2);
		// }
	}

	/*
	 * ******************************************************************* Function
	 * Name: xHighlight Author : CBRE SF Automation Purpose : Highlight the element
	 * Parameters: driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	public static void xScrollWindowTopByValue(CraftDriver driver, int value) {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("window.scrollTo(document.body.scrolllHeight, " + value + ")");
		timeWait(2);
		// }
	}

	/*
	 * ******************************************************************* Function
	 * Name: xHighlight Author : Cognizant Team Purpose : Highlight the element
	 * Parameters: driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	public static void xScrollWindowTopJustOnce(CraftDriver driver, int value) {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("window.scrollTo(document.body.scrollHeight, " + value + ")");
		timeWait(2);
	}

	public static void xSelectDropdownByIndex(CraftDriver driver, WebElement e, Integer index) {
		try {
			xWaitForElementPresent(driver, e, 10);
			xWaitForElementClickable(driver, e, 10);
			Select answer = new Select(e);

			if (answer.getOptions().size() < 1) {
				timeWait(1);
			}
			answer.selectByIndex(index);
		} catch (Exception e2) {
			System.out.println(" Select by DropDownIndex: Error " + xExptnsMsg(e2.getMessage()));
		}

	}

	/*
	 * ******************************************************************* Function
	 * Name: xSwitc Author : Cognizant Team Purpose : Highlight the element
	 * Parameters: driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	public static void xSelectDropdownByIndex(WebElement e, Integer index) {

		Select answer = new Select(e);
		answer.selectByIndex(index);

	}

	public static void xSelectDropdownByName(CraftDriver driver, Report report, WebElement e, String visibleName,
			String CustMsg) {
		if (visibleName != "") {
			xSelectDropdownByName(driver, e, visibleName);
		}

		report.updateTestLog("SelectDropDownByName", CustMsg + "-- " + visibleName, Status.PASS);

	}

	public static void xSelectDropdownByName(CraftDriver driver, WebElement e, String visibleName) {
		xWaitForElementPresent(driver, e, 10);
		xWaitForElementClickable(driver, e, 10);
		Select dropdown = new Select(e);
		if (dropdown.getOptions().size() <= 0) {
			timeWait(1);

		}
		dropdown.selectByVisibleText(visibleName);

	}

	public static void xSelectDropdownByName(CraftDriver driver, By e, String visibleName) {
		xWaitForElementPresent(driver, e, 10);
		xWaitForElementClickable(driver, e, 10);
		WebElement wb = driver.findElement(e);
		Select dropdown = new Select(wb);
		if (dropdown.getOptions().size() <= 0) {
			timeWait(1);

		}
		dropdown.selectByVisibleText(visibleName);

	}

	public static void xSelectDropdownByName(WebElement e, String visibleName) {
		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(visibleName);

	}

	public static void xSelectDropdownByNameIfAvlbl(CraftDriver driver, Report report, WebElement e, String visibleName,
			String CustMsg) {
		if (xIsDisplayed(e) && visibleName != "") {
			xSelectDropdownByName(driver, e, visibleName);
			report.updateTestLog("SelectDropDownByName", CustMsg + "-- " + visibleName, Status.PASS);
		}

	}

	public static void xSelectDropDownByPartialText(CraftDriver driver, Report report, WebElement e, String Text) {
		xWaitForElementPresent(driver, e, 10);
		xWaitForElementClickable(driver, e, 10);
		Select dropdown = new Select(e);
		List<WebElement> optionElements = dropdown.getOptions();
		try {
			for (WebElement optionElement : optionElements) {
				if (optionElement.getText().contains(Text)) {

					String optionValue = optionElement.getAttribute("Value");

					dropdown.selectByValue(optionValue);

					report.updateTestLog("Select drop down value by Partial Text",
							"Verify User is able to select drop down value as " + optionElement.getText(), Status.PASS);
					break;
				}
			}

			Thread.sleep(300);

		} catch (Exception e2) {
			System.out.println(e2.getStackTrace());

		}

	}

	public static void xSelectDropDownByPartialText(CraftDriver driver, Report report, By e, String Text) {

		xWaitForElementClickable(driver, e, 10);

		WebElement el = driver.findElement(e);
		Select dropdown = new Select(el);
		List<WebElement> optionElements = dropdown.getOptions();
		try {
			for (WebElement optionElement : optionElements) {
				if (optionElement.getText().contains(Text)) {

					String optionValue = optionElement.getAttribute("Value");

					dropdown.selectByValue(optionValue);

					report.updateTestLog("Select drop down value by Partial Text",
							"Verify User is able to select drop down value as " + optionElement.getText(), Status.PASS);
					break;
				}
			}

			Thread.sleep(300);

		} catch (Exception e2) {
			System.out.println(e2.getStackTrace());

		}

	}
	/*
	 * ******************************************************************* Function
	 * Name: xContainsvalue Author : Cognizant Team Takes a given driver and window
	 * title, returns true if the window title exists
	 * ******************************************************************
	 */

	public static void xSelectDropDownByPartialText(Report report, WebElement e, String Text) {
		Select dropdown = new Select(e);
		List<WebElement> optionElements = dropdown.getOptions();
		try {
			for (WebElement optionElement : optionElements) {
				String drpDwnText = optionElement.getText();
				if (drpDwnText.contains(Text)) {

					String optionValue = optionElement.getAttribute("Value");

					dropdown.selectByValue(optionValue);
					report.updateTestLog("Select drop down by Partial Text", "Successfuly Selected " + drpDwnText,
							Status.PASS);
					break;
				}
			}

			Thread.sleep(300);

		} catch (Exception e2) {
			System.out.println(e2.getStackTrace());

		}
	}

	public static void xSelectDropdownByValue(CraftDriver driver, WebElement e, String value) {
		xWaitForElementPresent(driver, e, 10);
		xWaitForElementClickable(driver, e, 10);
		Select dropdown = new Select(e);
		if (dropdown.getOptions().size() <= 0) {
			timeWait(1);
		}
		dropdown.selectByValue(value);
	}

	public static void xSelectDropdownByValue(CraftDriver driver, By e, String value) {
		xWaitForElementPresent(driver, e, 10);
		xWaitForElementClickable(driver, e, 10);
		WebElement el = driver.findElement(e);
		Select dropdown = new Select(el);
		if (dropdown.getOptions().size() <= 0) {
			timeWait(1);
		}
		dropdown.selectByValue(value);
	}

	public static void xSelectDropdownByValue(WebElement e, String value) {
		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}

	public static void xSelectRadio(CraftDriver driver, WebElement el) {
		boolean orig = el.isSelected();
		Utility_Functions.xClickButton(driver, el, true);
		if (orig == el.isSelected()) {
			Utility_Functions.xSendKeys(driver, el, " ");
		}

	}

	/**
	 * Method to select value for typo Box text box
	 *
	 * @param driver               Pass driver instance
	 * @param typeInputTxt         Pass webElement of typo input text box
	 * @param typoValueXpathString Pass xpath String typo value
	 * @param toBeReplaced         Pass the String to be replaced in typo value
	 *                             xpath
	 * @param typeInput            Pass String value to Type in typo box also it
	 *                             should be same value which will replace
	 *                             toBeReplaced string
	 */
	public static void xSelectTypoBxValue(CraftDriver driver, WebElement typeInputTxt, String typoValueXpathString,
			String toBeReplaced, String typeInput) {
		if (typeInputTxt != null) {
			xSendKeys(driver, typeInputTxt, typeInput);
			xSendKeyBoardkeys(typeInputTxt, Keys.ARROW_DOWN);
			xfindElAndClick(driver, typoValueXpathString, toBeReplaced, typeInput);
		}

	}

	public static void xSendCtrlPlusKey(WebElement el, String key) {
		el.click();
		el.clear();
		el.sendKeys(Keys.chord(Keys.CONTROL, key));

	}

	/**
	 * Method to Send Keyboard Keys
	 *
	 * @param el  Pass webelement
	 * @param Key Pass Key
	 */
	public static void xSendKeyBoardkeys(WebElement el, Keys Key) {
		try {
			el.sendKeys(Key);

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	public static void xSendKeyBoardkeys(CraftDriver driver, By el, Keys Key) {
		try {
			WebElement wb = driver.findElement(el);
			wb.sendKeys(Key);

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/**
	 * Method to Send Keyboard keys in Loop
	 *
	 * @param el    Pass Webelement of Text box
	 * @param Key   Pass Keyboad Key
	 * @param count How many times it need to be pressed
	 */
	public static void xSendKeyBoardkeys(WebElement el, Keys Key, int count) {
		try {
			for (int i = 0; i < count; i++) {
				el.sendKeys(Key);
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: Random Function Author : Automation Purpose :
	 * ******************************************************************
	 */

	public static void xSendKeys(CraftDriver driver, Report report, By el, String strVal, String CustomMsg) {

		WebElement elment = driver.findElement(el);
		xWaitForElementClickable(driver, el, 10);
		xSendKeys(report, elment, strVal, CustomMsg);
	}

	public static void xSendKeys(CraftDriver driver, Report report, WebElement el, String strVal, String CustomMsg) {
		xWaitForElementClickable(driver, el, 5);
		xSendKeys(el, strVal);
		report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
	}

	public static void xSendKeys(CraftDriver driver, WebElement el, Keys keyName) {
		//xHighlight(driver, el, "blue");
		el.sendKeys(keyName);

	}

	/*
	 * ******************************************************************* Function
	 * Name: xSendKeys Author : Cognizant Team Send keys to the input field: driver,
	 * webelement, highlight = true/false
	 * ******************************************************************
	 */
	public static void xSendKeys(CraftDriver driver, WebElement el, String strVal) {

		xWaitForElementVisible(driver, el, 10);
		xHighlight(driver, el, "yellow");
		el.click();
		el.clear();
		el.sendKeys(strVal);
		// timeWait(1);

	}

	public static void xSendKeys(CraftDriver driver, By el, String strVal) {

		xWaitForElementClickable(driver, el, 10);
		xHighlight(driver, el, "yellow");
		WebElement wb = driver.findElement(el);
		wb.click();
		wb.clear();
		wb.sendKeys(strVal);
		// timeWait(1);

	}

	/*
	 * ******************************************************************* Function
	 * Name: xSendKeys Author : Cognizant Team Send keys to the input field: driver,
	 * webelement, highlight = true/false
	 * ******************************************************************
	 */
	public static void xSendKeys(Report report, WebElement el, String strVal, String CustomMsg) {
		xSendKeys(el, strVal);
		report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
	}

	public static void xSendKeys(Report report, WebElement el, WebElement el1, String strVal, String CustomMsg) {
		try {
			xSendKeys(el, strVal);

			report.updateTestLog("Enter Data", CustomMsg, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Enter Data", CustomMsg, Status.PASS);

		}

	}

	public static void xSendKeys(WebElement el, String strVal) {
		if (!strVal.isEmpty() && strVal != "" && strVal != null) {
			el.click();
			el.clear();
			el.sendKeys(strVal);
		}

	}

	/*
	 * ******************************************************************* Function
	 * Name: Validate whether the page is ready
	 * ******************************************************************
	 */

	/**
	 *
	 * Method to enter data in text and tab out and update report
	 *
	 * @param report
	 * @param el
	 * @param strVal
	 * @param CustomMsg
	 */
	public static void xSendkeysAndTab(Report report, WebElement el, String strVal, String CustomMsg) {
		try {
			xSendkeysAndTab(el, strVal);
			report.updateTestLog("Enter Data and Tab out from field ", CustomMsg, Status.PASS);

		} catch (Exception e) {

			report.updateTestLog("Enter data", Utility_Functions.xExptnsMsg(e.getMessage()), Status.FAIL);
		}
	}

	/*
	 * ******************************************************************* Function
	 * Name: Validate whether the fields present on the page or not
	 * ******************************************************************
	 */

	public static void xSendkeysAndTab(WebElement el, String strVal) {
		xSendKeys(el, strVal);
		el.sendKeys(Keys.TAB);

	}

	public static void xSendKeysIfAvlb(CraftDriver driver, Report report, WebElement el, String strVal,
			String CustomMsg) {
		if (xIsDisplayed(el) && strVal.length() != 0) {
			xWaitForElementClickable(driver, el, 5);
			xSendKeys(el, strVal);
			report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
		}
	}

	public static void xSendKeysIfDisplay(CraftDriver driver, Report report, WebElement el, String strVal,
			String CustomMsg) {
		try {
			if (xIsDisplayed(el)) {
				xWaitForElementClickable(driver, el, 5);
				xSendKeys(el, strVal);
				report.updateTestLog("Enter Data", CustomMsg, Status.PASS);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void xSendKeysNonEmpty(WebElement el, String strVal) {
		if (!(strVal.equals("") || strVal.isEmpty())) {
			// xHighlight(driver, el, "yellow");
			// el.clear();
			// timeWait(1);
			el.sendKeys(strVal);
			el.sendKeys(Keys.TAB);
		}
		// timeWait(1);

	}

	/*
	 * ******************************************************************* Function
	 * Name: xSendKeysVisibleListElement Author : Cognizant Team Purpose : SendKeys
	 * to the visible element field: driver, List<WebElement>,string
	 * ******************************************************************
	 */
	public static void xSendKeysVisibleListElement(List<WebElement> eleList, String strVal) {

		for (WebElement element : eleList) {
			if (element.isDisplayed()) {
				xSendKeys(element, strVal);
				break;
			}
		}

	}

	/*
	 * ******************************************************************* Function
	 * Name: Miscellaneous functions Author : Cognizant Team Purpose : Hover and
	 * action Parameters: webelement, index
	 * ******************************************************************
	 */
	// Maximizing the browser size
	public static void xSetBrowserSize(CraftDriver driver) {
		driver.manage().window().maximize();
		// driver.manage().window().setSize(new Dimension(900, 700));
	}

	public static void xswitchToDfultFrame(CraftDriver driver) {
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
	}

	/*
	 * ******************************************************************* Function
	 * Name: xSwitchFrames Author : CBRE SF :
	 *
	 * ******************************************************************
	 */
	public static void xSwitchtoFrame(CraftDriver driver, WebElement webElement) {
		List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
		timeWait(2);
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		for (WebElement element : iframeList) {
			driver.switchTo().frame(element);
			try {
				boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
				if (isTextValuePresent) {
					System.out.println("********switched to Frame");
					break;
				} else {
					driver.switchTo().defaultContent();
				}
			} catch (Exception ex) {
				driver.switchTo().defaultContent();
			}
		}
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

	}
	
	public static boolean xswitchedToFrame(CraftDriver driver, WebElement webElement) {
		List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
		timeWait(2);
		boolean isswitch= false;
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		for (WebElement element : iframeList) {
			driver.switchTo().frame(element);
			try {
				boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
				if (isTextValuePresent) {
					isswitch= true;
					System.out.println("Switched to Frame");
					break;
				} else {
					driver.switchTo().defaultContent();
				}
			} catch (Exception ex) {
				driver.switchTo().defaultContent();
				isswitch= false;
			}
		}
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
return isswitch;
	}

	/*
	 * ******************************************************************* Function
	 * Name: xSwitchFrames Author : CBRE SF :
	 *
	 * ******************************************************************
	 */
	public static boolean xSwitchtoFrame(CraftDriver driver, By webElement) {
		driver.switchTo().defaultContent();
		List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
		timeWait(2);
		boolean isSwitched = false;
	
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		for (WebElement element : iframeList) {
			driver.switchTo().frame(element);
			try {
				boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
				if (isTextValuePresent) {
					System.out.println("Switched to Frame");
					isSwitched = true;
					break;
				} else {
					driver.switchTo().defaultContent();
					isSwitched = false;
				}
			} catch (Exception ex) {
				driver.switchTo().defaultContent();
				isSwitched = false;
			}
		}
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		return isSwitched;

	}

	/**
	 * @param driver       Craft Web Driver
	 * @param webElement   Page WebElement
	 * @param FrameTagName Pass Frame tag name(e.g. iframe,frame)
	 */
	public static void xSwitchtoFrame(CraftDriver driver, WebElement webElement, String FrameTagName) {
		driver.switchTo().defaultContent();

		List<WebElement> iframeList = driver.findElements(By.tagName(FrameTagName));

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		for (WebElement element : iframeList) {
			driver.switchTo().frame(element);
			try {
				boolean isTextValuePresent = Utility_Functions.xWaitForElementPresent(driver, webElement, 0);
				if (isTextValuePresent) {
					System.out.println("Switched to Frame");
					break;
				} else {
					driver.switchTo().defaultContent();
				}
			} catch (Exception ex) {
				driver.switchTo().defaultContent();
			}
		}
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

	}

	public static void xswitchToFrameById(CraftDriver driver, String id) {
		driver.switchTo().frame(id);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
	}

	/**
	 * Method to Switch to frame within frame
	 *
	 * @param driver       Pass drive instance
	 * @param webElement   pass webelment which is present in expected frame
	 * @param FrameTagName Pass the Frame tage frame/iframe
	 */
	public static void xSwitchtoMultiFrame(CraftDriver driver, WebElement webElement, String FrameTagName) {
		driver.switchTo().defaultContent();
		List<WebElement> iframeList = driver.findElements(By.tagName(FrameTagName));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		for (WebElement element : iframeList) {
			driver.switchTo().frame(element);
			List<WebElement> frameList = driver.findElements(By.tagName(FrameTagName));
			if (frameList.size() > 0) {
				for (WebElement el2 : frameList) {

					driver.switchTo().frame(el2);

					boolean isTextValuePresent1 = Utility_Functions.xWaitForElementPresentInFrame(driver, webElement,
							0);
					if (isTextValuePresent1) {
						break;
					} else {
						driver.switchTo().defaultContent();
						driver.switchTo().frame(element);
					}
				}
				break;
			} else {
				driver.switchTo().defaultContent();
			}

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

		}
	}

	public static void xSwitchToParentWin(CraftDriver driver) {

		Utility_Functions.xSwitchToWindow(driver, 0);
		WebDriver popup = driver.getWebDriver();
		System.out.println("Window Title : " + popup.getTitle());

	}

	/**
	 * Window switch by window number
	 *
	 * @param driver
	 * @param windowNumber
	 */
	public static void xSwitchToWindow(CraftDriver driver, int windowNumber) {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> itr = window.iterator();
		int count = 0;

		if (windowNumber < 0)
			return;

		while (itr.hasNext()) {
			driver.switchTo().window(itr.next());
			if (count == windowNumber)
				break;
			count++;
		}
	}

	/*
	 * public static void xUpdateListMap(String listKey, String srchByKey, String
	 * srchByVal, String mapKey, String val) { List<HashMap<String, Object>> list =
	 * xGetJsonList(listKey); for (int i = 0; i < list.size(); i++) { if
	 * (list.get(i).get(srchByKey).equalsIgnoreCase(srchByVal)) {
	 * list.get(i).replace(mapKey, val); xUpdateJson(listKey, list); break; }
	 *
	 * }
	 *
	 * }
	 */

	public static void xSwitchToWindow(CraftDriver driver, Report report, int windowNumber) {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> itr = window.iterator();
		int count = 0;

		if (windowNumber < 0)
			return;

		while (itr.hasNext()) {
			driver.switchTo().window(itr.next());
			if (count == windowNumber) {

				report.updateTestLog("SwitchToWindow", "Switch To Parent Window", Status.PASS);
				break;
			}

			count++;
		}
	}

	public static void xSwitchToWindow(CraftDriver driver, Report report, String windowName) {
		WebDriver popup = driver.getWebDriver();
		Set<String> windowIterator = driver.getWindowHandles();
		System.err.println("No of windows :  " + windowIterator.size());
		for (String s : windowIterator) {
			String windowHandle = s;
			popup = driver.switchTo().window(windowHandle);
			System.out.println("Window Title : " + popup.getTitle());
			System.out.println("Window Url : " + popup.getCurrentUrl());
			if (popup.getTitle().contains(windowName)) {
				System.out.println("Selected Window Title : " + popup.getTitle());
				report.updateTestLog("SwitchToWindow",
						"Switch to Window  containing Title as  " + popup.getTitle() + " ", Status.PASS);
				// driver = popup;
				break;

			}
		}
	}
	/*
	 * ******************************************************************* Function
	 * Name: xSwitc Author : Cognizant Team Purpose : Switch to Parent Window
	 * element Parameters: driver, webelement, color = e.g yellow, green etc
	 * ******************************************************************
	 */

	public static void xSwitchToWindow(CraftDriver driver, String windowName) {
		WebDriver popup = driver.getWebDriver();
		Set<String> windowIterator = driver.getWindowHandles();
		System.err.println("No of windows :  " + windowIterator.size());
		for (String s : windowIterator) {
			String windowHandle = s;
			popup = driver.switchTo().window(windowHandle);
			System.out.println("Window Title : " + popup.getTitle());
			System.out.println("Window Url : " + popup.getCurrentUrl());
			if (popup.getTitle().contains(windowName)) {
				System.out.println("Selected Window Title : " + popup.getTitle());
				// driver = popup;
				break;

			}
		}
	}

	public static void xUpdateExistingMap(String Key, HashMap<String, String> map) {

		HashMap<String, String> m = xGetJsonMap(Key);
		m.putAll(map);
		xUpdateJson(Key, m);

	}

	@SuppressWarnings("unchecked")
	public static void xUpdateJson(String Key, HashMap<String, String> map) {
		try {
			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));

			JSONObject obj1 = new JSONObject(map);

			/*
			 * ObjectMapper objectMapper = new ObjectMapper(); String test =
			 * objectMapper.writeValueAsString(list);
			 */

			JSONObject jo = (JSONObject) obj;
			jo.put(Key, obj1);
			PrintWriter pw = new PrintWriter("src/test/resources/Datatables/RunTimeData.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public static void xUpdateJson(String Key, List<HashMap<String, Object>> list) {
		try {
			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));
			ArrayList<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();

			List<JSONObject> jsonObj = new ArrayList<JSONObject>();

			for (HashMap<String, Object> data : list) {
				JSONObject obj1 = new JSONObject(data);
				jsonObj.add(obj1);
			}

			/*
			 * ObjectMapper objectMapper = new ObjectMapper(); String test =
			 * objectMapper.writeValueAsString(list);
			 */

			JSONObject jo = (JSONObject) obj;
			jo.put(Key, jsonObj);
			PrintWriter pw = new PrintWriter("src/test/resources/Datatables/RunTimeData.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public static void xUpdateJson(String Key, String value) {
		try {

			Object obj = new JSONParser().parse(new FileReader("src/test/resources/Datatables/RunTimeData.json"));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;

			jo.put(Key, value);

			PrintWriter pw = new PrintWriter("src/test/resources/Datatables/RunTimeData.json");
			pw.write(jo.toJSONString());

			pw.flush();
			pw.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 *
	 * Method to upload file using ROBOT class
	 *
	 * @param String
	 * @author Cognizant
	 *
	 */
	public static void xUploadFile(Report report, String fileLocation) {
		try {
			Robot robot = new Robot();

			StringSelection ss = new StringSelection(fileLocation);
			// copy the above string to clip board
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			// paste the copied string to dialog box

			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			report.updateTestLog("Upload ", "File upload" + fileLocation, Status.PASS);
		} catch (Exception exp) {
			exp.printStackTrace();
			report.updateTestLog("Upload", "File upload failed for " + fileLocation, Status.FAIL);
		}

	}

	public static boolean xvalidateFieldAttrValueMatches(Report report, WebElement tar, String expValue,
			String CustMsg) {
		String val = tar.getAttribute("value");
		val = val.isEmpty() ? "" : val;
		if (val != null && val.toUpperCase().contains(expValue.toUpperCase())) {
			report.updateTestLog(CustMsg, "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'",
					Status.PASS);
			return true;
		} else {
			report.updateTestLog("VerifyTextContains",
					"Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
					Status.FAIL);
			return false;
		}
	}

	public static List<String> xValidateFieldsPresentonPage(List<String> List1, List<WebElement> WebElements,
			String TextToBeDisplayed) {
		List<String> WebElementsList = new ArrayList<String>();
		for (WebElement element : WebElements) {
			WebElementsList.add(element.getText().toString());
		}
		System.out.println(TextToBeDisplayed + " -- " + WebElementsList + " are present in the page");
		List1.removeAll(WebElementsList);
		System.out.println(TextToBeDisplayed + " -- " + List1 + " which aren't present in the page");
		return List1;
	}

	public static int xValidateFieldsPresentonPage(String text, List<WebElement> WebElements,
			String TextToBeDisplayed) {
		int count = 0;
		if (WebElements.size() == 0) {
			System.out.println("There are no elements identified with the List<WebElement> xpath");
		} else {
			String labelArray[] = new String[WebElements.size()];
			int i1 = 0;
			try {
				for (WebElement element : WebElements) {
					labelArray[i1] = element.getText();
					if (labelArray[i1].equalsIgnoreCase(text)) {
						System.out.println("Verify the " + TextToBeDisplayed + " Fields:::" + element.getText());
						count++;
					}
					i1++;
				}
				i1 = 0;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Utility_Functions.timeWait(1);
		}
		return count;
	}
	/*
	 * ******************************************************************* Function
	 * Name: Click on the element from the list of elements
	 * ******************************************************************
	 */

	public static int xValidateFieldsPresentPage(List<String> FieldList, List<WebElement> WebElements,
			String TextToBeDisplayed) {
		int count = 0;
		if (WebElements.size() == 0) {
			System.out.println("There are no elements identified with the List<WebElement> xpath");
		} else {
			String labelArray[] = new String[WebElements.size()];
			int j = 0, i1 = 0;
			try {
				while (j < FieldList.size()) {
					for (WebElement element : WebElements) {
						labelArray[i1] = element.getText();
						labelArray[i1] = labelArray[i1].replace("\"", "");
						if (labelArray[i1].equalsIgnoreCase(FieldList.get(j))) {
							System.out.println("Verify the " + TextToBeDisplayed + " Fields:::" + element.getText());
							count++;
						}
						i1++;
					}
					i1 = 0;
					j++;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Utility_Functions.timeWait(1);
		}
		return count;
	}

	public static boolean xVrfyTextAvlble(CraftDriver driver, Report report, WebElement tar, String expValue,
			String CstmMsg) {

		String val = getText(tar, driver);
		if (val.contains(expValue)) {
			report.updateTestLog(CstmMsg, "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'",
					Status.PASS);
			return true;
		} else {
			xHighlight(driver, tar, "red");
			report.updateTestLog(CstmMsg,
					"Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
					Status.FAIL);
			return false;
		}
	}

	public static boolean xVrfyTextAvlble(Report report, String expValue, String Actual, String CstmMsg) {
		try {

			Assert.assertEquals(Actual, (expValue));

			if (Actual.equalsIgnoreCase(expValue)) {
				report.updateTestLog("Assertion",
						CstmMsg + " Expected value '" + expValue + "' is matching With Actual Value '" + Actual + "'",
						Status.PASS);
				return true;
			} else {
				report.updateTestLog("AssertionFail", CstmMsg + " Expected value '" + expValue
						+ "' is  Not matching With Text at failure  '" + Actual + "'", Status.FAIL);
				return false;
			}
		} catch (AssertionError e) {
			report.updateTestLog("AssertionFail", CstmMsg + e.getMessage(), Status.FAIL);
			return false;
		}
	}

	/**
	 * Utility Method to verify target value is matching with expected value
	 *
	 * @param report
	 * @param tar
	 * @param expValue
	 * @return
	 */
	public static boolean xVrfyTextAvlble(Report report, WebElement tar, String expValue) {
		String val = tar.getText();
		if (val != null && val.toUpperCase().equalsIgnoreCase(expValue.toUpperCase())) {
			report.updateTestLog("VerifyTextContains",
					"Expected text '" + expValue + "' is matching With Actual Text '" + val + "'", Status.PASS);
			return true;
		} else {
			report.updateTestLog("VerifyTextContains",
					"Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
					Status.FAIL);
			return false;
		}
	}

	public static boolean xVrfyTextAvlble(Report report, WebElement tar, String expValue, String CstmMsg) {
		String val = tar.getText();
		if (val.contains(expValue)) {
			report.updateTestLog(CstmMsg, "Expected text '" + expValue + "' is matching With Actual Text '" + val + "'",
					Status.PASS);
			return true;
		} else {
			report.updateTestLog(CstmMsg,
					"Expected text '" + expValue + "' is  Not matching With Text at failure  '" + val + "'",
					Status.FAIL);
			return false;
		}
	}

	public static boolean xVrfyTextAvlble(WebElement tar, String expValue) {
		String val = tar.getText();
		if (val.contains(expValue)) {

			return true;
		} else {

			return false;
		}
	}

	// ******************************************************************
	// wait for a particular attribute of the webelement
	public static void xWaitForElementAttribute(CraftDriver driver, By Locator, String attribute, String message,
			int timeWait) {
		int timeout = 0;
		while (!driver.findElement(Locator).getAttribute(attribute).contains(message)) {
			if (timeout >= 20) {
				break;
			}
			timeWait(2);
			timeout++;
		}
	}

	public static void xWaitForElementClickable(CraftDriver driver, By el, int timeWait) {

		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(el));
		wait.until(ExpectedConditions.elementToBeClickable(el));

		// return true;
	}

	// ******************************************************************
	// wait for the element to be clickable
	public static void xWaitForElementClickable(CraftDriver driver, WebElement el, int timeWait) {
		// xWaitForLoad(driver, timeWait);
		xWaitForElementPresent(driver, el, 10);
		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(el));
		wait.until(ExpectedConditions.elementToBeClickable(el));
		xHighlight(driver, el, "green");
		// return true;
	}

	// ******************************************************************
	// wait for element to disappear
	public static boolean xWaitForElementDisappear(CraftDriver driver, By locator, int timeWait) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			System.out.println("Element could not disappear within the specified time");
			System.out.println(e.getMessage());
			return false;
		}

	}

	// ******************************************************************
	// wait for element to disappear
	public static boolean xWaitForElementDisappear(CraftDriver driver, WebElement locator, int timeWait) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.invisibilityOf(locator));
			return true;
		} catch (Exception e) {
			System.out.println("Element could not disappear within the specified time");
			System.out.println(e.getMessage());
			return false;
		}

	}
	
	// ******************************************************************
		// wait for element to disappear
		public static  void xwaitForElmntToDisappr(CraftDriver driver, By locator, int timeWait,int iteration) {
			int i=0;
			while (i<=iteration) {
				if(xWaitForElementDisappear(driver, locator, timeWait)) {
					break;
				}
			
			}

		}


	// ******************************************************************
	// wait for element present
	public static boolean xWaitForElementPresent(CraftDriver driver, By locator, int timeWait) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
					.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;

		} catch (NoSuchElementException e) {

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean xWaitForElementPresent(CraftDriver driver, List<WebElement> elements, int timeWait) {
		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		return true;
	}

	// ******************************************************************
	// wait for element present
	public static boolean xWaitForElementPresent(CraftDriver driver, WebElement el, int timeWait) {
		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(el));
		return true;
	}

	public static boolean xWaitForElementPresentInFrame(CraftDriver driver, WebElement el, int timeWait) {
		try {
			WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeWait);
			wait.until(ExpectedConditions.visibilityOf(el));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// wait for the element to be clickable
	public static void xWaitForElementToBeClickable(CraftDriver driver, WebElement el, int timeWait) {

		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(el));
		xHighlight(driver, el, "blue");
		// return true;
	}

	// wait for the element to be clickable
	public static void xWaitForElementToBeClickable(CraftDriver driver, By el, int timeWait) {

		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(el));
		xHighlight(driver, el, "blue");
		// return true;
	}

	// wait for the element to be visible
	public static boolean xWaitForElementVisible(CraftDriver driver, WebElement el, int timeWait) {

		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(el));
		return true;
	}

	public static boolean xWaitForElementVisible(CraftDriver driver, By el, int timeWait) {

		FluentWait<WebDriver> wait = new WebDriverWait(driver.getWebDriver(), timeWait)
				.ignoring(StaleElementReferenceException.class);
		WebElement wb = driver.findElement(el);
		wait.until(ExpectedConditions.visibilityOf(wb));
		return true;
	}

	/*
	 * public boolean compare(List<Map<String, Object>> A, List<Map<String, String>>
	 * B) { // check size first if (A.size() == B.size()) { // if the Maps are
	 * abstracted into a POJO you could implement Comparator on that POJO. In the
	 * meantime you can sort manually // sort A Map<Object, Object> sorted =
	 * A.entrySet().stream()
	 * .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
	 * .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
	 * LinkedHashMap::new));
	 *
	 * Collections.sort(B, new Comparator<Map<String, String>>() { public int
	 * compare(final Map<String, String> o1, final Map<String, String> o2) { return
	 * o1.get("field").compareTo(o2.get("field")); } });
	 *
	 *
	 * for (int i = 0; i < A.size(); i++) { // get map from A & B Map<String,
	 * Object> aMap = A.get(i); Map<String, Object> bMap = B.get(i);
	 *
	 * // check equality of Maps if (!aMap.equals(bMap)) { return false; } } } else
	 * { // Data reconciliation failed :: Data size mismatch return false; }
	 *
	 * // if we get here then all was good return true; }
	 */
	public static void xWaitForLoad(CraftDriver driver, int timewait) {
		new WebDriverWait(driver.getWebDriver(), timewait)
				.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
						.executeScript("return document.readyState").equals("complete"));
	}

	public static boolean xWaitPageLoad(CraftDriver driver, WebElement element, int timeWait) {
		WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeWait);
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	public static void xWaitPageReady(CraftDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			try {
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Page is loaded:::");
			return;
		}
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		}
	}

	/*
	 * wait until the popup windows is open or closed
	 */
	public static boolean xWaitUntilnoOfWindows(CraftDriver driver, int noOfWindows, int timeWait) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), timeWait);
		wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
		return true;
	}

	public static void xWinLogin(String userName, String Pws) {
		try {
			Robot robot = new Robot();

		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

	public HashMap<String, String> getHashMapFromList(List<HashMap<String, String>> list, String srchBy) {

		HashMap<String, String> returnHMap = new HashMap<String, String>();
		for (HashMap<String, String> hashMap : list) {
			for (String key : hashMap.keySet()) {
				hashMap.get(key).equalsIgnoreCase(srchBy);
				returnHMap = hashMap;
				break;
			}

		}
		return returnHMap;

	}

	/*
	 * New Function added to fetch the data from Common sheet for tab based
	 * parameters
	 */
	public String getTable(String tableName, String Columnname) {
		String returnValue = dataTable.getData(tableName, Columnname);
		if (returnValue.startsWith("*")) {
			returnValue = returnValue.split("\\*")[1];
			// returnValue = getEnvParameterDataTable(returnValue);
			// returnValue = getConfiguration(returnValue);
		}
		return returnValue;
	}

	private boolean inputData(String tagID, String Value) {
		boolean isElementFound = false;
		List<WebElement> GenRiskInfoList = driver.findElements(By.xpath(".//*[@id[contains(.,'" + tagID + "')]]"));
		Utility_Functions.timeWait(1);

		for (WebElement GenRisk : GenRiskInfoList) {
			// System.out.println(GenRisk.getAttribute("id"));
			if (GenRisk.getAttribute("id").endsWith(tagID)) {
				if (GenRisk.getTagName().equals("input")) {

					Utility_Functions.xSendKeys(driver, GenRisk, Value);

					isElementFound = true;
				}
				if (GenRisk.getTagName().equals("select")) {
					if (Value != null && !(Value.equals(""))) {
						Utility_Functions.xSelectDropdownByName(GenRisk, Value);
						isElementFound = true;
					}
				}
			}
			if (isElementFound)
				break;

		}
		return isElementFound;
		// }
		// System.out.println("WebElement not found for : " + tagID);
	}

	public void inputValueWithReport(String tagID, String Value, String label) {
		if (!inputData(tagID, Value)) {
			report.updateTestLog("Input Value", "Error Entering value for field: <i>" + label + "</i> Value : " + Value,
					Status.WARNING);
			System.out.println(
					"WARNING : Problem Encountered while Entering value for field: " + label + " for Value : " + Value);
		} else {
			report.updateTestLog("Input Value", "Entering value for field: <i>" + label + "</i> Value : " + Value,
					Status.PASS);
		}
	}

	public String removeUsrNdPwd(String url) {
		String returnURL = "";
		if (url.contains("@")) {
			String[] spURL = url.split("@");
			returnURL = spURL[0].split("//")[0] + "//" + spURL[1];

		} else {
			returnURL = url;
		}
		return returnURL;
	}

	public int xValidateFieldsPresentonPage(List<WebElement> WebElements, HashMap<String, String> hashMap,
			String TextToBeDisplayed) {
		int count = 0;
		String labelArray[] = new String[WebElements.size()];
		for (int i = 0; i < WebElements.size(); i++) {
			for (WebElement element : WebElements) {
				labelArray[i] = element.getText();
				for (Map.Entry<String, String> entry : hashMap.entrySet()) {
					if (labelArray[i].equals(entry.getKey())) {
						hashMap.put(entry.getKey(), entry.getValue());
						break;
					}
				}
			}
		}
		return count;
	}

	// Generic Map filterbyvalue, with predicate
	public static <K, V> Map<K, V> xFilterMapByVal(Map<K, V> map, Predicate<V> predicate) {
		return map.entrySet().stream().filter(x -> predicate.test(x.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@SuppressWarnings("unchecked")
	public static List<HashMap<String, Object>> getOrderFullfillmentData(String node) {
		List<HashMap<String, Object>> data = Utility_Functions.xGetJsonList("Orders");
		List<HashMap<String, Object>> ordDtls = new ArrayList<HashMap<String, Object>>();

		for (HashMap<String, Object> ordDtl : data) {
			List<HashMap<String, Object>> sortedOrder = new ArrayList<HashMap<String, Object>>();
			String ordId = null;
			long qty1 = 0;
			HashMap<String, Object> ordsMap = new HashMap<String, Object>();

			List<HashMap<String, Object>> skuStore = new ArrayList<HashMap<String, Object>>();
			if (ordDtl.keySet().contains("commerceItems")) {
				skuStore = (List<HashMap<String, Object>>) ordDtl.get("commerceItems");

				for (HashMap<String, Object> skus : skuStore) {
					HashMap<String, Object> ssOrds = new HashMap<String, Object>();
					String strNo = (String) (skus.get("shipNode") == null ? "null" : skus.get("shipNode"));
					String status = (String) skus.get("OMSStatus");
					if (strNo.contains(node) && !status.equalsIgnoreCase("Shipped")
							&& !status.equalsIgnoreCase("Cancelled") && !status.equalsIgnoreCase("PartiallyShipped")) {
						ordId = (String) ordDtl.get("OrderID");
						// qty1 = (long) skus.get("qty");
						String sku = (String) ordDtl.get("sku");
						ssOrds.putAll(skus);

					}
					if (!ssOrds.isEmpty()) {
						sortedOrder.add(ssOrds);
					}

				}

			}

			if (ordId != null) {
				ordsMap.put("OrderId", ordId);
				ordsMap.put("commerceItems", sortedOrder);
				ordDtls.add(ordsMap);
			}

		}
		return ordDtls;

	}

	public static void xSendKey(CraftDriver driver, String weblementStrng, String inputText) {
		WebElement el = findElementByXpath(driver, weblementStrng);
		xWaitForElementClickable(driver, el, 10);
		xSendkeysAndTab(el, inputText);
	}
	
	/**
	 * 
	 * Function to verify actual vs Expected and add retrun as string
	 * @param actual  pass actual value
	 * @param expected  pass expected
	 * @param Column  pass table column Name
	 * @return
	 */
	public static StringBuilder verifyTb(String actual, String expected, String Column) {
		StringBuilder strB = new StringBuilder();

		if ((actual == null ? "" : actual).equalsIgnoreCase(expected)) {
			String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected + "  Actual val: "
					+ actual + " Matched \r\n </p>";
			strB.append(msg);
		} else {
			String msg = "<p style=\"color:red;\">" + Column + " :- Expected Val:" + expected + "  Actual val: "
					+ actual + " NotMatched \r\n";
			strB.append(msg);
		}

		return strB;

	}

	/**
	 *  Function to verify actual vs Expected and add return as string
	 * @param actual
	 * @param expected
	 * @param expected2
	 * @param Column
	 * @return
	 */
	public static StringBuilder verifyTb(String actual, String expected, String expected2, String Column) {
		StringBuilder strB = new StringBuilder();

		if ((actual == null ? "" : actual).contains(expected)) {
			String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected + "  Actual val: "
					+ actual + " Matched \r\n </p>";
			strB.append(msg);

		} else if ((actual == null ? "" : actual).contains(expected2)) {
			String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected2 + "  Actual val: "
					+ actual + " Matched \r\n </p>";
			strB.append(msg);
		} else {
			String msg = "<p style=\"color:red;\">" + Column + " :- Expected Val:" + expected + "  Actual val " + actual
					+ " NotMatched \r\n";
			strB.append(msg);
		}

		return strB;

	}
	
	/**
	 *  Function to verify actual vs Expected and add return as string
	 * @param actual
	 * @param expected
	 * @param expected2
	 * @param Column
	 * @return
	 */
	public static StringBuilder verifyTb(String actual, String expected, String expected2,String exp3, String Column) {
		StringBuilder strB = new StringBuilder();

		if ((actual == null ? "" : actual).contains(expected)) {
			String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected + "  Actual val: "
					+ actual + " Matched \r\n </p>";
			strB.append(msg);

		} else if ((actual == null ? "" : actual).contains(expected2)) {
			String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + expected2 + "  Actual val: "
					+ actual + " Matched \r\n </p>";
			strB.append(msg);
		}  else if ((actual == null ? "" : actual).contains(exp3)) {
			String msg = "<p style=\"color:green;\">" + Column + ":- Expected Val:" + exp3 + "  Actual val: "
					+ actual + " Matched \r\n </p>";
			strB.append(msg);
		}else {
			String msg = "<p style=\"color:red;\">" + Column + " :- Expected Val:" + expected + "  Actual val " + actual
					+ " NotMatched \r\n";
			strB.append(msg);
		}

		return strB;

	}
	}
