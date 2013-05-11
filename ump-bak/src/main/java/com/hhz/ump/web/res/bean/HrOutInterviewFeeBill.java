/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**外地人员面试费用确认单
 * @author huangj
 * 2010-12-24
 */
public class HrOutInterviewFeeBill  extends BaseTemplate {
	private String userName;//姓名
	private String starStopPlace;//起止地
	private String positionName;//应聘岗位
	private String shangHaiDate;//来沪日期
	private String arriveDate;//抵达日期
	private String remark;//备注
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
		return null;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return positionName;
	}
	public String getStarStopPlace() {
		return starStopPlace;
	}
	public void setStarStopPlace(String starStopPlace) {
		this.starStopPlace = starStopPlace;
	}
	public String getShangHaiDate() {
		return shangHaiDate;
	}
	public void setShangHaiDate(String shangHaiDate) {
		this.shangHaiDate = shangHaiDate;
	}
	public String getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
