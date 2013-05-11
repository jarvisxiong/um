package com.hhz.ump.dao.oa;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.oa.OaManInfo;
import com.hhz.ump.entity.oa.OaManInfoNode;
import com.hhz.ump.entity.oa.OaManNode;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.ump.util.CodeNameUtil;

@Service
@Transactional
public class OaManInfoManager extends BaseService<OaManInfo, String> {
	@Autowired
	private OaManInfoDao oaManInfoDao;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private OaManInfoNodeManager oaManInfoNodeManager;
	@Autowired
	private JbpmTaskManager jbpmTaskManager;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	
	
	public void saveOaManInfo(OaManInfo oaManInfo) {
		PowerUtils.setEmptyStr2Null(oaManInfo);
		oaManInfoDao.save(oaManInfo);
	}

	public void saveResApproveInfo(OaManInfo oaManInfo, String entityTmpId) {
		PowerUtils.setEmptyStr2Null(oaManInfo);
		// genApproveCd(resApproveInfo);
		
		saveOaManInfo(oaManInfo);
		if (StringUtils.isNotEmpty(entityTmpId)) {
			appAttachFileManager.updateTmpFile(entityTmpId, OaManInfo.class.getSimpleName(), oaManInfo.getOaManInfoId());
		}
	}
	
	public void deleteOaManInfo(String id) {
		oaManInfoDao.delete(id);
		appAttachFileManager.deleteByBizEntityId(id,false);
	}
	
	@Override
	public HibernateDao<OaManInfo, String> getDao() {
		return oaManInfoDao;
	}
	/**
	 * 得到最大数字
	 * @param manInfoId
	 * @return
	 */
	public long getMaxSeriaNo(String manInfoId){
		long seriaNo = 0;
		String hql = "select max(seriaNo) from OaManInfo where manInfoCd='"+manInfoId+"'";
		List lstResult  = oaManInfoDao.find(hql);
		try {
			if (null != lstResult && 0 != lstResult.size()) {
				seriaNo = (Long) lstResult.get(0);
				seriaNo++;
			}
		} catch (Exception e) {
		}
		
		return seriaNo;
		
		
	} 
	/**
	 * 返回关系表信息
	 * @param manInfoCd
	 * @return
	 */
	public ResAuthType getResAuthType(String manInfoCd){
		String hql = "from ResAuthType where   authTypeCd='"+manInfoCd+"'";
		List<ResAuthType>  list = oaManInfoDao.find(hql);
		if(list!=null && list.size()>0)
			return (ResAuthType)list.get(0);
		else
			return null;
		
	}
	
	/**
	 * 返回节点表信息
	 * @param manInfoCd
	 * @return
	 */
	public ResNode getResNode(String nodeCd){
		String hql = "from ResNode where   nodeCd='"+nodeCd+"'";
		List<ResNode>  list = oaManInfoDao.find(hql);
		if(list!=null && list.size()>0)
			return (ResNode)list.get(0);
		else
			return null;
		
	}
	
	/**
	 * 返回审批节点信息表
	 * @param nodeCd
	 * @return
	 */
	public OaManNode getOaManNode(String nodeCd){
		String hql = "from OaManNode where nodeCd='"+nodeCd+"'";
		List<OaManNode> list = oaManInfoDao.find(hql);
		if(list == null || list.size() == 0)
			return null;
		else
			return (OaManNode)list.get(0);
	}
	public void saveOaManInfoWithNode(OaManInfoNode oaManInfoNode) {
		oaManInfoNodeManager.saveOaManInfoNode(oaManInfoNode);
	}
	
	/**
	 * 返回审批节点信息表(单节点)
	 * @param nodeCd
	 * @return
	 */
	public OaManInfoNode getOaManInfoNode(String oaManInfoNodeId,String nodeCd,String userCd){
		String hql = "from OaManInfoNode where approveLevel='"+nodeCd+"' and oaManInfo.oaManInfoId='"+oaManInfoNodeId+"' and userCd='"+userCd+"'";
		List<OaManInfoNode> list = oaManInfoDao.find(hql);
		
		if(list == null || list.size() == 0)
			return null;
		else
			return (OaManInfoNode)list.get(0);
	}
	/**
	 * 返回审批节点信息表(单节点)
	 * @param nodeCd
	 * @return
	 */
	public List<OaManInfoNode> getLastOaManInfoNode(String oaManInfoNodeId,String nodeCd,String userCd){
		String hql = "from OaManInfoNode where  approveOptionCd='-1' and  approveLevel='"+nodeCd+"' and oaManInfo.oaManInfoId='"+oaManInfoNodeId+"' and userCd !='"+userCd+"'";
		List<OaManInfoNode> list = oaManInfoDao.find(hql);
		
		if(list == null || list.size() == 0)
			return null;
		else
			return list;
	}
	
	
	
   /**
    * 增加待办事宜
    * @param oaManInfo
    */
	public void updateJbpmTask(OaManInfo oaManInfo) {
		JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(oaManInfo.getOaManInfoId());
		if (oaManInfo.getStatusCd().equals("1")) {
			if (jbpmTask == null) {
					jbpmTask = new JbpmTask();
					jbpmTask.setModuleCd("oaManInfo");
					jbpmTask.setModuleName("合理化建议");
					jbpmTask.setJbpmCd(oaManInfo.getManInfoCd() +"_"+ oaManInfo.getSeriaNo());
					jbpmTask.setUserCd(oaManInfo.getCreator());
					jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(oaManInfo.getCreator()));
					jbpmTask.setApplyDate(oaManInfo.getUpdatedDate());
					jbpmTask.setJbpmId(oaManInfo.getOaManInfoId());
					jbpmTask.setTaskId(oaManInfo.getProposalCatCd());
					jbpmTask.setRemark(oaManInfo.getRemark());
					jbpmTask.setStatusCd(oaManInfo.getStatusCd().toString());
					jbpmTaskManager.saveJbpmTask(jbpmTask);
			    }
		    	String[] users = oaManInfo.getApproveUserCd().split(";");
		    	if(users.length>0){
		    		for(int i=0;i<users.length;i++){
			    		jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
						JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
						jbpmTaskCandidate.setUserCd(users[i]);
						jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(oaManInfo.getApproveUserCd()));
						jbpmTaskCandidate.setJbpmTask(jbpmTask);
						jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
			    	}
		    	}
		    	
				
		}else{
			if (jbpmTask != null) {
				jbpmTaskManager.delete(jbpmTask);
			}
		}
	}
	
	
	
	
	/**
	 * 发送内部邮件
	 * @param oaManInfo
	 * @param title
	 * @param content
	 * @param userCd
	 */
	public void sendEmail(OaManInfo oaManInfo, String title, String content, String... userCd) {
		String approveNo = oaManInfo.getManInfoCd() +"_"+ oaManInfo.getSeriaNo();
		StringBuffer msg = new StringBuffer("合理化建议(编号:").append(approveNo).append(")").append(content);
		msg.append("<div style=\"width: 30px;cursor: pointer;text-decoration:underline;color:blue;\" onclick=\"parent.parent.showAll('").append(
				Struts2Utils.getRequest().getContextPath()).append("/oa/oa-man-info.action?id=").append(oaManInfo.getOaManInfoId())
				.append("&proposalCatCd=").append(oaManInfo.getProposalCatCd()).append("','oaManInfo');\">进入</div>");
		oaEmailBodyManager.sendData2Email(title, msg.toString(), "admin", "0", userCd);
	}
	
	

}

