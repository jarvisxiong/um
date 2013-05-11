package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.model.RowState;

/**
 * Home object for domain model class Row.
 * 
 * @see com.intelitune.nwms.model.Row
 * @author Hibernate Tools
 */
public class RowHome {
	private final static RowHome instance = new RowHome();

	private RowHome() {
	}

	public static final RowHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(RowHome.class);

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

	public void persist(Row transientInstance) {
		log.debug("persisting Row instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Row instance) {
		log.debug("attaching dirty Row instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Row instance) {
		log.debug("attaching clean Row instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Row persistentInstance) {
		log.debug("deleting Row instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Row merge(Row detachedInstance) {
		log.debug("merging Row instance");
		try {
			Row result = (Row) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Row findById(java.lang.String id) {
		log.debug("getting Row instance with id: " + id);
		try {
			Row instance = (Row) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Row", id);
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

	public List<Row> findByExample(Row instance) {
		log.debug("finding Row instance by example");
		try {
			List<Row> results = (List<Row>) sessionFactory.getCurrentSession()
					.createCriteria("com.intelitune.nwms.model.Row").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Row> findRowByFloor_id(String floor_id) {
		String hql = "from Row as r where r.floor.id='" + floor_id
				+ "' and r.state.code='" + RowState.NORMAL + "'";
		List<Row> list = sessionFactory.getCurrentSession().createQuery(hql)
				.list();
		return list;
	}

}
