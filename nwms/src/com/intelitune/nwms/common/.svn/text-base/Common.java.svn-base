package com.intelitune.nwms.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
	
	public static String wipeOffHtml(String s) {
		if (s==null) return "";
		s = s.replaceAll("&nbsp;", " ").trim();
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		return m.replaceAll("");

	}

	public static String replaceForXML(String s) {
		if (s==null) return "";
		String s1 = wipeOffHtml(s);
		s1 = s1.replaceAll("&", "&amp;");
		s1 = s1.replaceAll("<", "&lt;");
		s1 = s1.replaceAll(">", "&gt;");
		s1 = s1.replaceAll("\n", "");
		s1 = s1.replaceAll("\r", "");
		s1 = s1.replaceAll("\t", "");
		s1 = s1.trim();
		return s1;
	}
	
	public static int getInt(Object v) {
		try {
			return Integer.parseInt((String)v);
		} catch (Exception e) {
			return -1;
		}
	}

	public Common() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
