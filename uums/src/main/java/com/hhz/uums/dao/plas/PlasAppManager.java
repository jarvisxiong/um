package com.hhz.uums.dao.plas;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasApp;

@Service
@Transactional
public class PlasAppManager extends BaseService<PlasApp, String> {
	@Autowired
	private PlasAppDao plasAppDao;

	public void savePlasApp(PlasApp plasApp) {
		PowerUtils.setEmptyStr2Null(plasApp);
		plasAppDao.save(plasApp);
	}

	public void deletePlasApp(String id) {
		plasAppDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasApp, String> getDao() {
		return plasAppDao;
	}

	/**
	 * webservice 功能: 使用 根据应用业务编号查询所有角色
	 * 
	 * @param appBizCd
	 *            :应用业务编号
	 * @return
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasApp findPlasApp(String appBizCd) {
		List<PlasApp> list = findBy(Restrictions.eq("appBizCd", appBizCd));
		if(list == null || list.size() == 0 )
			return null;
		else
			return (PlasApp)list.get(0);
	}

	// 查询应用,按序号排序
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasApp> getAllOrderedApps() {

		String hql = " from PlasApp t order by t.sequenceNo asc ";
		return getDao().createQuery(hql).list();
	}
	
	/**
	 * 功能: 查询应用信息
	 * 
	 * @param roleCd
	 *            
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasApp getEntityByAppBizCd(String appBizCd) {

		@SuppressWarnings("rawtypes")
		List result = getDao().createCriteria(PlasApp.class).add(Restrictions.eq("appBizCd", appBizCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasApp) result.get(0);
	}
}

