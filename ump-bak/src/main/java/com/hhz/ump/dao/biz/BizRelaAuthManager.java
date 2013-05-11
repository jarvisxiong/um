package com.hhz.ump.dao.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.biz.BizRelaAuth;

@Service
@Transactional
public class BizRelaAuthManager extends BaseService<BizRelaAuth, String> {
	@Autowired
	private BizRelaAuthDao bizRelaAuthDao;

	public void saveBizRelaAuth(BizRelaAuth bizRelaAuth) {
		PowerUtils.setEmptyStr2Null(bizRelaAuth);
		bizRelaAuthDao.save(bizRelaAuth);
	}

	public void deleteBizRelaAuth(String id) {
		bizRelaAuthDao.delete(id);
	}
	
	@Override
	public HibernateDao<BizRelaAuth, String> getDao() {
		return bizRelaAuthDao;
	}

	public void saveBatchUserOrgRel(String orgDictCds, String addUserIds,String delUserIds) {
		BizRelaAuth auth = null;
		String centerCd = null;
		
		String[] arrOldOrgCd = null;
		List<String> oldOrgCdList = new ArrayList<String>();
		
		String[] newOrgCds = orgDictCds.split(",");
		
		String[] arrUserCd = StringUtils.split(addUserIds,",");
		List<String> userCdList = new ArrayList<String>();
		
		StringBuffer sb = null;
		for (String tUserId : arrUserCd) {
			if(StringUtils.isBlank(tUserId)){
				continue;
			}
			
			userCdList.add(tUserId);
			auth = getBizRelaAuth(PlasCache.getUserById(tUserId).getUiid());
			if(auth == null){
				auth = new BizRelaAuth();
				auth.setUserCd(PlasCache.getUserById(tUserId).getUiid());
				auth.setSubmitCenterCd(orgDictCds);
				saveBizRelaAuth(auth);
			}else{
				//以前的
				centerCd = auth.getSubmitCenterCd();
				arrOldOrgCd = centerCd.split(",");
				
				oldOrgCdList = new ArrayList<String>();
				for (String tOldOrg : arrOldOrgCd) {
					if(StringUtils.isBlank(tOldOrg)){
						continue;
					}
					oldOrgCdList.add(tOldOrg);
				}
				
					
				//新的
				for (String tAddOrgCd: newOrgCds) {
					if(StringUtils.isBlank(tAddOrgCd)){
						continue;
					}
					if(oldOrgCdList.contains(tAddOrgCd)){
						continue;
					}else{
						oldOrgCdList.add(tAddOrgCd);
					}
				}
				
				//转成逗号隔开的字符串
				sb = new StringBuffer();
				for (int i = 0; i < oldOrgCdList.size(); i++) {
					 sb.append(oldOrgCdList.get(i)).append(",");
				}
				centerCd = sb.toString();
				
				//保存
				auth.setSubmitCenterCd(centerCd);
				saveBizRelaAuth(auth);
				
			}
		}
	}
	
	public BizRelaAuth getBizRelaAuth(String userCd){
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("userCd", userCd);
		
		String hql = " from BizRelaAuth where userCd = :userCd ";
		List<BizRelaAuth> list = getDao().createQuery(hql, values).list();
		if(list == null|| list.size() ==0)
			return null;
		else
			return list.get(0);
	}

	//删除关系
	public void removeRel(String userCd, String orgCd) {
	 
		BizRelaAuth auth = getBizRelaAuth(userCd);
		
		List<String> list = PowerUtils.array2List(auth.getSubmitCenterCd().split(","));
		
		if(list.contains(orgCd)){
			list.remove(orgCd);
		}
		if(list.contains("")){
			list.remove("");
		}
		
		if(list.size() == 0){
			delete(auth);
		}else{

			//转成逗号隔开的字符串
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				 sb.append(list.get(i)).append(",");
			}
			String centerCd = sb.toString();
			auth.setSubmitCenterCd(centerCd);
			bizRelaAuthDao.save(auth);
		}
	}
	
}

