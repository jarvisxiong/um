package com.hhz.ump.dao.res;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.spring.SpringContextHolder;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.res.ResApproveDelay;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveNode;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class ResApproveDelayManager extends BaseService<ResApproveDelay, String> {
	@Autowired
	private ResApproveDelayDao resApproveDelayDao;
	@Autowired
	private ResApproveNodeManager resApproveNodeManager;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	
	//企管部处理延期申请的人员 数据字典
	public static String QGB_DELAY_PROCESS_USERS = "QGB_DELAY_PROCESS_USERS";

	// private void update2JbpmTask(ResApproveInfo resApproveInfo) {
	// ResApproveInfoManager resApproveInfoManager =
	// SpringContextHolder.getBean("resApproveInfoManager");
	// resApproveInfoManager.update2JbpmTask(resApproveInfo);
	// }
	private void saveResApproveInfo(ResApproveInfo resApproveInfo) {
		ResApproveInfoManager resApproveInfoManager = SpringContextHolder.getBean("resApproveInfoManager");
		resApproveInfoManager.saveResApproveInfo(resApproveInfo,null);
	}

	public void saveResApproveDelay(ResApproveDelay resApproveDelay) {
		PowerUtils.setEmptyStr2Null(resApproveDelay);
		resApproveDelayDao.save(resApproveDelay);
		// update2JbpmTask(resApproveDelay.getResApproveInfo());
	}

	public void deleteResApproveDelay(String id) {
		resApproveDelayDao.delete(id);
	}

	@Override
	public HibernateDao<ResApproveDelay, String> getDao() {
		return resApproveDelayDao;
	}

	public static String CHIEF_AGREE = "1";
	public static String CHIEF_DEGREE = "0";
	public static String CONFIRM_AGREE = "1";
	public static String CONFIRM_DEGREE = "0";

	// RES_APPROVE_DELAY 
	public static String STATUS_APPLY = "0";// 申请
	public static String STATUS_PROCESS = "1";// 处理中
	public static String STATUS_OK = "2";// 最终OK
	public static String STATUS_REJECT = "3";// 最终驳回

	// RES_APPROVE_INFO 表的状态
	public static String RES_STATUS_NONE = "0";// 0-无延期
	public static String RES_STATUS_APPLY = "1";// 1-已申请
	public static String RES_STATUS_DEGREE = "2";// 2-已驳回
	public static String RES_STATUS_AGREE = "3";// 3-已通过

	/**
	 * 申请延期
	 * @param delay
	 * @throws Exception
	 */
	public void saveResApproveDelayWithApply(ResApproveDelay delay) throws Exception {

		ResApproveInfo resApporveInfo = delay.getResApproveInfo();
		//若3-决策层申请,直接保存
		if("3".equals(delay.getApplyTypeCd())){
			// 注意:不需要设置延期审批人、延期状态
			// 注意:主表一定要先保存
			//同时统一，加时间
			this.saveResApproveDelayWithAgree(delay);
		}
		//若非决策层申请,则更新主表
		else{
			String tmpUserCd = StringUtils.isNotBlank(delay.getApproveUserCd()) ? delay.getApproveUserCd() : delay.getConfirmUserCd();
			resApporveInfo.setDelayApproveUser(tmpUserCd);
			resApporveInfo.setDelayStatusCd(RES_STATUS_APPLY);
			// 注意:主表一定要先保存
			saveResApproveInfo(resApporveInfo);
			this.saveResApproveDelay(delay);
			
			//hidden by huangbijin 2011-10-20
			//add by huangbijin 2011-05-11
			//如果直接到企管部,发送短信 
			//if(StringUtils.isBlank(delay.getApproveUserCd()) && StringUtils.isNotBlank(delay.getConfirmUserCd())){
			//	sendQGBMesage(delay);
			//}
		}
		
	}

	public void saveResApproveDelayWithDegree(ResApproveDelay resApproveDelay) {
		ResApproveInfo approveInfo = resApproveDelay.getResApproveInfo();
		approveInfo.setDelayApproveUser(null);
		approveInfo.setDelayStatusCd(RES_STATUS_DEGREE);
		saveResApproveInfo(approveInfo);
		// 注意:主表一定要先保存
		this.saveResApproveDelay(resApproveDelay);
	}

	public void saveResApproveDelayWithAgree(ResApproveDelay delay) {

		String resApproveId = delay.getResApproveInfo().getResApproveInfoId();

		// 更新节点处理时间
		ResApproveNode node = resApproveNodeManager.getApproveNodeByUserCd(resApproveId, delay.getApplyUserCd(), delay.getNodeCd(), delay.getApproveLevel());
		long tmpLimit = delay.getDelayTime();
		if (node != null) {
			if (node.getTimeLimit() != null) {
				tmpLimit = node.getTimeLimit() + delay.getDelayTime();
			} else {
				tmpLimit = 24 + delay.getDelayTime();
			}
			node.setTimeLimit(tmpLimit);
			resApproveNodeManager.saveResApproveNode(node);
			StringBuffer content = new StringBuffer("您发起的延期申请已经完成审批,请悉知！");
			sendEmail(delay.getResApproveInfo(), "【延期申请完成通知】", content.toString(), delay.getApplyUserCd());
		}

		ResApproveInfo approveInfo = delay.getResApproveInfo();
		approveInfo.setDelayApproveUser(null);
		approveInfo.setTimeLimit(tmpLimit);
		approveInfo.setDelayStatusCd(RES_STATUS_AGREE);
		saveResApproveInfo(approveInfo);
		// 注意:主表一定要先保存
		this.saveResApproveDelay(delay);
	}

	/**
	 * 检查是否存在处理中的申请延期记录
	 * 
	 * @param resApproveId
	 * @return true-是 false-否
	 */
	public ResApproveDelay latestProcessDlay(String resApproveId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resApproveId", resApproveId);
		map.put("statusCd1", STATUS_PROCESS);
		map.put("statusCd2", STATUS_APPLY);
		String hql = " from ResApproveDelay t where t.resApproveInfo.resApproveInfoId = :resApproveId and (t.statusCd = :statusCd1 or t.statusCd = :statusCd2) ";
		List<ResApproveDelay> list = this.find(hql, map);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	/**
	 * (发起人)审批 同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 */
	public void chiefAgree(String resApproveDelayId, String reason) throws Exception {
		
		ResApproveDelay delay = getEntity(resApproveDelayId);
		delay.setApproveOptionCd(CHIEF_AGREE);
		delay.setApproveDate(new Date());
		delay.setStatusCd(STATUS_OK);
		delay.setApproveRemark(reason);
		saveResApproveDelayWithAgree(delay);
	}
	
	/**
	 * 发送短信
	 * @param delay
	 */
	public void sendQGBMesage(ResApproveDelay delay)throws Exception {
		
//		SMS sms = SMS.getInstanceCommon();
		String smsContent = "[延期申请] 申请人:"+  CodeNameUtil.getUserNameByCd(delay.getApplyUserCd())+",网批编号:"+ delay.getResApproveInfo().getApproveCd() + delay.getResApproveInfo().getSerialNo();
		
		Map<String, String> mapUser = appDictTypeManager.getDictDataByTypeCd(QGB_DELAY_PROCESS_USERS);
		//移除"-请选择-"项目
		mapUser.remove("");
		if(mapUser.keySet().size() == 0){
			Log.warn("企管部未设置延期申请处理人!");
			return;
		}
		
		String[] mobiles = new String[mapUser.keySet().size()]; 
		int i=0;
		for(String tmpKey:mapUser.keySet()){
			mobiles[i++] = mapUser.get(tmpKey);
		}
		Util.getPlasService().sendCommonSms("[延期申请]", mobiles,smsContent);
	}

	public void sendEmail(ResApproveInfo resApproveInfo, String title, String content, String userCd) {
		String approveNo = resApproveInfo.getApproveCd() + resApproveInfo.getSerialNo();
		StringBuffer msg = new StringBuffer(content).append("<br/>");
		msg.append("编　号：").append(approveNo).append("<br/>");
		msg.append("发起人：").append(CodeNameUtil.getUserNameByCd(resApproveInfo.getUserCd())).append("<br/>");
		msg.append("类　别：").append(CodeNameUtil.getResAuthTypeNameByCd(resApproveInfo.getAuthTypeCd())).append("<br/>");
		msg.append("标　题：").append(StringUtils.trimToEmpty(resApproveInfo.getTitleName())).append("<br/>");
		// StringBuffer msg = new
		// StringBuffer("审批(编号:").append(approveNo).append(")").append(content);
		msg.append("<div style=\"width: 30px;cursor: pointer;text-decoration:underline;color:blue;\" onclick=\"parent.parent.showAll('").append(
				Struts2Utils.getRequest().getContextPath()).append("/res/res-approve-info.action?id=").append(resApproveInfo.getResApproveInfoId())
				.append("&resAuthTypeCd=").append(resApproveInfo.getAuthTypeCd()).append("','resApprove');\">进入</div>");
		oaEmailBodyManager.sendData2Email(title, msg.toString(), "email_admin", "1", userCd);
	}

	/**
	 * (发起人)审批 不同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 */
	public void chiefDegree(String resApproveDelayId, String reason) {
		ResApproveDelay delay = getEntity(resApproveDelayId);
		delay.setApproveOptionCd(CHIEF_DEGREE);
		delay.setApproveDate(new Date());
		delay.setConfirmOptionCd(null);
		delay.setStatusCd(STATUS_REJECT);
		delay.setApproveRemark(reason);
		saveResApproveDelayWithDegree(delay);
		StringBuffer content = new StringBuffer("您发起的延期申请被").append(CodeNameUtil.getUserNameByCd(delay.getApproveUserCd())).append("拒绝，请悉知！");
		sendEmail(delay.getResApproveInfo(), "【拒绝延期申请通知】", content.toString(), delay.getApplyUserCd());
	}

	/**
	 * (企管部)同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 */
	public void confirmAgree(String resApproveDelayId, String reason) {

		// 更新审批结果
		ResApproveDelay delay = getEntity(resApproveDelayId);
		delay.setConfirmOptionCd(CONFIRM_AGREE);
		delay.setConfirmDate(new Date());
		delay.setStatusCd(STATUS_OK);
		delay.setConfirmRemark(reason);
		saveResApproveDelayWithAgree(delay);
	}

	/**
	 * (企管部)不同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 */
	public void confirmDegree(String resApproveDelayId, String reason) {
		ResApproveDelay delay = getEntity(resApproveDelayId);
		delay.setConfirmOptionCd(CONFIRM_DEGREE);
		delay.setConfirmDate(new Date());
		delay.setStatusCd(STATUS_REJECT);
		delay.setConfirmRemark(reason);
		saveResApproveDelayWithDegree(delay);
		StringBuffer content = new StringBuffer("您发起的延期申请被").append(CodeNameUtil.getUserNameByCd(delay.getConfirmUserCd())).append("拒绝，请悉知！");
		sendEmail(delay.getResApproveInfo(), "【拒绝延期申请通知】", content.toString(), delay.getApplyUserCd());
	}

	/**
	 * 查看申请历史
	 * 
	 * @param resApproveId
	 */
	public List<ResApproveDelay> history(String resApproveId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resApproveId", resApproveId);
		String hql = " from ResApproveDelay t where t.resApproveInfo.resApproveInfoId = :resApproveId order by createdDate desc";
		List<ResApproveDelay> list = this.find(hql, map);
		if (list == null || list.size() == 0)
			return new ArrayList<ResApproveDelay>();
		else
			return list;
	}

	/**
	 * 删除记录
	 * 
	 * @param resApproveDelayId
	 */
	public void cancelDelay(String resApproveDelayId) {
		ResApproveDelay delay = getEntity(resApproveDelayId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resApproveInfoId", delay.getResApproveInfo().getResApproveInfoId());
		map.put("delayStatusCd", RES_STATUS_NONE);
		String hql = " update ResApproveInfo t set t.delayStatusCd = :delayStatusCd, t.delayApproveUser = null where t.resApproveInfoId = :resApproveInfoId ";
		this.getDao().batchExecute(hql, map);

		delete(delay);
	}
}