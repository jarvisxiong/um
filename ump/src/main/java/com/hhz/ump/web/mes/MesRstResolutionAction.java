package com.hhz.ump.web.mes;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.mes.MesMeetingInfoManager;
import com.hhz.ump.dao.mes.MesRstResolutionManager;
import com.hhz.ump.entity.mes.MesMeetingInfo;
import com.hhz.ump.entity.mes.MesRstResolution;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.Util;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-10
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class MesRstResolutionAction extends CrudActionSupport<MesRstResolution> {

    //工具辅助类
    public HelperUtil helper = new HelperUtil();

    public MesRstResolution entity = new MesRstResolution();
    public MesMeetingInfo mesMeetingInfo = new MesMeetingInfo();
    @Autowired
    private MesRstResolutionManager mesRstResolutionManager;
    @Autowired
    private MesMeetingInfoManager mesMeetingInfoManager;
    @Autowired
    private AppAttachFileManager appAttachFileManager;

    /**
     * 上传文件列表
     */
    public File[] fileuploader;

    /**
     * 上传文件名称
     */
    public String[] fileuploaderFileName;

    /**
     * 以上传文件类型
     */
    public String[] fileuploaderContentType;

    @Override
    public void prepare() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            entity = mesRstResolutionManager.getEntity(getId());
            mesMeetingInfo = entity.getMesMeetingInfo();
        } else if (StringUtils.isNotBlank(Struts2Utils.getParameter("mesMeetingInfoId"))) {
            mesMeetingInfo = mesMeetingInfoManager.getEntity(Struts2Utils.getParameter("mesMeetingInfoId"));
        }

        Criterion conditions = Restrictions.eq("bizEntityId", entity.getMesRstResolutionId());
        ServletActionContext.getRequest().setAttribute("appAttachFiles", appAttachFileManager.findBy(conditions));

        Struts2Utils.getRequest().setAttribute("mesMeetingInfo", mesMeetingInfo);
        super.prepare();
    }

    @Override
    public String deleteBatch() throws Exception {
        return null;
    }

    @Override
    public String list() throws Exception {
        return null;
    }

    @Override
    public String input() throws Exception {
        return null;
    }

    @Override
    public String save() throws Exception {
        if (StringUtils.isBlank(entity.getMesRstResolutionId())) {
            entity.setStatusCd(MesRstResolutionManager.KEY_STATUS_INIT);
        }
        entity.setMesMeetingInfo(mesMeetingInfo);

        Map input = new HashMap();
        input.put("mesRstResolution", entity);
        input.put("fileuploader", fileuploader);
        input.put("fileuploaderContentType", fileuploaderContentType);
        input.put("fileuploaderFileName", fileuploaderFileName);

        boolean success = true;
        String message = "操作成功";
        try {
            String content = Util.clob2String(entity.getRstContet());
            if (HelperUtil.clearHtml(content).equalsIgnoreCase(""))
                throw new RuntimeException("决议内容不能为空!");
            mesRstResolutionManager.saveMesRstResolution(input);
        } catch (Exception ex) {
            success = false;
            message = ex.getMessage();
        }
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<script type=\"text/javascript\">");
        htmlBuffer.append("parent.execute({id:\"" + entity.getMesRstResolutionId() + "\",start:" + Struts2Utils.getParameter("start") + ",success:" + success + ",message:\"" + message + "\"})");
        htmlBuffer.append("</script>");
        Struts2Utils.renderHtml(htmlBuffer.toString());
        helper.nocache();
        return null;
    }

    @Override
    public String delete() throws Exception {
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
    }

    @Override
    public MesRstResolution getModel() {
        return entity;
    }


    /**
     * 决议 发布
     *
     * @return
     * @throws Exception
     */
    public String publish() throws Exception {
        Struts2Utils.getRequest().setAttribute("rstContet_", Util.clob2String(entity.getRstContet()));
        Struts2Utils.getRequest().setAttribute("currentUiid", SpringSecurityUtils.getCurrentUiid());
        return "publish";
    }

    /**
     * 文件导出
     *
     * @return
     * @throws Exception
     */
    public String export() throws Exception {
        if (StringUtils.isBlank(Struts2Utils.getParameter("mesRstResolutionId")))
            throw new RuntimeException("参数异常～～～");
        mesRstResolutionManager.export(Struts2Utils.getParameter("mesRstResolutionId"));
        return null;
    }

    /**
     * 远程 调用 接口
     * 统一处理 ，方便维护
     */
    public String ajax() throws Exception {
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        Map result = new HashMap() {{
            put("success", true);
            put("message", "返回消息！");
        }};
        int action = Integer.valueOf(Struts2Utils.getParameter("action"));
        if (action < 1) {
            result.put("success", false);
            result.put("message", "参数异常！");
        } else {
            switch (action) {
                case 1001://完成
                    mesRstResolutionManager.completeMesRstResolution(entity);
                    result.put("message", "决议填写已完成!");
                    break;
                /**
                 * 依照 年份 生成 序号
                 */
                case 1002:
                    result.put("serial", mesRstResolutionManager.generateSerialNo(String.valueOf(Struts2Utils.getParameter("mesYear")), Struts2Utils.getParameter("mesRstResolutionId")));
                    break;
                /**
                 * 决策发送邮件
                 */
                case 1003:
                    entity = mesRstResolutionManager.getEntity(getId());
                    if (entity == null) {
                        result.put("success", false);
                        result.put("message", "当前记录不存在！");
                    } else {
                        Map params = new HashMap();
                        params.put("mesRstResolution", entity);
                        if (!StringUtils.isBlank(Struts2Utils.getParameter("sendme"))) {
                            params.put("sendme", true);
                        }
                        try {
                            mesRstResolutionManager.sendMesRstResolutionEmail(params);
                            result.put("message", "发送成功！");
                        } catch (Exception ex) {
                            result.put("success", false);
                            result.put("message", "发送失败！");
                            logger.error("邮件发送失败", ex);
                        }
                    }
                    break;
                case 1004:
                    entity = mesRstResolutionManager.getEntity(getId());
                    entity.setStatusCd(MesRstResolutionManager.KEY_STATUS_INIT);
                    Map params = new HashMap();
                    params.put("mesRstResolution", entity);
                    mesRstResolutionManager.saveMesRstResolution(params);
                    Struts2Utils.getResponse().sendRedirect("mes-rst-resolution!publish.action?id=" + entity.getMesRstResolutionId());
                    return null;
            }
        }
        Struts2Utils.renderJson(result);
        return null;
    }
}
