import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Image_Server {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			String registry_URL = "rmi://localhost" + 1099 + "server";
			registry.rebind(registry_URL, new Image_Implement()); //********************
			System.out.println("Server is ON");
			
			
		} catch (Exception e) {
			System.out.println("Server Error " + e);
			
		}

	}

}
