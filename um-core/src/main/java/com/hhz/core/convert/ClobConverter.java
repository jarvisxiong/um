/**
 * 
 */
package com.hhz.core.convert;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.hibernate.lob.ClobImpl;

/**
 * Clob 类型转换器
 * 
 * @author huangj 2009-12-8
 */
public class ClobConverter extends StrutsTypeConverter {

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		String strContent = arg1[0];
		Clob clob = new ClobImpl(strContent);
		return clob;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util.Map, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		Clob clob = (Clob) arg1;
		String strContent = null;
		try {
			strContent = clob.getSubString(1l, (int) clob.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strContent;
	}

}
