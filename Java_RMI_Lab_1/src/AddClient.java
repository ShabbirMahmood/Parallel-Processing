import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class AddClient {

	public static void main(String[] args) {
		try {
			int RMIPort;
			String hostName,service_name;
			
			InputStreamReader inputStreamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			System.out.print("Enter the RMIRegistry Host Name: ");
			hostName = bufferedReader.readLine();
			
			System.out.print("Enter the Server Service Name(add / subtract): ");
			service_name = bufferedReader.readLine();
			
			System.out.print("Enter the RMIRegistry Port Number for Addition or Subtraction(According to Service): ");
			String portNumber = bufferedReader.readLine();
			RMIPort = Integer.parseInt(portNumber);
			
			
			if(service_name.equals("add")) {
				String registryURL = "rmi://" + hostName + ":" + portNumber + "/add";
				AddInterface addInterface = (AddInterface) Naming.lookup(registryURL);
				System.out.println("LookUp Completed");
				int x=5,y=6,result;  //**********************
				result = addInterface.Add(x, y);
				System.out.println("Addition Result is: " + result);
				SubtractInterface subtractInterface = (SubtractInterface)Naming.lookup(registryURL);
				
			}
			else {
				String registryURL = "rmi://" + hostName + ":" + portNumber + "/subtract";
				SubtractInterface subtractInterface = (SubtractInterface)Naming.lookup(registryURL);
				System.out.println("LookUp Completed");
				int x=5,y=6,result;
				result = subtractInterface.subtract(x, y);
				System.out.println("Subtraction Result is: " + result);
			}
		
			
		} catch (Exception e) {
			System.out.println("Exception in Addclient is: " + e);
		}

	}

}
