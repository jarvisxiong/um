package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.FloorState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FloorState.
 * 
 * @see com.intelitune.nwms.model.FloorState
 * @author Hibernate Tools
 */
public class FloorStateHome {
	private final static FloorStateHome instance = new FloorStateHome();

	private FloorStateHome() {
	}

	public static final FloorStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(FloorStateHome.class);

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

	public void persist(FloorState transientInstance) {
		log.debug("persisting FloorState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FloorState instance) {
		log.debug("attaching dirty FloorState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FloorState instance) {
		log.debug("attaching clean FloorState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FloorState persistentInstance) {
		log.debug("deleting FloorState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FloorState merge(FloorState detachedInstance) {
		log.debug("merging FloorState instance");
		try {
			FloorState result = (FloorState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FloorState findById(java.lang.String id) {
		log.debug("getting FloorState instance with id: " + id);
		try {
			FloorState instance = (FloorState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.FloorState", id);
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

	public List<FloorState> findByExample(FloorState instance) {
		log.debug("finding FloorState instance by example");
		try {
			List<FloorState> results = (List<FloorState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.FloorState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public FloorState findFloorStateByCode(int code) {
		String str = code + "";
		String hql = "from FloorState as f where f.code='" + str + "'";
		FloorState fs = (FloorState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return fs;
	}
}
