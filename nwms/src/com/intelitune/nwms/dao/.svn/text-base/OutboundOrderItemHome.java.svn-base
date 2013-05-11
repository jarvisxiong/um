package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OutboundOrderItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OutboundOrderItem.
 * 
 * @see com.intelitune.nwms.model.OutboundOrderItem
 * @author Hibernate Tools
 */
public class OutboundOrderItemHome {
	private final static OutboundOrderItemHome instance = new OutboundOrderItemHome();

	private OutboundOrderItemHome() {
	}

	public static final OutboundOrderItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(OutboundOrderItemHome.class);

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

	public void persist(OutboundOrderItem transientInstance) {
		log.debug("persisting OutboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundOrderItem instance) {
		log.debug("attaching dirty OutboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundOrderItem instance) {
		log.debug("attaching clean OutboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundOrderItem persistentInstance) {
		log.debug("deleting OutboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundOrderItem merge(OutboundOrderItem detachedInstance) {
		log.debug("merging OutboundOrderItem instance");
		try {
			OutboundOrderItem result = (OutboundOrderItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundOrderItem findById(java.lang.String id) {
		log.debug("getting OutboundOrderItem instance with id: " + id);
		try {
			OutboundOrderItem instance = (OutboundOrderItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OutboundOrderItem", id);
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

	public List<OutboundOrderItem> findByExample(OutboundOrderItem instance) {
		log.debug("finding OutboundOrderItem instance by example");
		try {
			List<OutboundOrderItem> results = (List<OutboundOrderItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OutboundOrderItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<OutboundOrderItem> findByHql(String hql) {
		return (List<OutboundOrderItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
