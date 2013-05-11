package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContVisaUpdateManager;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 变更费用核定单
 * @author Administrator
 *
 */
public class UpdateFeeCheck extends BaseContLedgerTemplate {

	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 费用核定编号  ？是否有用
	 */
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
	 * 变更编号
	 */
	private String visaNo;
	/**
	 * 变更内容
	 */
	private String visaContent;
	private String visaCause;//变更原因
	private String designWrong;//设计错漏  变更原因
	private String saleCause;//招商、销售原因
	private String constructCause;//施工原因
	private String partACause;//甲方原因
	private String otherCause;//其它
	private String period;
	/**
	 * 合同总价
	 */
	private String totalPrice;
	/**
	 * 已确认合同总价
	 */
	private String updateTotal;
	/**
	 * 已累计变更额（元）
	 */
	private String amountUpdateTotal;
	/**
	 * 已变更比例
	 */
	private String amountUpdateRate;
	/**
	 * 预估费用（元）——已审批预算
	 */
	private String prepareFee;
	/**
	 * 施工单位上报费用（元）
	 */
	private String workFee;
	/**
	 * 地产公司审核费用（元）
	 */
	private String projectAuditFee;
	/**
	 * 本次变更比例%
	 */
	private String updateRate;
	/**
	 * 签证变更ID
	 */
	private String visaNoId;
	/**
	 * 核价编制说明
	 */
	private String contItem;//合同条款
	private String contWay;//合同条款第N种方法
	private String contPrice;//合同综合单价
	private String refeContPrice;//参考合同综合单价
	private String fixedPrice;//定额计价
	private String lowerRate;//合同约定下浮率
	private String haveOtherFee;//无扣减其它费用
	private String noOtherFee;//有扣减其它费用
	private String preHaveOtherFee;//已同时上报《扣减费用核定单》:无
	private String preNoOtherFee;//已同时上报《扣减费用核定单》：有
	private String otherDeclare;//其它说明
	/**
	 * 地产公司成本部审核预算
	 */
	private String projectAuditId;
	/**
	 * 施工单位预算
	 */
	private String constructFeeId;
	/**
	 * 设计变更审批表
	 */
	private String designVisaAppId;
	/**
	 * 设计变更图纸或现场签证工程量确认单
	 */
	private String visaCheckId;
	/**
	 * 集团核定费用(元)
	 */
	private String groupMoney;
	
	/**
	 * 累计签证超过目标成本的3%
	 */
	private String abovePercent3;
	
	/**
	 * 累计签证超过单项合同额5%且累计签证金额超过100万元
	 */
	private String abovePercent5;
	
	/**
	 * 设计变更审批表
	 */
	private String other;
	
	//设计变更审批表
	private String resApproveCd;
	private String resApproveTitleName;
	private String resApproveId;
	
	
	public String getAbovePercent3() {
		return abovePercent3;
	}

	public void setAbovePercent3(String abovePercent3) {
		this.abovePercent3 = abovePercent3;
	}

	public String getAbovePercent5() {
		return abovePercent5;
	}

	public void setAbovePercent5(String abovePercent5) {
		this.abovePercent5 = abovePercent5;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getLowerRate() {
		return lowerRate;
	}

	public void setLowerRate(String lowerRate) {
		this.lowerRate = lowerRate;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
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

	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	public String getVisaContent() {
		return visaContent;
	}

	public void setVisaContent(String visaContent) {
		this.visaContent = visaContent;
	}

	public String getVisaCause() {
		return visaCause;
	}

	public void setVisaCause(String visaCause) {
		this.visaCause = visaCause;
	}

	public String getDesignWrong() {
		return designWrong;
	}

	public void setDesignWrong(String designWrong) {
		this.designWrong = designWrong;
	}

	public String getSaleCause() {
		return saleCause;
	}

	public void setSaleCause(String saleCause) {
		this.saleCause = saleCause;
	}

	public String getConstructCause() {
		return constructCause;
	}

	public void setConstructCause(String constructCause) {
		this.constructCause = constructCause;
	}

	public String getPartACause() {
		return partACause;
	}

	public void setPartACause(String partACause) {
		this.partACause = partACause;
	}

	public String getOtherCause() {
		return otherCause;
	}

	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getAmountUpdateTotal() {
		return amountUpdateTotal;
	}

	public void setAmountUpdateTotal(String amountUpdateTotal) {
		this.amountUpdateTotal = amountUpdateTotal;
	}

	public String getAmountUpdateRate() {
		return amountUpdateRate;
	}

	public void setAmountUpdateRate(String amountUpdateRate) {
		this.amountUpdateRate = amountUpdateRate;
	}

	public String getPrepareFee() {
		return prepareFee;
	}

	public void setPrepareFee(String prepareFee) {
		this.prepareFee = prepareFee;
	}

	public String getWorkFee() {
		return workFee;
	}

	public void setWorkFee(String workFee) {
		this.workFee = workFee;
	}

	public String getProjectAuditFee() {
		return projectAuditFee;
	}

	public void setProjectAuditFee(String projectAuditFee) {
		this.projectAuditFee = projectAuditFee;
	}

	public String getUpdateRate() {
		return updateRate;
	}

	public void setUpdateRate(String updateRate) {
		this.updateRate = updateRate;
	}

	public String getContItem() {
		return contItem;
	}

	public void setContItem(String contItem) {
		this.contItem = contItem;
	}

	public String getContWay() {
		return contWay;
	}

	public void setContWay(String contWay) {
		this.contWay = contWay;
	}

	public String getContPrice() {
		return contPrice;
	}

	public void setContPrice(String contPrice) {
		this.contPrice = contPrice;
	}

	public String getRefeContPrice() {
		return refeContPrice;
	}

	public void setRefeContPrice(String refeContPrice) {
		this.refeContPrice = refeContPrice;
	}

	public String getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(String fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public String getHaveOtherFee() {
		return haveOtherFee;
	}

	public void setHaveOtherFee(String haveOtherFee) {
		this.haveOtherFee = haveOtherFee;
	}

	public String getNoOtherFee() {
		return noOtherFee;
	}

	public void setNoOtherFee(String noOtherFee) {
		this.noOtherFee = noOtherFee;
	}

	public String getPreHaveOtherFee() {
		return preHaveOtherFee;
	}

	public void setPreHaveOtherFee(String preHaveOtherFee) {
		this.preHaveOtherFee = preHaveOtherFee;
	}

	public String getPreNoOtherFee() {
		return preNoOtherFee;
	}

	public void setPreNoOtherFee(String preNoOtherFee) {
		this.preNoOtherFee = preNoOtherFee;
	}

	public String getOtherDeclare() {
		return otherDeclare;
	}

	public void setOtherDeclare(String otherDeclare) {
		this.otherDeclare = otherDeclare;
	}

	public String getProjectAuditId() {
		return projectAuditId;
	}

	public void setProjectAuditId(String projectAuditId) {
		this.projectAuditId = projectAuditId;
	}

	public String getConstructFeeId() {
		return constructFeeId;
	}

	public void setConstructFeeId(String constructFeeId) {
		this.constructFeeId = constructFeeId;
	}

	public String getDesignVisaAppId() {
		return designVisaAppId;
	}

	public void setDesignVisaAppId(String designVisaAppId) {
		this.designVisaAppId = designVisaAppId;
	}

	public String getVisaCheckId() {
		return visaCheckId;
	}

	public void setVisaCheckId(String visaCheckId) {
		this.visaCheckId = visaCheckId;
	}


	public String getGroupMoney() {
		return groupMoney;
	}

	public void setGroupMoney(String groupMoney) {
		this.groupMoney = groupMoney;
	}

	public String getVisaNoId() {
		return visaNoId;
	}

	public void setVisaNoId(String visaNoId) {
		this.visaNoId = visaNoId;
	}

	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContVisaUpdateManager visaManager = SpringContextHolder.getBean("contVisaUpdateManager");
		if(StringUtils.isNotBlank(visaNoId)){
			ContVisaUpdate contVisaUpdate =visaManager.getEntity(visaNoId);
			BigDecimal groupCheckFee =new BigDecimal(FormatUtil.formatDouble(groupMoney));//集团核定费用
			//+审批的ID
			ResApproveInfo approveInfo=getResApproveInfo();
			contVisaUpdate.setApproveCheckId(approveInfo.getResApproveInfoId());
			
			contVisaUpdate.setAmountUpdate(groupCheckFee);
			ledgerManager.updateContByRes(getContLedgerId(),"", null, contVisaUpdate, null, null);
		}
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getResTitleName() {
		return visaContent;
	}

	public String getResApproveCd() {
		return resApproveCd;
	}

	public void setResApproveCd(String resApproveCd) {
		this.resApproveCd = resApproveCd;
	}

	public String getResApproveTitleName() {
		return resApproveTitleName;
	}

	public void setResApproveTitleName(String resApproveTitleName) {
		this.resApproveTitleName = resApproveTitleName;
	}

	public String getResApproveId() {
		return resApproveId;
	}

	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}
}
