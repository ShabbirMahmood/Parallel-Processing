import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Interface_Implement4 extends UnicastRemoteObject implements Interface {

	protected Interface_Implement4() throws RemoteException {
		super();
	}

	@Override
	public byte[] process_segment(byte[] image) throws RemoteException {
		return image;
		
	}

	@Override
	public boolean status() {
		boolean s = true;
		return s;
	}
}
