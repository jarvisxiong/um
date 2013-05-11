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
import com.hhz.ump.entity.ct.CtBaseOperation;

@Service
@Transactional
public class CtBaseOperationManager extends BaseService<CtBaseOperation, String> {
	@Autowired
	private CtBaseOperationDao ctBaseOperationDao;

	public void saveCtBaseOperation(CtBaseOperation ctBaseOperation) {
		PowerUtils.setEmptyStr2Null(ctBaseOperation);
		ctBaseOperationDao.save(ctBaseOperation);
	}

	public void deleteCtBaseOperation(String id) {
		ctBaseOperationDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtBaseOperation, String> getDao() {
		return ctBaseOperationDao;
	}
	public List<CtBaseOperation> getOperationByName(String operName){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("operName", operName);
		StringBuffer hql = new StringBuffer();
		hql.append(" from CtBaseOperation where operName =:operName ");
		List<CtBaseOperation> list = ctBaseOperationDao.find(hql.toString(), param);
		return list;
	}
	 
}

