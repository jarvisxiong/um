/**
 * 
 */
package com.hhz.core.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * @author huangj 2009-12-4
 */

public abstract class SpBaseService<T, PK extends Serializable> {
	public abstract HibernateDao<T, PK> getDao();

	private static final Log log = LogFactory.getLog(SpBaseService.class);
	/**
	 * 执行函数 <br/>
	 * executeStoreProcedure("{?= call fn_find_vessel_seaman2(?,?,?)}",
	 * map,SmmVesselSeamanVo.class);
	 * 
	 */
	public Object executeFunction(String name, Map<Integer, ? extends Object> map, Class entityClass) {
		Object resultObjct;
		List list = new ArrayList();
		Connection connection = getDao().getSession().connection();
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		log.info("excute:" + name);
		try {
			callableStatement = connection.prepareCall(name);
			if (entityClass == null) {
				callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
			} else if (entityClass == String.class) {
				callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			} else {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
			for (int i = 1; i <= map.size(); i++) {
				Object value = map.get(i);
				if (value instanceof String) {
					callableStatement.setString(i + 1, (String) value);
				} else if (value instanceof Date) {
					callableStatement.setDate(i + 1, (Date) value);
				} else if (value instanceof Integer) {
					callableStatement.setInt(i + 1, (Integer) value);
				} else {
					if (value == null) {
						callableStatement.setString(i + 1, null);
					} else {
						callableStatement.setObject(i + 1, value);
					}
				}
				// callableStatement.setObject(i + 1, (String) value);
			}
			callableStatement.execute();
			if (entityClass == null) {
				resultObjct = callableStatement.getObject(1);
			} else if (entityClass == String.class) {
				resultObjct = callableStatement.getObject(1);
			} else {
				resultObjct = list;
				rs = (ResultSet) callableStatement.getObject(1);
			}
			if (rs != null) {
				ResultSetMetaData tsmt = rs.getMetaData();
				int count = tsmt.getColumnCount();
				while (rs.next()) {
					Object result = entityClass.newInstance();
					for (int i = 1; i <= count; i++) {
						String propertyName = column2PropertyName(tsmt.getColumnName(i));
						if (result != null) {
							if (OracleTypes.DATE == tsmt.getColumnType(i)) {
								Date value = rs.getDate(i);
								java.util.Date value1 = value == null ? null : new java.util.Date(value.getTime());
								setValue2Entity(result, propertyName, value1);
							} else if (OracleTypes.NUMERIC == tsmt.getColumnType(i)) {
								Object value = rs.getObject(i);
								setValue2Entity(result, propertyName, value);
							} else if (OracleTypes.VARCHAR == tsmt.getColumnType(i) || OracleTypes.CHAR == tsmt.getColumnType(i)) {
								Object value = rs.getObject(i);
								setValue2Entity(result, propertyName, value);
							}
						} else {
							result = rs.getObject(i);
						}
					}
					list.add(result);
				}

				rs.close();
			}
			// 测试结果:不影响程序
			if (callableStatement != null) {
				callableStatement.close();
			}
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

		}
		return resultObjct;
	}

	private String column2PropertyName(String columnName) {
		StringBuffer propertyName = new StringBuffer();
		String[] str = columnName.split("_");
		propertyName.append(StringUtils.lowerCase(str[0]));
		for (int i = 1; i < str.length; i++) {
			propertyName.append(firstCharToUpperCase(StringUtils.lowerCase(str[i])));
		}
		return propertyName.toString();
	}

	private String firstCharToUpperCase(String str) {
		String result = StringUtils.upperCase(StringUtils.substring(str, 0, 1)) + StringUtils.substring(str, 1);
		return result;
	}

	private void setValue2Entity(Object entity, String propertyName, Object value) {
		try {
			if (value != null) {
				Method method = null;
				if (value instanceof java.util.Date) {
					method = getSetterMethod(propertyName, entity.getClass(), java.util.Date.class);
				} else if (value instanceof String) {
					method = getSetterMethod(propertyName, entity.getClass(), String.class);
				} else if (value instanceof Long) {
					method = getSetterMethod(propertyName, entity.getClass(), Long.class);
					if (method == null) {
						value = new BigDecimal(((Long) value));
					}
				} else if (value instanceof BigDecimal) {
					method = getSetterMethod(propertyName, entity.getClass(), BigDecimal.class);
					if (method == null) {
						method = getSetterMethod(propertyName, entity.getClass(), Long.class);
						value = ((BigDecimal) value).longValue();
					}
					if (method == null) {
						method = getSetterMethod(propertyName, entity.getClass(), Boolean.class);
						value = new BooleanConverter().convert(boolean.class, value);
					}
				}
				if (method != null) {
					method.invoke(entity, new Object[] { value });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Method getSetterMethod(String fieldName, Class entityClass, Class clazz) {
		String methodName = "set" + firstCharToUpperCase(fieldName);
		Method method = null;

		try {
			method = entityClass.getMethod(methodName, new Class[] { clazz });
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		return method;
	}
}
