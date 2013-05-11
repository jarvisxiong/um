package com.intelitune.nwms.dao;

// Generated 2009-3-8 14:35:38 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InvoiceState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InvoiceState.
 * 
 * @see com.intelitune.nwms.model.InvoiceState
 * @author Hibernate Tools
 */
public class InvoiceStateHome {
	private final static InvoiceStateHome instance = new InvoiceStateHome();

	private InvoiceStateHome() {
	}

	public static final InvoiceStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(InvoiceStateHome.class);

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

	public void persist(InvoiceState transientInstance) {
		log.debug("persisting InvoiceState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InvoiceState instance) {
		log.debug("attaching dirty InvoiceState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvoiceState instance) {
		log.debug("attaching clean InvoiceState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InvoiceState persistentInstance) {
		log.debug("deleting InvoiceState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvoiceState merge(InvoiceState detachedInstance) {
		log.debug("merging InvoiceState instance");
		try {
			InvoiceState result = (InvoiceState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InvoiceState findById(java.lang.String id) {
		log.debug("getting InvoiceState instance with id: " + id);
		try {
			InvoiceState instance = (InvoiceState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InvoiceState", id);
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

	public List<InvoiceState> findByExample(InvoiceState instance) {
		log.debug("finding InvoiceState instance by example");
		try {
			List<InvoiceState> results = (List<InvoiceState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InvoiceState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<InvoiceState> query(String str) {
		return sessionFactory.getCurrentSession().createQuery(str).list();
	}
}
