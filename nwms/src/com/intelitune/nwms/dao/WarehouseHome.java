package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.model.WarehouseState;

/**
 * Home object for domain model class Warehouse.
 * 
 * @see com.intelitune.nwms.model.Warehouse
 * @author Hibernate Tools
 */
public class WarehouseHome {
	private final static WarehouseHome instance = new WarehouseHome();

	private WarehouseHome() {
	}

	public static final WarehouseHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(WarehouseHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("nWMSSessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Warehouse transientInstance) {
		log.debug("persisting Warehouse instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Warehouse instance) {
		log.debug("attaching dirty Warehouse instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Warehouse instance) {
		log.debug("attaching clean Warehouse instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Warehouse persistentInstance) {
		log.debug("deleting Warehouse instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Warehouse merge(Warehouse detachedInstance) {
		log.debug("merging Warehouse instance");
		try {
			Warehouse result = (Warehouse) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Warehouse findById(java.lang.String id) {
		log.debug("getting Warehouse instance with id: " + id);
		try {
			Warehouse instance = (Warehouse) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.Warehouse", id);
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

	public List<Warehouse> findByExample(Warehouse instance) {
		log.debug("finding Warehouse instance by example");
		try {
			List<Warehouse> results = (List<Warehouse>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Warehouse").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public String fingNameById(String id) {
		log.debug("finding Warehouse instance by example");
		try {
			Warehouse instance = (Warehouse) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.Warehouse", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance.getAlias();
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Warehouse> findWarehouseList() {
		log.debug("finding Warehouse instance by example");
		try {

			String hql = "from Warehouse as w where w.state.code='"
					+ WarehouseState.NORMAL + "'";
			List<Warehouse> list = (List<Warehouse>) sessionFactory
					.getCurrentSession().createQuery(hql).list();
			return list;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

}
