/**
 * 
 */
package com.hhz.ump.web.sc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.lob.ClobImpl;
import org.mozilla.intl.chardet.RandomSeriNoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.dao.plan.ExecPlanLayoutManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.sc.ScContractInfoAttachManager;
import com.hhz.ump.dao.sc.ScContractInfoFlowManager;
import com.hhz.ump.dao.sc.ScContractInfoSayManager;
import com.hhz.ump.dao.sc.ScContractMarkHisManager;
import com.hhz.ump.dao.sc.ScContractMarkManager;
import com.hhz.ump.dao.sc.ScContractPrintManager;
import com.hhz.ump.dao.sc.ScContractTempletFillManager;
import com.hhz.ump.dao.sc.ScContractTempletHisManager;
import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.dao.sc.ScContractTempletManager;
import com.hhz.ump.dao.sc.ScContractTempletTypeManager;
import com.hhz.ump.dao.sc.ScContractTempletUserManager;
import com.hhz.ump.dao.sup.SupApproveResManager;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveNode;
import com.hhz.ump.entity.sc.ScContractAttachUpload;
import com.hhz.ump.entity.sc.ScContractInfoAttach;
import com.hhz.ump.entity.sc.ScContractInfoFlow;
import com.hhz.ump.entity.sc.ScContractInfoSay;
import com.hhz.ump.entity.sc.ScContractMark;
import com.hhz.ump.entity.sc.ScContractMarkHis;
import com.hhz.ump.entity.sc.ScContractPrint;
import com.hhz.ump.entity.sc.ScContractPrintInfo;
import com.hhz.ump.entity.sc.ScContractTemplet;
import com.hhz.ump.entity.sc.ScContractTempletFill;
import com.hhz.ump.entity.sc.ScContractTempletFillJson;
import com.hhz.ump.entity.sc.ScContractTempletHis;
import com.hhz.ump.entity.sc.ScContractTempletInfo;
import com.hhz.ump.entity.sc.ScContractTempletType;
import com.hhz.ump.entity.sc.ScContractTempletUser;
import com.hhz.ump.entity.sup.SupApproveRes;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.service.AcgiUser;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.SimplePredicate;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.sc.vo.VoScContractFromResAttach;
import com.hhz.ump.web.sc.vo.VoScContractFromResField;
import com.hhz.ump.web.sc.vo.VoScContractMark;
import com.hhz.ump.web.sc.vo.VoScContractTempletInfo;
import com.hhz.uums.entity.ws.WsPlasRole;

/**
 * @author qlb 2/9/2012 version 1.0.0
 * 
 */
@Namespace("/sc")
public class ScContractTempletInfoAction extends CrudActionSupport<ScContractTempletInfo> {

	/**
	 * 10:新增中
	 */
	public static final String CON_APPROVE_STATUS_NEW = "10";
	/**
	 * 20:审核中
	 */
	public static final String CON_APPROVE_STATUS_SUBMIT = "20";
	/**
	 * 30:修改中
	 */
	public static final String CON_APPROVE_STATUS_MARK = "30";
	/**
	 * 40:审核通过"
	 */
	public static final String CON_APPROVE_STATUS_MERGE = "40";

	/**
	 * 45驳回
	 */
	public static final String CON_APPROVE_STATUS_BACK = "45";
	/**
	 * 50:网批中
	 */
	public static final String CON_APPROVE_STATUS_ING = "50";
	/**
	 * 60:网批结束
	 */
	public static final String CON_APPROVE_STATUS_APP_FINISHL = "60";
	/**
	 * 70:合同完成、可打印
	 */
	public static final String CON_APPROVE_STATUS_FINISHL = "70";
	/**
	 * 80:合同签署
	 */
	public static final String CON_APPROVE_STATUS_EXTRA = "80";

	/**
	 * ，
	 */
	public static final String COMMA = ",";

	@Autowired
	ScContractTempletInfoManager scContractTempletInfoManager;
	@Autowired
	ScContractTempletFillManager scContractTempletFillManager;
	@Autowired
	ScContractTempletHisManager scContractTempletHisManager;

	@Autowired
	ScContractMarkManager scContractTepletMarkManager;

	@Autowired
	ScContractMarkManager scContractMarkManager;
	@Autowired
	ScContractPrintManager scContractPrintManager;
	@Autowired
	ScContractInfoFlowManager scContractInfoFlowManager;
	@Autowired
	ResApproveInfoManager resApproveInfoManager;
	@Autowired
	ScContractTempletUserManager contractTempletUserManager;

	@Autowired
	ScContractTempletManager ScContractTempletManager;

	@Autowired
	ScContractTempletTypeManager scContractTempletTypeManager;
	@Autowired
	ScContractInfoSayManager scContractInfoSayManager;
	@Autowired
	ScContractInfoAttachManager scContractInfoAttachManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private ExecPlanLayoutManager execPlanLayoutManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private SupApproveResManager supApproveResManager;
	@Autowired
	private SupBasicManager supBasicManager;
	@Autowired
	private ContProjectCodeManager projectCodeManager;
	@Autowired
	private ContLedgerManager contLedgerManager;
	
	@Autowired
	private ScContractMarkHisManager scContactMarkHisManager;

	private List<AppAttachFile> appAttachFileList = new ArrayList<AppAttachFile>();

	private List<ExecPlanLayout> execPlanLayouts; // 全部的业态

	private ScContractTempletInfo entity;
	// 合同实体用于合同创建、修改前台数剧初始化
	private ScContractTempletInfo conTempletInfoEntity = new ScContractTempletInfo();

	private ScContractTempletFill scContractTempletFill;

	private static final long serialVersionUID = 1L;

	private List<ResApproveInfo> resApproveInfoList = new ArrayList<ResApproveInfo>();
	private List<ScContractPrint> scContractPrintList = new ArrayList<ScContractPrint>();
	private List<ScContractPrint> scContractApproveHisList = new ArrayList<ScContractPrint>();

	private List<ScContractInfoSay> messages;
	private List<ScContractInfoAttach> conAttachOutputList = new ArrayList<ScContractInfoAttach>();
	private String fileName;

	private String realFileName;

	private String bizModuleCd;
	
	private String contLedgerId;
	
	//是否不导入合同台账
	String ifNoContLeger;

	/**
	 * 上传模板附件列表
	 */
	private File[] upload;
	/*
	 * 附件
	 */
	private File[] attachment;
	// 附件类型
	private String[] attachmentContentType;

	/**
	 * 附件名称
	 */
	private String[] uploadFileName;

	// 分页搜索
	@SuppressWarnings("unchecked")
	private Page voPage = new Page(10);
	private List<VoScContractTempletInfo> voContractTempletInfoList;

	/**
	 * 合同前台显示 参数值
	 */
	private ScContractParams scContractParams = new ScContractParams();

	/**
	 * 合同打印信息
	 */
	private ScContractPrintInfo conPrintInfo;

	// 流程状态
	private String statusCd;
	// 我的记录
	private Map<String, String> recoOpinions = new LinkedHashMap<String, String>();
	private String selectRecoOpinion;
	private String conTempletTypeId;
	// 历史最新合同模板ID
	private String maxHisContId;

	// 当前用户
	private String curUser = SpringSecurityUtils.getCurrentUiid();
	// 个人查看角色
	private final String isPerLook = "A_CONT_PER_LOOK";
	// 部门查看
	private final String isDeptLook = "A_CONT_DEPT_LOOK";
	// 中心（项目中心）查看角色
	private final String isCenterLook = "A_CONT_CEN_LOOK";
	// 合同查看角色(全部)
	private final String isLook = "A_CONT_LOOK";
	// 合同审核角色
	private final String isApprove = "A_CONT_APPROVE";

	// 合同附件类型
	private Map<String, String> attachTypeMaps = new HashMap<String, String>();

	// 附件操作实体类
	public ScContractAttachUpload conUpload = new ScContractAttachUpload();

	/**
	 * 非合当前合同引用的附件
	 */
	public ScContractInfoAttach conAttachmentEntity = new ScContractInfoAttach();


	public static final String DINGBIAO_CD_987 = "987";			// 定标审批表
	public static final String HETONGTIAOKUAN_CD_988 = "988";		// 合同条款审批表
	
	public static final String DINGBIAO = "定标审批表";
	public static final String HETONGTIAOKUAN = "合同条款审批表";
		
	/**
	 * @return the conAttachmentEntity
	 */
	public ScContractInfoAttach getConAttachmentEntity() {
		return conAttachmentEntity;
	}

	/**
	 * @param conAttachmentEntity
	 *            the conAttachmentEntity to set
	 */
	public void setConAttachmentEntity(ScContractInfoAttach conAttachmentEntity) {
		this.conAttachmentEntity = conAttachmentEntity;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the realFileName
	 */
	public String getRealFileName() {
		return realFileName;
	}

	/**
	 * @param realFileName
	 *            the realFileName to set
	 */
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	/**
	 * @return the bizModuleCd
	 */
	public String getBizModuleCd() {
		return bizModuleCd;
	}

	/**
	 * @param bizModuleCd
	 *            the bizModuleCd to set
	 */
	public void setBizModuleCd(String bizModuleCd) {
		this.bizModuleCd = bizModuleCd;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd
	 *            the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the conAttachOutputList
	 */
	public List<ScContractInfoAttach> getConAttachOutputList() {
		return conAttachOutputList;
	}

	/**
	 * @param conAttachOutputList
	 *            the conAttachOutputList to set
	 */
	public void setConAttachOutputList(List<ScContractInfoAttach> conAttachOutputList) {
		this.conAttachOutputList = conAttachOutputList;
	}

	/**
	 * @return the recoOpinions
	 */
	public Map<String, String> getRecoOpinions() {
		return recoOpinions;
	}

	/**
	 * @param recoOpinions
	 *            the recoOpinions to set
	 */
	public void setRecoOpinions(Map<String, String> recoOpinions) {
		this.recoOpinions = recoOpinions;
	}

	/**
	 * @return the messages
	 */
	public List<ScContractInfoSay> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<ScContractInfoSay> messages) {
		this.messages = messages;
	}

	/**
	 * @return the resApproveInfoList
	 */
	public List<ResApproveInfo> getResApproveInfoList() {
		return resApproveInfoList;
	}

	/**
	 * @param resApproveInfoList
	 *            the resApproveInfoList to set
	 */
	public void setResApproveInfoList(List<ResApproveInfo> resApproveInfoList) {
		this.resApproveInfoList = resApproveInfoList;
	}

	/**
	 * @return the selectRecoOpinion
	 */
	public String getSelectRecoOpinion() {
		return selectRecoOpinion;
	}

	/**
	 * @param selectRecoOpinion
	 *            the selectRecoOpinion to set
	 */
	public void setSelectRecoOpinion(String selectRecoOpinion) {
		this.selectRecoOpinion = selectRecoOpinion;
	}

	/**
	 * @return the scContractParams
	 */
	public ScContractParams getScContractParams() {
		return scContractParams;
	}

	/**
	 * @param scContractParams
	 *            the scContractParams to set
	 */
	public void setScContractParams(ScContractParams scContractParams) {
		this.scContractParams = scContractParams;
	}

	/**
	 * @return the scContractTempletFill
	 */
	public ScContractTempletFill getScContractTempletFill() {
		return scContractTempletFill;
	}

	/**
	 * @param scContractTempletFill
	 *            the scContractTempletFill to set
	 */
	public void setScContractTempletFill(ScContractTempletFill scContractTempletFill) {
		this.scContractTempletFill = scContractTempletFill;
	}

	/**
	 * @return the maxHisContId
	 */
	public String getMaxHisContId() {
		return maxHisContId;
	}

	/**
	 * @return the conPrintInfo
	 */
	public ScContractPrintInfo getConPrintInfo() {
		return conPrintInfo;
	}

	/**
	 * @param conPrintInfo
	 *            the conPrintInfo to set
	 */
	public void setConPrintInfo(ScContractPrintInfo conPrintInfo) {
		this.conPrintInfo = conPrintInfo;
	}

	/**
	 * @return the uploadFileName
	 */
	public String[] getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the upload
	 */
	public File[] getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	/**
	 * @param maxHisContId
	 *            the maxHisContId to set
	 */
	public void setMaxHisContId(String maxHisContId) {
		this.maxHisContId = maxHisContId;
	}

	/**
	 * @return the conTempletInfoEntity
	 */
	public ScContractTempletInfo getConTempletInfoEntity() {
		return conTempletInfoEntity;
	}

	/**
	 * @param conTempletInfoEntity
	 *            the conTempletInfoEntity to set
	 */
	public void setConTempletInfoEntity(ScContractTempletInfo conTempletInfoEntity) {
		this.conTempletInfoEntity = conTempletInfoEntity;
	}

	/**
	 * @return the voPage
	 */
	@SuppressWarnings("unchecked")
	public Page getVoPage() {
		return voPage;
	}

	/**
	 * @param voPage
	 *            the voPage to set
	 */
	@SuppressWarnings("unchecked")
	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}
	
	// 当前用户CD
	public String getCurrentUiid() {
		return SpringSecurityUtils.getCurrentUiid();
	}

	/**
	 * 合同选择，提取数据
	 */
	public String conSelect() {
		String frameId = Struts2Utils.getParameter("frameId");
		String sn = Struts2Utils.getParameter("sn");
		scContractParams.setFrameId(frameId);
		scContractParams.setSn(sn);
		return "select";
	}

	/**
	 * 获取合同状态列表
	 * 
	 * @return
	 */
	public String constacdlist() {
		conTempletTypeId = Struts2Utils.getParameter("sctempletTypeId");
		if (StringUtils.isNotBlank(conTempletTypeId)) {
			conTempletTypeId = "'" + conTempletTypeId.replace(",", "','") + "'";
		}
		queryMyReco();

		return "statusCdList";

	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String scContId = getId();
			ScContractTempletInfo scontractTempletInfo = scContractTempletInfoManager.getEntity(scContId);

			// 物理删除
			if ("0".equals("true")) {
				// 删除打印信息
				scContractPrintManager.delete(scontractTempletInfo.getScContractPrints());
				// 删除留言
				scContractInfoSayManager.delete(scontractTempletInfo.getScContractInfoSaies());
				// 删除附件
				scContractInfoAttachManager.delete(scontractTempletInfo.getScContractInfoAttachs());
				if (scontractTempletInfo.getScContractTempletHises() != null) {

					for (int i = 0; i < scontractTempletInfo.getScContractTempletHises().size(); i++) {

						ScContractTempletHis scContractTempletHis = scontractTempletInfo.getScContractTempletHises().get(i);
						if (scContractTempletHis != null && scContractTempletHis.getScContractMarks() != null) {
							// 删除所有批注信息
							scContractTepletMarkManager.delete(scContractTempletHis.getScContractMarks());
						}

					}
					// 删除所有历史版本

					scContractTempletHisManager.delete(scontractTempletInfo.getScContractTempletHises());
				}
				// 删除填空
				scContractTempletFillManager.delete(scontractTempletInfo.getScContractTempletFills());
				// 删除责任人
				contractTempletUserManager.delete(scontractTempletInfo.getScContractTempletUser());
				// 删除流程
				scContractInfoFlowManager.delete(scontractTempletInfo.getScContractInfoFlow());
				/**
				 * 删除合同
				 */
				scContractTempletInfoManager.delete(scontractTempletInfo);
			} else {
				// 逻辑删除
				scontractTempletInfo.setIsDel(Byte.valueOf("1"));

				scContractTempletInfoManager.saveScContractTempletInfo(scontractTempletInfo);

			}
			strBuffer.append("true}");

		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}

		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 合同文本库入口
	 */
	@Override
	public String execute() throws Exception {
		String frameId = Struts2Utils.getParameter("frameId");
		String sn = Struts2Utils.getParameter("sn");
		scContractParams.setFrameId(frameId);
		scContractParams.setSn(sn);
		
		conTempletInfoEntity = new ScContractTempletInfo();
		// 网批编号是否为空
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("resNo"))) {
			// 从定标审批表中导入合同库参数处理
			// 网批编号
			scContractParams.setResNo(Struts2Utils.getParameter("resNo"));
			// 网批中引用字段
			if (StringUtils.isNotBlank(Struts2Utils.getParameter("resFields"))) {
				Cookie[] cookies = Struts2Utils.getRequest().getCookies();// 获取一个cookie数组
				for (Cookie cookie : cookies) {
					if ("autoFileFields".equals(cookie.getName())) {
						try {
							scContractParams.setResFields(URLDecoder.decode(cookie.getValue(), "UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					if ("createContLedFiels".equals(cookie.getName())) {

						try {
							scContractParams.setResContLedgerParams(URLDecoder.decode(cookie.getValue(), "UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
			}
			// 网批中的附件列表
			if (StringUtils.isNotBlank(Struts2Utils.getParameter("resRela"))) {
				scContractParams.setResRela(Struts2Utils.getParameter("resRela"));
			}

		}
		// 默认执行的方法
		return SUCCESS;
	}

	/**
	 * 模板类别
	 * 
	 * @return
	 */
	public Map<String, String> getMapmoduleTypeCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SC_ATTACHMENT_TYPE);
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = scContractTempletInfoManager.getEntity(getId());

		} else {

			entity = new ScContractTempletInfo();
		}

	}

	/**
	 * 判断合同编是否已存在
	 */
	public void isExsitConNo() {

		String contractNo = Struts2Utils.getParameter("contractNo");
		String actType = Struts2Utils.getParameter("actType");
		String isYear = Struts2Utils.getParameter("isYear");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer strBuffer = new StringBuffer("{status:");

		if (StringUtils.isNotBlank(isYear) && isYear.equals("1")) {
			SimpleDateFormat dateformat1 = new SimpleDateFormat("yy");
			contractNo += dateformat1.format(new Date());

		}
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("");
		sqlBuf.append(" from ScContractTempletInfo t ").append(" where (isDel is null or isDel!=1) ");
		// 是否需要重构新的合同编号
		if (actType != null && actType.equals("buildNewConNo")) {
			sqlBuf.append(" and t.contractNo like :contractNo");
			values.put("contractNo", "" + contractNo + "%");
		} else {
			sqlBuf.append(" and t.contractNo=:contractNo");
			values.put("contractNo", contractNo);
		}
		sqlBuf.append(" order by createdDate desc,contractNo  desc");
		try {
			List<ScContractTempletInfo> conTractTempletInfoList = scContractTempletInfoManager.find(sqlBuf.toString(), values);
			int count = conTractTempletInfoList.size();
			if (actType != null && actType.equals("buildNewConNo")) {
				// 需要重构合同编号
				if (count > 0) {
					String conNo = "";
					String nums = "0";
					ScContractTempletInfo contractTempletInfo = conTractTempletInfoList.get(0);
					for (int i = 0; i < count; i++) {
						contractTempletInfo = conTractTempletInfoList.get(i);
						conNo = contractTempletInfo.getContractNo();
						// 获取后台后的序号
						nums = conNo.toLowerCase().replace(contractNo.toLowerCase(), "");
						Pattern pattern = Pattern.compile("[0-9]*");
						if (pattern.matcher(nums).matches()) {
							break;
						}
						nums = "0";

					}

					int buildConts = Integer.valueOf(nums);
					buildConts++;
					for (int i = ("" + buildConts).length(); i < 3; i++) {
						contractNo += "0";
					}
					// 生成新合同编号
					contractNo += buildConts;
				} else {
					// 第一份合同
					contractNo += "001";

				}

				strBuffer.append("true,newContNo:\"" + contractNo + "\"}");
			} else {
				strBuffer.append("true,count:\"" + count + "\"}");
			}

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}

		Struts2Utils.renderText(strBuffer.toString());
	}

	/**
	 * 获取记录中最大记录号
	 */
	@SuppressWarnings("unchecked")
	public Long getMaxRecord(String contId) {

		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hqlBuf = new StringBuffer();

		hqlBuf.append("select ");
		hqlBuf.append(" to_char(max(t.recordVersion))");
		Long maxRecord = Long.valueOf("0");
		hqlBuf.append(" from ScContractTempletHis t ");
		hqlBuf.append(" where t.scContractTempletInfo.contractTempletInfoId=:contId");
		values.put("contId", contId);
		List maxSequenceList = scContractTempletHisManager.find(hqlBuf.toString(), values);
		String value = maxSequenceList.toString();
		if (maxSequenceList != null && StringUtils.isNotBlank(value) && value.indexOf("null") < 0 && maxSequenceList.size() > 0) {

			maxRecord = Long.valueOf((String) maxSequenceList.get(0));
		}
		maxRecord++;
		return maxRecord;
	}

	/**
	 * 获取记录中最大历史ID
	 */
	@SuppressWarnings("unchecked")
	public String getMaxFieldValue(String contId, String fieldName) {
		Long maxRecord = getMaxRecord(contId);
		maxRecord = maxRecord - 1;
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hqlBuf = new StringBuffer();

		hqlBuf.append("select ");
		hqlBuf.append(" to_char(t." + fieldName + ")");

		hqlBuf.append(" from ScContractTempletHis t ");
		hqlBuf.append(" where t.scContractTempletInfo.contractTempletInfoId=:contId  and rownum=1");

		hqlBuf.append(" and t.recordVersion=:maxRecord");
		values.put("contId", contId);
		values.put("maxRecord", maxRecord);
		List maxSequenceList = scContractTempletHisManager.find(hqlBuf.toString(), values);
		String value = maxSequenceList.toString();
		if (StringUtils.isBlank(value)) {

			value = "";
		} else {
			value = value.replace("[", "").replace("]", "");
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer strBuffer = new StringBuffer("{status:");
		String jsonStr = Struts2Utils.getParameter("fillJson");
		// 合同模板ID
		String templetId = Struts2Utils.getParameter("conTemletId");
		// 合同ID
		String scContractId = Struts2Utils.getParameter("scContractId");
		// 合同ID2(用于判断当前合同是否是新增状态，上传附件以后会执行save方法，会生成合同ID用于关联合同，赋值给scContractId)
		String scContractId2 = Struts2Utils.getParameter("scContractId2");
		// 历史合同ID
		String hisContId = Struts2Utils.getParameter("hisContId");
		// 合同名称
		String ScContractName = Struts2Utils.getParameter("contractName");
		// 合同状态
		String conStatusCd = Struts2Utils.getParameter("conStatusCd");
		// 记录版本
		String recordVersion = Struts2Utils.getParameter("recordVersion");
		String fillRecordVersion = Struts2Utils.getParameter("fillRecordVersion");
		// 合同编号
		String contractNo = Struts2Utils.getParameter("contractNo");
		// 合同责任人
		String responsiblePersons = Struts2Utils.getParameter("curUserName");
		// 合同责任人CD
		String responsiblePersonsCd = Struts2Utils.getParameter("curUserCd");
		// 项目CD
		String projectCd = Struts2Utils.getParameter("projectCd");
		// 项目名称
		String projectName = Struts2Utils.getParameter("projectName");
		String contentHtml = Struts2Utils.getParameter("content");
		// 招标合同编号
		String inviteNo = Struts2Utils.getParameter("inviteNo");		
		// 合同总价
		String contractPrice = Struts2Utils.getParameter("contractPrice");
		// 旧网批编号
		String approveNo = Struts2Utils.getParameter("approveNo");
		// 旧网批ID
		String approveId = Struts2Utils.getParameter("approveId");
		// 定标审批表网批编号
		String approveNo1 = Struts2Utils.getParameter("approveNo1");
		// 定标审批表网批ID
		String approveId1 = Struts2Utils.getParameter("approveId1");
		// 合同条款审批表网批编号
		String approveNo2 = Struts2Utils.getParameter("approveNo2");
		// 合同条款审批表网批ID
		String approveId2 = Struts2Utils.getParameter("approveId2");
		// 合同评审表网批编号
		String approveNo3 = Struts2Utils.getParameter("approveNo3");
		// 合同评审表网批ID
		String approveId3 = Struts2Utils.getParameter("approveId3");
		// 合同台账编号
		String contNo = Struts2Utils.getParameter("contNo");
		// 合同台账ID
		String contID = Struts2Utils.getParameter("contID");
		// 定标审批表中带入的网批ID
		String resId = Struts2Utils.getParameter("resNo");
		// 网批中的附件列表
		String resRela = Struts2Utils.getParameter("resRela");
		List<ScContractTempletUser> contractTempletUserList = null;
		// 历史合同模板路径
		String hisTempletpath = null;
		// 创建合同台账操作状态信息
		String ctLedgerMsg = "";

		try {
			entity = new ScContractTempletInfo();

			JSONArray array = JSONArray.fromObject(jsonStr);
			List<ScContractTempletFill> ScContractTempletFillList = new ArrayList<ScContractTempletFill>();
			if (StringUtils.isBlank(recordVersion)) {

				recordVersion = "0";
			}
			if (StringUtils.isBlank(fillRecordVersion)) {

				fillRecordVersion = "0";
			}

			if (StringUtils.isBlank(conStatusCd)) {

				conStatusCd = "10";
			}
			// 上传附件后会执行一次合同保存，合同ID(scContractId)会被赋值，所以用scContractId2来判断
			if (StringUtils.isNotBlank(scContractId)) {
				// 修改合同
				entity = scContractTempletInfoManager.getEntity(scContractId);
				entity.setContractName(ScContractName);
				entity.setInviteNo(inviteNo);
				entity.setContractNo(contractNo);
				entity.setStatusCd(conStatusCd);
				entity.setProjectCd(projectCd);
				entity.setProjectName(projectName);
				entity.setContractPrice(contractPrice);
				entity.setApproveNo(approveNo);
				entity.setApproveId(approveId);
				entity.setApproveNo1(approveNo1);
				entity.setApproveId1(approveId1);
				entity.setApproveNo2(approveNo2);
				entity.setApproveId2(approveId2);
				entity.setApproveNo3(approveNo3);
				entity.setApproveId3(approveId3);
				entity.setContNo(contNo);
				entity.setContID(contID);
				entity.setIsstandard(Byte.valueOf("1"));
				contractTempletUserList = entity.getScContractTempletUser();
				scContractTempletInfoManager.saveScContractTempletInfo(entity);

				scContractId = entity.getContractTempletInfoId();
				recordVersion = "" + entity.getRecordVersion();
				for (Iterator iter = array.iterator(); iter.hasNext();) {
					JSONObject jsonObject = (JSONObject) iter.next();
					ScContractTempletFillJson conTempletFilJson = (ScContractTempletFillJson) JSONObject.toBean(jsonObject, ScContractTempletFillJson.class);
					ScContractTempletFill conTempletFill = null;
					if (StringUtils.isNotBlank(conTempletFilJson.getConFillId())) {
						conTempletFill = scContractTempletFillManager.getEntity(conTempletFilJson.getConFillId());
					}
					if (conTempletFill == null) {
						conTempletFill = new ScContractTempletFill();
					}
					conTempletFill.setScContractTempletInfo(entity);
					conTempletFill.setContractFillContent(conTempletFilJson.getConFillContent());
					ScContractTempletFillList.add(conTempletFill);
				} // 插入数据
				Session session = scContractTempletFillManager.getDao().getSession();
				int count = ScContractTempletFillList.size();
				int submitTime = count % 100;// 获取插入记录个数
				for (int i = 0; i < count; i++) {
					session.saveOrUpdate(ScContractTempletFillList.get(i));
					if (i % 100 == 0) { // 每100条提交一次数据
						session.flush();
						session.clear();
						fillRecordVersion = "" + ScContractTempletFillList.get(i).getRecordVersion();
					} else if (submitTime > 0 && i == count - 1) {
						// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
						session.flush();
						session.clear();
						fillRecordVersion = "" + ScContractTempletFillList.get(i).getRecordVersion();
					}
				}

			} else {
				// 添加合同
				ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(templetId);
				// 保存模板路径
				hisTempletpath = scContractTemplet.getTempletPath();
				entity.setContractName(ScContractName);
				entity.setStatusCd(conStatusCd);
				entity.setContractNo(contractNo);
				entity.setInviteNo(inviteNo);
				entity.setScContractTemplet(scContractTemplet);
				entity.setProjectCd(projectCd);
				entity.setProjectName(projectName);
				entity.setIsstandard(Byte.valueOf("1"));
				entity.setContractPrice(contractPrice);
				entity.setApproveNo(approveNo);
				entity.setApproveId(approveId);
				entity.setApproveNo1(approveNo1);
				entity.setApproveId1(approveId1);
				entity.setApproveNo2(approveNo2);
				entity.setApproveId2(approveId2);
				entity.setApproveNo3(approveNo3);
				entity.setApproveId3(approveId3);
				entity.setContNo(contNo);
				entity.setContID(contID);
				scContractTempletInfoManager.saveScContractTempletInfo(entity);

				scContractId = entity.getContractTempletInfoId();
				for (Iterator iter = array.iterator(); iter.hasNext();) {
					JSONObject jsonObject = (JSONObject) iter.next();
					ScContractTempletFillJson conTempletFilJson = (ScContractTempletFillJson) JSONObject.toBean(jsonObject, ScContractTempletFillJson.class);
					ScContractTempletFill conTempletFill = new ScContractTempletFill();
					// conTempletFill.setContractTempletFillId(StringUtils.isNotBlank(conTempletFilJson.getConFillId())
					// ? conTempletFilJson.getConFillId() : null);
					conTempletFill.setContractFillContent(conTempletFilJson.getConFillContent());

					conTempletFill.setScContractTempletInfo(entity);

					conTempletFill.setCreatedDate(entity.getCreatedDate());
					conTempletFill.setCreatedDeptCd(entity.getCreatedDeptCd());
					conTempletFill.setCreatedPositionCd(entity.getCreatedPositionCd());
					conTempletFill.setCreator(entity.getCreator());
					conTempletFill.setCreatedCenterCd(entity.getCreatedCenterCd());
					ScContractTempletFillList.add(conTempletFill);

				} // 插入数据 J

				Session session = scContractTempletFillManager.getDao().getSession();
				int count = ScContractTempletFillList.size();
				int submitTime = count % 100;// 获取插入记录个数
				for (int i = 0; i < count; i++) {
					session.saveOrUpdate(ScContractTempletFillList.get(i));
					if (i % 100 == 0) { // 每100条提交一次数据
						session.flush();
						session.clear();
					} else if (submitTime > 0 && i == count - 1) {
						// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
						session.flush();
						session.clear();
					}
				}
			}

			// 只有在合同创建时可以修改责任人
			if (StringUtils.isNotBlank(conStatusCd) && ("10".equals(conStatusCd) || "30".equals(conStatusCd))) {

				Map<String, Object> values = new HashMap<String, Object>();

				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf.append("delete from ScContractTempletUser ");
				sqlBuf.append(" where scContractTempletInfo.contractTempletInfoId=:templetInfoId");
				values.put("templetInfoId", entity.getContractTempletInfoId());
				Query query = contractTempletUserManager.getDao().createQuery(sqlBuf.toString(), values);
				query.executeUpdate();
				// 责任人列表
				String[] respPersionNameList = responsiblePersons.split(";");
				// 责任人CD
				String[] respPersionList = responsiblePersonsCd.split(";");

				Session contractTempletUserSession = contractTempletUserManager.getDao().getSession();

				contractTempletUserList = new ArrayList<ScContractTempletUser>();

				for (int i = 0; i < respPersionNameList.length; i++) {
					ScContractTempletUser contracttempletUser = new ScContractTempletUser();

					contracttempletUser.setScContractTempletInfo(entity);

					contracttempletUser.setCreatedDate(entity.getCreatedDate());
					contracttempletUser.setCreatedDeptCd(entity.getCreatedDeptCd());
					contracttempletUser.setCreatedPositionCd(entity.getCreatedPositionCd());
					contracttempletUser.setCreator(entity.getCreator());
					contracttempletUser.setUserName(respPersionNameList[i]);
					contracttempletUser.setUserCd(i < respPersionList.length ? respPersionList[i] : "");
					contractTempletUserList.add(contracttempletUser);
				}

				for (int i = 0; i < contractTempletUserList.size(); i++) {

					contractTempletUserSession.saveOrUpdate(contractTempletUserList.get(i));

					if (i == contractTempletUserList.size() - 1) {
						// 添加责任
						contractTempletUserSession.flush();
						contractTempletUserSession.clear();
					}

				}
			}

			/**
			 * 从定标审批表中导入合同文本库 (如果想操作网批或待办事项可在此判断体内进行
			 */
			// 如果网批编号不为空且附件信息不为空
			if (StringUtils.isNotBlank(resId)) {
				/**
				 * 合同历史记录
				 */
				ScContractTempletHis scContractTempletHis = new ScContractTempletHis();
				if (StringUtils.isBlank(hisContId)) {
					// 创建历史记录
					scContractTempletHis.setScContractTempletInfo(entity);
					scContractTempletHis.setTempletPath(hisTempletpath);
					scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);
				} else {
					scContractTempletHis = scContractTempletHisManager.getEntity(hisContId);
				}
				JSONArray resAttsArray = JSONArray.fromObject(resRela.replace("'", "\""));
				Map<String, Object> params = new HashMap<String, Object>();
				int iCount = 0;
				for (Iterator iter = resAttsArray.iterator(); iter.hasNext();) {

					JSONObject jsonObject = (JSONObject) iter.next();
					VoScContractFromResAttach resAttach = (VoScContractFromResAttach) JSONObject.toBean(jsonObject, VoScContractFromResAttach.class);
					if (StringUtils.isNotBlank(resAttach.getAttachId())) {
						params.put(resAttach.getAttachId(), resAttach.getAttachName());

					}

				}

				StringBuffer hql = new StringBuffer();
				hql.append(" from AppAttachFile t where t.bizEntityId =:bizEntityId ");

				params.put("bizEntityId", resId);
				appAttachFileList = appAttachFileManager.find(hql.toString(), params);
				iCount = appAttachFileList.size();
				ScContractInfoAttach infoAttach;
				String attachTypeCd;
				AppAttachFile appAttachFile;
				for (int i = 0; i < iCount; i++) {
					appAttachFile = appAttachFileList.get(i);
					infoAttach = new ScContractInfoAttach();
					attachTypeCd = "" + params.get(appAttachFile.getAppAttachFileId());
					if(StringUtils.isBlank(attachTypeCd)){
						//如果是空，就取“其他”类
						attachTypeCd = "999";
					}
					infoAttach.setAttachName(appAttachFile.getFileName());
					infoAttach.setRealFileName(appAttachFile.getRealFileName());
					infoAttach.setAttachPath(appAttachFile.getFilePath());
					infoAttach.setAttachTypeCd(attachTypeCd);
					infoAttach.setCreator(appAttachFile.getCreator());
					infoAttach.setCreatedDate(appAttachFile.getCreatedDate());
					infoAttach.setCreatedCenterCd(appAttachFile.getCreatedCenterCd());
					infoAttach.setCreatedDeptCd(appAttachFile.getCreatedDeptCd());
					infoAttach.setCreatedPositionCd(appAttachFile.getCreatedPositionCd());
					infoAttach.setScContractTempletInfo(entity);
					infoAttach.setScContractTempletHis(scContractTempletHis);
					// 抓取网批中的附件
					scContractInfoAttachManager.saveScContractInfoAttach(infoAttach);
				}

				// 导入合同台账
				ctLedgerMsg = doImportContLedger(resId, contractNo, ScContractName, entity.getContractTempletInfoId(), scContractTempletHis
						.getContractTempletHisId(), contNo);
				if(StringUtils.isNotBlank(contLedgerId)){
					entity.setContID(contLedgerId);
					entity.setContNo(entity.getContractNo());
					scContractTempletInfoManager.saveScContractTempletInfo(entity);
				}
			}

			/**
			 * 产生新版本 start
			 */
			generateFile(entity.getContractTempletInfoId(), templetId, contentHtml);

			/**
			 * 产生新版本 end
			 */

			strBuffer.append("true,scConId:\"" + scContractId + "\"");
			strBuffer.append(",reversion:\"" + recordVersion + "\"");
			strBuffer.append(",resNo:\"" + resId + "\"");
			// 如果不为空，则说明执行导入合同台账操作
			if (StringUtils.isNotBlank(ctLedgerMsg)) {
				strBuffer.append(",ctLedgerMsg:\"" + ctLedgerMsg + "\"");

			}
			strBuffer.append(",fillreversion:\"" + fillRecordVersion + "\"}");
		} catch (Exception ee) {
			if (StringUtils.isNotBlank(conStatusCd) && StringUtils.isNotBlank(scContractId) && conStatusCd.equals("10")) {

				entity.setScContractTempletFills(null);
				entity.setScContractInfoAttachs(null);
				scContractTempletInfoManager.delete(entity);

			}
			strBuffer.append(String.format("false,errorMsg:\"出现异常：%s\"}", ee.getMessage().replace("\"", "”")));

		}

		Struts2Utils.renderText(strBuffer.toString());
		return null;

	}

	/**
	 * 生成新文件
	 */
	public void generateFile(String contId, String templetId, String contentHtml) {
		String filePath = "";
		ScContractTemplet schisContractTemplet = ScContractTempletManager.getEntity(templetId);
		if (schisContractTemplet != null) {
			Long maxHisVersion = getMaxRecord(contId);
			// 首先获取模板路径
			filePath = schisContractTemplet.getTempletPath();
			if (StringUtils.isNotBlank(filePath)) {
				String savPath = getSavePath("sctemplet", null, true);
				String newFileName = "his_" + conUpload.genFileName(filePath);
				ScContractTempletHis scContractTempletHis = null;
				scContractTempletHis = new ScContractTempletHis();
				scContractTempletHis.setRecordVersion(++maxHisVersion);
				scContractTempletHis.setScContractTempletInfo(entity);
				scContractTempletHis.setTempletPath(savPath + "\\" + newFileName);
				/**
				 * 合并批注信信生成新的版本
				 */

				scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);

				if (StringUtils.isNotBlank(contentHtml)) {// 保存合同历史模板内容
					File fout = new File(savPath, newFileName);
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(fout);
						fos.write(contentHtml.toString().getBytes("utf-8"));
						fos.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	@Override
	public ScContractTempletInfo getModel() {
		// TODO Auto-generated method stub

		return entity;
	}

	@Override
	public String list() throws Exception {

		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");

		// 高级搜索条件
		String sctempletTypeId = Struts2Utils.getParameter("sctempletTypeId");

		// String projectName = Struts2Utils.getParameter("projectName");
		String projectCd = Struts2Utils.getParameter("projectCd");
		String conName = Struts2Utils.getParameter("conName");
		String conNo = Struts2Utils.getParameter("conNo");
		// 根据创建人搜索
		String curUserCd = Struts2Utils.getParameter("curUserCd");

		String startDate = Struts2Utils.getParameter("startDate");
		String endDate = Struts2Utils.getParameter("endDate");
		// String approveNo = Struts2Utils.getParameter("approveNo");
		String conStatusCd = Struts2Utils.getParameter("conStatusCd");
		String divStatusCd = Struts2Utils.getParameter("divStatusCd");

		String isStandard = Struts2Utils.getParameter("isSearchByStanCon");
		// 如果isStandard为false则说明需要搜索所有合同
		if (StringUtils.isNotBlank(isStandard) && isStandard.equals("false")) {
			isStandard = "";
		}
		// QQ:设计类，YX:营销类
		String sn = Struts2Utils.getParameter("sn");
		// 合同类型下的所有类别
		StringBuffer templetTypes = new StringBuffer("");
		if (StringUtils.isNotBlank(sn) || StringUtils.isNotBlank(isStandard)) {
			Map<String, Object> param = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();

			hql.append("from ScContractTempletType t1");
			hql.append(" where 1=1 ");
			if (StringUtils.isNotBlank(sn)) {
				// 合同类型，QQ：设计类，YX：营销类
				hql.append(" and t1.sn=:sn");
				param.put("sn", sn);
			}
			if (StringUtils.isNotBlank(isStandard)) {
				hql.append(" and t1.isstandard=1 ");
			}
			hql.append(" order by t1.sequenceNo desc");
			List<ScContractTempletType> scContractTempletTypeList = scContractTempletTypeManager.find(hql.toString(), param);

			for (int i = 0; null != scContractTempletTypeList && i < scContractTempletTypeList.size(); i++) {
				if (i > 0) {
					templetTypes.append(",");
				}
				templetTypes.append("'" + scContractTempletTypeList.get(i).getContractTempletTypeId() + "'");

			}
		}

		// String curUser = SpringSecurityUtils.getCurrentUiid();
		scContractParams.setCurLoginUser(curUser);
		Map<String, Object> values = new HashMap<String, Object>();

		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select  t.contract_templet_info_id, ");

		sqlBuf.append(" t.contract_name,");

		sqlBuf.append(" t.contract_no,");
		sqlBuf.append(" t.contract_templet_id ,");

		sqlBuf.append(" t.remark,");
		sqlBuf.append(" t.status_cd,");
		sqlBuf.append(" t.creator, ");
		sqlBuf.append(" to_char(t.isstandard), ");
		sqlBuf.append(" to_char(t.project_cd), ");
		sqlBuf.append(" to_char(t.project_name), ");
		sqlBuf.append(" cl.cont_no,");
		sqlBuf.append(" cl.cont_ledger_id,");
		sqlBuf.append(" t.UPDATED_DATE,");
		sqlBuf.append(" t.contract_price");
		sqlBuf.append(" from sc_contract_templet_info t ");
		sqlBuf.append(" left join  cont_ledger cl on t.contract_templet_info_id =cl.contract_templet_info_id  ");
		sqlBuf.append(" where 1=1  and t.status_cd!='99' ");
		/**
		 * 1: 标识删除状态,
		 * 如果搜索状态值为1, 则搜索删除状态的记录
		 */
		if("1".equals(conStatusCd)) {
			sqlBuf.append(" and t.is_del = 1");
			scContractParams.setIsDel("1"); //1 ： 已删除状态
		} else {
			sqlBuf.append(" and (t.is_del is null or t.is_del=0)");
		}
		/**
		 * 项目名称
		 */

		if (StringUtils.isNotBlank(projectCd)) {
			sqlBuf.append(" and t.project_cd=:projectCd ");
			values.put("projectCd", projectCd);

		}
		/**
		 * 合同名称
		 */
		if (StringUtils.isNotBlank(conName)) {
			sqlBuf.append(" and lower(t.contract_name) like :conName");
			values.put("conName", "%" + conName.trim().replace("'", "‘").toLowerCase() + "%");

		}
		/**
		 * 合同编号
		 */
		if (StringUtils.isNotBlank(conNo)) {
			sqlBuf.append(" and lower(t.contract_no) like :conNo ");
			values.put("conNo", "%" + conNo.trim().replace("'", "‘").toLowerCase() + "%");

		}

		/**
		 * 创建时间开始
		 */
		if (StringUtils.isNotBlank(startDate)) {
			sqlBuf.append(" and t.created_date >=to_date(:startDate,'yyyy-mm-dd')  ");
			values.put("startDate", startDate.replace("'", "‘"));

		}
		/**
		 * 创建时间结束
		 */
		if (StringUtils.isNotBlank(endDate)) {
			sqlBuf.append(" and t.created_date <=to_date(:endDate,'yyyy-mm-dd') ");
			values.put("endDate", endDate.replace("'", "‘"));

		}

		if (StringUtils.isNotBlank(sctempletTypeId) || StringUtils.isNotBlank(templetTypes.toString())) {

			Map<String, Object> templetValues = new HashMap<String, Object>();
			StringBuffer hqlBuf = new StringBuffer();
			hqlBuf.append("from ScContractTemplet t");

			if (StringUtils.isNotBlank(sctempletTypeId)) {
				sctempletTypeId = "'" + sctempletTypeId.replace(",", "','") + "'";
				hqlBuf.append(" where t.scContractTempletType.contractTempletTypeId in(" + sctempletTypeId + ")  and t.isDel is null");
				templetValues.put("conTempletTypeId", sctempletTypeId);
			} else {// 如果模板类别为空且是按合同类型搜索 QQ：设计类合同，YX:营销类合同搜索则执行到此方法

				hqlBuf.append(" where t.scContractTempletType.contractTempletTypeId in (" + templetTypes.toString() + ") ");
				// templetValues.put("conTempletTypeId",templetTypes.toString());

			}
			// 是否只搜索标准合同
			if (StringUtils.isNotBlank(isStandard)) {
				hqlBuf.append(" and t.isstandard=1 ");
			}
			List<ScContractTemplet> scContarctTempletList = ScContractTempletManager.find(hqlBuf.toString(), templetValues);
			StringBuffer tmepletIds = new StringBuffer("");
			for (int i = 0; scContarctTempletList != null && i < scContarctTempletList.size(); i++) {
				ScContractTemplet scContarctTemple = scContarctTempletList.get(i);
				scContractParams.setScContractTemletId(scContarctTemple.getContractTempletId());

				if (i > 0) {
					tmepletIds.append(",");
				}
				tmepletIds.append("'" + scContractParams.getScContractTemletId() + "'");
			}
			// 如果当前模板类型下没有模板
			if (scContarctTempletList == null || scContarctTempletList.size() == 0) {

				tmepletIds.append("'null'");

			}
			if (StringUtils.isNotBlank(tmepletIds.toString())) {

				sqlBuf.append(" and t.contract_templet_id in(" + tmepletIds + ")");
				values.put("templetIds", tmepletIds);

				/**
				 * 设置当前模板类型中默认启用的模板
				 */
				hqlBuf = new StringBuffer();
				hqlBuf.append("from ScContractTemplet t");

				if (StringUtils.isNotBlank(sctempletTypeId)) {
					hqlBuf.append(" where t.scContractTempletType.contractTempletTypeId =:conTempletTypeId and t.isvalid=1 and t.isDel is null ");
					templetValues.put("conTempletTypeId", sctempletTypeId);
				}
				scContarctTempletList = ScContractTempletManager.find(hqlBuf.toString(), templetValues);
				if (scContarctTempletList != null && scContarctTempletList.size() > 0) {

					scContractParams.setScContractTemletId(scContarctTempletList.get(0).getContractTempletId());
				} else {
					// 当前模板类型中没有模板启用或没有模板
					scContractParams.setScContractTemletId("");
				}

			}

		}
		String lookSql = buildLookSql();

		/**
		 * 高级搜索时根据创建人
		 */
		boolean isAppro = validateRole(isApprove);
		if (StringUtils.isNotBlank(curUserCd)) {
			sqlBuf.append(" and t.creator =:curUserCd ");
			values.put("curUserCd", curUserCd);
		}
		// 如果有查看权限，没有审核权限(审核权限最大可以查看所有的所以没有条件限制)
		if (StringUtils.isNotBlank(curUser) && !isAppro) {
			if (StringUtils.isNotBlank(lookSql)) {

				sqlBuf.append(lookSql);

			} else // 如果没有审核权限则只能搜索自己创建或负责的合同
			if (!isAppro) {
				sqlBuf.append(" and (t.creator =:curUser or t.contract_templet_info_id in(");
				sqlBuf.append(" select sct.contract_templet_info_id from  sc_contract_templet_user sct where sct.user_cd=:curUser)) ");
			}
			values.put("curUser", curUser);
		}

		/**
		 * 合同查詢状态
		 */
		if (StringUtils.isNotBlank(conStatusCd) && "1".equals(conStatusCd) == false) { // 1: 标识已删除状态(系统状态)
			sqlBuf.append(" and t.status_cd in (" + conStatusCd + ")");
			// values.put("conStatusCd", Long.valueOf(conStatusCd));
		}
		/**
		 * 合同点击状态
		 */
		if (StringUtils.isNotBlank(divStatusCd)) {
			sqlBuf.append(" and t.status_cd in (" + divStatusCd + ")");
			// values.put("divStatusCd", Long.valueOf(divStatusCd));
		}

		if (StringUtils.isNotBlank(isStandard)) {
			sqlBuf.append(" and t.isstandard=1 ");
		}
		// 设置默认排序方式
		if (StringUtils.isBlank(sortField)) {
			sqlBuf.append(" order by t.created_date desc");
		} else {
			sqlBuf.append(" order by t." + sortField + " " + order);
		}

		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			voPage.setPageSize(Integer.valueOf(rows));
		}
		try {
			voPage = ScContractTempletManager.findPageSql(voPage, sqlBuf.toString(), values, new HashMap<String, Class>());
			List tmpList = voPage.getResult();
			voContractTempletInfoList = new ArrayList<VoScContractTempletInfo>();
			Object[] obj = null;
			VoScContractTempletInfo tmpVo = null;
			StringBuffer contInfoIds = new StringBuffer("");
			for (int i = 0; i < tmpList.size(); i++) {
				obj = (Object[]) tmpList.get(i);
				tmpVo = new VoScContractTempletInfo();

				try {
					if (contInfoIds.toString().indexOf((String) obj[0]) > -1) {

						continue;
					}
					contInfoIds.append("," + (String) obj[0] + ",");
					tmpVo.setContractTempletInfoId((String) obj[0]);
					tmpVo.setContractName((String) obj[1]);
					tmpVo.setContractNo((String) obj[2]);
					tmpVo.setContractTempletId((String) obj[3]);
					tmpVo.setRemark((String) obj[4]);
					tmpVo.setStatusCd((String) obj[5]);
					tmpVo.setCreator((String) obj[6]);
					tmpVo.setCreatorName(CodeNameUtil.getUserNameByCd((String) obj[6]));
					// 合同类型（1.标准。0.非标
					tmpVo.setIsstandard((String) obj[7]);
					tmpVo.setProjectCd((String) obj[8]);
					tmpVo.setProjectName((String) obj[9]);
					tmpVo.setConNo((String) obj[10]);
					tmpVo.setContLedgerid((String) obj[11]);
					if (obj[12] != null) {// 更新时间
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						tmpVo.setUpdatedDate(sdf.parse("" + obj[12]));
					}
					tmpVo.setContractPrice((String)obj[13]);
					try{
						String tp = tmpVo.getContractPrice();
						if(".00".equalsIgnoreCase(tp.substring(tp.length()-3,tp.length()))){
							tp = tp.substring(0,tp.length()-3);
						}
						tmpVo.setContractPrice(tp);
					}catch(Exception e){}
					tmpVo.setContractTempletHisId(getMaxFieldValue((String) obj[0], "contractTempletHisId"));
					ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity((String) obj[3]);

					tmpVo.setContractTempletName(scContractTemplet.getScContractTempletType().getTypeName());

				} catch (Exception ee) {
					ee.printStackTrace();
				}
				voContractTempletInfoList.add(tmpVo);
			}

			// 获取模板
			if (StringUtils.isNotBlank(sctempletTypeId)) {
				Map<String, Object> templetValues = new HashMap<String, Object>();
				sqlBuf = new StringBuffer();
				sqlBuf.append("from ScContractTemplet t");

				if (StringUtils.isNotBlank(sctempletTypeId)) {
					sqlBuf.append(" where t.scContractTempletType.contractTempletTypeId =:sctempletTypeId and t.isDel is null ").append(" and t.isvalid = 1");
					// 在这里只查看查叶子节点是否有合同模板
					sctempletTypeId = sctempletTypeId.replace("'", "");
					templetValues.put("sctempletTypeId", sctempletTypeId);
				}
				List<ScContractTemplet> scContarctTempletList = ScContractTempletManager.find(sqlBuf.toString(), templetValues);
				if (scContarctTempletList != null && scContarctTempletList.size() > 0) {
					scContractParams.setScContractTemletId("");
					scContractParams.setScNonContractTempletId("");
					for (int i = 0; i < scContarctTempletList.size(); i++) {
						ScContractTemplet scContractTemplet = scContarctTempletList.get(i);
						if (scContractTemplet.getIsstandard() == 1) {
							// 标准合同
							scContractParams.setScContractTemletId(scContractTemplet.getContractTempletId());
							scContractParams.setIsValid(String.valueOf(scContractTemplet.getIsvalid()));
							scContractParams.setIsStandard(String.valueOf(scContractTemplet.getIsstandard()));

						} else {
							// 非标合同
							scContractParams.setScNonContractTempletId(scContractTemplet.getContractTempletId());

						}

					}

				}
			}
		} catch (Exception ee) {

			ee.printStackTrace();
		}

		return "loadList";
	}

	/**
	 * @return the voContractTempletInfoList
	 */
	public List<VoScContractTempletInfo> getVoContractTempletInfoList() {
		return voContractTempletInfoList;
	}

	/**
	 * @param voContractTempletInfoList
	 *            the voContractTempletInfoList to set
	 */
	public void setVoContractTempletInfoList(List<VoScContractTempletInfo> voContractTempletInfoList) {
		this.voContractTempletInfoList = voContractTempletInfoList;
	}

	/**
	 * 我的记录
	 */
	public String queryMyReco() {

		Date start = new Date();
		page.setPageSize(30);
		StringBuffer sqlBuf = new StringBuffer("");
		Map<String, Object> param = new HashMap<String, Object>();

		Map<String, Object> values = new HashMap<String, Object>();

		// String projectName = Struts2Utils.getParameter("projectName");
		String projectCd = Struts2Utils.getParameter("projectCd");
		String conName = Struts2Utils.getParameter("conName");
		String conNo = Struts2Utils.getParameter("conNo");
		String curUserCd = Struts2Utils.getParameter("curUserCd");

		String startDate = Struts2Utils.getParameter("startDate");
		String endDate = Struts2Utils.getParameter("endDate");
		// String approveNo = Struts2Utils.getParameter("approveNo");
		String conStatusCd = Struts2Utils.getParameter("conStatusCd");

		String isStandard = Struts2Utils.getParameter("isSearchByStanCon");
		// 如果isStandard为false则说明需要搜索所有合同
		if (StringUtils.isNotBlank(isStandard) && isStandard.equals("false")) {
			isStandard = "";
		}
		param.put("isStandard", isStandard);
		// QQ:设计类，YX:营销类
		String sn = Struts2Utils.getParameter("sn");
		// 合同类型下的所有类别
		StringBuffer templetTypes = new StringBuffer("");
		if (StringUtils.isNotBlank(sn) || StringUtils.isNotBlank(isStandard)) {
			Map<String, Object> searchPara = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();

			hql.append("from ScContractTempletType t1");
			hql.append(" where 1=1 ");
			if (StringUtils.isNotBlank(sn)) {
				// 合同类型，QQ：设计类，YX：营销类
				hql.append(" and t1.sn=:sn");
				searchPara.put("sn", sn);
			}
			if (StringUtils.isNotBlank(isStandard)) {
				hql.append(" and t1.isstandard=1 ");
			}
			hql.append(" order by t1.sequenceNo desc");
			List<ScContractTempletType> scContractTempletTypeList = scContractTempletTypeManager.find(hql.toString(), searchPara);

			for (int i = 0; null != scContractTempletTypeList && i < scContractTempletTypeList.size(); i++) {
				if (i > 0) {
					templetTypes.append(",");
				}
				templetTypes.append("'" + scContractTempletTypeList.get(i).getContractTempletTypeId() + "'");

			}
		}
		// 合同类型，QQ：设计类，YX：营销类 下的模板类别
		param.put("templetTypes", templetTypes.toString());

		/**
		 * 项目名称
		 */

		if (StringUtils.isNotBlank(projectCd)) {
			sqlBuf.append(" and t.project_cd=:projectCd ");
			values.put("projectCd", projectCd);

		}
		/**
		 * 合同名称
		 */
		if (StringUtils.isNotBlank(conName)) {
			sqlBuf.append(" and lower(t.contract_name) like :conName");
			values.put("conName", "%" + conName.replace("'", "‘").toLowerCase() + "%");

		}
		/**
		 * 合同编号
		 */
		if (StringUtils.isNotBlank(conNo)) {
			sqlBuf.append(" and lower(t.contract_no) like :conNo ");
			values.put("conNo", "%" + conNo.replace("'", "‘").toLowerCase() + "%");

		}

		/**
		 * 创建时间开始
		 */
		if (StringUtils.isNotBlank(startDate)) {
			sqlBuf.append(" and t.created_date >=to_date(:startDate,'yyyy-mm-dd')  ");
			values.put("startDate", startDate.replace("'", "‘"));

		}
		/**
		 * 创建时间结束
		 */
		if (StringUtils.isNotBlank(endDate)) {
			sqlBuf.append(" and t.created_date <=to_date(:endDate,'yyyy-mm-dd') ");
			values.put("endDate", endDate.replace("'", "‘"));

		}

		/**
		 * 网批编号
		 */
		/*
		 * if (StringUtils.isNotBlank(approveNo)) {
		 * sqlBuf.append(" and lower(t.res_approve_info_id) like :approveNo");
		 * values.put("approveNo", "%" + approveNo.replace("'",
		 * "‘").toLowerCase() + "%");
		 * 
		 * }
		 */
		String lookSql = buildLookSql();

		/**
		 * 高级搜索时根据创建人
		 */
		boolean isAppro = validateRole(isApprove);
		if (StringUtils.isNotBlank(curUserCd)) {
			sqlBuf.append(" and t.creator =:curUserCd ");
			values.put("curUserCd", curUserCd);
		}
		// 如果有查看权限，没有审核权限(审核权限最大可以查看所有的所以没有条件限制)
		if (StringUtils.isNotBlank(curUser) && !isAppro) {
			if (StringUtils.isNotBlank(lookSql)) {

				sqlBuf.append(lookSql);

			} else // 如果没有审核权限则只能搜索自己创建或负责的合同
			if (!isAppro) {
				sqlBuf.append(" and (t.creator =:curUser or t.contract_templet_info_id in(");
				sqlBuf.append(" select sct.contract_templet_info_id from  sc_contract_templet_user sct where sct.user_cd=:curUser)) ");
			}
			values.put("curUser", curUser);
		}

		/*
		 * 创建人
		 */
		/*
		 * if (StringUtils.isNotBlank(curUserCd)) {
		 * sqlBuf.append(" and t.creator =:curUserCd "); values.put("curUserCd",
		 * curUserCd); } else if (StringUtils.isNotBlank(curUser)) { sqlBuf
		 * .append(
		 * " and (t.creator =:curUser or t.contract_templet_info_id in(select sct.contract_templet_info_id from  sc_contract_templet_user sct where sct.user_cd=:curUser)) "
		 * );
		 * 
		 * values.put("curUser", curUser); }
		 */
		/**
		 * 合同状态
		 */
		if (StringUtils.isNotBlank(conStatusCd)) {
			sqlBuf.append(" and t.status_cd in (" + conStatusCd + ")");
			// values.put("conStatusCd", Long.valueOf(conStatusCd));
		}
		for (Iterator<Entry<String, String>> iter = buildRecoOpinions().entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> e = iter.next();

			String hql = buildMyRecoHql(param, e.getKey());

			if (StringUtils.isNotBlank(sqlBuf.toString())) {
				param.putAll(values);
				hql += " " + sqlBuf.toString();
			}
			long count = scContractTempletInfoManager.countSqlResult(hql, param);
			if (count > 0) {
				recoOpinions.put(e.getKey(), e.getValue() + "(" + count + ")");
			}
		}

		if (StringUtils.isBlank(selectRecoOpinion) && recoOpinions.size() > 0) {
			selectRecoOpinion = recoOpinions.entrySet().iterator().next().getKey();
		}

		Date end = new Date();
		// logger.error("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "myReco";
	}

	/**
	 * 我的记录HQL
	 */
	private String buildMyRecoHql(Map<String, Object> pram, String statusCD) {
		StringBuilder hql = new StringBuilder("select t.* from sc_contract_templet_info t where 1=1  and t.is_del is null ");

		// 推送给我
		if (StringUtils.isNotBlank(statusCD)) {
			hql.append("  and t.status_cd in (" + statusCD + ")");
			// pram.put("statusCd", statusCD);
		}

		String templetTypes = (String) pram.get("templetTypes");
		String isStandard = (String) pram.get("isStandard");
		if (StringUtils.isNotBlank(conTempletTypeId) || StringUtils.isNotBlank(templetTypes)) {

			Map<String, Object> templetValues = new HashMap<String, Object>();
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("from ScContractTemplet t");
			if (StringUtils.isNotBlank(conTempletTypeId)) {
				sqlBuf.append(" where t.scContractTempletType.contractTempletTypeId in(" + conTempletTypeId + ")  and t.isDel is null");
				templetValues.put("conTempletTypeId", conTempletTypeId);
			} else {// 如果模板类别为空且是按合同类型搜索 QQ：设计类合同，YX:营销类合同搜索则执行到此方法

				sqlBuf.append(" where t.scContractTempletType.contractTempletTypeId in (" + templetTypes.toString() + ") ");
				// templetValues.put("conTempletTypeId",templetTypes.toString());

			}
			// 是否只搜索标准合同
			if (StringUtils.isNotBlank(isStandard)) {
				sqlBuf.append(" and t.isstandard=1 ");
			}
			List<ScContractTemplet> scContarctTempletList = ScContractTempletManager.find(sqlBuf.toString(), templetValues);
			StringBuffer tmepletIds = new StringBuffer("");
			for (int i = 0; scContarctTempletList != null && i < scContarctTempletList.size(); i++) {
				ScContractTemplet scContarctTemple = scContarctTempletList.get(i);
				scContractParams.setScContractTemletId(scContarctTemple.getContractTempletId());

				if (i > 0) {

					tmepletIds.append(",");
				}
				tmepletIds.append("'" + scContractParams.getScContractTemletId() + "'");
			}
			// 如果当前模板类型下没有模板
			if (scContarctTempletList == null || scContarctTempletList.size() == 0) {

				tmepletIds.append("'nottemplet'");

			}
			if (StringUtils.isNotBlank(tmepletIds.toString())) {

				hql.append(" and t.contract_templet_id in(" + tmepletIds + ")");
				pram.put("templetIds", tmepletIds);
			}

		}
		scContractParams.setScContractTemletId("");

		return hql.toString();
	}

	/**
	 * 构建当前用户查看合同SQL
	 * 
	 * @return
	 */
	private String buildLookSql() {
		StringBuffer sql = new StringBuffer("");
		// 获取当前中心（项目公司）CD
		String currentCenterCd = SpringSecurityUtils.getCurrentCenterCd();
		// 获取部门CD
		String currentDeptCd = SpringSecurityUtils.getCurrentDeptCd();
		// 拥有中心（项目公司）查看权限
		if (validateRole(isCenterLook)) {
			// 可以查看所有中心（项目人公司）中的合同
			sql.append(" and t.created_center_cd = '" + currentCenterCd + "'");
		} else if (validateRole(isDeptLook)) {
			// 可以查看本部门的合同
			sql.append(" and t.created_dept_cd = '" + currentDeptCd + "'");
		} else if (validateRole(isLook)) {
			// 可以查看本部门的合同
			sql.append(" and 1= 1");
		}
		// 判断是否有合同查看权限

		return sql.toString();
	}

	/**
	 * 获取项目缩写
	 * 
	 * @author qlb 3/27/2012
	 * @param projectcd
	 *            项目CD
	 * 
	 * @return 返回项目缩写 如杭州地产 HZDC
	 */
	public void getProjectShortName() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String projectCd = Struts2Utils.getParameter("projectCd");
			String shortName = CodeNameUtil.getDeptShortNameByCd(projectCd);
			if (StringUtils.isBlank(shortName)) {
				shortName = "";

			}
			strBuffer.append("true,shortName:\"" + shortName + "\"}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 获取项目所对应的业态
	 * 
	 * @author qlb 3/27/2012
	 * @param projectCd
	 *            项目CD
	 * @return 业态页
	 */
	public String getExecPlanLayout() {

		String orgCd = Struts2Utils.getParameter("projectCd");
		// 搜索出所对应的所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(orgCd, "1", true, false);

		return "ExecPlanLayoutList";
	}

	/**
	 * 选择合同模板后去创建合同
	 * 
	 * @return
	 */

	public String toNewCreateContract() {
		conTempletInfoEntity = new ScContractTempletInfo();
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("resNo"))) {
			// 网批编号
			scContractParams.setResNo(Struts2Utils.getParameter("resNo"));
			ResApproveInfo resApprove = resApproveInfoManager.getEntity(Struts2Utils.getParameter("resNo"));
			conTempletInfoEntity.setApproveNo1(resApprove.getDisplayNo().toString());
			conTempletInfoEntity.setApproveId1(resApprove.getResApproveInfoId());
			String resFields = "";
			Cookie[] cookies = Struts2Utils.getRequest().getCookies();// 获取一个cookie数组
			for (Cookie cookie : cookies) {
				if ("autoFileFields".equals(cookie.getName())) {
					try {
						resFields = URLDecoder.decode(cookie.getValue(), "UTF-8");
						scContractParams.setResFields(resFields);
						break;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}

			}

			if (StringUtils.isNotBlank(Struts2Utils.getParameter("resRela"))) {
				// 网批中的附件列表
				try {
					scContractParams.setResRela(new String(Struts2Utils.getParameter("resRela").getBytes("ISO-8859-1"), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		bizModuleCd = Struts2Utils.getParameter("bizmodulecd");
		if (StringUtils.isBlank(bizModuleCd)) {
			bizModuleCd = "sctemplet";
		}
		String id = Struts2Utils.getParameter("templetId");

		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(id);

		String scContractName = scContractTemplet.getTempletName();
		scContractParams.setScContractHtml("");
		if (scContractTemplet != null) {
			scContractParams.setScContractHtml(getFileContent(scContractTemplet.getTempletPath()));
		}
		String curUserName = SpringSecurityUtils.getCurrentUserName();
		String SN = "";
		ScContractTempletType contractTempletType = scContractTemplet.getScContractTempletType();
		scContractParams.setTempletTypeCd(contractTempletType.getModuleTypeCd());
		while (StringUtils.isBlank(SN)) {
			SN = contractTempletType.getSn();
			if (StringUtils.isBlank(SN) && StringUtils.isNotBlank(contractTempletType.getParentId()) && !contractTempletType.getParentId().equals("0")) {
				contractTempletType = scContractTempletTypeManager.getEntity(contractTempletType.getParentId());
			} else {
				break;
			}

		}
		// 设置合同三级编号
		scContractParams.setThirdSn(SN);

		scContractParams.setResponsiblePersons(curUserName + ";");
		scContractParams.setResponsiblePersonCds(curUser + ";");

		conTempletInfoEntity.setContractTempletInfoId("");
		conTempletInfoEntity.setScContractTemplet(scContractTemplet);
		/*conTempletInfoEntity.setContractName(scContractName);*/

		appAttachFileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(id, bizModuleCd);
		// tab Frame页面ID
		scContractParams.setFrameId(Struts2Utils.getParameter("frameId"));

		// 是创建人不仅可以查看还可以编辑
		scContractParams.setIsOnlySee("0");

		return "create";
	}

	/**
	 * 查看合同
	 * 
	 * @return
	 */
	public String readContract() {
		//得到当前登录人Cd
		String curLoginUserCd = SpringSecurityUtils.getCurrentUiid();

		String id = Struts2Utils.getParameter("scContId");
		String contractTempletHisId = Struts2Utils.getParameter("contractTempletHisId");
		
		//liwei3 对 从"合同审批单" 跳转过来的合同页面上  定位选中的批注
		String highMarkId =  Struts2Utils.getParameter("highMarkId");
		Struts2Utils.getRequest().setAttribute("highMarkId", highMarkId);
		//代码调整后没有用了   liwei3 判断从"合同审批单" 跳转过来的合同页面上的 "批注"按钮是否显示,
		//String displayMarkBtn= Struts2Utils.getParameter("displayMarkBtn");
		//Struts2Utils.getRequest().setAttribute("displayMarkBtn", displayMarkBtn);

		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		
		//得到合同关联的审批单编号,如果审批单编号为空,说合同状态为完成
		String resApproveInfoId = conTempletInfoEntity.getResApproveInfoId();

		// 取得的审批单编号不为空
		if (resApproveInfoId != null && resApproveInfoId.length() > 0) {
			// 得到审批单信息,ApproveLevel是审批单当前审批节点
			ResApproveInfo info = resApproveInfoManager.getEntity(resApproveInfoId);
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:    "+ info.getApproveLevel());

			//如果审批单当前审批节点为空,代表审批单流程已经走完
			if (info.getApproveLevel() != null && info.getApproveLevel() != 0) {
				//判断后续审批节点的审批人是否都是当前登录人用到的中间变量
				String isLastUser = "0";
				//得到审批单的所有审批节点
				List<ResApproveNode> nodeList = info.getResApproveNodes();
				//取所有审批节点进行判断
				for (int i = 0; i < nodeList.size(); i++) {
					ResApproveNode node = nodeList.get(i);
					//跳过已处理的审批节点
					if (node.getApproveLevel() < info.getApproveLevel()) {
						//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:skip    "+ node.getApproveLevel());
						continue;
					}
					//判断后续审批节点的审批人是否都是当前登录人
					if (curLoginUserCd.equals(node.getUserCd())) {
						//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:break   "+ node.getApproveLevel());
						isLastUser = "1";
						break;
					}
				}
				//如果后续审批节点的审批人都是当前登录人,设置isLastUser为1,表明是最后审批人
				if (isLastUser.equals("1")) {
					//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:isLastUser  ");
					Struts2Utils.getRequest().setAttribute("isLastUser", "1");
				}
			}
		}

		String filePath = "";
		List<ScContractTempletFill> scContractTempletFills = conTempletInfoEntity.getScContractTempletFills();
		List<ScContractTempletFill> scContractTempletFillList = new ArrayList<ScContractTempletFill>();

		// 如果审核人、创建人或责任是当前登陆人，则允许进合同进行编辑审核
		if (curLoginUserCd.equals(conTempletInfoEntity.getCreator()) || validateRole(isApprove)) {
			scContractParams.setIsOnlySee("0");
		}
		for (int i = 0; scContractTempletFills != null && i < scContractTempletFills.size(); i++) {
			ScContractTempletFill fill = new ScContractTempletFill();
			fill.setContractTempletFillId(scContractTempletFills.get(i).getContractTempletFillId());
			fill.setContractFillContent(scContractTempletFills.get(i).getContractFillContent());
			fill.setRecordVersion(scContractTempletFills.get(i).getRecordVersion());

			fill.setScContractTempletInfo(null);
			scContractTempletFillList.add(fill);
		}
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());

		List<ScContractTempletUser> scContractTempletUserList = conTempletInfoEntity.getScContractTempletUser();
		StringBuffer resUserBuf = new StringBuffer("");
		StringBuffer resUserCdBuf = new StringBuffer("");
		for (int i = 0; scContractTempletUserList != null && i < scContractTempletUserList.size(); i++) {

			resUserBuf.append(scContractTempletUserList.get(i).getUserName() + ";");
			resUserCdBuf.append(scContractTempletUserList.get(i).getUserCd() + ";");
			// 如果创建人或责任是当前登陆人，则允许进合同进行编辑
			if (curLoginUserCd.equals(scContractTempletUserList.get(i).getUserCd())) {
				scContractParams.setIsOnlySee("0");
			}
		}
		/**
		 * 把责任人放到参数更表中用于初始化前台页面数据
		 */
		scContractParams.setResponsiblePersonCds(resUserCdBuf.toString());
		scContractParams.setResponsiblePersons(resUserBuf.toString());
		String SN = "";
		ScContractTempletType contractTempletType = scContractTemplet.getScContractTempletType();
		while (StringUtils.isBlank(SN)) {
			SN = contractTempletType.getSn();
			if (StringUtils.isBlank(SN) && StringUtils.isNotBlank(contractTempletType.getParentId()) && !contractTempletType.getParentId().equals("0")) {
				contractTempletType = scContractTempletTypeManager.getEntity(contractTempletType.getParentId());
			} else {
				break;
			}

		}
		// 设置合同三级编号
		scContractParams.setThirdSn(SN);
		/**
		 * 获取合同中填写的数据
		 */
		scContractParams.setConFillJson(JSONArray.fromObject(scContractTempletFillList).toString());

		filePath = getContHisFilePath(contractTempletHisId, scContractTemplet);
		scContractParams.setScContractHtml(getFileContent(filePath));

		// 查找合同对应的合同台帐
		StringBuffer hql = new StringBuffer("from ContLedger t where t.contractTempletInfoId =:contId");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("contId", id);

		List<ContLedger> contLedgerList = contLedgerManager.find(hql.toString(), values);
		// 如果已经关联合同台账则提供合同台账查
		if (!contLedgerList.isEmpty() && contLedgerList.size() > 0) {
			scContractParams.setContLedgerId(contLedgerList.get(0).getContLedgerId());
			scContractParams.setContLedgerNo(contLedgerList.get(0).getContNo());
		}
		// 定标审批表带入的网批ID
		scContractParams.setResNo(Struts2Utils.getParameter("resNo"));
		
		// 1: 已删除状态，
		if(conTempletInfoEntity.getIsDel() != null && 1 == conTempletInfoEntity.getIsDel()) {
			scContractParams.setIsDel("1");
		}

		return "merge";
	}

	/**
	 * 获取上模板内容
	 * 
	 * @param filePath
	 *            模板文件路径
	 * @return 文件内容
	 */
	public String getFileContent(String filePath) {
		StringBuffer sbuffer = new StringBuffer("");
		String FileName = "";
		try {

			if (StringUtils.isNotBlank(filePath)) {
				FileCharsetDetector fileChar = new FileCharsetDetector();

				FileName = filePath.trim();
				// 获取上传文件内容编码
				String encodeing = fileChar.guestFileEncoding(FileName);

				FileInputStream fis = new FileInputStream(FileName);
				InputStreamReader isr = new InputStreamReader(fis, encodeing);
				Reader in = new BufferedReader(isr);
				int ch;
				while ((ch = in.read()) > -1) {
					sbuffer.append((char) ch);
				}
				in.close();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		String returnStr = sbuffer.toString();
		returnStr = returnStr.replaceAll("<strong>", "<B>").replaceAll("</strong>", "</B>");
		return returnStr;
	}

	/**
	 * 保存历史版本
	 */
	public void saveHistory() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		String id = Struts2Utils.getParameter("scContractId");
		String contentHtml = Struts2Utils.getParameter("content");
		String markId = Struts2Utils.getParameter("markId");
		String actType = "" + Struts2Utils.getParameter("actType");
		String hisConId = Struts2Utils.getParameter("hisConId");
		String markContent = Struts2Utils.getParameter("markContent");

		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());
		String filePath = "";
		if (scContractTemplet != null) {
			filePath = scContractTemplet.getTempletPath();
		}
		try {

			String savPath = getSavePath("sctemplet", null, true);
			String newFileName = "his_" + conUpload.genFileName(filePath);
			// 获取历史版本列表
			List<ScContractTempletHis> scContractTempletHisList = conTempletInfoEntity.getScContractTempletHises();

			Long maxHisVersion = Long.valueOf("-1");
			if (scContractTempletHisList != null && scContractTempletHisList.size() > 0) {
				for (int i = 0; i < scContractTempletHisList.size(); i++) {
					ScContractTempletHis scContractTempletHis = scContractTempletHisList.get(i);
					if (null != scContractTempletHis && maxHisVersion < scContractTempletHis.getRecordVersion()) {

						maxHisVersion = scContractTempletHis.getRecordVersion();
						filePath = scContractTempletHis.getTempletPath();
					}

				}
				if (newFileName.length() > 0) {
					newFileName = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.length());
				}

			}

			ScContractTempletHis scContractTempletHis = null;
			if (StringUtils.isNotBlank(hisConId)) {
				// 更新历史版本
				scContractTempletHis = scContractTempletHisManager.getEntity(hisConId);

			} else {
				scContractTempletHis = new ScContractTempletHis();
				scContractTempletHis.setScContractTempletInfo(conTempletInfoEntity);
				scContractTempletHis.setTempletPath(savPath + "\\" + newFileName);
				/**
				 * 保存历史版本
				 */

				scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);
			}

			hisConId = scContractTempletHis.getContractTempletHisId();
			ScContractMark scContractMark = null;
			if (StringUtils.isNotBlank(markId) && !actType.equals("add")) {
				scContractMark = scContractMarkManager.getEntity(markId);

			} else {

				scContractMark = new ScContractMark();
			}
			scContractMark.setContractMarkContent(new ClobImpl(markContent));
			scContractMark.setScContractTempletHis(scContractTempletHis);
			if (scContractMark != null) {
				scContractMarkManager.saveScContractMark(scContractMark);
			}

			if (actType.equals("add")) {
				// 为了保证历史模板内容与标注内容的一致性需要替换掉之前的ID号
				contentHtml = contentHtml.replace("id=\"" + markId + "\"", "id=\"" + scContractMark.getContractMarkId() + "\"");
			}
			markId = scContractMark.getContractMarkId();
			if (StringUtils.isNotBlank(contentHtml)) {
				File fout = new File(savPath, newFileName);
				FileOutputStream fos = new FileOutputStream(fout);

				fos.write(contentHtml.toString().getBytes("utf-8"));
				fos.close();
			}
			strBuffer.append("true");
			strBuffer.append(",hisConId:\"" + hisConId + "\"");
			strBuffer.append(",markId:\"" + markId + "\"");
			strBuffer.append("}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 刪除标记
	 */
	public void delHistoryMark() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		String id = Struts2Utils.getParameter("scContractId");
		String contentHtml = Struts2Utils.getParameter("content");
		String markId = Struts2Utils.getParameter("markId");
		String actType = "" + Struts2Utils.getParameter("actType");

		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		String filePath = "";

		try {

			String savPath = getSavePath("sctemplet", null, true);
			String newFileName = "his_";
			// 获取历史版本列表
			List<ScContractTempletHis> scContractTempletHisList = conTempletInfoEntity.getScContractTempletHises();

			Long maxHisVersion = Long.valueOf("-1");
			if (scContractTempletHisList != null && scContractTempletHisList.size() > 0) {
				for (int i = 0; i < scContractTempletHisList.size(); i++) {
					ScContractTempletHis scContractTempletHis = scContractTempletHisList.get(i);
					if (null != scContractTempletHis && maxHisVersion < scContractTempletHis.getRecordVersion()) {

						maxHisVersion = scContractTempletHis.getRecordVersion();
						filePath = scContractTempletHis.getTempletPath();
					}

				}
				if (newFileName.length() > 0) {
					newFileName = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.length());
				}

			}

			if (StringUtils.isNotBlank(markId) && actType.equals("del")) {
				if (StringUtils.isNotBlank(markId)) {
					if (scContractMarkManager.getEntity(markId) != null) {
						try {
							// 删除标注内容
							scContractMarkManager.deleteScContractMark(markId);
						} catch (Exception ees) {

						}
					}
				}

			}

			if (StringUtils.isNotBlank(contentHtml)) {// 保存合同历史模板内容
				File fout = new File(savPath, newFileName);
				FileOutputStream fos = new FileOutputStream(fout);
				fos.write(contentHtml.toString().getBytes("utf-8"));
				fos.close();
			}
			strBuffer.append("true");
			strBuffer.append("}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 归并版本
	 */
	public void mergeCon() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		String id = Struts2Utils.getParameter("scContractId");
		String contentHtml = Struts2Utils.getParameter("content");
		String actType = "" + Struts2Utils.getParameter("actType");
		String statusCD = Struts2Utils.getParameter("statusCD");
		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		String filePath = "";

		try {
			ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());
			List<ScContractTempletHis> scContractTempletHisList = conTempletInfoEntity.getScContractTempletHises();
			if (scContractTemplet != null) {
				// 首先获取模板路径
				filePath = scContractTemplet.getTempletPath();
			}
			Long maxHisVersion = Long.valueOf("-1");
			if (scContractTempletHisList != null && scContractTempletHisList.size() > 0) {
				for (int i = 0; i < scContractTempletHisList.size(); i++) {
					ScContractTempletHis scContractTempletHis = scContractTempletHisList.get(i);
					if (null != scContractTempletHis && maxHisVersion < scContractTempletHis.getRecordVersion()) {
						maxHisVersion = scContractTempletHis.getRecordVersion();
						// 如果有历史记录则获取最新历史记录模板
						filePath = scContractTempletHis.getTempletPath();
					}

				}

			}

			String savPath = getSavePath("sctemplet", null, true);
			String newFileName = "hismark_" + conUpload.genFileName(filePath);
			ScContractTempletHis scContractTempletHis = null;

			scContractTempletHis = new ScContractTempletHis();
			scContractTempletHis.setRecordVersion(++maxHisVersion);
			scContractTempletHis.setScContractTempletInfo(conTempletInfoEntity);
			scContractTempletHis.setTempletPath(savPath + "\\" + newFileName);
			/**
			 * 合并批注信信生成新的版本
			 */

			scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);

			if ((actType.equals("merge") || actType.equals("markOk")) || StringUtils.isNotBlank(statusCD)) {
				conTempletInfoEntity.setStatusCd(statusCD);
				// 设置合并状态
				scContractTempletInfoManager.saveScContractTempletInfo(conTempletInfoEntity);
			}

			if (StringUtils.isNotBlank(contentHtml)) {// 保存合同历史模板内容
				File fout = new File(savPath, newFileName);
				FileOutputStream fos = new FileOutputStream(fout);
				fos.write(contentHtml.toString().getBytes("utf-8"));
				fos.close();
			}
			// 如果是通过，则记录通过人信息
			if (statusCD.equals("40")) {

				ScContractPrint scContractPrint = new ScContractPrint();
				scContractPrint.setRecordTypeCd("40");
				scContractPrint.setScContractTempletInfo(conTempletInfoEntity);
				scContractPrintManager.saveScContractPrint(scContractPrint);
			}
			strBuffer.append("true");
			strBuffer.append(",scConId:\"" + id + "\"");
			strBuffer.append("}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 查看合同合并
	 * 
	 * @return
	 */
	public String mergeContract() {
		String curLoginUserCd = SpringSecurityUtils.getCurrentUiid();
		String id = Struts2Utils.getParameter("scContId");
		String contractTempletHisId = Struts2Utils.getParameter("contractTempletHisId");
		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		String filePath = "";

		// 如果审核人、创建人或责任是当前登陆人，则允许进合同进行编辑审核
		if (curLoginUserCd.equals(conTempletInfoEntity.getCreator()) || validateRole(isApprove)) {
			scContractParams.setIsOnlySee("0");
		}

		List<ScContractTempletFill> scContractTempletFills = conTempletInfoEntity.getScContractTempletFills();
		List<ScContractTempletFill> scContractTempletFillList = new ArrayList<ScContractTempletFill>();

		for (int i = 0; scContractTempletFills != null && i < scContractTempletFills.size(); i++) {
			ScContractTempletFill fill = new ScContractTempletFill();
			fill.setContractTempletFillId(scContractTempletFills.get(i).getContractTempletFillId());
			fill.setContractFillContent(scContractTempletFills.get(i).getContractFillContent());
			fill.setRecordVersion(scContractTempletFills.get(i).getRecordVersion());

			fill.setScContractTempletInfo(null);
			scContractTempletFillList.add(fill);
		}
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());

		List<ScContractTempletUser> scContractTempletUserList = conTempletInfoEntity.getScContractTempletUser();
		StringBuffer resUserBuf = new StringBuffer("");
		StringBuffer resUserCdBuf = new StringBuffer("");
		for (int i = 0; scContractTempletUserList != null && i < scContractTempletUserList.size(); i++) {

			resUserBuf.append(scContractTempletUserList.get(i).getUserName() + ";");
			resUserCdBuf.append(scContractTempletUserList.get(i).getUserCd() + ";");
			// 如果创建人或责任是当前登陆人，则允许进合同进行编辑
			if (curLoginUserCd.equals(scContractTempletUserList.get(i).getUserCd())) {
				scContractParams.setIsOnlySee("0");
			}
		}
		/**
		 * 把责任人放到参数更表中用于初始化前台页面数据
		 */
		scContractParams.setResponsiblePersonCds(resUserCdBuf.toString());
		scContractParams.setResponsiblePersons(resUserBuf.toString());
		/**
		 * 获取合同中填写的数据
		 */
		scContractParams.setConFillJson(JSONArray.fromObject(scContractTempletFillList).toString());

		filePath = getContHisFilePath(contractTempletHisId, scContractTemplet);

		scContractParams.setScContractHtml(getFileContent(filePath));

		// 查找合同对应的合同台帐
		StringBuffer hql = new StringBuffer("from ContLedger t where t.contractTempletInfoId =:contId");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("contId", id);

		List<ContLedger> contLedgerList = contLedgerManager.find(hql.toString(), values);
		// 如果已经关联合同台账则提供合同台账查
		if (!contLedgerList.isEmpty() && contLedgerList.size() > 0) {
			scContractParams.setContLedgerId(contLedgerList.get(0).getContLedgerId());
			scContractParams.setContLedgerNo(contLedgerList.get(0).getContNo());
		}

		// 1: 已删除状态，
		if(conTempletInfoEntity.getIsDel() != null && conTempletInfoEntity.getIsDel() == 1) {
			scContractParams.setIsDel("1");
		}
		
		return "merge";
	}

	/**
	 * 清除标记
	 */
	public void doClearBookmark() {

		StringBuffer strBuffer = new StringBuffer("{status:");
		String id = Struts2Utils.getParameter("scContractId");
		String contentHtml = Struts2Utils.getParameter("content");

		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());
		String filePath = "";
		if (scContractTemplet != null) {
			filePath = scContractTemplet.getTempletPath();
		}
		try {

			String savPath = getSavePath("sctemplet", null, true);
			String newFileName = "boomark_" + id + conUpload.getSuffix(filePath);

			if (StringUtils.isNotBlank(contentHtml)) {// 保存清除标记后的合同内容
				File fout = new File(savPath, newFileName);
				FileOutputStream fos = new FileOutputStream(fout);
				fos.write(contentHtml.toString().getBytes("utf-8"));
				fos.close();
			}
			strBuffer.append("true");
			strBuffer.append(",scConId:\"" + id + "\"");
			strBuffer.append("}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 标记
	 */
	public void doBookmark() {

		StringBuffer strBuffer = new StringBuffer("{status:");
		String id = Struts2Utils.getParameter("scContractId");
		String contentHtml = Struts2Utils.getParameter("content");

		String contractTempletHisId = Struts2Utils.getParameter("hisConId");

		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());
		String filePath = "";
		if (scContractTemplet != null) {
			filePath = scContractTemplet.getTempletPath();
		}
		try {

			/*
			 * String newFileName = "boomark_" + id + getSuffix(filePath);
			 * 
			 * if (StringUtils.isNotBlank(contentHtml)) {// 保存合同历史模板内容 File fout
			 * = new File(savPath, newFileName); FileOutputStream fos = new
			 * FileOutputStream(fout);
			 * fos.write(contentHtml.toString().getBytes("utf-8")); fos.close();
			 * }
			 */
			ScContractTempletHis scContractTempletHis = scContractTempletHisManager.getEntity(contractTempletHisId);
			if (scContractTempletHis != null && scContractTempletHis.getTempletPath() != null) {
				maxHisContId = scContractTempletHis.getContractTempletHisId();
				filePath = scContractTempletHis.getTempletPath();
			}

			if (StringUtils.isNotBlank(contentHtml)) {// 保存合同历史模板内容
				File fout = new File(filePath);
				FileOutputStream fos = new FileOutputStream(fout);
				fos.write(contentHtml.toString().getBytes("utf-8"));
				fos.close();
			}
			strBuffer.append("true");
			strBuffer.append(",scConId:\"" + id + "\"");
			strBuffer.append("}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	public String print() {
		String id = Struts2Utils.getParameter("scContId");
		String contractTempletHisId = Struts2Utils.getParameter("contractTempletHisId");
		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		String conStatusCd = conTempletInfoEntity.getStatusCd();
		String filePath = "";
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());
		filePath = getContHisFilePath(contractTempletHisId, scContractTemplet);
		conPrintInfo = new ScContractPrintInfo();
		conPrintInfo.setContractId(conTempletInfoEntity.getContractTempletInfoId());
		conPrintInfo.setContractName(conTempletInfoEntity.getContractName());
		conPrintInfo.setContractNo(conTempletInfoEntity.getContractNo());
		conPrintInfo.setPrintUser(SpringSecurityUtils.getCurrentUiid());

		ScContractPrint scContractPrint = null;
		Long maxPrintVersion = Long.valueOf("-1");
		for (int i = 0; conTempletInfoEntity.getScContractPrints() != null && i < conTempletInfoEntity.getScContractPrints().size(); i++) {

			ScContractPrint contractPrint = conTempletInfoEntity.getScContractPrints().get(i);
			if (contractPrint != null && contractPrint.getCreator().equals(SpringSecurityUtils.getCurrentUiid())
					&& contractPrint.getRecordVersion() > maxPrintVersion) {

				maxPrintVersion = contractPrint.getRecordVersion();

			}
		}
		if (scContractPrint == null) {
			scContractPrint = new ScContractPrint();
		}
		if (maxPrintVersion < 0) {

			maxPrintVersion = Long.valueOf("1");
		} else {

			maxPrintVersion++;
		}
		/**
		 * 打印流水号
		 */
		String printSN = RandomSeriNoUtils.generateMixString(8);

		conPrintInfo.setRandPrintSN(printSN);
		scContractPrint.setContractPrintNo(conPrintInfo.getRandPrintSN());
		scContractPrint.setScContractTempletInfo(conTempletInfoEntity);
		scContractPrint.setRecordVersion(maxPrintVersion);
		if (conStatusCd.equals("70")) {
			// 合同完成后打印需记录打印人信息
			// 保存打印记录
			scContractPrintManager.saveScContractPrint(scContractPrint);
		} else {

			conPrintInfo.setRandPrintSN("未完成版");

		}
		/**
		 * 合同内容
		 */
		scContractParams.setScContractHtml(getFileContent(filePath));
		/**
		 * 当前用户
		 */

		conPrintInfo.setPrintRecordVersion(maxPrintVersion + "");

		conPrintInfo.setPrintDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		conPrintInfo.setCreator(SpringSecurityUtils.getCurrentUserName());
		conPrintInfo.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(conTempletInfoEntity.getCreatedDate()));

		return "print";
	}

	/**
	 * 导出WORD
	 * 
	 * @return
	 */
	public String exportDoc() {

		String id = Struts2Utils.getParameter("scContId");
		String contractTempletHisId = Struts2Utils.getParameter("contractTempletHisId");

		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		String filePath = "";
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());
		filePath = getContHisFilePath(contractTempletHisId, scContractTemplet);
		conPrintInfo = new ScContractPrintInfo();
		conPrintInfo.setContractId(conTempletInfoEntity.getContractTempletInfoId());
		conPrintInfo.setContractName(conTempletInfoEntity.getContractName());
		conPrintInfo.setContractNo(conTempletInfoEntity.getContractNo());
		conPrintInfo.setPrintUser(SpringSecurityUtils.getCurrentUiid());

		ScContractPrint scContractPrint = null;
		Long maxPrintVersion = Long.valueOf("-1");
		for (int i = 0; conTempletInfoEntity.getScContractPrints() != null && i < conTempletInfoEntity.getScContractPrints().size(); i++) {

			ScContractPrint contractPrint = conTempletInfoEntity.getScContractPrints().get(i);
			if (contractPrint != null && contractPrint.getCreator().equals(SpringSecurityUtils.getCurrentUiid())
					&& contractPrint.getRecordVersion() > maxPrintVersion) {

				maxPrintVersion = contractPrint.getRecordVersion();

			}
		}
		if (scContractPrint == null) {
			scContractPrint = new ScContractPrint();
		}
		if (maxPrintVersion < 0) {

			maxPrintVersion = Long.valueOf("1");
		} else {

			maxPrintVersion++;
		}

		/**
		 * 导出的文件名
		 */
		if (Struts2Utils.getSession() != null) {
			Struts2Utils.getSession().setAttribute("contName", conPrintInfo.getContractName());
		}
		/**
		 * 导出流水号
		 */
		String exportSN = RandomSeriNoUtils.generateMixString(8);
		conPrintInfo.setRandPrintSN(exportSN);
		scContractPrint.setContractPrintNo(conPrintInfo.getRandPrintSN());
		scContractPrint.setScContractTempletInfo(conTempletInfoEntity);
		scContractPrint.setRecordVersion(maxPrintVersion);
		// 保存打印记录
		scContractPrintManager.saveScContractPrint(scContractPrint);
		/**
		 * 合同内容
		 */
		String scContractHtml = getFileContent(filePath);
		scContractParams.setScContractHtml(scContractHtml.toLowerCase().replace("ins", "span"));
		/**
		 * 当前用户
		 */

		conPrintInfo.setPrintRecordVersion(maxPrintVersion + "");

		conPrintInfo.setPrintDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		conPrintInfo.setCreator(SpringSecurityUtils.getCurrentUserName());
		conPrintInfo.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(conTempletInfoEntity.getCreatedDate()));

		return "exportdoc";

	}

	// ----
	private String getSavePath(String bizModuleCd, String uiid, boolean isUpload) {
		return PowerUtils.getFilePath(bizModuleCd, uiid, isUpload);
	}

	/**
	 * 根据id更新填空数据
	 */
	public String updateTempletFill() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		String id = Struts2Utils.getParameter("id");
		String value = Struts2Utils.getParameter("value");
		// 是否修改合同总价
		String isAllPrice = Struts2Utils.getParameter("isAllPrice");
		if (StringUtils.isNotBlank(id)) {
			ScContractTempletFill scContractTempletFill = scContractTempletFillManager.getEntity(id);
			if (isAllPrice.equals("true")) {
				entity = scContractTempletFill.getScContractTempletInfo();
				entity.setContractPrice(value);
				scContractTempletInfoManager.saveScContractTempletInfo(entity);
			}
			if (scContractTempletFill != null) {
				scContractTempletFill.setContractFillContent(value);
				scContractTempletFillManager.saveScContractTempletFill(scContractTempletFill);
				this.scContractTempletFill = scContractTempletFill;
				strBuffer.append("更新成功");
			}
		}

		// Struts2Utils.renderText(strBuffer.toString());
		return "fill";
	}

	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		// 是否获取所有合同编号
		String isGetAll = Struts2Utils.getParameter("isGetAll");
		// 合同类别 sn :ZL-战略,QQ--设计类
		String sn = Struts2Utils.getParameter("sn");
		// 合同编号
		String contNo = Struts2Utils.getParameter("contNo");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, Object> param = new HashMap<String, Object>();

		// 合同类型下的所有类别
		StringBuffer templetTypes = new StringBuffer("");

		StringBuffer hql2 = new StringBuffer("from ScContractTempletInfo t where 1=1");
		// hql2.append(" and (statusCd='30' or statusCd = '40')");
		// 网批中不让引用

		hql2.append("	and (statusCd!='50') and ((isDel is not null and isDel != 1) or isDel is null) and ");
		hql2.append("	(lower(contractName) like :contractName ");
		hql2.append("	or lower(contractNo) like :contractName or");
		hql2.append("	lower(inviteNo) like :contractName)");
		/*
		 * if (StringUtils.isBlank(contNo)) { // 如果合同编号为空使用模糊搜索
		 * hql2.append("	(lower(contractName) like :contractName ");
		 * hql2.append("	or lower(contractNo) like :contractName or");
		 * hql2.append("	lower(inviteNo) like :contractName)"); } else {//
		 * 使用精确搜索 hql2.append("	(contractName =:contractName ");
		 * hql2.append("	or contractNo =:contractName or");
		 * hql2.append("	inviteNo =:contractName)"); }
		 */
		// 合同类型，QQ：设计类，YX：营销类
		if (StringUtils.isNotBlank(sn) && !sn.equals("undefined")) {
			Map<String, Object> searchParam = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();

			hql.append("from ScContractTempletType t1");
			hql.append(" where 1=1 ");
			// 合同类型，QQ：设计类，YX：营销类
			hql.append(" and  lower(t1.sn)=:sn");
			searchParam.put("sn", sn.toLowerCase());
			hql.append(" order by t1.sequenceNo desc");

			List<ScContractTempletType> scContractTempletTypeList = scContractTempletTypeManager.find(hql.toString(), searchParam);

			for (int i = 0; null != scContractTempletTypeList && i < scContractTempletTypeList.size(); i++) {
				if (i > 0) {
					templetTypes.append(",");
				}
				templetTypes.append("'" + scContractTempletTypeList.get(i).getContractTempletTypeId() + "'");

			}

			if (StringUtils.isNotBlank(templetTypes.toString())) {

				Map<String, Object> templetValues = new HashMap<String, Object>();
				StringBuffer hqlBuf = new StringBuffer();
				hqlBuf.append("from ScContractTemplet t");

				// 如果模板类别为空且是按合同类型搜索 QQ：设计类合同，YX:营销类合同搜索则执行到此方法

				hqlBuf.append(" where t.scContractTempletType.contractTempletTypeId in (" + templetTypes.toString() + ") ");

				List<ScContractTemplet> scContarctTempletList = ScContractTempletManager.find(hqlBuf.toString(), templetValues);
				StringBuffer tmepletIds = new StringBuffer("");
				for (int i = 0; scContarctTempletList != null && i < scContarctTempletList.size(); i++) {
					ScContractTemplet scContarctTemple = scContarctTempletList.get(i);
					scContractParams.setScContractTemletId(scContarctTemple.getContractTempletId());

					if (i > 0) {
						tmepletIds.append(",");
					}
					tmepletIds.append("'" + scContractParams.getScContractTemletId() + "'");
				}
				// 如果当前模板类型下没有模板
				if (scContarctTempletList == null || scContarctTempletList.size() == 0) {

					tmepletIds.append("'null'");

				}
				if (StringUtils.isNotBlank(tmepletIds.toString())) {

					hql2.append(" and t.scContractTemplet.contractTempletId in(" + tmepletIds + ")");
					param.put("templetIds", tmepletIds);

				}

			}

		}

		if (StringUtils.isNotBlank(isGetAll)) {
			hql2 = new StringBuffer("from ScContractTempletInfo where (lower(contractNo) like :contractName)");
		} else {
			// 判断是否有合同查看权限
			if (!validateRole(isLook) && !validateRole(isPerLook) && !validateRole(isDeptLook) && !validateRole(isCenterLook)) {
				// 没有权限直接返回空
				// Struts2Utils.renderJson(list);
				// return;
			}
		}
		if (StringUtils.isNotBlank(value)) {
			value = value.toLowerCase();
		}
		if (StringUtils.isBlank(contNo)) { // 如果合同编号为空使用模糊搜索
			param.put("contractName", "%" + value + "%");
		} else {
			// 否则使用合同后缀模糊搜索
			param.put("contractName", contNo.trim().toLowerCase() + "%");

		}

		hql2.append(" order by updatedDate desc ");
		page.setPageSize(30);
		page = scContractTempletInfoManager.findPage(page, hql2.toString(), param);

		for (ScContractTempletInfo scContractTempletInfo : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("contractName", StringUtils.trim(scContractTempletInfo.getContractName()));// 合同名称
			map.put("contractNo", StringUtils.trim(scContractTempletInfo.getContractNo()));// 合同编号
			map.put("contractTempletInfoId", scContractTempletInfo.getContractTempletInfoId()); // 主键
			map.put("projectCd", scContractTempletInfo.getProjectCd());// 项目代码
			map.put("projectName", scContractTempletInfo.getProjectName());// 项目名称
			map.put("isStandard", String.valueOf(scContractTempletInfo.getIsstandard()));
			// 获取最新合同模板
			ScContractTempletHis scContractTempletHis = new ScContractTempletHis();
			List<ScContractTempletHis> scContractTempletHisList = scContractTempletInfo.getScContractTempletHises();
			Long maxHisVersion = Long.valueOf("-1");
			if (scContractTempletHisList != null && scContractTempletHisList.size() > 0) {
				for (int i = 0; i < scContractTempletHisList.size(); i++) {
					ScContractTempletHis bean = scContractTempletHisList.get(i);
					if (null != bean && maxHisVersion < bean.getRecordVersion()) {
						maxHisVersion = scContractTempletHis.getRecordVersion();
						scContractTempletHis = bean;
					}

				}

			}
			map.put("contractTempletHisId", scContractTempletHis.getContractTempletHisId());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 校驗用戶是否拥有指定的角色
	 * 
	 * @param roleCd
	 *            角色CD
	 * @return true:有，false:无
	 */
	private static boolean validateRole(String roleCd) {

		AcgiUser user = (AcgiUser) SpringSecurityUtils.getCurrentUser();
		List<WsPlasRole> roles = user.getRoleList();
		WsPlasRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			role = roles.get(i);
			if (roleCd.equals(role.getRoleCd()))
				return true;
		}
		return false;
	}

	/**
	 * 我的记录中的节点
	 */
	public Map<String, String> buildRecoOpinions() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		StringBuffer statusCdIds = new StringBuffer("");

		statusCdIds.append(CON_APPROVE_STATUS_NEW);
		statusCdIds.append(COMMA);
		statusCdIds.append(CON_APPROVE_STATUS_SUBMIT);
		statusCdIds.append(COMMA);
		statusCdIds.append(CON_APPROVE_STATUS_MARK);
		statusCdIds.append(COMMA);
		statusCdIds.append(CON_APPROVE_STATUS_MERGE);
		statusCdIds.append(COMMA);
		statusCdIds.append(CON_APPROVE_STATUS_BACK);
		map.put(statusCdIds.toString(), "进行中");

		/*
		 * map.put(CON_APPROVE_STATUS_MERGE, "审核通过");
		 * map.put(CON_APPROVE_STATUS_NEW, "新增中");
		 * map.put(CON_APPROVE_STATUS_MARK, "修改中");
		 * map.put(CON_APPROVE_STATUS_SUBMIT, "审核中");
		 * map.put(CON_APPROVE_STATUS_BACK, "驳回");
		 * map.put(CON_APPROVE_STATUS_ING, "网批中");
		 * map.put(CON_APPROVE_STATUS_APP_FINISHL, "网批结束");
		 */
		map.put(CON_APPROVE_STATUS_ING, "网批中");
		map.put(CON_APPROVE_STATUS_APP_FINISHL, "网批结束");
		map.put(CON_APPROVE_STATUS_FINISHL, "可打印");
		map.put(CON_APPROVE_STATUS_EXTRA, "已签署");
		return map;
	}

	public String getFlow() throws Exception {
		String scContId = Struts2Utils.getParameter("scContId");
		List<ScContractInfoFlow> scContractInfoFlow = new ArrayList<ScContractInfoFlow>();
		if (StringUtils.isNotBlank(scContId)) {
			ScContractTempletInfo scContTempInfo = scContractTempletInfoManager.getEntity(scContId);
			if (scContTempInfo != null) {
				scContractInfoFlow = scContTempInfo.getScContractInfoFlow();
				for (ScContractInfoFlow bean : scContractInfoFlow) {
					ResApproveInfo resApproveInfo = resApproveInfoManager.getEntity(bean.getResApproveInfoId());
					if (resApproveInfo != null) {
						resApproveInfoList.add(resApproveInfo);
					}
				}
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("scContId", scContId);
			StringBuffer hql = new StringBuffer(
					"from ScContractPrint t where t.scContractTempletInfo.contractTempletInfoId=:scContId and t.recordTypeCd is null order by t.createdDate desc");
			scContractPrintList = scContractPrintManager.find(hql.toString(), param);// scContTempInfo.getScContractPrints();

			// 操作人记录
			hql = new StringBuffer(
					"from ScContractPrint t where t.scContractTempletInfo.contractTempletInfoId=:scContId and t.recordTypeCd is not null order by t.createdDate desc");
			scContractApproveHisList = scContractPrintManager.find(hql.toString(), param);// scContTempInfo.getScContractPrints();

		}

		return "flow";
	}

	/**
	 * 获取合同历史版本文件路径 如contractTempletHisId非空则直接返回该历史版本文件路径
	 * 
	 * @param contractTempletHisId
	 * @param scContractTemplet
	 * @return
	 */
	public String getContHisFilePath(String contractTempletHisId, ScContractTemplet scContractTemplet) {
		String filePath = null;
		Long maxHisVersion = Long.valueOf("-1");
		if (StringUtils.isNotBlank(contractTempletHisId)) {
			ScContractTempletHis scContractTempletHis = scContractTempletHisManager.getEntity(contractTempletHisId);
			if (scContractTempletHis != null && scContractTempletHis.getTempletPath() != null) {
				maxHisContId = scContractTempletHis.getContractTempletHisId();
				filePath = scContractTempletHis.getTempletPath();
			}
		} else {
			List<ScContractTempletHis> scContractTempletHisList = conTempletInfoEntity.getScContractTempletHises();
			if (scContractTempletHisList != null && scContractTempletHisList.size() > 0) {
				for (int i = 0; i < scContractTempletHisList.size(); i++) {
					ScContractTempletHis scContractTempletHis = scContractTempletHisList.get(i);
					if (null != scContractTempletHis && maxHisVersion < scContractTempletHis.getRecordVersion()) {
						maxHisContId = scContractTempletHis.getContractTempletHisId();
						maxHisVersion = scContractTempletHis.getRecordVersion();
						filePath = scContractTempletHis.getTempletPath();
					}

				}

			} else if (scContractTemplet != null) {
				filePath = scContractTemplet.getTempletPath();
			}
		}
		return filePath;
	}

	/**
	 * 网批查看合同 带有标记功能
	 * 
	 * @return
	 */
	public String markContract() {

		String curLoginUserCd = SpringSecurityUtils.getCurrentUiid();
		String id = Struts2Utils.getParameter("scContId");
		String contractTempletHisId = Struts2Utils.getParameter("contractTempletHisId");
		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		String filePath = "";
		// 流程状态
		statusCd = Struts2Utils.getParameter("statusCd");

		// 如果审核人、创建人或责任是当前登陆人，则允许进合同进行编辑审核
		if (curLoginUserCd.equals(conTempletInfoEntity.getCreator()) || validateRole(isApprove)) {
			scContractParams.setIsOnlySee("0");
		}
		List<ScContractTempletFill> scContractTempletFills = conTempletInfoEntity.getScContractTempletFills();
		List<ScContractTempletFill> scContractTempletFillList = new ArrayList<ScContractTempletFill>();

		for (int i = 0; scContractTempletFills != null && i < scContractTempletFills.size(); i++) {
			ScContractTempletFill fill = new ScContractTempletFill();
			fill.setContractTempletFillId(scContractTempletFills.get(i).getContractTempletFillId());
			fill.setContractFillContent(scContractTempletFills.get(i).getContractFillContent());
			fill.setRecordVersion(scContractTempletFills.get(i).getRecordVersion());

			fill.setScContractTempletInfo(null);
			scContractTempletFillList.add(fill);
		}
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(conTempletInfoEntity.getScContractTemplet().getContractTempletId());

		List<ScContractTempletUser> scContractTempletUserList = conTempletInfoEntity.getScContractTempletUser();
		StringBuffer resUserBuf = new StringBuffer("");
		StringBuffer resUserCdBuf = new StringBuffer("");
		for (int i = 0; scContractTempletUserList != null && i < scContractTempletUserList.size(); i++) {

			resUserBuf.append(scContractTempletUserList.get(i).getUserName() + ";");
			resUserCdBuf.append(scContractTempletUserList.get(i).getUserCd() + ";");
			// 如果创建人或责任是当前登陆人，则允许进合同进行编辑
			if (curLoginUserCd.equals(scContractTempletUserList.get(i).getUserCd())) {
				scContractParams.setIsOnlySee("0");
			}
		}
		/**
		 * 把责任人放到参数更表中用于初始化前台页面数据
		 */
		scContractParams.setResponsiblePersonCds(resUserCdBuf.toString());
		scContractParams.setResponsiblePersons(resUserBuf.toString());

		/**
		 * 获取合同中填写的数据
		 * 
		 */
		scContractParams.setConFillJson(JSONArray.fromObject(scContractTempletFillList).toString());

		/**
		 * 合同内容
		 */

		filePath = getContHisFilePath(contractTempletHisId, scContractTemplet);

		scContractParams.setScContractHtml(getFileContent(filePath));

		// 查找合同对应的合同台帐
		StringBuffer hql = new StringBuffer("from ContLedger t where t.contractTempletInfoId =:contId");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("contId", id);

		List<ContLedger> contLedgerList = contLedgerManager.find(hql.toString(), values);
		// 如果已经关联合同台账则提供合同台账查
		if (!contLedgerList.isEmpty() && contLedgerList.size() > 0) {
			scContractParams.setContLedgerId(contLedgerList.get(0).getContLedgerId());
			scContractParams.setContLedgerNo(contLedgerList.get(0).getContNo());
		}
		return "mark";
	}

	/**
	 * 获取绑定流程
	 * 
	 * @param contractTempletHisId
	 *            历史版本ID
	 * @return
	 */
	private ResApproveInfo getResFlow(String contractTempletHisId) {
		if (StringUtils.isNotBlank(contractTempletHisId)) {
			StringBuffer hql = new StringBuffer();
			Map<String, Object> param = new HashMap<String, Object>();
			hql.append("from ScContractInfoFlow where contractTempletHisId = :contractTempletHisId");
			param.put("contractTempletHisId", contractTempletHisId);
			List<ScContractInfoFlow> scFlowList = scContractInfoFlowManager.find(hql.toString(), param);
			if (scFlowList != null && scFlowList.size() > 0) {
				ScContractInfoFlow scFlow = scFlowList.get(0);
				if (scFlow != null && StringUtils.isNotBlank(scFlow.getResApproveInfoId())) {
					ResApproveInfo resApproveInfo = resApproveInfoManager.getEntity(scFlow.getResApproveInfoId());
					return resApproveInfo;
				}
			}

		}

		return null;
	}

	/**
	 * 合同附件上传页面
	 * 
	 * @return 页面名称
	 */
	public String attachUpload() {

		return "attachUpload";

	}

	/**
	 * 上传合同附件
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	public void doAttachUpload() throws IOException {

		StringBuffer strBuffer = new StringBuffer("{status:");
		String scContId = Struts2Utils.getParameter("scAttachContId");
		String scAttachHistContId = Struts2Utils.getParameter("scAttachHistContId");
		String attachTypeCd = Struts2Utils.getParameter("attachTypeCd");
		String isstandard = Struts2Utils.getParameter("isscstandard");
		// 获取模板CD
		String templetId = Struts2Utils.getParameter("sysTempletId");
		// 附件路径
		String attachSavePath = "";
		// 附件名称
		String attachFileName = "";
		if (StringUtils.isNotBlank(isstandard)) {
			isstandard = "0";
		}
		String remark = Struts2Utils.getParameter("remark");

		try {
			prepareModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ScContractTempletHis scContractTempletHis = new ScContractTempletHis();
		ScContractTempletInfo scContractTempletInfo = new ScContractTempletInfo();
		ScContractInfoAttach scContractInfoAttach = new ScContractInfoAttach();

		try {
			if (StringUtils.isNotBlank(scAttachHistContId)) {
				scContractTempletHis = scContractTempletHisManager.getEntity(scAttachHistContId);
			}
			if (StringUtils.isNotEmpty(scContId)) {

				scContractTempletInfo = scContractTempletInfoManager.getEntity(scContId);
			}
			String savPath = conUpload.getSavePath("sctemplet", null, true);
			conUpload.getDir(null);

			for (int i = 0; upload != null && i < upload.length; i++) {
				if (upload[i] != null) {

					String newFileName = "sc_attach" + conUpload.genFileName(uploadFileName[i]);
					File fout = new File(savPath, newFileName);
					FileOutputStream fos = new FileOutputStream(fout);
					FileInputStream fin = new FileInputStream(upload[i]);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fin.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					fin.close();
					upload[i].delete();
					attachSavePath = savPath + "\\" + newFileName;
					attachFileName = conUpload.getFileName(uploadFileName[i]);

					if (StringUtils.isBlank(scContId)) {// 为空
						ScContractTemplet scConTemplet = ScContractTempletManager.getEntity(templetId);
						// 添加合同
						scContractTempletInfo.setStatusCd("99");
						scContractTempletInfo.setRecordVersion(Long.valueOf("0"));
						scContractTempletInfo.setScContractTemplet(scConTemplet);
						// 合同类型
						scContractTempletInfo.setIsstandard(Byte.valueOf(isstandard));
						scContractTempletInfoManager.saveScContractTempletInfo(scContractTempletInfo);

						scContId = scContractTempletInfo.getContractTempletInfoId();

						scContractTempletHis = new ScContractTempletHis();

						scContractTempletHis.setScContractTempletInfo(scContractTempletInfo);
						scContractTempletHis.setRecordVersion(Long.valueOf("0"));
						scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);
						scAttachHistContId = scContractTempletHis.getContractTempletHisId();

					}
					if (StringUtils.isNotBlank(scContId)) {// 如果合同CD不为空
						scContractInfoAttach.setRealFileName(attachFileName);
						scContractInfoAttach.setAttachName(newFileName);
						scContractInfoAttach.setAttachPath(savPath);
						scContractInfoAttach.setScContractTempletInfo(scContractTempletInfo);
						scContractInfoAttach.setScContractTempletHis(scContractTempletHis);
						scContractInfoAttach.setRemark(remark);
						if (scContractTempletInfo != null) {

						}
						if (scContractTempletInfo.getScContractInfoSaies() != null && scContractTempletInfo.getScContractInfoAttachs().size() > 0) {

							scContractInfoAttach.setSequenceNo(Long.valueOf((scContractTempletInfo.getScContractInfoAttachs().size() + 1)));
						} else {
							scContractInfoAttach.setSequenceNo(Long.valueOf("1"));
						}

						scContractInfoAttach.setAttachTypeCd(attachTypeCd);
						scContractInfoAttachManager.saveScContractInfoAttach(scContractInfoAttach);
					}
				}
			}

			strBuffer.append("true,scConId:\"" + scContId + "\",");
			strBuffer.append("scHisContId:\"" + scAttachHistContId + "\"}");
		} catch (Exception ee) {
			// 如果插入数据失败，则删除已上传的附件
			if (StringUtils.isNotBlank(attachSavePath)) {
				conUpload.deleteFile(attachSavePath);

			}
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
	}

	/**
	 * 查詢留言信息或增加留言
	 * 
	 * @return
	 * @throws Exception
	 */
	public String say() throws Exception {

		String scContractId = Struts2Utils.getParameter("scContractId");
		String hisContId = Struts2Utils.getParameter("hisContId");
		// initPermission();
		String msgContent = Struts2Utils.getParameter("content");
		// 引用的留言
		String referMsgId = Struts2Utils.getParameter("referMsgId");
		StringBuffer whereString = new StringBuffer("");
		ScContractTempletHis scContractTempletHis = new ScContractTempletHis();
		ScContractTempletInfo scContractTempletInfo = null;
		Map<String, Object> values = new HashMap<String, Object>();

		values.put("hisContId", hisContId);
		values.put("scContractId", scContractId);
		if (StringUtils.isNotBlank(hisContId)) {
			scContractTempletHis = scContractTempletHisManager.getEntity(hisContId);

			whereString.append(" and m.scContractTempletHis.contractTempletHisId=:hisContId ");
		}
		if (StringUtils.isNotEmpty(scContractId)) {

			scContractTempletInfo = scContractTempletInfoManager.getEntity(scContractId);

			whereString.append(" and m.scContractTempletInfo.contractTempletInfoId=:scContractId ");

		}
		if (scContractTempletInfo != null && scContractTempletHis != null && StringUtils.isNotEmpty(msgContent)) {

			ScContractInfoSay scContractInfoSay = new ScContractInfoSay();
			scContractInfoSay.setMsgContent(msgContent);
			scContractInfoSay.setQuotetoConInfoSayId(referMsgId);
			scContractInfoSay.setScContractTempletHis(scContractTempletHis);
			scContractInfoSay.setScContractTempletInfo(scContractTempletInfo);
			if (scContractTempletInfo != null) {
				if (scContractTempletInfo.getScContractInfoSaies() != null && scContractTempletInfo.getScContractInfoSaies().size() > 0) {

					scContractInfoSay.setSequenceNo(Long.valueOf((scContractTempletInfo.getScContractInfoSaies().size() + 1)));
				} else {
					scContractInfoSay.setSequenceNo(Long.valueOf("1"));
				}

			} else {

			}
			scContractInfoSayManager.saveScContractInfoSay(scContractInfoSay);
		}
		messages = scContractInfoSayManager.loadMsgList(whereString.toString(), values);
		return "say";
	}

	/**
	 * 获取合同指定类别附件信息
	 * 
	 * @return
	 */
	public String seeAttachList() {
		String scContractId = Struts2Utils.getParameter("scContractId");
		String hisContId = Struts2Utils.getParameter("hisContId");
		String attachTypeCd = Struts2Utils.getParameter("attachTypeCd");
		StringBuffer whereString = new StringBuffer("");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("hisContId", hisContId);
		values.put("scContractId", scContractId);
		values.put("attachTypeCd", attachTypeCd);

		/**
		 * 最新的流信息
		 * */
		if (StringUtils.isNotEmpty(scContractId)) {

			whereString.append(" and m.scContractTempletInfo.contractTempletInfoId=:scContractId ");

		}
		whereString.append(" and m.attachTypeCd=:attachTypeCd");

		StringBuffer hql = null;
		/**
		 * 搜索上传的附件
		 */
		hql = new StringBuffer("from ScContractInfoAttach m where 1=1   " + whereString + " order by m.createdDate desc ");

		List<ScContractInfoAttach> contractAttachList = null;

		if (StringUtils.isNotEmpty(scContractId)) {

			ScContractInfoAttach infoAttachMent = new ScContractInfoAttach();
			contractAttachList = scContractInfoAttachManager.find(hql.toString(), values);
			for (int i = 0; contractAttachList != null && i < contractAttachList.size(); i++) {
				ScContractInfoAttach conAttach = contractAttachList.get(i);
				infoAttachMent = new ScContractInfoAttach();
				infoAttachMent.setScContractInfoAttachId(conAttach.getScContractInfoAttachId());
				infoAttachMent.setAttachName(conAttach.getAttachName());
				infoAttachMent.setAttachPath(conAttach.getAttachPath());
				infoAttachMent.setRealFileName(conAttach.getRealFileName());
				infoAttachMent.setCreatedDate(conAttach.getCreatedDate());
				infoAttachMent.setCreator(conAttach.getCreator());
				conAttachOutputList.add(infoAttachMent);

			}

		}

		return "seeAttachList";
	}

	/**
	 * 获取合同所包含的所有附件信息
	 * 
	 * @return
	 */
	public String loadAttachList() {
		String scContractId = Struts2Utils.getParameter("scContractId");
		String hisContId = Struts2Utils.getParameter("hisContId");
		StringBuffer whereString = new StringBuffer("");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("hisContId", hisContId);
		values.put("scContractId", scContractId);
		// 合同附件类别
		this.attachTypeMaps = appDictTypeManager.getDictDataByTypeCd(DictContants.SC_ATTACHMENT_TYPE);
		if (attachTypeMaps == null) {
			attachTypeMaps = new HashMap<String, String>();
		}
		/**
		 * 最新的流信息
		 * */
		if (StringUtils.isNotEmpty(scContractId)) {

			whereString.append(" and m.scContractTempletInfo.contractTempletInfoId=:scContractId ");

		}

		StringBuffer hql = null;
		/**
		 * 最近上传的附件
		 */
		hql = new StringBuffer("from ScContractInfoAttach m where (ifDelete is null or ifDelete='0') " + whereString + " order by m.createdDate desc ");

		List<ScContractInfoAttach> contractAttachList = null;

		if (StringUtils.isNotEmpty(scContractId)) {

			ScContractInfoAttach infoAttachMent = new ScContractInfoAttach();
			contractAttachList = scContractInfoAttachManager.find(hql.toString(), values);
			for (int i = 0; contractAttachList != null && i < contractAttachList.size(); i++) {
				ScContractInfoAttach conAttach = contractAttachList.get(i);
				infoAttachMent = new ScContractInfoAttach();
				infoAttachMent.setScContractInfoAttachId(conAttach.getScContractInfoAttachId());
				infoAttachMent.setAttachName(conAttach.getAttachName());
				infoAttachMent.setAttachPath(conAttach.getAttachPath());
				infoAttachMent.setRealFileName(conAttach.getRealFileName());
				infoAttachMent.setCreator(conAttach.getCreator());
				infoAttachMent.setCreatedDate(conAttach.getCreatedDate());
				infoAttachMent.setAttachTypeCd(attachTypeMaps.get(conAttach.getAttachTypeCd()));
				infoAttachMent.setRemark(conAttach.getRemark());
				conAttachOutputList.add(infoAttachMent);

			}

		}

		return "attachList";
	}

	/**
	 * 网批专用附件引用
	 * 
	 * @return
	 */
	public String loadResAttachList() {
		// 合同附件类别
		this.attachTypeMaps = appDictTypeManager.getDictDataByTypeCd(DictContants.SC_ATTACHMENT_TYPE);
		if (attachTypeMaps == null) {
			attachTypeMaps = new HashMap<String, String>();
		}

		String scContractId = Struts2Utils.getParameter("scContractId");
		String hisContId = Struts2Utils.getParameter("hisContId");
		StringBuffer whereString = new StringBuffer("");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("hisContId", hisContId);
		values.put("scContractId", scContractId);

		/**
		 * 最新的流信息
		 * */
		if (StringUtils.isNotEmpty(scContractId)) {

			whereString.append(" and m.scContractTempletInfo.contractTempletInfoId=:scContractId ");

		}

		StringBuffer hql = null;
		/**
		 * 最近上传的附件
		 */
		hql = new StringBuffer("from ScContractInfoAttach m where (ifDelete is null or ifDelete='0') " + whereString + " order by m.attachTypeCd asc ,m.createdDate desc ");

		List<ScContractInfoAttach> contractAttachList = null;

		if (StringUtils.isNotBlank(hisContId) && StringUtils.isNotEmpty(scContractId)) {

			entity = scContractTempletInfoManager.getEntity(scContractId);
			// 设置合同文本类别 (1.标准0非标）
			scContractParams.setIsStandard(entity.getIsstandard() + "");
			Struts2Utils.getRequest().setAttribute("scContractTempletInfo", entity);
			ScContractInfoAttach infoAttachMent = new ScContractInfoAttach();
			contractAttachList = scContractInfoAttachManager.find(hql.toString(), values);
			for (int i = 0; contractAttachList != null && i < contractAttachList.size(); i++) {
				ScContractInfoAttach conAttach = contractAttachList.get(i);
				infoAttachMent = new ScContractInfoAttach();
				infoAttachMent.setScContractInfoAttachId(conAttach.getScContractInfoAttachId());
				infoAttachMent.setAttachName(conAttach.getAttachName());
				infoAttachMent.setAttachPath(conAttach.getAttachPath());
				infoAttachMent.setRealFileName(conAttach.getRealFileName());
				infoAttachMent.setCreator(conAttach.getCreator());
				infoAttachMent.setCreatedDate(conAttach.getCreatedDate());
				infoAttachMent.setRemark(conAttach.getRemark());
				infoAttachMent.setAttachTypeCd(attachTypeMaps.get(conAttach.getAttachTypeCd()));
				infoAttachMent.setScContractTempletInfo(entity);
				conAttachOutputList.add(infoAttachMent);

			}

			//定标审批表网批编号有值，则构造为网批编号链接
			if(StringUtils.isNotBlank(entity.getApproveId1())){
				infoAttachMent = new ScContractInfoAttach();
				infoAttachMent.setScContractInfoAttachId(entity.getApproveId1());
				infoAttachMent.setAttachName(DINGBIAO_CD_987); // 定标审批表
				infoAttachMent.setAttachPath("");
				infoAttachMent.setRealFileName(entity.getApproveNo1());
				infoAttachMent.setCreator(entity.getCreator());
				infoAttachMent.setCreatedDate(entity.getCreatedDate());
				infoAttachMent.setRemark(null);
				infoAttachMent.setAttachTypeCd(DINGBIAO);	
				infoAttachMent.setScContractTempletInfo(entity);
				boolean if_do_cover = false;	//是否需要覆盖原来的附件为网批连接
				for(int i=0;null!=conAttachOutputList && i<conAttachOutputList.size();i++){
					ScContractInfoAttach infoAttachMent1 = conAttachOutputList.get(i);
					if(null==infoAttachMent1.getAttachTypeCd()
							|| "null".equalsIgnoreCase(infoAttachMent1.getAttachTypeCd())){
						infoAttachMent1.setAttachTypeCd("999");
					}
					if(infoAttachMent1.getAttachTypeCd().equalsIgnoreCase(DINGBIAO)){
						if_do_cover = true;
						conAttachOutputList.set(i, infoAttachMent);
						break;
					}
				}
				if(!if_do_cover){
					conAttachOutputList.add(infoAttachMent);
				}
			}
				
			//合同条款审批表网批编号有值，则构造为网批编号链接
			if(StringUtils.isNotBlank(entity.getApproveId2())){
				infoAttachMent = new ScContractInfoAttach();
				infoAttachMent.setScContractInfoAttachId(entity.getApproveId2());
				infoAttachMent.setAttachName(HETONGTIAOKUAN_CD_988); // 合同条款审批表
				infoAttachMent.setAttachPath("");
				infoAttachMent.setRealFileName(entity.getApproveNo2());
				infoAttachMent.setCreator(entity.getCreator());
				infoAttachMent.setCreatedDate(entity.getCreatedDate());
				infoAttachMent.setRemark(null);
				infoAttachMent.setAttachTypeCd(HETONGTIAOKUAN);	
				infoAttachMent.setScContractTempletInfo(entity);
				boolean if_do_cover = false;	//是否需要覆盖原来的附件为网批连接
				for(int i=0;null!=conAttachOutputList && i<conAttachOutputList.size();i++){
					ScContractInfoAttach infoAttachMent1 = conAttachOutputList.get(i);
					if(null==infoAttachMent1.getAttachTypeCd()
							|| "null".equalsIgnoreCase(infoAttachMent1.getAttachTypeCd())){
						infoAttachMent1.setAttachTypeCd("999");
					}
					if(infoAttachMent1.getAttachTypeCd().equalsIgnoreCase(HETONGTIAOKUAN)){
						if_do_cover = true;
						conAttachOutputList.set(i, infoAttachMent);
						break;
					}
				}
				if(!if_do_cover){
					conAttachOutputList.add(infoAttachMent);
				}
			}
		}
		
		return "resAttachList";
	}

	/**
	 * @return the attachTypeMaps
	 */
	public Map<String, String> getAttachTypeMaps() {
		return attachTypeMaps;
	}

	/**
	 * @param attachTypeMaps
	 *            the attachTypeMaps to set
	 */
	public void setAttachTypeMaps(Map<String, String> attachTypeMaps) {
		this.attachTypeMaps = attachTypeMaps;
	}

	/**
	 * 刪除附件
	 */
	public void delAttachment() {

		StringBuffer strBuffer = new StringBuffer("{status:");
		String attachmentId = Struts2Utils.getParameter("attachmentId");

		try {

			if (StringUtils.isNotBlank(attachmentId)) {

				ScContractInfoAttach infoAttach = scContractInfoAttachManager.getEntity(attachmentId);
				//String filePath = infoAttach.getAttachPath() + "\\" + infoAttach.getAttachName();
				// 物理删除合同附件
				//conUpload.deleteFile(filePath);
				infoAttach.setIfDelete(true);
				scContractInfoAttachManager.saveScContractInfoAttach(infoAttach);
				//scContractInfoAttachManager.deleteScContractInfoAttach(attachmentId);
			}

			strBuffer.append("true}");
		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		try {
			String attchid = Struts2Utils.getParameter("attchid");
			ScContractInfoAttach conAttach = null;
			if (StringUtils.isNotBlank(attchid)) {
				conAttach = scContractInfoAttachManager.getEntity(attchid);
			}
			if (conAttach == null)
				return null;

			// 根据jsp传入的参数寻找文件
			File file = new File(conAttach.getAttachPath() + "\\" + conAttach.getAttachName());

			if (!file.exists())
				return null;
			String fileSuf = fileName.substring(fileName.indexOf("."));
			// ChangeCharset.toISO_8859_1(realFileName);
			// setContentDisposition(operator);
			logger.info(conAttach.getAttachName());
			String realFileNameTmp = conAttach.getAttachName();

			HttpServletRequest request = Struts2Utils.getRequest();
			String agentStr = request.getHeader("User-Agent");
			if (agentStr == null) {
				agentStr = "MSIE";
			}
			logger.info("enter file download....agent=" + agentStr);

			agentStr = agentStr.toUpperCase();
			if (agentStr != null && agentStr.indexOf("MSIE") != -1) {
				// setDownFileName(java.net.URLEncoder.encode(realFileNameTmp,
				// "UTF-8"));
			} else {
				// / setDownFileName(new
				// String(realFileNameTmp.getBytes("UTF-8"), "ISO8859-1"));
			}

			String fileType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, fileSuf);
			new FileInputStream(file);
		} catch (Exception e) {

		}
		return SUCCESS;
	}

	/**
	 * 获取最近信息
	 * 
	 * @return
	 */
	public String recurentlyConInfo() {
		resApproveInfoList.clear();
		scContractPrintList.clear();
		resApproveInfoList.clear();
		conAttachOutputList.clear();
		String scContractId = Struts2Utils.getParameter("scContractId");
		String hisContId = Struts2Utils.getParameter("hisContId");
		StringBuffer whereString = new StringBuffer("");
		List<ScContractInfoAttach> contractAttachList = null;
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("hisContId", hisContId);
		values.put("scContractId", scContractId);

		if (StringUtils.isNotEmpty(scContractId)) {

			whereString.append(" and m.scContractTempletInfo.contractTempletInfoId=:scContractId ");

		}
		/*
		 * 最近流程
		 */
		StringBuffer hql = new StringBuffer("from ScContractInfoFlow m where 1=1  " + whereString + " order by m.createdDate desc ");
		List<ScContractInfoFlow> scContractInfoFlow = new ArrayList<ScContractInfoFlow>();
		if (StringUtils.isNotBlank(scContractId)) {
			scContractInfoFlow = scContractInfoFlowManager.find(hql.toString(), values);
			for (ScContractInfoFlow bean : scContractInfoFlow) {
				ResApproveInfo resApproveInfo = resApproveInfoManager.getEntity(bean.getResApproveInfoId());
				if (resApproveInfo != null) {
					resApproveInfoList.add(resApproveInfo);
					break;
				}
			}

		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("scContId", scContractId);
		List<ScContractPrint> scContractPrints = new ArrayList<ScContractPrint>();
		// 打印记录
		hql = new StringBuffer(
				"from ScContractPrint t where t.scContractTempletInfo.contractTempletInfoId=:scContId and t.recordTypeCd is null order by t.createdDate desc");
		scContractPrints = scContractPrintManager.find(hql.toString(), param);// scContTempInfo.getScContractPrints();

		if (null != scContractPrints && scContractPrints.size() > 0) {

			scContractPrintList.add(scContractPrints.get(0));
		}

		List<ScContractPrint> scContractApproveHiss = new ArrayList<ScContractPrint>();
		// 操作人记录
		hql = new StringBuffer(
				"from ScContractPrint t where t.scContractTempletInfo.contractTempletInfoId=:scContId and t.recordTypeCd is not null order by t.createdDate desc");
		scContractApproveHiss = scContractPrintManager.find(hql.toString(), param);// scContTempInfo.getScContractPrints();

		if (null != scContractApproveHiss && scContractApproveHiss.size() > 0) {

			scContractApproveHisList.add(scContractApproveHiss.get(0));
		}
		/**
		 * 最近上传的附件
		 */
		hql = new StringBuffer("from ScContractInfoAttach m where 1=1   " + whereString + " order by m.createdDate desc ");
		if (StringUtils.isNotEmpty(scContractId)) {
			ScContractInfoAttach infoAttachMent = new ScContractInfoAttach();
			contractAttachList = scContractInfoAttachManager.find(hql.toString(), values);
			if (contractAttachList != null && contractAttachList.size() > 0) {
				ScContractInfoAttach conAttach = contractAttachList.get(0);
				infoAttachMent = new ScContractInfoAttach();
				infoAttachMent.setScContractInfoAttachId(conAttach.getScContractInfoAttachId());
				infoAttachMent.setAttachName(conAttach.getAttachName());
				infoAttachMent.setAttachPath(conAttach.getAttachPath());
				infoAttachMent.setRealFileName(conAttach.getRealFileName());

				infoAttachMent.setCreator(conAttach.getCreator());
				infoAttachMent.setCreatedDate(conAttach.getCreatedDate());
				infoAttachMent.setRemark(conAttach.getRemark());
				conAttachOutputList.add(infoAttachMent);

			}

		}
		if (StringUtils.isNotBlank(hisContId)) {
			whereString.append(" and m.scContractTempletHis.contractTempletHisId=:hisContId ");
		}
		/**
		 * 最新的留言信息
		 * */
		hql = new StringBuffer("from ScContractInfoSay m where 1=1   " + whereString + " order by m.createdDate desc ");
		List<ScContractInfoSay> messageSays = scContractInfoSayManager.find(hql.toString(), values);

		if (messageSays != null && messageSays.size() > 0) {
			messages = new ArrayList<ScContractInfoSay>();
			messages.add(messageSays.get(0));
		}

		return "recurentlyMsgInfo";
	}

	/**
	 * 加载标准合同菜单按钮
	 * 
	 * @return
	 */
	public String loadButton() {
		String id = Struts2Utils.getParameter("scContractId");
		String isOnlySee = Struts2Utils.getParameter("isOnlySee");
		if (StringUtils.isNotBlank(isOnlySee) && isOnlySee.equals("0")) {

			scContractParams.setIsOnlySee(isOnlySee);
		}
		if (StringUtils.isNotBlank(id)) {
			conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		}

		return "button";
	}

	/**
	 * 加载非标合同顶部菜单按钮
	 * 
	 * @return
	 */
	public String loadNonStandardButton() {

		String id = Struts2Utils.getParameter("scContractId");
		String isOnlySee = Struts2Utils.getParameter("isOnlySee");
		if (StringUtils.isNotBlank(isOnlySee) && isOnlySee.equals("0")) {

			scContractParams.setIsOnlySee(isOnlySee);
		}
		if (StringUtils.isNotBlank(id)) {
			conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		}
		return "nonStanbutton";
	}

	public String buildContract() {

		return "buildCon";
		// return"buildCon";
	}

	/**
	 * 创建非标合同
	 * 
	 * @return
	 */
	public String createNonStandard() {
		conTempletInfoEntity = new ScContractTempletInfo();
		// 从定标审批表中导入
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("resNo"))) {
			String resFields = "";
			Cookie[] cookies = Struts2Utils.getRequest().getCookies();// 获取一个cookie数组
			for (Cookie cookie : cookies) {
				if ("autoFileFields".equals(cookie.getName())) {
					try {
						resFields = URLDecoder.decode(cookie.getValue(), "UTF-8");
						scContractParams.setResFields(resFields);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}

			}

			if (StringUtils.isNotBlank(Struts2Utils.getParameter("resRela"))) {
				// 网批中的附件列表

				scContractParams.setResRela(Struts2Utils.getParameter("resRela"));
			}
			// 网批编号
			scContractParams.setResNo(Struts2Utils.getParameter("resNo"));

			ResApproveInfo resApprove = resApproveInfoManager.getEntity(Struts2Utils.getParameter("resNo"));
			conTempletInfoEntity.setApproveNo1(resApprove.getDisplayNo().toString());
			conTempletInfoEntity.setApproveId1(resApprove.getResApproveInfoId());
		}

		String id = Struts2Utils.getParameter("templetId");
		bizModuleCd = Struts2Utils.getParameter("bizmodulecd");
		if (StringUtils.isBlank(bizModuleCd)) {
			bizModuleCd = "sctemplet";
		}
		String isstandard = Struts2Utils.getParameter("isstandard");
		if (StringUtils.isBlank(isstandard)) {
			isstandard = "0";
		}
		ScContractTemplet scContractTemplet = ScContractTempletManager.getEntity(id);

		String ScContractName = scContractTemplet.getTempletName();
		scContractParams.setScContractHtml("");

		conTempletInfoEntity.setContractTempletInfoId("");
		conTempletInfoEntity.setIsstandard(Byte.valueOf(isstandard));
		conTempletInfoEntity.setScContractTemplet(scContractTemplet);
		/*conTempletInfoEntity.setContractName(ScContractName);*/
		StringBuffer resUserBuf = new StringBuffer(SpringSecurityUtils.getCurrentUserName() + ";");
		StringBuffer resUserCdBuf = new StringBuffer(SpringSecurityUtils.getCurrentUserCd() + ";");
		// 设置合同三级编号
		// scContractParams.setThirdSn(scContractTemplet.getScContractTempletType()
		// != null ? scContractTemplet.getScContractTempletType().getSn() : "");

		String SN = "";
		ScContractTempletType contractTempletType = scContractTemplet.getScContractTempletType();
		scContractParams.setTempletTypeCd(contractTempletType.getModuleTypeCd());
		while (StringUtils.isBlank(SN)) {
			SN = contractTempletType.getSn();
			if (StringUtils.isBlank(SN) && StringUtils.isNotBlank(contractTempletType.getParentId()) && !contractTempletType.getParentId().equals("0")) {

				contractTempletType = scContractTempletTypeManager.getEntity(contractTempletType.getParentId());
			} else {

				break;
			}

		}
		// 设置合同三级编号
		scContractParams.setThirdSn(SN);
		/**
		 * 把责任人放到参数更表中用于初始化前台页面数据
		 */
		scContractParams.setResponsiblePersonCds(resUserCdBuf.toString());
		scContractParams.setResponsiblePersons(resUserBuf.toString());
		if (scContractTemplet != null) {
			conAttachmentEntity.setAttachPath(conUpload.getFileName(scContractTemplet.getTempletPath()));
			conAttachmentEntity.setCreator(scContractTemplet.getCreator());
			conAttachmentEntity.setAttachName(conTempletInfoEntity.getContractName() + conUpload.getSuffix(scContractTemplet.getTempletPath()));
		}

		appAttachFileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(id, bizModuleCd);
		// 设置可以创建
		scContractParams.setIsOnlySee("0");
		// tab Frame页面ID
		scContractParams.setFrameId(Struts2Utils.getParameter("frameId"));
		return "nonstandard";

	}

	/**
	 * 保存非标准合同
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveNonStandard() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer strBuffer = new StringBuffer("{status:");
		// 合同模板ID
		String templetId = Struts2Utils.getParameter("conTemletId");
		// 合同ID
		String scContractId = Struts2Utils.getParameter("scContractId");
		// 合同ID2(用于判断当前合同是否是新增状态，上传附件以后会执行save方法，会生成合同ID用于关联合同，赋值给scContractId)
		String scContractId2 = Struts2Utils.getParameter("scContractId2");
		// 历史合同ID
		String scHisContId = Struts2Utils.getParameter("scHisContId");
		// 合同编号
		String contractNo = Struts2Utils.getParameter("contractNo");
		// 合同名称
		String ScContractName = Struts2Utils.getParameter("contractName");
		// 项目CD
		String projectCd = Struts2Utils.getParameter("projectCd");
		// 项目名称
		String projectName = Struts2Utils.getParameter("projectName");

		// 记录版本
		String recordVersion = Struts2Utils.getParameter("recordVersion");
		// 招标合同编号
		String inviteNo = Struts2Utils.getParameter("inviteNo");
		// 合同责任人
		String responsiblePersons = Struts2Utils.getParameter("curUserName");
		// 合同责任人CD
		String responsiblePersonsCd = Struts2Utils.getParameter("curUserCd");
		// 合同总价
		String contractPrice = Struts2Utils.getParameter("contractPrice");
		// 合同状态
		statusCd = Struts2Utils.getParameter("scnonstatusCd");
		// 旧网批编号
		String approveNo = Struts2Utils.getParameter("approveNo");
		// 旧网批ID
		String approveId = Struts2Utils.getParameter("approveId");
		// 定标审批表网批编号
		String approveNo1 = Struts2Utils.getParameter("approveNo1");
		// 定标审批表网批ID
		String approveId1 = Struts2Utils.getParameter("approveId1");
		// 合同条款审批表网批编号
		String approveNo2 = Struts2Utils.getParameter("approveNo2");
		// 合同条款审批表网批ID
		String approveId2 = Struts2Utils.getParameter("approveId2");
		// 合同评审表网批编号
		String approveNo3 = Struts2Utils.getParameter("approveNo3");
		// 合同评审表网批ID
		String approveId3 = Struts2Utils.getParameter("approveId3");
		// 合同台账编号
		String contNo = Struts2Utils.getParameter("contNo");
		// 合同台账ID
		String contID = Struts2Utils.getParameter("contID");
		// 定标审批表中带入的网批ID
		String resNo = Struts2Utils.getParameter("resNo");
		// 定标审批表中带入的附件列表
		String resRela = Struts2Utils.getParameter("resRela");
		// 合同类型
		String isstandard = "0";
		// 创建合同台账操作状态信息
		String ctLedgerMsg = "";
		/**
		 * 合同历史记录
		 */
		ScContractTempletHis scContractTempletHis = new ScContractTempletHis();
		List<ScContractTempletUser> contractTempletUserList = null;
		try {
			entity = new ScContractTempletInfo();

			if (StringUtils.isBlank(recordVersion)) {
				recordVersion = "0";
			}

			if (StringUtils.isBlank(statusCd)) {
				statusCd = "10";
			}
			// 上传附件后会执行一次合同保存，合同ID(scContractId)会被赋值，所以用scContractId2来判断
			if (StringUtils.isNotBlank(scContractId)) {
				entity = scContractTempletInfoManager.getEntity(scContractId);
				if (entity.getStatusCd().equals("99")) {
					if (StringUtils.isNotBlank(scHisContId)) {
						scContractTempletHis = scContractTempletHisManager.getEntity(scHisContId);
					}
					scContractTempletHis.setScContractTempletInfo(entity);
					ScContractTemplet scConTemplet = ScContractTempletManager.getEntity(templetId);
					String savPath = conUpload.getSavePath("sctemplet", null, true);
					/**
					 * 拷貝文件
					 */
					if (scConTemplet != null && scConTemplet.getTempletPath() != null) {
						String newFileName = "nonsc_attach" + conUpload.genFileName(scConTemplet.getTempletPath());
						File fout = new File(savPath, newFileName);
						FileOutputStream fos = new FileOutputStream(fout);
						File file = new File(scConTemplet.getTempletPath());
						if (file.exists()) {
							FileInputStream fin = new FileInputStream(scConTemplet.getTempletPath());
							byte[] buffer = new byte[1024];
							int len = 0;
							while ((len = fin.read(buffer)) > 0) {
								fos.write(buffer, 0, len);
							}
							fos.close();
							fin.close();
						}
						savPath = savPath + "\\" + newFileName;
						scContractTempletHis.setTempletPath(savPath);

					}
					scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);
				}
				entity.setContractName(ScContractName);
				entity.setStatusCd(statusCd);
				entity.setProjectCd(projectCd);
				entity.setContractNo(contractNo);
				entity.setInviteNo(inviteNo);
				entity.setIsstandard(Byte.valueOf(isstandard));
				entity.setProjectName(projectName);
				entity.setContractPrice(contractPrice);
				entity.setApproveNo(approveNo);
				entity.setApproveId(approveId);
				entity.setApproveNo1(approveNo1);
				entity.setApproveId1(approveId1);
				entity.setApproveNo2(approveNo2);
				entity.setApproveId2(approveId2);
				entity.setApproveNo3(approveNo3);
				entity.setApproveId3(approveId3);
				entity.setContNo(contNo);
				entity.setContID(contID);
				contractTempletUserList = entity.getScContractTempletUser();
				scContractTempletInfoManager.saveScContractTempletInfo(entity);

				scContractId = entity.getContractTempletInfoId();
				recordVersion = "" + entity.getRecordVersion();

			} else { // 添加合同
				ScContractTemplet ScContractTemplet = new ScContractTemplet();
				ScContractTemplet.setContractTempletId(templetId);
				entity.setContractName(ScContractName);
				entity.setStatusCd(statusCd);
				entity.setContractNo(contractNo);
				entity.setInviteNo(inviteNo);
				entity.setScContractTemplet(ScContractTemplet);
				entity.setProjectCd(projectCd);
				entity.setProjectName(projectName);
				entity.setIsstandard(Byte.valueOf(isstandard));
				entity.setContractPrice(contractPrice);
				entity.setApproveNo(approveNo);
				entity.setApproveId(approveId);
				entity.setApproveNo1(approveNo1);
				entity.setApproveId1(approveId1);
				entity.setApproveNo2(approveNo2);
				entity.setApproveId2(approveId2);
				entity.setApproveNo3(approveNo3);
				entity.setApproveId3(approveId3);
				entity.setContNo(contNo);
				entity.setContID(contID);
				scContractTempletInfoManager.saveScContractTempletInfo(entity);

				scContractId = entity.getContractTempletInfoId();

				ScContractTemplet scConTemplet = ScContractTempletManager.getEntity(templetId);
				String savPath = conUpload.getSavePath("sctemplet", null, true);
				/**
				 * 拷貝文件
				 */
				if (scConTemplet != null && scConTemplet.getTempletPath() != null) {
					String newFileName = "nonsc_attach" + conUpload.genFileName(scConTemplet.getTempletPath());
					File fout = new File(savPath, newFileName);
					FileOutputStream fos = new FileOutputStream(fout);
					File file = new File(scConTemplet.getTempletPath());
					if (file.exists()) {
						FileInputStream fin = new FileInputStream(scConTemplet.getTempletPath());
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = fin.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						fos.close();
						fin.close();
					}
					savPath = savPath + "\\" + newFileName;
					scContractTempletHis.setTempletPath(savPath);
				}
				scContractTempletHis.setScContractTempletInfo(entity);
				scContractTempletHis.setRecordVersion(Long.valueOf("0"));
				scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);

				/**
				 * 从定标审批表中导入合同文本库 (如果想操作网批或待办事项可在此判断体内进行
				 */
				// 如果网批编号不为空且附件信息不为空
				if (StringUtils.isNotBlank(resNo)) {
					JSONArray resAttsArray = JSONArray.fromObject(resRela.replace("'", "\""));
					Map<String, Object> params = new HashMap<String, Object>();
					int iCount = 0;
					for (Iterator iter = resAttsArray.iterator(); iter.hasNext();) {
						JSONObject jsonObject = (JSONObject) iter.next();
						VoScContractFromResAttach resAttach = (VoScContractFromResAttach) JSONObject.toBean(jsonObject, VoScContractFromResAttach.class);
						if (StringUtils.isNotBlank(resAttach.getAttachId())) {
							params.put(resAttach.getAttachId(), resAttach.getAttachName());
							
						}
						
					}
					
					StringBuffer hql = new StringBuffer();
					hql.append(" from AppAttachFile t where t.bizEntityId =:bizEntityId ");
					params.put("bizEntityId", resNo);
					// 获取定标审批表对应的附件列表
					appAttachFileList = appAttachFileManager.find(hql.toString(), params);
					iCount = appAttachFileList.size();
					ScContractInfoAttach infoAttach;
					// 附件类型CD
					String attachTypeCd;
					AppAttachFile appAttachFile;
					
					scContractTempletHisManager.saveScContractTempletHis(scContractTempletHis);
					
					for (int i = 0; i < iCount; i++) {
						appAttachFile = appAttachFileList.get(i);
						infoAttach = new ScContractInfoAttach();
						attachTypeCd = "" + params.get(appAttachFile.getAppAttachFileId());
						if(StringUtils.isBlank(attachTypeCd)){
							//如果是空，就取“其他”类
							attachTypeCd = "999";
						}
						infoAttach.setAttachName(appAttachFile.getFileName());
						infoAttach.setRealFileName(appAttachFile.getRealFileName());
						infoAttach.setAttachPath(appAttachFile.getFilePath());
						infoAttach.setAttachTypeCd(attachTypeCd);
						infoAttach.setCreator(appAttachFile.getCreator());
						infoAttach.setCreatedDate(appAttachFile.getCreatedDate());
						infoAttach.setCreatedCenterCd(appAttachFile.getCreatedCenterCd());
						infoAttach.setCreatedDeptCd(appAttachFile.getCreatedDeptCd());
						infoAttach.setCreatedPositionCd(appAttachFile.getCreatedPositionCd());
						infoAttach.setScContractTempletInfo(entity);
						infoAttach.setScContractTempletHis(scContractTempletHis);
						// 抓取网批中的附件
						scContractInfoAttachManager.saveScContractInfoAttach(infoAttach);
						//	
					}
					// 导入合同台账
					ctLedgerMsg = doImportContLedger(resNo, contractNo, ScContractName, entity.getContractTempletInfoId(), scContractTempletHis
							.getContractTempletHisId(), contNo);
					if(StringUtils.isNotBlank(contLedgerId)){
						entity.setContID(contLedgerId);
						entity.setContNo(entity.getContractNo());
						scContractTempletInfoManager.saveScContractTempletInfo(entity);
					}
				}
				
			}

			// 只有在合同创建时可以修改责任人
			if (StringUtils.isNotBlank(statusCd) && Long.valueOf(statusCd) < 40) {

				Map<String, Object> values = new HashMap<String, Object>();

				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf.append("delete from ScContractTempletUser ");
				sqlBuf.append(" where scContractTempletInfo.contractTempletInfoId=:templetInfoId");
				values.put("templetInfoId", entity.getContractTempletInfoId());
				Query query = contractTempletUserManager.getDao().createQuery(sqlBuf.toString(), values);
				query.executeUpdate();
				// 责任人列表
				String[] respPersionNameList = responsiblePersons.split(";");
				// 责任人CD
				String[] respPersionList = responsiblePersonsCd.split(";");

				Session contractTempletUserSession = contractTempletUserManager.getDao().getSession();

				contractTempletUserList = new ArrayList<ScContractTempletUser>();

				for (int i = 0; i < respPersionNameList.length; i++) {
					ScContractTempletUser contracttempletUser = new ScContractTempletUser();

					contracttempletUser.setScContractTempletInfo(entity);

					contracttempletUser.setCreatedDate(entity.getCreatedDate());
					contracttempletUser.setCreatedDeptCd(entity.getCreatedDeptCd());
					contracttempletUser.setCreatedPositionCd(entity.getCreatedPositionCd());
					contracttempletUser.setCreator(entity.getCreator());
					contracttempletUser.setUserName(respPersionNameList[i]);
					contracttempletUser.setUserCd(i < respPersionList.length ? respPersionList[i] : "");
					contractTempletUserList.add(contracttempletUser);
				}

				for (int i = 0; i < contractTempletUserList.size(); i++) {

					contractTempletUserSession.saveOrUpdate(contractTempletUserList.get(i));

					if (i == contractTempletUserList.size() - 1) {
						// 添加责任
						contractTempletUserSession.flush();
						contractTempletUserSession.clear();
					}

				}
			}

			// 如果是合同编写完成或已签署，则记录通过人信息
			if (statusCd.equals("40") || statusCd.equals("70") || statusCd.equals("80")) {
				ScContractPrint scContractPrint = new ScContractPrint();
				scContractPrint.setRecordTypeCd(statusCd);
				scContractPrint.setScContractTempletInfo(entity);
				scContractPrintManager.saveScContractPrint(scContractPrint);
			}
			strBuffer.append("true,scConId:\"" + scContractId + "\"");
			strBuffer.append(",reversion:\"" + recordVersion + "\"");
			strBuffer.append(",resNo:\"" + resNo + "\"");
			// 如果不为空，则说明执行导入合同台账操作
			if (StringUtils.isNotBlank(ctLedgerMsg)) {
				strBuffer.append(",ctLedgerMsg:\"" + ctLedgerMsg + "\"");

			}
			strBuffer.append("}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee != null && ee.getMessage() != null ? ee.getMessage().replace("\"", "”") : "提交失败"));

		}

		Struts2Utils.renderText(strBuffer.toString());
		return null;

	}

	/**
	 * 查看非标准合同
	 * 
	 * @return
	 */
	public String readNonStandardCon() {
		// 当前登陆人
		String curLoginUserCd = SpringSecurityUtils.getCurrentUiid();
		// 当前合同ID
		String id = Struts2Utils.getParameter("scContId");
		conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
		// 流程状态
		statusCd = Struts2Utils.getParameter("statusCd");

		// 如果审核人、创建人或责任是当前登陆人，则允许进合同进行编辑审核
		if (curLoginUserCd.equals(conTempletInfoEntity.getCreator()) || validateRole(isApprove)) {
			scContractParams.setIsOnlySee("0");
		}
		// 获取合同负责人列表
		List<ScContractTempletUser> scContractTempletUserList = conTempletInfoEntity.getScContractTempletUser();
		StringBuffer resUserBuf = new StringBuffer("");
		StringBuffer resUserCdBuf = new StringBuffer("");
		for (int i = 0; scContractTempletUserList != null && i < scContractTempletUserList.size(); i++) {

			resUserBuf.append(scContractTempletUserList.get(i).getUserName() + ";");
			resUserCdBuf.append(scContractTempletUserList.get(i).getUserCd() + ";");
			// 如果创建人或责任是当前登陆人，则允许进合同进行编辑
			if (curLoginUserCd.equals(scContractTempletUserList.get(i).getUserCd())) {
				scContractParams.setIsOnlySee("0");
			}
		}

		/**
		 * 把责任人放到参数更表中用于初始化前台页面数据
		 */
		scContractParams.setResponsiblePersonCds(resUserCdBuf.toString());
		scContractParams.setResponsiblePersons(resUserBuf.toString());

		// 设置合同三级编号
		scContractParams.setThirdSn(conTempletInfoEntity.getScContractTemplet().getScContractTempletType() != null ? conTempletInfoEntity
				.getScContractTemplet().getScContractTempletType().getSn() : "");

		List<ScContractTempletHis> scContractTempletHisList = conTempletInfoEntity.getScContractTempletHises();

		Long maxHisVersion = Long.valueOf("-1");
		if (scContractTempletHisList != null && scContractTempletHisList.size() > 0) {
			for (int i = 0; i < scContractTempletHisList.size(); i++) {
				ScContractTempletHis scContractTempletHis = scContractTempletHisList.get(i);
				if (null != scContractTempletHis && maxHisVersion < scContractTempletHis.getRecordVersion()) {
					maxHisContId = scContractTempletHis.getContractTempletHisId();
					maxHisVersion = scContractTempletHis.getRecordVersion();
					conAttachmentEntity.setAttachPath(conUpload.getFileName(scContractTempletHis.getTempletPath()));
					conAttachmentEntity.setCreator(scContractTempletHis.getCreator());
					conAttachmentEntity.setAttachName(conTempletInfoEntity.getContractName() + conUpload.getSuffix(scContractTempletHis.getTempletPath()));
				}

			}
		}
		// 查找合同对应的合同台帐
		StringBuffer hql = new StringBuffer("from ContLedger t where t.contractTempletInfoId =:contId");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("contId", id);

		List<ContLedger> contLedgerList = contLedgerManager.find(hql.toString(), values);
		// 如果已经关联合同台账则提供合同台账查
		if (!contLedgerList.isEmpty() && contLedgerList.size() > 0) {
			scContractParams.setContLedgerId(contLedgerList.get(0).getContLedgerId());
			scContractParams.setContLedgerNo(contLedgerList.get(0).getContNo());
		}
		// 定标审批表带入的网批ID
		scContractParams.setResNo(Struts2Utils.getParameter("resNo"));
		
		// 1: 已删除状态，
		if(conTempletInfoEntity.getIsDel() != null && 1 == conTempletInfoEntity.getIsDel()) {
			scContractParams.setIsDel("1");
		}

		return "nonstandard";
	}

	/**
	 * 更新状态
	 */
	public void updateStatus() {
		String scContractId = Struts2Utils.getParameter("scContractId");
		statusCd = Struts2Utils.getParameter("statusCd");
		if (StringUtils.isNotBlank(scContractId) && StringUtils.isNotBlank(statusCd)) {
			ScContractTempletInfo scContractTempletInfo = scContractTempletInfoManager.getEntity(scContractId);
			scContractTempletInfo.setStatusCd(statusCd);

			scContractTempletInfoManager.saveScContractTempletInfo(scContractTempletInfo);
			// 如果是合同编写完成或已签署，则记录通过人信息
			if (statusCd.equals("40") || statusCd.equals("70") || statusCd.equals("80")) {
				ScContractPrint scContractPrint = new ScContractPrint();
				scContractPrint.setRecordTypeCd(statusCd);
				scContractPrint.setScContractTempletInfo(scContractTempletInfo);
				scContractPrintManager.saveScContractPrint(scContractPrint);
			}

		}

	}

	/**
	 * 获取填空数据
	 */
	public void getFill() {
		String id = Struts2Utils.getParameter("scContId");

		if (StringUtils.isNotBlank(id)) {
			conTempletInfoEntity = scContractTempletInfoManager.getEntity(id);
			List<ScContractTempletFill> scContractTempletFills = conTempletInfoEntity.getScContractTempletFills();
			List<ScContractTempletFill> scContractTempletFillList = new ArrayList<ScContractTempletFill>();

			for (int i = 0; scContractTempletFills != null && i < scContractTempletFills.size(); i++) {
				ScContractTempletFill fill = new ScContractTempletFill();
				fill.setContractTempletFillId(scContractTempletFills.get(i).getContractTempletFillId());
				fill.setContractFillContent(scContractTempletFills.get(i).getContractFillContent());
				fill.setRecordVersion(scContractTempletFills.get(i).getRecordVersion());

				fill.setScContractTempletInfo(null);
				scContractTempletFillList.add(fill);
			}
			/**
			 * 获取合同中填写的数据
			 */
			// scContractParams.setConFillJson(JSONArray.fromObject(scContractTempletFillList).toString());
			Struts2Utils.renderText(JSONArray.fromObject(scContractTempletFillList).toString());
		}

	}

	/**
	 * @return the curUser
	 */
	public String getCurUser() {
		return curUser;
	}

	/**
	 * @param curUser
	 *            the curUser to set
	 */
	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	/**
	 * @return the scContractPrintList
	 */
	public List<ScContractPrint> getScContractPrintList() {
		return scContractPrintList;
	}

	/**
	 * @param scContractPrintList
	 *            the scContractPrintList to set
	 */
	public void setScContractPrintList(List<ScContractPrint> scContractPrintList) {
		this.scContractPrintList = scContractPrintList;
	}

	/**
	 * @return the scContractApproveHisList
	 */
	public List<ScContractPrint> getScContractApproveHisList() {
		return scContractApproveHisList;
	}

	/**
	 * @param scContractApproveHisList
	 *            the scContractApproveHisList to set
	 */
	public void setScContractApproveHisList(List<ScContractPrint> scContractApproveHisList) {
		this.scContractApproveHisList = scContractApproveHisList;
	}

	/**
	 * @return the attachment
	 */
	public File[] getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the attachmentContentType
	 */
	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	/**
	 * @param attachmentContentType
	 *            the attachmentContentType to set
	 */
	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	/**
	 * @return the appAttachFileList
	 */
	public List<AppAttachFile> getAppAttachFileList() {
		return appAttachFileList;
	}

	/**
	 * @param appAttachFileList
	 *            the appAttachFileList to set
	 */
	public void setAppAttachFileList(List<AppAttachFile> appAttachFileList) {
		this.appAttachFileList = appAttachFileList;
	}

	public List<ExecPlanLayout> getExecPlanLayouts() {
		return execPlanLayouts;
	}

	public void setExecPlanLayouts(List<ExecPlanLayout> execPlanLayouts) {
		this.execPlanLayouts = execPlanLayouts;
	}

	/**
	 * 判断当前合同的编号是否为标准合同
	 */
	public void isStandardCont() {
		String id = Struts2Utils.getParameter("id");
//		System.out.println("?????????????"+id);
//		System.out.println("?????????????"+getId());
		ScContractTempletInfo info;
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(id)) {
			info = scContractTempletInfoManager.getEntity(getId());

		} else {

			info = new ScContractTempletInfo();
		}
		if (info.getIsstandard() > 0) {

			Struts2Utils.renderText("{status:'true'}");
		} else {
			Struts2Utils.renderText("{status:'false'}");

		}
	}

	/**
	 * 定标审批表导入合同库与合同台账参数处理
	 */
	public void getResAttachType() {
		// 定批审批表附件
		String resRela = Struts2Utils.getParameter("resAttachList");
		// 定标审批表中与合同文本库自动关联字段
		String autoFileFields = Struts2Utils.getParameter("autoFileFields");
		// 合同台账参数
		String createContLedFiels = Struts2Utils.getParameter("createContLedFiels");
		try {
			if (StringUtils.isNotBlank(autoFileFields)) {
				autoFileFields = URLEncoder.encode(autoFileFields, "UTF-8");
			}
			if (StringUtils.isNotBlank(createContLedFiels)) {
				createContLedFiels = URLEncoder.encode(createContLedFiels, "UTF-8");
			}
			/**
			 * 保存网批中引用的字段
			 */
			Cookie cookie = new Cookie("autoFileFields", autoFileFields);
			cookie.setMaxAge(60 * 60 * 1000);
			Struts2Utils.getResponse().addCookie(cookie);
			/**
			 * 保存合同台账字段
			 */
			Cookie contLedFielsCookie = new Cookie("createContLedFiels", createContLedFiels);
			contLedFielsCookie.setMaxAge(60 * 60 * 1000);
			Struts2Utils.getResponse().addCookie(contLedFielsCookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray resAttsArray = JSONArray.fromObject(resRela.replace("'", "\""));
		// 合同附件类别
		this.attachTypeMaps = appDictTypeManager.getDictDataByTypeCd(DictContants.SC_ATTACHMENT_TYPE);
		Iterator<String> attachiter = attachTypeMaps.keySet().iterator();
		Map<String, String> scAttachTypeMaps = new HashMap<String, String>();
		while (attachiter.hasNext()) {
			String key = attachiter.next();
			scAttachTypeMaps.put(attachTypeMaps.get(key), key);
		}
		if (attachTypeMaps == null) {
			attachTypeMaps = new HashMap<String, String>();
		}
		List<VoScContractFromResAttach> voFromResAttachList = new ArrayList<VoScContractFromResAttach>();
		String attachTypeCd = "";
		for (Iterator iter = resAttsArray.iterator(); iter.hasNext();) {

			JSONObject jsonObject = (JSONObject) iter.next();
			VoScContractFromResAttach resAttach = (VoScContractFromResAttach) JSONObject.toBean(jsonObject, VoScContractFromResAttach.class);
			if (StringUtils.isNotBlank(resAttach.getAttachId())) {
				attachTypeCd = scAttachTypeMaps.get(resAttach.getAttachName());
				if (StringUtils.isBlank(attachTypeCd)) {

					attachTypeCd = scAttachTypeMaps.get("其他");
				}
				resAttach.setAttachName(attachTypeCd);
				voFromResAttachList.add(resAttach);
			}

		}

		Struts2Utils.renderJson(voFromResAttachList);

	}

	/**
	 * 合同创建成功后，创建合同台账
	 * 
	 * @param resInfoId
	 *            网批ID
	 * @param contractNo
	 *            合同编号
	 * @param conName
	 *            合同名称
	 * @param contId
	 *            合同ID
	 * @param hisContId
	 *            合同历史记录
	 * @param contNo
	 * 			  合同台账编号
	 * @return 返回合创建合同台账信息，出现异常则返回false,否则返回true,并把网批ID、合同台账编号、合同总价返回前面保存到网批中
	 */
	public String doImportContLedger(String resInfoId, String contractNo, String conName, String contId, String hisContId, String contNo) {
		StringBuilder resultMsg = new StringBuilder("");
		try {
			// 定标审批表导入合同台账字段
			String resContLedParams;
			// 定义合同导入合同台账中的属性值
			// 商家库ID
			String supBasicId = "";
			// 项目名称
			String projectName = "";
			// 项目CD
			String projectCd = "";
			// 共多少天
			String totalDay = "";
			// 计划开工日期
			String fromDate = "";
			// 计划竣工日期
			String toDate = "";
			// 合同总价 ：审定价
			String contractPrice = "";
			// 中标单位
			String bidUnit = "";
			// 工程名称
			String engineeringName = "";
			// 总价包干pricingModel1
			String pricingModel1 = "";
			String pricingModel2 = "";

			// 二方合同
			String contTypeCd1 = "";
			// 三方合同
			String contTypeCd2 = "";
			// 带贸易公司合同
			String contTypeCd3 = "";
			// 甲方
			String parta = "";

			// 乙方
			String partb = "";
			// 丙方
			String partC = "";
			// //导出合同台账设置战略类型，0：非战略，1：战略
			String strageFlg = "";
			// 实际供方
			String realProvName = "";
			// 分类代码
			StringBuffer resAuthTypeCd = new StringBuffer();
			ResApproveInfo resAproveInfo;
			if (StringUtils.isNotBlank(resInfoId)) {
				resAproveInfo = resApproveInfoManager.getEntity(resInfoId);

			} else {
				resAproveInfo = new ResApproveInfo();
				return "";
			}

			Cookie[] cookies = Struts2Utils.getRequest().getCookies();// 这样便可以获取一个cookie数组
			for (Cookie cookie : cookies) {
				if ("createContLedFiels".equals(cookie.getName())) {
					try {
						resContLedParams = URLDecoder.decode(cookie.getValue(), "UTF-8");
						JSONArray resContLedFieldArray = JSONArray.fromObject(resContLedParams.replace("'", "\""));
						// 解析网批中的参数 并把对应的参数中的值放到对应的合同台账属性值
						for (Iterator iter = resContLedFieldArray.iterator(); iter.hasNext();) {
							JSONObject jsonObject = (JSONObject) iter.next();
							VoScContractFromResField resFields = (VoScContractFromResField) JSONObject.toBean(jsonObject, VoScContractFromResField.class);
							if ("supBasicId".equals(resFields.getFieldName())) {
								supBasicId = resFields.getFieldVal();
							} else if ("projectName".equals(resFields.getFieldName())) {
								projectName = resFields.getFieldVal();
							} else if ("projectCd".equals(resFields.getFieldName())) {
								projectCd = resFields.getFieldVal();
							} else if ("totalDay".equals(resFields.getFieldName())) {
								totalDay = resFields.getFieldVal();
							} else if ("fromDate".equals(resFields.getFieldName())) {
								fromDate = resFields.getFieldVal();
							} else if ("toDate".equals(resFields.getFieldName())) {
								toDate = resFields.getFieldVal();
							} else if ("contractPrice".equals(resFields.getFieldName())) {
								contractPrice = resFields.getFieldVal();
							} else if ("bidUnit".equals(resFields.getFieldName())) {
								bidUnit = resFields.getFieldVal();
							} else if ("engineeringName".equals(resFields.getFieldName())) {
								engineeringName = resFields.getFieldVal();
							} else if ("pricingModel1".equals(resFields.getFieldName())) {
								pricingModel1 = resFields.getFieldVal();
							} else if ("pricingModel2".equals(resFields.getFieldName())) {
								pricingModel2 = resFields.getFieldVal();
							} else if ("contTypeCd1".equals(resFields.getFieldName())) {
								contTypeCd1 = resFields.getFieldVal();
							} else if ("contTypeCd2".equals(resFields.getFieldName())) {
								contTypeCd2 = resFields.getFieldVal();
							} else if ("contTypeCd3".equals(resFields.getFieldName())) {
								contTypeCd3 = resFields.getFieldVal();
							} else if ("parta".equals(resFields.getFieldName())) {
								parta = resFields.getFieldVal();
							} else if ("partb".equals(resFields.getFieldName())) {
								parta = resFields.getFieldVal();
							} else if ("partb".equals(resFields.getFieldName())) {
								parta = resFields.getFieldVal();
							} else if ("partC".equals(resFields.getFieldName())) {
								partC = resFields.getFieldVal();
							} else if ("strageFlg".equals(resFields.getFieldName())) {
								strageFlg = resFields.getFieldVal();
							} else if ("realProvName".equals(resFields.getFieldName())) {
								realProvName = resFields.getFieldVal();
							}
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					break;
				}

			}
			if(null==ifNoContLeger || !"true".equalsIgnoreCase(ifNoContLeger)){
				// 如果商家库ID有值，则自动导入供应商库
				if (StringUtils.isNotBlank(supBasicId)) {
	
					SupApproveRes approveRes = new SupApproveRes();
					approveRes.setResApproveInfoId(resAproveInfo.getResApproveInfoId());
					approveRes.setSupAppType("1");// 定标
					approveRes.setProjectName(projectName);
					approveRes.setProjectCd(projectCd);
					approveRes.setAppCompTime(resAproveInfo.getCompleteDate());
					// approveRes.setAppProjectName(appProjectName);
					SupBasic supBasic = supBasicManager.getEntity(supBasicId);
					approveRes.setSupBasic(supBasic);
					supApproveResManager.saveSupApproveRes(approveRes);
				}
				// 若分类代码为地产定标审批表的记录，则新增一条合同台账
				// ZCGL_HTQS_50:为地产工程设计表
				resAuthTypeCd.append("'ZCGL_HTQS_50'");
				// ，BLSY_ZCGL_DB_10：为商业工程设计表
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_10'");
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_20'");
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_30'");
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_40'");
	
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_50'");
	
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_60'");
				resAuthTypeCd.append(",'BLSY_ZCGL_DB_70'");
	
				// if
				// (resAuthTypeCd.toString().indexOf("'"+resAproveInfo.getAuthTypeCd()+"'")>-1)
				// {
				ContLedger contLedger = new ContLedger();
				// contLedger.setProjectCd(projectCd)
				List<ContProjectCode> projectCodeList = projectCodeManager.getAll();
				// 寻找projectCd，若在合同类别里有projectCd,则新增该项目，若无则不setProjectCd
				boolean haveProject = false;
				for (ContProjectCode code : projectCodeList) {
					if (projectCd.equals(code.getProjectCd())) {
						haveProject = true;
						break;
					}
				}
				if (haveProject) {
					contLedger.setProjectCd(projectCd);
				}
				// 如果是商业工程设计表，则添加商业工程设计字段
				if ("BLSY_ZCGL_DB_10".equals(resAproveInfo.getAuthTypeCd())) {
					contLedger.setContTypeCd2("21");
				} else // 如果是定标审批表(工程改造、维护、维修)，则添加商业工程设计字段
				if ("BLSY_ZCGL_DB_20".equals(resAproveInfo.getAuthTypeCd())) {
					contLedger.setContTypeCd2("22");
				} else if ("BLSY_ZCGL_DB_30".equals(resAproveInfo.getAuthTypeCd())) {
					contLedger.setContTypeCd2("23");
				} else if ("BLSY_ZCGL_DB_40".equals(resAproveInfo.getAuthTypeCd())) {
					contLedger.setContTypeCd2("24");
				} else if ("BLSY_ZCGL_DB_50".equals(resAproveInfo.getAuthTypeCd())) {
					contLedger.setContTypeCd2("25");
				} else if ("BLSY_ZCGL_DB_60".equals(resAproveInfo.getAuthTypeCd()) || "BLSY_ZCGL_DB_70".equals(resAproveInfo.getAuthTypeCd())) {
					contLedger.setContTypeCd2("26");
				}
	
				if(StringUtils.isNotBlank(contNo)) {
					contLedger.setContNo(contNo);
				} else {
					if (StringUtils.isNotBlank(contractNo)) {
						contLedger.setContNo(contractNo);
					}
				}
				// 设计范围
				// contLedger.setRangeNum(designRange);
				// 付款方式
				// contLedger.setPayWay(paymentType);
	
				// 招标范围
				// contLedger.setRangeNum(bidRange);
	
				// 工程名称
				contLedger.setContName(engineeringName);
				// 总价包干pricingModel1
				if (StringUtils.isNotBlank(pricingModel1)) {
					contLedger.setContProperty("0");
				} else if (StringUtils.isNotBlank(pricingModel2)) {
					contLedger.setContProperty("2");
				}
	
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					// 计划开工日期
					contLedger.setPlanBeginDate(format.parse(fromDate));
				} catch (ParseException e) {
				}
				try {
					// 计划竣工日期
					contLedger.setPlanEndDate(format.parse(toDate));
				} catch (ParseException e) {
				}
				// 共多少天
				contLedger.setPeriod(totalDay);
				// 合同总价 ：审定价
				if (StringUtils.isNotBlank(contractPrice)) {
					String totalPrice = contractPrice.replace(",", "");
					// 合同总价
					contLedger.setTotalPrice(new BigDecimal(totalPrice));
					// 已确认合同总价
					contLedger.setUpdateTotal(contLedger.getTotalPrice());
				}
				// 中标单位
				contLedger.setPartB(bidUnit);
	
				contLedger.setPartA(parta);
				// 合同台账以乙方为准
				contLedger.setPartB(partb);
	
				if (StringUtils.isNotBlank(contTypeCd1) && contTypeCd1.equals("true")) {
	
					// 不做任何
				} else if (StringUtils.isNotBlank(contTypeCd2) && contTypeCd2.equals("true")) {
					contLedger.setPartC(partC);
					// 领料施工单位
					// contLedger.setAgreeAttaId(realUseName);
	
				} else if (StringUtils.isNotBlank(contTypeCd3) && contTypeCd3.equals("true")) {
					contLedger.setRealProvName(realProvName);
				}
				// 导出合同台账设置战略类型，0：非战略，1：战略
				contLedger.setStrageFlg(strageFlg);
	
				// 进度状态默认为未开工
				contLedger.setProcStatus("0");
				// 合同状态默认为未完未结
				contLedger.setContStatus("0");
				// 带入网批CD和ID
				// 带入网批CD和ID
				//contLedger.setResApproveCd(resAproveInfo.getApproveCd() + resAproveInfo.getSerialNo());
				contLedger.setResApproveCd(resAproveInfo.getDisplayNo().toString());
				contLedger.setResApproveId(resAproveInfo.getResApproveInfoId());
	
				// 关联合同文本库start
				// 合同ID
				contLedger.setContractTempletInfoId(contId);
				// 合同歷史ID
				contLedger.setContractTempletHisId(hisContId);
				// 合同文本編號
				contLedger.setContractNo(contractNo);
				// 合同名稱
				contLedger.setContractName(conName);
				// 关联合同文本库end
	
				contLedgerManager.saveContLedger(contLedger);
				contLedgerId=contLedger.getContLedgerId();
	
	
				/*
				 * ResApproveInfo resApproveInfo =
				 * resApproveInfoManager.getEntity(resInfoId);
				 * resApproveInfo.setIsImported(true); ResApproveContent
				 * approveContent = null; if
				 * (resApproveInfo.getResApproveContents().size() > 0) {
				 * approveContent = resApproveInfo.getResApproveContents().get(0);
				 * approveContent.setResApproveInfo(resApproveInfo);
				 * resApproveInfoManager.saveResApproveInfo(resApproveInfo,
				 * approveContent); }
				 */
				// }
			}
			resultMsg.append("'id':'" + resInfoId + "',");
			resultMsg.append("'templateBean.contractNo':'" + contractNo + "',");
			resultMsg.append("'templateBean.contractPrice':'" + contractPrice + "'");

		} catch (Exception ee) {
			resultMsg.append("'ctldStatus':'false'");
		}

		return resultMsg.toString();
	}
	
	/**
	 * 显示当前合同文本的批注列表信息
	 * @return
	 */
	public String markList() {
		String id = Struts2Utils.getParameter("id");
		String hisId = Struts2Utils.getParameter("hisId");

		if (StringUtils.isNotBlank(id)) {
			String filePath=getMaxFieldValue(id,"templetPath");
			String scContractHtml = getFileContent(filePath);
			List<VoScContractMark> markList = parseInsFromHtml(scContractHtml);
			
			Struts2Utils.getRequest().setAttribute("contractTempletInfoId", id);
			Struts2Utils.getRequest().setAttribute("contractTempletHisId", hisId);
			Struts2Utils.getRequest().setAttribute("markList", markList);
			Struts2Utils.getRequest().setAttribute("contractHtml", scContractHtml);

			if (StringUtils.isNotBlank(hisId)) {
				//根据ContractTempletHisId得到ContractMarkHis列表
				List<ScContractMarkHis> markHisList = scContactMarkHisManager.getListByContractTempletHisId(hisId);
				
				// 如果hisMarkList为空说明是第一次进入markList页面
				//如果hisMarkList和markList的contractMarkId不一致说明后来批注被增加或删除过,把ContractMark里的内容同步到ContractMarkHis
				if (markHisList == null){
					markHisList = new ArrayList<ScContractMarkHis>();
				}
				//删除比markList多出的MarkHis
				for (int i = 0; i < markHisList.size(); i++) {
					ScContractMarkHis markHis = markHisList.get(i);
					Predicate markIdCondition = new SimplePredicate("contractMarkId", markHis.getContractMarkId());
					boolean existFlag = CollectionUtils.exists(markList,markIdCondition);
					if (!existFlag) {
						scContactMarkHisManager.delete(markHis);
					}
				}
				//增加比markList少的MarkHis
				for (int i = 0; i < markList.size(); i++) {
					VoScContractMark mark = markList.get(i);
					Predicate markIdCondition = new SimplePredicate("contractMarkId", mark.getContractMarkId());
					boolean existFlag = CollectionUtils.exists(markHisList,markIdCondition);
					if (!existFlag) {
						ScContractMarkHis markHis = new ScContractMarkHis();
						markHis.setContractTempletHisId(hisId);
						markHis.setContractMarkId(mark.getContractMarkId());
						markHis.setContractMarkContent(new ClobImpl(mark.getContractMarkContent()));
						scContactMarkHisManager.saveScContractMarkHis(markHis);
					}
				}
				//对hisMarkContent进行赋值
				for (int i = 0; i < markHisList.size(); i++) {
					ScContractMarkHis hisMark = markHisList.get(i);
					Predicate markIdCondition = new SimplePredicate("contractMarkId", hisMark.getContractMarkId());
					VoScContractMark mark = (VoScContractMark) CollectionUtils.find(markList, markIdCondition);
					if (mark != null) {
						mark.setHisMarkContent(Util.clob2String2(hisMark.getContractMarkContent()));
					}
				}
			}
		}
		return "markList";
	}
	/**
	 * 删除批注历史数据
	 * 
	 */
	public void deleteContractMarkHis() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		String contractMarkId = Struts2Utils.getParameter("contractMarkId");
		try {
			if (contractMarkId != null && contractMarkId.length() > 0) {
				ScContractMarkHis entity = scContactMarkHisManager.getByContractMarkId(contractMarkId);
				scContactMarkHisManager.delete(entity);
			}
			strBuffer.append("true");
			strBuffer.append("}");
		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));
		}
		Struts2Utils.renderText(strBuffer.toString());
	}
	
	/**
	 * 根据合同文本解析批注信息
	 * 
	 * @param str
	 * @return
	 */
	public List<VoScContractMark> parseInsFromHtml(String str){
		List<VoScContractMark> resultList = new ArrayList();
		int beginIndex = str.indexOf("<ins"); 
		int endIndex = str.indexOf("</ins>");
		int begin = 0;
		int end = 0;
		int index=1;
		while(beginIndex>=0){
			
			String temp = str.substring(beginIndex, endIndex);

			begin = temp.indexOf("comment=\"");
			end = temp.indexOf("\"",begin+"comment=\"".length());
			String comment=temp.substring(begin+"comment=\"".length(),end);
			comment = comment.replaceAll("&amp;", "&");
			String comment2 = temp.substring(begin,end+1);
			temp = temp.replaceAll(comment2, "");
			
			begin = temp.indexOf("id=\"");
			end = temp.indexOf("\"",begin+"id=\"".length());
			String contractMarkId = temp.substring(begin+"id=\"".length(),end);
			
			begin = temp.indexOf(">");
			String contractMarkContent = temp.substring(begin+">".length()).trim();
			
			VoScContractMark mark = new VoScContractMark();
			mark.setContractMarkId(contractMarkId);
			mark.setComment(comment);
			mark.setContractMarkContent(contractMarkContent);
			mark.setHisMarkContent(contractMarkContent);//默认认为ContractHisMarkContent为ContractMarkContent
			mark.setContractMarkNo(String.valueOf(index++));
			resultList.add(mark);
			
			beginIndex = str.indexOf("<ins",endIndex+1); 
			endIndex = str.indexOf("</ins>",endIndex+1);
			
		}
		return resultList;
	}

	public String showTip() {
		return "showTip";
	}

	public String getIfNoContLeger() {
		return ifNoContLeger;
	}

	public void setIfNoContLeger(String ifNoContLeger) {
		this.ifNoContLeger = ifNoContLeger;
	}
	
}
