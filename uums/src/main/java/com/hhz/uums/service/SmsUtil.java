package com.hhz.uums.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SmsUtil {

	private static Log log = LogFactory.getLog(SmsUtil.class);
	
	/**
	 * 发送取随机密码(动态)
	 * @param uiid
	 * @param userName
	 * @param sexCd
	 * @param mobilePhone
	 * @param oriPwd
	 * @param biefSysName
	 * @return
	 */
	public static boolean sendRandomPassword(String uiid, String userName, String sexCd,String mobilePhone, String oriPwd, String biefSysName) {
		
		try{ 
			if (StringUtils.isNotBlank(mobilePhone) && mobilePhone.trim().length() == 11) {
				// 发送短信通知
				// 可能由于网络原因可能造成随机密码延迟收到，请耐心等候，如超过10分钟未收到，请再次.
//				SMS sms =SMS.getInstanceCommon();
				String call = "";//(DictContants.PLAS_SEX_MALE.equals(sexCd)) ? "先生" : (DictContants.PLAS_SEX_FEMALE.equals(sexCd) ? "女士" : "先生/女士");
				String msg = "尊敬的 " + userName + " " + call + ": 您好! 您的 账号是" + uiid +", " + biefSysName + "密码是 " + oriPwd + " ,请及时更改!";
//				sms.sendHidden("重置密码", new String[]{mobilePhone}, msg);
				return true;
			}else{
				log.error("用户[" + uiid + "]移动电话号码[" + mobilePhone + "]不符格式,无法发送短信!");
				return false;
			}
		}catch (Exception e) {
			log.error("用户[" + uiid + "]发送"+biefSysName+"密码,无法发送短信!", e);
			return false;
		}
	}

}
