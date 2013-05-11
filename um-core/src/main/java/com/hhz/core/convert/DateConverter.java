/**
 * 
 */
package com.hhz.core.convert;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.hhz.core.utils.DateOperator;

/**
 * 日期类型转换器
 * 
 * @author huangj 2009-12-8
 */
public class DateConverter extends StrutsTypeConverter {

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
		String dateString = arg1[0];
		Date date = DateOperator.parse(dateString);
		return date;
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
		Date date = (Date) arg1;
		String strContent = null;
		boolean isTime = DateOperator.isTime(date);
		if (isTime) {
			strContent = DateOperator.formatDate(date, DateOperator.FORMAT_STR_WITH_TIME);
		} else {
			strContent = DateOperator.formatDate(date);
		}
		return strContent;
	}

}
