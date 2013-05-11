package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResConditonType;

@Service
@Transactional
public class ResAuthTypeManager extends BaseService<ResAuthType, String> {
	@Autowired
	private ResAuthTypeDao resAuthTypeDao;

	@Autowired
	private ResConditonTypeManager conditionManager;

	public ResAuthType getResAuthTypeByCd(String authTypeCd) {
		return findUniqueBy("authTypeCd", authTypeCd);
	}

	public void saveResAuthType(ResAuthType resAuthType) {
		PowerUtils.setEmptyStr2Null(resAuthType);
		boolean isNew = false;
		if (resAuthType.getResAuthTypeId() == null) {
			isNew = true;
		}

		resAuthTypeDao.save(resAuthType);
		// 新增时默认插入一条默认分类权限记录
		if (isNew) {
			ResConditonType conditonType = new ResConditonType();
			conditonType.setConditionCd("DEFAULT");
			conditonType.setConditionName("默认");
			conditonType.setConditionValue("无");
			conditonType.setResAuthType(resAuthType);
			conditionManager.saveResConditonType(conditonType);
		}
	}

	public void deleteResAuthType(String id) {
		ResAuthType resAuthType = super.getEntity(id);
		this.deleteResAuthType(resAuthType);
	}

	public void deleteResAuthType(ResAuthType resAuthType) {
		for (ResConditonType conditonType : resAuthType.getResConditonTypes()) {
			conditionManager.deleteResConditonType(conditonType);
		}
		super.delete(resAuthType);
	}

	public List<ResAuthType> loadActiveResAuthType() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_active", true));
		return resAuthTypeDao.find(filters, "sequence_No asc");
	}
	
	public List<ResAuthType> loadActiveChilderResAuthType(String resModuleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer Hql = new StringBuffer();
		Hql.append("select t from ResAuthType t where 1=1");
		Hql.append(" and t.active = :active");
		Hql.append(" and t.resModule.resModuleId = :resModuleId");
		Hql.append(" order by sequenceNo asc");
		param.put("active", true);
		if(StringUtils.isNotBlank(resModuleId)){
			param.put("resModuleId", resModuleId);
		}
		
		return resAuthTypeDao.find(Hql.toString(),param);
	}
	
	public long countActiveChilderResAuthType(String resModuleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer Hql = new StringBuffer();
		Hql.append("select t from ResAuthType t where 1=1");
		Hql.append(" and t.active = :active");
		Hql.append(" and t.resModule.resModuleId = :resModuleId");
		Hql.append(" order by sequenceNo asc");
		param.put("active", true);
		if(StringUtils.isNotBlank(resModuleId)){
			param.put("resModuleId", resModuleId);
		}
		
		return resAuthTypeDao.countHqlResult(Hql.toString(),param);
	}
	public List<ResAuthType> loadPublishResAuthType() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_active", true));
		filters.add(new PropertyFilter("EQB_publish", true));
		return resAuthTypeDao.find(filters, "sequence_No asc");
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResAuthType, String> getDao() {
		return resAuthTypeDao;
	}
	
}
