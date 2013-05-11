package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UnitPackagePairState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class UnitPackagePairState.
 * 
 * @see com.intelitune.nwms.model.UnitPackagePairState
 * @author Hibernate Tools
 */
public class UnitPackagePairStateHome {
	private final static UnitPackagePairStateHome instance = new UnitPackagePairStateHome();

	private UnitPackagePairStateHome() {
	}

	public static final UnitPackagePairStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(UnitPackagePairStateHome.class);

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

	public void persist(UnitPackagePairState transientInstance) {
		log.debug("persisting UnitPackagePairState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitPackagePairState instance) {
		log.debug("attaching dirty UnitPackagePairState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitPackagePairState instance) {
		log.debug("attaching clean UnitPackagePairState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UnitPackagePairState persistentInstance) {
		log.debug("deleting UnitPackagePairState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitPackagePairState merge(UnitPackagePairState detachedInstance) {
		log.debug("merging UnitPackagePairState instance");
		try {
			UnitPackagePairState result = (UnitPackagePairState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UnitPackagePairState findById(java.lang.String id) {
		log.debug("getting UnitPackagePairState instance with id: " + id);
		try {
			UnitPackagePairState instance = (UnitPackagePairState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.UnitPackagePairState",
							id);
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

	public List<UnitPackagePairState> findByExample(
			UnitPackagePairState instance) {
		log.debug("finding UnitPackagePairState instance by example");
		try {
			List<UnitPackagePairState> results = (List<UnitPackagePairState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UnitPackagePairState")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UnitPackagePairState> findAll() {
		return (List<UnitPackagePairState>) sessionFactory.getCurrentSession()
				.createQuery("from UnitPackagePairState order by code").list();
	}

	public UnitPackagePairState getUnitPackagePairState(String code) {
		List list = sessionFactory.getCurrentSession().createQuery(
				" from UnitPackagaePairState i where i.code='" + code + "'")
				.list();
		if (list.size() != 0) {
			return (UnitPackagePairState) list.get(0);
		}
		return null;
	}

	public void saveUnitPackagePairState(
			UnitPackagePairState unitPackagePairState) {
		sessionFactory.getCurrentSession().save(unitPackagePairState);
	}

	public void updatePackagePairState(UnitPackagePairState unitPackagePairState) {
		sessionFactory.getCurrentSession().update(unitPackagePairState);
	}

	public UnitPackagePairState findUnitPackagePairState(String id) {
		return (UnitPackagePairState) sessionFactory.getCurrentSession().get(
				UnitPackagePairState.class, id);
	}
}
