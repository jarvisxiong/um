package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UnitPackagePair;

/**
 * Home object for domain model class UnitPackagePair.
 * 
 * @see com.intelitune.nwms.model.UnitPackagePair
 * @author Hibernate Tools
 */
public class UnitPackagePairHome {
	private final static UnitPackagePairHome instance = new UnitPackagePairHome();

	private UnitPackagePairHome() {
	}

	public static final UnitPackagePairHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(UnitPackagePairHome.class);

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

	public void persist(UnitPackagePair transientInstance) {
		log.debug("persisting UnitPackagePair instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitPackagePair instance) {
		log.debug("attaching dirty UnitPackagePair instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitPackagePair instance) {
		log.debug("attaching clean UnitPackagePair instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UnitPackagePair persistentInstance) {
		log.debug("deleting UnitPackagePair instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitPackagePair merge(UnitPackagePair detachedInstance) {
		log.debug("merging UnitPackagePair instance");
		try {
			UnitPackagePair result = (UnitPackagePair) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UnitPackagePair findById(java.lang.String id) {
		log.debug("getting UnitPackagePair instance with id: " + id);
		try {
			UnitPackagePair instance = (UnitPackagePair) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.UnitPackagePair", id);
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

	public List<UnitPackagePair> findByExample(UnitPackagePair instance) {
		log.debug("finding UnitPackagePair instance by example");
		try {
			List<UnitPackagePair> results = (List<UnitPackagePair>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UnitPackagePair").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UnitPackagePair> findAll() {
		List<UnitPackagePair> results = (List<UnitPackagePair>) sessionFactory
				.getCurrentSession().createQuery(" from UnitPackagePair")
				.list();
		return results;
	}

	public UnitPackagePair getUnitpackagePair(String id) {
		return (UnitPackagePair) sessionFactory.getCurrentSession().get(
				UnitPackagePair.class, id);
	}

	public void saveUnitPackagePair(UnitPackagePair unitPackagePair) {
		sessionFactory.getCurrentSession().save(unitPackagePair);
	}

	public void updatePackagePair(UnitPackagePair unitPackagePair) {
		sessionFactory.getCurrentSession().update(unitPackagePair);
	}

	public List<UnitPackagePair> findByHql(String hql) {
		return (List<UnitPackagePair>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
