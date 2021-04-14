package integratingDynatraceAndGrafana;

import java.io.IOException;
import java.util.HashMap;

public interface DynatraceAPIDAO {
	public String generateStarterLink();
	public String getAllMetrics()throws IOException;
	//public HashMap<String, Double> getCPU_Usage(String hostID)throws IOException;
	public String getCPU_Usage(String hostID)throws IOException;
	public HashMap<String, Double> getCPU_Idle(String hostID)throws IOException;
	public HashMap<String, Double> getDiskAvilablity(String hostID)throws IOException;
}
