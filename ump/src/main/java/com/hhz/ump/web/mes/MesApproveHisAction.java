package com.hhz.ump.web.mes;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.mes.MesApproveHisManager;
import com.hhz.ump.entity.mes.MesApproveHis;
import com.hhz.ump.util.HelperUtil;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

/**
 * Created by IntelliJ IDEA.
 * User: 123
 * Date: 12-2-17
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
@Results({
        @Result(name = "success", location = "mes-approve-his-list.ftl")
})
public class MesApproveHisAction extends CrudActionSupport<MesApproveHis> {

    //工具辅助类
    public HelperUtil helper = new HelperUtil();


    private MesApproveHis entity = new MesApproveHis();
    @Autowired
    private MesApproveHisManager mesApproveHisManager;

    @Override
    public String deleteBatch() throws Exception {
        return null;
    }

    @Override
    public String list() throws Exception {
        String mesMeetingInfoId = Struts2Utils.getParameter("mesMeetingInfoId");
        ServletActionContext.getRequest().setAttribute("mesApproveHis", mesApproveHisManager.findMesApproveHisByMesMeetingInfoId(mesMeetingInfoId));
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        return null;
    }

    @Override
    public String save() throws Exception {
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
    public MesApproveHis getModel() {
        return null;
    }

}
