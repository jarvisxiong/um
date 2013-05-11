package com.hhz.uums.web.plas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.vo.vw.VoAcct;
import com.opensymphony.xwork2.ActionSupport;

public class PlasValidateAction extends ActionSupport {

	private static final long serialVersionUID = 6558853160586602985L;

	@Autowired
	private PlasAcctManager plasAcctManager; 
	
	@Autowired
	private PlasUserManager plasUserManager; 
	
	private List<VoAcct> acctList;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 检查不一致账号 
	 * @return
	 */
	public String unMatchAcctList(){
		Page<VoAcct> pageAcct = plasAcctManager.searchUnMatchOrgList(new Page(30));
		JsonUtil.renderJson(pageAcct,new String[]{});
		return null;
	}

	public List<VoAcct> getAcctList() {
		return acctList;
	}

	public void setAcctList(List<VoAcct> acctList) {
		this.acctList = acctList;
	}
	
	public String moveToOrg(){

		String userId = Struts2Utils.getParameter("userId");
		String orgId = Struts2Utils.getParameter("orgId");
		String orgName = Struts2Utils.getParameter("orgName");
		String posOrgId = Struts2Utils.getParameter("posOrgId");
		String posOrgName = Struts2Utils.getParameter("posOrgName");
		
		plasUserManager.moveToOrg(userId, orgId, orgName, posOrgId, posOrgName);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	
	
	//导出人员人员角色关系(excel)
	public String exportAcctRoleRelList(){
//		
//		select distinct 
//		t.uiid,
//		t2.user_name, 
//		t4.org_cd, 
//		t4.org_biz_cd, 
//		t4.org_name, 
//		t5.role_cd, 
//		t5.role_name,
//		t4.sequence_no, 
//		t2.sequence_no,
//		case t2.service_status_cd when '1' then '1-在职' when '2' then '2-辞退' when '3' then '3-离职' else   t2.service_status_cd||'-其他'   end case ,
//		case t.status_cd         when '0' then '0-未启用' when '1' then '1-正常' when '2' then '2-冻结' when '3' then '3-解冻' when '4' then '4-注销' else t.status_cd||'-其他'  end case
//		from plas_acct t, plas_user t2, plas_sys_position t3, plas_org t4, plas_role t5, plas_sys_pos_role_rel t6
//		where t.plas_user_id = t2.plas_user_id
//		and t3.plas_acct_id = t.plas_acct_id
//		and t4.plas_org_id = t2.plas_org_id
//		and t6.plas_role_id = t5.plas_role_id
//		and t6.plas_sys_position_id = t3.plas_sys_position_id
//
//		order by t4.org_cd, t4.sequence_no asc, t2.sequence_no desc,t.uiid desc
//		
		return null;
	}
}

