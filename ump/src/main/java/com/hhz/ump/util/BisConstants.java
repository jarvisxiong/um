package com.hhz.ump.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class BisConstants {

	////////////////////////////////合同状态/////////////////////
	/**
	 * 合同状态：新增
	 */
	public static final String STATUS_ADD = "1"; 
	/**
	 * 合同状态：已提交
	 */
	public static final String STATUS_SUBMIT = "2"; 
	/**
	 * 合同状态：已审核
	 */
	public static final String STATUS_APPROVED = "3"; 
	/**
	 * 合同状态：已驳回
	 */
	public static final String STATUS_BACK = "4"; 
	/**
	 * 合同状态：待变更
	 */
	public static final String STATUS_NEED_CHANGE = "5"; 
	/**
	 * 合同状态：变更待审核
	 */
	public static final String STATUS_CHANGE_APPROVE = "6"; 
	/**
	 * 合同状态：变更已驳回
	 */
	public static final String STATUS_CHANGE_BACK = "7"; 
	
////////////////////////////////合同大类/////////////////////
	/**
	 * 合同大类：商铺
	 */
	public static final String STORE = "1";
	/**
	 * 合同大类：公寓
	 */
	public static final String FLAT = "2";
	/**
	 * 合同大类：多经
	 */
	public static final String MULTI = "3";
	
////////////////////////////////合同小类/////////////////////
	/**
	 * 合同小类：租赁
	 */
	public static final String RENT = "1";
	/**
	 * 合同小类：返租
	 */
	public static final String BACK = "2";
	/**
	 * 合同小类：物业
	 */
	public static final String PROP = "3";
	/**
	 * 合同小类：委托
	 */
	public static final String ENTR = "4";
	/**
	 * 合同小类：业主物业
	 */
	public static final String OWNER_PROP = "6";
	
	
	public static final String BIZ_MODULE_CD = "bisCont"; //BisCont附件目录
	
	public static Map<Integer, String> letters = new LinkedHashMap<Integer, String>();
	
	static {
		letters.put(1, "A");
		letters.put(2, "B");
		letters.put(3, "C");
		letters.put(4, "D");
		letters.put(5, "E");
		letters.put(6, "F");
		letters.put(7, "G");
		letters.put(8, "H");
		letters.put(9, "I");
		letters.put(10, "J");
		letters.put(11, "K");
		letters.put(12, "L");
		letters.put(13, "M");
		letters.put(14, "N");
		letters.put(15, "O");
		letters.put(16, "P");
		letters.put(17, "Q");
		letters.put(18, "R");
		letters.put(19, "S");
		letters.put(20, "T");
		letters.put(21, "U");
		letters.put(22, "V");
		letters.put(23, "W");
		letters.put(24, "X");
		letters.put(25, "Y");
		letters.put(26, "Z");
		letters.put(27, "AA");
		letters.put(28, "AB");
		letters.put(29, "AC");
		letters.put(30, "AD");
		letters.put(31, "AE");
		letters.put(32, "AF");
		letters.put(33, "AG");
		letters.put(34, "AH");
		letters.put(35, "AI");
		letters.put(36, "AJ");
		letters.put(37, "AK");
		letters.put(38, "AL");
		letters.put(39, "AM");
		letters.put(40, "AN");
		letters.put(41, "AO");
		letters.put(42, "AP");
		letters.put(43, "AQ");
		letters.put(44, "AR");
		letters.put(45, "AS");
		letters.put(46, "AT");
		letters.put(47, "AU");
		letters.put(48, "AV");
		letters.put(49, "AW");
		letters.put(50, "AX");
		letters.put(51, "AY");
		letters.put(52, "AZ");
	}
}
