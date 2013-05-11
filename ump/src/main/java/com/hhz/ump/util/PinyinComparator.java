package com.hhz.ump.util;

import java.util.Comparator;

/**
 *拼音比较器
 * 
 * 
 */
public class PinyinComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		String str1 = PingYinUtil.getPingYin((String) o1);
		String str2 = PingYinUtil.getPingYin((String) o2);
		return str1.compareTo(str2);
	}
}