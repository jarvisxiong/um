package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.RowState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RowState.
 * 
 * @see com.intelitune.nwms.model.RowState
 * @author Hibernate Tools
 */
public class RowStateHome {
	private final static RowStateHome instance = new RowStateHome();

	private RowStateHome() {
	}

	public static final RowStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(RowStateHome.class);

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

	public void persist(RowState transientInstance) {
		log.debug("persisting RowState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RowState instance) {
		log.debug("attaching dirty RowState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RowState instance) {
		log.debug("attaching clean RowState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RowState persistentInstance) {
		log.debug("deleting RowState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RowState merge(RowState detachedInstance) {
		log.debug("merging RowState instance");
		try {
			RowState result = (RowState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RowState findById(java.lang.String id) {
		log.debug("getting RowState instance with id: " + id);
		try {
			RowState instance = (RowState) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.RowState", id);
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

	public List<RowState> findByExample(RowState instance) {
		log.debug("finding RowState instance by example");
		try {
			List<RowState> results = (List<RowState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.RowState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public RowState findRowStateByCode(int code) {
		String str = code + "";
		String hql = "from RowState as r where r.code='" + str + "'";
		RowState rs = (RowState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return rs;
	}
}
