import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddImplementClass extends UnicastRemoteObject implements AddInterface {

	protected AddImplementClass() throws RemoteException {
		super();
		
	}

	@Override
	public int Add(int x, int y) throws RemoteException {
		
		return (x+y);
	}

}
