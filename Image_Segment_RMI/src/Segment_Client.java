import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.imageio.ImageIO;

public class Segment_Client {

	public static void main(String[] args) {
		Segment_Client segment_Client = new Segment_Client();
		segment_Client.connect_to_server();

	}
	
	private void connect_to_server() {
		try {
			Registry registry1 = LocateRegistry.getRegistry("localhost",1095);
			String registry_URL1 = "rmi://localhost" + 1095 + "server";
			Interface interface1 = (Interface) registry1.lookup(registry_URL1);
			System.out.println("Connected to Server1");

			Registry registry2 = LocateRegistry.getRegistry("localhost",1096);
			String registry_URL2 = "rmi://localhost" + 1096 + "server";
			Interface interface2 = (Interface) registry2.lookup(registry_URL2);
			System.out.println("Connected to Server2");
			
			Registry registry3 = LocateRegistry.getRegistry("localhost",1097);
			String registry_URL3 = "rmi://localhost" + 1097 + "server";
			Interface interface3 = (Interface) registry3.lookup(registry_URL3);
			System.out.println("Connected to Server3");

			Registry registry4 = LocateRegistry.getRegistry("localhost",1098);
			String registry_URL4 = "rmi://localhost" + 1098 + "server";
			Interface interface4 = (Interface) registry4.lookup(registry_URL4);
			System.out.println("Connected to Server4");
			
			boolean intf1 = interface1.status();
			boolean intf2 = interface2.status();
			boolean intf3 = interface3.status();
			boolean intf4 = interface4.status();
			
			System.out.println("Status of SErvers(1/2/3/4): " + intf1 + " " + intf2 + " " + intf3 + " " + intf4);
			int rows = 2;   //we assume the no. of rows and cols are known and each chunk has equal width and height
	        int cols = 2;
			cut_image("E:/image1.jpg", rows, cols);
			
			byte[] image_byte0, image_byte1,  image_byte2,  image_byte3;
			image_byte0 = byte_array_out("E:/img0.jpg");
			image_byte1 = byte_array_out("E:/img1.jpg");
			image_byte2 = byte_array_out("E:/img2.jpg");
			image_byte3 = byte_array_out("E:/img3.jpg");
			
			// Passing to Server
			byte[] image_output0, image_output1, image_output2, image_output3, full_output;
			image_output0 = interface1.process_segment(image_byte0);
			image_output1 = interface2.process_segment(image_byte1);
			image_output2 = interface3.process_segment(image_byte2);
			image_output3 = interface4.process_segment(image_byte3);
			
			/*ByteArrayOutputStream outputStream = new ByteArrayOutputStream( ); //************
			outputStream.write( image_output0);
			outputStream.write( image_output1);
			outputStream.write( image_output2);             // Byte Array Concatination 
			outputStream.write( image_output3);

			full_output = outputStream.toByteArray( ); //********* */
			
			InputStream inputStream1 = new ByteArrayInputStream(image_output0);
			BufferedImage bufferedImage1 = ImageIO.read(inputStream1);
			
			InputStream inputStream2 = new ByteArrayInputStream(image_output1);
			BufferedImage bufferedImage2 = ImageIO.read(inputStream2);
			
			InputStream inputStream3 = new ByteArrayInputStream(image_output2);
			BufferedImage bufferedImage3 = ImageIO.read(inputStream3);
			
			InputStream inputStream4 = new ByteArrayInputStream(image_output3);
			BufferedImage bufferedImage4 = ImageIO.read(inputStream4);
			
			

			ImageIO.write(bufferedImage1, "jpg", new File("E:/image_out0.jpg"));   //****
			ImageIO.write(bufferedImage2, "jpg", new File("E:/image_out1.jpg"));
			ImageIO.write(bufferedImage3, "jpg", new File("E:/image_out2.jpg"));
			ImageIO.write(bufferedImage4, "jpg", new File("E:/image_out3.jpg"));
			System.out.println("Image Received and Saved");
			
			
			// Image Concatenate
			
	        int chunks = rows * cols;

	        int chunkWidth, chunkHeight;
	        int type;
	        //fetching image files
	        File[] imgFiles = new File[chunks];
	        for (int i = 0; i < chunks; i++) {
	            imgFiles[i] = new File("E:/image_out" + i + ".jpg");
	        }

	       //creating a bufferd image array from image files
	        BufferedImage[] buffImages = new BufferedImage[chunks];
	        for (int i = 0; i < chunks; i++) {
	            buffImages[i] = ImageIO.read(imgFiles[i]);
	        }
	        type = buffImages[0].getType();
	        chunkWidth = buffImages[0].getWidth();
	        chunkHeight = buffImages[0].getHeight();

	        //Initializing the final image
	        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);

	        int num = 0;
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
	                num++;
	            }
	        }
	        System.out.println("Image concatenated.....");
	        ImageIO.write(finalImg, "jpeg", new File("E:/finalImg.jpg"));
			
			
			/*
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
			System.out.println("Image Received and Saved"); */
			
		} catch (Exception e) {
			System.out.println("Client Error " + e);
		}
		
	}
	public byte[] byte_array_out(String path) throws IOException  {
		byte[] image_byte;
		BufferedImage original_Image = ImageIO.read(new File(path));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(original_Image, "jpg", byteArrayOutputStream);
		byteArrayOutputStream.flush();
		
		image_byte = byteArrayOutputStream.toByteArray();   //****
		byteArrayOutputStream.close();
		
		return image_byte;
		
		
	}
	void cut_image(String path, int r, int c) throws IOException
	{
		File file = new File(path); // I have bear.jpg in my working directory
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis); //reading the image file

        int rows = r; //You should decide the values for rows and cols variables
        int cols = c;
        int chunks = rows * cols;

        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        System.out.println("Splitting done");

        //writing mini images into image files
        for (int i = 0; i < imgs.length; i++) {
            ImageIO.write(imgs[i], "jpg", new File("E:/img" + i + ".jpg"));
        }
        System.out.println("Mini images created");
	}
	
}
