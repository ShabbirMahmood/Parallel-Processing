import java.rmi.Remote;

public interface SubtractInterface extends Remote{
	public int  subtract(int x, int y) throws java.rmi.RemoteException;
}
