/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * <p>供方履约情况季度回访申请计划表</p>
 * 
 * @author  qlb
 * @version 1.00 2011-11-30
 */

public class ReviewApplySheet extends BaseTemplate {
	
	//项目	 
	private String projectName;
	/**
	 * 项目Cd
	 */
	private String projectCd;
	/**
	 * @return the projectCd
	 */
	public String getProjectCd() {
		return projectCd;
	}

	/**
	 * @param projectCd the projectCd to set
	 */
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	//考察供方类别	
	private String reviewSupportType;
	//考察要求	 
	private String reviewRequirement;
	//考察时间
	private String reviewDate;
	//考察部门及参与人员	
	private List<ReviewDeptAndPersons> reviewDeptAndPersons = new ArrayList<ReviewDeptAndPersons>();
	//考察按排
    private List<ReviewArrange> reviewArranges=new ArrayList<ReviewArrange>();
	

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the reviewSupportType
	 */
	public String getReviewSupportType() {
		return reviewSupportType;
	}

	/**
	 * @param reviewSupportType the reviewSupportType to set
	 */
	public void setReviewSupportType(String reviewSupportType) {
		this.reviewSupportType = reviewSupportType;
	}

	/**
	 * @return the reviewRequirement
	 */
	public String getReviewRequirement() {
		return reviewRequirement;
	}

	/**
	 * @param reviewRequirement the reviewRequirement to set
	 */
	public void setReviewRequirement(String reviewRequirement) {
		this.reviewRequirement = reviewRequirement;
	}

	/**
	 * @return the reviewDate
	 */
	public String getReviewDate() {
		return reviewDate;
	}

	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @return the reviewDeptAndPersons
	 */
	public List<ReviewDeptAndPersons> getReviewDeptAndPersons() {
		return reviewDeptAndPersons;
	}

	/**
	 * @param reviewDeptAndPersons the reviewDeptAndPersons to set
	 */
	public void setReviewDeptAndPersons(
			List<ReviewDeptAndPersons> reviewDeptAndPersons) {
		this.reviewDeptAndPersons = reviewDeptAndPersons;
	}

	/**
	 * @return the reviewArrange
	 */
	public List<ReviewArrange> getReviewArranges() {
		return reviewArranges;
	}

	/**
	 * @param reviewArrange the reviewArrange to set
	 */
	public void setReviewArranges(List<ReviewArrange> reviewArrange) {
		this.reviewArranges = reviewArrange;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}

}
