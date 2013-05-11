package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 标准审批表-名称
 * @author Administrator
 *
 */
public class StandardNameBill extends BaseTemplate {

	private String billName;//名称
	private String remark;//内容简述
	

	public String getBillName() {
		return billName;
	}

	public void setBillName(String landName) {
		this.billName = landName;
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
		return billName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
