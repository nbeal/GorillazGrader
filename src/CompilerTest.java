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

        if (compilerErrors.get(0).equals("success")) {
            //Woohoo it works!
        }
        else {
            replaceErrors(compilerErrors);
        }

        System.out.println("\nEnd of Line.");
	}

    private static void replaceErrors(ArrayList<String> errors) {
        System.out.println();
        for (String error : errors) {
            String[] eArray = error.split("-");
            System.out.println("Filename: " + eArray[0].split("\\\\")[1] + "\nLine of error: " + eArray[1]);
            System.out.println();
        }
    }

    private static void beginMethodReplacement()
    {
        String file = "./Input/Thing/Stock.java";
        BufferedReader br = null;
        String block = "";
        ArrayList<MethodHolder> mhList = new ArrayList<MethodHolder>();

        try {
            br = new BufferedReader(new FileReader(file));

            String name = "";
            String cur, prev = "";
            boolean inBlock = false;
            int count = 1;
            int start = 0;
            int end = 0;
            Pattern sp = Pattern.compile("(?:@[A-Z]\\w*\\s+)*(?:(?:public|private|protected)\\s+)?(?:(?:(?:abstract|final|native|transient|static|synchronized)\\s+)*(?:<(?:\\?|[A-Z]\\w*)(?:\\s+(?:extends|super)\\s+[A-Z]\\w*)?(?:(?:,\\s*(?:\\?|[A-Z]\\w*))(?:\\s+(?:extends|super)\\s+[A-Z]\\w*)?)*>\\s+)?(?:(?:(?:[A-Z]\\w*(?:<[A-Z]\\w*>)?|int|float|double|char|byte|long|short|boolean)(?:(?:\\[\\]))*)|void)+)\\s+(([a-zA-Z]\\w*)\\s*\\(\\s*(((?:[A-Z]\\w*(?:<(?:\\?|[A-Z]\\w*)(?:\\s+(?:extends|super)\\s+[A-Z]\\w*)?(?:(?:,\\s*(?:\\?|[A-Z]\\w*))(?:\\s+(?:extends|super)\\s+[A-Z]\\w*)?)*>)?|int|float|double|char|boolean|byte|long|short)(?:(?:\\[\\])|\\.\\.\\.)?\\s+[a-z]\\w*)(?:,\\s*((?:[A-Z]\\w*(?:<[A-Z]\\w*>)?|int|float|double|char|byte|long|short|boolean)(?:(?:\\[\\])|\\.\\.\\.)?\\s+[a-z]\\w*))*)?\\s*\\))");
            Pattern ep = Pattern.compile("\\}\\/\\/ end");

            while ((cur = br.readLine()) != null) {
                if (sp.matcher(cur).find()) {
                    String[] s1 = cur.split("\\(");
                    String[] s2 = s1[0].split(" ");
                    name = s2[s2.length - 1];
                    start = count;
                    inBlock = true;
                }
                if (inBlock)
                    block += cur + "\n";
                if (ep.matcher(cur).find() && inBlock == true) {
                    end = count;
                    inBlock = false;
                    mhList.add(new MethodHolder(name, block, start, end));
                    block = "";
                }

                count++;
                prev = cur;
            }
            System.out.println("--------------------");
            System.out.println("Methods Found: \n");
            for (int x = 0; x < mhList.size(); x++) {
                System.out.println("Method name: " + mhList.get(x).getName());
                System.out.println("Start point: " + mhList.get(x).getStart());
                System.out.println("End point: " + mhList.get(x).getEnd());
                System.out.println(mhList.get(x).getBlock());
            }
        } catch (IOException e) {e.printStackTrace();}

    }// end

}
