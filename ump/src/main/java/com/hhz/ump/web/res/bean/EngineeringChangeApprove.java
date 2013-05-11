package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 工程变更审批表
 * @author lujunyun 2010-12-21
 */
public class EngineeringChangeApprove extends BaseTemplate {
	private String engineeringName;	//工程名称
	private String subName;	//子项名称
	private String changeMoney;	//增减的费用(万元)
	private String addUpRate;	//累计变更比例
	private String addUpMoney;	//累计变更额(万元)
	private String contentAndReason;	//变更内容及原因

	
	
	public String getEngineeringName() {
		return engineeringName;
	}

	public void setEngineeringName(String engineeringName) {
		this.engineeringName = engineeringName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}

	public String getAddUpRate() {
		return addUpRate;
	}

	public void setAddUpRate(String addUpRate) {
		this.addUpRate = addUpRate;
	}

	public String getAddUpMoney() {
		return addUpMoney;
	}

	public void setAddUpMoney(String addUpMoney) {
		this.addUpMoney = addUpMoney;
	}

	public String getContentAndReason() {
		return contentAndReason;
	}

	public void setContentAndReason(String contentAndReason) {
		this.contentAndReason = contentAndReason;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return engineeringName;
	}
}
