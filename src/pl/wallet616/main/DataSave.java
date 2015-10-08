package pl.wallet616.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataSave {

	public static boolean saveData(int line, String message) {
		try {
			File file = new File("/home/wallet616/data.dat");
			
			BufferedReader in = new BufferedReader(new FileReader("/home/wallet616/data.dat"));
		    
			try 
			{
				message = message.replaceAll("\\s+", " ");
				
				if ((message.substring(0, 1)).equals(" ")) {
					message = message.substring(1);
				}
				if ((message.substring((message.length() - 1), message.length())).equals(" ")) {
					message = message.substring(0, (message.length() - 1));
				}
			}
			catch (Exception e)
		    {
				message = "Nothing.";
			}
			
			String str = "";
			String newstr = "";
			int c = 0;
		    while ((str = in.readLine()) != null)
		    {
		    	if (line == c) 
		    	{
		    		newstr += message;
		    	} else {
		    		newstr += str;
		    	}
		    	c++;
		    }
		    in.close();
		    
		    FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(newstr);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
