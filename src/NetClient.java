import java.io.*;
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
		
		Socket socket = new Socket(SERVER_ADDRESS, SERVER_SOCKET);
        OutputStream sos = socket.getOutputStream();
        File f = new File(_fileInput);

        DataOutputStream out = new DataOutputStream(sos);
        out.writeUTF(f.getName());
        DataInputStream dis = new DataInputStream(new FileInputStream(_fileInput));
        ByteArrayOutputStream ao = new ByteArrayOutputStream();

        int read = 0;
        byte[] buf = new byte[1024];
        while ((read = dis.read(buf)) > -1) {
            ao.write(buf, 0, read);
        }

        out.writeLong(ao.size());
        out.write(ao.toByteArray());
        out.flush();
        out.close();
        dis.close();

        //DataInputStream input = new DataInputStream(socket.getInputStream());
        //String value = input.readUTF();
        //System.out.println(value);
        //input.close();
	}
}
