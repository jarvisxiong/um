package com.hhz.ump.dao.prod;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.prod.ProdBussinessWeight;

@Service
@Transactional
public class ProdBussinessWeightManager extends BaseService<ProdBussinessWeight, String> {
	@Autowired
	private ProdBussinessWeightDao prodBussinessWeightDao;

	public void saveProdBussinessWeight(ProdBussinessWeight prodBussinessWeight) {
		PowerUtils.setEmptyStr2Null(prodBussinessWeight);
		prodBussinessWeightDao.save(prodBussinessWeight);
	}

	public void deleteProdBussinessWeight(String id) {
		prodBussinessWeightDao.delete(id);
	}
	
	@Override
	public HibernateDao<ProdBussinessWeight, String> getDao() {
		return prodBussinessWeightDao;
	}
	
	public void  deleteProdBussinessWeightByBussinessCd(String bussinessCd) {
		String hql="delete from ProdBussinessWeight pbw where pbw.bussinessCd = :bussinessCd";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bussinessCd", bussinessCd);
		prodBussinessWeightDao.batchExecute(hql, map);
	}
	
}

