package com.hhz.ump.web.mes;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.mes.MesApproveHisManager;
import com.hhz.ump.dao.mes.MesApproveNodeManager;
import com.hhz.ump.dao.mes.MesMeetingInfoManager;
import com.hhz.ump.dao.mes.MesSummaryManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.mes.MesApproveNode;
import com.hhz.ump.entity.mes.MesMeetingInfo;
import com.hhz.ump.entity.mes.MesSummary;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasUser;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhanggaojiang
 * Date: 2012-2-8
 * Time: 下午12:30
 * 会议纪要管理
 */
@Results({
        @Result(name = "index", type = "freemarker", location = "mes-meeting-info-index.ftl"),
        @Result(name = "input", type = "freemarker", location = "mes-meeting-info-input.ftl"),
        @Result(name = "list", type = "freemarker", location = "mes-meeting-info-list.ftl"),
        @Result(name = "history-directory", type = "freemarker", location = "mes-meeting-info-history-directory.ftl"),
        @Result(name = "save", type = "redirect", location = "mes-meeting-info!input.action", params = {"id",
                "${mesMeetingInfoId}"})})
public class MesMeetingInfoAction extends CrudActionSupport<MesMeetingInfo> {

    // 工具辅助类
    public HelperUtil helper = new HelperUtil();

    public MesMeetingInfo entity = new MesMeetingInfo();
    public MesSummary mesSummary = new MesSummary();

    @Autowired
    private MesMeetingInfoManager mesMeetingInfoManager;
    @Autowired
    private MesSummaryManager mesSummaryManager;
    @Autowired
    private AppAttachFileManager appAttachFileManager;
    @Autowired
    private MesApproveNodeManager mesApproveNodeManager;
    @Autowired
    private MesApproveHisManager mesApproveHisManager;

    // 会议开始时间
    public String startTime;
    // 会议结束时间
    public String endTime;

    // 会议审核人:
    public String[] approves;

    /**
     * 上传文件列表
     */
    public File[] fileuploader;
    public File[] filedata;

    /**
     * 上传文件名称
     */
    public String[] fileuploaderFileName;
    public String[] filedataFileName;

    /**
     * 以上传文件类型
     */
    public String[] fileuploaderContentType;

    @Override
    public String deleteBatch() throws Exception {
        return null;
    }

    @Override
    public String delete() throws Exception {
        return null;
    }

    @Override
    public void prepare() throws Exception {
        ServletActionContext.getRequest().setAttribute("currentUiid", SpringSecurityUtils.getCurrentUiid());
    }

    @Override
    protected void prepareModel() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            entity = mesMeetingInfoManager.getEntity(getId());
            Criterion conditions = Restrictions.eq("bizEntityId", entity.getMesMeetingInfoId());
            ServletActionContext.getRequest().setAttribute("appAttachFiles", appAttachFileManager.findBy(conditions));
        }

    }

    public void preparePush() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            entity = mesMeetingInfoManager.getEntity(getId());
        }
    }

    // ==============================================================================
    // 重写方法 Start

    /**
     * 默认页
     *
     * @return
     * @throws Exception
     */
    public String index() throws Exception {
        return "index";
    }

    /**
     * 新增/编辑-初始页
     *
     * @return
     * @throws Exception
     */
    @Override
    public String input() throws Exception {
        return "input";
    }

    /**
     * 搜索
     *
     * @return
     * @throws Exception
     */
    @Override
    public String list() throws Exception {
        Map params = new HashMap();
        params.put("recodeType", Struts2Utils.getParameter("recodeType"));
        params.put("entity", entity);
        params.put("status",
                StringUtils.isNotBlank(Struts2Utils.getParameter("status")) ? Struts2Utils.getParameter("status") : "0");
        mesMeetingInfoManager.findMesMeetingInfosByConditions(page, params);
        return "list";
    }

    /**
     * 新增,修改
     *
     * @return
     * @throws Exception
     */
    @Override
    public String save() throws Exception {
        // 组装 会议开始 与结束日期
        Date fromDate = entity.getFromDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.set(Calendar.HOUR, Integer.valueOf(startTime.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(startTime.split(":")[1]));
        entity.setFromDate(calendar.getTime());
        calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.set(Calendar.HOUR, Integer.valueOf(endTime.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(endTime.split(":")[1]));
        entity.setToDate(calendar.getTime());

        // 审批时限默认24小时
        entity.setTimeLimit(24l);

        // 审核级别
        entity.setApproveLevel(-1l);

        // 首次创建 会议 状态初始化为 新建。
        entity.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_INIT);

        Map input = new HashMap();
        input.put("mesMeetingInfo", entity);
        input.put("approves", approves);
        input.put("fileuploader", fileuploader);
        input.put("fileuploaderContentType", fileuploaderContentType);
        input.put("fileuploaderFileName", fileuploaderFileName);

        boolean success = true;
        String message = "操作成功";
        try {
            mesMeetingInfoManager.saveMesMeetingInfo(input);
        } catch (Exception ex) {
            success = false;
            message = ex.getMessage();
        }
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<script type=\"text/javascript\">");
        htmlBuffer.append("parent.execute({id:\"" + entity.getMesMeetingInfoId() + "\",start:"
                + Struts2Utils.getParameter("start") + ",success:" + success + ",message:\"" + message + "\"})");
        htmlBuffer.append("</script>");
        Struts2Utils.renderHtml(htmlBuffer.toString());
        helper.nocache();
        return null;
    }

    /**
     * 上传决议
     *
     * @return
     * @throws Exception
     */
    public String uploadRst() throws Exception {
        boolean success = true;
        String message = "操作成功!";
        Map input = new HashMap();
        input.put("mesMeetingInfo", entity = mesMeetingInfoManager.getEntity(Struts2Utils.getParameter("mesMeetingInfoId")));
        input.put("fileuploader", fileuploader);
        input.put("fileuploaderContentType", fileuploaderContentType);
        input.put("fileuploaderFileName", fileuploaderFileName);
        try {
            entity.setRstSubject(Struts2Utils.getParameter("rstSubject"));
            entity.setRstYear(Short.valueOf(Struts2Utils.getParameter("rstYear")));
            entity.setRstNo(Long.valueOf(Struts2Utils.getParameter("rstNo")));
            mesMeetingInfoManager.uploadRst(input);
        } catch (Exception ex) {
            success = false;
            message = ex.getMessage();
        }
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<script type=\"text/javascript\">");
        htmlBuffer.append("parent.execute({id:\"" + entity.getMesMeetingInfoId() + "\",start:" + Struts2Utils.getParameter("start") + ",success:" + success + ",message:\"" + message + "\"})");
        htmlBuffer.append("</script>");
        Struts2Utils.renderHtml(htmlBuffer.toString());
        helper.nocache();
        return null;
    }

    /**
     * 文件上传
     *
     * @return
     * @throws Exception
     */
    public String saveFileuploader() throws Exception {
        entity = mesMeetingInfoManager.getEntity(Struts2Utils.getParameter("mesMeetingInfoId"));
        Map input = new HashMap();
        input.put("mesMeetingInfo", entity);
        input.put("fileuploader", fileuploader);
        input.put("fileuploaderContentType", fileuploaderContentType);
        input.put("fileuploaderFileName", fileuploaderFileName);
        mesMeetingInfoManager.saveMesMeetingInfo(input);
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<script type=\"text/javascript\">");
        htmlBuffer.append("parent.execute({id:\"" + entity.getMesMeetingInfoId()
                + "\",start:0,success:true,message:\"\"})");
        htmlBuffer.append("</script>");
        Struts2Utils.renderHtml(htmlBuffer.toString());
        helper.nocache();
        return null;
    }

    /**
     * 文件下载
     *
     * @return
     * @throws Exception
     */
    public String download() throws Exception {
        String filename = Struts2Utils.getParameter("filename");
        String filerealname = Struts2Utils.getParameter("filerealname");
        File file = new File(MesMeetingInfoManager.savePathDirectory + "\\" + filename);
        if (!file.exists())
            throw new RuntimeException("文件不存在!");
        String postfix = filerealname.substring(filerealname.indexOf("."));
        filerealname = new String(ChangeCharset.decode2UTF_8(filerealname).getBytes("GBK"), "ISO8859-1");
        String contextType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, postfix);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType(contextType);
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filerealname + "\"");
        FileInputStream input = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        int i = 0;
        int b = 0;
        while (i != file.length()) {
            b = (byte) input.read();
            out.write(b);
            i++;
        }
        return null;
    }

    /**
     * 文件导出
     *
     * @return
     * @throws Exception
     */
    public String export() throws Exception {
        if (StringUtils.isBlank(Struts2Utils.getParameter("mesMeetingInfoId")))
            throw new RuntimeException("参数异常～～～");
        mesMeetingInfoManager.export(Struts2Utils.getParameter("mesMeetingInfoId"));
        return null;
    }

    /**
     * 历史会议 记录选择，自动赋值当前 纪要创建 界面
     *
     * @return
     */
    public String historyDirectory() throws Exception {
        return "history-directory";
    }

    /**
     * 历史会议 记录选择，自动赋值当前 纪要创建 内容片段
     *
     * @return
     */
    public String historyDirectoryBody() throws Exception {
        String date = Struts2Utils.getParameter("date");
        if (null == date || StringUtils.isBlank(date)) {
            ServletActionContext.getRequest().setAttribute("date", new Date());
        } else {
            ServletActionContext.getRequest().setAttribute("date", DateParser.defaultDateFormatter.parse(date));
        }

        Map<String, Object> params = new HashMap();
        params.put("category", Struts2Utils.getParameter("category"));
        params.put("date", Struts2Utils.getParameter("date"));
        List<String[]> values = mesMeetingInfoManager.findByMeetingRoomHistoryDirectory(params);

        Map<String, List> rooms = new HashMap();

        for (Object[] objs : values) {
            if (null != objs[1] && null != objs[2]) {
                if (StringUtils.isNotBlank(objs[1].toString())
                        && StringUtils.equalsIgnoreCase(objs[2].toString(), "ZC")) {
                    if (StringUtils.equalsIgnoreCase(objs[1].toString(), "0")) {
                        objs[1] = "总裁";
                    } else if (StringUtils.equalsIgnoreCase(objs[1].toString(), "1")) {
                        objs[1] = "执行总裁";
                    }
                } else if (StringUtils.isNotBlank(objs[1].toString())
                        && StringUtils.equalsIgnoreCase(objs[2].toString(), "GB")) {
                    objs[1] = CodeNameUtil.getDictNameByCd("OA_MEETING_ROOM", objs[1].toString());
                } else if (StringUtils.isNotBlank(objs[1].toString())
                        && StringUtils.equalsIgnoreCase(objs[2].toString(), "SHC")) {
                    objs[1] = CodeNameUtil.getDictNameByCd("OA_MEETING_ROOM_SHC", objs[1].toString());
                }
            }

            List room = rooms.get(objs[1]);
            if (null == room) {
                rooms.put(null == objs[1] || StringUtils.isBlank(objs[1].toString()) ? "未知会议室" : objs[1].toString(),
                        room = new ArrayList());
            }
            room.add(objs);
        }
        ServletActionContext.getRequest().setAttribute("results", rooms);
        // rooms
        return "history-directory-body";
    }

    /**
     * 文件上传
     *
     * @return
     */
    public String fileuploader() throws Exception {
        String fileName = HelperUtil.getFileName(filedataFileName[0]);
        String absPath = Struts2Utils.getRequest().getContextPath();
        String uiid = SpringSecurityUtils.getCurrentUiid();
        File dir = new File(PowerUtils.getFilePath(MesMeetingInfoManager.FILE_UPLOADER_SAVE_DIRECTORY, uiid, true));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File fout = new File(dir, fileName);
        FileOutputStream fos = new FileOutputStream(fout);
        if (filedata[0].length() > Constants.MAX_FILE_SIZE) {
            Struts2Utils.renderText("{'err':'上传文件最大不能超过" + Constants.MAX_FILE_SIZE / (1024 * 1000) + "M','msg':''}");
            filedata[0].delete();
            return null;
        }
        FileInputStream fin = new FileInputStream(filedata[0]);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fin.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        StringBuilder pathSb = new StringBuilder(absPath);
        pathSb.append("/app/show.action?fileName=");
        pathSb.append(fileName);
        pathSb.append("&realFileName=");
        pathSb.append(filedataFileName[0]);
        pathSb.append("&bizModuleCd=");
        pathSb.append(MesMeetingInfoManager.FILE_UPLOADER_SAVE_DIRECTORY);
        pathSb.append("&uiid=");
        pathSb.append(uiid);
        Struts2Utils.renderHtml("{'err':'','msg':'" + pathSb.toString() + "'}");
        return null;
    }

    /**
     * 远程 调用 接口
     * 统一处理 ，方便维护
     */
    public String ajax() throws Exception {
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        Map result = new HashMap() {
            {
                put("success", true);
                put("message", "返回消息！");
            }
        };
        int action = Integer.valueOf(Struts2Utils.getParameter("action"));
        if (action < 1) {
            result.put("success", false);
            result.put("message", "参数异常！");
        } else {
            switch (action) {
                /**
                 * 文件删除
                 */
                case 1001:
                    String mesMeetingInfoId = Struts2Utils.getParameter("mesMeetingInfoId");
                    AppAttachFile attachFileTmp = appAttachFileManager.getEntity(getId());
                    /*
                      * if (StringUtils.equalsIgnoreCase(attachFileTmp.getCreator(), currentUiid)) {
                      * appAttachFileManager.deleteAppAttachFile(attachFileTmp, MesMeetingInfo.class.getSimpleName(),
                      * mesMeetingInfoId, false);
                      * } else {
                      * result.put("success", false);
                      * }
                      */
                    appAttachFileManager.deleteAppAttachFile(attachFileTmp, MesMeetingInfo.class.getSimpleName(),
                            attachFileTmp.getBizEntityId(), false);
                    break;

                /**
                 * 依照 年份，分类 生成 序号
                 */
                case 1002:
                    result.put(
                            "serial",
                            mesMeetingInfoManager.generateSerialNo(String.valueOf(entity.getMesYear()),
                                    entity.getMesTypeCd(), Struts2Utils.getParameter("cid")));
                    break;

                /**
                 * 验证并 开始 纪要填写 流转
                 */
                case 1003:
                    entity = mesMeetingInfoManager.getEntity(getId());
                    if (null == entity.getMesApproveNodes() || entity.getMesApproveNodes().isEmpty()) {
                        result.put("success", false);
                        result.put("message", "请添加审批步骤！");
                    } else {
                        // 进入 第二步 变更状态为 纪要填写
                        entity.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_WRITE);
                        entity.setStartDate(new Date());
                        mesMeetingInfoManager.saveMesMeetingInfo(entity);
                        result.put("message", "纪要基本信息填写完成，已发送至纪要人。录入纪要内容。");

                        // 添加最后审批环节人，形成 回路。 添加前 需检测 当前人 是否已是最后环节审核人。
                        String approve = currentUiid;
                        WsPlasUser wsPlasUser = CodeNameUtil.getUserByUiid(approve);
                        if (null != wsPlasUser
                                && !entity.getMesApproveNodes().get(entity.getMesApproveNodes().size() - 1).getUserCd()
                                .equalsIgnoreCase(wsPlasUser.getUiid())) {
                            MesApproveNode mesApproveNode = new MesApproveNode();
                            mesApproveNode.setApproveLevel(Long.valueOf(entity.getMesApproveNodes().size()));
                            mesApproveNode.setUserCd(approve);
                            // 默认 时限 24小时 结束
                            mesApproveNode.setTimeLimit(24l);
                            mesApproveNode.setMesMeetingInfo(entity);
                            mesApproveNode.setWorkDutyDesc(wsPlasUser.getWorkDutyDesc());
                            mesApproveNodeManager.saveMesApproveNode(mesApproveNode);
                        }
                    }
                    break;
                /**
                 * 纪要填写完成，开始进入 纪要审核步骤
                 */
                case 1004:
                    entity = mesMeetingInfoManager.getEntity(getId());
                    if (null == entity.getMesSummaries() || entity.getMesSummaries().isEmpty()) {
                        result.put("success", false);
                        result.put("message", "请填写纪要信息！");
                    } else {
                        // 进入 第三步 开始 流转处理
                        Map input = new HashMap();
                        input.put("mesMeetingInfo", entity);
                        try {
                            mesMeetingInfoManager.workProcessBegin(input);
                            result.put("message", input.get("message"));
                        } catch (Exception ex) {
                            result.put("success", false);
                            result.put("message", ex.getMessage());
                        }
                    }
                    break;
                /**
                 * 获取不同版本的纪要信息
                 */
                case 1005:
                    mesSummary = mesSummaryManager.getEntity(Struts2Utils.getParameter("sid"));
                    if (mesSummary == null) {
                        result.put("success", false);
                        result.put("message", "纪要信息不存在!");
                    } else {
                        result.put("message", Util.clob2String(mesSummary.getMesContent()));
                        result.put("mesSummaryId", mesSummary.getMesSummaryId());
                    }
                    break;
                /**
                 * 审批流转 处理，
                 */
                case 1006:
                    String approveRemark = URLDecoder.decode(Struts2Utils.getParameter("approveRemark"), "UTF-8");
                    if (StringUtils.isBlank(approveRemark)) {
                        result.put("success", false);
                        result.put("message", "审核意见不能为空！");
                    } else {
                        entity = mesMeetingInfoManager.getEntity(getId());
                        Map input = new HashMap();
                        input.put("mesMeetingInfo", entity);
                        input.put("approveRemark", approveRemark);
                        if (StringUtils.isBlank(Struts2Utils.getParameter("status"))
                                || StringUtils.equalsIgnoreCase(Struts2Utils.getParameter("status"),
                                MesMeetingInfoManager.AUDITING_STATUS_SUCCESS)) {
                            // 审批通过
                            input.put(MesMeetingInfoManager.AUDITING_STATUS_KEY, true);
                        } else {
                            // 审批驳回
                            input.put(MesMeetingInfoManager.AUDITING_STATUS_KEY, false);
                        }
                        mesMeetingInfoManager.workProcessing(input);
                        result.put("message", input.get("message"));
                    }
                    break;
                /**
                 * 纪要删除，只能操作未开始未发送的 记录
                 */
                case 1007:
                    entity = mesMeetingInfoManager.getEntity(getId());

                    if (entity == null
                            || !entity.getStatusCd().equalsIgnoreCase(MesMeetingInfoManager.AUDITING_STATUS_INIT)) {
                        result.put("success", false);
                        result.put("message", "当前记录不存在，或记录状态不允许删除操作！");
                    } else {
                        mesApproveNodeManager.delete(entity.getMesApproveNodes());
                        mesApproveHisManager.delete(entity.getMesApproveHises());
                        mesSummaryManager.delete(entity.getMesSummaries());
                        mesMeetingInfoManager.delete(entity);
                    }
                    break;
                /**
                 * 发送邮件
                 */
                case 1008:
                    entity = mesMeetingInfoManager.getEntity(getId());
                    if (entity == null) {
                        result.put("success", false);
                        result.put("message", "当前记录不存在！");
                    } else {
                        Map params = new HashMap();
                        params.put("mesMeetingInfo", entity);
                        if (!StringUtils.isBlank(Struts2Utils.getParameter("sendme"))) {
                            params.put("sendme", true);
                        }
                        try {
                            mesMeetingInfoManager.sendMeetingInfoEmail(params);
                            result.put("message", "发送成功！");
                        } catch (Exception ex) {
                            result.put("success", false);
                            result.put("message", "发送失败！");
                            logger.error("邮件发送失败", ex);
                        }
                    }
                    break;

                // 追回操作
                case 1009:

                    entity = mesMeetingInfoManager.getEntity(getId());
                    Integer rp = mesMeetingInfoManager.isRollbackMesMeetingPermission(getId());
                    if (null == entity || rp == 0)
                        throw new RuntimeException("当前记录不存在，或状态不适用于追回！！！");

                    if (rp == 1) {
                        // 纪要填写状态,或者驳回状态,发起人可以追回。
                        entity.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_INIT);
                        entity.setStartDate(null);
                        mesMeetingInfoManager.saveMesMeetingInfo(entity);
                    } else if (rp == 2) {
                        // 审批状态；追回变为上一审批人。
                        List<MesApproveNode> mesApproveNodes = mesApproveNodeManager.getMesApproveNodeBySortLevel(getId());
                        MesApproveNode parentMesApproveNode = mesApproveNodes.get(Long
                                .valueOf(entity.getApproveLevel() - 1).intValue());
                        entity.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_PROCESSINGS);
                        entity.setStartDate(new Date());
                        entity.setUserCd(parentMesApproveNode.getUserCd());
                        entity.setApproveLevel(parentMesApproveNode.getApproveLevel());
                        mesMeetingInfoManager.saveMesMeetingInfo(entity);
                    } else if (rp == 3) {
                        // 审批状态；追回变为纪要填写。
                        entity.setStatusCd(MesMeetingInfoManager.AUDITING_STATUS_WRITE);
                        entity.setStartDate(null);
                        entity.setUserCd(null);
                        entity.setApproveLevel(-1l);
                        mesMeetingInfoManager.saveMesMeetingInfo(entity);
                    }
                    break;
            }
        }
        Struts2Utils.renderJson(result);
        return null;
    }

    /**
     * 推送
     *
     * @return
     * @throws Exception
     */
    public String push() throws Exception {
        try {
            String pushUserCds = Struts2Utils.getParameter("pushUserCds");
            String[] userCds = pushUserCds.split(";");
            mesMeetingInfoManager.push(entity, userCds);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Struts2Utils.renderText("success");
        return null;
    }

    // 表单封装数据 　　　
    @Override
    public MesMeetingInfo getModel() {
        return entity;
    }

    // 重写逻辑 End
    // ==============================================================================

    public static void main(String[] args) throws Exception {
        System.out.println("222\r11");
    }
}
