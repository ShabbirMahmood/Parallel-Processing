import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
	public byte[] process_segment(byte[] image) throws RemoteException ;

	public boolean status() throws RemoteException;
}
