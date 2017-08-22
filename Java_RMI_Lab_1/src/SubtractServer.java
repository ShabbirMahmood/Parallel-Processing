import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SubtractServer {

	public static void main(String[] args) {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String portNumber1, registryURL1, portNumber2, registryURL2;
		
		
		try {
			
			System.out.print("Enter the RMIregistry Port Number for Subtraction: ");
			portNumber2 = bufferedReader.readLine().trim();
			
		
			
			int RMIPortNumber2 = Integer.parseInt(portNumber2); 
			startRegistry(RMIPortNumber2);  //*************
			
			
			// Subtraction
			SubtractImplementClass subtractImplementClass  = new SubtractImplementClass();
			registryURL2 = "rmi://localhost:" + portNumber2 + "/subtract";
			Naming.rebind(registryURL2, subtractImplementClass);
			System.out.println("Subtract Server Registered & Registry Currently Contains");
			listRegistry(registryURL2);
			System.out.println("Subtract Server Ready");
					
		} catch (Exception e) {
			System.out.println("Exception in Subtract Server is: " + e);
		}
	}
	// ************************ Start Registry ********************
	private static void startRegistry(int RMIPortNumber) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNumber);
			registry.list();
			
		} catch (Exception e) {
			System.out.println("RMI Registry Can't be Located at Port: " + RMIPortNumber);
			Registry registry = LocateRegistry.createRegistry(RMIPortNumber);
			System.out.println("RMI Registry Created at Port: " + RMIPortNumber);		}
	}
	// ************************ List Registry ********************
	private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException{
		System.out.println("Registry " + registryURL + " contains: ");
		String[] name = Naming.list(registryURL);
		for (int i = 0; i < name.length; i++) {
			System.out.println(name[i]);
		}
		
	}
}
