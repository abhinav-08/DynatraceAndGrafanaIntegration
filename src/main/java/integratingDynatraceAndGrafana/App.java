package integratingDynatraceAndGrafana;

import java.io.IOException;
import java.util.Properties;
import java.io.*;

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
		Executor exec =new Executor();
		exec.runProcessForCPU_Usage();
	}
}
