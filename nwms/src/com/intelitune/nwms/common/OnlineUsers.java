package com.intelitune.nwms.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OnlineUsers {
	
	private static Map<String, String> users = new HashMap<String, String>();
	private static long count = 0; 
	public synchronized long getCount() {
		return count++;
	}
	public static String userLogin(String username) {
		String key = String.valueOf(new Date().getTime()) + "-" + String.valueOf(count);
		users.put(username, key);
		return key;
	}
	public static boolean checkLogin(String username, String key) {
		if (username==null) return false;
		String st = users.get(username);
		if (st==null)
			return false;
		if (!st.equals(key))
			return false;
		return true;
	}
	
}
