package com.intelitune.nwms.dao;

// Generated 2009-4-17 15:08:00 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.OutboundAssigned;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OutboundAssigned.
 * @see com.intelitune.nwms.model.OutboundAssigned
 * @author Hibernate Tools
 */
public class OutboundAssignedHome {
	
	private final static OutboundAssignedHome instance = new OutboundAssignedHome();

	private OutboundAssignedHome() {
	}

	public static final OutboundAssignedHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory
			.getLog(OutboundAssignedHome.class);

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

	public void persist(OutboundAssigned transientInstance) {
		log.debug("persisting OutboundAssigned instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OutboundAssigned instance) {
		log.debug("attaching dirty OutboundAssigned instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OutboundAssigned instance) {
		log.debug("attaching clean OutboundAssigned instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OutboundAssigned persistentInstance) {
		log.debug("deleting OutboundAssigned instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OutboundAssigned merge(OutboundAssigned detachedInstance) {
		log.debug("merging OutboundAssigned instance");
		try {
			OutboundAssigned result = (OutboundAssigned) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OutboundAssigned findById(java.lang.String id) {
		log.debug("getting OutboundAssigned instance with id: " + id);
		try {
			OutboundAssigned instance = (OutboundAssigned) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.OutboundAssigned", id);
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

	public List<OutboundAssigned> findByExample(OutboundAssigned instance) {
		log.debug("finding OutboundAssigned instance by example");
		try {
			List<OutboundAssigned> results = (List<OutboundAssigned>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.OutboundAssigned").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public void queryStr(String hql){
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	public List<OutboundAssigned> findAssignedByOrderId(String orderId){
		String hql="from OutboundAssigned as o where o.ooi.orderId='"+orderId+"' and o.isDelete=0 and o.si.qty>0";
		List<OutboundAssigned> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	public List<OutboundAssigned> findAssignedByOrderIdAndProductCode(String orderId,String productcode){
		String hql="from OutboundAssigned as o where o.ooi.orderId='"+orderId+"' and o.ooi.material.code='"+productcode+"' and o.isDelete=0";
		List<OutboundAssigned> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	public List<OutboundAssigned> findByOrderId(String order_id){
		String hql="from OutboundAssigned as o where o.ooi.orderId='"+order_id+"' and o.isDelete=0";
		List<OutboundAssigned> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	public List<OutboundAssigned> findByItemAndOrder(String order_id,
			String item_id) {
		String hql="from OutboundAssigned as o where o.ooi.orderId='"+order_id+"' and o.ooi.id='"+item_id+"' and o.isDelete=0";
		List<OutboundAssigned> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
