// 
package com.hhz.uums.vo.log;

// Generated 2010-1-12 16:06:03 by Hibernate Tools 3.2.4.GA

import java.util.Date;

public class LogAppDictData implements java.io.Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6349653030250100214L;
	private String dictCd;
	private String dictName;
	private Integer dispOrderNo;
	private String defaultFlg;
	private String remark;
	private String creator;
	private String createdDeptCd;
	private Date createdDate;
	private String updator;
	private String updatedDeptCd;
	private Date updatedDate;
	private long recordVersion;

	public String getDictCd() {
		return dictCd;
	}
	public void setDictCd(String dictCd) {
		this.dictCd = dictCd;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Integer getDispOrderNo() {
		return dispOrderNo;
	}
	public void setDispOrderNo(Integer dispOrderNo) {
		this.dispOrderNo = dispOrderNo;
	}
	public String getDefaultFlg() {
		return defaultFlg;
	}
	public void setDefaultFlg(String defaultFlg) {
		this.defaultFlg = defaultFlg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedDeptCd() {
		return createdDeptCd;
	}
	public void setCreatedDeptCd(String createdDeptCd) {
		this.createdDeptCd = createdDeptCd;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdatedDeptCd() {
		return updatedDeptCd;
	}
	public void setUpdatedDeptCd(String updatedDeptCd) {
		this.updatedDeptCd = updatedDeptCd;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public long getRecordVersion() {
		return recordVersion;
	}
	public void setRecordVersion(long recordVersion) {
		this.recordVersion = recordVersion;
	}

}
