package com.hhz.ump.dao.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResCenterInfo;
import com.hhz.ump.entity.res.ResConditonType;

@Service
@Transactional
public class ResCenterInfoManager extends BaseService<ResCenterInfo, String> {
	@Autowired
	private ResCenterInfoDao resCenterInfoDao;

	public void saveResCenterInfo(ResCenterInfo resCenterInfo) {
		PowerUtils.setEmptyStr2Null(resCenterInfo);
		resCenterInfoDao.save(resCenterInfo);
	}

	public void deleteResCenterInfo(String id) {
		resCenterInfoDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResCenterInfo, String> getDao() {
		return resCenterInfoDao;
	}
	
	public void saveResCenterInfos(List<ResCenterInfo> resCenterInfos, ResConditonType resConditonType) {
		for (ResCenterInfo resCenterInfo : resCenterInfos) {
			if (resCenterInfo.getResConditonType() == null) {
				resCenterInfo.setResConditonType(resConditonType);
			}
			this.saveResCenterInfo(resCenterInfo);
		}
	}

	public void deleteResCenterInfos(List<ResCenterInfo> resCenterInfos) {
		for (ResCenterInfo resCenterInfo : resCenterInfos) {
			resCenterInfo = this.getEntity(resCenterInfo.getResCenterInfoId());
			this.delete(resCenterInfo);
		}
	}

	public void saveOrDeleteResCenterInfos(List<ResCenterInfo> insertRecords, List<ResCenterInfo> updateRecords,
			List<ResCenterInfo> deleteRecords, ResConditonType resConditonType) {
		this.saveResCenterInfos(insertRecords, resConditonType);
		this.saveResCenterInfos(updateRecords, resConditonType);
		this.deleteResCenterInfos(deleteRecords);
	}

}

