package com.hhz.ump.dao.res;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveStep;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResConditonType;

@Service
@Transactional
public class ResConditonTypeManager extends BaseService<ResConditonType, String> {
	@Autowired
	private ResConditonTypeDao resConditonTypeDao;
	@Autowired
	private ResApproveStepManager approveStepManager;

	public void saveResConditonType(ResConditonType resConditonType) {
		PowerUtils.setEmptyStr2Null(resConditonType);
		resConditonTypeDao.save(resConditonType);
	}

	public void deleteResConditonType(String id) {
		ResConditonType resConditonType = super.getEntity(id);
		this.deleteResConditonType(resConditonType);
	}

	public void deleteResConditonType(ResConditonType resConditonType) {
		for (ResApproveStep approveStep : resConditonType.getResApproveSteps()) {
			approveStepManager.delete(approveStep);
		}
		super.delete(resConditonType);
	}
	public ResConditonType getConditionByCd(String authTypeCd,String conditionCd){
		StringBuffer hql=new StringBuffer("from ResConditonType r where r.resAuthType.authTypeCd=:authTypeCd and r.conditionCd=:conditionCd "); 
		Map<String, Object> values=new HashMap<String, Object>();
		values.put("authTypeCd", authTypeCd);
		values.put("conditionCd", conditionCd);
		List<ResConditonType> conditonTypes= find(hql.toString(), values);
		if (conditonTypes.size()>0 )
			return conditonTypes.get(0);
		return null;
	}
	public void deleteResConditionTypes(List<ResConditonType> resConditonTypes) {
		for (ResConditonType resConditonType : resConditonTypes) {
			resConditonType = this.getEntity(resConditonType.getResConditonTypeId());
			this.deleteResConditonType(resConditonType);
		}
	}

	public void saveResConditonTypes(List<ResConditonType> resConditonTypes, ResAuthType resAuthType) {
		for (ResConditonType resConditonType : resConditonTypes) {
			if (resConditonType.getResAuthType() == null) {
				resConditonType.setResAuthType(resAuthType);
			}
			this.saveResConditonType(resConditonType);
		}
	}

	public void saveOrDeleteResConditionTypes(List<ResConditonType> insertRecords,
 List<ResConditonType> updateRecords,
			List<ResConditonType> deleteRecords, ResAuthType resAuthType) {
		this.saveResConditonTypes(insertRecords, resAuthType);
		this.saveResConditonTypes(updateRecords, resAuthType);
		this.deleteResConditionTypes(deleteRecords);
	}

	@Override
	public HibernateDao<ResConditonType, String> getDao() {
		return resConditonTypeDao;
	}
	
}

