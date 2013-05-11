package com.hhz.ump.dao.mes;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mes.MesApproveNode;
import com.hhz.ump.entity.mes.MesMeetingInfo;
import com.hhz.uums.entity.ws.WsPlasAcct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class MesApproveNodeManager extends BaseService<MesApproveNode, String> {
    @Autowired
    private MesApproveNodeDao mesApproveNodeDao;

    @Autowired
    private MesMeetingInfoDao mesMeetingInfoDao;

    public void saveMesApproveNode(MesApproveNode mesApproveNode) {
        PowerUtils.setEmptyStr2Null(mesApproveNode);
        mesApproveNodeDao.save(mesApproveNode);
    }

    public void deleteMesApproveNode(String id) {
        mesApproveNodeDao.delete(id);
    }

    @Override
    public HibernateDao<MesApproveNode, String> getDao() {
        return mesApproveNodeDao;
    }


    /**
     * 获取 审批环节
     *
     * @param mesMeetingInfoId
     * @return
     * @throws Exception
     */
    public List<MesApproveNode> getMesApproveNodeBySortLevel(String mesMeetingInfoId) throws Exception {
        WsPlasAcct currentUser = SpringSecurityUtils.getCurrentPlasUser();
        MesMeetingInfo mesMeetingInfo = mesMeetingInfoDao.get(mesMeetingInfoId);
        if (null == mesMeetingInfo)
            throw new Exception("MesMeetingInfo 不能为空");
        //获取环节信息， 整理 审批 顺序 获取当前所处环节;
        int index = -1;//-1代表 未开始审批
        List<MesApproveNode> mesApproveNodes = mesMeetingInfo.getMesApproveNodes();
        if (mesApproveNodes.isEmpty())
            throw new Exception("流程信息 不能为空");
        Collections.sort(mesApproveNodes, new Comparator<MesApproveNode>() {
            public int compare(MesApproveNode o1, MesApproveNode o2) {
                if (o1.getApproveLevel() > o2.getApproveLevel())
                    return 1;
                if (o1.getApproveLevel() < o2.getApproveLevel())
                    return -1;
                return 0;
            }
        });
        return mesApproveNodes;
    }

    //下一个审批环节
    public MesApproveNode getMesApproveNodeByNext(String mesMeetingInfoId) throws Exception {
        MesMeetingInfo mesMeetingInfo = mesMeetingInfoDao.get(mesMeetingInfoId);
        List<MesApproveNode> mesApproveNodes = getMesApproveNodeBySortLevel(mesMeetingInfoId);
        for (int i = 0; i < mesApproveNodes.size(); i++) {
            if (mesApproveNodes.get(i).getApproveLevel().longValue() == mesMeetingInfo.getApproveLevel().longValue()) {
                if (i + 1 < mesApproveNodes.size()) {
                    return mesApproveNodes.get(i + 1);
                }
            }
        }
        return null;
    }

    //当前审批节点
    public MesApproveNode getMesApproveNodeByCurrent(String mesMeetingInfoId) throws Exception {
        MesMeetingInfo mesMeetingInfo = mesMeetingInfoDao.get(mesMeetingInfoId);
        List<MesApproveNode> mesApproveNodes = getMesApproveNodeBySortLevel(mesMeetingInfoId);
        for (int i = 0; i < mesApproveNodes.size(); i++) {
            if (mesApproveNodes.get(i).getApproveLevel().longValue() == mesMeetingInfo.getApproveLevel().longValue()) {
                return mesApproveNodes.get(i);
            }
        }
        return null;
    }

    //第一个审批节点
    public MesApproveNode getMesApproveNodeByFirst(String mesMeetingInfoId) throws Exception {
        List<MesApproveNode> mesApproveNodes = getMesApproveNodeBySortLevel(mesMeetingInfoId);
        return mesApproveNodes.get(0);
    }
}

