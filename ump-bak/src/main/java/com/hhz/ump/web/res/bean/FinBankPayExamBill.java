/**
 * 
 */
package com.hhz.ump.web.res.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.res.ResBeanSpecialRecordManager;
import com.hhz.ump.entity.res.ResBeanSpecialRecord;
import com.hhz.ump.web.res.baseBean.BaseTemplatePay;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 银行付款审批单
 */
@Entity
@Table(name = "RESTMP_FIN_BANK_PAY_EXAM_BILL")
public class FinBankPayExamBill extends BaseTemplatePay implements IAutoImport {

	// 付款种类 payTypeCd,payTypeName 付款事由 payContentDesc
	// 付款金额 （大写） payAmtCapitalDesc （小写）payAmtSmallDesc
	// 凭证号码 voucherNo
	// 报销单编号 expenseAccountNo
	// 收款单位 全称 receiveUnitFullName 帐号 receiveAccountNo 开户银行 receiveBankName,receiveBankCd
	private String type1;// 全资公司之间
	private String type2;// 全资公司与合资公司之间
	private String payTypeName;
	private String payTypeCd;
	private String payContentDesc;
	private String payAmtCapitalDesc;
	private String payAmtSmallDesc;
	private String voucherNo;
	private String expenseAccountNo;
	private String receiveUnitName;
	private String receiveUnitCd;
	private String receiveAccountNo;
	private String receiveBankName;
	private String receiveBankCd;
	// 付款单位
	private String payDeptName;
	// 开户行账号
	private String payAccount;
	private String specialApproveBillId;// 特别费申请ID
	private String confirmFlg;//是否确认过 1-是 0/null-否 

	private String area1;//集团内
	private String area2;//集团外
	private String resDisplayNo;// 其他审批查询号
	private String resApproveId;//其他审批ID
	private String noApply;//无资产申请
	@Override
	@Transient
	public String getResTitleName() {
		return receiveUnitName;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getPayTypeCd() {
		return payTypeCd;
	}

	public void setPayTypeCd(String payTypeCd) {
		this.payTypeCd = payTypeCd;
	}

	@Column(length = 4000)
	public String getPayContentDesc() {
		return payContentDesc;
	}

	public void setPayContentDesc(String payContentDesc) {
		this.payContentDesc = payContentDesc;
	}

	public String getPayAmtCapitalDesc() {
		return payAmtCapitalDesc;
	}

	public void setPayAmtCapitalDesc(String payAmtCapitalDesc) {
		this.payAmtCapitalDesc = payAmtCapitalDesc;
	}

	public String getPayAmtSmallDesc() {
		return payAmtSmallDesc;
	}

	public void setPayAmtSmallDesc(String payAmtSmallDesc) {
		this.payAmtSmallDesc = payAmtSmallDesc;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getExpenseAccountNo() {
		return expenseAccountNo;
	}

	public void setExpenseAccountNo(String expenseAccountNo) {
		this.expenseAccountNo = expenseAccountNo;
	}

	public String getReceiveAccountNo() {
		return receiveAccountNo;
	}

	public void setReceiveAccountNo(String receiveAccountNo) {
		this.receiveAccountNo = receiveAccountNo;
	}

	public String getReceiveBankName() {
		return receiveBankName;
	}

	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}

	public String getReceiveBankCd() {
		return receiveBankCd;
	}

	public void setReceiveBankCd(String receiveBankCd) {
		this.receiveBankCd = receiveBankCd;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceiveUnitCd() {
		return receiveUnitCd;
	}

	public void setReceiveUnitCd(String receiveUnitCd) {
		this.receiveUnitCd = receiveUnitCd;
	}

	public String getPayDeptName() {
		return payDeptName;
	}

	public void setPayDeptName(String payDeptName) {
		this.payDeptName = payDeptName;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
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

	public String getSpecialApproveBillId() {
		return specialApproveBillId;
	}

	public void setSpecialApproveBillId(String specialApproveBillId) {
		this.specialApproveBillId = specialApproveBillId;
	}

	@Override
	public void doImport() throws Exception {
		if (StringUtils.isNotBlank(specialApproveBillId)) {
			// 银行付款单，完成后，自动将对应的特别费‘台账’状态置为‘同意付款’
			ResBeanSpecialRecordManager recordManager = SpringContextHolder.getBean("resBeanSpecialRecordManager");
			ResBeanSpecialRecord record = recordManager.getEntityByResId(specialApproveBillId);
			record.setStatusCd("1");// 状态：0-立项未同意付款、1-同意付款、2-已付款
			recordManager.saveResBeanSpecialRecord(record);
		}
	}
	@Transient
	@Override
	public boolean isAutoImport() {
		return true;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getResDisplayNo() {
		return resDisplayNo;
	}

	public void setResDisplayNo(String resDisplayNo) {
		this.resDisplayNo = resDisplayNo;
	}

	public String getResApproveId() {
		return resApproveId;
	}

	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}

	public String getNoApply() {
		return noApply;
	}

	public void setNoApply(String noApply) {
		this.noApply = noApply;
	}

	public String getConfirmFlg() {
		return confirmFlg;
	}

	public void setConfirmFlg(String confirmFlg) {
		this.confirmFlg = confirmFlg;
	}

}
