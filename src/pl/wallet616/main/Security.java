package pl.wallet616.main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
	public static Boolean security(String function) {
		if (function.equals("status") || function.equals("")) {
			return true;
		} else if (function.equals("key")) {
			String currentKey = DataRead.readData(0);
			System.out.println("> Current security key: " + currentKey);
			if (currentKey.length() >= 15) {
				return true;
			}
			
		} else if (function.equals("generate")) {
			String getKey = generateKey();
			DataSave.saveData(0, getKey);
			System.out.println("> New security key: " + getKey);
			return true;
			
		} else if (function.equals("clear")) {
			String content = "Unassigned\n";
			DataSave.saveData(0, content);
			System.out.println("> Security key has been removed.");
			return true;
			
		} else {
    		Help.help(function);
    	}
		
		return false;
	}
	
	public static String generateKey() {
		String repeat = new Integer((int) System.currentTimeMillis()).toString();
        MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        m.update(repeat.getBytes(), 0, repeat.length());
        repeat =  new BigInteger(1, m.digest()).toString(12);
		
		return repeat;
	}

}
