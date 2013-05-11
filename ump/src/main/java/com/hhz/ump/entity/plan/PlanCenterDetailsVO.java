package com.hhz.ump.entity.plan;

import java.util.ArrayList;
import java.util.List;

public class PlanCenterDetailsVO {
	private String mainId;
	private String projectCd;
	private String projectName;
	private int preDelaySum;//前期延期数
	private int preRecentSum;//前期近期数
	private int execDelaySum;//执行计划延期数
	private int execRecentSum;//执行计划近期数
	private boolean isExecSum;//true:有执行计划，但在近期一个月内没有任务
	private List<PlanDetailVO> planDetailVO = new ArrayList<PlanDetailVO>();

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<PlanDetailVO> getPlanDetailVO() {
		return planDetailVO;
	}

	public void setPlanDetailVO(List<PlanDetailVO> planDetailVO) {
		this.planDetailVO = planDetailVO;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public int getPreDelaySum() {
		return preDelaySum;
	}

	public void setPreDelaySum(int preDelaySum) {
		this.preDelaySum = preDelaySum;
	}

	public int getPreRecentSum() {
		return preRecentSum;
	}

	public void setPreRecentSum(int preRecentSum) {
		this.preRecentSum = preRecentSum;
	}

	public int getExecDelaySum() {
		return execDelaySum;
	}

	public void setExecDelaySum(int execDelaySum) {
		this.execDelaySum = execDelaySum;
	}

	public int getExecRecentSum() {
		return execRecentSum;
	}

	public void setExecRecentSum(int execRecentSum) {
		this.execRecentSum = execRecentSum;
	}

	public boolean isExecSum() {
		return isExecSum;
	}

	public void setExecSum(boolean isExecSum) {
		this.isExecSum = isExecSum;
	}

}
