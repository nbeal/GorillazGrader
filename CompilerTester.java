import java.util.*;

public class CompilerTester
{
    public static void main(String[] args)
    {
        ApeCompiler compiler = new ApeCompiler("Thing");
        ArrayList<String> errors = compiler.run();
        
        for(String s : errors)
            System.out.println(s);
    }
}
