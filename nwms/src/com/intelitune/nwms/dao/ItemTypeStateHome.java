package com.intelitune.nwms.dao;

// Generated 2009-4-2 11:17:56 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemTypeState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ItemTypeState.
 * @see com.intelitune.nwms.model.ItemTypeState
 * @author Hibernate Tools
 */
public class ItemTypeStateHome {
	
	private final static ItemTypeStateHome instance = new ItemTypeStateHome();

	private ItemTypeStateHome() {
	}

	public static final ItemTypeStateHome getInstance() {
		return instance;
	}


	private static final Log log = LogFactory.getLog(ItemTypeStateHome.class);

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

	public void persist(ItemTypeState transientInstance) {
		log.debug("persisting ItemTypeState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ItemTypeState instance) {
		log.debug("attaching dirty ItemTypeState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItemTypeState instance) {
		log.debug("attaching clean ItemTypeState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ItemTypeState persistentInstance) {
		log.debug("deleting ItemTypeState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ItemTypeState merge(ItemTypeState detachedInstance) {
		log.debug("merging ItemTypeState instance");
		try {
			ItemTypeState result = (ItemTypeState) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemTypeState findById(java.lang.String id) {
		log.debug("getting ItemTypeState instance with id: " + id);
		try {
			ItemTypeState instance = (ItemTypeState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.ItemTypeState", id);
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

	public List<ItemTypeState> findByExample(ItemTypeState instance) {
		log.debug("finding ItemTypeState instance by example");
		try {
			List<ItemTypeState> results = (List<ItemTypeState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.ItemTypeState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public ItemTypeState findItemTypeStateByCode(int code){
		String str=code+"";
		String hql="from ItemTypeState as i where i.code='"+str+"'";
		ItemTypeState its=(ItemTypeState)sessionFactory.getCurrentSession().createQuery(hql).list().get(0);
		return its;
	}
}