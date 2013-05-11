package com.hhz.ump.dao.webim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.webim.UserMessage;

@Service
@Transactional
public class UserMessageManager extends BaseService<UserMessage, String> {
	@Autowired
	private UserMessageDao userMessageDao;
	
	public void saveUserMessage(UserMessage userMessage) {
		PowerUtils.setEmptyStr2Null(userMessage);
		userMessageDao.save(userMessage);
	}

	public void deleteUserMessage(String id) {
		userMessageDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<UserMessage, String> getDao() {
		return userMessageDao;
	}
}

