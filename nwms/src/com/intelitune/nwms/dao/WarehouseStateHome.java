package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.WarehouseState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class WarehouseState.
 * 
 * @see com.intelitune.nwms.model.WarehouseState
 * @author Hibernate Tools
 */
public class WarehouseStateHome {
	private final static WarehouseStateHome instance = new WarehouseStateHome();

	private WarehouseStateHome() {
	}

	public static final WarehouseStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(WarehouseStateHome.class);

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

	public void persist(WarehouseState transientInstance) {
		log.debug("persisting WarehouseState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(WarehouseState instance) {
		log.debug("attaching dirty WarehouseState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WarehouseState instance) {
		log.debug("attaching clean WarehouseState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(WarehouseState persistentInstance) {
		log.debug("deleting WarehouseState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WarehouseState merge(WarehouseState detachedInstance) {
		log.debug("merging WarehouseState instance");
		try {
			WarehouseState result = (WarehouseState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WarehouseState findById(java.lang.String id) {
		log.debug("getting WarehouseState instance with id: " + id);
		try {
			WarehouseState instance = (WarehouseState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.WarehouseState", id);
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

	public List<WarehouseState> findByExample(WarehouseState instance) {
		log.debug("finding WarehouseState instance by example");
		try {
			List<WarehouseState> results = (List<WarehouseState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.WarehouseState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public WarehouseState findWarehouseStateByCode(int code) {
		String str = code + "";
		String hql = "from WarehouseState as w where w.code='" + str + "'";
		WarehouseState w = (WarehouseState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return w;
	}
}
