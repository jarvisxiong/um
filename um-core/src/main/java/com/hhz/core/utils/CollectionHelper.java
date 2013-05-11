package com.hhz.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;

public class CollectionHelper {
	public static void trim(List list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			if (object == null) {
				iterator.remove();
			}
		}
	}
	/**
	 * 对集合进行排序(根据集合里面的对象的单个属性)
	 * 
	 * @param list
	 *            需要排序的集合
	 * @param property
	 *            需要对集合里面排序的属性名
	 */
	public static void sortList(List list, String property) {
		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		Collections.sort(list, new BeanComparator(property, cmp));
	}

	/**
	 * 对集合进行排序(根据集合里面的对象的单个属性，升降序)
	 * 
	 * @param list
	 *            需要排序的集合
	 * @param property
	 *            需要对集合里面排序的属性名
	 * @param polarity
	 *            降序true，升序false
	 */
	public static void sortList(List list, String property, boolean polarity) {
		sortList(list, property, polarity, false);
	}

	public static void sortList(List list, String property, boolean polarity, boolean nullHigh) {
		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		if (nullHigh) {
			cmp = ComparatorUtils.nullHighComparator(cmp);
		} else {
			cmp = ComparatorUtils.nullLowComparator(cmp);
		}
		Collections.sort(list, new BeanComparator(property, cmp));
	}

	public static void sortList(List list, Map<String, Boolean> map) {

		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		ArrayList sortFields = new ArrayList();
		for (String property : map.keySet()) {
			if (map.get(property)) {
				sortFields.add(new ReverseComparator(new BeanComparator(property, cmp)));
			} else {
				sortFields.add(new BeanComparator(property, cmp));
			}
		}
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(list, multiSort);
	}

	/**
	 * 对集合进行排序(根据集合里面的值，升降序)
	 * 
	 * @param list
	 *            需要排序的集合
	 * @param polarity
	 *            降序true，升序false
	 */
	public static void sortList(List list, boolean polarity) {
		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		if (polarity) {
			// 取反
			Collections.sort(list, new ReverseComparator(cmp));
		} else {
			Collections.sort(list, cmp);
		}
	}

	/**
	 * 对集合进行排序（根据集合里面的对象的多个属性）
	 * 
	 * @param list
	 *            需要排序的集合
	 * @param properties
	 *            需要对集合里面排序的对象的属性的集合， 定义成ArrayList，ArrayList里面的顺序就是排序的先后顺序
	 */
	public static void sortList(List list, List<String> properties) {

		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		ArrayList<BeanComparator> sortFields = new ArrayList<BeanComparator>();

		for (String property : properties) {
			sortFields.add(new BeanComparator(property, cmp));
		}

		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(list, multiSort);
	}

	/**
	 * 将set转化成list并排序
	 * 
	 * @param set
	 * @return List
	 */
	public static List setCollection2SortList(Collection coll, String property, boolean polarity) {
		List<Object> lstTemp = new ArrayList<Object>();
		if (coll != null) {
			for (Object object : coll) {
				lstTemp.add(object);
			}
		}
		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);
		if (polarity) {
			Collections.sort(lstTemp, new ReverseComparator(new BeanComparator(property, cmp)));
		} else {
			Collections.sort(lstTemp, new BeanComparator(property, cmp));
		}
		return lstTemp;
	}

	/**
	 * 删除重复记录
	 * 
	 * @param coll
	 */
	public static void removeRepeat(Collection coll) {
		boolean isRepeat = false;
		Set set = new LinkedHashSet();
		for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			set.add(object);
		}
		coll.clear();
		coll.addAll(set);
	}
}
