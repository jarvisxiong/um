package com.hhz.ump.web.webim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.webim.UserMessageManager;
import com.hhz.ump.entity.webim.UserMessage;
import com.hhz.ump.util.Constants;

@Namespace("/webim")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "user-message.action", type = "redirect") })
public class UserMessageAction extends CrudActionSupport<UserMessage> {

	private static final long serialVersionUID = -3445152342227169047L;

	private Page<UserMessage> page = new Page<UserMessage>(15);// 每页10条记录

	@Autowired
	private UserMessageManager userMessageManager;

	private UserMessage entity;

	private String filter_LIKES_pageName;

	private String filter_EQS_pageCd;

	// 这里申明,前台用来判断本人或他人消息,设置聊天内容样式
	private String currentUserCd;
	private String chattorCd;

	/**
	 * 传送文件名
	 */
	private String filedataFileName;
	private File filedata;

	public UserMessageAction() {
	}

	public UserMessage getModel() {
		return entity;
	}

	public String getFilter_LIKES_pageName() {
		return filter_LIKES_pageName;
	}

	public void setFilter_LIKES_pageName(String filterLIKESPageName) {
		filter_LIKES_pageName = filterLIKESPageName;
	}

	public String getFilter_EQS_pageCd() {
		return filter_EQS_pageCd;
	}

	public void setFilter_EQS_pageCd(String filterEQSPageCd) {
		filter_EQS_pageCd = filterEQSPageCd;
	}

	@Override
	public Page<UserMessage> getPage() {
		return page;
	}

	@Override
	protected void prepareModel() throws Exception {
		String UserMessageId = getId();
		if (StringUtils.isNotBlank(UserMessageId)) {
			entity = userMessageManager.getEntity(UserMessageId);
		} else {
			entity = new UserMessage();
		}
	}

	@Override
	public String list() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		chattorCd = request.getParameter("chattorCd");
		commonQuery(chattorCd);

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {

		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {

		return RELOAD;
	}

	/**
	 * 用于邮件里富文本编辑器的文件上传
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception {
		String fileName = genFileName(filedataFileName);
		String absPath = Struts2Utils.getRequest().getContextPath();
		String realPath = ServletActionContext.getServletContext().getRealPath("");
		File dir = new File(realPath + File.separator + "upload" + File.separator + "chat" + File.separator
				+ SpringSecurityUtils.getCurrentUiid());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File fout = new File(dir, fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		if (filedata.length() > Constants.MAX_FILE_SIZE) {
			Struts2Utils.renderText("{'err':'传送文件最大不能超过" + Constants.MAX_FILE_SIZE / (1024 * 1000) + "M','msg':''}");
			filedata.delete();
			return null;
		}
		FileInputStream fin = new FileInputStream(filedata);

		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fin.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		String path = absPath + "/upload/chat/" + SpringSecurityUtils.getCurrentUiid() + "/" + fileName;
		Struts2Utils.renderText("{'err':'','msg':'" + path + "','title':'" + filedataFileName + "'}");
		return null;
	}

	/*
	 * public Map<String, String> getMapTalkMember() { String myUserCd =
	 * SpringSecurityUtils.getCurrentUserCd(); String hql =
	 * " select distinct( from UserMessage where fromUserCd = :myUserCd or toUserCd = :myUserCd  order by sendDate desc "
	 * ; Map<String, Object> pram = new HashMap<String, Object>();
	 * pram.put("myUserCd", myUserCd);
	 * 
	 * userMessageManager.find(hql, pram); }
	 */

	private String genFileName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		Date now = new Date();
		String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss");
		String genName = dateFormat + RandomUtils.generateString(4) + suffix;
		return genName;
	}

	public String getCurrentUserCd() {
		return currentUserCd;
	}

	public void setCurrentUserCd(String currentUserCd) {
		this.currentUserCd = currentUserCd;
	}

	// 发送人列表
	public Map<String, String> getMapSendChattors() {
		currentUserCd = SpringSecurityUtils.getCurrentUserCd();
		String hqlSend = " select distinct t.fromUserCd,t.fromUserName from UserMessage t where t.toUserCd=? ";
		Map<String,String> map = new HashMap<String,String>();
		map.put("", "");
		List<Object[]> list = userMessageManager.getDao().createQuery(hqlSend, currentUserCd).list();
		for (int i = 0; i < list.size(); i++) {
			Object[] tmp = list.get(i);
			String userCd = (String) tmp[0];
			String userName = (String) tmp[1];
			if (map.containsKey(userCd)) {
				continue;
			} else {
				map.put(userCd, userName);
			}
		}
		return map;
	}

	// 接收人列表
	public Map<String, String> getMapReceiveChattors() {
		currentUserCd = SpringSecurityUtils.getCurrentUserCd();
		String hqlReceive = " select distinct t.toUserCd,t.toUserName from UserMessage t where t.fromUserCd=? ";
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		List<Object[]> list = userMessageManager.getDao().createQuery(hqlReceive, currentUserCd).list();
		for (int i = 0; i < list.size(); i++) {
			Object[] tmp = list.get(i);
			String userCd = (String) tmp[0];
			String userName = (String) tmp[1];
			if (map.containsKey(userCd)) {
				continue;
			} else {
				map.put(userCd, userName);
			}
		}
		return map;
	}

	// 所有联系人
	public Map<String, String> getMapAllChattors() {
		Map<String, String> map = getMapSendChattors();
		Map<String, String> mapReceive = getMapReceiveChattors();
		for (Iterator receive = mapReceive.keySet().iterator(); receive.hasNext();) {
			String userCd = (String) receive.next();
			String userName = (String) mapReceive.get(userCd);
			if (map.containsKey(userCd)) {
				continue;
			} else {
				map.put(userCd, userName);
			}
		}
		return map;
	}

	// 选择联系人,搜索历史聊天记录
	public String searchChatHistory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		chattorCd = request.getParameter("chattorCd");
		commonQuery(chattorCd);
		return SUCCESS;
	}

	private void commonQuery(String chattorUserCd) {
		// 很重要
		currentUserCd = SpringSecurityUtils.getCurrentUserCd();

		StringBuffer hqlBuffer = new StringBuffer().append(" from UserMessage ").append(
				" where (fromUserCd = :myUserCd ").append(
				StringUtils.isNotBlank(chattorUserCd) ? (" and toUserCd = :toUserCd") : "").append(
				") or (toUserCd = :myUserCd ").append(
				StringUtils.isNotBlank(chattorUserCd) ? (" and fromUserCd = :toUserCd") : "").append(
				") order by sendDate desc ");
		String hql = hqlBuffer.toString();
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("myUserCd", currentUserCd);
		pram.put("toUserCd", chattorUserCd);

		page = userMessageManager.findPage(page, hql, pram);
	}

	public String getChattorCd() {
		return chattorCd;
	}

	public void setChattorCd(String chattorCd) {
		this.chattorCd = chattorCd;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}
}
