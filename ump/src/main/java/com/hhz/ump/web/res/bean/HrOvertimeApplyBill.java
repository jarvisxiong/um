/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**加班申请表
 * @author huangj
 * 2010-12-24
 */
public class HrOvertimeApplyBill  extends BaseTemplate {
	private String centerName;//中心
	private String centerCd;//中心
	private String deptName;//部门
	private List<HrOvertimeProperty> otherProperties=new ArrayList<HrOvertimeProperty>();
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterCd() {
		return centerCd;
	}
	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<HrOvertimeProperty> getOtherProperties() {
		return otherProperties;
	}
	public void setOtherProperties(List<HrOvertimeProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return centerCd;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return centerName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return deptName;
	}
}
