package com.intelitune.nwms.dao;

// Generated 2009-2-4 18:23:24 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UncheckedItem;

/**
 * Home object for domain model class UncheckedItem.
 * 
 * @see com.intelitune.nwms.model.UncheckedItem
 * @author Hibernate Tools
 */
public class UncheckedItemHome {
	private final static UncheckedItemHome instance = new UncheckedItemHome();

	private UncheckedItemHome() {
	}

	public static final UncheckedItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(UncheckedItemHome.class);

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

	public void persist(UncheckedItem transientInstance) {
		log.debug("persisting UncheckedItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UncheckedItem instance) {
		log.debug("attaching dirty UncheckedItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UncheckedItem instance) {
		log.debug("attaching clean UncheckedItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UncheckedItem persistentInstance) {
		log.debug("deleting UncheckedItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UncheckedItem merge(UncheckedItem detachedInstance) {
		log.debug("merging UncheckedItem instance");
		try {
			UncheckedItem result = (UncheckedItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UncheckedItem findById(java.lang.String id) {
		log.debug("getting UncheckedItem instance with id: " + id);
		try {
			UncheckedItem instance = (UncheckedItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.UncheckedItem", id);
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

	public List<UncheckedItem> findByExample(UncheckedItem instance) {
		log.debug("finding UncheckedItem instance by example");
		try {
			List<UncheckedItem> results = (List<UncheckedItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UncheckedItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UncheckedItem> findByHql(String hql) {
		List<UncheckedItem> list = sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return list;
	}
}
