package com.hhz.ump.dao.res;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResOptionValue;

@Service
@Transactional
public class ResOptionValueManager extends BaseService<ResOptionValue, String> {
	@Autowired
	private ResOptionValueDao resOptionValueDao;

	public void saveResOptionValue(ResOptionValue resOptionValue) {
		PowerUtils.setEmptyStr2Null(resOptionValue);
		resOptionValueDao.save(resOptionValue);
	}

	public void deleteResOptionValue(String id) {
		resOptionValueDao.delete(id);
	}
	
	public void deleteByOptionId(String optionId) {
		if(StringUtils.isNotBlank(optionId)) {
			String hql = "delete from ResOptionValue r where r.resOptionId = :resOptionId";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("resOptionId", optionId);
			resOptionValueDao.batchExecute(hql, param);
		}
	}
	
	/***
	 * 根据网批选项(ResOption)ID获取网批选项内容(ResOptionValue)集合
	 * @param optIid 网批选项(ResOption)ID
	 * @return
	 */
	public List<ResOptionValue> getEntityListByOptId(String optIid) {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if(StringUtils.isNotBlank(optIid)) {
			filters.add(new PropertyFilter("EQS_resOptionId", optIid));
		}
		List<ResOptionValue> list = this.find(filters);
		CollectionHelper.sortList(list, "sequenceNo");
		return list;
	}
	
	@Override
	public HibernateDao<ResOptionValue, String> getDao() {
		return resOptionValueDao;
	}
	
}

