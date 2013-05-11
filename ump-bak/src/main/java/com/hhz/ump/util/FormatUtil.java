package com.hhz.ump.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {

	 public static boolean isDecimal(String str) {
		  if(str==null || "".equals(str))
		   return false;  
		  Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		  return pattern.matcher(str).matches();
	 }

	/**
	 * 将财务数字转成浮点小数
	 * @param str 财务数字字符串（例如: 22,041,749.27)
	 * 
	 * 说明： 使用PowerUtils.formatFloat(str),若金额太大，小数点值将会被忽略，产生bug.
	 * 
	 * @param str
	 * @return
	 */
	public static Double formatDouble(String str) {
		
		if(StringUtils.isBlank(str))
			return new Double(0);
		if("NaN.undefined".equals(str.trim()))
			return new Double(0);
		
		String regEx = "[^\\d\\.-]";
		// Pattern p=Pattern.compile(regEx);
		String val = str == null ? "" : str.replaceAll(regEx, "");
		return Double.valueOf(StringUtils.isEmpty(val) ? "0" : val);
	}


	public static BigDecimal formatMoney(String str) {
		return new BigDecimal(formatDouble(str));
		
	}
	public static void main(String[] args) {
//		System.out.println(FormatUtil.formatDouble("17,955,226.25"));
//		PowerUtils.formatFloat(str);

//		//金额特别注意
		String str = "17,955,226.25";//若226.25,正常显示;若值太大，小数点会被截取.
		String regEx = "[^\\d\\.-]";
		// Pattern p=Pattern.compile(regEx);
		String val = str == null ? "" : str.replaceAll(regEx, "");
		
		System.out.println(String.valueOf(Float.valueOf(StringUtils.isEmpty(val) ? "0" : val)));//1.7955226E7
		System.out.println(String.valueOf(Double.valueOf(StringUtils.isEmpty(val) ? "0" : val)));//1.795522625E7
		
		System.out.println(new BigDecimal(Float.valueOf(StringUtils.isEmpty(val) ? "0" : val)));//17955226
		System.out.println(new BigDecimal(Double.valueOf(StringUtils.isEmpty(val) ? "0" : val)));//17955226.25
		
//		System.out.println(formatMoney(" "));
	}
	
	/**
	 * 将数字转成财务数字 (如：1000格式化成 1,000)		
	 * @param str 要格式化的值
	 * @param len  格式化后保留的小数位数
	 * @return
	 */
	public static String formatMoney(BigDecimal value, int len){
		String str = null;
		if(value != null){
			str = value.toString();
		}
		if (str == null || str.length() < 1)
			return "0";
		NumberFormat formater = null;
		double num = Double.parseDouble(str);
		if(len == 0) {
			formater = new DecimalFormat("###,###");
		}else{
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		return result;
	}
}
