package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisMustRent;

@Service
@Transactional
public class BisMustRentManager extends BaseService<BisMustRent, String> {
	@Autowired
	private BisMustRentDao bisMustRentDao;

	public void saveBisMustRent(BisMustRent bisMustRent) {
		PowerUtils.setEmptyStr2Null(bisMustRent);
		bisMustRentDao.save(bisMustRent);
	}

	public void deleteBisMustRent(String id) {
		bisMustRentDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMustRent, String> getDao() {
		return bisMustRentDao;
	}
	
	/**
	 * 删除某合同下不在主键数组ids之外的实体
	 */
	public void deleteLeaveBatch(String bisContId, Object[] excludeIds) {
		
		StringBuffer hql = new StringBuffer("from BisMustRent where bisCont.bisContId=:bisContId ");
		if(excludeIds != null && excludeIds.length > 0) {
			hql.append("and bisMustRentId not in (:ids) ");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		param.put("ids", excludeIds);
		List<BisMustRent> list = getDao().find(hql.toString(), param);
		for(BisMustRent bisMustRent : list) {
			delete(bisMustRent);
		}
	}
}

