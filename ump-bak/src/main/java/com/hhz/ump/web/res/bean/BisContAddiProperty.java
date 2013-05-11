package com.hhz.ump.web.res.bean;

/**
 * 补充合同
 * @author baolm
 * 2011-11-11
 */
public class BisContAddiProperty {
	
    /**
     * 名称
     */
    private String name;
    /**
     * 签订日期
     */
    private String signDate;
    /**
     * 条款
     */
    private String content;
    /**
     * 备注
     */
    private String desc;
    /**
     * 补充合同附件ID
     */
	private String contAddiId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getContAddiId() {
		return contAddiId;
	}
	public void setContAddiId(String contAddiId) {
		this.contAddiId = contAddiId;
	}
	
}
