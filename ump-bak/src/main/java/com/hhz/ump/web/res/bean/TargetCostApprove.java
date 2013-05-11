package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.ct.CtLedgerManager;
import com.hhz.ump.entity.ct.CtLedger;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 目标成本审批表
 * @author baolm
 *
 * 2011-04-21
 */
public class TargetCostApprove extends BaseTemplate implements IAutoImport {
//	
	// 审核权限:是否与酒店有关
	private String approvePrivFlg;
	
	/**
	 * □目标成本(可研版)
	 */
	private String checkType1;
	
	/**
	 * □目标成本
	 */
	private String checkType2;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目Cd
	 */
	private String projectCd;
	
	/**
	 * 合同( )期
	 */
	private String projectPeriod;
	
	/**
	 * 开工时间
	 */
	private String startDate;
	
	/**
	 * 交房时间
	 */
	private String submitDate;
	
	/**
	 * 总建筑面积(㎡)
	 */
	private String totalBuildingArea;
	
	/**
	 * 计入容积率建筑面积(㎡)
	 */
	private String capacityRatioBuildingArea;
	
	/**
	 * 目标成本总额(元)
	 */
	private String totalTargetCost;
	
	/**
	 * 容积率面积单方造价(元/㎡)
	 */
	private String capacityRatioUnitCost;
	
	/**
	 * 主要内容及说明
	 */
	private String remark;
	
	/**
	 * 规划指标或产品标准
	 */
	private String indicateOrProductStandardId;
	
	/**
	 * 目标成本编制说明
	 */
	private String targetCostDrawingDescId;
	
	/**
	 * 目标成本组成表
	 */
	private String targetCostSheetId;
	
	
	
	
	//private String 
	public String getCheckType1() {
		return checkType1;
	}

	public void setCheckType1(String checkType1) {
		this.checkType1 = checkType1;
	}

	public String getCheckType2() {
		return checkType2;
	}

	public void setCheckType2(String checkType2) {
		this.checkType2 = checkType2;
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

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getTotalBuildingArea() {
		return totalBuildingArea;
	}

	public void setTotalBuildingArea(String totalBuildingArea) {
		this.totalBuildingArea = totalBuildingArea;
	}

	public String getCapacityRatioBuildingArea() {
		return capacityRatioBuildingArea;
	}

	public void setCapacityRatioBuildingArea(String capacityRatioBuildingArea) {
		this.capacityRatioBuildingArea = capacityRatioBuildingArea;
	}

	public String getTotalTargetCost() {
		return totalTargetCost;
	}

	public void setTotalTargetCost(String totalTargetCost) {
		this.totalTargetCost = totalTargetCost;
	}

	public String getCapacityRatioUnitCost() {
		return capacityRatioUnitCost;
	}

	public void setCapacityRatioUnitCost(String capacityRatioUnitCost) {
		this.capacityRatioUnitCost = capacityRatioUnitCost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIndicateOrProductStandardId() {
		return indicateOrProductStandardId;
	}

	public void setIndicateOrProductStandardId(String indicateOrProductStandardId) {
		this.indicateOrProductStandardId = indicateOrProductStandardId;
	}

	public String getTargetCostDrawingDescId() {
		return targetCostDrawingDescId;
	}

	public void setTargetCostDrawingDescId(String targetCostDrawingDescId) {
		this.targetCostDrawingDescId = targetCostDrawingDescId;
	}

	public String getTargetCostSheetId() {
		return targetCostSheetId;
	}

	public void setTargetCostSheetId(String targetCostSheetId) {
		this.targetCostSheetId = targetCostSheetId;
	}
	
	/**
	 * @return the approvePrivFlg
	 */
	public String getApprovePrivFlg() {
		return approvePrivFlg;
	}

	/**
	 * @param approvePrivFlg the approvePrivFlg to set
	 */
	public void setApprovePrivFlg(String approvePrivFlg) {
		this.approvePrivFlg = approvePrivFlg;
	}




	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return totalTargetCost;
	}

	@Override
	public void doImport() {
		// TODO Auto-generated method stub
		//构造台账总表
		CtLedger tmp = new CtLedger();
		tmp.setProjectCd(getProjectCd());
		tmp.setProjectName(getProjectName());
		tmp.setPeriods(getProjectPeriod());
		tmp.setApprovePrivFlg("0");
		if("true".equals(getApprovePrivFlg())){
			tmp.setApprovePrivFlg("1");
		}	
		String creator=getResApproveInfo().getCreator();
		String createdPositionCd=getResApproveInfo().getCreatedPositionCd();
		String createdCenterCd=getResApproveInfo().getCreatedCenterCd();

		if("true".equals(getCheckType1())){
			tmp.setSearchFlg("1");
		}else{
			tmp.setSearchFlg("0");
			if(StringUtils.isNotBlank(capacityRatioUnitCost)){
				tmp.setPlotRateUnitAmt(new BigDecimal(capacityRatioUnitCost.replace(",", "")));
			}
			if(StringUtils.isNotBlank(super.getHotel())&&super.getHotel().equals(true)){
				tmp.setApprovePrivFlg("1");
			}
			if(StringUtils.isNotBlank(totalTargetCost)){
				tmp.setCostTargetTotalAmt(new BigDecimal(totalTargetCost.replace(",", "")));
			}
			if(StringUtils.isNotBlank(getStartDate())){
				tmp.setStartDate(DateOperator.parse(getStartDate(), "yyyy-MM-dd"));
			}
			if(StringUtils.isNotBlank(getSubmitDate())){
				tmp.setHandDate(DateOperator.parse(getSubmitDate(), "yyyy-MM-dd"));
			}
			if(StringUtils.isNotBlank(getTotalBuildingArea())){
				tmp.setTotalConsArea(new BigDecimal(totalBuildingArea.replace(",", "")));
			}
			if(StringUtils.isNotBlank(getCapacityRatioBuildingArea())){
				tmp.setPlotRateArea(new BigDecimal(capacityRatioBuildingArea.replace(",", "")));
			}
			tmp.setCreator(creator);
			tmp.setCreatedCenterCd(createdCenterCd);
			tmp.setCreatedPositionCd(createdPositionCd);
		    tmp.setUpdatedCenterCd(createdCenterCd);
		    tmp.setUpdatedPositionCd(createdPositionCd);
		    tmp.setUpdator(creator);
			tmp.setPlotRateUnitAmt(new BigDecimal(0));
			tmp.setMainContentDesc(remark);
			tmp.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());
			//保存
			CtLedgerManager ctLedgerManager = SpringContextHolder.getBean("ctLedgerManager");
			ctLedgerManager.saveCtLedger(tmp);
		}
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

}
