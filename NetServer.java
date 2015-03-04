import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class NetServer {

	public static void connectToClient() throws IOException {
		ServerSocket server = new ServerSocket(1234);
		Socket connection = server.accept();
		
		InputStream input = connection.getInputStream();
		FileOutputStream wr = new FileOutputStream(new File("C:\\Users\\Kyler\\Downloads\\test\\received.txt"));
		byte[] outBuffer = new byte[connection.getReceiveBufferSize()];
		int bytesReceived = 0;
		
		while((bytesReceived = input.read(outBuffer))>0)
		{
			wr.write(outBuffer,0,bytesReceived);
		}
		wr.close();
		input.close();
		server.close();
		connection.close();
	}
}
