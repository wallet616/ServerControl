package pl.wallet616.main;

import java.math.BigInteger;
import java.util.Calendar;

public class Decode {

	public static String decode(String message) {
		
		// Checking right format.
		String[] Buffor = {"", "", ""};
		
		if ((message.length() - message.replaceAll(":", "").length()) >= 2)
		{
    		try {
    			Buffor = message.split(":", 3);
    	        Integer.parseInt(Buffor[0]);
    	        Integer.parseInt(Buffor[1]);
    	    }
    	    catch(Exception e) {
    	    	return "Error.";
    	    }
		} else {
			return "Error.";
		}
		
		String deoutput = "";  //Output String.
		
		String tabinput = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890~`!@#$%^&*()_+-=[];',./{}:<>? ";  //List of characters.
		char[][] tab = new char[tabinput.length()][5];
		int[] last = new int[4];
		String[] keyN = new String[1];
		
		//Extracting key.
		keybreak:
			for (int c = 0; c < message.length(); c++)
			{
				if (Character.toString(message.charAt(c)).equals(":".toString()) == true)
				{
					for (int d = c + 1; d < message.length(); d++)
					{
						if (Character.toString(message.charAt(d)).equals(":".toString()) == true)
						{
							BigInteger key = new BigInteger(message.substring(0, c)).multiply(new BigInteger(message.substring(c + 1, d)));
							keyN[0] = key.toString();
							message = message.substring(d + 1, message.length());
							break keybreak;  //Ends.
						}
					}
				} else {
					continue;
				}
			}
		
		BigInteger powKey = new BigInteger("6161616").multiply(new BigInteger(keyN[0]).pow(18));  //Key.
		keyN[0] = powKey.toString();
		
		for (int a = 0; a < tabinput.length(); a++)  //Assigns original character and 'code'.
		{
			tab[a][0] = tabinput.charAt(a);  //First column, original character.
		}
		for (int a = 0; a < tabinput.length(); a++)
		{
			tab[a][1] = keyN[0].charAt(a);  //Second column, code assign to character.
			last[0] += Character.getNumericValue(tab[a][1]);  //Counts new position of character.
			if (last[0] >= tabinput.length())  //Check if it is not more than all characters in the table.
			{
				last[0] -= tabinput.length();
			}
			
			if (tab[last[0]][2] != '\u0000')  //Check if new position in the third column is already used. Third column is for the encrypted characters.
			{
				outerloop:
					for (int c = 0; c < tabinput.length(); c++)
					{
						last[0]++;  //Adds one to last[0] and checks again.
						if (last[0] >= tabinput.length())  //Check if it is not more than characters in the table. Again.
						{
							last[0] -= tabinput.length();
						}
						
						if (tab[last[0]][2] == '\u0000')
						{
							break outerloop;  //If tab[last[0]][1] is equal to -1, breaks the loop. Which means position is free.
						} else {
							continue;
						}
					}
			}
			tab[last[0]][2] = tab[a][0];  //Assigns the encrypted character in a new place in table.
		}
		
		last[0] %= 6;
		for (int a = 0; a < tabinput.length(); a++)
		{
			last[0]++;
			if (last[0] >= tabinput.length())  //Check if it is not more than all characters in the table. one more time.
			{
				last[0] -= tabinput.length();
			}
			tab[last[0]][3] = tab[a][2];  //Assigns the encrypted character in a new place in table.
		}
		
		//Encrypting.
		last[1] = 1;
		for (int a = 0; a < message.length(); a++)
		{
			last[2]++;
			
			if (last[1] == last[2])
			{
				for (int x = 0; x < tabinput.length(); x++)
				{
					tab[x][4] = '\u0000';
				}
				keyN[0] = new BigInteger(Integer.toString(tab[last[3]][1])).multiply(powKey).toString();
				
				for (int d = 0; d < tabinput.length(); d++)
				{
					tab[d][1] = keyN[0].charAt(d);
					last[0] += Character.getNumericValue(tab[d][1]);
					if (last[0] >= tabinput.length())
						{
							last[0] -= tabinput.length();
						}
					
					if (tab[last[0]][4] != '\u0000')
					{
						outerLoop:
							for (int e = 0; e < tabinput.length(); e++)
							{
								last[0]++;
								if (last[0] >= tabinput.length())
								{
									last[0] -= tabinput.length();
								}
								
								if (tab[last[0]][4] == '\u0000')
								{
									break outerLoop;
								} else {
									continue;
								}
							}
					}
					tab[last[0]][4] = tab[d][3];
				}
			}
			
			outerloop:
				for (int c = 0; c < tabinput.length(); c++)
				{
					if (Character.toString(message.charAt(a)).equals(Character.toString(tab[c][4])) == true) {
						deoutput += tab[c][0];
						
						if (last[1] == last[2]) 
						{
							last[3]++;
							if (last[3] >= tabinput.length())
							{
								last[3] -= tabinput.length();
							}
							last[2] = 0;
							last[1] = Character.getNumericValue(tab[last[3]][1]);
							if (last[1] == 0)
							{
								last[1]++;
							}
						}
						break outerloop;  //Ends.
					} else {
						continue;
					}
				}
		}
		
		return deoutput;
	}
	
	 public static BigInteger key()  //Key generation. Once every minute.
	 {
		 Calendar c = Calendar.getInstance();
		 BigInteger Key = new BigInteger("6" + c.get(Calendar.DATE) + "6" + c.get(Calendar.MINUTE) + "6" + c.get(Calendar.HOUR_OF_DAY));
		 return Key;
	 }
	
}
