package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.util.PdCache;

@Service
@Transactional
public class ResModuleManager extends BaseService<ResModule, String> {
	@Autowired
	private ResModuleDao resModuleDao;
	@Autowired
	private ResAuthTypeManager typeManager;

	
	public void saveResModule(ResModule resModule) {
		PowerUtils.setEmptyStr2Null(resModule);
		resModuleDao.save(resModule);
	}

	public void deleteResModule(String id) {
		ResModule module = this.getEntity(id);
		for (ResAuthType type : module.getResAuthTypes()) {
			typeManager.deleteResAuthType(type);
		}
		super.delete(module);
	}
	public ResModule getResModuleByCd(String moduleCd){
		return findUniqueBy("moduleCd", moduleCd);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getModuleMap() {
		Map<String, String> mapModule = new LinkedHashMap<String, String>();
		mapModule.put("","");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("moduleTypeCd", "1");
		List<ResModule> modules =PdCache.getEntityList(ResModule.class,map);
		CollectionHelper.sortList(modules, "sequenceNo");
		for (ResModule resModule : modules) {
			mapModule.put(resModule.getModuleCd(), resModule.getModuleName());
		}
		return mapModule;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<ResModule, String> getDao() {
		return resModuleDao;
	}
	public List<ResModule> loadAllModule(String moduleTypeCd){
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_moduleTypeCd", moduleTypeCd));
		return resModuleDao.find(filters, "sequence_No asc");
	}
	public List<ResModule> loadActiveResModule(String moduleTypeCd){
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_active", true));
		filters.add(new PropertyFilter("EQS_moduleTypeCd", moduleTypeCd));
		return resModuleDao.find(filters, "sequence_No asc");
	}
	
	public List<ResModule> loadActiveChilderResModule(String parentModuleCd,String moduleTypeCd){
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_active", true));
		filters.add(new PropertyFilter("EQS_moduleTypeCd", moduleTypeCd));
		if(StringUtils.isNotBlank(parentModuleCd)){
			filters.add(new PropertyFilter("EQS_parentModuleCd",parentModuleCd));
		}
		return resModuleDao.find(filters, "sequence_No asc");
	}
	
	public long countActiveChilderResModule(String parentModuleCd,String moduleTypeCd){
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_active", true));
		filters.add(new PropertyFilter("EQS_moduleTypeCd", moduleTypeCd));
		if(StringUtils.isNotBlank(parentModuleCd)){
			filters.add(new PropertyFilter("EQS_parentModuleCd",parentModuleCd));
		}
		return resModuleDao.countByPropertyFilter(filters);
	}
}

