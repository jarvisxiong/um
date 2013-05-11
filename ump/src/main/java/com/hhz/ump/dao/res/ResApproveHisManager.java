package com.hhz.ump.dao.res;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveHis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ResApproveHisManager extends BaseService<ResApproveHis, String> {
	@Autowired
	private ResApproveHisDao resApproveHisDao;

	public void saveResApproveHis(ResApproveHis resApproveHis) {
		PowerUtils.setEmptyStr2Null(resApproveHis);
		resApproveHisDao.save(resApproveHis);
	}

	public void deleteResApproveHis(String id) {
		resApproveHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResApproveHis, String> getDao() {
		return resApproveHisDao;
	}
	
}

