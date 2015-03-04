import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Tester {
	
	private static String curDir = Paths.get(".").toAbsolutePath().normalize().toString();
	private static File inputDir = new File(curDir + "\\Input");
	private static File finishedDir = new File(curDir + "\\Finished");
	private static File resultsDir = new File(curDir + "\\Results");
		
	public static void main(String[] args) {
		
		checkStartupFolders();			
		
		while (true) {
			
		}
	}
	
	private static void checkStartupFolders() {
		
		if (!inputDir.exists())
			inputDir.mkdir();
		if (!finishedDir.exists())
			finishedDir.mkdir();
		if (!resultsDir.exists())
			resultsDir.mkdir();		
	}
}
