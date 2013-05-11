package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.BinState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class BinState.
 * 
 * @see com.intelitune.nwms.model.BinState
 * @author Hibernate Tools
 */
public class BinStateHome {
	
	private final static BinStateHome instance = new BinStateHome();

	private BinStateHome() {
	}

	public static final BinStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(BinStateHome.class);

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

	public void persist(BinState transientInstance) {
		log.debug("persisting BinState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(BinState instance) {
		log.debug("attaching dirty BinState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BinState instance) {
		log.debug("attaching clean BinState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(BinState persistentInstance) {
		log.debug("deleting BinState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BinState merge(BinState detachedInstance) {
		log.debug("merging BinState instance");
		try {
			BinState result = (BinState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BinState findById(java.lang.String id) {
		log.debug("getting BinState instance with id: " + id);
		try {
			BinState instance = (BinState) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.BinState", id);
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

	public List<BinState> findByExample(BinState instance) {
		log.debug("finding BinState instance by example");
		try {
			List<BinState> results = (List<BinState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.BinState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public BinState findBinStateByCode(int code) {
		String str = code + "";
		String hql = "from BinState as b where b.code='" + str + "'";
		BinState bs = (BinState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return bs;
	}

	public List<BinState> findBinStateList() {
		String hql = "from BinState";
		List<BinState> list = sessionFactory.getCurrentSession().createQuery(
				hql).list();
		return list;
	}
}
