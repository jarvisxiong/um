/**
 * 
 */
package com.hhz.core.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

/**
 * @author huangj 2009-12-4
 */

public abstract class BaseService<T, PK> extends SpBaseService<T, String> {

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> find(List<PropertyFilter> filters) {
		return getDao().find(filters);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters) {
		return getDao().findPage(page, filters);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List findBySql(String sql, Map<String, Object> map) {
		return getDao().findBySql(sql, map);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List findBySql(String sql, Map<String, Object> map, Map<String, Class> mapClazz) {
		return getDao().findBySql(sql, map, mapClazz);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> getAll() {
		return getDao().getAll();
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> getAll(String orderBy, boolean isAsc) {
		return getDao().getAll(orderBy, isAsc);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		return getDao().findPage(page, criterions);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> findBy(final Criterion... criterions) {
		return getDao().find(criterions);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public Page<T> findPage(final Page<T> page, final String hql, final Map<String, Object> values) {
		return getDao().findPage(page, hql, values);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public Page<T> findPageSql(final Page<T> page, final String sql, final Map<String, Object> values, Map<String, Class> mapClazz) {
		return getDao().findPageSql(page, sql, values, mapClazz);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> find(final String hql, final Object... values) {
		return getDao().find(hql, values);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> findByMap(final Class entityClass, final Map<String, Object> values) {
		return getDao().findByMap(entityClass, values);
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public List<T> find(final String hql, final Map<String, Object> values) {
		return getDao().find(hql, values);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isPropertyUnique(String prop, Object newValue, Object oldValue) {
		return getDao().isPropertyUnique(prop, newValue, oldValue);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public T findUniqueBy(String prop, Object value) {
		return getDao().findUniqueBy(prop, value);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public T getEntity(String id) {
		return getDao().get(id);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public long countHqlResult(final String hql, final Object... values) {
		return getDao().countHqlResult(hql, values);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public long countSqlResult(final String sql, final Map<String, Object> values) {
		return getDao().countSqlResult(sql, values);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public long countHqlResult(final String hql, final Map<String, Object> values) {
		return getDao().countHqlResult(hql, values);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int countCriteriaResult(final Criterion... criterions) {
		return getDao().countCriteriaResult(criterions);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int countCriteriaResult(final Criteria c) {
		return getDao().countCriteriaResult(c);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int countByPropertyFilter(List<PropertyFilter> filters) {
		return getDao().countByPropertyFilter(filters);
	}

	public String getIdValue(T entity) {
		return getDao().getIdValue(entity);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	@Transactional
	public void deleteBatch(String... ids) {
		for (String strId : ids) {
			if (StringUtils.isNotBlank(strId)) {
				getDao().delete(strId);
			}
		}
	}

	@Transactional
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Transactional
	public void delete(List<T> entities) {
		for (T t : entities) {
			getDao().delete(t);
		}
	}

	/**
	 * 刷新更新时间
	 * 
	 * @param id
	 */
	@Transactional
	public int refreshUpdatedDate(String id) {
		return getDao().refreshUpdatedDate(id);
	}
}
