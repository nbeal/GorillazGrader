import java.io.File;
import java.util.ArrayList;

public class CompilerTest {

	public static void main(String[] args) {
		
		System.out.println("initializing Systems...");
		
		String filePathInput = "./Input/BadDHolliday";

        File f = new File(filePathInput);
        String extractedDir = "./Input/" + f.getName();

        System.out.println("Starting compile...");
        ApeCompiler compiler = new ApeCompiler(extractedDir);
        ArrayList<String> compilerErrors = compiler.run();

        if (compilerErrors.get(0).equals("success"))
            System.out.println("Program compiled successfully.");
        else {
            System.out.println("Listing known errors.");
            for (String item : compilerErrors)
                System.out.println(item);
        }
	}

}
