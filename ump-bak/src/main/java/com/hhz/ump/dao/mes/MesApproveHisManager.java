package com.hhz.ump.dao.mes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mes.MesApproveHis;

@Service
@Transactional
public class MesApproveHisManager extends BaseService<MesApproveHis, String> {
    @Autowired
    private MesApproveHisDao mesApproveHisDao;

    public void saveMesApproveHis(MesApproveHis mesApproveHis) {
        PowerUtils.setEmptyStr2Null(mesApproveHis);
        mesApproveHisDao.save(mesApproveHis);
    }

    public void deleteMesApproveHis(String id) {
        mesApproveHisDao.delete(id);
    }

    @Override
    public HibernateDao<MesApproveHis, String> getDao() {
        return mesApproveHisDao;
    }

    public List<MesApproveHis> findMesApproveHisByMesMeetingInfoId(String mesMeetingInfoId) {
        StringBuffer hql=new StringBuffer("from MesApproveHis m where m.mesMeetingInfo.mesMeetingInfoId=? order by m.createdDate ");
        return mesApproveHisDao.find(hql.toString(),mesMeetingInfoId);
    }
}

