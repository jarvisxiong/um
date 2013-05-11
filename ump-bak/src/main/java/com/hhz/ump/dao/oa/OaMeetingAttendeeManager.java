package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaMeetingAttendee;

@Service
@Transactional
public class OaMeetingAttendeeManager extends BaseService<OaMeetingAttendee, String> {
	@Autowired
	private OaMeetingAttendeeDao oaMeetingAttendeeDao;

	
	public void saveOaMeetingAttendee(OaMeetingAttendee oaMeetingAttendee) {
		PowerUtils.setEmptyStr2Null(oaMeetingAttendee);
		oaMeetingAttendeeDao.save(oaMeetingAttendee);
	}

	public void deleteOaMeetingAttendee(String id) {
		oaMeetingAttendeeDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaMeetingAttendee, String> getDao() {
		return oaMeetingAttendeeDao;
	}
	
}

