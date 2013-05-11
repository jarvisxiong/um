package com.hhz.ump.dao.res;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResNotifyReduce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ResNotifyReduceManager extends BaseService<ResNotifyReduce, String> {
	@Autowired
	private ResNotifyReduceDao resNotifyReduceDao;

	public void saveResNotifyReduce(ResNotifyReduce resNotifyReduce) {
		PowerUtils.setEmptyStr2Null(resNotifyReduce);
		resNotifyReduceDao.save(resNotifyReduce);
	}

	public void deleteResNotifyReduce(String id) {
		resNotifyReduceDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResNotifyReduce, String> getDao() {
		return resNotifyReduceDao;
	}
	
}

