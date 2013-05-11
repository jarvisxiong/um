/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * @author baolm
 * 2011-04-12
 */
public class ContractItemOtherProperty {
	
	/**
	 * 条款项
	 */
	private String itemNo;

	/**
	 * 条款内容
	 */
	private String itemContent;

	/**
	 * 乙方反馈意见 feedback
	 */
	private String sideBFeedback;

	/**
	 * 责任部门 responsibility
	 */
	private String resDeptCd;

	/**
	 * 责任部门意见
	 */
	private String resDeptOption;

	/**
	 * 成本意见
	 */
	private String costCenterOption;
	
	/**
	 * 最终意见副总裁 vice-presidents
	 */
	private String vicePresidentsOption;

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public String getSideBFeedback() {
		return sideBFeedback;
	}

	public void setSideBFeedback(String sideBFeedback) {
		this.sideBFeedback = sideBFeedback;
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

	public String getCostCenterOption() {
		return costCenterOption;
	}

	public void setCostCenterOption(String costCenterOption) {
		this.costCenterOption = costCenterOption;
	}

	public String getVicePresidentsOption() {
		return vicePresidentsOption;
	}

	public void setVicePresidentsOption(String vicePresidentsOption) {
		this.vicePresidentsOption = vicePresidentsOption;
	}

}
