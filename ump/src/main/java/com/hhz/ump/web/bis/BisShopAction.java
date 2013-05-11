package com.hhz.ump.web.bis;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisShopBackupManager;
import com.hhz.ump.dao.bis.BisShopConnManager;
import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisShopSortManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisShopBackup;
import com.hhz.ump.entity.bis.BisShopConn;
import com.hhz.ump.entity.bis.BisShopSort;
import com.hhz.ump.entity.bis.BisShopSortRel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreeNode;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Namespace("/bis")
@Results( { @Result(name = "remindMerge", location = "/WEB-INF/content/bis/bis-shop-remindMerge.ftl", type = "freemarker"),
		@Result(name = "show", location = "bis-shop!input.action", type = "redirect", params = { "id", "${id}" }),
		@Result(name = "export", type = "stream",params = { "contentType", "application/vnd.ms-excel", 
				"inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
		})
public class BisShopAction extends CrudActionSupport<BisShop> {

	private BisShop entity;
	@Autowired
	private BisShopManager bisShopManager;
	@Autowired
	private BisShopConnManager bisShopConnManager;
	@Autowired
	private BisShopSortManager bisShopSortManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisShopBackupManager bisShopBackupManager;
	private String shopSortId; //树选中的id
	private String mergeId;// 要合并的ID
	private int currentPageNo;
	private String haveEditor;

	private String nameCn;
	private String nameEn;
	private String salesman;
	private String selectManageCd;
	private String selectShopType;
	private String companyName;
	private String areaCooperStr;
	private String shopAudit;
	private String centerCd;
	private String centerName;
	private String deleteId;
	private String createDate1;
	private String createDate2;
	private String quickQueryText;
	
	private InputStream is;	
	
	private String downFileName;

	Map<String, List<BisShop>> compareMap = new HashMap<String, List<BisShop>>();
	
	//34个省列表
	private Map<String,String> mapProvince = new LinkedHashMap<String, String>();

	@Override
	public BisShop getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			// entity = supBasicManager.getSupBasic(getId());
			bisShopManager.deleteBisShop(getId());
			Struts2Utils.renderJson("1");
			addActionMessage(getText("common.success"));
		}
		return null;
	}

	public void updateAttention() {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisShopManager.getEntity(getId());;
			if(StringUtils.equalsIgnoreCase(entity.getAttention(), "1")) {
				entity.setAttention("0");
			}else{
				entity.setAttention("1");
			}
			bisShopManager.saveBisShop(entity);
			Struts2Utils.renderJson("1");
			addActionMessage(getText("common.success"));
		}
	}
	
	public void updateStatus() {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisShopManager.getEntity(getId());
			entity.setDeleteBl("1");
			bisShopManager.saveBisShop(entity);
			Struts2Utils.renderJson("1");
			addActionMessage(getText("common.success"));
		}
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		// 显示合作区域
		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(DictContants.BIS_COOPERATIVE_REGION);
		if (StringUtils.isNotBlank(entity.getShopTypeCd())) {
			String[] areaStr = entity.getShopTypeCd().split(",");
			StringBuffer strBu = new StringBuffer("");
			for (AppDictData appDictData : appDictType.getAppDictDatas()) {
				for (int i = 0; i < areaStr.length; i++) {
					if (areaStr[i].equals(appDictData.getDictCd())) {
						strBu.append(appDictData.getDictName()).append(",");
					}
				}
			}
			setAreaCooperStr(strBu.toString());
		}
		// 遍历bis_project表，若与当前用户所在中心不一致，则弹出对话框让其选择
		List<BisProject> projectList = bisProjectManager.getAll();
		// 新增宝龙商业判断
		BisProject bisProject = new BisProject();
		bisProject.setOrgCd("153");
		bisProject.setProjectName("宝龙商业");
		projectList.add(bisProject);
		// 显示中心名称
		if (StringUtils.isNotBlank(entity.getBisShopDept())) {
			for (BisProject project : projectList) {
				if (project.getOrgCd().equals(entity.getBisShopDept())) {
					centerName = project.getProjectName();
					break;
				}
			}
		} else {
			// 如果取不到用户所在部门，则显示部门让其选择
			boolean haveCenter = false;
			for (BisProject project : projectList) {
				if (project.getOrgCd().equals(SpringSecurityUtils.getCurrentCenterCd())) {
					haveCenter = true;
					centerName = project.getProjectName();
					entity.setBisShopDept(project.getOrgCd());
					break;
				}
			}
			if (!haveCenter) {
				centerName = "";
			}
		}
		// 编辑权限
		if (StringUtils.isNotBlank(entity.getBisShopId())) {
			if (SpringSecurityUtils.getCurrentUiid().equals(entity.getSalesman())) {
				haveEditor = "1";
			} else {
				List<BisShopSortRel> relList = entity.getBisShopSortRels();
				for (BisShopSortRel rel : relList) {
					BisShopSort sort = rel.getBisShopSort();
					if (StringUtils.isNotBlank(sort.getDeptPerson())) {
						String dept = sort.getDeptPerson();
						if (dept.indexOf(SpringSecurityUtils.getCurrentUiid() + ";") >= 0) {
							haveEditor = "1";
							break;
						}
					}
				}
			}
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		page.setPageNo(currentPageNo);
		page = bisShopManager.findPage(page, shopQueryHql(param), param);
		return "list";
	}
	
	/**
	 * 拆分商家类别ID和项目orgCd
	 * 
	 * 注：shopSortId中包含了项目选中的商家类别ID和项目公司orgCd,以"-"区分;
	 * 如：402834e5319038110131904d6cfe003a-367
	 */
	public void initQueryCd(){
		String sortIds = null;
		String orgCds = null;
		if (StringUtils.isNotBlank(shopSortId)) {
			String[] shopSortIds = shopSortId.split(",");
			if(shopSortIds != null && shopSortIds.length > 0){
				for(int i = 0;i < shopSortIds.length;i++){
					String[] array = shopSortIds[i].split("-");
					String tmpShopSortId = array[0]; //商家类别ID
					String tmpCenterCd = array[1]; //项目公司ID
					if(StringUtils.isBlank(sortIds)){
						sortIds = tmpShopSortId;
					}else{
						String sIds = sortIds;
						if(sIds.indexOf(tmpShopSortId) == -1){
							sortIds = sIds+","+tmpShopSortId;
						}
					}
					if(StringUtils.isBlank(orgCds)){
						orgCds = tmpCenterCd;
					}else{
						String pIds = orgCds;
						if(pIds.indexOf(tmpCenterCd) == -1){
							orgCds = pIds+","+tmpCenterCd;
						}
					}
				}
			}
		}
		shopSortId = sortIds;
		centerCd = orgCds;
	}
	
	/**
	 * 导出商家库
	 * @return
	 */
	public String doExportShop() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		List<BisShop> results =bisShopManager.find(shopQueryHql(param), param);
		for(BisShop shopVo:results){
			if(shopVo.getManageCd()!=null){
				if("1".equals(shopVo.getManageCd())){
					shopVo.setManageCd("主力店");
				}else if("2".equals(shopVo.getManageCd())){
					shopVo.setManageCd("次主力店");
				}else{
					shopVo.setManageCd("小商铺");
				}
			}
			//商家类别
			if(shopVo.getBisShopSortRels()!=null&&shopVo.getBisShopSortRels().size()>0){
				StringBuffer sortName=new StringBuffer("");
				for(int i=0;i<shopVo.getBisShopSortRels().size();i++){
					BisShopSortRel rel =shopVo.getBisShopSortRels().get(i);
					sortName.append(rel.getBisShopSort().getSortName());
					if(i!=(shopVo.getBisShopSortRels().size()-1)) {
						sortName.append("->");
					}
				}
				shopVo.setCorrival(sortName.toString());
			}
			//经销商
			if(shopVo.getBisShopConns()!=null&&shopVo.getBisShopConns().size()>0){
				shopVo.setBusinessDistrict(shopVo.getBisShopConns().get(0).getPartName());
			}
		}
		Map beans = new HashMap();
		beans.put("result", results);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		is = JXLExcelUtil.initJxlsInputStream(beans, "bisShop.xls");
		String fileName = "商家库-" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	
	private String shopQueryHql(Map<String, Object> param) throws Exception {
		// 初始化查询参数：获取选中的'商家类别ID'和'项目公司orgCd'
		this.initQueryCd();
		StringBuffer hql = new StringBuffer().append("select a from BisShop a where 1=1 ");
		if (StringUtils.isNotBlank(quickQueryText)) {
			hql.append(" and (a.nameCn like :quickQuery or a.nameEn like :quickQuery or a.companyName like:quickQuery)");
			param.put("quickQuery", "%" + quickQueryText + "%");
		} else {
			if (StringUtils.isNotBlank(nameCn)) {
				hql.append(" and a.nameCn like :nameCn");
				param.put("nameCn", "%" + nameCn + "%");
			}
			if (StringUtils.isNotBlank(nameEn)) {
				hql.append(" and a.nameEn like :nameEn");
				param.put("nameEn", "%" + nameEn + "%");
			}
			if (StringUtils.isNotBlank(companyName)) {
				hql.append(" and a.companyName like:companyName");
				param.put("companyName", "%" + companyName + "%");
			}
		}
		if (StringUtils.isNotBlank(selectManageCd)) {
			hql.append(" and a.manageCd = :selectManageCd");
			param.put("selectManageCd", selectManageCd);
		}
		if (StringUtils.isNotBlank(selectShopType)) {
			hql.append(" and a.shopTypeCd =:selectShopType");
			param.put("selectManageCd", selectManageCd);
		}
		if (StringUtils.isNotBlank(shopAudit)) {
			if (!"4".equals(shopAudit)) {
				hql.append(" and a.bisShopAudit=:shopAudit");
				param.put("shopAudit", shopAudit);
				hql.append(" and (a.deleteBl =:deleteBl)");
				param.put("deleteBl", "0");
			} else {
				hql.append(" and (a.deleteBl =:deleteBl)");
				param.put("deleteBl", "2");
			}
		} else {
			hql.append(" and (a.deleteBl =:deleteBl)");
			param.put("deleteBl", "0");
		}
		hql.append(" and (");
		// 在or条件里面的判断标识，若无条件，则hql append 1=1
		boolean condBl = false;
		
		//由于使用了新的一套权限，所以代码得修改 -update by liuzhihui 2012-07-20
		//如果没有'查询全部'权限，只查所属于自己项目公司的数据
		if(!SpringSecurityUtils.hasRole("A_SHOP_ALL_VIEW")){ 
			String secuCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			if (!condBl) {
				condBl = true;
				hql.append(" a.bisShopDept=:secuCenterCd");
			} else {
				hql.append(" or a.bisShopDept=:secuCenterCd");
			}
			param.put("secuCenterCd", secuCenterCd);
			// 搜索业务员为自己的信息
			if (StringUtils.isBlank(salesman)) {
				if (!condBl) {
					hql.append(" a.salesman=:uiid");
					condBl = true;
				} else {
					hql.append(" or a.salesman=:uiid");
				}
				param.put("uiid", SpringSecurityUtils.getCurrentUiid());
			}
		}
		/*
		// 如果不是搜索全部，则把该有的权限搜索出来，否则开放权限
		if (!SpringSecurityUtils.hasRole("A_SHOP_QUERY_ALL")) {
			// 如果只搜索部门，则只显示该用户部门下得数据
			if (SpringSecurityUtils.hasRole("A_SHOP_QUERY_DEPA")) {
				String secuCenterCd = SpringSecurityUtils.getCurrentCenterCd();
				if (!condBl) {
					condBl = true;
					hql.append(" a.bisShopDept=:secuCenterCd");
				} else {
					hql.append(" or a.bisShopDept=:secuCenterCd");
				}
				param.put("secuCenterCd", secuCenterCd);
			}
			// 如果没有选择经营性质，则按照所分配的权限来显示数据
			// if(StringUtils.isBlank(selectManageCd)){
			if (SpringSecurityUtils.hasRole("A_SHOP_QUERY_MAIN")) {
				if (!condBl) {
					hql.append(" a.manageCd = :manageCd1 or a.manageCd =:manageCd2");
					condBl = true;
				} else {
					hql.append(" or a.manageCd = :manageCd1 or a.manageCd =:manageCd2");
				}
				param.put("manageCd1", "1");
				param.put("manageCd2", "2");
			}
			if (SpringSecurityUtils.hasRole("A_SHOP_QUERY_UNIM")) {
				if (!condBl) {
					hql.append(" a.manageCd = :manageCd3");
					condBl = true;
				} else {
					hql.append(" or a.manageCd = :manageCd3");
				}
				param.put("manageCd3", "3");
			}
			// }
			// 搜索业务员为自己的信息
			if (StringUtils.isBlank(salesman)) {
				if (!condBl) {
					hql.append(" a.salesman=:uiid");
					condBl = true;
				} else {
					hql.append(" or a.salesman=:uiid");
				}
				param.put("uiid", SpringSecurityUtils.getCurrentUiid());
			}

		}*/
		if (!condBl) {
			hql.append("1=1");
			condBl = true;
		}
		hql.append(" )");
		if (StringUtils.isNotBlank(centerCd)) {
			hql.append(" and (");
			String[] centers = centerCd.split(",");
			for (int i = 0; i < centers.length; i++) {
				hql.append(" (a.bisShopDept=:centers").append(i).append(" or a.createdDeptCd=:centers").append(i).append(")");
				param.put("centers" + i, centers[i]);
				if (i != (centers.length - 1)) {
					hql.append(" or ");
				}
			}
			hql.append(" )");
		}

		if (StringUtils.isNotBlank(salesman)) {
			hql.append(" and a.salesman=:salesman");
			param.put("salesman", salesman);
		}
		if (StringUtils.isNotBlank(createDate1)) {
			hql.append(" and a.createdDate >= :createDate1");
			param.put("createDate1", DateOperator.parse(createDate1, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(createDate2)) {
			hql.append(" and a.createdDate <= :createDate2");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate = DateOperator.addDays(format.parse(createDate2), 1);
			param.put("createDate2", DateOperator.parse(format.format(endDate), "yyyy-MM-dd"));
		}
		if (shopSortId != null && !"".equals(shopSortId)) {
			hql.append(" and exists(select 1 from BisShopSortRel b where a.bisShopId=b.bisShop.bisShopId ");
			String[] types = shopSortId.replaceAll(" ", "").split(",");
			hql.append(" and (");
			for (int i = 0; i < types.length; i++) {
				hql.append(" b.bisShopSort.bisShopSortId =:types").append(i);
				param.put("types" + i, types[i]);
				if (i != (types.length - 1)) {
					hql.append(" or ");
				}
			}
			hql.append(" ))");
		}
		hql.append(" order by a.manageCd asc,a.bisShopAudit desc,a.bisShopId desc ");
		return hql.toString();
	}

	/**
	 * 合并功能List
	 * 
	 * @return
	 */
	public String mergeList() throws Exception {
		this.initQueryCd();
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer().append("select a from BisShop a where 1=1 ");
		hql.append(" and (a.deleteBl =:deleteBl)");
		param.put("deleteBl", "0");
		if (StringUtils.isNotBlank(quickQueryText)) {
			hql.append(" and (a.nameCn like :quickQuery or a.nameEn like :quickQuery or a.companyName like:quickQuery)");
			param.put("quickQuery", "%" + quickQueryText + "%");
		} else {
			if (StringUtils.isNotBlank(nameCn)) {
				hql.append(" and a.nameCn like :nameCn");
				param.put("nameCn", "%" + nameCn + "%");
			}
			if (StringUtils.isNotBlank(nameEn)) {
				hql.append(" and a.nameEn like :nameEn");
				param.put("nameEn", "%" + nameEn + "%");
			}
			if (StringUtils.isNotBlank(companyName)) {
				hql.append(" and a.companyName like:companyName");
				param.put("companyName", "%" + companyName + "%");
			}
		}
		if (StringUtils.isNotBlank(selectManageCd)) {
			hql.append(" and a.manageCd = :selectManageCd");
			param.put("selectManageCd", selectManageCd);
		}
		if (StringUtils.isNotBlank(selectShopType)) {
			hql.append(" and a.shopTypeCd =:selectShopType");
			param.put("selectManageCd", selectManageCd);
		}
		if (StringUtils.isNotBlank(salesman)) {
			hql.append(" and a.salesman=:salesman");
			param.put("salesman", salesman);
		}
		if (StringUtils.isNotBlank(shopAudit)) {
			hql.append(" and a.bisShopAudit=:shopAudit");
			param.put("shopAudit", shopAudit);
		}
		if (StringUtils.isNotBlank(centerCd)) {
			String[] centers = centerCd.split(",");
			hql.append(" and (");
			for (int i = 0; i < centers.length; i++) {
				hql.append(" (a.bisShopDept=:centers").append(i).append(" or a.createdDeptCd=:centers").append(i).append(")");
				param.put("centers" + i, centers[i]);
				if (i != (centers.length - 1)) {
					hql.append(" or ");
				}
			}
			hql.append(" )");
			// hql.append(" and (a.bisShopDept=:centerCd or a.createdDeptCd:centerCd)");

		}
		if (StringUtils.isNotBlank(createDate1)) {
			hql.append(" and a.createdDate >= :createDate1");
			param.put("createDate1", DateOperator.parse(createDate1, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(createDate2)) {
			hql.append(" and a.createdDate <= :createDate2");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate = DateOperator.addDays(format.parse(createDate2), 1);
			param.put("createDate2", DateOperator.parse(format.format(endDate), "yyyy-MM-dd"));
		}
		// 如果mergeId有值，则查出id不为mergeId的数据
		if (StringUtils.isNotBlank(mergeId)) {
			hql.append(" and a.bisShopId!=:bisShopId");
			param.put("bisShopId", mergeId);
		}
		if (shopSortId != null && !"".equals(shopSortId)) {
			hql.append(" and exists(select 1 from BisShopSortRel b where a.bisShopId=b.bisShop.bisShopId ");
			String[] types = shopSortId.replaceAll(" ", "").split(",");
			hql.append(" and (");
			for (int i = 0; i < types.length; i++) {
				hql.append(" b.bisShopSort.bisShopSortId =:types").append(i);
				param.put("types" + i, types[i]);
				if (i != (types.length - 1)) {
					hql.append(" or ");
				}
			}
			hql.append(" ))");
		}
		hql.append(" order by a.manageCd asc,a.bisShopAudit desc,a.bisShopId desc ");
		page.setPageNo(currentPageNo);
		/*
		 * page.setOrderBy("a.manageCd"); page.setOrder(Page.DESC);
		 */
		page = bisShopManager.findPage(page, hql.toString(), param);
		return "mergeList";

	}

	/**
	 * 合并选择好的商家数据
	 */
	public void merge() {

		String batchData = Struts2Utils.getParameter("batchData");
		if (StringUtils.isNotBlank(mergeId)) {
			if (batchData.indexOf(mergeId) >= 0) {
				Struts2Utils.renderText("haveMergeId");
			}
			String succ = bisShopManager.merge(batchData, mergeId);
			if (StringUtils.isNotBlank(succ)) {
				Struts2Utils.renderText(succ);
			} else {
				Struts2Utils.renderText("请选择合并的记录");
			}
		}
	}

	/**
	 * 合并查看原数据
	 */
	public String mergeQueryByOne() {
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> param = new HashMap<String, Object>();
			String hql = "select a from BisShopBackup a where a.orgShopId=:orgShopId";
			param.put("orgShopId", getId());
			List<BisShopBackup> backupList = bisShopBackupManager.find(hql, param);
			if (backupList != null && backupList.size() > 0) {
				setId(backupList.get(0).getMergeShopId());
				return "show";
			}
		}
		return null;
	}

	/**
	 * 搜索存在的中文名称的公司
	 */
	public void existsShopList() {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer().append("select a from BisShop a where 1=1 ");
		hql.append(" and (a.deleteBl =:deleteBl)");
		param.put("deleteBl", "0");
		if (StringUtils.isNotBlank(nameCn)) {
			hql.append(" and a.nameCn like :nameCn");
			param.put("nameCn", "%" + nameCn + "%");
		}
		if (StringUtils.isNotBlank(getId())) {
			hql.append(" and a.bisShopId !=:bisShopId");
			param.put("bisShopId", getId());
		}
		page.setPageNo(currentPageNo);
		page = bisShopManager.findPage(page, hql.toString(), param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (BisShop bisShop : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("nameCn", bisShop.getNameCn());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisShopManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new BisShop();
		}
	}

	@Override
	public String save() throws Exception {
		if (shopSortId != null && !"".equals(shopSortId)) {
			String[] shopSort = shopSortId.split(",");
			List<BisShopSortRel> sortRelList = new ArrayList<BisShopSortRel>();
			for (String sort : shopSort) {
				BisShopSortRel bisRel = new BisShopSortRel();
				BisShopSort bisShopSort = bisShopSortManager.getEntity(sort);
				bisRel.setBisShopSort(bisShopSort);
				// bisRel.setBisShop(entity);
				sortRelList.add(bisRel);
			}
			// 若业务员为空，则保存为当前操作员
			if (StringUtils.isBlank(entity.getSalesman())) {
				entity.setSalesman(SpringSecurityUtils.getCurrentUiid());
			}
			entity.setBisShopSortRels(sortRelList);
		}
		// 搜索是否有LOGO附件
		if (StringUtils.isNotBlank(entity.getLogo())) {
			List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getLogo());
			if (file == null || file.size() <= 0) {
				entity.setLogo("");
			}
		}
		// 搜索是否有门店图片附件
		if (StringUtils.isNotBlank(entity.getStoreImg())) {
			List<AppAttachFile> file = appAttachFileManager.getAttaFileByBizEntityId(entity.getStoreImg());
			if (file == null || file.size() <= 0) {
				entity.setStoreImg("");
			}
		}
		// 若审核状态没值，则为未审核：0
		if (StringUtils.isBlank(entity.getBisShopAudit())) {
			entity.setBisShopAudit("0");
		}
		// 如果商家库项目为空则添加公司
		/*
		 * WsPlasOrg org =
		 * SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX); if
		 * (org == null) { centerCd = SpringSecurityUtils.getCurrentCenterCd();
		 * } else { centerCd = org.getOrgCd(); }
		 */
		// 如果删除字段为空，则默认为0,删除字段为2则表示合并了数据
		if (StringUtils.isBlank(entity.getDeleteBl())) {
			entity.setDeleteBl("0");
		}
		if (StringUtils.isBlank(entity.getBisShopDept())) {
			entity.setBisShopDept(SpringSecurityUtils.getCurrentCenterCd());
		}
		bisShopManager.saveBisShop(entity);
		setId(entity.getBisShopId());
		// TODO Auto-generated method stub
		return "show";
	}

	public void sortTreeByQuery() {
		if (StringUtils.isNotBlank(getId())) {
			BisShop bisShop = bisShopManager.getEntity(getId());
			TreeNode node = new TreeNode();
			node.setId("0");
			node.setText("商家类别");
			node.setOrderNo(new Long(0));
			node.setFinType("1");
			List<BisShopSortRel> bisShopSortRels = bisShop.getBisShopSortRels();
			List<BisShopSort> sortList = new ArrayList<BisShopSort>();
			for (BisShopSortRel rel : bisShopSortRels) {
				sortList.add(rel.getBisShopSort());
			}
			node.setChildren(bisShopSortManager.loadChildrenNode(node, false, sortList, ""));
			Struts2Utils.renderJson(node);
		}
	}

	public String report() throws Exception {
		return "report";
	}

	// 修改树节点，把原来选中的树节点勾上
	public void loadTreeCheckSelect() {
		if (StringUtils.isNotBlank(getId())) {
			BisShop bisShop = bisShopManager.getEntity(getId());
			TreeNode node = new TreeNode();
			node.setId("0");
			node.setText("商家类别");
			node.setOrderNo(new Long(0));
			node.setFinType("1");
			List<BisShopSort> allSortList = bisShopSortManager.getAll();
			List<BisShopSortRel> bisShopSortRels = bisShop.getBisShopSortRels();
			List<BisShopSort> sortList = new ArrayList<BisShopSort>();
			for (BisShopSortRel rel : bisShopSortRels) {
				sortList.add(rel.getBisShopSort());
			}
			node.setChildren(bisShopSortManager.loadTreeCheckSelect(node, sortList, allSortList));
			Struts2Utils.renderJson(node);
		}
	}

	/**
	 * 商家模块初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		return RELOAD;
	}

	public void deleteConn() {
		if (StringUtils.isNotBlank(deleteId)) {
			boolean haveDelete = bisShopConnManager.updateShopConn(deleteId);
			if (haveDelete) {
				Struts2Utils.renderText("1");
			} else {
				Struts2Utils.renderText("0");
			}
		}
	}

	/**
	 * 合同中搜索商家
	 */
	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String bisShopAudit = Struts2Utils.getParameter("bisShopAudit");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, Object> param = new HashMap<String, Object>();

		StringBuffer hql2 = new StringBuffer("from BisShop where deleteBl=0 and (nameCn like :likename or nameEn like :likename)");
		param.put("likename", "%" + value + "%");
		if (StringUtils.isNotBlank(bisShopAudit)) {
			// TODO 因唐乔暂时没时间审核，所以，先屏蔽该代码
			// hql2.append(" and contAuditStatus='2' ");
		}
		hql2.append(" order by updatedDate desc ");
		page.setPageSize(10);
		page = bisShopManager.findPage(page, hql2.toString(), param);

		String shopName = null;
		List<BisProject> projectList = bisProjectManager.getAll();
		for (BisShop bisShop : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotBlank(bisShop.getNameCn())) {
				shopName = bisShop.getNameCn();
			} else {
				shopName = bisShop.getNameEn();
			}
			// 显示中心名称
			if (StringUtils.isNotBlank(bisShop.getBisShopDept())) {
				for (BisProject project : projectList) {
					if (project.getOrgCd().equals(bisShop.getBisShopDept())) {
						map.put("projectName", project.getProjectName());
						break;
					}
				}
			}
			if(StringUtils.isBlank(map.get("projectName"))){
				map.put("projectName", "  /");
			}
			map.put("hqAddr", bisShop.getHqAddr());
			map.put("salesman", CodeNameUtil.getUserNameByCd(bisShop.getSalesman()));
			map.put("shopName", StringUtils.trim(shopName.trim()));// 品牌名称
			map.put("companyName", StringUtils.trim(bisShop.getCompanyName()));// 公司名称
			map.put("manageCd", StringUtils.trim(bisShop.getManageCd()));// 公司名称
			map.put("bisShopId", bisShop.getBisShopId()); // 主键
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 搜索经销商列表
	 */
	public void getMapShopConn() throws Exception {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<BisShopConn> list = bisShopConnManager.find("from BisShopConn where bisShop.bisShopId=?", getId());
		for (BisShopConn shopConn : list) {
			map.put(shopConn.getBisShopConnId(), shopConn.getPartName());
		}
		Struts2Utils.renderJson(map);
	}

	/**
	 * 合同中搜索经销商
	 */
	public void quickSearchConn() throws Exception {
		String value = Struts2Utils.getParameter("value");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_partName", value));

		Page<BisShopConn> connPage = new Page<BisShopConn>(15);
		connPage = bisShopConnManager.findPage(connPage, filters);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (BisShopConn shopConn : connPage.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("partName", shopConn.getPartName());// 城市/分公司代理名称
			map.put("bisShopConnId", shopConn.getBisShopConnId()); // 主键
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 搜索商家的唯一性
	 */
	public String shopValidate() {
		List<BisShop> shopList = bisShopManager.shopValidate(nameCn, nameEn);
		if (shopList != null && shopList.size() > 0) {
			Struts2Utils.renderText(String.valueOf(shopList.size()));
		} else {
			Struts2Utils.renderText("0");
		}
		return null;
	}

	/**
	 * 展示公司所在的部门
	 */
	public void showDept() {
		if (StringUtils.isNotBlank(centerCd)) {
			List<WsPlasOrg> orgList = PlasCache.getLogicalDirectOrgListByOrgCd(centerCd);
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (WsPlasOrg org : orgList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("orgCd", org.getOrgCd());// ID
				map.put("orgName", org.getOrgName());
				list.add(map);
			}
			Struts2Utils.renderJson(list);
		}
	}

	/**
	 * 搜索是否有附件
	 */
	public void queryAttach() {
		String attachId = Struts2Utils.getParameter("attachId");
		// 招标文件附件列表
		if (StringUtils.isNotBlank(attachId)) {
			Map<String, Object> values = new HashMap<String, Object>();
			StringBuffer hqlPl = new StringBuffer().append(" from AppAttachFile where statusCd='1' ");
			hqlPl.append(" and bizEntityId =:bizEntityId ");
			values.put("bizEntityId", attachId);
			List<AppAttachFile> attaBaseList = appAttachFileManager.find(hqlPl.toString(), values);
			if (attaBaseList != null && attaBaseList.size() > 0) {
				Struts2Utils.renderText("1");
			} else {
				Struts2Utils.renderText("0");
			}
		}
	}

	/**
	 * load商家搜索里面的部门树有 三类：大客户中心690、招商中心154、各商业公司
	 */
	public void loadDeptTree() {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("部门");
		node.setOrderNo(new Long(0));
		node.setFinType("1");
		node.setChildren(loadChildNodeByRoot(node));

		Struts2Utils.renderJson(node);
	}

	private List<TreeNode> loadChildNodeByRoot(TreeNode node) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		String[] dept = { "154", "512" };
		// 搜索dept下得部门或公司结点，放入treeNode里面
		for (int i = 0; i < dept.length; i++) {
			TreeNode childItem = new TreeNode();
			childItem.setId(dept[i]);
			WsPlasOrg o = PlasCache.getOrgByCd(dept[i]);
			childItem.setText(o.getOrgName());
			childItem.setChildren(loadChildrenNode(node, dept[i]));
			childItem.setChecked("0");// 需要设置默认checked属性
			children.add(childItem);
		}
		return children;
	}

	private List<TreeNode> loadChildrenNode(TreeNode treeNode, String orgCd) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		if (treeNode != null) {
			if (StringUtils.isNotBlank(orgCd)) {
				// 取中心所在的部门
				if ("512".equals(orgCd)) {
					Map<String, Object> param = new HashMap<String, Object>();
					String hql = "select a from BisProject a where 1=1 order by a.sequenceNo";
					List<BisProject> projectList = bisProjectManager.find(hql, param);
					for (BisProject proj : projectList) {
						TreeNode childItem = new TreeNode();
						childItem.setId(proj.getOrgCd());
						childItem.setText(proj.getProjectName());
						childItem.setChecked("0");// 需要设置默认checked属性
						children.add(childItem);
					}

				} else {
					List<WsPlasOrg> orgList = PlasCache.getLogicalDirectOrgListByOrgCd(orgCd);
					if (orgList != null && orgList.size() > 0) {
						for (WsPlasOrg org : orgList) {
							TreeNode childItem = new TreeNode();
							childItem.setId(org.getOrgCd());
							childItem.setText(org.getOrgName());
							childItem.setChecked("0");// 需要设置默认checked属性
							children.add(childItem);
						}
					}
				}
			}
		}
		return children;
	}

	public String getShopSortId() {
		return shopSortId;
	}

	public void setShopSortId(String shopSortId) {
		this.shopSortId = shopSortId;
	}

	public String getNowUiid() {
		return SpringSecurityUtils.getCurrentUiid();
	}

	// 租户性质
	public Map<String, String> getMapBisShopType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_TYPE);
	}

	// 品牌级别
	public Map<String, String> getMapBisBrandLevel() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_BRAND_LEVEL_CD);
	}

	// 合作区域
	public Map<String, String> getMapBisCooperative() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_COOPERATIVE_REGION);
	}

	// 供应商性质
	public Map<String, String> getMapBisSupplier() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SUPPLIER_ATTRIBUTE);
	}

	// 资料验证
	public Map<String, String> getMapInfoVeri() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_INFORCONF);
	}

	// 现场考察结果
	public Map<String, String> getMapExamResu() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_EXAMRESU);
	}

	/**
	 * 履约评估
	 * 
	 * @return
	 */
	public Map<String, String> getMapEval() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_EVAL);
	}

	/**
	 * 商业供方级别
	 * 
	 * @return
	 */
	public Map<String, String> getMapEvaluate() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_EVALUATE);
	}

	//
	// 合作区域多选
	public void getBisCooperative() {
		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(DictContants.BIS_COOPERATIVE_REGION);
		List<Map> list = new ArrayList<Map>();
		for (AppDictData appDictData : appDictType.getAppDictDatas()) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put(appDictData.getDictCd(), appDictData.getDictName());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	public String remindMerge() {

		long l1 = System.currentTimeMillis();
		int num = 2;
		String numStr = Struts2Utils.getParameter("num");
		if (!StringUtils.isBlank(numStr)) {
			num = Integer.valueOf(numStr);
		}
		String hql = "from BisShop where deleteBl='0'";
		Map<String, BisShop> total = new HashMap<String, BisShop>();

		List<BisShop> list = bisShopManager.find(hql);
		for (BisShop shop : list) {
			String name = shop.getNameCn();
			if (StringUtils.isBlank(name) || "0".equals(name) || name.length() < num) {
				continue;
			}
			name = name.trim().replaceAll("[^\u4e00-\u9fa5]", "");

			for (int i = 0; i < name.length() - (num - 1); i++) {
				String subname = name.substring(i, i + num);

				if (total.get(subname) == null) {
					total.put(subname, shop);
				} else {
					if (compareMap.get(subname) == null) {
						List<BisShop> namelist = new ArrayList<BisShop>();
						namelist.add(total.get(subname));
						namelist.add(shop);
						compareMap.put(subname, namelist);
					} else {
						compareMap.get(subname).add(shop);
					}
				}
			}

		}


		long l2 = System.currentTimeMillis();

		return "remindMerge";
	}

	/**
	 * 快速搜索用户
	 */
	public void quickSearchUser() {
		String value = Struts2Utils.getParameter("value");
		List<WsPlasAcct> accts = PlasCache.getAcctActiveList();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int i = 0;
		for (WsPlasAcct acct : accts) {
			if (acct.getUiid().indexOf(value) == 0 || acct.getUserName().indexOf(value) == 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("uiid", acct.getUiid());
				map.put("userName", acct.getUserName());
				list.add(map);
				i++;
			}
			if (i > 10) {
				break;
			}
		}
		Struts2Utils.renderJson(list);
	}

	public void updateSalesmanByOne() {
		String selectedUiid = Struts2Utils.getParameter("selectedUiid");
		if (StringUtils.isNotBlank(getId()) && StringUtils.isNotBlank(selectedUiid)) {
			entity = bisShopManager.getEntity(getId());
			entity.setSalesman(selectedUiid);
			bisShopManager.saveBisShop(entity);
			Struts2Utils.renderText("ok");
		} else {
			Struts2Utils.renderText("no");
		}
	}

	public void updateSalesmanByAll() {
		String selectedUiid = Struts2Utils.getParameter("selectedUiid");
		if (StringUtils.isNotBlank(getId()) && StringUtils.isNotBlank(selectedUiid)) {
			entity = bisShopManager.getEntity(getId());
			String hql = "select a from BisShop a where a.creator='" + entity.getCreator() + "'";
			List<BisShop> shopList = bisShopManager.find(hql);
			for (BisShop shop : shopList) {
				shop.setSalesman(selectedUiid);
				bisShopManager.saveBisShop(shop);
			}
			Struts2Utils.renderText("ok");
		} else {
			Struts2Utils.renderText("no");
		}
	}

	/**
	 * 根据权限，新增经营性质，如同时有新增主次主力店、小商铺的权限
	 */
	public static Map<String, String> getMapShopManageType() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "--选择--");
		// 如果有'商家库新增-总部(A_SHOP_NEW_HQ)'权限则都显示，只有'商家库新增-项目(A_SHOP_NEW_PRO)'权限则只显示小商铺。
		if (SpringSecurityUtils.hasRole("A_SHOP_NEW_HQ")) {
			map.put("1", "主力店");
			map.put("2", "次主力店");
		}
		if (SpringSecurityUtils.hasRole("A_SHOP_NEW_PRO") || SpringSecurityUtils.hasRole("A_SHOP_NEW_HQ")) {
			map.put("3", "小商铺");
		}
		return map;
	}
	
	/**
	 * 取得商家级别
	 */
	public static Map<String, String> getMapShopLevel() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "--选择--");
		map = DictMapUtil.getMapShopLevel();
		return map;
	}
	
	/**
	 * 取得考察等级
	 */
	public static Map<String, String> getMapShopInvestLevel() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "--选择--");
		map = DictMapUtil.getMapShopInvestLevel();
		StringUtils.isNotBlank("ss");
		return map;
	}

	/**
	 * 得到34个省、直辖市、自治区和特别行政区(数据字典取)
	 * @return
	 * @throws Exception
	 */
	public String getCooperateAreaList() throws Exception{
		String cooperateAreaCd = Struts2Utils.getParameter("cooperateAreaCd");
		String cooperateArea = Struts2Utils.getParameter("cooperateArea");
		Struts2Utils.getRequest().setAttribute("cooperateAreaCd", cooperateAreaCd);
		Struts2Utils.getRequest().setAttribute("cooperateArea", cooperateArea);
		mapProvince = DictMapUtil.getMapProvince();
		mapProvince.remove("");
		return "area";
	}
	/**
	 * 合并商家库页面
	 * 
	 * @return
	 */
	public String mergeQuery() {
		return "merge";
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getSelectManageCd() {
		return selectManageCd;
	}

	public void setSelectManageCd(String selectManageCd) {
		this.selectManageCd = selectManageCd;
	}

	public String getSelectShopType() {
		return selectShopType;
	}

	public void setSelectShopType(String selectShopType) {
		this.selectShopType = selectShopType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAreaCooperStr() {
		return areaCooperStr;
	}

	public void setAreaCooperStr(String areaCooperStr) {
		this.areaCooperStr = areaCooperStr;
	}

	public void setShopAudit(String shopAudit) {
		this.shopAudit = shopAudit;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public String getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}

	public String getCreateDate1() {
		return createDate1;
	}

	public void setCreateDate1(String createDate1) {
		this.createDate1 = createDate1;
	}

	public String getCreateDate2() {
		return createDate2;
	}

	public void setCreateDate2(String createDate2) {
		this.createDate2 = createDate2;
	}

	public Map<String, List<BisShop>> getCompareMap() {
		return compareMap;
	}

	public String getQuickQueryText() {
		return quickQueryText;
	}

	public void setQuickQueryText(String quickQueryText) {
		this.quickQueryText = quickQueryText;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getHaveEditor() {
		return haveEditor;
	}

	public void setHaveEditor(String haveEditor) {
		this.haveEditor = haveEditor;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public Map<String, String> getMapProvince() {
		return mapProvince;
	}

	public void setMapProvince(Map<String, String> mapProvince) {
		this.mapProvince = mapProvince;
	}
	
}
