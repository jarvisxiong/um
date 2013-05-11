package com.intelitune.nwms.dao;

// Generated 2009-4-23 18:24:07 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InttUser;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InttUser.
 * @see com.intelitune.nwms.model.InttUser
 * @author Hibernate Tools
 */
public class InttUserHome {

	private final static InttUserHome instance = new InttUserHome();

	private InttUserHome() {
	}

	public static final InttUserHome getInstance() {
		return instance;
	}
	
	private static final Log log = LogFactory.getLog(InttUserHome.class);

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

	public void persist(InttUser transientInstance) {
		log.debug("persisting InttUser instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InttUser instance) {
		log.debug("attaching dirty InttUser instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InttUser instance) {
		log.debug("attaching clean InttUser instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InttUser persistentInstance) {
		log.debug("deleting InttUser instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InttUser merge(InttUser detachedInstance) {
		log.debug("merging InttUser instance");
		try {
			InttUser result = (InttUser) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InttUser findById(java.lang.Integer id) {
		log.debug("getting InttUser instance with id: " + id);
		try {
			InttUser instance = (InttUser) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.InttUser", id);
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

	public List<InttUser> findByExample(InttUser instance) {
		log.debug("finding InttUser instance by example");
		try {
			List<InttUser> results = (List<InttUser>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InttUser").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<InttUser> findByQuery(String hql){
		List<InttUser> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
