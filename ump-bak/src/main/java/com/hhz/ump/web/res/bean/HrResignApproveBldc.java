
package com.hhz.ump.web.res.bean;

/** 
 * 地产公司辞职审批表
 * @author liwei
 * 2012-7-9
 */
public class HrResignApproveBldc extends HrResignApproveBase {

	/***
	 * 地产公司#职位等级
	 */
	// 总经理级以上
	private String positionGrade1;
	// 副总经理级
	private String positionGrade2;
	// 总经理级(双线管理人员部门第一负责人除外)
	private String positionGrade3;
	// 双线管理人员部门第一负责人(财务、成本)
	private String positionGrade4;
	// 双线管理人员部门第一负责人(人资)
	private String positionGrade5;
	// 其它人员
	private String positionGrade6;
	
	
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
	/**
	 * @return the positionGrade5
	 */
	public String getPositionGrade5() {
		return positionGrade5;
	}
	/**
	 * @param positionGrade5 the positionGrade5 to set
	 */
	public void setPositionGrade5(String positionGrade5) {
		this.positionGrade5 = positionGrade5;
	}
	/**
	 * @return the positionGrade6
	 */
	public String getPositionGrade6() {
		return positionGrade6;
	}
	/**
	 * @param positionGrade6 the positionGrade6 to set
	 */
	public void setPositionGrade6(String positionGrade6) {
		this.positionGrade6 = positionGrade6;
	}

}
