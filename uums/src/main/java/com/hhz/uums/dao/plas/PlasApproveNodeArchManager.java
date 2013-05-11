package com.hhz.uums.dao.plas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasApproveNodeArch;

@Service
@Transactional
public class PlasApproveNodeArchManager extends BaseService<PlasApproveNodeArch, String> {
	@Autowired
	private PlasApproveNodeArchDao plasApproveNodeArchDao;

	public void savePlasApproveNodeArch(PlasApproveNodeArch plasApproveNodeArch) {
		PowerUtils.setEmptyStr2Null(plasApproveNodeArch);
		plasApproveNodeArchDao.save(plasApproveNodeArch);
	}

	public void deletePlasApproveNodeArch(String id) {
		plasApproveNodeArchDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasApproveNodeArch, String> getDao() {
		return plasApproveNodeArchDao;
	}

	public List<PlasApproveNodeArch> getNodeArchList(String plasApproveInfoId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("plasApproveInfoId", plasApproveInfoId);
		
		String hql = "from PlasApproveNodeArch t where t.plasApproveInfo.plasApproveInfoId = :plasApproveInfoId  order by t.sequenceNo asc";
		return find(hql, values);
	}
	
}

