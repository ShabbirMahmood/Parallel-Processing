import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Interface_Implement1 extends UnicastRemoteObject implements Interface {
	boolean s = true ;
	protected Interface_Implement1() throws RemoteException {
		super();
	}

	@Override
	public byte[] process_segment(byte[] image) throws RemoteException {
		
		s = false;
		return image;
		
		
	}

	//@Override
	public boolean status()  {
		
		return s;
	} 





}
