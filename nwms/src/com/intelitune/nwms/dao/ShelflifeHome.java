package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Shelflife;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Shelflife.
 * 
 * @see com.intelitune.nwms.model.Shelflife
 * @author Hibernate Tools
 */
public class ShelflifeHome {
	private final static ShelflifeHome instance = new ShelflifeHome();

	private ShelflifeHome() {
	}

	public static final ShelflifeHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ShelflifeHome.class);

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

	public void persist(Shelflife transientInstance) {
		log.debug("persisting Shelflife instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Shelflife instance) {
		log.debug("attaching dirty Shelflife instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Shelflife instance) {
		log.debug("attaching clean Shelflife instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Shelflife persistentInstance) {
		log.debug("deleting Shelflife instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Shelflife merge(Shelflife detachedInstance) {
		log.debug("merging Shelflife instance");
		try {
			Shelflife result = (Shelflife) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Shelflife findById(java.lang.String id) {
		log.debug("getting Shelflife instance with id: " + id);
		try {
			Shelflife instance = (Shelflife) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.Shelflife", id);
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

	public List<Shelflife> findByExample(Shelflife instance) {
		log.debug("finding Shelflife instance by example");
		try {
			List<Shelflife> results = (List<Shelflife>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Shelflife").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
