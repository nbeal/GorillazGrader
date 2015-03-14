import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.ArrayList;


public class GraderJob implements Runnable {
	
	private Socket _socket;
	private final String CUR_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

	public GraderJob(Socket socket) {
		_socket = socket;
	}

	@Override
	public void run() {
		try { connectToClient(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	private void connectToClient() throws IOException {
		
		String inputPath = CUR_DIR + "\\Input\\";
	
		// Receive the name of the incoming zip file from the client.
		InputStream input = _socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		String incomingFileName = br.readLine();
		System.out.println("Incoming zip file name: " + incomingFileName);

		int incomingFileLength = Integer.parseInt(br.readLine());
		System.out.println("File size: " + incomingFileLength);
		
		File f = new File(incomingFileName);
		incomingFileName = f.getName();
				
		// Create the stream where you write the incoming file. (to the Input folder)
		String filePathInput = inputPath + incomingFileName;
		FileOutputStream fos = new FileOutputStream(new File(filePathInput));
		
		byte[] outBuffer = new byte[incomingFileLength];
		int bytesReceived = 0;
		
		while((bytesReceived = input.read(outBuffer))>0)
		{
			fos.write(outBuffer,0,bytesReceived);
		}
		
		fos.flush();
		fos.close();
		input.close();
		
		System.out.println("Finished receiving file:" + incomingFileName);
		
		extractAndCompile(filePathInput);
	}
	
	private void extractAndCompile(String zip) {

        // Extract the received zip file into a directory of same name. eg: kburnett.zip -> /kburnett/
		ZipExtractor.extract(zip);
		File f = new File(zip);
		String extractedDir = "./Input/" + f.getName();

        // Attempt to compile the java files within the directory.
        // If compilation is successful, the first index in errors will be "success".
        // Otherwise, each index is the filename and the line with the error. eg: MyStocks.java-19
		ArrayList<String> errors = compile(extractedDir);

        if (errors.get(0).equals("success"));
	}

    private ArrayList<String> compile(String dir) {
        System.out.println("Starting compile...");
        ApeCompiler compiler = new ApeCompiler(dir);
        ArrayList<String> compilerErrors = compiler.run();

        if (compilerErrors.get(0).equals("success"))
            System.out.println("Program compiled successfully.");
        else {
            System.out.println("Listing known errors.");
            for (String item : compilerErrors)
                System.out.println(item);
        }
        return compilerErrors;
    }
}
