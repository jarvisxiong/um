package com.hhz.ump.dao.oa;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.spring.SpringContextHolder;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.res.ResApproveShareManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaEmail;
import com.hhz.ump.entity.oa.OaEmailBody;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveShare;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.EmailUtil;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasAcct;

@Service
@Transactional
public class OaEmailBodyManager extends BaseService<OaEmailBody, String> {

	private static Log log = LogFactory.getLog(OaEmailBodyManager.class);

	@Autowired
	private OaEmailBodyDao oaEmailBodyDao;

	@Autowired
	private OaEmailManager oaEmailManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;
	
	@Autowired
	private ResApproveShareManager resApproveShareManager;

	/**
	 * 字典：每日事务定时处理字典cd
	 */
	private static String TASK_REMIND = "task_remind";

	/**
	 * <font color="red">发送邮件</font> <br/>
	 * 保存邮件主体，同时需要保存邮件所有者信息
	 * 
	 * @param oaEmailBody
	 */
	public void saveOaEmailBody(OaEmailBody oaEmailBody) throws SQLException, IOException {
		PowerUtils.setEmptyStr2Null(oaEmailBody);

		boolean idFlag = StringUtils.isEmpty(oaEmailBody.getOaEmailBodyId());

		oaEmailBody.setSendFlg("1");
		oaEmailBody.setSendTime(new Date());
		oaEmailBody.setEmailType("0");
		if (oaEmailBody.getCreator() == null) {
			oaEmailBody.setSender(CodeNameUtil.getUserNameByCd(SpringSecurityUtils.getCurrentUiid()));
		}
		oaEmailBodyDao.save(oaEmailBody);

		// 发件人
		if (idFlag) {
			OaEmail oaEmail = new OaEmail();
			oaEmail.setSender(oaEmailBody.getSender());
			oaEmail.setSubject(oaEmailBody.getSubject());
			oaEmail.setToUserNames(oaEmailBody.getToUserNames());
			oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
			oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
			oaEmail.setEmailType(oaEmailBody.getEmailType());
			oaEmail.setSendFlg(oaEmailBody.getSendFlg());
			oaEmail.setOaEmailBody(oaEmailBody);
			oaEmail.setUserCd(SpringSecurityUtils.getCurrentUiid());
			oaEmail.setUserTypeCd("0");
			oaEmail.setReadFlg("1");
			oaEmail.setDeleteFlg("0");
			oaEmail.setAttentionFlg("0");
			oaEmailManager.saveOaEmail(oaEmail);
		} else {
			for (OaEmail oaEmail : oaEmailBody.getOaEmails()) {
				oaEmail.setSender(oaEmailBody.getSender());
				oaEmail.setSubject(oaEmailBody.getSubject());
				oaEmail.setToUserNames(oaEmailBody.getToUserNames());
				oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
				oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
				oaEmail.setEmailType(oaEmailBody.getEmailType());
				oaEmail.setSendFlg(oaEmailBody.getSendFlg());
				oaEmail.setCreatedDate(new Date());
				oaEmailManager.saveOaEmail(oaEmail);
			}
		}

		HashMap toUserMap = new HashMap<String, String>();
		// 收件人
		if (oaEmailBody.getToUserCds() != null && oaEmailBody.getToUserCds().length() > 0) {
			Clob userCds = oaEmailBody.getToUserCds();
			String toUserCds = Util.clob2String(userCds);
			String[] toUsers = toUserCds.split(";");
			for (String userCode : toUsers) {
				toUserMap.put(userCode, "");
				OaEmail oaEmail = new OaEmail();
				oaEmail.setSender(oaEmailBody.getSender());
				oaEmail.setSubject(oaEmailBody.getSubject());
				oaEmail.setToUserNames(oaEmailBody.getToUserNames());
				oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
				oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
				oaEmail.setEmailType(oaEmailBody.getEmailType());
				oaEmail.setSendFlg(oaEmailBody.getSendFlg());
				oaEmail.setOaEmailBody(oaEmailBody);
				oaEmail.setUserCd(userCode);
				oaEmail.setUserTypeCd("1");
				oaEmail.setReadFlg("0");
				oaEmail.setDeleteFlg("0");
				oaEmail.setAttentionFlg("0");
				oaEmailManager.saveOaEmail(oaEmail);
			}
		}

		// 抄送人
		if (oaEmailBody.getCopyUserCds() != null && oaEmailBody.getCopyUserCds().length() > 0) {
			Clob copyUsercd = oaEmailBody.getCopyUserCds();
			String copyUserCds = Util.clob2String(copyUsercd);
			String[] copyUsers = copyUserCds.split(";");
			for (String userCode : copyUsers) {
				if (toUserMap.containsKey(userCode)) {
					continue;
				}
				OaEmail oaEmail = new OaEmail();
				oaEmail.setSender(oaEmailBody.getSender());
				oaEmail.setSubject(oaEmailBody.getSubject());
				oaEmail.setToUserNames(oaEmailBody.getToUserNames());
				oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
				oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
				oaEmail.setEmailType(oaEmailBody.getEmailType());
				oaEmail.setSendFlg(oaEmailBody.getSendFlg());
				oaEmail.setOaEmailBody(oaEmailBody);
				oaEmail.setUserCd(userCode);
				oaEmail.setUserTypeCd("2");
				oaEmail.setReadFlg("0");
				oaEmail.setDeleteFlg("0");
				oaEmail.setAttentionFlg("0");
				oaEmailManager.saveOaEmail(oaEmail);
			}
		}
		if (oaEmailBody.getBccUserCds() != null && oaEmailBody.getBccUserCds().length() > 0) {
			Clob bccUsercd = oaEmailBody.getBccUserCds();
			String bccUserCds = Util.clob2String(bccUsercd);
			String[] bccUsers = bccUserCds.split(";");
			for (String userCode : bccUsers) {
				if (toUserMap.containsKey(userCode)) {
					continue;
				}
				OaEmail oaEmail = new OaEmail();
				oaEmail.setSender(oaEmailBody.getSender());
				oaEmail.setSubject(oaEmailBody.getSubject());
				oaEmail.setToUserNames(oaEmailBody.getToUserNames());
				oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
				oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
				oaEmail.setEmailType(oaEmailBody.getEmailType());
				oaEmail.setSendFlg(oaEmailBody.getSendFlg());
				oaEmail.setOaEmailBody(oaEmailBody);
				oaEmail.setUserCd(userCode);
				oaEmail.setUserTypeCd("3");
				oaEmail.setReadFlg("0");
				oaEmail.setDeleteFlg("0");
				oaEmail.setAttentionFlg("0");
				oaEmailManager.saveOaEmail(oaEmail);
			}
		}
	}

	/**
	 * <font color="red">存入草稿箱</font>
	 * 
	 * @param oaEmailBody
	 */
	public void toDraftOaEmail(OaEmailBody oaEmailBody) {
		boolean idFlag = StringUtils.isEmpty(oaEmailBody.getOaEmailBodyId());

		oaEmailBody.setSendFlg("0");
		oaEmailBody.setEmailType("0");
		if (oaEmailBody.getCreator() == null) {
			oaEmailBody.setSender(CodeNameUtil.getUserNameByCd(SpringSecurityUtils.getCurrentUiid()));
		}
		oaEmailBodyDao.save(oaEmailBody);

		// 发件人
		if (idFlag) {
			OaEmail oaEmail = new OaEmail();
			oaEmail.setSender(oaEmailBody.getSender());
			oaEmail.setSubject(oaEmailBody.getSubject());
			oaEmail.setToUserNames(oaEmailBody.getToUserNames());
			oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
			oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
			oaEmail.setEmailType(oaEmailBody.getEmailType());
			oaEmail.setSendFlg(oaEmailBody.getSendFlg());
			oaEmail.setOaEmailBody(oaEmailBody);
			oaEmail.setUserCd(SpringSecurityUtils.getCurrentUiid());
			oaEmail.setUserTypeCd("0");
			oaEmail.setReadFlg("1");
			oaEmail.setDeleteFlg("0");
			oaEmail.setAttentionFlg("0");
			oaEmailManager.saveOaEmail(oaEmail);
		} else {
			for (OaEmail oaEmail : oaEmailBody.getOaEmails()) {
				oaEmail.setCreatedDate(new Date());
				oaEmailManager.saveOaEmail(oaEmail);
			}
		}
	}
	
	
	/**
	 * 发邮件外部接口(网批专用)
	 * @param resApproveInfo
	 * 			  网批实体 
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param sender
	 *            发件人
	 * @param emailType
	 *            0 普通邮件 1 审批邮件
	 * @param toUserUiids
	 *            收件人UIID列表
	 */
	public void sendData2Email(ResApproveInfo resApproveInfo,List<ResApproveShare> sharesRemove,List<ResApproveShare> shares,
			String subject, String content, String sender, String emailType, String... toUserUiids) {
		List<ResApproveShare> shareList = new ArrayList<ResApproveShare>();
		List<ResApproveShare> saveShareList = new ArrayList<ResApproveShare>();
		List<ResApproveShare> saveRemoveShareList = new ArrayList<ResApproveShare>();
		OaEmailBody oaEmailBody = new OaEmailBody();
		if (StringUtils.isNotEmpty(subject)) {
			oaEmailBody.setSubject(subject);
		} else {
			oaEmailBody.setSubject("[无主题]");
		}

		if (StringUtils.isNotEmpty(content)) {
			oaEmailBody.setContent(new ClobImpl(content));
		}

		//只保存新增用户，已存在用户不保存
		StringBuilder toUserCds = new StringBuilder();
		StringBuilder toUsers = new StringBuilder();
		
		//收件人详细
		StringBuilder toUserCdsDetail = new StringBuilder();
		StringBuilder toUsersDetail = new StringBuilder();
		
		//已经存在的共享用户
		StringBuffer shareToUserCds = new StringBuffer();
		StringBuffer shareToUserCdsTmp = new StringBuffer();
		StringBuffer shareToRemarks = new StringBuffer();
		
		//查出已经共享用户
		for(ResApproveShare resApproveShare : sharesRemove){
			shareToUserCds.append(resApproveShare.getUserCd()).append(";");
			shareToRemarks.append(resApproveShare.getRemark()).append(";");
		}
		shareToUserCdsTmp = shareToUserCds;
		for (String userUiid : toUserUiids) {
			//判断是否已经存在
			String tempUserCd = userUiid+";";
			
			//给所有需要共享操作用户
			toUserCdsDetail.append(userUiid);
			toUserCdsDetail.append(";");
			String userName = CodeNameUtil.getUserNameByCd(userUiid);
			toUsersDetail.append(userName);
			toUsersDetail.append(";");
			
			if(shareToUserCds.toString().indexOf(tempUserCd) >= 0){
				shareToUserCds = new StringBuffer(shareToUserCds.toString().replaceAll(tempUserCd, ""));
				continue;
			}
			//只给需要新增的用户不包括已经存在的
			toUserCds.append(userUiid);
			toUserCds.append(";");
			userName = CodeNameUtil.getUserNameByCd(userUiid);
			toUsers.append(userName);
			toUsers.append(";");
		}
		toUserUiids = toUserCds.toString().split(";");
		
		
		oaEmailBody.setToUserCds(new ClobImpl(toUserCdsDetail.toString()));
		oaEmailBody.setToUserNames(new ClobImpl(toUsersDetail.toString()));

		oaEmailBody.setCreator(sender);
		oaEmailBody.setSender(CodeNameUtil.getUserNameByCd(sender));

		oaEmailBody.setSendFlg("1");
		oaEmailBody.setSendTime(new Date());
		oaEmailBody.setImportLevelCd("3");

		oaEmailBody.setEmailType(emailType);

		oaEmailBodyDao.save(oaEmailBody);
		//放入提醒实体内容ID
		for(ResApproveShare resShare : shares){
					resShare.setRemark(oaEmailBody.getOaEmailBodyId());
					saveShareList.add(resShare);
		}
		//删除共享信息时清掉所有已发送的提醒
		if(sharesRemove != null && sharesRemove.size()>0){
			ResApproveShare resApproveShare = sharesRemove.get(0);
			StringBuffer hql = new StringBuffer("from OaEmail oaEmail where oaEmail.oaEmailBody.oaEmailBodyId = :oaEmailBodyId");
			Map<String ,Object> m = new HashMap<String , Object>();
			
			//将有可能为空的情况排除掉
			if(StringUtils.isNotBlank(resApproveShare.getRemark())){
				m.put("oaEmailBodyId", resApproveShare.getRemark());
				List<OaEmail> oaList = oaEmailManager.find(hql.toString(), m);
				//如果还在选择中则将bodyid更新为最新的，否则删除
				for(OaEmail oa : oaList){
					//判断是否有去掉用户有则删除
					if(shareToUserCds.toString().indexOf(oa.getUserCd())>=0){
						oaEmailManager.delete(oa);
					}else	
					//对已经存在用户做更新
					if(shareToUserCdsTmp.toString().indexOf(oa.getUserCd())>=0){
						oa.setOaEmailBody(oaEmailBody);
						oaEmailManager.saveOaEmail(oa);
					} 
				}
			}
				
			
		}
		//更新共享表中数据，先删除老的数据再做整个新增
		resApproveShareManager.refeshShares(sharesRemove, saveShareList);

		//新增新用户
		for (String userUiid : toUserUiids) {
			if(StringUtils.isNotBlank(userUiid)){
					OaEmail oaEmail = new OaEmail();
					oaEmail.setCreator(sender);
					oaEmail.setSender(oaEmailBody.getSender());
					oaEmail.setSubject(oaEmailBody.getSubject());
					oaEmail.setToUserNames(oaEmailBody.getToUserNames());
					oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
					oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
					oaEmail.setEmailType(oaEmailBody.getEmailType());
					oaEmail.setSendFlg(oaEmailBody.getSendFlg());
					oaEmail.setOaEmailBody(oaEmailBody);
					oaEmail.setUserCd(userUiid);
					oaEmail.setUserTypeCd("1");
					oaEmail.setReadFlg("0");
					oaEmail.setDeleteFlg("0");
					oaEmail.setAttentionFlg("0");
					oaEmailManager.saveOaEmail(oaEmail);
				
			}
			
		}
		
		
		// this.send2OutEmail(oaEmailBody);
	}

	/**
	 * 发邮件外部接口
	 * 
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param sender
	 *            发件人
	 * @param emailType
	 *            0 普通邮件 1 审批邮件
	 * @param toUserUiids
	 *            收件人UIID列表
	 */
	public void sendData2Email(String subject, String content, String sender, String emailType, String... toUserUiids) {
		OaEmailBody oaEmailBody = new OaEmailBody();
		if (StringUtils.isNotEmpty(subject)) {
			oaEmailBody.setSubject(subject);
		} else {
			oaEmailBody.setSubject("[无主题]");
		}

		if (StringUtils.isNotEmpty(content)) {
			oaEmailBody.setContent(new ClobImpl(content));
		}

		StringBuilder toUserCds = new StringBuilder();
		StringBuilder toUsers = new StringBuilder();
		for (String userUiid : toUserUiids) {
			toUserCds.append(userUiid);
			toUserCds.append(";");
			String userName = CodeNameUtil.getUserNameByCd(userUiid);
			toUsers.append(userName);
			toUsers.append(";");
		}
		oaEmailBody.setToUserCds(new ClobImpl(toUserCds.toString()));
		oaEmailBody.setToUserNames(new ClobImpl(toUsers.toString()));

		oaEmailBody.setCreator(sender);
		oaEmailBody.setSender(CodeNameUtil.getUserNameByCd(sender));

		oaEmailBody.setSendFlg("1");
		oaEmailBody.setSendTime(new Date());
		oaEmailBody.setImportLevelCd("3");

		oaEmailBody.setEmailType(emailType);

		oaEmailBodyDao.save(oaEmailBody);

		for (String userUiid : toUserUiids) {
			OaEmail oaEmail = new OaEmail();
			oaEmail.setCreator(sender);
			oaEmail.setSender(oaEmailBody.getSender());
			oaEmail.setSubject(oaEmailBody.getSubject());
			oaEmail.setToUserNames(oaEmailBody.getToUserNames());
			oaEmail.setUploadFlg(oaEmailBody.getUploadFlg());
			oaEmail.setImportLevelCd(oaEmailBody.getImportLevelCd());
			oaEmail.setEmailType(oaEmailBody.getEmailType());
			oaEmail.setSendFlg(oaEmailBody.getSendFlg());
			oaEmail.setOaEmailBody(oaEmailBody);
			oaEmail.setUserCd(userUiid);
			oaEmail.setUserTypeCd("1");
			oaEmail.setReadFlg("0");
			oaEmail.setDeleteFlg("0");
			oaEmail.setAttentionFlg("0");
			oaEmailManager.saveOaEmail(oaEmail);
		}
		// this.send2OutEmail(oaEmailBody);
	}

	public void editOaEmail(OaEmailBody oaEmailBody) {
		// oaEmailBody.setSendTime(new Date());
		oaEmailBodyDao.save(oaEmailBody);
	}

	public void deleteOaEmailBody(String id) {
		// OaEmailBody oaEmailBody = this.getEntity(id);
		// oaEmailManager.delete(oaEmailBody.getOaEmails());
		// oaEmailBodyDao.delete(oaEmailBody);

		String hql = "delete from OaEmail where oaEmailBody.oaEmailBodyId =?";
		String hqlBody = "delete from OaEmailBody where oaEmailBodyId=?";
		oaEmailBodyDao.batchExecute(hql, id);
		oaEmailBodyDao.batchExecute(hqlBody, id);
	}
	
	public void sendToAdmin(String id){
		OaEmailBody body = getEntity(id);
		//清空收件人/抄送/密送人
		body.setToUserCds(new ClobImpl("admin;"));
		body.setToUserNames(new ClobImpl("应用管理员;"));
	    body.setCopyUserCds(new ClobImpl(""));
		body.setCopyUserNames(new ClobImpl(""));
		body.setBccUserCds(new ClobImpl(""));
		body.setBccUserNames(new ClobImpl(""));
		oaEmailBodyDao.save(body);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<OaEmailBody, String> getDao() {
		return oaEmailBodyDao;
	}

	public class sendOutEmailThread extends Thread {

		private OaEmailBody oaEmailBody;

		private String sender;

		private String password;

		private String realPath;

		private String rootPath;

		public sendOutEmailThread(OaEmailBody oaEmailBody, String sender, String password, String rootPath, String realPath) {
			this.oaEmailBody = oaEmailBody;
			this.sender = sender;
			this.password = password;
			this.rootPath = rootPath;
			this.realPath = realPath;
		}

		@Override
		public void run() {
			try {
				String toUserCds = Util.clob2String(oaEmailBody.getToUserCds());
				String copyUserCds = Util.clob2String(oaEmailBody.getCopyUserCds());
				String bccUserCds = Util.clob2String(oaEmailBody.getBccUserCds());
				
				String content = Util.clob2String(oaEmailBody.getContent());
				String subject = oaEmailBody.getSubject();
				
				String[] toUsers = toUserCds.split(";");
				String[] copyUsers = copyUserCds.split(";");
				String[] bccUsers = bccUserCds.split(";");
				
				List<String> toUsersEmail = new ArrayList<String>();
				List<String> copyUsersEmail = new ArrayList<String>();
				List<String> bccUsersEmail = new ArrayList<String>();
 
				WsPlasAcct acct = null;
				for (int i = 0; i < toUsers.length; i++) {
					if (StringUtils.isBlank(toUsers[i])) {
						continue;
					}
					acct = PlasCache.getAcctByUiid(toUsers[i]);
					if(acct == null){
						continue;
					}
					if(Util.emailEnable(acct.getEmailFlg())){
						toUsersEmail.add(toUsers[i] + "@powerlong.com");
					}
				}
				for (int i = 0; i < copyUsers.length; i++) {
					if (StringUtils.isBlank(copyUsers[i])) {
						continue;
					}
					acct = PlasCache.getAcctByUiid(copyUsers[i]);
					if(acct == null){
						continue;
					}
					if(Util.emailEnable(acct.getEmailFlg())){
						copyUsersEmail.add(copyUsers[i] + "@powerlong.com");
					} 
				}
				for (int i = 0; i < bccUsers.length; i++) {
					if (StringUtils.isBlank(bccUsers[i])) {
						continue;
					}
					acct = PlasCache.getAcctByUiid(bccUsers[i]);
					if(acct == null){
						continue;
					}
					if(Util.emailEnable(acct.getEmailFlg())){
						bccUsersEmail.add(bccUsers[i] + "@powerlong.com");
					} 
				}

				
				List<AppAttachFile> attaFiles = appAttachFileManager.getAttaFileByBizEntityId(oaEmailBody.getOaEmailBodyId());
				List<File> files = new ArrayList<File>();
				File tmpFile = null;
				AppAttachFile tmpAttach = null;
				if(attaFiles!= null && attaFiles.size() > 0){
					for(int i=0; i<attaFiles.size(); i++){
						tmpAttach = attaFiles.get(i);
						tmpFile = new File(tmpAttach.getFilePath() + "\\" +tmpAttach.getFileName());
						if(tmpFile != null){
							files.add(tmpFile);
						}
					}
				}

				EmailUtil.send(sender, password, 
						toUsersEmail.toArray(new String[toUsersEmail.size()]), 
						copyUsersEmail.toArray(new String[copyUsersEmail.size()]), 
						bccUsersEmail.toArray(new String[bccUsersEmail.size()]), 
						subject, content, attaFiles, files, rootPath, realPath);
				

				//清空
				OaEmailBodyManager tmpManager = SpringContextHolder.getBean("oaEmailBodyManager");
				tmpManager.sendToAdmin(oaEmailBody.getOaEmailBodyId());
				log.info("发送反馈邮件到外部邮箱成功!["+sender +"]主题：" + subject);
				
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("发送反馈邮件出现异常!["+sender +"]" + e.getMessage(),e);
			}
		}
	}

	/**
	 * 将邮件发送至外部邮件箱
	 * 
	 * @param oaEmailBody
	 */
	public void send2OutEmail(OaEmailBody oaEmailBody) {
		String sender = SpringSecurityUtils.getCurrentUiid();
		String pwd =LoginUtil.getPwd(null);
		String rootPath = ServletActionContext.getServletContext().getRealPath("");
		String realPath = Struts2Utils.getRequest().getContextPath();
		Thread thread = new sendOutEmailThread(oaEmailBody, sender, pwd, rootPath, realPath);
		thread.start();
	}

	/**
	 * 删除用户已经全部删除邮件的邮件体
	 */
	public void deleteEmailBodyWithNoEmail() {
		String hql = " from OaEmailBody where (select count(*) from OaEmail b where b.oaEmailBody.oaEmailBodyId = a.oaEmailBodyId)>0";
		oaEmailBodyDao.batchExecute(hql);
	}

	/**
	 * 对系统中有效用户自动发送每日事务提醒
	 * 
	 * @throws UnknownHostException
	 */
	public void taskRemind() throws Exception {
		String ipAddress = InetAddress.getLocalHost().getHostAddress();
		log.error(ipAddress);
//		if (ipAddress.equals(Constants.PROD_IP)) {
			if (DateOperator.isWeekday(new Date())) {
				Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.QUARTZ_TYPE_CD);
				Date startDate = new Date();
				log.error("开始发送每日事务提醒,是否启用:" + map.get(TASK_REMIND));
				if ("0".equals(map.get(TASK_REMIND)))
					return;
//				this.taskRemindPersonal("xujian", SpringSecurityUtils.getCurrentDeptCd());
				int count = 1;
				List<WsPlasAcct> accts = PlasCache.getAcctActiveList();
				log.error("总人员：" + accts.size());
				for (WsPlasAcct acct : accts) {
					log.error("序号:" + count + "姓名：" + acct.getUserName() + ",工号:" + acct.getUiid());
					try {
						this.taskRemindPersonal(acct.getUiid(), acct.getOrgCd());
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}

					count++;
				}
				Date endDate = new Date();

				log.error("每日事务提醒发送完毕！用时:" + DateOperator.getMinutes(startDate, endDate));
			} else {
				log.error("每日事务简报：is weekend day!");
			}
//		}
	}

	/**
	 * 每日事务提醒
	 * 
	 * @throws UnknownHostException
	 */
	public void taskRemindPersonal(String uiid, String orgCd) throws UnknownHostException {

		Map<String, Object> searchMap = new HashMap<String, Object>();

		// 用户名以","逗号分隔
		String like_uiid_d = "%" + uiid + ",%";
		Clob uiid_clob_d = new ClobImpl(like_uiid_d);
		// 用户名以";"分号分隔
		String like_uiid_f = "%" + uiid + ";%";
		Clob uiid_clob_f = new ClobImpl(like_uiid_f);

		searchMap.put("uiid", uiid);
		searchMap.put("uiid_like_d", like_uiid_d);
		searchMap.put("uiid_like_f", like_uiid_f);
		searchMap.put("uiid_clob_d", uiid_clob_d);
		searchMap.put("uiid_clob_f", uiid_clob_f);

		searchMap.put("dept", "%," + orgCd + ",%");

		Date today = DateOperator.truncDate(new Date());
		Date nextDay = DateOperator.addDays(today, 1);
		Date next2Day = DateOperator.addDays(today, 3);
		Date before2Day = DateOperator.addDays(today, -2);
		searchMap.put("today", today);
		searchMap.put("nextDay", nextDay);
		searchMap.put("next2Day", next2Day);
		searchMap.put("before2Day", before2Day);

		String rootPath = getRootPath();

		String todayStr = DateOperator.formatDate(new Date(), "yyyy-MM-dd HH:mm");

		StringBuffer mail = new StringBuffer("<div style='line-height:25px;'>您好，截止目前(" + todayStr + ")需要您关注的事务如下:<br/>")
				.append("<ol style='padding-left:20px;'>");

		// // 我发起的审批
		// String myRes = this.myRes(searchMap);
		// mail.append(myRes);
		// // 等待我的审批
		// String waitRes = this.waitRes(searchMap);
		// mail.append(waitRes);
		// // 共享给我的审批
		// String shareRes = this.shareRes(searchMap);
		// mail.append(shareRes);

		String resInfo = this.resInfo(searchMap);
		mail.append(resInfo);

		String hql;

		// 指令单相关
		StringBuffer taskBuffer = new StringBuffer();// 任务类型的字符串拼接(指令单、月计划、中心内部任务)
		hql = "from OaMeeting where (responsiblePerson like :uiid_like_f or coordinatePerson like :uiid_like_f ) and status < '3' and isDeleted = '0' and hiddenFlg='0' ";
		long ceoTask = super.countHqlResult(hql, searchMap);
		if (ceoTask > 0) {
			taskBuffer.append("指令单 中有<span style='color:green;font-weight:bold;'> ").append(ceoTask).append(" </span>项与您有关");
			// 指令单过期
			hql = hql + " and targetDate is not null and targetDate <= :nextDay";
			long ceoTaskDelay = super.countHqlResult(hql, searchMap);
			if (ceoTaskDelay > 0) {
				taskBuffer.append("，其中<span style='color:red;font-weight:bold;'> ").append(ceoTaskDelay).append(" </span>项已过期");
			}
			String ceoLink = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.TabUtils.newTab('ceomeeting','指令单跟踪','"
					+ rootPath + "/oa/oa-meeting!list.action?myTask=true',true) >(点击浏览)</span>";
			taskBuffer.append(ceoLink);
			taskBuffer.append("；<br/>");
		}

		// 中心月计划相关
		hql = "from PlanWork p,JbpmTask t1,JbpmTaskCandidate t2 where p.planWorkId=t1.jbpmId and t1.jbpmTaskId=t2.jbpmTask.jbpmTaskId and (t2.userCd like :uiid_like_f or t2.userCd = :uiid) and p.statusCd < '3' ";
		long centerMonth = super.countHqlResult(hql, searchMap);
		if (centerMonth > 0) {
			taskBuffer.append("中心月计划中有<span style='color:green;font-weight:bold;'> ").append(centerMonth).append(" </span>项与您有关");
			// 中心月计划过期
			hql = hql + " and p.targetDate is not null and p.targetDate <= :nextDay";
			long monthDelay = super.countHqlResult(hql, searchMap);
			if (monthDelay > 0) {
				taskBuffer.append("，其中<span style='color:red;font-weight:bold;'> ").append(monthDelay).append(" </span>项已过期");
			}
			String monthLink = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.TabUtils.newTab('centerMonth','中心月计划','"
					+ rootPath + "/plan/plan-work!getAllPlan.action?myTask=true',true) >(点击浏览)</span>";
			taskBuffer.append(monthLink);
			taskBuffer.append("；</br>");
		}

		// 中心内部计划相关
		hql = "from PlanWorkCenter where principal like :uiid_like_f and statusCd < '3' ";
		long centerInner = super.countHqlResult(hql, searchMap);
		if (centerInner > 0) {
			taskBuffer.append("中心内部计划中有<span style='color:green;font-weight:bold;'> ").append(centerInner).append(" </span>项与您有关");
			// 中心内部任务过期
			hql = hql + " and targetDate is not null and targetDate <= :nextDay";
			long innerDelay = super.countHqlResult(hql, searchMap);
			if (innerDelay > 0) {
				taskBuffer.append("，其中<span style='color:red;font-weight:bold;'> ").append(innerDelay).append(" </span>项已过期");
			}
			String innerLink = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.TabUtils.newTab('centerInner','中心内部任务','"
					+ rootPath + "/plan/plan-work-center!getAllPlan.action?myTask=true',true) >(点击浏览)</span>";
			taskBuffer.append(innerLink);
			taskBuffer.append("；</br>");
		}

		// 添加到邮件内容中
		if (taskBuffer.length() > 0) {
			mail.append("<li>").append(taskBuffer).append("</li>");
		}

		// 便签条数
		hql = "from DlyNote where creator  = :uiid and tipStartDate > :nextDay and tipStartDate < :next2Day";
		long noteNum = super.countHqlResult(hql, searchMap);
		if (noteNum > 0) {
			mail.append("<li>").append("您未来两天有<span style='color:green;font-weight:bold;'> ").append(noteNum).append(" </span>条便签待处理").append(
					"；</li>");
		}
		mail.append("</ol>");
		mail.append("<br/>烦请关注，谢谢！");
		mail.append("</div>");

		Long total = ceoTask + centerMonth + centerInner + noteNum;
		// String res = myRes + waitRes + shareRes;
		if (total > 0 || !resInfo.trim().equals("")) {
			this.sendData2Email("[系统邮件]事务简报", mail.toString(), "email_admin", "0", uiid);
		}

	}

	private String getRootPath() {
		String rootPath = "/PowerDesk";
		String ipAddress = "";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ipAddress.equals("192.168.180.240")) {
			rootPath = "";
		}
		if (ipAddress.equals("192.168.170.20")) {
			rootPath = "";
		}
		return rootPath;
	}

	/**
	 * 发起的审批
	 * 
	 * @param uiid
	 * @return
	 * @throws UnknownHostException
	 */
	private String myRes(Map<String, Object> paramMap) throws UnknownHostException {
		String rootPath = getRootPath();
		// 进行中js方法
		String progresJs = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.showAll('" + rootPath
				+ "/res/res-approve-info.action?filter_LIKES_statusCd=1','resApprove') >(点击浏览)</span>";
		// 已完成js方法
		String completeJs = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.showAll('" + rootPath
				+ "/res/res-approve-info.action?filter_LIKES_statusCd=2','resApprove') >(点击浏览)</span>";
		// 已驳回js方法
		String rejectJs = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.showAll('" + rootPath
				+ "/res/res-approve-info.action?filter_LIKES_statusCd=3','resApprove') >(点击浏览)</span>";

		StringBuffer sb = new StringBuffer();
		// 我发起的审批
		String hql = "from ResApproveInfo where userCd = :uiid and (statusCd ='1' or statusCd = '2' and (updatedDate between :today and :nextDay) or  statusCd = '3' and (updatedDate between :today and :nextDay)) ";
		long total = super.countHqlResult(hql, paramMap);
		if (total > 0) {
			sb.append("<li>").append("您发起的网上审批文件有<span style='color:green;font-weight:bold;'> ");
			sb.append(total).append(" </span>项，其中:<br/>");

			// 进行中
			String progressHql = hql + " and statusCd = '1' ";
			long progress = super.countHqlResult(progressHql, paramMap);
			sb.append("<span style='color:green;font-weight:bold;'> ");
			sb.append(progress).append(" </span>项进行中").append(progresJs).append(";<br/>");

			// 今日完成
			String completeHql = hql + " and statusCd = '2' and (updatedDate between :today and :nextDay)";
			long complete = super.countHqlResult(completeHql, paramMap);
			sb.append("<span style='color:green;font-weight:bold;'> ");
			sb.append(complete).append(" </span>项今日完成").append(completeJs).append(";<br/>");

			// 今日驳回
			String rejectHql = hql + " and statusCd = '3' and (updatedDate between :today and :nextDay)";
			long reject = super.countHqlResult(rejectHql, paramMap);
			sb.append("<span style='color:green;font-weight:bold;'> ");
			sb.append(reject).append(" </span>项今日驳回").append(rejectJs).append(";<br/>");

			sb.append("</li>");
		}

		return sb.toString();
	}

	/**
	 * 等待审批
	 * 
	 * @param uiid
	 * @return
	 */
	private String waitRes(Map<String, Object> paramMap) {
		String rootPath = getRootPath();
		// 进行中js方法
		String progresJs = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.showAll('" + rootPath
				+ "/res/res-approve-info.action?filter_LIKES_statusCd=1','resApprove') >(点击浏览)</span>";

		StringBuffer sb = new StringBuffer();
		// 等待我的审批
		String hql = "from ResApproveInfo where (approveUserCd=:uiid or approveUserCd like :uiid_like_f)";
		long total = super.countHqlResult(hql, paramMap);
		if (total > 0) {
			sb.append("<li>").append("等待您审批的文件有<span style='color:green;font-weight:bold;'> ");
			sb.append(total).append(" </span>项").append(progresJs).append("，其中:<br/>");

			// 今日新增
			String completeHql = hql + " and (updatedDate between :today and :nextDay)";
			long complete = super.countHqlResult(completeHql, paramMap);
			sb.append("今日新增<span style='color:green;font-weight:bold;'> ");
			sb.append(complete).append(" </span>项;<br/>");

			// 昨日新增
			String more24Hql = hql + " and (updatedDate between :before2Day and :today)";
			long more24 = super.countHqlResult(more24Hql, paramMap);
			sb.append("已经超过24小时的有<span style='color:green;font-weight:bold;'> ");
			sb.append(more24).append(" </span>项;<br/>");

			// 超过48小时的
			String more48Hql = hql + " and updatedDate <= :before2Day";
			long more48 = super.countHqlResult(more48Hql, paramMap);
			sb.append("已经超过48小时的有<span style='color:green;font-weight:bold;'> ");
			sb.append(more48).append(" </span>项;<br/>");

			sb.append("</li>");
		}

		return sb.toString();
	}

	private String resInfo(Map<String, Object> paramMap) {
		String rootPath = getRootPath();
		// 进行中js方法
		String progresJs = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.showAll('" + rootPath
				+ "/res/res-approve-info.action?filter_LIKES_statusCd=1','resApprove') >(点击浏览)</span>";

		StringBuffer sbMsg = new StringBuffer();
		StringBuffer sbHql = new StringBuffer();
		// 网批中过期的记录
		sbHql.append("select r from ResApproveInfo as r,ResApproveNode as n ");
		sbHql.append("where r.statusCd='1' and r.resApproveInfoId=n.resApproveInfo.resApproveInfoId ");
		sbHql.append("and (r.approveUserCd=:uiid or r.approveUserCd like :uiid_like_f) ");
		sbHql.append("and r.nodeCd=n.nodeCd and r.approveUserCd=n.userCd and r.approveLevel=n.approveLevel ");
		sbHql.append("and (current_date() - r.updatedDate) * 24 - n.timeLimit > ");
		StringBuffer sbTotal = new StringBuffer(sbHql).append("0");
		long total = super.countHqlResult(sbTotal.toString(), paramMap);
		if (total > 0) {
			sbMsg.append("<li>").append("网上审批中过期的记录有");
			sbMsg.append("<span style='color:green;font-weight:bold;'> ");
			sbMsg.append(total).append(" </span>项").append(progresJs).append("，其中:<br/>");
			// 过期12小时
			StringBuffer sb12 = new StringBuffer(sbHql).append("12");
			long total12 = super.countHqlResult(sb12.toString(), paramMap);
			sbMsg.append("过期12小时有<span style='color:green;font-weight:bold;'> ");
			sbMsg.append(total12).append(" </span>项;<br/>");
			// 过期18小时
			StringBuffer sb18 = new StringBuffer(sbHql).append("12");
			long total18 = super.countHqlResult(sb18.toString(), paramMap);
			sbMsg.append("过期18小时有<span style='color:green;font-weight:bold;'> ");
			sbMsg.append(total18).append(" </span>项;<br/>");
			// 过期24小时
			StringBuffer sb24 = new StringBuffer(sbHql).append("12");
			long total24 = super.countHqlResult(sb24.toString(), paramMap);
			sbMsg.append("过期24小时有<span style='color:green;font-weight:bold;'> ");
			sbMsg.append(total24).append(" </span>项;<br/>");
			sbMsg.append("</li>");
		}
		return sbMsg.toString();
	}

	private String shareRes(Map<String, Object> paramMap) {
		String rootPath = getRootPath();
		// 进行中js方法
		String progresJs = "<span style='text-decoration: underline;color:#0167A2;cursor: pointer;' onclick=parent.parent.showAll('" + rootPath
				+ "/res/res-approve-info.action?filter_LIKES_statusCd=1','resApprove') >(点击浏览)</span>";

		StringBuffer sb = new StringBuffer();
		String hql = "from ResApproveInfo as res,ResApproveShare as ras where res.resApproveInfoId = ras.resApproveInfo.resApproveInfoId and ras.userCd=:uiid";
		long share = super.countHqlResult(hql, paramMap);
		if (share > 0) {
			sb.append("<li>").append("共享给您的审批文件有<span style='color:green;font-weight:bold;'> ");
			sb.append(share).append(" </span>项").append(progresJs);
			hql += " and ras.createdDate <= :before2Day";
			long more48 = super.countHqlResult(hql, paramMap);
			sb.append("，超过48小时未答复的有<span style='color:red;font-weight:bold;'> ").append(more48).append(" </span>项；");
			sb.append("</li>");
		}
		return sb.toString();
	}

	public List<String> sendEmail2Pwd123User() {
		StringBuilder content = new StringBuilder();
		content.append("您好：<br />为提高系统安全性，防止信息泄露和损失。系统将启动账户密码保护机制，经筛选您的PD密码仍为初始密码（123）<br />");
		content.append("请您于2011年2月23日17时前修改密码，如未修改届时您的账户将会被系统冻结，只能通过联系总裁办企管部的方式进行恢复。<br />");
		content.append("请您看到此邮件即修改密码，谢谢！<br />");
		content.append("修改密码步骤如下<br />");
		content.append("1.&nbsp; 登录PowerDesk2.1&nbsp;&nbsp;–&gt; 选择菜单栏 -&gt; 个人设置&nbsp;-&gt; 修改密码<p></p>");
		content.append("<p>2.&nbsp; 输入旧、新密码,如下图</p>");
		content.append("<p>&nbsp;&nbsp;&nbsp;&nbsp; <span style='color:#ff0000;'>特别说明： 密码尽量不包含特殊符号(如\"&lt;\" \"&gt;\"), 否则加密文件可能打不开.</span></p>");
		content.append("<p><div><img src='/help/upload/201007271824228R51.gif' /></div></p>");

		List<WsPlasAcct> accts = PlasCache.getAcctActiveList();
		List<String> list = new ArrayList<String>();
		
		String pwd = null;
		for (WsPlasAcct acct : accts) {
			pwd = acct.getLoginInPassword();
			if (pwd.equals("202cb962ac59075b964b07152d234b70")) {
				sendData2Email("关于修改PD系统密码的通知", content.toString(), "email_admin", "0", acct.getUiid());
				list.add(acct.getUserName());
			}
		}
		return list;
	}
}
