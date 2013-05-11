package com.hhz.uums.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.bis.BisSmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisSmsMessageManager extends BaseService<BisSmsMessage, String> {
	@Autowired
	private BisSmsMessageDao bisSmsMessageDao;

	public void saveBisSmsMessage(BisSmsMessage bisSmsMessage) {
		PowerUtils.setEmptyStr2Null(bisSmsMessage);
		bisSmsMessageDao.save(bisSmsMessage);
	}

	public void deleteBisSmsMessage(String id) {
		bisSmsMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSmsMessage, String> getDao() {
		return bisSmsMessageDao;
	}
	
}

