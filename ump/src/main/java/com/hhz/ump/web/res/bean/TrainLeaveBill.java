/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**培训请假单
 * @author huangj
 * 2010-12-21
 */
public class TrainLeaveBill  extends BaseTemplate {
	private String userName;//申请人姓名
	private String deptName;//部门
	private String positionName;//职务
	private String trainName;//培训项目
	private String trainDate;//培训日期
	private String reasonDesc;//请假事由
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return deptName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return trainName;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainDate() {
		return trainDate;
	}
	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
}
