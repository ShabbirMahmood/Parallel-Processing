import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Segment_Server {

	public static void main(String[] args) {
		try {
			Registry registry1 = LocateRegistry.createRegistry(1095);
			String registry_URL1 = "rmi://localhost" + 1095 + "server";
			registry1.rebind(registry_URL1, new Interface_Implement1()); //********************
			System.out.println("Server1 is ON");
			
			Registry registry2 = LocateRegistry.createRegistry(1096);
			String registry_URL2 = "rmi://localhost" + 1096 + "server";
			registry2.rebind(registry_URL2, new Interface_Implement2()); //********************
			System.out.println("Server2 is ON");
			
			Registry registry3 = LocateRegistry.createRegistry(1097);
			String registry_URL3 = "rmi://localhost" + 1097 + "server";
			registry3.rebind(registry_URL3, new Interface_Implement3()); //********************
			System.out.println("Server3 is ON");
			
			Registry registry4 = LocateRegistry.createRegistry(1098);
			String registry_URL4 = "rmi://localhost" + 1098 + "server";
			registry4.rebind(registry_URL4, new Interface_Implement4()); //********************
			System.out.println("Server4 is ON"); 
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("Server Error " + e);
			
		}

	}

}
