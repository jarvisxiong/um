package com.intelitune.nwms.dao;

// Generated 2009-2-26 13:09:08 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InboundRePickItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InboundRePickItemState.
 * 
 * @see com.intelitune.nwms.model.InboundRePickItemState
 * @author Hibernate Tools
 */
public class InboundRePickItemStateHome {
	private final static InboundRePickItemStateHome instance = new InboundRePickItemStateHome();

	private InboundRePickItemStateHome() {
	}

	public static final InboundRePickItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(InboundRePickItemStateHome.class);

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

	public void persist(InboundRePickItemState transientInstance) {
		log.debug("persisting InboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InboundRePickItemState instance) {
		log.debug("attaching dirty InboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InboundRePickItemState instance) {
		log.debug("attaching clean InboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InboundRePickItemState persistentInstance) {
		log.debug("deleting InboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InboundRePickItemState merge(InboundRePickItemState detachedInstance) {
		log.debug("merging InboundRePickItemState instance");
		try {
			InboundRePickItemState result = (InboundRePickItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InboundRePickItemState findById(java.lang.String id) {
		log.debug("getting InboundRePickItemState instance with id: " + id);
		try {
			InboundRePickItemState instance = (InboundRePickItemState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InboundRePickItemState",
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

	public List<InboundRePickItemState> findByExample(
			InboundRePickItemState instance) {
		log.debug("finding InboundRePickItemState instance by example");
		try {
			List<InboundRePickItemState> results = (List<InboundRePickItemState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InboundRePickItemState")
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
