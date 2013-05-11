/**
 * 
 */
package org.springside.modules.orm.hibernate;

import java.io.Serializable;

/**
 * @author huangj
 * 2010-1-3
 */
public interface ISimpleHibernateService<T, PK extends Serializable> {
	public void save(final T entity);
}
