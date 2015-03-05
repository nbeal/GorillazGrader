import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class GraderJob implements Runnable {
	
	private Socket _socket;

	public GraderJob(Socket socket) {
		_socket = socket;
	}

	@Override
	public void run() {
		try { connectToClient(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	private void connectToClient() throws IOException {
		
		String inputPath = "\\Input\\";
	
		// Receive the name of the incoming zip file from the client.
		InputStream input = _socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String incomingFileName = br.readLine();
		File f = new File(incomingFileName);
		incomingFileName = f.getName();

		System.out.println("Incoming zip file name: " + incomingFileName);
				
		// Create the stream where you write the incoming file. (to the Input folder)
		String filePathInput = inputPath + incomingFileName;
		FileOutputStream fos = new FileOutputStream(new File(filePathInput));
		
		byte[] outBuffer = new byte[_socket.getReceiveBufferSize()];
		int bytesReceived = 0;
		
		while((bytesReceived = input.read(outBuffer))>0)
		{
			fos.write(outBuffer,0,bytesReceived);
		}
		
		fos.flush();
		
		System.out.println("Finished receiving file:" + incomingFileName);
		
		fos.close();
		input.close();
		//_socket.close();
	}
}
