import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Image_Implement extends UnicastRemoteObject implements Image_Interface{

	protected Image_Implement() throws RemoteException {
		super();
		
	}

	@Override
	public byte[] image_process(byte[] image) throws RemoteException {
		System.out.println("Image Received from Client");
		return image;
	}

}
