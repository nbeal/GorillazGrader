import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NetServer {
	
	private static final int SOCKET = 1234;
	private static final int MAX_THREADS = 10;
			
	public static void main(String[] args) throws Exception {
		System.out.println("The server is now running.");
		int clientNumber = 0;
        ServerSocket listener = new ServerSocket(SOCKET);
        //ThreadManager manager = new ThreadManager();
        //manager.getThreadPool().startPool();
        //Thread t = new Thread(manager);
        //t.start();
        
		// Create the thread pool as a service with a maximum defined # of threads available.
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
        
        try {
            while (true) {
            	Socket socket = listener.accept();
            	service.submit(new GraderJob(socket));
            	//Job j = new Job(socket, ++clientNumber);
            	//manager.getThreadPool().getJobQueue().enqueue(j);
            	//manager.getThreadPool().getJobQueue().increaseClientsConnected();
            	//System.out.println(clientNumber + " to queue");
            	
            }
        }catch(Exception e)
        {

        }finally {
            listener.close();
            System.out.println("Server closing...\nEnd of Line.");
        }
	}
}
