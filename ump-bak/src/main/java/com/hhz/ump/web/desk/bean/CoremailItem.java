/**
 * 
 */
package com.hhz.ump.web.desk.bean;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hhz.core.utils.DateOperator;
import com.lowagie.text.html.HtmlEncoder;

/**
 * coremail单封邮件
 * 
 * @author huangjian
 * 
 *         2011-7-14
 */
public class CoremailItem {
	private String mid;// 邮件ID
	private String fid;// 信箱,1=收件箱
	private String size;// 邮件大小
	private String subject;//主题
	private String from;//发件人
	private String to;//收件人
	private Date sentDate;// 发送时间
	private Date receivedDate;// 接收时间
	private String priority;// 优先级
	private String antiVirusStatus;// 是否病毒邮件
	private Boolean read = new Boolean(false);//是否已读
	private Boolean replied = new Boolean(false);//是否回复
	private Boolean forwarded = new Boolean(false);//是否转发
	private Boolean attached = new Boolean(false);//是否有附件
	private String fromUserName;// 邮箱姓名
	
	private String sendDateDesc;//发送时间描述

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject =HtmlEncoder.encode(subject);
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
		setFromUserName(from);
	}
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAntiVirusStatus() {
		return antiVirusStatus;
	}

	public void setAntiVirusStatus(String antiVirusStatus) {
		this.antiVirusStatus = antiVirusStatus;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getReplied() {
		return replied;
	}

	public void setReplied(Boolean replied) {
		this.replied = replied;
	}

	public Boolean getForwarded() {
		return forwarded;
	}

	public void setForwarded(Boolean forwarded) {
		this.forwarded = forwarded;
	}

	public Boolean getAttached() {
		return attached;
	}

	public void setAttached(Boolean attached) {
		this.attached = attached;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String from) {
		String regEx1 = "\"[^\"]*\"";
		String regEx2 = "<.+@";
		Pattern p = Pattern.compile(regEx1);
		Matcher m = p.matcher(from);
		boolean rs = m.find();
		if (rs) {
			String str = m.group();
			if (str.equals("\"\"")) {
				p = Pattern.compile(regEx2);
				m = p.matcher(from);
				rs = m.find();
				if (rs) {
					str = m.group();
				}
			}
			this.fromUserName=str.substring(1, str.length() - 1);
		}else{
			p = Pattern.compile(".+@");
			m = p.matcher(from);
			rs =m.find();
			if (rs) {
				String str = m.group();
				this.fromUserName=str.substring(0, str.length() - 1);
			}
		}
	}

	public String getSendDateDesc() {
		
		if(sentDate != null){
			Date today=DateOperator.truncDate(new Date());
			Date tmpDate=DateOperator.truncDate(sentDate);
			//今天
			if(DateOperator.compareDays(today, tmpDate)== 0){
				sendDateDesc = "今天 " + DateOperator.formatDate(sentDate, "yyyy-MM-dd(EEE) HH:mm");
			}
			//昨天
			else if(DateOperator.compareDays(today, tmpDate)== -1){
				sendDateDesc = "昨天 " + DateOperator.formatDate(sentDate, "yyyy-MM-dd(EEE) HH:mm");
			}
			//前天
			else if(DateOperator.compareDays(today, tmpDate)== -2){
				sendDateDesc = "前天 " + DateOperator.formatDate(sentDate, "yyyy-MM-dd(EEE) HH:mm");
			}
			else{
				sendDateDesc = DateOperator.formatDate(sentDate, "yyyy-MM-dd(EEE) HH:mm");
			}
		}
		
		return sendDateDesc;
	}

	public void setSendDateDesc(String sendDateDesc) {
		this.sendDateDesc = sendDateDesc;
	}

}
