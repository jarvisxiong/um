package com.hhz.uums.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;

/**
 * springside已经对json进行封装,本工具在其基础上做一些拓展<br>
 * 主要是为了生成jquery_easyui所需要的json格式的数据
 * 
 * @author shixy 2010-11-10
 */
public class JsonUtil {

	public static String JSON_NAME = "_easy_grid";

	public static String INSERT_RECORDS_KEY = "_inserted";

	public static String UPDATE_RECORDS_KEY = "_updated";

	public static String DELETE_RECORDS_KEY = "_deleted";

	public static String[] DATE_FORMATS = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd" };

	public static void renderJson(final Page<?> page) {
		renderJson(page, null, new String[0], null);
	}

	public static void renderJson(final Page<?> page, Set<String> codeFields) {
		renderJson(page, null, new String[0], codeFields);
	}

	public static void renderJson(final Page<?> page, String[] excludes) {
		renderJson(page, null, excludes, null);
	}

	public static void renderJson(final Page<?> page, String[] excludes, Set<String> codeFields) {
		renderJson(page, null, excludes, codeFields);
	}

	/**
	 * 根据Page自动生成jquery_easyui_grid所需的json数据，包含分页信息
	 * 
	 * @param page
	 *            翻页信息
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	private static void renderJson(final Page<?> page, String dateformat, String[] excludes, Set<String> codeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalCount());
		map.put("rows", page.getResult());
		renderDataJson(map, dateformat, excludes, codeFields);
	}

	public static void renderListJson(final List<?> list) {
		renderListJson(list, null, new String[0], null);
	}

	public static void renderListJson(final List<?> list, String[] excludes) {
		renderListJson(list, null, excludes, null);
	}

	public static void renderListJson(final List<?> list, String[] excludes, Set<String> codeFields) {
		renderListJson(list, null, excludes, codeFields);
	}

	/**
	 * 根据List自动生成jquery_easyui_grid所需的json数据
	 * 
	 * @param list
	 *            列表数据
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	private static void renderListJson(final List<?> list, String dateformat, String[] excludes, Set<String> codeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		renderDataJson(map, dateformat, excludes, codeFields);
	}

	public static void renderTreeJson(final EasyTree easyTree) {
		renderTreeJson(easyTree, null, new String[] {});
	}

	public static void renderTreeJson(final EasyTree easyTree, String[] excludes) {
		renderTreeJson(easyTree, null, excludes);
	}

	public static void renderTreeJson(final List<EasyTree> easyTrees) {
		renderTreeJson(easyTrees, null, new String[] {});
	}

	public static void renderTreeJson(final List<EasyTree> easyTrees, String[] excludes) {
		renderTreeJson(easyTrees, null, excludes);
	}

	/**
	 * 构造easyTree所需要的json
	 */
	private static void renderTreeJson(final EasyTree easyTree, String dateformat, String[] excludes) {
		List<EasyTree> easyTrees = new ArrayList<EasyTree>();
		easyTrees.add(easyTree);
		renderTreeJson(easyTrees, dateformat, excludes);
	}

	private static void renderTreeJson(final List<EasyTree> easyTrees, String dateformat, String[] excludes) {
		renderArrayJson(easyTrees, dateformat, excludes);
	}

	public static void renderArrayJson(final Object obj, String dateformat) {
		renderArrayJson(obj, dateformat, new String[0]);
	}

	public static void renderArrayJson(final Object obj, String dateformat, String[] excludes) {
		JsonConfig config = getJsonConfig(excludes, dateformat, null);
		String jsonString = JSONArray.fromObject(obj, config).toString();
		Struts2Utils.renderText(jsonString);
	}

	public static void renderDataJson(final Object obj, String[] excludes) {
		renderDataJson(obj, null, excludes, null);
	}

	public static void renderDataJson(final Object obj, String[] excludes, Set<String> codeFields) {
		renderDataJson(obj, null, excludes, codeFields);
	}

	private static void renderDataJson(final Object obj, String dateformat, String[] excludes, Set<String> codeFields) {
		JsonConfig config = getJsonConfig(excludes, dateformat, codeFields);
		String jsonString = JSONObject.fromObject(obj, config).toString();
		Struts2Utils.renderText(jsonString);
	}

	private static List<?> getJsonObject(Class<?> clazz, String key) {
		String json = Struts2Utils.getParameter(JSON_NAME);
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray(key);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(DATE_FORMATS));
		return JSONArray.toList(jsonArray, clazz);
	}

	public static List<?> getInsertRecords(Class<?> clazz) {
		return getJsonObject(clazz, INSERT_RECORDS_KEY);
	}

	public static List<?> getUpdatedRecords(Class<?> clazz) {
		return getJsonObject(clazz, UPDATE_RECORDS_KEY);
	}

	public static List<?> getDeletedRecords(Class<?> clazz) {
		return getJsonObject(clazz, DELETE_RECORDS_KEY);
	}

	/**
	 * 返回成功信息  {'success':successInfo}
	 * @param successInfo
	 */
	public static void renderSuccess(String successInfo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", successInfo);
		Struts2Utils.renderJson(map);
	}
	/**
	 * 返回失败信息   {'failure':failInfo}
	 * @param failInfo
	 */
	public static void renderFailure(String failInfo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("failure", failInfo);
		Struts2Utils.renderJson(map);
	}
	private static JsonConfig getJsonConfig(final String[] excludes, String dateformat, Set<String> codeFields) {
		JsonConfig config = new JsonConfig();
		if (excludes.length > 0) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					boolean isExcluede = false;
					for (String exclude : excludes) {
						if (name.equals(exclude)) {
							isExcluede = true;
							break;
						}
					}
					return isExcluede;
				}
			});
		}
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(dateformat));
		//config.registerJsonValueProcessor(Boolean.class, new BooleanJsonValueProcessor());
		config.registerJsonValueProcessor(String.class, new Code2NameJsonValueProcessor(codeFields));
		return config;
	}

	/**
	 * 注册日期格式化类
	 * 
	 * @author user
	 * 
	 */
	public static class DateJsonValueProcessor implements JsonValueProcessor {

		private DateFormat dateFormat;

		/**
		 * 构造方法.
		 * 
		 * @param datePattern
		 *            日期格式
		 */
		public DateJsonValueProcessor(String datePattern) {
			if (dateFormat != null) {
				try {
					dateFormat = new SimpleDateFormat(datePattern);
				} catch (Exception ex) {
					dateFormat = new SimpleDateFormat(DateOperator.FORMAT_STR);
				}
			}
		}

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			if (null == value)
				return "";
			return process(value);
		}

		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			if (null == value)
				return "";
			return process(value);
		}

		private Object process(Object value) {
			Date date = (Date) value;
			String strContent = null;
			if (dateFormat == null) {
				boolean isTime = DateOperator.isTime(date);
				if (isTime) {
					strContent = DateOperator.formatDate(date, DateOperator.FORMAT_STR_WITH_TIME);
				} else {
					strContent = DateOperator.formatDate(date);
				}
			} else {
				strContent = dateFormat.format(date);
			}
			return strContent;
		}

	}

	public static class BooleanJsonValueProcessor implements JsonValueProcessor {

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			if (null == value)
				return "";
			return process(value);
		}

		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			if (null == value)
				return "";
			return process(value);
		}

		private Object process(Object value) {
			return BooleanUtils.toStringTrueFalse((Boolean) value);
		}

	}

	public static class Code2NameJsonValueProcessor implements JsonValueProcessor {
		private Set<String> fields = null;

		public Code2NameJsonValueProcessor(Set<String> fields) {
			this.fields = fields;
		}

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			if (null == value)
				return "";
			return value;
		}

		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			if (StringUtils.isBlank((String) value))
				return "";
			return process(key, (String)value);
		}

		private Object process(String key, String value) {
			String rtnValue = value;
			if (fields != null) {
				if (fields.contains(key)) {
					if (key.equals("startUser")){
						rtnValue = CodeNameUtil.getUserNameById(value);
					}else if(key.equals("curApprover")){
						rtnValue = CodeNameUtil.getUserNameById(value);
					}
				}
			}
			return rtnValue;
		}

	}
}
