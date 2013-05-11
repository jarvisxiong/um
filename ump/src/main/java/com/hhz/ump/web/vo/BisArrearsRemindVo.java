package com.hhz.ump.web.vo;

import java.math.BigDecimal;

/**
 * 欠费提醒VO
 * 
 * @author baolm
 */
public class BisArrearsRemindVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6944325866173269257L;
	
	private String id;			//商家ID / 公寓ID / 多经ID
	private String shopTypeCd; 	//租户性质（主力店等）
	private String name; 		//商家名称 / 公寓名称 /多经名称
	private BigDecimal area; 	//面积
	private BigDecimal accumulateMust; //累计应收
	private BigDecimal accumulateFact; //累计实收
	private BigDecimal accumulateRate; //累计实收百分比
	/**
	 * 累计欠费
	 */
	private BigDecimal accumulateArrears;
	/**
	 * 欠费提醒：按照账龄计算
	 */
	private String remindLevel;
	/**
	 * 欠费账龄分析：一级（一个月之内）、二级（一到三个月）、三级（三到六个月）、四级（六到十二个月）、五级（一年以上）
	 */
	private String arrearsAge;
	/**
	 * 保证金额度=租金履约保证金+物业履约保证金
	 */
	private BigDecimal earnestMoney;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopTypeCd() {
		return shopTypeCd;
	}
	public void setShopTypeCd(String shopTypeCd) {
		this.shopTypeCd = shopTypeCd;
	}
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public BigDecimal getAccumulateMust() {
		return accumulateMust;
	}
	public void setAccumulateMust(BigDecimal accumulateMust) {
		this.accumulateMust = accumulateMust;
	}
	public BigDecimal getAccumulateFact() {
		return accumulateFact;
	}
	public void setAccumulateFact(BigDecimal accumulateFact) {
		this.accumulateFact = accumulateFact;
	}
	public BigDecimal getAccumulateRate() {
		return accumulateRate;
	}
	public void setAccumulateRate(BigDecimal accumulateRate) {
		this.accumulateRate = accumulateRate;
	}
	public BigDecimal getAccumulateArrears() {
		return accumulateArrears;
	}
	public void setAccumulateArrears(BigDecimal accumulateArrears) {
		this.accumulateArrears = accumulateArrears;
	}
	public String getRemindLevel() {
		return remindLevel;
	}
	public void setRemindLevel(String remindLevel) {
		this.remindLevel = remindLevel;
	}
	public String getArrearsAge() {
		return arrearsAge;
	}
	public void setArrearsAge(String arrearsAge) {
		this.arrearsAge = arrearsAge;
	}
	public BigDecimal getEarnestMoney() {
		return earnestMoney;
	}
	public void setEarnestMoney(BigDecimal earnestMoney) {
		this.earnestMoney = earnestMoney;
	}
	
}
