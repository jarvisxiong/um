package com.hhz.ump.dao.mes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.mes.MesApproveHis;
import com.hhz.ump.entity.mes.MesApproveNode;
import com.hhz.ump.entity.mes.MesMeetingInfo;
import com.hhz.ump.entity.mes.MesMeetingPush;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.EmailUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.HtmlToWordUtil;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasUser;


/**
 * Created by IntelliJ IDEA.
 * User: zhanggaojiang
 * Date: 2012-2-13
 * Time: 下午13:19
 * 会议纪要Manager
 */

@Service
@Transactional
public class MesMeetingInfoManager extends BaseService<MesMeetingInfo, String> {

    private static Log logger = LogFactory.getLog(MesMeetingInfoManager.class);
    @Autowired
    private MesApproveNodeManager mesApproveNodeManager;

    @Autowired
    private AppAttachFileManager appAttachFileManager;

    @Autowired
    private MesApproveHisManager mesApproveHisManager;

    //新增/初始状态
    public static String AUDITING_STATUS_INIT = "0";
    //纪要填写
    public static String AUDITING_STATUS_WRITE = "1";
    //审批中
    public static String AUDITING_STATUS_PROCESSINGS = "2";
    //完成/通过
    public static String AUDITING_STATUS_SUCCESS = "3";
    //驳回/失败
    public static String AUDITING_STATUS_FIAL = "4";
    //删除
    public static String AUDITING_STATUS_DELETE = "5";

    //审核状态 字符常量
    public static String AUDITING_STATUS_KEY = "auditing_status_key";

    public static String FILE_UPLOADER_SAVE_DIRECTORY = "mesmeeting";

    public static File savePathDirectory = new File(PowerUtils.getFilePath(FILE_UPLOADER_SAVE_DIRECTORY, true));
    public final static SimpleDateFormat publishDateFormat = new SimpleDateFormat("yyyy年MM月dd日印发");
    public final static SimpleDateFormat startDateFormate = new SimpleDateFormat("yyyy年MM月dd日 aa HH:mm");
    public final static SimpleDateFormat endDateFormate = new SimpleDateFormat("HH:mm");
    public final static HelperUtil mesHelper = new HelperUtil();

    @Autowired
    private MesMeetingInfoDao mesMeetingInfoDao;
    @Autowired
    private JbpmTaskManager jbpmTaskManager;
    @Autowired
    private JbpmTaskCandidateManager jbpmTaskCandidateManager;
    @Autowired
    private AppDictTypeManager appDictTypeManager;
    @Autowired
    private MesMeetingPushManager mesMeetingPushManager;

    /**
     * 同步到待办实现
     *
     * @param mesMeetingInfo
     */
    private void update2JbpmTask(MesMeetingInfo mesMeetingInfo) {
        JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(mesMeetingInfo.getMesMeetingInfoId());
        if (mesMeetingInfo.getStatusCd().equals(AUDITING_STATUS_WRITE) || mesMeetingInfo.getStatusCd().equals(AUDITING_STATUS_FIAL) || StringUtils.isNotBlank(mesMeetingInfo.getUserCd())) {//纪要填写步骤，或审批人不为空

            if (jbpmTask == null) {
                jbpmTask = new JbpmTask();
                jbpmTask.setModuleCd("mesMeetingInfo");
                jbpmTask.setModuleName("纪要决议");
                jbpmTask.setRemark(mesMeetingInfo.getSubject());
                String mesTypeName = appDictTypeManager.getDictNameByCd(DictContants.MES_MEETING_CATEGORY, mesMeetingInfo.getMesTypeCd());
                jbpmTask.setJbpmCd("纪【" + mesMeetingInfo.getMesYear() + "】" + mesTypeName + mesMeetingInfo.getSerialNo() + "号");
                jbpmTask.setUserCd(mesMeetingInfo.getSumUserCd());
                jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(mesMeetingInfo.getSumUserCd()));
                jbpmTask.setApplyDate(mesMeetingInfo.getStartDate());
                jbpmTask.setJbpmId(mesMeetingInfo.getMesMeetingInfoId());
            }
            jbpmTask.setStatusCd(mesMeetingInfo.getStatusCd());
            jbpmTaskManager.saveJbpmTask(jbpmTask);
            String userCd = null;
            if (mesMeetingInfo.getStatusCd().equals(AUDITING_STATUS_FIAL) || mesMeetingInfo.getStatusCd().equals(AUDITING_STATUS_WRITE)) {//驳回,或纪要填写
                userCd = mesMeetingInfo.getSumUserCd();//纪要人

            } else {
                userCd = mesMeetingInfo.getUserCd();//审批人
            }
            if (userCd != null) {
                //先删除，再插入
                jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
                JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
                jbpmTaskCandidate.setUserCd(userCd);
                jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(userCd));
                jbpmTaskCandidate.setJbpmTask(jbpmTask);
                jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
            }
        } else {
            if (jbpmTask != null) {
                jbpmTaskManager.delete(jbpmTask);
            }
        }

    }

    public void saveMesMeetingInfo(MesMeetingInfo mesMeetingInfo) throws Exception {
        PowerUtils.setEmptyStr2Null(mesMeetingInfo);
        if (StringUtils.isBlank(mesMeetingInfo.getMesMeetingInfoId()) || isExistsSerialNo(mesMeetingInfo.getMesYear(), mesMeetingInfo.getMesTypeCd(), mesMeetingInfo.getMesMeetingInfoId(), mesMeetingInfo.getSerialNo())) {
            mesMeetingInfo.setSerialNo(generateSerialNo(String.valueOf(mesMeetingInfo.getMesYear()), mesMeetingInfo.getMesTypeCd(), String.valueOf(mesMeetingInfo.getSerialNo())));
        }
        mesMeetingInfoDao.save(mesMeetingInfo);
        update2JbpmTask(mesMeetingInfo);

    }

    //保存 纪要信息 到数据库
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMesMeetingInfo(Map input) throws Exception {
        if (!input.containsKey("mesMeetingInfo"))
            throw new RuntimeException("MesMeetingInfo 不能为空");
        //CurrentLoginUserUiid
        String uiid = SpringSecurityUtils.getCurrentUiid();
        //保存纪要信息
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) input.get("mesMeetingInfo");
        saveMesMeetingInfo(mesMeetingInfo);

        //生成审核步骤
        if (input.containsKey("approves") && null != input.get("approves") && ((String[]) input.get("approves")).length > 0) {
            //清空 审批节点
            mesApproveNodeManager.delete(mesMeetingInfo.getMesApproveNodes());
            //生成新的
            int index = 0;
            for (String approve : (String[]) input.get("approves")) {
                WsPlasUser wsPlasUser = CodeNameUtil.getUserByUiid(approve);
                if (null != wsPlasUser) {
                    MesApproveNode mesApproveNode = new MesApproveNode();
                    mesApproveNode.setApproveLevel(Long.valueOf(index));
                    mesApproveNode.setApproveOptionCd(AUDITING_STATUS_INIT);
                    // mesApproveNode.setNodeName(approve);
                    mesApproveNode.setUserCd(approve);
                    //默认 时限 24小时 结束
                    mesApproveNode.setTimeLimit(24l);
                    mesApproveNode.setMesMeetingInfo(mesMeetingInfo);
                    mesApproveNode.setWorkDutyDesc(wsPlasUser.getWorkDutyDesc());
                    //mesApproveNode.setRemark("当前审批节点位于第" + index + "，审批人" + wsPlasUser.getUserName());
                    mesApproveNodeManager.saveMesApproveNode(mesApproveNode);
                    index = index + 1;
                }
            }
        }

        //附件保存
        if (input.containsKey("fileuploader") && null != input.get("fileuploader") && ((File[]) input.get("fileuploader")).length > 0) {
            if (!savePathDirectory.exists()) {
                savePathDirectory.mkdirs();
            }
            File[] fileuploader = ((File[]) input.get("fileuploader"));
            String[] fileuploaderContentType = (String[]) input.get("fileuploaderContentType");
            String[] fileuploaderFileName = (String[]) input.get("fileuploaderFileName");
            for (int i = 0; i < fileuploader.length; i++) {
                String newFileName = HelperUtil.getFileName(fileuploaderFileName[i]);
                File fout = new File(savePathDirectory, newFileName);
                FileOutputStream fos = new FileOutputStream(fout);
                FileInputStream fin = new FileInputStream(fileuploader[i]);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fin.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                AppAttachFile attachFile = new AppAttachFile();
                attachFile.setBizModuleCd(FILE_UPLOADER_SAVE_DIRECTORY);
                attachFile.setBizEntityId(mesMeetingInfo.getMesMeetingInfoId());
                attachFile.setRealFileName(fileuploaderFileName[i]);
                attachFile.setFileName(newFileName);
                attachFile.setFilePath(savePathDirectory.getAbsolutePath());
                attachFile.setFileTypeName(fileuploaderContentType[i]);
                attachFile.setFileSize(new BigDecimal(fout.length()));
                attachFile.setStatusCd("1");

                Long mbLength = (fout.length() / (1024 * 1024));
                if (mbLength > 20)
                    throw new RuntimeException("第" + (i + 1) + "个文件：" + fileuploaderFileName[i] + "超过20MB 限制，服务器拒绝存储。");

                appAttachFileManager.saveAppAttachFile(attachFile);
                mesMeetingInfo.setAttachFlg("1");
            }
        }
        mesMeetingInfoDao.save(mesMeetingInfo);
    }

    /**
     * 上传决议
     *
     * @param input
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadRst(Map input) throws Exception {
        if (!input.containsKey("mesMeetingInfo"))
            throw new RuntimeException("MesMeetingInfo 不能为空");
        //保存纪要信息
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) input.get("mesMeetingInfo");

        //附件保存
        if (input.containsKey("fileuploader") && null != input.get("fileuploader") && ((File[]) input.get("fileuploader")).length > 0) {
            if (!savePathDirectory.exists()) {
                savePathDirectory.mkdirs();
            }
            File[] fileuploader = ((File[]) input.get("fileuploader"));
            String[] fileuploaderContentType = (String[]) input.get("fileuploaderContentType");
            String[] fileuploaderFileName = (String[]) input.get("fileuploaderFileName");
            for (int i = 0; i < fileuploader.length; i++) {
                String newFileName = HelperUtil.getFileName(fileuploaderFileName[i]);
                File fout = new File(savePathDirectory, newFileName);
                FileOutputStream fos = new FileOutputStream(fout);
                FileInputStream fin = new FileInputStream(fileuploader[i]);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fin.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                AppAttachFile attachFile = new AppAttachFile();
                attachFile.setBizModuleCd(FILE_UPLOADER_SAVE_DIRECTORY);
                attachFile.setBizEntityId(mesMeetingInfo.getMesMeetingInfoId());
                attachFile.setBizFieldName("rstAttachmentId");
                attachFile.setRealFileName(fileuploaderFileName[i]);
                attachFile.setFileName(newFileName);
                attachFile.setFilePath(savePathDirectory.getAbsolutePath());
                attachFile.setFileTypeName(fileuploaderContentType[i]);
                attachFile.setFileSize(new BigDecimal(fout.length()));
                attachFile.setStatusCd("1");

                Long mbLength = (fout.length() / (1024 * 1024));
                if (mbLength > 20)
                    throw new RuntimeException("第" + (i + 1) + "个文件：" + fileuploaderFileName[i] + "超过20MB 限制，服务器拒绝存储。");
                appAttachFileManager.saveAppAttachFile(attachFile);
                mesMeetingInfo.setRstAttachmentId(attachFile.getAppAttachFileId());
            }
        }
        mesMeetingInfo.setIsRst(true);
        mesMeetingInfo.setRstCompleteDate(new Date());
        mesMeetingInfoDao.save(mesMeetingInfo);
    }

    public void deleteMesMeetingInfo(String id) {
        mesMeetingInfoDao.delete(id);
    }

    @Override
    public HibernateDao<MesMeetingInfo, String> getDao() {
        return mesMeetingInfoDao;
    }

    private StringBuffer builderSql(Map params) {
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        StringBuffer queryString = new StringBuffer("from MesMeetingInfo t1 where 1=1");
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) params.get("entity");
        if (null != mesMeetingInfo) {
            //年份
            if (mesMeetingInfo.getMesYear() > 0) {
                queryString.append(" AND to_char(t1.fromDate,'yyyy') = :mesYear");
                params.put("mesYear", String.valueOf(mesMeetingInfo.getMesYear()));
            }
            //主题
            if (StringUtils.isNotBlank(mesMeetingInfo.getSubject())) {
                queryString.append(" AND (t1.subject like :subject OR t1.mesTitle like :subject)");
                params.put("subject", "%" + mesMeetingInfo.getSubject() + "%");
            }
            //类别
            if (StringUtils.isNotBlank(mesMeetingInfo.getMesTypeCd())) {
                queryString.append(" AND t1.mesTypeCd in(:mesTypeCd)");
                params.put("mesTypeCd", mesMeetingInfo.getMesTypeCd().replace(" ", "").split(","));
            }
            //主持人
            if (StringUtils.isNotBlank(mesMeetingInfo.getCompere())) {
                queryString.append(" AND t1.compere=:compere");
                params.put("compere", mesMeetingInfo.getCompere());
            }
            //召集人
            if (StringUtils.isNotBlank(mesMeetingInfo.getApplicant())) {
                queryString.append(" AND t1.applicant=:applicant");
                params.put("applicant", mesMeetingInfo.getApplicant());
            }
            //纪要人
            if (StringUtils.isNotBlank(mesMeetingInfo.getSumUserCd())) {
                queryString.append(" AND t1.sumUserCd=:sumUserCd");
                params.put("sumUserCd", mesMeetingInfo.getSumUserCd());
            }

            //参与人
            if (StringUtils.isNotBlank(mesMeetingInfo.getParticipators())) {
                queryString.append(" AND t1.participators  like :participators");
                params.put("participators", "%" + mesMeetingInfo.getParticipators() + "%");
            }
            //会议室
            if (StringUtils.isNotBlank(mesMeetingInfo.getMesRoom())) {
                queryString.append(" AND t1.mesRoom  like :mesRoom");
                params.put("mesRoom", "%" + mesMeetingInfo.getMesRoom() + "%");
            }
            //会议日期
            if (null != mesMeetingInfo.getFromDate()) {
                queryString.append(" AND to_char(t1.fromDate,'yyyy-MM-dd')=:fromDate");
                params.put("fromDate", DateOperator.defaultDateFormatter.format(mesMeetingInfo.getFromDate()));
            }
        }

        if (null != params.get("recodeType") && StringUtils.isNotBlank(String.valueOf(params.get("recodeType")))) {
            int recodeType = Integer.valueOf(params.get("recodeType").toString());
            int status = Integer.valueOf(params.get("status").toString());
            if (recodeType == 0 && status != -1) {
                params.put("currentUser", currentUiid);
                if (status == 0) {
                    //我新增的，待我填写纪要的，我新增的待别人填写纪要的/待我审批的。
                    queryString.append(" AND (");
                    if (SpringSecurityUtils.hasRole("A_MES_ADMIN") || SpringSecurityUtils.hasRole("A_MES_READ_ALL")) {
                        queryString.append(" t1.statusCd in(:AUDITING_STATUS_WRITE,:AUDITING_STATUS_PROCESSINGS,:AUDITING_STATUS_FIAL )");
                        queryString.append("                OR  ");
                        queryString.append("(t1.creator=:currentUser   AND t1.statusCd=:AUDITING_STATUS_INIT) ");
                    } else {
                        //我新增的，
                        queryString.append("            (t1.creator=:currentUser   AND t1.statusCd=:AUDITING_STATUS_INIT) ");
                        queryString.append("                OR  ");
                        //待我填写纪要的
                        queryString.append("            (t1.sumUserCd=:currentUser   AND t1.statusCd=:AUDITING_STATUS_WRITE) ");
                        queryString.append("                OR  ");
                        //我新增的待别人填写纪要的
                        queryString.append("            (t1.creator=:currentUser   AND t1.statusCd=:AUDITING_STATUS_WRITE) ");
                        queryString.append("                OR  ");
                        //待我审批的
                        queryString.append("            (t1.userCd=:currentUser   AND t1.statusCd=:AUDITING_STATUS_PROCESSINGS) ");
                        queryString.append("                OR  ");
                        //被驳回的，
                        queryString.append("            (t1.sumUserCd=:currentUser  AND t1.statusCd=:AUDITING_STATUS_FIAL) ");
                    }
                    queryString.append("     )");
                } else if (status == 1) {
                    //与我有关 在审批过程中的。
                    queryString.append(" AND (");
                    //与我有关。
                    queryString.append("            (t1.creator=:currentUser OR t1.sumUserCd=:currentUser OR exists(from MesApproveHis t2 where t2.mesMeetingInfo=t1 AND t2.userCd=:currentUser)) ");
                    queryString.append("                AND  ");
                    //还在审批过程中的
                    queryString.append("            t1.statusCd=:AUDITING_STATUS_PROCESSINGS");
                    queryString.append("     )");
                } else if (status == 2) {
                    //与我有关 在审批过程中的。
                    queryString.append(" AND (");
                    //与我有关。
                    queryString.append("            (t1.creator=:currentUser OR t1.sumUserCd=:currentUser OR exists(from MesApproveHis t2 where t2.mesMeetingInfo=t1 AND t2.userCd=:currentUser)) ");
                    queryString.append("                AND  ");
                    //还在审批过程中的
                    queryString.append("            t1.statusCd=:AUDITING_STATUS_SUCCESS");
                    queryString.append("     )");
                }
            } else if (recodeType != 0) {
                if (status == -1) {
                    queryString.append(" AND t1.statusCd=:AUDITING_STATUS_SUCCESS");
                } else {
                    queryString.append(" AND t1.statusCd=:STATUS_CUSTOMER");
                    params.put("STATUS_CUSTOMER", String.valueOf(status));
                }
                //纪要归档，管理员查看全部。
                /*if (recodeType == 1) {} else */
                //决议归档
                if (recodeType == 2) {
                    queryString.append(" AND t1.isRst=:isRst");
                    params.put("isRst", true);
                }
            }
        }

        params.put("AUDITING_STATUS_INIT", MesMeetingInfoManager.AUDITING_STATUS_INIT);
        params.put("AUDITING_STATUS_WRITE", MesMeetingInfoManager.AUDITING_STATUS_WRITE);
        params.put("AUDITING_STATUS_SUCCESS", MesMeetingInfoManager.AUDITING_STATUS_SUCCESS);
        params.put("AUDITING_STATUS_FIAL", MesMeetingInfoManager.AUDITING_STATUS_FIAL);
        params.put("AUDITING_STATUS_PROCESSINGS", MesMeetingInfoManager.AUDITING_STATUS_PROCESSINGS);

        return queryString;
    }


    /**
     * 多条件搜索 会议纪要信息
     *
     * @param params
     * @return 分页列表
     */
    public Page findMesMeetingInfosByConditions(Page pager, Map params) {
        StringBuffer queryString = builderSql(params);
        if (pager.isOrderBySetted()) {
            queryString.append(" order by ").append(pager.getOrderBy());
            queryString.append(" ").append(pager.getOrder());
        }
        //queryString.append(" order by t1.startDate desc");
        return mesMeetingInfoDao.findPage(pager, queryString.toString(), params);
    }

    /**
     * 生成序号
     *
     * @param year
     * @return
     */
    public long generateSerialNo(String year, String mesTypeCd, String mes_meeting_info_id) {
        Map params = new HashMap();
        params.put("year", year);
        params.put("mesTypeCd", mesTypeCd);
        params.put("mes_meeting_info_id", mes_meeting_info_id);
        StringBuffer queryString = new StringBuffer("select max(t1.serial_no)+1 from MES_MEETING_INFO t1 where 1=1");
        if (StringUtils.isNotBlank(year)) {
            queryString.append(" and to_char(t1.from_date,'yyyy') =:year");

        }
        if (StringUtils.isNotBlank(mesTypeCd)) {
            queryString.append(" and t1.mes_type_cd =:mesTypeCd");

        }
        if (StringUtils.isNotBlank(mes_meeting_info_id)) {
            queryString.append(" and t1.mes_meeting_info_id != :mes_meeting_info_id");
        }
        List values = mesMeetingInfoDao.findBySql(queryString.toString(), params);
        return null == values.get(0) ? 1 : ((BigDecimal) values.get(0)).longValue();
    }

    public boolean isExistsSerialNo(long year, String mesTypeCd, String mes_meeting_info_id, long serialNo) {
        Map params = new HashMap();
        params.put("year", year);
        params.put("mesTypeCd", mesTypeCd);
        params.put("mes_meeting_info_id", mes_meeting_info_id);
        params.put("SERIAL_NO", serialNo);
        StringBuffer queryString = new StringBuffer("select count(*) from MES_MEETING_INFO t1 where 1=1");
        queryString.append(" and to_char(t1.from_date,'yyyy') =:year");
        queryString.append(" and t1.mes_type_cd =:mesTypeCd");
        queryString.append(" and t1.mes_meeting_info_id != :mes_meeting_info_id");
        queryString.append(" and t1.SERIAL_NO=:SERIAL_NO ");
        List values = mesMeetingInfoDao.findBySql(queryString.toString(), params);
        return null == values.get(0) ? false : ((BigDecimal) values.get(0)).longValue() != 0;
    }


    /**
     * 审批 初始
     *
     * @param input
     */
    public void workProcessBegin(Map input) throws Exception {
        if (!input.containsKey("mesMeetingInfo"))
            throw new RuntimeException("MesMeetingInfo 不能为空");
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) input.get("mesMeetingInfo");
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        if ((!mesMeetingInfo.getStatusCd().equalsIgnoreCase(MesMeetingInfoManager.AUDITING_STATUS_WRITE) && !mesMeetingInfo.getStatusCd().equalsIgnoreCase(MesMeetingInfoManager.AUDITING_STATUS_FIAL)) || !mesMeetingInfo.getSumUserCd().equalsIgnoreCase(currentUiid))
            throw new RuntimeException("无权限对当前环节进行操作～～！");

        //第一个环节
        MesApproveNode mesApproveNode = mesApproveNodeManager.getMesApproveNodeByFirst(mesMeetingInfo.getMesMeetingInfoId());
        mesMeetingInfo.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_PROCESSINGS);
        mesMeetingInfo.setStartDate(new Date());
        mesMeetingInfo.setUserCd(mesApproveNode.getUserCd());
        mesMeetingInfo.setApproveLevel(mesApproveNode.getApproveLevel());
        saveMesMeetingInfo(mesMeetingInfo);
        input.put("message", "纪要填写完成，已发送至 " + CodeNameUtil.getUserNameByCd(mesApproveNode.getUserCd()) + " 进行审核。");
    }

    /**
     * 审批 处理中
     *
     * @return
     */
    public void workProcessing(Map input) throws Exception {
        if (!input.containsKey("mesMeetingInfo"))
            throw new RuntimeException("MesMeetingInfo 不能为空");

        WsPlasAcct currentUser = SpringSecurityUtils.getCurrentPlasUser();
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) input.get("mesMeetingInfo");

        //获取当前环节处理
        MesApproveNode mesApproveNode = mesApproveNodeManager.getMesApproveNodeByCurrent(mesMeetingInfo.getMesMeetingInfoId());
        if (!mesApproveNode.getUserCd().equalsIgnoreCase(currentUser.getUiid()))
            throw new RuntimeException("无权限对当前环节进行审核～～！");

        mesApproveNode.setApproveRemark(input.get("approveRemark") == null ? null : input.get("approveRemark").toString());
        mesApproveNode.setApproveDate(new Date());


        //修改审批状态
        if ((Boolean) input.get(AUDITING_STATUS_KEY)) {
            //同意
            mesApproveNode.setApproveOptionCd(MesMeetingInfoManager.AUDITING_STATUS_SUCCESS);
            MesApproveNode currentmesApproveNode = mesApproveNodeManager.getMesApproveNodeByNext(mesMeetingInfo.getMesMeetingInfoId());
            //流转进入下一流程。
            if (null != currentmesApproveNode) {
                mesMeetingInfo.setApproveLevel(currentmesApproveNode.getApproveLevel());
                mesMeetingInfo.setUserCd(currentmesApproveNode.getUserCd());
                mesMeetingInfo.setStartDate(new Date());
                //流转中
                mesMeetingInfo.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_PROCESSINGS);
                input.put("message", "纪要填写完成，已发送至 " + CodeNameUtil.getUserNameByCd(currentmesApproveNode.getUserCd()) + " 进行审核。");
            } else {
                //流转结束 状态 OK
                mesMeetingInfo.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_SUCCESS);
                mesMeetingInfo.setUserCd(null);
                mesMeetingInfo.setStartDate(new Date());
                mesMeetingInfo.setApproveLevel(-1l);
                input.put("message", "纪要审核完成，已归档至历史档案记录列表！所有相关人员已经可以查看。");

                //纪要流转结束,发送邮件给参与人员
                //workProcessEnd(input);
            }
        } else {
            //驳回 重新开始
            mesApproveNode.setApproveOptionCd(MesMeetingInfoManager.AUDITING_STATUS_FIAL);
            mesMeetingInfo.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_FIAL);
            mesMeetingInfo.setUserCd(null);
            mesMeetingInfo.setStartDate(new Date());
            mesMeetingInfo.setApproveLevel(null);
            input.put("message", "纪要审核完成，已驳回至 " + CodeNameUtil.getUserNameByCd(mesMeetingInfo.getSumUserCd()) + " 重新填写纪要。");
        }

        //生成 审批历史表
        MesApproveHis mesApproveHis = new MesApproveHis();
        mesApproveHis.setUserCd(mesApproveNode.getUserCd());
        mesApproveHis.setApproveDate(mesApproveNode.getApproveDate());
        mesApproveHis.setTimeLimit(mesApproveNode.getTimeLimit());
        mesApproveHis.setApproveOptionCd(mesApproveNode.getApproveOptionCd());
        mesApproveHis.setApproveRemark(mesApproveNode.getApproveRemark());
        mesApproveHis.setMesMeetingInfo(mesMeetingInfo);
        mesApproveHis.setApproveLevel(mesApproveNode.getApproveLevel());
        mesApproveHis.setWorkDutyDesc(mesApproveNode.getWorkDutyDesc());
        mesApproveHisManager.saveMesApproveHis(mesApproveHis);
        saveMesMeetingInfo(mesMeetingInfo);
    }

    /**
     * 审批 处理结束
     * 发送邮件给参与人员
     *
     * @param input
     */

    public void sendMeetingInfoEmail(Map input) throws Exception {
        MesMeetingInfo mesMeetingInfo = (MesMeetingInfo) input.get("mesMeetingInfo");
        StringBuffer subject = new StringBuffer("【纪要发布】").append(mesMeetingInfo.getMesTitle());
        String content = Util.clob2String(mesMeetingInfo.getMesSummaries().get(mesMeetingInfo.getMesSummaries().size() - 1).getMesContent());
        String[] toUsers = StringUtils.isBlank(mesMeetingInfo.getParticipators()) ? new String[]{} : mesMeetingInfo.getParticipators().split(";");
        String[] ccUsers = StringUtils.isBlank(mesMeetingInfo.getCcUsers()) ? new String[]{} : mesMeetingInfo.getCcUsers().split(";");

        Map<String, String> params = new HashMap();
        params.put("mesYear", String.valueOf(mesMeetingInfo.getMesYear()));
        params.put("mesTypeCd", String.valueOf(mesHelper.getCategorys().get(mesMeetingInfo.getMesTypeCd())));
        params.put("serialNo", String.valueOf(mesMeetingInfo.getSerialNo()));
        params.put("mesTitle", mesMeetingInfo.getMesTitle());
        params.put("subject", mesMeetingInfo.getSubject());
        params.put("startDate", startDateFormate.format(mesMeetingInfo.getFromDate()) + "-" + endDateFormate.format(mesMeetingInfo.getToDate()));
        params.put("mesRoom", mesMeetingInfo.getMesRoom());
        params.put("compere", mesHelper.getDataDictionary().getUserNameByCd(mesMeetingInfo.getCompere()));
        params.put("participators", mesHelper.getDataDictionary().getUserNamesByUiids(mesMeetingInfo.getParticipators(), ";"));
        params.put("ccUsers", mesHelper.getDataDictionary().getUserNamesByUiids(mesMeetingInfo.getCcUsers(), ";"));
        params.put("recorder", mesHelper.getDataDictionary().getUserNameByCd(mesMeetingInfo.getSumUserCd()));
        params.put("summary", content);

        params.put("meetingDate", DateOperator.formatDate(mesMeetingInfo.getFromDate(), "yyyy年MM月dd日"));
        params.put("sumUserName", CodeNameUtil.getUserNameByCd(mesMeetingInfo.getSumUserCd()));

        params.put("href", "http://" + ServletActionContext.getRequest().getLocalAddr() + Struts2Utils.getRequest().getContextPath() + "/mes/mes-summary!readonly.action?mesMeetingInfoId=" + mesMeetingInfo.getMesMeetingInfoId());

        String template = HelperUtil.getHtmlTemplate("mes/mesmeetinginfo.html");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = null == entry.getValue() ? "" : entry.getValue();
            template = template.replace(key, value);
        }
        /**
         * 邮件发送
         */
        String[] toUserEmails = new String[toUsers.length];
        String[] ccUserEmails = new String[ccUsers.length];
        if (input.containsKey("pushUsers")) {
            //推送邮件
            String[] pushUsers = (String[]) input.get("pushUsers");
            toUserEmails = new String[pushUsers.length];
            ccUserEmails = new String[0];
            for (int i = 0; i < pushUsers.length; i++) {
                toUserEmails[i] = pushUsers[i] + "@powerlong.com";
            }
        } else {
            if (!input.containsKey("sendme")) {
                for (int i = 0; i < toUsers.length; i++) {
                    toUserEmails[i] = toUsers[i] + "@powerlong.com";
                }
                for (int i = 0; i < ccUsers.length; i++) {
                    ccUserEmails[i] = ccUsers[i] + "@powerlong.com";
                }
            } else {
                toUserEmails = new String[]{mesMeetingInfo.getCreator() + "@powerlong.com"};
                ccUserEmails = new String[0];
            }
        }
        String rootPath = ServletActionContext.getServletContext().getRealPath("");
        String realPath = Struts2Utils.getRequest().getContextPath();
        EmailUtil.send("纪要发布", SpringSecurityUtils.getCurrentUiid(), LoginUtil.getPwd(null), toUserEmails, ccUserEmails, null, subject.toString(), template, null, null, rootPath, realPath);
        if (!input.containsKey("sendme") && !input.containsKey("pushUsers")) {
            mesMeetingInfo.setEmailSendCnt(mesMeetingInfo.getEmailSendCnt() == null ? 1 : mesMeetingInfo.getEmailSendCnt() + 1);
            mesMeetingInfo.setLastSendDate(new Date());
            mesMeetingInfoDao.save(mesMeetingInfo);
        }
    }

    /**
     * 是否有追回 纪要 的 权限
     */
    public Integer isRollbackMesMeetingPermission(String mesMeetingInfoId) throws Exception {
        MesMeetingInfo mesMeetingInfo = getEntity(mesMeetingInfoId);
        if (null == mesMeetingInfo)
            throw new RuntimeException("纪要不存在:" + mesMeetingInfoId);
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        if ((mesMeetingInfo.getStatusCd().matches("1|4")) && mesMeetingInfo.getCreator().equalsIgnoreCase(currentUiid))
            //纪要填写状态,或者驳回状态,发起人可以追回。
            return 1;
        else if (mesMeetingInfo.getStatusCd().equalsIgnoreCase("2")) {
            List<MesApproveNode> mesApproveNodes = mesApproveNodeManager.getMesApproveNodeBySortLevel(mesMeetingInfoId);
            if (mesMeetingInfo.getApproveLevel() != 0 && mesApproveNodes.get(Long.valueOf(mesMeetingInfo.getApproveLevel() - 1).intValue()).getUserCd().equalsIgnoreCase(currentUiid))
                //审批状态；追回变为上一审批人。
                return 2;
            else if (mesMeetingInfo.getSumUserCd().equalsIgnoreCase(currentUiid))
                //审批状态；追回变为纪要填写。
                return 3;
        }
        return 0;
    }

    /**
     * 导出 审核状态为完成的 会议纪要 Word 格式;
     */
    public void export(String mesMeetingInfoId) throws Exception {

        MesMeetingInfo mesMeetingInfo = getEntity(mesMeetingInfoId);

        /*if (null == mesMeetingInfo || !StringUtils.equalsIgnoreCase(mesMeetingInfo.getStatusCd(), MesMeetingInfoManager.AUDITING_STATUS_SUCCESS))
            throw new RunTimeException("纪要不存在，或者审核未通过:" + mesMeetingInfoId);*/

        if (null == mesMeetingInfo)
            throw new RuntimeException("纪要不存在:" + mesMeetingInfoId);

        //提取模板需要数据 定义 模板变量集合
        Map<String, String> params = new HashMap<String, String>();
        params.put("bianhao", "纪【" + mesMeetingInfo.getMesYear() + "】" + mesHelper.getCategorys().get(mesMeetingInfo.getMesTypeCd()) + mesMeetingInfo.getSerialNo() + "号");
        params.put("jiyaobiaoti", mesMeetingInfo.getMesTitle());
        params.put("huiyishijian", startDateFormate.format(mesMeetingInfo.getFromDate()) + "-" + endDateFormate.format(mesMeetingInfo.getToDate()));
        params.put("huiyididian", mesMeetingInfo.getMesRoom());
        params.put("huiyizhuti", mesMeetingInfo.getSubject());
        params.put("huiyizhuchi", mesHelper.getDataDictionary().getUserNameByCd(mesMeetingInfo.getCompere()));
        params.put("canhuirenyuan", mesHelper.getDataDictionary().getUserNamesByUiids(mesMeetingInfo.getParticipators(), ";"));
        params.put("huiyijilu", mesHelper.getDataDictionary().getUserNameByCd(mesMeetingInfo.getSumUserCd()));
//        params.put("baosong", mesMeetingInfo.getReportUsers());
//        params.put("zhusong", mesMeetingInfo.getMainUsers());
        params.put("bumen", mesMeetingInfo.getRelatedDept());
        params.put("faburiqi", publishDateFormat.format(new Date()));

        //纪要内容 格式重新 整理
        String summary = Util.clob2String(mesMeetingInfo.getMesSummaries().get(mesMeetingInfo.getMesSummaries().size() - 1).getMesContent());
        //summary = summary.replaceAll("<\\/p>|<br\\/>|<br[^<>]*>", "\n").replaceAll("</?[^<>]*/?>", "").replaceAll("\r", "").replaceAll(" ", "").replaceAll("&nbsp;", "").replaceAll("　", "");
        params.put("jiyaoneirong", summary);


        //创建Web文档引用
        InputStream docsTemplate = MesMeetingInfoManager.class.getClassLoader().getResourceAsStream("docsTemplates/mesmeetinginfo.docx");
        XWPFDocument doc = new XWPFDocument(docsTemplate);

        //模板中变量替换
        //获取 doc 中 全部段落 引用
        XWPFParagraph jynrXWPFParagraph = null;
        List<XWPFParagraph> xwpfParagraphs = doc.getParagraphs();
        for (XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            //获取段落中 每一子段落 容器。
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : xwpfRuns) {
                //获取容器内文本。
                String text = xwpfRun.getText(xwpfRun.getTextPosition());
                if (null == text) {//空内容舍弃
                    continue;
                }
                //检查容器内文本 在 变量定义集合中是否存在，进行 内容替换。
                if (params.containsKey(text)) {
                    //遇到纪要 内容 字段 做特殊处理
                    if (text.equalsIgnoreCase("jiyaoneirong")) {
                        jynrXWPFParagraph = xwpfParagraph;
                        xwpfRun.setText("", 0);
                    } else {
                        //普通字段 内容替换
                        xwpfRun.setText(params.get(text), 0);
                    }
                }
            }
        }
        try {
            HtmlToWordUtil.proccessDocument(summary, jynrXWPFParagraph);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        String contextType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, "docx");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        StringBuffer sbFileName = new StringBuffer();
        sbFileName.append("纪【").append(mesMeetingInfo.getMesYear()).append("】");
        sbFileName.append(mesHelper.getCategorys().get(mesMeetingInfo.getMesTypeCd()));
        sbFileName.append(mesMeetingInfo.getSerialNo()).append("号");
        sbFileName.append("_").append(mesMeetingInfo.getMesTitle());
        String filename = sbFileName.toString();
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "UTF-8");
        }
        response.setContentType(contextType);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + ".docx\"");
        doc.write(response.getOutputStream());

    }

    /**
     * 历史会议记录选择
     *
     * @param params
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public List findByMeetingRoomHistoryDirectory(Map params) {
        StringBuffer queryBuffer = new StringBuffer("select room,room_number,addr_type,applicant,compere,recorder,subject,to_char(t3.begin_time,'HH24:mi') as begin_time,participators,part_num,to_char(t3.end_time,'HH24:mi') as end_time,subject from (")
                .append("    select t1.oa_chairman_reserve_id as room,")
                .append("        chairman_type as room_number,")
                .append("        'ZC' as addr_type,")
                .append("        t1.applicant,")
                .append("        t1.compere,")
                .append("        t1.recorder,")
                .append("        t1.subject,")
                .append("        t1.begin_time,")
                .append("        t1.participators,")
                .append("        t1.part_num, ")
                .append("        t1.status,")
                .append("        t1.end_time")
                .append("    from oa_chairman_reserve t1")
                .append("           union                ")
                .append("    select t2.oa_meeting_room_res_id as room,")
                .append("        t2.room_id as room_number,")
                .append("        t2.addr_type,")
                .append("        t2.applicant,")
                .append("        t2.compere,")
                .append("        t2.recorder,")
                .append("        t2.subject,")
                .append("        t2.begin_time,")
                .append("        t2.participators,")
                .append("        t2.part_num,")
                .append("        t2.status,")
                .append("        t2.end_time")
                .append("    from oa_meeting_room_res t2")
                .append(") t3 where 1=1 ");

        //申请成功
        queryBuffer.append(" AND t3.status=1");
        //会议已结束
        queryBuffer.append(" AND t3.end_time<sysdate");
        //时间限制
        if (params.containsKey("date")) {
            queryBuffer.append(" AND to_char(t3.begin_time,'yyyy-MM-dd') =:limitDate");
            params.put("limitDate", params.get("date"));
        }
        if (params.containsKey("category")) {
            //会议室为 总裁预约
            if (StringUtils.equalsIgnoreCase("0", params.get("category").toString())) {
                queryBuffer.append(" AND t3.addr_type='ZC'");
            } else if (StringUtils.equalsIgnoreCase("1", params.get("category").toString())) {
                queryBuffer.append(" AND t3.addr_type='GB'");
            } else if (StringUtils.equalsIgnoreCase("2", params.get("category").toString())) {
                queryBuffer.append(" AND t3.addr_type='SHC'");
            }
        }
        List<Object[]> values = getDao().findBySql(queryBuffer.toString(), params);
        for (Object[] objs : values) {
            for (int i = 0; i < objs.length; i++) {
                if (objs[i] instanceof java.sql.Date) {
                    objs[i] = new Date(((java.sql.Date) objs[i]).getTime());
                }
            }
        }
        return values;
    }

    /**
     * 推送
     */
    public void push(MesMeetingInfo mesMeetingInfo, String[] userCds) throws Exception {
        List<MesMeetingPush> pushsAdd = new ArrayList<MesMeetingPush>();
        Set<String> lstAdd = new HashSet<String>();
        loop:
        for (String cdTmp : userCds) {
            if (StringUtils.isNotEmpty(cdTmp)) {
                for (MesMeetingPush push : mesMeetingInfo.getMesMeetingPushs()) {
                    if (push.getUserCd().equals(cdTmp)) {
                        continue loop;
                    }
                }
                MesMeetingPush meetingPush = new MesMeetingPush();
                meetingPush.setUserCd(cdTmp);
                meetingPush.setMesMeetingInfo(mesMeetingInfo);
                pushsAdd.add(meetingPush);
                lstAdd.add(cdTmp);
            }
        }

        mesMeetingPushManager.saveMesMeetingPushs(pushsAdd);
//        mesMeetingPushManager.refeshPushs(mesMeetingInfo.getMesMeetingPushs(), pushs);
//        mesMeetingInfo.getMesMeetingPushs().clear();
//        mesMeetingInfo.getMesMeetingPushs().addAll(pushs);
        //发送邮件
        String[] emailUserCds = new String[lstAdd.size()];
        lstAdd.toArray(emailUserCds);
        Map params = new HashMap();
        params.put("mesMeetingInfo", mesMeetingInfo);
        params.put("pushUsers", userCds);
        sendMeetingInfoEmail(params);
    }


}

