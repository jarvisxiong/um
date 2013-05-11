package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Owner;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Owner.
 * 
 * @see com.intelitune.nwms.model.Owner
 * @author Hibernate Tools
 */
public class OwnerHome {
	private final static OwnerHome instance = new OwnerHome();

	private OwnerHome() {
	}

	public static final OwnerHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(OwnerHome.class);

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

	public void persist(Owner transientInstance) {
		log.debug("persisting Owner instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Owner instance) {
		log.debug("attaching dirty Owner instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Owner instance) {
		log.debug("attaching clean Owner instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Owner persistentInstance) {
		log.debug("deleting Owner instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Owner merge(Owner detachedInstance) {
		log.debug("merging Owner instance");
		try {
			Owner result = (Owner) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Owner findById(java.lang.String id) {
		log.debug("getting Owner instance with id: " + id);
		try {
			Owner instance = (Owner) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Owner", id);
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

	public List<Owner> findByExample(Owner instance) {
		log.debug("finding Owner instance by example");
		try {
			List<Owner> results = (List<Owner>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Owner").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
