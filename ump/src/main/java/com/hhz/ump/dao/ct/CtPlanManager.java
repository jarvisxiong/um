package com.hhz.ump.dao.ct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtPlan;

@Service
@Transactional
public class CtPlanManager extends BaseService<CtPlan, String> {
	@Autowired
	private CtPlanDao ctPlanDao;

	public void saveCtPlan(CtPlan ctPlan) {
		PowerUtils.setEmptyStr2Null(ctPlan);
		ctPlanDao.save(ctPlan);
	}

	public void deleteCtPlan(String id) {
		ctPlanDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtPlan, String> getDao() {
		return ctPlanDao;
	}
	
	public List<CtPlan> getCtPlanByCtLedgerId(String ctLedgerId){
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ctLedgerId", ctLedgerId);
		String hql = " from CtPlan where ctLedger.ctLedgerId =:ctLedgerId ";
		List<CtPlan> list = getDao().createQuery(hql, values).list();
		if (list == null || list.size() == 0)
			return null;
		else
			return list;
	}
}

