package supportLibraries;


import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Properties;

import com.cognizant.craft.ReusableLibrary;
import com.cognizant.craft.ScriptHelper;
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Settings;
import com.cognizant.framework.selenium.Browser;
@SuppressWarnings("unused")
public class BrowserSelection {
	
	
	public static  String remoteBrowser = System.getProperty("RemoteExecutionBrowser");
	public static Browser	browser;
	
	public static String initializeRemoteBrowser() {
		Properties properties = Settings.getInstance();
		try {
			if (remoteBrowser.equals(null)) {

			}
		} catch (Exception e) {
			remoteBrowser = properties.getProperty("RemoteExecutionBrowser");
			System.out.println(
					"Environment is set as per the RunEnvironment value in Global Settings file:::" + remoteBrowser);
		}
		return remoteBrowser;
	}
	
	public static Browser selectBrowser(){
		
		
		
		System.out.println("The remote browser is :"+ initializeRemoteBrowser());	
        switch(remoteBrowser) {
		case "CHROME":
			browser = Browser.CHROME;
			System.out.println("The selected browser is : Chrome");	
			break;
		case "INTERNET_EXPLORER":
        
			browser = Browser.INTERNET_EXPLORER;
			System.out.println("The selected browser is : IE");	
			break;
		default:
			throw new FrameworkException("Unhandled browser or unable to recognize browser");
		}
        return browser; 
        
        
	}

	
	
}
