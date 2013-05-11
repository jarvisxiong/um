package com.hhz.ump.dao.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostStrageMate;

@Service
@Transactional
public class CostStrageMateManager extends BaseService<CostStrageMate, String> {
	@Autowired
	private CostStrageMateDao costStrageMateDao;

	public void saveCostStrageMate(CostStrageMate costStrageMate) {
		PowerUtils.setEmptyStr2Null(costStrageMate);
		costStrageMateDao.save(costStrageMate);
	}

	public void deleteCostStrageMate(String id) {
		costStrageMateDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostStrageMate, String> getDao() {
		return costStrageMateDao;
	}
	
	/**
	 * 根据领用单位合同号得到战略库
	 * @param takeUnitContNo
	 * @return
	 */
	public CostStrageMate getStrageMateByTakeUnitContNo(String takeUnitContNo){
		String hql = " from CostStrageMate t where t.takeUnitContNo = :takeUnitContNo order by t.createdDate asc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("takeUnitContNo", takeUnitContNo.trim());
		List<CostStrageMate> strageMateList = new ArrayList<CostStrageMate>();
		strageMateList = getDao().createQuery(hql, values).list();
		if (strageMateList != null && strageMateList.size() > 0)
			return strageMateList.get(0);
		return null;
	}
	/**
	 * 根据网批ID得到战略采购信息
	 * @param resApproveInfoId
	 * @return
	 */
	public CostStrageMate getStrageMateByResApproveInfoId(String resApproveInfoId){
		String hql = " from CostStrageMate t where t.resApproveInfoId = :resApproveInfoId order by t.createdDate desc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("resApproveInfoId", resApproveInfoId.trim());
		List<CostStrageMate> strageMateList = new ArrayList<CostStrageMate>();
		strageMateList = getDao().createQuery(hql, values).list();
		if (strageMateList != null && strageMateList.size() > 0)
			return strageMateList.get(0);
		return null;
	}
}

