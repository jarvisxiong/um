package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.dao.cost.CostStrageMateDetailManager;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.web.res.baseBean.BaseTemplatePay;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 
 * 类名 StrategyOnlineOrder
 * 
 * @author liuzhihui || 2011-03-13
 * 
 *         描述 :战略采购网上下单审批表
 */
public class StrategyOnlineOrder extends BaseTemplatePay implements IAutoImport {

	// 是否基础库有"材料设备"明细
	private String hasMateFlg;

	// 项目名称
	private String projectName;
	private String projectCd;

	// 材料设备
	private String mateModuleName;
	private String mateModuleId;
	// 下单日期
	private String orderDate;

	// 是否商业, 1-是 0-否
	private String bizFlg0;
	private String bizFlg1;

	// 领用单位 , 领用单位合同ID,领用单位合同号 (商业)
	private String bizTakeUnitName;
	private String bizTakeUnitId;
	// 
	private String bizTakeUnitContNo;
	

	// 领用单位 , 领用单位合同ID
	private String takeUnitName;
	private String takeUnitId;

	// 领用单位合同号
	private String takeUnitContNo;

	// 使用区域 useCoverage
	private String useCoverage;

	// 进场时间 enterDate
	private String enterDate;

	// 采购总价 (元)
	private String purchaseTotalAmt;

	// 战略内总价（元） -Add by luzhihui 2012-05-21
	private String purchaseStrategyTotalAmt;
	// 战略外总价（元） -Add by luzhihui 2012-05-22
	private String strategyOutTotalAmt;

	// 总量
	private String contTotalNum;
	// 本期止累计领用量(含本期)
	private String toThisTotalNum;
	// 技术参数
	private String purchaseTechConfirmFileId;

	// 供应商与贸易公司订货合同
	private String attachFileId1;
	// 贸易公司与项目公司订货合同
	private String attachFileId2;

	// 战略供方-甲方：
	private String partA;
	// 战略供方-乙方：partB/partBName(冗余保存,方便方便返查)
	private String partB;
	private String partBName;

	// 价格提示（如：铜价介于45000与70000之间)
	private String priceTip;

	// 采购周期 purchasePreiod
	// 订单说明 orderDesc

	// 应提供细项(请作为附件上传),如下:
	// 供应商与贸易公司订货合同:是否提供(是/否) hasSupplyTradeFlg
	// 贸易公司与项目公司订货合同:是否提供(是/否)hasTradeProjectFlg
	// 采购数量及技术参数确认单(须工程副总以上人员签字):是否提供(是/否) hasPurchaseTechConfirmFlg;
	private String titleName;
	private String contactNo;

	// ---战略网上下单添加的属性开始--
	private String contactName; // 战略合同名称
	private String contactNameM; // 合同名称(贸易与地产)

	private String budgetAdjustNum; // 预算调整量
	private String stargeFlg1; // 是否战略 (是)
	private String stargeFlg2; // 是否战略 (否)
	private String materialId; // 材料设备ID
	private String materialName; // 材料设备名称
	private String specTypeCd; // 材料设备特殊类型

	private String basePrice; // 基准价
	private String lowPrice; // 最低价
	private String highPrice; // 最高价
	private String floatRate;// 浮动率
	private String floatRateZa;
	private String floatRateZb;
	private String floatRateZc;
	private String totalFloatRate; // 总价浮动比例

	private String headNames; // 列头名称
	// ---战略网上下单添加的属性结束--

	private String partC; // 丙方 -Add for part C by zhuxj on 2012.3.31
	private String partCName;// 丙方签约人 -Add by liuzhihui 2012-04-01
	private String partBReal;// 实际供方

	private String purchasePreiod;
	private String orderDesc;
	private String supplyTradeFileId;
	private String tradeProjectFileId;

	private String beyondStraAmtId; // 相关附件

	private String contractNo;// 合同编号
	private String contractPrice;// 审定价

	private String contractNo2;// 合同编号2
	private String contractPrice2;// 审定价2

	// 设备型号列表 (战略网上下单添加的属性)
	private List<StrategyMatePrice> strategyMatePrices = new ArrayList<StrategyMatePrice>();

	@Override
	public void doImport() {

		/**
		 * 导入合同台账
		 */

		ContProjectCodeManager projectCodeManager = SpringContextHolder.getBean("contProjectCodeManager");
		List<ContProjectCode> projectCodeList = projectCodeManager.getAll();
		// 寻找projectCd，若在合同类别里有projectCd,则新增该项目，若无则不setProjectCd
		boolean haveProject = false;
		for (ContProjectCode code : projectCodeList) {
			if (StringUtils.isNotBlank(projectCd) && projectCd.equals(code.getProjectCd())) {
				haveProject = true;
				break;
			}
		}

		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		contLedgerManager.importStrageBill(this, haveProject ? "" : projectCd);// 润在项目，则传入

		/**
		 * 导入战略库
		 */
		CostStrageMateDetailManager costStrageMateManager = SpringContextHolder.getBean("costStrageMateDetailManager");
		costStrageMateManager.importStrageBill(this, projectCd);
	}

	@Override
	public boolean isAutoImport() {
		// true 网批走完自动导入 false 手动导入
		return false;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNameM() {
		return contactNameM;
	}

	public void setContactNameM(String contactNameM) {
		this.contactNameM = contactNameM;
	}

	public String getTakeUnitContNo() {
		return takeUnitContNo;
	}

	public void setTakeUnitContNo(String takeUnitContNo) {
		this.takeUnitContNo = takeUnitContNo;
	}

	public String getTakeUnitName() {
		return takeUnitName;
	}

	public void setTakeUnitName(String takeUnitName) {
		this.takeUnitName = takeUnitName;
	}

	public String getTakeUnitId() {
		return takeUnitId;
	}

	public void setTakeUnitId(String takeUnitId) {
		this.takeUnitId = takeUnitId;
	}

	public String getToThisTotalNum() {
		return toThisTotalNum;
	}

	public void setToThisTotalNum(String toThisTotalNum) {
		this.toThisTotalNum = toThisTotalNum;
	}

	public String getContTotalNum() {
		return contTotalNum;
	}

	public void setContTotalNum(String contTotalNum) {
		this.contTotalNum = contTotalNum;
	}

	public String getBudgetAdjustNum() {
		return budgetAdjustNum;
	}

	public void setBudgetAdjustNum(String budgetAdjustNum) {
		this.budgetAdjustNum = budgetAdjustNum;
	}

	public String getStargeFlg1() {
		return stargeFlg1;
	}

	public void setStargeFlg1(String stargeFlg1) {
		this.stargeFlg1 = stargeFlg1;
	}

	public String getStargeFlg2() {
		return stargeFlg2;
	}

	public void setStargeFlg2(String stargeFlg2) {
		this.stargeFlg2 = stargeFlg2;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getHeadNames() {
		return headNames;
	}

	public void setHeadNames(String headNames) {
		this.headNames = headNames;
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

	public String getPartBReal() {
		return partBReal;
	}

	public void setPartBReal(String partBReal) {
		this.partBReal = partBReal;
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

	public String getPurchaseStrategyTotalAmt() {

		if (purchaseStrategyTotalAmt == null) {
			purchaseStrategyTotalAmt = "0";
		}

		return purchaseStrategyTotalAmt;
	}

	public void setPurchaseStrategyTotalAmt(String purchaseStrategyTotalAmt) {
		this.purchaseStrategyTotalAmt = purchaseStrategyTotalAmt;
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

	// public String getBeyondStraAmt() {
	// return beyondStraAmt;
	// }
	//
	// public void setBeyondStraAmt(String beyondStraAmt) {
	// this.beyondStraAmt = beyondStraAmt;
	// }

	public String getBeyondStraAmtId() {
		return beyondStraAmtId;
	}

	public void setBeyondStraAmtId(String beyondStraAmtId) {
		this.beyondStraAmtId = beyondStraAmtId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
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

	public List<StrategyMatePrice> getStrategyMatePrices() {
		return strategyMatePrices;
	}

	public void setStrategyMatePrices(List<StrategyMatePrice> strategyMatePrices) {
		this.strategyMatePrices = strategyMatePrices;
	}

	public String getSpecTypeCd() {
		return specTypeCd;
	}

	public void setSpecTypeCd(String specTypeCd) {
		this.specTypeCd = specTypeCd;
	}

	public String getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(String highPrice) {
		this.highPrice = highPrice;
	}

	public String getTotalFloatRate() {
		return totalFloatRate;
	}

	public void setTotalFloatRate(String totalFloatRate) {
		this.totalFloatRate = totalFloatRate;
	}

	public String getStrategyOutTotalAmt() {

		if (strategyOutTotalAmt == null) {
			strategyOutTotalAmt = "0";
		}
		return strategyOutTotalAmt;
	}

	public void setStrategyOutTotalAmt(String strategyOutTotalAmt) {
		this.strategyOutTotalAmt = strategyOutTotalAmt;
	}

	public String getMateModuleName() {
		return mateModuleName;
	}

	public void setMateModuleName(String mateModuleName) {
		this.mateModuleName = mateModuleName;
	}

	public String getMateModuleId() {
		return mateModuleId;
	}

	public void setMateModuleId(String mateModuleId) {
		this.mateModuleId = mateModuleId;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getFloatRate() {
		return floatRate;
	}

	public void setFloatRate(String floatRate) {
		this.floatRate = floatRate;
	}

	public String getFloatRateZa() {
		return floatRateZa;
	}

	public void setFloatRateZa(String floatRateZa) {
		this.floatRateZa = floatRateZa;
	}

	public String getFloatRateZb() {
		return floatRateZb;
	}

	public void setFloatRateZb(String floatRateZb) {
		this.floatRateZb = floatRateZb;
	}

	public String getFloatRateZc() {
		return floatRateZc;
	}

	public void setFloatRateZc(String floatRateZc) {
		this.floatRateZc = floatRateZc;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getAttachFileId1() {
		return attachFileId1;
	}

	public void setAttachFileId1(String attachFileId1) {
		this.attachFileId1 = attachFileId1;
	}

	public String getAttachFileId2() {
		return attachFileId2;
	}

	public void setAttachFileId2(String attachFileId2) {
		this.attachFileId2 = attachFileId2;
	}

	public String getPriceTip() {
		return priceTip;
	}

	public void setPriceTip(String priceTip) {
		this.priceTip = priceTip;
	}

	public String getHasMateFlg() {
		return hasMateFlg;
	}

	public void setHasMateFlg(String hasMateFlg) {
		this.hasMateFlg = hasMateFlg;
	}

	public String getBizFlg0() {
		return bizFlg0;
	}

	public void setBizFlg0(String bizFlg0) {
		this.bizFlg0 = bizFlg0;
	}

	public String getBizFlg1() {
		return bizFlg1;
	}

	public void setBizFlg1(String bizFlg1) {
		this.bizFlg1 = bizFlg1;
	}

	public String getBizTakeUnitName() {
		return bizTakeUnitName;
	}

	public void setBizTakeUnitName(String bizTakeUnitName) {
		this.bizTakeUnitName = bizTakeUnitName;
	}

	public String getBizTakeUnitId() {
		return bizTakeUnitId;
	}

	public void setBizTakeUnitId(String bizTakeUnitId) {
		this.bizTakeUnitId = bizTakeUnitId;
	}

	public String getBizTakeUnitContNo() {
		return bizTakeUnitContNo;
	}

	public void setBizTakeUnitContNo(String bizTakeUnitContNo) {
		this.bizTakeUnitContNo = bizTakeUnitContNo;
	}

}
