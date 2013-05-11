package com.intelitune.nwms.dao;

// Generated 2009-2-26 14:21:14 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OutboundRePutAwayItemState;
import com.intelitune.nwms.model.PickingItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PickingItemState.
 * 
 * @see com.intelitune.nwms.model.PickingItemState
 * @author Hibernate Tools
 */
public class PickingItemStateHome {
	private final static PickingItemStateHome instance = new PickingItemStateHome();

	private PickingItemStateHome() {
	}

	public static final PickingItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(PickingItemStateHome.class);

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

	public void persist(PickingItemState transientInstance) {
		log.debug("persisting PickingItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PickingItemState instance) {
		log.debug("attaching dirty PickingItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PickingItemState instance) {
		log.debug("attaching clean PickingItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PickingItemState persistentInstance) {
		log.debug("deleting PickingItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PickingItemState merge(PickingItemState detachedInstance) {
		log.debug("merging PickingItemState instance");
		try {
			PickingItemState result = (PickingItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PickingItemState findById(java.lang.String id) {
		log.debug("getting PickingItemState instance with id: " + id);
		try {
			PickingItemState instance = (PickingItemState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.PickingItemState", id);
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

	public List<PickingItemState> findByExample(PickingItemState instance) {
		log.debug("finding PickingItemState instance by example");
		try {
			List<PickingItemState> results = (List<PickingItemState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.PickingItemState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public PickingItemState findStateByCode(int code) {
		String str = code + "";
		String hql = "from OutboundRePutAwayItemState as o where o.code='"
				+ str + "'";
		PickingItemState ps = (PickingItemState) sessionFactory
				.getCurrentSession().createQuery(hql).list().get(0);
		return ps;
	}

}
