package com.hhz.ump.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppDictTypeManager;

@Service
public class PdCache {
	private static Logger logger = Logger.getLogger(PdCache.class);
	// 实体名称-实体集合
	private static Map<String, List> mapEntityList = new HashMap<String, List>();

	private static HashMap<String, HashMap<String, HashMap<String, Object>>> mapOODB = new HashMap<String, HashMap<String, HashMap<String, Object>>>();

	private static HashMap<String, HashMap<String, HashMap<String, Integer>>> mapNull = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();

	// 是否需要再搜索次数
	private static int searchTimes = 1;

	private static AppDictTypeManager appDictTypeManager = SpringContextHolder
			.getBean("appDictTypeManager");
	private static int serverPort;

	/**
	 * 是否缓存的实体
	 * 
	 * @param entityClassName
	 * @return
	 */
	public static boolean isCacheClass(String entityClassName) {
		boolean flag = false;
		if (mapEntityList.keySet().contains(entityClassName)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 通过code string找到name string, 通过name string找到code string
	 * 
	 * @param entityClassName
	 * @param keyField
	 * @param keyValue
	 * @param refField
	 * @return 如果返回值为Null，则把搜索值返回
	 */
	public static String getRefString(Class entityClass, String keyField,
			String keyValue, String refField) {
		String value = getValueString(getRefObject(entityClass, keyField,
				keyValue, refField));
		// TODO 把原值返回，会有用工性质非空校验问题
		// return value == null ? keyValue : value;
		// TODO 把null返回，存在性校验会无效果
		return value;
	}

	/**
	 * 通过code string找到Object, 通过name string找到Object
	 * 
	 * @param entityClassName
	 * @param keyField
	 * @param keyValue
	 * @param refField
	 * @return
	 */
	public static <T> T getRefObject(Class<T> entityClass, String keyField,
			String keyValue, String refField) {
		Object obj = getValueObject(getRefEntity(entityClass, keyField,
				keyValue), refField);
		return (T) obj;
	}

	/**
	 * 通过code string找到entity, 通过name string找到entity
	 * 
	 * @param entityClassName
	 * @param keyField
	 * @param keyValue
	 * @return
	 */
	public static <T> T getRefEntity(Class<T> entityClass, String keyField,
			String keyValue) {
		if (StringUtils.isEmpty(keyValue))
			return null;
		HashMap<String, Object> mapKeyValue = getKeyValueMap(entityClass,
				keyField);
		if (mapKeyValue == null)
			return null;
		Object entity = mapKeyValue.get(keyValue);
		if (entity == null) {
			entity = doNullException(entityClass.getName(), keyField, keyValue);
			if (entity != null) {
				mapKeyValue.put(keyValue, entity);
			}
		}

		return (T) entity;
	}

	// getter
	/**
	 * 取得对应entity的指定字段的所有值（通常用于填充Grid中下拉框）
	 * 
	 * @param entityClassName
	 * @param keyField
	 * @return
	 */
	public static Collection<String> getFieldList(Class entityClass,
			String keyField) {
		HashMap<String, Object> mapKeyValue = getKeyValueMap(entityClass,
				keyField);
		return mapKeyValue.keySet();
	}

	public static List getEntityList(Class entityClass, Map<String, Object> map) {
		return getEntityList(entityClass, false, map);
	}

	public static List getEntityList(Class entityClass,
			Map<String, Object> map, List<String> lstProp) {
		return getEntityList(entityClass, false, map, lstProp);
	}

	public static List getEntityList(Class entityClass) {
		return getEntityList(entityClass, false, null);
	}

	public static List getEntityList(Class entityClass, boolean forceLoad,
			Map<String, Object> map) {
		return getEntityList(entityClass, forceLoad, map, null);
	}

	public static List getList(Class entityClass, String keyField) {
		HashMap<String, Object> mapKeyValue = getKeyValueMap(entityClass,
				keyField);
		return PowerUtils.setCollection2List(mapKeyValue.values());

	}

	public static List getList(Class entityClass, String keyField,
			String condField, Object condValue) {
		HashMap<String, Object> mapKeyValue = getKeyValueMap(entityClass,
				keyField);
		return PowerUtils.setCollection2List(mapKeyValue.values(), condField,
				condValue);

	}

	private static List getEntityList(Class entityClass, boolean forceLoad,
			Map<String, Object> map, List<String> lstProp) {
		List lstEntity = null;
		if (!forceLoad) {
			lstEntity = mapEntityList.get(entityClass.getName());
			lstEntity = PowerUtils.setCollection2List(lstEntity, map);
		}
		if (lstEntity == null) {
			Map<String, Object> mapCondition = new HashMap<String, Object>();

			// if
			// (SsmGuiUtil.isHaveField(SsmGuiUtil.getDomainClass(entityClass.getName()),
			// ACTIVE)) {
			// mapCondition.put(ACTIVE, true);
			// }
			// StringBuffer hql=new
			// StringBuffer("from ").append(entityClass.getSimpleName());
			lstEntity = appDictTypeManager.findByMap(entityClass, mapCondition); // .findByProperty(entityClass,
			// mapCondition);
			List<String> lstSort = new ArrayList<String>();
			// if
			// (SsmGuiUtil.isHaveField(SsmGuiUtil.getDomainClass(entityClass.getName()),
			// SEQUENCENO)) {
			// lstSort.add(SEQUENCENO);
			// }
			String codeField = getCodeField(entityClass);
			if (codeField != null) {
				lstSort.add(codeField);
			}
			CollectionHelper.sortList(lstEntity, lstSort);

			mapEntityList.put(entityClass.getName(), lstEntity);
			lstEntity = mapEntityList.get(entityClass.getName());
			lstEntity = PowerUtils.setCollection2List(lstEntity, map);
		}
		return lstEntity;
	}

	private static Object doNullException(String entityClassName,
			String keyField, String keyValue) {
		try {
			int time = getSearchTime(entityClassName, keyField, keyValue);
			if (time < searchTimes) {
				Object object = null;
				// Object object =
				// SsmCodeNameUtil.findEntityByProperty(Class.forName(entityClassName),
				// keyField, keyValue);
				addSearchTime(entityClassName, keyField, keyValue);
				return object;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	private static void addSearchTime(String entityClassName, String keyField,
			String keyValue) {
		HashMap<String, HashMap<String, Integer>> mapFieldValue = mapNull
				.get(entityClassName);
		if (mapFieldValue == null || mapFieldValue.isEmpty()) {
			mapFieldValue = new HashMap<String, HashMap<String, Integer>>();
			HashMap<String, Integer> mapKeyTime = new HashMap<String, Integer>();
			mapKeyTime.put(keyValue, 0);
			mapFieldValue.put(keyField, mapKeyTime);
			mapNull.put(entityClassName, mapFieldValue);
		} else {
			HashMap<String, Integer> mapKeyTime = mapFieldValue.get(keyField);
			if (mapKeyTime == null || mapKeyTime.isEmpty()) {
				mapKeyTime = new HashMap<String, Integer>();
				mapKeyTime.put(keyValue, 0);
				mapFieldValue.put(keyField, mapKeyTime);
			} else {
				mapKeyTime.put(keyValue, mapKeyTime.get(keyValue) == null ? 0
						: mapKeyTime.get(keyValue) + 1);

			}
		}
	}

	private static int getSearchTime(String entityClassName, String keyField,
			String keyValue) {
		HashMap<String, HashMap<String, Integer>> mapFieldValue = mapNull
				.get(entityClassName);
		if (mapFieldValue != null) {
			HashMap<String, Integer> mapKeyTime = mapFieldValue.get(keyField);
			if (mapKeyTime != null) {
				Integer value = mapKeyTime.get(keyValue);
				if (value == null)
					return 0;
				else
					return mapKeyTime.get(keyValue);
			}
		}
		return 0;
	}

	/**
	 * 找出对应entityClass,keyField存放的数据 例如：币种、币种代码的HashMap
	 * 
	 * @param entityClassName
	 * @param keyField
	 * @return
	 */
	private static HashMap<String, Object> getKeyValueMap(Class entityClass,
			String keyField) {
		HashMap<String, Object> mapKeyValue;
		HashMap<String, HashMap<String, Object>> mapField = mapOODB
				.get(entityClass.getName());
		if (mapField == null) {
			mapField = new HashMap<String, HashMap<String, Object>>();
		}
		mapKeyValue = mapField.get(keyField);
		if (mapKeyValue == null || mapKeyValue.isEmpty()) {
			mapKeyValue = setKeyValueMap(entityClass, keyField);
		}

		return mapKeyValue;
	}

	private static Object getValueObject(Object obj, String refField) {
		try {
			return BeanUtils.getProperty(obj, refField);
		} catch (Exception e) {
			return null;
		}
	}

	private static String getValueString(Object obj) {
		return ConvertUtils.convert(obj);
	}

	// setter

	/**
	 * 查找不到entityClassName，load一次
	 * 
	 * @param entityClassName
	 * @param keyField
	 * @return
	 */
	private static HashMap<String, Object> setKeyValueMap(Class entityClass,
			String keyField) {
		try {
			List lstEntity = getEntityList(entityClass, true, null);
			HashMap<String, HashMap<String, Object>> mapField = mapOODB
					.get(entityClass.getName());
			if (mapField == null) {
				mapField = new HashMap<String, HashMap<String, Object>>();
			}
			fillKeyValueMap(mapField, entityClass.getName(), lstEntity,
					keyField);
			mapOODB.put(entityClass.getName(), mapField);
			return mapField.get(keyField);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String getCodeField(Class entityClass) {
		String fieldName = null;
		Field[] fields = entityClass.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().endsWith("Cd")) {
				fieldName = f.getName();
				break;
			}
		}
		return fieldName;
	}

	public static void fillKeyValueMap(
			HashMap<String, HashMap<String, Object>> mapField,
			String entityClassName, List lst, String keyField) {
		HashMap<String, Object> mapKeyValue = fillKeyValueMap(lst, keyField);
		mapField.put(keyField, mapKeyValue);

		if (isExistProperty(entityClassName, replaceCodeName(keyField))) {
			HashMap<String, Object> mapKeyOppositeValue = fillKeyValueMap(lst,
					replaceCodeName(keyField));
			mapField.put(replaceCodeName(keyField), mapKeyOppositeValue);
		}
	}

	private static HashMap<String, Object> fillKeyValueMap(List lst,
			String keyField) {
		try {
			HashMap<String, Object> mapKeyValue = new HashMap<String, Object>();
			for (int i = 0; i < lst.size(); i++) {
				mapKeyValue.put(BeanUtils.getProperty(lst.get(i), keyField),
						lst.get(i));
			}
			return mapKeyValue;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 是否存在指定的字段
	 * 
	 * @param entityName
	 * @param propertyName
	 * @return
	 */
	private static boolean isExistProperty(String entityName,
			String propertyName) {
		if (propertyName == null)
			return false;
		try {
			return PropertyUtils.isWriteable(Class.forName(entityName)
					.newInstance(), propertyName);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 字段中Code和Name互换
	 * 
	 * @param fieldName
	 * @return
	 */
	private static String replaceCodeName(String fieldName) {
		if (fieldName.indexOf("Cd") > 0)
			return fieldName.replaceAll("Cd", "Name");
		if (fieldName.indexOf("Name") > 0)
			return fieldName.replaceAll("Name", "Cd");
		// 不存在则返回null，方便之后判断。
		return null;
	}

	/**
	 * 清除缓存
	 */
	public void cleanCache() {
		mapEntityList.clear();
		mapOODB.clear();
		mapNull.clear();
		logger.info("清除所有字典缓存");
	}

	/**
	 * 清除指定缓存
	 * 
	 * @param entityName
	 */
	public static void cleanCache(String entityName) {
		mapEntityList.remove(entityName);
		mapOODB.remove(entityName);
		mapNull.remove(entityName);
		logger.info("清除字典缓存:"+entityName);
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static void setServerPort(int serverPort) {
		if (PdCache.serverPort == 0 || PdCache.serverPort == 80) {
			PdCache.serverPort = serverPort;
		}
	}
}
