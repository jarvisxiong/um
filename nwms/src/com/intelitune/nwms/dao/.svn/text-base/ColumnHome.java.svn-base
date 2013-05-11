package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Column;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Column.
 * 
 * @see com.intelitune.nwms.model.Column
 * @author Hibernate Tools
 */
public class ColumnHome {
	private final static ColumnHome instance = new ColumnHome();

	private ColumnHome() {
	}

	public static final ColumnHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ColumnHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("nWMSSessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Column transientInstance) {
		log.debug("persisting Column instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Column instance) {
		log.debug("attaching dirty Column instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Column instance) {
		log.debug("attaching clean Column instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Column persistentInstance) {
		log.debug("deleting Column instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Column merge(Column detachedInstance) {
		log.debug("merging Column instance");
		try {
			Column result = (Column) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Column findById(java.lang.String id) {
		log.debug("getting Column instance with id: " + id);
		try {
			Column instance = (Column) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Column", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Column> findByExample(Column instance) {
		log.debug("finding Column instance by example");
		try {
			List<Column> results = (List<Column>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Column").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public Column findColumnByCode(String row_id, String column) {
		String hql = "from Column as c where c.row.id='" + row_id
				+ "' and code='" + column + "'";
		Column c = (Column) sessionFactory.getCurrentSession().createQuery(hql)
				.list().get(0);
		return c;
	}

	public int countColumnByRowId(String row_id) {
		int result = 0;
		String hql = "select count(*) from Column as c where c.row.id='"
				+ row_id + "'";
		result = Integer.parseInt(sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0).toString());
		return result;
	}
}
