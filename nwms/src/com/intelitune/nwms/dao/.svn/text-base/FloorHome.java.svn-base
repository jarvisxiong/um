package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.model.Floor;
import com.intelitune.nwms.model.FloorState;

/**
 * Home object for domain model class Floor.
 * 
 * @see com.intelitune.nwms.model.Floor
 * @author Hibernate Tools
 */
public class FloorHome {
	private final static FloorHome instance = new FloorHome();

	private FloorHome() {
	}

	public static final FloorHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(FloorHome.class);

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

	public void persist(Floor transientInstance) {
		log.debug("persisting Floor instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Floor instance) {
		log.debug("attaching dirty Floor instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Floor instance) {
		log.debug("attaching clean Floor instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Floor persistentInstance) {
		log.debug("deleting Floor instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Floor merge(Floor detachedInstance) {
		log.debug("merging Floor instance");
		try {
			Floor result = (Floor) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Floor findById(java.lang.String id) {
		log.debug("getting Floor instance with id: " + id);
		try {
			Floor instance = (Floor) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Floor", id);
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

	public List<Floor> findByExample(Floor instance) {
		log.debug("finding Floor instance by example");
		try {
			List<Floor> results = (List<Floor>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Floor").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Floor> findFloorList(String building_id) {
		String hql = "from Floor as f where f.building.id='" + building_id
				+ "' and f.state.code='" + FloorState.NORMAL + "'";
		List<Floor> list = sessionFactory.getCurrentSession().createQuery(hql)
				.list();
		return list;
	}
}
