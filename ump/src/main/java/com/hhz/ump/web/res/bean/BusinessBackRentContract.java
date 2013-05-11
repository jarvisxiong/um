/**
 * 
 */
package com.hhz.ump.web.res.bean;


/**
 * <p>
 * 商业公司返租合同信息
 * </p>
 * 
 * @author qlb
 * @version 1.00 2023-01-13
 */
public class BusinessBackRentContract {
	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 账户名（或业主）
	 */
	private String owner;
	/**
	 * 商铺位置
	 */
	private String shopPosition;

	/**
	 * 租金所属期
	 */
	private String blongPeriod;
	/**
	 * 实付定金
	 */
	private String downPayment;

	/**
	 * @return the blongPeriod
	 */
	public String getBlongPeriod() {
		return blongPeriod;
	}

	/**
	 * @param blongPeriod the blongPeriod to set
	 */
	public void setBlongPeriod(String blongPeriod) {
		this.blongPeriod = blongPeriod;
	}



	/**
	 * @return the downPayment
	 */
	public String getDownPayment() {
		return downPayment;
	}

	/**
	 * @param downPayment the downPayment to set
	 */
	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}

	/**
	 * @return the contNo
	 */
	public String getContNo() {
		return contNo;
	}

	/**
	 * @param contNo
	 *            the contNo to set
	 */
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the shopPosition
	 */
	public String getShopPosition() {
		return shopPosition;
	}

	/**
	 * @param shopPosition
	 *            the shopPosition to set
	 */
	public void setShopPosition(String shopPosition) {
		this.shopPosition = shopPosition;
	}

}
