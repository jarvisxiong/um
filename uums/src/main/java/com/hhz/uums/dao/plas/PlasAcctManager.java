
package com.hhz.uums.dao.plas;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.bis.BisSmsTmpManager;
import com.hhz.uums.entity.bis.BisSmsTmp;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasAcctPosRelTmp;
import com.hhz.uums.entity.plas.PlasLoginLog;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.SmsUtil;
import com.hhz.uums.utils.DateUtil;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.DictMapUtil;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.Util;
import com.hhz.uums.vo.vw.AcctTreeVo;
import com.hhz.uums.vo.vw.OperResultVo;
import com.hhz.uums.vo.vw.VoAcct;
import com.hhz.uums.vo.ws.WsPlasAcct;

@Service
@Transactional
public class PlasAcctManager extends BaseService<PlasAcct, String> {

	private static Log log = LogFactory.getLog(PlasAcctManager.class);

	@Autowired
	private PlasAcctDao plasAcctDao;
	@Autowired
	private PlasUserDao plasUserDao;
	@Autowired
	private PlasRoleDao plasRoleDao;
	@Autowired
	private PlasSysPosRoleRelDao plasSysPosRoleRelDao;
	@Autowired
	private PlasAcctPosRelTmpDao plasAcctPosRelTmpDao;
	@Autowired
	private PlasSysPositionDao plasSysPositionDao;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	@Autowired
	private PlasLoginLogManager plasLoginLogManager;

	@Autowired
	private AppParamManager appParamManager;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private BisSmsTmpManager bisSmsTmpManager;

	@Override
	public HibernateDao<PlasAcct, String> getDao() {
		return this.plasAcctDao;
	}

	@Override
	public List<PlasAcct> getAll() {
		return getAll("sequenceNo", true);
	}

	public void savePlasAcct(PlasAcct plasAcct) {
		PowerUtils.setEmptyStr2Null(plasAcct);
		this.getDao().save(plasAcct);
	}

	public void deletePlasAcct(String id) {
		this.getDao().delete(id);
	}

	public PlasAcct loadAcctByUiid(String uiid) {
		return findUniqueBy("uiid", uiid);
	}

	/**
	 * 查询账号信息
	 * 
	 * @param uiid
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasAcct getPlasAcctByUiid(String uiid) {
		List<PlasAcct> result = this.getPlasAcctList(uiid);
		log.debug("在plasAcct表中一共查到 " + result.size()+" 条");
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasAcct) result.get(0);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasAcct> getPlasAcctList(String uiid) {
		List<PlasAcct> result = this.getDao().createCriteria(PlasAcct.class).add(Restrictions.eq("uiid", uiid)).list();
		if (result == null)
			return new ArrayList<PlasAcct>();
		else
			return result;
	}

	/**
	 * 查询账户信息
	 * 
	 * @param userCd
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasAcct getPlasAcctByUserCd(String userCd) {
		String hql = " from PlasAcct t where t.plasUser.userCd = ? ";
		List result = this.getDao().createQuery(hql, userCd).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasAcct) result.get(0);
	}

	/**
	 * 
	 * author:jiaoxiaofeng 2011-2-22 Descrption：根据用户名查询账户信息
	 * 
	 * @param userCd
	 * @return PlasAcct
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasAcct> getAllsByUiid(String uiid) {
		String hql = " from PlasAcct t where t.uiid like ? ";
		List<PlasAcct> result = this.getDao().createQuery(hql, uiid).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return result;
	}

	/**
	 * 更新账户的密码
	 * 
	 * @param userCd
	 * @param md5Password
	 */
	public void savePassWordChange(String userCd, String md5Password) {
		PlasAcct acct = getPlasAcctByUserCd(userCd);
		acct.setLoginInPassword(md5Password);
		this.savePlasAcct(acct);
	}

	/**
	 * 更新账户最后登出日期
	 * 
	 * @param userCd
	 */
	public void savePlasLogout(String userCd) {
		PlasAcct acct = getPlasAcctByUserCd(userCd);
		acct.setLastLogoutDate(new Date());
		this.savePlasAcct(acct);
	}

	// ------------------------以下对[PLAS账号管理]进行操作 ----------------------------

	public static final int ACCT_FREEZE = 1;// 冻结
	public static final int ACCT_ENABLE = 2;// 生效
	public static final int ACCT_CLOSE = 3;// 注销
	public static final int ACCT_RESET_PWD = 4;// 重置密码
	public static final int ACCT_RESET_PWD_VALUE = 5;// 重置密码,传值
	public static final int ACCT_CREATE = 6;//创建
	public static final int ACCT_AUDIT_PASS= 7;//账号审核通过
	public static final int ACCT_AUDIT_REJECT = 8;//审核驳回

	public static final int ACCT_PWD_NOSYN_EAS = 41;// 设置未同步EAS
	public static final int ACCT_PWD_NOSYN_EMAIL = 42;// 设置未同步EMAIL
	public static final int ACCT_PWD_NOSYN_MYSOFT = 43;// 设置未同步MYSOFT
	public static final int ACCT_PWD_NOSYN_CMAIL = 44;// 设置未同步COREMAIL

	/**
	 * 账号(冻结/生效/注销/重置密码)
	 * 
	 * @param plasAcctId
	 * @param iOperateType
	 * @throws Exception
	 */
	public OperResultVo acctEnable(String plasAcctId) {
		return acctOperate(plasAcctId, ACCT_ENABLE);
	}
	public OperResultVo acctEnable(PlasAcct acct) {
		return acctOperate(acct, ACCT_ENABLE);
	}

	public OperResultVo acctFreeze(String plasAcctId) {
		return acctOperate(plasAcctId, ACCT_FREEZE);
	}
	public OperResultVo acctFreeze(PlasAcct plasAcct) {
		return acctOperate(plasAcct, ACCT_FREEZE);
	}

	public OperResultVo acctClose(String plasAcctId) {
		return acctOperate(plasAcctId, ACCT_CLOSE);
	}
	public OperResultVo acctClose(PlasAcct acct) {
		return acctOperate(acct, ACCT_CLOSE);
	}

	public OperResultVo acctResetPwd(String plasAcctId) {
		return acctOperate(plasAcctId, ACCT_RESET_PWD);
	}

	public OperResultVo acctResetPwdValue(PlasAcct acct, String newPassword, String operateUiid, String splitSystem) {
		return acctOperate(acct, ACCT_RESET_PWD_VALUE, operateUiid, newPassword, splitSystem);
	}

	public OperResultVo acctResetPwdEas(String plasAcctId) {
		return acctOperate(plasAcctId, ACCT_PWD_NOSYN_EAS);
	}

	public OperResultVo acctResetPwdEmail(String plasAcctId) {
		return acctOperate(plasAcctId, ACCT_PWD_NOSYN_EMAIL);
	}


	public OperResultVo acctOperate(String plasAcctId, int iOperateType) {
		return acctOperate(getEntity(plasAcctId), iOperateType, null, null, null);
	}

	public OperResultVo acctOperate(String plasAcctId, int iOperateType, String operUiid) {
		return acctOperate(getEntity(plasAcctId), iOperateType, operUiid, null, null);
	}

	public OperResultVo acctOperate(PlasAcct acct, int iOperateType) {
		return acctOperate(acct, iOperateType, null, null, null);
	}

	public OperResultVo acctOperate(PlasAcct acct, int iOperateType, String operUiid, String newPassword, String splitSystem) {
		 
		// 是否操作失败
		boolean bSuccessFlg = true;
		String mainOperateTip = "";
		String sumarry = "";
		String operName = "";
		
		try {

			PlasUser tmpUser = acct.getPlasUser();
			StringBuffer sb = new StringBuffer();
			if (StringUtils.isNotBlank(operUiid)) {
				operName = tmpUser.getUserName();
			} else {
				if(StringUtils.isBlank(operUiid)){
					try{
						operUiid = SpringSecurityUtils.getCurUiid();
						operName = SpringSecurityUtils.getCurUserName();
					}catch(Exception e){
						operUiid = "system";
						operName = "系统自动";
					}
				}
			}

			switch (iOperateType) {

			// 冻结
			case ACCT_FREEZE:

				log.info(" >>>>>>>>>>>>>>>>>>>>>>>冻结账号- operUiid:" + operUiid + ",");

				acct.setStatusCd(DictContants.PLAS_USER_STATUS_FREEZE);
				acct.setLockedDate(new Date());

				mainOperateTip = "冻结账号完成!";
				sumarry = OperConst.FREEZE;

				break;

			// 生效
			case ACCT_ENABLE:

				String oldStatusName = DictMapUtil.getMapAcctStatus(acct.getStatusCd());
				sb.append("原账号状态:").append(oldStatusName).append(",");
				log.info(" >>>>>>>>>>>>>>>>>>>>>>>生效账号- operUiid:" + operUiid + ",");
				PlasUser user = acct.getPlasUser();
				if(user!= null && !DictContants.PLAS_SERVICE_STATUS_ON.equals(user.getServiceStatusCd())){
					acct.getPlasUser().setServiceStatusCd(DictContants.PLAS_SERVICE_STATUS_ON);
					log.info("设置为在职!");
				}
				 

				acct.setFailureTimes(BigDecimal.ZERO);// 清空错误次数
				acct.setStatusCd(DictContants.PLAS_USER_STATUS_CREATE);

				mainOperateTip = "启用账号完成!";
				sumarry = OperConst.OPEN;

				break;

			// 注销
			case ACCT_CLOSE:

				log.info(" >>>>>>>>>>>>>>>>>>>>>>>注销账号,同时设置为停用- operUiid:" + operUiid + ",");
				// 停用
				acct.getPlasUser().setServiceStatusCd(DictContants.PLAS_SERVICE_STATUS_OFF);

				acct.setStatusCd(DictContants.PLAS_USER_STATUS_CLOSED);

				// 注销:默认失效日期为当天,显示序号为0,备注另存手机,手机为空(防止发短信)
				acct.setInvalidDate(new Date());
				acct.setSequenceNo(new BigDecimal(0));
				acct.setRemark((StringUtils.isBlank(acct.getRemark()) ? "" : acct.getRemark()) + " back up mobile:"+ tmpUser.getMobilePhone());

				mainOperateTip = "注销账号完成!";
				sumarry = OperConst.CLOSE;
				
				//add by huangbijin 2012-01-19  注销用户,去掉机构负责人
				removeOrgMgr(acct.getUiid());
				
				break;

			// 重置密码
			case ACCT_RESET_PWD:

				log.info(" >>>>>>>>>>>>>>>>>>>>>>>重置密码 账号- operUiid:" + operUiid + ",");
				// 注意：动态获取密码
				String oriPwd = appParamManager.getPassword();
				// 若已开通邮箱,则重置密码
//				if (Util.emailEnable(acct.getEmailFlg())) {
//					if (ExchangeServiceClient.resetPassword(acct.getUiid(), oriPwd)){
//						sb.append("同步mail邮箱密码成功!");
//						acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_YES);
//					} else {
//						acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_NO);
//						sb.append("同步mail邮箱密码失败!");
//						bSuccessFlg = false;
//					}
//				}
//				不同步密码: hidden by huangbijin 2011-08-17 
//				// 若已开通EAS,则重置密码
//				if (Util.easEnable(acct.getEasFlg())) {
//					if (EasServiceClient.resetPassword(acct, oriPwd)) {
//						sb.append("同步Eas密码成功!");
//						acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_YES);
//					} else {
//						sb.append("同步Eas密码失败!");
//						acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
//						bSuccessFlg = false;
//					}
//				}
				acct.setLoginInPassword(md5PasswordEncoder.encodePassword(oriPwd, ""));
				
				//发送PD密码短信
				SmsUtil.sendRandomPassword(acct.getUiid(), tmpUser.getUserName(), tmpUser.getSexCd(), tmpUser.getMobilePhone(), oriPwd, "PD");
				

				mainOperateTip = "重置密码完成!";
				sumarry = OperConst.EDT;

				break;

			// 修改密码,传入明文密码
			case ACCT_RESET_PWD_VALUE: {
				
				log.info(" >>>>>>>>>>>>>>>>>>>>>>>重置密码,传入明文密码 - operUiid:" + operUiid + ",");
				// 保存密码
				acct.setLoginInPassword(md5PasswordEncoder.encodePassword(newPassword, ""));
				// 更新策略/密码修改时间
				if(DictContants.PLAS_PWD_STRATEGY_9.equals(acct.getPwdStrategyCd())){
					acct.setPwdStrategyCd(DictContants.PLAS_PWD_STRATEGY_3);
				}
				acct.setPwdLastModDate(new Date());
				
				mainOperateTip = "修改密码完成!";
				sumarry = OperConst.EDT;
				break;
			}
			
			// 设置未同步EAS
			case ACCT_PWD_NOSYN_EAS:
				log.info(" >>>>>>>>>>>>>>>>>>>>>>>设置未同步EAS - operUiid:" + operUiid + ",");
				if (Util.easOpen(acct.getEasFlg())) {
					mainOperateTip = "设置未同步EAS成功!";
					acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
					sumarry = OperConst.EDT;
				} else {
					mainOperateTip = "设置未同步EAS失败!";
					sumarry = OperConst.EDT;
					bSuccessFlg = false;
				}
				break;

			// 设置未同步EMAIL
			case ACCT_PWD_NOSYN_EMAIL:
				log.info(" >>>>>>>>>>>>>>>>>>>>>>>设置未同步EMAIL - operUiid:" + operUiid + ",");
				if (Util.emailOpen(acct.getEmailFlg())) {
					acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_NO);
					mainOperateTip = "设置未同步EMAIL成功!";
					sumarry = OperConst.EDT;
				} else {
					mainOperateTip = "设置未同步EMAIL失败!";
					sumarry = OperConst.EDT;
					bSuccessFlg = false;
				}
				break;

			// 设置未同步EAS
			case ACCT_PWD_NOSYN_MYSOFT:
				log.info(" >>>>>>>>>>>>>>>>>>>>>>>设置未同步MYSOFT - operUiid:" + operUiid + ",");
				if (Util.mysoftOpen(acct.getMysoftFlg())) {
					mainOperateTip = "设置未同步MYSOFT成功!";
					acct.setMysoftPasswordSetFlg(DictContants.PLAS_MYSOFT_PASSWORD_SET_FLG_NO);
					sumarry = OperConst.EDT;
				} else {
					mainOperateTip = "设置未同步MYSOFT失败!";
					sumarry = OperConst.EDT;
					bSuccessFlg = false;
				}
				break;
			// 设置未同步coremail
			case ACCT_PWD_NOSYN_CMAIL:
				log.info(" >>>>>>>>>>>>>>>>>>>>>>>设置未同步coremail - operUiid:" + operUiid + ",");
				if (Util.cmailOpen(acct.getCmailFlg())) {
					mainOperateTip = "设置未同步coremail成功!";
					acct.setCmailPasswordSetFlg(DictContants.CMAIL_PASSWORD_SET_FLG_NO);
					sumarry = OperConst.EDT;
				} else {
					mainOperateTip = "设置未同步coremail失败!";
					sumarry = OperConst.EDT;
					bSuccessFlg = false;
				}
				break;

			default:
				throw new Exception("调用类型 不合法!操作类型:" + iOperateType + "[1-冻结/2-生效/3-注销/4-重置密码]");
			}

			this.savePlasAcct(acct);
//			this.updateMailInfoByUiid(acct.getUiid());//更新账户状态

			String sb2 = new StringBuffer().append("[").append(acct.getUiid()).append(",")
					.append(tmpUser.getUserName()).append("]").append(mainOperateTip).append(sb).toString();
			// 保存操作日志
			plasOperateLogManager.savePlasOperateLog(operUiid, operName, OperConst.ACCT, sumarry, sb2);

			return new OperResultVo(bSuccessFlg ? OperResultVo.TYPE_SUCCESS : OperResultVo.TYPE_SUCPART, sb2);
		} catch (Exception e) {
			String sb2 = new StringBuffer().append("[").append(acct.getUiid())
					.append("]操作类型:" + iOperateType + "[1-冻结2-生效3-注销4-重置密码]异常!").append(e.getMessage()).toString();
			log.error("EAS操作异常:" + e.getMessage());
			// 保存操作日志
			plasOperateLogManager.savePlasOperateLog(operUiid, operName, OperConst.ACCT, sumarry, sb2);
			
			return OperResultVo.getErrorVo(sb2);
		}
	}


	// ------------------------以下对[EAS账号管理]进行操作 ----------------------------

	/**
	 * EAS(开通/禁用/启用/同步账号信息/重置密码|同步密码)
	 * 
	 * @param plasAcctId
	 * @param iOperateType
	 * @throws Exception
	 */ 

	public OperResultVo easOperate(String plasAcctId, int iOperateType){
		return easOperate(plasAcctId, iOperateType, null);
	}
	public OperResultVo easOperate(String plasAcctId, int iOperateType, String operUiid) {
		
		PlasAcct acct = getEntity(plasAcctId);
		try {
			PlasUser tmpUser = acct.getPlasUser();
			StringBuffer sb = new StringBuffer();
			String operName = "";
			if (StringUtils.isNotBlank(operUiid)) {
				operName = tmpUser.getUserName();
			} else {
				operUiid = SpringSecurityUtils.getCurUiid();
				operName = SpringSecurityUtils.getCurUserName();
			}

			// 是否操作失败
			boolean bSuccessFlg = false;
			String mainOperateTip = "";
			String sumarry = "";

			this.savePlasAcct(acct);

			String sb2 = new StringBuffer().append("[").append(acct.getUiid()).append(",")
					.append(tmpUser.getUserName()).append("]").append(mainOperateTip).append(sb).toString();
			// 保存操作日志
			plasOperateLogManager.savePlasOperateLog(operUiid, operName, OperConst.ACCT, sumarry, sb2);
			return new OperResultVo(bSuccessFlg ? OperResultVo.TYPE_SUCCESS : OperResultVo.TYPE_FAILURE, sb2);

		} catch (Exception e) {
			String sb2 = new StringBuffer().append("[").append(acct.getUiid())
					.append("]操作类型:" + iOperateType + "[1-创建 2-修改 3-失效 4-生效5-重置密码 6-修改密码]异常!").append(e.getMessage())
					.toString();
			return OperResultVo.getErrorVo(sb2);
		}

	}
	// ------------------------以下对[MYSOFT账号管理]进行操作 ----------------------------

	/**
	 * mysoft(开通/禁用/启用/同步账号信息/重置密码|同步密码)
	 * 
	 * @param plasAcctId
	 * @param iOperateType
	 * @throws Exception
	 */
	public OperResultVo mysoftOperate(String plasAcctId, int iOperateType) {
		return mysoftOperate(getEntity(plasAcctId), iOperateType, null);
	}

	public OperResultVo mysoftOperate(String plasAcctId, int iOperateType, String operUiid) {
		return mysoftOperate(getEntity(plasAcctId), iOperateType, operUiid);
	}

	public OperResultVo mysoftOperate(PlasAcct acct, int iOperateType) {
		return mysoftOperate(acct, iOperateType, null);
	}

	public OperResultVo mysoftOperate(PlasAcct acct, int iOperateType, String operUiid) {
		return mysoftOperate(acct, iOperateType, operUiid, null);
	}

	public OperResultVo mysoftOperate(PlasAcct acct, int iOperateType, String operUiid, String oriPassword) {

		try {
			PlasUser tmpUser = acct.getPlasUser();
			StringBuffer sb = new StringBuffer();
			String operName = "";
			if (StringUtils.isNotBlank(operUiid)) {
				operName = tmpUser.getUserName();
			} else {
				operUiid = SpringSecurityUtils.getCurUiid();
				operName = SpringSecurityUtils.getCurUserName();
			}

			// 是否操作失败
			boolean bSuccessFlg = true;
			String mainOperateTip = "";
			String sumarry = "";

			this.savePlasAcct(acct);

			String sb2 = new StringBuffer().append("[").append(acct.getUiid()).append(",")
					.append(tmpUser.getUserName()).append("]").append(mainOperateTip).append(sb).toString();
			// 保存操作日志
			plasOperateLogManager.savePlasOperateLog(operUiid, operName, OperConst.ACCT, sumarry, sb2);
			return new OperResultVo(bSuccessFlg ? OperResultVo.TYPE_SUCCESS : OperResultVo.TYPE_FAILURE, sb2);

		} catch (Exception e) {
			String sb2 = new StringBuffer().append("[").append(acct.getUiid())
					.append("]操作类型:" + iOperateType + "[1-创建 2-修改 3-失效 4-生效5-重置密码 6-修改密码]异常!").append(e.getMessage())
					.toString();
			return OperResultVo.getErrorVo(sb2);
		}

	}
	/**
	 * 更新用户信息的邮箱地址
	 * 
	 * @param uiid
	 * @param email
	 */
	private boolean updateUserEmail(String uiid, String email) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("uiid", uiid);
		values.put("email", email);

		String hql = " update PlasUser t set t.email = :email where t.uiid = :uiid ";
		int i = this.getDao().batchExecute(hql, values);
		log.info("更新用户" + uiid + "邮箱:" + email + ",影响 " + i + "行");
		if (i > 0)
			return true;
		else
			return false;
	}

	/**
	 * 提供定时任务调用 目前当天密码错误3次,账户自动锁定,因此需当晚对生效次数进行重置(即账户未锁定,且错误次数>0的设置为0)
	 */
	public void resetFailureTimes() {
		String hql = " update PlasAcct t set t.failureTimes = 0 where t.statusCd <> ? and t.failureTimes > ? ";
		int count = this.getDao().batchExecute(hql, DictContants.PLAS_USER_STATUS_FREEZE, new Integer(0));
		log.debug(" 运行定时任务(重置密码错误次数),影响" + count + "行");
	}

	/**
	 * 查询邮箱可用的账号合计
	 * 
	 * @return
	 */
	public long getEmailEnableCount() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("acctCreate", DictContants.PLAS_USER_STATUS_CREATE);
		map.put("acctFreeze", DictContants.PLAS_USER_STATUS_FREEZE);

		map.put("openEmail", DictContants.PLAS_EMAIL_FLG_ENABLE);

		String hql = " from PlasAcct t where (t.statusCd = :acctCreate or t.statusCd = :acctFreeze ) and t.emailFlg = :openEmail ";
		return countHqlResult(hql, map);
	}

	/**
	 * 查询未同步账号列表
	 * 
	 * @param page
	 * @return
	 */
	public Page<PlasAcct> findNotSynAccts(Page<PlasAcct> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("acctCreate", DictContants.PLAS_USER_STATUS_CREATE);
		map.put("acctFreeze", DictContants.PLAS_USER_STATUS_FREEZE);

		map.put("openEmail", DictContants.PLAS_EMAIL_FLG_ENABLE);

		StringBuffer hqlBuf = new StringBuffer().append(" from PlasAcct t ")
				.append(" where (t.statusCd = :acctCreate or t.statusCd = :acctFreeze )")
				.append(" and t.emailFlg = :openEmail ")
				.append(" and not exists (select 1 from BisSynEmailUser t2 where t2.plasAcctId = t.plasAcctId)");
		return findPage(page, hqlBuf.toString(), map);
	}

	/**
	 * 查询"指定日期"未启用的账号清单(全部)
	 * 
	 * @param startDate
	 * @param endDate
	 *            不为空
	 * @return
	 */
	public List<PlasAcct> getTobeOpenAccts(Date startDate, Date endDate) {

		if (endDate == null)
			return new ArrayList<PlasAcct>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("acctNoEnter", DictContants.PLAS_USER_STATUS_NOENTER);
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		StringBuffer hqlBuf = new StringBuffer()
				.append(" from PlasAcct t where t.statusCd = :acctNoEnter and t.effectDate is not null ");
		if (startDate != null) {
			hqlBuf.append(" and t.effectDate >= :startDate ");
		}
		if (endDate != null) {
			hqlBuf.append(" and t.effectDate <= :endDate ");
		}

		return find(hqlBuf.toString(), map);
	}

	/**
	 * [定时任务]开通账户
	 * 
	 * @return
	 */
	public String timerOpenAccts() {
		return timerOpenAccts(null, new Date());
	}

	public String timerOpenAccts(Date startDate, Date endDate) {

		log.info("启用账号 ...");
		StringBuffer sucBuf = new StringBuffer();
		StringBuffer falBuf = new StringBuffer();
		StringBuffer result = new StringBuffer();
		
		try{
			List<PlasAcct> tmpAccts = getTobeOpenAccts(startDate, endDate);
			PlasAcct acct = null;
			PlasUser tmpUser = null;
			
			String tUiid = null;
			String tUserName = null;
	
			BisSmsTmp tmp = null;
			for (int i = 0; i < tmpAccts.size(); i++) {
				acct = tmpAccts.get(i);
				tmpUser = acct.getPlasUser();
				
				tUiid = tmpUser.getUiid();
				tUserName = tmpUser.getUserName();
				try {
					boolean flag = false;
					//若已开通,则生效
					if(Util.cmailOpen(acct.getCmailFlg())){
						flag = acctOperate(acct, ACCT_ENABLE).getSuccess();
					}else{
						flag = acctOperate(acct, ACCT_ENABLE).getSuccess();
					}
					if (flag) {
						sucBuf.append(tUserName).append("(").append(tUiid).append(")");
						
						//等待发送手机短信
						tmp = new BisSmsTmp();
						tmp.setUiid(tUiid);
						tmp.setUserName(tmpUser.getUserName());
						tmp.setTypeCd(BisSmsTmpManager.TYPE_CD_OPEN);
						tmp.setStatusCd(BisSmsTmpManager.STATUS_CD_TODO);
						bisSmsTmpManager.saveBisSmsTmp(tmp);
					} else {
						falBuf.append(tUserName).append("(").append(tUiid).append(")");
					}
				} catch (Exception e) {
					falBuf.append(tUserName).append("(").append(tUiid).append(")");
					log.error("启用账号:"+tUiid+"("+tUserName+"),异常!" + e.getMessage());
				}
			}
	
			String operUiid = null;
			String operUserName = null;
			try{
				operUiid = SpringSecurityUtils.getCurUiid();
				operUserName = SpringSecurityUtils.getCurUserName();
			}catch (Exception e) {
				//log.error("获取操作人员异常!",e);
				operUiid = "system";
				operUserName = "系统自动";
			}
	
			// 保存操作日志
			if (StringUtils.isNotBlank(sucBuf.toString())) {
				plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.ACCT, OperConst.OPEN, new StringBuffer(
						" 启用账号成功! 如下:").append(sucBuf).toString());
			}
			if (StringUtils.isNotBlank(falBuf.toString())) {
				plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.ACCT, OperConst.OPEN, new StringBuffer(
						" 启用账号失败! 如下:").append(falBuf).toString());
			}
	
	
			if (StringUtils.isNotBlank(sucBuf.toString())) {
				result.append("启用成功账号: ").append(sucBuf);
			}
			if (StringUtils.isNotBlank(falBuf.toString())) {
				result.append(" <br/>启用失败账号:").append(falBuf);
			}
			log.info("启用账号执行完成!共"+tmpAccts.size()+"个," + result.toString());
		}catch(Exception e){
//			e.printStackTrace();
			log.error("启用账号异常!", e);
		}
		log.info("执行结果: " + result.toString());
		return result.toString();
	}

	/**
	 * 获得账号对应的角色 对应关系：账号--》系统职位--》角色 List<PlasRole>
	 */
	public List<PlasRole> getRoles(PlasAcct acct) {
		List<PlasSysPosRoleRel> sysPosRoleList = null;
		List<PlasRole> result = new ArrayList<PlasRole>();
		for (PlasSysPosition p : acct.getPlasSysPositions()) {
			sysPosRoleList = p.getPlasSysPosRoleRels();
			for (PlasSysPosRoleRel r : sysPosRoleList) {
				if (!result.contains(r.getPlasRole())) {
					result.add(r.getPlasRole());
				}
			}
		}
		return result;
	}

	/**
	 * 查询账号列表
	 * 
	 * @param onlyEnableAcctFlg
	 * @return
	 */
	public List<AcctTreeVo> getAcctTreeVoList(boolean onlyEnableAcctFlg) {
		// long l1 = System.currentTimeMillis();

		// 账号ID
		// 账号状态ID
		// 机构ID
		// email
		// fixedPhone
		// sexCd
		// userName
		// workDutyDesc
		// reasPosCd

		StringBuffer hqlBuf = new StringBuffer()
				.append(" select t2.plasOrgId,t.plasAcctId,t.statusCd,t.email,t.plasUser.fixedPhone,t.plasUser.sexCd,t.plasUser.userName,t.plasUser.workDutyDesc,t.plasUser.realPosCd ")
				.append(" from PlasAcct t,PlasOrg t2 ").append(" where t.plasUser.plasOrg.plasOrgId = t2.plasOrgId ");

		// 若显示状态账号
		Map<String, Object> params = new HashMap<String, Object>();
		if (onlyEnableAcctFlg) {
			hqlBuf.append(" and (t.statusCd = :noEnter or t.statusCd = :normal or t.statusCd = :freeze )");
			params.put("noEnter", DictContants.PLAS_USER_STATUS_NOENTER);
			params.put("normal", DictContants.PLAS_USER_STATUS_CREATE);
			params.put("freeze", DictContants.PLAS_USER_STATUS_FREEZE);
		}

		List<Object> list = this.getDao().find(hqlBuf.toString(), params);
		Object[] obj = null;
		AcctTreeVo tmpVo = null;

		List<AcctTreeVo> resultList = new ArrayList<AcctTreeVo>();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			if (obj != null) {
				tmpVo = new AcctTreeVo();

				tmpVo.setParentId((String) obj[0]);
				tmpVo.setPlasAcctId((String) obj[1]);
				tmpVo.setStatusCd((String) obj[2]);
				tmpVo.setEmail((String) obj[3]);
				tmpVo.setFixedPhone((String) obj[4]);
				tmpVo.setSexCd((String) obj[5]);
				tmpVo.setUserName((String) obj[6]);
				tmpVo.setWorkDutyDesc((String) obj[7]);
				tmpVo.setRealPosCd((String) obj[8]);

				if ((String) obj[0] == null) {
					log.info("未找到上级机构!" + tmpVo.getUserName() + "[" + tmpVo.getPlasAcctId() + "]");
					continue;
				}

				resultList.add(tmpVo);
			}
		}
		return resultList;
	}

	/**
	 * 是否只要可用账号
	 * 
	 * @param isAllAcct
	 *            true-所有账号 false-非注销账号
	 * @return
	 */
	public VoAcct getVoAcctByUiid(String uiid) {
		List<VoAcct> list = getVoAcctList(true, uiid);
		if(list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	public List<VoAcct> getVoAcctList(boolean bAllAcct) {
		return getVoAcctList(bAllAcct, null);
	}
	private List<VoAcct> getVoAcctList(boolean bAllAcct,String eqUiid) {
		// long l1 = System.currentTimeMillis();

		// 查询所有用户(包含失效)
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hqlBuf = new StringBuffer()
				.append(" select t2.plasOrgId,")
				.append(" 		 t.plasAcctId,")
				.append(" 		 t.uiid,")
				.append("		 t.statusCd,")
				.append(" 		 t.email,")
				.append(" 		 t.plasUser.fixedPhone,")
				.append(" 		 t.plasUser.sexCd,")
				.append(" 		 t.plasUser.userName,")
				.append(" 		 t.plasUser.workDutyDesc,")
				.append(" 		 t.plasUser.realPosCd, ")
				.append(" 		 t.plasUser.sequenceNo, ")
				//以下3字段cormail使用
				.append(" 		 t.loginInPassword, ")
				.append(" 		 t.plasUser.mobilePhone, ")
				.append(" 		 t.plasUser.birthday,  ")
				.append(" 		 t2.orgBizCd, ")
				.append("        t.plasUser.serviceStatusCd, ")
				.append("        t2.orgName ")
				.append("   from PlasAcct t,PlasOrg t2 ")
				.append("  where t.plasUser.plasOrg.plasOrgId = t2.plasOrgId ");

		//账号
		if(StringUtils.isNotBlank(eqUiid)){
			hqlBuf.append(" and t.uiid = :eqUiid ");
			params.put("eqUiid", eqUiid);
		}
		
		// 若显示状态账号
		if (!bAllAcct) {
			hqlBuf.append(" and (t.statusCd = :noEnter or t.statusCd = :normal or t.statusCd = :freeze )");
			params.put("noEnter", DictContants.PLAS_USER_STATUS_NOENTER);
			params.put("normal", DictContants.PLAS_USER_STATUS_CREATE);
			params.put("freeze", DictContants.PLAS_USER_STATUS_FREEZE);
			//过滤只读用户
			hqlBuf.append(" and t.uiid not in( select t2.dictCd from AppDictData t2 where t2.appDictType.dictTypeCd = :filterDictTypeCd ) ");
			params.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
		}
		hqlBuf.append(" order by t.plasUser.sequenceNo desc ");

		List<Object> list = this.getDao().find(hqlBuf.toString(), params);
		Object[] obj = null;
		VoAcct tmpVo = null;

		List<VoAcct> resultList = new ArrayList<VoAcct>();
		Date tmpDate = null;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			if (obj != null) {
				tmpVo = new VoAcct();

				tmpVo.setParentOrgId((String) obj[0]);
				tmpVo.setAcctId((String) obj[1]);
				tmpVo.setUiid((String) obj[2]);
				tmpVo.setStatusCd((String) obj[3]);
				tmpVo.setEmail((String) obj[4]);
				tmpVo.setFixedPhone((String) obj[5]);
				tmpVo.setSexCd((String) obj[6]);
				tmpVo.setUserName((String) obj[7]);
				tmpVo.setWorkDutyDesc((String) obj[8]);
				tmpVo.setRealPosCd((String) obj[9]);

				tmpVo.setSequenceNo(obj[10] == null?new BigDecimal(0): ((BigDecimal)obj[10]));
				tmpVo.setMd5password((String) obj[11]);
				tmpVo.setMobilePhone((String) obj[12]);
				tmpDate = (Date) obj[13];
				tmpVo.setBirthday((tmpDate== null)?"":DateOperator.formatDate(tmpDate, "yyyy-MM-dd"));
				tmpVo.setOrgBizCd((String) obj[14]);
				tmpVo.setServiceStatusCd((String) obj[15]);
				tmpVo.setOrgName((String) obj[16]);//add by huangbijin 2012-04-06 统计未读邮件
				
				//System.out.println(tmpVo.getUiid()+":"+tmpVo.getSequenceNo());

				if (StringUtils.isBlank(tmpVo.getParentOrgId())) {
					log.info("未找到上级机构!" + tmpVo.getUserName() + "[" + tmpVo.getUiid() + "]");
					continue;
				} else {
					resultList.add(tmpVo);
				}
			}
		}
		return resultList;
	}

	/**
	 * 查询机构映射账号列表
	 * 
	 * @param onlyEnableAcctFlg
	 *            true-可用[未入职+正常+冻结(解冻)] false-全部[可用+注销]
	 * @return
	 */
	// TODO:remove function
	public Map<String, List<AcctTreeVo>> getAcctTreeVoMap(boolean onlyEnableAcctFlg) {

		String tmpOrgId = null;
		List<AcctTreeVo> resultList = getAcctTreeVoList(onlyEnableAcctFlg);
		List<AcctTreeVo> tmpVoList = null;
		// 构造map{orgID: acctList}
		Map<String, List<AcctTreeVo>> vos = new HashMap<String, List<AcctTreeVo>>();
		for (AcctTreeVo tmpVo : resultList) {
			tmpOrgId = tmpVo.getParentId();
			if (StringUtils.isBlank(tmpOrgId)) {
				continue;
			}
			if (!vos.keySet().contains(tmpOrgId)) {
				tmpVoList = new ArrayList<AcctTreeVo>();
			} else {
				tmpVoList = vos.get(tmpOrgId);
			}
			tmpVoList.add(tmpVo);
			vos.put(tmpVo.getParentId(), tmpVoList);
		}

		// long l2 = System.currentTimeMillis();
		// System.out.println(l2-l1);

		return vos;
	}

	/**
	 * 
	 * Description:模糊查询账号(匹配字段 uiid,custLoginName,userName)
	 * 
	 * @param mgrOrgIds
	 *            用户有权限查询机构Id列表
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasAcct> getFindAcctList(String uiidOrname, String maxNum) {
		return getFindAcctList(uiidOrname, maxNum, new ArrayList<String>());
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasAcct> getFindAcctList(String uiidOrname, String maxNum, List<String> mgrOrgIds) {
		if (StringUtils.isBlank(maxNum)) {
			maxNum = "30";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uiidOrname", "%" + uiidOrname + "%");
		map.put("statusCd", DictContants.PLAS_USER_STATUS_CLOSED);
		Page<PlasAcct> page = new Page<PlasAcct>(new Integer(maxNum));
		StringBuffer hql = new StringBuffer(" select distinct(t) from PlasAcct t "
				+ "where (lower(t.uiid) like :uiidOrname or lower(t.custLoginName) like :uiidOrname "
				+ "or lower(t.plasUser.userName) like :uiidOrname) ");

		// 获得所管理子孙机构列表
		if (null == mgrOrgIds)
			return new ArrayList<PlasAcct>();// 没有权限查询账户
		else if (mgrOrgIds.size() == 0) {
			hql.append("");//无限制
		} else {
			int i = 0;
			hql
			.append(" and t.statusCd !=:statusCd")
			.append(" and t.plasUser.plasOrg.plasOrgId in (");
			for (String s : mgrOrgIds) {
				if (i > 0) {
					hql.append(", :orgId" + i + " ");
				} else {
					hql.append(" :orgId" + i + " ");
				}
				map.put("orgId" + i, s);
				i++;
			}
			hql.append(")");
		}

		hql.append("order by t.sequenceNo desc ");

		page = this.getDao().findPage(page, hql.toString(), map);
		if (page.getResult() == null)
			return new ArrayList<PlasAcct>();
		else
			return page.getResult();
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasAcct> getFindLeaveAcctList(String uiidOrname, String maxNum) {
		if (StringUtils.isBlank(maxNum)) {
			maxNum = "30";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uiidOrname", "%"+uiidOrname+"%");
		map.put("statusCd", DictContants.PLAS_USER_STATUS_CLOSED);
		Page<PlasAcct> page = new Page<PlasAcct>(new Integer(maxNum));
		StringBuffer hql = new StringBuffer()
		.append(" select distinct(t) from PlasAcct t ")
		.append("  where t.statusCd = :statusCd ")
		.append("     and (")
		.append("        lower(t.uiid) like :uiidOrname ")
		.append("     or lower(t.custLoginName) like :uiidOrname ")
		.append("     or lower(t.plasUser.userName) like :uiidOrname ")
		.append("     )")
		.append("  order by t.sequenceNo desc ");

		page = this.getDao().findPage(page, hql.toString(), map);
		if (page.getResult() == null)
			return new ArrayList<PlasAcct>();
		else
			return page.getResult();
	}
	/**
	 * 校验用户名，密码
	 * 
	 * @return
	 */
	public boolean validateUser(String uiid, String md5Password) {

		PlasAcct user = getPlasAcctByUiid(uiid);
		if (user == null)
			return false;

		// 若冻结或注销,返回失败
		if (DictContants.PLAS_USER_STATUS_FREEZE.equals(user.getStatusCd())
				|| DictContants.PLAS_USER_STATUS_CLOSED.equals(user.getStatusCd()))
			return false;

		if (md5Password.equals(user.getLoginInPassword()))
			return true;
		else
			return false;
	}

	/* ******************************************************************************** */

	public List<WsPlasAcct> getWsAll() {
		return getWsAll(null);
	}

	public List<WsPlasAcct> getWsAll(String uiid) {
		StringBuffer sb = new StringBuffer()
				.append("select t.plasAcctId, ")
				// plasUser
				.append(" t.plasUser.plasUserId, ")
				.append(" t.plasUser.userCd, ")
				.append(" t.plasUser.userName, ")
				.append(" t.plasUser.plasOrg.plasOrgId, ")
				.append(" t.plasUser.plasOrg.orgCd, ")
				.append(" t.plasUser.plasOrg.orgName, ")
				.append(" t.plasUser.workDutyDesc, ")
				.append(" t.plasUser.idno, ")

				.append(" t.uiid, ")
				.append(" t.acctSeqNo, ")
				.append(" t.custLoginName, ")
				.append(" t.statusCd, ")
				.append(" t.email, ")
				.append(" t.authenticTypeCd, ")
				.append(" t.loginInPassword, ")
				.append(" t.lockedDate, ")
				.append(" t.lastLoginDate, ")
				.append(" t.lastLogoutDate, ")
				.append(" t.lastLoginIp, ")
				.append(" t.effectDate, ")
				.append(" t.invalidDate, ")
				.append(" t.failureTimes, ")
				.append(" t.macAddress, ")
				.append(" t.macLockedFlg, ")
				.append(" t.easFlg, ")
				.append(" t.easPasswordSetFlg, ")
				.append(" t.emailFlg, ")
				.append(" t.emailPasswordSetFlg, ")
				.append(" t.activeBl, ")
				.append(" t.sequenceNo, ")
				.append(" t.remark, ")
				.append(" t.plasUser.userTypeCd, ")//pd登录,若是mail账号,提示不允许登录.
				.append(" t.pwdLastModDate, ")//最后修改日期
				.append(" t.pwdStrategyCd, ")//密码策略
				.append(" t.cmailFlg, ")
				.append(" t.cmailPasswordSetFlg, ")
				.append(" t.mysoftFlg, ")
				.append(" t.mysoftPasswordSetFlg ")
				
				.append(" from PlasAcct t ")
				.append(" where t.activeBl = :activeBl ");

		if (StringUtils.isNotBlank(uiid)) {
			sb.append(" and t.uiid = :uiid ");
		}
		sb.append(" order by t.plasUser.sequenceNo desc ");

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("activeBl", new Boolean(true));
		values.put("uiid", uiid);

		List list = this.getDao().find(sb.toString(), values);

		List<WsPlasAcct> rtnList = new ArrayList<WsPlasAcct>();
		if (list != null) {
			Object[] t = null;
			WsPlasAcct tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[]) list.get(i);
				tmp = new WsPlasAcct();
				tmp.setPlasAcctId((String) t[0]);

				// plasUser
				tmp.setUserId((String) t[1]);
				tmp.setUserCd((String) t[2]);
				tmp.setUserName((String) t[3]);
				tmp.setOrgId((String) t[4]);
				tmp.setOrgCd((String) t[5]);
				tmp.setOrgName((String) t[6]);
				tmp.setRealPositonName((String) t[7]);
				tmp.setIdno((String) t[8]);

				tmp.setUiid((String) t[9]);
				tmp.setAcctSeqNo((String) t[10]);
				tmp.setCustLoginName((String) t[11]);
				tmp.setStatusCd((String) t[12]);
				tmp.setEmail((String) t[13]);
				tmp.setAuthenticTypeCd((String) t[14]);
				tmp.setLoginInPassword((String) t[15]);
				tmp.setLockedDate((Date) t[16]);
				tmp.setLastLoginDate((Date) t[17]);
				tmp.setLastLogoutDate((Date) t[18]);
				tmp.setLastLoginIp((String) t[19]);
				tmp.setEffectDate((Date) t[20]);
				tmp.setInvalidDate((Date) t[21]);
				tmp.setFailureTimes((BigDecimal) t[22]);
				tmp.setMacAddress((String) t[23]);
				tmp.setMacLockedFlg((String) t[24]);
				tmp.setEasFlg((String) t[25]);
				tmp.setEasPasswordSetFlg((String) t[26]);
				tmp.setEmailFlg((String) t[27]);
				tmp.setEmailPasswordSetFlg((String) t[28]);
				tmp.setActiveBl((Boolean) t[29]);
				tmp.setSequenceNo( t[30] == null? (new BigDecimal(0)):((BigDecimal) t[30]));
				tmp.setRemark((String) t[31]);
				tmp.setUserTypeCd((String) t[32]);
				tmp.setPwdLastModDate((Date) t[33]);
				tmp.setPwdStrategyCd((String) t[34]);
				//coremail 2011-09-29
				tmp.setCmailFlg((String) t[35]);
				tmp.setCmailPasswordSetFlg((String) t[36]);
				tmp.setMysoftFlg((String) t[37]);
				tmp.setMysoftPasswordSetFlg((String) t[38]);

				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	public WsPlasAcct getWsAcct(String uiid) {
		List<WsPlasAcct> accts = getWsAll(uiid);
		if (accts == null || accts.size() == 0)
			return null;
		else
			return accts.get(0);
	}

	// TODO
	public boolean updateAcct(WsPlasAcct acct) {
		return false;
	}

	// 不需要登录验证(身份证)
	public boolean ignoreLoginValidate(String tmpAcctId) {

		PlasAcct acct = getEntity(tmpAcctId);
		if (acct == null)
			return false;

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("acctId", tmpAcctId);
		values.put("lastLoginDate", new Date());

		String hql = " update PlasAcct t set t.lastLoginDate = :lastLoginDate where t.plasAcctId = :acctId and t.lastLoginDate is null";
		this.getDao().batchExecute(hql, values);

		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getCurUiid(),
				SpringSecurityUtils.getCurUserName(),
				OperConst.ACCT,
				"设置不要登录校验",
				new StringBuffer().append("[").append(acct.getUiid()).append(",")
						.append(acct.getPlasUser().getUserName()).append("]设置不要登录校验!").toString());
		return true;
	}

	// 同步邮箱至通讯录
	public boolean synEmailToContact(String acctId) {
		PlasAcct acct = getEntity(acctId);
		return updateUserEmail(acct.getUiid(), acct.getEmail());
	}

	// 查询所属机构与角色不一致的人员
	public Page<VoAcct> searchUnMatchOrgList(Page page) {

		String hql = new StringBuffer()
				.append(" select t.plasAcct.plasAcctId, ")
				.append(" t.plasAcct.plasUser.plasOrg.plasOrgId, ")
				// 用户所在机构ID
				.append(" t.plasAcct.plasUser.plasUserId, ")
				.append(" t.plasAcct.plasUser.userName, ")
				.append(" t.plasAcct.custLoginName, ")

				.append(" t.plasAcct.statusCd, ")
				.append(" t.plasAcct.plasUser.uiid, ")
				.append(" t.plasAcct.plasUser.workDutyDesc, ")
				.append(" t.plasAcct.plasUser.fixedPhone, ")
				.append(" t.plasAcct.plasUser.mobilePhone, ")

				.append(" t.plasAcct.plasUser.email, ")
				.append(" t.plasAcct.plasUser.sexCd, ")
				.append(" t.plasAcct.plasUser.plasOrg.orgCd, ")
				.append(" t.plasAcct.plasUser.plasOrg.orgName, ")
				// 用户所在机构名称
				.append(" t.plasAcct.plasUser.serviceStatusCd, ")

				// 职位所在机构ID
				.append(" t.plasOrg.plasOrgId, ")
				// 职位所在机构cd
				.append(" t.plasOrg.orgCd, ")
				// 职位所在机构名称
				.append(" t.plasOrg.orgName, ")

				.append(" t.plasAcct.applyStatusCd, ")
				.append(" t.plasAcct.creator, ")
				.append(" t.sysPosName, ")
				.append(" t.plasSysPositionId, ")
				.append(" t.plasAcct.plasUser.sequenceNo ")

				.append(" from PlasSysPosition t ")
				.append(" where t.plasAcct.plasUser.plasOrg.plasOrgId != t.plasOrg.plasOrgId ")
				.append(" order by t.sequenceNo desc ")
				.toString();

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("activeBl", new Boolean(true));
		
		Page<VoAcct> pageAcct = new Page<VoAcct>(30);
		
		Page p = this.getDao().findPage(page, hql, values);

		List<VoAcct> rtnList = new ArrayList<VoAcct>();
		if (p != null && p.getResult() != null) {
			Object[] t = null;
			VoAcct tmp = null;
			for (int i = 0; i < p.getResult().size(); i++) {
				t = (Object[]) p.getResult().get(i);
				tmp = new VoAcct();

				tmp.setAcctId((String) t[0]);
				tmp.setParentOrgId((String) t[1]);
				tmp.setUserId((String) t[2]);
				tmp.setUserName((String) t[3]);
				tmp.setCustAcctName((String) t[4]);

				tmp.setStatusCd((String) t[5]);
				tmp.setUiid((String) t[6]);
				tmp.setWorkDutyDesc((String) t[7]);
				tmp.setFixedPhone((String) t[8]);
				tmp.setMobilePhone((String) t[9]);

				tmp.setEmail((String) t[10]);
				tmp.setSexCd((String) t[11]);
				tmp.setOrgCd((String) t[12]);
				tmp.setOrgName((String) t[13]);
				tmp.setServiceStatusCd((String) t[14]);

				tmp.setPosOrgId((String) t[15]);
				tmp.setPosOrgCd((String) t[16]);
				tmp.setPosOrgName((String) t[17]);
				tmp.setApplyStatusCd((String) t[18]);
				
				tmp.setCreator((String)t[19]);
				tmp.setSysPosName((String)t[20]);
				tmp.setSysPositionId((String)t[21]);
				
				rtnList.add(tmp);
			}
		}
		pageAcct.setResult(rtnList);
		pageAcct.setPageNo(p.getPageNo());
		pageAcct.setPageSize(p.getPageSize());
		pageAcct.setTotalCount(p.getTotalCount());
		
		return pageAcct;
	}
	// 查询未审核通过或为分配职位的账号
	
	public List<VoAcct> getVoWaitingAcctList() {
		return getVoWaitingAcctList("");
	}
	
	public List<VoAcct> getVoWaitingAcctList(String orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t.plasAcctId,")
		.append(" t.uiid,")
		.append(" t.plasUser.userName,")
		.append(" t.plasUser.plasOrg.plasOrgId,")
		.append(" t.plasUser.plasOrg.orgName,")
		.append(" t.applyStatusCd,")
		.append(" t.creator,")
		.append(" t.createdDate ")
		.append(" from PlasAcct  t ,PlasUser u ,PlasOrg o ")
		.append(" where t.plasUser = u and u.plasOrg = o ")
		.append(" and o.activeBl =:activeBl ")
		.append(" and t.plasAcctId not in (")
		.append("	select t1.plasAcct.plasAcctId from PlasSysPosition t1 where t1.plasAcct.plasAcctId is not null ")
		.append(" 		and t.plasAcctId = t1.plasAcct.plasAcctId)");
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("activeBl", new Boolean(true));
		if(StringUtils.isNotBlank(orgId)){
			hql.append(" and o.plasOrgId =:orgId ");
			values.put("orgId", orgId);

		}
		List<Object> p = this.getDao().find( hql.toString(), values);
		
		List<VoAcct> rtnList = new ArrayList<VoAcct>();
		Object[] t = null;
		VoAcct tmp = null;
		for (int i = 0; i < p.size(); i++) {
			t = (Object[]) p.get(i);
			tmp = new VoAcct();
			getVOAcct(tmp,t);
			rtnList.add(tmp);
		}
		
		return rtnList;
	}
	//查找未经审核、为配置系统职位的账号
	public Page<VoAcct> getVoWaitingAcctPage(Page page,String orgId) {
		return getVoWaitingAcctPage(page, orgId, null);
	}
	/**
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<VoAcct> getVoWaitingAcctPage(Page page,String orgIds,String auditStatusCd) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t.plasAcctId,")
		.append(" t.uiid,")
		.append(" t.plasUser.userName,")
		.append(" t.plasUser.plasOrg.plasOrgId,")
		.append(" t.plasUser.plasOrg.orgName,")
		.append(" t.applyStatusCd,")
		.append(" t.creator,")
		.append(" t.createdDate ")
		;
		if(StringUtils.isNotBlank(auditStatusCd)){
			hql
			.append(" , ")
			.append(" m.plasSysPositionId, ")
			.append(" s.sysPosName,s.plasOrg.orgName ")
			.append(" from PlasAcct  t ,PlasAcctPosRelTmp m,PlasSysPosition s ")
			.append(" where    m.plasSysPositionId = s.plasSysPositionId ")
			.append(" and t.plasAcctId = m.plasAcctId ")
			.append(" and m.statusCd =:statusCd");

		}else{
			hql
			.append(" from PlasAcct  t  ")
			.append(" where  t.plasAcctId not in (")
			.append("			select t1.plasAcct.plasAcctId from PlasSysPosition t1  ")
			.append(" 				where t1.plasAcct.plasAcctId is not null")
			.append(" 				and t.plasAcctId = t1.plasAcct.plasAcctId")
			.append(" 		) ")
			.append(" and  t.plasAcctId not in (")
			.append("			select m.plasAcctId from PlasAcctPosRelTmp m  ")
			.append(" 		) ")
			;
		}
/*		if(StringUtils.isNotBlank(orgIds)) {
			hql.append(" and t.plasUser.plasOrg.plasOrgId =:orgId ")
			;
		}
*/		//排除销户账号
		hql
		//.append(" and t.plasUser.plasOrg.activeBl =:activeBl ")
		.append(" and t.statusCd !=:close ");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("activeBl", new Boolean(true));
		values.put("statusCd", auditStatusCd);
		values.put("close", DictContants.PLAS_USER_STATUS_CLOSED);
		StringBuffer orgIdsb = new StringBuffer();
		String[] tmps = orgIds.split(",");
		for (int i = 0;i<tmps.length;i++ ) {
			orgIdsb.append("'").append(tmps[i]).append("'");
			if(i!=tmps.length-1){
				orgIdsb.append(",");
			}
		}
		if(StringUtils.isNotBlank(orgIds)) {
			hql.append(" and t.plasUser.plasOrg.plasOrgId in(")
			.append(orgIdsb.toString()).append(")" );
			;
		}
		values.put("orgIds",orgIdsb.toString()); 
		Page p = this.getDao().findPage(page, hql.toString(), values);
		
		
		List<VoAcct> rtnList = new ArrayList<VoAcct>();
		if (p != null && p.getResult() != null) {
			Object[] t = null;
			VoAcct tmp = null;
			for (int i = 0; i < p.getResult().size(); i++) {
				t = (Object[]) p.getResult().get(i);
				tmp = new VoAcct();
				getVOAcct(tmp,t);
				if(StringUtils.isNotBlank(auditStatusCd)){
					tmp.setSysPositionId((String)t[8]);
					tmp.setSysPosName((String)t[9]+"||"+(String)t[10]);
				}
				rtnList.add(tmp);
			}
		}
		p.setResult(rtnList);
		return p;
	}

	private void getVOAcct(VoAcct tmp,Object[] t){
		tmp.setAcctId((String)t[0]);
		tmp.setUiid((String)t[1]);
		tmp.setUserName((String)t[2]);
		
		tmp.setParentOrgId((String)t[3]);
		tmp.setOrgName((String)t[4]);
		
		tmp.setApplyStatusCd((String)t[5]);
		
		tmp.setCreator((String)t[6]);
		tmp.setCreateDate(DateUtil.timeStamp2String((Timestamp)t[7]).toString());
	}
	public OperResultVo acctCreate(PlasAcct acct,String posId){
		return acctApply(acct, ACCT_CREATE,posId);
	}
	public OperResultVo acctAuditPass(String plasAcctId){
		return acctApply(getEntity(plasAcctId), ACCT_AUDIT_PASS,null);
	}
	public OperResultVo acctAuditReject(String plasAcctId){
		return acctApply(getEntity(plasAcctId), ACCT_AUDIT_REJECT,null);
	}
	public OperResultVo acctApply(PlasAcct acct,int iOperateType ,String tmp ){
		return acctApply(acct, iOperateType,null,tmp);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public OperResultVo acctApply(PlasAcct acct,int iOperateType,String operUiid ,String tmp){
	
		PlasUser tmpUser = acct.getPlasUser();
		StringBuffer sb = new StringBuffer();
		String operName = "";
		if (StringUtils.isNotBlank(operUiid)) {
			operName = tmpUser.getUserName();
		} else {
			operUiid = SpringSecurityUtils.getCurUiid();
			operName = SpringSecurityUtils.getCurUserName();
		}
		
		//是否操作失败
		boolean bSuccessFlg = true;
		String mainOperateTip = "";
		String sumarry = "";
		switch (iOperateType) {
		
		case ACCT_CREATE:
			log.info(" >>>>>>>>>>>>>>>>>>>>>>>创建账号 - operUiid:" + operUiid + ",");
			if (createAcct(acct,tmp)) {
				mainOperateTip = "创建账号成功:"+(DictContants.PLAS_ACCT_AUDIT_NO.equals(acct.getApplyStatusCd())?",未经审核":"审核通过");
				
				sumarry = OperConst.ADD;
				//已写日志  需saveAcct -- > saveUse - > saveTmpAcctPos 
				return new OperResultVo(OperResultVo.TYPE_SUCCESS, "");
			}else{
				mainOperateTip = "创建账号失败!";
				sumarry = OperConst.ADD;
				bSuccessFlg = false;
			}
			break;
			//账号审核通过
		case ACCT_AUDIT_PASS:
			log.info(" >>>>>>>>>>>>>>>>>>>>>>>账号审核通过 - operUiid:" + operUiid + ",");
			if(acctAuditPass(acct, tmp)){
				mainOperateTip = "账号审核通过 :";
				acct.setApplyStatusCd(DictContants.PLAS_ACCT_AUDIT_YES);
				sumarry = OperConst.EDT;
			} else {
				bSuccessFlg = false;
			}
			break;
			//账号开通申请驳回
		case ACCT_AUDIT_REJECT:
			log.info(" >>>>>>>>>>>>>>>>>>>>>>>账号开通申请驳回 - operUiid:" + operUiid + ",");
			acct.setApplyStatusCd(DictContants.PLAS_ACCT_AUDIT_REJECT);
			mainOperateTip = "账号开通申请驳回:";
			sumarry = OperConst.EDT;
			break;
			
		default:
			return new OperResultVo(OperResultVo.TYPE_FAILURE, "无权进行操作");
		}
		
		this.savePlasAcct(acct);
		
		String sb2 = new StringBuffer()
		.append("[").append(acct.getUiid()).append(",").append(tmpUser.getUserName()).append("]").append(mainOperateTip)
		.append(sb).toString();
		// 保存操作日志
		plasOperateLogManager.savePlasOperateLog(operUiid, operName, OperConst.ACCT, sumarry, sb2);
		
		return new OperResultVo(bSuccessFlg?OperResultVo.TYPE_SUCCESS:OperResultVo.TYPE_SUCPART, sb2);
	}
	
	//创建账号 
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean createAcct(PlasAcct acct){
		return createAcct(acct,null);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean acctAuditPass(PlasAcct acct,String sysPosId){
		//人力资源审计人员
		if(RoleUtil.validateRole(GlobalConstants.A_ADMIN_HR_CHIEF)){
			//审核通过
			PlasAcctPosRelTmp tmp =plasAcctPosRelTmpDao.getEntity(acct.getPlasAcctId(),sysPosId);
			tmp.setStatusCd(DictContants.PLAS_ACCT_AUDIT_YES);
			plasAcctPosRelTmpDao.save(tmp);
			//正式关联账号
			PlasSysPosition sysPos = plasSysPositionDao.get(sysPosId);
			sysPos.setPlasAcct(acct);
			plasSysPositionDao.save(sysPos);
			return true;
		}
		return false;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean createAcct(PlasAcct acct,String sysPosCd){
		//1 当前用户为应用管理员或人力资源权限，账号置为审核通过
		//2 当前用户为机构管理员权限（项目公司），账号置为待审核
		String auditStatus = DictContants.PLAS_ACCT_AUDIT_NO;
		if(RoleUtil.isAdmin()||RoleUtil.validateRole(GlobalConstants.A_ADMIN_HR_CHIEF)){
			auditStatus = DictContants.PLAS_ACCT_AUDIT_YES;
		}
	
		acct.setApplyStatusCd(auditStatus);
		
		//默认密码123
		acct.setLoginInPassword(md5PasswordEncoder.encodePassword(appParamManager.getDefaultPassword(), ""));
		
		if(StringUtils.isBlank(acct.getStatusCd())){
			acct.setStatusCd(DictContants.PLAS_USER_STATUS_CREATE);//开通账号
		}
		acct.setFailureTimes(new BigDecimal(0));
		
		acct.setEmailFlg(DictContants.PLAS_EMAIL_FLG_NOOPEN);
		acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_NO);
		
		acct.setEasFlg(DictContants.PLAS_EAS_FLG_NOOPEN);
		acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
		
		acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_NOOPEN);
		acct.setMysoftPasswordSetFlg(DictContants.PLAS_SYN_MYSOFT_USER_NO);

		acct.setCmailFlg(DictContants.CMAIL_FLG_NOOPEN);
		acct.setCmailPasswordSetFlg(DictContants.CMAIL_PASSWORD_SET_FLG_NO);
		
		
		acct.setActiveBl(new Boolean(true));
		acct.setAcctSeqNo("0");//最后一个
		
		//将uiid保存至用户PlasUser
		PlasUser tmpUser = acct.getPlasUser();
		plasUserDao.save(tmpUser);
		
		plasAcctDao.save(acct);
		
		
		saveTmpAcctPos(acct, sysPosCd);
		//默认开通邮箱
//		cmailOperate(acct, CoremailServiceClient.OPER_USER_ADD);

		return true;
	}
	/**
	 * 普通用户,与职位关联  暂时关联
	 */
	public boolean saveTmpAcctPos(PlasAcct acct,String sysPosCd){
		boolean pdFlg = false;
		if((DictContants.PLAS_USER_TYPE_1.equals(acct.getPlasUser().getUserTypeCd())
				||DictContants.PLAS_USER_TYPE_2.equals(acct.getPlasUser().getUserTypeCd())) 
				&& StringUtils.isNotBlank(sysPosCd)){
			if(RoleUtil.isAdmin()){
				//应用管理员 创建账号 ，同时创建系统职位
				saveAcctPos(acct, acct.getPlasUser());
			}else{
			
				//将账号与系统职位暂存至临时表，待审批
				PlasSysPosition pos = plasSysPositionDao.getEntityBySysPosCd(sysPosCd);
				PlasAcctPosRelTmp rel = new PlasAcctPosRelTmp();
				rel.setPlasSysPositionId(pos.getPlasSysPositionId());
				rel.setPlasAcctId(acct.getPlasAcctId());
				rel.setStatusCd(DictContants.PLAS_ACCT_AUDIT_NO);
				plasAcctPosRelTmpDao.save(rel);
			}
		}
		else if(DictContants.PLAS_USER_TYPE_3.equals(acct.getPlasUser().getUserTypeCd())){
			//邮箱用户,不与职位关联
			plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
					new StringBuffer().append("[").append(acct.getUiid()).append(",").append(acct.getPlasUser().getUserName()).append("] 开通账号! 不关联角色!").toString()
			);
		}
		return true;
	}
	/**
	 * 生成系统职位信息
	 */
	public void saveAcctPos(PlasAcct acct,PlasUser user){

		PlasSysPosition sysPos =  plasSysPositionDao.savePlasSysPosition(user,acct);
		
		PlasSysPosRoleRel rel = new PlasSysPosRoleRel();
		rel.setPlasSysPosition(sysPos);
		//创建职位与普通用户关联(A_USER)
		rel.setPlasRole(plasRoleDao.getPlasRoleByRoleCd(GlobalConstants.A_USER));
		plasSysPosRoleRelDao.save(rel);
		plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
				new StringBuffer().append("[").append(user.getUiid()).append(",").append(user.getUserName()).append("] 开通账号! 建立职位与普通用户关联!").toString()
			);
		
	}
	public int notifyLoginError(String uiid, String ip) {

		if(StringUtils.isBlank(uiid))
			return GlobalConstants.NONE_FAILURE_NOTEXIST;//不存在
		
		PlasAcct acct = getPlasAcctByUiid(uiid);
		if(acct == null)
			return GlobalConstants.NONE_FAILURE_NOTEXIST;//不存在
		
		BigDecimal filetimes = acct.getFailureTimes();
		Integer times = 0;
		
		if(filetimes != null){
			times = filetimes.intValue();
			if (times == null) {
				times = new Integer(0);
			}
		}else{
			times = new Integer(0);
		}
		acct.setFailureTimes(new BigDecimal(++times));

		int iRtn = 0;
		StringBuffer sbLog = new StringBuffer("登录失败!");
		
		// 返回剩余错误次数
		int maxFailure = appParamManager.getDefaultMaxFailureTimes();
		if (GlobalConstants.NONE_FAILURE_NOCHECK == maxFailure) {
			savePlasAcct(acct);
			iRtn = maxFailure;
			sbLog.append(" ,达到最大错误次数!("+maxFailure+")");
		}else{
			// 检查是否锁定
			if (maxFailure <= times) {
				acct.setStatusCd(DictContants.PLAS_USER_STATUS_FREEZE);
				savePlasAcct(acct);
				
				iRtn = GlobalConstants.NONE_FAILURE_FROZEN;
				sbLog.append(",已错误次数!("+ times +"次,系统自动冻结PD账户，其他系统不冻结(如eas/exchange等)!");
			} else{
				iRtn = maxFailure - times;
				sbLog.append(",已错误次数 "+ times +" 次,还剩次数 "+iRtn+" 次");
			}
		}
		
		// 保存登陆日志
		PlasLoginLog loginLog = new PlasLoginLog();
		loginLog.setUiid(acct.getUiid());
		loginLog.setUserCd(acct.getPlasUser().getUserCd());
		loginLog.setUserName(acct.getPlasUser().getUserName());
		loginLog.setIp(ip);
		loginLog.setLoginDate(new Date());
		loginLog.setRemark(sbLog.toString());
		
		plasLoginLogManager.savePlasLoginLog(loginLog);
		return iRtn;
	}

	//查询与账号相关的职位列表所在的机构
	public List<String> getRelPosOrgIdList(String acctId) {
		String hql = " select distinct t.plasOrg.plasOrgId from PlasSysPosition t where t.plasAcct.plasAcctId = ?";
		List result = this.getDao().createQuery(hql, acctId).list();
		if (result == null || result.size() == 0)
			return new ArrayList<String>();
		else
			return result;
	}

	// 查询账号与角色关系清单
	public List<Object> getAcctRoleRelList() {

		//uiid,userName,bizOrgCd,orgName,roleCd,roleName,serviceStatusCd,statusCd
		String hql = new StringBuffer()
						.append("select distinct ")
						.append(" t.plasSysPosition.plasAcct.plasUser.uiid,")
						.append(" t.plasSysPosition.plasAcct.plasUser.userName,")
						.append(" t.plasSysPosition.plasAcct.plasUser.plasOrg.orgBizCd,")
						.append(" t.plasSysPosition.plasAcct.plasUser.plasOrg.orgName,")
						.append(" t.plasRole.roleCd,")
						.append(" t.plasRole.roleName,")
						.append(" t.plasSysPosition.plasAcct.plasUser.serviceStatusCd,")
						.append(" t.plasSysPosition.plasAcct.statusCd ")
						.append(" from PlasSysPosRoleRel t")
						.toString();
		return this.getDao().createQuery(hql, new HashMap<String, Object>()).list();
	}
	public static void main(String[] args) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		System.out.println(md5PasswordEncoder.encodePassword("123", ""));
	}

	public long getLikAcctListSize(String uiid) {
		
		String hql = " from PlasAcct t where t.uiid like ?";
		return this.getDao().countHqlResult(hql, uiid+"%");
	}

	public List<String> getLikAcctList(String uiid) {
		
		String hql = "select t.uiid from PlasAcct t where t.uiid like ? ";
		List<String> list = this.getDao().createQuery(hql, uiid +"%").list();
		if(list == null || list.size() == 0)
			return list;
		else
			return list;
	}

	public void saveChgPwdStrategy(String strategyCd, String[] addIdList) {
		addIdList = (String[])ArrayUtils.removeElement(addIdList,"");

		String hql = "update PlasAcct t set t.pwdStrategyCd = ? where t.plasUser.plasUserId =? ";
		for (String tId : addIdList) {
			this.getDao().batchExecute(hql, strategyCd, tId);
		}
	}

	/**
	 * 查询全库失效账号
	 * @return
	 */
	public List<VoAcct> getDisableAcctList() {
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("statusCd", DictContants.PLAS_USER_STATUS_CLOSED);
		values.put("enable", DictContants.CMAIL_FLG_ENABLE);
		values.put("disable", DictContants.CMAIL_FLG_DISABLE);
		String sql = new StringBuffer()
				.append(" select distinct t.plas_acct_id, t.uiid,t2.user_name ")
				.append("   from plas_acct t,plas_user t2 ")
				.append("  where t.plas_user_id = t2.plas_user_id ")
				
				//查询PD注销的用户
				.append("    and t.status_cd = :statusCd and (t.cmail_flg = :enable or t.cmail_flg = :disable)")
				
				//查询PD注销,cmail可用的用户
//				.append("    and t.status_cd = :statusCd and t.cmail_flg = :enable ")
				.toString();
		List list = this.findBySql(sql, values);
		Object[] obj = null;
		VoAcct tmp = null;
		List<VoAcct> rtnList = new ArrayList<VoAcct>();
		if(list != null){
			for (Object t : list) {
				obj = (Object[]) t;
				tmp = new VoAcct();
				tmp.setAcctId((String)obj[0]);
				tmp.setUiid((String)obj[1]);
				tmp.setUserName((String)obj[2]);
				rtnList.add(tmp);
				System.out.println(tmp.getUiid()+","+tmp.getUserName());
			}
		}
		System.out.println("失效的账号列表,大小: " + rtnList.size());
		return rtnList;
	}

	public void disableCmailFlg(String acctId) {
		PlasAcct acct = getEntity(acctId);
		acct.setCmailFlg(DictContants.CMAIL_FLG_DISABLE);
		savePlasAcct(acct);
	}
	
	/**
	 * 注销机构负责人
	 * @param uiid
	 */
	public void removeOrgMgr(String uiid){
		try{
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("orgMgrId", uiid);
			String hql = "update PlasOrg t set t.orgMgrId = null where t.orgMgrId = :orgMgrId ";
			this.getDao().batchExecute(hql, values);
		}catch(Exception e){
			
		}
	}

	/**
	 * 通过机构,查询有占到系统职位的账号
	 * @param plasOrgId
	 * @return
	 */
	public long getEnableAcctCount(String plasOrgId) {
		List<String> orgIds = new ArrayList<String>();
		orgIds.add(plasOrgId);
		return getEnableAcctCount(orgIds);
	}
	
	public long getEnableAcctCount(List<String> orgIds) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) ");
		sb.append(" from plas_org t, ");
		sb.append("	     plas_sys_position t2, ");
		sb.append("      plas_acct t3 ");
		sb.append("where t.plas_org_id in (:orgIds) ");
		sb.append("  and t.plas_org_id = t2.plas_org_id ");
		sb.append("  and t2.plas_acct_id = t3.plas_acct_id ");
		//--0-未入职 1-正常 2-冻结 3-解冻 4-注销 "
		sb.append(" and t3.status_cd in(:s1, :s2) ");
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("orgIds", orgIds);
		values.put("s1", DictContants.PLAS_USER_STATUS_CREATE);
		values.put("s2", DictContants.PLAS_USER_STATUS_FREEZE);
//		values.put("s3", DictContants.PLAS_USER_STATUS_UNFREEZE);
		
		return this.countSqlResult(sb.toString(), values);
	}
}
