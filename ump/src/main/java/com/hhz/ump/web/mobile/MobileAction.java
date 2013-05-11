package com.hhz.ump.web.mobile;

import java.io.DataOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.ump.aop.HttpRequester;
import com.hhz.ump.aop.HttpRespons;
import com.hhz.ump.dao.app.CommonManager;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.ResApproveInfoAction;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/mobile")
public class MobileAction extends ActionSupport {
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	private List<UserInfo> userInfos;
	
	private String value;
	@Autowired
	private CommonManager commonManager;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "login";
	}
	
	public String login() throws Exception {
		String userName = Struts2Utils.getParameter("userName");
		String password = Struts2Utils.getParameter("password");
		
		String md5Password = md5PasswordEncoder.encodePassword(password, "");
		boolean loginState = Util.getPlasService().validateUser(userName, md5Password);
		if(loginState)
			return "list";
		else
			return "login";
	}
	
	public String list() throws Exception {
		String value = Struts2Utils.getParameter("value");
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser wsUaapUser = new WsPlasUser();
			wsUaapUser.setUiid(value);
			wsUaapUser.setUserName(value);
			wsUaapUser.setEmail(value);
			wsUaapUser.setFixedPhone(value);
			wsUaapUser.setMobilePhone(value);
			List<WsPlasUser> wsUaapUsers = Util.getPlasService().getUserList(wsUaapUser, 1, 20);
			userInfos = transf2UserInfo(wsUaapUsers);
		}
		return "list";
	}
	
	public String androidLogin(){
		String userName = Struts2Utils.getParameter("userName");
		String password = Struts2Utils.getParameter("password");
		
		String md5Password = md5PasswordEncoder.encodePassword(password, "");
		
		try {
			boolean loginState = Util.getPlasService().validateUser(userName, md5Password);
			DataOutputStream dos = new DataOutputStream(Struts2Utils.getResponse().getOutputStream());
			if(loginState){
				dos.writeInt(16);
//				dos.writeUTF("OK");
			}else{
				dos.writeInt(0);
//				dos.writeUTF("FAILUR");
			}
			dos.flush();
			dos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchUser() throws Exception {
		value=ChangeCharset.decode2UTF_8(value);
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser wsUaapUser = new WsPlasUser();
			wsUaapUser.setUiid(value);
			wsUaapUser.setUserName(value);
			//wsUaapUser.setEmail(value);
			//wsUaapUser.setFixedPhone(value);
			wsUaapUser.setMobilePhone(value);
			List<WsPlasUser> list = Util.getPlasService().getUserList(wsUaapUser, 1, 20);
			Struts2Utils.renderJson(transf2UserInfo(list));
		}
		return null;
	}
	
	public String countApprove() throws Exception{
		ResApproveInfoAction res=new ResApproveInfoAction();
		res.countApprove();
		return null;
	}
	public void sendNotification()throws Exception{
		String uiid =Struts2Utils.getParameter("uiid");
		StringBuffer sql=new StringBuffer("select uri_path,cnt from RES_NOTIFICATION_URI where uiid=:uiid");
		Map<String, Object> values=new HashMap<String, Object>();
		values.put("uiid", uiid);
		final List list= commonManager.findBySql(sql.toString(), values);
		final HttpRequester requester=new HttpRequester();
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (Object object : list) {
						Object[] objs=(Object[])object;
						String uriPath=(String)objs[0];
						BigDecimal cnt=(BigDecimal)objs[1];
						HttpRespons respons=  requester.sendTile(uriPath,"网上审批","您有未审核的网批",cnt==null?0:cnt.intValue());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		
		Struts2Utils.renderText("success");
	}
	public String saveOrUpdateUri(){
		String uiid =Struts2Utils.getParameter("uiid");
		String uri =Struts2Utils.getParameter("uri");
		StringBuffer sqlUpdate=new StringBuffer("update RES_NOTIFICATION_URI set URI_PATH=:uri where uiid=:uiid ");
		StringBuffer sqlCount=new StringBuffer("select * from RES_NOTIFICATION_URI where uiid=:uiid");
		
		StringBuffer sqlInsert=new StringBuffer("insert into RES_NOTIFICATION_URI(UIID, URI_PATH) VALUES (:uiid,:uri) ");
		Map<String, Object> values=new HashMap<String, Object>();
		values.put("uiid", uiid);
		values.put("uri", uri);
		long cnt=commonManager.countSqlResult(sqlCount.toString(), values);
		if (cnt==0){
			commonManager.getDao().batchExecuteSql(sqlInsert.toString(), values);
		}else{
			commonManager.getDao().batchExecuteSql(sqlUpdate.toString(), values);
		}
		Struts2Utils.renderText("success");
		return null;
	}
	private List<UserInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
		List<UserInfo> list = new ArrayList<UserInfo>();
		if (users == null)
			return list;
		for (WsPlasUser user : users) {
			UserInfo info = new UserInfo();
			info.setUiid(user.getUiid());
			info.setSexCd(user.getSexCd());
			info.setUserName(user.getUserName());
			
			
			if("168".equals(user.getOrgCd())){
//				info.setMobilePhone("******");
			}else{
				info.setMobilePhone(StringUtils.trimToNull(user.getMobilePhone()));
			}
			
			
			//部门
			String phyOrgName = CodeNameUtil.getDeptNameByCd(user.getOrgCd());
			info.setOrgName(phyOrgName);
			
			//职位
			String myPositionName = StringUtils.isBlank(user.getWorkDutyDesc())?"":user.getWorkDutyDesc();//CodeNameUtil.getPositionNameByCd(user.getPositionCd());
			if(StringUtils.isBlank(myPositionName)){
				myPositionName = (user.getWorkDutyDesc() == null)?"":user.getWorkDutyDesc().trim();
			}
			info.setPosition(myPositionName);
			
			list.add(info);
		}
		return list;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
