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
 * 目标成本设定审批表
 * 
 * @author Administrator
 * 
 */
public class CostTargetSetBill extends BaseTemplate implements IAutoImport{

	// 审核权限:是否与酒店有关
	private String approvePrivFlg;

	
	// 目标成本(可研版)
	private String ctSearchFlg;
	// 目标成本
	private String ctFlg;
	

	// 项目编号
	// 项目名称
	// 期数
	private String projectCd;
	private String projectName;
	private String periods;

	// 开工时间
	private String startDate;
	// 交付时间
	private String handDate;

	// 总建筑面积
	private String totalConsArea;
	// 计入容积率建筑面积(m2)
	private String plotRateArea;

	// 目标成本总额(元)
	private String costTargetTotalAmt;
	// 容积率面积单方造价(元/m2)
	private String plotRateUnitAmt;

	// 主要内容及说明
	private String mainContentDesc;

	// 规划指标或产品标准(附件)
	private String productAttachId;
	// 目标成本编制说明(附件)
	private String ctDescAttachId;
	// 目标成本组成表(附件)
	private String ctConsAttachId;

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getApprovePrivFlg() {
		return approvePrivFlg;
	}

	public void setApprovePrivFlg(String approvePrivFlg) {
		this.approvePrivFlg = approvePrivFlg;
	}

	public String getCtSearchFlg() {
		return ctSearchFlg;
	}

	public void setCtSearchFlg(String ctSearchFlg) {
		this.ctSearchFlg = ctSearchFlg;
	}

	public String getCtFlg() {
		return ctFlg;
	}

	public void setCtFlg(String ctFlg) {
		this.ctFlg = ctFlg;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getHandDate() {
		return handDate;
	}

	public void setHandDate(String handDate) {
		this.handDate = handDate;
	}

	public String getTotalConsArea() {
		return totalConsArea;
	}

	public void setTotalConsArea(String totalConsArea) {
		this.totalConsArea = totalConsArea;
	}

	public String getPlotRateArea() {
		return plotRateArea;
	}

	public void setPlotRateArea(String plotRateArea) {
		this.plotRateArea = plotRateArea;
	}

	public String getCostTargetTotalAmt() {
		return costTargetTotalAmt;
	}

	public void setCostTargetTotalAmt(String costTargetTotalAmt) {
		this.costTargetTotalAmt = costTargetTotalAmt;
	}

	public String getPlotRateUnitAmt() {
		return plotRateUnitAmt;
	}

	public void setPlotRateUnitAmt(String plotRateUnitAmt) {
		this.plotRateUnitAmt = plotRateUnitAmt;
	}

	public String getMainContentDesc() {
		return mainContentDesc;
	}

	public void setMainContentDesc(String mainContentDesc) {
		this.mainContentDesc = mainContentDesc;
	}

	public String getProductAttachId() {
		return productAttachId;
	}

	public void setProductAttachId(String productAttachId) {
		this.productAttachId = productAttachId;
	}

	public String getCtDescAttachId() {
		return ctDescAttachId;
	}

	public void setCtDescAttachId(String ctDescAttachId) {
		this.ctDescAttachId = ctDescAttachId;
	}

	public String getCtConsAttachId() {
		return ctConsAttachId;
	}

	public void setCtConsAttachId(String ctConsAttachId) {
		this.ctConsAttachId = ctConsAttachId;
	}

	@Override
	public void doImport() {
		//导入合同台账表
		//构造台账总表
		CtLedger tmp = new CtLedger();
		tmp.setProjectCd(getProjectCd());
		tmp.setProjectName(getProjectName());
		tmp.setPeriods(getPeriods());
		
		if("true".equals(getApprovePrivFlg())){
			tmp.setApprovePrivFlg("1");
		}else{
			tmp.setApprovePrivFlg("0");
		}
		if("true".equals(getCtSearchFlg())){
			tmp.setSearchFlg("1");
		}else{
			tmp.setSearchFlg("0");
		}
		if(StringUtils.isNotBlank(getStartDate())){
			tmp.setStartDate(DateOperator.parse(getStartDate(), "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(getHandDate())){
			tmp.setHandDate(DateOperator.parse(getHandDate(), "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(getTotalConsArea())){
			tmp.setTotalConsArea(new BigDecimal(getTotalConsArea()));
		}
		if(StringUtils.isNotBlank(getPlotRateArea())){
			tmp.setPlotRateArea(new BigDecimal(getPlotRateArea()));
		}
		tmp.setMainContentDesc(getMainContentDesc());
		tmp.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());

		//保存
		CtLedgerManager ctLedgerManager = SpringContextHolder.getBean("ctLedgerManager");
		ctLedgerManager.saveCtLedger(tmp);
	}

	@Override
	public boolean isAutoImport() {
		return true;
	}

}
