package com.hhz.ump.web.bis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.app.CommonManager;
import com.hhz.ump.dao.bis.BisBudgetManager;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisReportManager;
import com.hhz.ump.dao.bis.BisTenantManager;
import com.hhz.ump.entity.bis.BisBudget;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisReport;
import com.hhz.ump.web.vo.BisArrearsRemindVo;
import com.hhz.ump.web.vo.BisBudgetVo;
import com.hhz.ump.web.vo.BisFlatReportVo;
import com.hhz.ump.web.vo.BisMultiReportVo;
import com.hhz.ump.web.vo.BisProjectReportVo;
import com.hhz.ump.web.vo.BisStoreReportVo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商业费用报表
 *
 * @author baolm 2011-7-9
 */
@Namespace("/bis")
public class BisProjectReportAction extends ActionSupport {

    private static final long serialVersionUID = 2806347865620189538L;

    private static final String TOTAL = "total";
    private static final String MANAGE = "manage";
    private static final String BUDGET = "budget"; //预算执行情况标识
    private static final String STORE = "store";
    private static final String WALK_STREET = "walkStreet";
    private static final String FLAT = "flat";
    private static final String MULTI = "multi";

    private String bisProjectId;
    private String bisProjectName;
    private String reportDate;
    private String reportType;
    private String manageCd;
    private String floorType;
    private String chargeTypeCd;
    private String[] chargeTypeArr;

    private String quarter;
    private String monthOne;
    private String monthTwo;
    private String monthThree;

    private int monthIndex;

    private String totalMoney;

    private List<BisStoreReportVo> bisStoreReportVos = new ArrayList<BisStoreReportVo>();

    private List<BisFlatReportVo> bisFlatReportVos = new ArrayList<BisFlatReportVo>();

    private List<BisMultiReportVo> bisMultiReportVos = new ArrayList<BisMultiReportVo>();

    private List<BisProjectReportVo> inlist = new ArrayList<BisProjectReportVo>();

    private List<BisProjectReportVo> outlist = new ArrayList<BisProjectReportVo>();

    private BisProjectReportVo totalReportVo;

    private Map<String, List<BisArrearsRemindVo>> storeMap = new LinkedHashMap<String, List<BisArrearsRemindVo>>();

    private List<BisArrearsRemindVo> flatList = new ArrayList<BisArrearsRemindVo>();

    private List<BisArrearsRemindVo> multiList = new ArrayList<BisArrearsRemindVo>();

    private Map<String, BisReport> resultMap = new HashMap<String, BisReport>();
    
    // 当前月度预算
    private BisBudget curMonthBisBudget;
    // 截至当前月份的年度总预算
    private BisBudget curYearBisBudget;
    //全年年度经营预算
    private BisBudget curAllYearBisBudget;
    
    //当月预算VO
    private BisBudgetVo curMonthBisBudgetVo;
    // 截至当前月份的年度总预算VO
    private BisBudgetVo curYearBisBudgetVo;
    //全年年度经营预算VO
    private BisBudgetVo curAllYearBisBudgetVo;
    @Autowired
    private BisFlatManager bisFlatManger;
    @Autowired
    private CommonManager commonManager;

    @Autowired
    private BisTenantManager bisTenantManager;
    @Autowired
    private BisProjectManager bisProjectManager;
    @Autowired
    private BisReportManager bisReportManager;
    @Autowired
    private BisBudgetManager bisBudgetManager;
    
    private String reportsType;	//报表的类型，用于bis-project_report-mana判断类型

    @Override
    public String execute() throws Exception {
        BisProject bisProject = bisProjectManager.getCurrProject();
        if (StringUtils.isBlank(reportType)) {
            reportType = TOTAL;
        }
        if (StringUtils.isBlank(bisProjectId)) {
            if (bisProject != null) {
                bisProjectId = bisProject.getBisProjectId();
                bisProjectName = bisProject.getProjectName();
            }
        } else {
            bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
        }
        if (StringUtils.isBlank(reportDate)) {
            reportDate = DateOperator.formatDate(DateOperator.addMonthes(new Date(), -1), "yyyy-MM");
        }
        if (bisProject != null) {
            //标识 当前用户是商业公司用户
            Struts2Utils.getRequest().setAttribute("isBusinessCompanyUser", true);
        }
        return SUCCESS;
    }

    public String load() throws Exception {

        if (TOTAL.equals(reportType) || MANAGE.equals(reportType))
            return loadTotal();

        if (STORE.equals(reportType)) {
            floorType = "1";
            return storeReport();
        }

        if (WALK_STREET.equals(reportType)) {
            floorType = "2";
            return storeReport();
        }

        if (FLAT.equals(reportType))
            return flatReport();

        if (MULTI.equals(reportType))
            return multiReport();

        return null;
    }

    /**
     * 项目现金流报表、项目经营报表
     */
    public String loadTotal() {

        // Map<String, Object> param = new HashMap<String, Object>();
        // param.put("bisProjectId", bisProjectId);
        //
        // BigDecimal totalArrears = new BigDecimal(0);
        // List<BisArrearsRemindVo> storelist =
        // bisTenantManager.getDebtTenants(bisProjectId);
        // BigDecimal storeArrears = setTotalStoreValue(storelist);
        //
        // String flatSql = buildTotalFlatSql();
        // List<Object[]> flatlist = commonManager.findBySql(flatSql, param);
        // BigDecimal flatArrears = setTotalFlatValue(flatlist);
        //
        // //TODO 欠费提醒：多经
        //
        // totalMoney =
        // totalArrears.add(storeArrears).add(flatArrears).divide(new
        // BigDecimal(10000), 0, BigDecimal.ROUND_HALF_UP).toString();
        return "total";
    }

    /**
     * 项目报表信息
     */
    public String loadReportDetail() {

        //
        String statDimansion = "";
        if (MANAGE.equals(reportType)) {
            statDimansion = "2";
        } else if(TOTAL.equals(reportType)){
        	statDimansion = "2";
        }else {
            statDimansion = "1";
        }
        
        //System.out.println("reportType="+reportType);
        
        List<BisReport> bisReportList = bisProjectManager.getReportData(reportDate, "2", statDimansion, bisProjectId);
        for (BisReport bisReport : bisReportList) {
        	//System.out.println("bisReport="+bisReport.getChargeTypeCd()+";"+bisReport.getMoneyFact()+","+bisReport.getMoneyArrear());
            addToResultMap(bisReport);
        }
       
            
        //搜索当前月度预算
        StringBuffer hql = new StringBuffer(" from BisBudget t where 1=1 and t.yearStatus='1' ");
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("bisProjectId", bisProjectId);
        
        if (StringUtils.isNotBlank(reportDate)) {
            hql.append(" and t.monthStr=:monthStr ");
            values.put("monthStr", reportDate);
        }
        hql.append(" and t.bisProjectId=:bisProjectId ");
        hql.append(" order by t.monthStr ");

        List<BisBudget> monthBisBudgets = bisBudgetManager.find(hql.toString(), values);
        curMonthBisBudget = new BisBudget();
        if (monthBisBudgets.size() > 0) {
            curMonthBisBudget = monthBisBudgets.get(0);

        }
        //搜索当前月截止总的年度预算
        hql = new StringBuffer(" from BisBudget t where 1=1 and t.yearStatus='2' ");

        if (StringUtils.isNotBlank(reportDate)) {
            hql.append(" and t.monthStr=:monthStr ");
        }
        hql.append(" and t.bisProjectId=:bisProjectId ");
        hql.append(" order by t.monthStr ");
        List<BisBudget> yearBisBudgets = bisBudgetManager.find(hql.toString(), values);
        curYearBisBudget = new BisBudget();
        if (yearBisBudgets.size() > 0) {
            curYearBisBudget = yearBisBudgets.get(0);

        }
        
        //搜索全年年度经营预算
        
        hql = new StringBuffer(" from BisBudget t where 1=1 and t.yearStatus='2' ");

        hql.append(" and t.bisProjectId=:bisProjectId ");
        hql.append(" order by t.monthStr desc");
        List<BisBudget> allYearBisBudgets = bisBudgetManager.find(hql.toString(), values);
        curAllYearBisBudget = new BisBudget();
        if (yearBisBudgets.size() > 0) {
        	curAllYearBisBudget = allYearBisBudgets.get(0);
        }
        
        //查询公寓物业费按月份
        List<BigDecimal> propManageMonth =  bisFlatManger.getPropManageForFlat(reportDate,bisProjectId,true,false);
        
        //查询公寓物业费按累计年份
        List<BigDecimal> propManageYear =  bisFlatManger.getPropManageForFlat(reportDate,bisProjectId,false,false);
        //查询公寓物业费按全年份
        List<BigDecimal> propManageAllYear =  bisFlatManger.getPropManageForFlat(reportDate,bisProjectId,true,false);
        
        curMonthBisBudgetVo = new BisBudgetVo();
		curYearBisBudgetVo = new BisBudgetVo();
		curAllYearBisBudgetVo = new BisBudgetVo();
		
        if(propManageMonth.size()>0){
        	BigDecimal propManageFlat = propManageMonth.get(0);
        	if(propManageFlat!=null){
	        	BigDecimal unit = new BigDecimal(10000);
	        	BigDecimal result = propManageFlat.divide(unit);
	        	//System.out.println("result="+result);
	        	curMonthBisBudgetVo.setPropManage(result);
        	}

        }
        if(propManageYear.size()>0){
        	BigDecimal propManageFlat = propManageYear.get(0);
        	if(propManageFlat!=null){
	        	BigDecimal unit = new BigDecimal(10000);
	        	BigDecimal result = propManageFlat.divide(unit);
	        	curYearBisBudgetVo.setPropManage(result);
        	}
        }
        if(propManageAllYear.size()>0){
        	BigDecimal propManageFlat = propManageYear.get(0);
        	if(propManageFlat!=null){
	        	BigDecimal unit = new BigDecimal(10000);
	        	BigDecimal result = propManageFlat.divide(unit);
	        	curAllYearBisBudgetVo.setPropManage(result);
        	}
        }
         
         if (MANAGE.equals(reportType)) {
        	
        	//经营情况相关数据求和计算
        	manageCaculateTotalData();
          	
        	//System.out.println("bisProjectId="+bisProjectId);
        	return "mana";
		}
        //预算执行情况表相关数据求和计算
        budgetExecuteCaculateTotalData();
		return "budget";
    }

    /**
     * 预算执行情况表相关数据求和计算
     */
	private void budgetExecuteCaculateTotalData() {

		
		//计算本月预算中停车场费用 = 停车位管理费+停车费
		BigDecimal[] parkChargeMonthArrays = new BigDecimal[]{curMonthBisBudget.getCarManage(),curMonthBisBudget.getCarTemporary()};
		BigDecimal parkCharge = bisBudgetManager.getTotalData(parkChargeMonthArrays);
		curMonthBisBudgetVo.setCarParking(parkCharge);
		
		BigDecimal[] parkChargeYearArrays = new BigDecimal[]{curYearBisBudget.getCarManage(),curYearBisBudget.getCarTemporary()};
		parkCharge = bisBudgetManager.getTotalData(parkChargeYearArrays);
		curYearBisBudgetVo.setCarParking(parkCharge);
		
		
		//计算多种经营收入
		BigDecimal[] multiMonthArrays = new BigDecimal[]{curMonthBisBudget.getMultiAdvert(),curMonthBisBudget.getMultiService()
				,curMonthBisBudget.getMultiRepair(),curMonthBisBudget.getMultiClean(),curMonthBisBudget.getMultiOther()};
		
		BigDecimal multiTotal = bisBudgetManager.getTotalData(multiMonthArrays);
		
		curMonthBisBudgetVo.setMultiTotal(multiTotal);
		
		BigDecimal[] multiYearArrays = new BigDecimal[]{curYearBisBudget.getMultiAdvert(),curYearBisBudget.getMultiService()
				,curYearBisBudget.getMultiRepair(),curYearBisBudget.getMultiClean(),curYearBisBudget.getMultiOther()};
		
		multiTotal = bisBudgetManager.getTotalData(multiYearArrays);
		
		curYearBisBudgetVo.setMultiTotal(multiTotal);
		
		//招商代理费收入
		BigDecimal[] inviteMerchantMonthArrays = new BigDecimal[]{curMonthBisBudget.getInviteAgent(),curMonthBisBudget.getInviteRent()};
		
		BigDecimal inviteMerchant = bisBudgetManager.getTotalData(inviteMerchantMonthArrays);
		
		curMonthBisBudgetVo.setInviteMerchant(inviteMerchant);
		
		BigDecimal[] inviteMerchantYearArrays = new BigDecimal[]{curMonthBisBudget.getInviteAgent(),curMonthBisBudget.getInviteRent()};
		
		inviteMerchant = bisBudgetManager.getTotalData(inviteMerchantYearArrays);
		
		curYearBisBudgetVo.setInviteMerchant(inviteMerchant);
		
		
		//收入合计 = 物业管理费用+停车场收入+多种经营收入+招商代理收入
		
		BigDecimal[] incomeTotalMonthArrays = new BigDecimal[]{curMonthBisBudget.getPropManage(),
				curMonthBisBudgetVo.getCarParking(),curMonthBisBudgetVo.getMultiTotal(),curMonthBisBudgetVo.getInviteMerchant()};
		BigDecimal incomeTotal = bisBudgetManager.getTotalData(incomeTotalMonthArrays);
		
		curMonthBisBudgetVo.setIncomeTotal(incomeTotal);
		
		BigDecimal[] incomeTotalYearArrays = new BigDecimal[]{curYearBisBudget.getPropManage(),
				curYearBisBudgetVo.getCarParking(),curYearBisBudgetVo.getMultiTotal(),curYearBisBudgetVo.getInviteMerchant()};
		
		inviteMerchant = bisBudgetManager.getTotalData(incomeTotalYearArrays);
		
		curYearBisBudgetVo.setIncomeTotal(incomeTotal);
		
		
		//其他费用
		BigDecimal[] otherPayMonthArrays = new BigDecimal[]{curMonthBisBudget.getOtherBisCost(),curMonthBisBudget.getOtherMgCharge()
				,curMonthBisBudget.getOtherPersonnel(),curMonthBisBudget.getOtherPay(),curMonthBisBudget.getOtherSaleCharge()};
		
		BigDecimal otherPay = bisBudgetManager.getTotalData(otherPayMonthArrays);
		
		curMonthBisBudgetVo.setOtherPay(otherPay);
		
		BigDecimal[] otherPayYearArrays = new BigDecimal[]{curYearBisBudget.getOtherBisCost(),curYearBisBudget.getOtherMgCharge()
				,curYearBisBudget.getOtherPersonnel(),curYearBisBudget.getOtherPay(),curYearBisBudget.getOtherSaleCharge()};
		
		otherPay = bisBudgetManager.getTotalData(otherPayYearArrays);
		
		curYearBisBudgetVo.setOtherPay(otherPay);
		

		//人工福利费
		BigDecimal[] welfareMonthArrays = new BigDecimal[]{curMonthBisBudget.getSalaryFareBiscost(),curMonthBisBudget.getSalaryFareSales()
				,curMonthBisBudget.getWelfare()};
		
		BigDecimal welfare = bisBudgetManager.getTotalData(welfareMonthArrays);
		
		curMonthBisBudgetVo.setWelfare(welfare);
		
		BigDecimal[] welfareYearArrays = new BigDecimal[]{curYearBisBudget.getSalaryFareBiscost(),curYearBisBudget.getSalaryFareSales()
				,curYearBisBudget.getWelfare()};
		
		welfare = bisBudgetManager.getTotalData(welfareYearArrays);
		
		curYearBisBudgetVo.setWelfare(welfare);
		
		//成本合计
		
		BigDecimal[] payTotalMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getWelfare(),curMonthBisBudget.getOtherPersonnel()
				,curMonthBisBudget.getAdministraFee(),curMonthBisBudget.getTravelFee(),curMonthBisBudget.getBusinessServe(),
				curMonthBisBudget.getAdivertise(),curMonthBisBudget.getInviteReward(),curMonthBisBudget.getCleanHealthCost(),
				curMonthBisBudget.getSafeGuard(),curMonthBisBudget.getAfforeMainten(),curMonthBisBudget.getEngineerMainten(),
				curMonthBisBudget.getEnergyCost(),curMonthBisBudget.getSetupProject(),
				curMonthBisBudget.getOtherPay()};
		
		BigDecimal payTotal = bisBudgetManager.getTotalData(payTotalMonthArrays);
		
		curMonthBisBudgetVo.setPayTotal(payTotal);
		
		BigDecimal[] payTotalYearArrays = new BigDecimal[]{curYearBisBudgetVo.getWelfare(),curYearBisBudget.getOtherPersonnel()
				,curYearBisBudget.getAdministraFee(),curYearBisBudget.getTravelFee(),curYearBisBudget.getBusinessServe(),
				curYearBisBudget.getAdivertise(),curYearBisBudget.getInviteReward(),curYearBisBudget.getCleanHealthCost(),
				curYearBisBudget.getSafeGuard(),curYearBisBudget.getAfforeMainten(),curYearBisBudget.getEngineerMainten(),
				curYearBisBudget.getEnergyCost(),curYearBisBudget.getSetupProject(),
				curYearBisBudget.getOtherPay()};
		
		payTotal = bisBudgetManager.getTotalData(payTotalYearArrays);
		
		curYearBisBudgetVo.setPayTotal(payTotal);
		
		
		//营业利润
		
		BigDecimal pt = (curMonthBisBudgetVo.getPayTotal()==null?new BigDecimal(0):curMonthBisBudgetVo.getPayTotal().negate());
		BigDecimal se = (curMonthBisBudget.getSalesExtra()==null?new BigDecimal(0):curMonthBisBudget.getSalesExtra().negate());
		
		BigDecimal[] profitMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getIncomeTotal(),pt,se};
		BigDecimal bisProfit = bisBudgetManager.getTotalData(profitMonthArrays);
		
		curMonthBisBudgetVo.setBisProfit(bisProfit);
		
		
		pt = (curYearBisBudgetVo.getPayTotal()==null?new BigDecimal(0):curYearBisBudgetVo.getPayTotal().negate());
		se = (curYearBisBudget.getSalesExtra()==null?new BigDecimal(0):curYearBisBudget.getSalesExtra().negate());
		
		BigDecimal[] profitYearArrays = new BigDecimal[]{curYearBisBudgetVo.getIncomeTotal(),pt,se};
		
		bisProfit = bisBudgetManager.getTotalData(profitYearArrays);
		
		curYearBisBudgetVo.setBisProfit(bisProfit);
		
		//利润总额
		
		BigDecimal be = (curMonthBisBudget.getBisExpense()==null?new BigDecimal(0):curMonthBisBudget.getBisExpense().negate());
		
		BigDecimal[] profitTotalMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getBisProfit(),curMonthBisBudget.getBisIncome(),be};
		BigDecimal profitTotal = bisBudgetManager.getTotalData(profitTotalMonthArrays);
		
		curMonthBisBudgetVo.setProfitTotal(profitTotal);
		
		
		be = (curYearBisBudget.getBisExpense()==null?new BigDecimal(0):curYearBisBudget.getBisExpense().negate());
		
		BigDecimal[] profitTotalYearArrays = new BigDecimal[]{curYearBisBudgetVo.getBisProfit(),curYearBisBudget.getBisIncome(),be};
		
		profitTotal = bisBudgetManager.getTotalData(profitTotalYearArrays);
		
		curYearBisBudgetVo.setProfitTotal(profitTotal);
		
		//净利润
		
		BigDecimal ic = (curMonthBisBudget.getIncomeTax()==null?new BigDecimal(0):curMonthBisBudget.getIncomeTax().negate());
		
		BigDecimal[] netProfitMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getProfitTotal(),ic};
		BigDecimal netProfit = bisBudgetManager.getTotalData(netProfitMonthArrays);
		
		curMonthBisBudgetVo.setNetProfit(netProfit);
		
		ic = (curYearBisBudget.getIncomeTax()==null?new BigDecimal(0):curYearBisBudget.getIncomeTax().negate());
		
		BigDecimal[] netProfitYearArrays = new BigDecimal[]{curYearBisBudgetVo.getProfitTotal(),ic};
		
		profitTotal = bisBudgetManager.getTotalData(netProfitYearArrays);
		
		curYearBisBudgetVo.setNetProfit(netProfit);
	}

    /**
     * 经营情况相关数据求和计算
     */
	private void manageCaculateTotalData() {
		
		
		//计算本月预算中招商佣金收入
		BigDecimal[] inviteMerchantMonthArrays = new BigDecimal[]{curMonthBisBudget.getInviteAgent(),curMonthBisBudget.getInviteRent()};
		BigDecimal inviteMerchant = bisBudgetManager.getTotalData(inviteMerchantMonthArrays);
		curMonthBisBudgetVo.setInviteMerchant(inviteMerchant);
		
		BigDecimal[] inviteMerchantYearArrays = new BigDecimal[]{curYearBisBudget.getInviteAgent(),curYearBisBudget.getInviteRent()};
		inviteMerchant = bisBudgetManager.getTotalData(inviteMerchantYearArrays);
		curYearBisBudgetVo.setInviteMerchant(inviteMerchant);
		
		
		BigDecimal[] inviteMerchantAllYearArrays = new BigDecimal[]{curAllYearBisBudget.getInviteAgent(),curAllYearBisBudget.getInviteRent()};
		inviteMerchant = bisBudgetManager.getTotalData(inviteMerchantAllYearArrays);
		curAllYearBisBudgetVo.setInviteMerchant(inviteMerchant);
		
		//计算预算中多种经营总和
		
		//本月预算多种经营总和
		BigDecimal[] muiltiMonthArrays = new BigDecimal[]{curMonthBisBudget.getMultiAdvert(),
				curMonthBisBudgetVo.getInviteMerchant(),curMonthBisBudget.getMultiService(),curMonthBisBudget.getCarTemporary(),
				curMonthBisBudget.getMultiRepair(),curMonthBisBudget.getMultiClean(),curMonthBisBudget.getMultiOther()};
		
		BigDecimal multiTotal = bisBudgetManager.getTotalData(muiltiMonthArrays);
		//System.out.println("multiTotal="+multiTotal);
		curMonthBisBudgetVo.setMultiTotal(multiTotal);
		
		//本年度累计预算多种经营总和
		BigDecimal[] muiltiYearArrays = new BigDecimal[]{curYearBisBudget.getMultiAdvert(),
				curYearBisBudgetVo.getInviteMerchant(),curYearBisBudget.getMultiService(),curYearBisBudget.getCarTemporary(),
				curYearBisBudget.getMultiRepair(),curYearBisBudget.getMultiClean(),curYearBisBudget.getMultiOther()};
		 multiTotal = bisBudgetManager.getTotalData(muiltiYearArrays);
		 
		 curYearBisBudgetVo.setMultiTotal(multiTotal);
		
		//本年度全年预算多种经营总和
		BigDecimal[] muiltiAllYearArrays = new BigDecimal[]{curAllYearBisBudget.getMultiAdvert(),
				curAllYearBisBudgetVo.getInviteMerchant(),curAllYearBisBudget.getMultiService(),curAllYearBisBudget.getCarTemporary(),
				curAllYearBisBudget.getMultiRepair(),curAllYearBisBudget.getMultiClean(),curAllYearBisBudget.getMultiOther()};
		 multiTotal = bisBudgetManager.getTotalData(muiltiAllYearArrays);
		 
		 curAllYearBisBudgetVo.setMultiTotal(multiTotal);
		 
		 //营业收入
		 
		 
		//本月预算营业收入
		BigDecimal[] incomeTotalMonthArrays = new BigDecimal[]{curMonthBisBudget.getPropManage(),
				curMonthBisBudget.getCarManage(),curMonthBisBudgetVo.getMultiTotal()};
		BigDecimal incomeTotal = bisBudgetManager.getTotalData(incomeTotalMonthArrays);
		curMonthBisBudgetVo.setIncomeTotal(incomeTotal);
		
		//本年度累计预算多种经营总和
		BigDecimal[] incomeTotalYearArrays = new BigDecimal[]{curYearBisBudget.getPropManage(),
				curYearBisBudget.getCarManage(),curYearBisBudgetVo.getMultiTotal()};
		 incomeTotal = bisBudgetManager.getTotalData(incomeTotalYearArrays);
		curYearBisBudgetVo.setIncomeTotal(incomeTotal);
		
		//本年度全年预算多种经营总和
		BigDecimal[] incomeTotalAllYearArrays = new BigDecimal[]{curAllYearBisBudget.getPropManage(),
				curAllYearBisBudget.getCarManage(),curAllYearBisBudgetVo.getMultiTotal()};
		 incomeTotal = bisBudgetManager.getTotalData(incomeTotalAllYearArrays);
		curAllYearBisBudgetVo.setIncomeTotal(incomeTotal);
		 
		 
		
		//营业成本
		
		//本月预算营业成本总和
		BigDecimal[] payTotalMonthArrays = new BigDecimal[]{curMonthBisBudget.getCleanHealthCost(),
				curMonthBisBudget.getSafeGuard(),curMonthBisBudget.getAfforeMainten(),curMonthBisBudget.getEngineerMainten(),
				curMonthBisBudget.getEnergyCost(),curMonthBisBudget.getParkCharge(),curMonthBisBudget.getSalaryFareBiscost()
				,curMonthBisBudget.getOtherBisCost()};
		BigDecimal payTotal = bisBudgetManager.getTotalData(payTotalMonthArrays);
		
		curMonthBisBudgetVo.setPayTotal(payTotal);
		
		//本年度累计预算营业成本总和
		BigDecimal[] payTotalYearArrays = new BigDecimal[]{curYearBisBudget.getCleanHealthCost(),
				curYearBisBudget.getSafeGuard(),curYearBisBudget.getAfforeMainten(),curYearBisBudget.getEngineerMainten(),
				curYearBisBudget.getEnergyCost(),curYearBisBudget.getParkCharge(),curYearBisBudget.getSalaryFareBiscost()
				,curYearBisBudget.getOtherBisCost()};
		 payTotal = bisBudgetManager.getTotalData(payTotalYearArrays);
		 
		 curYearBisBudgetVo.setPayTotal(payTotal);
		
		//本年度累计预算营业成本总和
		BigDecimal[] payTotalAllYearArrays = new BigDecimal[]{curAllYearBisBudget.getCleanHealthCost(),
				curAllYearBisBudget.getSafeGuard(),curAllYearBisBudget.getAfforeMainten(),curAllYearBisBudget.getEngineerMainten(),
				curAllYearBisBudget.getEnergyCost(),curAllYearBisBudget.getParkCharge(),curAllYearBisBudget.getSalaryFareBiscost()
				,curAllYearBisBudget.getOtherBisCost()};
		 payTotal = bisBudgetManager.getTotalData(payTotalAllYearArrays);
		 
		 curAllYearBisBudgetVo.setPayTotal(payTotal);
		
		
		//销售费用
		 
		//本月预算销售费用总和
		 BigDecimal[] setSaleChargeTotalMonthArrays = new BigDecimal[]{curMonthBisBudget.getSalaryFareSales(),
				curMonthBisBudget.getAdivertise(),curMonthBisBudget.getInviteReward(),curMonthBisBudget.getOtherSaleCharge()};
		 
		BigDecimal setSaleChargeTotal= bisBudgetManager.getTotalData(setSaleChargeTotalMonthArrays);
		
		curMonthBisBudgetVo.setSaleChargeTotal(setSaleChargeTotal);
		
		//本年度累计预算销售费用总和
		BigDecimal[] setSaleChargeTotalYearArrays = new BigDecimal[]{curYearBisBudget.getSalaryFareSales(),
				curYearBisBudget.getAdivertise(),curYearBisBudget.getInviteReward(),curYearBisBudget.getOtherSaleCharge()};
		
		setSaleChargeTotal = bisBudgetManager.getTotalData(setSaleChargeTotalYearArrays);
		 
		curYearBisBudgetVo.setSaleChargeTotal(setSaleChargeTotal);
		
		//本年度累计预算销售费用总和
		BigDecimal[] setSaleChargeTotalAllYearArrays = new BigDecimal[]{curAllYearBisBudget.getSalaryFareSales(),
				curAllYearBisBudget.getAdivertise(),curAllYearBisBudget.getInviteReward(),curAllYearBisBudget.getOtherSaleCharge()};
		
		setSaleChargeTotal = bisBudgetManager.getTotalData(setSaleChargeTotalAllYearArrays);
		 
		curAllYearBisBudgetVo.setSaleChargeTotal(setSaleChargeTotal);
		 
		 //营业利润
		
		//计算预算中营业利润 = 营业收入-营业成本-营业税金及附加-销售费用-管理费用-财务费用+投资收益(0)
		 
		BigDecimal pt = (curMonthBisBudgetVo.getPayTotal()==null?new BigDecimal(0):curMonthBisBudgetVo.getPayTotal().negate());
		BigDecimal se = (curMonthBisBudget.getSalesExtra()==null?new BigDecimal(0):curMonthBisBudget.getSalesExtra().negate());
		BigDecimal sc = (curMonthBisBudgetVo.getSaleChargeTotal()==null?new BigDecimal(0):curMonthBisBudgetVo.getSaleChargeTotal().negate());
		BigDecimal om = (curMonthBisBudget.getOtherMgCharge()==null?new BigDecimal(0):curMonthBisBudget.getOtherMgCharge().negate());
		BigDecimal fc = (curMonthBisBudget.getFinacialCharge()==null?new BigDecimal(0):curMonthBisBudget.getFinacialCharge().negate());
		 
		 BigDecimal[] profitMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getIncomeTotal(),
				pt,se,sc,om,fc};
		 
		BigDecimal bisProfit = bisBudgetManager.getTotalData(profitMonthArrays);
		
		curMonthBisBudgetVo.setBisProfit(bisProfit);
		
		//本年度累计预算营业利润总和
		
		 pt = (curYearBisBudgetVo.getPayTotal()==null?new BigDecimal(0):curYearBisBudgetVo.getPayTotal().negate());
		 se = (curYearBisBudget.getSalesExtra()==null?new BigDecimal(0):curYearBisBudget.getSalesExtra().negate());
		 sc = (curYearBisBudgetVo.getSaleChargeTotal()==null?new BigDecimal(0):curYearBisBudgetVo.getSaleChargeTotal().negate());
		 om = (curYearBisBudget.getOtherMgCharge()==null?new BigDecimal(0):curYearBisBudget.getOtherMgCharge().negate());
		 fc = (curYearBisBudget.getFinacialCharge()==null?new BigDecimal(0):curYearBisBudget.getFinacialCharge().negate());
		
		 BigDecimal[] profitYearArrays = new BigDecimal[]{curYearBisBudgetVo.getIncomeTotal(),
				pt,se,sc,om,fc};
		
		bisProfit = bisBudgetManager.getTotalData(profitYearArrays);
		 
		curYearBisBudgetVo.setBisProfit(bisProfit);
		//本年度累计预算营业利润总和
		
		 pt = (curAllYearBisBudgetVo.getPayTotal()==null?new BigDecimal(0):curAllYearBisBudgetVo.getPayTotal().negate());
		 se = (curAllYearBisBudget.getSalesExtra()==null?new BigDecimal(0):curAllYearBisBudget.getSalesExtra().negate());
		 sc = (curAllYearBisBudgetVo.getSaleChargeTotal()==null?new BigDecimal(0):curAllYearBisBudgetVo.getSaleChargeTotal().negate());
		 om = (curAllYearBisBudget.getOtherMgCharge()==null?new BigDecimal(0):curAllYearBisBudget.getOtherMgCharge().negate());
		 fc = (curAllYearBisBudget.getFinacialCharge()==null?new BigDecimal(0):curAllYearBisBudget.getFinacialCharge().negate());
		 
		 BigDecimal[] profitAllYearArrays = new BigDecimal[]{curAllYearBisBudgetVo.getIncomeTotal(),
					pt,se,sc,om,fc};
		
		bisProfit = bisBudgetManager.getTotalData(profitAllYearArrays);
		 
		curAllYearBisBudgetVo.setBisProfit(bisProfit);
		
		//利润总额计算
		
		BigDecimal be = (curMonthBisBudget.getBisExpense()==null?new BigDecimal(0):curMonthBisBudget.getBisExpense().negate());
		
		BigDecimal[] profitTotalMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getBisProfit(),
				curMonthBisBudget.getBisIncome(),be};
		 
		BigDecimal profitTotal = bisBudgetManager.getTotalData(profitTotalMonthArrays);
		
		curMonthBisBudgetVo.setProfitTotal(profitTotal);
		
		 be = (curYearBisBudget.getBisExpense()==null?new BigDecimal(0):curYearBisBudget.getBisExpense().negate());
		
		BigDecimal[] profitTotalYearArrays = new BigDecimal[]{curYearBisBudgetVo.getBisProfit(),
				curYearBisBudget.getBisIncome(),be};
		 
		 profitTotal = bisBudgetManager.getTotalData(profitTotalYearArrays);
		
		curYearBisBudgetVo.setProfitTotal(profitTotal);
		
		be = (curAllYearBisBudget.getBisExpense()==null?new BigDecimal(0):curAllYearBisBudget.getBisExpense().negate());
		 
		BigDecimal[] profitTotalAllYearArrays = new BigDecimal[]{curAllYearBisBudgetVo.getBisProfit(),
				curAllYearBisBudget.getBisIncome(),be};
		 
		 profitTotal = bisBudgetManager.getTotalData(profitTotalAllYearArrays);
		
		curAllYearBisBudgetVo.setProfitTotal(profitTotal);
		
		
		
		//净利润计算
		
		//本月
		BigDecimal ic = (curMonthBisBudget.getIncomeTax()==null?new BigDecimal(0):curMonthBisBudget.getIncomeTax().negate());
		
		BigDecimal[] netProfitMonthArrays = new BigDecimal[]{curMonthBisBudgetVo.getProfitTotal(),
				ic};
		 
		BigDecimal netProfit = bisBudgetManager.getTotalData(netProfitMonthArrays);
		
		curMonthBisBudgetVo.setNetProfit(netProfit);
		
		//本年累计
		ic = (curYearBisBudget.getIncomeTax()==null?new BigDecimal(0):curYearBisBudget.getIncomeTax().negate());
		
		BigDecimal[] netProfitYearArrays = new BigDecimal[]{curYearBisBudgetVo.getProfitTotal(),
				ic};
		 
		netProfit = bisBudgetManager.getTotalData(netProfitYearArrays);
		
		curYearBisBudgetVo.setNetProfit(netProfit);
		
		//本年度全年
		
		ic = (curAllYearBisBudget.getIncomeTax()==null?new BigDecimal(0):curAllYearBisBudget.getIncomeTax().negate());
		
		BigDecimal[] netProfitAllYearArrays = new BigDecimal[]{curAllYearBisBudgetVo.getProfitTotal(),
				ic};
		 
		netProfit = bisBudgetManager.getTotalData(netProfitAllYearArrays);
		
		curAllYearBisBudgetVo.setNetProfit(netProfit);
	}

    private void addToResultMap(BisReport bisReport) {
        String key = "";
        if ("2".equals(bisReport.getTimeDimansion().trim())) {
            key = bisReport.getChargeTypeCd() + "-1";
        } else {
            key = bisReport.getChargeTypeCd() + "-2";
        }
        resultMap.put(key, bisReport);
    }

    /**
     * 欠费提醒：商铺、公寓
     */
    @SuppressWarnings("unchecked")
    public String loadProjectDebt() {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bisProjectId", bisProjectId);

        BigDecimal totalArrears = new BigDecimal(0);
        List<BisArrearsRemindVo> storelist = bisTenantManager.getDebtTenants(bisProjectId);
        BigDecimal storeArrears = setTotalStoreValue(storelist);

        String flatSql = buildTotalFlatSql();
        List<Object[]> flatlist = commonManager.findBySql(flatSql, param);
        BigDecimal flatArrears = setTotalFlatValue(flatlist);

        // TODO 欠费提醒：多经

        totalMoney = totalArrears.add(storeArrears).add(flatArrears).divide(new BigDecimal(10000), 0, BigDecimal.ROUND_HALF_UP).toString();

        return "debt";
    }

    public String refreshData() {
        bisReportManager.refreshData(reportDate);
        
        return null;
    }

    /**
     * MALL、步行街报表
     */
    public String storeReport() {

        if (StringUtils.isBlank(chargeTypeCd)) {
            chargeTypeCd = "1,4";
        }

        chargeTypeArr = chargeTypeCd.split(",");

        String year = reportDate.substring(0, 4);
        String month = reportDate.substring(5, 7);

        bisStoreReportVos = bisProjectManager.getStoreReportData(bisProjectId, year, month, chargeTypeCd, manageCd, floorType);

        return "store";
    }

    /**
     * 公寓报表
     */
    public String flatReport() {
        if (StringUtils.isBlank(chargeTypeCd)) {
            chargeTypeCd = "3";
        }

        chargeTypeArr = chargeTypeCd.split(",");

        String year = reportDate.substring(0, 4);
        String month = reportDate.substring(5, 7);

        bisFlatReportVos = bisProjectManager.getFlatReportData(bisProjectId, year, month, chargeTypeCd);

        return "flat";
    }

    /**
     * 多经报表
     */
    public String multiReport() {

        if (StringUtils.isBlank(chargeTypeCd)) {
            chargeTypeCd = "1,4";
        }

        chargeTypeArr = chargeTypeCd.split(",");

        String year = reportDate.substring(0, 4);
        String month = reportDate.substring(5, 7);

        bisMultiReportVos = bisProjectManager.getMultiReportData(bisProjectId, year, month, chargeTypeCd);

        return "multi";
    }

    /**
     * 项目费用报表SQL
     */
    private String buildTotalFeeSql() {
        StringBuffer sql = new StringBuffer();
        sql
                .append("select * from")
                .append(" (select t.dict_cd charge_type_cd , t.dict_name charge_type_name,")
                .append(" round(NVL(m.must_year,0)/10000,0),")
                .append(" round(NVL(m.must_quarter,0)/10000,0),")
                .append(" round(NVL(m.must_one,0)/10000,0),")
                .append(" round(NVL(m.must_two,0)/10000,0),")
                .append(" round(NVL(m.must_three,0)/10000,0),")
                .append(" round(NVL(f.fact_year,0)/10000,0),")
                .append(" round(NVL(f.fact_quarter,0)/10000,0),")
                .append(" round(NVL(f.fact_one,0)/10000,0),")
                .append(" round(NVL(f.fact_two,0)/10000,0),")
                .append(" round(NVL(f.fact_three,0)/10000,0)")
                        // .append(" NVL(round(f.fact_year/m.must_year*100, 2),0) rate_year,")
                        // .append(" NVL(round(f.fact_quarter/m.must_quarter*100, 2),0) rate_quarter,")
                        // .append(" NVL(round(f.fact_one/m.must_one*100, 2),0) rate_one,")
                        // .append(" NVL(round(f.fact_two/m.must_two*100, 2),0) rate_two,")
                        // .append(" NVL(round(f.fact_three/m.must_three*100, 2),0) rate_three")
                .append(" from").append(" (select dd.dict_cd,dd.dict_name").append(
                " from app_dict_data dd join app_dict_type dt on dd.app_dict_type_id=dt.app_dict_type_id").append(
                " where dict_type_cd='BIS_CHARGE_TYPE' and dd.dict_cd not in ('02','03','38') ) t").append(" left join").append(
                " (select bm.charge_type_cd, sum(bm.money) must_year,").append(
                " sum(decode(bm.must_month, :one, bm.money, :two, bm.money, :three, bm.money, null)) must_quarter,").append(
                " sum(decode(bm.must_month, :one, bm.money, null)) must_one,").append(" sum(decode(bm.must_month, :two, bm.money, null)) must_two,")
                .append(" sum(decode(bm.must_month, :three, bm.money, null)) must_three").append(
                " from bis_must bm join bis_cont bc on bm.bis_cont_id = bc.bis_cont_id").append(
                " where bc.bis_project_id = :bisProjectId and bm.must_year = :year").append(
                " group by bm.charge_type_cd ) m on t.dict_cd=m.charge_type_cd").append(" left join").append(
                " (select bf.charge_type_cd, sum(bf.money) fact_year,").append(
                " sum(decode(bf.fact_month, :one, bf.money, :two, bf.money, :three, bf.money, null)) fact_quarter,").append(
                " sum(decode(bf.fact_month, :one, bf.money, null)) fact_one,")
                .append(" sum(decode(bf.fact_month, :two, bf.money, null)) fact_two,")
                .append(" sum(decode(bf.fact_month, :three, bf.money, null)) fact_three")
                .append(" from bis_fact bf ")
                .append(" where bf.bis_project_id = :bisProjectId and bf.fact_year = :year")
                .append(" group by bf.charge_type_cd ) f on t.dict_cd=f.charge_type_cd")

                .append(" union all")

                .append(" select tm.charge_type_cd, '合计' charge_type_name,")
                .append(" round(NVL(tm.must_year,0)/10000,0),")
                .append(" round(NVL(tm.must_quarter,0)/10000,0),")
                .append(" round(NVL(tm.must_one,0)/10000,0),")
                .append(" round(NVL(tm.must_two,0)/10000,0),")
                .append(" round(NVL(tm.must_three,0)/10000,0),")
                .append(" round(NVL(tf.fact_year,0)/10000,0),")
                .append(" round(NVL(tf.fact_quarter,0)/10000,0),")
                .append(" round(NVL(tf.fact_one,0)/10000,0),")
                .append(" round(NVL(tf.fact_two,0)/10000,0),")
                .append(" round(NVL(tf.fact_three,0)/10000,0)")
                        // .append(" NVL(round(tf.fact_year/tm.must_year*100, 2),0) rate_year,")
                        // .append(" NVL(round(tf.fact_quarter/tm.must_quarter*100, 2),0) rate_quarter,")
                        // .append(" NVL(round(tf.fact_one/tm.must_one*100, 2),0) rate_one,")
                        // .append(" NVL(round(tf.fact_two/tm.must_two*100, 2),0) rate_two,")
                        // .append(" NVL(round(tf.fact_three/tm.must_three*100, 2),0) rate_three")
                .append(" from").append(" (select '100' charge_type_cd, sum(bm.money) must_year,").append(
                " sum(decode(bm.must_month, :one, bm.money, :two, bm.money, :three, bm.money, null)) must_quarter,").append(
                " sum(decode(bm.must_month, :one, bm.money, null)) must_one,").append(" sum(decode(bm.must_month, :two, bm.money, null)) must_two,")
                .append(" sum(decode(bm.must_month, :three, bm.money, null)) must_three").append(
                " from bis_must bm join bis_cont bc on bm.bis_cont_id = bc.bis_cont_id").append(
                " where bc.bis_project_id = :bisProjectId and bm.must_year = :year")
                .append(" and bm.charge_type_cd not in ('02','03','38','21') ) tm ").append(" join").append(
                " (select '100' charge_type_cd, sum(bf.money) fact_year,").append(
                " sum(decode(bf.fact_month, :one, bf.money, :two, bf.money, :three, bf.money, null)) fact_quarter,").append(
                " sum(decode(bf.fact_month, :one, bf.money, null)) fact_one,").append(" sum(decode(bf.fact_month, :two, bf.money, null)) fact_two,")
                .append(" sum(decode(bf.fact_month, :three, bf.money, null)) fact_three").append(" from bis_fact bf ").append(
                " where bf.bis_project_id = :bisProjectId and bf.fact_year = :year").append(
                " and bf.charge_type_cd not in ('02','03','38','21') ) tf on tm.charge_type_cd=tf.charge_type_cd ) tb")

                .append(" order by (decode(tb.charge_type_cd,'01',1,'11',2,'42',3,'41',4,'21',5,'31',6,'32',7,'100',8,9))");

        return sql.toString();
    }

    /**
     * 项目商家欠费提醒SQL
     */
    private String buildTotalStoreSql() {
        StringBuffer sql = new StringBuffer();

        sql
                .append("select")
                .append(" a.bis_shop_id,")
                .append(" NVL(f.name_cn,f.name_en)||f.company_name shopname,")
                .append(" e.square,")
                .append(" a.mustmoney,")
                .append(" b.factmoney,")
                .append(" round(b.factmoney/a.mustmoney*100,2) rate,")
                .append(" a.mustmoney-b.factmoney arrears,")
                .append(" case when 1-b.factmoney/a.mustmoney > 0.5 then 'one' when 1-b.factmoney/a.mustmoney > 0.3 then 'two' else 'three' end remindlevel,")
                .append(" d.arrearsAge,")
                .append(" c.earnestMoney,")
                .append(" f.shop_type_cd")
                .append(" from")
                .append(" ( select bm.bis_shop_id, sum(bm.money) mustmoney from bis_must bm")
                .append(" where bm.bis_tenant_id is not null and bm.status_cd='2' and bm.charge_type_cd in ('01','11','31','32','33','34','35','36','37')")
                .append(" and exists (select 1 from bis_tenant bt where bt.bis_tenant_id=bm.bis_tenant_id and bt.bis_project_id=:bisProjectId)")
                .append(" group by bm.bis_shop_id ) a")
                .append(" join")
                .append(" ( select bf.bis_shop_id, sum(bf.money) factmoney from bis_fact bf ")
                .append(
                        " where bf.bis_tenant_id is not null and bf.status_cd='1' and bf.charge_type_cd in ('01','11','31','32','33','34','35','36','37') and bf.bis_project_id=:bisProjectId")
                .append(" group by bf.bis_shop_id ) b on a.bis_shop_id=b.bis_shop_id")
                .append(" left join")
                .append(" ( select bis_shop_id, sum(money) earnestMoney from bis_fact ")
                .append(" where bis_tenant_id is not null and status_cd='1' and charge_type_cd in ('02','38') and bis_project_id=:bisProjectId")
                .append(" group by bis_shop_id ) c on a.bis_shop_id=c.bis_shop_id")
                .append(" left join")
                .append(" ( select c.bis_shop_id,")
                .append(" case when months_between(sysdate,min(c.firstdate))<12 then '1年以内'")
                .append("  when months_between(sysdate,min(c.firstdate))<24 then '1-2年'")
                .append("  when months_between(sysdate,min(c.firstdate))<36 then '2-3年'")
                .append("  else '3年以上' end arrearsAge")
                .append(" from")
                .append(" ( select a.bis_shop_id,")
                .append(" case when a.mustmoney-b.factmoney>0 then to_date(a.must_year||a.must_month, 'yyyyMM') end firstdate")
                .append(" from")
                .append(" ( select bm.bis_shop_id, bm.must_year, bm.must_month, sum(bm.money) mustmoney from bis_must bm")
                .append(" where bm.bis_tenant_id is not null and bm.status_cd='2' and bm.charge_type_cd in ('01','11','31','32','33','34','35','36','37')")
                .append(" and exists (select 1 from bis_tenant bt where bt.bis_tenant_id=bm.bis_tenant_id and bt.bis_project_id=:bisProjectId)")
                .append(" group by bm.bis_shop_id, bm.must_year, bm.must_month ) a ")
                .append(" left join")
                .append(" ( select bf.bis_shop_id, bf.fact_year, bf.fact_month, sum(bf.money) factmoney from bis_fact bf ")
                .append(
                        " where bf.bis_tenant_id is not null and bf.status_cd='1' and bf.charge_type_cd in ('01','11','31','32','33','34','35','36','37') and bf.bis_project_id=:bisProjectId")
                .append(
                        " group by bf.bis_shop_id, bf.fact_year, bf.fact_month ) b on a.bis_shop_id=b.bis_shop_id and a.must_year=b.fact_year and a.must_month=b.fact_month")
                .append(" ) c group by c.bis_shop_id").append(" ) d on a.bis_shop_id=d.bis_shop_id").append(" left join").append(
                " ( select a.bis_shop_id, sum(b.square) square from ").append(
                " (select bt.bis_shop_id, bsc.bis_store_id from bis_tenant bt join bis_cont bc on bt.bis_tenant_id=bc.bis_tenant_id ").append(
                " join bis_store_cont_rel bsc on bc.bis_cont_id=bsc.bis_cont_id").append(" where bt.bis_project_id=:bisProjectId").append(
                " group by bt.bis_shop_id, bsc.bis_store_id ) a").append(" join bis_store b on a.bis_store_id=b.bis_store_id").append(
                " group by a.bis_shop_id").append(" ) e on a.bis_shop_id=e.bis_shop_id").append(" left join bis_shop f on a.bis_shop_id=f.bis_shop_id")
                .append(" where a.mustmoney-b.factmoney > 0").append(" order by a.mustmoney-b.factmoney desc");

        return sql.toString();
    }

    /**
     * 项目公寓欠费提醒SQL
     */
    private String buildTotalFlatSql() {
        StringBuffer sql = new StringBuffer();

        sql.append("select").append(" a.bis_flat_id,").append(" a.building_num||a.floor_num||a.flat_no flatno, ").append(" a.square,").append(" b.mustmoney,")
                .append(" c.factmoney,").append(" round(c.factmoney/b.mustmoney*100,2) rate,").append(" b.mustmoney-c.factmoney arrears,").append(
                " case when 1-c.factmoney/b.mustmoney > 0.5 then 'one' when 1-c.factmoney/b.mustmoney > 0.3 then 'two' else 'three' end remind,")
                .append(" e.arrearsAge,").append(" d.earnestMoney").append(" from").append(
                " ( select bf.bis_flat_id,bfl.building_num,bfl.floor_num,bf.flat_no,bf.square ").append(
                " from bis_flat bf join bis_floor bfl on bf.bis_floor_id=bfl.bis_floor_id ) a").append(" left join").append(
                " ( select bfc.bis_flat_id, sum(bm.money) mustmoney").append(" from bis_must bm join bis_cont bc on bm.bis_cont_id=bc.bis_cont_id")
                .append(" join bis_flat_cont_rel bfc on bc.bis_cont_id=bfc.bis_cont_id").append(
                " where bm.status_cd='2' and bm.charge_type_cd in ('32','33','34','35','36','37')").append(
                " and bc.cont_big_type_cd='2' and bc.bis_project_id=:bisProjectId group by bfc.bis_flat_id").append(
                " ) b on a.bis_flat_id=b.bis_flat_id").append(" left join")
                .append(" ( select bf.bis_flat_id, sum(bf.money) factmoney from bis_fact bf").append(
                " where bf.status_cd='1' and bf.bis_flat_id is not null and bf.charge_type_cd in ('32','33','34','35','36','37')").append(
                " and bf.bis_project_id=:bisProjectId group by bf.bis_flat_id").append(" ) c on a.bis_flat_id=c.bis_flat_id").append(" left join")
                .append(" ( select bis_flat_id, sum(money) earnestMoney from bis_fact").append(
                " where bis_fact_id is not null and status_cd='1' and charge_type_cd ='38' and bis_project_id=:bisProjectId").append(
                " group by bis_flat_id").append(" ) d on a.bis_flat_id=d.bis_flat_id").append(" left join").append(" ( select c.bis_flat_id,").append(
                " case when months_between(sysdate,min(c.firstdate))<12 then '1年以内'").append(
                " when months_between(sysdate,min(c.firstdate))<24 then '1-2年'")
                .append(" when months_between(sysdate,min(c.firstdate))<36 then '2-3年'").append(" else '3年以上' end arrearsAge").append(" from").append(
                " ( select a.bis_flat_id,").append(
                " case when a.mustmoney-b.factmoney>0 then to_date(a.must_year||a.must_month, 'yyyyMM') end firstdate").append(" from").append(
                " ( select bfc.bis_flat_id, bm.must_year, bm.must_month, sum(bm.money) mustmoney").append(
                " from bis_must bm join bis_cont bc on bm.bis_cont_id=bc.bis_cont_id").append(
                " join bis_flat_cont_rel bfc on bc.bis_cont_id=bfc.bis_cont_id").append(
                " where bm.status_cd='2' and bm.charge_type_cd in ('32','33','34','35','36','37')").append(
                " and bc.cont_big_type_cd='2' and bc.bis_project_id=:bisProjectId")
                .append(" group by bfc.bis_flat_id, bm.must_year, bm.must_month ) a").append(" left join").append(
                " ( select bf.bis_flat_id, bf.fact_year, bf.fact_month, sum(bf.money) factmoney from bis_fact bf").append(
                " where bf.status_cd='1' and bf.bis_flat_id is not null and bf.charge_type_cd in ('32','33','34','35','36','37')").append(
                " and bf.bis_project_id=:bisProjectId group by bf.bis_flat_id, bf.fact_year, bf.fact_month").append(
                " ) b on a.bis_flat_id=b.bis_flat_id and a.must_year=b.fact_year and a.must_month=b.fact_month").append(" ) c group by c.bis_flat_id")
                .append(" ) e on a.bis_flat_id = e.bis_flat_id").append(" where b.mustmoney-c.factmoney > 0").append(" order by b.mustmoney-c.factmoney desc");

        return sql.toString();
    }

    /**
     * 设值：项目费用报表
     */
    private void setTotalFeeValue(List<Object[]> list) {
        List<BisProjectReportVo> tmpList = new ArrayList<BisProjectReportVo>();
        BisProjectReportVo totalInVo = new BisProjectReportVo();
        BisProjectReportVo propVo = new BisProjectReportVo();
        BisProjectReportVo energyVo = new BisProjectReportVo();
        BisProjectReportVo totalOutVo = new BisProjectReportVo();
        BigDecimal zero = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            Object[] result = list.get(i);
            if (i < 9) {
                BisProjectReportVo vo = new BisProjectReportVo();
                vo.setChargeTypeCd((String) result[0]);
                vo.setChargeTypeName((String) result[1]);
                vo.setMustYear((BigDecimal) result[2]);
                vo.setMustQuarter((BigDecimal) result[3]);
                vo.setMustMonthOne((BigDecimal) result[4]);
                vo.setMustMonthTwo((BigDecimal) result[5]);
                vo.setMustMonthThree((BigDecimal) result[6]);
                vo.setFactYear((BigDecimal) result[7]);
                vo.setFactQuarter((BigDecimal) result[8]);
                vo.setFactMonthOne((BigDecimal) result[9]);
                vo.setFactMonthTwo((BigDecimal) result[10]);
                vo.setFactMonthThree((BigDecimal) result[11]);
                if (vo.getMustYear() != null && vo.getFactYear() != null && vo.getMustYear().compareTo(zero) != 0) {
                    vo.setRateYear(vo.getFactYear().multiply(new BigDecimal(100)).divide(vo.getMustYear(), 2, BigDecimal.ROUND_HALF_UP));
                }
                if (vo.getMustQuarter() != null && vo.getFactQuarter() != null && vo.getMustQuarter().compareTo(zero) != 0) {
                    vo.setRateQuarter(vo.getFactQuarter().multiply(new BigDecimal(100)).divide(vo.getMustQuarter(), 2, BigDecimal.ROUND_HALF_UP));
                }
                if (vo.getMustMonthOne() != null && vo.getFactMonthOne() != null && vo.getMustMonthOne().compareTo(zero) != 0) {
                    vo.setRateMonthOne(vo.getFactMonthOne().multiply(new BigDecimal(100)).divide(vo.getMustMonthOne(), 2, BigDecimal.ROUND_HALF_UP));
                }
                if (vo.getMustMonthTwo() != null && vo.getFactMonthTwo() != null && vo.getMustMonthTwo().compareTo(zero) != 0) {
                    vo.setRateMonthTwo(vo.getFactMonthTwo().multiply(new BigDecimal(100)).divide(vo.getMustMonthTwo(), 2, BigDecimal.ROUND_HALF_UP));
                }
                if (vo.getMustMonthThree() != null && vo.getFactMonthThree() != null && vo.getMustMonthThree().compareTo(zero) != 0) {
                    vo.setRateMonthThree(vo.getFactMonthThree().multiply(new BigDecimal(100)).divide(vo.getMustMonthThree(), 2, BigDecimal.ROUND_HALF_UP));
                }
                // vo.setRateYear((BigDecimal) result[12]);
                // vo.setRateQuarter((BigDecimal) result[13]);
                // vo.setRateMonthOne((BigDecimal) result[14]);
                // vo.setRateMonthTwo((BigDecimal) result[15]);
                // vo.setRateMonthThree((BigDecimal) result[16]);
                if (i == 0) {
                    inlist.add(vo);
                } else if (i > 0 && i < 4) {
                    tmpList.add(vo);
                } else if (i == 4) {
                    outlist.add(vo);
                    totalOutVo = vo;
                } else if (i == 5) {
                    propVo = vo;
                } else if (i == 6) {
                    if (propVo.getMustYear() == null) {
                        propVo = vo;
                    }
                    inlist.add(propVo);
                } else if (i == 7) {
                    totalInVo = vo;
                } else {
                    energyVo = vo;
                }
            } else {
                energyVo.setMustYear(energyVo.getMustYear().add((BigDecimal) result[2]));
                energyVo.setMustQuarter(energyVo.getMustQuarter().add((BigDecimal) result[3]));
                energyVo.setMustMonthOne(energyVo.getMustMonthOne().add((BigDecimal) result[4]));
                energyVo.setMustMonthTwo(energyVo.getMustMonthTwo().add((BigDecimal) result[5]));
                energyVo.setMustMonthThree(energyVo.getMustMonthThree().add((BigDecimal) result[6]));
                energyVo.setFactYear(energyVo.getFactYear().add((BigDecimal) result[7]));
                energyVo.setFactQuarter(energyVo.getFactQuarter().add((BigDecimal) result[8]));
                energyVo.setFactMonthOne(energyVo.getFactMonthOne().add((BigDecimal) result[9]));
                energyVo.setFactMonthTwo(energyVo.getFactMonthTwo().add((BigDecimal) result[10]));
                energyVo.setFactMonthThree(energyVo.getFactMonthThree().add((BigDecimal) result[11]));
            }
        }

        if (energyVo.getMustYear() != null && energyVo.getFactYear() != null && energyVo.getMustYear().compareTo(zero) != 0) {
            energyVo.setRateYear(energyVo.getFactYear().multiply(new BigDecimal(100)).divide(energyVo.getMustYear(), 2, BigDecimal.ROUND_HALF_UP));
        }
        if (energyVo.getMustQuarter() != null && energyVo.getFactQuarter() != null && energyVo.getMustQuarter().compareTo(zero) != 0) {
            energyVo.setRateQuarter(energyVo.getFactQuarter().multiply(new BigDecimal(100)).divide(energyVo.getMustQuarter(), 2, BigDecimal.ROUND_HALF_UP));
        }
        if (energyVo.getMustMonthOne() != null && energyVo.getFactMonthOne() != null && energyVo.getMustMonthOne().compareTo(zero) != 0) {
            energyVo.setRateMonthOne(energyVo.getFactMonthOne().multiply(new BigDecimal(100)).divide(energyVo.getMustMonthOne(), 2, BigDecimal.ROUND_HALF_UP));
        }
        if (energyVo.getMustMonthTwo() != null && energyVo.getFactMonthTwo() != null && energyVo.getMustMonthTwo().compareTo(zero) != 0) {
            energyVo.setRateMonthTwo(energyVo.getFactMonthTwo().multiply(new BigDecimal(100)).divide(energyVo.getMustMonthTwo(), 2, BigDecimal.ROUND_HALF_UP));
        }
        if (energyVo.getMustMonthThree() != null && energyVo.getFactMonthThree() != null && energyVo.getMustMonthThree().compareTo(zero) != 0) {
            energyVo.setRateMonthThree(energyVo.getFactMonthThree().multiply(new BigDecimal(100)).divide(energyVo.getMustMonthThree(), 2,
                    BigDecimal.ROUND_HALF_UP));
        }
        inlist.add(energyVo);
        inlist.addAll(tmpList);
        inlist.add(new BisProjectReportVo("招商代理费"));
        inlist.add(new BisProjectReportVo("多经收入及其他"));
        inlist.add(new BisProjectReportVo("保证金"));
        inlist.add(totalInVo);

        outlist.add(new BisProjectReportVo("成本费用支出"));
        outlist.add(new BisProjectReportVo("广告宣传费"));
        outlist.add(new BisProjectReportVo("人工福利费"));
        outlist.add(new BisProjectReportVo("项目能源费"));
        outlist.add(new BisProjectReportVo("日常办公支出"));
        totalOutVo.setChargeTypeCd("100");
        totalOutVo.setChargeTypeName("合计");
        outlist.add(totalOutVo);

        totalOutVo.setChargeTypeCd("200");
        totalReportVo = new BisProjectReportVo();
        totalReportVo.setChargeTypeName("总计");
        totalReportVo.setMustYear(totalInVo.getMustYear().subtract(totalOutVo.getMustYear()));
        totalReportVo.setMustQuarter(totalInVo.getMustQuarter().subtract(totalOutVo.getMustQuarter()));
        totalReportVo.setMustMonthOne(totalInVo.getMustMonthOne().subtract(totalOutVo.getMustMonthOne()));
        totalReportVo.setMustMonthTwo(totalInVo.getMustMonthTwo().subtract(totalOutVo.getMustMonthTwo()));
        totalReportVo.setMustMonthThree(totalInVo.getMustMonthThree().subtract(totalOutVo.getMustMonthThree()));
        totalReportVo.setFactYear(totalInVo.getFactYear().subtract(totalOutVo.getFactYear()));
        totalReportVo.setFactQuarter(totalInVo.getFactQuarter().subtract(totalOutVo.getFactQuarter()));
        totalReportVo.setFactMonthOne(totalInVo.getFactMonthOne().subtract(totalOutVo.getFactMonthOne()));
        totalReportVo.setFactMonthTwo(totalInVo.getFactMonthTwo().subtract(totalOutVo.getFactMonthTwo()));
        totalReportVo.setFactMonthThree(totalInVo.getFactMonthThree().subtract(totalOutVo.getFactMonthThree()));
        if (totalReportVo.getMustYear() != null && totalReportVo.getFactYear() != null && totalReportVo.getMustYear().compareTo(zero) != 0) {
            totalReportVo.setRateYear(totalReportVo.getFactYear().multiply(new BigDecimal(100))
                    .divide(totalReportVo.getMustYear(), 2, BigDecimal.ROUND_HALF_UP));
        }
        if (totalReportVo.getMustQuarter() != null && totalReportVo.getFactQuarter() != null && totalReportVo.getMustQuarter().compareTo(zero) != 0) {
            totalReportVo.setRateQuarter(totalReportVo.getFactQuarter().multiply(new BigDecimal(100)).divide(totalReportVo.getMustQuarter(), 2,
                    BigDecimal.ROUND_HALF_UP));
        }
        if (totalReportVo.getMustMonthOne() != null && totalReportVo.getFactMonthOne() != null && totalReportVo.getMustMonthOne().compareTo(zero) != 0) {
            totalReportVo.setRateMonthOne(totalReportVo.getFactMonthOne().multiply(new BigDecimal(100)).divide(totalReportVo.getMustMonthOne(), 2,
                    BigDecimal.ROUND_HALF_UP));
        }
        if (totalReportVo.getMustMonthTwo() != null && totalReportVo.getFactMonthTwo() != null && totalReportVo.getMustMonthTwo().compareTo(zero) != 0) {
            totalReportVo.setRateMonthTwo(totalReportVo.getFactMonthTwo().multiply(new BigDecimal(100)).divide(totalReportVo.getMustMonthTwo(), 2,
                    BigDecimal.ROUND_HALF_UP));
        }
        if (totalReportVo.getMustMonthThree() != null && totalReportVo.getFactMonthThree() != null && totalReportVo.getMustMonthThree().compareTo(zero) != 0) {
            totalReportVo.setRateMonthThree(totalReportVo.getFactMonthThree().multiply(new BigDecimal(100)).divide(totalReportVo.getMustMonthThree(), 2,
                    BigDecimal.ROUND_HALF_UP));
        }
    }

    /**
     * 设值：项目商家欠费提醒
     */
    private BigDecimal setTotalStoreValue(List<BisArrearsRemindVo> list) {
        storeMap.put("1", new ArrayList<BisArrearsRemindVo>());
        storeMap.put("2", new ArrayList<BisArrearsRemindVo>());
        storeMap.put("3", new ArrayList<BisArrearsRemindVo>());
        BigDecimal total = new BigDecimal(0);
        if (null != list) {
            for (BisArrearsRemindVo vo : list) {
                storeMap.get(vo.getShopTypeCd()).add(vo);
                total = total.add((BigDecimal) vo.getAccumulateArrears());
            }
        }
        return total;
    }

    private BigDecimal setTotalStoreValueOld(List<Object[]> list) {
        storeMap.put("1", new ArrayList<BisArrearsRemindVo>());
        storeMap.put("2", new ArrayList<BisArrearsRemindVo>());
        storeMap.put("3", new ArrayList<BisArrearsRemindVo>());
        BigDecimal total = new BigDecimal(0);

        for (Object[] data : list) {

            BisArrearsRemindVo vo = new BisArrearsRemindVo();
            vo.setId((String) data[0]);
            vo.setName((String) data[1]);
            vo.setArea((BigDecimal) data[2]);
            vo.setAccumulateMust((BigDecimal) data[3]);
            vo.setAccumulateFact((BigDecimal) data[4]);
            vo.setAccumulateRate((BigDecimal) data[5]);
            vo.setAccumulateArrears((BigDecimal) data[6]);
            vo.setRemindLevel((String) data[7]);
            vo.setArrearsAge((String) data[8]);
            vo.setEarnestMoney((BigDecimal) data[9]);
            vo.setShopTypeCd((String) data[10]);

            storeMap.get(vo.getShopTypeCd()).add(vo);

            total = total.add((BigDecimal) data[6]);
        }
        return total;
    }

    /**
     * 设值：项目公寓欠费提醒
     */
    private BigDecimal setTotalFlatValue(List<Object[]> list) {
        BigDecimal total = new BigDecimal(0);

        for (Object[] data : list) {

            BisArrearsRemindVo vo = new BisArrearsRemindVo();
            vo.setId((String) data[0]);
            vo.setName((String) data[1]);
            vo.setArea((BigDecimal) data[2]);
            vo.setAccumulateMust((BigDecimal) data[3]);
            vo.setAccumulateFact((BigDecimal) data[4]);
            vo.setAccumulateRate((BigDecimal) data[5]);
            vo.setAccumulateArrears((BigDecimal) data[6]);
            vo.setRemindLevel((String) data[7]);
            vo.setArrearsAge((String) data[8]);
            vo.setEarnestMoney((BigDecimal) data[9]);

            flatList.add(vo);

            total = total.add((BigDecimal) data[6]);
        }
        return total;
    }

    public Map<String, String> getMapBisProject() {
        return bisProjectManager.getMapBisProject();
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getManageCd() {
        return manageCd;
    }

    public void setManageCd(String manageCd) {
        this.manageCd = manageCd;
    }

    public String getBisProjectId() {
        return bisProjectId;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getChargeTypeCd() {
        return chargeTypeCd;
    }

    public void setChargeTypeCd(String chargeTypeCd) {
        this.chargeTypeCd = chargeTypeCd;
    }

    public List<BisStoreReportVo> getBisStoreReportVos() {
        return bisStoreReportVos;
    }

    public List<BisFlatReportVo> getBisFlatReportVos() {
        return bisFlatReportVos;
    }

    public List<BisMultiReportVo> getBisMultiReportVos() {
        return bisMultiReportVos;
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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    public void setMonthIndex(int monthIndex) {
        this.monthIndex = monthIndex;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<BisProjectReportVo> getInlist() {
        return inlist;
    }

    public List<BisProjectReportVo> getOutlist() {
        return outlist;
    }

    public Map<String, List<BisArrearsRemindVo>> getStoreMap() {
        return storeMap;
    }

    public List<BisArrearsRemindVo> getFlatList() {
        return flatList;
    }

    public List<BisArrearsRemindVo> getMultiList() {
        return multiList;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getMonthOne() {
        return monthOne;
    }

    public String getMonthTwo() {
        return monthTwo;
    }

    public String getMonthThree() {
        return monthThree;
    }

    public BisProjectReportVo getTotalReportVo() {
        return totalReportVo;
    }

    public void setTotalReportVo(BisProjectReportVo totalReportVo) {
        this.totalReportVo = totalReportVo;
    }

    public Map<String, BisReport> getResultMap() {
        return resultMap;
    }

    public String[] getChargeTypeArr() {
        return chargeTypeArr;
    }

    public void setChargeTypeArr(String[] chargeTypeArr) {
        this.chargeTypeArr = chargeTypeArr;
    }

    /**
     * @return the curMonthBisBudget
     */
    public BisBudget getCurMonthBisBudget() {
        return curMonthBisBudget;
    }

    /**
     * @param curMonthBisBudget the curMonthBisBudget to set
     */
    public void setCurMonthBisBudget(BisBudget curMonthBisBudget) {
        this.curMonthBisBudget = curMonthBisBudget;
    }

    /**
     * @return the curYearBisBudget
     */
    public BisBudget getCurYearBisBudget() {
        return curYearBisBudget;
    }

    /**
     * @param curYearBisBudget the curYearBisBudget to set
     */
    public void setCurYearBisBudget(BisBudget curYearBisBudget) {
        this.curYearBisBudget = curYearBisBudget;
    }

	public String getReportsType() {
		return reportsType;
	}

	public void setReportsType(String reportsType) {
		this.reportsType = reportsType;
	}

	public BisBudget getCurAllYearBisBudget() {
		return curAllYearBisBudget;
	}

	public void setCurAllYearBisBudget(BisBudget curAllYearBisBudget) {
		this.curAllYearBisBudget = curAllYearBisBudget;
	}

	public BisBudgetVo getCurMonthBisBudgetVo() {
		return curMonthBisBudgetVo;
	}

	public void setCurMonthBisBudgetVo(BisBudgetVo curMonthBisBudgetVo) {
		this.curMonthBisBudgetVo = curMonthBisBudgetVo;
	}

	public BisBudgetVo getCurYearBisBudgetVo() {
		return curYearBisBudgetVo;
	}

	public void setCurYearBisBudgetVo(BisBudgetVo curYearBisBudgetVo) {
		this.curYearBisBudgetVo = curYearBisBudgetVo;
	}

	public BisBudgetVo getCurAllYearBisBudgetVo() {
		return curAllYearBisBudgetVo;
	}

	public void setCurAllYearBisBudgetVo(BisBudgetVo curAllYearBisBudgetVo) {
		this.curAllYearBisBudgetVo = curAllYearBisBudgetVo;
	}

}
