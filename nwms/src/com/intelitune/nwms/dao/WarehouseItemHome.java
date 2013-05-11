package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.WarehouseItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class WarehouseItem.
 * 
 * @see com.intelitune.nwms.model.WarehouseItem
 * @author Hibernate Tools
 */
public class WarehouseItemHome {
	private final static WarehouseItemHome instance = new WarehouseItemHome();

	private WarehouseItemHome() {
	}

	public static final WarehouseItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(WarehouseItemHome.class);

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

	public void persist(WarehouseItem transientInstance) {
		log.debug("persisting WarehouseItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(WarehouseItem instance) {
		log.debug("attaching dirty WarehouseItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WarehouseItem instance) {
		log.debug("attaching clean WarehouseItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(WarehouseItem persistentInstance) {
		log.debug("deleting WarehouseItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WarehouseItem merge(WarehouseItem detachedInstance) {
		log.debug("merging WarehouseItem instance");
		try {
			WarehouseItem result = (WarehouseItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WarehouseItem findById(java.lang.String id) {
		log.debug("getting WarehouseItem instance with id: " + id);
		try {
			WarehouseItem instance = (WarehouseItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.WarehouseItem", id);
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

	public List<WarehouseItem> findByExample(WarehouseItem instance) {
		log.debug("finding WarehouseItem instance by example");
		try {
			List<WarehouseItem> results = (List<WarehouseItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.WarehouseItem").add(
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
