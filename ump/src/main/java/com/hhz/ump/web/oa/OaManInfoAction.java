package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaManInfoManager;
import com.hhz.ump.dao.oa.OaManInfoMessageManager;
import com.hhz.ump.dao.oa.OaManNodeManager;
import com.hhz.ump.dao.res.ResAuthTypeManager;
import com.hhz.ump.dao.res.ResModuleManager;
import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.oa.OaManInfo;
import com.hhz.ump.entity.oa.OaManInfoMessage;
import com.hhz.ump.entity.oa.OaManInfoNode;
import com.hhz.ump.entity.res.ResApproveStep;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResConditonType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtilRes;
import com.hhz.ump.web.res.ResConstants;

/**
 * 审批流程Action
 * 
 * @author zmwu 2010-11-8
 */
@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "oa-man-info!load.action", type = "redirect", params = { "dictCd","${manInfoCd}","proposalCatCd","${proposalCatCd}",
		"page.pageNo", "${page.pageNo}"}) })
public class OaManInfoAction extends CrudActionSupport<OaManInfo> {

	private static final long serialVersionUID = 6009555571310681380L;

	private OaManInfo entity;

	@Autowired
	private OaManInfoManager oaManInfoManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;
	
	@Autowired
	private OaManInfoMessageManager oaManInfoMessageManager;
	@Autowired
	private OaManNodeManager oaManNodeManager;
	@Autowired
	private ResModuleManager resModuleManager;
	@Autowired
	private ResAuthTypeManager resAuthTypeManager;
	@Autowired
	private ResNodeManager resNodeManager;
	@Autowired
	private JbpmTaskManager jbpmTaskManager;
	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	private String dictCd;
	
	private String dictName;
	
	private String proposalCatCd;

	//新增记录时，附件上传使用的临时ID
	private String entityTmpId;
	private String isMyApprove; 
	private String isZcApprove; 
	
	// 申请状态 
	public static final String OA_MAN_STATUS_NEW = "0";		//新增
	public static final String OA_MAN_STATUS_PROCESS = "1";	//审批
	public static final String OA_MAN_STATUS_OVER = "2";	//完成
	public static final String OA_MAN_STATUS_REJECT = "3";	//驳回
	public static final String OA_MAN_STATUS_MYDEAL = "6";	//已经手
	public static final String OA_MAN_STATUS_MYDUTY = "7";	//与我有关的

	// 同意
	public static final String SP_OPTION_AGREE = "1";
	// 驳回
	public static final String SP_OPTION_BACK = "3";
	
	// 过滤条件
	// 审批状态
	private String filter_EQS_statusCd;
	
	// 审批编号
	private String filter_LIKES_appSerialNo;

	// 发起人
	private String filter_LIKES_applyUserName;
	private String filter_EQS_applyUserCd;
	// 审批人
	private String filter_LIKES_approveUserName;
	private String filter_EQS_approveUserCd;

	// 发起时间
	private String filter_GED_startDate;

	private String filter_LTD_startDate;

	// 标题
	private String filter_LIKES_titleName;
	
	
	@Override
	public String delete() throws Exception {
		oaManInfoManager.deleteOaManInfo(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		 
		 try {
				this.getModel().getOaManInfoId();
			} catch (ObjectNotFoundException e) {
				Struts2Utils.getRequest().setAttribute("error_msg", new String[] { "该记录已经被删除" });
				return "message";
			}
		return "input";
	}

	@Override
	public String list() throws Exception {
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaManInfoManager.getEntity(getId());
			dictCd = entity.getManInfoCd();
			if (isMyApprove(entity)) {
				setIsMyApprove("1");
			}
			if (isZcApprove(entity)) {
				setIsZcApprove("1");
			}
		} else {
			entity = new OaManInfo();
			entityTmpId = RandomUtils.generateTmpEntityId();
			dictCd = Struts2Utils.getParameter("dictCd");
			proposalCatCd = Struts2Utils.getParameter("proposalCatCd");
		}
	}

	public List<AppDictData> getNodeTypeList() {
		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(DictContants.SP_NODE_TYPE_MAN);
		if (appDictType == null || appDictType.getAppDictDatas() == null)
			return new ArrayList<AppDictData>();
		return appDictType.getAppDictDatas();
	}
	
	/**
	 * 模块名称 
	 * @return
	 */
	public Map<String, String> getMapDictCdType() {
		return resModuleManager.getModuleMap();
	}

	public String checkCd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newCd = request.getParameter("newCd");
		String oldCd = request.getParameter("oldCd");
		if (oaManInfoManager.isPropertyUnique("nodeCd", newCd, oldCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}
	
	private boolean isMyApprove(OaManInfo oaManInfo) {
		boolean flag = false;
		if (StringUtils.equals(oaManInfo.getApproveUserCd(), SpringSecurityUtils.getCurrentUiid()) ) {
			flag = true;
		}
		if (oaManInfo.getApproveUserCd() != null && oaManInfo.getApproveUserCd().contains(SpringSecurityUtils.getCurrentUiid() + ";")) {
			flag = true;
		}
		return flag;
	}
	
	private boolean isZcApprove( OaManInfo oaManInfo) {
		boolean flag = false;
		if (StringUtils.equals(oaManInfo.getApproveUserCd(), SpringSecurityUtils.getCurrentUiid()) && oaManInfo.getApproveUserCd().equals("xuhf") ) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 驳回
	 * 
	 * @return
	 * @throws Exception
	 */
	public String back() throws Exception {
		if (!isMyApprove(entity)) {
			Struts2Utils.renderText("fail");
			return null;
		}
		// 审批意见
		String approveRemark = Struts2Utils.getParameter("approveRemark");
		// 获取当前节点记录,并更新当前节点的信息
		OaManInfoNode currentNode = oaManInfoManager.getOaManInfoNode(entity.getOaManInfoId(), entity.getNodeCd(),SpringSecurityUtils.getCurrentUiid());
		if(currentNode == null){
			Struts2Utils.renderText("当前节点信息为空");
			return null;
		}else{
			currentNode.setRemark(approveRemark);
			currentNode.setApproveDate(new Date());
			currentNode.setApproveOptionCd("0");
			oaManInfoManager.saveOaManInfoWithNode(currentNode);
		}
		
		
        entity.setApproveUserCd("");
        entity.setStatusCd(SP_OPTION_BACK);
        entity.setNodeCd("");
        oaManInfoManager.saveOaManInfo(entity);
        
        
        
		return RELOAD;
	}
	
	public void prepareAgree() throws Exception {
		prepareModel();
	}
	public void prepareBack() throws Exception {
		prepareModel();
	}
	/**
	 * 审批状态
	 * 
	 * @return
	 */
	public Map<String, String> getMapStatusCd() {
		Map<String,String> map = new HashMap<String,String>();
		map.put(OA_MAN_STATUS_NEW, "新增");
		map.put(OA_MAN_STATUS_PROCESS, "审批");
		map.put(OA_MAN_STATUS_OVER, "完成");
		map.put(OA_MAN_STATUS_REJECT, "驳回");
		return map;
	}
	/**
	 * 提案评级
	 * @return
	 */
	public Map<String,String> getMapRatingCd(){
		Map<String,String> mapRating = new LinkedHashMap<String,String>();
		mapRating.put("", "-选择-");
		mapRating.put("1", "一等奖");
		mapRating.put("2", "二等奖");
		mapRating.put("3", "三等奖");
		mapRating.put("4", "鼓励奖");
		mapRating.put("5", "突出贡献奖");
		return mapRating;
	}
	
	
	
	@Override
	public String save() throws Exception {
		entity.setSeriaNo(oaManInfoManager.getMaxSeriaNo(entity.getManInfoCd()));
		entity.setStatusCd("0");
		oaManInfoManager.saveResApproveInfo(entity, entityTmpId);
		return RELOAD;
	}

	/**
	 * 同意
	 * 
	 * @return
	 * @throws Exception
	 */
	public String agree() throws Exception {
		if (!isMyApprove(entity)) {
			Struts2Utils.renderText("fail");
			return null;
		}
		
		long tmpNextNodeLevel = -1;
		// 审批意见
		String approveRemark = Struts2Utils.getParameter("approveRemark");
		
		// 获取当前节点记录,并更新当前节点的信息
		OaManInfoNode currentNode = oaManInfoManager.getOaManInfoNode(entity.getOaManInfoId(), entity.getNodeCd(),SpringSecurityUtils.getCurrentUiid());
		if(currentNode == null){
			Struts2Utils.renderText("当前节点信息为空");
			return null;
		}else{
			currentNode.setRemark(approveRemark);
			currentNode.setApproveDate(new Date());
			currentNode.setApproveOptionCd(SP_OPTION_AGREE);
			oaManInfoManager.saveOaManInfoWithNode(currentNode);
		}
		
		// 获取所有节点列表
		List<OaManInfoNode> allNodes = entity.getOaManInfoNodes();
		// 排序
		Collections.sort(allNodes, new Comparator<OaManInfoNode>() {
			public int compare(OaManInfoNode nr1, OaManInfoNode nr2) {
				long cd1 = nr1.getApproveLevel();
				long cd2 = nr2.getApproveLevel();
				return (int) (cd1 - cd2);
			}
		});
		
		//先判断当前节点是否是组审批，如果是的话，那要看循环该组是否都审批结束，不执行获取下一个节点的步骤
		List<OaManInfoNode> lastNodeList = oaManInfoManager.getLastOaManInfoNode(entity.getOaManInfoId(), entity.getNodeCd(),SpringSecurityUtils.getCurrentUiid());
		if(lastNodeList==null){
			
			//获取下一个节点
			List<OaManInfoNode> nextTmplateNodeList = new ArrayList<OaManInfoNode>();
			
			//是否找到当前节点 false-无 true-是
			boolean bHasFindCurNodeFlg = false;
			OaManInfoNode tmpNode = null;
			for(int i=0; i < allNodes.size(); i++){
				tmpNode = allNodes.get(i);
				if(bHasFindCurNodeFlg && tmpNode.getApproveOptionCd().equals("-1")){
					if(tmpNextNodeLevel == -1){
						tmpNextNodeLevel = tmpNode.getApproveLevel();
						nextTmplateNodeList.add(tmpNode);
					}else{
						if(tmpNextNodeLevel == tmpNode.getApproveLevel()){
							nextTmplateNodeList.add(tmpNode);
						}
					}
					continue;
				}
				if(currentNode.getOaManInfoNodeId().equals(tmpNode.getOaManInfoNodeId())  ){
					bHasFindCurNodeFlg = true;
					continue;
				}
			}

			if(nextTmplateNodeList!=null && nextTmplateNodeList.size()>0){
				String userCd = null;
				StringBuffer sbApproveUserCd = new StringBuffer();
				for(int i=0;i<nextTmplateNodeList.size();i++){
					OaManInfoNode temp = nextTmplateNodeList.get(i);
					userCd = temp.getUserCd();
					if(nextTmplateNodeList.size()>1){
						sbApproveUserCd.append(userCd).append(";");
					}else{
						sbApproveUserCd.append(userCd);
					}
					
				}
				entity.setApproveUserCd(sbApproveUserCd.toString());
			    entity.setUpdatedDate(new Date());
			    entity.setNodeCd(String.valueOf(tmpNextNodeLevel));
			    oaManInfoManager.saveOaManInfo(entity);
			    String[] users = entity.getApproveUserCd().split(";");
//			    oaManInfoManager.updateJbpmTask(entity);
			    oaManInfoManager.sendEmail(entity, "合理化建议审批", "已经到您审批",users);
			  
			}else{
				    String rating = Struts2Utils.getParameter("rating");
				    entity.setRating(Character.valueOf(rating.charAt(0)));
					entity.setApproveUserCd("");
				    entity.setUpdatedDate(new Date());
				    entity.setStatusCd(OA_MAN_STATUS_OVER);
				    entity.setApproveUserCd("");
				    oaManInfoManager.saveOaManInfo(entity);
//				    oaManInfoManager.updateJbpmTask(entity);
				    oaManInfoManager.sendEmail(entity, "合理化建议审批", "审批已完成",entity.getCreator());
				 
			}
			
		}else{
			 
			 entity.setApproveUserCd(entity.getApproveUserCd().replaceAll(SpringSecurityUtils.getCurrentUiid() + ";", ""));
		     entity.setUpdatedDate(new Date());
			 oaManInfoManager.saveOaManInfo(entity);
//			 JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(entity.getOaManInfoId());
//			 String[] users = entity.getApproveUserCd().split(";");
//			 for (Iterator<JbpmTaskCandidate> it = jbpmTask.getJbpmTaskCandidates().iterator(); it.hasNext();) {
//					JbpmTaskCandidate taskCandidate = it.next();
//					if (!ArrayUtils.contains(users, taskCandidate.getUserCd())) {
//						jbpmTaskCandidateManager.delete(taskCandidate);
//						it.remove();
//					}
//				}
		}
		
		return RELOAD;
	}
	
	/**
	 * 得到部门名称
	 * @return
	 */
	public Map<String, String> getMapNode() {
		return resNodeManager.getNodeMap();
	}
	/**
	 * 审批意见
	 * 
	 * @return
	 */
	public Map<String, String> getMapOption() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_OPTION);
	}

	public Map<String, String> getMapOptionZyps() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_OPTION_ZYPS);
	}
	
	public String load() throws Exception {
		
		/*
		dictCd = Struts2Utils.getParameter("dictCd");
		page.setPageSize(20);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		PropertyFilter propertyFilter = new PropertyFilter("EQS_creator", uiid);
		if(!dictCd.equals("") && dictCd!=null){
			PropertyFilter propertyFilterDictCd = new PropertyFilter("EQS_manInfoCd", dictCd);
			filters.add(propertyFilterDictCd);
		}
		
		filters.add(propertyFilter);
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("proposalCatCd");
			page.setOrder(Page.ASC);
		}
		page = oaManInfoManager.findPage(page, filters);
		*/
		
		page.setPageSize(20);
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = buildHql(params);
		page = oaManInfoManager.findPage(page, hql, params);
		
		return "load";
	}
	
	/**
	 * 留言
	 * @return
	 * @throws Exception
	 */
	public String say() throws Exception {
		String content = Struts2Utils.getParameter("content");
		if (StringUtils.isNotEmpty(content)) {
			OaManInfoMessage message = new OaManInfoMessage();
			message.setMsgContent(content);
			message.setOaManInfo(entity);
			oaManInfoMessageManager.saveOaManInfoMessage(message);
			// entity = resApproveInfoManager.getEntity(getId());
		}
		return "say";
	}
	public void prepareSay() throws Exception {
		prepareModel();
	}
	
	
	
	//构造搜索语句
	private String buildHql(Map<String, Object> params) {

		if(StringUtils.isNotBlank(filter_EQS_statusCd) 
				|| StringUtils.isNotBlank(filter_LIKES_appSerialNo) 
				|| StringUtils.isNotBlank(filter_EQS_applyUserCd) 
				|| StringUtils.isNotBlank(filter_EQS_approveUserCd) 
				|| StringUtils.isNotBlank(filter_LTD_startDate) 
				|| StringUtils.isNotBlank(filter_GED_startDate) 
				|| StringUtils.isNotBlank(filter_LIKES_titleName) 
				|| StringUtils.isNotBlank(dictCd)){
		}
		
		StringBuffer hql = new StringBuffer(" from OaManInfo as t where 1=1 ");
		
		// 申请类型
		if(StringUtils.isNotBlank(dictCd)){
			hql.append(" and t.manInfoCd = :manInfoCd ");
			params.put("manInfoCd", dictCd);
		} 
		
		// 申请状态,默认搜索不显示"驳回"记录
		if(StringUtils.isNotBlank(filter_EQS_statusCd)){
			if(OA_MAN_STATUS_REJECT.equals(filter_EQS_statusCd)){
				hql.append(" and t.statusCd = :rejectStatusCd ");
				params.put("rejectStatusCd", OA_MAN_STATUS_REJECT);
			}else{
				if(OA_MAN_STATUS_MYDEAL.equals(filter_EQS_statusCd)
						|| OA_MAN_STATUS_MYDUTY.equals(filter_EQS_statusCd)){
					
				}else{
					hql.append(" and t.statusCd = :statusCd ");
					params.put("statusCd", filter_EQS_statusCd);
				}
			}
		} 
		
		// 审批编号
		if(StringUtils.isNotBlank(filter_LIKES_appSerialNo)){
			int idx = filter_LIKES_appSerialNo.lastIndexOf("_");
			if(idx != -1){
				String manInfoCdTmp = filter_LIKES_appSerialNo.substring(0, filter_LIKES_appSerialNo.lastIndexOf("_"));
				String serialNoTmp = filter_LIKES_appSerialNo.substring(filter_LIKES_appSerialNo.lastIndexOf("_") + 1);
			
				if (NumberUtils.isNumber(serialNoTmp)) {
					manInfoCdTmp = StringUtils.trim(manInfoCdTmp);
					hql.append(" and t.manInfoCd like :manInfoCd ");
					params.put("manInfoCd", "%" + manInfoCdTmp + "%");
					
					hql.append("  and t.seriaNo = :seriaNo ");
					params.put("seriaNo", Long.valueOf(serialNoTmp));
				}else{
					hql.append(" and t.manInfoCd like :manInfoCd ");
					params.put("manInfoCd", "%" + filter_LIKES_appSerialNo + "%");
				}
			}else{
				hql.append(" and t.manInfoCd like :manInfoCd ");
				params.put("manInfoCd", "%" + filter_LIKES_appSerialNo + "%");
			}
		} 
		
		// 发起人
		if(StringUtils.isNotBlank(filter_EQS_applyUserCd)){
			hql.append(" and t.creator = :creator ");
			params.put("creator", filter_EQS_applyUserCd);
		} 

		// 审批人
//		if(StringUtils.isNotBlank(filter_EQS_approveUserCd)){
//			//hql.append(" and exists(select 1 from OaManInfoNode t2 where t2.oaManInfoId = t.oaManInfoId and t2.userCd = :chiefUserUiid) ");
//			hql.append(" and t.approveUserCd like :approveUserCd ");
//			params.put("approveUserCd", "%"+filter_EQS_approveUserCd+",%");
//		} 

		// 发起时间
		if(StringUtils.isNotBlank(filter_GED_startDate)){
			hql.append("and t.createdDate >= :startDateFrom ");
			Date startDateFrom = DateOperator.parse(filter_GED_startDate);
			params.put("startDateFrom", startDateFrom);
		} 
		if(StringUtils.isNotBlank(filter_LTD_startDate)){
			hql.append("and t.createdDate <= :startDateTo ");
			Date startDateTo = DateOperator.addDate(DateOperator.parse(filter_LTD_startDate,"yyyy-MM-dd"),1);
			params.put("startDateTo", startDateTo);
		} 

		// 标题
		if(StringUtils.isNotBlank(filter_LIKES_titleName)){
			hql.append(" and t.remark like :titleName ");
			params.put("titleName", "%"+filter_LIKES_titleName+"%");
		} 

		
		// 我审批/同意 
		if (OA_MAN_STATUS_MYDEAL.equals(filter_EQS_statusCd)){
			hql.append(" and exists (select 1 from OaManInfoNode t2 where t2.oaManInfo.oaManInfoId = t.oaManInfoId and t2.userCd = :curUserCd) ");
			params.put("curUserCd", SpringSecurityUtils.getCurrentUiid());
		}
		// 我经手(我申请/审批/同意/驳回)
		else if (OA_MAN_STATUS_MYDUTY.equals(filter_EQS_statusCd)){
			hql.append(" and (");
			hql.append("   exists (select 1 from OaManInfoNode t2 where t2.oaManInfo.oaManInfoId = t.oaManInfoId and t2.userCd = :curUserCd) ");
			hql.append("   or  t.creator = :myCreator or t.approveUserCd like :myApproveUserCd or t.approveUserCd = :myApproveUserCdOne");
			hql.append(" )");

			params.put("curUserCd", SpringSecurityUtils.getCurrentUiid());
			params.put("myCreator", SpringSecurityUtils.getCurrentUiid());
			params.put("myApproveUserCd", "%"+SpringSecurityUtils.getCurrentUiid()+";%");
			params.put("myApproveUserCdOne", SpringSecurityUtils.getCurrentUiid());
			
		}
		else{
			//若无发起人,默认发起人是自己
			hql.append(" and (t.creator = :myCreator or t.approveUserCd like :myApproveUserCd or t.approveUserCd = :myApproveUserCdOne) ");
			if(!OA_MAN_STATUS_REJECT.equals(filter_EQS_statusCd) || filter_EQS_statusCd.equals("")){
				hql.append(" and t.statusCd <> :rejectStatusCd ");
				params.put("rejectStatusCd", OA_MAN_STATUS_REJECT);
			}
			params.put("myCreator", SpringSecurityUtils.getCurrentUiid());
			params.put("myApproveUserCd", "%"+SpringSecurityUtils.getCurrentUiid()+";%");
			params.put("myApproveUserCdOne", SpringSecurityUtils.getCurrentUiid());
		}
		
 		hql.append(" order by t.createdDate desc ");
		return hql.toString();
		
	}
	/**
	 * 申请**保存主表，并保存NODE表和MESSAGE表,根据主表的模块CD查找审批配置表，然后循环保存NODE表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepareApply(){
		if (StringUtils.isNotBlank(getId())) {
			entity = oaManInfoManager.getEntity(getId());
			dictCd = entity.getManInfoCd();
		} else {
			entity = new OaManInfo();
			entityTmpId = RandomUtils.generateTmpEntityId();
			dictCd = Struts2Utils.getParameter("dictCd");
		}
	}
	/**
	 * 申请
	 * @return
	 * @throws Exception
	 */
	public String apply() throws Exception {
		entity.setStatusCd("1");
		entity.setSeriaNo(oaManInfoManager.getMaxSeriaNo(entity.getManInfoCd()));
		String manInfoCd = entity.getManInfoCd();
		List<ResApproveStep>  resApproveStep = null;
		/*得到分类信息*/
		ResAuthType resAuthType = oaManInfoManager.getResAuthType(manInfoCd);
		if(resAuthType != null){
			List<ResConditonType> listResConditonType = resAuthType.getResConditonTypes();
			for(int i=0;i<listResConditonType.size();i++){
				ResConditonType  resConditonType  = listResConditonType.get(i);
				resApproveStep = resConditonType.getResApproveSteps();
			}
		}
			
	    if(resApproveStep.size()>0){
	    	ResApproveStep resApproveStepFirst	= resApproveStep.get(0);
	    	String nodeCd = resApproveStepFirst.getNodeCd();
	    	ResNode resNode = oaManInfoManager.getResNode(nodeCd);
			entity.setApproveUserCd(resNode.getNodeUser());
			entity.setNodeCd(resApproveStepFirst.getApproveLevel().toString());
	    }
		
		oaManInfoManager.saveResApproveInfo(entity, entityTmpId);
		
		for(int i=0;i<resApproveStep.size();i++){
			ResApproveStep resApproveStepFirst	= resApproveStep.get(i);
			OaManInfoNode oManInfoNode = new OaManInfoNode();
	    	String nodeCd = resApproveStepFirst.getNodeCd();
	    	ResNode resNode = oaManInfoManager.getResNode(nodeCd);
			oManInfoNode.setUserCd(resNode.getNodeUser());
			oManInfoNode.setUserName(CodeNameUtil.getUserNameByCd(resNode.getNodeUser()));
			oManInfoNode.setApproveLevel(resApproveStepFirst.getApproveLevel());
			oManInfoNode.setNodeCd(resApproveStepFirst.getNodeCd());
			oManInfoNode.setOaManInfo(entity);
			oManInfoNode.setApproveOptionCd("-1");
			oManInfoNode.setGroupNodeCd(resApproveStepFirst.getGroupNodeCd());
			oaManInfoManager.saveOaManInfoWithNode(oManInfoNode);
		}
		
//		oaManInfoManager.updateJbpmTask(entity);
		oaManInfoManager.sendEmail(entity, "合理化建议审批", "已经到您审批",entity.getApproveUserCd());
		return RELOAD;
	}
	

	public String buildTree() {
		String active = Struts2Utils.getParameter("active");
		String title = "合理化建议";
		List<ResModule> modules;
		List<ResAuthType> authTypes;
		if (StringUtils.equals(active, "1")) {
			modules = resModuleManager.loadActiveResModule(ResConstants.MODULE_TYPE_CD_HLH);
			authTypes = resAuthTypeManager.loadActiveResAuthType();
		} else {
			modules = resModuleManager.loadAllModule(ResConstants.MODULE_TYPE_CD_HLH);
			authTypes = resAuthTypeManager.getAll("sequenceNo", true);
		}
		TreePanelNode node = TreePanelUtilRes.buildProjectModuleTree(modules, authTypes,title);
		Struts2Utils.renderJson(node);
		return null;
	}
	
	
	public OaManInfo getModel() {
		return entity;
	}

	public String getDictCd() {
		return dictCd;
	}

	public void setDictCd(String dictCd) {
		this.dictCd = dictCd;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}
 
	public String getFilter_EQS_statusCd() {
		return filter_EQS_statusCd;
	}

	public void setFilter_EQS_statusCd(String filter_EQS_statusCd) {
		this.filter_EQS_statusCd = filter_EQS_statusCd;
	}

	public String getFilter_LIKES_appSerialNo() {
		return filter_LIKES_appSerialNo;
	}

	public void setFilter_LIKES_appSerialNo(String filter_LIKES_appSerialNo) {
		this.filter_LIKES_appSerialNo = filter_LIKES_appSerialNo;
	}

	public String getFilter_LIKES_applyUserName() {
		return filter_LIKES_applyUserName;
	}

	public void setFilter_LIKES_applyUserName(String filter_LIKES_applyUserName) {
		this.filter_LIKES_applyUserName = filter_LIKES_applyUserName;
	}
 
	public String getFilter_LIKES_approveUserName() {
		return filter_LIKES_approveUserName;
	}

	public void setFilter_LIKES_approveUserName(String filter_LIKES_approveUserName) {
		this.filter_LIKES_approveUserName = filter_LIKES_approveUserName;
	}
 
	public String getFilter_EQS_applyUserCd() {
		return filter_EQS_applyUserCd;
	}

	public void setFilter_EQS_applyUserCd(String filter_EQS_applyUserCd) {
		this.filter_EQS_applyUserCd = filter_EQS_applyUserCd;
	}

	public String getFilter_EQS_approveUserCd() {
		return filter_EQS_approveUserCd;
	}

	public void setFilter_EQS_approveUserCd(String filter_EQS_approveUserCd) {
		this.filter_EQS_approveUserCd = filter_EQS_approveUserCd;
	}

	public String getFilter_GED_startDate() {
		return filter_GED_startDate;
	}

	public void setFilter_GED_startDate(String filter_GED_startDate) {
		this.filter_GED_startDate = filter_GED_startDate;
	}

	public String getFilter_LTD_startDate() {
		return filter_LTD_startDate;
	}

	public void setFilter_LTD_startDate(String filter_LTD_startDate) {
		this.filter_LTD_startDate = filter_LTD_startDate;
	}

	public String getFilter_LIKES_titleName() {
		return filter_LIKES_titleName;
	}

	public void setFilter_LIKES_titleName(String filter_LIKES_titleName) {
		this.filter_LIKES_titleName = filter_LIKES_titleName;
	}

	public String getIsMyApprove() {
		return isMyApprove;
	}

	public void setIsMyApprove(String isMyApprove) {
		this.isMyApprove = isMyApprove;
	}

	public String getIsZcApprove() {
		return isZcApprove;
	}

	public void setIsZcApprove(String isZcApprove) {
		this.isZcApprove = isZcApprove;
	}

	public String getProposalCatCd() {
		return proposalCatCd;
	}

	public void setProposalCatCd(String proposalCatCd) {
		this.proposalCatCd = proposalCatCd;
	}
	
}
