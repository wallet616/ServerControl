package pl.wallet616.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ts3 {
	public static Boolean ts3(String function) {
		String repeat = "";
		String line;
        Process process;
        Boolean status = false;
        
        try {
        	if (function.equals("status") || function.equals("")) {
        		process = Runtime.getRuntime().exec("service teamspeak3 status");
        		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        		while ((line = br.readLine()) != null) 
        		{
        			if (line.equals("Server is running")) {
        				repeat = "> Server is running.";
        				status = true;
        				break;
        			} else {
        				repeat = "> Server is offline.";
        			}
        		}
        		process.waitFor();
        		process.destroy();
        		
        		System.out.println(repeat);
        		
        	} else if (function.equals("stop")) {
        		process = Runtime.getRuntime().exec("service teamspeak3 stop");
        		status = true;
        		
        		process.waitFor();
        		process.destroy();
        		System.out.println("> Server has been stopped.");
        		
        	} else if (function.equals("start")) {
        		process = Runtime.getRuntime().exec("service teamspeak3 start");
        		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        		while ((line = br.readLine()) != null) 
        		{
        			if (line.equals("TeamSpeak 3 server started, for details please view the log file")) {
        				repeat = "> Server has been run.";
        				status = true;
        				break;
        			} else {
        				repeat = "> Unable to start server.";
        			}
        		}
        		process.waitFor();
        		process.destroy();
        		
        		System.out.println(repeat);
        		
        	} else if (function.equals("restart")) {
        		process = Runtime.getRuntime().exec("service teamspeak3 restart");
        		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        		while ((line = br.readLine()) != null) 
        		{
        			if (line.equals("TeamSpeak 3 server started, for details please view the log file")) {
        				repeat = "> Server has been restarted.";
        				status = true;
        				break;
        			} else {
        				repeat = "> Unable to restart server.";
        			}
        		}
        		process.waitFor();
        		process.destroy();
        		
        		System.out.println(repeat);
        		
        	} else {
        		Help.help(function);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
		return status;
	}
}
