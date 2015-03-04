import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NetServer {
	
	private static final int MAX_THREADS = 10;
	
	private static ExecutorService	_service;
	private static ServerMonitor 	_monitor;
	private static Thread		 	_worker;
					
	public static void main(String[] args) throws Exception {
		
		//Initiate thread pool, thread monitor, and the worker thread.
		Startup();		
		
		// Server will loop until "exit" is entered into the console.
		waitForExitCommand();
        
        // Cleanup - finish and close the server.
		finish();
	}
	
	private static void Startup() {
		System.out.println("The grading server is now running.");
		
		ServerUtil.checkForRequiredDirectories();
		
		/* Create the thread pool as a service with a maximum defined # of threads available.
		 * Create the ServerMonitor - whose goal is to tell threads when an exit command is submitted.
		 * Create the ServerWorker thread. This thread does does the socket work.
		 */		
        _service = Executors.newFixedThreadPool(MAX_THREADS);
        _monitor = new ServerMonitor();
        _worker  = new Thread(new ServerWorker(_service, _monitor));
        
		_worker.start();
	}
	
	private static void waitForExitCommand() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        
        while (line.equalsIgnoreCase("exit") == false) {
        	line = in.readLine();
        }
        System.out.println("Exit has been entered.\nPlease wait for the remaining threads to finish.");
        in.close();
        _monitor.setFinish(true);
	}
	
	private static void finish() throws InterruptedException {
		// Wait for the remaining threads to finish.
        Thread countDown = new Thread(new CountDown(MAX_THREADS));
        countDown.start();
        
        _service.shutdown();
        _service.awaitTermination(10, TimeUnit.SECONDS);
	}
}
