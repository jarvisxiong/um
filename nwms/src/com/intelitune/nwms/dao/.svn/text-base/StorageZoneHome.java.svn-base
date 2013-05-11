package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.model.ZoneState;

/**
 * Home object for domain model class StorageZone.
 * 
 * @see com.intelitune.nwms.model.StorageZone
 * @author Hibernate Tools
 */
public class StorageZoneHome {
	private final static StorageZoneHome instance = new StorageZoneHome();

	private StorageZoneHome() {
	}

	public static final StorageZoneHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(StorageZoneHome.class);

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

	public void persist(StorageZone transientInstance) {
		log.debug("persisting StorageZone instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StorageZone instance) {
		log.debug("attaching dirty StorageZone instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StorageZone instance) {
		log.debug("attaching clean StorageZone instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StorageZone persistentInstance) {
		log.debug("deleting StorageZone instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StorageZone merge(StorageZone detachedInstance) {
		log.debug("merging StorageZone instance");
		try {
			StorageZone result = (StorageZone) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StorageZone findById(java.lang.String id) {
		log.debug("getting StorageZone instance with id: " + id);
		try {
			StorageZone instance = (StorageZone) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.StorageZone", id);
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

	public List<StorageZone> findByExample(StorageZone instance) {
		log.debug("finding StorageZone instance by example");
		try {
			List<StorageZone> results = (List<StorageZone>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.StorageZone").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<StorageZone> findStorageZoneList() {
		String hql = "from StorageZone as s where s.state.code='"
				+ ZoneState.NORMAL + "'";
		List<StorageZone> list = (List<StorageZone>) sessionFactory
				.getCurrentSession().createQuery(hql).list();
		return list;
	}

	public StorageZone findStorageZoneById(String id) {
		String hql = "from StorageZone as s where s.id='" + id + "'";
		List<StorageZone> list = sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
}
