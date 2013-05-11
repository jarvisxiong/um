package com.hhz.ump.dao.jbpm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.jbpm.JbpmBudgetDetail;

@Service
@Transactional
public class JbpmBudgetDetailManager extends BaseService<JbpmBudgetDetail, String> {
	@Autowired
	private JbpmBudgetDetailDao jbpmBudgetDetailDao;

	// JbpmBudgetDetail Manager //
	@Transactional(readOnly = true)
	public JbpmBudgetDetail getJbpmBudgetDetail(String id) {
		return jbpmBudgetDetailDao.get(id);
	}
	
	public List<JbpmBudgetDetail> getAllJbpmBudgetDetail() {
		return jbpmBudgetDetailDao.getAll();
	}
	
	public void saveJbpmBudgetDetail(JbpmBudgetDetail jbpmBudgetDetail) {
		PowerUtils.setEmptyStr2Null(jbpmBudgetDetail);
		jbpmBudgetDetailDao.save(jbpmBudgetDetail);
	}

	public void deleteJbpmBudgetDetail(String id) {
		jbpmBudgetDetailDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<JbpmBudgetDetail, String> getDao() {
		return jbpmBudgetDetailDao;
	}
	
}

