package com.intelitune.nwms.dao;

// Generated 2009-3-27 12:22:16 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.LogPickedItem;

/**
 * Home object for domain model class LogPickedItem.
 * 
 * @see com.intelitune.nwms.model.LogPickedItem
 * @author Hibernate Tools
 */
public class LogPickedItemHome {
	private final static LogPickedItemHome instance = new LogPickedItemHome();

	private LogPickedItemHome() {
	}

	public static final LogPickedItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(LogPickedItemHome.class);

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

	public void persist(LogPickedItem transientInstance) {
		log.debug("persisting LogPickedItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(LogPickedItem instance) {
		log.debug("attaching dirty LogPickedItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LogPickedItem instance) {
		log.debug("attaching clean LogPickedItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(LogPickedItem persistentInstance) {
		log.debug("deleting LogPickedItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LogPickedItem merge(LogPickedItem detachedInstance) {
		log.debug("merging LogPickedItem instance");
		try {
			LogPickedItem result = (LogPickedItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LogPickedItem findById(java.lang.String id) {
		log.debug("getting LogPickedItem instance with id: " + id);
		try {
			LogPickedItem instance = (LogPickedItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.LogPickedItem", id);
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

	public List<LogPickedItem> findByExample(LogPickedItem instance) {
		log.debug("finding LogPickedItem instance by example");
		try {
			List<LogPickedItem> results = (List<LogPickedItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.LogPickedItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<LogPickedItem> findLogPickedItemByWarehouseAndClient(String owner_id,String warehouse_id,String date1,String date2){
		String hql="from LogPickedItem as lpi where lpi.material.inttClientDetailWSId='"+owner_id+"' and lpi.warehouse.id='"+warehouse_id+"' and lpi.state.code='"+ItemState.NORMAL+"' and lpi.creationTime>=to_date('"+date1+"','YY-MM-DD') and lpi.creationTime<=to_date('"+date2+"','YY-MM-DD') order by lpi.jobId";
		List<LogPickedItem> list=new ArrayList<LogPickedItem>();
		list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
