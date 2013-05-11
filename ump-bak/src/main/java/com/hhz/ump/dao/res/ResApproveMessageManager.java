package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveMessage;

@Service
@Transactional
public class ResApproveMessageManager extends BaseService<ResApproveMessage, String> {
	@Autowired
	private ResApproveMessageDao resApproveMessageDao;

	public void saveResApproveMessage(ResApproveMessage resApproveMessage) {
		PowerUtils.setEmptyStr2Null(resApproveMessage);
		resApproveMessageDao.save(resApproveMessage);
	}

	public void deleteResApproveMessage(String id) {
		resApproveMessageDao.delete(id);
	}
	public List<ResApproveMessage> loadMsgList(String resApproveInfoId){
		StringBuffer hql=new StringBuffer("from ResApproveMessage m where m.resApproveInfo.resApproveInfoId=:infoId order by m.createdDate ");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("infoId", resApproveInfoId);
		List<ResApproveMessage> list= find(hql.toString(),map);
		return list;
	}
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<ResApproveMessage, String> getDao() {
		return resApproveMessageDao;
	}
	
}

