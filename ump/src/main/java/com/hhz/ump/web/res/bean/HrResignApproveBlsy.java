
package com.hhz.ump.web.res.bean;

/** 
 * 商业集团辞职审批表
 * @author liwei
 * 2012-7-9
 */
public class HrResignApproveBlsy extends HrResignApproveBase {

	/***
	 * 商业集团#职位等级
	 */
	// 中心总经理级以上人员
	private String positionGrade1;
	// 中心副总经理
	private String positionGrade2;
	// 双线管理人员(财务)
	private String positionGrade3;
	// 其它人员
	private String positionGrade4;
	
	
	/**
	 * @return the positionGrade1
	 */
	public String getPositionGrade1() {
		return positionGrade1;
	}
	/**
	 * @param positionGrade1 the positionGrade1 to set
	 */
	public void setPositionGrade1(String positionGrade1) {
		this.positionGrade1 = positionGrade1;
	}
	/**
	 * @return the positionGrade2
	 */
	public String getPositionGrade2() {
		return positionGrade2;
	}
	/**
	 * @param positionGrade2 the positionGrade2 to set
	 */
	public void setPositionGrade2(String positionGrade2) {
		this.positionGrade2 = positionGrade2;
	}
	/**
	 * @return the positionGrade3
	 */
	public String getPositionGrade3() {
		return positionGrade3;
	}
	/**
	 * @param positionGrade3 the positionGrade3 to set
	 */
	public void setPositionGrade3(String positionGrade3) {
		this.positionGrade3 = positionGrade3;
	}
	/**
	 * @return the positionGrade4
	 */
	public String getPositionGrade4() {
		return positionGrade4;
	}
	/**
	 * @param positionGrade4 the positionGrade4 to set
	 */
	public void setPositionGrade4(String positionGrade4) {
		this.positionGrade4 = positionGrade4;
	}
	
	
	
}
