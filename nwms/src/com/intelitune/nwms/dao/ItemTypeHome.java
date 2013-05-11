package com.intelitune.nwms.dao;

// Generated 2009-4-2 11:17:56 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.ItemTypeState;

/**
 * Home object for domain model class ItemType.
 * @see com.intelitune.nwms.model.ItemType
 * @author Hibernate Tools
 */
public class ItemTypeHome {
//	public ItemTypeStateService itss=ItemTypeStateServiceImp.getInstance();
	
	private final static ItemTypeHome instance = new ItemTypeHome();

	private ItemTypeHome() {
	}

	public static final ItemTypeHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(ItemTypeHome.class);

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

	public void persist(ItemType transientInstance) {
		log.debug("persisting ItemType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ItemType instance) {
		log.debug("attaching dirty ItemType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItemType instance) {
		log.debug("attaching clean ItemType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ItemType persistentInstance) {
		log.debug("deleting ItemType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ItemType merge(ItemType detachedInstance) {
		log.debug("merging ItemType instance");
		try {
			ItemType result = (ItemType) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemType findById(java.lang.String id) {
		log.debug("getting ItemType instance with id: " + id);
		try {
			ItemType instance = (ItemType) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.ItemType", id);
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

	public List<ItemType> findByExample(ItemType instance) {
		log.debug("finding ItemType instance by example");
		try {
			List<ItemType> results = (List<ItemType>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.ItemType").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<ItemType> findItemTypeList(){
		String hql="from ItemType as i where i.state.code='"+ItemTypeState.NORMAL+"'";
		List<ItemType> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
