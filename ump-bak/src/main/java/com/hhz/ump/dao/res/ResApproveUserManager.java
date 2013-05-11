package com.hhz.ump.dao.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveUser;

@Service
@Transactional
public class ResApproveUserManager extends BaseService<ResApproveUser, String> {
	@Autowired
	private ResApproveUserDao resApproveUserDao;

	public void saveResApproveUser(ResApproveUser resApproveUser) {
		PowerUtils.setEmptyStr2Null(resApproveUser);
		resApproveUserDao.save(resApproveUser);
	}
	public void saveResApproveUsers(List<ResApproveUser> resApproveUsers) {
		for (ResApproveUser resApproveUser : resApproveUsers) {
			saveResApproveUser(resApproveUser);
		}
	}
	
	public void deleteResApproveUser(String id) {
		resApproveUserDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResApproveUser, String> getDao() {
		return resApproveUserDao;
	}
	
}

