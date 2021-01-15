package supportLibraries;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.cognizant.craft.CRAFTLiteTestCase;
import com.cognizant.craft.CRAFTTestCase;

public class XmlGenerator {
	
	public static void reRunFailedTestCases() {
		
		XmlSuite xmlSuite = new XmlSuite();
		if(CRAFTTestCase.currentPacakage.equals("LuluE2E")) {
			xmlSuite.setName("SmokeTests");
		} else if((CRAFTTestCase.currentPacakage.equals("RegTransActTestScripts"))) {
			xmlSuite.setName("RegressionTests");
		}	

		xmlSuite.setParallel(XmlSuite.ParallelMode.CLASSES);
		xmlSuite.setThreadCount(2);
		//xmlSuite.setVerbose(1);
		
		XmlTest xmlTest = new XmlTest(xmlSuite);
		if(CRAFTTestCase.currentPacakage.equals("LuluE2E")) {
			xmlTest.setName("SmokeTests");
		} else if((CRAFTLiteTestCase.currentPacakage.equals("RegTransActTestScripts"))) {
			xmlTest.setName("RegressionTests");
		}	
		xmlTest.setPreserveOrder("true");
		
		List<String> testCasesList = null;
		if(CRAFTTestCase.currentPacakage.equals("LuluE2E")) {
			testCasesList = CRAFTTestCase.failedTestCase;
		} else if((CRAFTTestCase.currentPacakage.equals("RegTransActTestScripts"))) {
			testCasesList = CRAFTLiteTestCase.failedTestCase;
		}
		String sTestCaseList = null;
		XmlClass xmlClass = null;
		List<XmlClass> listClasses = new ArrayList<XmlClass>();

		for(int i=0; i < testCasesList.size(); i++) {
			sTestCaseList = testCasesList.get(i);
			xmlClass = new XmlClass(sTestCaseList);
			listClasses.add(xmlClass);
		}				
		xmlTest.setXmlClasses(listClasses);
		
		TestNG testng = new TestNG();
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(xmlSuite);
		testng.setXmlSuites(suites);
		CRAFTLiteTestCase.failedTestCase.clear();
		testng.run(); 
	}	
}
