import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetClient {
	
	private String _fileInput;
	private final int SERVER_SOCKET = 1234;
	private final String SERVER_ADDRESS = "localhost";
	
	public NetClient(String fileInput) {
		_fileInput = fileInput;
	}
	
	public void start() {
		try {
			connectToServer();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	// Client side method.
	public void connectToServer() throws IOException {
		
		Socket connection = new Socket(SERVER_ADDRESS, SERVER_SOCKET);

		File f = new File(_fileInput);
		
		long fileSize = f.length();
		String fileName = f.getName();
		
		File transfer = new File(_fileInput);
		InputStream in = new FileInputStream(transfer);
		
		OutputStream output = connection.getOutputStream();
		
		PrintWriter out = new PrintWriter(output, true);
		out.println(fileName);
		
		byte[] buff = new byte[(int) fileSize];
		int bytesRead = 0;
		
		System.out.println(transfer.length()+ " bytes");
		
		while((bytesRead = in.read(buff))>0)
		{
			output.write(buff,0,bytesRead);
		}
		
		output.flush();
		
		in.close();
		output.close();
		connection.close();
		
		
	}
}
