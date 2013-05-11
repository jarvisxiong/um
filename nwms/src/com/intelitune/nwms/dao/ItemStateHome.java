package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ItemState.
 * 
 * @see com.intelitune.nwms.model.ItemState
 * @author Hibernate Tools
 */
public class ItemStateHome {
	private final static ItemStateHome instance = new ItemStateHome();

	private ItemStateHome() {
	}

	public static final ItemStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ItemStateHome.class);

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

	public void persist(ItemState transientInstance) {
		log.debug("persisting ItemState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ItemState instance) {
		log.debug("attaching dirty ItemState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItemState instance) {
		log.debug("attaching clean ItemState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ItemState persistentInstance) {
		log.debug("deleting ItemState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ItemState merge(ItemState detachedInstance) {
		log.debug("merging ItemState instance");
		try {
			ItemState result = (ItemState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemState findById(java.lang.String id) {
		log.debug("getting ItemState instance with id: " + id);
		try {
			ItemState instance = (ItemState) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.ItemState", id);
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

	public List<ItemState> findByExample(ItemState instance) {
		log.debug("finding ItemState instance by example");
		try {
			List<ItemState> results = (List<ItemState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.ItemState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ItemState> query(String str) {
		List<ItemState> li = sessionFactory.getCurrentSession()
				.createQuery(str).list();
		return li;
	}

	public ItemState findItemStateByCode(int code) {
		String str = code + "";
		String hql = "from ItemState as i where i.code='" + str + "'";
		ItemState is = (ItemState) sessionFactory.getCurrentSession()
				.createQuery(hql).list().get(0);
		return is;
	}
}
