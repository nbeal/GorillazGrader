	
	import javax.tools.*;
	import java.io.*;
	import java.util.*;
	
	public class SimpleCompileTest 
	{
		public static void main(String[] args) 
		{
			
			try{
				File[] files;
				File dir = new File("LinkedList");
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
				FileOutputStream errorStream = new FileOutputStream("Errors.txt");
				
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

					int compilationResult =	compiler.run(null, null, errorStream, filenames);
							if(compilationResult == 0){
								System.out.println("Compilation is successful");
							}else{
								System.out.println("Compilation Failed");
							}
			}catch(Exception e)
			{
				System.out.println("error in compiler");
			}
		}catch(Exception h)
		{
			System.out.println("error in filename");
		}
		}
	
	}
