package com.intelitune.nwms.dao;

// Generated 2009-2-26 14:21:14 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.CheckingItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CheckingItemState.
 * 
 * @see com.intelitune.nwms.model.CheckingItemState
 * @author Hibernate Tools
 */
public class CheckingItemStateHome {
	private final static CheckingItemStateHome instance = new CheckingItemStateHome();

	private CheckingItemStateHome() {
	}

	public static final CheckingItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(CheckingItemStateHome.class);

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

	public void persist(CheckingItemState transientInstance) {
		log.debug("persisting CheckingItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CheckingItemState instance) {
		log.debug("attaching dirty CheckingItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CheckingItemState instance) {
		log.debug("attaching clean CheckingItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CheckingItemState persistentInstance) {
		log.debug("deleting CheckingItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CheckingItemState merge(CheckingItemState detachedInstance) {
		log.debug("merging CheckingItemState instance");
		try {
			CheckingItemState result = (CheckingItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CheckingItemState findById(java.lang.String id) {
		log.debug("getting CheckingItemState instance with id: " + id);
		try {
			CheckingItemState instance = (CheckingItemState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.CheckingItemState", id);
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

	public List<CheckingItemState> findByExample(CheckingItemState instance) {
		log.debug("finding CheckingItemState instance by example");
		try {
			List<CheckingItemState> results = (List<CheckingItemState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.CheckingItemState").add(
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
