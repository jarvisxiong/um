package com.intelitune.nwms.dao;

// Generated 2009-2-25 18:17:59 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InboundRePutAwayItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InboundRePutAwayItem.
 * 
 * @see com.intelitune.nwms.model.InboundRePutAwayItem
 * @author Hibernate Tools
 */
public class InboundRePutAwayItemHome {
	private final static InboundRePutAwayItemHome instance = new InboundRePutAwayItemHome();

	private InboundRePutAwayItemHome() {
	}

	public static final InboundRePutAwayItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(InboundRePutAwayItemHome.class);

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

	public void persist(InboundRePutAwayItem transientInstance) {
		log.debug("persisting InboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InboundRePutAwayItem instance) {
		log.debug("attaching dirty InboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InboundRePutAwayItem instance) {
		log.debug("attaching clean InboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InboundRePutAwayItem persistentInstance) {
		log.debug("deleting InboundRePutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InboundRePutAwayItem merge(InboundRePutAwayItem detachedInstance) {
		log.debug("merging InboundRePutAwayItem instance");
		try {
			InboundRePutAwayItem result = (InboundRePutAwayItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InboundRePutAwayItem findById(java.lang.String id) {
		log.debug("getting InboundRePutAwayItem instance with id: " + id);
		try {
			InboundRePutAwayItem instance = (InboundRePutAwayItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InboundRePutAwayItem",
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

	public List<InboundRePutAwayItem> findByExample(
			InboundRePutAwayItem instance) {
		log.debug("finding InboundRePutAwayItem instance by example");
		try {
			List<InboundRePutAwayItem> results = (List<InboundRePutAwayItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InboundRePutAwayItem")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<InboundRePutAwayItem> findByHql(String hql) {
		return (List<InboundRePutAwayItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
