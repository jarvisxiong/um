package com.hhz.ump.dao.pm;

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
import com.hhz.ump.entity.pm.PmEntryResRelation;

@Service
@Transactional
public class PmEntryResRelationManager extends BaseService<PmEntryResRelation, String> {
	@Autowired
	private PmEntryResRelationDao pmEntryResRelationDao;

	public void savePmEntryResRelation(PmEntryResRelation pmEntryResRelation) {
		PowerUtils.setEmptyStr2Null(pmEntryResRelation);
		pmEntryResRelationDao.save(pmEntryResRelation);
	}

	public void deletePmEntryResRelation(String id) {
		pmEntryResRelationDao.delete(id);
	}
	
	@Override
	public HibernateDao<PmEntryResRelation, String> getDao() {
		return pmEntryResRelationDao;
	}
	
	
	public List<PmEntryResRelation> getPmEntryResRelation(String pmMateEntryId){
		List<PmEntryResRelation> pmEntryResRelationList = new ArrayList<PmEntryResRelation>();
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from PmEntryResRelation f where 1=1");
		if(StringUtils.isNotBlank(pmMateEntryId)){
			hql.append(" and f.pmMateEntryId = :pmMateEntryId");
			values.put("pmMateEntryId",pmMateEntryId);
		}
		hql.append(" order by f.approveEndDate desc ");
		pmEntryResRelationList = pmEntryResRelationDao.find(hql.toString(), values);
		if(pmEntryResRelationList.size()>0&&null!=pmEntryResRelationList)
			return pmEntryResRelationList;
		else
			return new ArrayList<PmEntryResRelation>();
	}
	
}

