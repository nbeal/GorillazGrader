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

    private static void beginMethodReplacement()
    {
        String file = "./Input/Thing/Stock.java";
        BufferedReader br = null;
        String block = "";
        ArrayList<MethodHolder> mhList = new ArrayList<MethodHolder>();

        try {
            br = new BufferedReader(new FileReader(file));

            String cur, prev = "";
            boolean inBlock = false;
            int count = 1;
            int start = 0;
            int end = 0;
            Pattern sp = Pattern.compile("(private|public|protected)\\s\\w(.)*\\((.)*\\)[^;]");
            Pattern ep = Pattern.compile("\\}\\/\\/ end");

            while ((cur = br.readLine()) != null) {
                if (sp.matcher(cur).find()) {
                    System.out.println(prev);
                    start = count;
                    inBlock = true;
                }
                if (inBlock)
                    block += cur + "\n";
                if (ep.matcher(cur).find()) {
                    end = count;
                    inBlock = false;

                }

                count++;
                prev = cur;
            }
            System.out.println("--------------------");
            System.out.println("Block of code: ");
            System.out.println(block);
        } catch (IOException e) {e.printStackTrace();}

    }// end

}
