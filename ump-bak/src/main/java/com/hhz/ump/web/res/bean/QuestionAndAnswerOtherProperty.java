/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * @author baolm
 * 2011-04-12
 */
public class QuestionAndAnswerOtherProperty {
	
	/**
	 * 投标单位疑问
	 */
	private String bidUnitQuestion;

	/**
	 * 责任部门名称
	 */
	private String resDeptName;
	
	/**
	 * 责任部门
	 */
	private String resDeptCd;

	/**
	 * 责任部门回复
	 */
	private String resDeptOption;
	
	/**
	 * 区域公司成本管理部回复
	 */
	private String costDeptOption;
	
	/**
	 * 区域总经理回复
	 */
	private String regionManagerOption;
	
	/**
	 * 最终回复副总裁(同意/不同意)
	 */
	private String vicePresidentsOption;

	public String getBidUnitQuestion() {
		return bidUnitQuestion;
	}

	public void setBidUnitQuestion(String bidUnitQuestion) {
		this.bidUnitQuestion = bidUnitQuestion;
	}

	public String getResDeptName() {
		return resDeptName;
	}

	public void setResDeptName(String resDeptName) {
		this.resDeptName = resDeptName;
	}

	public String getResDeptCd() {
		return resDeptCd;
	}

	public void setResDeptCd(String resDeptCd) {
		this.resDeptCd = resDeptCd;
	}

	public String getResDeptOption() {
		return resDeptOption;
	}

	public void setResDeptOption(String resDeptOption) {
		this.resDeptOption = resDeptOption;
	}

	public String getCostDeptOption() {
		return costDeptOption;
	}

	public void setCostDeptOption(String costDeptOption) {
		this.costDeptOption = costDeptOption;
	}

	public String getRegionManagerOption() {
		return regionManagerOption;
	}

	public void setRegionManagerOption(String regionManagerOption) {
		this.regionManagerOption = regionManagerOption;
	}

	public String getVicePresidentsOption() {
		return vicePresidentsOption;
	}

	public void setVicePresidentsOption(String vicePresidentsOption) {
		this.vicePresidentsOption = vicePresidentsOption;
	}

}
