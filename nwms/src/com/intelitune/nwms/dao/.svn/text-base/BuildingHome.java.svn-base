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
import com.intelitune.nwms.model.Building;
import com.intelitune.nwms.model.BuildingState;

/**
 * Home object for domain model class Building.
 * 
 * @see com.intelitune.nwms.model.Building
 * @author Hibernate Tools
 */
public class BuildingHome {
	private final static BuildingHome instance = new BuildingHome();

	private BuildingHome() {
	}

	public static final BuildingHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(BuildingHome.class);

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

	public void persist(Building transientInstance) {
		log.debug("persisting Building instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Building instance) {
		log.debug("attaching dirty Building instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Building instance) {
		log.debug("attaching clean Building instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Building persistentInstance) {
		log.debug("deleting Building instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Building merge(Building detachedInstance) {
		log.debug("merging Building instance");
		try {
			Building result = (Building) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Building findById(java.lang.String id) {
		log.debug("getting Building instance with id: " + id);
		try {
			Building instance = (Building) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.Building", id);
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

	public List<Building> findByExample(Building instance) {
		log.debug("finding Building instance by example");
		try {
			List<Building> results = (List<Building>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Building").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Building> findBuildingListByWarehouse_id(String warehouse_id) {
		String hql = "from Building as b where b.warehouse.id = '"
				+ warehouse_id + "' and b.state.code='" + BuildingState.NORMAL
				+ "'";
		try {
			List<Building> list = null;

			list = sessionFactory.getCurrentSession().createQuery(hql).list();

			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
