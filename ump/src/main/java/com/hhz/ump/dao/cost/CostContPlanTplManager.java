package com.hhz.ump.dao.cost;

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
import com.hhz.ump.entity.cost.CostContPlanTpl;
import com.hhz.ump.entity.cost.CostContPlanTplDetail;

@Service
@Transactional
public class CostContPlanTplManager extends BaseService<CostContPlanTpl, String> {
	@Autowired
	private CostContPlanTplDao costContPlanTplDao;

	public void saveCostContPlanTpl(CostContPlanTpl costContPlanTpl) {
		PowerUtils.setEmptyStr2Null(costContPlanTpl);
		costContPlanTplDao.save(costContPlanTpl);
	}

	public void deleteCostContPlanTpl(String id) {
		costContPlanTplDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostContPlanTpl, String> getDao() {
		return costContPlanTplDao;
	}
	
	
	public CostContPlanTpl getCostContPlanTpl(String costContPlanTplId){
		String hql = " from CostContPlanTpl t where t.costContPlanTplId = :costContPlanTplId";
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(costContPlanTplId)){
			map.put("costContPlanTplId", costContPlanTplId);
		}
		List costContPlanTplList = costContPlanTplDao.createQuery(hql, map).list();
		if(costContPlanTplList != null)
			return (CostContPlanTpl)costContPlanTplList.get(0);
		else
			return null;
	}
	
	
	public List<CostContPlanTplDetail> getCostContPlanTplDetail(String costContPlanTplId){
		String hql = " from CostContPlanTplDetail t where t.costContPlanTpl.costContPlanTplId = :costContPlanTplId";
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(costContPlanTplId)){
			map.put("costContPlanTplId", costContPlanTplId);
		}
		List<CostContPlanTplDetail> costContPlanTplList = costContPlanTplDao.createQuery(hql, map).list();
		if(costContPlanTplList != null)
			return costContPlanTplList;
		else
			return null;
	}
}

