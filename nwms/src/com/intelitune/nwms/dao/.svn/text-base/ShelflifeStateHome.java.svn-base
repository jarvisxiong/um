package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ShelflifeState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ShelflifeState.
 * 
 * @see com.intelitune.nwms.model.ShelflifeState
 * @author Hibernate Tools
 */
public class ShelflifeStateHome {
	private final static ShelflifeStateHome instance = new ShelflifeStateHome();

	private ShelflifeStateHome() {
	}

	public static final ShelflifeStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ShelflifeStateHome.class);

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

	public void persist(ShelflifeState transientInstance) {
		log.debug("persisting ShelflifeState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ShelflifeState instance) {
		log.debug("attaching dirty ShelflifeState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ShelflifeState instance) {
		log.debug("attaching clean ShelflifeState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ShelflifeState persistentInstance) {
		log.debug("deleting ShelflifeState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ShelflifeState merge(ShelflifeState detachedInstance) {
		log.debug("merging ShelflifeState instance");
		try {
			ShelflifeState result = (ShelflifeState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ShelflifeState findById(java.lang.String id) {
		log.debug("getting ShelflifeState instance with id: " + id);
		try {
			ShelflifeState instance = (ShelflifeState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.ShelflifeState", id);
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

	public List<ShelflifeState> findByExample(ShelflifeState instance) {
		log.debug("finding ShelflifeState instance by example");
		try {
			List<ShelflifeState> results = (List<ShelflifeState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.ShelflifeState").add(
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
