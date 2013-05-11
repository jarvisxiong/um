package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.MaterialState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class MaterialState.
 * 
 * @see com.intelitune.nwms.model.MaterialState
 * @author Hibernate Tools
 */
public class MaterialStateHome {
	private final static MaterialStateHome instance = new MaterialStateHome();

	private MaterialStateHome() {
	}

	public static final MaterialStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(MaterialStateHome.class);

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

	public void persist(MaterialState transientInstance) {
		log.debug("persisting MaterialState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialState instance) {
		log.debug("attaching dirty MaterialState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialState instance) {
		log.debug("attaching clean MaterialState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(MaterialState persistentInstance) {
		log.debug("deleting MaterialState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialState merge(MaterialState detachedInstance) {
		log.debug("merging MaterialState instance");
		try {
			MaterialState result = (MaterialState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MaterialState findById(java.lang.String id) {
		log.debug("getting MaterialState instance with id: " + id);
		try {
			MaterialState instance = (MaterialState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.MaterialState", id);
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

	public List<MaterialState> findByExample(MaterialState instance) {
		log.debug("finding MaterialState instance by example");
		try {
			List<MaterialState> results = (List<MaterialState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.MaterialState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<MaterialState> findAll() {
		log.debug("finding all MaterialState");
		try {
			List<MaterialState> results = (List<MaterialState>) sessionFactory
					.getCurrentSession().createQuery(
							" from MaterialState order by code").list();
			log.debug("find all sucessful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void updateMaterialState(MaterialState materialState) {
		log.debug("update MaterialState");
		try {
			sessionFactory.getCurrentSession().update(materialState);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update filed", re);
			throw re;
		}
	}

	public MaterialState getMaterialState(String code) {
		List list = sessionFactory.getCurrentSession().createQuery(
				" from MaterialState i where i.code='" + code + "'").list();
		if (list.size() != 0) {
			return (MaterialState) list.get(0);
		}
		return null;
	}

	public MaterialState findMaterialState(String id) {
		return (MaterialState) sessionFactory.getCurrentSession().get(
				MaterialState.class, id);
	}
}
