import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.imageio.ImageIO;

public class Image_Client {

	public static void main(String[] args) {
		Image_Client image_Client = new Image_Client();
		image_Client.connect_to_server();

	}
	
	private void connect_to_server() {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost",1099);
			String registry_URL = "rmi://localhost" + 1099 + "server";
			
			Image_Interface image_Interface = (Image_Interface) registry.lookup(registry_URL);
			System.out.println("Connected to Server");
			
			// Image Conversion
			byte[] image_byte;
			BufferedImage original_Image = ImageIO.read(new File("E:/image1.jpg"));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(original_Image, "jpg", byteArrayOutputStream);
			byteArrayOutputStream.flush();
			
			image_byte = byteArrayOutputStream.toByteArray();   //****
			byteArrayOutputStream.close();
			
			// Passing to Server
			byte[] image_output = image_Interface.image_process(image_byte);
			
			
			
			InputStream inputStream = new ByteArrayInputStream(image_output);
			BufferedImage bufferedImage = ImageIO.read(inputStream);
			
			ImageIO.write(bufferedImage, "jpg", new File("E:/image_out1.jpg"));   //****
			System.out.println("Image Received and Saved");
			
		} catch (Exception e) {
			System.out.println("Client Error " + e);
		}
		
	}

}
