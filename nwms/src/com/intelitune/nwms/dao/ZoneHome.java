package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Zone;
import com.intelitune.nwms.model.ZoneState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Zone.
 * 
 * @see com.intelitune.nwms.model.Zone
 * @author Hibernate Tools
 */
public class ZoneHome {
	private final static ZoneHome instance = new ZoneHome();

	private ZoneHome() {
	}

	public static final ZoneHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ZoneHome.class);

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

	public void persist(Zone transientInstance) {
		log.debug("persisting Zone instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zone instance) {
		log.debug("attaching dirty Zone instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zone instance) {
		log.debug("attaching clean Zone instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zone persistentInstance) {
		log.debug("deleting Zone instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zone merge(Zone detachedInstance) {
		log.debug("merging Zone instance");
		try {
			Zone result = (Zone) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zone findById(java.lang.String id) {
		log.debug("getting Zone instance with id: " + id);
		try {
			Zone instance = (Zone) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Zone", id);
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

	public List<Zone> findByExample(Zone instance) {
		log.debug("finding Zone instance by example");
		try {
			List<Zone> results = (List<Zone>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Zone").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Zone> findZoneList() {
		String hql = "from Zone as z where z.state.code='" + ZoneState.NORMAL
				+ "'";
		List<Zone> list = sessionFactory.getCurrentSession().createQuery(hql)
				.list();
		return list;
	}
}
