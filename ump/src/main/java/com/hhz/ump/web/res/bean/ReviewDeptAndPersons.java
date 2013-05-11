/**
 * 
 */
package com.hhz.ump.web.res.bean;

/**
 * <p>考察部门及参与人员	</p>
 * 
 * @author  qlb
 * @version 1.00 2011-11-30
 */
public class ReviewDeptAndPersons {
	//部门
	private String department;
	//姓名
	private String firstName;
	private String userCd;
	private String posCd;
	
	/**
	 * @return the userCd
	 */
	public String getUserCd() {
		return userCd;
	}

	/**
	 * @param userCd the userCd to set
	 */
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	/**
	 * @return the posCd
	 */
	public String getPosCd() {
		return posCd;
	}

	/**
	 * @param posCd the posCd to set
	 */
	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}

	//职务
	private String post;

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}

}
