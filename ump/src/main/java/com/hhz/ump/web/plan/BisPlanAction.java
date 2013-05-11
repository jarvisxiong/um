package com.hhz.ump.web.plan;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.uums.entity.ws.WsAppDictData;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/plan")
public class BisPlanAction extends ActionSupport {

	private static final long serialVersionUID = -4963581552723264673L;

	private Map<String, List<WsPlasOrg>> orgMap;

	private List<WsPlasOrg> fetchAllProjects;
	private String projectCd;
	private Map<String, String> mapProjectsType;
	private int now_year;
	private int now_month;

	/**
	 * 商业计划平台入口
	 * @return
	 * @throws Exception
	 */
	public String portal() throws Exception {
		this.orgMap = this.buildOrgMap();
		if (0==now_year) {
			now_year = DateOperator.getYear(new Date());
		}
		if (0==now_month) {
			now_month = DateOperator.getMonth12(new Date());
		}
		if(StringUtils.isNotBlank(SpringSecurityUtils.getCurrentCenterCd())){
			projectCd=SpringSecurityUtils.getCurrentCenterCd();
			if(projectCd.indexOf("155,156,154,157,869,439,453,454")<0){
				projectCd="153";
			}
		}else{
			projectCd="153";
		}
		return "portal";
	}
	
	public String work() throws Exception {
		this.orgMap = this.buildOrgMap();
		if (0==now_year) {
			now_year = DateOperator.getYear(new Date());
		}
		if (0==now_month) {
			now_month = DateOperator.getMonth12(new Date());
		}
		return "portalWork";
	}
	
	public String levelFour() throws Exception {
		if (0==now_year) {
			now_year = DateOperator.getYear(new Date());
		}
		if (0==now_month) {
			now_month = DateOperator.getMonth12(new Date());
		}
		return "levelFour";
	}
	
	
	public String business() throws Exception {
		this.fetchAllProjects = this.fetchAllProjects();
		if (0==now_year) {
			now_year = DateOperator.getYear(new Date());
		}
		if (0==now_month) {
			now_month = DateOperator.getMonth12(new Date());
		}
		return "portalBusiness";
	}
	
	
	public String center() throws Exception {
		if (0==now_year) {
			now_year = DateOperator.getYear(new Date());
		}
		if (0==now_month) {
			now_month = DateOperator.getMonth12(new Date());
		}
		return "portalCenter";
	}
	
	public Map<String, List<WsPlasOrg>> getOrgMap() {
		return orgMap;
	}
	


	public List<WsPlasOrg> getFetchAllProjects() {
		return fetchAllProjects;
	}
	
	
	
	
	/**
	 * 获取大小中心分组
	 * 
	 * @return
	 */
	private Map<String, List<WsPlasOrg>> buildOrgMap() {
		Map<String, List<WsPlasOrg>> map = new LinkedHashMap<String, List<WsPlasOrg>>();

		List<WsPlasOrg> tempOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
		CollectionHelper.sortList(tempOrgs, "sequenceNo");// 排序

		for (WsPlasOrg wsUaapOrg : tempOrgs) {
			if(!wsUaapOrg.getOrgCd().equalsIgnoreCase("153")){
				continue;
			}
			if (wsUaapOrg.getOrgBizCd().equals(Constants.ORG_BIZ_CD_DCGS)
					||wsUaapOrg.getOrgName().equals("厦门城市公司")
					||wsUaapOrg.getOrgName().equals("投研(已删除)")) {
				continue;
			}
			List<WsPlasOrg> list = PlasCache.getLogicalDirectOrgListByOrgCd(wsUaapOrg.getOrgCd(),Constants.ORG_TYPE_CD_ZX);
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
				if("1003".equalsIgnoreCase(wsUaapOrg.getOrgBizCd())){
					//投研，新增商业地产研究院，投资发展中心
					boolean if_has = false;
					
					WsPlasOrg wsUaapOrgTemp2 = null;
					for(int i=0;null!=list&&i<list.size();i++){
						wsUaapOrgTemp2 =list.get(i);
						if("126".equalsIgnoreCase(wsUaapOrgTemp2.getOrgCd())){
							if_has = true;
							break;
						}
					}
					if(!if_has){
						WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
						wsUaapOrgTemp.setOrgCd("126");
						wsUaapOrgTemp.setOrgName("商业地产研究院(已删除)");
						list.add(wsUaapOrgTemp);
						
						wsUaapOrgTemp = new WsPlasOrg();
						wsUaapOrgTemp.setOrgCd("11");
						wsUaapOrgTemp.setOrgName("投资发展中心(已删除)");
						list.add(wsUaapOrgTemp);
					}
				}
				if("1004".equalsIgnoreCase(wsUaapOrg.getOrgBizCd())){
					//开发，新增营销管理中心
					boolean if_has = false;
					for(int i=0;null!=list&&i<list.size();i++){
						WsPlasOrg wsUaapOrgTemp2 = (WsPlasOrg)list.get(i);
						if("133".equalsIgnoreCase(wsUaapOrgTemp2.getOrgCd())){
							if_has = true;
							break;
						}
					}
					if(!if_has){
						WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
						wsUaapOrgTemp.setOrgCd("133");
						wsUaapOrgTemp.setOrgName("营销管理中心(已删除)");
						list.add(wsUaapOrgTemp);
					}
				}
				if("1002".equalsIgnoreCase(wsUaapOrg.getOrgBizCd())){
					//财务，新增投资关系部
					boolean if_has = false;
					for(int i=0;null!=list&&i<list.size();i++){
						WsPlasOrg wsUaapOrgTemp2 = (WsPlasOrg)list.get(i);
						if("133".equalsIgnoreCase(wsUaapOrgTemp2.getOrgCd())){
							if_has = true;
							break;
						}
					}
					if(!if_has){
						WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
						wsUaapOrgTemp.setOrgCd("125");
						wsUaapOrgTemp.setOrgName("投资关系部(已删除)");
						list.add(wsUaapOrgTemp);
					}
				}
			/*	if("事业管理中心".equalsIgnoreCase(wsUaapOrg.getOrgName())){
					//事业管理中心，不能修改
					if(null!=list){
						list.clear();
					}
					WsPlasOrg wsUaapOrgTemp = new WsPlasOrg();
					wsUaapOrgTemp.setOrgCd("100000");
					wsUaapOrgTemp.setOrgName("事业管理中心");
					list.add(wsUaapOrgTemp);
				}*/
				map.put(wsUaapOrg.getOrgName(), list);
			} else {
				List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
				orgList.add(wsUaapOrg);
				map.put(wsUaapOrg.getOrgName(), orgList);
			}
		}

		return map;
	}

	public List<WsPlasOrg> fetchAllProjects() {

		// 搜索所有项目
		 String planTypeCd = "24";
		// 搜索所有项目
		WsPlasOrg org = null;
		mapProjectsType = new TreeMap<String, String>();
		List<WsPlasOrg> allProjs = new ArrayList<WsPlasOrg>();
		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd)
				|| "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
			List<WsAppDictData> listDict = PlasCache.getDictDataList("LAND_BIS_REL");
			for (int i = 0; null != listDict && i < listDict.size(); i++) {
				WsAppDictData dict = listDict.get(i);
				org = new WsPlasOrg();
				org.setOrgName(dict.getDictName());
				org.setOrgCd(dict.getRemark());
				if (!GlobalConstants.WYS_ORG_CD.equals(dict.getRemark())) {
					mapProjectsType.put(dict.getRemark(), dict.getDictName());
					if (SpringSecurityUtils.getCurrentCenterCd().equals(dict.getDictCd())) {
						projectCd = dict.getRemark();
					}
				}
				allProjs.add(org);
			}
			if ("24".equalsIgnoreCase(planTypeCd)) {
				if (!mapProjectsType.containsKey("0000")) {
					mapProjectsType.put("0000", "请选择项目");
				}
			}
		} else {
			List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
			for (WsPlasOrg m : list) {
				org = new WsPlasOrg();
				org.setOrgName(m.getOrgName());
				org.setOrgCd(m.getOrgCd());
				if (!GlobalConstants.WYS_ORG_CD.equals(m.getOrgCd())) {
					mapProjectsType.put(m.getOrgCd(), m.getOrgName());
					if (SpringSecurityUtils.getCurrentCenterCd().equals(m.getOrgCd())) {
						projectCd = m.getOrgCd();
					}
				}
				allProjs.add(org);
			}
			if ("1".equalsIgnoreCase(planTypeCd)) {
				if (!mapProjectsType.containsKey("0000")) {
					mapProjectsType.put("0000", "请选择项目");
				}
			}
		}

		return allProjs;
	}


	public String getProjectCd() {
		return projectCd;
	}

	public Map<String, String> getMapProjectsType() {
		return mapProjectsType;
	}

	public int getNow_year() {
		return now_year;
	}

	public void setNow_year(int nowYear) {
		now_year = nowYear;
	}

	public int getNow_month() {
		return now_month;
	}

	public void setNow_month(int nowMonth) {
		now_month = nowMonth;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
}
