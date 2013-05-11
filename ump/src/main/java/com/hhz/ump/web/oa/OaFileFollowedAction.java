package com.hhz.ump.web.oa;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaFileFollowedManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaFileFollowed;
import com.hhz.ump.entity.oa.OaFileFollowedVO;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.IExcelExporter;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Namespace("/oa")
@Results({ @Result(name = "Exported", location = "oa-file-followed!exportExcel.action", type = "stream", params = {
		"contentType", "application/vnd.ms-excel", "contentDisposition", "attachment;filename=${excelFileName}.xls",
		"inputName", "excelFile"}) })
public class OaFileFollowedAction extends CrudActionSupport<OaFileFollowed> {

	private static final long serialVersionUID = -3445152342227169047L;

	private static final String SPLITER = "<BR>";
	// 文件结果类型：新建文件
	private static final String RESULT_TYPECD_NEW = "1";
	private static final String RESULT_TYPECD_NEW_DESC = "新建文件";
	// 文件结果类型：已送出
	private static final String RESULT_TYPECD_SENT = "2";
	private static final String RESULT_TYPECD_SENT_DESC = "已送出";
	// 文件结果类型：未确认
	private static final String RESULT_TYPECD_NOT_CONFIRMED = "3";
	private static final String RESULT_TYPECD_NOT_CONFIRMED_DESC = "未确认";
	// 文件结果类型：已确认
	private static final String RESULT_TYPECD_CONFIRMED = "4";
	private static final String RESULT_TYPECD_CONFIRMED_DESC = "已确认";
	// 文件结果类型：已处理
	private static final String RESULT_TYPECD_DEALT = "5";
	private static final String RESULT_TYPECD_DEALT_DESC = "已处理";
	// 文件结果类型：已完成
	private static final String RESULT_TYPECD_COMPLETED = "6";
	private static final String RESULT_TYPECD_COMPLETED_DESC = "已完成";

	// 构造页面按状态搜索时搜索类型跟文件状态的映射，用于搜索文件记录
	private static final String SEARCH_STATUS_PROCESSING = "1"; // 搜索条件：进行中
	private static final String SEARCH_STATUS_COMPLETED = "2"; // 搜索条件：已完成
	private static final String SEARCH_STATUS_DEALT = "3"; // 搜索条件：已处理
	private static final String SEARCH_STATUS_ALL = "4"; // 搜索条件：全部文件
	// 构造页面按状态搜索条件和文件跟踪记录的状态之间的映射
	private static final Map<String, Short[]> STATUS_SEARCH_MAP = new HashMap<String, Short[]>();
	static {
		// 1:进行中
		STATUS_SEARCH_MAP.put(SEARCH_STATUS_PROCESSING, new Short[] { OaFileFollowedManager.STATUS_NEW,
				OaFileFollowedManager.STATUS_SENT, OaFileFollowedManager.STATUS_CONFIRMED });
		// 2:已完成
		STATUS_SEARCH_MAP.put(SEARCH_STATUS_COMPLETED, new Short[] { OaFileFollowedManager.STATUS_COMPLETED });
		// 3:已处理
		STATUS_SEARCH_MAP.put(SEARCH_STATUS_DEALT, new Short[] { OaFileFollowedManager.STATUS_SENT,
				OaFileFollowedManager.STATUS_CONFIRMED, OaFileFollowedManager.STATUS_COMPLETED });
		// 4:全部文件
		STATUS_SEARCH_MAP.put(SEARCH_STATUS_ALL, new Short[] { OaFileFollowedManager.STATUS_NEW,
				OaFileFollowedManager.STATUS_SENT, OaFileFollowedManager.STATUS_CONFIRMED,
				OaFileFollowedManager.STATUS_COMPLETED });
	}

	@Autowired
	private OaFileFollowedManager oaFileFollowedManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	/**
	 * 用于展示文件列表的额外信息
	 */
	private Map<String, String> mapToProjectNames = new HashMap<String, String>();
	private Map<String, String> mapToCenterNames = new HashMap<String, String>();
	private Map<String, String> mapLatestStatus = new HashMap<String, String>();
	private Map<String, Boolean> mapIfHasAttach = new HashMap<String, Boolean>();

	/**
	 * 文件分页列表
	 */
	private Page<OaFileFollowed> page = new Page<OaFileFollowed>(50);
	/**
	 * 经过分类的文件列表
	 */
	private List<OaFileFollowedVO> fileResults = new ArrayList<OaFileFollowedVO>();

	private Map<String, String> mapProjectList;

	private OaFileFollowed entity;
	private String bizEntityId;
	private String bizModuleCd;
	private String serialNum;
	private String fileTrackingName;
	private String fileProjSn;
	// 留言
	private String message;
	private String curUser;
	private String receiverId;
	// 搜索过滤条件
	private String searchByStatus;
	private String searchByProjCd;
	private String searchByDeptCd;
	private String searchByDeptName;
	private String searchByGECreateDate;
	private String searchByLECreateDate;
	private String searchBySerialNum;
	private String searchByContent;
	private String searchByUser;
	private String projectName;
	private String projectSn;
	private String query;
	private String currentCd;
	private String currentId;

	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;

	// 附件列表
	private List<AppAttachFile> attachList;

	// 临时ID
	private String entityTmpId;
	// 用于批处理的信息
	private String batchData;

	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	private boolean if_in_attention; // 是否由关注引进本模块
	private Map<String, String> attentionMap;
	private String isAttentioned; // 修改的记录是否是被关注的，这里直接传这个参数可以不用读取关注表的map出来
	private Map<String, String> attentionMapUnread; // 未读的关注id，关注列表时用，用来判断关注是否已读

	@Override
	public String list() throws Exception {
		currentId = getId();
		entityTmpId = RandomUtils.generateTmpEntityId();

		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		if (org == null) {
			serialNum = oaFileFollowedManager.getFileSerailNumber(SpringSecurityUtils.getCurrentDeptCd());
			projectName = SpringSecurityUtils.getCurrentDeptName();
			projectSn = SpringSecurityUtils.getCurrentDeptCd();
		} else {
			projectName = org.getOrgName();
			projectSn = org.getOrgCd();
			serialNum = oaFileFollowedManager.getFileSerailNumber(projectSn);
		}
		populateFileResults();

		return SUCCESS;
	}

	/**
	 * 用于展现文件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fileList() throws Exception {
		populateFileResults();
		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		if (org == null) {
			currentCd = SpringSecurityUtils.getCurrentDeptCd();
		} else {
			currentCd = org.getOrgCd();
		}

		return "fileList";
	}

	/**
	 * 根据过滤条件获取文件跟踪列表
	 */
	private void populateFileResults() {
		curUser = SpringSecurityUtils.getCurrentUiid();

		if (!if_in_attention) {
			StringBuilder hql = new StringBuilder("from OaFileFollowed where 1=1");
			Map<String, Object> params = new HashMap<String, Object>();

			hql.append(" and status in (");
			Short[] status = null;
			if (StringUtils.isNotBlank(searchByStatus)) {
				status = STATUS_SEARCH_MAP.get(searchByStatus);
			} else {
				// 默认显示进行中状态的文件跟踪记录
				status = STATUS_SEARCH_MAP.get(SEARCH_STATUS_PROCESSING);
			}
			for (int i = 0; i < status.length; i++) {
				Short s = status[i];
				hql.append(String.valueOf(s));
				if (i < status.length - 1) {
					hql.append(",");
				}
			}
			hql.append(")");

			if (StringUtils.isNotBlank(searchByProjCd)) {
				hql.append(" and projectSn=:projectSn");
				params.put("projectSn", searchByProjCd);
			}

			if (StringUtils.isNotBlank(searchByGECreateDate)) {
				hql.append(" and createdDate>=:geCreatedDate");
				params.put("geCreatedDate", DateOperator.parse(searchByGECreateDate, "yyyy-MM-dd"));
			}

			if (StringUtils.isNotBlank(searchByDeptCd)) {
				// 若部门有值，则把部门以内的所有部门值搜索出来
				hql.append(" and acrossDeptCd like :acrossDeptCd");
				params.put("acrossDeptCd", "%" + searchByDeptCd + "%");
			}
			/*
			 * else{ //如果不是系统管理员，并选择全文检索，则默认搜索当前中心的文件 if
			 * (!SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) {
			 * if(SEARCH_STATUS_ALL.equals(searchByStatus)){ WsPlasOrg org =
			 * SpringSecurityUtils
			 * .getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX); if(org!=null){
			 * setSearchByDeptCd(org.getOrgCd());
			 * setSearchByDeptName(org.getOrgName());
			 * hql.append(" and acrossDeptCd like:acrossDeptCd");
			 * params.put("acrossDeptCd", "%"+searchByDeptCd+"%"); } } } }
			 */

			if (StringUtils.isNotBlank(searchByLECreateDate)) {
				hql.append(" and createdDate<=:leCreatedDate");
				params.put("leCreatedDate", DateOperator.parse(searchByLECreateDate, "yyyy-MM-dd"));
			}

			if (StringUtils.isNotBlank(searchByContent)) {
				hql.append(" and content like :content");
				params.put("content", "%" + searchByContent + "%");
			}

			// 按编号搜索时需要解析页面传过来的编号
			if (StringUtils.isNotBlank(searchBySerialNum)) {
				searchBySerialNum = searchBySerialNum.toUpperCase();
				String serialNumber = null;
				int serialNumberNum = -1;
				int lastIndex = searchBySerialNum.lastIndexOf("-");
				if (lastIndex >= 0) {
					serialNumber = searchBySerialNum.substring(0, lastIndex);
					if (lastIndex < searchBySerialNum.length() - 1) {
						try {
							serialNumberNum = Integer.parseInt(searchBySerialNum.substring(lastIndex + 1));
						} catch (NumberFormatException ne) {
							serialNumberNum = -1;
							serialNumber = searchBySerialNum;
						}
					}
				} else {
					try {
						serialNumberNum = Integer.parseInt(searchBySerialNum);
					} catch (NumberFormatException ne) {
						serialNumberNum = -1;
						serialNumber = searchBySerialNum;
					}
				}
				if (StringUtils.isNotBlank(serialNumber)) {
					if (serialNumberNum >= 0) {
						hql.append(" and serialNumber = :serialNumber");
						params.put("serialNumber", serialNumber + "-");
					} else {
						hql.append(" and serialNumber like :serialNumber");
						params.put("serialNumber", "%" + serialNumber + "%");
					}
				}
				if (serialNumberNum >= 0) {
					hql.append(" and serialNumberNum=:serialNumberNum");
					params.put("serialNumberNum", new BigDecimal(serialNumberNum));
				}
			}

			// 如果是搜索所有文件，则返回所有人的文件列表
			// if (!SEARCH_STATUS_ALL.equals(searchByStatus)) {
			// 如果搜索状态为3或4
			if (SEARCH_STATUS_DEALT.equals(searchByStatus) || SEARCH_STATUS_ALL.equals(searchByStatus)) {
				// 如果是管理员，当指定搜索人时，可以搜索该人员所有已处理的文件，否则搜索所有人的记录
				if (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) {
					// 若有搜索用户
					if (StringUtils.isNotBlank(searchByUser)) {
						hql.append(" and remark like (:relatedUser)");
						params.put("relatedUser", "%," + searchByUser + ",%");
					}
				} else {
					// 如果不是管理员，选择全部文件则搜索本中心的文件，否则搜索经手过的文件
					if (SEARCH_STATUS_ALL.equals(searchByStatus)) {
						query = "1";
					} else {
						hql.append(" and remark like (:relatedUser)");
						params.put("relatedUser", "%," + curUser + ",%");
					}

				}
			} else {
				// 如果是管理员，则当指定搜索人时，可以搜索该人员的文件，否则，搜索所有人的记录
				if (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) {
					if (StringUtils.isNotBlank(searchByUser)) {
						hql.append(" and (currentUiid=:curId or receiver=:receiverId)");
						params.put("curId", searchByUser);
						params.put("receiverId", searchByUser);
					}
				} else {
					hql.append(" and (currentUiid=:curId or receiver=:receiverId)");
					params.put("curId", curUser);
					params.put("receiverId", curUser);
				}
			}
			// }

			hql.append(" and deleteFlg=0 order by status asc, updatedDate desc");

			page = oaFileFollowedManager.findPage(page, hql.toString(), params);
		} else {
			// 如果在关注里，只需要显示有更新的关注的记录
			List list_page = new ArrayList();
			String uiid = SpringSecurityUtils.getCurrentUiid();
			attentionMapUnread = new LinkedHashMap<String, String>();
			List list_attention = oaAllAttentionManager.getUnreadIds("oaFileFollowed", SpringSecurityUtils.getCurrentUiid(), "OA_FILE_FOLLOWED", "OA_FILE_FOLLOW_ID");
			for (int i = 0; null != list_attention && i < list_attention.size(); i++) {
				String attentionEntityId = (String) list_attention.get(i);
				OaFileFollowed oaFileFollowed = oaFileFollowedManager.getEntity(attentionEntityId);
				list_page.add(oaFileFollowed);
				attentionMapUnread.put(attentionEntityId, "unread");
			}
			List list_read_attention = oaAllAttentionManager.getReadIds("oaFileFollowed", uiid, "OA_FILE_FOLLOWED", "OA_FILE_FOLLOW_ID");
			for (int i = 0; null != list_read_attention && i < list_read_attention.size(); i++) {
				String attentionEntityId = (String) list_read_attention.get(i);
				OaFileFollowed oaFileFollowed = oaFileFollowedManager.getEntity(attentionEntityId);
				list_page.add(oaFileFollowed);
			}
			page.setResult(list_page);
		}
		attentionMap = oaAllAttentionManager.getMyAttention("oaFileFollowed", SpringSecurityUtils.getCurrentUiid());
		// 对搜索结果进行处理
		buildDisplayData(page.getResult());
		mapProjectList = buildProjSelMap();
	}

	/**
	 * 构造页面待展示的数据
	 * 
	 * @param result
	 */
	private void buildDisplayData(List<OaFileFollowed> fileList) {

		String fId = null;
		Map<String, OaFileFollowedVO> results = new HashMap<String, OaFileFollowedVO>();
		OaFileFollowedVO v = null;
		String typeCd = null;
		String typeName = null;
		String curId = SpringSecurityUtils.getCurrentUiid();

		for (OaFileFollowed f : fileList) {
			fId = f.getOaFileFollowId();
			if (StringUtils.isBlank(f.getProjectSn()) || "-1".equals(f.getProjectSn())) {
				mapToProjectNames.put(fId, "其他");
			} else {
				mapToProjectNames.put(fId, CodeNameUtil.getDeptNameByCd(f.getProjectSn()));
			}
			mapToCenterNames.put(fId, CodeNameUtil.getDeptNameByCd(f.getCenterDeptSn()));
			mapLatestStatus.put(fId, getLatestStatus(f.getScheduleDescribe2()));
			mapIfHasAttach.put(fId, appAttachFileManager.ifEntityHasAttach(fId));

			if (SEARCH_STATUS_DEALT.equals(searchByStatus)) {
				typeCd = RESULT_TYPECD_DEALT;
				typeName = RESULT_TYPECD_DEALT_DESC;
			} else {
				short status = f.getStatus().shortValue();
				switch (status) {
				case OaFileFollowedManager.STATUS_NEW:
					typeCd = RESULT_TYPECD_NEW;
					typeName = RESULT_TYPECD_NEW_DESC;
					break;
				case OaFileFollowedManager.STATUS_SENT:
					if (curId.equals(f.getCurrentUiid())) {
						typeCd = RESULT_TYPECD_SENT;
						typeName = RESULT_TYPECD_SENT_DESC;
					} else if (curId.equals(f.getReceiver())) {
						typeCd = RESULT_TYPECD_NOT_CONFIRMED;
						typeName = RESULT_TYPECD_NOT_CONFIRMED_DESC;
					} else {
						typeCd = RESULT_TYPECD_NOT_CONFIRMED;
						typeName = RESULT_TYPECD_NOT_CONFIRMED_DESC;
					}
					break;
				case OaFileFollowedManager.STATUS_CONFIRMED:
					typeCd = RESULT_TYPECD_CONFIRMED;
					typeName = RESULT_TYPECD_CONFIRMED_DESC;
					break;
				case OaFileFollowedManager.STATUS_COMPLETED:
					typeCd = RESULT_TYPECD_COMPLETED;
					typeName = RESULT_TYPECD_COMPLETED_DESC;
					break;
				default:
					break;
				}
			}
			if (results.get(typeCd) != null) {
				results.get(typeCd).addNewFile(f);
			} else {
				v = new OaFileFollowedVO();
				v.setTypeCd(typeCd);
				v.setTypeName(typeName);
				v.addNewFile(f);
				results.put(typeCd, v);
			}
		}

		if (results.get(RESULT_TYPECD_NEW) != null) {
			fileResults.add(results.get(RESULT_TYPECD_NEW));
		}
		if (results.get(RESULT_TYPECD_SENT) != null) {
			fileResults.add(results.get(RESULT_TYPECD_SENT));
		}
		if (results.get(RESULT_TYPECD_NOT_CONFIRMED) != null) {
			fileResults.add(results.get(RESULT_TYPECD_NOT_CONFIRMED));
		}
		if (results.get(RESULT_TYPECD_CONFIRMED) != null) {
			fileResults.add(results.get(RESULT_TYPECD_CONFIRMED));
		}
		if (results.get(RESULT_TYPECD_DEALT) != null) {
			fileResults.add(results.get(RESULT_TYPECD_DEALT));
		}
		if (results.get(RESULT_TYPECD_COMPLETED) != null) {
			fileResults.add(results.get(RESULT_TYPECD_COMPLETED));
		}
	}

	/**
	 * 导出Excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		// populateFileResults();
		IExcelExporter oaFileExcelExporter = new OaFileExcelExporter(populateExcelFileResults());
		excelFile = oaFileExcelExporter.buildExportedExcelInputStream();
		Date now = Calendar.getInstance().getTime();
		String d = DateOperator.formatDate(now, "yyyy-MM-dd");
		String fileName = "专项任务列表_" + d;
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "Exported";
	}

	/**
	 * 根据过滤条件获取符合条件的文件跟踪列表，并以Excel文件的形式导出
	 */
	private List<OaFileFollowed> populateExcelFileResults() {
		curUser = SpringSecurityUtils.getCurrentUiid();

		StringBuilder hql = new StringBuilder("from OaFileFollowed where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();

		hql.append(" and status in (");
		Short[] status = null;
		if (StringUtils.isNotBlank(searchByStatus)) {
			status = STATUS_SEARCH_MAP.get(searchByStatus);
		} else {
			// 默认显示进行中状态的文件跟踪记录
			status = STATUS_SEARCH_MAP.get(SEARCH_STATUS_PROCESSING);
		}
		for (int i = 0; i < status.length; i++) {
			Short s = status[i];
			hql.append(String.valueOf(s));
			if (i < status.length - 1) {
				hql.append(",");
			}
		}
		hql.append(")");

		if (StringUtils.isNotBlank(searchByProjCd)) {
			hql.append(" and projectSn=:projectSn");
			params.put("projectSn", searchByProjCd);
		}

		if (StringUtils.isNotBlank(searchByGECreateDate)) {
			hql.append(" and createdDate>=:geCreatedDate");
			params.put("geCreatedDate", DateOperator.parse(searchByGECreateDate, "yyyy-MM-dd"));
		}

		if (StringUtils.isNotBlank(searchByDeptCd)) {
			// 若部门有值，则把部门以内的所有部门值搜索出来
			hql.append(" and acrossDeptCd like:acrossDeptCd");
			params.put("acrossDeptCd", "%" + searchByDeptCd + "%");
			// hql.append(" and centerDeptSn=:centerDeptSn");
			// params.put("centerDeptSn", searchByDeptCd);
		}

		if (StringUtils.isNotBlank(searchByLECreateDate)) {
			hql.append(" and createdDate<=:leCreatedDate");
			params.put("leCreatedDate", DateOperator.parse(searchByLECreateDate, "yyyy-MM-dd"));
		}

		if (StringUtils.isNotBlank(searchByContent)) {
			hql.append(" and content like :content");
			params.put("content", "%" + searchByContent + "%");
		}

		// 按编号搜索时需要解析页面传过来的编号
		if (StringUtils.isNotBlank(searchBySerialNum)) {
			searchBySerialNum = searchBySerialNum.toUpperCase();
			String serialNumber = null;
			int serialNumberNum = -1;
			int lastIndex = searchBySerialNum.lastIndexOf("-");
			if (lastIndex >= 0) {
				serialNumber = searchBySerialNum.substring(0, lastIndex);
				if (lastIndex < searchBySerialNum.length() - 1) {
					try {
						serialNumberNum = Integer.parseInt(searchBySerialNum.substring(lastIndex + 1));
					} catch (NumberFormatException ne) {
						serialNumberNum = -1;
						serialNumber = searchBySerialNum;
					}
				}
			} else {
				try {
					serialNumberNum = Integer.parseInt(searchBySerialNum);
				} catch (NumberFormatException ne) {
					serialNumberNum = -1;
					serialNumber = searchBySerialNum;
				}
			}
			if (StringUtils.isNotBlank(serialNumber)) {
				if (serialNumberNum >= 0) {
					hql.append(" and serialNumber = :serialNumber");
					params.put("serialNumber", serialNumber + "-");
				} else {
					hql.append(" and serialNumber like :serialNumber");
					params.put("serialNumber", "%" + serialNumber + "%");
				}
			}
			if (serialNumberNum >= 0) {
				hql.append(" and serialNumberNum=:serialNumberNum");
				params.put("serialNumberNum", new BigDecimal(serialNumberNum));
			}
		}

		if (SEARCH_STATUS_DEALT.equals(searchByStatus) || SEARCH_STATUS_ALL.equals(searchByStatus)) {
			// 如果是管理员，当指定搜索人时，可以搜索该人员所有已处理的文件，否则搜索所有人的记录
			if (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) {
				// 若有搜索用户
				if (StringUtils.isNotBlank(searchByUser)) {
					hql.append(" and remark like (:relatedUser)");
					params.put("relatedUser", "%," + searchByUser + ",%");
				}
			} else {
				// 如果不是管理员，选择全部文件则搜索本中心的文件，否则搜索经手过的文件
				if (SEARCH_STATUS_ALL.equals(searchByStatus)) {
					query = "1";
					/*
					 * WsPlasOrg org = SpringSecurityUtils
					 * .getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX); if (org
					 * == null) { projectSn =
					 * SpringSecurityUtils.getCurrentDeptCd(); } else {
					 * projectSn = org.getOrgCd(); List<WsPlasOrg> orgList =
					 * UaapCache.getUaapOrgsByType( "1", projectSn);
					 * hql.append(" and centerDeptSn in ('" + projectSn + "'");
					 * for (WsPlasOrg wsOrg : orgList) { String project =
					 * wsOrg.getOrgCd(); // hql.append(" or centerDeptSn =" +
					 * project); hql.append(",'" + project + "'"); }
					 * hql.append(" )"); }
					 */
					// hql.append(" and centerDeptSn =:projectSn");
					// params.put("projectSn", projectSn);
				} else {
					hql.append(" and remark like (:relatedUser)");
					params.put("relatedUser", "%," + curUser + ",%");
				}

			}
		} else {
			// 如果是管理员，则当指定搜索人时，可以搜索该人员的文件，否则，搜索所有人的记录
			if (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) {
				if (StringUtils.isNotBlank(searchByUser)) {
					hql.append(" and (currentUiid=:curId or receiver=:receiverId)");
					params.put("curId", searchByUser);
					params.put("receiverId", searchByUser);
				}
			} else {
				hql.append(" and (currentUiid=:curId or receiver=:receiverId)");
				params.put("curId", curUser);
				params.put("receiverId", curUser);
			}
		}
		/*
		 * if (SEARCH_STATUS_DEALT.equals(searchByStatus)) { //
		 * 如果是管理员，当指定搜索人时，可以搜索该人员所有已处理的文件，否则搜索所有人的记录 if
		 * (SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN")) { if
		 * (StringUtils.isNotBlank(searchByUser)) {
		 * hql.append(" and remark like (:relatedUser)");
		 * params.put("relatedUser", "%," + searchByUser + ",%"); } } else {
		 * hql.append(" and remark like (:relatedUser)");
		 * params.put("relatedUser", "%," + curUser + ",%"); } } else { //
		 * 如果是管理员，则当指定搜索人时，可以搜索该人员的文件，否则，搜索所有人的记录 if (isAdmin()) { if
		 * (StringUtils.isNotBlank(searchByUser)) {
		 * hql.append(" and (currentUiid=:curId or receiver=:receiverId)");
		 * params.put("curId", searchByUser); params.put("receiverId",
		 * searchByUser); } } //搜索出这个人所有经手过的文件，暂时去掉， else {
		 * hql.append(" and (currentUiid=:curId or receiver=:receiverId)");
		 * params.put("curId", curUser); params.put("receiverId", curUser); } }
		 */
		hql.append(" and deleteFlg=0 order by status asc, updatedDate desc");

		return oaFileFollowedManager.find(hql.toString(), params);
	}

	/**
	 * 附件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String attachMents() throws Exception {
		attachList = appAttachFileManager.getAttaFileByBizEntityId(bizEntityId);
		return "attachList";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = oaFileFollowedManager.getOaFileFollowed(getId());
		} else {
			entity = new OaFileFollowed();
		}
	}

	/**
	 * 新增一条文件跟踪记录
	 * 
	 * @throws Exception
	 */
	private void addNewFile() throws Exception {
		String curUiid = SpringSecurityUtils.getCurrentUiid();
		String curName = SpringSecurityUtils.getCurrentUserName();

		entity.setSerialNumberNum(new BigDecimal(oaFileFollowedManager.getFileSerialNumberNum(entity.getSerialNumber())));
		entity.setStatus(new BigDecimal("1"));
		entity.setCurrentUiid(curUiid);
		entity.setCenterDeptSn(SpringSecurityUtils.getCurrentDeptCd());
		// 插入新的中心CD
		String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
		if (curCenterCd == null || "".equals(curCenterCd)) {
			curCenterCd = SpringSecurityUtils.getCurrentDeptCd();
		}
		if (entity.getAcrossDeptCd() != null && !"".equals(entity.getAcrossDeptCd())
				&& entity.getAcrossDeptCd().indexOf(curCenterCd) < 0) {
			entity.setAcrossDeptCd(entity.getAcrossDeptCd() + curCenterCd + ",");
		} else if (entity.getAcrossDeptCd() == null && "".equals(entity.getAcrossDeptCd())) {
			entity.setAcrossDeptCd(curCenterCd + ",");
		}
		//
		entity.setRemark("," + curUiid + ",");
		if (StringUtils.isNotBlank(message)) {
			message = curName + "(" + getCurDate() + message;
			entity.setScheduleDescribe(message);
		}
		StringBuilder describe = new StringBuilder(SpringSecurityUtils.getCurrentUserName());
		describe.append("文件建立").append("(" + getCurDate() + ")");
		entity.setScheduleDescribe2(describe.toString());
		entity.setDeleteFlg("0");
		oaFileFollowedManager.saveOaFileFollowed(entity);

		// 如果是新增的话，则在保存后把新增时上传到文件跟保存后的文件跟踪记录关联在一起
		appAttachFileManager.updateTmpFile(entityTmpId, entity.getOaFileFollowId());
	}

	@Override
	public String save() throws Exception {
		if (StringUtils.isBlank(this.getId())) {
			addNewFile();
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 是否管理员
	 * 
	 * @return
	 */
	private boolean isAdmin() {
		return SpringSecurityUtils.hasRole("A_FILE_TRACK_ADMIN");
	}

	/**
	 * 保存文件名
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFileName() throws Exception {
		if (isAdmin()) {
			prepareEntity();
			if (StringUtils.isNotBlank(fileTrackingName)) {
				entity.setContent(fileTrackingName);
				oaFileFollowedManager.saveOaFileFollowed(entity);
				Struts2Utils.renderText("done");
			} else {
				Struts2Utils.renderText("fail");
			}
		} else {
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	/**
	 * 保存文件来源
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveProjSn() throws Exception {
		if (!isAdmin()) {
			Struts2Utils.renderText("fail");
			return null;
		}

		prepareEntity();
		if (StringUtils.isNotBlank(fileProjSn)) {
			entity.setProjectSn(fileProjSn);
			oaFileFollowedManager.saveOaFileFollowed(entity);
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 保存留言
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveComment() throws Exception {
		prepareEntity();
		StringBuilder m = new StringBuilder();
		String oriMessage = entity.getScheduleDescribe();
		String curName = SpringSecurityUtils.getCurrentUserName();
		if (StringUtils.isNotBlank(oriMessage)) {
			m.append(oriMessage + SPLITER);
		}
		m.append(curName + "(" + getCurDate() + "):" + this.getMessage());
		entity.setScheduleDescribe(m.toString());
		oaFileFollowedManager.saveOaFileFollowed(entity);
		Struts2Utils.renderHtml(entity.getScheduleDescribe());

		return null;
	}

	/**
	 * 发送到下一个人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendToNext() throws Exception {
		prepareEntity();

		if (StringUtils.isNotBlank(receiverId)) {
			entity.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_SENT));
			String curId = entity.getCurrentUiid();
			if(receiverId.indexOf(";")>=0){
				setReceiverId(receiverId.replace(";", ""));
			}
			entity.setReceiver(receiverId);

			StringBuilder desc = new StringBuilder(entity.getScheduleDescribe2());
			desc.append("<BR>" + CodeNameUtil.getUserNameByCd(curId)).append("将文件送到")
					.append(CodeNameUtil.getUserNameByCd(receiverId)).append("(" + getCurDate() + ")");
			entity.setScheduleDescribe2(desc.toString());
			// 插入新的中心CD
			String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			if (curCenterCd == null || "".equals(curCenterCd)) {
				curCenterCd = SpringSecurityUtils.getCurrentDeptCd();
			}
			if (entity.getAcrossDeptCd() != null && !"".equals(entity.getAcrossDeptCd())
					&& entity.getAcrossDeptCd().indexOf(curCenterCd) < 0) {
				entity.setAcrossDeptCd(entity.getAcrossDeptCd() + curCenterCd + ",");
			} else if (entity.getAcrossDeptCd() == null || "".equals(entity.getAcrossDeptCd())) {
				entity.setAcrossDeptCd(curCenterCd + ",");
			}
			//
			String rm = entity.getRemark();
			if (StringUtils.isNotBlank(rm)) {
				if (rm.indexOf("," + receiverId + ",") == -1) {
					entity.setRemark(rm + receiverId + ",");
				}
			} else {
				entity.setRemark("," + receiverId + ",");

			}
			oaFileFollowedManager.saveOaFileFollowed(entity);
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 批量发送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchSend() throws Exception {
		if (oaFileFollowedManager.batchSend(batchData)) {
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 批量确认
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchConfirm() throws Exception {
		if (oaFileFollowedManager.batchConfirm(batchData)) {
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 确认接收文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String receiveFile() throws Exception {
		prepareEntity();
		String reveiver = entity.getReceiver();
		// 只有当前人跟接收人是同一个人时才能接收
		if (SpringSecurityUtils.getCurrentUiid().equalsIgnoreCase(reveiver)) {
			StringBuilder desc = new StringBuilder(entity.getScheduleDescribe2());
			desc.append("<BR>" + CodeNameUtil.getUserNameByCd(reveiver) + "文件收到").append("(" + getCurDate() + ")");
			entity.setScheduleDescribe2(desc.toString());
			entity.setCurrentUiid(reveiver);
			entity.setReceiver(null);
			entity.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_CONFIRMED));
			// 插入新的中心CD
			String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			if (curCenterCd == null || "".equals(curCenterCd)) {
				curCenterCd = SpringSecurityUtils.getCurrentDeptCd();
			}
			if (entity.getAcrossDeptCd() != null && !"".equals(entity.getAcrossDeptCd())
					&& entity.getAcrossDeptCd().indexOf(curCenterCd) < 0) {
				entity.setAcrossDeptCd(entity.getAcrossDeptCd() + curCenterCd + ",");
			} else if (entity.getAcrossDeptCd() == null && "".equals(entity.getAcrossDeptCd())) {
				entity.setAcrossDeptCd(curCenterCd + ",");
			}
			//
			oaFileFollowedManager.saveOaFileFollowed(entity);
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 退回文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendBackFile() throws Exception {
		prepareEntity();

		StringBuilder desc = new StringBuilder(entity.getScheduleDescribe2());
		String receiver = entity.getReceiver();
		if (StringUtils.isNotBlank(receiver)) {
			if (isAdmin() && !SpringSecurityUtils.getCurrentUiid().equalsIgnoreCase(receiver)) {
				desc.append("<BR>管理员把文件退回(" + getCurDate() + ")");
			} else {
				desc.append("<BR>" + CodeNameUtil.getUserNameByCd(receiver) + "把文件退回(" + getCurDate() + ")");
			}
			entity.setScheduleDescribe2(desc.toString());
			entity.setReceiver(null);
			entity.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_CONFIRMED));
			oaFileFollowedManager.saveOaFileFollowed(entity);
			Struts2Utils.renderText("done");
		}

		return null;
	}

	/**
	 * 完成文件流转
	 * 
	 * @return
	 * @throws Exception
	 */
	public String completeFile() throws Exception {
		prepareEntity();

		StringBuilder desc = new StringBuilder(entity.getScheduleDescribe2());
		String curName = SpringSecurityUtils.getCurrentUserName();
		desc.append("<BR>" + CodeNameUtil.getUserNameByCd(curName) + "文件流程完成").append("(" + getCurDate() + ")");
		entity.setScheduleDescribe2(desc.toString());
		entity.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_COMPLETED));
		entity.setCurrentUiid(SpringSecurityUtils.getCurrentUiid());
		entity.setReceiver(null);
		oaFileFollowedManager.saveOaFileFollowed(entity);
		Struts2Utils.renderText("done");

		return null;
	}

	/**
	 * 继续文件流转
	 * 
	 * @return
	 * @throws Exception
	 */
	public String continueFile() throws Exception {
		if (!isAdmin()) {
			Struts2Utils.renderText("fail");
			return null;
		}

		prepareEntity();

		StringBuilder desc = new StringBuilder(entity.getScheduleDescribe2());
		desc.append("<BR>管理员将文件恢复至" + CodeNameUtil.getUserNameByCd(entity.getCurrentUiid()) + "继续流转状态").append(
				"(" + getCurDate() + ")");
		entity.setScheduleDescribe2(desc.toString());
		entity.setStatus(new BigDecimal(OaFileFollowedManager.STATUS_CONFIRMED));
		entity.setReceiver(null);
		oaFileFollowedManager.saveOaFileFollowed(entity);
		Struts2Utils.renderText("done");

		return null;
	}

	/**
	 * 构造项目列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapProjectList() {
		return mapProjectList;
	}

	/**
	 * 构造机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buildOrgs() throws Exception {
		Struts2Utils.renderText(OrgTreeUtil.buildNoCheckOrgTreeJSON(PlasCache.getOrgEnableList()));
		return null;
	}

	/**
	 * 构造项目列表
	 * 
	 * @return
	 */
	private Map<String, String> buildProjSelMap() {
		List<WsPlasOrg> orgList = PlasCache.getPhysicalDirectOrgListByOrgCd("135");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "--选择--");
		map.put("-1", "其他");
		for (WsPlasOrg org : orgList) {
			String orgName = org.getOrgName();
			String orgValue = org.getOrgCd();
			map.put(orgValue, orgName);
		}
		return map;
	}

	/**
	 * 删除文件跟踪记录
	 */
	@Override
	public String delete() throws Exception {
		prepareEntity();

		entity = oaFileFollowedManager.getOaFileFollowed(getId());
		BigDecimal status = entity.getStatus();
		// 1. 如果是完成状态，则不能删除
		// 2. 如果是新建状态，则管理员和创建人都可以删除，而且是物理删除
		// 3. 如果是其他状态，则只有管理员可以删除，此时是逻辑删除
		if (status != null) {
			switch (status.shortValue()) {
			case OaFileFollowedManager.STATUS_NEW:
				if (isAdmin() || SpringSecurityUtils.getCurrentUiid().equals(entity.getCreator())) {
					oaFileFollowedManager.delete(entity);
				}
				break;
			case OaFileFollowedManager.STATUS_COMPLETED:
				return null;
			default:
				if (isAdmin()) {
					entity.setDeleteFlg("1");
					oaFileFollowedManager.saveOaFileFollowed(entity);
				}
				break;
			}
		}
		Struts2Utils.renderText("done");
		return null;
	}

	/**
	 * 批量删除文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchDelete() throws Exception {
		if (oaFileFollowedManager.batchDelete(batchData)) {
			Struts2Utils.renderText("done");
		}
		return null;
	}

	/**
	 * 把当前时间以MM-dd HH:mm的格式返回
	 * 
	 * @return
	 */
	private String getCurDate() {
		return DateOperator.formatDate(new Date(), "MM-dd HH:mm");
	}

	/**
	 * 获取最近的状态
	 * 
	 * @param statusDesc
	 * @return
	 */
	private String getLatestStatus(String statusDesc) {
		String latestStatus = "";
		if (StringUtils.isNotBlank(statusDesc)) {
			String[] stats = statusDesc.split(SPLITER);
			latestStatus = stats[stats.length - 1];
		}
		return latestStatus;
	}

	public String updateRemark() throws Exception {
		oaFileFollowedManager.updateRemark();
		return null;
	}

	public String updatePersonRemark() {
		oaFileFollowedManager.updatePersonRemark(curUser, fileProjSn);
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	public OaFileFollowed getModel() {
		return entity;
	}

	private void prepareEntity() {
		entity = oaFileFollowedManager.getOaFileFollowed(getId());
	}

	public String getBizModuleCd() {
		return bizModuleCd;
	}

	public void setBizModuleCd(String bizModuleCd) {
		this.bizModuleCd = bizModuleCd;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}

	public List<AppAttachFile> getAttachList() {
		return attachList;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public Map<String, String> getMapToProjectNames() {
		return mapToProjectNames;
	}

	public void setMapToProjectNames(Map<String, String> mapToProjectNames) {
		this.mapToProjectNames = mapToProjectNames;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Map<String, String> getMapToCenterNames() {
		return mapToCenterNames;
	}

	public void setMapToCenterNames(Map<String, String> mapToCenterNames) {
		this.mapToCenterNames = mapToCenterNames;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getMapLatestStatus() {
		return mapLatestStatus;
	}

	public void setMapLatestStatus(Map<String, String> mapLatestStatus) {
		this.mapLatestStatus = mapLatestStatus;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	@Override
	public Page<OaFileFollowed> getPage() {
		return page;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public List<OaFileFollowedVO> getFileResults() {
		return fileResults;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	public String getSearchByStatus() {
		return searchByStatus;
	}

	public void setSearchByStatus(String searchByStatus) {
		this.searchByStatus = searchByStatus;
	}

	public String getSearchByProjCd() {
		return searchByProjCd;
	}

	public void setSearchByProjCd(String searchByProjCd) {
		this.searchByProjCd = searchByProjCd;
	}

	public String getSearchByDeptCd() {
		return searchByDeptCd;
	}

	public void setSearchByDeptCd(String searchByDeptCd) {
		this.searchByDeptCd = searchByDeptCd;
	}

	public String getSearchBySerialNum() {
		return searchBySerialNum;
	}

	public void setSearchBySerialNum(String searchBySerialNum) {
		this.searchBySerialNum = searchBySerialNum;
	}

	public String getSearchByContent() {
		return searchByContent;
	}

	public void setSearchByContent(String searchByContent) {
		this.searchByContent = searchByContent;
	}

	public String getSearchByUser() {
		return searchByUser;
	}

	public void setSearchByUser(String searchByUser) {
		this.searchByUser = searchByUser;
	}

	public String getSearchByGECreateDate() {
		return searchByGECreateDate;
	}

	public void setSearchByGECreateDate(String searchByGECreateDate) {
		this.searchByGECreateDate = searchByGECreateDate;
	}

	public String getSearchByLECreateDate() {
		return searchByLECreateDate;
	}

	public void setSearchByLECreateDate(String searchByLECreateDate) {
		this.searchByLECreateDate = searchByLECreateDate;
	}

	public void setFileTrackingName(String fileTrackingName) {
		this.fileTrackingName = fileTrackingName;
	}

	public String getFileTrackingName() {
		return fileTrackingName;
	}

	public String getFileProjSn() {
		return fileProjSn;
	}

	public void setFileProjSn(String fileProjSn) {
		this.fileProjSn = fileProjSn;
	}

	public String getBatchData() {
		return batchData;
	}

	public void setBatchData(String batchData) {
		this.batchData = batchData;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectSn() {
		return projectSn;
	}

	public void setProjectSn(String projectSn) {
		this.projectSn = projectSn;
	}

	public Map<String, Boolean> getMapIfHasAttach() {
		return mapIfHasAttach;
	}

	public void setMapIfHasAttach(Map<String, Boolean> mapIfHasAttach) {
		this.mapIfHasAttach = mapIfHasAttach;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getCurrentCd() {
		return currentCd;
	}

	public void setCurrentCd(String currentCd) {
		this.currentCd = currentCd;
	}

	public String getCurrentId() {
		return currentId;
	}

	public boolean isIf_in_attention() {
		return if_in_attention;
	}

	public void setIf_in_attention(boolean if_in_attention) {
		this.if_in_attention = if_in_attention;
	}

	public Map<String, String> getAttentionMap() {
		return attentionMap;
	}

	public void setAttentionMap(Map<String, String> attentionMap) {
		this.attentionMap = attentionMap;
	}

	public String getIsAttentioned() {
		return isAttentioned;
	}

	public void setIsAttentioned(String isAttentioned) {
		this.isAttentioned = isAttentioned;
	}

	public Map<String, String> getAttentionMapUnread() {
		return attentionMapUnread;
	}

	public void setAttentionMapUnread(Map<String, String> attentionMapUnread) {
		this.attentionMapUnread = attentionMapUnread;
	}

	public String getSearchByDeptName() {
		return searchByDeptName;
	}

	public void setSearchByDeptName(String searchByDeptName) {
		this.searchByDeptName = searchByDeptName;
	}
}