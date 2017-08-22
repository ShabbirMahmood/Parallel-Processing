import java.rmi.Remote;

public interface AddInterface extends Remote {
	public int Add(int x, int y)throws java.rmi.RemoteException;

}
