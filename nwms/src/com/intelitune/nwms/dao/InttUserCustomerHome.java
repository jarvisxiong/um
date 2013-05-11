package com.intelitune.nwms.dao;

// Generated 2009-4-23 18:24:07 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InttUserCustomer;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InttUserCustomer.
 * @see com.intelitune.nwms.model.InttUserCustomer
 * @author Hibernate Tools
 */
public class InttUserCustomerHome {

	private final static InttUserCustomerHome instance = new InttUserCustomerHome();

	private InttUserCustomerHome() {
	}

	public static final InttUserCustomerHome getInstance() {
		return instance;
	}
	
	private static final Log log = LogFactory
			.getLog(InttUserCustomerHome.class);

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

	public void persist(InttUserCustomer transientInstance) {
		log.debug("persisting InttUserCustomer instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InttUserCustomer instance) {
		log.debug("attaching dirty InttUserCustomer instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InttUserCustomer instance) {
		log.debug("attaching clean InttUserCustomer instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InttUserCustomer persistentInstance) {
		log.debug("deleting InttUserCustomer instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InttUserCustomer merge(InttUserCustomer detachedInstance) {
		log.debug("merging InttUserCustomer instance");
		try {
			InttUserCustomer result = (InttUserCustomer) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InttUserCustomer findById(java.lang.Integer id) {
		log.debug("getting InttUserCustomer instance with id: " + id);
		try {
			InttUserCustomer instance = (InttUserCustomer) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InttUserCustomer", id);
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

	public List<InttUserCustomer> findByExample(InttUserCustomer instance) {
		log.debug("finding InttUserCustomer instance by example");
		try {
			List<InttUserCustomer> results = (List<InttUserCustomer>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InttUserCustomer").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<InttUserCustomer> findByQuery(String hql){
		List<InttUserCustomer> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
