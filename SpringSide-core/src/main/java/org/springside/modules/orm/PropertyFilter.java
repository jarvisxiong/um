/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: PropertyFilter.java 512 2009-10-01 15:15:12Z calvinxiu $
 */
package org.springside.modules.orm;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.lob.ClobImpl;
import org.springframework.util.Assert;
import org.springside.modules.utils.ReflectionUtils;

import sun.nio.cs.ext.MacHebrew;

/**
 * 与具体ORM实现无关的属性过滤条件封装类.
 * 
 * PropertyFilter主要记录页面中简单的搜索过滤条件,比Hibernate的Criterion要简单.
 * 
 * @author calvin
 */
public class PropertyFilter {

	/**
	 * 多个属性间OR关系的分隔符.
	 */
	public static final String OR_SEPARATOR = "_OR_";

	/**
	 * 属性比较类型.IN条件必须传入Object数组
	 */
	public enum MatchType {
		EQ, NEQ, LIKE, NLIKE, LT, GT, LE, GE, IN;
	}

	/**
	 * 属性数据类型.
	 */
	public enum PropertyType {
		A(Object.class), S(String.class), I(Integer.class), L(Long.class), N(Double.class), M(BigDecimal.class), D(Date.class), B(Boolean.class), C(Clob.class), s(
				Short.class);

		private Class<?> clazz;

		PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	private String[] propertyNames = null;

	private Class<?> propertyType = null;

	private Object propertyValue = null;

	private MatchType matchType = MatchType.EQ;
	//多个OR条件
	private List<PropertyFilter> propertyFilters=new ArrayList<PropertyFilter>();

	public PropertyFilter() {
	}

	/**
	 * @param filterName LIKES_LOGIN_NAME
	 * @param value 
	 */
	public PropertyFilter(final String[] filterName, Object[] value) {
		for (int i = 0; i < filterName.length; i++) {
			propertyFilters.add(new PropertyFilter(filterName[i], value[i]));
		}
	}

	/**
	 * @param filterName
	 *            比较属性字符串,含待比较的比较类型、属性值类型及属性列表. eg.
	 *            LIKES_NAME_OR_LIKES_LOGIN_NAME
	 * @param value
	 *            待比较的值.eg. huangj_OR_wuzm
	 */
	public PropertyFilter(final String filterName, Object value) {

		String matchTypeStr = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
		String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
		try {
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}

		try {
			propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		propertyNames = propertyNameStr.split(PropertyFilter.OR_SEPARATOR);

		Assert.isTrue(propertyNames.length > 0, "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		// 按entity property中的类型将字符串转化为实际类型.
		if (propertyType == Date.class && matchType.equals(MatchType.LT)) {
			value = ReflectionUtils.convertValue(value, propertyType);
			Date dateValue = (Date) value;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateValue);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			value = calendar.getTime();
		}
		if (matchType.equals(MatchType.IN)) {
			//
			this.propertyValue = value;
		} else if (propertyType == Clob.class) {
			if (matchType.equals(MatchType.LIKE))
				value = "%" + value + "%";
			this.propertyValue = new ClobImpl((String) value);
		} else {
			this.propertyValue = ReflectionUtils.convertValue(value, propertyType);
		}
	}

	public boolean isOrProperty() {
		return propertyFilters.size() > 0;
	}

	public List<PropertyFilter> getPropertyFilters() {
		return propertyFilters;
	}

	/**
	 * 是否有多个属性.
	 */
	public boolean isMultiProperty() {
		return (propertyNames!=null&&propertyNames.length > 1);
	}

	/**
	 * 获取比较属性名称列表.
	 */
	public String[] getPropertyNames() {
		return propertyNames;
	}

	/**
	 * 获取唯一的属性名称.
	 */
	public String getPropertyName() {
		if (propertyNames.length > 1)
			throw new IllegalArgumentException("There are not only one property");
		return propertyNames[0];
	}

	/**
	 * 获取比较值.
	 */
	public Object getPropertyValue() {
		return propertyValue;
	}

	/**
	 * 获取比较值的类型.
	 */
	public Class<?> getPropertyType() {
		return propertyType;
	}

	/**
	 * 获取比较类型.
	 */
	public MatchType getMatchType() {
		return matchType;
	}
}
