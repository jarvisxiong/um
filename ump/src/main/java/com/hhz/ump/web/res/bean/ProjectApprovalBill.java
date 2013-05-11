package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 立项申请单-自由步骤
 * @author Administrator
 *
 */
public class ProjectApprovalBill extends BaseTemplate {
	private String feeName;//费用名称
	//预算类别
	private String budgetType1;//初步预算
	private String budgetType2;//精确费用(立即付款)
	//预算处理
	private String budgetDeal1;//年度预算内调剂
	private String budgetDeal2;//新增预算
	private String budgetMoney;//预算金额(元)
	//立项类别
	private String approvalType1;//法律事务
	private String approvalType2;//品牌推广
	private String approvalType3;//培训活动
	private String approvalType4;//会务组织
	private String approvalType5;//奖惩
	private String approvalType6;//计划方案变更
	private String approvalType7;//其他
	private String otherInfo;//其他信息
	
	private String remark;//申请事由
	
	@Override
	public String getResTitleName() {
		return feeName;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getBudgetType1() {
		return budgetType1;
	}

	public void setBudgetType1(String budgetType1) {
		this.budgetType1 = budgetType1;
	}

	public String getBudgetType2() {
		return budgetType2;
	}

	public void setBudgetType2(String budgetType2) {
		this.budgetType2 = budgetType2;
	}

	public String getBudgetDeal1() {
		return budgetDeal1;
	}

	public void setBudgetDeal1(String budgetDeal1) {
		this.budgetDeal1 = budgetDeal1;
	}

	public String getBudgetDeal2() {
		return budgetDeal2;
	}

	public void setBudgetDeal2(String budgetDeal2) {
		this.budgetDeal2 = budgetDeal2;
	}

	public String getBudgetMoney() {
		return budgetMoney;
	}

	public void setBudgetMoney(String budgetMoney) {
		this.budgetMoney = budgetMoney;
	}

	public String getApprovalType1() {
		return approvalType1;
	}

	public void setApprovalType1(String approvalType1) {
		this.approvalType1 = approvalType1;
	}

	public String getApprovalType2() {
		return approvalType2;
	}

	public void setApprovalType2(String approvalType2) {
		this.approvalType2 = approvalType2;
	}

	public String getApprovalType3() {
		return approvalType3;
	}

	public void setApprovalType3(String approvalType3) {
		this.approvalType3 = approvalType3;
	}

	public String getApprovalType4() {
		return approvalType4;
	}

	public void setApprovalType4(String approvalType4) {
		this.approvalType4 = approvalType4;
	}

	public String getApprovalType5() {
		return approvalType5;
	}

	public void setApprovalType5(String approvalType5) {
		this.approvalType5 = approvalType5;
	}

	public String getApprovalType6() {
		return approvalType6;
	}

	public void setApprovalType6(String approvalType6) {
		this.approvalType6 = approvalType6;
	}

	public String getApprovalType7() {
		return approvalType7;
	}

	public void setApprovalType7(String approvalType7) {
		this.approvalType7 = approvalType7;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}