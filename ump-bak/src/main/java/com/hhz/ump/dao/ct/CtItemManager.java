package com.hhz.ump.dao.ct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtItem;

@Service
@Transactional
public class CtItemManager extends BaseService<CtItem, String> {
	@Autowired
	private CtItemDao ctItemDao;

	public void saveCtItem(CtItem ctItem) {
		PowerUtils.setEmptyStr2Null(ctItem);
		ctItemDao.save(ctItem);
	}

	public void deleteCtItem(String id) {
		ctItemDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtItem, String> getDao() {
		return ctItemDao;
	}
	//建立合约规划与科目关联
	public String updateCtContPlanId(String ctContPlanId,String itemIds){
		String flag="0";
		Map<String,Object> value = new HashMap<String,Object>();
		value.put("itemId", itemIds.split(","));
		value.put("ctContPlanId", ctContPlanId);
		StringBuffer hql = new StringBuffer().append(" update CtItem set ctContPlan.ctContPlanId=:ctContPlanId ");
		hql.append(" where ctItemId in(:itemId) ");
		this.getDao().batchExecute(hql.toString(), value);
		flag="1";
		return flag;
	}
	//取消合约规划与科目关联
	public String updateCtContPlanId(String ctContPlanId,String itemIds,String cancel){
		String flag="0";
		Map<String,Object> value = new HashMap<String,Object>();
		value.put("itemId", itemIds.split(","));
		value.put("cancel", cancel);
		StringBuffer hql = new StringBuffer().append(" update CtItem set ctContPlan.ctContPlanId= null ");
		hql.append(" where ctItemId in(:itemId) ");
		this.getDao().batchExecute(hql.toString(), value);
		flag="1";
		return flag;
	}
	public String getContPlanConn(String ctContPlanId){
		String flag="0";
		Map<String,Object> value = new HashMap<String,Object>();
		value.put("ctContPlanId", ctContPlanId);
		String hql = " from CtItem where ctContPlan.ctContPlanId=:ctContPlanId ";
		List<CtItem> list= getDao().find(hql, value);
		if(list == null|| list.size() ==0)
			return flag;
		else{
			flag="1";
			return flag;
		}
	}
}

