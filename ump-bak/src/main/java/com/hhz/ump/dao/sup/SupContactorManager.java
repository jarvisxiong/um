package com.hhz.ump.dao.sup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sup.SupContactor;

@Service
@Transactional
public class SupContactorManager extends BaseService<SupContactor, String> {
	@Autowired
	private SupContactorDao supContactorDao;

	public void saveSupContactor(SupContactor supContactor) {
		PowerUtils.setEmptyStr2Null(supContactor);
		supContactorDao.save(supContactor);
	}

	public void deleteSupContactor(String id) {
		supContactorDao.delete(id);
	}
	
	@Override
	public HibernateDao<SupContactor, String> getDao() {
		return supContactorDao;
	}
	
	public List<SupContactor> querySupContactor(String supBasicId){
		String hql = "from SupContactor a where a.supBasic.supBasicId = ?";
		List<SupContactor> lstResult = supContactorDao.find(hql, supBasicId);
		return lstResult;
	}
	
}

