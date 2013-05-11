package com.hhz.ump.dao.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApprovePush;

@Service
@Transactional
public class ResApprovePushManager extends BaseService<ResApprovePush, String> {
	@Autowired
	private ResApprovePushDao resApprovePushDao;

	public void saveResApprovePush(ResApprovePush resApprovePush) {
		PowerUtils.setEmptyStr2Null(resApprovePush);
		resApprovePushDao.save(resApprovePush);
	}
	public void saveResApprovePushs(List<ResApprovePush> resApprovePushs) {
		for (ResApprovePush resApprovePush : resApprovePushs) {
			saveResApprovePush(resApprovePush);
		}
	}
	public void refeshPushs(List<ResApprovePush> sharesOld,List<ResApprovePush> pushs){
		delete(sharesOld);
		saveResApprovePushs(pushs);
	}
	
	public void deleteResApprovePush(String id) {
		resApprovePushDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResApprovePush, String> getDao() {
		return resApprovePushDao;
	}
	
}

