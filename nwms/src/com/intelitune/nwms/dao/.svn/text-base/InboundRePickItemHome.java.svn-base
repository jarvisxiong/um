package com.intelitune.nwms.dao;

// Generated 2009-2-25 18:17:59 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InboundRePickItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InboundRePickItem.
 * 
 * @see com.intelitune.nwms.model.InboundRePickItem
 * @author Hibernate Tools
 */
public class InboundRePickItemHome {
	private final static InboundRePickItemHome instance = new InboundRePickItemHome();

	private InboundRePickItemHome() {
	}

	public static final InboundRePickItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(InboundRePickItemHome.class);

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

	public void persist(InboundRePickItem transientInstance) {
		log.debug("persisting InboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InboundRePickItem instance) {
		log.debug("attaching dirty InboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InboundRePickItem instance) {
		log.debug("attaching clean InboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InboundRePickItem persistentInstance) {
		log.debug("deleting InboundRePickItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InboundRePickItem merge(InboundRePickItem detachedInstance) {
		log.debug("merging InboundRePickItem instance");
		try {
			InboundRePickItem result = (InboundRePickItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InboundRePickItem findById(java.lang.String id) {
		log.debug("getting InboundRePickItem instance with id: " + id);
		try {
			InboundRePickItem instance = (InboundRePickItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InboundRePickItem", id);
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

	public List<InboundRePickItem> findByExample(InboundRePickItem instance) {
		log.debug("finding InboundRePickItem instance by example");
		try {
			List<InboundRePickItem> results = (List<InboundRePickItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InboundRePickItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<InboundRePickItem> findByHql(String hql) {
		return (List<InboundRePickItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
