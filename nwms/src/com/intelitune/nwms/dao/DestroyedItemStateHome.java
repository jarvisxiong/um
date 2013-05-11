package com.intelitune.nwms.dao;

// Generated 2009-2-26 14:21:14 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.DestroyedItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DestroyedItemState.
 * 
 * @see com.intelitune.nwms.model.DestroyedItemState
 * @author Hibernate Tools
 */
public class DestroyedItemStateHome {
	private final static DestroyedItemStateHome instance = new DestroyedItemStateHome();

	private DestroyedItemStateHome() {
	}

	public static final DestroyedItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(DestroyedItemStateHome.class);

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

	public void persist(DestroyedItemState transientInstance) {
		log.debug("persisting DestroyedItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DestroyedItemState instance) {
		log.debug("attaching dirty DestroyedItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DestroyedItemState instance) {
		log.debug("attaching clean DestroyedItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DestroyedItemState persistentInstance) {
		log.debug("deleting DestroyedItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DestroyedItemState merge(DestroyedItemState detachedInstance) {
		log.debug("merging DestroyedItemState instance");
		try {
			DestroyedItemState result = (DestroyedItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DestroyedItemState findById(java.lang.String id) {
		log.debug("getting DestroyedItemState instance with id: " + id);
		try {
			DestroyedItemState instance = (DestroyedItemState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.DestroyedItemState", id);
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

	public List<DestroyedItemState> findByExample(DestroyedItemState instance) {
		log.debug("finding DestroyedItemState instance by example");
		try {
			List<DestroyedItemState> results = (List<DestroyedItemState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.DestroyedItemState")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
