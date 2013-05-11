package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
//商业公司人事审批表
public class BizPersonnelApprove extends BaseTemplate {

	private String positionLevel1;//总经理
	private String positionLevel2;//副总经理
	private String positionLevel3;//高级经理级(除垂直管理人员外)
	private String positionLevel4;//垂直管理人员
	private String positionLevel5;//其他人员
	
	private String personName;//姓名
	private String stationName;//岗位名称
	private String personDept;//所在单位
	private String rank;//职级
	private String enterDate;//入职日期
	private String personNo;//员工编号
	private String tryOut;//试用
	private String regularWorker;//转正
	private String remove;//任免
	private String upSalary;//调薪
	private String reward;//奖励
	private String punish;//处罚
	private String dismiss;//辞退
	private String contentDesc;//简要说明
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getPersonDept() {
		return personDept;
	}
	public void setPersonDept(String personDept) {
		this.personDept = personDept;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getPersonNo() {
		return personNo;
	}
	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}
	public String getTryOut() {
		return tryOut;
	}
	public void setTryOut(String tryOut) {
		this.tryOut = tryOut;
	}
	public String getRegularWorker() {
		return regularWorker;
	}
	public void setRegularWorker(String regularWorker) {
		this.regularWorker = regularWorker;
	}
	public String getRemove() {
		return remove;
	}
	public void setRemove(String remove) {
		this.remove = remove;
	}
	public String getUpSalary() {
		return upSalary;
	}
	public void setUpSalary(String upSalary) {
		this.upSalary = upSalary;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getPunish() {
		return punish;
	}
	public void setPunish(String punish) {
		this.punish = punish;
	}
	public String getDismiss() {
		return dismiss;
	}
	public void setDismiss(String dismiss) {
		this.dismiss = dismiss;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	public String getPositionLevel1() {
		return positionLevel1;
	}
	public void setPositionLevel1(String positionLevel1) {
		this.positionLevel1 = positionLevel1;
	}
	public String getPositionLevel2() {
		return positionLevel2;
	}
	public void setPositionLevel2(String positionLevel2) {
		this.positionLevel2 = positionLevel2;
	}
	public String getPositionLevel3() {
		return positionLevel3;
	}
	public void setPositionLevel3(String positionLevel3) {
		this.positionLevel3 = positionLevel3;
	}
	public String getPositionLevel4() {
		return positionLevel4;
	}
	public void setPositionLevel4(String positionLevel4) {
		this.positionLevel4 = positionLevel4;
	}
	public String getPositionLevel5() {
		return positionLevel5;
	}
	public void setPositionLevel5(String positionLevel5) {
		this.positionLevel5 = positionLevel5;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return stationName;
	}
}
