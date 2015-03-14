
public class ServerMonitor {

	private boolean finish = false;
	
	public ServerMonitor() {
		
	}
	
	public synchronized void setFinish(boolean boo) {
		finish = boo;
	}
	
	public synchronized boolean getFinish() {
		return finish;
	}
}
