package integratingDynatraceAndGrafana;

import java.io.IOException;
//import java.util.HashMap;
//import java.text.SimpleDateFormat;

//InfluxDB realted imports
import org.influxdb.dto.Point;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import java.util.concurrent.TimeUnit;

import java.lang.Thread;

import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class App 
{
	public static void main( String[] args )throws IOException, InterruptedException
	{   	
		// API related Code
		while(true) {
		String clientID = "seb66358";
		String token = "Api-Token dt0c01.XILGQBCEXZ3LCQEKTXKTJBHM.EG3TUYH22ABMANV2OSMJAPCTDOGJLQYWA2ZS3DHNQ4ZYIX5JOTKMI2GQBFNFPDCI";
		String hostID = "6BF6DD9191EE2F78";
		DynatraceAPIImplementation dyna = new DynatraceAPIImplementation(token,clientID);

		//    	implementation of 1st method
		//    	System.out.println(dyna.getAllMetrics());

		//    	implementation of 2nd method(1st approach)
		//    	HashMap<String, Double> cpuUsage = dyna.getCPU_Usage(hostID);
		//    	System.out.println("timestamps - - "+ cpuUsage.get("timestamps"));
		//    	System.out.println("value of CPU USage - - " + cpuUsage.get("value"));

		//    	implementation of 2nd method(2nd approach)

//		String timeAndValue[] = dyna.getCPU_Usage(hostID).trim().split(",");
//		System.out.println("timestamps - - "+ Long.parseLong(timeAndValue[0]));
//		System.out.println("value of CPU USage - - " + Double.parseDouble(timeAndValue[1]));
//
//		System.out.println(BasicOperations.getDateAndTimeFromEpocValue((Long.parseLong(timeAndValue[0]))));
//

		//		Database Related Code    	

//		DatabaseOperations db = new DatabaseOperations();
//
//		db.getConnected("http://localhost:8086", "Abhinav", "123456");
//		db.createDatabase("dyna_integration");
//		db.addCpuUsageToDataBase(Double.parseDouble(timeAndValue[1]));
//
//		Thread.sleep(1000);
//		System.out.println("\n\n");
		
		//getting Value of Cpu Usage from method
		String timeAndValue[] = dyna.getCPU_Usage(hostID).trim().split(",");
		
		System.out.println("hi\n"+timeAndValue[0]+"\n"+timeAndValue[1]);
		
		//creating a connection to the database
		
		DatabaseOperations db =new DatabaseOperations();
		db.getConnected("http://localhost:8086/", "Abhinav", "1234");
		db.addCpuUsageToDataBase(Double.parseDouble(timeAndValue[1]));		
		
		   	}


	}
}
