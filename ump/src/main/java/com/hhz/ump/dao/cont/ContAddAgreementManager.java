package com.hhz.ump.dao.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContAddAgreement;

@Service
@Transactional
public class ContAddAgreementManager extends BaseService<ContAddAgreement, String> {
	@Autowired
	private ContAddAgreementDao contAddAgreementDao;

	public void saveContAddAgreement(ContAddAgreement contAddAgreement) {
		PowerUtils.setEmptyStr2Null(contAddAgreement);
		contAddAgreementDao.save(contAddAgreement);
	}

	public void deleteContAddAgreement(String id) {
		contAddAgreementDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContAddAgreement, String> getDao() {
		return contAddAgreementDao;
	}


	/**
	 * 查询合同签证列表
	 * @param contLedgerId
	 * @return
	 */
	public List<ContAddAgreement> getContAddList(String contLedgerId) {
		
		String hql = " from ContAddAgreement t where t.contLedger.contLedgerId = :contLedgerId order by t.createdDate asc";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("contLedgerId", contLedgerId);
		List<ContAddAgreement> list =  getDao().find(hql, values);
		if(list == null)
			return new ArrayList<ContAddAgreement>();
		return list;
	}
	
}

