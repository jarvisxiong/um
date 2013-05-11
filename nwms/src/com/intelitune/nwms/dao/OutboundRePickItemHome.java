package com.intelitune.nwms.dao;

// Generated 2009-2-25 18:17:59 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundRePickItem;

/**
 * Home object for domain model class OutboundRePickItem.
 * 
 * @see com.intelitune.nwms.model.OutboundRePickItem
 * @author Hibernate Tools
 */
public class OutboundRePickItemHome {
	private final static OutboundRePickItemHome instance = new OutboundRePickItemHome();

	private OutboundRePickItemHome() {
	}

	public static final OutboundRePickItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(OutboundRePickItemHome.class);

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

	public void persist(OutboundRePickItem transientInstance) {
		log.debug("persisting OutboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundRePickItem instance) {
		log.debug("attaching dirty OutboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundRePickItem instance) {
		log.debug("attaching clean OutboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundRePickItem persistentInstance) {
		log.debug("deleting OutboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundRePickItem merge(OutboundRePickItem detachedInstance) {
		log.debug("merging OutboundRePickItem instance");
		try {
			OutboundRePickItem result = (OutboundRePickItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundRePickItem findById(java.lang.String id) {
		log.debug("getting OutboundRePickItem instance with id: " + id);
		try {
			OutboundRePickItem instance = (OutboundRePickItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OutboundRePickItem", id);
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

	public List<OutboundRePickItem> findByExample(OutboundRePickItem instance) {
		log.debug("finding OutboundRePickItem instance by example");
		try {
			List<OutboundRePickItem> results = (List<OutboundRePickItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OutboundRePickItem")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<OutboundRePickItem> findOutboundRePickItemByTempJobId(
			String jobId) {
		List<OutboundRePickItem> list = new ArrayList<OutboundRePickItem>();
		String hql = "from OutboundRePickItem as o where o.tempJobId='" + jobId
				+ "' and o.state.code='" + ItemState.NORMAL + "'";
		list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	public List<OutboundRePickItem> findOutboundRePickItemByJobId(String jobId) {
		List<OutboundRePickItem> list = new ArrayList<OutboundRePickItem>();
		String hql = "from OutboundRePickItem as o where o.jobId='" + jobId
				+ "' and o.state.code='" + ItemState.NORMAL + "'";
		list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

}
