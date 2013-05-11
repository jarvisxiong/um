/**
 * 
 */
package com.hhz.core.convert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.hhz.core.utils.NumberUtil;

/**
 * 日期类型转换器
 * 
 * @author huangj 2009-12-8
 */
public class BigdecimalConverter extends StrutsTypeConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util
	 * .Map, java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		String string = arg1[0];
		if (NumberUtil.isNumber(string)) {
			BigDecimal decimal = new BigDecimal(string);
			return decimal;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util
	 * .Map, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		BigDecimal decimal = (BigDecimal) arg1;
		DecimalFormat formattor = new DecimalFormat("#.##");
		String strContent = formattor.format(decimal);
		return strContent;
	}

}
