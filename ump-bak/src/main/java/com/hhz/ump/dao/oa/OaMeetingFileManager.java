package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaMeetingFile;

@Service
@Transactional
public class OaMeetingFileManager extends BaseService<OaMeetingFile, String> {
	@Autowired
	private OaMeetingFileDao oaMeetingFileDao;

	public void saveOaMeetingFile(OaMeetingFile oaMeetingFile) {
		PowerUtils.setEmptyStr2Null(oaMeetingFile);
		oaMeetingFileDao.save(oaMeetingFile);
	}

	public void deleteOaMeetingFile(String id) {
		oaMeetingFileDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaMeetingFile, String> getDao() {
		return oaMeetingFileDao;
	}
	
}

