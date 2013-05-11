package com.intelitune.nwms.dao;

// Generated 2009-2-26 14:21:14 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.StorageItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class StorageItemState.
 * 
 * @see com.intelitune.nwms.model.StorageItemState
 * @author Hibernate Tools
 */
public class StorageItemStateHome {
	private final static StorageItemStateHome instance = new StorageItemStateHome();

	private StorageItemStateHome() {
	}

	public static final StorageItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(StorageItemStateHome.class);

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

	public void persist(StorageItemState transientInstance) {
		log.debug("persisting StorageItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StorageItemState instance) {
		log.debug("attaching dirty StorageItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StorageItemState instance) {
		log.debug("attaching clean StorageItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StorageItemState persistentInstance) {
		log.debug("deleting StorageItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StorageItemState merge(StorageItemState detachedInstance) {
		log.debug("merging StorageItemState instance");
		try {
			StorageItemState result = (StorageItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StorageItemState findById(java.lang.String id) {
		log.debug("getting StorageItemState instance with id: " + id);
		try {
			StorageItemState instance = (StorageItemState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.StorageItemState", id);
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

	public List<StorageItemState> findByExample(StorageItemState instance) {
		log.debug("finding StorageItemState instance by example");
		try {
			List<StorageItemState> results = (List<StorageItemState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.StorageItemState").add(
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
