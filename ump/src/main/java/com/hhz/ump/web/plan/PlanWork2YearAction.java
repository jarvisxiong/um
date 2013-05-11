package com.hhz.ump.web.plan;

import java.math.BigDecimal;
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
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.PlanWork2YearManager;
import com.hhz.ump.entity.plan.PlanWork2Year;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 年度工作计划
 * @author lujy 2011-2-12
 * 计划依据accordingType
1：上年计划延伸
2：本年公司要求
3：分管领导要求
4：中心内部要求
 */
@Namespace("/plan")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "planWork2Year.action", type = "redirect", params = { "centerCd", "${centerCd}" }),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "planWork2Year.action", type = "redirect") })
public class PlanWork2YearAction extends CrudActionSupport<PlanWork2Year> {
	private static final long serialVersionUID = 8071052318603147332L;
	private PlanWork2Year entity;

	private int serialOrder;
	
	private String[] chkIds;

	private Integer[] chkOrders;

	@Autowired
	private PlanWork2YearManager planWork2YearManager;

	private List<WsPlasOrg> uaapOrgs;

	private String centerCd;

	private int now_year = 0; // 当前的年份
	private boolean cannotChangeDept = true;	//是否不能改变中心

	private Map<String, List<WsPlasOrg>> orgMap;

	private String isEditOrg;
	
	private Map<String, String> mapContentTips = new HashMap<String, String>();	//保存content的title,包含content和前三条留言记录


	/**
	 * 计划管理统一入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		String result = null;
		return result;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		planWork2YearManager.deletePlanWork2Year(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planWork2YearManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}
	
	public String getAllPlan() throws Exception {
		// getRelationCenters();
		orgMap = this.buildOrgMap();
		String from_centerCd = Struts2Utils.getRequest().getParameter("centerCd");
		if (null != from_centerCd && !"".equalsIgnoreCase(from_centerCd)) {
			centerCd = from_centerCd;
		} else {
			String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			if (curCenterCd == null||"".equals(curCenterCd)) {
				centerCd = SpringSecurityUtils.getCurrentDeptCd();
			} else {
				WsPlasOrg org = SpringSecurityUtils
				.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
				if(org!=null){
					centerCd = org.getOrgCd();
				}else{
					centerCd = SpringSecurityUtils.getCurrentCenterCd();
				}
			}
		}
		now_year = DateOperator.getYear(new Date());

		if(SpringSecurityUtils.hasRole("A_PLAN_WORK2_ADMIN")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_CENTER")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_OFFICE")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_VICE")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_PROJECT")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_CEO")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_VIEW")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK_BISA")){
			cannotChangeDept = false;
		}
		return SUCCESS;
	}

	//重新生成所有的年计划给月计划
	public String createAll() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		List<PlanWork2Year> list = planWork2YearManager.find(filters);
		for(PlanWork2Year planWork2Year:list){
			planWork2YearManager.savePlanWork2Year(planWork2Year,"","");
		}
		return null;
	}

	/**
	 * 获取大小中心分组
	 * 
	 * @return
	 */
	private Map<String, List<WsPlasOrg>> buildOrgMap() {
		Map<String, List<WsPlasOrg>> map = new LinkedHashMap<String, List<WsPlasOrg>>();
		List<WsPlasOrg> uaapOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
		CollectionHelper.sortList(uaapOrgs, "sequenceNo");// 排序

		List<WsPlasOrg> list = null;
		for (WsPlasOrg wsUaapOrg : uaapOrgs) {
			if (wsUaapOrg.getOrgBizCd().equals(Constants.ORG_BIZ_CD_DCGS)
					||wsUaapOrg.getOrgName().equals("厦门城市公司")
					||wsUaapOrg.getOrgName().equals("投研(已删除)")) {
				continue;
			}
			list = PlasCache.getLogicalDirectOrgListByOrgCd(wsUaapOrg.getOrgCd(),Constants.ORG_TYPE_CD_ZX);
			if (null != list && list.size() > 0) {
				if("1001".equalsIgnoreCase(wsUaapOrg.getOrgBizCd())){
					//行政，新增总裁办
					boolean if_has = false;
					
					WsPlasOrg wsUaapOrgTemp2 = null;
					for(int i=0;null!=list&&i<list.size();i++){
						wsUaapOrgTemp2 =list.get(i);
						if("7".equalsIgnoreCase(wsUaapOrgTemp2.getOrgCd())){
							if_has = true;
							break;
						}
					}
					if(!if_has){
						WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
						wsUaapOrgTemp.setOrgCd("7");
						wsUaapOrgTemp.setOrgName("总裁办(已删除)");
						list.add(wsUaapOrgTemp);
					}
				}
				if("1004".equalsIgnoreCase(wsUaapOrg.getOrgBizCd())){
					//开发，新增投资发展中心
					boolean if_has = false;
					for(int i=0;null!=list&&i<list.size();i++){
						WsPlasOrg wsUaapOrgTemp2 = (WsPlasOrg)list.get(i);
						if("11".equalsIgnoreCase(wsUaapOrgTemp2.getOrgCd())){
							if_has = true;
							break;
						}
					}
					if(!if_has){
						WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
						wsUaapOrgTemp.setOrgCd("11");
						wsUaapOrgTemp.setOrgName("投资发展中心(已删除)");
						list.add(wsUaapOrgTemp);
					}
				}
				if("事业管理中心".equalsIgnoreCase(wsUaapOrg.getOrgName())){
					//事业管理中心，不能修改
					if(null!=list){
						list.clear();
					}
					WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
					wsUaapOrgTemp.setOrgCd("100000");
					wsUaapOrgTemp.setOrgName("事业管理中心");
					list.add(wsUaapOrgTemp);
				}
				map.put(wsUaapOrg.getOrgName(), list);
			} else {
				List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
				orgList.add(wsUaapOrg);
				map.put(wsUaapOrg.getOrgName(), orgList);
			}
		}

		return map;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//page.setPageSize(999);
		if (null == centerCd || "".equalsIgnoreCase(centerCd)) {
			centerCd = SpringSecurityUtils.getCurrentCenterCd();
		}
		PropertyFilter filter = new PropertyFilter("EQS_centerCd", centerCd);
		filters.add(filter);
		filters.add(new PropertyFilter("EQM_planYear",Integer.toString(now_year)));
		String search_statusCd = Struts2Utils.getParameter("search_statusCd");
		if (search_statusCd != null && !search_statusCd.equalsIgnoreCase("")) {
			filters.add(new PropertyFilter("EQS_statusCd", search_statusCd));
		} else {
			filters.add(new PropertyFilter("INA_statusCd", new Object[] { "", "0", "1" }));
		}
		page.setOrderBy("serialOrder,workTarget,detailStep");
		page.setOrder(Page.ASC + "," + Page.ASC + "," + Page.ASC);

		// 判断是否搜索旧的记录，如果是当周并且当周没有拍照过，显示双周计划，否则显示拍照的记录
		int source_now_year = DateOperator.getYear(new Date());
		Struts2Utils.getRequest().setAttribute("source_now_year", source_now_year);
		page = planWork2YearManager.findPage(page, filters);
		// 生成编号
		Struts2Utils.getRequest().setAttribute("newSerialOrder", planWork2YearManager.getMaxNo(centerCd,now_year));
		Struts2Utils.getRequest().setAttribute("newSerialNumber",
				"NJH-"+now_year+"-" + CodeNameUtil.getDeptShortNameByCd(centerCd) + "-");
		//initIsEditOrg();
		isEditOrg = "1";
		
		return "list";
	}

	private void initIsEditOrg() {
		if (SpringSecurityUtils.hasRole("A_PLAN_WORK2_YEAR")) {
			//List<WsPlasOrg> relaOrgs = PlasCache.getRelationCenterOrgs(SpringSecurityUtils.getCurrentUiid());
			List<WsPlasOrg> relaOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(),"");
			if (relaOrgs!=null) {
				for (WsPlasOrg wsUaapOrg : relaOrgs) {
					if (wsUaapOrg.getOrgTypeCd() != null && !wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)) {
						if (wsUaapOrg.getOrgCd().equals(centerCd)) {
							isEditOrg = "1";
							break;
						}
					}else if(wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)){
						//如果用户在分管下，判断是否是现中心所属的分管
						WsPlasOrg wsUaapOrg2 = PlasCache.getOrgByCd(centerCd);
						if(wsUaapOrg2!=null && wsUaapOrg.getPlasOrgId().equalsIgnoreCase(
								PlasCache.getCenterOrgIdById(wsUaapOrg2.getPlasOrgId())
								)){
							isEditOrg = "1";
							break;
						}
					}else if(wsUaapOrg.getOrgTypeCd() != null && wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_JT)){
						isEditOrg = "1";
						break;
					}
				}
			}
		}
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planWork2YearManager.getEntity(getId());
		} else {
			entity = new PlanWork2Year();
		}
	}

	@Override
	public String save() throws Exception {
		String ifDelete = Struts2Utils.getParameter("ifDelete");
		String ifBack = Struts2Utils.getParameter("ifBack");
		if(null==ifDelete || "null".equalsIgnoreCase(ifDelete)){
			ifDelete = "";
		}
		if(null==ifBack || "null".equalsIgnoreCase(ifBack)){
			ifBack = "";
		}
		planWork2YearManager.savePlanWork2Year(entity,ifDelete,ifBack);
		String userDeptCd = Struts2Utils.getParameter("userDeptCd");
		if (userDeptCd != null && !userDeptCd.equals("")) {
			centerCd = userDeptCd;
		}
		setId(entity.getPlanWork2YearId());
		addActionMessage(getText("common.success"));
		Struts2Utils.renderText(String.valueOf(entity.getPlanWork2YearId()));

		return null;
	}

	public void prepareFetchDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWork2YearManager.getEntity(getId());
			Struts2Utils.getRequest().setAttribute("centerCd", entity.getCenterCd());
		}
	}

	public String fetchDetail() throws Exception {
		return "detail";
	}

	public void prepareFetchMain() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWork2YearManager.getEntity(getId());
			Struts2Utils.getRequest().setAttribute("centerCd", entity.getCenterCd());
		}
	}

	public String fetchMain() throws Exception {
		return "main";
	}

	/*
	 * 获取大中心列表
	 */
	public List<WsPlasOrg> getListFgCenters() {
		// List<WsPlasOrg> fgCenters_return = new ArrayList<WsPlasOrg>();
		List<WsPlasOrg> fgCenters_ordered = new ArrayList<WsPlasOrg>();
		List<WsPlasOrg> uaapOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
//		CollectionHelper.sortList(uaapOrgs, "orgBizCd");// 排序
		int DC_order = -1; // 地产公司在列表中的序号，要把地产公司提取出来排第一个
		WsPlasOrg org = null;
		for (int i = 0; null != uaapOrgs && i < uaapOrgs.size(); i++) {
			org = uaapOrgs.get(i);
			if (org.getOrgBizCd().equals("1099")) {
				DC_order = i;
				fgCenters_ordered.add(org);
			}
		}
		for (int i = 0; null != uaapOrgs && i < uaapOrgs.size(); i++) {
			if (i != DC_order) {
				org =  uaapOrgs.get(i);
				fgCenters_ordered.add(org);
			}
		}

		return fgCenters_ordered;
	}

	/*
	 * 交换顺序
	 */
	public String doExchangeOrder() throws Exception {
		try {
			for (int i = 0; null != chkIds && i < chkIds.length; i++) {
				String planWork2Year_id = chkIds[i];
				entity = planWork2YearManager.getEntity(planWork2Year_id);
				entity.setSerialOrder(new BigDecimal(chkOrders[i]));
				planWork2YearManager.savePlanWork2Year(entity,"","");
			}
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("done");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("error");
		}
		return null;
	}
	
	@Override
	public String input() throws Exception {
		return null;
	}

	
	/**
	 * 获取机构信息
	 * 
	 * @return
	 */
	public List<WsPlasOrg> getUaapOrgs() {
		return uaapOrgs;
	}

	public PlanWork2Year getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public Integer[] getChkOrders() {
		return chkOrders;
	}

	public void setChkOrders(Integer[] chkOrders) {
		this.chkOrders = chkOrders;
	}

	public String[] getChkIds() {
		return chkIds;
	}

	public void setChkIds(String[] chkIds) {
		this.chkIds = chkIds;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public int getNow_year() {
		return now_year;
	}

	public void setNow_year(int nowYear) {
		now_year = nowYear;
	}

	public Map<String, List<WsPlasOrg>> getOrgMap() {
		return orgMap;
	}

	public String getIsEditOrg() {
		return isEditOrg;
	}

	public void setIsEditOrg(String isEditOrg) {
		this.isEditOrg = isEditOrg;
	}
	public Map<String, String> getMapContentTips() {
		return mapContentTips;
	}

	public void setMapContentTips(Map<String, String> mapContentTips) {
		this.mapContentTips = mapContentTips;
	}

	public int getSerialOrder() {
		return serialOrder;
	}

	public void setSerialOrder(int serialOrder) {
		this.serialOrder = serialOrder;
	}
	public boolean isCannotChangeDept() {
		return cannotChangeDept;
	}

}
