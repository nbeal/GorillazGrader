import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Kyler on 3/17/2015.
 */
public class MyParser {

    public static void replace(String fn, String line) {
        File studentFile = new File(fn);
        String studentFileName = studentFile.getName();
        File solutionFile = new File("Solution\\" + studentFileName);
        int num = Integer.parseInt(line);

        ArrayList<MethodHolder> studentMethods = new MethodParser(studentFile.getAbsolutePath()).parse();
        ArrayList<MethodHolder> solutionMethods = new MethodParser(solutionFile.getAbsolutePath()).parse();

        for (int x = 0; x < studentMethods.size(); x++) {
            int start = studentMethods.get(x).getStart();
            int end   = studentMethods.get(x).getEnd();

            if (num > start && num < end) {
                System.out.println("Method to replace: ");
                System.out.println(studentMethods.get(x).getBlock());
                System.out.println("Solution method: ");
                System.out.println(solutionMethods.get(x).getBlock());

                doWork(studentFile.getAbsolutePath(), studentMethods.get(x), solutionMethods.get(x));
            }
        }


    }

    private static void doWork(String fn, MethodHolder stu, MethodHolder sol) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fn));

            String cur = "";
            int count = 0;
            String newFile = "";
            boolean done = false;
            while ((cur = br.readLine()) != null) {
                if (count+1 >= stu.getStart() && count+1 <= stu.getEnd() && !done) {
                    newFile += sol.getBlock();
                    done = true;
                    for (int x = 0; x < (sol.getEnd() - stu.getStart()); x++)
                        if((cur = br.readLine()) != null);
                }
                else {
                    newFile += cur + "\n";
                }
                count++;
            }
            br.close();

            PrintWriter out = new PrintWriter(fn);
            out.println(newFile);
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
