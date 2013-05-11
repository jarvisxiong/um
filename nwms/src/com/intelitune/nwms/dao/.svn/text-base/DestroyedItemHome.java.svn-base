package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.DestroyedItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DestroyedItem.
 * 
 * @see com.intelitune.nwms.model.DestroyedItem
 * @author Hibernate Tools
 */
public class DestroyedItemHome {
	private final static DestroyedItemHome instance = new DestroyedItemHome();

	private DestroyedItemHome() {
	}

	public static final DestroyedItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(DestroyedItemHome.class);

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

	public void persist(DestroyedItem transientInstance) {
		log.debug("persisting DestroyedItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DestroyedItem instance) {
		log.debug("attaching dirty DestroyedItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DestroyedItem instance) {
		log.debug("attaching clean DestroyedItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DestroyedItem persistentInstance) {
		log.debug("deleting DestroyedItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DestroyedItem merge(DestroyedItem detachedInstance) {
		log.debug("merging DestroyedItem instance");
		try {
			DestroyedItem result = (DestroyedItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DestroyedItem findById(java.lang.String id) {
		log.debug("getting DestroyedItem instance with id: " + id);
		try {
			DestroyedItem instance = (DestroyedItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.DestroyedItem", id);
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

	public List<DestroyedItem> findByExample(DestroyedItem instance) {
		log.debug("finding DestroyedItem instance by example");
		try {
			List<DestroyedItem> results = (List<DestroyedItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.DestroyedItem").add(
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
