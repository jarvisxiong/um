/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

/**
 * <p>
 * 网批动态属性
 * </p>
 * 
 * @author huangjian
 * @create 2012-6-11
 */
public class DynamicBean {
	/**
	 * 实体Object
	 */
	private BaseTemplate object = null;

	/**
	 * 属性map
	 */
	private BeanMap beanMap = null;

	/**
	 * 基类
	 */
	private Class<? extends BaseTemplate> superclass = null;

	/**
	 * 构造函数
	 */
	public DynamicBean(Class<? extends BaseTemplate> superclass) {
		this.superclass = superclass;
	}

	/**
	 * 构造函数
	 */
	public DynamicBean(Class<? extends BaseTemplate> superclass, Map<String, Class<?>> propertyMap) {
		this.superclass = superclass;
		this.object = generateBean(propertyMap);
		this.beanMap = BeanMap.create(this.object);
	}

	/**
	 * 给bean属性赋值
	 */
	public void setValue(String property, Object value) {
		beanMap.put(property, value);
	}

	/**
	 * 通过属性名得到属性值
	 * 
	 * @param property
	 *            属性名
	 */
	public Object getValue(String property) {
		return beanMap.get(property);
	}

	/**
	 * 得到该实体bean对象
	 * 
	 * @return
	 */
	public BaseTemplate getObject() {
		return this.object;
	}

	/**
	 * @param propertyMap
	 * @return
	 */
	private BaseTemplate generateBean(Map<String, Class<?>> propertyMap) {
		BeanGenerator generator = new BeanGenerator();
		generator.setSuperclass(superclass);
		if (propertyMap != null) {
			Set<String> keySet = propertyMap.keySet();
			for (Iterator<String> i = keySet.iterator(); i.hasNext();) {
				String key = i.next();
				generator.addProperty(key, propertyMap.get(key));
			}
		}
		return (BaseTemplate) generator.create();
	}

	public Class<? extends BaseTemplate> getSuperclass() {
		return superclass;
	}

	public void setSuperclass(Class<? extends BaseTemplate> superclass) {
		this.superclass = superclass;
	}
}
