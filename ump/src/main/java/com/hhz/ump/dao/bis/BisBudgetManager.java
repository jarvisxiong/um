package com.hhz.ump.dao.bis;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisBudget;

@Service
@Transactional
public class BisBudgetManager extends BaseService<BisBudget, String> {
	@Autowired
	private BisBudgetDao bisBudgetDao;

	public void saveBisBudget(BisBudget bisBudget) {
		PowerUtils.setEmptyStr2Null(bisBudget);
		bisBudgetDao.save(bisBudget);
	}

	public void deleteBisBudget(String id) {
		bisBudgetDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisBudget, String> getDao() {
		return bisBudgetDao;
	}
	/**
	 * 计算指定数组中包含的字段的数值总和
	 * @param dataArrays
	 * @return
	 */
	public BigDecimal getTotalData(BigDecimal[] dataArrays){
		
		BigDecimal totalData = new BigDecimal(0);
		
		for(BigDecimal data :dataArrays){
			//System.out.println("data="+data);
			if(data==null){
				data =  new BigDecimal(0);
			}
			totalData = totalData.add(data);
		}
		
		return totalData;
		
	}
}

