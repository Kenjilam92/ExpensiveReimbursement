package tools;
import java.io.*;

public interface GetTextFile {
	public default String getTextFile (String filename) throws IOException {
		FileInputStream a = null;
		try {
			 a = new FileInputStream(getClass().getClassLoader().getResource(filename).getFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (a == null) {
			return "<div>Error: Not Found <div>";
		}
		else {
			BufferedInputStream bis = new BufferedInputStream(a);
			StringBuilder fromFile = new StringBuilder(); 
			while (bis.available() >0) {
				fromFile.append((char) bis.read() );
			}
			return fromFile.toString();
		}
		
	}
}
