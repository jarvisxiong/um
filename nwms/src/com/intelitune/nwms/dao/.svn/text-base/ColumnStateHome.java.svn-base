package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ColumnState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ColumnState.
 * 
 * @see com.intelitune.nwms.model.ColumnState
 * @author Hibernate Tools
 */
public class ColumnStateHome {
	private final static ColumnStateHome instance = new ColumnStateHome();

	private ColumnStateHome() {
	}

	public static final ColumnStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ColumnStateHome.class);

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

	public void persist(ColumnState transientInstance) {
		log.debug("persisting ColumnState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ColumnState instance) {
		log.debug("attaching dirty ColumnState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ColumnState instance) {
		log.debug("attaching clean ColumnState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ColumnState persistentInstance) {
		log.debug("deleting ColumnState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ColumnState merge(ColumnState detachedInstance) {
		log.debug("merging ColumnState instance");
		try {
			ColumnState result = (ColumnState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ColumnState findById(java.lang.String id) {
		log.debug("getting ColumnState instance with id: " + id);
		try {
			ColumnState instance = (ColumnState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.ColumnState", id);
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

	public List<ColumnState> findByExample(ColumnState instance) {
		log.debug("finding ColumnState instance by example");
		try {
			List<ColumnState> results = (List<ColumnState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.ColumnState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public ColumnState findColumnStateByCode(int code) {
		String str = code + "";
		String hql = "from ColumnState as c where c.code='" + str + "'";
		ColumnState cs = (ColumnState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return cs;
	}
}
