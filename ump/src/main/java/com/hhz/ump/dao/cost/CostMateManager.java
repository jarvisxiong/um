package com.hhz.ump.dao.cost;

import java.math.BigDecimal;
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
import com.hhz.ump.entity.cost.CostMate;
import com.hhz.ump.entity.cost.CostMateCol;
import com.hhz.ump.entity.cost.CostMatePrice;

@Service
@Transactional
public class CostMateManager extends BaseService<CostMate, String> {
	@Autowired
	private CostMateDao costMateDao;
	@Autowired
	private CostMatePriceDao costMatePriceDao;
	@Autowired
	private CostMateColDao costMateColDao;

	public void saveCostMate(CostMate costMate) {
		PowerUtils.setEmptyStr2Null(costMate);
		costMateDao.save(costMate);
	}

	/**
	 * 删除设备信息
	 * @param id
	 */
	public void deleteCostMate(String id) {
		CostMate mate = getEntity(id);
		if(mate != null){
			if(mate.getCostMatePrices().size() > 0){
				for (CostMatePrice price : mate.getCostMatePrices()) {
					costMatePriceDao.delete(price);
				}
			}
			if(mate.getCostMateCols().size() > 0){
				for (CostMateCol col : mate.getCostMateCols()) {
					costMateColDao.delete(col);
				}
			}
			costMateDao.delete(id);
		}
	}
	
	/**
	 * 根据设备名称搜索设备列表
	 * @param mateName
	 * @return
	 */
	public List<CostMate> checkMateName(String mateName){
		List<CostMate> mateList = new ArrayList<CostMate>();
		String hql = " from CostMate t where t.mateName = :mateName";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("mateName", mateName.trim());
		mateList = getDao().createQuery(hql,values).list();
		return mateList;
	}
	
	@Override
	public HibernateDao<CostMate, String> getDao() {
		return costMateDao;
	}

	/**
	 * 批量保存,提高I/O效率
	 * @param costMate
	 * @param costMateColList
	 * @param costMatePriceList
	 */
	public void saveCostMate(CostMate costMate, List<CostMateCol> costMateColList, List<CostMatePrice> costMatePriceList) {
		this.saveCostMate(costMate);
		//保存设备列头
		for (CostMateCol mateCol : costMateColList) {
			mateCol.setCostMate(costMate);
			costMateColDao.save(mateCol);
		}
		//保存设备型号
		long tmpSequenceNo =1; 
		for (CostMatePrice matePrice : costMatePriceList) {
			matePrice.setCostMate(costMate);
			
			if (matePrice.getPrice() == null) {
				matePrice.setPrice(new BigDecimal(0));
			}
			matePrice.setSequenceNo(new Long(tmpSequenceNo++));
			
			//非空控制
			matePrice.setGroupName( formatDefault(matePrice.getGroupName()));
			matePrice.setGroupName2( formatDefault(matePrice.getGroupName2()));
			matePrice.setGroupName3( formatDefault(matePrice.getGroupName3()));
			matePrice.setMaterialName(formatDefault(matePrice.getMaterialName()));
			matePrice.setSpecName(formatDefault(matePrice.getSpecName()));
			matePrice.setModelName(formatDefault(matePrice.getModelName()));
			
			costMatePriceDao.save(matePrice);
		}
		
	}

	private String formatDefault(String str){
		return StringUtils.isBlank(str)?"-":str.trim();
	}
	
	public CostMate getCostMate(String moduleId){
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("parentModuleId", moduleId);
		String hql = "from CostMate t where t.parentModuleId = :parentModuleId ";
		List list = find(hql, values);
		if(list == null || list.size() == 0)
			return null;
		else
			return (CostMate)list.get(0);
	}
}

