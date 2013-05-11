package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Tier;

/**
 * Home object for domain model class Tier.
 * 
 * @see com.intelitune.nwms.model.Tier
 * @author Hibernate Tools
 */
public class TierHome {
	private final static TierHome instance = new TierHome();

	private TierHome() {
	}

	public static final TierHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(TierHome.class);

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

	public void persist(Tier transientInstance) {
		log.debug("persisting Tier instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tier instance) {
		log.debug("attaching dirty Tier instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tier instance) {
		log.debug("attaching clean Tier instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tier persistentInstance) {
		log.debug("deleting Tier instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tier merge(Tier detachedInstance) {
		log.debug("merging Tier instance");
		try {
			Tier result = (Tier) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tier findById(java.lang.String id) {
		log.debug("getting Tier instance with id: " + id);
		try {
			Tier instance = (Tier) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Tier", id);
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

	public List<Tier> findByExample(Tier instance) {
		log.debug("finding Tier instance by example");
		try {
			List<Tier> results = (List<Tier>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Tier").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public Tier findTierByCode(String row_id, String tier) {
		String hql = "from Tier as t where t.row.id='" + row_id
				+ "' and code='" + tier + "'";
		Tier t = (Tier) sessionFactory.getCurrentSession().createQuery(hql)
				.list().get(0);
		return t;
	}

	public int countTierByRowId(String row_id) {
		int result = 0;
		String hql = "select count(*) from Tier as t where t.row.id='" + row_id
				+ "'";
		result = Integer.parseInt(sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0).toString());
		return result;
	}
}
