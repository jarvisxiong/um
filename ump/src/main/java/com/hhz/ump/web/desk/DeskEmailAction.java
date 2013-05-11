package com.hhz.ump.web.desk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.dao.oa.OaEmailManager;
import com.hhz.ump.entity.oa.OaEmail;
import com.opensymphony.xwork2.ActionSupport;

public class DeskEmailAction extends ActionSupport {

	private static final long serialVersionUID = -7173178438557072107L;

	@Autowired
	private OaEmailManager oaEmailManager;

	private Page<OaEmail> deskHomePager = new Page<OaEmail>(6);
	public String email() {

		// deskHomePager.setOrderBy("createdDate");
		// deskHomePager.setOrder(Page.DESC);
		// deskHomePager = oaEmailManager.findPage(deskHomePager, filters);
		
		String uiid = SpringSecurityUtils.getCurrentUiid();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userCd", uiid);
		
		StringBuffer sql = new StringBuffer("SELECT /*+ index(e IX_OA_EMAIL_5) */e.* ");
		sql.append(" from ump.oa_email e");
		sql.append(" WHERE e.user_cd = :userCd AND e.delete_flg = 0 AND e.user_type_cd IN ('1', '2', '3')");
		
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("e", OaEmail.class);
		deskHomePager = oaEmailManager.findPageSql(deskHomePager, sql.toString(), values, mapClazz);
		
		return "email";
	}
	
	public String updateAlert() throws Exception {
		return "updateAlert";
	}

	public Page<OaEmail> getDeskHomePager() {
		return deskHomePager;
	}

	public void setDeskHomePager(Page<OaEmail> deskHomePager) {
		this.deskHomePager = deskHomePager;
	}

	// 未读提醒
	public String getUnReadMailNum() {
		try{
			return String.valueOf("("+oaEmailManager.getNoReadCount(SpringSecurityUtils.getCurrentUiid())+")");
		}catch(Exception e){
			//log.error("获取内部邮件异常!" + e);
			return "";
		}
	}
	
	// 未读邮件
	public String getUnReadMailOutNum() {
		try{
			return "";//String.valueOf("("+EmailUtil.getNewEmailNum()+")");
		}catch(Exception e){
			//log.error("获取内部邮件异常!" + e);
			return "";
		}
	}
}
