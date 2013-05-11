package com.hhz.ump.dao.res;

import java.sql.Clob;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveContent;

@Service
@Transactional
public class ResApproveContentManager extends BaseService<ResApproveContent, String> {
	@Autowired
	private ResApproveContentDao resApproveContentDao;

	public void saveResApproveContent(ResApproveContent resApproveContent) {
		PowerUtils.setEmptyStr2Null(resApproveContent);
		resApproveContentDao.save(resApproveContent);
		getDao().getSession().flush();
	}
	public void saveResApproveContentLock(ResApproveContent resApproveContent) {
		PowerUtils.setEmptyStr2Null(resApproveContent);
		String id=resApproveContent.getResApproveContentId();
		Clob oldValue=resApproveContent.getApproveContent();
		if (StringUtils.isNotBlank(id)) {
			getDao().getSession().refresh(resApproveContent,LockMode.UPGRADE);
		}
		resApproveContent.setApproveContent(oldValue);
		resApproveContentDao.save(resApproveContent);
		getDao().getSession().flush();
	}

	public void deleteResApproveContent(String id) {
		resApproveContentDao.delete(id);
	}

	@Override
	public HibernateDao<ResApproveContent, String> getDao() {
		return resApproveContentDao;
	}

}
