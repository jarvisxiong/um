package com.intelitune.nwms.dao;

// Generated 2009-3-27 12:22:16 by Hibernate Tools 3.2.2.GA

import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.LogPickedItem;
import com.intelitune.nwms.model.LogStorageItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class LogStorageItem.
 * 
 * @see com.intelitune.nwms.model.LogStorageItem
 * @author Hibernate Tools
 */
public class LogStorageItemHome {
	private final static LogStorageItemHome instance = new LogStorageItemHome();

	private LogStorageItemHome() {
	}

	public static final LogStorageItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(LogStorageItemHome.class);

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

	public void persist(LogStorageItem transientInstance) {
		log.debug("persisting LogStorageItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(LogStorageItem instance) {
		log.debug("attaching dirty LogStorageItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LogStorageItem instance) {
		log.debug("attaching clean LogStorageItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(LogStorageItem persistentInstance) {
		log.debug("deleting LogStorageItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LogStorageItem merge(LogStorageItem detachedInstance) {
		log.debug("merging LogStorageItem instance");
		try {
			LogStorageItem result = (LogStorageItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LogStorageItem findById(java.lang.String id) {
		log.debug("getting LogStorageItem instance with id: " + id);
		try {
			LogStorageItem instance = (LogStorageItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.LogStorageItem", id);
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

	public List<LogStorageItem> findByExample(LogStorageItem instance) {
		log.debug("finding LogStorageItem instance by example");
		try {
			List<LogStorageItem> results = (List<LogStorageItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.LogStorageItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<LogStorageItem> findLogStorageItemByWarehouseAndClient(String owner_id,String warehouse_id,String date1,String date2){
		String hql="from LogStorageItem as lpi where lpi.material.inttClientDetailWSId='"+owner_id+"' and lpi.warehouse.id='"+warehouse_id+"' and lpi.state.code='"+ItemState.NORMAL+"' ";
				hql+="and lpi.creationTime>=to_date('"+date1+"','YY-MM-DD') and lpi.creationTime<=to_date('"+date2+"','YY-MM-DD') order by lpi.jobId";
		List<LogStorageItem> list=new ArrayList<LogStorageItem>();
		list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	
}
