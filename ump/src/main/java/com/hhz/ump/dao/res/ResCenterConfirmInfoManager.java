package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResCenterConfirmInfo;

@Service
@Transactional
public class ResCenterConfirmInfoManager extends BaseService<ResCenterConfirmInfo, String> {
	@Autowired
	private ResCenterConfirmInfoDao resCenterConfirmInfoDao;

	public void saveResCenterConfirmInfo(ResCenterConfirmInfo resCenterConfirmInfo) {
		PowerUtils.setEmptyStr2Null(resCenterConfirmInfo);
		resCenterConfirmInfoDao.save(resCenterConfirmInfo);
	}

	public boolean isAllConfirmed(String resApproveInfoId){
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_resApproveInfo.resApproveInfoId", resApproveInfoId));
		filters.add(new PropertyFilter("EQS_conOption", "0"));
		int cnt=countByPropertyFilter(filters);
		return cnt==0;
	}
	public void deleteResCenterConfirmInfo(String id) {
		resCenterConfirmInfoDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResCenterConfirmInfo, String> getDao() {
		return resCenterConfirmInfoDao;
	}
	
}

