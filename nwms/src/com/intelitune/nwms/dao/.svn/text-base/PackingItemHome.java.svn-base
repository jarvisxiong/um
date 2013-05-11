package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.PackingItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PackingItem.
 * 
 * @see com.intelitune.nwms.model.PackingItem
 * @author Hibernate Tools
 */
public class PackingItemHome {
	private final static PackingItemHome instance = new PackingItemHome();

	private PackingItemHome() {
	}

	public static final PackingItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(PackingItemHome.class);

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

	public void persist(PackingItem transientInstance) {
		log.debug("persisting PackingItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PackingItem instance) {
		log.debug("attaching dirty PackingItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PackingItem instance) {
		log.debug("attaching clean PackingItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PackingItem persistentInstance) {
		log.debug("deleting PackingItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PackingItem merge(PackingItem detachedInstance) {
		log.debug("merging PackingItem instance");
		try {
			PackingItem result = (PackingItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PackingItem findById(java.lang.String id) {
		log.debug("getting PackingItem instance with id: " + id);
		try {
			PackingItem instance = (PackingItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.PackingItem", id);
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

	public List<PackingItem> findByExample(PackingItem instance) {
		log.debug("finding PackingItem instance by example");
		try {
			List<PackingItem> results = (List<PackingItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.PackingItem").add(
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
