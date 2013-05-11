package com.hhz.ump.dao.mes;

import java.util.Map;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mes.MesMeetingInfo;
import com.hhz.ump.entity.mes.MesSummary;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.Util;

import org.apache.commons.lang.StringUtils;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;


@Service
@Transactional
public class MesSummaryManager extends BaseService<MesSummary, String> {
    @Autowired
    private MesSummaryDao mesSummaryDao;
    @Autowired
    private MesMeetingInfoManager mesMeetingInfoManager;
    private HelperUtil mesHelper = new HelperUtil();

    public void saveMesSummary(Map input) throws Exception {
        if (!input.containsKey("mesSummary"))
            throw new RuntimeException("mesSummary 不能为空");

        //保存纪要信息
        MesSummary mesSummary = (MesSummary) input.get("mesSummary");
        PowerUtils.setEmptyStr2Null(mesSummary);
        mesSummaryDao.save(mesSummary);

        if (input.containsKey("mesMeetingInfo")) {
            mesMeetingInfoManager.saveMesMeetingInfo(input);
        }
    }

    public void saveNewMesSummary(Map input) throws Exception {
        if (!input.containsKey("mesSummary"))
            throw new RuntimeException("mesSummary 不能为空");


        String currentUiid = SpringSecurityUtils.getCurrentUiid();

        //保存纪要信息
        MesSummary mesSummary = (MesSummary) input.get("mesSummary");
        //纪要信息
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) input.get("mesMeetingInfo");

        boolean isUpdateContent = !Util.clob2String(mesSummary.getMesContent()).equalsIgnoreCase(input.get("content").toString());
        if (null != mesMeetingInfo && !StringUtils.equalsIgnoreCase(mesSummary.getCreator(), currentUiid) && isUpdateContent) {
            StringBuffer hql = new StringBuffer("update MesSummary m set m.isCurrent = false where m.mesMeetingInfo.mesMeetingInfoId=? ");
            mesSummaryDao.batchExecute(hql.toString(), mesMeetingInfo.getMesMeetingInfoId());
            MesSummary newMesSummary = new MesSummary();
            newMesSummary.setMesVersion(String.valueOf(mesMeetingInfo.getMesSummaries().size()));
            newMesSummary.setIsCurrent(true);
            newMesSummary.setMesContent(new ClobImpl(String.valueOf(input.get("content"))));
            newMesSummary.setMesMeetingInfo(mesMeetingInfo);
            mesSummaryDao.save(newMesSummary);
        } else {
            PowerUtils.setEmptyStr2Null(mesSummary);
            mesSummary.setMesContent(new ClobImpl(String.valueOf(input.get("content"))));
            mesSummaryDao.save(mesSummary);
        }

        if (input.containsKey("mesMeetingInfo")) {
            mesMeetingInfoManager.saveMesMeetingInfo(input);
        }
    }

    public void deleteMesSummary(String id) {
        mesSummaryDao.delete(id);
    }

    @Override
    public HibernateDao<MesSummary, String> getDao() {
        return mesSummaryDao;
    }

}

