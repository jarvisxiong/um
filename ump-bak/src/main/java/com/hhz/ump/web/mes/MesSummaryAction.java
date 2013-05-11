package com.hhz.ump.web.mes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.mes.MesApproveNodeManager;
import com.hhz.ump.dao.mes.MesMeetingInfoManager;
import com.hhz.ump.dao.mes.MesSummaryManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.mes.MesMeetingInfo;
import com.hhz.ump.entity.mes.MesSummary;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.Util;


/**
 * Created by IntelliJ IDEA.
 * User: zhanggaojiang
 * Date: 2012-2-14
 * Time: 下午12:30
 * 会议纪要-内容 管理
 */
@Results({
        @Result(name = "input", type = "freemarker", location = "mes-summary-input.ftl"),
        @Result(name = "auditing", type = "freemarker", location = "mes-summary-auditing.ftl")
})
public class MesSummaryAction extends CrudActionSupport<MesSummary> {

    //辅助工具类
    public HelperUtil helper = new HelperUtil();

    public MesSummary entity = new MesSummary();
    public MesMeetingInfo mesMeetingInfo = new MesMeetingInfo();

    @Autowired
    public MesMeetingInfoManager mesMeetingInfoManager;
    @Autowired
    public MesSummaryManager mesSummaryManager;
    @Autowired
    public AppAttachFileManager appAttachFileManager;
    @Autowired
    public MesApproveNodeManager mesApproveNodeManager;


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


    /**
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        super.prepare();
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        if (StringUtils.isNotBlank(getId())) {
            entity = mesSummaryManager.getEntity(getId());
        }
        if (StringUtils.isNotBlank(Struts2Utils.getParameter("mesMeetingInfoId"))) {
            mesMeetingInfo = mesMeetingInfoManager.getEntity(Struts2Utils.getParameter("mesMeetingInfoId"));
            if (null != mesMeetingInfo) {
                Criterion conditions = Restrictions.eq("bizEntityId", mesMeetingInfo.getMesMeetingInfoId());
                ServletActionContext.getRequest().setAttribute("appAttachFiles", appAttachFileManager.findBy(conditions));
                if (StringUtils.isBlank(getId()) && null != mesMeetingInfo.getMesSummaries() && !mesMeetingInfo.getMesSummaries().isEmpty()) {
                    for (MesSummary mesSummary : mesMeetingInfo.getMesSummaries()) {
                        if (mesSummary.getIsCurrent()) {
                            entity = mesSummary;
                        }
                    }
                }

                if(StringUtils.isNotBlank(mesMeetingInfo.getRstAttachmentId())){
                	AppAttachFile appAttachFile=appAttachFileManager.getEntity(mesMeetingInfo.getRstAttachmentId());
                	try{
                		appAttachFile.getBizEntityId();
                		ServletActionContext.getRequest().setAttribute("rstAttachment",appAttachFile);
                	}catch(Exception ex){
                	}
                }
            }
        }
        ServletActionContext.getRequest().setAttribute("mesMeetingInfo", mesMeetingInfo);
        ServletActionContext.getRequest().setAttribute("currentUiid", currentUiid);
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
    public String delete() throws Exception {
        return null;
    }

    @Override
    public MesSummary getModel() {
        return entity;
    }

    //==============================================================================
    //重写逻辑  Start
    @Override
    protected void prepareModel() throws Exception {

    }

    //纪要填写
    @Override
    public String input() throws Exception {
        if (null != entity.getMesContent()) {
            ServletActionContext.getRequest().setAttribute("content", Util.clob2String(entity.getMesContent()));
        }
        return "input";
    }

    //纪要填写
    public String readonly() throws Exception {
        if (null != entity.getMesContent()) {
            ServletActionContext.getRequest().setAttribute("content", Util.clob2String(entity.getMesContent()));
        }
        return "readonly";
    }


    //纪要保存
    @Override
    public String save() throws Exception {
        String content = Struts2Utils.getParameter("content");
        boolean success = true;
        String message = "操作成功!";
        if (!"".equalsIgnoreCase(HelperUtil.clearHtml(content))) {
            Map input = new HashMap();
            input.put("mesSummary", entity);
            input.put("mesMeetingInfo", mesMeetingInfo);
            input.put("fileuploader", fileuploader);
            input.put("fileuploaderContentType", fileuploaderContentType);
            input.put("fileuploaderFileName", fileuploaderFileName);
            input.put("content", content);
            try {
                mesSummaryManager.saveNewMesSummary(input);
            } catch (Exception ex) {
                success = false;
                message = ex.getMessage();
            }
        } else {
            success = false;
            message = "纪要内容不能为空～～!";
        }
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<script type=\"text/javascript\">");
        htmlBuffer.append("parent.execute({id:\"" + mesMeetingInfo.getMesMeetingInfoId() + "\",start:" + Struts2Utils.getParameter("start") + ",success:" + success + ",message:\"" + message + "\"})");
        htmlBuffer.append("</script>");
        Struts2Utils.renderHtml(htmlBuffer.toString());
        helper.nocache();
        return null;
    }


    //纪要审核
    public String auditing() throws Exception {
        if (null != entity.getMesContent()) {
            ServletActionContext.getRequest().setAttribute("content", Util.clob2String(entity.getMesContent()));
        }
        return "auditing";
    }

    //纪要审核
    public String auditingSave() throws Exception {
        String content = Util.clob2String(entity.getMesContent());
        boolean success = true;
        String message = "操作成功!";
        if (!"".equalsIgnoreCase(HelperUtil.clearHtml(content))) {
            Map input = new HashMap();
            input.put("mesSummary", entity);
            input.put("mesMeetingInfo", mesMeetingInfo);
            input.put("fileuploader", fileuploader);
            input.put("fileuploaderContentType", fileuploaderContentType);
            input.put("fileuploaderFileName", fileuploaderFileName);
            mesSummaryManager.saveNewMesSummary(input);
        } else {
            success = false;
            message = "修改纪要时，内容不能为空～～!";
        }
        StringBuffer htmlBuffer = new StringBuffer();
        htmlBuffer.append("<script type=\"text/javascript\">");
        htmlBuffer.append("parent.execute({id:\"" + mesMeetingInfo.getMesMeetingInfoId() + "\",start:" + Struts2Utils.getParameter("start") + ",success:" + success + ",message:\"" + message + "\"})");
        htmlBuffer.append("</script>");
        Struts2Utils.renderHtml(htmlBuffer.toString());
        helper.nocache();
        return null;
    }




    //重写逻辑  End
    //==============================================================================



    public static void main(String[] args) {
        System.out.print("====\r=====");
    }
}
