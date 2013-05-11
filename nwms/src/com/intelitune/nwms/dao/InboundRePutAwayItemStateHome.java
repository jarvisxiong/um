package com.intelitune.nwms.dao;

// Generated 2009-2-26 13:09:08 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InboundRePutAwayItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InboundRePutAwayItemState.
 * 
 * @see com.intelitune.nwms.model.InboundRePutAwayItemState
 * @author Hibernate Tools
 */
public class InboundRePutAwayItemStateHome {
	private final static InboundRePutAwayItemStateHome instance = new InboundRePutAwayItemStateHome();

	private InboundRePutAwayItemStateHome() {
	}

	public static final InboundRePutAwayItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(InboundRePutAwayItemStateHome.class);

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

	public void persist(InboundRePutAwayItemState transientInstance) {
		log.debug("persisting InboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InboundRePutAwayItemState instance) {
		log.debug("attaching dirty InboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InboundRePutAwayItemState instance) {
		log.debug("attaching clean InboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InboundRePutAwayItemState persistentInstance) {
		log.debug("deleting InboundRePutAwayItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InboundRePutAwayItemState merge(
			InboundRePutAwayItemState detachedInstance) {
		log.debug("merging InboundRePutAwayItemState instance");
		try {
			InboundRePutAwayItemState result = (InboundRePutAwayItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InboundRePutAwayItemState findById(java.lang.String id) {
		log.debug("getting InboundRePutAwayItemState instance with id: " + id);
		try {
			InboundRePutAwayItemState instance = (InboundRePutAwayItemState) sessionFactory
					.getCurrentSession()
					.get("com.intelitune.nwms.model.InboundRePutAwayItemState",
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

	public List<InboundRePutAwayItemState> findByExample(
			InboundRePutAwayItemState instance) {
		log.debug("finding InboundRePutAwayItemState instance by example");
		try {
			List<InboundRePutAwayItemState> results = (List<InboundRePutAwayItemState>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.intelitune.nwms.model.InboundRePutAwayItemState")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
