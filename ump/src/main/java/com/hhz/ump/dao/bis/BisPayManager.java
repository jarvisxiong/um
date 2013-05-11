package com.hhz.ump.dao.bis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisPay;

@Service
@Transactional
public class BisPayManager extends BaseService<BisPay, String> {
	@Autowired
	private BisPayDao BisPayDao;
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveBisPay(BisPay BisPay) {
		PowerUtils.setEmptyStr2Null(BisPay);
		BisPayDao.save(BisPay);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteBisPay(String id) {
		BisPayDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisPay, String> getDao() {
		return BisPayDao;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<BisPay> getBisPayList(String bisProjectId,String chargeTypeCd,String year,String month) {
		BisPay obj = new BisPay();
		obj.setBisProjectId(bisProjectId);
		obj.setYear(year);
		obj.setMonth(month);
		obj.setChargeTypeCd(chargeTypeCd);
		return getBisPayList(obj);
	}
	@SuppressWarnings("unchecked")
	public List<BisPay> getBisPayList(BisPay obj) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BisPay f   ") 
		.append(" where 1=1 ");		
		if(StringUtils.isNotBlank(obj.getBisProjectId())){
			hql.append(" and  f.bisProjectId=:bisProjectId");		
		}
		if(StringUtils.isNotBlank(obj.getChargeTypeCd())){
			hql.append(" and  chargeTypeCd=:chargeTypeCd");		
		}
		if(StringUtils.isNotBlank(obj.getYear())){
			hql.append(" and  year=:year");		
		}
		if(StringUtils.isNotBlank(obj.getMonth())){
			hql.append(" and  month=:month");		
		}
		
		hql.append(" order by f.createDate desc");	
		;
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("bisProjectId", obj.getBisProjectId());
		values.put("chargeTypeCd", obj.getChargeTypeCd());
		values.put("year", obj.getYear());
		values.put("month", obj.getMonth());
		List<BisPay> result = this.getDao().createQuery(hql.toString(),
				values).list();
		
		if (result == null)
			return new ArrayList<BisPay>();
		else
			return result;
	}
	
}

