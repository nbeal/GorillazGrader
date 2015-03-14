import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class ServerWorker implements Runnable{

	private final int SOCKET = 1234;
	private ServerMonitor _monitor;
	private ExecutorService _service;
	
	public ServerWorker(ExecutorService service, ServerMonitor monitor) {
		_monitor = monitor;
		_service = service;
	}
	
	public void run() {
		try {
			waitForConnections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void waitForConnections() throws IOException {
		ServerSocket listener = new ServerSocket(SOCKET);

		Thread socketListener = new Thread(new SocketListener(_service, listener, _monitor));
        socketListener.start();
        
        // Wait for the user to enter the exit command.
        while (_monitor.getFinish() == false);

        // Do some cleanup and finish.
        listener.close();
        System.out.println("ServerWorker finished.");
	}
}
