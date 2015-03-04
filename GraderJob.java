import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class GraderJob implements Runnable {
	
	private Socket _socket;

	public GraderJob(Socket socket) {
		_socket = socket;
	}

	@Override
	public void run() {
		try {
			connectToClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void connectToClient() throws IOException {
		
		InputStream input = _socket.getInputStream();
		FileOutputStream wr = new FileOutputStream(new File("C:\\Users\\Kyler\\Downloads\\test\\received.txt"));
		byte[] outBuffer = new byte[_socket.getReceiveBufferSize()];
		int bytesReceived = 0;
		
		while((bytesReceived = input.read(outBuffer))>0)
		{
			wr.write(outBuffer,0,bytesReceived);
		}
		wr.close();
		input.close();
		_socket.close();
	}
}
