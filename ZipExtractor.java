import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class ZipExtractor {

	public static void extract(String zipPath) {
		File zip = new File(zipPath);
		
		System.out.println("Checking zip file validity...\n" + zip.getAbsolutePath());
		
		if (zip.exists()) {
			extractToDir(zip);
		}
	}
	
	private static void extractToDir(File zip) {
		String newDirPath = "./Input/" + zip.getName().substring(0, zip.getName().length() - 4);
		File newDir = new File(newDirPath);
		newDir.mkdir();
		
		try {
			ZipFile zipFile = new ZipFile(zip);
		
			ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zip.getAbsolutePath()));
			
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry)entries.nextElement();
				String filePath = newDirPath + "/" + entry.getName();
				
				if (entry.isDirectory()) {
					System.out.println("Type: Directory - " + entry.getName());
					File dir = new File(filePath);
	                dir.mkdir();
				}
				else {
					System.out.println("Type: File      - " + entry.getName());
					extractFile(zipIn, filePath);
				}
			}// end while
			
			zipFile.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}// end extractToDir
	
    private static void extractFile(ZipInputStream zipIn, String filePath) {
        BufferedOutputStream bos = null;
        
		try {
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
		
	        byte[] bytesIn = new byte[4096];
	        int read = 0;
	        while ((read = zipIn.read(bytesIn)) != -1) {
	            bos.write(bytesIn, 0, read);
	        }
	        bos.close();
		} catch (Exception e) {e.printStackTrace();}
    }
}
