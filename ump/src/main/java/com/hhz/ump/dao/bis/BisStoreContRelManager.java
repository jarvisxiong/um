package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisStoreContRel;

@Service
@Transactional
public class BisStoreContRelManager extends BaseService<BisStoreContRel, String> {
	@Autowired
	private BisStoreContRelDao bisStoreContRelDao;

	public void saveBisStoreContRel(BisStoreContRel bisStoreContRel) {
		PowerUtils.setEmptyStr2Null(bisStoreContRel);
		bisStoreContRelDao.save(bisStoreContRel);
	}

	public void deleteBisStoreContRel(String id) {
		bisStoreContRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisStoreContRel, String> getDao() {
		return bisStoreContRelDao;
	}
	
	/**
	 * 根据合同ID商铺ID删除记录
	 */
	public void deleteByContAndStore(String bisContId, Object[] bisStoreIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		String sql = "delete from BisStoreContRel where bisCont.bisContId=:bisContId ";
		param.put("bisContId", bisContId);
		if(bisStoreIds != null) {
			sql += "and bisStore.bisStoreId in (:bisStoreIds)";
			param.put("bisStoreIds", bisStoreIds);
		}
		bisStoreContRelDao.batchExecute(sql, param);
	}
	
}

