package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFlatContRel;

@Service
@Transactional
public class BisFlatContRelManager extends BaseService<BisFlatContRel, String> {
	@Autowired
	private BisFlatContRelDao bisFlatContRelDao;

	public void saveBisFlatContRel(BisFlatContRel bisFlatContRel) {
		PowerUtils.setEmptyStr2Null(bisFlatContRel);
		bisFlatContRelDao.save(bisFlatContRel);
	}

	public void deleteBisFlatContRel(String id) {
		bisFlatContRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFlatContRel, String> getDao() {
		return bisFlatContRelDao;
	}
	
	/**
	 * 根据合同ID公寓ID删除记录
	 */
	public void deleteByContAndFlat(String bisContId, Object[] bisFlatIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		String sql = "delete from BisFlatContRel where bisCont.bisContId=:bisContId ";
		param.put("bisContId", bisContId);
		if(bisFlatIds != null) {
			sql += "and bisFlat.bisFlatId in (:bisFlatIds)";
			param.put("bisFlatIds", bisFlatIds);
		}
		bisFlatContRelDao.batchExecute(sql, param);
	}
}

