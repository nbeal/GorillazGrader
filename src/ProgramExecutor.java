import java.io.IOException;
import java.io.*;
import java.util.*;

public class ProgramExecutor
{
	private String directory;
	private File dir;
	
	public ProgramExecutor(String filename)
	{
		directory = filename;
		dir = new File(directory);
	}
		
		public void runProgram()
		{
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("java Tester", null, dir).getInputStream()));
				
				File file = new File("Results/" + dir.getName() + ".txt");
				FileOutputStream fis = new FileOutputStream(file);
				PrintStream out = new PrintStream(fis);
				System.setOut(out);  //Overrides System.out to file ("Assuming direct control")
				
				System.out.println("RUNNING TESTER IN DIRECTORY: \\" + directory + " \nResults: \n");
				String line = null;
				while((line = in.readLine()) != null) {
  					System.out.println(line);
				}
				System.out.println("\n\n--END OF LINE--\n");
				in.close();
				out.close();
				
			}catch(Exception e)
			{
				System.out.println("error running");
				e.printStackTrace();
			}
		}
}
