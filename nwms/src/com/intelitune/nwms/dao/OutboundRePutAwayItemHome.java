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
import com.intelitune.nwms.model.OutboundRePutAwayItem;

/**
 * Home object for domain model class OutboundRePutAwayItem.
 * 
 * @see com.intelitune.nwms.model.OutboundRePutAwayItem
 * @author Hibernate Tools
 */
public class OutboundRePutAwayItemHome {
	private final static OutboundRePutAwayItemHome instance = new OutboundRePutAwayItemHome();

	private OutboundRePutAwayItemHome() {
	}

	public static final OutboundRePutAwayItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(OutboundRePutAwayItemHome.class);

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

	public void persist(OutboundRePutAwayItem transientInstance) {
		log.debug("persisting OutboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundRePutAwayItem instance) {
		log.debug("attaching dirty OutboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundRePutAwayItem instance) {
		log.debug("attaching clean OutboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundRePutAwayItem persistentInstance) {
		log.debug("deleting OutboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundRePutAwayItem merge(OutboundRePutAwayItem detachedInstance) {
		log.debug("merging OutboundRePutAwayItem instance");
		try {
			OutboundRePutAwayItem result = (OutboundRePutAwayItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundRePutAwayItem findById(java.lang.String id) {
		log.debug("getting OutboundRePutAwayItem instance with id: " + id);
		try {
			OutboundRePutAwayItem instance = (OutboundRePutAwayItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OutboundRePutAwayItem",
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

	public List<OutboundRePutAwayItem> findByExample(
			OutboundRePutAwayItem instance) {
		log.debug("finding OutboundRePutAwayItem instance by example");
		try {
			List<OutboundRePutAwayItem> results = (List<OutboundRePutAwayItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OutboundRePutAwayItem")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<OutboundRePutAwayItem> findOutboundRePutAwayItemByTempJobId(
			String jobId) {
		List<OutboundRePutAwayItem> list = new ArrayList<OutboundRePutAwayItem>();
		String hql = "from OutboundRePutAwayItem as o where o.tempJobId='"
				+ jobId + "' and o.state.code='" + ItemState.NORMAL + "'";
		list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	public List<OutboundRePutAwayItem> findOutboundRePutAwayItemByJobId(
			String jobId) {
		List<OutboundRePutAwayItem> list = new ArrayList<OutboundRePutAwayItem>();
		String hql = "from OutboundRePutAwayItem as o where o.jobId='" + jobId
				+ "' and o.state.code='" + ItemState.NORMAL + "'";
		
		System.out.println(hql);
		
		list = sessionFactory.getCurrentSession().createQuery(hql).list();
		
		System.out.println(list.size());
		return list;
	}

}
