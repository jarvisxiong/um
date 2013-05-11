package com.intelitune.nwms.dao;

// Generated 2009-3-8 14:35:38 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Invoice;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Invoice.
 * 
 * @see com.intelitune.nwms.model.Invoice
 * @author Hibernate Tools
 */
public class InvoiceHome {
	private final static InvoiceHome instance = new InvoiceHome();

	private InvoiceHome() {
	}

	public static final InvoiceHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(InvoiceHome.class);

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

	public void persist(Invoice transientInstance) {
		log.debug("persisting Invoice instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Invoice instance) {
		log.debug("attaching dirty Invoice instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Invoice instance) {
		log.debug("attaching clean Invoice instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Invoice persistentInstance) {
		log.debug("deleting Invoice instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Invoice merge(Invoice detachedInstance) {
		log.debug("merging Invoice instance");
		try {
			Invoice result = (Invoice) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Invoice findById(java.lang.String id) {
		log.debug("getting Invoice instance with id: " + id);
		try {
			Invoice instance = (Invoice) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.Invoice", id);
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

	public List<Invoice> findByExample(Invoice instance) {
		log.debug("finding Invoice instance by example");
		try {
			List<Invoice> results = (List<Invoice>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Invoice").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Invoice> query(String str) {

		return sessionFactory.getCurrentSession().createQuery(str).list();
	}

	public Invoice findByCode(String code) {
		String hql = "from Invoice as i where i.code='" + code + "'";
		List<Invoice> list = sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
