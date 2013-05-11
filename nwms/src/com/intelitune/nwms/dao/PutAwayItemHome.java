package com.intelitune.nwms.dao;

// Generated 2009-2-27 14:29:08 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.PutAwayItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PutAwayItem.
 * 
 * @see com.intelitune.nwms.model.PutAwayItem
 * @author Hibernate Tools
 */
public class PutAwayItemHome {
	private final static PutAwayItemHome instance = new PutAwayItemHome();

	private PutAwayItemHome() {
	}

	public static final PutAwayItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(PutAwayItemHome.class);

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

	public void persist(PutAwayItem transientInstance) {
		log.debug("persisting PutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PutAwayItem instance) {
		log.debug("attaching dirty PutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PutAwayItem instance) {
		log.debug("attaching clean PutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PutAwayItem persistentInstance) {
		log.debug("deleting PutAwayItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PutAwayItem merge(PutAwayItem detachedInstance) {
		log.debug("merging PutAwayItem instance");
		try {
			PutAwayItem result = (PutAwayItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PutAwayItem findById(java.lang.String id) {
		log.debug("getting PutAwayItem instance with id: " + id);
		try {
			PutAwayItem instance = (PutAwayItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.PutAwayItem", id);
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

	public List<PutAwayItem> findByExample(PutAwayItem instance) {
		log.debug("finding PutAwayItem instance by example");
		try {
			List<PutAwayItem> results = (List<PutAwayItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.PutAwayItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<PutAwayItem> findByHql(String hql) {
		return (List<PutAwayItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
}
