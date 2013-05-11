package com.intelitune.nwms.dao;

// Generated 2008-12-16 11:32:40 by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.BinState;

/**
 * Home object for domain model class Bin.
 * @see com.intelitune.nwms.model.Bin
 * @author Hibernate Tools
 */
public class BinHome{

	private final static BinHome instance =new BinHome();
	
	private BinHome(){
		
	}
	
	public static final BinHome getInstance() {
		return instance;
	}
	
	
	private static final Log log = LogFactory.getLog(BinHome.class);

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

	public void persist(Bin transientInstance) {
		log.debug("persisting Bin instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Bin instance) {
		log.debug("attaching dirty Bin instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bin instance) {
		log.debug("attaching clean Bin instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Bin persistentInstance) {
		log.debug("deleting Bin instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bin merge(Bin detachedInstance) {
		log.debug("merging Bin instance");
		try {
			Bin result = (Bin) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Bin findById(java.lang.String id) {
		log.debug("getting Bin instance with id: " + id);
		try {
			Bin instance = (Bin) sessionFactory.getCurrentSession().get(
					"com.intelitune.nwms.model.Bin", id);
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

	public List<Bin> findByExample(Bin instance) {
		log.debug("finding Bin instance by example");
		try {
			List<Bin> results = (List<Bin>) sessionFactory.getCurrentSession()
					.createCriteria("com.intelitune.nwms.model.Bin").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Bin> findBinByRow_id(String row_id){
		String hql="from Bin as b where b.row.id='"+row_id+"'";
		List<Bin> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	
	public Bin findBinByCode(String warehouse_id,String code){
		String hql="from Bin as b where b.warehouse.id='"+warehouse_id+"' and b.code='"+code+"' and b.state.code!='"+BinState.DELETE+"'" ;
		List list = sessionFactory.getCurrentSession().createQuery(hql).list();
		if(list.size()!=0){
			return (Bin) list.get(0);
		}
		else{
			return null;
		}
	}
}
