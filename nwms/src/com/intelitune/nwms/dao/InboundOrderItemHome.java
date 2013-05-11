package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InboundOrderItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InboundOrderItem.
 * 
 * @see com.intelitune.nwms.model.InboundOrderItem
 * @author Hibernate Tools
 */
public class InboundOrderItemHome {
	private final static InboundOrderItemHome instance = new InboundOrderItemHome();

	private InboundOrderItemHome() {
	}

	public static final InboundOrderItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(InboundOrderItemHome.class);

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

	public void persist(InboundOrderItem transientInstance) {
		log.debug("persisting InboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InboundOrderItem instance) {
		log.debug("attaching dirty InboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InboundOrderItem instance) {
		log.debug("attaching clean InboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InboundOrderItem persistentInstance) {
		log.debug("deleting InboundOrderItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InboundOrderItem merge(InboundOrderItem detachedInstance) {
		log.debug("merging InboundOrderItem instance");
		try {
			InboundOrderItem result = (InboundOrderItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InboundOrderItem findById(java.lang.String id) {
		log.debug("getting InboundOrderItem instance with id: " + id);
		try {
			InboundOrderItem instance = (InboundOrderItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InboundOrderItem", id);
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

	public List<InboundOrderItem> findByExample(InboundOrderItem instance) {
		log.debug("finding InboundOrderItem instance by example");
		try {
			List<InboundOrderItem> results = (List<InboundOrderItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InboundOrderItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<InboundOrderItem> findByHql(String hql) {
		return (List<InboundOrderItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
