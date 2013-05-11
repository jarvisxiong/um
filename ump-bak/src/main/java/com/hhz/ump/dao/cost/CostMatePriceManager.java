package com.hhz.ump.dao.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostMatePrice;

@Service
@Transactional
public class CostMatePriceManager extends BaseService<CostMatePrice, String> {
	@Autowired
	private CostMatePriceDao costMatePriceDao;

	public void saveCostMatePrice(CostMatePrice costMatePrice) {
		PowerUtils.setEmptyStr2Null(costMatePrice);
		costMatePriceDao.save(costMatePrice);
	}

	public void deleteCostMatePrice(String id) {
		costMatePriceDao.delete(id);
	}
	
	/**
	 * 根据条件搜索设备型号
	 * @param costMateId
	 * @param specName
	 * @param modelName
	 * @return
	 */
	public List<CostMatePrice> checkMatePrice(String costMateId,String specName,String modelName){
		StringBuffer hql = new StringBuffer();
		hql.append(" from CostMatePrice p where 1=1");
		Map<String,Object> params = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(costMateId)) {
			hql.append(" and p.costMate.costMateId = :costMateId");
			params.put("costMateId", costMateId);
		}
		if (StringUtils.isNotBlank(specName)) {
			hql.append(" and p.specName = :specName");
			params.put("specName", specName.trim());
		}
		if (StringUtils.isNotBlank(modelName)) {
			hql.append(" and p.modelName = :modelName");
			params.put("modelName", modelName.trim());
		}
		return costMatePriceDao.find(hql.toString(), params);
	}
	public List<CostMatePrice> quickSearch(String specName,String modelName,String costMateId){
		List<CostMatePrice> list = new ArrayList<CostMatePrice>();
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from CostMatePrice p where p.enableFlg = :enableFlg");
		params.put("enableFlg", Long.valueOf(1));
		if (StringUtils.isNotBlank(costMateId)) {
			hql.append(" and p.costMate.costMateId = :costMateId");
			params.put("costMateId", costMateId);
		}
		if (StringUtils.isNotBlank(specName)) {
			hql.append(" and(p.specName like :specName");
			params.put("specName", "%"+specName.trim()+"%");
		}
		if (StringUtils.isNotBlank(modelName)) {
			hql.append(" or p.modelName like :modelName)");
			params.put("modelName", "%"+modelName.trim()+"%");
		}
		hql.append(" order by p.modelName asc");
		list = costMatePriceDao.find(hql.toString(), params);
		return list;
	} 
	
	/**
	 * 得到设备型号
	 * @param matePriceIds 
	 * @return
	 */
	public List<CostMatePrice> getCostMatePriceByPriceIds(String matePriceIds){
		List<CostMatePrice> list = new ArrayList<CostMatePrice>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from CostMatePrice p where 1=1");
		Map<String,Object> params = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(matePriceIds)) {
			String[] matePriceId = matePriceIds.split(",");
			hql.append(" and p.costMatePriceId in(:matePriceIds)");
			params.put("matePriceIds", matePriceId);
		}
		hql.append(" order by p.modelName asc");
		list = costMatePriceDao.find(hql.toString(), params);
		return list;
	} 
	
	
	
	@Override
	public HibernateDao<CostMatePrice, String> getDao() {
		return costMatePriceDao;
	}
	
}

