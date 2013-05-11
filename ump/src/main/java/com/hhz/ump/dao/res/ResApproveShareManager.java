package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveShare;

@Service
@Transactional
public class ResApproveShareManager extends BaseService<ResApproveShare, String> {
	@Autowired
	private ResApproveShareDao resApproveShareDao;

	public void saveResApproveShare(ResApproveShare resApproveShare) {
		PowerUtils.setEmptyStr2Null(resApproveShare);
		resApproveShareDao.save(resApproveShare);
	}
	public void saveResApproveShares(List<ResApproveShare> resApproveShares) {
		for (ResApproveShare resApproveShare : resApproveShares) {
			saveResApproveShare(resApproveShare);
		}
	}
	public void refeshShares(List<ResApproveShare> sharesOld,List<ResApproveShare> shares){
		delete(sharesOld);
		saveResApproveShares(shares);
	}
	public void deleteResApproveShare(String id) {
		resApproveShareDao.delete(id);
	}
	public List<ResApproveShare> loadShareList(String resApproveInfoId){
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_resApproveInfo.resApproveInfoId", resApproveInfoId));
		List<ResApproveShare> list= find(filters);
		return list;
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<ResApproveShare, String> getDao() {
		return resApproveShareDao;
	}
	public void delete(List<ResApproveShare> shares,ResApproveInfo approveInfo){
		for (ResApproveShare resApproveShare : shares) {
			getDao().delete(resApproveShare);
			approveInfo.getResApproveShares().remove(resApproveShare);
		}
	}
}

