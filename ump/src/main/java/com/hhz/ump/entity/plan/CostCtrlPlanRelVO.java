package com.hhz.ump.entity.plan;

import java.util.ArrayList;
import java.util.List;

public class CostCtrlPlanRelVO {

	private String costCd;//类型：招标、采购
	private String costName;
	private int costSize;
	private int currentMonth;
	private int nextMonth;
	private List<CostDetailVO> costDetailList =new ArrayList<CostDetailVO>();

	public String getCostCd() {
		return costCd;
	}
	public void setCostCd(String costCd) {
		this.costCd = costCd;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public List<CostDetailVO> getCostDetailList() {
		return costDetailList;
	}
	public void setCostDetailList(List<CostDetailVO> costDetailList) {
		this.costDetailList = costDetailList;
	}
	public int getCostSize() {
		return costSize;
	}
	public void setCostSize(int costSize) {
		this.costSize = costSize;
	}
	public int getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	public int getNextMonth() {
		return nextMonth;
	}
	public void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth;
	}
	
}
