package com.hhz.uums.dao.app;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppParam;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.RandomPassword;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.web.PdCache;

@Service
@Transactional
public class AppParamManager extends BaseService<AppParam, String> {

	public static final Log log = LogFactory.getLog(AppParamManager.class);
 
	@Autowired
	private AppParamDao appParamDao;

	public void saveAppParam(AppParam appParam) {
		PowerUtils.setEmptyStr2Null(appParam);
		appParamDao.save(appParam);
	}

	public void deleteAppParam(String id) {
		appParamDao.delete(id);
	}
	
	public AppParam getAppParamByCd(String paramCd){
		AppParam appDictType = (AppParam) PdCache.getRefEntity(AppParam.class, "paramCd", paramCd);
		return appDictType;
	}
	
	@Override
	public List<AppParam> getAll() {
		return getAll("sequenceNo", true);
	}
	@Override
	public HibernateDao<AppParam, String> getDao() {
		return appParamDao;
	}

	/**
	 * 批量保存
	 * @param insertedRecords
	 * @param updatedRecords
	 * @param deletedRecords
	 */
	public void saveOrUpdateAppParams(List<AppParam> insertedRecords,List<AppParam> updatedRecords,List<AppParam> deletedRecords){
		for(AppParam appParam : insertedRecords){
			this.saveAppParam(appParam);
		}
		for(AppParam appParam : updatedRecords){
			this.saveAppParam(appParam);
		}
		for(AppParam appParam : deletedRecords){
			this.delete(appParam);
		}
		
	} 
	 
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String findAppParamValueByCd(String paramCd) {
		
//		AppParam appParam = appParamDao.findUniqueBy("paramCd", paramCd);
//		if (appParam == null)
//			return "";
//		return appParam.getParamValue();
		String paramValue= PdCache.getRefString(AppParam.class, "paramCd", paramCd, "paramValue");
//		LocalCache.getParamValueByCd(paramCd);
		return paramValue;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgTreeRootCd() {
		return TreePanelUtil2.DEFAULT_ROOT_ORG_CD;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgTreeRootBizCd() {
		return TreePanelUtil2.DEFAULT_ROOT_ORG_BIZ_CD;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppOrgTreeRootName() {
		return TreePanelUtil2.DEFAULT_ROOT_ORG_NAME;
	}
   
	// 获取密码(静态/动态)
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getPassword() {
		String type = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_RANDOM_PWD);
		if (DictContants.PLAS_DEFAULT_PWD_RANDOM_YES.equals(type))
			return RandomPassword.genRandomNum(GlobalConstants.DEFAULT_PASSWORD_LENGTH);
		else
			return getDefaultPassword();
	}

	// 默认密码(静态)
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultPassword() {
		String pwd = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_PASSWORD);
		if (StringUtils.isBlank(pwd)) {
			pwd = "123";
		}
		return pwd;
	}

	// 是否启用随机密码
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultRandomPwd() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_RANDOM_PWD);
	}

	// 是否启用mac地址过滤
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private String getDefaultMacLockedFlg() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_MAC_LOCKED_FLG);
	}

	// mac地址过滤:1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultMacLocked() {
		String defualtMacLockedFlg = getDefaultMacLockedFlg();
		if (DictContants.PLAS_MAC_LOCKED_FLG_LOCKED.equals(defualtMacLockedFlg))
			return true;
		else if (DictContants.PLAS_MAC_LOCKED_FLG_UNLOCKED.equals(defualtMacLockedFlg))
			return false;
		else
			return false;
	}

	//************************* 是否同步exchange邮箱用户 *************************
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultSynEmailUser() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_EMAIL_USER);
	}

	// 同步标识:1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultSynEmailUser() throws Exception {
		String defaultSynEmailUser = getDefaultSynEmailUser();
		if (DictContants.PLAS_SYN_EMAIL_USER_YES.equals(defaultSynEmailUser))
			return true;
		else if (DictContants.PLAS_SYN_EMAIL_USER_NO.equals(defaultSynEmailUser))
			return false;
		else
			throw new Exception("对不起,管理员未设置同步邮箱!请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_EMAIL_USER+ "的值!");
	}
	//************************* 是否同步coremail邮箱用户 *************************
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultSynCmailUser() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_CMAIL_USER);
	}
	
	// 同步标识:1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultSynCmailUser() throws Exception {
		String defaultSynCmailUser = getDefaultSynCmailUser();
		if (DictContants.PLAS_SYN_CMAIL_USER_YES.equals(defaultSynCmailUser))
			return true;
		else if (DictContants.PLAS_SYN_CMAIL_USER_NO.equals(defaultSynCmailUser))
			return false;
		else
			throw new Exception("对不起,管理员未设置同步邮箱!请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_CMAIL_USER+ "的值!");
	}

	//************************* 是否同步eas用户 *************************
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultSynEasUser() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_EAS_USER);
	}

	// 同步标识:1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultSynEasUser() throws Exception {
		String defaultSynEasUser = getDefaultSynEasUser();
		if (DictContants.PLAS_SYN_EAS_USER_YES.equals(defaultSynEasUser))
			return true;
		else if (DictContants.PLAS_SYN_EAS_USER_NO.equals(defaultSynEasUser))
			return false;
		else
			throw new Exception("对不起,管理员未设置同步EAS.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_EAS_USER + "的值!");
	}


	//************************* 是否同步mysoft用户 *************************
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultSynMysoftUser() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_MYSOFT_USER);
	}

	// 同步标识:1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultSynMysoftUser() throws Exception {
		String defaultSynMysoftUser = getDefaultSynMysoftUser();
		if (DictContants.PLAS_SYN_MYSOFT_USER_YES.equals(defaultSynMysoftUser))
			return true;
		else if (DictContants.PLAS_SYN_MYSOFT_USER_NO.equals(defaultSynMysoftUser))
			return false;
		else
			throw new Exception("对不起,管理员未设置同步EAS.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_MYSOFT_USER + "的值!");
	}

	//************************* 是否同步adobe用户 *************************
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getDefaultSynAdobeUser() {
		return findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_ADOBE_USER);
	}

	// 同步标识:1-是 0-否
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isDefaultSynAdobeUser() throws Exception {
		String defaultSynEasUser = getDefaultSynEasUser();
		if (DictContants.PLAS_SYN_ADOBE_USER_YES.equals(defaultSynEasUser))
			return true;
		else if (DictContants.PLAS_SYN_ADOBE_USER_NO.equals(defaultSynEasUser))
			return false;
		else
			throw new Exception("对不起,管理员未设置同步ADOBE.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_DEFAULT_SYN_ADOBE_USER + "的值!");
	}

	// 默认最大失败次数
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getDefaultMaxFailureTimes() {
		String failureTimes = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_DEFAULT_FAILURE_TIMES);
		if (failureTimes == null)
			return GlobalConstants.NONE_FAILURE_NOCHECK;
		else
			return new Integer(failureTimes).intValue();
	}

	// exchange服务 eg:http://ip:port
	public String getExchangePreUrl() {
		String url = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_EXCHANGE_PRE_URL);
		if (StringUtils.isBlank(url)) {
			log.error("对不起,管理员未设置参数.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_EXCHANGE_PRE_URL + "的值!");
			return "";
		} else
			return url;
	}
	
	// coremail服务 eg:http://ip:port
	public String getCoremailPreUrl() {
		String url = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_COREMAIL_PRE_URL);
		if (StringUtils.isBlank(url)) {
			log.error("对不起,管理员未设置参数.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_COREMAIL_PRE_URL + "的值!");
			return "";
		} else
			return url;
	}

	// eas服务 eg:http://ip:port
	public String getEasPreUrl() {
		String url = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_EAS_PRE_URL);
		if (StringUtils.isBlank(url)) {
			log.error("对不起,管理员未设置参数.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_EAS_PRE_URL + "的值!");
			return "";
		} else
			return url;
	}
	// mysoft服务 eg:http://ip:port
	public String getMysoftPreUrl() {
		String url = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_MYSOFT_PRE_URL);
		if (StringUtils.isBlank(url)) {
			log.error("对不起,管理员未设置参数.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_MYSOFT_PRE_URL + "的值!");
			return "";
		} else
			return url;
	}

	// livecyclerms服务 eg:http://ip:port
	public String getLivecycleURL() {
		String url = findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_LIVECYCLE_URL);
		if (StringUtils.isBlank(url)) {
			log.error("对不起,管理员未设置参数.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_LIVECYCLE_URL + "的值!");
			return "";
		} else
			return url;
	}

	//************************* 短信发送开关  *************************
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isSmsGateFlag() {
		if(GlobalConstants.GLOBAL_KEY_SMS_GATE_FLAG_YES.equals(findAppParamValueByCd(GlobalConstants.GLOBAL_KEY_SMS_GATE_FLAG)))
			return true;
		else{
			log.error("对不起,管理员未设置参数.请在<参数定义表>设置" + GlobalConstants.GLOBAL_KEY_SMS_GATE_FLAG + "的值!");
			return false;
		}
	}
}

