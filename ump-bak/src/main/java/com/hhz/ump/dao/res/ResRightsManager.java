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
import com.hhz.ump.entity.res.ResRights;

@Service
@Transactional
public class ResRightsManager extends BaseService<ResRights, String> {
	@Autowired
	private ResRightsDao resRightsDao;
	/**
	 * 根据父节点查找所有子节点
	 * @param parentId
	 * @param args
	 * @return
	 */
	public List<ResRights> getListByParentId(String parentId,String... args){
		StringBuffer hql = new StringBuffer(" from ResRights t ");
		hql.append(" where t.parentId = :parentId and t.ifActive = 1 order by t.sequenceNo ");
		Map<String, Object> param =  new HashMap<String, Object>();
		param.put("parentId", parentId);
		return resRightsDao.find(hql.toString(), param);
	}
	
	public void saveResRights(ResRights resRights) {
		PowerUtils.setEmptyStr2Null(resRights);
		resRightsDao.save(resRights);
	}

	public void deleteResRights(String id) {
		resRightsDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResRights, String> getDao() {
		return resRightsDao;
	}
	
}

