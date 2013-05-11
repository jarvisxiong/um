/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: HibernateDao.java 577 2009-10-20 15:44:24Z calvinxiu $
 */
package org.springside.modules.orm.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;
import org.springside.modules.utils.ReflectionUtils;

/**
 * 封装SpringSide扩展功能的Hibernat DAO泛型基类.
 * 
 * 扩展功能包括分页查询,按属性过滤条件列表查询. 可在Service层直接使用,也可以扩展泛型DAO子类使用,见两个构造函数的注释.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 * @author calvin
 */
public class HibernateDao<T, PK extends Serializable> extends SimpleHibernateDao<T, PK> implements IHibernaterService<T, PK> {
	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * HibernateDao<User, Long>{ }
	 */
	public HibernateDao() {
		super();
	}

	/**
	 * 用于省略Dao层, Service层直接使用通用HibernateDao的构造函数. 在构造函数中定义对象类型Class. eg.
	 * HibernateDao<User, Long> userDao = new HibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 */
	public HibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	// -- 分页查询函数 --//
	/**
	 * 分页获取全部对象.
	 */
	public Page<T> getAll(final Page<T> page) {
		return findPage(page);
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final String hql, final Object... values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final String hql, final Map<String, Object> values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameter(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}
	public Page<T> findPageSql(final Page<T> page, final String sql, final Map<String, Object> values,Map<String, Class> mapClazz) {
		Assert.notNull(page, "page不能为空");
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		if (page.isAutoCount()) {
			long totalCount = countSqlResult(sql, values);
			page.setTotalCount(totalCount);
		}
		
		setPageParameter(query, page);
		if (values != null) {
			query.setProperties(values);
		}
		for(String ails:mapClazz.keySet()){
			query.addEntity(ails,mapClazz.get(ails));
		}
		List result = query.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, Map<String, List<Criterion>> mapCondition, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");
		Criteria c = createCriteria(criterions);
		for (String entityName : mapCondition.keySet()) {
			Criteria cSub = c.createCriteria(entityName);
			List<Criterion> lstCriterion = mapCondition.get(entityName);
			for (Criterion criterionSub : lstCriterion) {
				cSub.add(criterionSub);
			}
		}
		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query q, final Page<T> page) {
		// hibernate的firstResult的序号从0开始
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		// hibernate的firstResult的序号从0开始
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());
		if (page.getOrderStr() != null) {
			c.addOrder(CustomerOrder.getInstance(page.getOrderStr()));
		}
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	public long countHqlResult(final String hql, final Object... values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	public long countHqlResult(final String hql, final Map<String, Object> values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	public long countSqlResult(final String sql, final Map<String, Object> values) {
		String fromSql = sql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromSql = "from " + StringUtils.substringAfter(fromSql, "from");
		fromSql = StringUtils.substringBeforeLast(fromSql, "order by");

		String countSql = "select count(*) " + fromSql;

		try {
			BigDecimal count =(BigDecimal)createSQLQuery(countSql, values).uniqueResult();
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, sql is:" + countSql, e);
		}
	}
	
	public int countCriteriaResult(final Criterion... criterions) {
		Class<T> entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return countCriteriaResult(criteria);
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings("unchecked")
	public int countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		Integer tmpCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
		int totalCount = tmpCount == null ? 0 : tmpCount;
		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	// -- 属性过滤条件(PropertyFilter)查询函数 --//

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public List<T> findBy(final String propertyName, final Object value, final MatchType matchType) {
		Criterion criterion = buildPropertyFilterCriterion(propertyName, value, matchType);
		return find(criterion);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> find(List<PropertyFilter> filters) {
		return find(filters, null);
	}

	public List<T> find(List<PropertyFilter> filters, String orderStr) {
		Object[] obj = buildPropertyFilterCriterions(filters);
		Criterion[] criterions = (Criterion[]) obj[0];
		Map<String, List<Criterion>> mapCondition = (Map<String, List<Criterion>>) obj[1];
		if (mapCondition != null) {
			return find(mapCondition, orderStr, criterions);
		} else
			return findByC(orderStr, criterions);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters) {
		Object[] obj = buildPropertyFilterCriterions(filters);
		Criterion[] criterions = (Criterion[]) obj[0];
		Map<String, List<Criterion>> mapCondition = (Map<String, List<Criterion>>) obj[1];
		if (mapCondition != null) {
			return findPage(page, mapCondition, criterions);
		} else {
			return findPage(page, criterions);
		}
	}

	public int countByPropertyFilter(List<PropertyFilter> filters) {
		int totalCount = 0;
		Object[] obj = buildPropertyFilterCriterions(filters);
		Criterion[] criterions = (Criterion[]) obj[0];
		Map<String, List<Criterion>> mapCondition = (Map<String, List<Criterion>>) obj[1];
		Criteria c = createCriteria(criterions);
		if (mapCondition != null) {
			for (String entityName : mapCondition.keySet()) {
				Criteria cSub = c.createCriteria(entityName);
				List<Criterion> lstCriterion = mapCondition.get(entityName);
				for (Criterion criterionSub : lstCriterion) {
					cSub.add(criterionSub);
				}
			}
		}
		totalCount = countCriteriaResult(c);
		return totalCount;
	}

	protected void parserPropertyFilter(final List<PropertyFilter> filters) {

	}

	/**
	 * sql查询
	 * 
	 * @param sql
	 * @return
	 */
	public List findBySql(String sql, Map<String, Object> map,Map<String, Class> mapClazz) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (map != null) {
			query.setProperties(map);
		}
		if (mapClazz!=null){
			for(String ails:mapClazz.keySet()){
				query.addEntity(ails,mapClazz.get(ails));
			}
		}
		return query.list();
	}
	public List findBySql(String sql, Map<String, Object> map) {
		return findBySql(sql, map, null);
	}
	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected Object[] buildPropertyFilterCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Map<String, List<Criterion>> mapCondition = new HashMap<String, List<Criterion>>();
		if (filters != null) {
			for (PropertyFilter filter : filters) {
				wrapProperty(filter, mapCondition, criterionList);
			}
		}
		Object[] obj = new Object[2];
		obj[0] = criterionList.toArray(new Criterion[criterionList.size()]);
		if (mapCondition.size() > 0) {
			obj[1] = mapCondition;
		}
		return obj;
	}
	private void buildFilter(PropertyFilter filter, Map<String, List<Criterion>> mapCondition, List<Criterion> criterionList){
		String toke = null;
		if (filter.getPropertyName().contains("_")) {
			toke = "_";
		} else if (filter.getPropertyName().contains(".")) {
			toke = ".";
		}
		if (toke != null) {
			String[] stokes = StringUtils.split(filter.getPropertyName(), toke);
			List<Criterion> lstCriterion = mapCondition.get(stokes[0]);
			if (lstCriterion == null) {
				lstCriterion = new ArrayList<Criterion>();
				mapCondition.put(stokes[0], lstCriterion);
			}
			lstCriterion.add(buildPropertyFilterCriterion(stokes[1], filter.getPropertyValue(), filter.getMatchType()));
		} else {
			Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(), filter.getMatchType());
			criterionList.add(criterion);
		}
	}
	private void wrapProperty(PropertyFilter filter, Map<String, List<Criterion>> mapCondition, List<Criterion> criterionList) {
		if (!filter.isMultiProperty() && !filter.isOrProperty()) { // 只有一个属性需要比较的情况.
			buildFilter(filter,mapCondition,criterionList);
		} else {// 包含多个属性需要比较的情况,进行or处理.
			if (filter.isMultiProperty()) {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}

			if (filter.isOrProperty()) {
				Disjunction disjunction = Restrictions.disjunction();
				for (PropertyFilter filterTmp : filter.getPropertyFilters()) {
					Criterion criterion = buildPropertyFilterCriterion(filterTmp.getPropertyName(), filterTmp.getPropertyValue(), filterTmp
							.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}

	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildPropertyFilterCriterion(final String propertyName, final Object propertyValue, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		try {

			// 根据MatchType构造criterion
			if (MatchType.EQ.equals(matchType)) {
				criterion = Restrictions.eq(propertyName, propertyValue);
			} else if (MatchType.LIKE.equals(matchType)) {
				if (propertyValue instanceof Clob) {
					criterion = Restrictions.like(propertyName, propertyValue);
				} else {
					criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
				}

			} else if (MatchType.NLIKE.equals(matchType)) {
				criterion = Restrictions.not(Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE));
			} else if (MatchType.LE.equals(matchType)) {
				criterion = Restrictions.le(propertyName, propertyValue);
			} else if (MatchType.LT.equals(matchType)) {
				criterion = Restrictions.lt(propertyName, propertyValue);
			} else if (MatchType.GE.equals(matchType)) {
				criterion = Restrictions.ge(propertyName, propertyValue);
			} else if (MatchType.GT.equals(matchType)) {
				criterion = Restrictions.gt(propertyName, propertyValue);
			} else if (MatchType.NEQ.equals(matchType)) {
				criterion = Restrictions.ne(propertyName, propertyValue);
			} else if (MatchType.IN.equals(matchType)) {
				Object[] objAry = (Object[]) propertyValue;
				criterion = Restrictions.in(propertyName, objAry);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		return criterion;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue))
			return true;
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}

	/**
	 * 刷新更新时间
	 */
	public int refreshUpdatedDate(String id) {
		StringBuffer hql = new StringBuffer("update ");
		hql.append(entityClass.getSimpleName()).append(" set updatedDate=:p_now where ").append(getIdName()).append("=:p_id");
		Map<String, Object> pram = new HashMap<String, Object>();
		Date now = new Date(new java.util.Date().getTime());
		pram.put("p_now", now);
		pram.put("p_id", id);
		int result = batchExecute(hql.toString(), pram);
		return result;
	}
}
