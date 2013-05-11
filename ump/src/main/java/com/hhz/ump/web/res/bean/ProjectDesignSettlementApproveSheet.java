/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 工程设计结算审批表 Bean
 * 
 * @author qilb 4/12/2012
 * 
 */
public class ProjectDesignSettlementApproveSheet extends BaseContLedgerTemplate {
	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 编号
	 */
	private String seriNo;

	/**
	 * 合同名称
	 */
	private String contName;

	/**
	 * 乙方(收款人)
	 */
	private String partB;

	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目cd
	 */
	private String projectCd;
	/**
	 * ( )期
	 */
	private String projectPeriod;

	/**
	 * 合同总价
	 */
	private String totalPrice;

	/**
	 * 合同性质
	 */
	private String contProperty;
	/**
	 * 乙方报审金额（元）
	 */
	private String partBReviewAmount;
	/**
	 *地产公司审核费用（元）
	 */
	private String landEstateCorpApproCost;
	/**
	 * 设计原因引起的变更造价
	 */
	private String designChangeCost;

	/**
	 * 对应工程造价
	 */
	private String projectCost;

	/**
	 * 设计变更率
	 */
	private String designChangeRate;
	/**
	 * 违约款
	 */
	private String defaultDeductCost;
	/**
	 * 设计成果验收、现场服务说明
	 */
	private String designResultCheck;

	/**
	 *设计成果审批表
	 */
	private String designResultApproveId;

	/**
	 * 设计变更统计表
	 */
	private String designChangeTotalId;
	/**
	 * 集团核定费用（元）
	 */
	private String settlementPrice;
	/**
	 * 甲供料
	 */
	private String clearPriceParta;

	/**
	 * @return the clearPriceParta
	 */
	public String getClearPriceParta() {
		return clearPriceParta;
	}

	/**
	 * @param clearPriceParta the clearPriceParta to set
	 */
	public void setClearPriceParta(String clearPriceParta) {
		this.clearPriceParta = clearPriceParta;
	}

	/**
	 * @return the settlementPrice
	 */
	public String getSettlementPrice() {
		return settlementPrice;
	}

	/**
	 * @param settlementPrice
	 *            the settlementPrice to set
	 */
	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	/**
	 * @return the contNo
	 */
	public String getContNo() {
		return contNo;
	}

	/**
	 * @param contNo
	 *            the contNo to set
	 */
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	/**
	 * @return the seriNo
	 */
	public String getSeriNo() {
		return seriNo;
	}

	/**
	 * @param seriNo
	 *            the seriNo to set
	 */
	public void setSeriNo(String seriNo) {
		this.seriNo = seriNo;
	}

	/**
	 * @return the contName
	 */
	public String getContName() {
		return contName;
	}

	/**
	 * @param contName
	 *            the contName to set
	 */
	public void setContName(String contName) {
		this.contName = contName;
	}

	/**
	 * @return the partB
	 */
	public String getPartB() {
		return partB;
	}

	/**
	 * @param partB
	 *            the partB to set
	 */
	public void setPartB(String partB) {
		this.partB = partB;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectCd
	 */
	public String getProjectCd() {
		return projectCd;
	}

	/**
	 * @param projectCd
	 *            the projectCd to set
	 */
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	/**
	 * @return the projectPeriod
	 */
	public String getProjectPeriod() {
		return projectPeriod;
	}

	/**
	 * @param projectPeriod
	 *            the projectPeriod to set
	 */
	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	/**
	 * @return the totalPrice
	 */
	public String getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the contProperty
	 */
	public String getContProperty() {
		return contProperty;
	}

	/**
	 * @param contProperty
	 *            the contProperty to set
	 */
	public void setContProperty(String contProperty) {
		this.contProperty = contProperty;
	}

	/**
	 * @return the partBReviewAmount
	 */
	public String getPartBReviewAmount() {
		return partBReviewAmount;
	}

	/**
	 * @param partBReviewAmount
	 *            the partBReviewAmount to set
	 */
	public void setPartBReviewAmount(String partBReviewAmount) {
		this.partBReviewAmount = partBReviewAmount;
	}

	/**
	 * @return the landEstateCorpApproCost
	 */
	public String getLandEstateCorpApproCost() {
		return landEstateCorpApproCost;
	}

	/**
	 * @param landEstateCorpApproCost
	 *            the landEstateCorpApproCost to set
	 */
	public void setLandEstateCorpApproCost(String landEstateCorpApproCost) {
		this.landEstateCorpApproCost = landEstateCorpApproCost;
	}

	/**
	 * @return the designChangeCost
	 */
	public String getDesignChangeCost() {
		return designChangeCost;
	}

	/**
	 * @param designChangeCost
	 *            the designChangeCost to set
	 */
	public void setDesignChangeCost(String designChangeCost) {
		this.designChangeCost = designChangeCost;
	}

	/**
	 * @return the projectCost
	 */
	public String getProjectCost() {
		return projectCost;
	}

	/**
	 * @param projectCost
	 *            the projectCost to set
	 */
	public void setProjectCost(String projectCost) {
		this.projectCost = projectCost;
	}

	/**
	 * @return the designChangeRate
	 */
	public String getDesignChangeRate() {
		return designChangeRate;
	}

	/**
	 * @param designChangeRate
	 *            the designChangeRate to set
	 */
	public void setDesignChangeRate(String designChangeRate) {
		this.designChangeRate = designChangeRate;
	}

	/**
	 * @return the defaultDeductCost
	 */
	public String getDefaultDeductCost() {
		return defaultDeductCost;
	}

	/**
	 * @param defaultDeductCost
	 *            the defaultDeductCost to set
	 */
	public void setDefaultDeductCost(String defaultDeductCost) {
		this.defaultDeductCost = defaultDeductCost;
	}

	/**
	 * @return the designResultCheck
	 */
	public String getDesignResultCheck() {
		return designResultCheck;
	}

	/**
	 * @param designResultCheck
	 *            the designResultCheck to set
	 */
	public void setDesignResultCheck(String designResultCheck) {
		this.designResultCheck = designResultCheck;
	}

	/**
	 * @return the designResultApproveId
	 */
	public String getDesignResultApproveId() {
		return designResultApproveId;
	}

	/**
	 * @param designResultApproveId
	 *            the designResultApproveId to set
	 */
	public void setDesignResultApproveId(String designResultApproveId) {
		this.designResultApproveId = designResultApproveId;
	}

	/**
	 * @return the designChangeTotalId
	 */
	public String getDesignChangeTotalId() {
		return designChangeTotalId;
	}

	/**
	 * @param designChangeTotalId
	 *            the designChangeTotalId to set
	 */
	public void setDesignChangeTotalId(String designChangeTotalId) {
		this.designChangeTotalId = designChangeTotalId;
	}

	@Override
	public void doImport() {
		ContLedger contLedger = new ContLedger();
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		if (StringUtils.isNotBlank(settlementPrice)) {
			settlementPrice = settlementPrice.replace(",", "");

		} else {

			settlementPrice = "0.00";
		}
		if(StringUtils.isNotBlank(clearPriceParta)){
			clearPriceParta=clearPriceParta.replace(",", "");
			
		}else{
			clearPriceParta="0.00";
			
		}
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getContLedgerId())) {
			contLedger = contLedgerManager.getEntity(getContLedgerId());
			// 导出结算价
			contLedger.setClearPrice(new BigDecimal(settlementPrice));
			//含甲供料
			contLedger.setClearPriceParta(new BigDecimal(clearPriceParta));
		} 
		contLedger.setProcStatus("0");
		//设置合同台账状态为已完已结
		contLedger.setContStatus("2");
		ResApproveInfo approveInfo=getResApproveInfo();
	
		contLedger.setClearResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		contLedger.setClearResApproveId(approveInfo.getResApproveInfoId());
		//// 导出结算价
		contLedgerManager.saveContLedger(contLedger);

	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}

}
