import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Kyler on 3/15/2015.
 */
public class MethodParser {

    private ArrayList<MethodHolder> _methods;
    private String _dir = "";

    public MethodParser(String dir) {
        _dir = dir;
    }

    public ArrayList<MethodHolder> parse() {
        File dir = new File(_dir);
        return findMethods(dir.getAbsolutePath());
    }

    private static ArrayList<MethodHolder> findMethods(String file) {
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
            return mhList;
        } catch (IOException e) {e.printStackTrace();}
        return null;
    }// end

}
