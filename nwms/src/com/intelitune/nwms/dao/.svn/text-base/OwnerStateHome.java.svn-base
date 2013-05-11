package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OwnerState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OwnerState.
 * 
 * @see com.intelitune.nwms.model.OwnerState
 * @author Hibernate Tools
 */
public class OwnerStateHome {
	private final static OwnerStateHome instance = new OwnerStateHome();

	private OwnerStateHome() {
	}

	public static final OwnerStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(OwnerStateHome.class);

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

	public void persist(OwnerState transientInstance) {
		log.debug("persisting OwnerState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OwnerState instance) {
		log.debug("attaching dirty OwnerState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OwnerState instance) {
		log.debug("attaching clean OwnerState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OwnerState persistentInstance) {
		log.debug("deleting OwnerState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OwnerState merge(OwnerState detachedInstance) {
		log.debug("merging OwnerState instance");
		try {
			OwnerState result = (OwnerState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OwnerState findById(java.lang.String id) {
		log.debug("getting OwnerState instance with id: " + id);
		try {
			OwnerState instance = (OwnerState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OwnerState", id);
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

	public List<OwnerState> findByExample(OwnerState instance) {
		log.debug("finding OwnerState instance by example");
		try {
			List<OwnerState> results = (List<OwnerState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OwnerState").add(
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
