package com.hhz.ump.dao.sc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractInfoSay;

@Service
@Transactional
public class ScContractInfoSayManager extends BaseService<ScContractInfoSay, String> {
	@Autowired
	private ScContractInfoSayDao scContractInfoSayDao;

	public void saveScContractInfoSay(ScContractInfoSay scContractInfoSay) {
		PowerUtils.setEmptyStr2Null(scContractInfoSay);
		scContractInfoSayDao.save(scContractInfoSay);
	}

	public void deleteScContractInfoSay(String id) {
		scContractInfoSayDao.delete(id);
	}

	@Override
	public HibernateDao<ScContractInfoSay, String> getDao() {
		return scContractInfoSayDao;
	}

	/**
	 * 搜索信息列表
	 * 
	 * @param whereString
	 * @param map
	 * @return
	 */
	public List<ScContractInfoSay> loadMsgList(String whereString, Map<String, Object> map) {

		StringBuffer hql = new StringBuffer("from ScContractInfoSay m where 1=1 " + whereString + " order by m.createdDate ");

		List<ScContractInfoSay> list = find(hql.toString(), map);
		return list;
	}
}
