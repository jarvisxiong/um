package com.hhz.ump.dao.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.SmsMessage;

@Service
@Transactional
public class SmsMessageManager extends BaseService<SmsMessage, String> {
	@Autowired
	private SmsMessageDao smsMessageDao;

	public void saveSmsMessage(SmsMessage smsMessage) {
		PowerUtils.setEmptyStr2Null(smsMessage);
		smsMessageDao.save(smsMessage);
	}

	public void deleteSmsMessage(String id) {
		smsMessageDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<SmsMessage, String> getDao() {
		return smsMessageDao;
	}
	
}

