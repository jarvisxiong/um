package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.UnitPackage;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class UnitPackage.
 * 
 * @see com.intelitune.nwms.model.UnitPackage
 * @author Hibernate Tools
 */
public class UnitPackageHome {
	private final static UnitPackageHome instance = new UnitPackageHome();

	private UnitPackageHome() {
	}

	public static final UnitPackageHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(UnitPackageHome.class);

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

	public void persist(UnitPackage transientInstance) {
		log.debug("persisting UnitPackage instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitPackage instance) {
		log.debug("attaching dirty UnitPackage instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitPackage instance) {
		log.debug("attaching clean UnitPackage instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UnitPackage persistentInstance) {
		log.debug("deleting UnitPackage instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitPackage merge(UnitPackage detachedInstance) {
		log.debug("merging UnitPackage instance");
		try {
			UnitPackage result = (UnitPackage) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UnitPackage findById(java.lang.String id) {
		log.debug("getting UnitPackage instance with id: " + id);
		try {
			UnitPackage instance = (UnitPackage) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.UnitPackage", id);
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

	public List<UnitPackage> findByExample(UnitPackage instance) {
		log.debug("finding UnitPackage instance by example");
		try {
			List<UnitPackage> results = (List<UnitPackage>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.UnitPackage").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<UnitPackage> findAll() {
		List<UnitPackage> results = (List<UnitPackage>) sessionFactory
				.getCurrentSession().createQuery(" from UnitPackage").list();
		return results;
	}

	public UnitPackage getUnitPackage(String id) {
		return (UnitPackage) sessionFactory.getCurrentSession().get(
				UnitPackage.class, id);
	}

	public void saveUnitPackage(UnitPackage unitPackage) {
		sessionFactory.getCurrentSession().save(unitPackage);
	}

	public void updateUnitPackage(UnitPackage unitPackage) {
		sessionFactory.getCurrentSession().update(unitPackage);
	}

	public List<UnitPackage> findByHql(String hql) {
		return (List<UnitPackage>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
