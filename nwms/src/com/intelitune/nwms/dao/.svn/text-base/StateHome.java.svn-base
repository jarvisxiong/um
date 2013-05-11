package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.State;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class State.
 * 
 * @see com.intelitune.nwms.model.State
 * @author Hibernate Tools
 */
public class StateHome {
	private final static StateHome instance = new StateHome();

	private StateHome() {
	}

	public static final StateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(StateHome.class);

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

	public void persist(State transientInstance) {
		log.debug("persisting State instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(State instance) {
		log.debug("attaching dirty State instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(State instance) {
		log.debug("attaching clean State instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(State persistentInstance) {
		log.debug("deleting State instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public State merge(State detachedInstance) {
		log.debug("merging State instance");
		try {
			State result = (State) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public State findById(java.lang.String id) {
		log.debug("getting State instance with id: " + id);
		try {
			State instance = (State) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.State", id);
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

	public List<State> findByExample(State instance) {
		log.debug("finding State instance by example");
		try {
			List<State> results = (List<State>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.State").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public State findStateByCode(String code) {
		log.debug("finding State instance by example");
		try {
			String hql = "from State as s where s.code='" + code + "'";
			State s = (State) sessionFactory.getCurrentSession().createQuery(
					hql).list().get(0);
			return s;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<State> findStateList() {
		String hql = "from State";
		List<State> list = sessionFactory.getCurrentSession().createQuery(hql)
				.list();
		return list;
	}
}
