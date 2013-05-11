/**
 * 
 */
package com.hhz.ump.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.bis.BisContHisManager;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.dao.bis.BisFactManager;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisMustManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisShopConnManager;
import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisStoreManager;
import com.hhz.ump.dao.bis.BisTenantManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisContHis;
import com.hhz.ump.entity.bis.BisFact;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.JSONUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.vo.BisContExportVo;
import com.hhz.ump.web.vo.BisMustAutoVo;
import com.hhz.ump.web.vo.VoCont;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * @author baolm 2011-6-13
 */
@Namespace("/bis")
@Results( {
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "exportFile", "contentDisposition",
				"attachment;filename=${exportFileName}.xls" }),
		@Result(name = "exportContent", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "contentExcel",
				"contentDisposition", "attachment;filename=${contentExcelName}.xls" }),
		@Result(name = CrudActionSupport.RELOAD, location = "bis-cont!input.action", type = "redirect", params = { "id", "${id}" }) })
public class BisContAction extends CrudActionSupport<BisCont> {

	private static final long serialVersionUID = 4418027004630062156L;

	private BisCont entity;

	private BisCont compareCont1;
	private BisCont compareCont2;

	private String filter_LIKES_contName;
	private String filter_LIKES_contNo;
	private String filter_EQ_contBigTypeCd;
	private String filter_EQ_contSmallTypeCd;
	private String filter_GED_contStartDate;
	private String filter_LTD_contStartDate;
	private String filter_GED_contEndDate;
	private String filter_LTD_contEndDate;
	private String filter_EQ_statusCd;
	private String filter_LIKES_storeNo;
	private String filter_EQ_manageCd;
	private String filter_EQ_mustStatus;
	private String filter_EQ_mustDate;

	private String contNo;
	private String contName;

	private String statusCd;

	private String entityTmpId;

	private String bisProjectId; // 项目
	private String bisProjectName;

	private String bisFloorId; // 楼层

	private String bisTenantId; // 租户ID

	private String bisStoreIds; // 商铺编号
	private String bisStoreNos;

	private String bisFlatIds; // 公寓编号
	private String bisFlatNos;

	private String bisMultiIds; // 多经编号
	private String bisMultiNos;

	private String bisShopId; // 商家
	private String bisShopName;
	private String bisShopConnId; // 经销商
	private String bisShopConnName;

	private String contBigTypeCd; // 合同大类
	private String contSmallTypeCd; // 合同小类

	private String contStartDate;
	private String contEndDate;
	private Date failDate;

	private String passedDate;
	private String remainDate;

	private String rentYear;

	private boolean changeBl;

	private String[] chkIds; // 选中的应收款ID

	private String seleChargeType; // 选中的费用种类

	private BigDecimal totalSquare;//面积合计
	
	private BigDecimal rentSquareTotal;//实际收费面积
	
	private BigDecimal innerSquareTotal; //套内面积

	private BigDecimal showSquareTotal; //计租面积
	
	private BigDecimal showSquare;

	private boolean tenantActiveBl = true; // 是否已退铺

	private List<BisMust> mustList = new ArrayList<BisMust>(); // 应收款列表

	private List<BisCont> tenantConts = new ArrayList<BisCont>(); // 租户相关合同

	private Map<String, String> mapBisProject = new LinkedHashMap<String, String>();

	private Map<String, String> mapContBigType = new LinkedHashMap<String, String>();

	private Map<String, String> mapContSmallType = new LinkedHashMap<String, String>();

	private Map<String, String> mapChargeType = new LinkedHashMap<String, String>();

	private Map<String, String> mapBisShopConn = new LinkedHashMap<String, String>();

	private List<BisContHis> bisContHisList = new ArrayList<BisContHis>();

	// private String projPermSwitch = "off"; //TODO DEL 项目权限开关(测试时设为off)
	private boolean projAdmin; // 项目权限
	// private boolean operator; //操作权限(新增、保存、提交、更改)
	// private boolean contApprover; //合同审核权限
	// private boolean mustApprover; //应收款审核权限
	// private boolean contDelete; //合同删除权限
	// private boolean mustDelete; //应收款删除权限
	//
	// private boolean storeOperator;
	// private boolean flatOperator;
	// private boolean multiOperator;

	private InputStream exportFile;
	private String exportFileName;

	private InputStream contentExcel;
	private String contentExcelName;

	private File importFile;

	@Autowired
	private BisContManager bisContManager;

	@Autowired
	private BisProjectManager bisProjectManager;

	@Autowired
	private BisShopManager bisShopManager;

	@Autowired
	private BisShopConnManager bisShopConnManager;

	@Autowired
	private BisTenantManager bisTenantManager;

	@Autowired
	private BisMustManager bisMustManager;

	@Autowired
	private BisFactManager bisFactManager;

	@Autowired
	private BisStoreManager bisStoreManager;

	@Autowired
	private BisFlatManager bisFlatManager;

	@Autowired
	private BisContHisManager bisContHisManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Override
	public BisCont getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() throws Exception {

		Struts2Utils.getRequest().setAttribute("isProjectBusinessCompany", bisProjectManager.getCurrProject() != null);

		buildPermission();
		if (StringUtils.isBlank(bisProjectId)) {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if (bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		} else {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}

		if (!StringUtils.equals(SpringSecurityUtils.getCurrentUiid(), "baolm")) {
			if (StringUtils.isBlank(filter_EQ_statusCd)) {
				filter_EQ_statusCd = "10";
			}
		}

		// if(StringUtils.isBlank(filter_EQ_contBigTypeCd)) {
		// contBigTypeCd = "1";
		// }
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {

		page.setPageSize(10);

		Map<String, Object> param = new HashMap<String, Object>();
		String hql = buildSearchHql(param);

		page = bisContManager.findPage(page, hql, param);

		hql = "select sum(bc.square),sum(bc.innerSquare),sum(bc.rentSquare),sum(bc.square)" + hql;
		@SuppressWarnings("rawtypes")
		List<Object[]> list = bisContManager.getDao().find(hql, param);
		if (!list.isEmpty()) {
			Object[] obj = list.get(0);
			totalSquare = (BigDecimal) obj[0]; //建筑面积
			innerSquareTotal = (BigDecimal) obj[1]; //套内面积
			rentSquareTotal = (BigDecimal) obj[2]; //计租面积
			showSquareTotal = (BigDecimal) obj[3]; //实际收费面积
			
		}

		return "list";
	}

	public String loadContList() {

		Map<String, Object> param = new HashMap<String, Object>();
		String hql = buildSearchHql(param);
		tenantConts = bisContManager.find(hql, param);
		return "contList";
	}

	/**
	 * 构建搜索HQL
	 */
	private String buildSearchHql(Map<String, Object> param) {

		// boolean searchPermission = false; //一级搜索权限
		// if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_OPER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_ADVE)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FINA)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_FINA)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_OPER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_OP_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_ADVE)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_AD_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_AD_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_FINA)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_FI_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_FI_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_MANAGER)
		// || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_SYSTEM_ADMIN)) {
		// searchPermission = true;
		// }
		boolean notSearchStatus = false; // 是否不搜索近期合同状态。如果搜索了商铺或者商家，就自动不搜索近期合同状态

		StringBuffer hql = new StringBuffer("from BisCont bc where 1=1 ");

		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bc.bisProjectId=:bisProjectId ");
			param.put("bisProjectId", bisProjectId);
		}
		if (StringUtils.isNotBlank(bisShopId)) {
			hql.append(" and exists (select 1 from BisTenant bt where bc.bisTenantId=bt.bisTenantId and btbisShopId=:bisShopId) ");
			param.put("bisShopId", bisShopId);
		}

		if (StringUtils.isNotBlank(bisShopName)) {
			hql.append(" and ( ");
			hql.append(" upper(bc.owner) like :bisShopName ");
			hql
					.append(" or bisTenantId in ( select bt.bisTenantId from BisTenant bt , BisShop bs where bt.bisShopId=bs.bisShopId  and (upper(bs.nameCn) like :bisShopName or upper(bs.nameEn) like :bisShopName) ) ");
			hql.append(" or bisContId in( select bb.bisCont.bisContId from BisContShopBack  bb where upper(bb.owner) like :bisShopName ) ");
			hql.append(" or bisContId in( select bp.bisCont.bisContId from BisContFlatProp  bp where upper(bp.owner) like :bisShopName ) ");
			hql
					.append(" or bisContId in( select rel.bisCont.bisContId from BisMultiContRel  rel where exists (select 1 from BisMulti bm  where bm.bisMultiId=rel.bisMulti.bisMultiId and bm.bisProjectId=:bisProjectId and upper(bm.multiName) like :bisShopName ) ) ");
			hql.append(" ) ");
			param.put("bisProjectId", bisProjectId);
			param.put("bisShopName", "%" + bisShopName.toUpperCase() + "%");
			/*
			 * hql.append(" and ((bisTenantId in (")
			 * .append(" select bt.bisTenantId from BisTenant bt, BisShop bs ")
			 * .append(
			 * " where bt.bisShopId=bs.bisShopId and (upper(bs.nameCn) like :bisShopName or upper(bs.nameEn) like :bisShopName) )"
			 * ) .append(" or bisTenantId in (").append(
			 * " select bt.bisTenantId from BisTenant bt where upper(bt.owner) like :bisShopName ) )"
			 * ); param.put("bisShopName", "%"+bisShopName.toUpperCase()+"%");
			 */
			notSearchStatus = true;
		}
		if (StringUtils.isNotBlank(bisStoreIds)) {
			hql
					.append(" and exists (select 1 from BisStoreContRel scr where bc.bisContId=scr.bisCont.bisContId and scr.bisStore.bisStoreId in (:bisStoreIds)) ");
			param.put("bisStoreIds", bisStoreIds.split(","));
		}
		if (StringUtils.isNotBlank(filter_LIKES_storeNo)) {
			hql
					.append(" and exists (select 1 from BisStoreContRel scr where bc.bisContId=scr.bisCont.bisContId and upper(scr.bisStore.storeNo) like :storeNo) ");
			param.put("storeNo", "%" + filter_LIKES_storeNo.toUpperCase() + "%");
			notSearchStatus = true;
		}
		if (StringUtils.isNotBlank(filter_LIKES_contName)) {
			hql.append(" and bc.contName like :contName");
			param.put("contName", "%" + filter_LIKES_contName + "%");
		}
		if (StringUtils.isNotBlank(filter_LIKES_contNo)) {
			hql.append(" and bc.contNo like :contNo");
			param.put("contNo", "%" + filter_LIKES_contNo + "%");
		}
		if (StringUtils.isNotBlank(filter_GED_contStartDate)) {
			hql.append(" and bc.contStartDate >= :contStartDateFrom");
			param.put("contStartDateFrom", DateOperator.parse(filter_GED_contStartDate, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(filter_LTD_contStartDate)) {
			hql.append(" and bc.contStartDate < :contStartDateTo");
			param.put("contStartDateTo", DateOperator.parse(filter_LTD_contStartDate, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(filter_GED_contEndDate)) {
			hql.append(" and bc.contEndDate >= :contEndDateFrom");
			param.put("contEndDateFrom", DateOperator.parse(filter_GED_contEndDate, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(filter_LTD_contEndDate)) {
			hql.append(" and bc.contEndDate < :contEndDateTo");
			param.put("contEndDateTo", DateOperator.parse(filter_LTD_contEndDate, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(contBigTypeCd)) {
			hql.append(" and bc.contBigTypeCd=:contBigTypeCd ");
			param.put("contBigTypeCd", contBigTypeCd);
		}
		if (StringUtils.isNotBlank(contSmallTypeCd)) {
			hql.append(" and bc.contSmallTypeCd=:contSmallTypeCd ");
			param.put("contSmallTypeCd", contSmallTypeCd);
		}
		if (StringUtils.isNotBlank(filter_EQ_statusCd)) {
			if (!"9".equals(filter_EQ_statusCd)) {
				hql.append(" and bc.activeBl='1'");
			}
			if ("8".equals(filter_EQ_statusCd)) {
				hql.append(" and bc.updateBl='1'");
			} else if ("9".equals(filter_EQ_statusCd)) {
				hql.append(" and bc.activeBl='0'");
			} else if ("10".equals(filter_EQ_statusCd)) {
				// 近期合同，往前推半年
				if (!notSearchStatus) {
					hql.append(" and add_months(bc.contEndDate,-6) < sysdate").append(
							" and bc.contEndDate >= to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') ");
				}
			} else {
				hql.append(" and bc.statusCd = :statusCd");
				param.put("statusCd", filter_EQ_statusCd);
			}
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			if (BisConstants.STORE.equals(contBigTypeCd)) {
				hql
						.append(" and exists (select 1 from BisStoreContRel scr join scr.bisStore bs where bc.bisContId=scr.bisCont.bisContId and bs.bisFloor.bisFloorId = :bisFloorId) ");
			} else if (BisConstants.FLAT.equals(contBigTypeCd)) {
				hql
						.append(" and exists (select 1 from BisFlatContRel fcr join fcr.bisFlat bf where bc.bisContId=fcr.bisCont.bisContId and bf.bisFloor.bisFloorId = :bisFloorId) ");
			} else {

			}
			param.put("bisFloorId", bisFloorId);
		}
		if (StringUtils.isNotBlank(filter_EQ_manageCd)) {
			hql.append(" and bc.manageCd=:manageCd ");
			param.put("manageCd", filter_EQ_manageCd);
		}
		if (StringUtils.isNotBlank(filter_EQ_mustStatus)) {
			if ("0".equals(filter_EQ_mustStatus)) {
				hql.append(" and not exists ");
			} else {
				hql.append(" and exists");
			}
			hql.append(" ( select 1 from BisMust bm where bm.bisCont.bisContId=bc.bisContId and bm.typeCd=:mustType");
			param.put("mustType", "0");
			// if(!"-1".equals(filter_EQ_mustStatus) &&
			// !"0".equals(filter_EQ_mustStatus)) {
			// hql.append(" and bm.statusCd=:mustStatus");
			// param.put("mustStatus", filter_EQ_mustStatus);
			// }
			if (StringUtils.isNotBlank(filter_EQ_mustDate)) {
				hql.append(" and bm.mustYear=:mustYear and bm.mustMonth=:mustMonth");
				param.put("mustYear", filter_EQ_mustDate.substring(0, 4));
				param.put("mustMonth", filter_EQ_mustDate.substring(5, 7).replaceAll("0", ""));
			}
			hql.append(")");
		}
		if (SpringSecurityUtils.hasRole(GlobalConstants.A_CONT_QUERY_PERS) && !SpringSecurityUtils.hasRole(GlobalConstants.A_CONT_QUERY_ALL)) {
			// if(!searchPermission) {
			hql.append(" and (bc.creator=:currUser or bc.updator=:currUser or bc.checkUserCd=:currUser)");
			param.put("currUser", SpringSecurityUtils.getCurrentUiid());
		}
		if ("10".equals(filter_EQ_statusCd)) {
			hql.append(" order by bc.contEndDate");
		} else {
			hql.append(" order by bc.updatedDate desc");
		}

		return hql.toString();
	}

	@Override
	public String input() throws Exception {

		buildPermission();

		return INPUT;
	}

	@Override
	public String save() throws Exception {
		String text = "";
		try {
			text = "1&" + bisContManager.save(entity, entityTmpId, bisShopId, bisShopConnId, bisStoreIds, bisFlatIds, bisMultiIds);
		} catch (Exception e) {
			// e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				text = "0&保存失败：<br>" + e.getMessage();
				String[] error = e.getMessage().split(":");
				if ("conflict".equals(error[0])) {
					text = "2&" + error[1];
				}
			} else
				throw new RuntimeException(e);
		}
		Struts2Utils.renderText(text);
		return null;
	}

	public String submit() throws Exception {
		String text = "";
		try {
			if (StringUtils.isEmpty(entity.getStatusCd()) || BisConstants.STATUS_ADD.equals(entity.getStatusCd())
					|| BisConstants.STATUS_BACK.equals(entity.getStatusCd())) {
				entity.setStatusCd(BisConstants.STATUS_SUBMIT);
				text = "1&" + bisContManager.save(entity, entityTmpId, bisShopId, bisShopConnId, bisStoreIds, bisFlatIds, bisMultiIds);
			} else {
				text = "1&" + bisContManager.submitContChange(entity, bisStoreIds, bisFlatIds, bisMultiIds);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				// logger.error(e.getMessage(), e);
				text = "0&保存失败：<br>" + e.getMessage();
				String[] error = e.getMessage().split(":");
				if ("conflict".equals(error[0])) {
					text = "2&" + error[1];
				}
			} else
				throw new RuntimeException(e);
		}
		Struts2Utils.renderText(text);
		return null;
	}

	public void prepareSubmit() throws Exception {

		prepareModel();
	}

	public String saveMust() throws Exception {

		String text = "";
		try {

			bisContManager.saveMust(entity.getBisContId(), entity.getBisMusts(), seleChargeType);

			StringBuffer msg = new StringBuffer("1&");
			msg.append(appDictTypeManager.getDictNameByCd(DictContants.BIS_CHARGE_TYPE_STORE, seleChargeType));

			String totalHql = "from BisMust where bisCont.bisContId=? and chargeTypeCd=? and typeCd='0'";
			long total = bisContManager.countHqlResult(totalHql, entity.getBisContId(), seleChargeType);
			if (total > 0) {
				String approveHql = "from BisMust where bisCont.bisContId=? and chargeTypeCd=? and statusCd=2 and typeCd='0'";
				long approve = bisContManager.countHqlResult(approveHql, entity.getBisContId(), seleChargeType);
				msg.append(" ").append(approve).append("/").append(total);
			}
			text = msg.toString();
		} catch (Exception e) {
			// e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				text = "0&保存失败：<br>" + e.getMessage();
			} else
				throw new RuntimeException(e);
		}
		Struts2Utils.renderText(text);
		return null;
	}

	public void prepareSaveMust() throws Exception {

		prepareModel();
	}

	@Override
	public String delete() throws Exception {
		bisContManager.deleteBisCont(getId());
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

		String resId = Struts2Utils.getParameter("resId");
		if (StringUtils.isNotBlank(getId())) {
			entity = bisContManager.getEntity(getId());
			contBigTypeCd = entity.getContBigTypeCd();
			contSmallTypeCd = entity.getContSmallTypeCd();

			initMapChargeType(entity);

			setShopIdName(entity.getBisTenantId());

			setStoreFlatIdNos(entity.getBisContId(), entity.getContBigTypeCd());

			if (BisConstants.STORE.equals(contBigTypeCd)) {
				setSquare();
			}

			calculateDays(entity.getContStartDate(), entity.getContEndDate());

			bisProjectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();

		} else if (StringUtils.isNotBlank(resId)) {

			entity = bisContManager.getByResId(resId);
			if (entity == null)
				throw new RuntimeException("未找到相关合同");

			contBigTypeCd = entity.getContBigTypeCd();
			contSmallTypeCd = entity.getContSmallTypeCd();

			initMapChargeType(entity);

			setShopIdName(entity.getBisTenantId());

			setStoreFlatIdNos(entity.getBisContId(), entity.getContBigTypeCd());

			calculateDays(entity.getContStartDate(), entity.getContEndDate());

			bisProjectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();

		} else {
			entity = new BisCont();
			entityTmpId = "bisCont_" + RandomUtils.generateTmpEntityId();
			entity.setContApprovalId("bisContApproval_" + RandomUtils.generateTmpEntityId());

			if (StringUtils.isBlank(bisProjectId)) {
				BisProject bisProject = bisProjectManager.getCurrProject();
				if (bisProject != null) {
					bisProjectId = bisProject.getBisProjectId();
					bisProjectName = bisProject.getProjectName();
				}
			} else {
				bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
			}
		}

	}

	public String invalid() throws Exception {

		try {
			BisCont bisCont = bisContManager.getEntity(getId());
			bisCont.setActiveBl(false);
			bisCont.setInvalidBy("1");
			bisCont.setContToFailDate(failDate);
			bisContManager.saveBisCont(bisCont);
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("fail," + e.getMessage());
		}

		return null;
	}

	public String cancelInvalid() throws Exception {

		try {
			BisCont bisCont = bisContManager.getEntity(getId());
			bisCont.setActiveBl(true);
			bisCont.setContToFailDate(null);
			bisContManager.saveBisCont(bisCont);
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("fail," + e.getMessage());
		}

		return null;
	}

	public String invalidAuto() throws Exception {

		bisContManager.runInvalid();
		return null;
	}

	public String deleteMust() throws Exception {
		// 在批量删除前，取得该应收的实收数据，将实收的应收TOTAL值置为null
		if (chkIds != null) {
			BisMust bisMust = new BisMust();
			List<BisFact> factList = new ArrayList<BisFact>();
			for (int i = 0; i < chkIds.length; i++) {
				if (!"on".equals(chkIds[i])) {
					bisMust = bisMustManager.getEntity(chkIds[i]);
					List<BisFact> bisFactList = bisFactManager.getFact(bisMust.getBisProjectId(), bisMust.getBisTenantId(), null, null, bisMust
							.getChargeTypeCd(), bisMust.getMustYear(), bisMust.getMustMonth(), bisMust.getBisCont().getBisContId());
					for (BisFact bisFact : bisFactList) {
						bisFact.setTotalMustMoney(null);
						factList.add(bisFact);
					}
				}
			}
			if (factList != null && factList.size() > 0) {
				bisFactManager.batchExecute(factList);
			}
		}
		bisMustManager.batchDelete(chkIds);
		return null;
	}

	/**
	 * 初始化费用类别列表
	 */
	private void initMapChargeType(BisCont bisCont) {
		// 如果是返租合同，则没有费用类别————这条现删除
		/*
		 * if (StringUtils.equals(BisConstants.BACK,
		 * bisCont.getContSmallTypeCd())) return;
		 */

		// List<String> contChargeType =
		// bisContManager.getContChargeType(bisCont.getBisContId());
		// if (contChargeType.isEmpty()) {
		mapChargeType = getDefaultChargeTypeMap(bisCont.getContBigTypeCd(), bisCont.getContSmallTypeCd());
		/*
		 * if(BisConstants.STORE.equals(bisCont.getContBigTypeCd())) {
		 * 
		 * List<String> typeInUsed =
		 * bisContManager.getChargeTypeInUsed(bisCont); for (String typeCd :
		 * typeInUsed) { mapChargeType.remove(typeCd); } }
		 */

		/*
		 * } else { for (String chargeTypeCd : contChargeType) { String name =
		 * appDictTypeManager.getDictNameByCd(DictContants.BIS_CHARGE_TYPE,
		 * chargeTypeCd); mapChargeType.put(chargeTypeCd, name); } }
		 */
		// if (StringUtils.isNotBlank(bisCont.getChargerTypeArr())) {
		// String[] types = bisCont.getChargerTypeArr().split(",");
		// for (int i = 0; i < types.length; i++) {
		// if (StringUtils.isBlank(types[i])) {
		// continue;
		// }
		// String name =
		// appDictTypeManager.getDictNameByCd(DictContants.BIS_CHARGE_TYPE_STORE,
		// types[i]);
		// mapChargeType.put(types[i], name);
		// }
		// } else {
		// mapChargeType = getDefaultChargeTypeMap(bisCont.getContBigTypeCd(),
		// bisCont.getContSmallTypeCd());
		// if (StringUtils.equals(BisConstants.STORE,
		// bisCont.getContBigTypeCd())
		// && !StringUtils.equals(BisConstants.BACK,
		// bisCont.getContSmallTypeCd())
		// && !StringUtils.equals(BisConstants.OWNER_PROP,
		// bisCont.getContSmallTypeCd())) {
		// bisContManager.removeTypeInUsed(mapChargeType, bisCont);
		// }
		// }

		String approveHql = "from BisMust where bisCont.bisContId=? and chargeTypeCd=? and statusCd=2";
		String totalHql = "from BisMust where bisCont.bisContId=? and chargeTypeCd=?";
		for (Iterator<Entry<String, String>> iter = mapChargeType.entrySet().iterator(); iter.hasNext();) {
			Entry<String, String> e = iter.next();
			long total = bisContManager.countHqlResult(totalHql, bisCont.getBisContId(), e.getKey());
			long approve = bisContManager.countHqlResult(approveHql, bisCont.getBisContId(), e.getKey());
			if (total > 0) {
				mapChargeType.put(e.getKey(), new StringBuilder(e.getValue()).append(" ").append(approve).append("/").append(total).toString());
			}
		}

		if (StringUtils.isBlank(seleChargeType) && !mapChargeType.isEmpty()) {
			seleChargeType = mapChargeType.entrySet().iterator().next().getKey();
		}

	}

	/**
	 * 设置商家、供销商
	 */
	private void setShopIdName(String bisTenantId) {
		if (StringUtils.isNotBlank(bisTenantId)) {
			BisTenant bisTenant = bisTenantManager.getEntity(bisTenantId);
			tenantActiveBl = bisTenant.getActiveBl();
			bisShopId = bisTenant.getBisShopId();
			if (StringUtils.isNotBlank(bisShopId)) {
				bisShopName = bisShopManager.getEntity(bisShopId).getNameCn();
				mapBisShopConn = bisShopConnManager.getMapBisShopConn(bisShopId);
				bisShopConnId = bisTenant.getBisShopConnId();
				// bisShopConnName =
				// bisShopConnManager.getEntity(bisTenant.getBisShopConnId()).getPartName();
			} else {
				bisShopName = bisTenant.getOwner();
			}
		}
	}

	/**
	 * 计算时长
	 */
	private void calculateDays(Date startDate, Date endDate) {

		if (startDate == null || endDate == null)
			return;

		Date now = new Date();
		String nowstr = DateOperator.formatDate(now, "yyyy-MM-dd");
		Date nowDate = DateOperator.parse(nowstr, "yyyy-MM-dd");

		Long passed = nowDate.getTime() - startDate.getTime();
		Long remain = endDate.getTime() - nowDate.getTime();

		if (passed > 0 && remain > 0) {

			passedDate = String.valueOf(DateOperator.compareDays(startDate, nowDate));
			remainDate = String.valueOf(DateOperator.compareDays(nowDate, endDate));
		}
	}

	public String toSearchSenior() throws Exception {

		if (StringUtils.isBlank(contBigTypeCd)) {
			mapContSmallType = appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_SMALL_TYPE);
		} else if (StringUtils.equals(contBigTypeCd, BisConstants.STORE)) {
			mapContSmallType = appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_SMALL_TYPE);
			mapContSmallType.remove("5");
		} else if (StringUtils.equals(contBigTypeCd, BisConstants.FLAT)) {
			mapContSmallType.put("", "--选择--");
			mapContSmallType.put("3", appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, "3"));
		} else {
			mapContSmallType.put("", "--选择--");
			mapContSmallType.put("5", appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, "5"));
		}

		return "searchSenior";
	}

	/**
	 * 合同主页
	 */
	public String main() throws Exception {

		buildPermission();
		if (StringUtils.isBlank(bisProjectId)) {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if (bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		} else {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
		if (StringUtils.isBlank(filter_EQ_contBigTypeCd)) {
			contBigTypeCd = "1";
		}
		return SUCCESS;
	}

	/**
	 * 合同冲突提示
	 */
	public String loadContInfo() throws Exception {

		return "contInfo";
	}

	public void prepareLoadContInfo() throws Exception {

		entity = bisContManager.getEntity(getId());
		// contNo = bisCont.getContNo();
		// contName = bisCont.getContName();
		// contStartDate = bisCont.getContStartDate();
		// contEndDate = bisCont.getContEndDate();
		// contBigTypeCd = bisCont.getContBigTypeCd();
		// contSmallTypeCd = bisCont.getContSmallTypeCd();
		if (StringUtils.isNotBlank(entity.getBisTenantId())) {
			BisShop bisShop = bisShopManager.getEntity(bisTenantManager.getEntity(entity.getBisTenantId()).getBisShopId());
			if (StringUtils.isBlank(bisShop.getNameCn())) {
				bisShopName = bisShop.getNameEn() + "|" + bisShop.getCompanyName();
			} else {
				bisShopName = bisShop.getNameCn() + "|" + bisShop.getCompanyName();
			}
		}
		setStoreFlatIdNos(entity.getBisContId(), entity.getContBigTypeCd());
	}

	public String loadMustList() throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(getId())) {
			StringBuffer hql = new StringBuffer("from BisMust bm where bm.bisCont.bisContId=:bisContId");
			params.put("bisContId", getId());
			if (StringUtils.isNotBlank(seleChargeType)) {
				hql.append(" and bm.chargeTypeCd=:chargeTypeCd ");
				params.put("chargeTypeCd", seleChargeType);
			}
			hql.append(" order by to_number(bm.mustYear), to_number(bm.mustMonth)");
			mustList = bisMustManager.find(hql.toString(), params);
		}

		return "mustList";
	}

	public String reloadChargeType() {

		String approveHql = "from BisMust where bisCont.bisContId=? and chargeTypeCd=? and statusCd=2";
		String totalHql = "from BisMust where bisCont.bisContId=? and chargeTypeCd=?";
		long total = bisContManager.countHqlResult(totalHql, getId(), seleChargeType);
		long approve = bisContManager.countHqlResult(approveHql, getId(), seleChargeType);
		String name = appDictTypeManager.getDictNameByCd(DictContants.BIS_CHARGE_TYPE, seleChargeType);
		if (total > 0) {
			name = name + " " + approve + "/" + total;
		}
		Struts2Utils.renderText(name);
		return null;
	}

	public String loadContAddiList() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			entity = bisContManager.getEntity(getId());
		}
		return "additionalList";
	}

	/**
	 * 审核合同
	 */
	public String approveCont() {

		String result = null;
		try {
			// if(BisConstants.STATUS_SUBMIT.equals(statusCd)) {
			// statusCd = BisConstants.STATUS_APPROVED;
			// } else if(BisConstants.STATUS_CHANGE_APPROVE.equals(statusCd)) {
			// statusCd = BisConstants.STATUS_APPROVED;
			// }
			result = bisContManager.approveCont(getId(), BisConstants.STATUS_APPROVED);
		} catch (Exception e) {
			e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				result = "error";
			} else
				throw new RuntimeException(e);
		}
		Struts2Utils.renderText(result);
		return null;
	}

	/**
	 * 审核变更合同(NOW)
	 */
	public String approveChange() {

		String result = null;
		try {
			result = bisContManager.approveChange(getId());
		} catch (Exception e) {
			e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				result = "error";
			} else
				throw new RuntimeException(e);
		}
		Struts2Utils.renderText(result);
		return null;
	}

	/**
	 * 驳回合同
	 */
	public String backCont() {

		String result = null;
		try {
			// if(BisConstants.STATUS_SUBMIT.equals(statusCd) ||
			// BisConstants.STATUS_APPROVED.equals(statusCd)) {
			result = bisContManager.backCont(getId(), BisConstants.STATUS_BACK);
			// } else if(BisConstants.STATUS_CHANGE_APPROVE.equals(statusCd)) {
			// result = bisContManager.backContVersion(getId(),
			// BisConstants.STATUS_CHANGE_BACK);
			// }
		} catch (Exception e) {
			// e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				result = "error";
			} else
				throw new RuntimeException(e);
		}
		Struts2Utils.renderText(result);
		return null;
	}

	/**
	 * 驳回变更合同(NOW)
	 */
	public String backChange() {

		String result = null;
		try {
			result = bisContManager.backChange(getId());
		} catch (Exception e) {
			// e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				result = "error";
			} else
				throw new RuntimeException(e);
		}

		Struts2Utils.renderText(result);
		return null;
	}

	/**
	 * 取消变更
	 */
	// @Deprecated
	// public String cancelChange() {
	//		
	// String result = null;
	// try {
	// result = bisContManager.backContVersion(getId(), null);
	// } catch(Exception e) {
	// e.printStackTrace();
	// if(StringUtils.isNotBlank(e.getMessage())) {
	// logger.error(e.getMessage(), e);
	// result = "error";
	// } else
	// throw new RuntimeException(e);
	// }
	// Struts2Utils.renderText(result);
	// return null;
	// }

	/**
	 * 审核应收款
	 */
	public String approveMust() {
		// String result = null;
		try {
			bisMustManager.approveMust(chkIds, statusCd);
		} catch (Exception e) {
			e.printStackTrace();
			if (StringUtils.isNotBlank(e.getMessage())) {
				logger.error(e.getMessage(), e);
				// result = "error";
			} else
				throw new RuntimeException(e);
		}
		// Struts2Utils.renderText(result);
		return null;
	}

	/**
	 * 变更合同
	 */
	// @Deprecated
	// public String changeCont() {
	//		
	// String result = null;
	// try {
	// result = bisContManager.changeCont(getId());
	// } catch(Exception e) {
	// e.printStackTrace();
	// if(StringUtils.isNotBlank(e.getMessage())) {
	// logger.error(e.getMessage(), e);
	// result = "error";
	// } else
	// throw new RuntimeException(e);
	// }
	// Struts2Utils.renderText(result);
	// return null;
	// }

	public String showHis() throws Exception {

		buildPermission();
		return "his";
	}

	public void prepareShowHis() throws Exception {

		entity = bisContManager.getHisCont(getId());

		contBigTypeCd = entity.getContBigTypeCd();
		contSmallTypeCd = entity.getContSmallTypeCd();

		initMapChargeType(entity);

		setShopIdName(entity.getBisTenantId());

		// setStoreFlatIdNos(entity.getBisContId(), entity.getContBigTypeCd());
		setHisStoreIdNos(entity.getTempHisId(), entity.getContBigTypeCd());

		calculateDays(entity.getContStartDate(), entity.getContEndDate());

		bisProjectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();
	}

	public String showHisVersion() throws Exception {

		buildPermission();
		return "his";
	}

	public void prepareShowHisVersion() throws Exception {

		entity = bisContManager.getHisVersion(getId());

		contBigTypeCd = entity.getContBigTypeCd();
		contSmallTypeCd = entity.getContSmallTypeCd();

		initMapChargeType(entity);

		setShopIdName(entity.getBisTenantId());

		// setStoreFlatIdNos(entity.getBisContId(), entity.getContBigTypeCd());
		setHisStoreIdNos(entity.getTempHisId(), entity.getContBigTypeCd());

		calculateDays(entity.getContStartDate(), entity.getContEndDate());

		bisProjectName = bisProjectManager.getEntity(entity.getBisProjectId()).getProjectName();
	}

	public String showHisList() throws Exception {

		bisContHisList = bisContHisManager.getHisList(getId());

		return "hislist";
	}

	public void prepareShowHisList() throws Exception {

		entity = bisContManager.getEntity(getId());
	}

	public String compareCont() throws Exception {

		String compareIds = Struts2Utils.getParameter("compareIds");
		String[] ids = compareIds.split(",");

		if ("0".equals(ids[0].substring(0, 1))) {
			compareCont1 = bisContManager.getEntity(ids[0].substring(2));
		} else {
			compareCont1 = bisContManager.getHisVersion(ids[0].substring(2));
		}
		if ("0".equals(ids[1].substring(0, 1))) {
			compareCont2 = bisContManager.getEntity(ids[1].substring(2));
		} else {
			compareCont2 = bisContManager.getHisVersion(ids[1].substring(2));
		}

		bisProjectId = compareCont1.getBisProjectId();
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		return "compare";
	}

	/**
	 * 选择合同类型页
	 */
	public String typeSelect() throws Exception {

		buildPermission();

		mapContBigType.clear();
		// mapContBigType.put("", "--选择--");
		if (StringUtils.isNotBlank(bisTenantId)) {
			// if(storeOperator) {
			mapContBigType.put(BisConstants.STORE, appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE, BisConstants.STORE));
			// contBigTypeCd = BisConstants.STORE;
			// }
			// return "typeSelect";
		} else {
			mapContBigType = appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CONT_BIG_TYPE);
			mapContSmallType = getMapContSmallTypeA();
		}
		// if(storeOperator) {
		// mapContBigType.put(BisConstants.STORE,
		// appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE,
		// BisConstants.STORE));
		// }
		// if(flatOperator) {
		// mapContBigType.put(BisConstants.FLAT,
		// appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE,
		// BisConstants.FLAT));
		// }
		// if(multiOperator) {
		// mapContBigType.put(BisConstants.MULTI,
		// appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE,
		// BisConstants.MULTI));
		// }

		return "typeSelect";
	}

	/**
	 * 为选择合同使用：批量减免模块
	 */
	public String contTypeSelect() throws Exception {

		buildPermission();

		mapContBigType.clear();
		// mapContBigType.put("", "--选择--");
		if (StringUtils.isNotBlank(bisTenantId)) {
			// if(storeOperator) {
			mapContBigType.put(BisConstants.STORE, appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE, BisConstants.STORE));
			// contBigTypeCd = BisConstants.STORE;
			// }
			return "select";
		}

		mapContBigType = appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CONT_BIG_TYPE);
		mapContSmallType = getMapContSmallTypeA();

		return "select";
	}

	/**
	 * 显示合同条款参考
	 */
	public String showContentRef() throws Exception {

		return "contentRef";
	}

	public String loadSmallType() throws Exception {

		mapContSmallType.clear();
		if (StringUtils.isBlank(contBigTypeCd)) {
			mapContSmallType = getMapContSmallTypeA();
		} else if (StringUtils.equals(contBigTypeCd, BisConstants.STORE)) {
			mapContSmallType = getMapContSmallTypeA();
			mapContSmallType.remove("5");
		} else if (StringUtils.equals(contBigTypeCd, BisConstants.FLAT)) {
			mapContSmallType.put("3", appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, "3"));
		} else {
			mapContSmallType.put("5", appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, "5"));
		}

		return "typeSmall";
	}

	public String checkContName() {

		List<BisCont> list = new ArrayList<BisCont>();
		if (StringUtils.isBlank(getId())) {
			String hql = "from BisCont where bisProjectId=? and contName=?";
			list = bisContManager.find(hql, bisProjectId, contName);
		} else {
			String hql = "from BisCont where bisProjectId=? and contName=? and bisContId!=?";
			list = bisContManager.find(hql, bisProjectId, contName, getId());
		}
		if (!list.isEmpty()) {
			Struts2Utils.renderText("exists");
		}
		return null;
	}

	public String toAddChargeType() throws Exception {

		mapChargeType = bisContManager.getChargeTypeMap(getId());

		return "addChargeType";
	}

	public String toAutoAdd() throws Exception {

		// contStartDate = contStartDate.substring(0, 7);
		// contEndDate = contEndDate.substring(0, 7);

		// Date sdate = DateOperator.parse(contStartDate, "yyyy-MM-dd");
		// Date edate = DateOperator.parse(contEndDate, "yyyy-MM-dd");
		//		
		// if(DateOperator.getDay(sdate)>14) {
		// sdate = DateOperator.addMonthes(sdate, 1);
		// } else {
		// edate = DateOperator.addMonthes(edate, -1);
		// }
		// contStartDate = DateOperator.formatDate(sdate, "yyyy-MM-dd");
		// contEndDate = DateOperator.formatDate(edate, "yyyy-MM-dd");

		return "autoAdd";
	}

	public String toYearIncrease() throws Exception {
		return "yearIncrease";
	}

	/**
	 * 自动生成应收款
	 */
	public String doAutoAdd() throws Exception {

		List<BisMustAutoVo> list = new ArrayList<BisMustAutoVo>();

		Date collDate = DateOperator.parse(contStartDate, "yyyy-MM-dd");

		String startDate = Struts2Utils.getParameter("startDate");
		String endDate = Struts2Utils.getParameter("endDate");
		String firstDay = Struts2Utils.getParameter("firstDay");
		collDate = DateOperator.parse(firstDay, "yyyy-MM-dd");
		String payMode = Struts2Utils.getParameter("payMode");
		String sMoney = Struts2Utils.getParameter("money");
		String sIncreaseRate = Struts2Utils.getParameter("increaseRate");

		Date sDate = DateParser.parse(startDate, "yyyy-MM");
		Date eDate = DateParser.parse(endDate, "yyyy-MM");
		eDate = DateOperator.addMonthes(eDate, 1);

		int months = 1;
		if (!StringUtils.equals(startDate, endDate)) {
			months = DateOperator.getMonthes(eDate, sDate) + 1;
		}
		// if(months > 1) {
		// months = months-1;
		// }
		for (int i = 0; i < months; i++) {

			BisMustAutoVo autoVo = new BisMustAutoVo();
			autoVo.setSequenceNo(new Long(i + 1));
			if (StringUtils.equals(payMode, "1")) {
				if (i != 0) {
					collDate = DateOperator.addMonthes(collDate, 1);
				}
			} else if (StringUtils.equals(payMode, "2")) {
				if (i != 0 && i % 3 == 0) {
					collDate = DateOperator.addMonthes(collDate, 3);
				}
			} else if (StringUtils.equals(payMode, "3")) {
				if (i != 0 && i % 6 == 0) {
					collDate = DateOperator.addMonthes(collDate, 6);
				}
			} else if (StringUtils.equals(payMode, "4")) {
				if (i != 0 && i % 12 == 0) {
					collDate = DateOperator.addMonthes(collDate, 12);
				}
			}
			autoVo.setCollDate(collDate);
			autoVo.setMustYear(String.valueOf(DateOperator.getYear(sDate)));
			autoVo.setMustMonth(String.valueOf(DateOperator.getMonth12(sDate)));
			if (StringUtils.isNotBlank(sMoney)) {

				BigDecimal money = new BigDecimal(FormatUtil.formatDouble(sMoney));
				if (StringUtils.isNotBlank(sIncreaseRate)) {
					BigDecimal r = (new BigDecimal(FormatUtil.formatDouble(sIncreaseRate))).divide(new BigDecimal(100)).add(new BigDecimal(1));
					money = money.multiply(r.pow(i));
				}
				autoVo.setMoney(money.setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			autoVo.setEntityTmpId("bisCont_" + RandomUtils.generateTmpEntityId());
			list.add(autoVo);
			sDate = DateOperator.addMonthes(sDate, 1);
		}

		JSONUtil.renderListJson(list, "yyyy-MM-dd");
		// Struts2Utils.renderJson(list);
		return null;
	}

	public String getExistsCont() throws Exception {

		BisCont bisCont = new BisCont();
		bisCont.setBisProjectId(bisProjectId);
		bisCont.setContBigTypeCd(contBigTypeCd);
		bisCont.setContSmallTypeCd(contSmallTypeCd);
		bisCont.setContStartDate(DateOperator.parse(contStartDate, "yyyy-MM-dd"));
		bisCont.setContEndDate(DateOperator.parse(contEndDate, "yyyy-MM-dd"));

		String existsContId = bisContManager.containsConts(bisCont, bisStoreIds, bisShopId, bisShopConnId);
		if (StringUtils.isBlank(existsContId)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText(existsContId);
		}

		return null;
	}

	public String getAttachTmpId() throws Exception {
		Struts2Utils.renderText("bisCont_" + RandomUtils.generateTmpEntityId());
		return null;
	}

	/**
	 * 获得默认费用类别集合
	 */
	private Map<String, String> getDefaultChargeTypeMap(String bType, String sType) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.putAll(appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CHARGE_TYPE));
		/*
		 * if(StringUtils.equals(bType, BisConstants.STORE) &&
		 * StringUtils.equals(sType, BisConstants.RENT)) {
		 * 
		 * map.putAll(appDictTypeManager.getDictDataByTypeCdA(DictContants.
		 * BIS_CHARGE_TYPE_RENT));
		 * 
		 * } else if(StringUtils.equals(bType, BisConstants.STORE) &&
		 * StringUtils.equals(sType, BisConstants.BACK)) {
		 * 
		 * } else if(StringUtils.equals(bType, BisConstants.STORE) &&
		 * StringUtils.equals(sType, BisConstants.PROP)) {
		 * 
		 * map.putAll(appDictTypeManager.getDictDataByTypeCdA(DictContants.
		 * BIS_CHARGE_TYPE_PROP));
		 * 
		 * } else if(StringUtils.equals(bType, BisConstants.STORE) &&
		 * StringUtils.equals(sType, BisConstants.ENTR)) {
		 * map.putAll(appDictTypeManager
		 * .getDictDataByTypeCdA(DictContants.BIS_CHARGE_TYPE));
		 * 
		 * } else if(StringUtils.equals(bType, BisConstants.STORE) &&
		 * StringUtils.equals(sType, BisConstants.OWNER_PROP)) {
		 * 
		 * map.putAll(appDictTypeManager.getDictDataByTypeCdA(DictContants.
		 * BIS_CHARGE_TYPE_PROP));
		 * 
		 * } else if(StringUtils.equals(bType, BisConstants.FLAT)) {
		 * 
		 * map.putAll(appDictTypeManager.getDictDataByTypeCdA(DictContants.
		 * BIS_CHARGE_TYPE_FLAT));
		 * 
		 * } else if(StringUtils.equals(bType, BisConstants.MULTI)) {
		 * 
		 * map.putAll(appDictTypeManager.getDictDataByTypeCdA(DictContants.
		 * BIS_CHARGE_TYPE_MULTI)); }else{
		 * map.putAll(appDictTypeManager.getDictDataByTypeCdA
		 * (DictContants.BIS_CHARGE_TYPE)); }
		 */
		return map;
	}

	public String getSmallType() throws Exception {

		String bigType = Struts2Utils.getParameter("bigType");
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (StringUtils.isBlank(bigType)) {
			map = getMapContSmallTypeA();
		} else if (StringUtils.equals(bigType, BisConstants.STORE)) {
			map = getMapContSmallTypeA();
			map.remove("5");
		} else if (StringUtils.equals(bigType, BisConstants.FLAT)) {
			map.put("3", appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, "3"));
		} else {
			map.put("5", appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, "5"));
		}
		Struts2Utils.renderJson(map);
		return null;
	}

	/**
	 * 设置商铺、公寓编号
	 */
	private void setStoreFlatIdNos(String bisContId, String bigType) {

		String[] idno = bisContManager.getStoreIdAndNo(bisContId);
		if (StringUtils.equals(bigType, BisConstants.STORE)) {
			bisStoreIds = idno[0];
			bisStoreNos = idno[1];
		} else if (StringUtils.equals(bigType, BisConstants.FLAT)) {
			bisFlatIds = idno[0];
			bisFlatNos = idno[1];
		} else {
			bisMultiIds = idno[0];
			bisMultiNos = idno[1];
		}
	}

	/**
	 * 设置商铺、公寓编号
	 */
	private void setHisStoreIdNos(String bisContHisId, String bigType) {

		String[] idno = bisContManager.getHisStoreIdAndNo(bisContHisId);
		if (StringUtils.equals(bigType, BisConstants.STORE)) {
			bisStoreIds = idno[0];
			bisStoreNos = idno[1];
		} else if (StringUtils.equals(bigType, BisConstants.FLAT)) {
			bisFlatIds = idno[0];
			bisFlatNos = idno[1];
		} else {
			bisMultiIds = idno[0];
			bisMultiNos = idno[1];
		}
	}

	private void setSquare() {

		Map<String, Object> param = new HashMap<String, Object>();
		String sql = "select round(sum(square),2),round(sum(inner_square),2),round(sum(rent_square),2) "
				+ "from bis_store where bis_store_id in (:bisStoreIds)";
		param.put("bisStoreIds", bisStoreIds.split(","));
		@SuppressWarnings("unchecked")
		List<Object[]> list = bisContManager.findBySql(sql, param);
		for (Object[] obj : list) {
			// entity.setSquare((BigDecimal) obj[0]);
			// entity.setInnerSquare((BigDecimal) obj[1]);
			// entity.setRentSquare((BigDecimal) obj[2]);
			showSquare = (BigDecimal) obj[2];
		}
	}

	/**
	 * 获取商铺面积
	 */
	public String getStoreInfo() throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisStore where bisStoreId in (:bisStoreIds)";
		param.put("bisStoreIds", bisStoreIds.split(","));
		List<BisStore> list = bisStoreManager.find(hql, param);

		BigDecimal square = new BigDecimal(0);
		// 建筑面积实测
		BigDecimal squareReal = new BigDecimal(0);
		BigDecimal innerSquare = new BigDecimal(0);
		// 套内面积实测
		BigDecimal innerSquareReal = new BigDecimal(0);
		BigDecimal rentSquare = new BigDecimal(0);
		BigDecimal zero = new BigDecimal(0);
		String owner = "";
		boolean booSameOwner = true;
		for (BisStore bisStore : list) {
			squareReal = bisStore.getSquareReal();
			innerSquareReal = bisStore.getInnerSquareReal();
			rentSquare = rentSquare.add(bisStore.getRentSquare());
			// 如果有实测则使用实测数据
			if (null != bisStore.getSquareReal() && (zero.compareTo(squareReal)!=0)) {
				// 建筑面积实测
				square = square.add(squareReal);

			} else { // 否则使用图测数据
				square = square.add(bisStore.getSquare());
			}
			// 如果有实测则使用实测数据
			if (null != innerSquareReal && (zero.compareTo(innerSquareReal)!=0)) {
				// 套内面积实测
				innerSquare = innerSquare.add(innerSquareReal);
			} else { // 否则使用图测数据
				innerSquare = innerSquare.add(bisStore.getInnerSquare());
			}
			if ("2".equals(bisStore.getEquityNature())) {
				if (booSameOwner) {
					owner = bisStore.getOwner();
				}
				if (!StringUtils.equals(owner, bisStore.getOwner())) {
					booSameOwner = false;
				}
			}
		}
		if (!booSameOwner) {
			owner = "";
		}
		result.put("square", square);
		result.put("innerSquare", innerSquare);
		result.put("rentSquare", rentSquare);
		result.put("showSquare", rentSquare);
		if (StringUtils.isNotBlank(owner)) {
			result.put("owner", owner);
		}

		Struts2Utils.renderJson(result);
		return null;
	}

	/**
	 * 获取公寓面积
	 */
	public String getFlatInfo() throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisFlat where bisFlatId in (:bisFlatIds)";
		param.put("bisFlatIds", bisFlatIds.split(","));
		List<BisFlat> list = bisFlatManager.find(hql, param);

		BigDecimal square = new BigDecimal(0);
		BigDecimal innerSquare = new BigDecimal(0);

		for (BisFlat bisFlat : list) {
			square = square.add(bisFlat.getSquare());
			innerSquare = innerSquare.add(bisFlat.getInnerSquare());
		}
		result.put("square", square);
		result.put("innerSquare", innerSquare);

		Struts2Utils.renderJson(result);
		return null;
	}

	/**
	 * 功能: 构造用户权限
	 */
	private void buildPermission() {

		projAdmin = false;
		List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(SpringSecurityUtils.getCurrentDeptCd());
		List<BisProject> projectList = bisProjectManager.getAll();
		for (BisProject project : projectList) {
			for (WsPlasOrg org : orgList) {
				if (StringUtils.equals(project.getOrgCd(), org.getOrgCd())) {
					projAdmin = true;
					break;
				}
			}
		}

		/*
		 * boolean contStoreApprover = false; boolean contStoreDelete = false;
		 * boolean mustStoreApprover = false; boolean mustStoreDelete = false;
		 * boolean contFlatApprover = false; boolean contFlatDelete = false;
		 * boolean mustFlatApprover = false; boolean mustFlatDelete = false;
		 * boolean contMultiApprover = false; boolean contMultiDelete = false;
		 * boolean mustMultiApprover = false; boolean mustMultiDelete = false;
		 * boolean hasProject = false;
		 * 
		 * if (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_STORE) ||
		 * SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FINA)) {
		 * storeOperator = true; } if
		 * (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FLAT) ||
		 * SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FINA)) {
		 * flatOperator = true; } if
		 * (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_MULTI) ||
		 * SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FINA)) {
		 * multiOperator = true; }
		 * if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_OPER) ||
		 * SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_FINA) ||
		 * SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_SYSTEM_ADMIN)) {
		 * contStoreDelete = true; contFlatDelete = true; contMultiDelete =
		 * true; }
		 * if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FINA)) {
		 * mustStoreDelete = true; mustFlatDelete = true; mustMultiDelete =
		 * true; contStoreApprover = true; contFlatApprover = true;
		 * contMultiApprover = true; } if
		 * (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_ADVE)) {
		 * contStoreApprover = true; } if
		 * (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_VI_FINA)) {
		 * mustStoreApprover = true; mustFlatApprover = true; mustMultiApprover
		 * = true; }
		 * 
		 * List<WsPlasOrg> orgList =
		 * PlasCache.getPhysicalBubbleOrgListByOrgCd(SpringSecurityUtils
		 * .getCurrentDeptCd()); if(StringUtils.isNotBlank(getId())) { String
		 * orgCd =
		 * bisProjectManager.getEntity(entity.getBisProjectId()).getOrgCd(); for
		 * (WsPlasOrg org : orgList) { if (StringUtils.equals(orgCd,
		 * org.getOrgCd())) { projAdmin = true; break; } } //TODO DEL
		 * SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_SYSTEM_ADMIN) ||
		 * if(StringUtils.equals(projPermSwitch, "off")) { projAdmin = true; }
		 * 
		 * if(StringUtils.isNotBlank(contBigTypeCd)) { if(projAdmin &&
		 * (BisConstants.STORE.equals(contBigTypeCd) && storeOperator) ||
		 * (BisConstants.FLAT.equals(contBigTypeCd) && flatOperator) ||
		 * (BisConstants.MULTI.equals(contBigTypeCd) && multiOperator)) {
		 * operator = true; }
		 * 
		 * if(projAdmin && (BisConstants.STORE.equals(contBigTypeCd) &&
		 * contStoreApprover) || (BisConstants.FLAT.equals(contBigTypeCd) &&
		 * contFlatApprover) || (BisConstants.MULTI.equals(contBigTypeCd) &&
		 * contMultiApprover)) { contApprover = true; }
		 * 
		 * if(projAdmin && (BisConstants.STORE.equals(contBigTypeCd) &&
		 * mustStoreApprover) || (BisConstants.FLAT.equals(contBigTypeCd) &&
		 * mustFlatApprover) || (BisConstants.MULTI.equals(contBigTypeCd) &&
		 * mustMultiApprover)) { mustApprover = true; }
		 * 
		 * if(projAdmin && (BisConstants.STORE.equals(contBigTypeCd) &&
		 * contStoreDelete) || (BisConstants.FLAT.equals(contBigTypeCd) &&
		 * contFlatDelete) || (BisConstants.MULTI.equals(contBigTypeCd) &&
		 * contMultiDelete)) { contDelete = true; }
		 * 
		 * if(projAdmin && (BisConstants.STORE.equals(contBigTypeCd) &&
		 * mustStoreDelete) || (BisConstants.FLAT.equals(contBigTypeCd) &&
		 * mustFlatDelete) || (BisConstants.MULTI.equals(contBigTypeCd) &&
		 * mustMultiDelete)) { mustDelete = true; } }
		 * 
		 * } else { //TODO DEL if(StringUtils.equals(projPermSwitch, "off")) {
		 * projAdmin = true; }
		 * 
		 * if(StringUtils.isNotBlank(contBigTypeCd)) {
		 * if((BisConstants.STORE.equals(contBigTypeCd) && storeOperator) ||
		 * (BisConstants.FLAT.equals(contBigTypeCd) && flatOperator) ||
		 * (BisConstants.MULTI.equals(contBigTypeCd) && multiOperator)) {
		 * operator = true; } } else {
		 * 
		 * List<String> orgCdList = PowerUtils.getProptyArray(orgList, "orgCd");
		 * int size =
		 * bisProjectManager.getMapBisProject(orgCdList.toArray()).size();
		 * if(size > 1) { hasProject = true; } //TODO DEL
		 * if(StringUtils.equals(projPermSwitch, "off")) { hasProject = true; }
		 * 
		 * if(hasProject && (storeOperator || flatOperator || multiOperator)) {
		 * operator = true; } }
		 * 
		 * }
		 */
	}

	public String loadTenantConts() {

		tenantConts = bisContManager.find("from BisCont where bisTenantId=? order by contSmallTypeCd", bisTenantId);

		return "tenantConts";
	}

	public String loadMultiConts() {
		String bisMultiId = Struts2Utils.getParameter("bisMultiId");
		tenantConts = bisContManager.find("select r.bisCont from BisMultiContRel r where r.bisMulti.bisMultiId=?", bisMultiId);

		return "tenantConts";
	}

	public void loadTenantCont4() {
		tenantConts = bisContManager.find("from BisCont where bisTenantId=?", bisTenantId);
		List<VoCont> result = new ArrayList<VoCont>();
		for (BisCont obj : tenantConts) {
			VoCont vo = new VoCont();
			vo.setBisContId(obj.getBisContId());
			vo.setBisProjectId(obj.getBisProjectId());
			vo.setBisTenantId(obj.getBisTenantId());
			vo.setContBigTypeCd(DictMapUtil.getMapContBigType().get(obj.getContBigTypeCd()));
			vo.setContName(obj.getContName());
			vo.setContNo(obj.getContNo());
			vo.setContSmallTypeCd(DictMapUtil.getMapContSmallType().get(obj.getContSmallTypeCd()));
			result.add(vo);
		}
		Struts2Utils.renderJson(result);
	}

	public String addTenantCont() {

		buildPermission();

		return INPUT;
	}

	public void prepareAddTenantCont() throws Exception {

		entity = new BisCont();

		if (StringUtils.isNotBlank(bisTenantId)) {

			BisTenant bisTenant = bisTenantManager.getEntity(bisTenantId);
			entity.setBisProjectId(bisTenant.getBisProjectId());
			bisProjectId = bisTenant.getBisProjectId();
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

			if (StringUtils.equals(BisConstants.STORE, contBigTypeCd)) {
				bisShopId = bisTenant.getBisShopId();
				bisShopName = bisShopManager.getEntity(bisShopId).getNameCn();

				if (StringUtils.isNotBlank(bisShopId)) {
					mapBisShopConn = bisShopConnManager.getMapBisShopConn(bisShopId);
					bisShopConnId = bisTenant.getBisShopConnId();
				}

				// setStoreFlatIdNos(bisTenant.getFirstContId(), contBigTypeCd);

				// setSquare();
			}

		} else {

			if (StringUtils.isBlank(bisProjectId)) {
				BisProject bisProject = bisProjectManager.getCurrProject();
				if (bisProject != null) {
					bisProjectId = bisProject.getBisProjectId();
					bisProjectName = bisProject.getProjectName();
				}
			} else {
				bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
			}
		}
		entityTmpId = "bisCont_" + RandomUtils.generateTmpEntityId();
	}

	/**
	 * 根据搜索条件导出合同信息
	 */
	public String exportExcel() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String hql = buildSearchHql(param);
		List<BisCont> list = bisContManager.find(hql, param);
		List<BisContExportVo> resultList = new ArrayList<BisContExportVo>();

		for (BisCont bisCont : list) {
			BisContExportVo vo = new BisContExportVo();
			vo.setBisProjectId(bisCont.getBisProjectId());
			vo.setBisTenantId(bisCont.getBisTenantId());
			vo.setBisContId(bisCont.getBisContId());
			vo.setContNo(bisCont.getContNo());
			// 合同类型
			if (BisConstants.RENT.equals(bisCont.getContSmallTypeCd())) {
				vo.setContType("商铺-租赁");
			} else if (BisConstants.BACK.equals(bisCont.getContSmallTypeCd())) {
				vo.setContType("商铺-返租");
			} else if (BisConstants.ENTR.equals(bisCont.getContSmallTypeCd())) {
				vo.setContType("商铺-委托经营");
			} else if (BisConstants.OWNER_PROP.equals(bisCont.getContSmallTypeCd())) {
				vo.setContType("商铺-业主物业");
			} else if (BisConstants.MULTI.equals(bisCont.getContBigTypeCd())) {
				vo.setContType("多经合同");
			} else if (BisConstants.FLAT.equals(bisCont.getContBigTypeCd())) {
				vo.setContType("公寓-物业");
			} else {
				vo.setContType("商铺-物业");
			}
			// 商家
			if (StringUtils.isNotBlank(bisCont.getBisTenantId())) {
				vo.setShopName(getShopName(bisCont.getBisTenantId()));
				// vo.setShopConnName(getShopConnName(bisCont.getBisTenantId()));
			} else {
				// ...
			}
			if (BisConstants.BACK.equals(bisCont.getContSmallTypeCd())) {
				vo.setShopName(bisCont.getBisContShopBacks().get(0).getOwner());
			} else if (BisConstants.OWNER_PROP.equals(bisCont.getContSmallTypeCd())) {
				vo.setShopName(bisCont.getOwner());
			} else if (BisConstants.FLAT.equals(bisCont.getContBigTypeCd())) {
				vo.setShopName(bisCont.getBisContFlatProps().get(0).getOwner());
			} else if (BisConstants.MULTI.equals(bisCont.getContBigTypeCd())) {
				vo.setShopName(getStoreNos(bisCont.getBisContId()));
			}

			// 商铺
			if (BisConstants.MULTI.equals(bisCont.getContBigTypeCd())) {
				vo.setStoreNos(bisCont.getBisContMultis().get(0).getOperationArea());
			} else {
				vo.setStoreNos(getStoreNos(bisCont.getBisContId()));
			}

			vo.setSquare(bisCont.getSquare());
			vo.setInnerSquare(bisCont.getInnerSquare());
			vo.setRentSquare(bisCont.getRentSquare());
			vo.setRentDate(DateOperator.formatDate(bisCont.getRentDate(), "yyyy-MM-dd"));
			vo.setContStartDate(DateOperator.formatDate(bisCont.getContStartDate(), "yyyy-MM-dd"));
			vo.setContEndDate(DateOperator.formatDate(bisCont.getContEndDate(), "yyyy-MM-dd"));
			vo.setRentYears(bisCont.getRentYears());

			vo.setEquityNature(appDictTypeManager.getDictNameByCd(DictContants.BIS_PROPERTY_RIGHT, bisCont.getEquityNature()));
			vo.setLayout(appDictTypeManager.getDictNameByCd(DictContants.BIS_STORE_LAYOUT, bisCont.getLayoutCd()));
			vo.setManage(appDictTypeManager.getDictNameByCd(DictContants.BIS_SHOP_MANAGE_TYPE, bisCont.getManageCd()));
			vo.setCoopWay(appDictTypeManager.getDictNameByCd(DictContants.BIS_COOP_WAY, bisCont.getCoopWayCd()));

			vo.setConnPeople(bisCont.getConnPeople());
			vo.setConnTel(bisCont.getConnTel());
			vo.setPartyA(bisCont.getPartyA());
			vo.setPartyB(bisCont.getPartyB());

			vo.setCreator(bisCont.getCreator());
			vo.setUpdator(bisCont.getUpdator());
			vo.setCheckUser(bisCont.getCheckUserCd());

			vo.setContContent(bisCont.getContContent());

			resultList.add(vo);
		}

		Map<String, Object> beans = new HashMap<String, Object>();
		if (BisConstants.STORE.equals(contBigTypeCd)) {
			beans.put("shopTitle", "商家");
			beans.put("storeTitle", "商铺");
		} else if (BisConstants.FLAT.equals(contBigTypeCd)) {
			beans.put("shopTitle", "业主");
			beans.put("storeTitle", "公寓");
		} else if (BisConstants.MULTI.equals(contBigTypeCd)) {
			beans.put("shopTitle", "多经");
			beans.put("storeTitle", "位置区域");
		} else {
			beans.put("shopTitle", "商家/业主/多经");
			beans.put("storeTitle", "商铺/公寓/位置");
		}

		beans.put("result", resultList);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));

		exportFile = JXLExcelUtil.initJxlsInputStream(beans, "BisCont.xls");
		String fileName = "商业合同台账" + DateOperator.formatDate(new Date(), "MMddHHmm");
		exportFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	/**
	 * 根据搜索条件导出合同信息
	 */
	public String exportContent() throws Exception {

		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		contentExcel = buildContentExcel();
		String fileName = bisProjectName + "-商业合同台账-" + DateOperator.formatDate(new Date(), "yy-MM-dd");
		contentExcelName = new String(fileName.getBytes("GBK"), "ISO8859-1");

		return "exportContent";
	}

	private InputStream buildContentExcel() {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {
			try {
				populateData(wbook);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JXLExcelUtil.closeWorkBook(wbook);
		}
		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}

	@SuppressWarnings("unchecked")
	private void populateData(WritableWorkbook wbook) throws Exception {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat();
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.LEFT);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2.setBackground(Colour.GOLD);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);

		// 商铺
		WritableSheet wsheet0 = wbook.createSheet("商铺", 0);
		// wsheet0.getSettings().setHorizontalFreeze(1);
		wsheet0.getSettings().setVerticalFreeze(1);
		wsheet0.getSettings().setProtected(true);

		wsheet0.addCell(new Label(0, 0, "", format_head2));
		wsheet0.addCell(new Label(1, 0, "合同编号", format_head2));// 合同编号、品牌、经销商、楼栋名称、商铺、建筑面积、套内面积、计租面积、
		// 实际收费面积、合同开始日期、合同结束日期、合同签约日期、计租起始日、合同关键条款

		wsheet0.addCell(new Label(2, 0, "品牌", format_head2));
		wsheet0.addCell(new Label(3, 0, "经销商", format_head2));
		// wsheet0.addCell(new Label(2, 0, "业主名称", format_head2));
		wsheet0.addCell(new Label(4, 0, "楼栋名称", format_head2));
		// wsheet0.addCell(new Label(2, 0, "商家", format_head2));
		wsheet0.addCell(new Label(5, 0, "商铺编号", format_head2));
		wsheet0.addCell(new Label(6, 0, "建筑面积", format_head2));
		wsheet0.addCell(new Label(7, 0, "套内面积", format_head2));
		// wsheet0.addCell(new Label(8, 0, "计租面积", format_head2));
		wsheet0.addCell(new Label(8, 0, "实际收费面积", format_head2));
		wsheet0.addCell(new Label(9, 0, "合同开始日期", format_head2));
		wsheet0.addCell(new Label(10, 0, "合同结束日期", format_head2));
		wsheet0.addCell(new Label(11, 0, "合同签约日期", format_head2));
		wsheet0.addCell(new Label(12, 0, "计租起始日", format_head2));
		wsheet0.addCell(new Label(13, 0, "合同关键条款", format_head2));
		// wsheet0.addCell(new Label(4, 0, "合同类型", format_head2));
		// 每一列宽度
		wsheet0.setColumnView(0, 0);
		wsheet0.setColumnView(1, 20);
		wsheet0.setColumnView(2, 15);
		wsheet0.setColumnView(3, 25);
		wsheet0.setColumnView(4, 10);
		wsheet0.setColumnView(5, 25);
		wsheet0.setColumnView(6, 15);
		wsheet0.setColumnView(7, 15);
		wsheet0.setColumnView(8, 15);
		wsheet0.setColumnView(9, 15);
		wsheet0.setColumnView(10, 20);
		wsheet0.setColumnView(11, 20);
		wsheet0.setColumnView(12, 20);
		wsheet0.setColumnView(13, 20);
		wsheet0.setColumnView(14, 25);
		// 首列行高
		wsheet0.setRowView(0, 600);

		List<Object[]> storeContList = bisContManager.findBySql(buildStoreContQuery(), param);
		for (int i = 0; i < storeContList.size(); i++) {
			Object[] obj = storeContList.get(i);
			// String contType =
			// appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE,
			// (String) obj[4]);
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");

			wsheet0.addCell(new Label(0, i + 1, (String) obj[0], format_label));
			wsheet0.addCell(new Label(1, i + 1, (String) obj[1], format_label));
			wsheet0.addCell(new Label(2, i + 1, (String) obj[2], format_label));
			wsheet0.addCell(new Label(3, i + 1, (String) obj[3], format_label));
			wsheet0.addCell(new Label(4, i + 1, (String) obj[4], format_label));
			wsheet0.addCell(new Label(5, i + 1, (String) obj[5], format_label));

			wsheet0.addCell(new Label(6, i + 1, String.valueOf(obj[6]), format_unlock));
			wsheet0.addCell(new Label(7, i + 1, String.valueOf(obj[7]), format_unlock));
			// wsheet0.addCell(new Label(8, i + 1, String.valueOf(obj[8]),
			// format_unlock));
			wsheet0.addCell(new Label(8, i + 1, String.valueOf(obj[9]), format_label));
			wsheet0.addCell(new Label(9, i + 1, format.format(obj[10]), format_label));
			wsheet0.addCell(new Label(10, i + 1, format.format(obj[11]), format_label));
			wsheet0.addCell(new Label(11, i + 1, format.format(obj[12]), format_label));
			wsheet0.addCell(new Label(12, i + 1, format.format(obj[13]), format_label));
			wsheet0.addCell(new Label(13, i + 1, (String) obj[14], format_label));
			// wsheet0.addCell(new Label(4, i + 1, contType, format_label));
			// wsheet0.addCell(new Label(5, i + 1, "", format_unlock));
			// 每列行高
			wsheet0.setRowView(i + 1, 500);
		}

		// 公寓
		WritableSheet wsheet1 = wbook.createSheet("公寓", 1);
		wsheet1.getSettings().setVerticalFreeze(1);
		wsheet1.getSettings().setProtected(true);

		wsheet1.addCell(new Label(0, 0, "", format_head2));
		wsheet1.addCell(new Label(1, 0, "合同编号", format_head2));
		wsheet1.addCell(new Label(2, 0, "公寓编号", format_head2));
		wsheet1.addCell(new Label(3, 0, "", format_head2));
		wsheet1.addCell(new Label(4, 0, "合同类型", format_head2));
		wsheet1.addCell(new Label(5, 0, "合同条款", format_head2));
		// 每一列宽度
		wsheet1.setColumnView(0, 0);
		wsheet1.setColumnView(1, 30);
		wsheet1.setColumnView(2, 30);
		wsheet1.setColumnView(3, 0);
		wsheet1.setColumnView(4, 10);
		wsheet1.setColumnView(5, 70);
		// 首列行高
		wsheet1.setRowView(0, 600);

		List<Object[]> flatContList = bisContManager.findBySql(buildFlatContQuery(), param);
		for (int i = 0; i < flatContList.size(); i++) {
			Object[] obj = flatContList.get(i);
			String contType = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, (String) obj[3]);

			wsheet1.addCell(new Label(0, i + 1, (String) obj[0], format_label));
			wsheet1.addCell(new Label(1, i + 1, (String) obj[1], format_label));
			wsheet1.addCell(new Label(2, i + 1, (String) obj[2], format_label));
			wsheet1.addCell(new Label(3, i + 1, "", format_label));
			wsheet1.addCell(new Label(4, i + 1, contType, format_label));
			wsheet1.addCell(new Label(5, i + 1, "", format_unlock));
			// 每列行高
			wsheet1.setRowView(i + 1, 500);
		}

		// 多经
		WritableSheet wsheet2 = wbook.createSheet("多经", 2);
		wsheet2.getSettings().setVerticalFreeze(1);
		wsheet2.getSettings().setProtected(true);
		wsheet2.addCell(new Label(0, 0, "", format_head2));
		wsheet2.addCell(new Label(1, 0, "合同编号", format_head2));
		wsheet2.addCell(new Label(2, 0, "多经编号", format_head2));
		wsheet2.addCell(new Label(3, 0, "", format_head2));
		wsheet2.addCell(new Label(4, 0, "合同类型", format_head2));
		wsheet2.addCell(new Label(5, 0, "合同条款", format_head2));
		// 每一列宽度
		wsheet2.setColumnView(0, 0);
		wsheet2.setColumnView(1, 30);
		wsheet2.setColumnView(2, 30);
		wsheet2.setColumnView(3, 0);
		wsheet2.setColumnView(4, 10);
		wsheet2.setColumnView(5, 70);
		// 首列行高
		wsheet2.setRowView(0, 600);

		List<Object[]> multiContList = bisContManager.findBySql(buildMultiContQuery(), param);
		for (int i = 0; i < multiContList.size(); i++) {
			Object[] obj = multiContList.get(i);
			String contType = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, (String) obj[3]);

			wsheet2.addCell(new Label(0, i + 1, (String) obj[0], format_label));
			wsheet2.addCell(new Label(1, i + 1, (String) obj[1], format_label));
			wsheet2.addCell(new Label(2, i + 1, (String) obj[2], format_label));
			wsheet2.addCell(new Label(3, i + 1, "", format_label));
			wsheet2.addCell(new Label(4, i + 1, contType, format_label));
			wsheet2.addCell(new Label(5, i + 1, "", format_unlock));
			// 每列行高
			wsheet2.setRowView(i + 1, 500);
		}
	}

	private String buildStoreContQuery() {

		StringBuffer sql = new StringBuffer();
		StringBuffer storeSql = new StringBuffer();

		storeSql.append(" SELECT c.bis_cont_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no").append(" FROM (").append(
				" SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_cont_id ORDER BY b.bis_cont_id, num DESC) rn, b.bis_cont_id, b.text").append(" FROM (").append(
				" SELECT a.bis_cont_id, level num, SYS_CONNECT_BY_PATH(a.store_no, '/') text").append(" FROM (").append(
				" select bc.bis_cont_id, bs.store_no, ROW_NUMBER() OVER(PARTITION BY bc.bis_cont_id ORDER BY bc.bis_cont_id, bs.store_no) x").append(
				" from bis_cont bc, bis_store_cont_rel scr, bis_store bs").append(" where bc.bis_cont_id=scr.bis_cont_id").append(
				" and scr.bis_store_id=bs.bis_store_id").append(" and bc.bis_project_id=:bisProjectId").append(" group by bc.bis_cont_id, bs.store_no").append(
				" ) a CONNECT BY a.bis_cont_id = PRIOR a.bis_cont_id AND a.x - 1 = PRIOR a.x").append(" ) b ) c").append(" WHERE c.rn = 1").append(
				" ORDER BY c.bis_cont_id");

		sql.append(
				" select c.bis_cont_id,c.cont_no,s.name_cn,conn.part_name,c.building_Name, d.store_no,C.SQUARE,C.INNER_SQUARE,C.RENT_SQUARE,C.RENT_SQUARE,"
						+ "C.cont_Start_Date,c.cont_End_Date,c.sign_Date,c.rent_Date,c.cont_content")// c.cont_small_type_cd
				.append(" from bis_cont c").append(" left join bis_tenant t on c.bis_tenant_id=t.bis_tenant_id").append(
						" left join bis_shop s on s.bis_shop_id=t.bis_shop_id").append(
						" left join bis_shop_conn conn on conn.bis_shop_conn_id =t.bis_shop_conn_id").append(" left join (").append(storeSql).append(
						" ) d on c.bis_cont_id=d.bis_cont_id").append(" where c.bis_project_id=:bisProjectId").append(" and c.cont_big_type_cd='1'").append(
						" order by d.store_no,c.cont_small_type_cd");

		return sql.toString();
	}

	private String buildFlatContQuery() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select c.bis_cont_id,c.cont_no,f.flat_no,c.cont_small_type_cd,c.cont_content").append(" from bis_cont c").append(
				" left join bis_flat_cont_rel fc on fc.bis_cont_id=c.bis_cont_id").append(" left join bis_flat f on fc.bis_flat_id=f.bis_flat_id").append(
				" where c.bis_project_id=:bisProjectId").append(" and c.cont_big_type_cd='2'").append(" order by f.flat_no");

		return sql.toString();
	}

	private String buildMultiContQuery() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select c.bis_cont_id,c.cont_no,m.multi_name,c.cont_small_type_cd,c.cont_content").append(" from bis_cont c").append(
				" left join bis_multi_cont_rel mc on mc.bis_cont_id=c.bis_cont_id").append(" left join bis_multi m on mc.bis_multi_id=m.bis_multi_id").append(
				" where c.bis_project_id=:bisProjectId").append(" and c.cont_big_type_cd='3'").append(" order by c.bis_cont_id");

		return sql.toString();
	}

	public Map<String, String> getMapBisProject() {
		if (mapBisProject.isEmpty())
			return bisProjectManager.getMapBisProject();
		else
			return mapBisProject;
	}

	public Map<String, String> getMapBisShop() {
		return bisShopManager.getMapBisShop();
	}

	public Map<String, String> getMapBisShopConn() {
		if (mapBisShopConn.isEmpty()) {
			mapBisShopConn.put("", "--选择--");
		}
		return mapBisShopConn;
	}

	public String getShopName(String tenantId) {
		return bisShopManager.getShopName(tenantId);
	}

	public String getShopConnName(String tenantId) {
		return bisShopManager.getShopConnName(tenantId);
	}

	public String getStoreNos(String bisContId) {

		return bisContManager.getStoreIdAndNo(bisContId)[1];
	}

	public String getHisStoreNos(String bisContHisId) {

		return bisContManager.getHisStoreIdAndNo(bisContHisId)[1];
	}

	public String getTimeStatus(Date endDate) {

		String status = "0";
		Date now = new Date();
		if (now.after(endDate)) {
			status = "2";
		} else if (DateOperator.addMonthes(now, 6).after(endDate)) {
			status = "1";
		}

		return status;
	}

	/**
	 * 合同中搜索合同台账编号
	 */
	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, Object> param = new HashMap<String, Object>();

		StringBuffer hql2 = new StringBuffer("from BisCont b where contSmallTypeCd=2 and (Upper(contNo) like :likename)");
		param.put("likename", "%" + value.toUpperCase() + "%");

		hql2.append(" order by updatedDate desc ");
		page.setPageSize(30);
		page = bisContManager.findPage(page, hql2.toString(), param);

		for (BisCont bisCont : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("contNo", StringUtils.trim(bisCont.getContNo()));// 品牌名称
			map.put("owner", StringUtils.trim(bisCont.getBisContShopBacks().get(0).getOwner()));// 公司名称
			map.put("storeNo", bisContManager.getStoreIdAndNo(bisCont.getBisContId())[1]);// 公司名称
			map.put("contName", contName);// 合同名称
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 合同大类
	 */
	public Map<String, String> getMapContBigType() {
		if (mapContBigType.isEmpty())
			return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_BIG_TYPE);
		else
			return mapContBigType;
	}

	/**
	 * 合同小类(无空项)
	 */
	public Map<String, String> getMapContSmallTypeA() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CONT_SMALL_TYPE);
	}

	/**
	 * 合同小类
	 */
	public Map<String, String> getMapContSmallType() {
		if (mapContSmallType.isEmpty())
			return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_SMALL_TYPE);
		else
			return mapContSmallType;
	}

	public Map<String, String> getMapChargeType() {
		return mapChargeType;
	}

	public void setMapChargeType(Map<String, String> mapChargeType) {
		this.mapChargeType = mapChargeType;
	}

	public BisCont getEntity() {
		return entity;
	}

	public void setEntity(BisCont entity) {
		this.entity = entity;
	}

	public BisCont getCompareCont1() {
		return compareCont1;
	}

	public BisCont getCompareCont2() {
		return compareCont2;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	public String getBisTenantId() {
		return bisTenantId;
	}

	public void setBisTenantId(String bisTenantId) {
		this.bisTenantId = bisTenantId;
	}

	public String getBisStoreIds() {
		return bisStoreIds;
	}

	public void setBisStoreIds(String bisStoreIds) {
		this.bisStoreIds = bisStoreIds;
	}

	public String getBisStoreNos() {
		return bisStoreNos;
	}

	public void setBisStoreNos(String bisStoreNos) {
		this.bisStoreNos = bisStoreNos;
	}

	public String getBisFlatIds() {
		return bisFlatIds;
	}

	public void setBisFlatIds(String bisFlatIds) {
		this.bisFlatIds = bisFlatIds;
	}

	public String getBisFlatNos() {
		return bisFlatNos;
	}

	public void setBisFlatNos(String bisFlatNos) {
		this.bisFlatNos = bisFlatNos;
	}

	public String getBisMultiIds() {
		return bisMultiIds;
	}

	public void setBisMultiIds(String bisMultiIds) {
		this.bisMultiIds = bisMultiIds;
	}

	public String getBisMultiNos() {
		return bisMultiNos;
	}

	public void setBisMultiNos(String bisMultiNos) {
		this.bisMultiNos = bisMultiNos;
	}

	public String getBisShopId() {
		return bisShopId;
	}

	public void setBisShopId(String bisShopId) {
		this.bisShopId = bisShopId;
	}

	public String getBisShopName() {
		return bisShopName;
	}

	public void setBisShopName(String bisShopName) {
		this.bisShopName = bisShopName;
	}

	public String getBisShopConnId() {
		return bisShopConnId;
	}

	public void setBisShopConnId(String bisShopConnId) {
		this.bisShopConnId = bisShopConnId;
	}

	public String getBisShopConnName() {
		return bisShopConnName;
	}

	public void setBisShopConnName(String bisShopConnName) {
		this.bisShopConnName = bisShopConnName;
	}

	public String getContBigTypeCd() {
		return contBigTypeCd;
	}

	public void setContBigTypeCd(String contBigTypeCd) {
		this.contBigTypeCd = contBigTypeCd;
	}

	public String getContSmallTypeCd() {
		return contSmallTypeCd;
	}

	public void setContSmallTypeCd(String contSmallTypeCd) {
		this.contSmallTypeCd = contSmallTypeCd;
	}

	public String getSeleChargeType() {
		return seleChargeType;
	}

	public void setSeleChargeType(String seleChargeType) {
		this.seleChargeType = seleChargeType;
	}

	public List<BisMust> getMustList() {
		return mustList;
	}

	public List<BisCont> getTenantConts() {
		return tenantConts;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getContStartDate() {
		return contStartDate;
	}

	public void setContStartDate(String contStartDate) {
		this.contStartDate = contStartDate;
	}

	public String getContEndDate() {
		return contEndDate;
	}

	public void setContEndDate(String contEndDate) {
		this.contEndDate = contEndDate;
	}

	public String getPassedDate() {
		return passedDate;
	}

	public String getRemainDate() {
		return remainDate;
	}

	public void setRemainDate(String remainDate) {
		this.remainDate = remainDate;
	}

	public String getRentYear() {
		return rentYear;
	}

	public void setRentYear(String rentYear) {
		this.rentYear = rentYear;
	}

	public String[] getChkIds() {
		return chkIds;
	}

	public void setChkIds(String[] chkIds) {
		this.chkIds = chkIds;
	}

	public boolean isProjAdmin() {
		return projAdmin;
	}

	public void setProjAdmin(boolean projAdmin) {
		this.projAdmin = projAdmin;
	}

	// public boolean isOperator() {
	// return operator;
	// }
	//
	// public void setOperator(boolean operator) {
	// this.operator = operator;
	// }
	//	
	// public boolean isContApprover() {
	// return contApprover;
	// }
	//
	// public void setContApprover(boolean contApprover) {
	// this.contApprover = contApprover;
	// }
	//	
	// public boolean isMustApprover() {
	// return mustApprover;
	// }
	//
	// public void setMustApprover(boolean mustApprover) {
	// this.mustApprover = mustApprover;
	// }
	//
	// public boolean isContDelete() {
	// return contDelete;
	// }
	//
	// public void setContDelete(boolean contDelete) {
	// this.contDelete = contDelete;
	// }
	//
	// public boolean isMustDelete() {
	// return mustDelete;
	// }
	//
	// public void setMustDelete(boolean mustDelete) {
	// this.mustDelete = mustDelete;
	// }

	public boolean isChangeBl() {
		return changeBl;
	}

	public void setChangeBl(boolean changeBl) {
		this.changeBl = changeBl;
	}

	public BigDecimal getTotalSquare() {
		return totalSquare;
	}

	public void setTotalSquare(BigDecimal totalSquare) {
		this.totalSquare = totalSquare;
	}

	public BigDecimal getShowSquare() {
		return showSquare;
	}

	public void setShowSquare(BigDecimal showSquare) {
		this.showSquare = showSquare;
	}

	public boolean isTenantActiveBl() {
		return tenantActiveBl;
	}

	public void setTenantActiveBl(boolean tenantActiveBl) {
		this.tenantActiveBl = tenantActiveBl;
	}

	public String getFilter_LIKES_contName() {
		return filter_LIKES_contName;
	}

	public void setFilter_LIKES_contName(String filter_LIKES_contName) {
		this.filter_LIKES_contName = filter_LIKES_contName;
	}

	public String getFilter_LIKES_contNo() {
		return filter_LIKES_contNo;
	}

	public void setFilter_LIKES_contNo(String filter_LIKES_contNo) {
		this.filter_LIKES_contNo = filter_LIKES_contNo;
	}

	public String getFilter_EQ_contBigTypeCd() {
		return filter_EQ_contBigTypeCd;
	}

	public void setFilter_EQ_contBigTypeCd(String filter_EQ_contBigTypeCd) {
		this.filter_EQ_contBigTypeCd = filter_EQ_contBigTypeCd;
	}

	public String getFilter_EQ_contSmallTypeCd() {
		return filter_EQ_contSmallTypeCd;
	}

	public void setFilter_EQ_contSmallTypeCd(String filter_EQ_contSmallTypeCd) {
		this.filter_EQ_contSmallTypeCd = filter_EQ_contSmallTypeCd;
	}

	public String getFilter_GED_contStartDate() {
		return filter_GED_contStartDate;
	}

	public void setFilter_GED_contStartDate(String filter_GED_contStartDate) {
		this.filter_GED_contStartDate = filter_GED_contStartDate;
	}

	public String getFilter_LTD_contStartDate() {
		return filter_LTD_contStartDate;
	}

	public void setFilter_LTD_contStartDate(String filter_LTD_contStartDate) {
		this.filter_LTD_contStartDate = filter_LTD_contStartDate;
	}

	public String getFilter_GED_contEndDate() {
		return filter_GED_contEndDate;
	}

	public void setFilter_GED_contEndDate(String filter_GED_contEndDate) {
		this.filter_GED_contEndDate = filter_GED_contEndDate;
	}

	public String getFilter_LTD_contEndDate() {
		return filter_LTD_contEndDate;
	}

	public void setFilter_LTD_contEndDate(String filter_LTD_contEndDate) {
		this.filter_LTD_contEndDate = filter_LTD_contEndDate;
	}

	public String getFilter_EQ_statusCd() {
		return filter_EQ_statusCd;
	}

	public void setFilter_EQ_statusCd(String filter_EQ_statusCd) {
		this.filter_EQ_statusCd = filter_EQ_statusCd;
	}

	public String getFilter_LIKES_storeNo() {
		return filter_LIKES_storeNo;
	}

	public void setFilter_LIKES_storeNo(String filter_LIKES_storeNo) {
		this.filter_LIKES_storeNo = filter_LIKES_storeNo;
	}

	public String getFilter_EQ_manageCd() {
		return filter_EQ_manageCd;
	}

	public void setFilter_EQ_manageCd(String filter_EQ_manageCd) {
		this.filter_EQ_manageCd = filter_EQ_manageCd;
	}

	public String getFilter_EQ_mustStatus() {
		return filter_EQ_mustStatus;
	}

	public void setFilter_EQ_mustStatus(String filter_EQ_mustStatus) {
		this.filter_EQ_mustStatus = filter_EQ_mustStatus;
	}

	public String getFilter_EQ_mustDate() {
		return filter_EQ_mustDate;
	}

	public void setFilter_EQ_mustDate(String filter_EQ_mustDate) {
		this.filter_EQ_mustDate = filter_EQ_mustDate;
	}

	public String getBisFloorId() {
		return bisFloorId;
	}

	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}

	public Date getFailDate() {
		return failDate;
	}

	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}

	public InputStream getExportFile() {
		return exportFile;
	}

	public void setExportFile(InputStream exportFile) {
		this.exportFile = exportFile;
	}

	public String getExportFileName() {
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	public InputStream getContentExcel() {
		return contentExcel;
	}

	public void setContentExcel(InputStream contentExcel) {
		this.contentExcel = contentExcel;
	}

	public String getContentExcelName() {
		return contentExcelName;
	}

	public void setContentExcelName(String contentExcelName) {
		this.contentExcelName = contentExcelName;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public List<BisContHis> getBisContHisList() {
		return bisContHisList;
	}

	public BigDecimal getRentSquareTotal() {
		return rentSquareTotal;
	}

	public void setRentSquareTotal(BigDecimal rentSquareTotal) {
		this.rentSquareTotal = rentSquareTotal;
	}

	public BigDecimal getInnerSquareTotal() {
		return innerSquareTotal;
	}

	public void setInnerSquareTotal(BigDecimal innerSquareTotal) {
		this.innerSquareTotal = innerSquareTotal;
	}

	public BigDecimal getShowSquareTotal() {
		return showSquareTotal;
	}

	public void setShowSquareTotal(BigDecimal showSquareTotal) {
		this.showSquareTotal = showSquareTotal;
	}

}
