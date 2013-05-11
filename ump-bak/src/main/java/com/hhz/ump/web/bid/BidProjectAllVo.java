package com.hhz.ump.web.bid;

import java.math.BigDecimal;


public class BidProjectAllVo implements  java.io.Serializable{

    private String projectName;
    private BigDecimal amtOne;
    private BigDecimal amtTwo;
    private BigDecimal amtThree;
    private BigDecimal amtFour;
    private BigDecimal amtFive;
    private BigDecimal amtSix;
    private BigDecimal amtSeven;
	public String getProjectName() {
		return projectName;
	}
	public BigDecimal getAmtOne() {
		return amtOne;
	}
	public BigDecimal getAmtTwo() {
		return amtTwo;
	}
	public BigDecimal getAmtThree() {
		return amtThree;
	}
	public BigDecimal getAmtFour() {
		return amtFour;
	}
	public BigDecimal getAmtFive() {
		return amtFive;
	}
	public BigDecimal getAmtSix() {
		return amtSix;
	}
	public BigDecimal getAmtSeven() {
		return amtSeven;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setAmtOne(BigDecimal amtOne) {
		this.amtOne = amtOne;
	}
	public void setAmtTwo(BigDecimal amtTwo) {
		this.amtTwo = amtTwo;
	}
	public void setAmtThree(BigDecimal amtThree) {
		this.amtThree = amtThree;
	}
	public void setAmtFour(BigDecimal amtFour) {
		this.amtFour = amtFour;
	}
	public void setAmtFive(BigDecimal amtFive) {
		this.amtFive = amtFive;
	}
	public void setAmtSix(BigDecimal amtSix) {
		this.amtSix = amtSix;
	}
	public void setAmtSeven(BigDecimal amtSeven) {
		this.amtSeven = amtSeven;
	}
    
}
