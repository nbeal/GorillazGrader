import java.io.IOException;
import javax.tools.*;
import java.io.*;
import java.util.*;

public class ApeCompiler
{
    private ArrayList<String> _errors = new ArrayList<String>();
    private String directory;

    public ApeCompiler(String directory)
    {
        this.directory = directory;
    }

    public ArrayList run()
    {
        _errors.clear();
        try{
            File[] files;
            File dir = new File(directory);
            files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".java");
                }
            });
            String[] filenames = new String[files.length];
            for(int i = 0; i < files.length; i++)
                filenames[i] = files[i].getPath();

            File file = new File("Errors.txt");
            try{
                ByteArrayOutputStream out= new ByteArrayOutputStream();
                //FileOutputStream errorStream = new FileOutputStream("Errors.txt");
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

                int compilationResult =	compiler.run(null, null, out, filenames);

                if(compilationResult == 0){
                    //System.out.println("Compilation is successful");
                    //runStuff(dir);
                    _errors.add("success");

                }else{
                    System.out.println("Compilation Failed");
                    parser(dir, out);

                }
                return _errors;
            }catch(Exception e)
            {
                System.out.println("error in compiler");
                //e.printStackTrace();
            }
        }catch(Exception h)
        {
            System.out.println("error in filename");
        }
        return null;
    }//end run

    public void runStuff(File dir)
    {
        try {
            Process p = Runtime.getRuntime().exec("java Tester", null, dir);

            BufferedReader in = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("java Tester", null, dir).getInputStream()));

            String line = null;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }


        }catch(Exception e)
        {
            System.out.println("error running");
        }

    }

    /*
     * Warning: The splitter must be changed if the compiler is used on a non-Windows machine
     * due to Windows using "\" in the file path and Linux using "/" in the file path.
     */
    public void parser(File dir, ByteArrayOutputStream out)
    {
        String[] colonSplit;
        String toArrayList, temp, errorStream = out.toString();
        String splitter = dir.getName();
        String[] errorSplit = errorStream.split(splitter);

        for(int i = 0; i < errorSplit.length; i++)
        {
            temp = errorSplit[i];
            //System.out.println(temp);
            if (temp.indexOf(":") != -1) {
                colonSplit = temp.split(":");
                toArrayList = colonSplit[0] + "-" + colonSplit[1];
                _errors.add(toArrayList);
            }
        }
    }
}