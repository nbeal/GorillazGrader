import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class NetClient {

	private BufferedOutputStream bufferedOutputStream;
	
	public NetClient() {
		
	}
	
	// Client side method.
	public static void connectToServer() throws IOException {
		//bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("INSERT_FILE_NAME"));

		File transfer = new File("C:\\Users\\Kyler\\Downloads\\test\\send.txt");
		InputStream in = new FileInputStream(transfer);
		
		Socket connection = new Socket("localhost", 1234);
		
		OutputStream output = connection.getOutputStream();
		
		byte[] buff = new byte[connection.getSendBufferSize()];
		int bytesRead = 0;
		
		System.out.println(transfer.length()+ " bytes");
		
		while((bytesRead = in.read(buff))>0)
		{
			output.write(buff,0,bytesRead);
		}
		in.close();
		connection.close();
		output.close();
		
	}
}
