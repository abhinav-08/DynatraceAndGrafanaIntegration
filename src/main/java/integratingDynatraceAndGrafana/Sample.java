package integratingDynatraceAndGrafana;


import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class Sample {
	public static void main(String args[]) {
		InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086/", "Abhinav", "1234");
		BatchPoints batchPoints = BatchPoints.database("Dynatrace_integration").tag("async", "true").build();
		
		Point point1 = Point.measurement("memory")
				.addField("free", Runtime.getRuntime().freeMemory()).tag("MemoryTag", "MemoryTagValue")  											 
				.build();
		batchPoints.point(point1);

		// Write them to InfluxDB
		influxDB.write(batchPoints);
		
		System.out.println("Done");
		
	}
}
