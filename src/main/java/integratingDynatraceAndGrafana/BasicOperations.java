package integratingDynatraceAndGrafana;
import java.util.Date;
import java.text.SimpleDateFormat; 

public class BasicOperations {
	public static Date getDateAndTimeFromEpocValue(Long epocValue) {
		Date date = new Date(epocValue);
		return date;
	}
	
	public static String extractTimeFromDate(Date date) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		String time = localDateFormat.format(date);
		return time;
	}
	
	
}
