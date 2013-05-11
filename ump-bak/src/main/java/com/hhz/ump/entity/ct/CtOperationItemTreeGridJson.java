package com.hhz.ump.entity.ct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CtOperationItemTreeGridJson {
	private String ctItemId;
	private String ctLedgerId;
	private String ctDimeCd;
	private String ctContPlanId;
	private Long sequenceNo;

	private Long itemLevel;
	private String itemName;
	private String parentId;
	private String ctOperationId;

	private Date updatedDate;
	private long recordVersion;
	private String checked;
	private String state;
	private String iconCls;
	private String sequenceText;
	/**
	 * @return the sequenceText
	 */
	public String getSequenceText() {
		return sequenceText;
	}

	/**
	 * @param sequenceText the sequenceText to set
	 */
	public void setSequenceText(String sequenceText) {
		this.sequenceText = sequenceText;
	}

	private boolean isShowTreeIcon=false;
	/**
	 * @return the isShowTreeIcon
	 */
	public boolean isShowTreeIcon() {
		return isShowTreeIcon;
	}

	/**
	 * @param isShowTreeIcon the isShowTreeIcon to set
	 */
	public void setShowTreeIcon(boolean isShowTreeIcon) {
		this.isShowTreeIcon = isShowTreeIcon;
	}

	private String remark;
	private BigDecimal targetAmt;
	private String contPlanName;


	private List<CtOperationItemTreeGridJson>children=new ArrayList<CtOperationItemTreeGridJson>();

	/**
	 * @return the ctItemId
	 */
	public String getCtItemId() {
		return ctItemId;
	}

	/**
	 * @param ctItemId
	 *            the ctItemId to set
	 */
	public void setCtItemId(String ctItemId) {
		this.ctItemId = ctItemId;
	}

	/**
	 * @return the ctLedgerId
	 */
	public String getCtLedgerId() {
		return ctLedgerId;
	}

	/**
	 * @param ctLedgerId
	 *            the ctLedgerId to set
	 */
	public void setCtLedgerId(String ctLedgerId) {
		this.ctLedgerId = ctLedgerId;
	}

	/**
	 * @return the ctDimeCd
	 */
	public String getCtDimeCd() {
		return ctDimeCd;
	}

	/**
	 * @param ctDimeCd
	 *            the ctDimeCd to set
	 */
	public void setCtDimeCd(String ctDimeCd) {
		this.ctDimeCd = ctDimeCd;
	}

	/**
	 * @return the sequenceNo
	 */
	public Long getSequenceNo() {
		return sequenceNo;
	}

	/**
	 * @param sequenceNo
	 *            the sequenceNo to set
	 */
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	/**
	 * @return the itemLevel
	 */
	public Long getItemLevel() {
		return itemLevel;
	}

	/**
	 * @param itemLevel
	 *            the itemLevel to set
	 */
	public void setItemLevel(Long itemLevel) {
		this.itemLevel = itemLevel;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the targetAmt
	 */
	public BigDecimal getTargetAmt() {
		return targetAmt;
	}

	/**
	 * @param targetAmt
	 *            the targetAmt to set
	 */
	public void setTargetAmt(BigDecimal targetAmt) {
		this.targetAmt = targetAmt;
	}



	/**
	 * @return the ctOperationId
	 */
	public String getCtOperationId() {
		return ctOperationId;
	}

	/**
	 * @param ctOperationId the ctOperationId to set
	 */
	public void setCtOperationId(String ctOperationId) {
		this.ctOperationId = ctOperationId;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the recordVersion
	 */
	public long getRecordVersion() {
		return recordVersion;
	}

	/**
	 * @param recordVersion
	 *            the recordVersion to set
	 */
	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

	/**
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls
	 *            the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return the ctContPlanId
	 */
	public String getCtContPlanId() {
		return ctContPlanId;
	}

	/**
	 * @param ctContPlanId
	 *            the ctContPlanId to set
	 */
	public void setCtContPlanId(String ctContPlanId) {
		this.ctContPlanId = ctContPlanId;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the contPlanName
	 */
	public String getContPlanName() {
		return contPlanName;
	}

	/**
	 * @param contPlanName
	 *            the contPlanName to set
	 */
	public void setContPlanName(String contPlanName) {
		this.contPlanName = contPlanName;
	}
	/**
	 * @return the children
	 */
	public List<CtOperationItemTreeGridJson> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<CtOperationItemTreeGridJson> children) {
		this.children = children;
	}
}
