package com.hhz.ump.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.app.AppVocationManager;
import com.hhz.uums.service.WSPlasService;
import com.hhz.uums.service.WebServiceClient;

public class Util {

	private static final Logger logger = Logger.getLogger(Util.class);

	public static WSPlasService getPlasService() {
		return  new WebServiceClient().getPlasService();
	}

	public static String clob2String(Clob clob) throws SQLException, IOException {
		if (clob == null)
			return "";

		String reString = "";
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		StringBuffer sb = new StringBuffer();
		char[] tempbytes = new char[100];
		int byteread = 0;
		// 读入多个字节到字节数组中，byteread为一次读入的字节数
		while ((byteread = br.read(tempbytes)) != -1) {
			sb.append(tempbytes, 0, byteread);
		}
		reString = sb.toString();
		return reString;
	}

	/**
	 * 读取Clob内容
	 * 
	 * @param clob
	 * @return
	 */
	public static String clob2String2(Clob clob) {
		if (clob == null)
			return "";
		String content = "";
		try {
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {
				sb.append(s);
				s = br.readLine();
			}
			content = sb.toString();
		} catch (Exception e) {
			logger.error("读取Clob内容出错", e);
			throw new RuntimeException("读取Clob内容出错", e);
		}
		return content;
	}

	// [邮箱]是否开通
	public static boolean emailOpen(String emailFlag) {
		if (DictContants.UAAP_EMAIL_FLG_ENABLE.equals(emailFlag)
				|| DictContants.UAAP_EMAIL_FLG_DISABLE.equals(emailFlag))
			return true;
		else
			return false;
	}

	// [邮箱]是否生效
	public static boolean emailEnable(String emailFlag) {
		if (DictContants.UAAP_EMAIL_FLG_ENABLE.equals(emailFlag))
			return true;
		return false;
	}

	// [EAS]是否开通
	public static boolean easOpen(String easFlag) {
		if (DictContants.UAAP_EAS_FLG_ENABLE.equals(easFlag) || DictContants.UAAP_EAS_FLG_DISABLE.equals(easFlag))
			return true;
		else
			return false;
	}

	// [EAS]是否生效
	public static boolean easEnable(String easFlag) {
		if (DictContants.UAAP_EAS_FLG_ENABLE.equals(easFlag))
			return true;
		return false;
	}

	// [EAS]是否失效
	public static boolean easDisable(String easFlag) {
		if (DictContants.UAAP_EAS_FLG_DISABLE.equals(easFlag))
			return true;
		return false;
	}

	// [明源]是否开通
	public static boolean mysoftOpen(String mysoftFlag) {
		if (DictContants.UAAP_MYSOFT_FLG_ENABLE.equals(mysoftFlag)
				|| DictContants.UAAP_MYSOFT_FLG_DISABLE.equals(mysoftFlag))
			return true;
		else
			return false;
	}

	// [cmail]是否开通
	public static boolean cmailOpen(String cmailFlg) {
		if (DictContants.CMAIL_FLG_ENABLE.equals(cmailFlg) || DictContants.CMAIL_FLG_DISABLE.equals(cmailFlg))
			return true;
		else
			return false;
	}

	// [cmail]是否生效
	public static boolean cmailEnable(String cmailFlg) {
		if (DictContants.CMAIL_FLG_ENABLE.equals(cmailFlg))
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {
		String s = "a加b等于c，如果a等1、b等于2，那么c等3";
	}

	/**
	 * 搜索是否包含假期、周末
	 * 
	 */
	public static boolean isContainHoliday(Date dFrom, Date dTo) {
		AppVocationManager vocationManager = SpringContextHolder.getBean("appVocationManager");
		boolean flag = false;
		flag = vocationManager.isContainHoliday(dFrom, dTo);
		return flag;
	}
	public static boolean isContainImpDay(Date dFrom, Date dTo) {
		AppVocationManager vocationManager = SpringContextHolder.getBean("appVocationManager");
		boolean flag = false;
		flag = vocationManager.isContainImpDay(dFrom, dTo);
		return flag;
	}
	/**
	 * 判断是否是假期、周末
	 * 
	 */
	public static boolean isHoliday(Date dDate) {
		AppVocationManager vocationManager = SpringContextHolder.getBean("appVocationManager");
		boolean flag = false;
		flag = vocationManager.isHoliday(dDate);
		return flag;
	}

	/**
	 * DESC:将结果集resultset封装到指定 bean
	 * 
	 * @param bean
	 *            需要封装的vo
	 * @param map
	 *            需要转换的map
	 * @return 已经封装好数据的vo（object）
	 */
	public static List<Object> ResultsToBeans(Object bean, List<Object> maps) {
		List<Object> results = new ArrayList<Object>();
		for (Object m : maps) {
			results.add(ResultToBean(bean, (Map) m));
		}
		return results;
	}

	public static Object ResultToBean(Object bean, Map map) {
		Map methods = new HashMap();
		Method m[] = bean.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			Method method = m[i];
			String methodName = method.getName().toUpperCase();
			methods.put(methodName, method);
		}

		Iterator it = null;
		String key = "";
		it = map.keySet().iterator();
		while (it.hasNext()) {
			key = (String) it.next();
			String name = "GET" + key.toUpperCase();
			if (methods.containsKey(name)) {
				Method setMethod = (Method) methods.get("SET" + key.toUpperCase());
				try {
					if (setMethod != null) {
						Object[] obj = null;
						obj = new Object[1];
						obj[0] = map.get(key);
						setMethod.invoke(bean, obj);
					} else {
						continue;
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}
		return bean;
	}

	/**
	 * 获取map中第一个键值对的key
	 */
	public static String getMapFirstKey(Map lhsMap) {
		Iterator lit = lhsMap.entrySet().iterator();
		if (lit.hasNext()) {
			Map.Entry e = (Map.Entry) lit.next();
			return e.getKey().toString();
		}
		return null;
	}
	/**
	 * 获取对象field-value
	 * author:jiaoxiaofeng  2011-12-16
	 * @param entityName
	 * @param param
	 * @return
	 * Map<String,Object>
	 */
	public static Map<String, Object> obj2Param(Object entityName, Map<String, Object> param) {
		Class c = entityName.getClass();
		Field field[] = c.getDeclaredFields();
		StringBuffer sb = new StringBuffer();

		sb.append("------ " + " begin ------\n");
		for (Field f : field) {
			sb.append(f.getName());
			sb.append(" : ");
			try {
				sb.append(invokeMethod(entityName, f.getName(), null));
				sb.append("\n");
				param.put(f.getName(), invokeMethod(entityName, f.getName(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("------ " + " end ------\n"+sb);
		return param;
	}
	
	/**
	 * 反射，获取指定field的value
	 * author:jiaoxiaofeng  2011-12-16
	 * @param owner
	 * @param fieldName
	 * @param args
	 * @return
	 * @throws Exception
	 * Object
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked", "null" })
	public static Object invokeMethod(Object owner, String fieldName, Object[] args) throws Exception {
		Class ownerClass = owner.getClass();

		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = null;
		try {
			method = ownerClass.getMethod("get" + fieldName);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
			return " can't find 'get" + fieldName + "' method";
		}

		return method.invoke(owner);
	}
	
	/**
	 *判断数值
	 */
	 public static boolean isDecimal(String str) {
		  if(str==null || "".equals(str))
		   return false;  
		  Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		  return pattern.matcher(str).matches();
		 }
	 public static void executeFunction(String name, Map<Integer, ? extends Object> map) {
			HibernateDao dao = SpringContextHolder.getBean("HibernateDao");
			Connection connection = dao.getSession().connection();
			CallableStatement callableStatement = null;
			try {
				callableStatement = connection.prepareCall(name);
				for (int i = 0; i < map.size(); i++) {
					Object value = map.get(i);
					if (value instanceof String) {
						callableStatement.setString(i + 1, (String) value);
					} else if (value instanceof Date) {
						callableStatement.setDate(i + 1, (java.sql.Date) value);
					} else if (value instanceof Integer) {
						callableStatement.setInt(i + 1, (Integer) value);
					} else {
						if (value == null) {
							callableStatement.setString(i + 1, null);
						} else {
							callableStatement.setObject(i + 1, value);
						}
					}
				}
				callableStatement.execute();

				if (callableStatement != null) {
					callableStatement.close();
				}
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {

			}
		}
}
