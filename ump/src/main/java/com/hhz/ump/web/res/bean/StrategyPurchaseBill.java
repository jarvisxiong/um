package com.hhz.ump.web.res.bean;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.web.res.baseBean.BaseTemplatePay;
import com.hhz.ump.web.res.baseBean.IAutoImport;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;

/**
 * 
 * 类名 StrategyPurchaseBill 创建者 李劲 创建日期 2010-12-3 描述 战略采购订单审批表
 */
public class StrategyPurchaseBill extends BaseTemplatePay implements IAutoImport,IStatusModifyListener {

	// 项目名称 projectName
	// 战略合同名称(合同编号) contactName

	// 战略供方-甲方：partA
	// 战略供方-乙方：partB/partBName(冗余保存,方便方便返查)

	// 材料名称 materialName
	// 使用区域 useCoverage

	// 采购周期 purchasePreiod
	// 进场时间 enterDate

	// 采购总价 purchaseTotalAmt
	// 订单说明 orderDesc

	// 应提供细项(请作为附件上传),如下:
	// 供应商与贸易公司订货合同:是否提供(是/否) hasSupplyTradeFlg
	// 贸易公司与项目公司订货合同:是否提供(是/否)hasTradeProjectFlg
	// 贸易公司与海尔公司订货合同:是否提供(是/否)hasTradeHaierProjectFlg
	// 海尔公司与项目公司订货合同:是否提供(是/否)hasHaierTradeProjectFlg
	// 采购数量及技术参数确认单(须工程副总以上人员签字):是否提供(是/否) hasPurchaseTechConfirmFlg;
	private String titleName;
	private String projectName;
	private String projectCd;
	private String contactNo;
	private String contactName;

	private String partA;
	private String partB;
	private String partC; //丙方 -Add for part C by zhuxj on 2012.3.31
	private String partBName;
	private String partCName;//丙方签约人 -Add for part C by zhuxj on 2012.3.31
	private String partBReal; //实际供方
	private String stargeFlg; // 是否战略  -Add by liuzhihui 2012-04-01
	private String haierFlg;// 是否海尔 - Add for haier by zhuxj on 2012.4.11

	private String materialName;
	private String useCoverage;

	private String purchasePreiod;
	private String enterDate;

	private String purchaseTotalAmt;
	private String orderDesc;

	private String supplyTradeFileId;
	private String tradeProjectFileId;
	private String purchaseTechConfirmFileId;
	private String tradeHaierFileId;//贸易公司与海尔公司订货合同 -Add for haier by zhuxj on 2012.4.11
	private String haierProjectFileId;//海尔公司与项目公司订货合同 -Add for haier by zhuxj on 2012.4.11
	
	private String beyondStraAmt; //战略外价格
	private String beyondStraAmtId; //相关附件
	
	//注意：这两个很重要
    private String contractNo ;//合同编号   (主合同号，非空)
    private String contractPrice ;//审定价
    
    private String contractNo2 ;//合同编号2
    private String contractPrice2 ;//审定价2
    //合同文本库编号
    private String scContractNo;
 
	//标准合同  5/31/2012 增加属性
	private String standard;
	
	//非标准合同
	private String nonstandard;
	
	private String contractTempletInfoId;
	//合同历史版id
	private String contractTempletHisId;
	
	//使用合同库
	private String contlib;
	//不使用合同库
	private String noncontlib;
	//合同编号
	private String contractNo1;


	//合同名称
	private String contractName;
	
	
	 /**
	 * @return the scContractNo
	 */
	public String getScContractNo() {
		return scContractNo;
	}

	/**
	 * @param scContractNo the scContractNo to set
	 */
	public void setScContractNo(String scContractNo) {
		this.scContractNo = scContractNo;
	}

	
	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return the nonstandard
	 */
	public String getNonstandard() {
		return nonstandard;
	}
	/**
	 * @return the contractNo1
	 */
	public String getContractNo1() {
		return contractNo1;
	}

	/**
	 * @param contractNo1 the contractNo1 to set
	 */
	public void setContractNo1(String contractNo1) {
		this.contractNo1 = contractNo1;
	}
	/**
	 * @param nonstandard the nonstandard to set
	 */
	public void setNonstandard(String nonstandard) {
		this.nonstandard = nonstandard;
	}

	/**
	 * @return the contractTempletInfoId
	 */
	public String getContractTempletInfoId() {
		return contractTempletInfoId;
	}

	/**
	 * @param contractTempletInfoId the contractTempletInfoId to set
	 */
	public void setContractTempletInfoId(String contractTempletInfoId) {
		this.contractTempletInfoId = contractTempletInfoId;
	}

	/**
	 * @return the contractTempletHisId
	 */
	public String getContractTempletHisId() {
		return contractTempletHisId;
	}

	/**
	 * @param contractTempletHisId the contractTempletHisId to set
	 */
	public void setContractTempletHisId(String contractTempletHisId) {
		this.contractTempletHisId = contractTempletHisId;
	}

	/**
	 * @return the contlib
	 */
	public String getContlib() {
		return contlib;
	}

	/**
	 * @param contlib the contlib to set
	 */
	public void setContlib(String contlib) {
		this.contlib = contlib;
	}

	/**
	 * @return the noncontlib
	 */
	public String getNoncontlib() {
		return noncontlib;
	}

	/**
	 * @param noncontlib the noncontlib to set
	 */
	public void setNoncontlib(String noncontlib) {
		this.noncontlib = noncontlib;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getBeyondStraAmt() {
		return beyondStraAmt;
	}

	public void setBeyondStraAmt(String beyondStraAmt) {
		this.beyondStraAmt = beyondStraAmt;
	}

	public String getBeyondStraAmtId() {
		return beyondStraAmtId;
	}

	public void setBeyondStraAmtId(String beyondStraAmtId) {
		this.beyondStraAmtId = beyondStraAmtId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPartA() {
		return partA;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String getPartBName() {
		return partBName;
	}

	public void setPartBName(String partBName) {
		this.partBName = partBName;
	}

	public String getPartCName() {
		return partCName;
	}

	public void setPartCName(String partCName) {
		this.partCName = partCName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getUseCoverage() {
		return useCoverage;
	}

	public void setUseCoverage(String useCoverage) {
		this.useCoverage = useCoverage;
	}

	public String getPurchasePreiod() {
		return purchasePreiod;
	}

	public void setPurchasePreiod(String purchasePreiod) {
		this.purchasePreiod = purchasePreiod;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getPurchaseTotalAmt() {
		return purchaseTotalAmt;
	}

	public void setPurchaseTotalAmt(String purchaseTotalAmt) {
		this.purchaseTotalAmt = purchaseTotalAmt;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getSupplyTradeFileId() {
		return supplyTradeFileId;
	}

	public void setSupplyTradeFileId(String supplyTradeFileId) {
		this.supplyTradeFileId = supplyTradeFileId;
	}

	public String getTradeProjectFileId() {
		return tradeProjectFileId;
	}

	public void setTradeProjectFileId(String tradeProjectFileId) {
		this.tradeProjectFileId = tradeProjectFileId;
	}

	public String getPurchaseTechConfirmFileId() {
		return purchaseTechConfirmFileId;
	}

	public void setPurchaseTechConfirmFileId(String purchaseTechConfirmFileId) {
		this.purchaseTechConfirmFileId = purchaseTechConfirmFileId;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return titleName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Override
	public void doImport() {
		
		//导入合同台账
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		contLedgerManager.importBill(this);
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getPartBReal() {
		return partBReal;
	}

	public void setPartBReal(String partBReal) {
		this.partBReal = partBReal;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractNo2() {
		return contractNo2;
	}

	public void setContractNo2(String contractNo2) {
		this.contractNo2 = contractNo2;
	}

	public String getContractPrice2() {
		return contractPrice2;
	}

	public void setContractPrice2(String contractPrice2) {
		this.contractPrice2 = contractPrice2;
	}

	public String getStargeFlg() {
		return stargeFlg;
	}

	public void setStargeFlg(String stargeFlg) {
		this.stargeFlg = stargeFlg;
	}


	public String getTradeHaierFileId() {
		return tradeHaierFileId;
	}

	public void setTradeHaierFileId(String tradeHaierFileId) {
		this.tradeHaierFileId = tradeHaierFileId;
	}

	public String getHaierProjectFileId() {
		return haierProjectFileId;
	}

	public void setHaierProjectFileId(String haierProjectFileId) {
		this.haierProjectFileId = haierProjectFileId;
	}

	public String getHaierFlg() {
		return haierFlg;
	}

	public void setHaierFlg(String haierFlg) {
		this.haierFlg = haierFlg;
	}

	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "StrategyPurchaseBill");
	}
}
