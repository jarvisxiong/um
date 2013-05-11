package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContFlatProp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContFlatPropManager extends BaseService<BisContFlatProp, String> {
	@Autowired
	private BisContFlatPropDao bisContFlatPropDao;

	public void saveBisContFlatProp(BisContFlatProp bisContFlatProp) {
		PowerUtils.setEmptyStr2Null(bisContFlatProp);
		bisContFlatPropDao.save(bisContFlatProp);
	}

	public void deleteBisContFlatProp(String id) {
		bisContFlatPropDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContFlatProp, String> getDao() {
		return bisContFlatPropDao;
	}
	
}

