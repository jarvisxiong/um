package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Material;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Material.
 * 
 * @see com.intelitune.nwms.model.Material
 * @author Hibernate Tools
 */
public class MaterialHome {

	private final static MaterialHome instance = new MaterialHome();

	private MaterialHome() {

	}

	public static final MaterialHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(MaterialHome.class);

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

	public void persist(Material transientInstance) {
		log.debug("persisting Material instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Material instance) {
		log.debug("attaching dirty Material instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Material instance) {
		log.debug("attaching clean Material instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Material persistentInstance) {
		log.debug("deleting Material instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Material merge(Material detachedInstance) {
		log.debug("merging Material instance");
		try {
			Material result = (Material) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Material findById(java.lang.String id) {
		log.debug("getting Material instance with id: " + id);
		try {
			Material instance = (Material) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.Material", id);
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

	public List<Material> findByExample(Material instance) {
		log.debug("finding Material instance by example");
		try {
			List<Material> results = (List<Material>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.Material").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Material> findByHql(String hql) {
		log.debug("finding Material instance by hql");
		try {
			List<Material> results = (List<Material>) sessionFactory
					.getCurrentSession().createQuery(hql).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public void updateMaterial(Material material) {
		log.debug("update Material instance by hql");
		try {
			sessionFactory.getCurrentSession().update(material);
			log.debug("update end");
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

}
