package com.intelitune.nwms.dao;

// Generated 2009-2-26 13:09:08 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ColumnState;
import com.intelitune.nwms.model.OutboundRePickItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OutboundRePickItemState.
 * 
 * @see com.intelitune.nwms.model.OutboundRePickItemState
 * @author Hibernate Tools
 */
public class OutboundRePickItemStateHome {
	private final static OutboundRePickItemStateHome instance = new OutboundRePickItemStateHome();

	private OutboundRePickItemStateHome() {
	}

	public static final OutboundRePickItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(OutboundRePickItemStateHome.class);

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

	public void persist(OutboundRePickItemState transientInstance) {
		log.debug("persisting OutboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundRePickItemState instance) {
		log.debug("attaching dirty OutboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundRePickItemState instance) {
		log.debug("attaching clean OutboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundRePickItemState persistentInstance) {
		log.debug("deleting OutboundRePickItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundRePickItemState merge(
			OutboundRePickItemState detachedInstance) {
		log.debug("merging OutboundRePickItemState instance");
		try {
			OutboundRePickItemState result = (OutboundRePickItemState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundRePickItemState findById(java.lang.String id) {
		log.debug("getting OutboundRePickItemState instance with id: " + id);
		try {
			OutboundRePickItemState instance = (OutboundRePickItemState) sessionFactory
					.getCurrentSession()
					.get("com.intelitune.nwms.model.OutboundRePickItemState",
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

	public List<OutboundRePickItemState> findByExample(
			OutboundRePickItemState instance) {
		log.debug("finding OutboundRePickItemState instance by example");
		try {
			List<OutboundRePickItemState> results = (List<OutboundRePickItemState>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.intelitune.nwms.model.OutboundRePickItemState")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public OutboundRePickItemState findStateByCode(int code) {
		String str = code + "";
		String hql = "from OutboundRePickItemState as o where o.code='" + str
				+ "'";
		OutboundRePickItemState os = (OutboundRePickItemState) sessionFactory
				.getCurrentSession().createQuery(hql).list().get(0);
		return os;
	}
}
