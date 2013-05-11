/**
 * 
 */
package com.hhz.ump.web.oa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictDataManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 短信发送模块
 * 
 * @author huangjian
 * 
 *         2011-7-22
 */
@Namespace("/oa")
public class OaSendMsgAction extends ActionSupport {
	private static final long serialVersionUID = 7483249291589090389L;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private AppDictDataManager appDictDataManager;
	private String userCds;
	private String userNames;
	private String smsModule;// 短信模块
	/**
	 * 指定短信收件人
	 */
	private static String PROVIDE_RECEIVER = "PROVIDE_RECEIVER";
	private static String SMS_XSYJ_RECEIVER = "SMS_XSYJ_RECEIVER";// 销售业绩
	private static String SMS_BHKY_RECEIVER = "SMS_BHKY_RECEIVER";// 百货开业
	private static String SMS_SYKY_RECEIVER = "SMS_SYKY_RECEIVER";// 商业开业

	/**
	 * 指定收件人短信发送,销售业绩
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendXsyj() throws Exception {
		int i = 1;
		StringBuilder msg1 = new StringBuilder("[销售-01]").append("\n");
		StringBuilder msg2 = new StringBuilder("[销售-02]").append("\n");
		StringBuilder msg3 = new StringBuilder("[销售-03]").append("\n");
		msg1.append(getPara(i++)).append("月").append(getPara(i++)).append("日").append("\n");
		msg1.append("合共:月认购").append(getPara(i++)).append("(今天").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(今天").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		
		/*
		msg2.append("洛阳:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg2.append("蚌埠:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg2.append("旺庄:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg2.append("玉祁:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg2.append("宿迁:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg2.append("新乡:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg2.append("盐城:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("烟台:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("李沧:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("常州:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("即墨:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("安溪:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("泰安:认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append("(目标").append(
				getPara(i++)).append("),月合同").append(getPara(i++)).append("(目标").append(getPara(i++)).append("),合同未签").append(getPara(i++)).append(
				",按揭欠款").append(getPara(i++)).append(";").append("\n");
		msg3.append("其他:合同未签").append(getPara(i++)).append(",按揭欠款").append(getPara(i++)).append("\n");
		*/
		String[] projs=new String[]{"洛阳","蚌埠","旺庄","玉祁","宿迁","新乡","盐城","烟台","李沧","常州","即墨","安溪","泰安","晋江","合川","于家堡","太仓二期"};
		for (String proj : projs) {
			msg2.append(proj).append(":认购").append(getPara(i++)).append(",合同").append(getPara(i++)).append(",月认购").append(getPara(i++)).append(
					",月合同").append(getPara(i++)).append(";").append("\n");
			msg3.append(proj).append(":合同未签").append(getPara(i++)).append(",按揭欠款").append(getPara(i++)).append(";").append("\n");
		}
		msg3.append("其他:合同未签").append(getPara(i++)).append(",按揭欠款").append(getPara(i++)).append("\n");
		getRecevier();
		String currUser = SpringSecurityUtils.getCurrentUserName();
		msg3.append("[" + currUser + "]");
		sendImport(userCds, msg1.toString(), msg2.toString(), msg3.toString());
//		sendImport(userCds, msg1.toString());
//		sendImport(userCds, msg2.toString());
//		sendImport(userCds, msg3.toString());
		return null;
	}

	/**
	 * 指定收件人短信发送,百货开业
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendBhky() throws Exception {
		int i = 1;
		StringBuilder msgContent = new StringBuilder();
		msgContent.append("[百货]").append(getPara(i++)).append("月").append(getPara(i++)).append("日").append("\n");
		String[] projs=new String[]{"即墨"};
		for (String proj : projs) {
			msgContent.append(proj).append(":共").append(getPara(i++)).append("柜位,送签").append(getPara(i++)).append("(").append(getPara(i++)).append("%),新增").append(
					getPara(i++)).append(",双签").append(getPara(i++)).append("(").append(getPara(i++)).append("%),新增").append(getPara(i++)).append(";")
					.append("\n");
		}
		getRecevier();
		String currUser = SpringSecurityUtils.getCurrentUserName();
		msgContent.append("[" + currUser + "]");
		sendImport(userCds, msgContent.toString());
		return null;
	}

	/**
	 * 指定收件人短信发送,商业开业
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendSyky() throws Exception {
		int i = 1;
		StringBuilder msg1 = new StringBuilder("[商业-01]").append("\n");
		StringBuilder msg2 = new StringBuilder("[商业-02]").append("\n");
		msg1.append(getPara(i++)).append("月").append(getPara(i++)).append("日").append("\n");
		String[] projs=new String[]{"新乡"};
		int index=0;
		for (String proj : projs) {
			StringBuilder tmp = new StringBuilder();
			tmp.append(proj).append(":意向").append(getPara(i++)).append("%,新增").append(getPara(i++)).append("平方,合同").append(getPara(i++)).append(
					"%(目标").append(getPara(i++)).append("%),新增").append(getPara(i++)).append("平方,进场").append(getPara(i++)).append("%,新增").append(
					getPara(i++)).append("平方;").append("\n");
			if (index<2){
				msg1.append(tmp);
			}else{
				msg2.append(tmp);
			}
			index++;
		}
		getRecevier();
		String currUser = SpringSecurityUtils.getCurrentUserName();
		msg2.append("[" + currUser + "]");
		sendImport(userCds, msg1.toString(),msg2.toString());
		return null;
	}

	/**
	 * 指定收件人短信发送界面:销售业绩
	 */
	public String xsyj() {
		getRecevier();
		return "xsyj";
	}

	@Override
	public String execute() throws Exception {
		getRecevier();
		return smsModule.toLowerCase();
	}

	/**
	 * 保存指定收件人
	 * 
	 * @return
	 */
	public String saveRecv() {
		String[] userCds_array = userCds.split(";");
		String[] userNames_array = userNames.split(";");
		List<AppDictData> list = new ArrayList<AppDictData>();
		for (int i = 0; i < userCds_array.length; i++) {
			AppDictData appDictData = new AppDictData();
			appDictData.setDictCd(userCds_array[i]);
			appDictData.setDictName(userNames_array[i]);
			appDictData.setDispOrderNo(new BigDecimal(i));
			list.add(appDictData);
		}
		appDictDataManager.saveAppDictData(getReceiverByModule(), list);

		return null;
	}

	// 获取对应短信模块的数据字典
	private String getReceiverByModule() {
		String rtn = null;
		if (StringUtils.equals(smsModule, "xsyj")) {
			rtn = SMS_XSYJ_RECEIVER;
		} else if (StringUtils.equals(smsModule, "bhky")) {
			rtn = SMS_BHKY_RECEIVER;
		} else if (StringUtils.equals(smsModule, "syky")) {
			rtn = SMS_SYKY_RECEIVER;
		}
		return rtn;
	}

	private String getPara(int index) {
		String content = Struts2Utils.getParameter("content" + index);
		return content;
	}

	/**
	 * 根据字典cd获取收件人
	 */
	private void getRecevier() {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(getReceiverByModule(), true);
		map.remove("");

		StringBuilder sb_userCd = new StringBuilder();
		StringBuilder sb_userName = new StringBuilder();
		for (String dictCd : map.keySet()) {
			sb_userCd.append(dictCd).append(";");
			sb_userName.append(map.get(dictCd)).append(";");
		}
		setUserCds(sb_userCd.toString());
		setUserNames(sb_userName.toString());
	}

	/**
	 * 手动发送短信
	 * 
	 * @param uiids
	 * @param telNos
	 * @param msgContent
	 */
	private void send(String uiids, String telNos, String msgContent) throws Exception {
		String[] toUserUiids = uiids.split(";");
		String[] mobiles = new String[toUserUiids.length];
		WsPlasUser user = null;
		for (int i = 0; i < toUserUiids.length; i++) {
			user = PlasCache.getUserByUiid(toUserUiids[i]);
			if (user != null && StringUtils.isNotEmpty(user.getMobilePhone())) {
				mobiles[i] = user.getMobilePhone();
			}
		}

		String[] mobileNos = (String[]) ArrayUtils.addAll(mobiles, telNos.split(","));

//		SMS sms = SMS.getInstanceCommon();
		Util.getPlasService().sendCommonSms("手动发送",mobileNos,msgContent);
	}

	private void sendImport(String uiids, String... msgContent) throws Exception {
		String[] toUserUiids = uiids.split(";");
		String[] mobiles = new String[toUserUiids.length];
		WsPlasUser user = null;
		for (int i = 0; i < toUserUiids.length; i++) {
			user = PlasCache.getUserByUiid(toUserUiids[i]);
			if (user != null && StringUtils.isNotEmpty(user.getMobilePhone())) {
				mobiles[i] = user.getMobilePhone();
			}
		}

//		SMS sms = SMS.getInstanceImport();
		Util.getPlasService().sendImportSms( "重要短信",mobiles,msgContent);
	}

	public String send() throws Exception {
		String telNos = Struts2Utils.getParameter("telNos");
		StringBuilder msgContent = new StringBuilder(Struts2Utils.getParameter("msgContent"));
		send(userCds, telNos, msgContent.toString());
		return null;
	}

	/**
	 * 人工发送短信界面
	 * 
	 * @return
	 */
	public String msg() {
		return "msg";
	}

	public String getUserCds() {
		return userCds;
	}

	public void setUserCds(String userCds) {
		this.userCds = userCds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getSmsModule() {
		return smsModule;
	}

	public void setSmsModule(String smsModule) {
		this.smsModule = smsModule;
	}
}
