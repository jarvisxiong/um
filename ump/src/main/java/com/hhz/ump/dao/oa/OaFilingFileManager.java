package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaFilingFile;

@Service
@Transactional
public class OaFilingFileManager extends BaseService<OaFilingFile, String> {
	@Autowired
	private OaFilingFileDao oaFilingFileDao;

	public void saveOaFilingFile(OaFilingFile oaFilingFile) {
		PowerUtils.setEmptyStr2Null(oaFilingFile);
		oaFilingFileDao.save(oaFilingFile);
	}

	public void deleteOaFilingFile(String id) {
		oaFilingFileDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaFilingFile, String> getDao() {
		return oaFilingFileDao;
	}
	
}

