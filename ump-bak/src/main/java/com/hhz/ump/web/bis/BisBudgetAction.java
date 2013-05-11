package com.hhz.ump.web.bis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisBudgetManager;
import com.hhz.ump.entity.bis.BisBudget;

public class BisBudgetAction extends CrudActionSupport<BisBudget> {
	private boolean NO_PAGE_SIZE = true;
	private BisBudget entity;
	@Autowired
	private BisBudgetManager bisBudgetManager;
	// private BisBudget bisBudget;
	private Page<BisBudget> voPage = new Page(10);
	
	private String currentMonth;

	@Override
	public BisBudget getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} else if (NO_PAGE_SIZE) {
			page.setPageSize(Integer.valueOf("30"));

		}
		page.setTotalCount(0);

		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			voPage.setPageSize(Integer.valueOf(rows));
		} else if (NO_PAGE_SIZE) {
			voPage.setPageSize(Integer.valueOf("10"));

		}

		String monthStr = Struts2Utils.getParameter("monthStr");
		String bisProjectId = Struts2Utils.getParameter("bisProjectId");
		StringBuffer hql = new StringBuffer(" from BisBudget t where 1=1 and t.yearStatus='1' ");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("bisProjectId", bisProjectId);
		if (StringUtils.isNotBlank(monthStr)) {
			hql.append(" and t.monthStr=:monthStr ");
			values.put("monthStr", monthStr);
		}
		hql.append(" and t.bisProjectId=:bisProjectId ");
		hql.append(" order by t.monthStr ");
		values.put("monthStr", monthStr);

		voPage = bisBudgetManager.findPage(voPage, hql.toString(), values);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String curDate = sdf.format(new Date());
		for (int i = 0; i < voPage.getResult().size(); i++) {
			BisBudget bisBudget = (BisBudget)voPage.getResult().get(i);
			if(curDate.trim().equals(bisBudget.getMonthStr())){
				currentMonth = bisBudget.getMonthStr();
			}
		}
		page.setPageNo(voPage.getPageNo());
		page.setPageSize(voPage.getPageSize());
		// page.setPageSize(voPage.getPageSize());
		page.setTotalCount(voPage.getTotalCount());
		return "list";
	}
	

	@Override
	public String input() throws Exception {

		try {

			String bisProjectId = Struts2Utils.getParameter("bisProjectId");
			String monthStr = Struts2Utils.getParameter("monthStr");
			StringBuffer hql = new StringBuffer(" from BisBudget t where 1 = 1 and t.yearStatus='1'");
			hql.append(" and t.bisProjectId=:bisProjectId ");
			hql.append(" and t.monthStr=:monthStr");
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("bisProjectId", bisProjectId);
			values.put("monthStr", monthStr);
			List<BisBudget> bidBudgetList = bisBudgetManager.find(hql.toString(), values);
			if (bidBudgetList.size() > 0) {

				entity = bidBudgetList.get(0);
			}
		} catch (Exception ee) {
		}
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public String save() throws Exception {

		StringBuffer strBuffer = new StringBuffer("{status:");
		try {

			// TODO Auto-generated method stub
			if (StringUtils.isNotBlank(getId())) {
				entity = bisBudgetManager.getEntity(getId());

			}
			String monthStrs = getMonthStrs(entity.getMonthStr());
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("monthStrs", monthStrs);
			values.put("bisProjectId", entity.getBisProjectId());
			values.put("monthStr", entity.getMonthStr());

			bisBudgetManager.saveBisBudget(entity);

			StringBuffer hql = new StringBuffer("select sum(propManage),sum(carParking),sum(carManage),sum(carTemporary),");
			hql.append("sum(multiTotal),sum(multiAdvert),sum(multiService),sum(multiRepair),sum(multiClean),");
			hql.append("sum(multiOther),sum(inviteMerchant),sum(inviteAgent),sum(inviteRent),sum(agencyRent),");
			hql.append("sum(agencyFund),sum(welfare),sum(otherPersonnel),sum(administraFee),sum(travelFee),sum(businessServe),");
			hql.append("sum(adivertise),sum(inviteReward),sum(cleanHealthCost),sum(safeGuard),sum( afforeMainten),sum(engineerMainten),");
			hql.append("sum(energyCost),sum(otherPay),sum(capitalExpend),sum(capitalExpend),sum(salesExtra),sum(proxyRent),");
			hql.append("sum(setupProject),sum(prePayFee),sum(netProfit),sum(payTotal),sum(incomeTotal),sum(rentTotal), ");
			//add by zhengyi 2012-06-14 
			hql.append("sum(salaryFareBiscost),sum(salaryFareSales),sum(parkCharge),sum(otherBisCost),sum(otherMgCharge),sum(finacialCharge)");
			hql.append(",sum(bisProfit),sum(bisIncome),sum(bisExpense),sum(profitTotal),sum(incomeTax),sum(otherSaleCharge),sum(saleChargeTotal)");
			
			hql.append(" from BisBudget t where 1=1 and t.yearStatus='1' ");
			hql.append(" and t.bisProjectId=:bisProjectId  ");
			hql.append(" and t.monthStr in (" + monthStrs + ")  ");
			List bisBudgetList = bisBudgetManager.find(hql.toString(), values);

			Object[] obj;

			hql = new StringBuffer("from BisBudget t  where 1=1 ");
			hql.append(" and t.yearStatus='2' ");
			hql.append(" and t.bisProjectId=:bisProjectId  ");
			hql.append(" and t.monthStr =:monthStr ");
			List<BisBudget> yearBisBudgetList = bisBudgetManager.find(hql.toString(), values);

			BisBudget yearBisBudget = new BisBudget();
			if (yearBisBudgetList != null && yearBisBudgetList.size() > 0) {
				// 有则则修改
				yearBisBudget = yearBisBudgetList.get(0);

			} else {// 增加年度预算
				yearBisBudget.setBisProjectId(entity.getBisProjectId());
				yearBisBudget.setMonthStr(entity.getMonthStr());
				yearBisBudget.setYearStatus("2");

			}
			for (int i = 0; i < bisBudgetList.size(); i++) {
				obj = (Object[]) bisBudgetList.get(i);

				yearBisBudget.setPropManage(obj[0] == null ? null : (BigDecimal) obj[0]);
				yearBisBudget.setCarParking(obj[1] == null ? null : (BigDecimal) obj[1]);
				yearBisBudget.setCarManage(obj[2] == null ? null : (BigDecimal) obj[2]);
				yearBisBudget.setCarTemporary(obj[3] == null ? null : (BigDecimal) obj[3]);

				yearBisBudget.setMultiTotal(obj[4] == null ? null : (BigDecimal) obj[4]);
				yearBisBudget.setMultiAdvert(obj[5] == null ? null : (BigDecimal) obj[5]);
				yearBisBudget.setMultiService(obj[6] == null ? null : (BigDecimal) obj[6]);
				yearBisBudget.setMultiRepair(obj[7] == null ? null : (BigDecimal) obj[7]);
				yearBisBudget.setMultiClean(obj[8] == null ? null : (BigDecimal) obj[8]);

				yearBisBudget.setMultiOther(obj[9] == null ? null : (BigDecimal) obj[9]);
				yearBisBudget.setInviteMerchant(obj[10] == null ? null : (BigDecimal) obj[10]);
				yearBisBudget.setInviteAgent(obj[11] == null ? null : (BigDecimal) obj[11]);
				yearBisBudget.setInviteRent(obj[12] == null ? null : (BigDecimal) obj[12]);
				yearBisBudget.setAgencyRent(obj[13] == null ? null : (BigDecimal) obj[13]);

				yearBisBudget.setAgencyFund(obj[14] == null ? null : (BigDecimal) obj[14]);
				yearBisBudget.setWelfare(obj[15] == null ? null : (BigDecimal) obj[15]);
				yearBisBudget.setOtherPersonnel(obj[16] == null ? null : (BigDecimal) obj[16]);
				yearBisBudget.setAdministraFee(obj[17] == null ? null : (BigDecimal) obj[17]);
				yearBisBudget.setTravelFee(obj[18] == null ? null : (BigDecimal) obj[18]);
				yearBisBudget.setBusinessServe(obj[19] == null ? null : (BigDecimal) obj[19]);

				yearBisBudget.setAdivertise(obj[20] == null ? null : (BigDecimal) obj[20]);
				yearBisBudget.setInviteReward(obj[21] == null ? null : (BigDecimal) obj[21]);
				yearBisBudget.setCleanHealthCost(obj[22] == null ? null : (BigDecimal) obj[22]);
				yearBisBudget.setSafeGuard(obj[23] == null ? null : (BigDecimal) obj[23]);
				yearBisBudget.setAfforeMainten(obj[24] == null ? null : (BigDecimal) obj[24]);
				yearBisBudget.setEngineerMainten(obj[25] == null ? null : (BigDecimal) obj[25]);

				yearBisBudget.setEnergyCost(obj[26] == null ? null : (BigDecimal) obj[26]);
				yearBisBudget.setOtherPay(obj[27] == null ? null : (BigDecimal) obj[27]);
				yearBisBudget.setCapitalExpend(obj[29] == null ? null : (BigDecimal) obj[29]);
				yearBisBudget.setSalesExtra(obj[30] == null ? null : (BigDecimal) obj[30]);
				yearBisBudget.setProxyRent(obj[31] == null ? null : (BigDecimal) obj[31]);

				yearBisBudget.setSetupProject(obj[32] == null ? null : (BigDecimal) obj[32]);
				yearBisBudget.setPrePayFee(obj[33] == null ? null : (BigDecimal) obj[33]);
				yearBisBudget.setNetProfit(obj[34] == null ? null : (BigDecimal) obj[34]);
				yearBisBudget.setPayTotal(obj[35] == null ? null : (BigDecimal) obj[35]);
				yearBisBudget.setIncomeTotal(obj[36] == null ? null : (BigDecimal) obj[36]);
				yearBisBudget.setRentTotal(obj[37] == null ? null : (BigDecimal) obj[37]);
				
				yearBisBudget.setSalaryFareBiscost(obj[38] == null ? null : (BigDecimal) obj[38]);
				yearBisBudget.setSalaryFareSales(obj[39] == null ? null : (BigDecimal) obj[39]);
				yearBisBudget.setParkCharge(obj[40] == null ? null : (BigDecimal) obj[40]);
				yearBisBudget.setOtherBisCost(obj[41] == null ? null : (BigDecimal) obj[41]);
				yearBisBudget.setOtherMgCharge(obj[42] == null ? null : (BigDecimal) obj[42]);
				yearBisBudget.setFinacialCharge(obj[43] == null ? null : (BigDecimal) obj[43]);
				yearBisBudget.setBisProfit(obj[44] == null ? null : (BigDecimal) obj[44]);
				yearBisBudget.setBisIncome(obj[45] == null ? null : (BigDecimal) obj[45]);
				yearBisBudget.setBisExpense(obj[46] == null ? null : (BigDecimal) obj[46]);
				yearBisBudget.setProfitTotal(obj[47] == null ? null : (BigDecimal) obj[47]);
				yearBisBudget.setIncomeTax(obj[48] == null ? null : (BigDecimal) obj[48]);
				yearBisBudget.setOtherSaleCharge(obj[49] == null ? null : (BigDecimal) obj[49]);
				yearBisBudget.setSaleChargeTotal(obj[50] == null ? null : (BigDecimal) obj[50]);
				
				bisBudgetManager.saveBisBudget(yearBisBudget);
				
				
				upDateYearBudget(entity.getBisProjectId(),entity.getMonthStr());
			}

			strBuffer.append("true}");

		} catch (Exception ee) {

			ee.printStackTrace();
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage() != null ? ee.getMessage().replace("\"", "”") : ""));

		}

		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	/**
	 * 獲取当前年中所有月份
	 * 
	 * @author qlb 4/6/2012
	 * @param year
	 *            年
	 * @return 一年中所有月份 如:'2012-01','2012-02'.....
	 */
	private String getMonthStrs(String monthStr) {
		String year = "";
		String month = "0";
		if (monthStr.split("-").length > 1) {
			year = monthStr.split("-")[0];
			month = monthStr.split("-")[1];
		}
		StringBuffer allMonthsOfYear = new StringBuffer("");
		for (int i = 1; i <= Integer.parseInt(month); i++) {
			if (i > 1) {
				allMonthsOfYear.append(",");
			}
			allMonthsOfYear.append("'" + String.format("%s-%s", year, i > 9 ? "" + i : "0" + i) + "'");
		}
		return allMonthsOfYear.toString();
	}

	/**
	 * 判断指定日期的月度预算是否已存在
	 * 
	 * @author qilb 4/9/2012
	 * @param bisProjectId
	 *            项目ID
	 * @param monthStr
	 *            指定日期
	 * @return true:已存在，false:未存在
	 */
	public void isExistBisBudget() {

		String bisProjectId = Struts2Utils.getParameter("bisProjectId");
		String monthStr = Struts2Utils.getParameter("monthStr");
		StringBuffer hql = new StringBuffer(" from BisBudget t where 1 = 1 and t.yearStatus='1'");
		hql.append(" and t.bisProjectId=:bisProjectId ");
		hql.append(" and t.monthStr=:monthStr");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("bisProjectId", bisProjectId);
		values.put("monthStr", monthStr);
		List<BisBudget> bidBudgetList = bisBudgetManager.find(hql.toString(), values);
		if (bidBudgetList.size() > 0) {

			Struts2Utils.renderText("true");

		} else {

			Struts2Utils.renderText("false");
		}

	}

	/**
	 * @return the entity
	 */
	public BisBudget getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(BisBudget entity) {
		this.entity = entity;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			if (StringUtils.isNotBlank(getId())) {
				entity = bisBudgetManager.getEntity(getId());
				Map<String,Object>	values = new HashMap<String, Object>();
				values.put("bisProjectId",entity.getBisProjectId());
				values.put("monthStr", entity.getMonthStr());
				
				StringBuffer hql = new StringBuffer("from BisBudget t  where 1=1 ");
				hql.append(" and t.yearStatus='2' ");
				hql.append(" and t.bisProjectId=:bisProjectId  ");
				hql.append(" and t.monthStr =:monthStr ");
				List<BisBudget> yearBisBudgetList = bisBudgetManager.find(hql.toString(), values);
				bisBudgetManager.delete(entity);
				
				if (yearBisBudgetList != null && yearBisBudgetList.size() > 0) {
			        //删除年度预算
					bisBudgetManager.delete(yearBisBudgetList);
				} 
				
				
				upDateYearBudget(entity.getBisProjectId(),entity.getMonthStr());
				
			
			} else {
				entity = new BisBudget();
			}

			strBuffer.append("true}");

		} catch (Exception ee) {

			ee.printStackTrace();
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage() != null ? ee.getMessage().replace("\"", "”") : ""));

		}
		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	
	public void updateOtherYearBudget(String monthStr,Map<String,Object>values){
		try {

			StringBuffer hql=new StringBuffer("from BisBudget t  where 1=1 ");
			hql.append(" and t.yearStatus='2' ");
			hql.append(" and t.bisProjectId=:bisProjectId  ");
			hql.append(" and t.monthStr =:monthStr ");
	    	List<BisBudget> yearBisBudgetList=bisBudgetManager.find(hql.toString(), values);	
	    	
	    	BisBudget yearBisBudget = new BisBudget();
			if(yearBisBudgetList!=null && yearBisBudgetList.size()>0){
				//有则则修改
				yearBisBudget=yearBisBudgetList.get(0);
				
			} else
				//当前月度预算还没有创建则不进行更新
				return;
			//获取从1月份到当前月份至中所有月份，如果2012-4 ，则返回，('2012-1'，'2012-2'，'2012-3'，'2012-4')
			String monthStrs = getMonthStrs(monthStr);
			
			
			
		 hql = new StringBuffer("select sum(propManage),sum(carParking),sum(carManage),sum(carTemporary),");
			hql.append("sum(multiTotal),sum(multiAdvert),sum(multiService),sum(multiRepair),sum(multiClean),");
			hql.append("sum(multiOther),sum(inviteMerchant),sum(inviteAgent),sum(inviteRent),sum(agencyRent),");
			hql.append("sum(agencyFund),sum(welfare),sum(otherPersonnel),sum(administraFee),sum(travelFee),sum(businessServe),");
			hql.append("sum(adivertise),sum(inviteReward),sum(cleanHealthCost),sum(safeGuard),sum( afforeMainten),sum(engineerMainten),");
			hql.append("sum(energyCost),sum(otherPay),sum(capitalExpend),sum(capitalExpend),sum(salesExtra),sum(proxyRent),");
			hql.append("sum(setupProject),sum(prePayFee),sum(netProfit),sum(payTotal),sum(incomeTotal),sum(rentTotal) ");
			hql.append(" from BisBudget t where 1=1 and t.yearStatus='1' ");
			hql.append(" and t.bisProjectId=:bisProjectId  ");
			hql.append(" and t.monthStr in (" + monthStrs + ")  ");
		
			List bisBudgetList = bisBudgetManager.find(hql.toString(), values);
			
			Object[] obj;
			
			
			
			for (int i = 0; i < bisBudgetList.size(); i++) {
				obj = (Object[]) bisBudgetList.get(i);
				
				yearBisBudget.setPropManage(obj[0] == null ? null : (BigDecimal) obj[0]);
				yearBisBudget.setCarParking(obj[1] == null ? null : (BigDecimal) obj[1]);
				yearBisBudget.setCarManage(obj[2] == null ? null : (BigDecimal) obj[2]);
				yearBisBudget.setCarTemporary(obj[3] == null ? null : (BigDecimal) obj[3]);
				

				
				yearBisBudget.setMultiTotal(obj[4] == null ? null : (BigDecimal) obj[4]);
				yearBisBudget.setMultiAdvert(obj[5] == null ? null : (BigDecimal) obj[5]);
				yearBisBudget.setMultiService(obj[6] == null ? null : (BigDecimal) obj[6]);
				yearBisBudget.setMultiRepair(obj[7] == null ? null : (BigDecimal) obj[7]);
				yearBisBudget.setMultiClean(obj[8] == null ? null : (BigDecimal) obj[8]);
				
				
				yearBisBudget.setMultiOther(obj[9] == null ? null : (BigDecimal) obj[9]);
				yearBisBudget.setInviteMerchant(obj[10] == null ? null : (BigDecimal) obj[10]);
				yearBisBudget.setInviteAgent(obj[11] == null ? null : (BigDecimal) obj[11]);
				yearBisBudget.setInviteRent(obj[12] == null ? null : (BigDecimal) obj[12]);
				yearBisBudget.setAgencyRent(obj[13] == null ? null : (BigDecimal) obj[13]);
				
				
				yearBisBudget.setAgencyFund(obj[14] == null ? null : (BigDecimal) obj[14]);
				yearBisBudget.setWelfare(obj[15] == null ? null : (BigDecimal) obj[15]);			
				yearBisBudget.setOtherPersonnel(obj[16] == null ? null : (BigDecimal) obj[16]);
				yearBisBudget.setAdministraFee(obj[17] == null ? null : (BigDecimal) obj[17]);
				yearBisBudget.setTravelFee(obj[18] == null ? null : (BigDecimal) obj[18]);
				yearBisBudget.setBusinessServe(obj[19] == null ? null : (BigDecimal) obj[19]);
				
				
				yearBisBudget.setAdivertise(obj[20] == null ? null : (BigDecimal) obj[20]);
				yearBisBudget.setInviteReward(obj[21] == null ? null : (BigDecimal) obj[21]);
				yearBisBudget.setCleanHealthCost(obj[22] == null ? null : (BigDecimal) obj[22]);
				yearBisBudget.setSafeGuard(obj[23] == null ? null : (BigDecimal) obj[23]);
				yearBisBudget.setAfforeMainten(obj[24] == null ? null : (BigDecimal) obj[24]);
				yearBisBudget.setEngineerMainten(obj[25] == null ? null : (BigDecimal) obj[25]);
				
				
				
				yearBisBudget.setEnergyCost(obj[26] == null ? null : (BigDecimal) obj[26]);
				yearBisBudget.setOtherPay(obj[27] == null ? null : (BigDecimal) obj[27]);
				yearBisBudget.setCapitalExpend(obj[29] == null ? null : (BigDecimal) obj[29]);
				yearBisBudget.setSalesExtra(obj[30] == null ? null : (BigDecimal) obj[30]);
				yearBisBudget.setProxyRent(obj[31] == null ? null : (BigDecimal) obj[31]);			
				
				
				yearBisBudget.setSetupProject(obj[32] == null ? null : (BigDecimal) obj[32]);
				yearBisBudget.setPrePayFee(obj[33]==null?null:(BigDecimal)obj[33]);
				yearBisBudget.setNetProfit(obj[34] == null ? null : (BigDecimal) obj[34]);
				yearBisBudget.setPayTotal(obj[35] == null ? null : (BigDecimal) obj[35]);
				yearBisBudget.setIncomeTotal(obj[36] == null ? null : (BigDecimal) obj[36]);
                yearBisBudget.setRentTotal(obj[37] == null ? null : (BigDecimal) obj[37]);
				bisBudgetManager.saveBisBudget(yearBisBudget);
			}

	

		} catch (Exception ee) {

			ee.printStackTrace();
		
		}

		
	}
	
	
	/**
	 * 獲取当前年中所有月份
	 * 
	 * @author qlb 4/6/2012
	 * @param year
	 *            年
	 * @return 一年中所有月份 如:'2012-01','2012-02'.....
	 */
	private String upDateYearBudget(String bisProjectId,String monthStr) {
		String year = "";
		String month = "0";
		if (monthStr.split("-").length > 1) {
			year = monthStr.split("-")[0];
			month = monthStr.split("-")[1];
		}
		
		Map<String, Object> values = new HashMap<String, Object>();	
		
		StringBuffer allMonthsOfYear = new StringBuffer("");
		String otherMonthStr="";
		//如果用户更新了5月份的预算，则需要自动更新6，7，8....12月份的年度预，如果存在的话
		//month:5，，month+1则从6月份更新，
		for (int i = (Integer.parseInt(month)+1); i <=12;  i++) {
			
			otherMonthStr= String.format("%s-%s", year, i > 9 ? "" + i : "0" + i);
			values = new HashMap<String, Object>();
			
			values.put("bisProjectId",bisProjectId);
			values.put("monthStr", otherMonthStr);
			updateOtherYearBudget(otherMonthStr,values);
		}
		return allMonthsOfYear.toString();
	}
	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisBudgetManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new BisBudget();
		}

	}

	/**
	 * @return the bisBudgetManager
	 */
	public BisBudgetManager getBisBudgetManager() {
		return bisBudgetManager;
	}

	/**
	 * @param bisBudgetManager
	 *            the bisBudgetManager to set
	 */
	public void setBisBudgetManager(BisBudgetManager bisBudgetManager) {
		this.bisBudgetManager = bisBudgetManager;
	}

	/**
	 * @return the voPage
	 */
	public Page<BisBudget> getVoPage() {
		return voPage;
	}

	/**
	 * @param voPage
	 *            the voPage to set
	 */
	public void setVoPage(Page<BisBudget> voPage) {
		this.voPage = voPage;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	
	
}
