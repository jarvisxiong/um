package com.hhz.ump.web.vo;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.hhz.ump.util.Arith;

public class CostStatisticsArea {
	 
	private String qy;
	private String uiid;
	private BigDecimal wwc = new BigDecimal(0);
	private BigDecimal aqwc = new BigDecimal(0);
	private BigDecimal yqwc = new BigDecimal(0);
 	
	private String rowTypeCd; //1-明细 2-小计 3-合计
	 
	public String getRowTypeCd() {
		return rowTypeCd;
	}
	public void setRowTypeCd(String rowTypeCd){
		this.rowTypeCd = rowTypeCd;
	}
	public String getQy() {
		return qy;
	}
	public void setQy(String qy) {
		this.qy = qy;
	}
	public String getUiid() {
		return uiid;
	}
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	public BigDecimal getWwc() {
		return wwc;
	}
	public void setWwc(BigDecimal wwc) {
		this.wwc = wwc;
	}
	public BigDecimal getAqwc() {
		return aqwc;
	}
	public void setAqwc(BigDecimal aqwc) {
		this.aqwc = aqwc;
	}
	public BigDecimal getYqwc() {
		return yqwc;
	}
	public void setYqwc(BigDecimal yqwc) {
		this.yqwc = yqwc;
	}

	public BigDecimal getZwc() {
		return BigDecimal.valueOf(aqwc.longValue() + yqwc.longValue());
	} 
	public BigDecimal getTotal() {
		return BigDecimal.valueOf(wwc.longValue() + aqwc.longValue() + yqwc.longValue());
	}
	public double getWwcRate() {
		if(getTotal().doubleValue() == 0)
			return 0;
		else
			return Arith.round(Arith.div(getWwc(), getTotal(), 4)*100,2);
	}
	public double getAqwcRate() {
		if(getTotal().doubleValue() == 0)
			return 0;
		else
			return Arith.round(Arith.div(getAqwc(), getTotal(), 4)*100,2);
	}
	public double getYqwcRate() {
		if(getTotal().doubleValue() == 0)
			return 0;
		else
			return Arith.round(Arith.div(getYqwc(), getTotal(), 4)*100,2);
	}
	public double getZwcRate() {
		if(getTotal().doubleValue() == 0)
			return 0;
		else
			return Arith.round(Arith.div(getZwc(), getTotal(), 4)*100,2);
	}
	public void appendValue(CostStatisticsArea tmp) {

		if(StringUtils.isBlank(this.getQy()) && "2".equals(this.getRowTypeCd())){
			setQy(tmp.getQy());
			setUiid("小计");
		}
		if(StringUtils.isBlank(this.getQy()) && "3".equals(this.getRowTypeCd())){
			setQy("total");
			setUiid("合计");
		}
		setWwc(new BigDecimal(getWwc().doubleValue() + tmp.getWwc().doubleValue()));
		setAqwc(new BigDecimal(getAqwc().doubleValue() + tmp.getAqwc().doubleValue()));
		setYqwc(new BigDecimal(getYqwc().doubleValue() + tmp.getYqwc().doubleValue()));
		
		//System.out.println("["+this.getQy()+"] wwc:"+getWwc().doubleValue() + ",aqwc:" +getAqwc().doubleValue()+ ",yqwc:" + getYqwc().doubleValue());
	}
	public static void main(String[] args) {
		System.out.println(new BigDecimal(1/2.00).doubleValue());
	}
}
