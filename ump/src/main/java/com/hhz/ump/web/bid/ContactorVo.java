/**  
 * ContactorVo.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-10-19        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
 */

package com.hhz.ump.web.bid;

/**
 * ClassName:ContactorVo Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2011-10-19 下午01:46:33
 * 
 * 
 */
public class ContactorVo implements java.io.Serializable {
	private String contactId;
	private String name;
	private String mail;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	

}
