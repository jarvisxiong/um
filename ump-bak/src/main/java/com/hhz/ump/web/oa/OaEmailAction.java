package com.hhz.ump.web.oa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.dao.oa.OaEmailGroupManager;
import com.hhz.ump.dao.oa.OaEmailManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaEmail;
import com.hhz.ump.entity.oa.OaEmailBody;
import com.hhz.ump.entity.oa.OaEmailGroup;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "oa-email!list.action", type = "redirect", params = { "boxId", "${boxId}",
		"pageEmail.pageSize", "${pageEmail.pageSize}", "pageEmail.pageNo", "${pageEmail.pageNo}", "filter_LIKES_sender", "${filter_LIKES_sender}",
		"filter_LIKEC_toUserNames", "${filter_LIKEC_toUserNames}", "filter_LIKES_subject", "${filter_LIKES_subject}" }) })
public class OaEmailAction extends CrudActionSupport<OaEmailBody> {

	private static final long serialVersionUID = -4075294463166517739L;

	@Autowired
	private AppParamManager appParamManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	@Autowired
	private OaEmailManager oaEmailManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private OaEmailGroupManager oaEmailGroupManager;

	private static final String IN_BOX = "1";// 收件箱
	private static final String DRAFT_BOX = "2";// 草稿箱
	private static final String OUT_BOX = "3";// 发件箱
	private static final String DELETE_BOX = "4";// 已删除
	private static final String UN_READ = "5";// 未读

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;

	private String boxId;

	private String filter_LIKES_sender;
	private String filter_LIKEC_toUserNames;
	private String filter_LIKES_subject;
	private Date filter_GED_sendTime;
	private Date filter_LED_sendTime;
	private String filter_EQS_emailType;

	private OaEmailBody entity;

	private OaEmailBody oaEmailBody;

	private String oaEmailId;

	private String[] oaEmailIds;

	/**
	 * 上传文件名
	 */
	private File filedata;

	private String filedataFileName;

	private String filedataContentType;

	private Page<OaEmail> pageEmail = new Page<OaEmail>(13);

	private Page<OaEmail> pageUnRead = new Page<OaEmail>(13);

	private List<OaEmailGroup> oaEmailGroups;

	private Long nextMailNum;
	private Long prevMailNum;
	private String npFlag;
	private String orgBizCd;
	private String[] orgCds;
	private String sort;
	private Long readMailNum;
	private String searchFlg;

	/**
	 * 操作方式： edit 编辑邮件 resend 重新发送邮件 draft 草稿箱
	 */
	private String operate;

	private String[] attaFileIds;

	private List<WsPlasOrg> wsUaapOrgs;
	private List<WsPlasUser> wsPlasUsers;
	private List<AppAttachFile> appAttachFiles;

	private List<OaEmail> oaEmails4status;

	private boolean sendEmailFlag = false;

	private boolean emailAuthority = false;
	
	
	//联系系统管理员专用  1-可编辑 否则，空
	private String enableWriteFlg;

	/**
	 * 写邮件<br>
	 * reply 回复邮件<br>
	 * forward 转发邮件
	 */
	@Override
	public String input() throws Exception {
		String dataId = Struts2Utils.getParameter("dataId");
		String args = Struts2Utils.getParameter("args");
		if (args != null) {
			if (args.equals("reply")) {
				reply(dataId);
			} else if (args.equals("replyAll")) {
				replyAll(dataId);
			} else if (args.equals("forward")) {
				foward(dataId);
			}
		}
		setEntityTmpId(RandomUtils.generateTmpEntityId());
		Struts2Utils.getRequest().setAttribute("reply_id", Struts2Utils.getRequest().getParameter("reply_id"));
		return INPUT;
	}

	/**
	 * 回复邮件
	 * 
	 * @param dataId
	 */
	private void reply(String dataId) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			OaEmailBody emailBody = oaEmailBodyManager.getEntity(dataId);
			String content = Util.clob2String(emailBody.getContent());
			String toUser = emailBody.getCreator();
			String userName = CodeNameUtil.getUserNameByCd(toUser);// 需要通过userCode从uaap中获取用户名称
			StringBuilder str = new StringBuilder("<br/><br/><br/>");
			str.append("---------------------");
			str.append(userName);
			str.append("在");
			if (emailBody.getSendTime() != null) {
				String date = format.format(emailBody.getSendTime());
				str.append(date);
			}
			str.append("中的来信写道:---------------------<br/><br/>");
			str.append("<div style='BORDER-LEFT: #000 2px solid;PADDING-LEFT: 5px'>");
			str.append(content);
			str.append("</div>");
			Struts2Utils.getRequest().setAttribute("toUserCds", toUser);
			Struts2Utils.getRequest().setAttribute("toUserNames", userName);
			Struts2Utils.getRequest().setAttribute("subject", "Re:" + emailBody.getSubject());
			Struts2Utils.getRequest().setAttribute("content", str.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回复全部
	 * 
	 * @param dataId
	 */
	private void replyAll(String dataId) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			OaEmailBody emailBody = oaEmailBodyManager.getEntity(dataId);
			String content = Util.clob2String(emailBody.getContent());
			String toUsers = Util.clob2String(emailBody.getToUserCds());
			String toUserNames = Util.clob2String(emailBody.getToUserNames());
			String copyUsers = Util.clob2String(emailBody.getCopyUserCds());
			String copyUserNames = Util.clob2String(emailBody.getCopyUserNames());

			String userName = CodeNameUtil.getUserNameByCd(emailBody.getCreator());
			String currentUserCd = SpringSecurityUtils.getCurrentUiid();

			String[] userCds = toUsers.split(";");
			String[] userNames = toUserNames.split(";");
			String[] copyUserCds = copyUsers.split(";");
			String[] copyNames = copyUserNames.split(";");

			Map<String, String> toUserMap = new LinkedHashMap<String, String>();
			Map<String, String> copyUserMap = new LinkedHashMap<String, String>();

			for (int i = 0; i < userCds.length; i++) {
				toUserMap.put(userCds[i], userNames[i]);
			}
			for (int i = 0; i < copyUserCds.length; i++) {
				copyUserMap.put(copyUserCds[i], copyNames[i]);
			}

			if (!toUserMap.containsKey(emailBody.getCreator())) {
				toUserMap.put(emailBody.getCreator(), userName);
			}
			if (toUserMap.containsKey(currentUserCd)) {
				toUserMap.remove(currentUserCd);
			}
			if (copyUserMap.containsKey(currentUserCd)) {
				copyUserMap.remove(currentUserCd);
			}
			StringBuffer sbUserCds = new StringBuffer();
			StringBuffer sbUserNames = new StringBuffer();
			StringBuffer sbCopyUserCds = new StringBuffer();
			StringBuffer sbCopyUserNames = new StringBuffer();

			for (String userCd : toUserMap.keySet()) {
				if (userCd.equals("")) {
					continue;
				}
				sbUserCds.append(userCd).append(";");
				sbUserNames.append(toUserMap.get(userCd)).append(";");
			}
			for (String userCd : copyUserMap.keySet()) {
				if (userCd.equals("")) {
					continue;
				}
				sbCopyUserCds.append(userCd).append(";");
				sbCopyUserNames.append(copyUserMap.get(userCd)).append(";");
			}

			StringBuilder str = new StringBuilder("<br/><br/><br/>");
			str.append("---------------------");
			str.append(userName);
			str.append("在");
			if (emailBody.getSendTime() != null) {
				String date = format.format(emailBody.getSendTime());
				str.append(date);
			}
			str.append("中的来信写道:---------------------<br/><br/>");
			str.append("<div style='BORDER-LEFT: #000 2px solid;PADDING-LEFT: 5px'>");
			str.append(content);
			str.append("</div>");
			Struts2Utils.getRequest().setAttribute("toUserCds", sbUserCds.toString());
			Struts2Utils.getRequest().setAttribute("toUserNames", sbUserNames.toString());
			Struts2Utils.getRequest().setAttribute("copyUserCds", sbCopyUserCds);
			Struts2Utils.getRequest().setAttribute("copyUserNames", sbCopyUserNames);
			Struts2Utils.getRequest().setAttribute("subject", "Re:" + emailBody.getSubject());
			Struts2Utils.getRequest().setAttribute("content", str.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转发邮件
	 * 
	 * @param dataId
	 */
	private void foward(String dataId) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			OaEmailBody emailBody = oaEmailBodyManager.getEntity(dataId);
			String content = Util.clob2String(emailBody.getContent());
			String toUser = emailBody.getCreator();
			String userName = CodeNameUtil.getUserNameByCd(toUser);// 需要通过userCode从uaap中获取用户名称
			StringBuilder str = new StringBuilder("<br/><br/><br/>");
			str.append("---------------------");
			str.append(userName);
			str.append("在");
			if (emailBody.getSendTime() != null) {
				String date = format.format(emailBody.getSendTime());
				str.append(date);
			}
			str.append("中的来信写道:---------------------<br/><br/>");
			str.append("<div style='BORDER-LEFT: #000 2px solid;PADDING-LEFT: 5px'>");
			str.append(content);
			str.append("</div>");
			Struts2Utils.getRequest().setAttribute("subject", "Fw:" + emailBody.getSubject());
			Struts2Utils.getRequest().setAttribute("content", str.toString());

			Criterion criterion1 = Restrictions.eq("bizEntityId", emailBody.getOaEmailBodyId());
			Criterion criterion2 = Restrictions.eq("statusCd", "1");
			List<AppAttachFile> result = appAttachFileManager.findBy(criterion1, criterion2);
			if (result.size() > 0) {
				setAppAttachFiles(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将信息通过邮件发送 类似于便签、聊天内容
	 * 
	 * @return
	 */
	public void sendContent2Email() throws Exception {
		String userUiid = Struts2Utils.getParameter("uiid");
		String content = Struts2Utils.getParameter("content");
		String feedbackFlg = Struts2Utils.getParameter("feedbackFlg");
		if (StringUtils.isNotEmpty(userUiid)) {
			Struts2Utils.getRequest().setAttribute("toUserCds", userUiid);
			Struts2Utils.getRequest().setAttribute("toUserNames", CodeNameUtil.getUserNameByCd(userUiid));
		}
		if (StringUtils.isNotEmpty(content)) {
			String charset = Struts2Utils.getParameter("charset");
			if (StringUtils.isNotEmpty(charset) && "GBK".equals(charset)) {
				content = ChangeCharset.decode2GBK(content);
			} else {
				content = ChangeCharset.decode2UTF_8(content);
			}

			Struts2Utils.getRequest().setAttribute("content", content);
		}
		if (StringUtils.isNotEmpty(feedbackFlg)) {
			if (feedbackFlg.equals("0")) {
				Map<String, String> sysMap = appDictTypeManager.getDictDataByTypeCd(Constants.SYS_ADMIN);
				setParam2Email(sysMap);

			} else if (feedbackFlg.equals("1")) {
				Map<String, String> groupMap = appDictTypeManager.getDictDataByTypeCd(Constants.GROUP_ADMIN);
				setParam2Email(groupMap);
			}
		}

		// 生成临时id供附件上传使用
		setEntityTmpId(RandomUtils.generateTmpEntityId());
	}

	/**
	 * 根据数据字典Map将邮件收件人、抄送人、密送人信息返回至页面
	 * 
	 * @param map
	 */
	private void setParam2Email(Map<String, String> map) {
		String to = map.get(Constants.TO);
		String cc = map.get(Constants.CC);
		String bcc = map.get(Constants.BCC);
		String toNames = CodeNameUtil.getUserNamesByUiids(to, ";");
		String ccNames = CodeNameUtil.getUserNamesByUiids(cc, ";");
		String bccNames = CodeNameUtil.getUserNamesByUiids(bcc, ";");
		Struts2Utils.getRequest().setAttribute("toUserCds", to);
		Struts2Utils.getRequest().setAttribute("toUserNames", toNames);
		Struts2Utils.getRequest().setAttribute("copyUserCds", cc);
		Struts2Utils.getRequest().setAttribute("copyUserNames", ccNames);
		Struts2Utils.getRequest().setAttribute("bccUserCds", bcc);
		Struts2Utils.getRequest().setAttribute("bccUserNames", bccNames);
		Struts2Utils.getRequest().setAttribute("subject", "问题反馈");
	}

	public String search() throws Exception {

		return "search";
	}

	/**
	 * 标记为已读
	 * 
	 * @throws Exception
	 */
	public String mark() throws Exception {
		oaEmailManager.readOaEmails(getOaEmailIds());
		setBoxId(IN_BOX);
		return RELOAD;
	}

	/**
	 * 邮件添加关注
	 * 
	 * @return
	 * @throws Exception
	 */
	public String attention() throws Exception {
		OaEmail oaEmail = oaEmailManager.getEntity(getOaEmailId());
		if (oaEmail.getAttentionFlg() != null && oaEmail.getAttentionFlg().equals("1")) {
			oaEmail.setAttentionFlg("0");
		} else {
			oaEmail.setAttentionFlg("1");
		}
		oaEmailManager.saveOaEmail(oaEmail);
		Struts2Utils.renderText(oaEmail.getAttentionFlg());
		return null;
	}

	/**
	 * 编辑已发送邮件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		oaEmailBodyManager.editOaEmail(entity);
		// 删除用户在界面上删除的附件
		if (attaFileIds != null && attaFileIds.length > 0) {
			List<String> appAttachFileIds = new ArrayList<String>();
			for (int i = 0; i < attaFileIds.length; i++) {
				appAttachFileIds.add(attaFileIds[i]);
			}
			appAttachFileManager.deleteFileNotExist(entity.getOaEmailBodyId(), appAttachFileIds);
		} else {
			appAttachFileManager.deleteFileNotExist(entity.getOaEmailBodyId(), null);
		}
		// 将新上传的附件挂到当前邮件下
		appAttachFileManager.updateTmpFile(entityTmpId, entity.getOaEmailBodyId());
		setBoxId(OUT_BOX);
		return RELOAD;
	}

	/**
	 * 查看邮件<br/>
	 * 包含上一封下一封相关信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String read() throws Exception {
		OaEmail oaEmail = null;
		try {
			if (StringUtils.isNotEmpty(getOaEmailId())) {
				oaEmail = oaEmailManager.getEntity(getOaEmailId());
				oaEmailBody = oaEmail.getOaEmailBody();
			} else {
				oaEmailBody = oaEmailBodyManager.getEntity(getId());
			}
		} catch (Exception e) {
			Struts2Utils.renderText("error_deleted");
			return null;
		}
		if (oaEmailBody.getUploadFlg() != null && oaEmailBody.getUploadFlg().equals("1")) {
			Criterion criterion1 = Restrictions.eq("bizEntityId", oaEmailBody.getOaEmailBodyId());
			
			//问题： xinxin发现邮件没有附件
			//原因： 邮件需要清理，已将发送人已离职邮件的附件，标志设置为失效
			//解决： 暂时搜索不带"有效标志"判断.
//			Criterion criterion2 = Restrictions.eq("statusCd", "1");
//			List<AppAttachFile> result = appAttachFileManager.findBy(criterion1, criterion2);
			
			List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
			setAppAttachFiles(result);
		}
		// 发件箱的编辑|重新发送
		if (StringUtils.isNotEmpty(operate)) {
			// 生成临时id供附件上传使用
			setEntityTmpId(RandomUtils.generateTmpEntityId());
			return operate;
		}
		// 草稿箱邮件的查看
		if (getBoxId().equals(DRAFT_BOX)) {
			setEntityTmpId(RandomUtils.generateTmpEntityId());
			return INPUT;
		}
		// 普通邮件的查看
		else {
			if (oaEmail != null && oaEmail.getReadFlg().equals("0")) {
				oaEmailManager.readOaEmail(getOaEmailId());
			}
			// Page<OaEmail> nextPage = this.getNextOrPrePage("next");
			// Page<OaEmail> prevPage = this.getNextOrPrePage("prev");
			// setNextMailNum(nextPage.getTotalCount());
			// setPrevMailNum(prevPage.getTotalCount());
			// if (nextPage.getTotalCount() > 0) {
			// Struts2Utils.getRequest().setAttribute("nextDataId",
			// nextPage.getResult().get(0).getOaEmailId());
			// }
			// if (prevPage.getTotalCount() > 0) {
			// Struts2Utils.getRequest().setAttribute("prevDataId",
			// prevPage.getResult().get(0).getOaEmailId());
			// }
			String expand = Struts2Utils.getParameter("expand");
			if (StringUtils.isNotEmpty(expand))
				return "readSimple";

			return "read";
		}
	}

	/**
	 * 邮件页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		// Page<OaEmail> numPage = new Page<OaEmail>(1);
		// Long unReadNum = this.getUnReadMail(new ArrayList<PropertyFilter>(),
		// numPage).getTotalCount();
		// Struts2Utils.getRequest().setAttribute("unReadNum", unReadNum);
		String newEmailFlag = Struts2Utils.getParameter("newEmailFlag");
		if (StringUtils.isNotEmpty(newEmailFlag)) {
			Struts2Utils.getRequest().setAttribute("newEmailFlag", newEmailFlag);
		}
		if (getSendEmailFlag()) {
			sendContent2Email();
		}

		return "main";
	}

	/**
	 * 搜索邮件列表 【收件箱、发件箱、草稿箱、自定义、未读】
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());

		// 处理搜索条件
		String searchBoxId = Struts2Utils.getParameter("searchBoxId");
		String emailType = Struts2Utils.getParameter("emailType");
		if (StringUtils.isNotEmpty(searchBoxId)) {
			setBoxId(searchBoxId);
			// pageEmail.setPageSize(10);
		}
		if (StringUtils.isNotEmpty(emailType)) {
			PropertyFilter propertyFilter = new PropertyFilter("EQS_emailType", emailType);
			filters.add(propertyFilter);
		}

		// 如果传入的boxId为空则默认为收件箱
		if (StringUtils.isBlank(boxId)) {
			boxId = IN_BOX;
		}

		if (!pageEmail.isOrderBySetted()) {
			// 只有收件箱按照关注、发送时间进行排序
//			if (boxId.equals(IN_BOX)) {
//				pageEmail.setOrderBy("attentionFlg");
//				pageEmail.setOrder(Page.DESC);
//			} else {
				// pageEmail.setOrderBy("createdDate");
				// pageEmail.setOrder(Page.DESC);
//			}
			setSort(Page.ASC);
		} else {
			if (sort.equals(Page.DESC)) {
				setSort(Page.ASC);
			} else {
				setSort(Page.DESC);
			}
		}

		try {
			if (StringUtils.isNotEmpty(boxId)) {
				if (boxId.equals(IN_BOX)) {// 收件箱
					pageEmail = this.getMail(filters, pageEmail);
				} else if (boxId.equals(DRAFT_BOX)) {// 草稿箱
					pageEmail = this.getDraftMail(filters, pageEmail);
					return "draftBox";
				} else if (boxId.equals(OUT_BOX)) {// 发件箱
					pageEmail = this.getSendMail(filters, pageEmail);
					return "outBox";
				} else if (boxId.equals(DELETE_BOX)) {
					pageEmail = this.getDelMail(filters, pageEmail);
					return "delBox";
				} else if (boxId.equals(UN_READ)) {// 未读
					pageEmail = this.getUnReadMail(filters, pageEmail);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			Struts2Utils.renderText("error_locked");
			return null;
		}
		return "list";
	}

	/**
	 * 未读邮件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unRead() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		pageEmail = this.getUnReadMail(filters, pageEmail);
		setBoxId(UN_READ);
		return SUCCESS;
	}

	public String clearDelBox() throws Exception {
		oaEmailManager.clearDelBox();
		setBoxId(DELETE_BOX);
		return RELOAD;
	}

	/**
	 * 已删除邮件恢复
	 * 
	 * @return
	 * @throws Exception
	 */
	public String recover() throws Exception {
		oaEmailManager.recoverEmails(getOaEmailIds());
		return RELOAD;
	}

	/**
	 * 查看收件人阅读状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String status() throws Exception {
		entity = oaEmailBodyManager.getEntity(getId());
		String toUserCds = Util.clob2String(entity.getToUserCds());
		String copyUserCds = Util.clob2String(entity.getCopyUserCds());
		String bccUserCds = Util.clob2String(entity.getBccUserCds());
		List<OaEmail> list = entity.getOaEmails();
		oaEmails4status = new ArrayList<OaEmail>();
		Long readMailNum = 0l;

		String[] list_toUserCds;
		if (null != toUserCds && !"".equalsIgnoreCase(toUserCds)) {
			try {
				list_toUserCds = toUserCds.split(";");
				for (String compareUserCd : list_toUserCds) {
					// 遍历收件人
					boolean if_in_toUserCds = false;
					for (OaEmail oaEmail : list) {
						// 遍历子邮件，如果有就增加到结果中
						if (compareUserCd.equalsIgnoreCase(oaEmail.getUserCd())) {
							if (!oaEmail.getUserTypeCd().equals("0")) {
								oaEmails4status.add(oaEmail);
								if (oaEmail.getReadFlg().equals("1")) {
									readMailNum++;
								}
								if_in_toUserCds = true;
								break;
							}
						}
					}
					if (!if_in_toUserCds) {
						// 如果在子邮件中找不到收件人，说明已被完全删除，同样增加到结果集中
						OaEmail deleted_email = new OaEmail();
						deleted_email.setReadFlg("2");
						deleted_email.setUserCd(compareUserCd);
						oaEmails4status.add(deleted_email);
					}
				}
			} catch (Exception e) {
			}
		}
		String[] list_copyUserCds;
		if (null != copyUserCds && !"".equalsIgnoreCase(copyUserCds)) {
			try {
				list_copyUserCds = copyUserCds.split(";");
				for (String compareUserCd : list_copyUserCds) {
					// 遍历抄送人
					boolean if_in_toUserCds = false;
					for (OaEmail oaEmail : list) {
						// 遍历子邮件，如果有就增加到结果中
						if (compareUserCd.equalsIgnoreCase(oaEmail.getUserCd())) {
							if (!oaEmail.getUserTypeCd().equals("0")) {
								oaEmails4status.add(oaEmail);
								if (oaEmail.getReadFlg().equals("1")) {
									readMailNum++;
								}
								if_in_toUserCds = true;
								break;
							}
						}
					}
					if (!if_in_toUserCds) {
						// 如果在子邮件中找不到抄送人，说明已被完全删除，同样增加到结果集中
						OaEmail deleted_email = new OaEmail();
						deleted_email.setReadFlg("2");
						deleted_email.setUserCd(compareUserCd);
						oaEmails4status.add(deleted_email);
					}
				}
			} catch (Exception e) {
			}
		}
		String[] list_bccUserCds;
		if (null != bccUserCds && !"".equalsIgnoreCase(bccUserCds)) {
			try {
				list_bccUserCds = bccUserCds.split(";");
				for (String compareUserCd : list_bccUserCds) {
					// 遍历密送人
					boolean if_in_toUserCds = false;
					for (OaEmail oaEmail : list) {
						// 遍历子邮件，如果有就增加到结果中
						if (compareUserCd.equalsIgnoreCase(oaEmail.getUserCd())) {
							if (!oaEmail.getUserTypeCd().equals("0")) {
								oaEmails4status.add(oaEmail);
								if (oaEmail.getReadFlg().equals("1")) {
									readMailNum++;
								}
								if_in_toUserCds = true;
								break;
							}
						}
					}
					if (!if_in_toUserCds) {
						// 如果在子邮件中找不到密送人，说明已被完全删除，同样增加到结果集中
						OaEmail deleted_email = new OaEmail();
						deleted_email.setReadFlg("2");
						deleted_email.setUserCd(compareUserCd);
						oaEmails4status.add(deleted_email);
					}
				}
			} catch (Exception e) {
			}
		}

		setReadMailNum(readMailNum);
		return "status";
	}

	/**
	 * 在发件箱的查看收件状况时，删除未读的收件人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteUserBySingle() throws Exception {
		String deleteUserCd = Struts2Utils.getParameter("deleteUserCd");
		entity = oaEmailBodyManager.getEntity(getId());
		List<OaEmail> list = entity.getOaEmails();
		for (OaEmail oaEmail : list) {
			// 遍历子邮件，如果有就删除
			if (deleteUserCd.equalsIgnoreCase(oaEmail.getUserCd()) && "0".equalsIgnoreCase(oaEmail.getReadFlg())) {
				oaEmailManager.delete(oaEmail);
			}
		}
		// String toUserCds = Util.clob2String(entity.getToUserCds());
		// String toUserCds2 = "";
		// String copyUserCds = Util.clob2String(entity.getCopyUserCds());
		// String copyUserCds2 = "";
		// String bccUserCds = Util.clob2String(entity.getBccUserCds());
		// String bccUserCds2 = "";
		// String[] list_toUserCds;
		// if (null != toUserCds && !"".equalsIgnoreCase(toUserCds)) {
		// list_toUserCds = toUserCds.split(";");
		// for (String compareUserCd : list_toUserCds) {
		// if(!deleteUserCd.equalsIgnoreCase(compareUserCd)){
		// toUserCds2 += compareUserCd+";";
		// }
		// }
		// }
		// String[] list_copyUserCds;
		// if (null != copyUserCds && !"".equalsIgnoreCase(copyUserCds)) {
		// list_copyUserCds = copyUserCds.split(";");
		// for (String compareUserCd : list_copyUserCds) {
		// if(!deleteUserCd.equalsIgnoreCase(compareUserCd)){
		// copyUserCds2 += compareUserCd+";";
		// }
		// }
		// }
		// String[] list_bccUserCds;
		// if (null != bccUserCds && !"".equalsIgnoreCase(bccUserCds)) {
		// list_bccUserCds = bccUserCds.split(";");
		// for (String compareUserCd : list_bccUserCds) {
		// if(!deleteUserCd.equalsIgnoreCase(compareUserCd)){
		// bccUserCds2 += compareUserCd+";";
		// }
		// }
		// }
		return status();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaEmailBodyManager.getEntity(getId());
		} else {
			entity = new OaEmailBody();
		}
	}

	/**
	 * 发送邮件
	 */
	@Override
	public String save() throws Exception {
		if (StringUtils.isEmpty(entity.getSubject())) {
			entity.setSubject("[无主题]");
		}
		oaEmailBodyManager.saveOaEmailBody(entity);
		// 将原来已经上传的附件挂到当前邮件
		if (attaFileIds != null && attaFileIds.length > 0) {
			List<AppAttachFile> list = new ArrayList<AppAttachFile>();

			List<String> appAttachFileIds = new ArrayList<String>();
			for (int i = 0; i < attaFileIds.length; i++) {
				appAttachFileIds.add(attaFileIds[i]);
			}
			appAttachFileManager.deleteFileNotExist(entity.getOaEmailBodyId(), appAttachFileIds);

			for (String attaFileId : attaFileIds) {
				AppAttachFile file = appAttachFileManager.getEntity(attaFileId);
				if (file.getBizEntityId().equals(entity.getOaEmailBodyId())) {
					break;
				}
				AppAttachFile newFile = new AppAttachFile();
				BeanUtils.copyProperties(newFile, file);
				newFile.setBizEntityId(entity.getOaEmailBodyId());
				newFile.setAppAttachFileId(null);
				newFile.setRecordVersion(0);
				list.add(newFile);
			}
			appAttachFileManager.saveAppAttachFiles(list);
		} else {
			appAttachFileManager.deleteFileNotExist(entity.getOaEmailBodyId(), null);
		}
		// 将新上传的附件挂到当前邮件下
		appAttachFileManager.updateTmpFile(entityTmpId, entity.getOaEmailBodyId());
		if (StringUtils.isNotEmpty(Struts2Utils.getParameter("send2OutEmail"))) {
			
			oaEmailBodyManager.send2OutEmail(getCopyOaBody(entity));
		}

		// 设置之前的邮件是否已回复
		String reply_id = Struts2Utils.getRequest().getParameter("reply_id");
		if (null != reply_id && !"".equalsIgnoreCase(reply_id)) {
			try {
				oaEmailManager.setOaEmailReply(reply_id);
			} catch (Exception e) {
			}
		}

		setBoxId(OUT_BOX);
		return RELOAD;
	}

	public OaEmailBody getCopyOaBody(OaEmailBody body ){
		
		OaEmailBody tmp = new OaEmailBody();
		tmp.setOaEmailBodyId(body.getOaEmailBodyId());
		tmp.setSubject(body.getSubject());
		tmp.setContent(body.getContent());
		tmp.setToUserNames(body.getToUserNames());
		tmp.setToUserCds(body.getToUserCds());
		tmp.setCopyUserNames(body.getCopyUserNames());
		tmp.setCopyUserCds(body.getCopyUserCds());
		tmp.setBccUserNames(body.getBccUserNames());
		tmp.setBccUserCds(body.getBccUserCds());
		tmp.setSendTime(body.getSendTime());
		tmp.setUploadFlg(body.getUploadFlg());
		tmp.setSendFlg(body.getSendFlg());
		tmp.setRemindFlg(body.getRemindFlg());
		tmp.setImportLevelCd(body.getImportLevelCd());
		
		return tmp;
	}
	
	
	/**
	 * 删除单封邮件 <br/>
	 * <font color="red">delete:</font> 删除邮件(个人删除自己的邮件，无法恢复)<br/>
	 * <font color="red">remove:</font> 移入垃圾箱 <br/>
	 * <font color="red">clear:</font> 彻底从数据库中删除,无法恢复
	 */
	@Override
	public String delete() throws Exception {
		try {
			String status = Struts2Utils.getParameter("status");
			if (boxId.equals(IN_BOX) || boxId.equals(UN_READ)) {
				status = "remove";
			}
			if (status.equals("delete")) {
				if (StringUtils.isNotEmpty(oaEmailId)) {
					oaEmailManager.deleteOaEmail(getOaEmailId());
				}
			} else if (status.equals("remove")) {
				if (StringUtils.isNotEmpty(oaEmailId)) {
					oaEmailManager.removeOaEmail(getOaEmailId());
				}
			} else if (status.equals("clear")) {
				oaEmailManager.deleteBatchWithUnRead(new String[] { getOaEmailId() });
				// oaEmailBodyManager. deleteOaEmailBody(getId());
			} else if (status.equals("clear2")) {
				// 从发件箱查看发送情况，点击删除按钮
				oaEmailBodyManager.deleteOaEmailBody(getId());
			}
			setBoxId(boxId);
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		Struts2Utils.renderText("doSearch");
		return null;
	}

	/**
	 * 删除多封邮件 <br/>
	 * <font color="red">delete:</font> 删除邮件(个人删除自己的邮件，无法恢复)<br/>
	 * <font color="red">remove:</font> 移入垃圾箱 <br/>
	 * <font color="red">clear:</font> 彻底从数据库中删除,无法恢复
	 */
	@Override
	public String deleteBatch() throws Exception {
		try {
			String status = Struts2Utils.getParameter("status");
			if (status.equals("delete")) {
				if (boxId.equals(OUT_BOX)) {
					oaEmailManager.deleteBatchWithUnRead(getOaEmailIds());
				} else {
					oaEmailManager.deleteBatch(getOaEmailIds());
				}
			} else if (status.equals("remove")) {
				oaEmailManager.removeOaEmails(getOaEmailIds());
			}
			setBoxId(boxId);
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		Struts2Utils.renderText("doSearch");
		return null;
	}

	/**
	 * 获取各个邮件箱的邮件数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMailNum() throws Exception {
		// Page<OaEmail> numPage = new Page<OaEmail>(1);
		ArrayList<Integer> list = new ArrayList<Integer>();
		int inBoxNum = oaEmailManager.countByPropertyFilter(this.getMailFilter(new ArrayList<PropertyFilter>()));
		list.add(inBoxNum);
		int draftBoxNum = oaEmailManager.countByPropertyFilter(this.getDraftFilters(new ArrayList<PropertyFilter>()));
		list.add(draftBoxNum);
		int outBoxNum = oaEmailManager.countByPropertyFilter(this.getSendMailFilter(new ArrayList<PropertyFilter>()));
		list.add(outBoxNum);
		int delBoxNum = oaEmailManager.countByPropertyFilter(this.getDelMailFilters(new ArrayList<PropertyFilter>()));
		list.add(delBoxNum);
		int unReadNum = oaEmailManager.countByPropertyFilter(this.getUnReadMailFilter(new ArrayList<PropertyFilter>()));
		list.add(unReadNum);
		Struts2Utils.renderText(list.toString());
		return null;
	}

	/**
	 * 选择用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String member() throws Exception {
		// List<WsPlasOrg> list = UaapCache.getUaapOrgs();
		// setWsPlasOrgs(list);
		String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		if (StringUtils.isNotEmpty(orgCd)) {
			setOrgBizCd(orgCd);
			setWsPlasUsers(PlasCache.getDirectUserListByCd(orgCd));
		}

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(filter);
		Page<OaEmailGroup> page = new Page<OaEmailGroup>(99);
		page.setOrderBy("dispOrderNo");
		page.setOrder(Page.ASC);
		page = oaEmailGroupManager.findPage(page, filters);
		oaEmailGroups = page.getResult();

		return "member";
	}

	/**
	 * 获取机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOrgTree() throws Exception {
		for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			if (authority.getAuthority().equals("A_EMAIL_USER_ADMIN")) {
				emailAuthority = true;
				break;
			}
		}
		TreeNode node = new TreeNode();
		node.setId(appParamManager.getAppOrgTreeRootCd());
		node.setText(appParamManager.getAppOrgTreeRootName());
		node.setOrgOrUser("1");
		node.setChildren(this.getChildrenNode(node));
		Struts2Utils.renderJson(node);
		return null;
	}

	private List<TreeNode> getChildrenNode(TreeNode treeNode) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		
		String parentId = null;
		for (WsPlasOrg org : PlasCache.getOrgEnableList()) {
			parentId = PlasCache.getParentId(PlasCacheUtil.TREE_TYPE_PHYSICAL, org.getPlasOrgId());
			if (StringUtils.isNotBlank(parentId) && parentId.equals(treeNode.getId())) {
				TreeNode childOrg = new TreeNode();
				childOrg.setId(org.getPlasOrgId());
				childOrg.setText(org.getOrgName());
				if (emailAuthority) {
					childOrg.setChecked("0");
				}
				childOrg.setOrgOrUser("1");
				childOrg.setExtParam(org.getOrgCd());
				
				childOrg.setChildren(getChildrenNode(childOrg));
				children.add(childOrg);
			}
		}

		return children;
	}

	/**
	 * 获取对应部门或角色下所有的用户 <br/>
	 * 人员从缓存中获取
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersbyOrg() throws Exception {
		String orgCds = Struts2Utils.getParameter("orgCds");
		if (StringUtils.isNotEmpty(orgCds)) {
			List<UserDisplayInfo> userInfos = new ArrayList<UserDisplayInfo>();
			for (String orgCd : orgCds.split(",")) {
				List<WsPlasUser> users = PlasCache.getDirectUserListByCd(orgCd);
				if (users != null && users.size() > 0) {
					userInfos.addAll(transf2UserInfo(users));
				}
			}
			Struts2Utils.renderJson(userInfos);
		}

		return null;
	}

	/**
	 *根据关键字搜索人员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersByFilter() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String maxNum = Struts2Utils.getParameter("maxNum");
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser wsUaapUser = new WsPlasUser();
			wsUaapUser.setUiid(value);
			wsUaapUser.setUserName(value);
			List<WsPlasUser> result = Util.getPlasService().getUserListByFilter(wsUaapUser);
			List<UserDisplayInfo> list = transf2UserInfo(result);
			if (StringUtils.isNotEmpty(maxNum)) {
				int num = Integer.valueOf(maxNum);
				List<UserDisplayInfo> newList;
				if (list.size() > num) {
					newList = list.subList(0, num);
				} else {
					newList = list.subList(0, list.size());
				}
				Struts2Utils.renderJson(newList);
			} else {
				Struts2Utils.renderJson(list);
			}
		}
		return null;
	}

	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		if (users == null)
			return list;
		
		UserDisplayInfo info = null;
		for (WsPlasUser user : users) {
			info = new UserDisplayInfo();
			info.setCenterOrgCd(PlasCache.getCenterOrgCdByUserId(user.getPlasUserId()));
			info.setCenterOrgName(PlasCache.getCenterOrgNameByUserId(user.getPlasUserId()));
			info.setOrgBizCd(user.getOrgBizCd());
			info.setOrgCd(user.getOrgCd());
			info.setOrgName(user.getOrgName());// 这里取的是逻辑机构名称
			String phyOrgName = CodeNameUtil.getDeptNameByCd(user.getOrgCd());//UaapCache.getOrgNameByCd(user.getUaapPhysicalOrgCd());
			info.setOrgName(phyOrgName);
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			list.add(info);
		}
		return list;
	}

	@Override
	public OaEmailBody getModel() {
		String oaEmailBodyId = getId();
		if (StringUtils.isNotBlank(oaEmailBodyId)) {
			entity = oaEmailBodyManager.getEntity(getId());
		} else {
			entity = new OaEmailBody();
		}
		return entity;
	}

	/**
	 * 存入草稿箱
	 * 
	 * @return
	 */
	public String draft() throws Exception {
		oaEmailBodyManager.toDraftOaEmail(entity);
		// 删除用户在界面上删除的附件
		if (attaFileIds != null && attaFileIds.length > 0) {
			List<String> appAttachFileIds = new ArrayList<String>();
			for (int i = 0; i < attaFileIds.length; i++) {
				appAttachFileIds.add(attaFileIds[i]);
			}
			appAttachFileManager.deleteFileNotExist(entity.getOaEmailBodyId(), appAttachFileIds);
		} else {
			appAttachFileManager.deleteFileNotExist(entity.getOaEmailBodyId(), null);
		}
		// 将新上传的附件挂到当前邮件下
		appAttachFileManager.updateTmpFile(entityTmpId, entity.getOaEmailBodyId());
		setBoxId(DRAFT_BOX);
		return RELOAD;
	}

	/**
	 * 用于邮件里富文本编辑器的文件上传
	 * 
	 * @return
	 * @throws Exception
		@Description
	 */
	@Deprecated 
	public String uploadTemp() throws Exception {
		String fileName = genFileName(filedataFileName);
		String absPath = Struts2Utils.getRequest().getContextPath();
		String realPath = ServletActionContext.getServletContext().getRealPath("");
		File dir = new File(realPath + File.separator + "upload" + File.separator + SpringSecurityUtils.getCurrentUiid());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File fout = new File(dir, fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		if (filedata.length() > Constants.MAX_FILE_SIZE) {
			Struts2Utils.renderText("{'err':'上传文件最大不能超过" + Constants.MAX_FILE_SIZE / (1024 * 1000) + "M','msg':''}");
			filedata.delete();
			return null;
		}
		FileInputStream fin = new FileInputStream(filedata);

		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fin.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		String path = absPath + "/upload/" + SpringSecurityUtils.getCurrentUiid() + "/" + fileName;
		Struts2Utils.renderHtml("{'err':'','msg':'" + path + "'}");
		return null;
	}
	
	public String upload() throws Exception {
		String fileName = genFileName(filedataFileName);
		String absPath = Struts2Utils.getRequest().getContextPath();
		String uiid = SpringSecurityUtils.getCurrentUiid();
		File dir = new File(PowerUtils.getFilePath("mailCus", uiid,true));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File fout = new File(dir, fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		if (filedata.length() > Constants.MAX_FILE_SIZE) {
			Struts2Utils.renderText("{'err':'上传文件最大不能超过" + Constants.MAX_FILE_SIZE / (1024 * 1000) + "M','msg':''}");
			filedata.delete();
			return null;
		}
		FileInputStream fin = new FileInputStream(filedata);

		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fin.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		
		StringBuilder pathSb = new StringBuilder(absPath);
		pathSb.append("/app/show.action?fileName=");
		pathSb.append(fileName);
		pathSb.append("&realFileName=");
		pathSb.append(filedataFileName);
		pathSb.append("&bizModuleCd=");
		pathSb.append("mailCus");
		pathSb.append("&uiid=");
		pathSb.append(uiid);
		Struts2Utils.renderHtml("{'err':'','msg':'" + pathSb.toString() + "'}");
		return null;
	}

	public String getEmailSign() throws Exception {
		String signContent = Util.getPlasService().getEmailSignContent(SpringSecurityUtils.getCurrentUserId());
		if (StringUtils.isEmpty(signContent)) {
			signContent = "";
		}
		Struts2Utils.renderText(signContent);
		return null;
	}

	public String saveEmailSign() throws Exception {
		String signContent = Struts2Utils.getParameter("signContent");
		Util.getPlasService().saveEmailSignContent(SpringSecurityUtils.getCurrentUserId(), signContent);
		return null;
	}

	/**
	 * 收件箱列表
	 * 
	 * @param filters
	 * @return
	 */
	private Page<OaEmail> getMail(List<PropertyFilter> filters, Page<OaEmail> pageMail) {
		// List<PropertyFilter> newFilters = this.getMailFilter(filters);
		// return oaEmailManager.findPage(pageMail, newFilters);
		// StringBuffer hql = new StringBuffer();
		// hql.append("select e from OaEmail as e where e.userCd=:userCd and e.userTypeCd in(:userTypeCd) and e.deleteFlg=:deleteFlg");
		// String subject =
		// Struts2Utils.getRequest().getParameter("filter_LIKES_subject");
		// String sender =
		// Struts2Utils.getRequest().getParameter("filter_LIKES_sender");
		// if (StringUtils.isNotBlank(subject)) {
		// hql.append(" and e.subject like:subject");
		// }
		// if (StringUtils.isNotBlank(sender)) {
		// hql.append(" and e.sender like:sender");
		// }
		// hql.append(" order by e.createdDate desc");
		// Map<String, Object> pram = new HashMap<String, Object>();
		// pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		// Object[] userTypeCds=new Object[]{"1","2","3"};
		// pram.put("userTypeCd", userTypeCds);
		// pram.put("deleteFlg", "0");
		// pram.put("subject", "%" + subject + "%");
		// pram.put("sender", "%" + sender + "%");
		// Page<OaEmail> pageTmp = oaEmailManager.findPage(pageMail,
		// hql.toString(), pram);
		//
		// return pageTmp;

		// SELECT *
		// FROM POWERDESK.oa_email e
		// WHERE e.user_cd = 'huangjian' AND e.delete_flg = 0 AND e.user_type_cd
		// IN ('1', '2', '3')
		StringBuffer sql = new StringBuffer("SELECT /*+ index(e IX_OA_EMAIL_4) */e.* ");
		sql.append(" from POWERDESK.oa_email e WHERE 1=1 ");
		sql.append(" and e.user_cd = :userCd AND e.delete_flg = :deleteFlg AND e.user_type_cd IN (:userTypeCds)");
		if (StringUtils.isNotBlank(filter_LIKES_subject)) {
			sql.append(" AND e.subject like :subject");
		}
		if (StringUtils.isNotBlank(filter_LIKES_sender)) {
			sql.append(" AND e.sender like :sender");
		}
		if (filter_GED_sendTime != null) {
			sql.append(" AND e.updated_date >= :fromDate");
		}
		if (filter_LED_sendTime != null) {
			sql.append(" AND e.updated_date <= :toDate");
		}
		if (StringUtils.isNotBlank(filter_EQS_emailType)) {
			sql.append(" AND e.email_Type <= :emailType");
		}
		
		if (pageMail.isOrderBySetted()){
			pageMail.getOrderBy();
			if (Page.ASC.equals(pageMail.getOrder())) {
				sql.append(" order by ").append(pageMail.getOrderBy());
			} else {
				sql.append(" order by ").append(pageMail.getOrderBy()).append(" desc");
			}
		}
		Object[] objs=new Object[]{"1", "2", "3"};
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("userTypeCds", objs);
		pram.put("deleteFlg", "0");
		pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		pram.put("subject", "%" + filter_LIKES_subject + "%");
		pram.put("sender", "%" + filter_LIKES_sender + "%");
		pram.put("fromDate", filter_GED_sendTime);
		pram.put("toDate", filter_LED_sendTime);
		pram.put("emailType", filter_EQS_emailType);
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("e", OaEmail.class);
		Page<OaEmail> pageTmp = oaEmailManager.findPageSql(pageMail, sql.toString(), pram, mapClazz);
		return pageTmp;
	}

	private List<PropertyFilter> getMailFilter(List<PropertyFilter> filters) {
		// userCd = 当前用户
		PropertyFilter newFilter = new PropertyFilter("EQS_userCd", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		// 删除状态为未删除
		newFilter = new PropertyFilter("EQS_deleteFlg", "0");
		filters.add(newFilter);
		// userTypeCd != "0" 不为发送人(接收人或者抄送人)
		Object[] userTypeCds = new Object[] { "1", "2", "3" };
		newFilter = new PropertyFilter("INA_userTypeCd", userTypeCds);
		filters.add(newFilter);

		return filters;
	}

	/**
	 * 未读邮件列表
	 * 
	 * @param filters
	 * @return
	 */
	private Page<OaEmail> getUnReadMail(List<PropertyFilter> filters, Page<OaEmail> pageMail) {
		List<PropertyFilter> newFilters = this.getUnReadMailFilter(filters);
		return oaEmailManager.findPage(pageMail, newFilters);
	}

	private List<PropertyFilter> getUnReadMailFilter(List<PropertyFilter> filters) {
		// userCd = 当前用户
		PropertyFilter newFilter = new PropertyFilter("EQS_userCd", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		// userTypeCd <> "0" 不为发送人(接收人或者抄送人)
		newFilter = new PropertyFilter("NEQS_userTypeCd", "0");
		filters.add(newFilter);
		// 删除状态为未删除
		newFilter = new PropertyFilter("EQS_deleteFlg", "0");
		filters.add(newFilter);
		// 未读
		newFilter = new PropertyFilter("EQS_readFlg", "0");
		filters.add(newFilter);
		return filters;
	}

	/**
	 * 发件箱列表
	 * 
	 * @param filters
	 * @return
	 */
	private Page<OaEmail> getSendMail(List<PropertyFilter> filters, Page<OaEmail> pageMail) {
		// StringBuffer hql = new StringBuffer();
		// hql.append("select /*+ index(this_ IX_OA_EMAIL_4) */ e from OaEmail as e where e.userCd=:userCd and e.userTypeCd=:userTypeCd and e.deleteFlg=:deleteFlg");
		// hql.append(" and e.sendFlg=:sendFlg");
		// String subject =
		// Struts2Utils.getRequest().getParameter("filter_LIKES_subject");
		// String toUserNames =
		// Struts2Utils.getRequest().getParameter("filter_LIKEC_oaEmailBody_toUserNames");
		// if (StringUtils.isNotBlank(subject)) {
		// hql.append(" and e.subject like:subject");
		// }
		// if (StringUtils.isNotBlank(toUserNames)) {
		// hql.append(" e.toUserNames like:toUserNames");
		// }
		//
		// hql.append(" order by e.createdDate desc");
		//
		// Map<String, Object> pram = new HashMap<String, Object>();
		// pram.put("subject", "%" + subject + "%");
		// pram.put("toUserNames", new ClobImpl("%" + toUserNames + "%"));
		// pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		// pram.put("userTypeCd", "0");
		// pram.put("deleteFlg", "0");
		// pram.put("sendFlg", "1");
		// Page<OaEmail> pageTmp = oaEmailManager.findPage(pageMail,
		// hql.toString(), pram);
		// return pageTmp;

		// SELECT *
		// FROM POWERDESK.oa_email e
		// WHERE e.user_cd = 'huangjian' AND e.delete_flg = 0 AND e.user_type_cd
		// IN ('1', '2', '3')

		StringBuffer sql = new StringBuffer("SELECT e.* ");
		sql.append(" from POWERDESK.oa_email e");
		sql.append(" WHERE e.user_cd = :userCd AND e.delete_flg = 0");
		sql.append(" AND e.send_Flg=:sendFlg");
		sql.append(" AND e.user_Type_Cd=:userTypeCd");
		if (StringUtils.isNotBlank(filter_LIKES_subject)) {
			sql.append(" AND e.subject like :subject");
		}
		if (StringUtils.isNotBlank(filter_LIKEC_toUserNames)) {
			sql.append(" AND e.to_User_Names like :toUserNames");
		}
		if (filter_GED_sendTime != null) {
			sql.append(" AND e.updated_date >= :fromDate");
		}
		if (filter_LED_sendTime != null) {
			sql.append(" AND e.updated_date <= :toDate");
		}
		if (StringUtils.isNotBlank(filter_EQS_emailType)) {
			sql.append(" AND e.email_Type <= :emailType");
		}
		if (pageMail.isOrderBySetted()){
			pageMail.getOrderBy();
			if (Page.ASC.equals(pageMail.getOrder())) {
				sql.append(" order by ").append(pageMail.getOrderBy());
			} else {
				sql.append(" order by ").append(pageMail.getOrderBy()).append(" desc");
			}
		}
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		pram.put("subject", "%" + filter_LIKES_subject + "%");
		pram.put("toUserNames", "%" + filter_LIKEC_toUserNames + "%");
		pram.put("userTypeCd", "0");
		pram.put("sendFlg", "1");
		pram.put("fromDate", filter_GED_sendTime);
		pram.put("toDate", filter_LED_sendTime);
		pram.put("emailType", filter_EQS_emailType);
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("e", OaEmail.class);
		Page<OaEmail> pageTmp = oaEmailManager.findPageSql(pageMail, sql.toString(), pram, mapClazz);
		return pageTmp;

		// List<PropertyFilter> newFilters = this.getSendMailFilter(filters);
		// return oaEmailManager.findPage(pageMail, newFilters);
	}

	public int userSize(Clob toUserNames) {
		int size = 0;
		try {
			String userNames = Util.clob2String(toUserNames);
			if (StringUtils.isNotBlank(userNames)) {
				size = StringUtils.split(userNames, ";").length;
			}
		} catch (Exception e) {

		}
		return size;
	}
	public String userName(Clob toUserNames) {
		String userName = "";
		try {
			String userNames = Util.clob2String(toUserNames);
			if (StringUtils.isNotBlank(userNames)) {
				userName = StringUtils.split(userNames, ";")[0];
			}
		} catch (Exception e) {
			
		}
		return userName;
	}

	private List<PropertyFilter> getSendMailFilter(List<PropertyFilter> filters) {
		PropertyFilter newFilter = new PropertyFilter("EQS_userCd", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		newFilter = new PropertyFilter("EQS_userTypeCd", "0");
		filters.add(newFilter);
		newFilter = new PropertyFilter("EQS_deleteFlg", "0");
		filters.add(newFilter);
		newFilter = new PropertyFilter("EQS_sendFlg", "1");
		filters.add(newFilter);
		return filters;
	}

	/**
	 * 草稿箱列表
	 * 
	 * @param filters
	 * @return
	 */
	private Page<OaEmail> getDraftMail(List<PropertyFilter> filters, Page<OaEmail> pageMail) {
		List<PropertyFilter> newFilters = this.getDraftFilters(filters);
		return oaEmailManager.findPage(pageMail, newFilters);

		// StringBuffer hql = new StringBuffer();
		// hql.append("select e from OaEmail as e where e.userCd=:userCd and e.userTypeCd =:userTypeCd and e.deleteFlg=:deleteFlg and e.sendFlg=:sendFlg ");
		// String subject =
		// Struts2Utils.getRequest().getParameter("filter_LIKES_subject");
		// String sender =
		// Struts2Utils.getRequest().getParameter("filter_LIKES_sender");
		// if (StringUtils.isNotBlank(subject)) {
		// hql.append(" and e.subject like:subject");
		// }
		// if (StringUtils.isNotBlank(sender)) {
		// hql.append(" and e.sender like:sender");
		// }
		// hql.append(" order by e.createdDate desc");
		// Map<String, Object> pram = new HashMap<String, Object>();
		// pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		// pram.put("userTypeCd", "0");
		// pram.put("deleteFlg", "0");
		// pram.put("sendFlg", "0");
		// pram.put("subject", "%" + subject + "%");
		// pram.put("sender", "%" + sender + "%");
		// Page<OaEmail> pageTmp = oaEmailManager.findPage(pageMail,
		// hql.toString(), pram);
		// return pageTmp;
	}

	private List<PropertyFilter> getDraftFilters(List<PropertyFilter> filters) {
		PropertyFilter newFilter = new PropertyFilter("EQS_userCd", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		newFilter = new PropertyFilter("EQS_userTypeCd", "0");
		filters.add(newFilter);
		newFilter = new PropertyFilter("EQS_deleteFlg", "0");
		filters.add(newFilter);
		newFilter = new PropertyFilter("EQS_sendFlg", "0");
		filters.add(newFilter);
		return filters;
	}

	/**
	 * 已经删除邮件列表
	 * 
	 * @param filters
	 * @return
	 */
	private Page<OaEmail> getDelMail(List<PropertyFilter> filters, Page<OaEmail> pageMail) {
//		List<PropertyFilter> newFilters = this.getDelMailFilters(filters);
//		return oaEmailManager.findPage(pageMail, newFilters);
		
		
		StringBuffer sql = new StringBuffer("SELECT e.* ");
		sql.append(" from POWERDESK.oa_email e");
		sql.append(" WHERE e.user_cd = :userCd AND e.delete_flg = 1");
		if (StringUtils.isNotBlank(filter_LIKES_subject)) {
			sql.append(" AND e.subject like :subject");
		}
		if (StringUtils.isNotBlank(filter_LIKEC_toUserNames)) {
			sql.append(" AND e.to_User_Names like :toUserNames");
		}
		if (StringUtils.isNotBlank(filter_LIKES_sender)) {
			sql.append(" AND e.sender like :sender");
		}
		if (filter_GED_sendTime != null) {
			sql.append(" AND e.updated_date >= :fromDate");
		}
		if (filter_LED_sendTime != null) {
			sql.append(" AND e.updated_date <= :toDate");
		}
		if (StringUtils.isNotBlank(filter_EQS_emailType)) {
			sql.append(" AND e.email_Type <= :emailType");
		}
		if (pageMail.isOrderBySetted()){
			pageMail.getOrderBy();
			if (Page.ASC.equals(pageMail.getOrder())) {
				sql.append(" order by ").append(pageMail.getOrderBy());
			} else {
				sql.append(" order by ").append(pageMail.getOrderBy()).append(" desc");
			}
		}
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		pram.put("subject", "%" + filter_LIKES_subject + "%");
		pram.put("sender", "%" + filter_LIKES_sender + "%");
		pram.put("toUserNames", "%" + filter_LIKEC_toUserNames + "%");
		pram.put("userTypeCd", "0");
		pram.put("sendFlg", "1");
		pram.put("fromDate", filter_GED_sendTime);
		pram.put("toDate", filter_LED_sendTime);
		pram.put("emailType", filter_EQS_emailType);
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("e", OaEmail.class);
		Page<OaEmail> pageTmp = oaEmailManager.findPageSql(pageMail, sql.toString(), pram, mapClazz);
		return pageTmp;
	}

	private List<PropertyFilter> getDelMailFilters(List<PropertyFilter> filters) {
		// userCd = 当前用户
		PropertyFilter newFilter = new PropertyFilter("EQS_userCd", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		// 删除状态为已删除
		newFilter = new PropertyFilter("EQS_deleteFlg", "1");
		filters.add(newFilter);
		return filters;

	}

	/**
	 * 搜索上一封邮件或者下一封邮件<br>
	 * (包含往上有多少条记录或者往下有多少记录)
	 * 
	 * @param str
	 *            next:下一封 prev:上一封
	 * @return
	 */
	private Page<OaEmail> getNextOrPrePage(String str) {
		OaEmail oaEmail = oaEmailManager.getEntity(getOaEmailId());
		Page<OaEmail> mailPage = new Page<OaEmail>(1);

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

		if (boxId.equals(IN_BOX)) {// 收件箱
			filters = this.getMailFilter(filters);
		} else if (boxId.equals(DRAFT_BOX)) {// 草稿箱
			filters = this.getDraftFilters(filters);
		} else if (boxId.equals(OUT_BOX)) {
			filters = this.getSendMailFilter(filters);
		} else if (boxId.equals(DELETE_BOX)) {
			filters = this.getDelMailFilters(filters);
		} else if (boxId.equals(UN_READ)) {// 未读
			filters = this.getUnReadMailFilter(filters);
		}
		PropertyFilter newFilter = new PropertyFilter("NEQS_oaEmailId", oaEmail.getOaEmailId());
		filters.add(newFilter);
		mailPage.setOrderBy("createdDate");
		if (str.equals("next")) {
			mailPage.setOrder(Page.DESC);
			newFilter = new PropertyFilter("LED_createdDate", oaEmail.getCreatedDate());
		} else {
			mailPage.setOrder(Page.ASC);
			newFilter = new PropertyFilter("GED_createdDate", oaEmail.getCreatedDate());
		}
		filters.add(newFilter);
		return oaEmailManager.findPage(mailPage, filters);

	}

	private String genFileName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		Date now = new Date();
		String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss");
		String genName = dateFormat + RandomUtils.generateString(4) + suffix;
		return genName;
	}

	public void prepareDraft() throws Exception {
		prepareModel();
	}

	public void prepareEdit() throws Exception {
		prepareModel();
	}

	public Page<OaEmail> getPageEmail() {
		return pageEmail;
	}

	public Page<OaEmail> getPageUnRead() {
		return pageUnRead;
	}

	public String getOaEmailId() {
		return oaEmailId;
	}

	public String[] getOaEmailIds() {
		return oaEmailIds;
	}

	public void setOaEmailId(String oaEmailId) {
		this.oaEmailId = oaEmailId;
	}

	public void setOaEmailIds(String[] oaEmailIds) {
		this.oaEmailIds = oaEmailIds;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public Long getNextMailNum() {
		return nextMailNum;
	}

	public Long getPrevMailNum() {
		return prevMailNum;
	}

	public void setNextMailNum(Long nextMailNum) {
		this.nextMailNum = nextMailNum;
	}

	public void setPrevMailNum(Long prevMailNum) {
		this.prevMailNum = prevMailNum;
	}

	public String getNpFlag() {
		return npFlag;
	}

	public void setNpFlag(String npFlag) {
		this.npFlag = npFlag;
	}

	public OaEmailBody getOaEmailBody() {
		return oaEmailBody;
	}

	public void setOaEmailBody(OaEmailBody oaEmailBody) {
		this.oaEmailBody = oaEmailBody;
	}

	public List<WsPlasOrg> getWsPlasOrgs() {
		return wsUaapOrgs;
	}

	public void setWsPlasOrgs(List<WsPlasOrg> wsUaapOrgs) {
		this.wsUaapOrgs = wsUaapOrgs;
	}

	public String getOrgBizCd() {
		return orgBizCd;
	}

	public void setOrgBizCd(String orgBizCd) {
		this.orgBizCd = orgBizCd;
	}

	public List<WsPlasUser> getWsPlasUsers() {
		return wsPlasUsers;
	}

	public void setWsPlasUsers(List<WsPlasUser> wsPlasUsers) {
		this.wsPlasUsers = wsPlasUsers;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public List<AppAttachFile> getAppAttachFiles() {
		return appAttachFiles;
	}

	public void setAppAttachFiles(List<AppAttachFile> appAttachFiles) {
		this.appAttachFiles = appAttachFiles;
	}

	public String[] getAttaFileIds() {
		return attaFileIds;
	}

	public void setAttaFileIds(String[] attaFileIds) {
		this.attaFileIds = attaFileIds;
	}

	public boolean getSendEmailFlag() {
		return sendEmailFlag;
	}

	public void setSendEmailFlag(boolean sendEmailFlag) {
		this.sendEmailFlag = sendEmailFlag;
	}

	public List<OaEmail> getOaEmails4status() {
		return oaEmails4status;
	}

	public void setOaEmails4status(List<OaEmail> oaEmails4status) {
		this.oaEmails4status = oaEmails4status;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public File getFiledata() {
		return filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public Long getReadMailNum() {
		return readMailNum;
	}

	public void setReadMailNum(Long readMailNum) {
		this.readMailNum = readMailNum;
	}

	public String getSearchFlg() {
		return searchFlg;
	}

	public void setSearchFlg(String searchFlg) {
		this.searchFlg = searchFlg;
	}

	public String[] getOrgCds() {
		return orgCds;
	}

	public void setOrgCds(String[] orgCds) {
		this.orgCds = orgCds;
	}

	public void setPageEmail(Page<OaEmail> pageEmail) {
		this.pageEmail = pageEmail;
	}

	public void setPageUnRead(Page<OaEmail> pageUnRead) {
		this.pageUnRead = pageUnRead;
	}

	public List<OaEmailGroup> getOaEmailGroups() {
		return oaEmailGroups;
	}

	public void setOaEmailGroups(List<OaEmailGroup> oaEmailGroups) {
		this.oaEmailGroups = oaEmailGroups;
	}

	public String getFilter_LIKES_sender() {
		return filter_LIKES_sender;
	}

	public void setFilter_LIKES_sender(String filter_LIKES_oaEmailBody_sender) {
		this.filter_LIKES_sender = filter_LIKES_oaEmailBody_sender;
	}

	public String getFilter_LIKEC_toUserNames() {
		return filter_LIKEC_toUserNames;
	}

	public void setFilter_LIKEC_toUserNames(String filter_LIKEC_oaEmailBody_toUserNames) {
		this.filter_LIKEC_toUserNames = filter_LIKEC_oaEmailBody_toUserNames;
	}

	public String getFilter_LIKES_subject() {
		return filter_LIKES_subject;
	}

	public void setFilter_LIKES_subject(String filter_LIKES_oaEmailBody_subject) {
		this.filter_LIKES_subject = filter_LIKES_oaEmailBody_subject;
	}

	public Date getFilter_GED_sendTime() {
		return filter_GED_sendTime;
	}

	public void setFilter_GED_sendTime(Date filterGEDSendTime) {
		filter_GED_sendTime = filterGEDSendTime;
	}

	public Date getFilter_LED_sendTime() {
		return filter_LED_sendTime;
	}

	public void setFilter_LED_sendTime(Date filterLEDSendTime) {
		filter_LED_sendTime = filterLEDSendTime;
	}

	public String getFilter_EQS_emailType() {
		return filter_EQS_emailType;
	}

	public void setFilter_EQS_emailType(String filterEQSEmailType) {
		filter_EQS_emailType = filterEQSEmailType;
	}

	public String getEnableWriteFlg() {
		return enableWriteFlg;
	}

	public void setEnableWriteFlg(String enableWriteFlg) {
		this.enableWriteFlg = enableWriteFlg;
	}

	
	/**
	 * 发送coremail邮件
	 * @return
	 */
	public String coremail(){
		
		return "coremail";
	}
	
	/**
	 * 发送反馈coremail邮件
	 * @return
	 */
	public String saveCoremail(){
		
		return null;
	}
}
