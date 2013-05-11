package com.hhz.ump.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

/**
 * springside已经对json进行封装,本工具在其基础上做一些拓展<br>
 * 主要是为了生成gt_gird所需要的json格式的数据
 * 
 * @author shixy 2010-7-9
 */
public class JSONUtil {

	public static String GT_JSON_NAME = "_gt_json";
	public static String INSERT_RECORDS_KEY = "insertedRecords";
	public static String UPDATE_RECORDS_KEY = "updatedRecords";
	public static String DELETE_RECORDS_KEY = "deletedRecords";
	public static String[] DATE_FORMATS = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

	/**
	 * 根据Page自动生成gt_grid所需的json数据，包含分页信息
	 * 
	 * @param page
	 *            翻页信息
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	public static void renderJson(final Page<?> page, String dateformat, String... excludes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", getPageInfoJson(page));
		map.put("data", page.getResult());
		renderDataJson(map, dateformat, excludes);
	}

	/**
	 * 根据Page自动生成gt_grid所需的分页json数据
	 * 
	 * @param page
	 *            翻页信息
	 */
	public static JSONObject getPageInfoJson(final Page<?> page) {
		JSONObject pageInfoJS = new JSONObject();
		pageInfoJS.put("endRowNum", page.getFirst() + page.getPageSize() - 1);
		pageInfoJS.put("pageNum", page.getPageNo());
		pageInfoJS.put("pageSize", page.getPageSize());
		pageInfoJS.put("startRowNum", page.getFirst());
		pageInfoJS.put("totalPageNum", page.getTotalPages());
		pageInfoJS.put("totalRowNum", page.getTotalCount());
		return pageInfoJS;
	}

	/**
	 * 根据List自动生成gt_grid所需的json数据
	 * 
	 * @param page
	 *            翻页数据
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	public static void renderListJson(final List<?> list, String dateformat, String... excludes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		renderDataJson(map, dateformat, excludes);
	}

	/**
	 * 根据Map自动生成gt_grid所需的json数据<br>
	 * 需手动构造Map<br>
	 * Map.put("pageInfo",...)<br>
	 * Map.put("data",...)
	 * 
	 * @param page
	 *            翻页信息
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	public static void renderDataJson(final Map map, String dateformat, String... excludes) {
		JsonConfig config = getJsonConfig(excludes, dateformat);
		String jsonString = JSONObject.fromObject(map, config).toString();
		Struts2Utils.renderText(jsonString);
	}

	public static List<?> getJsonObject(Class<?> clazz, String key) {
		String json = Struts2Utils.getParameter(GT_JSON_NAME);
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray(key);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(DATE_FORMATS));
		return JSONArray.toList(jsonArray, clazz);
	}

	/**
	 * 获取前台自定义属性的值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getExtrKey(String key) {
		String json = Struts2Utils.getParameter(GT_JSON_NAME);
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key);
	}

	public static  List<?>  getInsertRecords(Class<?> clazz) {
		return getJsonObject(clazz, INSERT_RECORDS_KEY);
	}

	public static List<?> getUpdateRecords(Class<?> clazz) {
		return getJsonObject(clazz, UPDATE_RECORDS_KEY);
	}

	public static List<?> getDeleteRecords(Class<?> clazz) {
		return getJsonObject(clazz, DELETE_RECORDS_KEY);
	}

	public static JsonConfig getJsonConfig(final String[] excludes, String dateformat) {
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
		return config;
	}

	/**
	 * 生成保存成功后gt_grid所需要的json字符串
	 */
	public static void renderSuccessText() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		Struts2Utils.renderText(jsonObject.toString());
	}

	public static void setSortInfo(final Page<?> page) {
		String json = Struts2Utils.getParameter(GT_JSON_NAME);
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("sortInfo");
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject si = jsonArray.getJSONObject(i);
				String columnId = si.getString("columnId");
				String fieldName = si.getString("fieldName");
				String sortOrder = si.getString("sortOrder");

			}
		}
	}

	/**
	 * 注册日期格式化类
	 * 
	 * @author user
	 * 
	 */
	public static class DateJsonValueProcessor implements JsonValueProcessor {

		public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
		private DateFormat dateFormat;

		/**
		 * 构造方法.
		 * 
		 * @param datePattern
		 *            日期格式
		 */
		public DateJsonValueProcessor(String datePattern) {
			try {
				dateFormat = new SimpleDateFormat(datePattern);
			} catch (Exception ex) {
				dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
			}
		}

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		private Object process(Object value) {
			if(value == null)
				return null;
			else
				return dateFormat.format((Date) value);
		}

	}
}
