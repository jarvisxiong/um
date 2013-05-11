package com.hhz.uums.dao.plas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasOperBatch;

@Service
@Transactional
public class PlasOperBatchManager extends BaseService<PlasOperBatch, String> {
	@Autowired
	private PlasOperBatchDao plasOperBatchDao;
	
	public static String APPLY_STATUS_CD_APPLY  = "1";
	public static String APPLY_STATUS_CD_OK  = "2";
	public static String APPLY_STATUS_CD_NO  = "3";

	public void savePlasOperBatch(PlasOperBatch plasOperBatch) {
		PowerUtils.setEmptyStr2Null(plasOperBatch);
		plasOperBatchDao.save(plasOperBatch);
	}

	public void deletePlasOperBatch(String id) {
		plasOperBatchDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasOperBatch, String> getDao() {
		return plasOperBatchDao;
	}
	
}

