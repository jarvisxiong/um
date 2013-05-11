package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContAddAgreement;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 材料设备价格批定审批表（甲定乙供）
 * @author baolm
 *
 * 2011-04-13
 */
public class MaterialEquipPrice extends BaseContLedgerTemplate {
	
	private String in5percent; //与约定价格差额5%以内
	private String others; //其他情况
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 工程名称
	 */
	private String engineeringName;
	
	/**
	 * 甲方
	 */
	private String sideA;
	
	/**
	 * 乙方
	 */
	private String sideB;
	
	/**
	 * 丙方
	 */
	private String sideC;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31
	
	/**
	 * □甲定乙供
	 */
	private String provideB="true";
	
	/**
	 * □甲供合同增补
	 */
	private String provideA;
	
	/**
	 * 材料名称
	 */
	private String equipMaterialName;
	
	/**
	 * 进场时间
	 */
	private String approachDate;
	
	/**
	 * 原合同价
	 */
	private String oriContractPrice;
	
	/**
	 * 上报总价
	 */
	private String reportTotalPrice;
	
	/**
	 * 备注：增补原因说明
	 */
	private String addReason;
	
	/**
	 * 原合同约定的品牌
	 */
	private String oriContractBrand;
	
	/**
	 * 原合同单价
	 */
	private String oriContractUnitPrice;
	
	/**
	 * 原合同总价
	 */
	private String oriContractTotalPrice;
	
	/**
	 * 推荐品牌
	 */
	private String commendBrand;
	
	/**
	 * 推荐品牌单价
	 */
	private String commendBrandUnitPrice;
	
	/**
	 * 推荐品牌总价
	 */
	private String commendBrandTotalPrice;
	
	/**
	 * 备注：需批定价格原因说明
	 */
	private String approvePriceReason;
	
	/**
	 * 备注：推荐改品牌的理由
	 */
	private String commendBrandReason;
	
	/**
	 * 材料数量清单
	 */
	private String meterialQuantityListId;
	
	/**
	 * 乙方报价清单
	 */
	private String sideBPriceListId;
	
	/**
	 * 技术参数要求（非比传项）
	 */
	private String teckParamRequireId;
	
	/**
	 * 相关图纸（非比传项）
	 */
	private String relatedDrawingId;
	
	/**
	 * 合同编号
	 */
	private String contNo;
	
	/**
	 * 合同名称
	 */
	private String contName;
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
	
	public String getIn5percent() {
		return in5percent;
	}

	public void setIn5percent(String in5percent) {
		this.in5percent = in5percent;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSideA() {
		return sideA;
	}

	public void setSideA(String sideA) {
		this.sideA = sideA;
	}

	public String getSideB() {
		return sideB;
	}

	public void setSideB(String sideB) {
		this.sideB = sideB;
	}

	public String getSideC() {
		return sideC;
	}

	public void setSideC(String sideC) {
		this.sideC = sideC;
	}

	public String getProvideB() {
		return provideB;
	}

	public void setProvideB(String provideB) {
		this.provideB = provideB;
	}

	public String getProvideA() {
		return provideA;
	}

	public void setProvideA(String provideA) {
		this.provideA = provideA;
	}

	public String getEquipMaterialName() {
		return equipMaterialName;
	}

	public void setEquipMaterialName(String equipMaterialName) {
		this.equipMaterialName = equipMaterialName;
	}

	public String getApproachDate() {
		return approachDate;
	}

	public void setApproachDate(String approachDate) {
		this.approachDate = approachDate;
	}

	public String getOriContractBrand() {
		return oriContractBrand;
	}

	public void setOriContractBrand(String oriContractBrand) {
		this.oriContractBrand = oriContractBrand;
	}

	public String getOriContractUnitPrice() {
		return oriContractUnitPrice;
	}

	public void setOriContractUnitPrice(String oriContractUnitPrice) {
		this.oriContractUnitPrice = oriContractUnitPrice;
	}

	public String getOriContractTotalPrice() {
		return oriContractTotalPrice;
	}

	public void setOriContractTotalPrice(String oriContractTotalPrice) {
		this.oriContractTotalPrice = oriContractTotalPrice;
	}

	public String getCommendBrand() {
		return commendBrand;
	}

	public void setCommendBrand(String commendBrand) {
		this.commendBrand = commendBrand;
	}

	public String getCommendBrandUnitPrice() {
		return commendBrandUnitPrice;
	}

	public void setCommendBrandUnitPrice(String commendBrandUnitPrice) {
		this.commendBrandUnitPrice = commendBrandUnitPrice;
	}

	public String getCommendBrandTotalPrice() {
		return commendBrandTotalPrice;
	}

	public void setCommendBrandTotalPrice(String commendBrandTotalPrice) {
		this.commendBrandTotalPrice = commendBrandTotalPrice;
	}

	public String getApprovePriceReason() {
		return approvePriceReason;
	}

	public void setApprovePriceReason(String approvePriceReason) {
		this.approvePriceReason = approvePriceReason;
	}

	public String getCommendBrandReason() {
		return commendBrandReason;
	}

	public void setCommendBrandReason(String commendBrandReason) {
		this.commendBrandReason = commendBrandReason;
	}

	public String getMeterialQuantityListId() {
		return meterialQuantityListId;
	}

	public void setMeterialQuantityListId(String meterialQuantityListId) {
		this.meterialQuantityListId = meterialQuantityListId;
	}

	public String getSideBPriceListId() {
		return sideBPriceListId;
	}

	public void setSideBPriceListId(String sideBPriceListId) {
		this.sideBPriceListId = sideBPriceListId;
	}

	public String getTeckParamRequireId() {
		return teckParamRequireId;
	}

	public void setTeckParamRequireId(String teckParamRequireId) {
		this.teckParamRequireId = teckParamRequireId;
	}

	public String getRelatedDrawingId() {
		return relatedDrawingId;
	}

	public void setRelatedDrawingId(String relatedDrawingId) {
		this.relatedDrawingId = relatedDrawingId;
	}

	public String getOriContractPrice() {
		return oriContractPrice;
	}

	public void setOriContractPrice(String oriContractPrice) {
		this.oriContractPrice = oriContractPrice;
	}

	public String getReportTotalPrice() {
		return reportTotalPrice;
	}

	public void setReportTotalPrice(String reportTotalPrice) {
		this.reportTotalPrice = reportTotalPrice;
	}

	public String getAddReason() {
		return addReason;
	}

	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}

	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		return engineeringName;
	}

	@Override
	public void doImport() {

		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ContAddAgreement addAgree =new ContAddAgreement();
		ResApproveInfo approveInfo=getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		resApproveId =approveInfo.getResApproveInfoId();
		
		
		BigDecimal groupCheckFee = new BigDecimal(FormatUtil.formatDouble(groupMoney));

		//核定费用
		addAgree.setGroupCheckFee(groupCheckFee);
		//合同暂定价
		addAgree.setContTempMoney(new BigDecimal(0));
		//协议增减金额(元) 
		addAgree.setAmountUpdate(groupCheckFee);
		
		addAgree.setResApproveId(resApproveId);
		addAgree.setResApproveCd(resApproveCd); 
		
		//原因说明
		try{
		addAgree.setContent(getAllReasons(getAllReasons(addReason, approvePriceReason), commendBrandReason));
		}catch(Exception e){
			addAgree.setContent("请看网批");
		}
	
		ledgerManager.updateContByRes(getContLedgerId(),resApproveCd, null, null, addAgree, null);
	}
	
	private String getAllReasons(String tmp,String tmpAdd){

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.isBlank(tmp)?"":tmp);
		if(StringUtils.isNotBlank(tmpAdd)){
			sb.append("/");
			sb.append(tmpAdd.trim());
		}
		return sb.toString();
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the contNo
	 */
	public String getContNo() {
		return contNo;
	}

	/**
	 * @param contNo the contNo to set
	 */
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	/**
	 * @return the contName
	 */
	public String getContName() {
		return contName;
	}

	/**
	 * @param contName the contName to set
	 */
	public void setContName(String contName) {
		this.contName = contName;
	}

	/**
	 * @return the groupMoney
	 */
	public String getGroupMoney() {
		return groupMoney;
	}

	/**
	 * @param groupMoney the groupMoney to set
	 */
	public void setGroupMoney(String groupMoney) {
		this.groupMoney = groupMoney;
	}

	/**
	 * @return the resApproveCd
	 */
	public String getResApproveCd() {
		return resApproveCd;
	}

	/**
	 * @param resApproveCd the resApproveCd to set
	 */
	public void setResApproveCd(String resApproveCd) {
		this.resApproveCd = resApproveCd;
	}

	/**
	 * @return the resApproveId
	 */
	public String getResApproveId() {
		return resApproveId;
	}

	/**
	 * @param resApproveId the resApproveId to set
	 */
	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}

}
