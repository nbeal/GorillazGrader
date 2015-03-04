import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class GraderJob implements Runnable {
	
	private Socket _socket;

	public GraderJob(Socket socket) {
		_socket = socket;
	}

	@Override
	public void run() {
		connectToClient();
	}
	
	private void connectToClient() {
		
		int bytesRead;
		String inputPath = "C:\\Users\\Kyler\\Downloads\\test\\Input\\";
		
		InputStream in = null;
		
		try {
			in = _socket.getInputStream();
		  
			DataInputStream clientData = new DataInputStream(in); 
	        
			String fileName = clientData.readUTF();   
	        OutputStream output = new FileOutputStream(inputPath + fileName);   
	        long size = clientData.readLong();   
	        byte[] buffer = new byte[1024];   
	        
	        while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)   
	        {   
	            output.write(buffer, 0, bytesRead);   
	            size -= bytesRead;   
	        }
	        
	        clientData.close();
	        output.flush();
	         
	        // Closing the FileOutputStream handle
	        output.close();
			_socket.close();
			
		} catch (IOException e) {e.printStackTrace();} 
		
		System.out.println("Finished receiving file.");
		
		/*
		// Receive the name of the incoming zip file from the client.
		br = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
		incomingFileName = br.readLine();
		//br.close();
		System.out.println("Incoming zip file name: " + incomingFileName);
		
		InputStream input = _socket.getInputStream();
		
		// 
		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Kyler\\Downloads\\test\\Input\\" + incomingFileName));
		
		byte[] outBuffer = new byte[_socket.getReceiveBufferSize()];
		int bytesReceived = 0;
		
		while((bytesReceived = input.read(outBuffer))>0)
		{
			fos.write(outBuffer,0,bytesReceived);
		}
		fos.close();
		input.close();
		_socket.close();
		*/
	}
}
