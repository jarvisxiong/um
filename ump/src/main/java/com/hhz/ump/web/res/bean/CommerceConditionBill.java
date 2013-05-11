package com.hhz.ump.web.res.bean;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.web.res.baseBean.BaseBidTemplate;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;

//商务条件审批表(eg:商务条件审批表（由总部发起的申请）)	
public class CommerceConditionBill extends BaseBidTemplate implements IStatusModifyListener {
//	发起方
	private String sendOrg1;//招商中心
	private String sendOrg2;//大客户中心
//	政策类型
	private String policyCd1;//政策内
	private String policyCd2;//政策外
	
//	面积类型
	private String areaCd1;//面积＜2000平方米
	private String areaCd2;//面积≥2000平方米
	
//	标准类型
	private String brandCd1;//品牌满足
	private String brandCd2;//品牌不满足
	private String yetaiCd1;//业态满足
	private String yetaiCd2;//业态不满足
	private String projectChange1;//工程不变
	private String projectChange2;//工程变更
	
//	文件标题		所在项目	
	private String fileTitle;
	private String projectName;
	private String projectCd;
//	密级
//	□绝密	□机密
//	□保密	□内部公开 

	private String securityCd1;
	private String securityCd2;
	private String securityCd3;
	private String securityCd4;
	private String securityCd5;
	
//	急缓	
	private String urgencyCd1;
	private String urgencyCd2;
	
//		业态类别	□大主力店（百货、超市）  □次主力店    □品牌店    □其他 
	private String operTypeCd1;
	private String operTypeCd2;
	private String operTypeCd3;
	private String operTypeCd4;
	private String opertypeCd4Desc;
	
//		拟稿部门		招商经办人		校核人	
	private String deptName;
	private String deptCd;
	private String operatorName;
	private String operatorCd;
	private String checkerName;
	private String checkerCd;
	
//		意向商户	
	private String purposeStoreDesc;
	private String bisShopId;//商家库ID
	
//		商务条件
//		说   明
//		（具体内容附后）	一、商务条件
//				 标准（已批条件）	申请条件	批注
//		1	业态			
	private String oper1;
	private String oper2;
	private String oper3;
//		2	租赁区域			
	private String rentArea1;
	private String rentArea2;
	private String rentArea3;
//		3	计租面积			
	private String calcArea1;
	private String calcArea2;
	private String calcArea3;
//		4	租期			
	private String rentRank1;
	private String rentRank2;
	private String rentRank3;
//		5	交付时间			
	private String handDate1;
	private String handDate2;
	private String handDate3;
//		6	开业时间			
	private String openDate1;
	private String openDate2;
	private String openDate3;
//		7	租金			
	private String rentFeeAmt1;
	private String rentFeeAmt2;
	private String rentFeeAmt3;
//		8	递增率			
	private String incrRate1;
	private String incrRate2;
	private String incrRate3;
//		9	物业管理费			
	private String propMgrAmt1;
	private String propMgrAmt2;
	private String propMgrAmt3;
//		10	综合管理费			
	private String compMgrAmt1;
	private String compMgrAmt2;
	private String compMgrAmt3;
//		11	装修期			
	private String decoDateRank1;
	private String decoDateRank2;
	private String decoDateRank3;
//		12	免租期			
	private String rentDateRank1;
	private String rentDateRank2;
	private String rentDateRank3;
//		13	租赁保证金			
	private String rentGaraAmt1;
	private String rentGaraAmt2;
	private String rentGaraAmt3;
//		14	违约金			
	private String defaultAmt1;
	private String defaultAmt2;
	private String defaultAmt3;
//		15	广告位			
	private String adverPos1;
	private String adverPos2;
	private String adverPos3;
//		16	停车			
	private String parkDesc1;
	private String parkDesc2;
	private String parkDesc3;
//		17	其他事项			
	private String otherItem1;
	private String otherItem2;
	private String otherItem3;
//		商务条件
//		说   明
//		（具体内容附后）	二、主要工程条件（注：工程交付提要见附件）
//		
//			事  项	要     求	备注
//		1	给排水		
	private String waterSupply1;
	private String waterSupply2;
//		2	天然气		
	private String gas1;
	private String gas2;
//		3	排油、排风		
	private String airExhaust1;
	private String airExhaust2;
//		4	空调		
	private String airCond1;
	private String airCond2;
//		5	设备机房		
	private String equiment1;
	private String equiment2;
//		6	隔油池		
	private String oilSeparator1;
	private String oilSeparator2;
//		7	消防报验		
	private String secuCheck1;
	private String secuCheck2;
//		8	装修		
	private String decorate1;
	private String decorate2;
//		9	配电		
	private String elecDist1;
	private String elecDist2;
//		10	用电量		
	private String powerCons1;
	private String powerCons2;
//		11	其他事项		
	private String other1;
	private String other2;
	
	private String projectBudget;	//工程改造预算
	
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
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
	public String getSecurityCd1() {
		return securityCd1;
	}
	public void setSecurityCd1(String securityCd1) {
		this.securityCd1 = securityCd1;
	}
	public String getSecurityCd2() {
		return securityCd2;
	}
	public void setSecurityCd2(String securityCd2) {
		this.securityCd2 = securityCd2;
	}
	public String getSecurityCd3() {
		return securityCd3;
	}
	public void setSecurityCd3(String securityCd3) {
		this.securityCd3 = securityCd3;
	}
	public String getSecurityCd4() {
		return securityCd4;
	}
	public void setSecurityCd4(String securityCd4) {
		this.securityCd4 = securityCd4;
	}
	public String getSecurityCd5() {
		return securityCd5;
	}
	public void setSecurityCd5(String securityCd5) {
		this.securityCd5 = securityCd5;
	}
	 
	public String getOperTypeCd1() {
		return operTypeCd1;
	}
	public void setOperTypeCd1(String operTypeCd1) {
		this.operTypeCd1 = operTypeCd1;
	}
	public String getOperTypeCd2() {
		return operTypeCd2;
	}
	public void setOperTypeCd2(String operTypeCd2) {
		this.operTypeCd2 = operTypeCd2;
	}
	public String getOperTypeCd3() {
		return operTypeCd3;
	}
	public void setOperTypeCd3(String operTypeCd3) {
		this.operTypeCd3 = operTypeCd3;
	}
	public String getOperTypeCd4() {
		return operTypeCd4;
	}
	public void setOperTypeCd4(String operTypeCd4) {
		this.operTypeCd4 = operTypeCd4;
	}
	public String getOpertypeCd4Desc() {
		return opertypeCd4Desc;
	}
	public void setOpertypeCd4Desc(String opertypeCd4Desc) {
		this.opertypeCd4Desc = opertypeCd4Desc;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorCd() {
		return operatorCd;
	}
	public void setOperatorCd(String operatorCd) {
		this.operatorCd = operatorCd;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public String getCheckerCd() {
		return checkerCd;
	}
	public void setCheckerCd(String checkerCd) {
		this.checkerCd = checkerCd;
	}
	public String getPurposeStoreDesc() {
		return purposeStoreDesc;
	}
	public void setPurposeStoreDesc(String purposeStoreDesc) {
		this.purposeStoreDesc = purposeStoreDesc;
	}
	public String getOper1() {
		return oper1;
	}
	public void setOper1(String oper1) {
		this.oper1 = oper1;
	}
	public String getOper2() {
		return oper2;
	}
	public void setOper2(String oper2) {
		this.oper2 = oper2;
	}
	public String getOper3() {
		return oper3;
	}
	public void setOper3(String oper3) {
		this.oper3 = oper3;
	}
	public String getRentArea1() {
		return rentArea1;
	}
	public void setRentArea1(String rentArea1) {
		this.rentArea1 = rentArea1;
	}
	public String getRentArea2() {
		return rentArea2;
	}
	public void setRentArea2(String rentArea2) {
		this.rentArea2 = rentArea2;
	}
	public String getRentArea3() {
		return rentArea3;
	}
	public void setRentArea3(String rentArea3) {
		this.rentArea3 = rentArea3;
	}
	public String getCalcArea1() {
		return calcArea1;
	}
	public void setCalcArea1(String calcArea1) {
		this.calcArea1 = calcArea1;
	}
	public String getCalcArea2() {
		return calcArea2;
	}
	public void setCalcArea2(String calcArea2) {
		this.calcArea2 = calcArea2;
	}
	public String getCalcArea3() {
		return calcArea3;
	}
	public void setCalcArea3(String calcArea3) {
		this.calcArea3 = calcArea3;
	}
	public String getRentRank1() {
		return rentRank1;
	}
	public void setRentRank1(String rentRank1) {
		this.rentRank1 = rentRank1;
	}
	public String getRentRank2() {
		return rentRank2;
	}
	public void setRentRank2(String rentRank2) {
		this.rentRank2 = rentRank2;
	}
	public String getRentRank3() {
		return rentRank3;
	}
	public void setRentRank3(String rentRank3) {
		this.rentRank3 = rentRank3;
	}
	public String getHandDate1() {
		return handDate1;
	}
	public void setHandDate1(String handDate1) {
		this.handDate1 = handDate1;
	}
	public String getHandDate2() {
		return handDate2;
	}
	public void setHandDate2(String handDate2) {
		this.handDate2 = handDate2;
	}
	public String getHandDate3() {
		return handDate3;
	}
	public void setHandDate3(String handDate3) {
		this.handDate3 = handDate3;
	}
	public String getOpenDate1() {
		return openDate1;
	}
	public void setOpenDate1(String openDate1) {
		this.openDate1 = openDate1;
	}
	public String getOpenDate2() {
		return openDate2;
	}
	public void setOpenDate2(String openDate2) {
		this.openDate2 = openDate2;
	}
	public String getOpenDate3() {
		return openDate3;
	}
	public void setOpenDate3(String openDate3) {
		this.openDate3 = openDate3;
	}
	public String getRentFeeAmt1() {
		return rentFeeAmt1;
	}
	public void setRentFeeAmt1(String rentFeeAmt1) {
		this.rentFeeAmt1 = rentFeeAmt1;
	}
	public String getRentFeeAmt2() {
		return rentFeeAmt2;
	}
	public void setRentFeeAmt2(String rentFeeAmt2) {
		this.rentFeeAmt2 = rentFeeAmt2;
	}
	public String getRentFeeAmt3() {
		return rentFeeAmt3;
	}
	public void setRentFeeAmt3(String rentFeeAmt3) {
		this.rentFeeAmt3 = rentFeeAmt3;
	}
	public String getIncrRate1() {
		return incrRate1;
	}
	public void setIncrRate1(String incrRate1) {
		this.incrRate1 = incrRate1;
	}
	public String getIncrRate2() {
		return incrRate2;
	}
	public void setIncrRate2(String incrRate2) {
		this.incrRate2 = incrRate2;
	}
	public String getIncrRate3() {
		return incrRate3;
	}
	public void setIncrRate3(String incrRate3) {
		this.incrRate3 = incrRate3;
	}
	public String getPropMgrAmt1() {
		return propMgrAmt1;
	}
	public void setPropMgrAmt1(String propMgrAmt1) {
		this.propMgrAmt1 = propMgrAmt1;
	}
	public String getPropMgrAmt2() {
		return propMgrAmt2;
	}
	public void setPropMgrAmt2(String propMgrAmt2) {
		this.propMgrAmt2 = propMgrAmt2;
	}
	public String getPropMgrAmt3() {
		return propMgrAmt3;
	}
	public void setPropMgrAmt3(String propMgrAmt3) {
		this.propMgrAmt3 = propMgrAmt3;
	}
	public String getCompMgrAmt1() {
		return compMgrAmt1;
	}
	public void setCompMgrAmt1(String compMgrAmt1) {
		this.compMgrAmt1 = compMgrAmt1;
	}
	public String getCompMgrAmt2() {
		return compMgrAmt2;
	}
	public void setCompMgrAmt2(String compMgrAmt2) {
		this.compMgrAmt2 = compMgrAmt2;
	}
	public String getCompMgrAmt3() {
		return compMgrAmt3;
	}
	public void setCompMgrAmt3(String compMgrAmt3) {
		this.compMgrAmt3 = compMgrAmt3;
	}
	public String getDecoDateRank1() {
		return decoDateRank1;
	}
	public void setDecoDateRank1(String decoDateRank1) {
		this.decoDateRank1 = decoDateRank1;
	}
	public String getDecoDateRank2() {
		return decoDateRank2;
	}
	public void setDecoDateRank2(String decoDateRank2) {
		this.decoDateRank2 = decoDateRank2;
	}
	public String getDecoDateRank3() {
		return decoDateRank3;
	}
	public void setDecoDateRank3(String decoDateRank3) {
		this.decoDateRank3 = decoDateRank3;
	}
	public String getRentDateRank1() {
		return rentDateRank1;
	}
	public void setRentDateRank1(String rentDateRank1) {
		this.rentDateRank1 = rentDateRank1;
	}
	public String getRentDateRank2() {
		return rentDateRank2;
	}
	public void setRentDateRank2(String rentDateRank2) {
		this.rentDateRank2 = rentDateRank2;
	}
	public String getRentDateRank3() {
		return rentDateRank3;
	}
	public void setRentDateRank3(String rentDateRank3) {
		this.rentDateRank3 = rentDateRank3;
	}
	public String getRentGaraAmt1() {
		return rentGaraAmt1;
	}
	public void setRentGaraAmt1(String rentGaraAmt1) {
		this.rentGaraAmt1 = rentGaraAmt1;
	}
	public String getRentGaraAmt2() {
		return rentGaraAmt2;
	}
	public void setRentGaraAmt2(String rentGaraAmt2) {
		this.rentGaraAmt2 = rentGaraAmt2;
	}
	public String getRentGaraAmt3() {
		return rentGaraAmt3;
	}
	public void setRentGaraAmt3(String rentGaraAmt3) {
		this.rentGaraAmt3 = rentGaraAmt3;
	}
	public String getDefaultAmt1() {
		return defaultAmt1;
	}
	public void setDefaultAmt1(String defaultAmt1) {
		this.defaultAmt1 = defaultAmt1;
	}
	public String getDefaultAmt2() {
		return defaultAmt2;
	}
	public void setDefaultAmt2(String defaultAmt2) {
		this.defaultAmt2 = defaultAmt2;
	}
	public String getDefaultAmt3() {
		return defaultAmt3;
	}
	public void setDefaultAmt3(String defaultAmt3) {
		this.defaultAmt3 = defaultAmt3;
	}
	public String getAdverPos1() {
		return adverPos1;
	}
	public void setAdverPos1(String adverPos1) {
		this.adverPos1 = adverPos1;
	}
	public String getAdverPos2() {
		return adverPos2;
	}
	public void setAdverPos2(String adverPos2) {
		this.adverPos2 = adverPos2;
	}
	public String getAdverPos3() {
		return adverPos3;
	}
	public void setAdverPos3(String adverPos3) {
		this.adverPos3 = adverPos3;
	}
	public String getParkDesc1() {
		return parkDesc1;
	}
	public void setParkDesc1(String parkDesc1) {
		this.parkDesc1 = parkDesc1;
	}
	public String getParkDesc2() {
		return parkDesc2;
	}
	public void setParkDesc2(String parkDesc2) {
		this.parkDesc2 = parkDesc2;
	}
	public String getParkDesc3() {
		return parkDesc3;
	}
	public void setParkDesc3(String parkDesc3) {
		this.parkDesc3 = parkDesc3;
	}
	public String getOtherItem1() {
		return otherItem1;
	}
	public void setOtherItem1(String otherItem1) {
		this.otherItem1 = otherItem1;
	}
	public String getOtherItem2() {
		return otherItem2;
	}
	public void setOtherItem2(String otherItem2) {
		this.otherItem2 = otherItem2;
	}
	public String getOtherItem3() {
		return otherItem3;
	}
	public void setOtherItem3(String otherItem3) {
		this.otherItem3 = otherItem3;
	}
	public String getWaterSupply1() {
		return waterSupply1;
	}
	public void setWaterSupply1(String waterSupply1) {
		this.waterSupply1 = waterSupply1;
	}
	public String getWaterSupply2() {
		return waterSupply2;
	}
	public void setWaterSupply2(String waterSupply2) {
		this.waterSupply2 = waterSupply2;
	}
	public String getGas1() {
		return gas1;
	}
	public void setGas1(String gas1) {
		this.gas1 = gas1;
	}
	public String getGas2() {
		return gas2;
	}
	public void setGas2(String gas2) {
		this.gas2 = gas2;
	}
	public String getAirExhaust1() {
		return airExhaust1;
	}
	public void setAirExhaust1(String airExhaust1) {
		this.airExhaust1 = airExhaust1;
	}
	public String getAirExhaust2() {
		return airExhaust2;
	}
	public void setAirExhaust2(String airExhaust2) {
		this.airExhaust2 = airExhaust2;
	}
	public String getAirCond1() {
		return airCond1;
	}
	public void setAirCond1(String airCond1) {
		this.airCond1 = airCond1;
	}
	public String getAirCond2() {
		return airCond2;
	}
	public void setAirCond2(String airCond2) {
		this.airCond2 = airCond2;
	}
	public String getEquiment1() {
		return equiment1;
	}
	public void setEquiment1(String equiment1) {
		this.equiment1 = equiment1;
	}
	public String getEquiment2() {
		return equiment2;
	}
	public void setEquiment2(String equiment2) {
		this.equiment2 = equiment2;
	}
	public String getOilSeparator1() {
		return oilSeparator1;
	}
	public void setOilSeparator1(String oilSeparator1) {
		this.oilSeparator1 = oilSeparator1;
	}
	public String getOilSeparator2() {
		return oilSeparator2;
	}
	public void setOilSeparator2(String oilSeparator2) {
		this.oilSeparator2 = oilSeparator2;
	}
	public String getSecuCheck1() {
		return secuCheck1;
	}
	public void setSecuCheck1(String secuCheck1) {
		this.secuCheck1 = secuCheck1;
	}
	public String getSecuCheck2() {
		return secuCheck2;
	}
	public void setSecuCheck2(String secuCheck2) {
		this.secuCheck2 = secuCheck2;
	}
	public String getDecorate1() {
		return decorate1;
	}
	public void setDecorate1(String decorate1) {
		this.decorate1 = decorate1;
	}
	public String getDecorate2() {
		return decorate2;
	}
	public void setDecorate2(String decorate2) {
		this.decorate2 = decorate2;
	}
	public String getElecDist1() {
		return elecDist1;
	}
	public void setElecDist1(String elecDist1) {
		this.elecDist1 = elecDist1;
	}
	public String getElecDist2() {
		return elecDist2;
	}
	public void setElecDist2(String elecDist2) {
		this.elecDist2 = elecDist2;
	}
	public String getPowerCons1() {
		return powerCons1;
	}
	public void setPowerCons1(String powerCons1) {
		this.powerCons1 = powerCons1;
	}
	public String getPowerCons2() {
		return powerCons2;
	}
	public void setPowerCons2(String powerCons2) {
		this.powerCons2 = powerCons2;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	public String getUrgencyCd1() {
		return urgencyCd1;
	}
	public void setUrgencyCd1(String urgencyCd1) {
		this.urgencyCd1 = urgencyCd1;
	}
	public String getUrgencyCd2() {
		return urgencyCd2;
	}
	public void setUrgencyCd2(String urgencyCd2) {
		this.urgencyCd2 = urgencyCd2;
	}
	public String getPolicyCd1() {
		return policyCd1;
	}
	public void setPolicyCd1(String policyCd1) {
		this.policyCd1 = policyCd1;
	}
	public String getPolicyCd2() {
		return policyCd2;
	}
	public void setPolicyCd2(String policyCd2) {
		this.policyCd2 = policyCd2;
	}
	public String getAreaCd1() {
		return areaCd1;
	}
	public void setAreaCd1(String areaCd1) {
		this.areaCd1 = areaCd1;
	}
	public String getAreaCd2() {
		return areaCd2;
	}
	public void setAreaCd2(String areaCd2) {
		this.areaCd2 = areaCd2;
	}
	public String getBrandCd1() {
		return brandCd1;
	}
	public void setBrandCd1(String brandCd1) {
		this.brandCd1 = brandCd1;
	}
	public String getBrandCd2() {
		return brandCd2;
	}
	public void setBrandCd2(String brandCd2) {
		this.brandCd2 = brandCd2;
	}
	public String getYetaiCd1() {
		return yetaiCd1;
	}
	public void setYetaiCd1(String yetaiCd1) {
		this.yetaiCd1 = yetaiCd1;
	}
	public String getYetaiCd2() {
		return yetaiCd2;
	}
	public void setYetaiCd2(String yetaiCd2) {
		this.yetaiCd2 = yetaiCd2;
	}
	public String getSendOrg1() {
		return sendOrg1;
	}
	public void setSendOrg1(String sendOrg1) {
		this.sendOrg1 = sendOrg1;
	}
	public String getSendOrg2() {
		return sendOrg2;
	}
	public void setSendOrg2(String sendOrg2) {
		this.sendOrg2 = sendOrg2;
	}
	@Override
	public String getResTitleName() {
		return fileTitle;
	}
	public String getBisShopId() {
		return bisShopId;
	}
	public void setBisShopId(String bisShopId) {
		this.bisShopId = bisShopId;
	}
	public String getProjectChange1() {
		return projectChange1;
	}
	public void setProjectChange1(String projectChange1) {
		this.projectChange1 = projectChange1;
	}
	public String getProjectChange2() {
		return projectChange2;
	}
	public void setProjectChange2(String projectChange2) {
		this.projectChange2 = projectChange2;
	}
	public String getProjectBudget() {
		return projectBudget;
	}
	public void setProjectBudget(String projectBudget) {
		this.projectBudget = projectBudget;
	}

	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "CommerceConditionBill");
	}
}
