
public class ClientTest {

	public static void main(String[] args) {
		String filePath = "C:/Users/Kyler/Downloads/test/KyTest.zip";
		NetClient netClient = new NetClient(filePath);
		netClient.start();
	}
}
