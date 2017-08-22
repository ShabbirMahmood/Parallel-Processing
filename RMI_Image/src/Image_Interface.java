import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Image_Interface extends Remote {
	public byte[] image_process(byte[] image) throws RemoteException;

}
