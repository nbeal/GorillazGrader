import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kyler on 3/17/2015.
 */
public class MethodReplaceTest {

    public static void main(String[] args) {

        File zip = new File ("Input/NBeal.zip");
        //System.out.println(zip.getAbsolutePath());
        // Step 1: Extract Student zip file.
        //ZipExtractor.extract(zip.getAbsolutePath());
        File newdir = new File("Input\\NBeal");
        newdir.mkdir();
        ZipUtils.extract(zip, newdir);
        File studentFolder = new File ("Input/NBeal");

        // Step 2:
        //System.out.println(studentFolder.getAbsolutePath());
        ApeCompiler compiler = new ApeCompiler(studentFolder.getAbsolutePath());
        ArrayList<String> errors = compiler.run();

        if (errors.get(0).equals("success")) {
            // Success. Run it.
            System.out.println("here");
            ProgramExecutor executor = new ProgramExecutor(studentFolder.getAbsolutePath(), 0);
            executor.runProgram();
            System.out.println("Successful run. Output in Results.");
        }
        else {
            for (String item : errors)
                System.out.println(item);

            replaceBrokenMethods(studentFolder, errors);
            System.out.println("Made it.");
            ProgramExecutor executor = new ProgramExecutor(studentFolder.getAbsolutePath(), 0);
            executor.runProgram();
            System.out.println("Replaced errors in file, output in Results.");
        }

    }// end main

    private static void replaceBrokenMethods(File folder, ArrayList<String> errors) {
        for (String pair : errors) {
            String fn = pair.split("-")[0].split("\\\\")[1];
            String line = pair.split("-")[1];
            fn = folder.getAbsolutePath() + "\\" + fn;

            MyParser.replace(fn, line);

        }
    }

}// end MethodReplaceTest
