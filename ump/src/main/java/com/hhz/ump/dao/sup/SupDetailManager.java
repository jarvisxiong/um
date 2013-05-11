package com.hhz.ump.dao.sup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sup.SupDetail;

@Service
@Transactional
public class SupDetailManager extends BaseService<SupDetail, String> {
	@Autowired
	private SupDetailDao supDetailDao;

	public void saveSupDetail(SupDetail supDetail) {
		PowerUtils.setEmptyStr2Null(supDetail);
		supDetailDao.save(supDetail);
	}

	public void deleteSupDetail(String id) {
		supDetailDao.delete(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<SupDetail, String> getDao() {
		return supDetailDao;
	}

	public List<SupDetail> querySupDetail(String supBasicId) {
		String hql = "from SupDetail a where a.supBasic.supBasicId = ?";
		List<SupDetail> lstResult = supDetailDao.find(hql, supBasicId);
		return lstResult;
	}
	
}

