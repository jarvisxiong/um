package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisMultiContRel;

@Service
@Transactional
public class BisMultiContRelManager extends BaseService<BisMultiContRel, String> {
	@Autowired
	private BisMultiContRelDao bisMultiContRelDao;

	public void saveBisMultiContRel(BisMultiContRel bisMultiContRel) {
		PowerUtils.setEmptyStr2Null(bisMultiContRel);
		bisMultiContRelDao.save(bisMultiContRel);
	}

	public void deleteBisMultiContRel(String id) {
		bisMultiContRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMultiContRel, String> getDao() {
		return bisMultiContRelDao;
	}
	
	/**
	 * 根据合同ID公寓ID删除记录
	 */
	public void deleteByContAndMulti(String bisContId, Object[] bisMultiIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		String sql = "delete from BisMultiContRel where bisCont.bisContId=:bisContId ";
		param.put("bisContId", bisContId);
		if(bisMultiIds != null) {
			sql += " and bisMulti.bisMultiId in (:bisMultiIds)";
			param.put("bisMultiIds", bisMultiIds);
		}
		bisMultiContRelDao.batchExecute(sql, param);
	}
}

