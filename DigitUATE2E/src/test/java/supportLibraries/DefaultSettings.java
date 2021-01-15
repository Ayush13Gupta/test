package supportLibraries;

import java.util.Properties;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Settings;
import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.ExecutionMode;

@SuppressWarnings("unused")
public class DefaultSettings {
    static Properties properties = Settings.getInstance();
    public static String remoteBrowser = System.getProperty("DefaultBrowser");
    public static String mode = System.getProperty("DefaultExecutionMode");
    public static Browser browser;
    public static ExecutionMode executionMode;

    public static String initializeExecution() {
	try {
	    if (remoteBrowser.equals(null)) {

	    }
	} catch (Exception e) {
	    remoteBrowser = properties.getProperty("DefaultBrowser");
	    System.out.println(
		    "Environment is set as per the RunEnvironment value in Global Settings file:::" + remoteBrowser);
	}
	return mode;

    }

    public static String initializeRemoteBrowser() {
	Properties properties = Settings.getInstance();
	try {
	    if (remoteBrowser.equals(null)) {

	    }
	} catch (Exception e) {
	    remoteBrowser = properties.getProperty("DefaultBrowser");
	    System.out.println(
		    "Environment is set as per the RunEnvironment value in Global Settings file:::" + remoteBrowser);
	}
	return remoteBrowser;
    }

    /**
     * Function to select Default browser based on Global setting file's Parameter
     * DefaultBrowser
     *
     * @return
     */
    public static Browser selectBrowser() {

	System.out.println("The remote browser is :" + initializeRemoteBrowser());
	switch (remoteBrowser) {
	case "CHROME":
	    browser = Browser.CHROME;
	    System.out.println("The selected browser is : Chrome");
	    break;
	case "CHROME_HEADLESS":
	    browser = Browser.CHROME_HEADLESS;
	    System.out.println("The selected browser is : Chrome Headless");
	    break;
	case "INTERNET_EXPLORER":
	    browser = Browser.INTERNET_EXPLORER;
	    System.out.println("The selected browser is : IE");
	    break;
	case "FIREFOX":
	    browser = Browser.FIREFOX;
	    break;
	case "GHOST_DRIVER":
	    browser = Browser.GHOST_DRIVER;
	    System.out.println("The selected browser is : GHOST DRIVER");
	    break;
	default:
	    throw new FrameworkException("Unhandled browser or unable to recognize browser");
	}
	return browser;

    }

    /**
     * Function to select Execution Mode based on Global Setting file
     * DefaultExecutionMode parameter
     *
     * @return
     */
    public static ExecutionMode setExecutionMode() {
	mode = mode == null ? properties.getProperty("DefaultExecutionMode") : mode;
	switch (mode.toUpperCase()) {
	case "LOCAL":
	    executionMode = ExecutionMode.LOCAL;
	    break;
	case "REMOTE":
	    executionMode = ExecutionMode.REMOTE;
	    break;
	default:
	    break;
	}

	return executionMode;

    }

}
