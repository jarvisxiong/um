/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * 入库试用条件审核
 * @author qlb 3/19/2012
 *
 */
public class SupplierImportLabCond {
   //评估维度
	private String assessDime;
	//入库试用条件
	private String importCont;
	//待评定供方情况
	private String assessSupplierStatus;
	/**
	 * @return the assessDime
	 */
	public String getAssessDime() {
		return assessDime;
	}
	/**
	 * @param assessDime the assessDime to set
	 */
	public void setAssessDime(String assessDime) {
		this.assessDime = assessDime;
	}
	/**
	 * @return the importCont
	 */
	public String getImportCont() {
		return importCont;
	}
	/**
	 * @param importCont the importCont to set
	 */
	public void setImportCont(String importCont) {
		this.importCont = importCont;
	}
	/**
	 * @return the assessSupplierStatus
	 */
	public String getAssessSupplierStatus() {
		return assessSupplierStatus;
	}
	/**
	 * @param assessSupplierStatus the assessSupplierStatus to set
	 */
	public void setAssessSupplierStatus(String assessSupplierStatus) {
		this.assessSupplierStatus = assessSupplierStatus;
	}
	
}
