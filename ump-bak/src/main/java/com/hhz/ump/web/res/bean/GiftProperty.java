/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**礼品申请表
 * @author huangj
 * 2010-12-25
 */
public class GiftProperty{
	private String giftList;//礼品清单
	private String giftContent;//内容
	private String quantity;//数量
	private String unitPrice;//单价
	public String getGiftList() {
		return giftList;
	}
	public void setGiftList(String giftList) {
		this.giftList = giftList;
	}
	public String getGiftContent() {
		return giftContent;
	}
	public void setGiftContent(String giftContent) {
		this.giftContent = giftContent;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
}
