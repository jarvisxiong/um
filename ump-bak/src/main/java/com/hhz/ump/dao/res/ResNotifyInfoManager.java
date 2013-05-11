package com.hhz.ump.dao.res;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResNotifyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ResNotifyInfoManager extends BaseService<ResNotifyInfo, String> {
	@Autowired
	private ResNotifyInfoDao resNotifyInfoDao;

	public void saveResNotifyInfo(ResNotifyInfo resNotifyInfo) {
		PowerUtils.setEmptyStr2Null(resNotifyInfo);
		resNotifyInfoDao.save(resNotifyInfo);
	}

	public void deleteResNotifyInfo(String id) {
		resNotifyInfoDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResNotifyInfo, String> getDao() {
		return resNotifyInfoDao;
	}
	
}

