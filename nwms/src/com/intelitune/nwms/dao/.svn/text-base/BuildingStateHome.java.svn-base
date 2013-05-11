package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.BuildingState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class BuildingState.
 * 
 * @see com.intelitune.nwms.model.BuildingState
 * @author Hibernate Tools
 */
public class BuildingStateHome {
	private final static BuildingStateHome instance = new BuildingStateHome();

	private BuildingStateHome() {
	}

	public static final BuildingStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(BuildingStateHome.class);

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

	public void persist(BuildingState transientInstance) {
		log.debug("persisting BuildingState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(BuildingState instance) {
		log.debug("attaching dirty BuildingState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BuildingState instance) {
		log.debug("attaching clean BuildingState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(BuildingState persistentInstance) {
		log.debug("deleting BuildingState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BuildingState merge(BuildingState detachedInstance) {
		log.debug("merging BuildingState instance");
		try {
			BuildingState result = (BuildingState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BuildingState findById(java.lang.String id) {
		log.debug("getting BuildingState instance with id: " + id);
		try {
			BuildingState instance = (BuildingState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.BuildingState", id);
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

	public List<BuildingState> findByExample(BuildingState instance) {
		log.debug("finding BuildingState instance by example");
		try {
			List<BuildingState> results = (List<BuildingState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.BuildingState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public BuildingState findBuildingStateByCode(int code) {
		String str = code + "";
		String hql = "from BuildingState as b where b.code='" + str + "'";
		BuildingState bs = (BuildingState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return bs;
	}
}
