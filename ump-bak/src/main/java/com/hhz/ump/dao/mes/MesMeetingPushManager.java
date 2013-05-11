package com.hhz.ump.dao.mes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mes.MesMeetingPush;

@Service
@Transactional
public class MesMeetingPushManager extends BaseService<MesMeetingPush, String> {
	@Autowired
	private MesMeetingPushDao mesMeetingPushDao;

	public void saveMesMeetingPush(MesMeetingPush mesMeetingPush) {
		PowerUtils.setEmptyStr2Null(mesMeetingPush);
		mesMeetingPushDao.save(mesMeetingPush);
	}

	public void deleteMesMeetingPush(String id) {
		mesMeetingPushDao.delete(id);
	}
	public void saveMesMeetingPushs(List<MesMeetingPush> pushs){
		for (MesMeetingPush mesMeetingPush : pushs) {
			saveMesMeetingPush(mesMeetingPush);
		}
	}
	@Override
	public HibernateDao<MesMeetingPush, String> getDao() {
		return mesMeetingPushDao;
	}
	public void refeshPushs(List<MesMeetingPush> pushsOld,List<MesMeetingPush> pushs){
		delete(pushsOld);
		saveMesMeetingPushs(pushs);
	}
}

