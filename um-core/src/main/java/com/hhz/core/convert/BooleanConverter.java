/**
 * 
 */
package com.hhz.core.convert;

import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 日期类型转换器
 * 
 * @author huangj 2009-12-8
 */
public class BooleanConverter extends StrutsTypeConverter {

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
		Boolean decimal = BooleanUtils.toBooleanObject(string);
		return decimal;
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
		Boolean boolean1 = (Boolean) arg1;
		String strContent = BooleanUtils.toStringTrueFalse(boolean1);
		return strContent;
	}

}
