package com.hhz.ump.web.desk.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CoremailCondition {
	private  Log log = LogFactory.getLog(getClass());
	// http://邮箱域名或ip/coremail/demo/message/listMessages.jsp?fid=1&sid=BAudECbbAZbVHXxEhWbbISTDBDTQAmbX&page_no=1&itemPerPage=10

	// 查看邮件列表
	// 参数列表：
	// sid - 可以通过 login/doLogin.jsp 获得
	// fid - 邮件所在的文件夹
	// page_no - 翻到第几页
	// 1 表示第 1 页（缺省值）
	// 2 表示第 2 页
	// ...依此类推
	// itemPerPage - 每页显示的邮件数
	// order - 排序字段
	// date 按日期排序（缺省值）
	// subject 按标题排序
	// from 按发件人排序
	// to 按收件人排序
	// size 按大小排序
	// desc - 排序方式
	// true 为降序排序（缺省值）
	// false 为升序排序

	private String sid;
	private String fid = "1"; // 默认1-收件箱
	private String pageNo = "1";// 默认1-第1页
	private String itemPerPage = "10";
	private String order = "date";
	private String desc = "true";

	public String getParams() {
		String params = new StringBuffer()
		/* .append("sid=") */.append(getSid()).append("&fid=").append(getFid())
				.append("&page_no=").append(getPageNo())
				.append("&itemPerPage=").append(getItemPerPage()).append(
						"&order=").append(getOrder()).append("&desc=").append(
						"true").toString();

		log.info("请求参数:" + params);
		return params.toString();
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(String itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
