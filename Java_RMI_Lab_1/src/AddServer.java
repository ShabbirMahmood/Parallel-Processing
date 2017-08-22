import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AddServer {

	public static void main(String[] args) {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String portNumber1, registryURL1, portNumber2, registryURL2;
		
		
		try {
			System.out.print("Enter the RMIregistry Port Number for Addition   : ");
			portNumber1 = bufferedReader.readLine().trim();
			
			
			int RMIPortNumber1 = Integer.parseInt(portNumber1); 
			startRegistry(RMIPortNumber1);  //*************
			
						
			// Addition
			AddImplementClass addImplementClass = new AddImplementClass();
			registryURL1 = "rmi://localhost:" + portNumber1 + "/add";
			Naming.rebind(registryURL1, addImplementClass);
			System.out.println("Add Server Registered & Registry Currently Contains");
			listRegistry(registryURL1);
			System.out.println("Add Server Ready");
			
			
					
		} catch (Exception e) {
			System.out.println("Exception in Add & Subtract Server is: " + e);
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
