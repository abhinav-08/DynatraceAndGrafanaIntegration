package integratingDynatraceAndGrafana;

import java.util.*;  
import java.io.*;

public class Executor {
	FileReader reader;
	Properties properties;
	DynatraceAPIImplementation api;
	DatabaseOperations database;
	
	public Executor() throws FileNotFoundException,IOException{
		this.reader=new FileReader("db.properties");    
		this.properties=new Properties();  
		this.properties.load(this.reader); 
		
		
		this.api = new DynatraceAPIImplementation(properties.getProperty("token"),properties.getProperty("clientID"));
		System.out.println("DynatraceAPIImplementation ref got created successfully");
		
		
		this.database = new DatabaseOperations();
		this.database.getConnected(properties.getProperty("databaseLink"), properties.getProperty("databaseUserID"), properties.getProperty("databaseUserPass"));
		System.out.println("Database connection got created successfully");
	}
	
	public void runProcessForCPU_Usage()throws IOException {
		while(true) {
			String timeAndValue[] = this.api.getCPU_Usage(properties.getProperty("hostID")).trim().split(",");
			this.database.addCpuUsageToDataBase(Double.parseDouble(timeAndValue[1]));
			if(this.database.getTotalNoOfRecords()==Integer.parseInt((properties.getProperty("cleanupInterval")))) {
				this.database.cleanWholeMeasurement("cpu_usage","Dynatrace_integration");
				break;
			}	
		}
	}
}
