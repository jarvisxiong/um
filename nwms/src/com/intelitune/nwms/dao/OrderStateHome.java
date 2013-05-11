package com.intelitune.nwms.dao;

// Generated 2008-12-31 13:55:49 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OrderState;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OrderState.
 * 
 * @see com.intelitune.nwms.model.OrderState
 * @author Hibernate Tools
 */
public class OrderStateHome {
	private final static OrderStateHome instance = new OrderStateHome();

	private OrderStateHome() {
	}

	public static final OrderStateHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(OrderStateHome.class);

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

	public void persist(OrderState transientInstance) {
		log.debug("persisting OrderState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OrderState instance) {
		log.debug("attaching dirty OrderState instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OrderState instance) {
		log.debug("attaching clean OrderState instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OrderState persistentInstance) {
		log.debug("deleting OrderState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrderState merge(OrderState detachedInstance) {
		log.debug("merging OrderState instance");
		try {
			OrderState result = (OrderState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderState findById(java.lang.String id) {
		log.debug("getting OrderState instance with id: " + id);
		try {
			OrderState instance = (OrderState) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OrderState", id);
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

	public List<OrderState> findByExample(OrderState instance) {
		log.debug("finding OrderState instance by example");
		try {
			List<OrderState> results = (List<OrderState>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OrderState").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
