package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 设计费用变更审批表
 * @author huangjian
 *
 */
public class DesignFeeVisaApprove extends BaseTemplate implements IAutoImport {

	/**
	 * 合同台账id
	 */
	private String contLedgerId;

	public String getContLedgerId() {
		return contLedgerId;
	}

	public void setContLedgerId(String contLedgerId) {
		this.contLedgerId = contLedgerId;
	}
	/**
	 * 重大设计变更
	 */
	private String importDesignVisa;
	/**
	 * 其他设计变更
	 */
	private String guraDesignVisa;
	/**
	 * 其他
	 */
	private String otherVisa;
	private String changeRate1;//累计增加金额/原合同金额≤5%
	private String changeRate2;//5%<累计增加金额/原合同金额≤20%
	private String changeRate3;//20%<累计增加金额/原合同金额
	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 变更编号
	 */
	private String visaNo;
	/**
	 * 合同名称
	 */
	private String contName;
	/**
	 * 乙方
	 */
	private String partB;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 设计变更内容
	 */
	private String visaContent;
	
	private String visaReason1;//招商/销售原因业态改变
	private String visaReason2;//市场定位改变
	private String visaReason3;//设计范围调整
	private String visaReason4;//设计条件改变
	private String visaReason5;//产品设计风格调整
	private String visaReason6;//法律法规调整
	private String visaReason7;//其它
	/**
	 * 拟签发联系单编号
	 */
	private String issueVisaNo;
	/**
	 * 合同总价
	 */
	private String totalPrice;
	/**
	 * 已确认合同总价
	 */
	private String updateTotal;
	/**
	 * 已累计变更预估费用（元）
	 */
	private String prepareFeeTotal;
	/**
	 * 已预估变更比例
	 */
	private String preFeeTotRate;
	/**
	 * 本次预估费用（元）
	 */
	private String prepareFee;
	/**
	 * 本次预估变更比例
	 */
	private String preFeeRate;
	/**
	 * 本次设计费用增加后预估合同总价（元）
	 */
	private String curVisaFeeAdd;
	/**
	 * 本次设计费用增加后费用变更比例%
	 */
	private String curVisaFeeAddRate;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 设计调整决策决议纪要
	 */
	private String issueVisaDrawId;
	/**
	 * 设计费用调整预算
	 */
	private String updateBudgetId;
	/**
	 * 提出部门的文件
	 */
	private String adjureFileId;
	public String getImportDesignVisa() {
		return importDesignVisa;
	}
	public void setImportDesignVisa(String importDesignVisa) {
		this.importDesignVisa = importDesignVisa;
	}
	public String getGuraDesignVisa() {
		return guraDesignVisa;
	}
	public void setGuraDesignVisa(String guraDesignVisa) {
		this.guraDesignVisa = guraDesignVisa;
	}
	public String getOtherVisa() {
		return otherVisa;
	}
	public void setOtherVisa(String otherVisa) {
		this.otherVisa = otherVisa;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getVisaNo() {
		return visaNo;
	}
	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}
	public String getContName() {
		return contName;
	}
	public void setContName(String contName) {
		this.contName = contName;
	}
	public String getPartB() {
		return partB;
	}
	public void setPartB(String partB) {
		this.partB = partB;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getVisaContent() {
		return visaContent;
	}
	public void setVisaContent(String visaContent) {
		this.visaContent = visaContent;
	}
	public String getVisaReason1() {
		return visaReason1;
	}
	public void setVisaReason1(String visaReason1) {
		this.visaReason1 = visaReason1;
	}
	public String getVisaReason2() {
		return visaReason2;
	}
	public void setVisaReason2(String visaReason2) {
		this.visaReason2 = visaReason2;
	}
	public String getVisaReason3() {
		return visaReason3;
	}
	public void setVisaReason3(String visaReason3) {
		this.visaReason3 = visaReason3;
	}
	public String getVisaReason4() {
		return visaReason4;
	}
	public void setVisaReason4(String visaReason4) {
		this.visaReason4 = visaReason4;
	}
	public String getVisaReason5() {
		return visaReason5;
	}
	public void setVisaReason5(String visaReason5) {
		this.visaReason5 = visaReason5;
	}
	public String getVisaReason6() {
		return visaReason6;
	}
	public void setVisaReason6(String visaReason6) {
		this.visaReason6 = visaReason6;
	}
	public String getVisaReason7() {
		return visaReason7;
	}
	public void setVisaReason7(String visaReason7) {
		this.visaReason7 = visaReason7;
	}
	public String getIssueVisaNo() {
		return issueVisaNo;
	}
	public void setIssueVisaNo(String issueVisaNo) {
		this.issueVisaNo = issueVisaNo;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUpdateTotal() {
		return updateTotal;
	}
	public void setUpdateTotal(String updateTotal) {
		this.updateTotal = updateTotal;
	}
	public String getPrepareFeeTotal() {
		return prepareFeeTotal;
	}
	public void setPrepareFeeTotal(String prepareFeeTotal) {
		this.prepareFeeTotal = prepareFeeTotal;
	}
	public String getPreFeeTotRate() {
		return preFeeTotRate;
	}
	public void setPreFeeTotRate(String preFeeTotRate) {
		this.preFeeTotRate = preFeeTotRate;
	}
	public String getPrepareFee() {
		return prepareFee;
	}
	public void setPrepareFee(String prepareFee) {
		this.prepareFee = prepareFee;
	}
	public String getPreFeeRate() {
		return preFeeRate;
	}
	public void setPreFeeRate(String preFeeRate) {
		this.preFeeRate = preFeeRate;
	}
	public String getCurVisaFeeAdd() {
		return curVisaFeeAdd;
	}
	public void setCurVisaFeeAdd(String curVisaFeeAdd) {
		this.curVisaFeeAdd = curVisaFeeAdd;
	}
	public String getCurVisaFeeAddRate() {
		return curVisaFeeAddRate;
	}
	public void setCurVisaFeeAddRate(String curVisaFeeAddRate) {
		this.curVisaFeeAddRate = curVisaFeeAddRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIssueVisaDrawId() {
		return issueVisaDrawId;
	}
	public void setIssueVisaDrawId(String issueVisaDrawId) {
		this.issueVisaDrawId = issueVisaDrawId;
	}
	public String getUpdateBudgetId() {
		return updateBudgetId;
	}
	public void setUpdateBudgetId(String updateBudgetId) {
		this.updateBudgetId = updateBudgetId;
	}
	public String getAdjureFileId() {
		return adjureFileId;
	}
	public void setAdjureFileId(String adjureFileId) {
		this.adjureFileId = adjureFileId;
	}
	@Override
	public String getResTitleName() {
		return contName;
	}
	public String getChangeRate1() {
		return changeRate1;
	}
	public void setChangeRate1(String changeRate1) {
		this.changeRate1 = changeRate1;
	}
	public String getChangeRate2() {
		return changeRate2;
	}
	public void setChangeRate2(String changeRate2) {
		this.changeRate2 = changeRate2;
	}
	public String getChangeRate3() {
		return changeRate3;
	}
	public void setChangeRate3(String changeRate3) {
		this.changeRate3 = changeRate3;
	}

	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContVisaUpdate visaUpdate =new ContVisaUpdate();
		visaUpdate.setContent(visaContent);
		ResApproveInfo approveInfo=getResApproveInfo();
		String resApproveCd=approveInfo.getApproveCd()+approveInfo.getSerialNo();
		String resApproveId =approveInfo.getResApproveInfoId();
		visaUpdate.setResApproveCd(resApproveCd);
		visaUpdate.setResApproveId(resApproveId);
		visaUpdate.setPrepareFee(new BigDecimal(PowerUtils.formatFloat(prepareFee)));//本次预估费用
		ledgerManager.updateContByRes(getContLedgerId(),resApproveCd, null, visaUpdate, null, null);
		
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
