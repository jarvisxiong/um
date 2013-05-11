package com.hhz.ump.dao.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResAuthMsg;
import com.hhz.ump.entity.res.ResAuthType;

@Service
@Transactional
public class ResAuthMsgManager extends BaseService<ResAuthMsg, String> {
	@Autowired
	private ResAuthMsgDao resAuthMsgDao;

	public void saveResAuthMsg(ResAuthMsg resAuthMsg) {
		PowerUtils.setEmptyStr2Null(resAuthMsg);
		resAuthMsgDao.save(resAuthMsg);
	}

	public void deleteResAuthMsg(String id) {
		resAuthMsgDao.delete(id);
	}

	@Override
	public HibernateDao<ResAuthMsg, String> getDao() {
		return resAuthMsgDao;
	}

	public void deleteResAuthMsgs(List<ResAuthMsg> resAuthMsgs) {
		for (ResAuthMsg resAuthMsg : resAuthMsgs) {
			resAuthMsg = this.getEntity(resAuthMsg.getResAuthMsgId());
			delete(resAuthMsg);
		}
	}

	public void saveResAuthMsgs(List<ResAuthMsg> resAuthMsgs, ResAuthType resAuthType) {
		for (ResAuthMsg resAuthMsg : resAuthMsgs) {
			if (resAuthMsg.getResAuthType() == null) {
				resAuthMsg.setResAuthType(resAuthType);
			}
			this.saveResAuthMsg(resAuthMsg);
		}
	}

	public void saveOrDeleteResAuthMsgs(List<ResAuthMsg> insertRecords, List<ResAuthMsg> updateRecords, List<ResAuthMsg> deleteRecords,
			ResAuthType resAuthType) {
		this.saveResAuthMsgs(insertRecords, resAuthType);
		this.saveResAuthMsgs(updateRecords, resAuthType);
		this.deleteResAuthMsgs(deleteRecords);
	}
}
