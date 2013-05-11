package com.hhz.ump.dao.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtItemTemplate;

@Service
@Transactional
public class CtItemTemplateManager extends BaseService<CtItemTemplate, String> {
	@Autowired
	private CtItemTemplateDao ctItemTemplateDao;

	public void saveCtItemTemplate(CtItemTemplate ctItemTemplate) {
		PowerUtils.setEmptyStr2Null(ctItemTemplate);
		ctItemTemplateDao.save(ctItemTemplate);
	}

	public void deleteCtItemTemplate(String id) {
		ctItemTemplateDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtItemTemplate, String> getDao() {
		return ctItemTemplateDao;
	}
	
}

