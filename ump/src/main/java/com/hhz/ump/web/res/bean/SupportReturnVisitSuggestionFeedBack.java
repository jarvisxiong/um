package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>供方履约情况回访意见反馈汇总表</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-23
 */

public class SupportReturnVisitSuggestionFeedBack extends BaseTemplate {

	//南方区域
	private String southArea;
	
	//北方区域
	private String northArea;
	
	//上海区域
	private String shanghaiArea;
	
	//回访范围
	private List<SupportReturnVisitScope> supportReturnVisitScope = new ArrayList<SupportReturnVisitScope>();
	
	//反馈问题简述
	private String feedbackDesc;
	
	//附件
	private String attachmentes;

	/**
	 * @return the southArea
	 */
	public String getSouthArea() {
		return southArea;
	}

	/**
	 * @param southArea the southArea to set
	 */
	public void setSouthArea(String southArea) {
		this.southArea = southArea;
	}

	/**
	 * @return the northArea
	 */
	public String getNorthArea() {
		return northArea;
	}

	/**
	 * @param northArea the northArea to set
	 */
	public void setNorthArea(String northArea) {
		this.northArea = northArea;
	}

	/**
	 * @return the shanghaiArea
	 */
	public String getShanghaiArea() {
		return shanghaiArea;
	}

	/**
	 * @param shanghaiArea the shanghaiArea to set
	 */
	public void setShanghaiArea(String shanghaiArea) {
		this.shanghaiArea = shanghaiArea;
	}

	/**
	 * @return the supportReturnVisitScope
	 */
	public List<SupportReturnVisitScope> getSupportReturnVisitScope() {
		return supportReturnVisitScope;
	}

	/**
	 * @param supportReturnVisitScope the supportReturnVisitScope to set
	 */
	public void setSupportReturnVisitScope(List<SupportReturnVisitScope> supportReturnVisitScope) {
		this.supportReturnVisitScope = supportReturnVisitScope;
	}

	/**
	 * @return the feedbackDesc
	 */
	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	/**
	 * @param feedbackDesc the feedbackDesc to set
	 */
	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	/**
	 * @return the attachmentes
	 */
	public String getAttachmentes() {
		return attachmentes;
	}

	/**
	 * @param attachmentes the attachmentes to set
	 */
	public void setAttachmentes(String attachmentes) {
		this.attachmentes = attachmentes;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
