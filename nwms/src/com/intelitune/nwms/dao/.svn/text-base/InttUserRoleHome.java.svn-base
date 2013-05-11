package com.intelitune.nwms.dao;

// Generated 2009-4-23 18:24:07 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InttUserRole;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InttUserRole.
 * @see com.intelitune.nwms.model.InttUserRole
 * @author Hibernate Tools
 */
public class InttUserRoleHome {

	private final static InttUserRoleHome instance = new InttUserRoleHome();

	private InttUserRoleHome() {
	}

	public static final InttUserRoleHome getInstance() {
		return instance;
	}
	
	private static final Log log = LogFactory.getLog(InttUserRoleHome.class);

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

	public void persist(InttUserRole transientInstance) {
		log.debug("persisting InttUserRole instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InttUserRole instance) {
		log.debug("attaching dirty InttUserRole instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InttUserRole instance) {
		log.debug("attaching clean InttUserRole instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InttUserRole persistentInstance) {
		log.debug("deleting InttUserRole instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InttUserRole merge(InttUserRole detachedInstance) {
		log.debug("merging InttUserRole instance");
		try {
			InttUserRole result = (InttUserRole) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InttUserRole findById(java.lang.Integer id) {
		log.debug("getting InttUserRole instance with id: " + id);
		try {
			InttUserRole instance = (InttUserRole) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.InttUserRole", id);
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

	public List<InttUserRole> findByExample(InttUserRole instance) {
		log.debug("finding InttUserRole instance by example");
		try {
			List<InttUserRole> results = (List<InttUserRole>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InttUserRole").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<InttUserRole> findByQuery(String hql){
		List<InttUserRole> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	
}
