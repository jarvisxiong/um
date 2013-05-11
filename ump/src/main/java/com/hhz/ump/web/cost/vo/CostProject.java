package com.hhz.ump.web.cost.vo;

public class CostProject implements java.io.Serializable{

	private String costProjectCd;//项目代码（中心代码center_cd）
	private String costProjectName;//项目名称
	private String dutyPerson;//负责人
	
	public String getCostProjectCd() {
		return costProjectCd;
	}
	public void setCostProjectCd(String costProjectCd) {
		this.costProjectCd = costProjectCd;
	}
	public String getCostProjectName() {
		return costProjectName;
	}
	public void setCostProjectName(String costProjectName) {
		this.costProjectName = costProjectName;
	}
	public String getDutyPerson() {
		return dutyPerson;
	}
	public void setDutyPerson(String dutyPerson) {
		this.dutyPerson = dutyPerson;
	}
	
	
	
	

}
