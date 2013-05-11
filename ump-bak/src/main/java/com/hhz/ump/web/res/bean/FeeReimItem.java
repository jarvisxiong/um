/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>
 * 费用报销明细
 * </p>
 * 
 * @author huangjian
 * @create 2011-9-20
 */
public class FeeReimItem {
	private String docNumber;// 单据数
	private String money;// 金额
	private String remark;// 说明
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
