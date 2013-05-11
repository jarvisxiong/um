package com.intelitune.nwms.dao;

// Generated 2009-4-24 16:07:29 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.LogStockMoveItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class LogStockMoveItem.
 * @see com.intelitune.nwms.model.LogStockMoveItem
 * @author Hibernate Tools
 */
public class LogStockMoveItemHome {

	
	private final static LogStockMoveItemHome instance = new LogStockMoveItemHome();

	private LogStockMoveItemHome() {
	}

	public static final LogStockMoveItemHome getInstance() {
		return instance;
	}
	
	private static final Log log = LogFactory
			.getLog(LogStockMoveItemHome.class);

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

	public void persist(LogStockMoveItem transientInstance) {
		log.debug("persisting LogStockMoveItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(LogStockMoveItem instance) {
		log.debug("attaching dirty LogStockMoveItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LogStockMoveItem instance) {
		log.debug("attaching clean LogStockMoveItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(LogStockMoveItem persistentInstance) {
		log.debug("deleting LogStockMoveItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LogStockMoveItem merge(LogStockMoveItem detachedInstance) {
		log.debug("merging LogStockMoveItem instance");
		try {
			LogStockMoveItem result = (LogStockMoveItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LogStockMoveItem findById(java.lang.String id) {
		log.debug("getting LogStockMoveItem instance with id: " + id);
		try {
			LogStockMoveItem instance = (LogStockMoveItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.LogStockMoveItem", id);
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

	public List<LogStockMoveItem> findByExample(LogStockMoveItem instance) {
		log.debug("finding LogStockMoveItem instance by example");
		try {
			List<LogStockMoveItem> results = (List<LogStockMoveItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.LogStockMoveItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<LogStockMoveItem> findLogByWarehouseAndOwnerAndDate(
			String owner_id, String warehouse_id, String date1, String date2){
		String hql="from LogStockMoveItem as l where l.formStorageItem.material.inttClientDetailWSId='"+owner_id+"' and l.formStorageItem.warehouse.id='"+warehouse_id+"'";
		hql+=" and l.creationTime>=to_date('"+date1+"','YY-MM-DD') and l.creationTime<=to_date('"+date2+"','YY-MM-DD')";
		List<LogStockMoveItem> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
		
	}
	
}
