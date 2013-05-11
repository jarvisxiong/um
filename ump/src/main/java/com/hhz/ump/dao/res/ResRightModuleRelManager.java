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
import com.hhz.ump.entity.res.ResRightModuleRel;

@Service
@Transactional
public class ResRightModuleRelManager extends BaseService<ResRightModuleRel, String> {
	@Autowired
	private ResRightModuleRelDao resRightModuleRelDao;

	public void saveResRightModuleRel(ResRightModuleRel resRightModuleRel) {
		PowerUtils.setEmptyStr2Null(resRightModuleRel);
		resRightModuleRelDao.save(resRightModuleRel);
	}
	/**
	 * 根据ModuleId查询ResRightsId
	 * @param moduleId
	 * @return
	 */
	public String getResRightsIdByMId(String moduleId){
		String tmp = "";
		StringBuffer sb = new StringBuffer(" from ResRightModuleRel t where t.resModuleId = :resModuleId");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resModuleId", moduleId);
		List<ResRightModuleRel> tlist = resRightModuleRelDao.find(sb.toString(),param);
		for (ResRightModuleRel rights : tlist) {
			tmp = rights.getResRightsId();
		}
		return tmp;
	}
	public void deleteResRightModuleRel(String id) {
		resRightModuleRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResRightModuleRel, String> getDao() {
		return resRightModuleRelDao;
	}
	
}

