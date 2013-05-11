package com.hhz.ump.dao.ct;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtLedger;

@Service
@Transactional
public class CtLedgerManager extends BaseService<CtLedger, String> {
	@Autowired
	private CtLedgerDao ctLedgerDao;

	public void saveCtLedger(CtLedger ctLedger) {
		PowerUtils.setEmptyStr2Null(ctLedger);
		ctLedgerDao.save(ctLedger);
	}

	public void deleteCtLedger(String id) {
		ctLedgerDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtLedger, String> getDao() {
		return ctLedgerDao;
	}
	public Map<String, String> getCtLedger() {
		Map<String, String> mapCtLedger = new LinkedHashMap<String, String>();
		mapCtLedger.put("", "--选择--");
		String hql = "from CtLedger order by projectName,ctLedgerId ";
		List<CtLedger> list = ctLedgerDao.find(hql);
		for(CtLedger ctLedger : list){
			if(StringUtils.isNotBlank(ctLedger.getPeriods())){
				mapCtLedger.put(ctLedger.getCtLedgerId(), ctLedger.getProjectName()+"--"+ctLedger.getPeriods()+"期");
			}else{
				mapCtLedger.put(ctLedger.getCtLedgerId(), ctLedger.getProjectName());
			}
		}
		return mapCtLedger;
	}

}

