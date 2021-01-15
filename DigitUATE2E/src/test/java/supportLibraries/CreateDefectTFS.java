package supportLibraries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.json.JSONObject;
import com.cognizant.framework.Settings;;

public class CreateDefectTFS  {

	public static String environment = System.getProperty("RunEnvironment");
	public static String initializeEnvironment() {
		Properties properties = Settings.getInstance();
		try {
			if (environment.equals(null)) {

			}
		} catch (Exception e) {
			environment = properties.getProperty("RunEnvironment");
			System.out.println(
					"Environment is set as per the RunEnvironment value in Global Settings file:::" + environment);
		}
		return environment;
	}

	public static boolean createBugInTFS(String testTitle, String testRetroSteps) {

		HttpURLConnection httpcon = null;
		try {
			initializeEnvironment();
			Properties properties = Settings.getInstance();
			String sTestTitle = testTitle;
			String sTestRetroSteps = testRetroSteps;
			JSONObject json = new JSONObject();
			json.put("action", "create");
			json.put("application_id", properties.getProperty("ApplicationId"));
			json.put("automation_tool_id", properties.getProperty("AutomationToolId"));
			json.put("title", sTestTitle ); 
			json.put("repro_steps", sTestRetroSteps);
			json.put("project_name_in_TFS", properties.getProperty("ProjectNameinTFS"));
			json.put("SystemInfo", environment);
			json.put("priority", properties.getProperty("Priority"));
			json.put("severity", properties.getProperty("Severity"));
			json.put("worktype", properties.getProperty("WorkType"));
			json.put("history", properties.getProperty("History"));
			json.put("tfs_env", properties.getProperty("TestEnvironment"));
			
			URL url = new URL(properties.getProperty("URL"));
			httpcon = (HttpURLConnection) url.openConnection();
			httpcon.setDoOutput(true);
			httpcon.setDoInput(true);
			httpcon.setRequestMethod("POST");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.connect();
			httpcon.setConnectTimeout(20);
			Utility_Functions.timeWait(10);
			OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
			output.write(json.toString());
			output.flush();
			System.out.println(httpcon.getRequestMethod());
			System.out.println(httpcon.getResponseMessage());
			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
			String inputLine;
			StringBuffer response1 = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();
			httpcon.disconnect();
			System.out.println(response1.toString());
			String response_string = response1.toString();
			JSONObject jsonObj = new JSONObject(response_string);
			System.out.println("Defect ID = "+jsonObj.get("id"));
			return true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			httpcon.disconnect();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean searchBugInTFS(String title) {

		HttpURLConnection httpcon = null;
		try {
			Properties properties = Settings.getInstance();
			JSONObject json = new JSONObject();			
			json.put("action", "search");
			json.put("application_id", properties.getProperty("ApplicationId"));
			json.put("automation_tool_id", properties.getProperty("AutomationToolId"));
			json.put("defect_title_to_search", title); 
			json.put("project_name_in_TFS", properties.getProperty("ProjectNameinTFS"));
			URL url = new URL(properties.getProperty("URL"));
			httpcon = (HttpURLConnection) url.openConnection();
			httpcon.setDoOutput(true);
			httpcon.setDoInput(true);
			httpcon.setRequestMethod("POST");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.connect();
			httpcon.setConnectTimeout(20);
			OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
			output.write(json.toString());
			output.flush();
			System.out.println(httpcon.getRequestMethod());
			System.out.println(httpcon.getResponseMessage());
			BufferedReader in = new BufferedReader(
					new InputStreamReader(httpcon.getInputStream()));
			String inputLine;
			StringBuffer response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();
			httpcon.disconnect();

			//print result
			System.out.println(response1.toString());
			String response_string = response1.toString();
			JSONObject jsonObj = new JSONObject(response_string);
			System.out.println("Existing defect = "+jsonObj.get("workItems"));
			if(jsonObj.get("workItems").toString().contains("id")) {
				return true;				
			}
			else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			httpcon.disconnect();
		}
		return false;

	}
}