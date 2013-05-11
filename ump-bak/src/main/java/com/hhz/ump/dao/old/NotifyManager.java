package com.hhz.ump.dao.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.old.Notify;

@Service
@Transactional
public class NotifyManager extends BaseService<Notify, String> {
	@Autowired
	private NotifyDao notifyDao;

	public void saveNotify(Notify notify) {
		PowerUtils.setEmptyStr2Null(notify);
		notifyDao.save(notify);
	}

	public void deleteNotify(String id) {
		notifyDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<Notify, String> getDao() {
		return notifyDao;
	}
	
}

