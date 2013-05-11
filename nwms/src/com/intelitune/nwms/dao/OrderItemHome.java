package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OrderItem;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OrderItem.
 * 
 * @see com.intelitune.nwms.model.OrderItem
 * @author Hibernate Tools
 */
public class OrderItemHome {
	private final static OrderItemHome instance = new OrderItemHome();

	private OrderItemHome() {
	}

	public static final OrderItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(OrderItemHome.class);

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

	public void persist(OrderItem transientInstance) {
		log.debug("persisting OrderItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OrderItem instance) {
		log.debug("attaching dirty OrderItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OrderItem instance) {
		log.debug("attaching clean OrderItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OrderItem persistentInstance) {
		log.debug("deleting OrderItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrderItem merge(OrderItem detachedInstance) {
		log.debug("merging OrderItem instance");
		try {
			OrderItem result = (OrderItem) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderItem findById(java.lang.String id) {
		log.debug("getting OrderItem instance with id: " + id);
		try {
			OrderItem instance = (OrderItem) sessionFactory.getCurrentSession()
					.get("com.intelitune.nwms.model.OrderItem", id);
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

	public List<OrderItem> findByExample(OrderItem instance) {
		log.debug("finding OrderItem instance by example");
		try {
			List<OrderItem> results = (List<OrderItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OrderItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List query(String str) {
		log.debug("finding obj instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createQuery(str)
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public List<OrderItem> findOrderItemByOrder(String str) {
		log.debug("finding obj instance by example");
		try {
			List<OrderItem> results = sessionFactory.getCurrentSession().createQuery(str)
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<OrderItem> findByHql(String hql) {
		return (List<OrderItem>) sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}

	public int findItemNumByOrderId(String order_id) {
		String hql = "select count(*) from  OrderItem o where  o.orderId='"
				+ order_id + "'  and  o.state.code='"
				+ ItemState.NORMAL.toString() + "'";
		String m = sessionFactory.getCurrentSession().createQuery(hql).list()
				.get(0).toString();
		return Integer.parseInt(m);
	}
	

}
