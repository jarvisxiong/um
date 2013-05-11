package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.TierState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TierState.
 * 
 * @see com.intelitune.nwms.model.TierState
 * @author Hibernate Tools
 */
public class TierStateHome {
	private final static TierStateHome instance = new TierStateHome();

	private TierStateHome() {
	}

	public static final TierStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(TierStateHome.class);

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

	public void persist(TierState transientInstance) {
		log.debug("persisting TierState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TierState instance) {
		log.debug("attaching dirty TierState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TierState instance) {
		log.debug("attaching clean TierState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TierState persistentInstance) {
		log.debug("deleting TierState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TierState merge(TierState detachedInstance) {
		log.debug("merging TierState instance");
		try {
			TierState result = (TierState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TierState findById(java.lang.String id) {
		log.debug("getting TierState instance with id: " + id);
		try {
			TierState instance = (TierState) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.TierState", id);
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

	public List<TierState> findByExample(TierState instance) {
		log.debug("finding TierState instance by example");
		try {
			List<TierState> results = (List<TierState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.TierState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public TierState findTierStateByCode(int code) {
		String str = code + "";
		String hql = "from TierState as t where t.code='" + str + "'";
		TierState ts = (TierState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return ts;
	}
}
