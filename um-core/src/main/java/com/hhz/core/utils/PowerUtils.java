package com.hhz.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangj 2009-11-25
 */
public class PowerUtils {

	private static Logger logger = LoggerFactory.getLogger(PowerUtils.class);

	/**
	 * 确定数组中相同位置的值
	 * 
	 * @param target
	 * @param source
	 * @param sourceKey
	 * @return
	 */
	public static String getSamPlaceValue(String[] target, String[] source, String sourceKey) {
		String strValue = "";
		if (target.length != source.length)
			throw new RuntimeException("length not match");
		int index = ArrayUtils.indexOf(source, sourceKey);
		if (index != -1) {
			strValue = target[index];
		}
		return strValue;
	}

	public static List<String> getProptyArray(List list, String strProp) {
		List<String> lstString = new ArrayList<String>();
		if (list != null) {
			for (Object obj : list) {
				try {
					String strValue = (String) PropertyUtils.getSimpleProperty(obj, strProp);
					lstString.add(strValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return lstString;
	}

	public static void setEmptyStr2Null(Object obj) {

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType() == String.class) {
				field.setAccessible(true);
				try {
					String value = (String) field.get(obj);
					if (value != null && value.isEmpty()) {
						field.set(obj, null);
					}
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Map<String, String> map2I18N(Map<String, String> map) {
		Map<String, String> mapType = new LinkedHashMap<String, String>(4);
		for (String key : map.keySet()) {
			mapType.put(key, MessageUtils.getMessage(map.get(key)));
		}
		return mapType;
	}

	public static String array2String(Collection<String> strArray) {
		return array2String(strArray, ",");
	}

	/**
	 * 将字符串数组，转换成用逗号隔开的字符串
	 * 
	 * @param strArray
	 * @return
	 */
	public static String array2String(String[] strArray) {
		StringBuilder sbTmp = new StringBuilder();
		if (strArray != null) {
			for (String str : strArray) {
				if (str != null) {
					if (sbTmp.length() > 0) {
						sbTmp.append(",");
					}
					sbTmp.append(str.trim());
				}
			}
		}
		return sbTmp.toString();
	}

	public static String array2String(Collection<String> strArray, String split) {
		StringBuilder sbTmp = new StringBuilder();
		if (strArray != null) {
			for (String str : strArray) {
				if (str != null) {
					if (sbTmp.length() > 0) {
						sbTmp.append(split);
					}
					sbTmp.append(str.trim());
				}
			}
		}
		return sbTmp.toString();
	}

	public static String array2String(List<String> strArray) {
		StringBuilder sbTmp = new StringBuilder();
		if (strArray != null) {
			for (String str : strArray) {
				if (str != null) {
					if (sbTmp.length() > 0) {
						sbTmp.append(",");
					}
					sbTmp.append(str.trim());
				}
			}
		}
		return sbTmp.toString();
	}

	public static List<String> array2List(String[] strArray) {
		List<String> lstTmp = new ArrayList<String>();
		for (String string : strArray) {
			lstTmp.add(string);
		}
		return lstTmp;
	}

	/**
	 * 合并数组
	 * 
	 * @param arrays
	 * @return
	 */
	public static String[] uniteArrays(String[]... arrays) {
		String[] rtnVal = null;
		if (arrays != null) {
			if (arrays.length > 0) {
				List<String> lstTmp = new ArrayList<String>();
				for (String[] strings : arrays) {
					lstTmp.addAll(array2List(strings));
				}
				String[] strAll = new String[lstTmp.size()];
				rtnVal = lstTmp.toArray(strAll);
			} else if (arrays.length == 0) {
				rtnVal = arrays[0];
			}
		}
		return rtnVal;
	}

	public static String[] uniteArrays(Collection<String[]> arrays) {
		String[] rtnVal = null;
		if (arrays != null) {
			if (arrays.size() > 0) {
				List<String> lstTmp = new ArrayList<String>();
				for (String[] strings : arrays) {
					lstTmp.addAll(array2List(strings));
				}
				String[] strAll = new String[lstTmp.size()];
				rtnVal = lstTmp.toArray(strAll);
			} else if (arrays.size() == 0) {
				rtnVal = arrays.iterator().next();
			}
		}
		return rtnVal;
	}

	public static <T> T getEntityFromList(final List<T> lstOffice, String strProp, String strValue) {
		Object rtn = null;
		for (Object obj : lstOffice) {
			try {
				String strValueTar = (String) PropertyUtils.getSimpleProperty(obj, strProp);
				if (strValueTar != null && strValue != null) {
					if (StringUtils.equals(strValueTar.trim(), strValue.trim())) {
						rtn = obj;
						break;
					}
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (T) rtn;
	}

	/**
	 * 将一个list按照某个字段拆分成多个list
	 * 
	 * @param list
	 *            子元素为Map对象
	 * @param key
	 * @return
	 */
	public static HashMap splitList(List<HashMap> list, String key) {
		HashMap<String, List<HashMap>> map = new HashMap<String, List<HashMap>>();
		for (HashMap srcMap : list) {
			String value = (String) srcMap.get(key);
			List<HashMap> newList = map.get(value);
			if (newList == null) {
				newList = new ArrayList<HashMap>();
			}
			newList.add(srcMap);
			map.put(value, newList);
		}
		return map;
	}

	public static String object2Str(Object object) {
		if (object == null)
			return "";
		else
			return object.toString();
	}

	public static String getFilePath(String moudelKey, boolean isUpload) {
		return getFilePath(moudelKey, null, isUpload);
	}

	public static String getPropKey(String fileName, String moudelKey) {
		try {
			InputStream is = PowerUtils.class.getClassLoader().getResourceAsStream(fileName);
			PropertyResourceBundle bundle = new PropertyResourceBundle(is);
			String key = (String) bundle.handleGetObject(moudelKey);
			is.close();
			return key;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得文件上传，下载路径
	 * 
	 * @return
	 */
	public static String getFilePath(String moudelKey, String userCd, boolean isUpload) {
		try {
			InputStream is = PowerUtils.class.getClassLoader().getResourceAsStream("file.properties");
			PropertyResourceBundle bundle = new PropertyResourceBundle(is);
			String filePath = (String) bundle.handleGetObject(moudelKey);
			if (filePath == null) {
				filePath = (String) bundle.handleGetObject("rootPath");
			} else {
				if (StringUtils.isNotEmpty(userCd)) {
					filePath += File.separator + userCd;
				}
			}
			is.close();
			if (isUpload) {
				// String ym = DateOperator.formatDate(new Date(), "yyyyMM");
				// filePath += File.separator + ym;
			}
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式浮动数
	 * 
	 * @param str
	 * @return
	 */
	public static Float formatFloat(String str) {
		String regEx = "[^\\d\\.-]";
		// Pattern p=Pattern.compile(regEx);
		String val = str == null ? "" : str.replaceAll(regEx, "");
		return Float.valueOf(StringUtils.isEmpty(val) ? "0" : val);
	}

	/**
	 * 取得文件的路径
	 * 
	 * @param object
	 * @return
	 */
	public static String getFilePath(Class object) {
		String filePath = object.getProtectionDomain().getCodeSource().getLocation().getPath();
		filePath = filePath.substring(filePath.indexOf("/") + 1, filePath.length());
		return filePath;
	}

	public static String dealFilePath(String path) {
		if (path == null)
			return null;
		return path.replaceAll("/", "\\\\");
	}

	/**
	 * 将set转化成list
	 * 
	 * @param set
	 * @return
	 */
	public static List setCollection2List(Collection list) {
		List<Object> lstTemp = new ArrayList<Object>();
		if (list != null) {
			for (Object object : list) {
				lstTemp.add(object);
			}
		}
		return lstTemp;
	}

	/**
	 * 将set转化成list 并根据指定的字段值过滤
	 * 
	 * @param set
	 * @return
	 */
	public static List setCollection2List(Collection list, String condField, Object condValue) {
		List<Object> lstTemp = new ArrayList<Object>();
		if (list != null) {
			for (Object object : list) {
				Object value = null;
				try {
					value = PropertyUtils.getSimpleProperty(object, condField);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (condValue.equals(value)) {
					lstTemp.add(object);
				}
			}
		}
		return lstTemp;
	}

	/**
	 * 根据Map中的条件过滤实体集(Collection)
	 * 
	 * @param list
	 * @param map
	 * @return 符合map条件的数据
	 */
	public static List setCollection2List(Collection list, Map<String, ? extends Object> map) {
		List<Object> lstTemp = null;

		if (list != null) {
			lstTemp = new ArrayList<Object>();
			for (Object object : list) {
				if (map != null) {
					if (validate(object, map)) {
						lstTemp.add(object);
					}
				} else {
					lstTemp.add(object);
				}
			}
		}
		return lstTemp;
	}

	private static boolean validate(Object object, Map<String, ? extends Object> map) {
		boolean flag = false;
		// for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
		Iterator iter = map.keySet().iterator();
		flag = validate(object, iter, map);
		// if (!flag) {
		// break;
		// }
		// }
		return flag;
	}

	private static boolean validate(Object object, Iterator iter, Map<String, ? extends Object> map) {
		boolean flag = true;
		if (iter.hasNext()) {
			String key = (String) iter.next();
			Object value = null;
			try {
				value = PropertyUtils.getSimpleProperty(object, key);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (value != null && value.equals(map.get(key))) {
				flag = validate(object, iter, map);
			} else if (value == null && map.get(key) == null) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;

	}

	public static List<Object> hashMap2List(HashMap<String, Object> hash) {
		return map2List(hash);
	}

	public static List<Object> map2List(Map map) {
		Set set = map.keySet();
		List<Object> list = new ArrayList<Object>();
		for (Object object : set) {
			if (object instanceof String) {
				list.add(map.get(object));
			}
		}
		return list;
	}

	public static boolean isEmpty(Object o) {
		return o != null ? o.toString().trim().length() == 0 : true;
	}

	// 把空的字符串转换为字符串"0"
	public static String null2String0(String s) {
		String rtn;
		rtn = isEmpty(s) ? "0" : s.trim();
		if (isEmpty(rtn)) {
			rtn = "0";
		}
		return rtn;
	}

	public static String trimArray(String oriValue, String split) {
		if (oriValue != null) {
			String[] strs = oriValue.split(split);
			StringBuffer sbRtn = new StringBuffer();
			int index = 1;
			for (String str : strs) {
				sbRtn.append(StringUtils.trim(str));
				if (index < strs.length) {
					sbRtn.append(split);
				}
				index++;
			}
			return sbRtn.toString();
		}
		return null;
	}

	public static String clob2String(Clob clob) throws SQLException, IOException {
		if (clob == null)
			return "";
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

	public static String bSubstring(String s, int length) throws Exception {
		byte[] bytes = s.getBytes("Unicode");
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0) {
				i = i - 1;
			} else {
				i = i + 1;
			}
		}
		return new String(bytes, 0, i, "Unicode");
	}

}
