import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.ArrayList;


public class GraderJob implements Runnable {
	
	private Socket _socket;
    private ServerSocket _serverSocket;
	private final String CUR_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

	public GraderJob(Socket socket) {
		_socket = socket;
	}

	@Override
	public void run() {
		try { connectToClient(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	private void connectToClient() throws IOException {

        DataInputStream dis = new DataInputStream(_socket.getInputStream());
        String fn = dis.readUTF();
        long length = dis.readLong();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("Input\\" + fn));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = dis.read(buffer)) != -1) {
            dos.write(buffer, 0, len);
        }

        dis.close();
        dos.close();

        System.out.println("Received: " + fn);

		scanMethods(fn);
	}

    private void scanMethods(String fn) {
        File zip = new File ("Input/" + fn);

        File studentFolder = new File ("Input/" + fn.substring(0, fn.length() - 4));
        studentFolder.mkdir();
        ZipUtils.extract(zip, studentFolder);

        // Step 2:
        ApeCompiler compiler = new ApeCompiler(studentFolder.getAbsolutePath());
        ArrayList<String> errors = compiler.run();

        if (errors.get(0).equals("success")) {
            // Success. Run it.
            ProgramExecutor executor = new ProgramExecutor(studentFolder.getAbsolutePath());
            executor.runProgram();
            System.out.println("Successful run. Output in Results.\n");
        }
        else {
            System.out.println("\nErrors Found:\n");
            for (String item : errors)
                System.out.println(item);

            replaceBrokenMethods(studentFolder, errors);

            ApeCompiler compiler2 = new ApeCompiler(studentFolder.getAbsolutePath());
            ArrayList<String> errors2 = compiler2.run();

            ProgramExecutor executor = new ProgramExecutor(studentFolder.getAbsolutePath());
            executor.runProgram();
            System.out.println("Replaced errors in file, output in Results.\n");
        }

    }// end scanMethods

    private void replaceBrokenMethods(File folder, ArrayList<String> errors) {
        for (String pair : errors) {
            String fn = pair.split("-")[0].split("\\\\")[1];
            String line = pair.split("-")[1];
            fn = folder.getAbsolutePath() + "\\" + fn;

            MyParser.replace(fn, line);

        }
    }
}
