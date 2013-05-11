package com.hhz.ump.dao.mes;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mes.MesDelayInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class MesDelayInfoManager extends BaseService<MesDelayInfo, String> {
	@Autowired
	private MesDelayInfoDao mesDelayInfoDao;

	public void saveMesDelayInfo(MesDelayInfo mesDelayInfo) {
		PowerUtils.setEmptyStr2Null(mesDelayInfo);
		mesDelayInfoDao.save(mesDelayInfo);
	}

	public void deleteMesDelayInfo(String id) {
		mesDelayInfoDao.delete(id);
	}
	
	@Override
	public HibernateDao<MesDelayInfo, String> getDao() {
		return mesDelayInfoDao;
	}
	
}

