package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContAddiHis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContAddiHisManager extends BaseService<BisContAddiHis, String> {
	@Autowired
	private BisContAddiHisDao bisContAddiHisDao;

	public void saveBisContAddiHis(BisContAddiHis bisContAddiHis) {
		PowerUtils.setEmptyStr2Null(bisContAddiHis);
		bisContAddiHisDao.save(bisContAddiHis);
	}

	public void deleteBisContAddiHis(String id) {
		bisContAddiHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContAddiHis, String> getDao() {
		return bisContAddiHisDao;
	}
	
}

