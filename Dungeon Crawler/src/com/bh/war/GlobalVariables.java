package com.bh.war;

import java.util.HashMap;

public class GlobalVariables {
	public static HashMap<String, Object> vars = new HashMap<String, Object>();
	
	public static void set(String key, Object val) {
		vars.put(key, val);
	}
	
	public static Object get(String key) {
		return vars.get(key);
	}
	
	public static void remove(String key) {
		vars.remove(key);
	}
}
