package com.intelitune.nwms.dao;

// Generated 2009-4-23 18:24:07 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.InttRole;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class InttRole.
 * @see com.intelitune.nwms.model.InttRole
 * @author Hibernate Tools
 */
public class InttRoleHome {
	
	private final static InttRoleHome instance = new InttRoleHome();

	private InttRoleHome() {
	}

	public static final InttRoleHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(InttRoleHome.class);

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

	public void persist(InttRole transientInstance) {
		log.debug("persisting InttRole instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InttRole instance) {
		log.debug("attaching dirty InttRole instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InttRole instance) {
		log.debug("attaching clean InttRole instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InttRole persistentInstance) {
		log.debug("deleting InttRole instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InttRole merge(InttRole detachedInstance) {
		log.debug("merging InttRole instance");
		try {
			InttRole result = (InttRole) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InttRole findById(java.lang.Integer id) {
		log.debug("getting InttRole instance with id: " + id);
		try {
			InttRole instance = (InttRole) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.InttRole", id);
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

	public List<InttRole> findByExample(InttRole instance) {
		log.debug("finding InttRole instance by example");
		try {
			List<InttRole> results = (List<InttRole>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.InttRole").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<InttRole> findAll(){
		String hql="from InttRole";
		List<InttRole> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	public List<InttRole> findByquery(String hql){
		List<InttRole> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	} 
}
