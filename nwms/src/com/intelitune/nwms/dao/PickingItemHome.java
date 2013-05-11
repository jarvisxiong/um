package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.PickingItem;

/**
 * Home object for domain model class PickingItem.
 * 
 * @see com.intelitune.nwms.model.PickingItem
 * @author Hibernate Tools
 */
public class PickingItemHome {
	private final static PickingItemHome instance = new PickingItemHome();

	private PickingItemHome() {
	}

	public static final PickingItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(PickingItemHome.class);

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

	public void persist(PickingItem transientInstance) {
		log.debug("persisting PickingItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PickingItem instance) {
		log.debug("attaching dirty PickingItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PickingItem instance) {
		log.debug("attaching clean PickingItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PickingItem persistentInstance) {
		log.debug("deleting PickingItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PickingItem merge(PickingItem detachedInstance) {
		log.debug("merging PickingItem instance");
		try {
			PickingItem result = (PickingItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PickingItem findById(java.lang.String id) {
		log.debug("getting PickingItem instance with id: " + id);
		try {
			PickingItem instance = (PickingItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.PickingItem", id);
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

	public List<PickingItem> findByExample(PickingItem instance) {
		log.debug("finding PickingItem instance by example");
		try {
			List<PickingItem> results = (List<PickingItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.PickingItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<PickingItem> findPickingItemByOrderId(String id) {
		String hql = "from PickingItem as p where p.orderId='" + id + "' and p.state.code='0'";
		List<PickingItem> list = (List<PickingItem>) sessionFactory
				.getCurrentSession().createQuery(hql).list();
		return list;
	}
	
	public List<PickingItem> findPickingItemByOrderIdIgnoreStatus(String id) {
		//String hql = "from PickingItem as p where p.orderId='" + id +"' and p.state.code!='"+ItemState.DELETE+"'";
		String hql = "from PickingItem as p where p.orderId='" + id +"'";
		List<PickingItem> list = (List<PickingItem>) sessionFactory
				.getCurrentSession().createQuery(hql).list();
		return list;
	}

	public List<PickingItem> findPickingItemByOrderIdhe(String id) {
		String hql = "from PickingItem as p where p.orderId='" + id + "' and p.state.code='0'";
		List<PickingItem> list = (List<PickingItem>) sessionFactory
				.getCurrentSession().createQuery(hql).list();
		List<PickingItem> result = new ArrayList<PickingItem>();
		for (int i = 0; i < list.size(); i++) {
			PickingItem pi = list.get(i);
			if (result.size() == 0 || !pi.getSn().equals("N/A")) {
				PickingItem pi_item = new PickingItem();
				pi_item.setBin(pi.getBin());
				pi_item.setCreationTime(pi.getCreationTime());
				pi_item.setInvoice(pi.getInvoice());
				pi_item.setJobId(pi.getJobId());
				pi_item.setLastItems(pi.getLastItems());
				pi_item.setLastModifiedTime(pi.getLastModifiedTime());
				pi_item.setMaterial(pi.getMaterial());
				pi_item.setNextItems(pi.getNextItems());
				pi_item.setOrderId(pi.getOrderId());
				pi_item.setQty(pi.getQty());
				pi_item.setRemark(pi.getRemark());
				pi_item.setReferenceBin(pi.getReferenceBin());
				pi_item.setSn(pi.getSn());
				pi_item.setState(pi.getState());
				pi_item.setUnitPackage(pi.getUnitPackage());
				pi_item.setWarehouse(pi.getWarehouse());
				result.add(pi_item);
			} else {
				for (int j = 0; j < result.size(); j++) {
					if (pi.getMaterial().getCode().trim().equalsIgnoreCase(
							result.get(j).getMaterial().getCode().trim())
							&& pi.getBin().getCode().trim().equalsIgnoreCase(
									result.get(j).getBin().getCode())) {
						float a = pi.getQty();
						float b = result.get(j).getQty();
						result.get(j).setQty(a + b);
						break;
					}else{
						if(j==result.size()-1){
							PickingItem pi_item = new PickingItem();
							pi_item.setBin(pi.getBin());
							pi_item.setCreationTime(pi.getCreationTime());
							pi_item.setInvoice(pi.getInvoice());
							pi_item.setJobId(pi.getJobId());
							pi_item.setLastItems(pi.getLastItems());
							pi_item.setLastModifiedTime(pi.getLastModifiedTime());
							pi_item.setMaterial(pi.getMaterial());
							pi_item.setNextItems(pi.getNextItems());
							pi_item.setOrderId(pi.getOrderId());
							pi_item.setQty(pi.getQty());
							pi_item.setRemark(pi.getRemark());
							pi_item.setReferenceBin(pi.getReferenceBin());
							pi_item.setSn(pi.getSn());
							pi_item.setState(pi.getState());
							pi_item.setUnitPackage(pi.getUnitPackage());
							pi_item.setWarehouse(pi.getWarehouse());
							result.add(pi_item);
							break;
						}
					}
				}
			}
		}
		return result;
	}
}
