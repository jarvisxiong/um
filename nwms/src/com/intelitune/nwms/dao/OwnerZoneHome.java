package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.ZoneState;

/**
 * Home object for domain model class OwnerZone.
 * 
 * @see com.intelitune.nwms.model.OwnerZone
 * @author Hibernate Tools
 */
public class OwnerZoneHome {
	private final static OwnerZoneHome instance = new OwnerZoneHome();

	private OwnerZoneHome() {
	}

	public static final OwnerZoneHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(OwnerZoneHome.class);

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

	public void persist(OwnerZone transientInstance) {
		log.debug("persisting OwnerZone instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OwnerZone instance) {
		log.debug("attaching dirty OwnerZone instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OwnerZone instance) {
		log.debug("attaching clean OwnerZone instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OwnerZone persistentInstance) {
		log.debug("deleting OwnerZone instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OwnerZone merge(OwnerZone detachedInstance) {
		log.debug("merging OwnerZone instance");
		try {
			OwnerZone result = (OwnerZone) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OwnerZone findById(java.lang.String id) {
		log.debug("getting OwnerZone instance with id: " + id);
		try {
			OwnerZone instance = (OwnerZone) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.OwnerZone", id);
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

	public List<OwnerZone> findByExample(OwnerZone instance) {
		log.debug("finding OwnerZone instance by example");
		try {
			List<OwnerZone> results = (List<OwnerZone>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OwnerZone").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<OwnerZone> findOwnerZoneList() {
		String hql = "from OwnerZone as z where z.state.code='"
				+ ZoneState.NORMAL + "'";
		List<OwnerZone> list = sessionFactory.getCurrentSession().createQuery(
				hql).list();
		return list;
	}

	public OwnerZone findOwnerZoneById(String id) {
		String hql = "from OwnerZone as o where o.id='" + id + "'";
		List<OwnerZone> list = sessionFactory.getCurrentSession().createQuery(
				hql).list();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
}
