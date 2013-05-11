package com.hhz.ump.web.res.bean;

/**
 * <p>使用档案资料名称</p>
 * 
 * @author  hy
 * @version 1.00 2012-1-6
 */

public class ArchivesInfo {

	//名称
	private String archivesName;
	
	/**
	 * @return the archivesName
	 */
	public String getArchivesName() {
		return archivesName;
	}

	/**
	 * @param archivesName the archivesName to set
	 */
	public void setArchivesName(String archivesName) {
		this.archivesName = archivesName;
	}

	/**
	 * @return the archivesIndexNum
	 */
	public String getArchivesIndexNum() {
		return archivesIndexNum;
	}

	/**
	 * @param archivesIndexNum the archivesIndexNum to set
	 */
	public void setArchivesIndexNum(String archivesIndexNum) {
		this.archivesIndexNum = archivesIndexNum;
	}

	/**
	 * @return the pageNum
	 */
	public String getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	//档案检索号
	private String archivesIndexNum;
	
	//页数
	private String pageNum;
}
