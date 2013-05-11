/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**工程缺陷改造
 * @author huangjian
 *
 * 2011-8-26
 */
public class ProjectModifyGcqx extends BaseTemplatePay {
	private String feeType1;//维保期，地产公司承担费用
	private String feeType2;//营运期，资本性支出
	private String modifyType1;//设计外观、效果改造
	private String modifyType2;//其他
	private String modifyType3;//施工单位未能按照维保协议履行维保义务,由酒店公司自行安排
	public String getFeeType1() {
		return feeType1;
	}
	public void setFeeType1(String feeType1) {
		this.feeType1 = feeType1;
	}
	public String getFeeType2() {
		return feeType2;
	}
	public void setFeeType2(String feeType2) {
		this.feeType2 = feeType2;
	}
	public String getModifyType1() {
		return modifyType1;
	}
	public void setModifyType1(String modifyType1) {
		this.modifyType1 = modifyType1;
	}
	public String getModifyType2() {
		return modifyType2;
	}
	public void setModifyType2(String modifyType2) {
		this.modifyType2 = modifyType2;
	}
	

	//商业工程改造审批表
	//项目名称
	private String itemName;
	//工程名称
	private String projectName;
	//预估工程费用
	private String preProjectFee;
	//工程改造内容及原因
	private String transformCause;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return itemName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPreProjectFee() {
		return preProjectFee;
	}

	public void setPreProjectFee(String preProjectFee) {
		this.preProjectFee = preProjectFee;
	}

	public String getTransformCause() {
		return transformCause;
	}

	public void setTransformCause(String transformCause) {
		this.transformCause = transformCause;
	}
	/**
	 * @return the modifyType3
	 */
	public String getModifyType3() {
		return modifyType3;
	}
	/**
	 * @param modifyType3 the modifyType3 to set
	 */
	public void setModifyType3(String modifyType3) {
		this.modifyType3 = modifyType3;
	}

}
