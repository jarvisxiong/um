package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.CheckingItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CheckingItem.
 * 
 * @see com.intelitune.nwms.model.CheckingItem
 * @author Hibernate Tools
 */
public class CheckingItemHome {
	private final static CheckingItemHome instance = new CheckingItemHome();

	private CheckingItemHome() {
	}

	public static final CheckingItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(CheckingItemHome.class);

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

	public void persist(CheckingItem transientInstance) {
		log.debug("persisting CheckingItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CheckingItem instance) {
		log.debug("attaching dirty CheckingItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CheckingItem instance) {
		log.debug("attaching clean CheckingItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CheckingItem persistentInstance) {
		log.debug("deleting CheckingItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CheckingItem merge(CheckingItem detachedInstance) {
		log.debug("merging CheckingItem instance");
		try {
			CheckingItem result = (CheckingItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CheckingItem findById(java.lang.String id) {
		log.debug("getting CheckingItem instance with id: " + id);
		try {
			CheckingItem instance = (CheckingItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.CheckingItem", id);
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

	public List<CheckingItem> findByExample(CheckingItem instance) {
		log.debug("finding CheckingItem instance by example");
		try {
			List<CheckingItem> results = (List<CheckingItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.CheckingItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<CheckingItem> findByHql(String hql) {
		List<CheckingItem> results = (List<CheckingItem>) sessionFactory
				.getCurrentSession().createQuery(hql).list();
		return results;
	}

	public void saveCheckingItem(CheckingItem checkingItem) {
		sessionFactory.getCurrentSession().save(checkingItem);
	}

}
