package integratingDynatraceAndGrafana;

import java.net.*; //for API handling
import java.io.*; //for response handling and conversion
import org.json.JSONObject;
import java.util.HashMap;

public class DynatraceAPIImplementation implements DynatraceAPIDAO{
	private String apiToken="";
	private String clientID="";

	//parameterized constructor
	public DynatraceAPIImplementation(String apiToken, String clientID) {
		this.apiToken= apiToken;
		this.clientID = clientID;
	}

	public String generateStarterLink() {
		return "https://" + this.clientID + ".live.dynatrace.com";
	}

	public String getAllMetrics()throws IOException {
		String fullLink = generateStarterLink() + "/api/v2/metrics/";
		URL link = new URL(fullLink);
		HttpURLConnection connection = (HttpURLConnection) link.openConnection();

		//Setting up Header for authorization
		connection.setRequestProperty("Authorization", this.apiToken);

		// Setting HTTP Method (optional default is GET)
		connection.setRequestMethod("GET");

		//Below this, we are taking response from server and storing it as a String 
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream())); // getInputStream() -> Returns an input stream that reads from this open connection.A SocketTimeoutException can be thrown when reading from the returned input stream if the read timeout expires before data is available for read.

		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}


	
	public String getCPU_Usage(String hostID)throws IOException{
		String result="";
		String fullLink = generateStarterLink() + "/api/v2/metrics/query?metricSelector=builtin:host.cpu.usage&"
				+ "entitySelector=type(%22HOST%22),entityId(%22HOST-"+hostID+"%22)&from=now-5m&resolution=Inf";

		URL link = new URL(fullLink);
		HttpURLConnection connection = (HttpURLConnection) link.openConnection();
		connection.setRequestProperty("Authorization", this.apiToken);
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream())); 

		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//Converting that Response string to JSON 
		JSONObject myResponse = new JSONObject(response.toString());


		//getting specifics for CPU Usage
		Long timestampValue = myResponse.getJSONArray("result").getJSONObject(0).getJSONArray("data").getJSONObject(0).getJSONArray("timestamps").getLong(0);
		double cpuUsageValue = myResponse.getJSONArray("result").getJSONObject(0).getJSONArray("data").getJSONObject(0).getJSONArray("values").getDouble(0);

		result += Long.toString(timestampValue)+","+Double.toString(cpuUsageValue);

		return result;
	}


	public HashMap<String, Double> getCPU_Idle(String hostID)throws IOException{
		HashMap<String, Double> cpuIdle = new HashMap<String, Double>();


		//yet to do

		return cpuIdle;
	}
	
	public HashMap<String, Double> getDiskAvilablity(String hostID)throws IOException{
		HashMap<String, Double> diskAvailability = new HashMap<String, Double>();


		//yet to do

		return diskAvailability;
	}
	
	//getter and setters
	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


}
