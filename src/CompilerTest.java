import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompilerTest {

	public static void main(String[] args) {
		
		System.out.println("initializing Systems...");
		
		String filePathInput = "./Input/Thing";
        ApeCompiler compiler = new ApeCompiler("./Input/Thing");
        ArrayList<String> compilerErrors = compiler.run();
        for (String item : compilerErrors)
            System.out.println("output-->" + item);

        if (!compilerErrors.get(0).equals("success")) {
            beginMethodReplacement();
        }

        System.out.println("\nEnd of Line.");
	}

    private static void beginMethodReplacement() {
        String file = "./Input/Stock.java";
        BufferedReader br = null;
            String block = "";

                try {
                    br = new BufferedReader(new FileReader(file));

                    String cur, prev = "";
                    boolean inBlock = false;
                    int count = 1;
                    int start = 0;
                    int end = 0;
                    Pattern sp = Pattern.compile("\\{");
                    Pattern ep = Pattern.compile("\\}\\/\\/ end");

                    while ((cur = br.readLine()) != null) {
                        if (sp.matcher(cur).find()) {
                            System.out.println(prev);
                            start = count;
                            inBlock = true;
                        }
                        if (inBlock)
                            block += cur;
                        if (ep.matcher(cur).find()) {
                            end = count;
                            inBlock = false;
                        }

                        count++;
                        prev = cur;
                    }
                } catch (IOException e) {e.printStackTrace();}
    }

}
