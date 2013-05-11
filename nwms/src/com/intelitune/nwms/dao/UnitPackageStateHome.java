package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UnitPackageState;

/**
 * Home object for domain model class UnitPackageState.
 * 
 * @see com.intelitune.nwms.model.UnitPackageState
 * @author Hibernate Tools
 */
public class UnitPackageStateHome {
	private final static UnitPackageStateHome instance = new UnitPackageStateHome();

	private UnitPackageStateHome() {
	}

	public static final UnitPackageStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(UnitPackageStateHome.class);

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

	public void persist(UnitPackageState transientInstance) {
		log.debug("persisting UnitPackageState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitPackageState instance) {
		log.debug("attaching dirty UnitPackageState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitPackageState instance) {
		log.debug("attaching clean UnitPackageState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UnitPackageState persistentInstance) {
		log.debug("deleting UnitPackageState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitPackageState merge(UnitPackageState detachedInstance) {
		log.debug("merging UnitPackageState instance");
		try {
			UnitPackageState result = (UnitPackageState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UnitPackageState findById(java.lang.String id) {
		log.debug("getting UnitPackageState instance with id: " + id);
		try {
			UnitPackageState instance = (UnitPackageState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.UnitPackageState", id);
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

	public List<UnitPackageState> findByExample(UnitPackageState instance) {
		log.debug("finding UnitPackageState instance by example");
		try {
			List<UnitPackageState> results = (List<UnitPackageState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UnitPackageState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UnitPackageState> findAll() {
		log.debug("finding UnitPackageState");
		try {
			List<UnitPackageState> results = (List<UnitPackageState>) sessionFactory
					.getCurrentSession().createQuery(
							" from UnitPackageState order by code").list();

			log.debug("find successful result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	public void saveUnitPackageState(UnitPackageState unitPackageState) {
		try {
			sessionFactory.getCurrentSession().save(unitPackageState);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void updateUnitPackageState(UnitPackageState unitPackageState) {
		try {
			sessionFactory.getCurrentSession().update(unitPackageState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UnitPackageState getUnitPackageState(String code) {
		List list = sessionFactory.getCurrentSession().createQuery(
				" from UnitPackageState i where i.code='" + code + "'").list();
		if (list.size() != 0) {
			return (UnitPackageState) list.get(0);
		}
		return null;
	}

	public UnitPackageState findUnitPackageState(String id) {
		return (UnitPackageState) sessionFactory.getCurrentSession().get(
				UnitPackageState.class, id);
	}

}
