package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.ctdb.CtdbBillingAppManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.ctdb.CtdbBillingApp;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 材料设备结算审批表
 * @author baolm
 *
 * 2011-04-27
 */
public class MaterialSettlementApprove extends BaseContLedgerTemplate {
	
	/**
	 * 合同编号
	 */
	private String contNo;
	
	/**
	 * 合同名称
	 */
	private String contName;
	
	/**
	 * 项目cd
	 */
	private String projectCd;

	/**
	 * 乙方(收款人)
	 */
	private String partB;
	
	//丙方
	
	//实际供方
	
	private String partC;
	private String realProvName;
	
	/**
	 * 合同总价
	 */
	private String totalPrice;
	
	/**
	 * 合同性质
	 */
	private String contProperty;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * ( )期
	 */
	private String projectPeriod;
	
	/**
	 * 扣减编号
	 */
	private String serialNo;
	
	/**
	 * 乙方报审金额(元)
	 */
	private String partBReviewAmount;
	
	/**
	 * 地产公司审核费用(元)
	 */
	private String approvedFee;
	
	/**
	 * 领货单位
	 */
	private List<MaterialSettlementOtherProperty> otherProperties = new ArrayList<MaterialSettlementOtherProperty>();
	
	/**
	 * 库存管理单位名称
	 */
	private String storageUnitName;
	
	/**
	 * 库存(元)
	 */
	private String storageAmount;
	
	/**
	 * 供货和领货说明
	 */
	private String description;
	
	/**
	 * 一审结算汇总表
	 */
	private String settlementSummaryId;
	
	/**
	 * 供货单位结算
	 */
	private String provideUnitSettlementId;
	
	/**
	 * 一审结算汇总表
	 */
	private String payMoney;
	
	/**
	 * 供货单位结算
	 */
	private String tatalRate;
	
	/**
	 * 三方签收资料
	 */
	private String threePartReceiveId;
	private String curPicId;// 现场照片
	/**
	 * 集团核定费用(元)//结算价
	 */
	private String groupMoney;
	
	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContName() {
		return contName;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getTatalRate() {
		return tatalRate;
	}

	public void setTatalRate(String tatalRate) {
		this.tatalRate = tatalRate;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getContProperty() {
		return contProperty;
	}

	public void setContProperty(String contProperty) {
		this.contProperty = contProperty;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getPartBReviewAmount() {
		return partBReviewAmount;
	}

	public void setPartBReviewAmount(String partBReviewAmount) {
		this.partBReviewAmount = partBReviewAmount;
	}

	public String getApprovedFee() {
		return approvedFee;
	}

	public void setApprovedFee(String approvedFee) {
		this.approvedFee = approvedFee;
	}

	public String getStorageUnitName() {
		return storageUnitName;
	}

	public void setStorageUnitName(String storageUnitName) {
		this.storageUnitName = storageUnitName;
	}

	public String getStorageAmount() {
		return storageAmount;
	}

	public void setStorageAmount(String storageAmount) {
		this.storageAmount = storageAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSettlementSummaryId() {
		return settlementSummaryId;
	}

	public void setSettlementSummaryId(String settlementSummaryId) {
		this.settlementSummaryId = settlementSummaryId;
	}

	public String getProvideUnitSettlementId() {
		return provideUnitSettlementId;
	}

	public void setProvideUnitSettlementId(String provideUnitSettlementId) {
		this.provideUnitSettlementId = provideUnitSettlementId;
	}

	public String getThreePartReceiveId() {
		return threePartReceiveId;
	}

	public void setThreePartReceiveId(String threePartReceiveId) {
		this.threePartReceiveId = threePartReceiveId;
	}

	public List<MaterialSettlementOtherProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(
			List<MaterialSettlementOtherProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getGroupMoney() {
		return groupMoney;
	}

	public void setGroupMoney(String groupMoney) {
		this.groupMoney = groupMoney;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contName;
	}

	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ResApproveInfo approveInfo=getResApproveInfo();
		ContLedger ledger =ledgerManager.getEntity(getContLedgerId());
		ledger.setClearResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		ledger.setClearResApproveId(approveInfo.getResApproveInfoId());
		
		//若结算价有值，修改结算价并合同状态改为已完已结，并进度状态改为已竣工
		if(StringUtils.isNotBlank(groupMoney)) {
			BigDecimal clear =new BigDecimal(FormatUtil.formatDouble(groupMoney));
			ledger.setClearPrice(clear);
			ledger.setContStatus("2");//2为已完已结
			ledger.setProcStatus("4");//4为已竣工
		}
		ledgerManager.saveContLedger(ledger);
		try {
			//contLedgerManager.updateContByRes(getContLedgerId(),"", groupMoney, null, null, null);
			importBillingApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void importBillingApp(){
		CtdbBillingAppManager ctdbBillingAppManager = SpringContextHolder.getBean("ctdbBillingAppManager");
		CtdbBillingApp cba=new CtdbBillingApp();
		//1.项目名称
		cba.setProjectName(this.getProjectName());
		cba.setProjectCd(this.getProjectCd());
		// 2.合同名称
		cba.setCtName(this.getContName());
		//3.乙方
		cba.setImpSupCd(this.getPartB());
		cba.setImpSupName(null);
		//4.承包范围（现结算审批表内无，建议增加，由合同台账自动导入）
		cba.setCtArea(null);
		//5.合同性质
		cba.setCtProperty(this.getContProperty());
		//6.面积（数量，材料结算内无，建议增加）
		cba.setArea(null);
		//7.结算价（需审批完成后，由经办人手工导入）
		if(StringUtils.isNotBlank(groupMoney)){
			try {
				BigDecimal billingPrice=BigDecimal.valueOf(Double.valueOf(groupMoney.trim().replaceAll(",", "")));
				cba.setBillingPrice(billingPrice);
			} catch (Exception e) {
					Log.error(e);
				}
		}
		//8.单位造价（单价=结算价/面积）
		cba.setUnitPrice(null);
		//9.结算日期（按结算审批表完成的日期导入）
		cba.setBillingDate(this.getResApproveInfo().getCompleteDate());
		//10.网批编号
		cba.setBillingResCd(this.getResApproveInfo().getResApproveInfoId());
		cba.setApproveCd(this.getResApproveInfo().getApproveCd()+this.getResApproveInfo().getSerialNo());
		//10.导入类型
		cba.setImportType(this.getResApproveInfo().getAuthTypeCd());
		//默认系统创建
		cba.setCreator("system");
		cba.setCreatedDate(new Date());
		cba.setAuthTypeCd(this.getResApproveInfo().getAuthTypeCd());
		ctdbBillingAppManager.saveCtdbBillingApp(cba);
	}

	@Override
	public boolean isAutoImport() {
		return false;
	}

	public String getCurPicId() {
		return curPicId;
	}

	public void setCurPicId(String curPicId) {
		this.curPicId = curPicId;
	}
 

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String getRealProvName() {
		return realProvName;
	}

	public void setRealProvName(String realProvName) {
		this.realProvName = realProvName;
	}

	
}
