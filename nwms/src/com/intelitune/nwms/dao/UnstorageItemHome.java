package com.intelitune.nwms.dao;

// Generated 2009-2-4 18:23:24 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UnstorageItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class UnstorageItem.
 * 
 * @see com.intelitune.nwms.model.UnstorageItem
 * @author Hibernate Tools
 */
public class UnstorageItemHome {
	private final static UnstorageItemHome instance = new UnstorageItemHome();

	private UnstorageItemHome() {
	}

	public static final UnstorageItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(UnstorageItemHome.class);

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

	public void persist(UnstorageItem transientInstance) {
		log.debug("persisting UnstorageItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UnstorageItem instance) {
		log.debug("attaching dirty UnstorageItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnstorageItem instance) {
		log.debug("attaching clean UnstorageItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UnstorageItem persistentInstance) {
		log.debug("deleting UnstorageItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnstorageItem merge(UnstorageItem detachedInstance) {
		log.debug("merging UnstorageItem instance");
		try {
			UnstorageItem result = (UnstorageItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UnstorageItem findById(java.lang.String id) {
		log.debug("getting UnstorageItem instance with id: " + id);
		try {
			UnstorageItem instance = (UnstorageItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.UnstorageItem", id);
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

	public List<UnstorageItem> findByExample(UnstorageItem instance) {
		log.debug("finding UnstorageItem instance by example");
		try {
			List<UnstorageItem> results = (List<UnstorageItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UnstorageItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UnstorageItem> findByHql(String hql) {
		return (List<UnstorageItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
