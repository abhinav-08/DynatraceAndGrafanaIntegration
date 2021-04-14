package integratingDynatraceAndGrafana;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import org.influxdb.dto.BatchPoints;
import java.util.concurrent.TimeUnit;

//import org.influxdb.InfluxDBIOException;
//import org.influxdb.impl.InfluxDBResultMapper;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Model;
//import javax.enterprise.inject.Produces;
//import javax.inject.Inject;
//import javax.inject.Named;
 import integratingDynatraceAndGrafana.BasicOperations;

public class DatabaseOperations {

	InfluxDB influxDB;
	String databaseName = "dyna_integration";
	String measurementName = "cpu_usage";
	
	public void getConnected(String url, String id, String passkey){
		this.influxDB = InfluxDBFactory.connect(url,id,passkey);
		
	}
	
//	public void createMeasurement(String nameOfMeasurement) {
//		Query query = new Query("Create measurement helliDynaANdINFLUX");
//		influxDB.query(query);
//	}
	
//	public void createDatabase(String databaseName) {
//		influxDB.createDatabase(databaseName);
//		influxDB.createRetentionPolicy(
//		  "defaultPolicy", databaseName, "30d", 1, true);
//	}
	
	public void addCpuUsageToDataBase(double cpuUsage) {
		BatchPoints batchPoints = BatchPoints.database("Dynatrace_integration").tag("async", "true").build();
		
		Point point1 = Point.measurement("cpu_usage")
				.addField("Usage",cpuUsage).tag("MemoryTag", "MemoryTagValue")  											 
				.build();
		batchPoints.point(point1);

		// Write them to InfluxDB
		influxDB.write(batchPoints);
		
		System.out.println("Done");
		
		
//	Point point = Point.measurement("cpu_usage")
//			  .time(System.currentTimeMillis(), TimeUnit.SECONDS)
//			  .addField("usage", cpuUsage)
//			  .addField("timestamp", 1)
//			  .build();
//	
//	BatchPoints batchPoints = BatchPoints
//			  .database("dyna_integration")
//			  .build();
//	batchPoints.point(point);
//	this.influxDB.write(batchPoints);
	}
	
	public void cleanWholeMeasurement(String measurement, String database) {
		Query query = new Query("DROP SERIES from "+measurement, database);
		this.influxDB.query(query);
	}
	
	public void getTotalNoOfRecords(String measurement, String database) {
		Query query = new Query("Select count(\"usage\") from cpu_usage", "dyna_integration");
		QueryResult qr = influxDB.query(query);
		System.out.println(qr);
	}
	
	
}
