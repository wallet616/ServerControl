package pl.wallet616.main;

public class Help {
	public static void help(String function) {
		
		// Help for Menu
		if (function.equals("menu") || function.equals("1")) {
			System.out.println("> Type MENU too see list of all avaiable finctions.");
		} else if (function.equals("help") || function.equals("2")) {
			System.out.println("> Seriously?");
		} else if (function.equals("ts3") || function.equals("3")) {
			System.out.println("> Type TS3 [STATUS] to display Ts3 server status.");
			System.out.println("> Type TS3 START to start Ts3 server.");
			System.out.println("> Type TS3 STOP to stop Ts3 server.");
			System.out.println("> Type TS3 RESTART to restart Ts3 server.");
		} else if (function.equals("security") || function.equals("4")) {
			System.out.println("> Type SECURITY KEY to display security");
			System.out.println("> keys and status.");
			System.out.println("> Type SECURITY GENERATE to generate new key.");
			System.out.println("> Type SECURITY CLEAR to delete last key.");
		} else {
			System.out.println("> Type HELP <FUNCTION> to get help of that function.");
		}
		
	}
}
