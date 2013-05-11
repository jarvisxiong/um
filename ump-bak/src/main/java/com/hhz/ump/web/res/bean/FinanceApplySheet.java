package com.hhz.ump.web.res.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 资金申请表
 * 
 * @author baolm
 *         2011-05-31
 */
@Entity
@Table(name="RESTMP_FINANCE_APPLY_SHEET")
public class FinanceApplySheet extends BaseTemplate {
	private String type1;// 全资公司之间
	private String type2;// 全资公司与合资公司之间
	private String unit; // 申请单位
	private String openAccount; // 开户行账户信息
	private String usage; // 资金用途
	private String subject; // 列支科目
	private String amount; // 资金数额(元)
	private String useDate; // 用款时间
	private String reason; // 申请事由
	private String remark; // 调拨路径描述
	private String resApproveCd;// 其他审批编号
	private String resApproveId;//其他审批ID
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}
	
	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	@Column(length = 4000)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Transient
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return usage;
	}

	public String getResApproveCd() {
		return resApproveCd;
	}

	public void setResApproveCd(String resApproveCd) {
		this.resApproveCd = resApproveCd;
	}

	public String getResApproveId() {
		return resApproveId;
	}

	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

}
