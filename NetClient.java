import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetClient {
	
	private String _fileInput;
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
		//bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("INSERT_FILE_NAME"));
		Socket connection = new Socket("localhost", 1234);
		//String filePath = "C:\\Users\\Kyler\\Downloads\\test\\";
		//String fileName = "KyTest.zip";
		File f = new File(_fileInput);
		
		//Send file  
		DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
		dos.writeUTF(f.getName());
		FileInputStream fis = new FileInputStream(_fileInput);
		byte[] buffer = new byte[1024];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		dos.flush();
		
		fis.close();
		dos.close();	
		connection.close();
		
		/*
		File transfer = new File("C:\\Users\\Kyler\\Downloads\\test\\" + fileName);
		InputStream in = new FileInputStream(transfer);
		
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
		*/
		
	}
}
