package com.hhz.ump.web.res.bean;

public class SlotCardProperty {

	// 日期 班次 实际打卡时间 未刷卡原因 编辑
	private String slotDate;//MM-dd
	private String selectMorning1;
	private String selectMorning2;
	private String selectAfternoon1;
	private String selectAfternoon2;
	private String realSlotDate;//hh-mm
	private String noSlotReasonDesc;
	
	public String getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	public String getSelectMorning1() {
		return selectMorning1;
	}
	public void setSelectMorning1(String selectMorning1) {
		this.selectMorning1 = selectMorning1;
	}
	public String getSelectMorning2() {
		return selectMorning2;
	}
	public void setSelectMorning2(String selectMorning2) {
		this.selectMorning2 = selectMorning2;
	}
	public String getSelectAfternoon1() {
		return selectAfternoon1;
	}
	public void setSelectAfternoon1(String selectAfternoon1) {
		this.selectAfternoon1 = selectAfternoon1;
	}
	public String getSelectAfternoon2() {
		return selectAfternoon2;
	}
	public void setSelectAfternoon2(String selectAfternoon2) {
		this.selectAfternoon2 = selectAfternoon2;
	}
	public String getRealSlotDate() {
		return realSlotDate;
	}
	public void setRealSlotDate(String realSlotDate) {
		this.realSlotDate = realSlotDate;
	}
	public String getNoSlotReasonDesc() {
		return noSlotReasonDesc;
	}
	public void setNoSlotReasonDesc(String noSlotReasonDesc) {
		this.noSlotReasonDesc = noSlotReasonDesc;
	}
}
