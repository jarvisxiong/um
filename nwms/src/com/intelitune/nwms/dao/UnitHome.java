package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Unit;

/**
 * Home object for domain model class Unit.
 * 
 * @see com.intelitune.nwms.model.Unit
 * @author Hibernate Tools
 */
public class UnitHome {
	private final static UnitHome instance = new UnitHome();

	private UnitHome() {
	}

	public static final UnitHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(UnitHome.class);

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

	public void persist(Unit transientInstance) {
		log.debug("persisting Unit instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Unit instance) {
		log.debug("attaching dirty Unit instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Unit instance) {
		log.debug("attaching clean Unit instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Unit persistentInstance) {
		log.debug("deleting Unit instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Unit merge(Unit detachedInstance) {
		log.debug("merging Unit instance");
		try {
			Unit result = (Unit) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Unit findById(java.lang.String id) {
		log.debug("getting Unit instance with id: " + id);
		try {
			Unit instance = (Unit) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Unit", id);
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

	public List<Unit> findByExample(Unit instance) {
		log.debug("finding Unit instance by example");
		try {
			List<Unit> results = (List<Unit>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Unit").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Unit> findAll() {
		List<Unit> results = (List<Unit>) sessionFactory.getCurrentSession()
				.createQuery(" from Unit").list();
		return results;
	}

	public Unit getUnit(String id) {
		return (Unit) sessionFactory.getCurrentSession().get(Unit.class, id);
	}

	public void saveUnit(Unit unit) {
		sessionFactory.getCurrentSession().save(unit);
	}

	public void updateUnit(Unit unit) {
		sessionFactory.getCurrentSession().update(unit);
	}

	public List<Unit> findByHql(String hql) {
		return (List<Unit>) sessionFactory.getCurrentSession().createQuery(hql)
				.list();
	}
}
