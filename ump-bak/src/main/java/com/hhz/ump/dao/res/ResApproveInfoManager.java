package com.hhz.ump.dao.res;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.struts2.ServletActionContext;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.aop.HttpRequester;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.res.ResApproveContent;
import com.hhz.ump.entity.res.ResApproveDelay;
import com.hhz.ump.entity.res.ResApproveHis;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveLock;
import com.hhz.ump.entity.res.ResApproveNode;
import com.hhz.ump.entity.res.ResApprovePush;
import com.hhz.ump.entity.res.ResApproveShare;
import com.hhz.ump.entity.res.ResApproveUser;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResBillTemplet;
import com.hhz.ump.entity.res.ResCenterConfirmInfo;
import com.hhz.ump.entity.res.ResNotifyInfo;
import com.hhz.ump.entity.res.ResNotifyReduce;
import com.hhz.ump.entity.res.ResTypeUserRel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.EmailUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.ResConstants;
import com.hhz.ump.web.res.ResXmlParser;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;
import com.hhz.ump.web.res.baseBean.IInProcess;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;
import com.hhz.ump.web.res.bean.NetTempAuthorizeApproveSheet;
import com.hhz.ump.web.res.bean.WorkOrder;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class ResApproveInfoManager extends BaseService<ResApproveInfo, String> {
	private static Log log = LogFactory.getLog(ResApproveInfoManager.class);
	@Autowired
	private ResApproveInfoDao resApproveInfoDao;

	@Autowired
	private ResApproveMessageManager resApproveMessageManager;

	@Autowired
	private ResApproveNodeManager resApproveNodeManager;

	@Autowired
	private ResApproveHisManager resApproveHisManager;

	@Autowired
	private ResApproveLockManager resApproveLockManager;

	@Autowired
	private ResApproveShareManager resApproveShareManager;
	@Autowired
	private ResApproveShareHisManager resApproveShareHisManager;
	@Autowired
	private ResApproveDelayManager resApproveDelayManager;
	@Autowired
	private ResNotifyInfoManager resNotifyInfoManager;
	@Autowired
	private ResNotifyReduceManager resNotifyReduceManager;

	@Autowired
	private ResCenterConfirmInfoManager resCenterConfirmInfoManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private ResBillTempletManager resBillTempletManager;

	// @Autowired
	// private OaEmailBodyManager oaEmailBodyManager;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private ResApprovePushManager resApprovePushManager;
	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	@Autowired
	private AppAttachFileManager attachFileManager;

	@Autowired
	protected ResTypeUserRelManager resTypeUserRelManager;
	@Autowired
	private ResNodeManager resNodeManager;
	@Autowired
	private ResApproveUserManager resApproveUserManager;
	@Autowired
	private ResApproveContentManager resApproveContentManager;

	@Autowired
	private ResImportLogManager resImportLogManager;
	@Autowired
	private ResAuthTypeManager resAuthTypeManager;

	/**
	 * 同步到待办事项。同步的人员涉及：
	 * 1.当前审批人
	 * 2.当前审批人对应的二级审批节点
	 * 3.被共享人
	 * 4.延期审批人
	 * 
	 * @param res
	 */
	public void update2JbpmTask(ResApproveInfo res) {
		List notNowUsers = this.getNotNowUsers(res);
		JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(res.getResApproveInfoId());
		
		//流程状态
		String statusCd = res.getStatusCd();
		//一级审批节点，或 会签节点
		String approveUserCd = res.getApproveUserCd();
		//二级审批节点(+)
		String approveUserCd2 = res.getApproveUserCd2();
		
		//若审批中或驳回
		if (ResConstants.RES_APPROVE_STATUS_ING.equals(statusCd) && StringUtils.isNotBlank(approveUserCd)
				|| ResConstants.RES_APPROVE_STATUS_BACK.equals(statusCd)) {
			
			//若存在下一个审批人
			if (StringUtils.isNotBlank(approveUserCd)) {
				
				// 1.当前审批人，用于显示待办事项
				String[] approveUserCds = approveUserCd.split(";");
				
				// 3.未回复的共享人，用于显示待办事项
				List<String> lstShare = new ArrayList<String>();
				
				for (ResApproveShare approveShare : res.getResApproveShares()) {
					// 无论是否回复都显示在待办事项 2011-07-05
					// if (approveShare.getReplied() == null ||
					// !approveShare.getReplied()) {
					// 共享人中，未回复的才显示待办事项
					lstShare.add(approveShare.getUserCd());
					// }
				}
				
				// 类型转换 list->[]
				String[] shareUsers = new String[lstShare.size()];
				lstShare.toArray(shareUsers);

				// 4.延期申请审批人
				String delayUserCd = res.getDelayApproveUser();
				if ( StringUtils.isNotBlank(delayUserCd)) {
					 lstShare.add(delayUserCd);
				}
				
				// 收集显示待办事项的人员
				// 1.当前审批人 + 3.共享人
				Object[] taskUserCds = ArrayUtils.addAll(approveUserCds, shareUsers);
				
				// 2.当前审批人对应的二级审批节点
				List<String> preUserCdList = new ArrayList<String>();

				if( StringUtils.isNotBlank(approveUserCd2)){
					String[] preUserCds = approveUserCd2.split(";");
					if(preUserCds != null && preUserCds.length > 0){
						for (Object t : preUserCds) {
							if (t != null && !ArrayUtils.contains(taskUserCds, t)) {
								taskUserCds = ArrayUtils.add(taskUserCds, t);
								preUserCdList.add(String.valueOf(t));
							}
						}
					}
				}
				
				// 4.延期申请审批人
				if (StringUtils.isNotBlank(delayUserCd) && !ArrayUtils.contains(taskUserCds, delayUserCd)) {
					taskUserCds = ArrayUtils.add(taskUserCds, delayUserCd);
				}
				
				// 若新待办任务
				if (jbpmTask == null) {
					
					jbpmTask = new JbpmTask();
					jbpmTask.setModuleCd("resApprove");
					jbpmTask.setModuleName("网上审批");
					StringBuffer sbRemark = new StringBuffer();
					if (StringUtils.isNotEmpty(res.getLandProject())) {
						jbpmTask.setExecutionId(res.getLandProject());// 中心/项目
					}
					if (StringUtils.isNotEmpty(res.getTitleName())) {
						sbRemark.append(res.getTitleName());// 标题
					}
					jbpmTask.setRemark(sbRemark.toString());
					
					//网批查询号
					jbpmTask.setJbpmCd(String.valueOf(res.getDisplayNo()));
					
					//待办任务对应网批的的发起人(对应的uiid,姓名)
					jbpmTask.setUserCd(res.getUserCd());
					jbpmTask.setUserName(res.getUserName());
					
					//网批发起时间
					jbpmTask.setApplyDate(res.getStartDate());
					
					//网批ID
					jbpmTask.setJbpmId(res.getResApproveInfoId());
					
					//表单类型
					jbpmTask.setTaskId(res.getAuthTypeCd());
					
					//表单模块类别代码
					jbpmTask.setDeptCd(res.getModuleTypeCd());
				} else {
					//若存在待提醒人员，则移除存在于原"待办任务明细"，但不存在当前于“待提醒人员列表”中的人员提醒。
					if (taskUserCds.length > 0) {
						JbpmTaskCandidate tmpItem = null;
						for (Iterator<JbpmTaskCandidate> it = jbpmTask.getJbpmTaskCandidates().iterator(); it.hasNext();) {
							tmpItem = it.next();
							//若人员不在新列表中，则移除掉。
							if (!ArrayUtils.contains(taskUserCds, tmpItem.getUserCd())) {
								jbpmTaskCandidateManager.delete(tmpItem);
								it.remove();
							}
						}
					}
				}
				
				//网批状态
				jbpmTask.setStatusCd(res.getStatusCd());
				
				//共享标识
				jbpmTask.setOtherStatusCd(res.getShareStatusCd());
				
				//延期标识
				jbpmTask.setDelayStatusCd(res.getDelayStatusCd());
				
				//保存提醒主表
				jbpmTaskManager.saveJbpmTask(jbpmTask);

				
				//若网批被驳回，则提醒网批发起人。
				if (ResConstants.RES_APPROVE_STATUS_BACK.equals(statusCd)) {
					JbpmTaskCandidate backTaskItem = new JbpmTaskCandidate();
					backTaskItem.setUserCd(res.getUserCd());
					backTaskItem.setUserName(CodeNameUtil.getUserNameByCd(res.getUserCd()));
					backTaskItem.setJbpmTask(jbpmTask);
					if(notNowUsers.contains(backTaskItem)){
						backTaskItem.setStatusCd("2");
					}
					jbpmTaskCandidateManager.saveJbpmTaskCandidate(backTaskItem);
				} 
				//若是流程中,则增加"待办任务明细"
				else {
					// 会签会删除所有人
					// jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
					
					String tmpTaskUserCd = null;
					for (Object obj : taskUserCds) {
						tmpTaskUserCd = (String) obj;
						//判断原来是否有任务明细,true-是 false-否（默认）
						boolean isExist = false;
						for (JbpmTaskCandidate candidate : jbpmTask.getJbpmTaskCandidates()) {
							if (StringUtils.equals(candidate.getUserCd(), tmpTaskUserCd)) {
								isExist = true;
								//如果不是当前审批人，设置statusCd为2
								if(notNowUsers.contains(tmpTaskUserCd)){
									candidate.setStatusCd("2");
									jbpmTaskCandidateManager.saveJbpmTaskCandidate(candidate);
								}
								break;
							}
						}
						//若不存在，则创建
						if (!isExist) {
							//初始化"待办任务明细"
							JbpmTaskCandidate newTaskCand = new JbpmTaskCandidate();
							//提醒人cd
							newTaskCand.setUserCd(tmpTaskUserCd);
							//提醒人姓名
							newTaskCand.setUserName(CodeNameUtil.getUserNameByCd(tmpTaskUserCd));
							
							//是否共享
							if (lstShare.contains(tmpTaskUserCd)) {
								newTaskCand.setStatusCd("1");//1-是 0-否
							}
							
							//是否二级节点
							if(preUserCdList.contains(tmpTaskUserCd)){
								newTaskCand.setExtFlg1("1");//1-是 0-否
							}
							
							//如果不是当前审批人，设置statusCd为2
							if(notNowUsers.contains(tmpTaskUserCd)){
								newTaskCand.setStatusCd("2");
							}
							
							//关联“待办任务主表"
							newTaskCand.setJbpmTask(jbpmTask);
							
							//特殊处理延期申请
							if (StringUtils.isNotBlank(delayUserCd) && delayUserCd.equals(tmpTaskUserCd)) {
								newTaskCand.setRemark("[延期申请]");
							}
							//保存”待办任务明细"
							jbpmTaskCandidateManager.saveJbpmTaskCandidate(newTaskCand);
						}
					}
				}
			} 
			//若不存在下一个审批人（即流程走完),则删除待办任务。
			//以下代码，不会执行到。
			else {
				if (jbpmTask != null) {
					jbpmTaskManager.delete(jbpmTask);
				}
			}
		} 
		//若流程走完,则删除待办任务。
		else {
			if (jbpmTask != null) {
				jbpmTaskManager.delete(jbpmTask);
			}
		}
		refreshUser(res);
	}

	public void saveResApproveInfo(ResApproveInfo resApproveInfo, String entityTmpId, ResApproveContent approveContent) {
		PowerUtils.setEmptyStr2Null(resApproveInfo);
		// genApproveCd(resApproveInfo);
		saveResApproveInfo(resApproveInfo, approveContent);
		// resApproveContentManager.saveResApproveContent(approveContent);
		if (StringUtils.isNotEmpty(entityTmpId)) {
			appAttachFileManager.updateTmpFile(entityTmpId, ResApproveInfo.class.getSimpleName(),
					resApproveInfo.getResApproveInfoId());
		}
	}

	public void copyEntity(ResApproveInfo resApproveInfo, ResApproveInfo resApproveInfo2,
			List<ResApproveHis> approveHises) throws Exception {
		List<AppAttachFile> attachFiles = appAttachFileManager.getAttaFileByBizEntityId(resApproveInfo
				.getResApproveInfoId());
		saveProcess(resApproveInfo, approveHises, null, false);
		saveResApproveInfo(resApproveInfo2, resApproveInfo2.getResApproveContents().get(0));
		sendEmail(resApproveInfo2, "【网上审批新增】" + getEmailTitle(resApproveInfo2), "已经新增,谢谢！",
				new String[] { resApproveInfo2.getUserCd() });

		for (AppAttachFile appAttachFile : attachFiles) {
			AppAttachFile attachFile = new AppAttachFile();
			attachFile.setFileName(appAttachFile.getFileName());
			attachFile.setRealFileName(appAttachFile.getRealFileName());
			attachFile.setBizEntityId(appAttachFile.getBizEntityId());
			attachFile.setBizModuleCd(appAttachFile.getBizModuleCd());
			attachFile.setFilePath(appAttachFile.getFilePath());
			attachFile.setFileSize(appAttachFile.getFileSize());
			attachFile.setFileTypeName(appAttachFile.getFileTypeName());
			attachFile.setStatusCd(appAttachFile.getStatusCd());
			attachFile.setBizEntityId(resApproveInfo2.getResApproveInfoId());
			appAttachFileManager.saveAppAttachFile(attachFile);
		}
	}

	private void saveResApproveInfoWithNode(ResApproveInfo resApproveInfo, String entityTmpId,
			List<ResApproveNode> approveNodesNew, List<ResApproveHis> approveHises, ResApproveContent approveContent) {
		PowerUtils.setEmptyStr2Null(resApproveInfo);
		// genApproveCd(resApproveInfo);
		List<ResApproveNode> approveNodesDel = resApproveInfo.getResApproveNodes();
		saveResApproveInfo(resApproveInfo, entityTmpId, approveContent);
		resApproveShareManager.delete(resApproveInfo.getResApproveShares());
		resApproveNodeManager.delete(approveNodesDel);
		resApproveNodeManager.saveResApproveNodes(approveNodesNew);
		if (approveHises != null) {
			for (ResApproveHis his : approveHises) {
				resApproveHisManager.saveResApproveHis(his);
			}
		}
		for (ResCenterConfirmInfo confirmInfo : resApproveInfo.getResCenterConfirmInfos()) {
			resCenterConfirmInfoManager.saveResCenterConfirmInfo(confirmInfo);
		}
	}

	/**
	 * 提交
	 */
	public void saveResApproveInfoBySubmit(ResApproveInfo resApproveInfo, List<ResApproveNode> approveNodesNew,
			List<ResApproveHis> approveHises, BaseTemplate templateBean) throws Exception {
		doListener(resApproveInfo, templateBean);
		saveResApproveInfoWithNode(resApproveInfo, null, approveNodesNew, approveHises, null);
		getDao().getSession().flush();
		if (templateBean != null) {
			if (templateBean instanceof IInProcess) {
				try {
					((IInProcess) templateBean).doInProcess();
					getDao().getSession().flush();
				} catch (Exception e) {
					doImportError(resApproveInfo.getResApproveInfoId(), "doInProcess", e);
				}
			}
		}
	}

	/**
	 * 是否是被驳回的记录
	 * 
	 * @param resApproveInfo
	 * @return
	 */
	public boolean isBack(ResApproveInfo resApproveInfo) {
		boolean flag = false;
		if (resApproveInfo.getResApproveHises().size() > 0) {
			ResApproveHis approveHis = resApproveInfo.getResApproveHises().get(0);
			if (StringUtils.equals(approveHis.getApproveOptionCd(), ResConstants.SP_OPTION_BACK)) {
				flag = true;
			}
		}
		return flag;
	}

	private void searchShare(List<ResApproveShare> approveShares, String uiid, List<ResApproveShare> lstDelete) {
		for (ResApproveShare share : approveShares) {
			if (share.getCreator().equals(uiid)) {
				if (!lstDelete.contains(share)) {
					lstDelete.add(share);
					searchShare(approveShares, share.getUserCd(), lstDelete);
				}
			}
		}
	}

	// 去除当前正在处理的延迟记录
	private void removeDelay(ResApproveInfo resApproveInfo) {
		for (Iterator<ResApproveDelay> it = resApproveInfo.getResApproveDelaies().iterator(); it.hasNext();) {
			ResApproveDelay approveDelay = it.next();
			if (approveDelay.getApproveDate() == null && approveDelay.getApproveUserCd() != null) {
				it.remove();
				resApproveDelayManager.delete(approveDelay);
				resApproveInfo.setDelayApproveUser(null);
			}
		}
	}

	/**
	 * 状态改变事件处理
	 * 
	 * @param resApproveInfo
	 * @param templateBean
	 */
	@SuppressWarnings("rawtypes")
	private void doListener(ResApproveInfo resApproveInfo, BaseTemplate templateBean) {

		if (templateBean instanceof IStatusModifyListener) {
			String oldStatusCd = null;
			String hql = "select status_Cd from Res_Approve_Info r where r.res_Approve_Info_Id =:id ";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", resApproveInfo.getResApproveInfoId());
			List list = findBySql(hql, map);
			try {
				if (null != list && 0 != list.size()) {
					oldStatusCd = (String) list.get(0);
				}
			} catch (Exception e) {
			}
			if (!StringUtils.equals(oldStatusCd, resApproveInfo.getStatusCd())) {
				try {
					((IStatusModifyListener) templateBean).statusModified(oldStatusCd, resApproveInfo.getStatusCd());
				} catch (Exception e) {
					doImportError(resApproveInfo.getResApproveInfoId(), "statusModified", e);
				}
			}
		}
	}

	public void saveProcess(ResApproveInfo resApproveInfo, List<ResApproveHis> approveHises, BaseTemplate templateBean,
			boolean isPass) throws Exception {
		resApproveInfo.setShareStatusCd(null);// 去除共享标识
		resApproveInfo.setDelayStatusCd("0");// 去延期标识
		if (isPass) {
			resApproveInfo.setLastApproveDate(new Date());// 最近审批时间
			// 去除所有被共享人
			resApproveShareManager.delete(resApproveInfo.getResApproveShares());
			resApproveInfo.getResApproveShares().clear();
		} else {
			// 去除当前用户的共享人
			List<ResApproveShare> lstDelete = new ArrayList<ResApproveShare>();
			searchShare(resApproveInfo.getResApproveShares(), SpringSecurityUtils.getCurrentUiid(), lstDelete);
			resApproveShareManager.delete(lstDelete, resApproveInfo);
		}
		if (approveHises != null) {
			for (ResApproveHis his : approveHises) {
				resApproveHisManager.saveResApproveHis(his);
			}
		}
		// 去除当前正在处理的延迟记录
		removeDelay(resApproveInfo);
		doListener(resApproveInfo, templateBean);
		saveResApproveInfo(resApproveInfo, null);
		getDao().getSession().flush();
		ResApproveHis approveHis_last = null;
		if (resApproveInfo.getResApproveHises().size() > 0) {
			approveHis_last = resApproveInfo.getResApproveHises().get(0);
		}
		List<String> lstUserCds = new ArrayList<String>();
		lstUserCds.add(resApproveInfo.getUserCd());
		//TODO
		//增加发送给二级审批人
		
		// 发送邮件,发给级别小于最后一个审批人的所有人员
		if (approveHis_last != null && approveHis_last.getApproveLevel() != null) {
			for (int i = 0; i < resApproveInfo.getResApproveNodes().size() - 1; i++) {
				ResApproveNode approveNode = resApproveInfo.getResApproveNodes().get(i);
				if (approveNode.getApproveLevel() < approveHis_last.getApproveLevel()) {
					if (!lstUserCds.contains(approveNode.getUserCd())) {
						lstUserCds.add(approveNode.getUserCd());
					}
				}
			}
		}
		String[] toUserCds = new String[lstUserCds.size()];
		lstUserCds.toArray(toUserCds);
		String emailTitle = getEmailTitle(resApproveInfo);
		if (resApproveInfo.getStatusCd() != null
				&& resApproveInfo.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_FINISHL)) {
			// 只给发起人发送完成邮件,2011-5-26,因许总网批中太多完成邮件。原值toUserCds
			sendEmail(resApproveInfo, "【网上审批完成】" + emailTitle, "该记录已经审批完成,谢谢！",
					new String[] { resApproveInfo.getUserCd() });

		} else if (isBack(resApproveInfo)) {
			StringBuffer sbMsg = new StringBuffer("该记录已经被");
			sbMsg.append(SpringSecurityUtils.getCurrentUserName());
			sbMsg.append("驳回");
			if (StringUtils.isNotEmpty(resApproveInfo.getApproveUserCd())) {
				sbMsg.append("给").append(CodeNameUtil.getUserNamesByUiids(resApproveInfo.getApproveUserCd(), ";"));
			} else {
				sbMsg.append(";");
			}
			sbMsg.append("请关注！");
			sendEmail(resApproveInfo, "【网上审批驳回】" + emailTitle, sbMsg.toString(), toUserCds);
		} else if (resApproveInfo.getNodeCd() != null && resApproveInfo.getNodeCd().equals(ResConstants.SP_NODE_CD_ZC)) {
			// 总裁步骤
			String conFollower = null;
			for (ResApproveNode approveNode : resApproveInfo.getResApproveNodes()) {
				if (approveNode.getNodeCd().equals(ResConstants.SP_NODE_CD_GZR)) {
					conFollower = approveNode.getUserCd();
					break;
				}
			}
			if (conFollower != null) {
				sendEmail(resApproveInfo, "【网上审批到总裁】" + emailTitle, "请到准备上传总裁签字扫描件！", new String[] { conFollower });
			}
		}
		if (templateBean != null) {

			if (ResConstants.RES_APPROVE_STATUS_FINISHL.equals(resApproveInfo.getStatusCd())) {
				// 审批完成后操作
				if (resApproveInfo.getAuthTypeCd().equals(ResConstants.SP_TYPE_QTSW_GZZL)) {
					// 工作指令单,审批人，审批完成后，自动发邮件给所有接单人
					if (approveHis_last != null && approveHis_last.getNodeCd().equals(ResConstants.SP_NODE_CD_SPR)) {
						WorkOrder workOrder = (WorkOrder) templateBean;
						String[] userCds = workOrder.getReceiveUserCds().split(";");
						StringBuffer msg = new StringBuffer("要求完成时间：").append(workOrder.getOrderEndDate()).append(
								"<br/>");
						msg.append("工作指令：").append("<br/>").append(workOrder.getOrderContent());
						sendEmail(resApproveInfo, "【工作指令单】" + emailTitle, msg.toString(), userCds);
					}
				}
				// getDao().getSession().flush();
				if (templateBean instanceof IAutoImport) {
					// 审批完成后自动导入合同台账
					IAutoImport iAutoImport = (IAutoImport) templateBean;
					if (iAutoImport.isAutoImport()) {
						try {
							iAutoImport.doImport();
							getDao().getSession().flush();
						} catch (Exception e) {
							doImportError(resApproveInfo.getResApproveInfoId(), "doImport", e);
						}
					}

				}

				if ("true".equals(templateBean.getIsAuto())) {
					try {
						templateBean.doAutoImport();
						getDao().getSession().flush();
					} catch (Exception e) {
						doImportError(resApproveInfo.getResApproveInfoId(), "doAutoImport", e);
					}
				}

				if (templateBean instanceof NetTempAuthorizeApproveSheet) {
					// 网批临时授权审批表，审批完成后，自动发邮件给被授权人
					NetTempAuthorizeApproveSheet approveSheet = (NetTempAuthorizeApproveSheet) templateBean;
					String toUserCd = approveSheet.getAuthorizedUserCd();
					StringBuffer msg = new StringBuffer(approveSheet.getAuthorizeUserName()).append("将网批临时授权给您")
							.append("<br/>");
					msg.append("授权日期：从").append(approveSheet.getBeginDate()).append("到")
							.append(approveSheet.getEndDate()).append("<br/>");
					msg.append("授权原因：").append(approveSheet.getAuthorizeReason()).append("<br/>");
					msg.append("请悉知");
					sendEmail(resApproveInfo, "【网批临时授权】" + emailTitle, msg.toString(), new String[] { toUserCd });
				}
				// 自动推送
				autoPush(resApproveInfo);
			}
		}
		// --end发送邮件
	}

	/**
	 * 将xml的数据存入数据表
	 * 
	 * @param templetCd
	 *            new Object[]{"154","104"}
	 * @throws Exception
	 */
	public void saveXml2Table(String... templetCd) throws Exception {
		// 104-银行付款审批单 154-资金申请表
		StringBuffer hqlTemplet = new StringBuffer(
				"from ResBillTemplet r where r.activeBl = :activeBl and r.templetCd in (:templetCd) ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activeBl", true);
		map.put("templetCd", templetCd);
		List<ResBillTemplet> billTemplets = resBillTempletManager.find(hqlTemplet.toString(), map);
		for (ResBillTemplet templet : billTemplets) {
			String hql = "select c from ResApproveContent c where c.resApproveInfo.resApproveInfoId in (select resApproveInfoId from ResApproveInfo r where r.templetCd=? and r.statusCd = '2' ) ";
			List<ResApproveContent> list = resApproveContentManager.find(hql, templet.getTempletCd());
			for (ResApproveContent content : list) {
				BaseTemplate baseTemplate = ResXmlParser.parserXml(content, templet.getEntityName());
				saveXml2Table(baseTemplate);
				resApproveContentManager.saveResApproveContent(content);
			}

		}
	}

	private void saveXml2Table(BaseTemplate templateBean) {
		resApproveInfoDao.getSession().save(templateBean);
	}

	private void doImportError(String resApproveInfoId, String methodName, Exception e) {
		log.error("自动导入失败", e);
		String errorLog = e.getMessage();
		if (e.getCause() != null && e.getCause().getMessage() != null) {
			errorLog = e.getCause().getMessage();
		}
		resImportLogManager.insertResImportLog(resApproveInfoId, methodName, "0", errorLog);
	}

	/**
	 * 审批完成后，自动推送
	 */
	private void autoPush(ResApproveInfo resApproveInfo) throws Exception {
		List<ResTypeUserRel> typeUserRels = resTypeUserRelManager.loadPushUser(resApproveInfo.getAuthTypeCd());
		List<String> lstUserCd = PowerUtils.getProptyArray(typeUserRels, "userCd");
		String[] userCds = new String[lstUserCd.size()];
		lstUserCd.toArray(userCds);
		push(resApproveInfo, userCds, "系统");
	}

	/**
	 * 推送
	 */
	public void push(ResApproveInfo resApproveInfo, String[] userCds, String pushUserName) throws Exception {
		List<ResApprovePush> pushs = new ArrayList<ResApprovePush>();
		Set<String> lstAdd = new HashSet<String>();
		loop: for (String cdTmp : userCds) {
			if (StringUtils.isNotEmpty(cdTmp)) {
				ResApprovePush approvePush = new ResApprovePush();
				approvePush.setUserCd(cdTmp);
				approvePush.setUserName(CodeNameUtil.getUserNameByCd(cdTmp));
				approvePush.setResApproveInfo(resApproveInfo);
				pushs.add(approvePush);
				for (ResApprovePush push : resApproveInfo.getResApprovePushs()) {
					if (push.getUserCd().equals(cdTmp)) {
						continue loop;
					}
				}
				lstAdd.add(cdTmp);
			}
		}

		resApprovePushManager.refeshPushs(resApproveInfo.getResApprovePushs(), pushs);
		resApproveInfo.getResApprovePushs().clear();
		resApproveInfo.getResApprovePushs().addAll(pushs);

		String[] emailUserCds = new String[lstAdd.size()];
		lstAdd.toArray(emailUserCds);
		sendEmail(resApproveInfo, "【网上审批推送】" + getEmailTitle(resApproveInfo), pushUserName + "已经推送给您，请查看", emailUserCds);

	}

	public void sendEmail(ResApproveInfo resApproveInfo, String title, String content, String[] userCd) {
		String approveNo = resApproveInfo.getApproveCd() + resApproveInfo.getSerialNo();

		StringBuffer msg = new StringBuffer(content).append("<br/>");
		msg.append("查询号：").append(resApproveInfo.getDisplayNo()).append("<br/>");
		msg.append("编　号：").append(approveNo).append("<br/>");
		msg.append("发起人：").append(CodeNameUtil.getUserNameByCd(resApproveInfo.getUserCd())).append("<br/>");
		msg.append("类　别：").append(CodeNameUtil.getResAuthTypeNameByCd(resApproveInfo.getAuthTypeCd())).append("<br/>");
		msg.append("标　题：").append(StringUtils.trimToEmpty(resApproveInfo.getTitleName())).append("<br/>");
		// StringBuffer msg = new
		// StringBuffer("审批(编号:").append(approveNo).append(")").append(content);
		StringBuffer msg_pc = new StringBuffer();
		String ctx = "";
		if (ActionContext.getContext() != null && Struts2Utils.getRequest() != null) {
			ctx = Struts2Utils.getRequest().getContextPath();
		}
		msg_pc.append(
				"<div style=\"width: 30px;cursor: pointer;text-decoration:underline;color:blue;\" onclick=\"parent.parent.showAll('")
				.append(ctx).append("/res/res-approve-info.action?id=").append(resApproveInfo.getResApproveInfoId())
				.append("&resAuthTypeCd=").append(resApproveInfo.getAuthTypeCd()).append("','resApprove');\">进入</div>");
		// oaEmailBodyManager.sendData2Email(title, msg.toString() + msg_pc.toString(), "email_admin", "1", userCd);
		// if (userCd.length == 1 && userCd[0] != null && (userCd[0].equals("huangjian") || userCd[0].equals("wubc"))) {
		try {
			StringBuffer msg_email = new StringBuffer();
			String localAddr = ServletActionContext.getRequest().getLocalAddr();
			msg_email
					.append("<a style=\"width: 30px;cursor: pointer;text-decoration:underline;color:blue;\" href=\"http://")
					.append(localAddr).append(ctx).append("/res/res-approve-info.action?id=")
					.append(resApproveInfo.getResApproveInfoId()).append("&resAuthTypeCd=")
					.append(resApproveInfo.getAuthTypeCd()).append("\">进入</a>");
			EmailUtil.sendAsyncSys(title, msg.toString() + msg_email.toString(), userCd);
		} catch (Exception e) {
			log.error("to users:" + userCd);
			log.error("发送邮件异常", e);
		}
		// }
	}

	public void saveResApproveInfo(ResApproveInfo resApproveInfo, ResApproveContent approveContent) {
		PowerUtils.setEmptyStr2Null(resApproveInfo);
		genApproveCd(resApproveInfo);
		// genDisplayNo(resApproveInfo);
		resApproveInfoDao.save(resApproveInfo);
		if (approveContent != null) {
			resApproveContentManager.saveResApproveContentLock(approveContent);
		}
		update2JbpmTask(resApproveInfo);
	}

	/**
	 * 将主表中的审批人拆分插入子表,节点完成后，将对应记录删除。
	 * 包含：当前审批人，被共享人
	 * 
	 * @param resApproveInfo
	 */
	private void refreshUser(ResApproveInfo resApproveInfo) {
		getDao().getSession().flush();
		List<ResApproveUser> approveUsers = new ArrayList<ResApproveUser>();
		// 审批人
		// Long timeLimit = getCurTimeLimit(resApproveInfo);
		// 记录原审批人和新审批人，用于推送手机
		final Set<String> userSet = new HashSet<String>();
		// 原审批人
		for (ResApproveUser approveUser : resApproveInfo.getResApproveUsers()) {
			userSet.add(approveUser.getUserCd());
		}
		if (resApproveInfo.getApproveUserCd() != null) {
			String[] userCds = resApproveInfo.getApproveUserCd().split(";");
			for (String userCd : userCds) {
				ResApproveUser approveUser = new ResApproveUser();
				approveUser.setUserCd(userCd);
				approveUser.setUserTypeCd("0");// 0-审批人，1-共享人，2-延期审批人,3-二级审批人
				approveUser.setTimeLimit(resApproveInfo.getTimeLimit());// 审批时限
				approveUser.setResApproveInfo(resApproveInfo);
				approveUsers.add(approveUser);
				userSet.add(userCd);
			}
		}
		
		// 二级审批人
		if (StringUtils.isNotEmpty(resApproveInfo.getApproveUserCd2())) {
			String[] userCds = resApproveInfo.getApproveUserCd2().split(";");
			for (String userCd : userCds) {
				ResApproveUser approveUser = new ResApproveUser();
				approveUser.setUserCd(userCd);
				approveUser.setUserTypeCd("3");// 0-审批人，1-共享人，2-延期审批人,3-二级审批人
				approveUser.setTimeLimit(resApproveInfo.getTimeLimit());// 审批时限
				approveUser.setResApproveInfo(resApproveInfo);
				approveUsers.add(approveUser);
				userSet.add(userCd);
			}
		}
		
		// 共享人
		for (ResApproveShare approveShare : resApproveInfo.getResApproveShares()) {
			ResApproveUser approveUser = new ResApproveUser();
			approveUser.setUserCd(approveShare.getUserCd());
			approveUser.setUserTypeCd("1");// 0-审批人，1-共享人，2-延期审批人，3-二级审批人
			approveUser.setResApproveInfo(resApproveInfo);
			approveUsers.add(approveUser);
			userSet.add(approveShare.getUserCd());
		}
		// 延期审批人
		if (resApproveInfo.getDelayApproveUser() != null) {
			ResApproveUser approveUser = new ResApproveUser();
			approveUser.setUserCd(resApproveInfo.getDelayApproveUser());
			approveUser.setUserTypeCd("2");// 0-审批人，1-共享人，2-延期审批人，3-二级审批人
			approveUser.setResApproveInfo(resApproveInfo);
			approveUsers.add(approveUser);
			userSet.add(resApproveInfo.getDelayApproveUser());
		}
		
		
		out: for (Iterator<ResApproveUser> it=resApproveInfo.getResApproveUsers().iterator();it.hasNext(); ) {
			// 原审批人中，是否存在当前审批人，如果不存在就删除
			ResApproveUser approveUser =it.next();
			for (Iterator<ResApproveUser> iterator = approveUsers.iterator(); iterator.hasNext();) {
				ResApproveUser userAdd = iterator.next();
			System.out.println("............"+userAdd.getUserCd());
				if (userAdd.getUserCd().equals(approveUser.getUserCd())
						&& userAdd.getUserTypeCd().equals(approveUser.getUserTypeCd())) {
					iterator.remove();
					continue out;
				}
			}
			// 删除不存在的人员
			resApproveUserManager.delete(approveUser);
			it.remove();
		}
		resApproveUserManager.saveResApproveUsers(approveUsers);

		push2Wp7(userSet);
	}

	private void push2Wp7(Set<String> uiids) {
		if (uiids.size() == 0)
			return;
		StringBuffer sqlPath = new StringBuffer(
				"select uri_path,uiid,cnt from RES_NOTIFICATION_URI u where u.uiid in (:uiids) ");
		StringBuffer sqlTask = new StringBuffer("select count(*) from Jbpm_task t where t.module_Cd='resApprove' ");
		sqlTask.append("and exists (select 1 from Jbpm_Task_Candidate c where t.jbpm_task_id=c.jbpm_task_id and c.user_Cd=:uiid )");
		Map<String, Object> valuesPath = new HashMap<String, Object>();
		Map<String, Object> valuesTask = new HashMap<String, Object>();
		valuesPath.put("uiids", uiids);
		List list = findBySql(sqlPath.toString(), valuesPath);
		final HttpRequester requester = new HttpRequester();
		final Map<String, Integer> mapCnt = new HashMap<String, Integer>();
		final Map<String, Integer> mapCnt_o = new HashMap<String, Integer>();
		final Map<String, String> mapUrl = new HashMap<String, String>();
		for (Object object : list) {
			Object[] objs = (Object[]) object;
			String uriPath = (String) objs[0];
			String uiid = (String) objs[1];
			BigDecimal b_cnt = (BigDecimal) objs[2];
			int cnt_o = b_cnt == null ? 0 : b_cnt.intValue();
			valuesTask.put("uiid", uiid);
			long cnt = countSqlResult(sqlTask.toString(), valuesTask);
			mapCnt_o.put(uiid, cnt_o);
			mapCnt.put(uriPath, (int) cnt);
			mapUrl.put(uriPath, uiid);
			updateCnt(uiid, (int) cnt);
		}
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (String uriPath : mapCnt.keySet()) {
						int cnt = mapCnt.get(uriPath);
						requester.sendTile(uriPath, "网上审批", "您有未审核的网批", cnt);
						String uiidTmp = mapUrl.get(uriPath);
						int cnt_o = mapCnt_o.get(uiidTmp);
						if (cnt_o < cnt) {
							requester.sendToast(uriPath, "网上审批", "您有新网批");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	/**
	 * 更新指定人员的网批次数
	 * 
	 * @param uiid
	 * @param cnt
	 */
	public void updateCnt(String uiid, int cnt) {
		StringBuffer sqlDelete = new StringBuffer("update RES_NOTIFICATION_URI set cnt=:cnt where uiid=:uiid ");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("uiid", uiid);
		values.put("cnt", cnt);
		getDao().batchExecuteSql(sqlDelete.toString(), values);
	}

	public void deleteResApproveInfo(String id) {
		ResApproveInfo resApproveInfo = resApproveInfoDao.get(id);
		if (resApproveInfo.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_NEW)) {
			resApproveMessageManager.delete(resApproveInfo.getResApproveMessages());
			resApproveNodeManager.delete(resApproveInfo.getResApproveNodes());
			resApproveShareManager.delete(resApproveInfo.getResApproveShares());
			resCenterConfirmInfoManager.delete(resApproveInfo.getResCenterConfirmInfos());
			resApproveHisManager.delete(resApproveInfo.getResApproveHises());
			resApproveContentManager.delete(resApproveInfo.getResApproveContents());
			resApproveDelayManager.delete(resApproveInfo.getResApproveDelaies());
			resApproveShareHisManager.delete(resApproveInfo.getResApproveShareHises());
			resApproveInfoDao.delete(resApproveInfo);
			jbpmTaskManager.deleteByEntityId(id);
			appAttachFileManager.deleteByBizEntityId(id, false);
		} else {
			resApproveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_DELETED);
			resApproveInfoDao.save(resApproveInfo);
		}
	}

	/**
	 * 锁定
	 * 
	 * @param resApproveInfo
	 */
	public void lock(ResApproveInfo resApproveInfo) {
		Date now = new Date();
		resApproveInfo.setLockDate(now);
		resApproveInfo.setLockUser(SpringSecurityUtils.getCurrentUiid());
		ResApproveLock approveLock = new ResApproveLock();
		approveLock.setLockDate(now);
		approveLock.setLockUser(SpringSecurityUtils.getCurrentUiid());
		approveLock.setResApproveInfo(resApproveInfo);
		resApproveLockManager.saveResApproveLock(approveLock);
		saveResApproveInfo(resApproveInfo, null);
	}

	/**
	 * 解锁
	 * 
	 * @param resApproveInfo
	 */
	public void unlock(ResApproveInfo resApproveInfo) {
		Date now = new Date();
		Date lockDate = resApproveInfo.getLockDate();
		String lockUser = resApproveInfo.getLockUser();
		resApproveInfo.setLockDate(null);
		resApproveInfo.setLockUser(null);
		if (StringUtils.isNotEmpty(lockUser) && lockUser != null) {
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQS_lockUser", lockUser));
			filters.add(new PropertyFilter("EQD_lockDate", lockDate));
			filters.add(new PropertyFilter("EQS_resApproveInfo.resApproveInfoId", resApproveInfo.getResApproveInfoId()));
			List<ResApproveLock> approveLocks = resApproveLockManager.find(filters);
			if (approveLocks.size() == 1) {
				ResApproveLock approveLock = approveLocks.get(0);
				approveLock.setUnlockDate(now);
				approveLock.setUnlockUser(SpringSecurityUtils.getCurrentUiid());
				resApproveLockManager.saveResApproveLock(approveLock);
			} else
				throw new RuntimeException("解锁失败：没找到唯一的锁定个记录");
		}
		saveResApproveInfo(resApproveInfo, null);
	}

	/**
	 * 生成编号
	 * 
	 * @param resApproveInfo
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void genApproveCd(ResApproveInfo resApproveInfo) {
		if (StringUtils.isEmpty(resApproveInfo.getApproveCd())) {
			String authTypeCd = resApproveInfo.getAuthTypeCd();
			// String templetCd = resApproveInfo.getTempletCd();
			String hql = "select max(serial_No) from res_approve_info where auth_Type_Cd=:authTypeCd ";
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("authTypeCd", authTypeCd);
			List lstResult = findBySql(hql, map);
			long sn = 0;
			try {
				if (null != lstResult && 0 != lstResult.size()) {
					sn = ((BigDecimal) lstResult.get(0)).longValue();
				}
			} catch (Exception e) {
			}
			sn++;
			String rtn = "SP_" + authTypeCd + "_";
			resApproveInfo.setSerialNo(sn);
			resApproveInfo.setApproveCd(rtn);
		}
	}

	/**
	 * 是否是我指定的节点
	 * 
	 * @return
	 */
	private boolean isMyNode(String curNodeCd, Long curApproveLevel,String curNodeCd2, Long curApproveLevel2, ResApproveNode approveNode) {
		boolean flag = false;
	    if(StringUtils.isNotEmpty(curNodeCd2)&&StringUtils.isNotEmpty(curApproveLevel2+"")){
		   if(StringUtils.equals(approveNode.getUserCd(), SpringSecurityUtils.getCurrentUiid())) {
					flag = true;
		   }
	    }else{
		   if (curApproveLevel == null || curApproveLevel.equals(approveNode.getApproveLevel())) {
				if (curNodeCd.equals(approveNode.getGroupNodeCd())) {
					if (StringUtils.equals(approveNode.getUserCd(), SpringSecurityUtils.getCurrentUiid())) {
						flag = true;
					}
				} else if (approveNode.getNodeCd().equals(curNodeCd)) {
					flag = true;
				} 
	   }
		 
		
		
		}
//		//越过二级节点
//		if (curApproveLevel == null || (curApproveLevel<approveNode.getApproveLevel()
//				&&approveNode.getApproveRank()==DictContants.RES_APPROVE_RANK_TWO)) {
//			   flag =true;
//		}
		
		return flag;
	}

	public ResApproveHis addApproveHis(Date fromDate, ResApproveNode approveNode) {
		ResApproveHis approveHis = new ResApproveHis();
		approveHis.setFromDate(fromDate);
		approveHis.setApproveDate(approveNode.getApproveDate());
		approveHis.setApproveLevel(approveNode.getApproveLevel());
		approveHis.setApproveOptionCd(approveNode.getApproveOptionCd());
		approveHis.setGroupNodeCd(approveNode.getGroupNodeCd());
		approveHis.setNodeCd(approveNode.getNodeCd());
		approveHis.setUserCd(approveNode.getUserCd());
		approveHis.setUserName(approveNode.getUserName());
		approveHis.setPositionCd(approveNode.getPositionCd());
		approveHis.setTimeLimit(approveNode.getTimeLimit());// 审批时限
		approveHis.setVerifyCd(approveNode.getVerifyCd());// 审批、审核标识
		approveHis.setBackFlg(approveNode.getBackFlg());
		approveHis.setResApproveInfo(approveNode.getResApproveInfo());
		approveHis.setRemark(approveNode.getRemark());
		approveHis.setCreatedDeptCd(SpringSecurityUtils.getCurrentCenterCd());
		approveHis.setUpdator(approveNode.getUpdator());
		approveHis.setSequenceNo(approveNode.getSequenceNo());
		approveHis.setApproveRank(approveNode.getApproveRank());//写入approveRank
		
		return approveHis;
	}

	private ResApproveNode getNextNode(ResApproveInfo approveInfo, ResApproveNode approveNode) {
		ResApproveNode nodeNext = null;
         if(approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)){ //当前节点是二级节点时，另行处理
        	 nodeNext = getNextNodeCd2(approveInfo,approveNode);
		  }else{
			  
		   for (Iterator<ResApproveNode> it = approveInfo.getResApproveNodes().iterator(); it.hasNext();) {
			ResApproveNode nodeTmp = it.next();
			if (StringUtils.isNotEmpty(approveNode.getGroupNodeCd())) {
				if (nodeTmp.getApproveLevel() > approveNode.getApproveLevel()) {
					nodeNext = nodeTmp;
					break;
				}
			} else {
				if (nodeTmp.getNodeCd().equals(approveNode.getNodeCd())
						&& nodeTmp.getApproveLevel() == approveNode.getApproveLevel()) {
					if (it.hasNext()) {
						nodeNext = it.next();
					}
					break;
				}
			}
           
		 } 
		}
		return nodeNext;
	}
	/**
	 * 获取未审批的二级节点中最前的节点
	 * @param approveInfo
	 * @param approveNode
	 * @return
	 */
	
	@SuppressWarnings("null")
	private ResApproveNode getNextNodeCd2(ResApproveInfo approveInfo, ResApproveNode approveNode){
		ResApproveNode nodeNext = null;
		ResApproveNode oneNode = null; //此二级节点的一级
		ResApproveNode oneBeforNode = null;//多层结构，在这个二级上的一级 。
	
		for(ResApproveNode tmpNode:approveInfo.getResApproveNodes()){//二级节点后面必有一级节点   约定。
	         if(tmpNode.getApproveLevel()>approveNode.getApproveLevel()&&tmpNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)){
	        	 oneNode = tmpNode;
	        	 break;
	         }
		}
		for(ResApproveNode tmpNode:approveInfo.getResApproveNodes()){
			if(tmpNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)&&tmpNode.getApproveLevel()<approveNode.getApproveLevel()){
				oneBeforNode = tmpNode;//一直循环找到最近的
			}
		}
		if(null != oneBeforNode){
			for(ResApproveNode tmpNode:approveInfo.getResApproveNodes()){
				if(tmpNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)&&tmpNode.getApproveLevel()>oneBeforNode.getApproveLevel()&&
					tmpNode.getApproveLevel()<oneNode.getApproveLevel()&&tmpNode.getApproveOptionCd()=="-1"&&!tmpNode.getNodeCd().equals(approveNode.getNodeCd())	){
					      nodeNext  = tmpNode;
					      break;
					}
			}
		}else{//没有上层一级节点，直接找最小level没审批的二级当下个节点
			for(ResApproveNode tmpNode:approveInfo.getResApproveNodes()){
				if(tmpNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)&&
					tmpNode.getApproveLevel()<oneNode.getApproveLevel()&&tmpNode.getApproveOptionCd().equals("-1")&&!tmpNode.getNodeCd().equals(approveNode.getNodeCd())	){
					      nodeNext  = tmpNode;
					      break;
				   }
			}
			if(null == nodeNext&&oneNode.getApproveOptionCd().equals("-1")){ //一级未审批，如果已审批，则下个节点直接为null，没意义
				nodeNext = oneNode;
			}
		}
		 
		return nodeNext;
	}  
	
	/**
	 * 为合同的word文件页眉增加随机码
	 * 
	 * @param entity2
	 */
	private void markDocWithRandomCd(ResApproveInfo entity2) {
		try {
			// added by huangbijin 2011-04-18
			// 若没有合同文本,则不生成随机码
			AppAttachFile attFile = getFileInfo(entity2);
			if (attFile == null) {
				log.warn("未找到合同文本,无法生成随机码至合同!");
				return;
			}

			String randomNo = getRandomNo();
			entity2.setRandomNo(randomNo);
			// hidden by huangbijin 2011-04-18
			// AppAttachFile attFile = getContractFileInfo(entity2);

			File file = new File(attFile.getFilePath() + "\\" + attFile.getFileName());

			XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
			XWPFParagraph para = doc.createParagraph();
			para.createRun().setText(randomNo);
			XWPFHeaderFooterPolicy hfPolicy = doc.getHeaderFooterPolicy();
			XWPFParagraph[] contents = new XWPFParagraph[] { para };
			hfPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, contents);
			doc.write(new FileOutputStream(file));
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("系统无法找到有效的合同文件。", ex);
		} catch (Exception ex) {
			throw new RuntimeException("生成随机码错误。", ex);
		}
	}

	private void markCompleteWithRandomCd(ResApproveInfo entity2) {
		String randomNo = getRandomNo();
		entity2.setRandomNo(randomNo);
	}

	// 得到本审批的合同文件信息
	private AppAttachFile getContractFileInfo(ResApproveInfo entity2) {
		String id = entity2.getResApproveInfoId();
		List<AppAttachFile> attFiles = attachFileManager.getLastFile(id, "resContract");
		if (attFiles.size() == 0)
			throw new RuntimeException("本审批不含有效的合同文本。");
		AppAttachFile attFile = attFiles.get(0);
		return attFile;
	}

	// add by huangbijin 2010-11-05
	/**
	 * 如果没有附件,则返回空
	 * 
	 * @param entity2
	 * @return
	 */
	private AppAttachFile getFileInfo(ResApproveInfo entity2) {
		String id = entity2.getResApproveInfoId();
		List<AppAttachFile> attFiles = attachFileManager.getLastFile(id, "resContract");
		if (attFiles.size() == 0)
			return null;
		else
			return attFiles.get(0);
	}

	private String getRandomNo() {
		return System.currentTimeMillis() + RandomUtils.generateString(4);
	}
 
	 //判断这个level的节点是否走完。
	private boolean isAllNodeApproved(ResApproveInfo approveInfo, ResApproveNode approveNode) {
		boolean flag = true;
		if (StringUtils.isNotEmpty(approveNode.getGroupNodeCd())) {
			for (ResApproveNode nodeTmp : approveInfo.getResApproveNodes()) {
				if (StringUtils.equals(nodeTmp.getGroupNodeCd(), approveNode.getGroupNodeCd())
						&& nodeTmp.getApproveLevel().equals(approveNode.getApproveLevel())) {
					if (!ResConstants.SP_OPTION_AGREE.equals(nodeTmp.getApproveOptionCd())) {
						flag = false;
						break;
					}
				}
			}
		}
		return flag;
	}

	// 新增节点级别
	public boolean[] updateResApproveNode(ResApproveInfo approveInfo, String option, String tarNodeCd,
			Long toApproveLevel, List<ResApproveHis> approveHises) throws Exception {
		return updateResApproveNode(approveInfo, option, tarNodeCd, toApproveLevel, null, approveHises, false);
	}

	/**
	 * 判断本次操作是否延期
	 * 
	 * @param fromDate
	 * @param approveNode
	 * @return
	 */
	private boolean isDelay(Date fromDate, ResApproveNode approveNode) {
		boolean flag = false;
		if (approveNode.getTimeLimit() != null && approveNode.getTimeLimit() > 0) {
			Date toDate = DateOperator.addHours(fromDate, approveNode.getTimeLimit().intValue());
			Date approveDate = approveNode.getApproveDate();
			if (approveDate.compareTo(toDate) == 1) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 例外事项审批表，在流程最后面增加额外审批人
	 * 
	 * @param approveInfo
	 * @throws Exception
	 */
	public void addExtendUsers(ResApproveInfo approveInfo) throws Exception {
		String addUserCds = Struts2Utils.getParameter("addUserCds");
		String[] userCds = addUserCds.split(";");
		Integer index = approveInfo.getResApproveNodes().size() + 1;
		for (String userCd : userCds) {
			boolean isAdded = addExtendUser(approveInfo, userCd, index);
			if (isAdded) {
				index++;
			}
		}
		saveResApproveInfo(approveInfo, null);
	}

	private boolean addExtendUser(ResApproveInfo approveInfo, String userCd, Integer index) {
		boolean isAdded = false;
		ResApproveNode approveNode = null;
		boolean isExist = false;
		for (Iterator<ResApproveNode> it = approveInfo.getResApproveNodes().iterator(); it.hasNext();) {
			ResApproveNode nodeTmp = it.next();
			if (nodeTmp.getUserCd().equals(userCd)) {
				approveNode = nodeTmp;
				isExist = true;
				break;
			}
		}
		// 如果已经存在该人员，就不添加
		if (!isExist) {
			approveNode = new ResApproveNode();
			String approveUserCd = userCd;
			String approveUserName = CodeNameUtil.getUserNameByCd(approveUserCd);
			approveNode.setNodeCd(approveUserName + ":" + index);
			// 如果没有参照节点，往最后增加节点
			Long approveLevel = Long.valueOf(index);
			approveNode.setApproveLevel(approveLevel);
			approveNode.setResApproveInfo(approveInfo);

			approveNode.setUserCd(approveUserCd);
			approveNode.setUserName(approveUserName);
			approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);
			WsPlasUser t = PlasCache.getUserByUiid(approveUserCd);
			if (t != null) {
				approveNode.setWorkDutyDesc(t.getWorkDutyDesc());
			}
			approveNode.setTimeLimit(24l);// 审批时限
			approveInfo.getResApproveNodes().add(approveNode);
			resApproveNodeManager.saveResApproveNode(approveNode);
			isAdded = true;
		}
		return isAdded;
	}

	private Long getMaxLevel(ResApproveInfo approveInfo) {
		Long level = 1000l;
		int size = approveInfo.getResApproveNodes().size();
		if (size > 0) {
			level = approveInfo.getResApproveNodes().get(size - 1).getApproveLevel();
		}
		return level;
	}

	/**
	 * 删除营运管理中心节点 
	 *  营运管理中心节点是通过“上会”生成的，则取消该节点
	 * 
	 * @param approveInfo
	 * @param nodeOrUser
	 * @return
	 */
	private void deleteNodeIfToMeeting(ResApproveInfo approveInfo, String nodeOrUser){
		ResApproveNode approveNode = null;
		for (Iterator<ResApproveNode> it = approveInfo.getResApproveNodes().iterator(); it.hasNext();) {
			ResApproveNode nodeTmp = it.next();//已经存在并且是通过上会生成的节点,上会是否是这样判断
				if (nodeTmp.getNodeCd().equals(nodeOrUser)&&StringUtils.equals(nodeTmp.getRecoverMark(), "toMeeting")) {
					approveNode = nodeTmp;
					resApproveNodeManager.delete(approveNode);
					it.remove();
					break;
				}
		}
		return ;
	}
	/**
	 * 增加审批节点
	 * 
	 * @param approveInfo
	 * @param nodeOrUser
	 * @param afterNodeCd
	 *            插入位置，该节点之后，如果null，在最后增加
	 * @return 返回目标节点
	 */
	@SuppressWarnings("null")
	private ResApproveNode addNode(ResApproveInfo approveInfo, String nodeOrUser, ResApproveNode afterNode,
			boolean isUser,String recoverMark) {
		ResApproveNode approveNode = null;
		boolean isExist = false;
		for (Iterator<ResApproveNode> it = approveInfo.getResApproveNodes().iterator(); it.hasNext();) {
			ResApproveNode nodeTmp = it.next();
			if (isUser) {
				if (nodeTmp.getUserCd().equals(nodeOrUser)) {
					approveNode = nodeTmp;
					isExist = true;
					break;
				}
			} else {
				if (nodeTmp.getNodeCd().equals(nodeOrUser)) {
					approveNode = nodeTmp;
					isExist = true;
					break;
				}
			}
		}
		// 如果不存在，则添加
		if (!isExist) {
			approveNode = new ResApproveNode();
			String approveUser;
			String approveUserName;
			String nodeCd;
			Long approveLevel;
			approveLevel = getMaxLevel(approveInfo) + 1;
			if (isUser) {
				// 例外事项，在最后面增加节点
				approveUser = nodeOrUser;
				approveUserName = CodeNameUtil.getUserNameByCd(approveUser);
				nodeCd = approveUserName + ":" + (approveInfo.getResApproveNodes().size() + 1);
			} else {
				approveUser = resNodeManager.getNodeUserByCd(nodeOrUser);
				approveUserName = CodeNameUtil.getUserNameByCd(approveUser);
				nodeCd = nodeOrUser;
			}
			approveNode.setNodeCd(nodeCd);
			// 如果有参照节点，在其增加节点
			if (afterNode != null) {
				approveLevel = afterNode.getApproveLevel() + 1;
			}
			approveNode.setApproveLevel(approveLevel);
			approveNode.setResApproveInfo(approveInfo);
			approveNode.setRecoverMark(recoverMark);//判断是否上会，上会追回使用
			approveNode.setUserCd(approveUser);
			approveNode.setUserName(approveUserName);
			approveNode.setApproveRank(DictContants.RES_APPROVE_RANK_ONE);
			approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);
			WsPlasUser t = PlasCache.getUserByUiid(approveUser);
			if (t != null) {
				approveNode.setWorkDutyDesc(t.getWorkDutyDesc());
			}
			// 手动增加的节点，不算时限
			approveNode.setTimeLimit(null);
			approveInfo.getResApproveNodes().add(approveNode);
			resApproveNodeManager.saveResApproveNode(approveNode);
		}else{//如果已经存在，将状态改为未审批，这个是新增节点的标准逻辑（by bo.wu）
			approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);
			resApproveNodeManager.saveResApproveNode(approveNode);
			
		}
		return approveNode;
	}

	// 新增节点级别
	private boolean[] updateResApproveNode(ResApproveInfo approveInfo, String option, String tarNodeCd,
			Long toApproveLevel, String nodeRemark, List<ResApproveHis> approveHises, boolean isAuto) throws Exception {
		
		String curNodeCd = approveInfo.getNodeCd();// 当前审批节点
		Long curApproveLevel = approveInfo.getApproveLevel();// 当前审批节点所处的级别
		String curNodeCd2  = approveInfo.getNodeCd2(); //当前二级节点
		Long curApproveLevel2 =  approveInfo.getApproveLevel2();//当前二级审批节点所处的级别
		String curUserCd = SpringSecurityUtils.getCurrentUiid();
	
		
		// 审批意见
		String approveRemark = nodeRemark;
		if (approveRemark == null) {
			approveRemark = Struts2Utils.getParameter("approveRemark");
		}
	   
		ResApproveNode nodeNext = null;
		ResApproveNode nodeCur = null;
		boolean isPassed = false;
		boolean isApproved = false;
		boolean isSameApproveUser = false;
		if (option.equals(ResConstants.SP_OPTION_ROLLBACK)) {
			// 收回
			ResApproveNode approveNode = new ResApproveNode();
			approveNode.setResApproveInfo(approveInfo);
			approveNode.setUserCd(SpringSecurityUtils.getCurrentUiid());
			approveNode.setUserName(SpringSecurityUtils.getCurrentUserName());
			approveNode.setApproveDate(new Date());
			approveNode.setApproveOptionCd(option);
			approveNode.setRemark(approveRemark);
			// 解决追回时审批节点相同所出现
			approveNode.setNodeCd(tarNodeCd);
			approveNode.setApproveLevel(toApproveLevel);
			approveHises.add(addApproveHis(approveInfo.getLastApproveDate(), approveNode));
			
		    deleteNodeIfToMeeting(approveInfo, ResConstants.SP_NODE_CD_JCWYH);
			
			
		} else{//首先同意，将接点设置为同意，然判断这个节点是否走完，然后获取下一个节点;二级节点只能同意本节点。
		    if(isOnePassTwo(approveInfo,curNodeCd,curApproveLevel,curNodeCd2,curApproveLevel2,curUserCd)){
		    	  autoPassCd2(approveInfo, curNodeCd,curApproveLevel,approveRemark,isPassed,isSameApproveUser,approveHises);
		    }else{

			  for (Iterator<ResApproveNode> it = approveInfo.getResApproveNodes().iterator(); it.hasNext();) {
				ResApproveNode approveNode = it.next();
				if (isMyNode(curNodeCd, curApproveLevel,curNodeCd2,curApproveLevel2, approveNode)) {
			
					if (!approveNode.getApproveOptionCd().equals(ResConstants.SP_OPTION_AGREE)) {
						// 原状态不是“同意”，将该节点状态改为“同意”
//						if(StringUtils.contains(approveRemark, "不同意")){//二级不同意，后改，不同意只是备注，不影响流程，本身节点还是要过去的
//							option = ResConstants.SP_OPTION_UNAGREE;
//						}
						approveNode.setApproveDate(new Date());
						approveNode.setApproveOptionCd(option);
						approveNode.setRemark(approveRemark);
						isApproved = true;
						if (isAuto) {
							// 如果是系统自动事务，登记为admin更新
							approveNode.setUpdator("admin");
							nodeCur = approveNode;
						}
						// TODO 判断本次操作是否延期
						if (isDelay(approveInfo.getUpdatedDate(), approveNode)) {
							// notifyDelayUsers(approveNode.getUserCd());
						}
						approveHises.add(addApproveHis(approveInfo.getLastApproveDate(), approveNode));
					}
				   
					if (curNodeCd.equals(approveNode.getGroupNodeCd()) && isApproved) {
						// 如果是小组节点
						if (StringUtils.equals(approveNode.getUserCd(), SpringSecurityUtils.getCurrentUiid())) {
							approveInfo.setApproveUserCd(approveInfo.getApproveUserCd().replaceAll(
									SpringSecurityUtils.getCurrentUiid() + ";", ""));
						}
					}
					if (isApproved) {
						isPassed = isAllNodeApproved(approveInfo, approveNode);
						nodeNext = getNextNode(approveInfo, approveNode);
					}
					// TODO 以下代码在会签中如果同一个人处在2个不同的节点时，会有审批不到的问题
					// if (isApproved) {
					// break;
					// }
				}

			 }
		   }
			  
		}
	  
		//判断是否新增接电,新增包含上会,toCeo,toPresident三个
		if (option.equals(ResConstants.SP_OPTION_AGREE)) {
			String addNode = null;
			try {
				addNode = Struts2Utils.getParameter("addNode");
			} catch (Exception e) {
			}
			if (StringUtils.isNotBlank(addNode)) {
				ResApproveNode approveNode = null;
				StringBuffer sbMsg = new StringBuffer();
				if (StringUtils.equals(addNode, "toMeeting")) {
					approveNode = addNode(approveInfo, ResConstants.SP_NODE_CD_JCWYH, null, false,"toMeeting");
					sbMsg.append(SpringSecurityUtils.getCurrentUserName()).append("发起决策会，系统自动跳过");
					// --上会处理结束--
				}
				if (StringUtils.equals(addNode, "toCeo")) {
					approveNode = addNode(approveInfo, "xuhf", null, true,null);
					sbMsg.append(SpringSecurityUtils.getCurrentUserName()).append("直接发给执行总裁，系统自动跳过");
					// --上会处理结束--
				}
				if (StringUtils.equals(addNode, "toPresident")) {
					approveNode = addNode(approveInfo, "xujk", null, true,null);
					sbMsg.append(SpringSecurityUtils.getCurrentUserName()).append("直接发给总裁，系统自动跳过");
					// --上会处理结束--
				}
				if (approveNode != null) {
					autoPass(approveInfo, approveNode, sbMsg.toString());
				}
				nodeNext = approveNode;
				isSameApproveUser = false;
			}
			//如果之前的节点全部通过，则判断是否有下个节点，如果有，则获取下个节点的usrCd，如果这个节点是新增的节点，则判断新增节点的usercd和下个
			//节点的usercd是否相同，相同，则是相同的审批人。
			//并将下个节点，设置为即将审批的节点。
			if (isPassed) {
				if (nodeNext != null) {		
					 String approveUserCd = getApproveUser(nodeNext, approveInfo.getResApproveNodes(),DictContants.RES_APPROVE_RANK_ONE);//默认取节点的审批人
					 String approveUserCd2 = getApproveUser(nodeNext, approveInfo.getResApproveNodes(),DictContants.RES_APPROVE_RANK_TWO);
					 if (StringUtils.isBlank(addNode)) {
						if (StringUtils.isNotEmpty(approveInfo.getApproveUserCd2())) {//1;2;3  2;如果二级审批人不为空，则证明当前节点是二级节点。则取二级审核人比较
							if (isMutiCd2(approveUserCd2,curUserCd)) {
								isSameApproveUser = true;

							}
						} else {
							if (StringUtils.equals(approveInfo.getApproveUserCd(), approveUserCd)) {
								isSameApproveUser = true;
							}
						}
					}
					// 下一个节点Cd，如果该节点属于某个小组，则取小组Cd
					String nextNodeCd = nodeNext.getNodeCd();
					if (StringUtils.isNotEmpty(nodeNext.getGroupNodeCd())) {
						nextNodeCd = nodeNext.getGroupNodeCd();
					}
					// TODO start 如果是节假日、周末；延迟24小时
					dealTimeLimit(nodeNext);
					// end
					setCurNode(approveInfo, nodeNext);
					// 审批中:1
					approveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_ING);
					if (nextNodeCd.equals(ResConstants.SP_NODE_CD_HTFB)) {
						// 进入合同发布步骤，产生随机码，跟踪人对文件进行加密
						markDocWithRandomCd(approveInfo);
					}
				} else {//如果没有下个节点，如果当前节点是自动，则设置为未审核，
					    //非自动，则完成审核。
					    
					if (isAuto) {
						// 如果是系统事务，将回到发起人
						// TODO 2011-4-30晚，电话和draco确认，最后一步不自动跳回去。注释以下代码
						// back2start(approveInfo, curNodeCd, curApproveLevel,
						// option);
						if (nodeCur != null) {
							nodeCur.setApproveOptionCd(ResConstants.SP_OPTION_NOT);
						}
					} else {
						setCurNode(approveInfo, null);
						// 完成:2
						approveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_FINISHL);
						approveInfo.setCompleteDate(new Date());
						approveInfo.setCompleteUser(SpringSecurityUtils.getCurrentUiid());
						markCompleteWithRandomCd(approveInfo);
					}
				}
			}
		}else{//驳回  二级审批人不能驳回
			if (StringUtils.isNotEmpty(tarNodeCd)) {
				// 驳回到指定步骤，状态仍然为审批中
				boolean isMatch = false;
				boolean isSetCueNode = false; 
				for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
                       if(isNodeCd2(approveInfo,approveNode,tarNodeCd)){  //寻找一级节点
							approveNode.setApproveDate(null);
							approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);// 状态未审批
							if(!isSetCueNode){
								setCurNode(approveInfo, approveNode);
								isSetCueNode = true;
							}
							continue;
					     }
						if (!isMatch) {
						  if ((tarNodeCd.equals(approveNode.getNodeCd()) || tarNodeCd
								.equals(approveNode.getGroupNodeCd()))
								&& (approveNode.getApproveLevel().equals(toApproveLevel))) {
							approveNode.setApproveDate(null);
							approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);// 状态未审批
							isMatch = true;
							// TODO 重复节点时会有判断问题
							if(!isSetCueNode){
								setCurNode(approveInfo, approveNode);
								isSetCueNode = true;
							}
							// 审批中:1
							approveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_ING);
							approveInfo.setCompleteUser(null);
							approveInfo.setCompleteDate(null);
							approveInfo.setRandomNo(null);
						}
					} else if (!isMyNode(curNodeCd, curApproveLevel,curNodeCd2, curApproveLevel2, approveNode)) {
						// 驳回到指定步骤时，把其他步骤的审批日期和审批状态都恢复原状，审批意见保留
						approveNode.setApproveDate(null);
						approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);// 状态未审批
					}
				}
			} else {
				back2start(approveInfo, curNodeCd, curApproveLevel, curNodeCd2, curApproveLevel2,option);
			}
		}

		return new boolean[] { isSameApproveUser, isPassed };
	}
	
	/**
	 * 计算一个二级节点有没有重复用户
	 * 
	 */
	 public  static boolean isMutiCd2(String nextApproveCd2,String curUserCd){
		 boolean isMuti = false;
		 int count =0;
		 if(StringUtils.isNotEmpty(nextApproveCd2)){
			String[] userCd2 = nextApproveCd2.split("\\;");
			for(String usercd:userCd2){
				if(StringUtils.contains(curUserCd,usercd)){
					count++;
				}
			}
			if(count>0){
				isMuti = true;
			}
	
		}
		 return isMuti;
	 }
	/**
	 * /**
	 * 判断一个节点是否是一级节点的二级
	 * 驳回时连同二级节点一起驳回
	 * @param approveInfo
	 * @param approveNode
	 * @param tarNodeCd
	 * @return
	 */
	@SuppressWarnings("null")
	public boolean isNodeCd2(ResApproveInfo approveInfo,ResApproveNode approveNode,String tarNodeCd){
		  boolean isNodeCd2 =true;
		   ResApproveNode tarApproveNode = null;
			for (ResApproveNode resapproveNode : approveInfo.getResApproveNodes()) {//先找出当前节点，一定要循环读吗/
				  if(tarNodeCd.equals(resapproveNode.getNodeCd())){
					  tarApproveNode = resapproveNode;
				      break;
				  }
			}
			if(approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)){
				for (ResApproveNode resapproveNode : approveInfo.getResApproveNodes()) {
				     if(resapproveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)&&  //在这个二级节点和要驳回的一级节点中有一个一级节点，则证明这个二级
				    		 resapproveNode.getApproveLevel()>approveNode.getApproveLevel()&&         // 不是驳回节点的二级，不需要驳回，否则，驳回
				    		 resapproveNode.getApproveLevel()<tarApproveNode.getApproveLevel()){
				    	 isNodeCd2 = false;  
				     }		  
				}	
			}else{
				isNodeCd2 = false;
			}
			return isNodeCd2;
			
	}
	/**
	 * 判断此接点是否是一级节点，并是一定有二级节点，返回true
	 * by bo.wu
	 * @param approveInfo
	 * @param curNodeCd
	 * @param curApproveLevel 当前节点level
	 * @param curNodeCd2
	 * @param curApproveLevel2
	 * @return
	 */
     public boolean isOnePassTwo(ResApproveInfo approveInfo, String curNodeCd,Long curApproveLevel,String curNodeCd2,Long curApproveLevel2,String curUserCd){
    	    boolean flag = false;
    	    Long tmpNodeLevel = null;
    	    if(isOneLevel(approveInfo,curUserCd)){//一级节点
    	    	for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
        		    if(approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)&&approveNode.getApproveLevel()<curApproveLevel){//寻找离此一级节点最近的一级                                                                                                 		    	
        		    		tmpNodeLevel  = approveNode.getApproveLevel();    	
        		    }
        		}
    	    	if(null ==tmpNodeLevel){//在此一级节点上没有一级节点，那判断是否有二级，如果没有，则是单独的一级
    	    		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
    	    			if(approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)&&approveNode.getApproveLevel()<curApproveLevel){
    	    				flag = true;
    	    			}
    	    		}
    	    	}else{//有一级节点，则判断在这个一级节点和当前一级节点中是否有二级，如果没有，则为单独的一级
    	    		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
    	    			if(approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)&&approveNode.getApproveLevel()<curApproveLevel
    	    					&&approveNode.getApproveLevel()>tmpNodeLevel){
    	    				flag = true;
    	    			}
    	    	}
    	      }
    	   }	
    	   return flag;
    	 
     }
     
     /**
      * 判断当前节点是否为一级节点
      * @param approveInfo
      * @param curApproveLevel
      * @return
      */
     public boolean isOneLevel(ResApproveInfo approveInfo,String curUserCd){
    	   boolean isOne = false;
    	 //  Long curApproveLevel = approveInfo.getApproveLevel();
    	   for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
    		   if(approveNode.getUserCd().equals(curUserCd)&&approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)){
    			   isOne = true;
    			   break;
    		   }
    	   }
    	 return isOne;
     }
	
	
	public boolean[] agree(ResApproveInfo approveInfo, List<ResApproveHis> approveHises, BaseTemplate templateBean,
			ResApproveContent approveContent) throws Exception {
		// 是否点击上会按钮,在最后增加决策委员会节点
		boolean[] isSameAndPass = updateResApproveNode(approveInfo, ResConstants.SP_OPTION_AGREE, approveHises);
		if (approveContent != null) {
			resApproveContentManager.saveResApproveContent(approveContent);
		}
		saveProcess(approveInfo, approveHises, templateBean, isSameAndPass[1]);
		return isSameAndPass;
	}

	// 新增返回节点级别
	public boolean[] rollBack(ResApproveInfo approveInfo, List<ResApproveHis> approveHises, BaseTemplate templateBean,
			String toNodeCd, Long toApproveLevel) throws Exception {
		boolean[] isSameAndPass = updateResApproveNode(approveInfo, ResConstants.SP_OPTION_ROLLBACK, toNodeCd,
				toApproveLevel, approveHises);
		saveProcess(approveInfo, approveHises, templateBean, isSameAndPass[1]);
		return isSameAndPass;
	}

	private String getEmailTitle(ResApproveInfo approveInfo) {
		StringBuffer emailTitle = new StringBuffer();
		if (StringUtils.isNotBlank(approveInfo.getLandProject())) {
			emailTitle.append(approveInfo.getLandProject());
		}
		if (StringUtils.isNotBlank(approveInfo.getTitleName())) {
			emailTitle.append(approveInfo.getTitleName());
		}
		return emailTitle.toString();
	}

	public void back2Last(ResApproveInfo approveInfo, List<ResApproveHis> approveHises, BaseTemplate templateBean,
			ResApproveNode lastNode) throws Exception {
		boolean[] isSameAndPass = updateResApproveNode(approveInfo, ResConstants.SP_OPTION_ROLLBACK,
				lastNode.getNodeCd(), lastNode.getApproveLevel(), approveHises);
		saveProcess(approveInfo, approveHises, templateBean, isSameAndPass[1]);
		// 同时给发起人和企管部发送邮件
		sendEmail(approveInfo, "【网上审批追回】" + getEmailTitle(approveInfo), "该记录已经被追回,请勿执行,谢谢！",
				new String[] { approveInfo.getUserCd(), "qgb" });
	}

	public void back(ResApproveInfo approveInfo, List<ResApproveHis> approveHises, BaseTemplate templateBean,
			String rejectTo) throws Exception {
		String rejectToNode = rejectTo;
		Long rejectToLevel = null;
		// 获取节点级别，以便于区别相同nodeCd节点
		if (StringUtils.isNotBlank(rejectTo)) {
			String[] tmp = StringUtils.split(rejectTo, "||");
			if (tmp != null && tmp.length > 0) {
				rejectToNode = tmp[0];
				rejectToLevel = Long.parseLong(tmp[1]);
			}
		}
		boolean[] isSameAndPass = updateResApproveNode(approveInfo, ResConstants.SP_OPTION_BACK, rejectToNode,
				rejectToLevel, approveHises);
		saveProcess(approveInfo, approveHises, templateBean, isSameAndPass[1]);
	}

	

	/**
	 * 自动跳过中间所有步骤
	 * @param approveInfo
	 * @param curNodeCd
	 * @param curApproveLevel
	 * @param msg
	 */
	@SuppressWarnings("null")
	public void autoPassCd2(ResApproveInfo approveInfo, String curNodeCd, Long curApproveLevel,String msg,boolean isPassed,boolean isSameApproveUser,List<ResApproveHis> approveHises) {
          
		Long temApproveLevel = null;
		ResApproveNode resApproveNode =null;		
		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
		    if(approveNode.getNodeCd().equals(curNodeCd)){
		    	 resApproveNode =  approveNode;
		    	 break;
		    }
		}
        //中间是否有一级节点
		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
			if (approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)){
				if(approveNode.getApproveLevel()<curApproveLevel){
					temApproveLevel =approveNode.getApproveLevel();
				}
			}
		}
		
		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
			   if(null!=temApproveLevel){//如果一级，则将一级之间没有审批的二级更新为agree
				if (approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)&& approveNode.getApproveLevel() > temApproveLevel
						&& approveNode.getApproveLevel() < curApproveLevel&&StringUtils.equals(approveNode.getApproveOptionCd(),ResConstants.SP_OPTION_NOT) ) {
					approveNode.setApproveDate(new Date());
					approveNode.setApproveOptionCd(ResConstants.SP_OPTION_AGREE);
					approveHises.add(addApproveHis(approveInfo.getLastApproveDate(), approveNode));
					// approveNode.setRemark(msg);
				}
			 }else{
				if (approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)
						&& approveNode.getApproveLevel() < curApproveLevel&&StringUtils.equals(approveNode.getApproveOptionCd(),ResConstants.SP_OPTION_NOT)) {
					approveNode.setApproveDate(new Date());
					approveNode.setApproveOptionCd(ResConstants.SP_OPTION_AGREE);
					approveHises.add(addApproveHis(approveInfo.getLastApproveDate(), approveNode));
					// approveNode.setRemark(msg);
				}
			 }
		   
		}
		if (resApproveNode.getNodeCd().equals(curNodeCd)) {
			resApproveNode.setApproveDate(new Date());
			resApproveNode.setApproveOptionCd(ResConstants.SP_OPTION_AGREE);
			resApproveNode.setRemark(msg);
			approveHises.add(addApproveHis(approveInfo.getLastApproveDate(),resApproveNode));
			isPassed =true;
		}  
		ResApproveNode nextNode =getNextNode(approveInfo,resApproveNode);
		if(nextNode!=null){
			 String approveUserCd = getApproveUser(nextNode, approveInfo.getResApproveNodes(),DictContants.RES_APPROVE_RANK_ONE);//默认取节点的审批人
			 String approveUserCd2 = getApproveUser(nextNode, approveInfo.getResApproveNodes(),DictContants.RES_APPROVE_RANK_TWO);
				if (StringUtils.isNotEmpty(approveInfo.getApproveUserCd2())) {// 如果二级审批人不为空，则证明当前节点是二级节点。则取二级审核人比较
					if (StringUtils.equals(approveInfo.getApproveUserCd2(),approveUserCd2)) {
						isSameApproveUser = true;
					}
				} else {
					if (StringUtils.equals(approveInfo.getApproveUserCd(), approveUserCd)) {
						isSameApproveUser = true;
					}
	
			}
			setCurNode(approveInfo,nextNode);
		}else{
		        //此处没处理Auto
				setCurNode(approveInfo, null);
				// 完成:2
				approveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_FINISHL);
				approveInfo.setCompleteDate(new Date());
				approveInfo.setCompleteUser(SpringSecurityUtils.getCurrentUiid());
				markCompleteWithRandomCd(approveInfo);
			} 
	}
	/**
	 * 自动跳过中间所有步骤
	 * 
	 * @param approveInfo
	 * @param toNode
	 */
	public void autoPass(ResApproveInfo approveInfo, ResApproveNode toNode, String msg) {

		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
			if (toNode.getApproveLevel() > approveNode.getApproveLevel()
					&& approveNode.getApproveLevel() > approveInfo.getApproveLevel()) {
				approveNode.setApproveDate(new Date());
				approveNode.setApproveOptionCd(ResConstants.SP_OPTION_AGREE);
				approveNode.setRemark(msg);
				// 强制跳过中间所有步骤
			}
		}
		setCurNode(approveInfo, toNode);
	}

	/**
	 * 设置主表中，当前审批节点的信息
	 * 
	 * @param approveInfo
	 * @param approveNode
	 */
	private void setCurNode(ResApproveInfo approveInfo, ResApproveNode approveNode) {
		if (approveNode != null) {
//			String nextNodeCd = approveNode.getNodeCd();
//			// 下一个节点Cd，如果该节点属于某个小组，则取小组Cd
//			if (StringUtils.isNotEmpty(approveNode.getGroupNodeCd())) {
//				nextNodeCd = approveNode.getGroupNodeCd();
//			}
			String approveUserCd = getApproveUser(approveNode, approveInfo.getResApproveNodes(),DictContants.RES_APPROVE_RANK_ONE);
			String approveUserCd2 = getApproveUser(approveNode, approveInfo.getResApproveNodes(),DictContants.RES_APPROVE_RANK_TWO);
			String approveNodeCd2 =  getApproveNodeCd2(approveNode, approveInfo.getResApproveNodes());
			long   approveLevel2 = getApproveLevel2(approveNode, approveInfo.getResApproveNodes());
			
			approveInfo.setApproveUserCd(approveUserCd);
			approveInfo.setApproveUserCd2(approveUserCd2);
			approveInfo.setApproveLevel(getApproveLevel(approveNode, approveInfo.getResApproveNodes()));
			approveInfo.setTimeLimit(approveNode.getTimeLimit());
			approveInfo.setBackFlg(approveNode.getBackFlg());
			approveInfo.setNodeCd(getApproveNodeCd(approveNode, approveInfo.getResApproveNodes()));
			approveInfo.setApproveLevel2(approveLevel2);
			approveInfo.setNodeCd2(approveNodeCd2);
		} else {
			approveInfo.setApproveUserCd(null);
			approveInfo.setApproveUserCd2(null);
			approveInfo.setNodeCd2(null);
			approveInfo.setApproveLevel2(null);
			approveInfo.setApproveLevel(null);
			approveInfo.setNodeCd(null);
			approveInfo.setTimeLimit(null);
			approveInfo.setBackFlg(null);
		}
	}

	/**
	 * 如果是节假日、周末；延迟24小时
	 * 
	 * @param approveNode
	 */
	public void dealTimeLimit(ResApproveNode approveNode) {
		// start 如果是节假日、周末；延迟24小时
		Date dFrom = new Date();
		if (approveNode.getTimeLimit() != null && approveNode.getTimeLimit() > 0) {
			Date dTo = DateOperator.addHours(dFrom, approveNode.getTimeLimit().intValue());
			if (Util.isContainImpDay(dFrom, dTo)) {// 如果是重要节日，如春节
				Date date = DateOperator.createDate(2012, 0, 29);
				Long houres = DateOperator.getHours(dFrom, date);
				approveNode.setTimeLimit(approveNode.getTimeLimit() + houres);// 审批时限+6天
			} else if (Util.isContainHoliday(dFrom, dTo)) {// 如果是一般假期
				approveNode.setTimeLimit(approveNode.getTimeLimit() + 24);// 审批时限+1天
			}
		}
		// end
	}

	/**
	 * 驳回到发起人
	 */
	private void back2start(ResApproveInfo approveInfo, String curNodeCd, Long curApproveLevel,String curNodeCd2,Long curApproveLevel2, String option) {
		for (ResApproveNode approveNode : approveInfo.getResApproveNodes()) {
			if (!isMyNode(curNodeCd, curApproveLevel, curNodeCd2, curApproveLevel2,approveNode)) {
				// 驳回时，把其他步骤的审批状态恢复原状，审批意见保留
				approveNode.setApproveOptionCd(ResConstants.SP_OPTION_NOT);// 状态未审批
			}
		}
		// 如果为空，驳回到发起人
		setCurNode(approveInfo, null);
		if (option.equals(ResConstants.SP_OPTION_ROLLBACK)) {
			// 如果是发起人收回，记录状态变回新增
			// 新增:0
			approveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_NEW);
		
		} else {
			// 驳回:3
			approveInfo.setStatusCd(ResConstants.RES_APPROVE_STATUS_BACK);
		}
	}

	public boolean[] updateResApproveNode(ResApproveInfo approveInfo, String option, List<ResApproveHis> approveHises)
			throws Exception {
		return updateResApproveNode(approveInfo, option, null, null, null, approveHises, false);
	}
    
	/*
	 * 获取二级节点的一级节点
	 * nodeNext未二级节点
	 *  by bo.wu
	 */
	public String getApproveNodeCd(ResApproveNode nodeNext,
			List<ResApproveNode> approveNodes) {
		String approveNodeCd = "";
		long nodeRank =  nodeNext.getApproveRank();
		if (nodeRank == DictContants.RES_APPROVE_RANK_TWO) {
			for (ResApproveNode nodeTmp : approveNodes) {
				String tmpNodeCd = nodeTmp.getNodeCd();
				if (nodeTmp.getApproveLevel() > nodeNext.getApproveLevel()&& nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)) {
					if (StringUtils.isNotEmpty(nodeTmp.getGroupNodeCd())) {
						approveNodeCd = nodeTmp.getGroupNodeCd();
					} else {
						approveNodeCd = tmpNodeCd;
					}
					break;
				}
			}
		}else{
			approveNodeCd = nodeNext.getNodeCd();
			if (StringUtils.isNotEmpty(nodeNext.getGroupNodeCd())) {
				approveNodeCd = nodeNext.getGroupNodeCd();
			}
		}
		return approveNodeCd;
	}
	/*
	 * 获取一个节点的下一个二级节点，只针对当前节点为二级，并且下个节点还是二级
	 * 如果下一个是一级节点则为空
	 * 如果当前节点为一级，下一个为二级节点，也为空。
	 * nodeNext未二级节点
	 *  by bo.wu
	 */
	public String getApproveNodeCd2(ResApproveNode nodeNext,
			List<ResApproveNode> approveNodes) {
		String approveNodeCd = "";
		long nodeRank =  nodeNext.getApproveRank();
		if (nodeRank == DictContants.RES_APPROVE_RANK_TWO) {
			for (ResApproveNode nodeTmp : approveNodes) {
				String tmpNodeCd = nodeTmp.getNodeCd();
				if (nodeTmp.getApproveLevel().equals(nodeNext.getApproveLevel())&& nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)) {
					if (StringUtils.isNotEmpty(nodeTmp.getGroupNodeCd())) {
						approveNodeCd = nodeTmp.getGroupNodeCd();
					} else {
						approveNodeCd = tmpNodeCd;
					}
					break;
				}
			}
		}
		return approveNodeCd;
	}
	/*
	 * 获取二级Level
	 *  by bo.wu
	 */
	public long getApproveLevel2(ResApproveNode nodeNext,List<ResApproveNode> approveNodes) {
        long  approveLevel = 0l;
        long nodeRank =  nodeNext.getApproveRank();
		if (nodeRank == DictContants.RES_APPROVE_RANK_TWO) {
			for (ResApproveNode nodeTmp : approveNodes) {
				long tmpLevel = nodeTmp.getApproveLevel();
				if (tmpLevel >= nodeNext.getApproveLevel()&& nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)) {
					approveLevel = tmpLevel;
					break;
				}
			}
		}
        return approveLevel;
	}
	/*
	 * 获取二级Level的一级level
	 * nodeNext是二级节点
	 *  by bo.wu
	 */
	public long getApproveLevel(ResApproveNode nodeNext,List<ResApproveNode> approveNodes) {
        long  approveLevel = 0l;
        long nodeRank =  nodeNext.getApproveRank();
		if (nodeRank == DictContants.RES_APPROVE_RANK_TWO) {
			for (ResApproveNode nodeTmp : approveNodes) {
				long tmpLevel = nodeTmp.getApproveLevel();
				if (tmpLevel > nodeNext.getApproveLevel()&& nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)) {
					approveLevel = tmpLevel;
					break;
				}
			}
		} else {
			approveLevel = nodeNext.getApproveLevel();
		}
        return approveLevel;
	}
	/*
	 *获取第一个节点的审批人，如果第一个是一级，则和之前一样，
	 *如果为二级，则同时更新一级和二级审批人
	 *by bo.wu
	 */
	public String getApproveUser(ResApproveNode nodeNext, List<ResApproveNode> approveNodes,long approveRank) {
		StringBuffer sbUsers = new StringBuffer();
		long nodeRank  = nodeNext.getApproveRank()==null?DictContants.RES_APPROVE_RANK_ONE:nodeNext.getApproveRank();
	//	CollectionHelper.sortList(approveNodes, "approveLevel",false);//对approveLevel降序
		if ((nodeRank==DictContants.RES_APPROVE_RANK_ONE&&nodeRank==approveRank)) {//当前是一级并且要获取一级,/
			if (StringUtils.isNotEmpty(nodeNext.getGroupNodeCd())) {
				for (ResApproveNode nodeTmp : approveNodes) {
					if (StringUtils.equals(nodeTmp.getGroupNodeCd(), nodeNext.getGroupNodeCd())
							&& nodeTmp.getApproveLevel().equals(nodeNext.getApproveLevel())) {
						sbUsers.append(nodeTmp.getUserCd()).append(";");
					}
				}
			} else {
				sbUsers.append(nodeNext.getUserCd());
			}
		}
		if(nodeRank==DictContants.RES_APPROVE_RANK_TWO&&nodeRank==approveRank){//当前节点是二级并且要获取二级;不考虑会签
		      for (ResApproveNode nodeTmp : approveNodes) {
					if (nodeTmp.getApproveLevel()>=(nodeNext.getApproveLevel())) {
						if(nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)&&nodeTmp.getApproveOptionCd().equals("-1")){
							sbUsers.append(nodeTmp.getUserCd()).append(";");
						}else if(nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)){
							break;
						}
					}
				}
		     
		}
		if(nodeRank ==DictContants.RES_APPROVE_RANK_TWO&&approveRank==DictContants.RES_APPROVE_RANK_ONE){//当前是二级，要获取一级，还是要获取的,此一级不是会签
			 for (ResApproveNode nodeTmp : approveNodes) {
					if (nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)&& nodeTmp.getApproveLevel()>nodeNext.getApproveLevel()) {
						sbUsers.append(nodeTmp.getUserCd());
						break;
					}
				}
		}
		
		return sbUsers.toString();
	}
	
	public void notifyAllUser() throws Exception {
		if (DateOperator.isWeekday(new Date())) {
			// SMS sms = SMS.getInstanceCommon();
			StringBuffer sbHql = new StringBuffer(
					"select approveUserCd from ResApproveInfo where statusCd='1' group by approveUserCd ");
			List lstResult = find(sbHql.toString());
			for (Object objTemp : lstResult) {
				String uiid = (String) objTemp;
				sendNotice(uiid);
			}
		} else {
			log.info("is weekend day!");
		}
		// SMS sms = new SMS();
		// sendNotice("shisn",sms);
	}

	/**
	 * 网批延期过24小时仍未完成者，系统将填写“延期未签”并推往下一节点；若当前是最后节点，系统将退返发起人 <br>
	 * SELECT R.* FROM res_approve_info r, RES_APPROVE_NODE N WHERE R.STATUS_CD
	 * =1 AND R.RES_APPROVE_INFO_ID = N.RES_APPROVE_INFO_ID AND R.NODE_CD =
	 * N.NODE_CD and R.APPROVE_USER_CD=N.USER_CD AND (SYSDATE - R.UPDATED_DATE)
	 * * 24 - N.TIME_LIMIT > 24;
	 */

	public void notifyDelay24() throws Exception {
		log.info("网批延期过24自动通过");
		String ipAddress = InetAddress.getLocalHost().getHostAddress();
		if (ipAddress.equals(Constants.PROD_IP)) {
			if (DateOperator.isWeekday(new Date())) {
				log.info("开始延期自动通过处理");
				StringBuffer sbHql = new StringBuffer();
				sbHql.append("select r from ResApproveInfo as r,ResApproveNode as n ");
				sbHql.append("where r.statusCd='1' and r.resApproveInfoId=n.resApproveInfo.resApproveInfoId ");
				sbHql.append("and r.nodeCd=n.nodeCd and r.approveUserCd=n.userCd ");
				sbHql.append("and (current_date() - r.updatedDate) * 24 - n.timeLimit > 24");
				List<ResApproveInfo> lstResult = find(sbHql.toString());
				for (ResApproveInfo approveInfo : lstResult) {
					// 网批延期过24小时自动跳过该节点功能
					List<ResApproveHis> approveHises = new ArrayList<ResApproveHis>();
					boolean[] isSameAndPass = updateResApproveNode(approveInfo, ResConstants.SP_OPTION_AGREE, null,
							null, "延期未签", approveHises, true);
					saveProcess(approveInfo, approveHises, null, isSameAndPass[1]);
				}
				log.info("结束延期自动通过处理");
			}
		}
	}

	/**
	 * 累计一个月录得第一、二次延期，系统发出警告短信和邮件 每小时运行一次
	 * 
	 * <pre>
	 * SELECT *
	 *   FROM (SELECT *
	 *           FROM (SELECT SUM (h.flag) CNT, H.USER_CD
	 *                   FROM VW_RES_APPROVE_DELAY h
	 *                  WHERE TO_CHAR (H.APPROVE_DATE, 'yyyy-MM') = TO_CHAR (SYSDATE, 'yyyy-MM')
	 *                 GROUP BY H.USER_CD)
	 *          WHERE cnt > 0 AND cnt <= 2) d
	 *  WHERE NOT EXISTS (SELECT ''
	 *                      FROM res_notify_info n
	 *                     WHERE d.cnt = N.DELAY_TIME AND d.user_cd = N.USER_CD AND TO_CHAR (n.CREATED_DATE, 'yyyy-MM') = TO_CHAR (SYSDATE, 'yyyy-MM'))
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void notifyDelayUsers() throws Exception {
		log.info("过期次数提醒");
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT * FROM (");
		sbSql.append("SELECT * FROM(SELECT SUM (h.flag) CNT,H.USER_CD");
		sbSql.append(" FROM VW_RES_APPROVE_DELAY h");
		sbSql.append(" WHERE TO_CHAR (H.APPROVE_DATE, 'yyyy-MM') = TO_CHAR (SYSDATE, 'yyyy-MM')");
		sbSql.append(" GROUP BY H.USER_CD) WHERE cnt > 0 )d");
		sbSql.append(" WHERE NOT EXISTS (");
		sbSql.append("SELECT '' FROM res_notify_info n");
		sbSql.append(" WHERE d.cnt = N.DELAY_TIME AND d.user_cd = N.USER_CD");
		sbSql.append(" AND TO_CHAR (n.CREATED_DATE, 'yyyy-MM') = TO_CHAR (SYSDATE, 'yyyy-MM')");
		sbSql.append(" )");
		// sbSql.append("and user_cd='huangjian'");
		Map<String, Object> pram = new HashMap<String, Object>();
		List lstResult = findBySql(sbSql.toString(), pram);
		// SMS sms = SMS.getInstanceCommon();
		String title = "网批过期提醒";

		StringBuffer smsMsg = null;
		StringBuffer emailMsg = null;
		ResNotifyInfo notifyInfo = null;
		BigDecimal cnt = null;
		String userCd = null;
		Object[] objAry = null;

		for (Object obj : lstResult) {

			objAry = (Object[]) obj;
			cnt = (BigDecimal) objAry[0];// 本月延迟次数
			userCd = (String) objAry[1];// 审批人员

			// ----发送短信----//
			String phone = getUserPhone(userCd);
			smsMsg = new StringBuffer();
			smsMsg.append(title).append(":").append("\n");
			smsMsg.append("您本月网批过期").append(cnt).append("次");
			Util.getPlasService().sendCommonSms(title, new String[] { phone }, smsMsg.toString());
			// ----end-----//

			/*
			 * // ----发送邮件----//
			 * emailMsg = new StringBuffer();
			 * emailMsg.append(title).append(":").append("<br/>");
			 * emailMsg.append("您本月已延迟审批").append(cnt).append("次");
			 * oaEmailBodyManager.sendData2Email(title, emailMsg.toString(), "email_admin", "1", userCd);
			 * // -----end-----//
			 */

			// -----插入信息表-----//
			notifyInfo = new ResNotifyInfo();
			notifyInfo.setUserCd(userCd);
			notifyInfo.setMsgTitle(title);
			notifyInfo.setYearMonth(DateOperator.formatDate(new Date(), "yyyy-MM"));
			notifyInfo.setDelayTime(cnt.longValue());
			resNotifyInfoManager.saveResNotifyInfo(notifyInfo);

			// -----end-----//
		}
		log.info("结束过期次数提醒");
	}

	private String getUserPhone(String uiid) {
		// TODO 肖总，施小姐不发短信。
		String phone = null;
		String proxyUiid = CodeNameUtil.getDictNameByCd(DictContants.RES_USER_PROXY, uiid);
		if (StringUtils.isNotBlank(proxyUiid)) {
			if (proxyUiid.length() == 11) {
				phone = proxyUiid;
			} else {
				phone = CodeNameUtil.getUserByUiid(proxyUiid).getMobilePhone();
			}
		} else {
			phone = CodeNameUtil.getUserByUiid(uiid).getMobilePhone();
		}
		return phone;
	}

	/**
	 * SELECT d.user_cd, d.approve_cd, ROUND (d.reduce) AS
	 * reduce_h,D.RES_APPROVE_INFO_ID FROM VW_RES_APPROVE_TIME_REDUCE d
	 * WHERE (ROUND (d.reduce) = 7 OR d.reduce < 3) AND NOT EXISTS (SELECT '' FROM res_notify_reduce n
	 * WHERE D.USER_CD = n.user_cd AND D.REDUCE = n.REDUCE_TIME AND
	 * D.RES_APPROVE_INFO_ID = n.RES_APPROVE_INFO_ID)
	 */
	public void notifyReduce() throws Exception {
		log.info("开始即将过期提醒");
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT d.user_cd, d.approve_cd, ROUND (d.reduce) AS reduce_h, d.reduce, D.RES_APPROVE_INFO_ID, D.TITLE_NAME");
		sbSql.append(" FROM VW_RES_APPROVE_TIME_REDUCE d");
		sbSql.append(" WHERE (ROUND (d.reduce) = 7 OR d.reduce < 3)");
		sbSql.append(" AND NOT EXISTS (SELECT '' FROM res_notify_reduce n ");
		sbSql.append(" WHERE D.USER_CD = n.user_cd AND D.REDUCE = n.REDUCE_TIME AND D.RES_APPROVE_INFO_ID = n.RES_APPROVE_INFO_ID)");
		Map<String, Object> pram = new HashMap<String, Object>();
		List lstResult = findBySql(sbSql.toString(), pram);
		String title = null;

		Object[] objAry = null;
		String userCd = null;
		String approveCd = null;
		BigDecimal reduceH = null;
		BigDecimal reduce = null;
		String resApproveInfoId = null;
		String titleName = null;
		String phone = null;
		StringBuffer smsMsg = null;
		ResNotifyReduce notifyReduce = null;

		for (Object obj : lstResult) {
			objAry = (Object[]) obj;
			userCd = (String) objAry[0];// 审批人员
			approveCd = (String) objAry[1];// 审批编号
			reduceH = (BigDecimal) objAry[2];// 剩余时间
			reduce = (BigDecimal) objAry[3];// 剩余时间（小数）
			resApproveInfoId = (String) objAry[4];// 主表ID
			titleName = (String) objAry[5];// 标题
			if (reduceH.intValue() < 7) {
				title = "您有一条网批即将过期。\n剩余时间" + reduce;
			} else {
				title = "您有一条过7小时即将过期，申请延期需要提前6小时，请尽快关注处理。";
			}
			// ----发送短信----//
			phone = getUserPhone(userCd);
			smsMsg = new StringBuffer();
			smsMsg.append(title).append("\n");
			if (StringUtils.isNotBlank(titleName)) {
				smsMsg.append("标题:").append(titleName).append("\n");
			}
			smsMsg.append("编号").append(approveCd);
			Util.getPlasService().sendCommonSms(title, new String[] { phone }, smsMsg.toString());
			// ----end-----//

			// -----插入信息表-----//
			notifyReduce = new ResNotifyReduce();
			notifyReduce.setUserCd(userCd);
			notifyReduce.setMsgTitle(title);
			notifyReduce.setReduceTime(reduceH.longValue());
			notifyReduce.setResApproveInfoId(resApproveInfoId);
			resNotifyReduceManager.saveResNotifyReduce(notifyReduce);

			// -----end-----//
		}
		log.info("结束即将过期提醒");
	}

	/**
	 * 通过短信及邮件提醒相关人员,在网上审批历史中调用
	 * 
	 * @param userCd
	 *            人员Cd
	 * @param title
	 *            标题
	 * @param smsMsg
	 *            短信内容
	 * @param emailMsg
	 *            邮件内容
	 */
	public void notifyUser(String userCd, String title, String smsMsg, String emailMsg) throws Exception {
		String phone = getUserPhone(userCd);
		// ----发送短信---- //
		// SMS sms = SMS.getInstanceCommon();
		Util.getPlasService().sendCommonSms(title, new String[] { phone }, smsMsg.toString());

		// ----发送邮件---- //
		// oaEmailBodyManager.sendData2Email(title, emailMsg, "email_admin", "1", userCd);
	}

	/**
	 * 发送通知
	 */
	private void sendNotice(String uiid) {
		String sbMsg = getNoticeContent(uiid);
		if (sbMsg != null) {
			String phone = PlasCache.getUserByUiid(uiid).getMobilePhone();
			// sms.send(sbMsg, new String[] { phone });
		}
	}

	/**
	 * 取得当前人员的通知内容
	 * 
	 * @return
	 */
	private String getNoticeContent(String uiid) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filterMy = new PropertyFilter(new String[] { "EQS_approveUserCd", "LIKES_approveUserCd" },
				new String[] { uiid, uiid + ";" });
		// PropertyFilter filterMy_like = new
		// PropertyFilter("LIKES_approveUserCd", uiid + ";");
		PropertyFilter filterStatus = new PropertyFilter("EQS_statusCd", ResConstants.RES_APPROVE_STATUS_ING);
		filters.add(filterMy);
		// filters.add(filterMy_like);
		filters.add(filterStatus);
		// 总共
		int intTotal = countByPropertyFilter(filters);
		filters.clear();
		filters.add(filterMy);
		filters.add(filterStatus);
		if (intTotal > 0) {
			Date today = DateOperator.truncDate(new Date());
			Date yestoday = DateOperator.addDays(today, -1);
			filters.add(new PropertyFilter("GED_updatedDate", yestoday));
			// 近2天新增
			int intNew = countByPropertyFilter(filters);
			filters.clear();
			filters.add(filterMy);
			filters.add(filterStatus);
			Date beforeYestoday = DateOperator.addDays(today, -2);
			filters.add(new PropertyFilter("LTD_updatedDate", beforeYestoday));
			// 过24小时
			int int24 = countByPropertyFilter(filters);
			filters.clear();
			filters.add(filterMy);
			filters.add(filterStatus);
			Date db5 = DateOperator.addDays(today, -6);
			filters.add(new PropertyFilter("LTD_updatedDate", db5));
			// 过5天
			int int5d = countByPropertyFilter(filters);
			StringBuffer sbMsg = new StringBuffer("网上审批提醒：\n");
			sbMsg.append("当前共 ").append(intTotal);
			sbMsg.append(" 条未审批记录；\n");
			sbMsg.append("其中:\n");
			sbMsg.append("新增 ");
			sbMsg.append(intNew).append(" 条,\n");
			sbMsg.append("过24小时 ").append(int24).append(" 条,\n");
			sbMsg.append("过5天 ").append(int5d).append(" 条。");
			return sbMsg.toString();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResApproveInfo, String> getDao() {
		return resApproveInfoDao;
	}

	public boolean isResSharedUser(String bizEntityId) {
		return isResSharedUser(bizEntityId, false);
	}

	public boolean isResSharedUser(String bizEntityId, boolean isNotReplied) {
		boolean isShared = false;
		ResApproveInfo rai = this.getEntity(bizEntityId);
		if (rai.getResApproveShares() != null) {
			for (ResApproveShare s : rai.getResApproveShares()) {
				if (s.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())) {
					if (!isNotReplied || !StringUtils.equals(s.getRemark(), "replied")) {
						isShared = true;
						break;
					}
				}
			}
		}
		return isShared;
	}

	private String buildRemindSql(Map<String, Object> pram) {
		StringBuffer baseSql = new StringBuffer();
		baseSql.append("select  t1.res_approve_info_id, t1.approve_cd||t1.serial_no as approve_cd_no,");
		// baseSql.append("t1.approve_level,t1.title_name,t2.node_cd,t3.node_name,t2.time_limit,");
		// baseSql.append("t1.last_approve_date,sysdate,t1.created_date,");
		baseSql.append(" trunc(((t1.last_approve_date+(t2.time_limit/24)) - sysdate)*24) as timereduce ,");
		baseSql.append(" t2.user_cd ,");
		baseSql.append(" t1.title_name ,");
		baseSql.append(" t2.time_limit");
		baseSql.append("  from res_approve_info t1,res_approve_node t2,res_node t3  ,res_approve_user t4");
		baseSql.append(" where");
		baseSql.append(" t1.res_approve_info_id = t2.res_approve_info_id and t3.node_cd = t2.node_cd and t2.time_limit is not null and t2.time_limit >0");
		baseSql.append(" and t1.res_approve_info_id = t4.res_approve_info_id");
		baseSql.append(" and t1.node_cd = t2.node_cd");
		baseSql.append(" and t2.user_cd = t4.user_cd");
		baseSql.append(" and t1.approve_level = t2.approve_level ");
		// 人员条件
		// baseSql.append(" and t2.user_cd = 'chenjj1'");//上正式机去掉
		baseSql.append(" order by t2.user_name,trunc(((t1.last_approve_date+(t2.time_limit/24)) - sysdate)*24) desc, t1.last_approve_date desc ");
		return baseSql.toString();
	}

	public void sendWorkEmail() throws Exception {
		String hql = "";
		List lstResult = new ArrayList();
		Object[] objAry = null;
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List allList = new ArrayList();
		Map<String, Object> pram = new HashMap<String, Object>();
		hql = buildRemindSql(pram);
		lstResult = findBySql(hql, pram);
		// 先保存临时用户名
		String oldName = "";
		String newName = "";
		if (lstResult != null && lstResult.size() > 0) {
			for (int i = 0; i < lstResult.size(); i++) {
				objAry = (Object[]) lstResult.get(i);

				if (!"".equals(newName)) {
					newName = (String) objAry[3];
				}
				if ("".equals(oldName) && "".equals(newName)) {
					allList.add(objAry);
					oldName = (String) objAry[3];
					newName = (String) objAry[3];
				} else if (!oldName.equals(newName)) {
					tempMap.put(oldName, allList);
					allList = new ArrayList();
					oldName = (String) objAry[3];
					allList.add(objAry);
				} else if (oldName.equals(newName)) {
					allList.add(objAry);
				}

				// 记录最后一条记录
				if (i == lstResult.size() - 1) {
					tempMap.put(oldName, allList);
				}
			}
			// 发送邮件
			if (tempMap != null) {
				sendMsgEmail(tempMap);
			}

		}

	}

	/**
	 * 新建表单，处于‘新建’状态
	 * @param authTypeCd
	 * @param template
	 * @throws Exception 
	 */
	public void createBill(String authTypeCd,BaseTemplate template,ResApproveInfo entity) throws Exception {
		if (StringUtils.isNotEmpty(authTypeCd)) {
			ResAuthType resAuthType = resAuthTypeManager.getResAuthTypeByCd(authTypeCd);
			// 初始化审批数据
			if (resAuthType != null) {
				entity.setModuleTypeCd(resAuthType.getResModule().getModuleTypeCd());
				entity.setTempletCd(resAuthType.getTempletCd());
				entity.setAuthTypeCd(resAuthType.getAuthTypeCd());
				// 初始状态0-新增
				entity.setStatusCd(ResConstants.RES_APPROVE_STATUS_NEW);
				// 默认延期状态0：无延期
				entity.setDelayStatusCd(ResConstants.DELAY_STATUS_CD_NORMAL);
				entity.setIsImported(false);
				entity.setIsSync(false);
				entity.setCreatedDeptCd(SpringSecurityUtils.getCurrentCenterCd());
				
				ResApproveContent approveContent=	new ResApproveContent();
				approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(template)));
				approveContent.setResApproveInfo(entity);
				saveResApproveInfo(entity,null,approveContent);
			}
		}
	}

	// 遍历hashmap并拼出内容发送邮件
	private String sendMsgEmail(Map map) {
		String title = "事务简报";
		StringBuffer msg_email = new StringBuffer();
		String rootPath = ServletActionContext.getServletContext().getRealPath("");
		String realPath = "http://" + Struts2Utils.getRequest().getLocalAddr()
				+ Struts2Utils.getRequest().getContextPath(); // 正式机
		// String realPath = "http://localhost:8080"+Struts2Utils.getRequest().getContextPath(); //本机测试
		List list = new ArrayList();
		Object[] objArray = null;
		String[] userCd = { "huangyong" };
		String[] toUserEmails = null;
		StringBuffer tempStr1 = new StringBuffer();
		StringBuffer tempStr2 = new StringBuffer();
		StringBuffer tempStr3 = new StringBuffer();
		StringBuffer idArray1 = new StringBuffer();
		StringBuffer idArray2 = new StringBuffer();
		StringBuffer idArray3 = new StringBuffer();
		int i1 = 1;
		int i2 = 1;
		int i3 = 1;
		// 遍历hasmMap发送邮件
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			msg_email = new StringBuffer();
			tempStr1 = new StringBuffer();
			tempStr2 = new StringBuffer();
			tempStr3 = new StringBuffer();
			idArray1 = new StringBuffer();
			idArray2 = new StringBuffer();
			idArray3 = new StringBuffer();
			i1 = 1;
			i2 = 1;
			i3 = 1;
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			if (entry.getValue() != null) {
				list = (List) entry.getValue();
				for (Object obj : list) {
					objArray = (Object[]) obj;
					String approveNo = (String) objArray[1];
					String titleName = "null".equals(String.valueOf(objArray[4])) ? "" : objArray[4].toString();
					int retimeReduce = Integer.parseInt(objArray[2].toString());
					int re = retimeReduce;
					int retimeLimit = Integer.parseInt(objArray[5].toString());
					retimeReduce = retimeLimit - retimeReduce;
					// 已耗时12-18小时审批
					if (re > 0 && retimeReduce > 12 && retimeReduce < 18) {
						if (!tempStr1.toString().isEmpty()) {
							tempStr1.append("<br/>");
						}
						if (!idArray1.toString().isEmpty()) {
							idArray1.append(",");
						}
						idArray1.append((String) objArray[0]);
						tempStr1.append(String.valueOf(i1)).append(".");
						i1++;
						tempStr1.append(approveNo).append("(").append(titleName).append(")");
					}
					// 18小时以上并没有过期
					else if (re > 0 && retimeReduce > 18) {
						if (!tempStr2.toString().isEmpty()) {
							tempStr2.append("<br/>");
						}
						if (!idArray2.toString().isEmpty()) {
							idArray2.append(",");
						}
						idArray2.append((String) objArray[0]);
						tempStr2.append(String.valueOf(i2)).append(".");
						i2++;
						tempStr2.append(approveNo).append("(").append(titleName).append(")");
					}
					// 已过期
					else if (re < 0) {
						if (!tempStr3.toString().isEmpty()) {
							tempStr3.append("<br/>");
						}
						if (!idArray3.toString().isEmpty()) {
							idArray3.append(",");
						}
						idArray3.append((String) objArray[0]);
						tempStr3.append(String.valueOf(i3)).append(".");
						i3++;
						tempStr3.append(approveNo).append("(").append(titleName).append(")");
					}
				}
				msg_email
						.append("等待您审核的网批条目，已耗时在")
						.append(idArray1.length() > 0 ? "<a href='" + realPath
								+ "/res/res-approve-info!queryMyRemindDuty.action?idArrays=" + idArray1 + "'>" : "")
						.append("12-18")
						.append(idArray1.length() > 0 ? "</a>" : "")
						.append("小时的有：<br/>")
						.append(tempStr1)
						.append(tempStr1.length() > 0 ? "<br/>" : "")
						.append(idArray2.length() > 0 ? "<a href='" + realPath
								+ "/res/res-approve-info!queryMyRemindDuty.action?idArrays=" + idArray2 + "'>" : "")
						.append("18")
						.append(idArray2.length() > 0 ? "</a>" : "")
						.append("小时以上的有")
						.append("：<br/>")
						.append(tempStr2)
						.append(tempStr2.length() > 0 ? "<br/>" : "")
						.append(idArray3.length() > 0 ? "<a href='" + realPath
								+ "/res/res-approve-info!queryMyRemindDuty.action?idArrays=" + idArray3 + "'>" : "")
						.append("已过期的有：").append(idArray3.length() > 0 ? "</a>" : "").append("<br/>").append(tempStr3);

				// 发送邮件
				toUserEmails = new String[1];
				// toUserEmails[0] = key+"@powerlong.com";
				toUserEmails[0] = "huangyong@powerlong.com";

				try {
					EmailUtil.send("admin", "pd1201[]", toUserEmails, null, null, title, msg_email.toString(), null,
							null, rootPath, realPath);
					// EmailUtil.send("huangyong", "huang321", toUserEmails, null, null, title, msg_email.toString(),
					// null, null, rootPath, realPath);
					// System.out.println(key+"--->"+msg_email.toString());
				} catch (Exception e) {
					System.out.println(key + "发送失败");
					// e.printStackTrace();
				}
			}

		}
		return null;
	}
	
	public ResApproveInfo getResApproveInfoById(String resId){
		
		String hql = " from ResApproveInfo t where t.resApproveInfoId = ? ";
		List list = find(hql, resId);
		if(list == null || list.size() == 0)
			return null;
		else
			return (ResApproveInfo)list.get(0);
	}
	
	/*
	 * 属于审批序列，但是不是当前审批人的userCds
	 */
	@SuppressWarnings("rawtypes")
	public List getNotNowUsers(ResApproveInfo res){
		List<String> notNowUsers = new ArrayList<String>();
		int cd2Count = 0;//未审批的二级审批人数
		for (ResApproveNode approveNode : res.getResApproveNodes()) {			
			if(approveNode.getApproveOptionCd().equalsIgnoreCase("-1")){
				if(approveNode.getApproveRank().equals(DictContants.RES_APPROVE_RANK_TWO)){ //节点是二级并且未审批，则为当前审批人
					cd2Count++;
					continue;
			   }else{
		            if(cd2Count>0){
		            	notNowUsers.add(approveNode.getUserCd());
		            } 
		            break;
				}
			}
		}
//		if(notNowUsers.size()>1){
//			notNowUsers.remove(0);
//		}
		return notNowUsers;
	}
}
