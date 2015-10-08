package pl.wallet616.main;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	// Main Variables
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Boolean firstRunApp = false;
	static String selectFunction;

	// Main thread
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Startup
					Thread listener = new Thread(){
						public void run(){
					    	Listener.run();
					    }
					};
					listener.start();
					
					new Main();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Main program 
	public Main() throws IOException {
		
		// Startup info
		System.out.println("##################################################");
		System.out.println("# Creator: wallet616");
		System.out.println("# Current version: 0.1 beta");
		System.out.println("##################################################");
		MenuInfo();
		
		// Menu loop
		while (true) 
		{
			// Input read and formating 
			String selectFunction = input.readLine();
			String[] selectFunctionCutted = {"", ""};
			String selectFunctionFormated;
			
			try 
			{
				selectFunctionFormated = selectFunction.replaceAll("\\s+", " ").toLowerCase();
				
				if ((selectFunctionFormated.substring(0, 1)).equals(" ")) {
					selectFunctionFormated = selectFunctionFormated.substring(1);
				}
				if ((selectFunctionFormated.substring((selectFunctionFormated.length() - 1), selectFunctionFormated.length())).equals(" ")) {
					selectFunctionFormated = selectFunctionFormated.substring(0, (selectFunctionFormated.length() - 1));
				}
				
				if (selectFunctionFormated.length() > selectFunctionFormated.replaceAll("\\s+", "").length())
				{
					selectFunctionCutted = selectFunctionFormated.split(" ", 2);
				} else {
					selectFunctionCutted[0] = selectFunctionFormated;
				}
			}
			catch (Exception e)
		    {
				selectFunctionCutted[0] = "Nothing.";
			}
			
			// Selecting function
			if ((selectFunctionCutted[0]).equals("menu") || (selectFunctionCutted[0]).equals("1")) {
				MenuInfo();
			} else if ((selectFunctionCutted[0]).equals("help") || (selectFunctionCutted[0]).equals("2")) {
				Help.help(selectFunctionCutted[1]);
			} else if ((selectFunctionCutted[0]).equals("ts3") || (selectFunctionCutted[0]).equals("3")) {
				Ts3.ts3(selectFunctionCutted[1]);
			} else if ((selectFunctionCutted[0]).equals("security") || (selectFunctionCutted[0]).equals("4")) {
				Security.security(selectFunctionCutted[1]);
			} else if ((selectFunctionCutted[0]).equals("Nothing.")) {
				System.out.println("> Unreadable command.");
			} else {
				System.out.println("> Command unreconized.");
			}
		}
	}
	
	// Menu Console
	public void MenuInfo() {
		
		// First run
		if (!firstRunApp) 
		{
			firstRunApp = true;
		} else {
			System.out.println("##################################################");
		}
		
		// Menu console information
		System.out.println("# List of all avaiable function of program: ");
		System.out.println("# 1 or MENU - Display menu of all functions.");
		System.out.println("# 2 or HELP <FUNCTION> - Display help of function.");
		System.out.println("# 3 or TS3 - Function of TS3 server control.");
		System.out.println("# 4 or SECURITY - Security functions control.");
		System.out.println("##################################################");
	}
}
