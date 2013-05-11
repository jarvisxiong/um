/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 邀标单位审批表
 * 
 * @author huangjian
 * 
 *         2011-3-16
 */
public class StrageInviteUnitProperty {
	private String unitName;// 单位名称
	private String contactUser;// 联系人
	private String contact;// 联系方式
	private String unitLevel;// 供方级别
	private String attacheFileId;//附件ID
	private String supBasicId;// 供应商ID
	private List<String> attacheFileIds = new ArrayList<String>();// 附件IDs

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getContactUser() {
		return contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUnitLevel() {
		return unitLevel;
	}

	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}

	public String getAttacheFileId() {
		return attacheFileId;
	}

	public void setAttacheFileId(String attacheFileId) {
		this.attacheFileId = attacheFileId;
	}

	public List<String> getAttacheFileIds() {
		return attacheFileIds;
	}

	public void setAttacheFileIds(List<String> attacheFileIds) {
		this.attacheFileIds = attacheFileIds;
	}

	public String getSupBasicId() {
		return supBasicId;
	}

	public void setSupBasicId(String supBasicId) {
		this.supBasicId = supBasicId;
	}
}
