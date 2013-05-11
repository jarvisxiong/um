/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 战略邀标审批表
 * 
 * @author huangjian
 * 
 * 
 *         2011-3-16
 */
public class StrageInviteUnitBill extends BaseTemplate implements IAutoImport {
	
	private static final Log log = LogFactory.getLog(StrageInviteUnitBill.class);
	
	/********如下属性是根据最新需求追加的,date：2012.5.17  author： wangming ------------start******/
	private String projectCd;      //项目名称Cd
	private String projectName;//项目名称
	private String periodNum; //期数
	private String construction;//工程
	/*******--------------end*******/
	
	private String strageName;// 战略名称 
	private String showFlag;// 明标
	private String hideFlag;// 暗标
	private String remark;//备注

	private List<StrageInviteUnitProperty> otherProperties = new ArrayList<StrageInviteUnitProperty>();

	//2012-06-26 add by huangbijin
	//招标采购ID
	private String ccbpId;
	//招标采购编号(serailType+no)
	private String ccbpNo; 
	//招标采购事项
	private String ccbpPlanContentDesc;
	
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

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public String getStrageName() {
		return strageName;
	}

	public void setStrageName(String strageName) {
		this.strageName = strageName;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<StrageInviteUnitProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<StrageInviteUnitProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	@Override
	public void doImport() {

		BidLedgerManager bidLedgerManager = SpringContextHolder.getBean("bidLedgerManager");
		bidLedgerManager.importStrageBean(this);
	}

	@Override
	public boolean isAutoImport() {
		return true;
	}

	public String getCcbpId() {
		return ccbpId;
	}

	public void setCcbpId(String ccbpId) {
		this.ccbpId = ccbpId;
	}

	public String getCcbpNo() {
		return ccbpNo;
	}

	public void setCcbpNo(String ccbpNo) {
		this.ccbpNo = ccbpNo;
	}

	public String getCcbpPlanContentDesc() {
		return ccbpPlanContentDesc;
	}

	public void setCcbpPlanContentDesc(String ccbpPlanContentDesc) {
		this.ccbpPlanContentDesc = ccbpPlanContentDesc;
	}
	
}
