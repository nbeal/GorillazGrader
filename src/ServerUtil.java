import java.io.File;
import java.nio.file.Paths;


public class ServerUtil {
	
	public static void checkForRequiredDirectories() {
		String curDir = Paths.get(".").toAbsolutePath().normalize().toString();
		File inputDir = new File(curDir + "\\Input");
		File finishedDir = new File(curDir + "\\Finished");
		File resultsDir = new File(curDir + "\\Results");
		
		if (!inputDir.exists())
			inputDir.mkdir();
		if (!finishedDir.exists())
			finishedDir.mkdir();
		if (!resultsDir.exists())
			resultsDir.mkdir();
	}
}
