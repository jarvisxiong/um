package com.intelitune.nwms.dao;

// Generated 2009-2-26 13:09:08 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OutboundRePutAwayItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OutboundRePutAwayItemState.
 * 
 * @see com.intelitune.nwms.model.OutboundRePutAwayItemState
 * @author Hibernate Tools
 */
public class OutboundRePutAwayItemStateHome {
	private final static OutboundRePutAwayItemStateHome instance = new OutboundRePutAwayItemStateHome();

	private OutboundRePutAwayItemStateHome() {
	}

	public static final OutboundRePutAwayItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(OutboundRePutAwayItemStateHome.class);

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

	public void persist(OutboundRePutAwayItemState transientInstance) {
		log.debug("persisting OutboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundRePutAwayItemState instance) {
		log.debug("attaching dirty OutboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundRePutAwayItemState instance) {
		log.debug("attaching clean OutboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundRePutAwayItemState persistentInstance) {
		log.debug("deleting OutboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundRePutAwayItemState merge(
			OutboundRePutAwayItemState detachedInstance) {
		log.debug("merging OutboundRePutAwayItemState instance");
		try {
			OutboundRePutAwayItemState result = (OutboundRePutAwayItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundRePutAwayItemState findById(java.lang.String id) {
		log.debug("getting OutboundRePutAwayItemState instance with id: " + id);
		try {
			OutboundRePutAwayItemState instance = (OutboundRePutAwayItemState) sessionFactory
					.getCurrentSession()
					.get(
							"com.intelitune.nwms.model.OutboundRePutAwayItemState",
							id);
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

	public List<OutboundRePutAwayItemState> findByExample(
			OutboundRePutAwayItemState instance) {
		log.debug("finding OutboundRePutAwayItemState instance by example");
		try {
			List<OutboundRePutAwayItemState> results = (List<OutboundRePutAwayItemState>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.intelitune.nwms.model.OutboundRePutAwayItemState")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public OutboundRePutAwayItemState findStateByCode(int code) {
		String str = code + "";
		String hql = "from OutboundRePutAwayItemState as o where o.code='"
				+ str + "'";
		OutboundRePutAwayItemState os = (OutboundRePutAwayItemState) sessionFactory
				.getCurrentSession().createQuery(hql).list().get(0);
		return os;
	}
}
