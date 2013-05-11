package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.RateState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RateState.
 * 
 * @see com.intelitune.nwms.model.RateState
 * @author Hibernate Tools
 */
public class RateStateHome {
	private final static RateStateHome instance = new RateStateHome();

	private RateStateHome() {
	}

	public static final RateStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(RateStateHome.class);

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

	public void persist(RateState transientInstance) {
		log.debug("persisting RateState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RateState instance) {
		log.debug("attaching dirty RateState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RateState instance) {
		log.debug("attaching clean RateState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RateState persistentInstance) {
		log.debug("deleting RateState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RateState merge(RateState detachedInstance) {
		log.debug("merging RateState instance");
		try {
			RateState result = (RateState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RateState findById(java.lang.String id) {
		log.debug("getting RateState instance with id: " + id);
		try {
			RateState instance = (RateState) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.RateState", id);
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

	public List<RateState> findByExample(RateState instance) {
		log.debug("finding RateState instance by example");
		try {
			List<RateState> results = (List<RateState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.RateState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
