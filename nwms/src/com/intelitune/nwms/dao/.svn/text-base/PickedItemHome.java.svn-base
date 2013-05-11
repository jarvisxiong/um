package com.intelitune.nwms.dao;

// Generated 2009-2-27 15:02:02 by Hibernate Tools 3.2.2.GA

import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundRePickItem;
import com.intelitune.nwms.model.PickedItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PickedItem.
 * 
 * @see com.intelitune.nwms.model.PickedItem
 * @author Hibernate Tools
 */
public class PickedItemHome {
	private final static PickedItemHome instance = new PickedItemHome();

	private PickedItemHome() {
	}

	public static final PickedItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(PickedItemHome.class);

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

	public void persist(PickedItem transientInstance) {
		log.debug("persisting PickedItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PickedItem instance) {
		log.debug("attaching dirty PickedItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PickedItem instance) {
		log.debug("attaching clean PickedItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PickedItem persistentInstance) {
		log.debug("deleting PickedItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PickedItem merge(PickedItem detachedInstance) {
		log.debug("merging PickedItem instance");
		try {
			PickedItem result = (PickedItem) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PickedItem findById(java.lang.String id) {
		log.debug("getting PickedItem instance with id: " + id);
		try {
			PickedItem instance = (PickedItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.PickedItem", id);
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

	public List<PickedItem> findByExample(PickedItem instance) {
		log.debug("finding PickedItem instance by example");
		try {
			List<PickedItem> results = (List<PickedItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.PickedItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<PickedItem> findPickedItemByTempJobId(String jobId) {
		List<PickedItem> list = new ArrayList<PickedItem>();
		String hql = "from PickedItem as o where o.tempJobId='" + jobId
				+ "' and o.state.code='" + ItemState.NORMAL + "'";
		list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	public List<PickedItem> findPickedItemByJobId(String jobId) {
		List<PickedItem> list = new ArrayList<PickedItem>();
		String hql = "from PickedItem as o where o.jobId='" + jobId
				+ "' and o.state.code='" + ItemState.NORMAL + "'";
		list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	
	public List<PickedItem> findPickedItemByOrder_id(String order_id){
		List<PickedItem> list=new ArrayList<PickedItem>();
		String hql="from PickedItem as o where o.orderId='"+order_id+"' and o.state.code='"+ItemState.NORMAL+"'";
		list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	

	
}
