package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 询标问卷审批表
 * @author baolm
 *
 * 2011-04-20
 */
public class BidQuestionnaireApprove extends BaseTemplate {
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * 招标文件编号
	 */
	private String inviteBidFileCd;
	
	/**
	 * 参加答疑单位
	 */
	private String accessUnit;
	
	/**
	 * 询标回复时间
	 */
	private String bidAnswerDate;
	
	/**
	 * 询标附件页数
	 */
	private String bidAttachmentPage;
	
	/**
	 * 相关附件下载
	 */
	private String questionnaireAttachmentId;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getInviteBidFileCd() {
		return inviteBidFileCd;
	}

	public void setInviteBidFileCd(String inviteBidFileCd) {
		this.inviteBidFileCd = inviteBidFileCd;
	}

	public String getAccessUnit() {
		return accessUnit;
	}

	public void setAccessUnit(String accessUnit) {
		this.accessUnit = accessUnit;
	}

	public String getBidAnswerDate() {
		return bidAnswerDate;
	}

	public void setBidAnswerDate(String bidAnswerDate) {
		this.bidAnswerDate = bidAnswerDate;
	}

	public String getBidAttachmentPage() {
		return bidAttachmentPage;
	}

	public void setBidAttachmentPage(String bidAttachmentPage) {
		this.bidAttachmentPage = bidAttachmentPage;
	}

	public String getQuestionnaireAttachmentId() {
		return questionnaireAttachmentId;
	}

	public void setQuestionnaireAttachmentId(String questionnaireAttachmentId) {
		this.questionnaireAttachmentId = questionnaireAttachmentId;
	}

	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		return engineeringName;
	}

}
