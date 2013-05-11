package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContAddAgreement;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 标后核对费用核定单
 * @author Administrator
 *
 */
public class AfterFeeCheck extends BaseContLedgerTemplate {
	/**
	 * 合同编号
	 */
	private String contNo;
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
	 * 期
	 */
	private String period;

	/**
	 * 面积
	 */
	private String area;
	/**
	 * 核对内容
	 */
	private String checkContent;
	/**
	 * 暂定合同总价(元)(对应核对内容)
	 */
	private String temporaryPrice;
	/**
	 * 原合同单位指标(元/m2)
	 */
	private String contTarget;
	/**
	 * 施工单位上报金额
	 */
	private String projAppMoney;
	/**
	 * 相应单位指标
	 */
	private String relProjTarget;
	/**
	 * 施工单位上报时间
	 */
	private String projAppTime;
	/**
	 * 咨询机构上报金额
	 */
	private String instAppMoney;
	/**
	 * 相应单位指标
	 */
	private String relInstTarget;
	/**
	 * 核减率:咨询机构
	 */
	private String cutInstRate;
	/**
	 * 区域公司核对金额
	 */
	private String compCheckMoney;
	/**
	 * 相应单位指标
	 */
	private String relComTarget;
	/**
	 * 核减率
	 */
	private String cutComRate;
	/**
	 * 目标成本金额
	 */
	private String targetCostMoney;
	/**
	 * 相应单位指标
	 */
	private String relCostTarget;
	/**
	 * 差异率
	 */
	private String differRate;
	/**
	 * 编制说明
	 */
	private String orgExplan;
	/**
	 * 原合同、标后核对差异说明
	 */
	private String differExplan;
	/**
	 * 核对的图纸清单目录
	 */
	private String checkGraphListId;
	/**
	 * 单位工程预算汇总和指标表
	 */
	private String projBudgetId;
	/**
	 * 原合同、标后核对差异附表
	 */
	private String origCheckDiffId;
	/**
	 * 地产公司审核意见汇总
	 */
	private String easteAuditId;
	/**
	 * 甲供料材料设备数量表
	 */
	private String materialNumId;
	/**
	 * 集团核定费用(元)
	 */
	private String groupMoney;
	/**
	 * 合同台账存放的网批CD
	 */
	private String resApproveCd;
	/**
	 * 合同台账存放的网批ID
	 */
	private String resApproveId;
	
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getTemporaryPrice() {
		return temporaryPrice;
	}

	public void setTemporaryPrice(String temporaryPrice) {
		this.temporaryPrice = temporaryPrice;
	}

	public String getContTarget() {
		return contTarget;
	}

	public void setContTarget(String contTarget) {
		this.contTarget = contTarget;
	}

	public String getProjAppMoney() {
		return projAppMoney;
	}

	public void setProjAppMoney(String projAppMoney) {
		this.projAppMoney = projAppMoney;
	}

	public String getRelProjTarget() {
		return relProjTarget;
	}

	public void setRelProjTarget(String relProjTarget) {
		this.relProjTarget = relProjTarget;
	}

	public String getProjAppTime() {
		return projAppTime;
	}

	public void setProjAppTime(String projAppTime) {
		this.projAppTime = projAppTime;
	}

	public String getInstAppMoney() {
		return instAppMoney;
	}

	public void setInstAppMoney(String instAppMoney) {
		this.instAppMoney = instAppMoney;
	}

	public String getRelInstTarget() {
		return relInstTarget;
	}

	public void setRelInstTarget(String relInstTarget) {
		this.relInstTarget = relInstTarget;
	}

	public String getCutInstRate() {
		return cutInstRate;
	}

	public void setCutInstRate(String cutInstRate) {
		this.cutInstRate = cutInstRate;
	}

	public String getCompCheckMoney() {
		return compCheckMoney;
	}

	public void setCompCheckMoney(String compCheckMoney) {
		this.compCheckMoney = compCheckMoney;
	}

	public String getRelComTarget() {
		return relComTarget;
	}

	public void setRelComTarget(String relComTarget) {
		this.relComTarget = relComTarget;
	}

	public String getCutComRate() {
		return cutComRate;
	}

	public void setCutComRate(String cutComRate) {
		this.cutComRate = cutComRate;
	}

	public String getTargetCostMoney() {
		return targetCostMoney;
	}

	public void setTargetCostMoney(String targetCostMoney) {
		this.targetCostMoney = targetCostMoney;
	}

	public String getRelCostTarget() {
		return relCostTarget;
	}

	public void setRelCostTarget(String relCostTarget) {
		this.relCostTarget = relCostTarget;
	}

	public String getDifferRate() {
		return differRate;
	}

	public void setDifferRate(String differRate) {
		this.differRate = differRate;
	}

	public String getOrgExplan() {
		return orgExplan;
	}

	public void setOrgExplan(String orgExplan) {
		this.orgExplan = orgExplan;
	}

	public String getDifferExplan() {
		return differExplan;
	}

	public void setDifferExplan(String differExplan) {
		this.differExplan = differExplan;
	}

	public String getCheckGraphListId() {
		return checkGraphListId;
	}

	public void setCheckGraphListId(String checkGraphListId) {
		this.checkGraphListId = checkGraphListId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getOrigCheckDiffId() {
		return origCheckDiffId;
	}

	public void setOrigCheckDiffId(String origCheckDiffId) {
		this.origCheckDiffId = origCheckDiffId;
	}

	public String getEasteAuditId() {
		return easteAuditId;
	}

	public void setEasteAuditId(String easteAuditId) {
		this.easteAuditId = easteAuditId;
	}

	public String getMaterialNumId() {
		return materialNumId;
	}

	public void setMaterialNumId(String materialNumId) {
		this.materialNumId = materialNumId;
	}

	public String getGroupMoney() {
		return groupMoney;
	}

	public void setGroupMoney(String groupMoney) {
		this.groupMoney = groupMoney;
	}

	public String getProjBudgetId() {
		return projBudgetId;
	}

	public void setProjBudgetId(String projBudgetId) {
		this.projBudgetId = projBudgetId;
	}


	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContAddAgreement contAddAgreement =new ContAddAgreement();
		contAddAgreement.setContent(checkContent);
		ResApproveInfo approveInfo=getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		resApproveId =approveInfo.getResApproveInfoId();
		BigDecimal tempPrice =new BigDecimal(FormatUtil.formatDouble(temporaryPrice));//暂定价格
		BigDecimal groupCheckFee =new BigDecimal(FormatUtil.formatDouble(groupMoney));//集团核定费用
		contAddAgreement.setContTempMoney(tempPrice);
		contAddAgreement.setGroupCheckFee(groupCheckFee);
		contAddAgreement.setResApproveId(resApproveId);
		if(tempPrice!=null&&groupCheckFee!=null){
			contAddAgreement.setAmountUpdate(groupCheckFee.subtract(tempPrice));
		}
		contAddAgreement.setResApproveCd(resApproveCd);
		ledgerManager.updateContByRes(getContLedgerId(),resApproveCd, null, null, contAddAgreement, null);
	}

	@Override
	public boolean isAutoImport() {
		return false;
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

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contName;
	}

}
