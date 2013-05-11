package com.hhz.ump.web.cost;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cost.CostBudgetMonthDetailManager;
import com.hhz.ump.dao.cost.CostBudgetMonthManager;
import com.hhz.ump.dao.cost.CostProjectSectionManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cost.CostBudgetMonth;
import com.hhz.ump.entity.cost.CostBudgetMonthDetail;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.cost.vo.CostBudgetMonthDetailVo;
import com.hhz.ump.web.vo.CostBudgetMonthVo;

@Namespace("/cost")
@Results( 
	{ 
		@Result(name = "exportMonthStat", type = "stream",params = { "contentType", "application/vnd.ms-excel", "inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" }),
		@Result(name = "exportSectionMonthList", type = "stream",params = { "contentType", "application/vnd.ms-excel", "inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
	}
)
public class CostBudgetMonthAction extends CrudActionSupport<CostBudgetMonth> {

	private static final long serialVersionUID = 8288550627257184960L;
	
	private static Log log = LogFactory.getLog(CostBudgetMonthAction.class);
	 
	@Autowired
	private CostBudgetMonthManager costBudgetMonthManager;
	@Autowired
	private CostBudgetMonthDetailManager costBudgetMonthDetailManager;
	@Autowired
	private CostProjectSectionManager costProjectSectionManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private ContLedgerManager contLedgerManager;
	
	

	private CostBudgetMonth entity;
	
	//搜索条件
	private String pageNo;//页码
	private String sectionIds;
	private String yearMonth;
	private String subjectCd;
	private String contactNo;
	private String partb;
	private String contactName;
	private String strageFlg;
	

	/**
	 * 项目列表
	 */
	private List<CostProjectSection> dropProjectList;
	/**
	 * 月度预算明细(不分页)
	 */
	private Page<CostBudgetMonthDetail> monthDetailPage;
	/**
	 * 汇总行
	 */
	private CostBudgetMonthDetail totalRow;
	
	/**
	 * 月度资金预算明细对象(更新字段值)
	 */
	private CostBudgetMonthDetail monthDetail;
	/**
	 * 最近新增的月度明细ID
	 */
	private String latestDetailId;
	/**
	 * 是否显示无预算
	 */
	private String nocontactVisiable;
	//月度汇总
	private String planYearMonth;
	private String planSectionId;

	private List<CostBudgetMonthVo> monthStatVoList; 

	
	//导出“月度资金预算汇总”
	private InputStream is;
	private String downFileName;
	

	@Override
	public CostBudgetMonth getModel() {
		if(StringUtils.isNotBlank(getId())){
			entity = costBudgetMonthManager.getEntity(getId());
		}
		return entity;
	}
	/**
	 * 获取项目月度汇总表单(不分页)
	 * @param planYearMonth：年月 ( yyyy-MM)
	 * @return
	 */
	public String monthStat() {
		if(StringUtils.isNotBlank(getPlanYearMonth())){
			monthStatVoList = costBudgetMonthManager.findMonthTotalByYm(getPlanYearMonth());
		}
		return "total";
	}
	
	/**
	 * 项目月度汇总
	 * @author liuzhihui 2012-03-06
	 * @param planYearMonth：年月 ( yyyy-MM)
	 * @return 
	 */
	public String monthTotal(){
		//delete
		return "totalList";
	}
	
	
	/**
	 * 搜索月度资金计划列表
	 * @param yearMonth
	 * @param subjectCd 科目名称(1,2,...,nocontact)
	 * @param contactNo 合同编号
	 * @param contactName 合同名称
	 * @param partb  施工单位
	 * 
	 * @return
	 */
	public String loadMonthList() { 
		
		//获取有权限的所有项目
		List<String> sectionIdList = costProjectSectionManager.getSectionIdList();


		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sectionIdList", sectionIdList);

		// 搜索语句条件初始化
		StringBuffer hql = new StringBuffer()
				.append("   from CostBudgetMonthDetail c ")
				.append("  where c.costBudgetMonth.costProjectSection.costProjectSectionId in (:sectionIdList) ");
		 
		if (StringUtils.isNotBlank(yearMonth)){
			
			hql.append(" and c.costBudgetMonth.budegetYear = :budegetYear ");
			hql.append(" and c.costBudgetMonth.budegetMonth = :budegetMonth ");

			String[] arr = yearMonth.split("-");
			values.put("budegetYear", Long.valueOf(arr[0]));
			values.put("budegetMonth",  Long.valueOf(arr[1]));
		}

		if (StringUtils.isNotBlank(getSectionIds())) {
			hql.append(" and c.costBudgetMonth.costProjectSection.costProjectSectionId in (:selectedIdList) ");
			values.put("selectedIdList", Arrays.asList(getSectionIds().split(",")));
		} 
		if (StringUtils.isNotBlank(subjectCd)) {
			hql.append(" and c.subjectCd = :subjectCd ");
			values.put("subjectCd", subjectCd);
		}
		if (StringUtils.isNotBlank(contactNo)) {
			hql.append(" and c.contactNo like :contactNo ");
			values.put("contactNo", "%" + contactNo + "%");
		}
		if (StringUtils.isNotBlank(contactName)) {
			hql.append(" and c.contactName like :contactName ");
			values.put("contactName", "%" + contactName + "%");
		}
		if (StringUtils.isNotBlank(partb)) {
			hql.append(" and c.partb like :partb ");
			values.put("partb", "%" + partb + "%");
		}
		if (StringUtils.isNotBlank(strageFlg)) {
			hql.append(" and c.strageFlg  = :strageFlg ");
			values.put("strageFlg",  strageFlg );
		}
		//排序
		hql.append(" order by c.costBudgetMonth.budegetYear desc, c.costBudgetMonth.budegetMonth desc, c.costBudgetMonth.costProjectSection.sectionName asc ,c.contactNo desc");
		//搜索导出
		String searchExport = Struts2Utils.getParameter("searchExport");
		//如果是搜索导出,搜索出全部
		if(StringUtils.isNotBlank(searchExport)){
			monthDetailPage = new Page<CostBudgetMonthDetail>();
			monthDetailPage.setResult(costBudgetMonthDetailManager.find( hql.toString(), values));
		}
		//分页搜索
		else{
			monthDetailPage = new Page<CostBudgetMonthDetail>(10);
			if(StringUtils.isNotBlank(getPageNo())) {
				monthDetailPage.setPageNo(Integer.valueOf(getPageNo()).intValue());
			}
			// 搜索所有记录
			monthDetailPage = costBudgetMonthDetailManager.findPage(monthDetailPage, hql.toString(), values);
		}
		
		
		return "loadMonthList";
	}
	
	  
	/**
	 * 月度计划明细统计值汇总
	 * @param detailList
	 */
	private void totalCostBudgetMonth(List<CostBudgetMonthDetail> detailList) {
		// 汇总行数据
		totalRow = new CostBudgetMonthDetail();
		for (CostBudgetMonthDetail currentRow : detailList) {
			//设置月度对象
			totalRow.setCostBudgetMonth(currentRow.getCostBudgetMonth());
			
			// 合同总价contactTotalAmt
			totalRow.setContactTotalAmt(processAdd(totalRow.getContactTotalAmt(), currentRow.getContactTotalAmt()));
			
			// 实际合同总价contactRealTotalAmt
			totalRow.setContactRealTotalAmt(processAdd(totalRow.getContactRealTotalAmt(), currentRow.getContactRealTotalAmt()));
			
			// 结算价settleAmt
			totalRow.setSettleAmt(processAdd(totalRow.getSettleAmt(), currentRow.getSettleAmt()));
			
			// 已完产值合计（元）finishProdTotalAmt
			totalRow.setFinishProdTotalAmt(processAdd(totalRow.getFinishProdTotalAmt(), currentRow.getFinishProdTotalAmt()));
			
			// 其中甲供料产值nailFeedWorthAmt
			totalRow.setNailFeedWorthAmt(processAdd(totalRow.getNailFeedWorthAmt(), currentRow.getNailFeedWorthAmt()));
			
			// 累计应付款合计(元)cumuMustPayTotalAmt
			totalRow.setCumuMustPayTotalAmt(processAdd(totalRow.getCumuMustPayTotalAmt(), currentRow.getCumuMustPayTotalAmt()));
			
			// 累计实际支付cumuRealPayTotalAmt
			totalRow.setCumuRealPayTotalAmt(processAdd(totalRow.getCumuRealPayTotalAmt(), currentRow.getCumuRealPayTotalAmt()));
			
			// 累计支付比率%cumuPaiedRate
			totalRow.setCumuPaiedRate(processAdd(totalRow.getCumuPaiedRate(), currentRow.getCumuPaiedRate()));
			
			// 本期拟确认产值curPeriodPlanConfmAmt
			totalRow.setCurPeriodPlanConfmAmt(processAdd(totalRow.getCurPeriodPlanConfmAmt(), currentRow.getCurPeriodPlanConfmAmt()));
			
			// 本期产值内甲供料curPeriodNailFeedAmt
			totalRow.setCurPeriodNailFeedAmt(processAdd(totalRow.getCurPeriodNailFeedAmt(), currentRow.getCurPeriodNailFeedAmt()));
			
			// 本期：资金应付预算curPeriodFundMpayAmt
			totalRow.setCurPeriodFundMpayAmt(processAdd(totalRow.getCurPeriodFundMpayAmt(), currentRow.getCurPeriodFundMpayAmt()));
			
			// 累计：应付未付cumuMustNoPayAmt
			totalRow.setCumuMustNoPayAmt(processAdd(totalRow.getCumuMustNoPayAmt(), currentRow.getCumuMustNoPayAmt()));
			
			// 本期资金预算curPeriodFundBudgetAmt
			totalRow.setCurPeriodFundBudgetAmt(processAdd(totalRow.getCurPeriodFundBudgetAmt(), currentRow.getCurPeriodFundBudgetAmt()));
			
			// 调整本期资金预算（地产上报）curPeriodFundBudgetAmt1
			totalRow.setCurPeriodFundBudgetAmt1(processAdd(totalRow.getCurPeriodFundBudgetAmt1(), currentRow.getCurPeriodFundBudgetAmt1()));
			
			// 本期资金预算（区域审核）curPeriodFundBudgetAmt2
			totalRow.setCurPeriodFundBudgetAmt2(processAdd(totalRow.getCurPeriodFundBudgetAmt2(), currentRow.getCurPeriodFundBudgetAmt2()));
			
			// 本期资金预算（元）（批准）curPeriodFundBudgetAmt3
			totalRow.setCurPeriodFundBudgetAmt3(processAdd(totalRow.getCurPeriodFundBudgetAmt3(), currentRow.getCurPeriodFundBudgetAmt3()));
		}
	}
	
	/**
	 * 求和
	 * @param b1总值
	 * @param b2加码
	 * @return
	 */
	private BigDecimal processAdd(BigDecimal b1, BigDecimal b2){

		if(b1 == null){
			b1 = BigDecimal.ZERO;
		} 
		if(b2 == null){
			b2 = BigDecimal.ZERO;
		}
		
		return b1.add(b2);
	}



	/**
	 * 月度资金计划首页
	 * @param id
	 */
	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	};
	
	/**
	 * 新增月度首页
	 */
	@Override
	public String input() throws Exception {
		if(entity != null){
			planSectionId = entity.getCostProjectSection().getCostProjectSectionId();
			planYearMonth=entity.getBudegetYear().toString()+"-"+StringUtils.leftPad(entity.getBudegetMonth().toString(), 2, "0");
		}
		return "index";
	}

	@Override
	public String save() throws Exception {
		return null;
	}
	
	
	 
	/**
	 * 创建月度资金计划
	 * @param planSectionId 项目
	 * @param planYearMonth 年月
	 * @return
	 */
	public String createMonthBudget(){

		String tSectionId = Struts2Utils.getParameter("planSectionId");
		String tYearMonth = Struts2Utils.getParameter("planYearMonth");
		
		String [] arr = tYearMonth.split("-");
		
		Long tYear = Long.valueOf(arr[0]);
		Long tMonth = Long.valueOf(arr[1]);
		
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("tSectionId", tSectionId);
		values.put("budegetYear", tYear);
		values.put("budegetMonth", tMonth);
		
		//entity
		getMonth(values);
		
		if (entity == null) {
			
			CostProjectSection t = costProjectSectionManager.getEntity(tSectionId);
			entity = new CostBudgetMonth();
			entity.setCostProjectSection(t);
			entity.setBudegetYear(tYear);
			entity.setBudegetMonth(tMonth);
			entity.setStatusCd(CostBudgetMonthManager.MONTH_BUDGET_PROJECT);//设置 1-项目
			

			//获取代码表
			Map<String,String> codeTypeCd2Map = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
			codeTypeCd2Map.put("nocontact", "无合同预算");
			
			
			costBudgetMonthManager.createBudgetMonth(entity, codeTypeCd2Map);
			
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("已存在!");
		}
		return null;
	}

	
	/**
	 * 新增一行无合同情况的记录
	 * @param costMonthId 月度计划ID
	 * @param sequenceNo 序号
	 */ 
	public String saveNoContact(){

		String tCostMonthId = Struts2Utils.getParameter("costMonthId");
		String tSequenceNo  = Struts2Utils.getParameter("sequenceNo");
		
		entity = costBudgetMonthManager.getEntity(tCostMonthId);
		if(entity == null){
			Struts2Utils.renderText("参数不对!");
			return null;
		}
		
		//新建一条月度资金预算对象
		monthDetail = new CostBudgetMonthDetail();
		monthDetail.setCostBudgetMonth(entity);
		monthDetail.setNoContactFlg("0");//1-是，0-否2012.03.13 add -导入时，默认0；新增，默认1
		monthDetail.setStrageFlg("0");//默认不是战略
		monthDetail.setCurPeriodFundBudgetAmt(BigDecimal.ZERO);//本期资金预算(理论计算应付)初始化为0
		monthDetail.setCurPeriodFundBudgetAmt1(BigDecimal.ZERO);//调整本期资金预算（地产上报）初始化为0
		monthDetail.setCurPeriodFundBudgetAmt2(BigDecimal.ZERO);//本期资金预算（区域审核）初始化为0
		monthDetail.setCurPeriodFundBudgetAmt3(BigDecimal.ZERO);//本期资金预算（元）（批准）初始化为0
		monthDetail.setContactId("-");//无合同舒适化为"-"
		//如果没有带序号，则取当先最大序号
		if(StringUtils.isBlank(tSequenceNo)){
			monthDetail.setSequenceNo(costBudgetMonthDetailManager.getcurrentSequenceNo());
			costBudgetMonthDetailManager.saveCostBudgetMonthDetail(monthDetail);
		}else{
			monthDetail.setSequenceNo(Long.valueOf(tSequenceNo )+1);
			//更新序号并保存最新的无合同明细
			costBudgetMonthDetailManager.saveNoct(monthDetail,Long.valueOf(tSequenceNo ),tCostMonthId);
		}
		Struts2Utils.renderText("success,"+monthDetail.getCostBudgetMonthDetailId());
		return null;
	}
	
	
	
	
	@Override
	public String delete() throws Exception {
		
		return null;
	}

	

	/**
	 * 删除月度资金预算明细
	 * @param costMonthDetailId
	 */
	public String deleteMonthDetail(){
		String costMonthDetailId = Struts2Utils.getParameter("costMonthDetailId");
		if(StringUtils.isNotBlank(costMonthDetailId)){
			costBudgetMonthDetailManager.deleteDetail(costMonthDetailId);
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("参数不对!");
		}
		return null;
	}
	
	/**
	 * 提交月度资金预算状态
	 * @return
	 */
	public String commitCost(){
		String costProjectSectionId=Struts2Utils.getParameter("planSectionId");
		if(StringUtils.isNotBlank(costProjectSectionId)&&StringUtils.isNotBlank(planYearMonth)){
			//搜索
			entity=costBudgetMonthManager.exsitBudgetMonth(costProjectSectionId, planYearMonth);
		}
		//结果字符串
		String resultText="";
		//如果不为空
		if(entity!=null){
			//如果为项目成本状态,设置到区域成本状态
			if(CostBudgetMonthManager.MONTH_BUDGET_PROJECT.equals(entity.getStatusCd())){
				entity.setStatusCd(CostBudgetMonthManager.MONTH_BUDGET_AREA_COST);
				resultText="success,";
			}else{
				//如果为区域成本状态,设置到区域财务状态
				if(CostBudgetMonthManager.MONTH_BUDGET_AREA_COST.equals(entity.getStatusCd())){
					entity.setStatusCd(CostBudgetMonthManager.MONTH_BUDGET_AREA_FIN);
					resultText="success,";
				}else{
					////如果为 区域财务状态,设置到归档
					if(CostBudgetMonthManager.MONTH_BUDGET_AREA_FIN.equals(entity.getStatusCd())){
						entity.setStatusCd(CostBudgetMonthManager.MONTH_BUDGET_ARCHIVE);
						resultText="success,";
					}else{
						//归档
					}
					
				}
			
			} 
			//保存更新
			costBudgetMonthManager.saveCostBudgetMonth(entity);
			resultText+=","+entity.getCostProjectSection().getCostProjectSectionId()+","+entity.getBudegetYear().toString()+StringUtils.leftPad(entity.getBudegetMonth().toString(), 2,"0");
			Struts2Utils.renderText(resultText);
		}
		return null;
	}
	
	
	
	public String rejectBudget(){
		String costProjectSectionId=Struts2Utils.getParameter("planSectionId");
		if(StringUtils.isNotBlank(costProjectSectionId)&&StringUtils.isNotBlank(planYearMonth)){
			//搜索
			entity=costBudgetMonthManager.exsitBudgetMonth(costProjectSectionId, planYearMonth);
		}
		//如果不为空
		if(entity!=null){
			entity.setStatusCd(Struts2Utils.getParameter("nowStatus"));
			costBudgetMonthManager.saveCostBudgetMonth(entity);
			Struts2Utils.renderText("success");
		}
		return null;
	}
	/**
	 * 获取月度明细
	 * @param costMonthId
	 * @param contactNo
	 * @return
	 */
	private CostBudgetMonthDetail getMonthDetail(String costMonthId, String contactNo){
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("costBudgetMonthId", costMonthId);
		values.put("contactNo", contactNo);
		String hql = " from CostBudgetMonthDetail t where t.costBudgetMonth.costBudgetMonthId = :costBudgetMonthId and t.contactNo = :contactNo ";
		List<CostBudgetMonthDetail> details = costBudgetMonthDetailManager.find(hql, values);
		if(details == null || details.size() == 0)
			return null;
		else
			return details.get(0);
	}
	
	/**
	 * 保存月度资金预算合同部分信息
	 */
	public String saveContBudget() throws Exception {
		//合同号
		String tContactNo = Struts2Utils.getParameter("contNo");
		//月度ID
		String tCostMonthId = Struts2Utils.getParameter("costBudgetMonthId");
		if(StringUtils.isBlank(tContactNo) ||  StringUtils.isBlank(tCostMonthId)){
			Struts2Utils.renderText("参数不对!");
			return null;
		}
		
		entity = costBudgetMonthManager.getEntity(tCostMonthId);
		
		if(entity == null){
			Struts2Utils.renderText("对不起，找不到月度计划!");
			return null;
		}else{
			if(StringUtils.isNotBlank(tContactNo)){
				//合同是否导入过
				CostBudgetMonthDetail tmpDetail = getMonthDetail(tCostMonthId, tContactNo);
				if(tmpDetail != null){
					//如果已经存在,则不需要进行操作
					Struts2Utils.renderText("已追加！");
				}else{
					ContLedger cl = contLedgerManager.findContLedgerByContNo(tContactNo);
					if(cl!=null){

						//获取代码表
						Map<String,String> codeTypeCd2Map = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
						codeTypeCd2Map.put("nocontact", "无合同预算");
						
						//创建月度资金计划明细
						tmpDetail = costBudgetMonthManager.createMonthDetail(entity, cl, codeTypeCd2Map);
						
						//提交保存
						costBudgetMonthDetailManager.saveCostBudgetMonthDetail(tmpDetail);
						
						Struts2Utils.renderText("success,"+tmpDetail.getCostBudgetMonthDetailId());
					}
				}
				
			}
		}
		
		return null;
	}
	
	
	
	
	
	/**
	 * 修改月计划之前之前准备工作
	 */
	public void prepareModifyBudget(){
		getModel();
	}
	
	
	/**
	 * 保存月度资金预算的抬头，同时新增一条明细
	 * @return
	 */
	public String saveMonthAndDetail(){
//		//保存月度抬头
////		preNewMonthBudget();
//		//保存明细
//		preSaveNoct();
////		Struts2Utils.renderText("success,"+entity.getSequenceNo());
		return null;
	}
	
	
	/**
	 * 根据搜索更新相应的月度资金预算(字段)
	 * @param costMonthId 月度计划明细ID
	 * @param fieldName 更新字段名称
	 * @return
	 */
	public void prepareSaveMonthDetailPropValue(){

		String tMonthDetailId = Struts2Utils.getParameter("costMonthId");
		monthDetail = costBudgetMonthDetailManager.getEntity(tMonthDetailId);
	}
	
	/**
	 * 修改相应字段的值
	 * @return
	 */
	public String saveMonthDetailPropValue(){
		
		String tMonthDetailId = Struts2Utils.getParameter("costMonthId");
		if(StringUtils.isBlank(tMonthDetailId) || monthDetail == null){
			Struts2Utils.renderText("参数不对!");
			return null;
		}
		String tFieldName = Struts2Utils.getParameter("fieldName");
			//如果修改"累计应付款合计(元)"或者"累计实际支付"
			if("cumuMustPayTotalAmt".equals(tFieldName)||"cumuRealPayTotalAmt".equals(tFieldName)){
				//通过"累计应付款合计(元)","累计实际支付"计算"出累计：应付未付"
				if(monthDetail.getCumuMustPayTotalAmt()!=null){
					if(monthDetail.getCumuRealPayTotalAmt()!=null){
						monthDetail.setCumuMustNoPayAmt(monthDetail.getCumuMustPayTotalAmt().subtract(monthDetail.getCumuRealPayTotalAmt()));
					}else{
						monthDetail.setCumuMustNoPayAmt(monthDetail.getCumuMustPayTotalAmt());
					}
				}else{
					if(monthDetail.getCumuRealPayTotalAmt()!=null){
						monthDetail.setCumuMustNoPayAmt(BigDecimal.ZERO.subtract(monthDetail.getCumuRealPayTotalAmt()));	
					}else{
						monthDetail.setCumuMustNoPayAmt(BigDecimal.ZERO);	
					}
				}
			}
			//如果修改"累计：应付未付"或者"本期：资金应付预算"
			if("curPeriodFundMpayAmt".equals(tFieldName)||"cumuMustNoPayAmt".equals(tFieldName)){
				//通过"累计：应付未付","本期：资金应付预算"计算出"本期资金预算"
				BigDecimal curPeriodFundMpayAmt = BigDecimal.ZERO;
				BigDecimal cumuMustNoPayAmt = BigDecimal.ZERO;
				if (monthDetail.getCurPeriodFundMpayAmt() != null) {
					curPeriodFundMpayAmt = monthDetail.getCurPeriodFundMpayAmt();
				}
				if (monthDetail.getCumuMustNoPayAmt() != null) {
					cumuMustNoPayAmt = monthDetail.getCumuMustNoPayAmt();
				}
				// 设置"本期资金预算（地产上报）"
				monthDetail.setCurPeriodFundBudgetAmt(cumuMustNoPayAmt.add(curPeriodFundMpayAmt));
			}
		
		//如果是修改的成本科目subjectCd，则需要修改相应的subjectName
		if("subjectCd".equals(tFieldName)) {
			monthDetail.setSubjectName(getMapTypeByEstate().get(monthDetail.getSubjectCd()));
		}		
		//计算“本期资金预算(理论计算应付)”		
		if(monthDetail.getCurPeriodFundMpayAmt()!=null){
			if(monthDetail.getCumuMustNoPayAmt()!=null){
				monthDetail.setCurPeriodFundBudgetAmt(monthDetail.getCurPeriodFundMpayAmt().add(monthDetail.getCumuMustNoPayAmt()));
			}else{
				monthDetail.setCurPeriodFundBudgetAmt(monthDetail.getCurPeriodFundMpayAmt());
			}
		}else{
			if(monthDetail.getCumuMustNoPayAmt()!=null){
				monthDetail.setCurPeriodFundBudgetAmt(monthDetail.getCumuMustNoPayAmt());
			}
		}
		costBudgetMonthDetailManager.saveCostBudgetMonthDetail(monthDetail);
		// success + (累计：应付未付) + (本期资金预算（地产上报）)
		String result = "success,"+ monthDetail.getCumuMustNoPayAmt() + "," + monthDetail.getCurPeriodFundBudgetAmt();
		Struts2Utils.renderText(result);
		return null;
	}
	
	
	/**
	 * 地产公司合同类别
	 * @return
	 */
	public Map<String,String> getMapTypeByEstate(){
		Map<String,String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
		return map;
	}
	/**
	 * 战略列表
	 * @return
	 */
	public Map<String,String> getMapStrageFlg(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("", " ");
		map.put("1", "是");
		map.put("0", "否");
		return map;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity = costBudgetMonthManager.getEntity(getId());
		}else{
			if(StringUtils.isNotBlank(planYearMonth)&&StringUtils.isNotBlank(planSectionId)){
				entity = costBudgetMonthManager.exsitBudgetMonth(planSectionId, planYearMonth);
			}
		}
	}
	 

	/**
	 * 获取下拉框
	 * @return
	 */
	public List<CostProjectSection> getDropProjectList() {
		// 初始化
		dropProjectList = new ArrayList<CostProjectSection>();
		// 搜索已经有的项目
		List<CostProjectSection> tSectionList = costProjectSectionManager.getPermSectionList();
		// 加入已经有的项目
		if (tSectionList != null && tSectionList.size() > 0) {
			dropProjectList.addAll(tSectionList);
		}

		return dropProjectList;
	}
	public void setDropProjectList(List<CostProjectSection> dropProjectList) {
		this.dropProjectList = dropProjectList;
	}
	public String getLatestDetailId() {
		return latestDetailId;
	}

	public void setLatestDetailId(String latestDetailId) {
		this.latestDetailId = latestDetailId;
	}
  
	
	public CostBudgetMonthDetail getTotalRow() {
		return totalRow;
	}
	
	public void setTotalRow(CostBudgetMonthDetail totalRow) {
		this.totalRow = totalRow;
	}
	

	/**
	 * 导出月度资金计划汇总表
	 * @param planYearMonth 搜索年月(格式: yyyy-MM)
	 * @return 导弹问问
	 * 
	 */
	public String exportMonthStat(){
		
		if(StringUtils.isBlank(getPlanYearMonth()))
			return null;
		
		try{
			long l1 = System.currentTimeMillis();
			List<CostBudgetMonthVo> list = costBudgetMonthManager.findMonthTotalByYm(getPlanYearMonth());
			Map<String,Object> beanMap = new HashMap<String,Object>();
			beanMap.put("yearMonth", getPlanYearMonth());
			beanMap.put("result", list);
	
			String fileName = "导出月度预算汇总表[" + getPlanYearMonth() + "]";
			is = JXLExcelUtil.initJxlsInputStream(beanMap, "exportCostBudgetMonthStat.xls");
			downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");

			long l2 = System.currentTimeMillis();
			log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileName +", 耗时 " + (l2-l1)/1000.00 + " 秒");
		}catch(Exception e){
			log.error("导出月度预算汇总表异常!", e);
		}finally{
			if(is!= null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "exportMonthStat";
	}

	/**
	 *导出指定"项目+年月"的月度资金计划-Excel
	 * @param planSectionId 
	 * @param planYearMonth
	 * 
	 * @return
	 */
	public String exportSectionMonthList() {

		try{ 
//			String tSectionId = Struts2Utils.getParameter("planSectionId");
			String tYearMonth = Struts2Utils.getParameter("planYearMonth");
			//搜索导出
			String searchExport = Struts2Utils.getParameter("searchExport");
			String projectName = "";
			if( entity!= null && entity.getCostProjectSection()!= null){
				projectName = entity.getCostProjectSection().getSectionName();
			}
			String title = "月度资金预算表";//+ (StringUtils.isBlank(tYearMonth)?"": ("（"+ tYearMonth +"）"));
			String fileName = "导出月度资预算表";// + (StringUtils.isBlank(tYearMonth)?"": ("[" + tYearMonth + "]"));
			//如果是搜索导出
			if(StringUtils.isNotBlank(searchExport)){
				loadMonthList();
			}else{
				//载入列表(获取项目名称前执行）
				loadMonthDetailList();
			}
			
			
			
			
			long l1 = System.currentTimeMillis();
			Map<String,Object> beanMap = new HashMap<String,Object>();
			beanMap.put("title", title);
			
			beanMap.put("result",convertModel( monthDetailPage.getResult()));
	
			is = JXLExcelUtil.initJxlsInputStream(beanMap, "exportCostBudgetMonthList.xls");
			downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			
			long l2 = System.currentTimeMillis();
			log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileName +", 耗时 " + (l2-l1)/1000.00 + " 秒");
		}catch(Exception e){
			log.error("导出年度预算表异常!", e);
			System.out.println("导出年度预算表异常!" + e.getCause());
		}finally{
			if(is!= null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "exportSectionMonthList";
	}

	public List<CostBudgetMonthDetailVo> convertModel(List<CostBudgetMonthDetail> detailList){
		List<CostBudgetMonthDetailVo> rs = new ArrayList();
		if (detailList != null) {
			CostBudgetMonthDetailVo vo = null;
			CostBudgetMonthDetail detail = null;
			for (int i = 0; i < detailList.size(); i++) {
				detail = detailList.get(i);
				vo = new CostBudgetMonthDetailVo();
				vo.setOrderNo(i + 1);
				vo.setContactId(detail.getContactId());
				vo.setContactName(detail.getContactName());
				vo.setContactNo(detail.getContactNo());
				vo.setContactRealTotalAmt(detail.getContactRealTotalAmt());
				vo.setContactTotalAmt(detail.getContactTotalAmt());
				vo.setCostBudgetMonth(detail.getCostBudgetMonth());
				vo.setCreator(detail.getCreator());
				vo.setCumuMustNoPayAmt(detail.getCumuMustNoPayAmt());
				vo.setCumuMustPayTotalAmt(detail.getCumuMustPayTotalAmt());
				vo.setCumuPaiedRate(detail.getCumuPaiedRate());
				vo.setCumuRealPayTotalAmt(detail.getCumuRealPayTotalAmt());
				vo.setCurPeriodFundBudgetAmt(detail.getCurPeriodFundBudgetAmt());
				vo.setCurPeriodFundBudgetAmt1(detail.getCurPeriodFundBudgetAmt1());
				vo.setCurPeriodFundBudgetAmt2(detail.getCurPeriodFundBudgetAmt2());
				vo.setCurPeriodFundBudgetAmt3(detail.getCurPeriodFundBudgetAmt3());
				vo.setCurPeriodFundMpayAmt(detail.getCurPeriodFundMpayAmt());
				vo.setCurPeriodNailFeedAmt(detail.getCurPeriodNailFeedAmt());
				vo.setCurPeriodPlanConfmAmt(detail.getCurPeriodPlanConfmAmt());
				vo.setCurYearPrePeriodPayAmt(detail.getCurYearPrePeriodPayAmt());
				vo.setFinishProdTotalAmt(detail.getFinishProdTotalAmt());
				vo.setMemoDesc(detail.getMemoDesc());
				vo.setNailFeedWorthAmt(detail.getNailFeedWorthAmt());
				vo.setPartb(detail.getPartb());

				vo.setPreYearCumuPaiedAmt(detail.getPreYearCumuPaiedAmt());
				vo.setRemark(detail.getRemark());
				vo.setSettleAmt(detail.getSettleAmt());
				vo.setSubjectCd(detail.getSubjectCd());
				vo.setSubjectName(detail.getSubjectName());
				vo.setYm(detail.getCostBudgetMonth().getBudegetYear()+"-"+StringUtils.leftPad(detail.getCostBudgetMonth().getBudegetMonth().toString(), 2, "0"));
				if(StringUtils.isNotBlank(detail.getStrageFlg())){
					if("1".equals(detail.getStrageFlg())){
						vo.setStrageFlg("是");
					}else{
						vo.setStrageFlg("否");
					}
				}
				vo.setProjectName(detail.getCostBudgetMonth().getCostProjectSection().getSectionName());
				rs.add(vo);
			}
		}
		return rs;
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
	
	/**
	 * 搜索月度资金计划
	 * @param planSectionId 项目ID
	 * @param planYearMonth 年月(yyyy-MM)
	 * 
	 * @return
	 */
	public String loadMonthDetailList(){
		
		String tSectionId = Struts2Utils.getParameter("planSectionId");
		String tYearMonth = Struts2Utils.getParameter("planYearMonth");
		/*String costBudgetMonthId = Struts2Utils.getParameter("costBudgetMonthId");
		//如果是传的月度ID则，取本月度ID对应的项目和年月
		if(StringUtils.isNotBlank(costBudgetMonthId)){
			entity=costBudgetMonthManager.getCostBudgetMonthById(costBudgetMonthId);
			if(entity!=null){
				tYearMonth=entity.getBudegetYear().toString()+"-"+StringUtils.leftPad(entity.getBudegetMonth().toString(), 2, "0");
				tSectionId=entity.getCostProjectSection().getCostProjectSectionId();
			}
		}*/
		String [] arr = tYearMonth.split("-");		
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("tSectionId", tSectionId);
		values.put("budegetYear", Long.valueOf(arr[0]));
		values.put("budegetMonth", Long.valueOf(arr[1]));
		values.put("nocontactVisiable", nocontactVisiable);
		values.put("strageFlg", strageFlg);
		
		//主表
		getMonth(values);
		//子表
		getMonthDetailList(values);
		
		return "loadMonthDetailList";
	}
	
	/**
	 * 获取月度计划
	 * @param values budegetYear、budegetMonth、项目ID
	 */
	private void getMonth(Map<String, Object> values){
		String hql1 = new StringBuffer()
			.append("  from CostBudgetMonth t")
			.append(" where t.budegetYear  = :budegetYear ")
			.append("   and t.budegetMonth = :budegetMonth ")
			.append("   and t.costProjectSection.costProjectSectionId = :tSectionId ")
			.toString(); 
		
		Page<CostBudgetMonth> monthPage = new Page<CostBudgetMonth>();
		monthPage = costBudgetMonthManager.findPage(monthPage, hql1, values);
		if(monthPage != null && monthPage.getResult().size() > 0){
			entity = monthPage.getResult().get(0);
		}else{
			entity = null;
		}
	}
	
	
	/**
	 * 计算月度资金汇总（明细、汇总）
	 * @param values  budegetYear、budegetMonth、项目ID
	 */
	//@Transactional(propagation = Propagation.SUPPORTS)//这行注释很重要,释放锁!
	private void getMonthDetailList(Map<String, Object> values){
		// 明细
		StringBuffer hql2 = new StringBuffer()
			.append("  from CostBudgetMonthDetail c ")
			.append(" where c.costBudgetMonth.costProjectSection.costProjectSectionId = :tSectionId ")
			.append("   and c.costBudgetMonth.budegetYear  = :budegetYear ")
			.append("   and c.costBudgetMonth.budegetMonth = :budegetMonth ");
			//有无预算情况
			if(values.get("nocontactVisiable")!=null&&"show".equals((String)values.get("nocontactVisiable"))){				
			}else{
				hql2.append(" and c.curPeriodFundBudgetAmt1 >0 ");
			}
			//战略
			if(values.get("strageFlg")!=null&&StringUtils.isNotBlank((String)values.get("strageFlg"))){
				hql2.append(" and c.strageFlg = :strageFlg");
			}
			//注意排序
			hql2.append(" order by c.subjectCd asc,  c.contactNo asc");// c.subjectName asc,
			
		
		// 设置记录数
		long tmpTotalCount = costBudgetMonthDetailManager.countHqlResult(hql2.toString(), values);
		monthDetailPage = new Page<CostBudgetMonthDetail>(Long.valueOf(tmpTotalCount).intValue());
		//monthDetailPage.setPageSize(Long.valueOf(tmpTotalCount).intValue());
		monthDetailPage = costBudgetMonthDetailManager.findPage(monthDetailPage, hql2.toString(), values);
		List<CostBudgetMonthDetail> l=costBudgetMonthDetailManager.find(hql2.toString(), values);
		if(l!=null) {
			monthDetailPage.setResult(l);
		}
		
		// 汇总
		totalCostBudgetMonth(monthDetailPage.getResult());
	}
	public CostBudgetMonth getEntity() {
		return entity;
	}
	public void setEntity(CostBudgetMonth entity) {
		this.entity = entity;
	}
	public Page<CostBudgetMonthDetail> getMonthDetailPage() {
		return monthDetailPage;
	}
	public void setMonthDetailPage(Page<CostBudgetMonthDetail> monthDetailPage) {
		this.monthDetailPage = monthDetailPage;
	}
	public CostBudgetMonthDetail getMonthDetail() {
		return monthDetail;
	}
	public void setMonthDetail(CostBudgetMonthDetail monthDetail) {
		this.monthDetail = monthDetail;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getSubjectCd() {
		return subjectCd;
	}
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPartb() {
		return partb;
	}
	public void setPartb(String partb) {
		this.partb = partb;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getSectionIds() {
		return sectionIds;
	}
	public void setSectionIds(String sectionIds) {
		this.sectionIds = sectionIds;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPlanYearMonth() {
		return planYearMonth;
	}
	public void setPlanYearMonth(String planYearMonth) {
		this.planYearMonth = planYearMonth;
	}
	public String getPlanSectionId() {
		return planSectionId;
	}
	public void setPlanSectionId(String planSectionId) {
		this.planSectionId = planSectionId;
	}
	public List<CostBudgetMonthVo> getMonthStatVoList() {
		return monthStatVoList;
	}
	public void setMonthStatVoList(List<CostBudgetMonthVo> monthStatVoList) {
		this.monthStatVoList = monthStatVoList;
	}
	public String getNocontactVisiable() {
		return nocontactVisiable;
	}
	public void setNocontactVisiable(String nocontactVisiable) {
		this.nocontactVisiable = nocontactVisiable;
	}
	public String getStrageFlg() {
		return strageFlg;
	}
	public void setStrageFlg(String strageFlg) {
		this.strageFlg = strageFlg;
	} 
	
	
	
}
