package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 答疑审批表
 * 
 * @author baolm 2011-04-12
 */
public class QuestionAndAnswerApprove extends BaseTemplate {
	
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
	private String bidFileCd;

	/**
	 * 答疑回复时间
	 */
	private String answerDate;

	/**
	 * 答疑附件页数
	 */
	private String answerAttachmentTotalPage;
	
	private List<QuestionAndAnswerOtherProperty> otherProperties = new ArrayList<QuestionAndAnswerOtherProperty>();

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

	public String getBidFileCd() {
		return bidFileCd;
	}

	public void setBidFileCd(String bidFileCd) {
		this.bidFileCd = bidFileCd;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	public String getAnswerAttachmentTotalPage() {
		return answerAttachmentTotalPage;
	}

	public void setAnswerAttachmentTotalPage(String answerAttachmentTotalPage) {
		this.answerAttachmentTotalPage = answerAttachmentTotalPage;
	}

	public List<QuestionAndAnswerOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(
			List<QuestionAndAnswerOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}
	
	@Override
	public String getResProjectName() {
		return projectName;
	}
	
	@Override
	public String getResTitleName() {
		return engineeringName;
	}
	
}
