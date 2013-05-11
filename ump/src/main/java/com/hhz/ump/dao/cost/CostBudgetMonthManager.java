package com.hhz.ump.dao.cost;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.cont.ContLedgerDao;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContPay;
import com.hhz.ump.entity.cost.CostBudgetMonth;
import com.hhz.ump.entity.cost.CostBudgetMonthDetail;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.web.vo.CostBudgetMonthVo;

@Service
@Transactional
public class CostBudgetMonthManager extends BaseService<CostBudgetMonth, String> {
	@Autowired
	private CostBudgetMonthDao costBudgetMonthDao;
	@Autowired
	private CostBudgetMonthDetailDao costBudgetMonthDetailDao;
	@Autowired
	private ContLedgerDao contLedgerDao;
	
	@Autowired
	private CostProjectSectionManager costProjectSectionManager;
	/**
	 * 项目成本
	 */
	public static String MONTH_BUDGET_PROJECT = "1";
	/**
	 * 区域成本
	 */
	public static String MONTH_BUDGET_AREA_COST = "2";
	/**
	 * 区域财务
	 */
	public static String MONTH_BUDGET_AREA_FIN = "3";
	/**
	 * 归档
	 */
	public static String MONTH_BUDGET_ARCHIVE = "4";

	public void saveCostBudgetMonth(CostBudgetMonth costBudgetMonth) {
		PowerUtils.setEmptyStr2Null(costBudgetMonth);
		costBudgetMonthDao.save(costBudgetMonth);
	}

	public void deleteCostBudgetMonth(String id) {
		costBudgetMonthDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostBudgetMonth, String> getDao() {
		return costBudgetMonthDao;
	}
	
	/**
	 * 根据ID 搜索月度资金计划
	 * @param costBudgetMonthId 月度资金ID
	 * @return
	 */
	public CostBudgetMonth getCostBudgetMonthById(String costBudgetMonthId){
		CostBudgetMonth costBudgetMonth=null;
		StringBuffer hql=new StringBuffer().append(" from CostBudgetMonth m where m.costBudgetMonthId = :costBudgetMonthId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("costBudgetMonthId", costBudgetMonthId);
		List<CostBudgetMonth> rs=costBudgetMonthDao.find(hql.toString(), map);
		if(rs!=null&&rs.size()>0){
			costBudgetMonth=rs.get(0);
		}
		return costBudgetMonth;
	}

	public CostBudgetMonth exsitBudgetMonth(String costProjectSectionId, String ym) {
		CostBudgetMonth costBudgetMonth=null;
		try {
			//准备搜索语句
			StringBuffer hql = new StringBuffer()
			.append(" from CostBudgetMonth c where 1=1 ")
			.append(" and c.costProjectSection.costProjectSectionId = :costProjectSectionId")
			.append(" and c.budegetYear = :budegetYear")
			.append(" and c.budegetMonth = :budegetMonth");
			//搜索条件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("costProjectSectionId", costProjectSectionId);
			if(StringUtils.isNotBlank(ym)){
				String []yms=ym.split("-");
				map.put("budegetYear", Long.valueOf(yms[0]));
				map.put("budegetMonth", Long.valueOf(yms[1]));
			}
			//执行搜索
			List<CostBudgetMonth> rs=costBudgetMonthDao.find(hql.toString(), map);
			if(rs!=null&&rs.size()>0){
				costBudgetMonth=rs.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return costBudgetMonth;
	}
	
	/**
	 * 月度资金汇总搜索(不分页搜索)
	 * @author liuzhihui 2012-03-06
	 * @param yearMonth
	 * @return
	 */
	public List<CostBudgetMonthVo> findMonthTotalByYm(String yearMonth){

		
		String[] arr = StringUtils.split(yearMonth, "-");
		Long iYear = Long.valueOf(arr[0]);
		Long iMonth = Long.valueOf(arr[1]);
		
		
		
		List<CostBudgetMonthVo> voList = new ArrayList<CostBudgetMonthVo>();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("year", iYear);
		values.put("month", iMonth);

		
		//获取有权限的项目ID
		List<String> sectionIdList = costProjectSectionManager.getSectionIdList();
		values.put("sectionIdList", sectionIdList);
		
		
		StringBuffer sb = new StringBuffer() 
			.append("  select * from (")
			.append("  select distinct ")
			.append("         tt.project_cd, ")//0 项目公司
			.append("         tt.project_name, ")//1 项目公司
			.append("         tt.cost_project_section_id, ")//2 项目
			.append("         tt.section_name,  ")//3 项目
			.append("         tt1.group_total_amt, (");//4
		
		for (int i = 1; i <= iMonth; i++) {
			sb.append(" nvl(tt1.plan_amt_m").append(StringUtils.leftPad(String.valueOf(i), 2, "0")).append(",0) ");//2位,不足补充0//5
			if( i < iMonth){
				sb.append(" + ");
			}
		}
		  sb.append("  ) as plan_amt_m, ")
		    .append(" 	      tt1.bases_desc_m").append(StringUtils.leftPad(String.valueOf(iMonth), 2, "0")).append(",")//2位,不足补充0//6
			.append("         sum(tt2.cumu_real_pay_total_amt) as cumu_real_pay_total_amt, ")//7
			.append("         sum(tt2.cur_period_fund_budget_amt3) as cur_period_fund_budget_amt3, ")//8
			.append("         count(tt2.cost_budget_month_detail_id) as detailCount, ")//9
			.append("         count(tt1.cost_budget_year_id) as yearCount, ")//10
			.append("         nvl(tt1.cost_budget_year_id,'none') ,")//11
			.append("         nvl(max(tt2.status_cd),'-1') as status_cd,")//12
			.append("         nvl(max(tt2.cost_budget_month_id),'-') as cost_budget_month_id")//13
			.append("  from cost_project_section tt ")
			.append("  left join ( ")
			.append("       select p.* ")
			.append("       from cost_budget_year p ")
			.append("       where p.budget_year = :year  ")
			.append("  )tt1 on tt.cost_project_section_id = tt1.cost_project_section_id ")
			.append("  left join ( ")
			.append("       select t1.cost_project_section_id,t1.status_cd,t2.* ")
			.append("       from  ")
			.append("       cost_budget_month        t1, ")
			.append("       cost_budget_month_detail t2 ")
			.append("       where t1.budeget_year  = :year ")
			.append("         and t1.budeget_month = :month   ")
			.append("         and t2.cost_budget_month_id = t1.cost_budget_month_id ")
			.append("  )tt2 on tt.cost_project_section_id = tt2.cost_project_section_id ")
			.append(" 		 ")
			.append("  where tt.cost_project_section_id in (:sectionIdList) ")
			.append("  group by tt.project_cd, ")
			.append("           tt.project_name,")
			.append("           tt.cost_project_section_id, ")
			.append("           tt.section_name,  ")
			.append("           tt1.group_total_amt, (");

			for (int i = 1; i <= iMonth; i++) {
				sb.append(" nvl(tt1.plan_amt_m").append(StringUtils.leftPad(String.valueOf(i), 2, "0")).append(",0) ");//2位,不足补充0
				if( i < iMonth){
					sb.append(" + ");
				}
			}
		  sb.append("  ), ")
			.append(" 	    tt1.bases_desc_m").append(StringUtils.leftPad(String.valueOf(iMonth), 2, "0")).append(", ")//2位,不足补充0
			.append("       tt1.cost_budget_year_id ")//11
			.append("  order by tt.project_cd, tt.project_name, tt.section_name ");
	        
		  sb.append("  ) tttt ");
		  
		  //控制，只出来“有年度计划"或"月度计划"的项目
		  sb.append("  where detailCount > 0 or yearCount >0 ");
		  
		List list = getDao().findBySql(sb.toString(), values);
		if (list == null || list.size() == 0)
			return voList;
		else {
			Object[] obj = null;
			CostBudgetMonthVo tmp = null;
			BigDecimal groupTotalAmt = null;
			BigDecimal planAmtMCount = null;
			BigDecimal cumuRealPayTotalAmt = null;
			BigDecimal curPeriodFundBudgetAmt3 = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				groupTotalAmt = new BigDecimal(0);
				planAmtMCount = new BigDecimal(0);
				cumuRealPayTotalAmt = new BigDecimal(0);
				curPeriodFundBudgetAmt3 = new BigDecimal(0);
				
				if ((BigDecimal)obj[4] != null) {
					//数值以元计算: 乘以10000
					groupTotalAmt = (BigDecimal) obj[4];//((BigDecimal) obj[4]).multiply(new BigDecimal(10000));
				}
				if ((BigDecimal)obj[5] != null) {
					planAmtMCount = (BigDecimal) obj[5];//((BigDecimal) obj[5]).multiply(new BigDecimal(10000));
				}
				if ((BigDecimal)obj[7] != null) {
					cumuRealPayTotalAmt =  (BigDecimal)obj[7];
					if(!BigDecimal.ZERO.equals(cumuRealPayTotalAmt)){
						cumuRealPayTotalAmt=cumuRealPayTotalAmt.divide(new BigDecimal(10000), RoundingMode.HALF_UP);
					}
				}
				if ((BigDecimal)obj[8] != null) {
					curPeriodFundBudgetAmt3 =  (BigDecimal)obj[8];
					if(!BigDecimal.ZERO.equals(curPeriodFundBudgetAmt3)){
						curPeriodFundBudgetAmt3=curPeriodFundBudgetAmt3.divide(new BigDecimal(10000), RoundingMode.HALF_UP);
					}
				}
				tmp = new CostBudgetMonthVo();
				tmp.setOrderNo(i+1);//显示序号
			    tmp.setProjectId((String)(obj[2]));//项目ID（即sectionId)
				tmp.setProjectName((String)obj[3]);//项目名称
				tmp.setGroupTotalAmt(groupTotalAmt);//年度预算合计
				tmp.setPlanAmtMCount(planAmtMCount);//至本期的年度预算合计
				tmp.setCumuRealPayTotalAmt(cumuRealPayTotalAmt);//本年度至本期累计实付 	
				tmp.setCurPeriodFundBudgetAmt3(curPeriodFundBudgetAmt3);//本期资金预算
				tmp.setDesc((String)obj[6]); //偏差说明
				BigDecimal deviationAmt = planAmtMCount.subtract(cumuRealPayTotalAmt);
				BigDecimal deviationAmt2 = deviationAmt.subtract(curPeriodFundBudgetAmt3);
				tmp.setDeviationAmt(deviationAmt2);//预算偏差 = 至本期的年度预算合计 - 本年度至本期累计实付 - 本期资金预算
				tmp.setCostBudgetYearId((String)obj[11]); //年度表ID
				tmp.setStatusCd((String)obj[12]);
				tmp.setCostMonthId((String)obj[13]);
				voList.add(tmp);
			}
		}
		return voList;
	}
	

	/**
	 * 创建月度资金计划
	 * @param budgetMonth
	 * @param codeTypeCd2Map
	 */
	public void createBudgetMonth(CostBudgetMonth budgetMonth,Map<String,String> codeTypeCd2Map) {
		
		saveCostBudgetMonth(budgetMonth);
		
		CostProjectSection section = budgetMonth.getCostProjectSection();
		
		//根据项目和期数,获取合同台账清单
		List<ContLedger> tmpList = contLedgerDao.getContLedgerList(section.getProjectCd(), section.getSectionCd());
		
		CostBudgetMonthDetail tDetail = null;
		for (ContLedger tCont : tmpList) {
			//拷贝合同
			tDetail = createMonthDetail(budgetMonth, tCont, codeTypeCd2Map);
			//提交保存明细
			costBudgetMonthDetailDao.save(tDetail);
		}
		System.out.println(">>>>>>>>>>>>>>>>> 导入合同清单： 一共  " + tmpList.size() + " 条!");
	}
	
	/**
	 * 创建月度资金计划明细
	 * 
	 * @param month 月度计划主表
	 * @param cl  合同台账(地产)
	 * @param codeTypeCd2Map 代码表
	 * @return
	 */
	public CostBudgetMonthDetail createMonthDetail(CostBudgetMonth month, ContLedger cl, Map<String,String> codeTypeCd2Map ){

		CostBudgetMonthDetail tmpDetail = new CostBudgetMonthDetail();
		//获取月度资金抬头
		tmpDetail.setCostBudgetMonth(month);
		//成本科目
		tmpDetail.setSubjectName(codeTypeCd2Map.get(cl.getContTypeCd2()));
		tmpDetail.setSubjectCd(cl.getContTypeCd2());
		//合同编号 	
		tmpDetail.setContactNo(cl.getContNo());		
		tmpDetail.setContactId(cl.getContLedgerId());
		//合同名称
		tmpDetail.setContactName(cl.getContName());
		//施工单位（乙方）
		tmpDetail.setPartb(cl.getPartB());
		//合同总价
		tmpDetail.setContactTotalAmt(cl.getTotalPrice());
		//实际合同总价
		tmpDetail.setContactRealTotalAmt(cl.getUpdateTotal());
		//结算价
		tmpDetail.setSettleAmt(cl.getClearPrice());
		//已完产值合计（元）
		BigDecimal completeNum = new BigDecimal(0);
		//其中甲供料产值
		BigDecimal matieralNum = new BigDecimal(0);
		//累计应付款合计(元)
		BigDecimal currentAdd = new BigDecimal(0);
		//累计实际支付
		BigDecimal currentPay = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		if(cl.getPayNum()!=null) {
			currentPay=cl.getPayNum();
		}
		//累计支付比率%
		BigDecimal payRate = new BigDecimal(0);
		//本期：资金应付预算
		BigDecimal currentPayBudget = new BigDecimal(0);
		//累计：应付未付
		BigDecimal cumuMustNoPayAmt = new BigDecimal(0);
		
		BigDecimal zero = new BigDecimal(0);
		for(ContPay contPay : cl.getContPaies()) {
			if(contPay.getCompleteNum() != null) {
				completeNum = completeNum.add(contPay.getCompleteNum());
			}
			if(contPay.getMatieralNum() != null) {
				matieralNum = matieralNum.add(contPay.getMatieralNum());
			}
			if(contPay.getCurrentPay() != null) {
				currentAdd = currentAdd.add(contPay.getCurrentPay());
			}
			/*if(contPay.getCurrentPay() != null) {
				currentPay = currentPay.add(contPay.getp);
			}*/
		}
		if(completeNum.compareTo(zero) != 0) {
			//(扣：甲供料款(按暂定价)+扣：其他扣款或代扣款+直接支付)/已确认产值(含甲供料)
			payRate = (matieralNum.add(currentAdd).add(currentPay)).divide(completeNum, 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
		}
		currentPayBudget=currentAdd.subtract(currentPay);
		//累计应付款合计(元)-累计实际支付
		cumuMustNoPayAmt=currentAdd.subtract(currentPay);
		//填充统计值
		tmpDetail.setFinishProdTotalAmt(completeNum);
		tmpDetail.setNailFeedWorthAmt(matieralNum);
		tmpDetail.setCumuMustPayTotalAmt(currentAdd);
		tmpDetail.setCumuRealPayTotalAmt(currentPay);
		tmpDetail.setCumuPaiedRate(payRate);
		tmpDetail.setCumuMustNoPayAmt(cumuMustNoPayAmt);
		//通过"累计：应付未付","本期：资金应付预算"计算出"本期资金预算"
		BigDecimal curPeriodFundMpayAmt = BigDecimal.ZERO;
		if (tmpDetail.getCurPeriodFundMpayAmt() != null) {
			curPeriodFundMpayAmt = tmpDetail.getCurPeriodFundMpayAmt();
		}
		if (tmpDetail.getCumuMustNoPayAmt() != null) {
			cumuMustNoPayAmt = tmpDetail.getCumuMustNoPayAmt();
		}
		// 设置"本期资金预算（地产上报）"
		tmpDetail.setCurPeriodFundBudgetAmt(cumuMustNoPayAmt.add(curPeriodFundMpayAmt));
		//搜索当前序列号
		tmpDetail.setSequenceNo(0L);
//		tmpDetail.setSequenceNo(costBudgetMonthDetailManager.getcurrentSequenceNo(month.getCostBudgetMonthId()));
		return tmpDetail;
	}
}

