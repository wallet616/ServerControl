package pl.wallet616.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataRead {

	public static String readData(int i) {
		String str = "";
		try {
			File folder = new File("/home/wallet616");
			File file = new File("/home/wallet616/data.dat");
	
			if (!folder.exists()) {
				folder.mkdirs();
			}
			if (!file.exists()) {
				
					file.createNewFile();
					
				
				String content = "Unassigned\n";
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			}
		    
			str = Files.readAllLines(Paths.get("/home/wallet616/data.dat")).get(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		
		return str;
	}

}
