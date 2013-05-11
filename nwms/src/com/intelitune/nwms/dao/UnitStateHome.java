package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UnitState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class UnitState.
 * 
 * @see com.intelitune.nwms.model.UnitState
 * @author Hibernate Tools
 */
public class UnitStateHome {
	private final static UnitStateHome instance = new UnitStateHome();

	private UnitStateHome() {
	}

	public static final UnitStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(UnitStateHome.class);

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

	public void persist(UnitState transientInstance) {
		log.debug("persisting UnitState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitState instance) {
		log.debug("attaching dirty UnitState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitState instance) {
		log.debug("attaching clean UnitState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UnitState persistentInstance) {
		log.debug("deleting UnitState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitState merge(UnitState detachedInstance) {
		log.debug("merging UnitState instance");
		try {
			UnitState result = (UnitState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UnitState findById(java.lang.String id) {
		log.debug("getting UnitState instance with id: " + id);
		try {
			UnitState instance = (UnitState) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.UnitState", id);
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

	public List<UnitState> findByExample(UnitState instance) {
		log.debug("finding UnitState instance by example");
		try {
			List<UnitState> results = (List<UnitState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UnitState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UnitState> findAll() {
		return (List<UnitState>) sessionFactory.getCurrentSession()
				.createQuery(" from UnitState order by code").list();
	}

	public UnitState getUnitState(String code) {
		List list = sessionFactory.getCurrentSession().createQuery(
				" from UnitState i where i.code='" + code + "'").list();
		if (list.size() != 0) {
			return (UnitState) list.get(0);
		}
		return null;
	}

	public void saveUnitState(UnitState unitState) {
		sessionFactory.getCurrentSession().save(unitState);
	}

	public void updateUnitState(UnitState unitState) {
		sessionFactory.getCurrentSession().update(unitState);
	}

	public UnitState findUnitState(String id) {
		return (UnitState) sessionFactory.getCurrentSession().get(
				UnitState.class, id);
	}
}
