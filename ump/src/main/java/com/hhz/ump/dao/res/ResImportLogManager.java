package com.hhz.ump.dao.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResImportLog;

@Service
@Transactional
public class ResImportLogManager extends BaseService<ResImportLog, String> {
	@Autowired
	private ResImportLogDao resImportLogDao;

	public void saveResImportLog(ResImportLog resImportLog) {
		PowerUtils.setEmptyStr2Null(resImportLog);
		resImportLogDao.save(resImportLog);
	}

	public void deleteResImportLog(String id) {
		resImportLogDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResImportLog, String> getDao() {
		return resImportLogDao;
	}
	
	public void insertResImportLog(String resApproveInfoId,String methodName,String importTypeCd,String errorLog){
		ResImportLog importLog=new ResImportLog();
		importLog.setResApproveInfoId(resApproveInfoId);
		importLog.setMethodName(methodName);
		importLog.setImportTypeCd(importTypeCd);
		importLog.setErrorLog(errorLog);
		saveResImportLog(importLog);
	}
}

