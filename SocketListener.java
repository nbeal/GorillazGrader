import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


public class SocketListener implements Runnable {

	private ExecutorService _service;
	private ServerSocket 	_listener;
	private ServerMonitor 	_monitor;
	
	public SocketListener(ExecutorService service, ServerSocket listener, ServerMonitor monitor) {
		_service 	= service;
		_listener 	= listener;
		_monitor 	= monitor;
	}
	
	@Override
	public void run() {
		while(_monitor.getFinish() == false) {
			try {
				Socket socket = _listener.accept();
				System.out.println("new client has connected.");
				_service.submit(new GraderJob(socket));
			} catch (IOException e) {System.out.println("Socket listener has been closed.");}
		}
	}// end run
}// end SocketListener
