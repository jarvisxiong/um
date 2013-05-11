package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.StorageItem;

/**
 * Home object for domain model class StorageItem.
 * 
 * @see com.intelitune.nwms.model.StorageItem
 * @author Hibernate Tools
 */
public class StorageItemHome {
	private final static StorageItemHome instance = new StorageItemHome();

	private StorageItemHome() {
	}

	public static final StorageItemHome getInstance() {
		return instance;
	}

	private static final Log log = LogFactory.getLog(StorageItemHome.class);

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

	public void persist(StorageItem transientInstance) {
		log.debug("persisting StorageItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StorageItem instance) {
		log.debug("attaching dirty StorageItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StorageItem instance) {
		log.debug("attaching clean StorageItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StorageItem persistentInstance) {
		log.debug("deleting StorageItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StorageItem merge(StorageItem detachedInstance) {
		log.debug("merging StorageItem instance");
		try {
			StorageItem result = (StorageItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StorageItem findById(java.lang.String id) {
		log.debug("getting StorageItem instance with id: " + id);
		try {
			StorageItem instance = (StorageItem) sessionFactory
					.getCurrentSession().get(
							"com.intelitune.nwms.model.StorageItem", id);
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

	public List<StorageItem> findByExample(StorageItem instance) {
		log.debug("finding StorageItem instance by example");
		try {
			List<StorageItem> results = (List<StorageItem>) sessionFactory
					.getCurrentSession().createCriteria(
							"com.intelitune.nwms.model.StorageItem").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<StorageItem> findStorageItemByWmsOrder(WmsOrder wo,
			String productcode) {
		// List result=new ArrayList();
		// String hql="from StorageItem as s";
		// List<StorageItem>
		// list=sessionFactory.getCurrentSession().createQuery(hql).list();
		// for(int i=0;i<list.size();i++){
		// StorageItem si = (StorageItem)list.get(i);
		//			
		// for(int j =0;j<si.getMaterial().getZones().size();i++){
		// Zone zone=si.getMaterial().getZones().get(j);
		// if(zone.getAlias().trim().equals(clientname.trim())){
		// result.add(si);
		// }
		// }
		// }
		// return result;
		String hql = "from StorageItem as s where s.material.inttClientDetailWSId="
				+ wo.getClientId()
				+ " and s.warehouse.id='"
				+ wo.getWarehouseId()
				+ "' and s.state.code='"
				+ ItemState.NORMAL
				+ "' and s.material.code='"
				+ productcode
				+ "'";
		List<StorageItem> list = sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return list;
	}

	
	public List<StorageItem> findStorageItemByWmsOrderAndInvoice(WmsOrder wo,String productcode,String invoicecode){
		String hql="from StorageItem as s where s.material.inttClientDetailWSId="+wo.getClientId()+" and s.warehouse.id='"+wo.getWarehouseId()+"' and s.state.code='"+ItemState.NORMAL+"' and s.material.code='"+productcode+"' and s.invoice.code='"+invoicecode+"'";
		List<StorageItem> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	
	
	public List<StorageItem> findByHql(String hql){
		List<StorageItem> list=(List<StorageItem>)sessionFactory.getCurrentSession().createQuery(hql).list();

		return list;
	}

	public List<StorageItem> findStorageItemByBincodeAndProductCode(
			String bincode, String productcode, String sn) {
		// 如果没有sn
		String hql = "";
		if ("N/A".equals(sn) || "".equals(sn)) {
			hql = "from StorageItem as s where s.material.code='" + productcode
					+ "' and s.bin.code='" + bincode + "' and s.state.code='"
					+ ItemState.NORMAL + "' and s.sn='N/A'";
		} else {// 有sn
			hql = "from StorageItem as s where s.sn='" + sn
					+ "' and s.bin.code='" + bincode + "'";
		}
		List<StorageItem> list = sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return list;
	}

	public List<StorageItem> findStorageItemByProductCodeAndClient_id(String hql) {
		// if("".equals(productcode)||productcode==null||client_id==null||"".equals(client_id))
		// String hql="from StorageItem as s where
		// s.material.code='"+productcode+"' and
		// s.material.inttClientDetailWSId='"+client_id+"'";
		if (hql == null || "".equals(hql)) {
			return null;
		} else {
			List<StorageItem> list = sessionFactory.getCurrentSession()
					.createQuery(hql).list();
			return list;
		}

	}
	
	
	
	public List<StorageItem> findStorageItemByWarehouseAndClient(String owner_id,String warehouse_id){
		String hql="from StorageItem as si where si.warehouse.id='"+warehouse_id+"' and si.material.inttClientDetailWSId='"+owner_id+ "' and si.qty<>0 "+" and si.state.code='"+ItemState.NORMAL+"'";
		List<StorageItem> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
		
	}
	
	
	public List<StorageItem> findStorageItembyWarehouseAndProductAndClient(
			String warehouse_id, String productcode, String owner_id){
		String hql="from StorageItem as s where s.warehouse.id='"+warehouse_id+"' and s.material.code='"+productcode+"' and s.material.inttClientDetailWSId='"+owner_id+"' and s.state.code='"+ItemState.NORMAL+"' and s.qty!=0.0";
		List<StorageItem> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
