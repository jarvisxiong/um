package com.hhz.ump.dao.sc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractMarkHis;

@Service
@Transactional
public class ScContractMarkHisManager extends BaseService<ScContractMarkHis, String> {
	@Autowired
	private ScContractMarkHisDao scContractMarkHisDao;

	public void saveScContractMarkHis(ScContractMarkHis scContractMarkHis) {
		PowerUtils.setEmptyStr2Null(scContractMarkHis);
		scContractMarkHisDao.save(scContractMarkHis);
	}

	public void deleteScContractMarkHis(String id) {
		scContractMarkHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractMarkHis, String> getDao() {
		return scContractMarkHisDao;
	}
	
	/**
	 * 根据contractTempletHisId得到ScContractMarkHis列表
	 * 
	 * @param contractTempletHisId
	 * @return
	 */
	public List<ScContractMarkHis> getListByContractTempletHisId(String contractTempletHisId) {
		StringBuffer hql = new StringBuffer("from ScContractMarkHis t where t.contractTempletHisId=:contractTempletHisId");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contractTempletHisId", contractTempletHisId);
		List<ScContractMarkHis> list = find(hql.toString(), map);
		return list;
	}
	
	/**
	 * 根据contractMarkId得到ScContractMarkHis
	 * 
	 * @param contractMarkId
	 * @return
	 */
	
	public ScContractMarkHis getByContractMarkId(String contractMarkId) {
		ScContractMarkHis result=null;
		StringBuffer hql = new StringBuffer("from ScContractMarkHis t where contractMarkId=:contractMarkId");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contractMarkId", contractMarkId);
		List<ScContractMarkHis> list = find(hql.toString(), map);
		if(list!=null && list.size()>0){
			result = list.get(0);
		}
		return result;
	}
}

