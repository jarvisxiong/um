package com.intelitune.nwms.dao;

// Generated 2009-2-25 18:17:59 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OutboundTxtData;

/**
 * Home object for domain model class OutboundTxtData.
 * 
 * @see com.intelitune.nwms.model.OutboundTxtData
 * @author Hibernate Tools
 */
public class OutboundTxtDataHome {
	private final static OutboundTxtDataHome instance = new OutboundTxtDataHome();

	private OutboundTxtDataHome() {
	}

	public static final OutboundTxtDataHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(OutboundTxtDataHome.class);

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

	public void persist(OutboundTxtData transientInstance) {
		log.debug("persisting OutboundTxtData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundTxtData instance) {
		log.debug("attaching dirty OutboundTxtData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundTxtData instance) {
		log.debug("attaching clean OutboundTxtData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundTxtData persistentInstance) {
		log.debug("deleting OutboundTxtData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundTxtData merge(OutboundTxtData detachedInstance) {
		log.debug("merging OutboundTxtData instance");
		try {
			OutboundTxtData result = (OutboundTxtData) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundTxtData findById(java.lang.String id) {
		log.debug("getting OutboundTxtData instance with id: " + id);
		try {
			OutboundTxtData instance = (OutboundTxtData) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OutboundTxtData", id);
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

	public List<OutboundTxtData> findByExample(OutboundTxtData instance) {
		log.debug("finding OutboundTxtData instance by example");
		try {
			List<OutboundTxtData> results = (List<OutboundTxtData>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OutboundTxtData").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<OutboundTxtData> findOutboundTxtDataByJob_id(String job_id) {
		String hql = "from OutboundTxtData as o where o.orderJobId='" + job_id
				+ "'";
		List<OutboundTxtData> list = sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return list;
	}

}
