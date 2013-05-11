package com.hhz.ump.web.bis;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisProjectLayoutManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisProjectOverviewManager;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisProjectOverview;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商业费用报表
 *
 * @author baolm 2011-7-9
 */
@Namespace("/bis")
@Results({
        @Result(name = BisManageAction.GROUP_REPORT, location = "bis-manage-report.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}"}),
        @Result(name = BisManageAction.PROJECT_REPORT, location = "bis-project-report.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}", "reportType", "${reportType}", "manageCd", "${manageCd}"}),
        @Result(name = BisManageAction.FEE_MANAGE, location = "bis-fact!list.action", type = "redirect", params = {"ifFromReport", "${ifFromReport}", "bisProjectId", "${bisProjectId}", "bisTenantId", "${bisTenantId}", "factYear", "${factYear}", "currDetailName", "${currDetailName}", "dimension", "${dimension}"}),
        @Result(name = BisManageAction.CONT_MANAGE, location = "bis-cont.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}"}),
        @Result(name = BisManageAction.TANENT_INFO, location = "bis-tenant!main.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}"}),
        @Result(name = BisManageAction.SHOP_MANAGE, location = "bis-shop!main.action", type = "redirect"),
        @Result(name = BisManageAction.PROJECT_MANAGE, location = "bis-project!main.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}"}),
        @Result(name = BisManageAction.PROJECT_LAYOUT, location = "bis-project-layout.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}"}),
        @Result(name = BisManageAction.BIS_MANAGE_DAY, location = "bis-manage-day!index.action", type = "redirect", params = {"bisProjectId", "${bisProjectId}"}),
        /*@Result(name = BisManageAction.BIS_MANAGE_DAY_REPORT, location = "bis-manage-day!report.action", type = "redirect")*/
        @Result(name = BisManageAction.BIS_MANAGE_DAY_REPORT, location = "bis-manage!reports.action", type = "redirect")
})
public class BisManageAction extends ActionSupport {
    /**
     *
     */
    private static final long serialVersionUID = -250854422205027521L;

    //跳转路径
    public static final String GROUP_OVERVIEW = "GROUP_OVERVIEW";//集团总况
    public static final String PROJECT_OVERVIEW = "PROJECT_OVERVIEW";//项目总况
    public static final String GROUP_REPORT = "GROUP_REPORT";
    public static final String PROJECT_REPORT = "PROJECT_REPORT";
    public static final String FEE_MANAGE = "FEE_MANAGE";
    public static final String CONT_MANAGE = "CONT_MANAGE";
    public static final String TANENT_INFO = "TANENT_INFO";
    public static final String SHOP_MANAGE = "SHOP_MANAGE";
    public static final String PROJECT_MANAGE = "PROJECT_MANAGE";
    public static final String PROJECT_LAYOUT = "PROJECT_LAYOUT";
    public static final String BIS_MANAGE_DAY = "BIS_MANAGE_DAY";
    public static final String BIS_MANAGE_DAY_REPORT = "BIS_MANAGE_DAY_REPORT";

    //模块编号
    private static final String MODULE_GROUP_REPORT = "1";
    private static final String MODULE_PROJECT_REPORT = "2";
    private static final String MODULE_FEE_MANAGE = "3";
    private static final String MODULE_CONT_MANAGE = "4";
    private static final String MODULE_TANENT_INFO = "5";
    private static final String MODULE_SHOP_MANAGE = "6";
    private static final String MODULE_PROJECT_MANAGE = "7";
    private static final String MODULE_PROJECT_LAYOUT = "8";
    private static final String MODULE_BIS_MANAGE_DAY = "9";


    //项目信息
    private String ifFromReport;
    private String bisProjectId;
    private String bisProjectAreaCd;
    private String bisTenantId;
    private String factYear;
    private String bisProjectName;
    private String module;
    private String defaultModule;//默认页面(权限控制)
    private String dimension;//维度参数

    private String reportType;
    private String reportDate;
    private String manageCd;

    private String conditionType;
    private String startDate;
    private String endDate;

    //权限(需修改)
    private boolean groupRPermi;
    private boolean projectRPermi;
    private boolean feeMPermi;
    private boolean contMPermi;
    private boolean tenantIPermi;
    private boolean shopMPermi;
    private boolean projectMPermi;


    private String layoutId;//业态图
    private String brandLoadId;//品牌落位图
    private String priceApproveId;//价格审批表
    private List<BisFloor> bisFloorList;

    private BisProjectOverview totalInfo; //总况信息
    private BisProjectOverview mainStoreInfo; //主力店信息
    private BisProjectOverview minorStoreInfo; //次主力店信息
    private BisProjectOverview smallStoreInfo; //小商铺信息
    private List<BisProjectOverview> floorInfoList = new ArrayList<BisProjectOverview>(); //楼层信息

    @Autowired
    private BisProjectManager bisProjectManager;

    @Autowired
    private BisFloorManager bisFloorManager;

    @Autowired
    private BisProjectOverviewManager bisProjectOverviewManager;

    @Autowired
    private BisProjectLayoutManager bisProjectLayoutManager;

    /**
     * 初始页面
     */
    @Override
    public String execute() throws Exception {

        buildPermission();

        //2012-3-2日之前 集团用户跳转至 项目 列表页。
        //          之后 需求变更, 有经营日报表权限 则直接 跳转至 经营日报表页，
        //               经营日报表页面 有 切换 项目列表 按钮
        //2012-3-5日修改：
        //  项目级人员 只有能进入 项目详细页 判断提前 。
        if (PROJECT_OVERVIEW.equals(defaultModule))
            return toProjectOverview();
        else if (StringUtils.isBlank(Struts2Utils.getParameter("report")) && SpringSecurityUtils.hasRole("A_BIS_DAY_REPORT"))
            return BIS_MANAGE_DAY_REPORT;
        else if (GROUP_OVERVIEW.equals(defaultModule))
            return SUCCESS;


        /*  if(StringUtils.isBlank(bisProjectId)) {
            BisProject bisProject = bisProjectManager.getCurrProject();
            if(bisProject != null) {
                bisProjectId = bisProject.getBisProjectId();
                bisProjectName = bisProject.getProjectName();
            }
        } else {
            bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
        }

        if(StringUtils.isBlank(defaultModule)) {
            //TODO 无权限
            defaultModule = MODULE_GROUP_REPORT;
        }

        if(MODULE_GROUP_REPORT.equals(defaultModule))
            return GROUP_REPORT;
        else if(MODULE_PROJECT_REPORT.equals(defaultModule))
            return PROJECT_REPORT;
        else if(MODULE_FEE_MANAGE.equals(defaultModule))
            return FEE_MANAGE;
        else if(MODULE_CONT_MANAGE.equals(defaultModule))
            return CONT_MANAGE;
        else if(MODULE_TANENT_INFO.equals(defaultModule))
            return TANENT_INFO;
        else if(MODULE_SHOP_MANAGE.equals(defaultModule))
            return SHOP_MANAGE;
        else if(MODULE_PROJECT_MANAGE.equals(defaultModule))
            return PROJECT_MANAGE;
        else
            return ERROR;
*/
        return SUCCESS;
    }

    public String reports() throws Exception {
        BisProject bisProject = bisProjectManager.getCurrProject();
        if (bisProject != null) {
            bisProjectId = bisProject.getBisProjectId();
            bisProjectName = bisProject.getProjectName();
            bisProjectAreaCd = bisProject.getAreaCd();
        }
        return "reports";
    }

    public List<BisProject> getProjectSouth() {
        return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_SOUTH);
    }

    public List<BisProject> getProjectNorth() {
        return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_NORTH);
    }

    public List<BisProject> getProjectSh() {
        return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_SH);
    }

    public String layout() throws Exception {

        if (MODULE_GROUP_REPORT.equals(module))
            return GROUP_REPORT;
        else if (MODULE_PROJECT_REPORT.equals(module))
            return PROJECT_REPORT;
        else if (MODULE_FEE_MANAGE.equals(module))
            return FEE_MANAGE;
        else if (MODULE_CONT_MANAGE.equals(module))
            return CONT_MANAGE;
        else if (MODULE_TANENT_INFO.equals(module))
            return TANENT_INFO;
        else if (MODULE_SHOP_MANAGE.equals(module))
            return SHOP_MANAGE;
        else if (MODULE_PROJECT_MANAGE.equals(module))
            return PROJECT_MANAGE;
        else if (MODULE_PROJECT_LAYOUT.equals(module))
            return PROJECT_LAYOUT;
        else if (MODULE_BIS_MANAGE_DAY.equals(module))
            return BIS_MANAGE_DAY;
        else
            return ERROR;

    }

    public String toProjectOverview() throws Exception {
        buildPermission();

        //是否是 项目级人员，界面做控制 不能 切换 项目
        if (PROJECT_OVERVIEW.equals(defaultModule)) {
            Struts2Utils.getRequest().setAttribute("isProjectLevel", false);
        } else {
            Struts2Utils.getRequest().setAttribute("isProjectLevel", true);
        }


        if (StringUtils.isBlank(bisProjectId)) {
            BisProject bisProject = bisProjectManager.getCurrProject();
            if (bisProject != null) {
                bisProjectId = bisProject.getBisProjectId();
                bisProjectName = bisProject.getProjectName();
                bisProjectAreaCd = bisProject.getAreaCd();
            }
        } else {
            BisProject bisProject = bisProjectManager.getEntity(bisProjectId);
            bisProjectName = bisProject.getProjectName();
            bisProjectAreaCd = bisProject.getAreaCd();
        }

        if (SpringSecurityUtils.hasRole(GlobalConstants.A_TOTAL_QUERY)) {

            if ("1".equals(conditionType) && !StringUtils.equals(startDate, endDate)) { // 按时间段搜索

                Map<String, BisProjectOverview> floorMap = new LinkedHashMap<String, BisProjectOverview>();

                List<BisProjectOverview> list = bisProjectOverviewManager.getOverviewList(bisProjectId, endDate);
                for (BisProjectOverview overview : list) {
                    if ("1".equals(overview.getDataType())) {
                        totalInfo = overview;
                    } else if ("3".equals(overview.getDataType())) {
                        mainStoreInfo = overview;
                    } else if ("4".equals(overview.getDataType())) {
                        minorStoreInfo = overview;
                    } else if ("5".equals(overview.getDataType())) {
                        smallStoreInfo = overview;
                    } else if ("2".equals(overview.getDataType())) {
                        floorMap.put(overview.getBisFloorId(), overview);
                        // floorInfoList.add(overview);
                    }
                }

                List<Object[]> list2 = bisProjectOverviewManager
                        .getRegionOverviewList(bisProjectId, startDate, endDate);
                for (Object[] obj : list2) {
                    String dataType = (String) obj[0];
                    if (StringUtils.equals(dataType, "1")) {
                        totalInfo.setRentRate((BigDecimal) obj[2]);
                        totalInfo.setRentUnitPrice((BigDecimal) obj[3]);
                        totalInfo.setPropLeave((BigDecimal) obj[4]);
                        totalInfo.setPropSell((BigDecimal) obj[5]);
                        totalInfo.setPropUnitPrice((BigDecimal) obj[6]);
                        totalInfo.setBackRate((BigDecimal) obj[7]);
                        totalInfo.setBackUnitPrice((BigDecimal) obj[8]);
                        totalInfo.setBackPayBalance((BigDecimal) obj[9]);
                        totalInfo.setRentCollectRate((BigDecimal) obj[10]);
                        totalInfo.setEnergyCollectRate((BigDecimal) obj[11]);
                        totalInfo.setPropCollectRate((BigDecimal) obj[12]);
                    } else if (StringUtils.equals(dataType, "3")) {
                        mainStoreInfo.setRentRate((BigDecimal) obj[2]);
                        mainStoreInfo.setBackRate((BigDecimal) obj[7]);
                        mainStoreInfo.setRentUnitPrice((BigDecimal) obj[3]);
                        mainStoreInfo.setRentCollectRate((BigDecimal) obj[10]);
                        mainStoreInfo.setEnergyCollectRate((BigDecimal) obj[11]);
                        mainStoreInfo.setPropCollectRate((BigDecimal) obj[12]);
                    } else if (StringUtils.equals(dataType, "4")) {
                        minorStoreInfo.setRentRate((BigDecimal) obj[2]);
                        minorStoreInfo.setBackRate((BigDecimal) obj[7]);
                        minorStoreInfo.setRentUnitPrice((BigDecimal) obj[3]);
                        minorStoreInfo.setRentCollectRate((BigDecimal) obj[10]);
                        minorStoreInfo.setEnergyCollectRate((BigDecimal) obj[11]);
                        minorStoreInfo.setPropCollectRate((BigDecimal) obj[12]);
                    } else if (StringUtils.equals(dataType, "5")) {
                        smallStoreInfo.setRentRate((BigDecimal) obj[2]);
                        smallStoreInfo.setBackRate((BigDecimal) obj[7]);
                        smallStoreInfo.setRentUnitPrice((BigDecimal) obj[3]);
                        smallStoreInfo.setRentCollectRate((BigDecimal) obj[10]);
                        smallStoreInfo.setEnergyCollectRate((BigDecimal) obj[11]);
                        smallStoreInfo.setPropCollectRate((BigDecimal) obj[12]);
                    } else if (StringUtils.equals(dataType, "2")) {
                        String floorId = (String) obj[1];
                        if (floorMap.get(floorId) != null) {
                            floorMap.get(floorId).setRentRate((BigDecimal) obj[2]);
                            floorMap.get(floorId).setBackRate((BigDecimal) obj[7]);
                            floorMap.get(floorId).setRentUnitPrice((BigDecimal) obj[3]);
                        }
                    }
                }

                floorInfoList.addAll(floorMap.values());

            } else { // 按月搜索

                if (StringUtils.isBlank(reportDate)) {
                    reportDate = DateOperator.formatDate(DateOperator.addMonthes(new Date(), -2), "yyyy-MM");
                    startDate = reportDate;
                    endDate = reportDate;
                }

                List<BisProjectOverview> list = bisProjectOverviewManager.getOverviewList(bisProjectId, reportDate);

                for (BisProjectOverview overview : list) {
                    if ("1".equals(overview.getDataType())) {
                        totalInfo = overview;
                    } else if ("3".equals(overview.getDataType())) {
                        mainStoreInfo = overview;
                    } else if ("4".equals(overview.getDataType())) {
                        minorStoreInfo = overview;
                    } else if ("5".equals(overview.getDataType())) {
                        smallStoreInfo = overview;
                    } else if ("2".equals(overview.getDataType())) {
                        floorInfoList.add(overview);
                    }
                }
            }
        }

        if (SpringSecurityUtils.hasRole(GlobalConstants.A_TOTAL_DATA_QUERY)) {
            Map<String, String> layoutMap = bisProjectLayoutManager.getLatestLayout(bisProjectId);
            layoutId = layoutMap.get("layoutId");
            brandLoadId = layoutMap.get("brandLoadId");
            priceApproveId = layoutMap.get("priceApproveId");
        }

        // 无楼层统计信息时直接搜索楼层表
        if (floorInfoList.isEmpty()) {

            List<BisFloor> floorlist = bisFloorManager.getFloorByProject(bisProjectId);
            for (BisFloor floor : floorlist) {
                BisProjectOverview overview = new BisProjectOverview();
                overview.setBisFloorId(floor.getBisFloorId());
                overview.setBuildingNum(floor.getBuildingNum());
                overview.setFloorNum(floor.getFloorNum());
                overview.setFloorUrl(floor.getFloorUrl());
                floorInfoList.add(overview);
            }
        }

        return "projectOverview";
    }

    public String overviewMall() throws Exception {

        if (SpringSecurityUtils.hasRole(GlobalConstants.A_TOTAL_QUERY)) {

            String hql = "from BisProjectOverview where bisProjectId=? and to_char(reportMonth,'yyyy-MM')=? and dataType='2' order by sequenceNo,bisFloorId";
            floorInfoList = bisProjectOverviewManager.find(hql, bisProjectId, reportDate);

            if (floorInfoList.isEmpty()) {

                List<BisFloor> floorlist = bisFloorManager.getFloorByProject(bisProjectId);
                for (BisFloor floor : floorlist) {
                    BisProjectOverview overview = new BisProjectOverview();
                    overview.setBisFloorId(floor.getBisFloorId());
                    overview.setBuildingNum(floor.getBuildingNum());
                    overview.setFloorNum(floor.getFloorNum());
                    overview.setFloorUrl(floor.getFloorUrl());
                    floorInfoList.add(overview);
                }
            }

        } else {

            List<BisFloor> list = bisFloorManager.getFloorByProject(bisProjectId);
            for (BisFloor floor : list) {
                BisProjectOverview overview = new BisProjectOverview();
                overview.setBisFloorId(floor.getBisFloorId());
                overview.setBuildingNum(floor.getBuildingNum());
                overview.setFloorNum(floor.getFloorNum());
                overview.setFloorUrl(floor.getFloorUrl());
                floorInfoList.add(overview);
            }
        }
        return "overviewMall";
    }

    public String overviewMulti() throws Exception {

        if (SpringSecurityUtils.hasRole(GlobalConstants.A_TOTAL_QUERY)) {

            String hql = "from BisProjectOverview where bisProjectId=? and to_char(reportMonth,'yyyy-MM')=? and dataType='6' order by sequenceNo";
            floorInfoList = bisProjectOverviewManager.find(hql, bisProjectId, reportDate);

            if (floorInfoList.isEmpty()) {

                List<BisFloor> list = bisFloorManager.getAdByProject(bisProjectId);
                for (BisFloor floor : list) {
                    BisProjectOverview overview = new BisProjectOverview();
                    overview.setBisFloorId(floor.getBisFloorId());
                    overview.setBuildingNum(floor.getBuildingNum());
                    overview.setFloorUrl(floor.getFloorUrl());
                    floorInfoList.add(overview);
                }
            }

        } else {

            List<BisFloor> list = bisFloorManager.getAdByProject(bisProjectId);
            for (BisFloor floor : list) {
                BisProjectOverview overview = new BisProjectOverview();
                overview.setBisFloorId(floor.getBisFloorId());
                overview.setBuildingNum(floor.getBuildingNum());
                overview.setFloorUrl(floor.getFloorUrl());
                floorInfoList.add(overview);
            }
        }
        return "overviewMulti";
    }

    public void refreshData() throws Exception {

        bisProjectOverviewManager.refreshData(reportDate, bisProjectId);
    }

    /**
     * 功能: 构造用户权限
     */
    private void buildPermission() {

        defaultModule = GROUP_OVERVIEW;
        List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(SpringSecurityUtils.getCurrentDeptCd());
        for (WsPlasOrg org : orgList) {
            if ("512".equals(org.getOrgCd())) {
                defaultModule = PROJECT_OVERVIEW;
                break;
            }
        }

        /*
          if (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_SYSTEM_ADMIN)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_FINA)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_FI_MANAGER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_MANAGER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_SYSTEM_ADMIN)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_OP_MANAGER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_OPER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_OP_MANAGER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_VI_AD_MANAGER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_ADVE)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_AD_MANAGER)
                  || SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_MANAGER) ) {

              defaultModule = GROUP_OVERVIEW;
          } else {
              defaultModule = PROJECT_OVERVIEW;
          }
          if (SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_INVESTOR)) {
              groupRPermi = true;
              projectRPermi = true;
              feeMPermi = true;
              contMPermi = true;
              tenantIPermi = true;
              shopMPermi = true;
              projectMPermi = true;

              defaultModule = MODULE_GROUP_REPORT;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_STORE)
                  ||SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FLAT)
                  ||SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_MULTI)) {
              feeMPermi = true;
              contMPermi = true;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_FINA)) {
              feeMPermi = true;
              contMPermi = true;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_PROJ_MANAGER)) {
              projectRPermi = true;
              feeMPermi = true;
              contMPermi = true;
              tenantIPermi = true;

              defaultModule = MODULE_PROJECT_REPORT;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_OPER)
                  ||SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_OP_MANAGER)) {
              projectRPermi = true;
              feeMPermi = true;
              contMPermi = true;
              tenantIPermi = true;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_SYSTEM_ADMIN)) {
              shopMPermi = true;
              projectMPermi = true;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_ADVE)
                  ||SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_AD_MANAGER)) {
              contMPermi = true;
              shopMPermi = true;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_FINA)
                  ||SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_FI_MANAGER)) {
              contMPermi = true;
              feeMPermi = true;
          } else if(SpringSecurityUtils.hasRole(GlobalConstants.A_BIS_HQ_MANAGER)) {
              groupRPermi = true;
              projectRPermi = true;
              feeMPermi = true;
              contMPermi = true;
              tenantIPermi = true;

              defaultModule = MODULE_GROUP_REPORT;
          }
          */
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isGroupRPermi() {
        return groupRPermi;
    }

    public boolean isProjectRPermi() {
        return projectRPermi;
    }

    public boolean isFeeMPermi() {
        return feeMPermi;
    }

    public boolean isContMPermi() {
        return contMPermi;
    }

    public boolean isTenantIPermi() {
        return tenantIPermi;
    }

    public boolean isShopMPermi() {
        return shopMPermi;
    }

    public boolean isProjectMPermi() {
        return projectMPermi;
    }

    public String getBisTenantId() {
        return bisTenantId;
    }

    public void setBisTenantId(String bisTenantId) {
        this.bisTenantId = bisTenantId;
    }

    public String getFactYear() {
        return factYear;
    }

    public void setFactYear(String factYear) {
        this.factYear = factYear;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public String getBrandLoadId() {
        return brandLoadId;
    }

    public void setBrandLoadId(String brandLoadId) {
        this.brandLoadId = brandLoadId;
    }

    public String getPriceApproveId() {
        return priceApproveId;
    }

    public void setPriceApproveId(String priceApproveId) {
        this.priceApproveId = priceApproveId;
    }

    public String getBisProjectAreaCd() {
        return bisProjectAreaCd;
    }

    public void setBisProjectAreaCd(String bisProjectAreaCd) {
        this.bisProjectAreaCd = bisProjectAreaCd;
    }

    public List<BisFloor> getBisFloorList() {
        return bisFloorList;
    }

    public BisProjectOverview getTotalInfo() {
        return totalInfo;
    }

    public BisProjectOverview getMainStoreInfo() {
        return mainStoreInfo;
    }

    public BisProjectOverview getMinorStoreInfo() {
        return minorStoreInfo;
    }

    public BisProjectOverview getSmallStoreInfo() {
        return smallStoreInfo;
    }

    public List<BisProjectOverview> getFloorInfoList() {
        return floorInfoList;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getManageCd() {
        return manageCd;
    }

    public void setManageCd(String manageCd) {
        this.manageCd = manageCd;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIfFromReport() {
        return ifFromReport;
    }

    public void setIfFromReport(String ifFromReport) {
        this.ifFromReport = ifFromReport;
    }

}
