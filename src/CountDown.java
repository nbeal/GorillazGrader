import java.util.concurrent.TimeUnit;


public class CountDown implements Runnable {

	private int _value;
	
	public CountDown(int value) {
		_value = value;
	}
	
	@Override
	public void run() {
        for (int x = 0; x < _value; x++) {
        	try {
				//Thread.sleep(1000);
        		TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	System.out.println("Terminatng in " + (_value-x) + " seconds...");
        }// end for
        System.out.println("\nServer finished...\nEnd of Line.");
	}
}
