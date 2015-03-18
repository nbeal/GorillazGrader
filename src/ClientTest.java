
// concurrent systems

public class ClientTest {

	public static void main(String[] args) {
		String filePath = "C:/Users/Kyler/Downloads/test/NBeal.zip";
		NetClient netClient = new NetClient(filePath);
		netClient.start();
	}
}
