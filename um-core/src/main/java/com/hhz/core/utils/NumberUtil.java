/**
 * 
 */
package com.hhz.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * 数字格式化类
 * 
 * @author huangj 2010-12-23
 */
public class NumberUtil {
	public static String formartMonth(int month) {
		// DecimalFormat formattor = new DecimalFormat("00");
		return formatNumber(month, "00");
	}

	public static String formartDay(int day) {
		return formatNumber(day, "00");
	}

	public static String formatNumber(Object value, String formatter) {
		return formatNumber(value, formatter, false);
	}

	/**
	 * @param value
	 * @param formatter
	 *            格式化字符串 如:0.00
	 * @param negative2Null
	 *            负数返回空串
	 * @return
	 */
	public static String formatNumber(Object value, String formatter,
			boolean negative2Null) {
		String rtValue = "";
		if (!PowerUtils.isEmpty(value)) {
			DecimalFormat formattor = new DecimalFormat(formatter);
			if (isFloatNumber(value)) {
				rtValue = formattor.format(value);
			} else if (isNumber(value.toString())) {
				BigDecimal bdValue = new BigDecimal(value.toString());
				rtValue = formattor.format(bdValue);
			} else {
				// throw new NumberFormatException();
				rtValue = value.toString();
			}
		}
		if (negative2Null) {
			rtValue = isZero(rtValue) ? "" : rtValue.trim();
		}
		return rtValue;
	}

	public static boolean isNumber(String str) {
		return Pattern.matches(CoreContants.PATTERN_NUM, str);
	}

	/**
	 * 2位小数
	 * 
	 * @param value
	 * @return
	 */
	public static String formatDecimal2(Object value) {
		return formatNumber(value, "0.00", false);
	}

	/**
	 * 2位小数
	 * 
	 * @param value
	 * @return 如果小于0返回"",否则返回二位小数
	 */
	public static String formatNegativeNull(Object value) {
		return formatNumber(value, "0.00", true);
	}

	/**
	 * 2位小数
	 * 
	 * @param value
	 * @return 如果小于0返回"0"
	 */
	public static String formatNegative0(Object value) {
		return PowerUtils.null2String0(formatNegativeNull(value));
	}

	private static boolean isFloatNumber(Object value) {
		return value instanceof BigDecimal || value instanceof Float
				|| value instanceof Double || value instanceof Integer;
	}

	/**
	 * 
	 * 
	 * @param value
	 * @return 如果等于"0"返回true,或者返回false
	 */
	public static boolean isZero(String value) {
		if ((!PowerUtils.isEmpty(value) && Float.valueOf(value) == 0)
				|| PowerUtils.isEmpty(value))
			return true;
		else
			return false;
	}
}
