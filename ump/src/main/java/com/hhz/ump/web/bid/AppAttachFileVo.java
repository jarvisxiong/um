package com.hhz.ump.web.bid;

public class AppAttachFileVo implements java.io.Serializable {

	String fileName;
	String realFileName;
	String bizModuleCd;
	String appAttachFileId;
	String fieldName;
	String bizFieldName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public String getBizModuleCd() {
		return bizModuleCd;
	}

	public void setBizModuleCd(String bizModuleCd) {
		this.bizModuleCd = bizModuleCd;
	}


	public String getAppAttachFileId() {
		return appAttachFileId;
	}

	public void setAppAttachFileId(String appAttachFileId) {
		this.appAttachFileId = appAttachFileId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getBizFieldName() {
		return bizFieldName;
	}

	public void setBizFieldName(String bizFieldName) {
		this.bizFieldName = bizFieldName;
	}

}
