package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>供方履约情况季度回访申请计划表</p>
 * 
 * @author  hy
 * @version 1.00 2011-9-23
 */

public class SupportQuarterReturnVisitPlanSheet extends BaseTemplate {

	
	//季度回访目的事项
	private String quarterReturnVisitMatter;
	
	//要求完成时间
	private String completeTime ;
	
	//其它说明
	private String otherDesc;
	
	//回访范围
	private List<SupportReturnVisitScope> supportReturnVisitScope = new ArrayList<SupportReturnVisitScope>();
	
	//附件上传
	private String attachmentes;

	/**
	 * @return the quarterReturnVisitMatter
	 */
	public String getQuarterReturnVisitMatter() {
		return quarterReturnVisitMatter;
	}

	/**
	 * @param quarterReturnVisitMatter the quarterReturnVisitMatter to set
	 */
	public void setQuarterReturnVisitMatter(String quarterReturnVisitMatter) {
		this.quarterReturnVisitMatter = quarterReturnVisitMatter;
	}

	/**
	 * @return the completeTime
	 */
	public String getCompleteTime() {
		return completeTime;
	}

	/**
	 * @param completeTime the completeTime to set
	 */
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	/**
	 * @return the otherDesc
	 */
	public String getOtherDesc() {
		return otherDesc;
	}

	/**
	 * @param otherDesc the otherDesc to set
	 */
	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
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
		return quarterReturnVisitMatter;
	}
	
	
}
