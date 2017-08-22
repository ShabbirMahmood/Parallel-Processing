import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubtractImplementClass extends UnicastRemoteObject implements SubtractInterface{

	protected SubtractImplementClass() throws RemoteException {
		super();
	}

	@Override
	public int subtract(int x, int y) throws RemoteException {
		return (x-y);
	}
	

}
