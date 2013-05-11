package com.hhz.ump.web.cost;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostBudgetAuthManager;
import com.hhz.ump.dao.cost.CostBudgetYearManager;
import com.hhz.ump.dao.cost.CostProjectSectionManager;
import com.hhz.ump.entity.cost.CostBudgetYear;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.bid.PoiStyle;
import com.hhz.ump.web.cost.vo.UncreatedBudgetYearVo;
import com.hhz.ump.web.ctdb.PoiExcelTool;
import com.hhz.ump.web.vo.CostBudgetYearVo;

@Namespace("/cost")
@Results( { 
	@Result(name = "exportExcel", type = "stream", params = { "contentType", "application/vnd.ms-excel","inputName", "excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }),
	@Result(name = "exportMonthList", type = "stream",params = { "contentType", "application/vnd.ms-excel", "inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
})
public class CostBudgetYearAction extends CrudActionSupport<CostBudgetYear> {

	private static final long serialVersionUID = 4570637730831100834L;

	@Autowired
	protected CostBudgetYearManager costBudgetYearManager;
	@Autowired
	protected CostProjectSectionManager costProjectSectionManager;
	@Autowired
	protected CostBudgetAuthManager costBudgetAuthManager;
	/**
	 * 年计划分页对象
	 */
	private Page<CostBudgetYear> page = new Page<CostBudgetYear>(10);
	/**
	 * 年计划对象
	 */
	private CostBudgetYear entity;
	/**
	 * 项目列表(中心列表)
	 */
	private List<CostProjectSection> dropDownList;

	/**
	 * 未创建的项目列表
	 */
	private List<UncreatedBudgetYearVo> uncreateYears;
	/**
	 * 搜索列表时的汇总行
	 */
	private CostBudgetYear totalRow;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;
	

	//导出年度预算表
	private InputStream is;
	private String downFileName;
	
	
	//搜索条件
	private String pageNo;//页码
	private String sectionIds;//选中项目
	private String budgetYear;//年份,页面间传递参数 
	private String sectionName;//项目名称
	

	@Override
	public CostBudgetYear getModel() {
		if (StringUtils.isNotBlank(getId())) {
			entity = costBudgetYearManager.getEntity(getId());
		}
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		//初始化
		dropDownList = new ArrayList<CostProjectSection>();
		
		// 若传入ID
		if (StringUtils.isNotBlank(getId())) {
			entity = costBudgetYearManager.getEntity(getId());
			dropDownList.add(entity.getCostProjectSection());
			
			//设置值
			budgetYear = String.valueOf(entity.getBudgetYear());
			
		} else {
			// 创建一个空的项目
			CostProjectSection project = new CostProjectSection();
			project.setCostProjectSectionId("");
			project.setSectionName(" ");
			dropDownList.add(project);
			
			// 搜索已经有的项目
			List<CostProjectSection> permSectionList = costProjectSectionManager.getPermSectionList();
			// 加入已经有的项目
			if (permSectionList != null && permSectionList.size() > 0) {
				dropDownList.addAll(permSectionList);
			}

			String tBudgetYear = Struts2Utils.getParameter("budgetYear");
			if(StringUtils.isNotBlank(tBudgetYear)){
				budgetYear = String.valueOf(budgetYear);
			}
		}
	}
	/**
	 * 新增，修改年计划页面
	 */
	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}
	@Override
	public String input() throws Exception {

		return "input";
	}

	/**
	 *导出Excel
	 * @param exportFlg 不空，导出全部;若空，分页搜索
	 * @param orgCd
	 * @param year
	 * @param sectionName
	 * @param remark
	 * @return
	 */
	public String exportExcel() {

		String tmpYear = Struts2Utils.getParameter("budgetYear");
//		if(StringUtils.isBlank(tmpYear))
//			return null;
		
		try{
			//搜索
			loadYearList();
			
			
			List<CostBudgetYearVo> voList = new ArrayList<CostBudgetYearVo>();
			CostBudgetYearVo vo = null;
			int i = 1; //序号
			for (CostBudgetYear tYear : page.getResult()) {
				vo = new CostBudgetYearVo();
				vo.setOrderNo(i++);
				vo.setCostBudgetYearId(tYear.getCostBudgetYearId());
				vo.setCostProjectSection(tYear.getCostProjectSection());
				vo.setBudgetYear(tYear.getBudgetYear());
				vo.setSectionTotalAmt(tYear.getSectionTotalAmt());
				vo.setTargetCostAmt(tYear.getTargetCostAmt());
				vo.setPreYearPaiedAmt(tYear.getPreYearPaiedAmt());
				vo.setAreaTotalAmt(tYear.getAreaTotalAmt());
				vo.setGroupTotalAmt(tYear.getGroupTotalAmt());
				vo.setSuggestDesc(tYear.getSuggestDesc());
				vo.setMemoDesc(tYear.getMemoDesc());
				vo.setPlanAmtM01(tYear.getPlanAmtM01());
				vo.setPlanAmtM02(tYear.getPlanAmtM02());
				vo.setPlanAmtM03(tYear.getPlanAmtM03());
				vo.setPlanAmtM04(tYear.getPlanAmtM04());
				vo.setPlanAmtM05(tYear.getPlanAmtM05());
				vo.setPlanAmtM06(tYear.getPlanAmtM06());
				vo.setPlanAmtM07(tYear.getPlanAmtM07());
				vo.setPlanAmtM08(tYear.getPlanAmtM08());
				vo.setPlanAmtM09(tYear.getPlanAmtM09());
				vo.setPlanAmtM10(tYear.getPlanAmtM10());
				vo.setPlanAmtM11(tYear.getPlanAmtM11());
				vo.setPlanAmtM12(tYear.getPlanAmtM12());
				vo.setBasesDescM01(tYear.getBasesDescM01());
				vo.setBasesDescM02(tYear.getBasesDescM02());
				vo.setBasesDescM03(tYear.getBasesDescM03());
				vo.setBasesDescM04(tYear.getBasesDescM04());
				vo.setBasesDescM05(tYear.getBasesDescM05());
				vo.setBasesDescM06(tYear.getBasesDescM06());
				vo.setBasesDescM07(tYear.getBasesDescM07());
				vo.setBasesDescM08(tYear.getBasesDescM08());
				vo.setBasesDescM09(tYear.getBasesDescM09());
				vo.setBasesDescM10(tYear.getBasesDescM10());
				vo.setBasesDescM11(tYear.getBasesDescM11());
				vo.setBasesDescM12(tYear.getBasesDescM12());
				vo.setRemark(tYear.getRemark());
				voList.add(vo);
			}

			String title = "年度资金预算表" + (StringUtils.isBlank(tmpYear)?"": ("（"+ tmpYear +"年）"));
			String fileName = "导出年度预算表" + (StringUtils.isBlank(tmpYear)?"": ("[" + tmpYear + "]"));
			
			
//			long l1 = System.currentTimeMillis();
			Map<String,Object> beanMap = new HashMap<String,Object>();
			beanMap.put("title", title);
			beanMap.put("result", voList);
	
			is = JXLExcelUtil.initJxlsInputStream(beanMap, "exportCostBudgetYearList.xls");
			downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");

//			long l2 = System.currentTimeMillis();
//			log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileName +", 耗时 " + (l2-l1)/1000.00 + " 秒");
		}catch(Exception e){
//			log.error("导出年度预算表异常!", e);
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
		return "exportMonthList";
	}

	/**
	 * 建立导出的excel
	 * 
	 * @param fileName
	 */
	private void buildExcel(String fileName) {
		ByteArrayOutputStream outExcelFile = null;
		try {
			excelFileName=fileName;
			// 输出文件流
			outExcelFile = new ByteArrayOutputStream();
			// 将分析数据填入excel
			fillInExcel(fileName, outExcelFile);
			// 将数据读出到数组
			byte[] data = outExcelFile.toByteArray();
			// 将数据写入文件，执行导出
			excelFile = new ByteArrayInputStream(data);
			// 文件名编码转换，防止乱码
			excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e);
		}

	}

	/**
	 * 填充EXCEL
	 * 
	 * @param fileName
	 * @param outExcelFile
	 */
	private void fillInExcel(String fileName, ByteArrayOutputStream outExcelFileTmp) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(fileName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		sheet.setDefaultRowHeight((short) 25);
		// 冻结
		sheet.createFreezePane(7, 2);
		// 生成一个样式
		HSSFCellStyle style = PoiStyle.buildStyle(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = PoiStyle.buildStyle2(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style3 = PoiStyle.buildStyle3(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style4 = PoiStyle.buildStyle4(workbook);

		// 单元格
		HSSFCell cell = null;
		// 单元格内容
		HSSFRichTextString text = null;
		// 合并单元区域
		CellRangeAddress address = null;
		// 第1行
		HSSFRow row = sheet.createRow(0);
		// 列头
		PoiExcelTool.addCell(row, cell, text, 0, style4, "1", "序号", null);
		PoiExcelTool.addCell(row, cell, text, 1, style4, "1", "年份", null);
		PoiExcelTool.addCell(row, cell, text, 2, style4, "1", "项目名称", null);
		PoiExcelTool.addCell(row, cell, text, 3, style4, "1", "目标成本", null);
		PoiExcelTool.addCell(row, cell, text, 4, style4, "1", "本年度之前已付额", null);
		PoiExcelTool.addCell(row, cell, text, 5, style4, "1", "本年度预算批准额", null);
		PoiExcelTool.addCell(row, cell, text, 6, style4, "1", "备注", null);
		PoiExcelTool.addCell(row, cell, text, 7, style4, "1", "1月份", null);
		PoiExcelTool.addCell(row, cell, text, 8, style4, "1", "2月份", null);
		PoiExcelTool.addCell(row, cell, text, 9, style4, "1", "3月份", null);
		PoiExcelTool.addCell(row, cell, text, 10, style4, "1", "4月份", null);
		PoiExcelTool.addCell(row, cell, text, 11, style4, "1", "5月份", null);
		PoiExcelTool.addCell(row, cell, text, 12, style4, "1", "6月份", null);
		PoiExcelTool.addCell(row, cell, text, 13, style4, "1", "7月份", null);
		PoiExcelTool.addCell(row, cell, text, 14, style4, "1", "8月份", null);
		PoiExcelTool.addCell(row, cell, text, 15, style4, "1", "9月份", null);
		PoiExcelTool.addCell(row, cell, text, 16, style4, "1", "10月份", null);
		PoiExcelTool.addCell(row, cell, text, 17, style4, "1", "11月份", null);
		PoiExcelTool.addCell(row, cell, text, 18, style4, "1", "12月份", null);

		// 表体数据
		Integer line = 1;
		for (CostBudgetYear yearBudget : page.getResult()) {
			row = sheet.createRow(line);
			PoiExcelTool.addCell(row, cell, text, 0, style2, "1", line.toString(), null);
			if(yearBudget.getBudgetYear()!=null) {
				PoiExcelTool.addCell(row, cell, text, 1, style2, "1", yearBudget.getBudgetYear().toString(), null);
			}else{
				PoiExcelTool.addCell(row,cell,text,1,style2,"1","",null);
			}
			if( yearBudget.getCostProjectSection()!=null) {
				PoiExcelTool.addCell(row, cell, text, 2, style2, "1", yearBudget.getCostProjectSection().getSectionName(), null);
			}else{
				PoiExcelTool.addCell(row,cell,text,2,style2,"1","",null);
			}
			if (yearBudget.getTargetCostAmt() != null) {
				PoiExcelTool.addCell(row, cell, text, 3, style2, "0", "目标成本", yearBudget.getTargetCostAmt().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,3,style2,"1","",null);
			}
			if (yearBudget.getPreYearPaiedAmt() != null) {
				PoiExcelTool.addCell(row, cell, text, 4, style2, "0", "本年度之前已付额", yearBudget.getPreYearPaiedAmt().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,4,style2,"1","",null);
			}
			if(yearBudget.getGroupTotalAmt()!=null){
				PoiExcelTool.addCell(row, cell, text, 5, style2, "0", "本年度预算批准额", yearBudget.getGroupTotalAmt().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,5,style2,"1","",null);
			}
			if(yearBudget.getMemoDesc()!=null){
				PoiExcelTool.addCell(row, cell, text, 6, style2, "1", yearBudget.getMemoDesc(), null);
			}else{
				PoiExcelTool.addCell(row,cell,text,6,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM01()!=null){
				PoiExcelTool.addCell(row, cell, text, 7, style2, "0", "1月份", yearBudget.getPlanAmtM01().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,7,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM02()!=null) {
				PoiExcelTool.addCell(row, cell, text, 8, style2, "0", "2月份", yearBudget.getPlanAmtM02().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,8,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM03()!=null) {
				PoiExcelTool.addCell(row, cell, text, 9, style2, "0", "3月份", yearBudget.getPlanAmtM03().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,9,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM04()!=null) {
				PoiExcelTool.addCell(row, cell, text, 10, style2, "0", "4月份", yearBudget.getPlanAmtM04().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,10,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM05()!=null) {
				PoiExcelTool.addCell(row, cell, text, 11, style2, "0", "5月份", yearBudget.getPlanAmtM05().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,11,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM06()!=null) {
				PoiExcelTool.addCell(row, cell, text, 12, style2, "0", "6月份", yearBudget.getPlanAmtM06().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,12,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM07()!=null) {
				PoiExcelTool.addCell(row, cell, text, 13, style2, "0", "7月份", yearBudget.getPlanAmtM07().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,13,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM08()!=null) {
				PoiExcelTool.addCell(row, cell, text, 14, style2, "0", "8月份", yearBudget.getPlanAmtM08().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,14,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM09()!=null) {
				PoiExcelTool.addCell(row, cell, text, 15, style2, "0", "9月份", yearBudget.getPlanAmtM09().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,15,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM10()!=null) {
				PoiExcelTool.addCell(row, cell, text, 16, style2, "0", "10月份", yearBudget.getPlanAmtM10().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,16,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM11()!=null) {
				PoiExcelTool.addCell(row, cell, text, 17, style2, "0", "11月份", yearBudget.getPlanAmtM11().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,17,style2,"1","",null);
			}
			if(yearBudget.getPlanAmtM12()!=null) {
				PoiExcelTool.addCell(row, cell, text, 18, style2, "0", "12月份", yearBudget.getPlanAmtM12().doubleValue());
			}else{
				PoiExcelTool.addCell(row,cell,text,18,style2,"1","",null);
			}
			line++;
		}
		  try {
  			if(workbook!=null) {
					workbook.write(outExcelFileTmp);
				}
			} catch (IOException e) {
				e.printStackTrace();			
			}finally{
				if(workbook!=null){
					workbook=null;
				}
			}

	} 
	
	/**
	 * 年度计划搜索列表
	 * @param exportFlg 导出标记 1-导出 
	 * @param sectionIds 选中项目
	 * @param budgetYear 年份
	 * @param sectionName 项目名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadYearList() throws Exception {
		
		//导出标记 1-导出 
		String exportFlg = Struts2Utils.getParameter("exportFlg");
 
		
		//获取有权限的所有项目
		List<String> sectionIdList = costProjectSectionManager.getSectionIdList();


		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sectionIdList", sectionIdList);
		
		// 搜索语句条件初始化
		StringBuffer hql = new StringBuffer()
				.append("   from CostBudgetYear y ")
				.append("  where y.costProjectSection.costProjectSectionId in (:sectionIdList) ");
		 
		if (StringUtils.isNotBlank(getSectionIds())) {
			hql.append(" and y.costProjectSection.costProjectSectionId in( :selectedIdList)");
			values.put("selectedIdList", Arrays.asList(getSectionIds().split(",")));
		} 
		if (StringUtils.isNotBlank(getBudgetYear())) {
			hql.append(" and y.budgetYear = :budgetYear ");
			values.put("budgetYear", Long.valueOf(getBudgetYear()));
		}
		if (StringUtils.isNotBlank(getSectionName())) {
			hql.append(" and y.costProjectSection.sectionName like :sectionName ");
			values.put("sectionName", "%" + getSectionName() + "%");
		} 
		hql.append(" order by y.budgetYear desc ,y.costProjectSection.sectionName desc");

		//如果是导出，则不需要分页，故搜索总条数作为页码大小
		if(StringUtils.isNotBlank(exportFlg)){
			Long totalNum = costBudgetYearManager.countTotal(values,hql);
			page.setPageSize(totalNum.intValue());
		}
		
		if(StringUtils.isNotBlank(getPageNo())){
			page.setPageNo(Integer.valueOf(getPageNo()).intValue());
		}
		// 执行搜索
		page = costBudgetYearManager.findPage(page, hql.toString(), values);
		// 计算统计值，年度计划搜索列表汇总
		totalCostBudgetYear(page);
		
		return "loadYearList";
	}

	/**
	 * 年度计划搜索列表汇总
	 * 
	 * @param temPage
	 */
	private void totalCostBudgetYear(Page<CostBudgetYear> temPage) {
		// 统计值-totalRow
		totalRow = new CostBudgetYear();
		for (CostBudgetYear yearBudget : temPage.getResult()) {
			// 目标成本targetCostAmt
			if (totalRow.getTargetCostAmt() == null) {
				if (yearBudget.getTargetCostAmt() == null || BigDecimal.ZERO.equals(yearBudget.getTargetCostAmt())) {
					totalRow.setTargetCostAmt(BigDecimal.ZERO);
				} else {
					totalRow.setTargetCostAmt(yearBudget.getTargetCostAmt());
				}
			} else {
				// 如果totalRow已经有值
				if (yearBudget.getTargetCostAmt() != null) {
					totalRow.setTargetCostAmt(totalRow.getTargetCostAmt().add(yearBudget.getTargetCostAmt()));
				}

			}
			// 本年度之前已付额preYearPaiedAmt
			if (totalRow.getPreYearPaiedAmt() == null) {
				if (yearBudget.getPreYearPaiedAmt() == null || BigDecimal.ZERO.equals(yearBudget.getPreYearPaiedAmt())) {
					totalRow.setPreYearPaiedAmt(BigDecimal.ZERO);
				} else {
					totalRow.setPreYearPaiedAmt(yearBudget.getPreYearPaiedAmt());
				}
			} else {
				if (yearBudget.getPreYearPaiedAmt() != null) {
					totalRow.setPreYearPaiedAmt(totalRow.getPreYearPaiedAmt().add(yearBudget.getPreYearPaiedAmt()));
				}
			}
			// 本年度预算批准额groupTotalAmt
			if (totalRow.getGroupTotalAmt() == null) {
				if (yearBudget.getGroupTotalAmt() == null || BigDecimal.ZERO.equals(yearBudget.getGroupTotalAmt())) {
					totalRow.setGroupTotalAmt(BigDecimal.ZERO);
				} else {
					totalRow.setGroupTotalAmt(yearBudget.getGroupTotalAmt());
				}
			} else {
				if (yearBudget.getGroupTotalAmt() != null) {
					totalRow.setGroupTotalAmt(totalRow.getGroupTotalAmt().add(yearBudget.getGroupTotalAmt()));
				}
			}
			// 备注
			// 1月份planAmtM01
			if (totalRow.getPlanAmtM01() == null) {
				if (yearBudget.getPlanAmtM01() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM01())) {
					totalRow.setPlanAmtM01(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM01(yearBudget.getPlanAmtM01());
				}
			} else {
				if (yearBudget.getPlanAmtM01() != null) {
					totalRow.setPlanAmtM01(totalRow.getPlanAmtM01().add(yearBudget.getPlanAmtM01()));
				}
			}
			// 2月份planAmtM02
			if (totalRow.getPlanAmtM02() == null) {
				if (yearBudget.getPlanAmtM02() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM02())) {
					totalRow.setPlanAmtM02(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM02(yearBudget.getPlanAmtM02());
				}
			} else {
				if (yearBudget.getPlanAmtM02() != null) {
					totalRow.setPlanAmtM02(totalRow.getPlanAmtM02().add(yearBudget.getPlanAmtM02()));
				}
			}
			// 3月份planAmtM03
			if (totalRow.getPlanAmtM03() == null) {
				if (yearBudget.getPlanAmtM03() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM03())) {
					totalRow.setPlanAmtM03(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM03(yearBudget.getPlanAmtM03());
				}
			} else {
				if (yearBudget.getPlanAmtM03() != null) {
					totalRow.setPlanAmtM03(totalRow.getPlanAmtM03().add(yearBudget.getPlanAmtM03()));
				}
			}
			// 4月份planAmtM04
			if (totalRow.getPlanAmtM04() == null) {
				if (yearBudget.getPlanAmtM04() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM04())) {
					totalRow.setPlanAmtM04(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM04(yearBudget.getPlanAmtM04());
				}
			} else {
				if (yearBudget.getPlanAmtM04() != null) {
					totalRow.setPlanAmtM04(totalRow.getPlanAmtM04().add(yearBudget.getPlanAmtM04()));
				}
			}
			// 5月份planAmtM05
			if (totalRow.getPlanAmtM05() == null) {
				if (yearBudget.getPlanAmtM05() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM05())) {
					totalRow.setPlanAmtM05(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM05(yearBudget.getPlanAmtM05());
				}
			} else {
				if (yearBudget.getPlanAmtM05() != null) {
					totalRow.setPlanAmtM05(totalRow.getPlanAmtM05().add(yearBudget.getPlanAmtM05()));
				}
			}
			// 6月份planAmtM06
			if (totalRow.getPlanAmtM06() == null) {
				if (yearBudget.getPlanAmtM06() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM06())) {
					totalRow.setPlanAmtM06(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM06(yearBudget.getPlanAmtM06());
				}
			} else {
				if (yearBudget.getPlanAmtM06() != null) {
					totalRow.setPlanAmtM06(totalRow.getPlanAmtM06().add(yearBudget.getPlanAmtM06()));
				}
			}
			// 7月份planAmtM07
			if (totalRow.getPlanAmtM07() == null) {
				if (yearBudget.getPlanAmtM07() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM07())) {
					totalRow.setPlanAmtM07(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM07(yearBudget.getPlanAmtM07());
				}
			} else {
				if (yearBudget.getPlanAmtM07() != null) {
					totalRow.setPlanAmtM07(totalRow.getPlanAmtM07().add(yearBudget.getPlanAmtM07()));
				}
			}
			// 8月份planAmtM08
			if (totalRow.getPlanAmtM08() == null) {
				if (yearBudget.getPlanAmtM08() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM08())) {
					totalRow.setPlanAmtM08(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM08(yearBudget.getPlanAmtM08());
				}
			} else {
				if (yearBudget.getPlanAmtM08() != null) {
					totalRow.setPlanAmtM08(totalRow.getPlanAmtM08().add(yearBudget.getPlanAmtM08()));
				}
			}
			// 9月份planAmtM09
			if (totalRow.getPlanAmtM09() == null) {
				if (yearBudget.getPlanAmtM09() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM09())) {
					totalRow.setPlanAmtM09(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM09(yearBudget.getPlanAmtM09());
				}
			} else {
				if (yearBudget.getPlanAmtM09() != null) {
					totalRow.setPlanAmtM09(totalRow.getPlanAmtM09().add(yearBudget.getPlanAmtM09()));
				}
			}
			// 10月份planAmtM10
			if (totalRow.getPlanAmtM10() == null) {
				if (yearBudget.getPlanAmtM10() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM10())) {
					totalRow.setPlanAmtM10(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM10(yearBudget.getPlanAmtM10());
				}
			} else {
				if (yearBudget.getPlanAmtM10() != null) {
					totalRow.setPlanAmtM10(totalRow.getPlanAmtM10().add(yearBudget.getPlanAmtM10()));
				}
			}
			// 11月份planAmtM11
			if (totalRow.getPlanAmtM11() == null) {
				if (yearBudget.getPlanAmtM11() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM11())) {
					totalRow.setPlanAmtM11(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM11(yearBudget.getPlanAmtM11());
				}
			} else {
				if (yearBudget.getPlanAmtM11() != null) {
					totalRow.setPlanAmtM11(totalRow.getPlanAmtM11().add(yearBudget.getPlanAmtM11()));
				}
			}
			// 12月份planAmtM12
			if (totalRow.getPlanAmtM12() == null) {
				if (yearBudget.getPlanAmtM12() == null || BigDecimal.ZERO.equals(yearBudget.getPlanAmtM12())) {
					totalRow.setPlanAmtM12(BigDecimal.ZERO);
				} else {
					totalRow.setPlanAmtM12(yearBudget.getPlanAmtM12());
				}
			} else {
				if (yearBudget.getPlanAmtM12() != null) {
					totalRow.setPlanAmtM12(totalRow.getPlanAmtM12().add(yearBudget.getPlanAmtM12()));
				}
			}
		}
	}

	/**
	 * 新增年资金页面的搜索列表
	 * @param planYear 格式： yyyy
	 * 
	 * @return
	 */
	public String planList() throws Exception {
		
		String tPlanYear = Struts2Utils.getParameter("budgetYear");
		if(StringUtils.isNotBlank(tPlanYear)){
			//获取有权限的所有项目
			List<String> sectionIdList = costProjectSectionManager.getSectionIdList();
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("sectionIdList", sectionIdList);
			values.put("budgetYear", Long.valueOf(tPlanYear));
			
			//加载已创建的项目(不分页)
			String tHql = " from CostBudgetYear t where  t.budgetYear =:budgetYear and t.costProjectSection.costProjectSectionId in (:sectionIdList) "; 
			long totalCount = costBudgetYearManager.countHqlResult(tHql, values);
			page.setPageSize(Long.valueOf(totalCount).intValue());
			page = costBudgetYearManager.findPage(page, tHql.toString(), values);
			
			//计算统计值，年度计划搜索列表汇总
			totalCostBudgetYear(page);
			
			List<CostBudgetYear> createdList = page.getResult();
			List<String> createdIdList = new ArrayList<String>();
			for (CostBudgetYear tCreated : createdList) {
				createdIdList.add(tCreated.getCostProjectSection().getCostProjectSectionId());
			}
			

			// 将授权的没有生成的项目搜索出来
			uncreateYears = new ArrayList<UncreatedBudgetYearVo>();
			// 临时vo
			UncreatedBudgetYearVo vo = null;
			// 遍历所有项目
			List<CostProjectSection> sectionList = costProjectSectionManager.getPermSectionList();
			for (CostProjectSection tSection : sectionList) {
				//若已创建，则跳过;否则，追加
				if(!createdIdList.contains(tSection.getCostProjectSectionId())){
					vo = new UncreatedBudgetYearVo();
					vo.setYear(tPlanYear);
					vo.setCostProjectSectionId(tSection.getCostProjectSectionId());
					vo.setSectionName(tSection.getSectionName());
					uncreateYears.add(vo);
				}
			}
		}
		
		// 准备合计信息

		return "planList";
	}

	/**
	 * 创建年计划
	 * 
	 * 根据用户权限查看是否存在没有创建的，没有创建，则创建
	 * 
	 * @param planYear  年份
	 * @param sectionId 项目ID
	 * 
	 * @return
	 */
	public String createYearBudget() throws Exception {
		
		String tPlanYear = Struts2Utils.getParameter("planYear");
		String tSectionId = Struts2Utils.getParameter("sectionId");
		
		if(StringUtils.isBlank(tPlanYear) || StringUtils.isBlank(tSectionId)){
			Struts2Utils.renderText("failure");
			return null;
		}
		
		// 搜索是否已经存在
		Map<String, Object> tmpMap = null;
		tmpMap = new HashMap<String, Object>();
		tmpMap.put("budgetYear", Long.valueOf(tPlanYear));
		tmpMap.put("costProjectSectionId", tSectionId);
		
		// 是否已存在
		if (costBudgetYearManager.hasCreatedYearBudget(tmpMap)) {
			// 什么都不错
			Struts2Utils.renderText("failure");
		} else {
			entity = new CostBudgetYear();
			// 项目
			entity.setCostProjectSection(costProjectSectionManager.getProjectById(tSectionId));
			// 年度
			entity.setBudgetYear(Long.valueOf(tPlanYear));
			costBudgetYearManager.saveCostBudgetYear(entity);
			Struts2Utils.renderText("success");
		}

		return null;
	}

	/**
	 * 实时更新"年计划"字段值
	 * @param id 记录ID
	 * @param fieldName 字段名
	 * @param fieldOldValue 旧值
	 * @param fieldNewValue 新值
	 */
	public void parepareSaveBudgetYearPropValue() throws Exception {
		this.getModel();
	}
	public String saveBudgetYearPropValue() throws Exception {
		
		//String fieldOldValue = Struts2Utils.getParameter("fieldOldValue");
		//String fieldNewValue = Struts2Utils.getParameter("fieldNewValue");

		String fieldName = Struts2Utils.getParameter("fieldName");
		
		BigDecimal total = BigDecimal.ZERO;
//		if(StringUtils.isNotBlank(fieldName)){
//			if("planAmtM01".equals(fieldName)
//				||"planAmtM02".equals(fieldName)
//				||"planAmtM03".equals(fieldName)
//				||"planAmtM04".equals(fieldName)
//				||"planAmtM05".equals(fieldName)
//				||"planAmtM06".equals(fieldName)
//				||"planAmtM07".equals(fieldName)
//				||"planAmtM08".equals(fieldName)
//				||"planAmtM09".equals(fieldName)
//				||"planAmtM10".equals(fieldName)
//				||"planAmtM11".equals(fieldName)
//				||"planAmtM12".equals(fieldName)
//				||"groupTotalAmt".equals(fieldName)){
				//相加
				if (entity.getPlanAmtM01() != null) {
					total = total.add(entity.getPlanAmtM01());
				}
				if (entity.getPlanAmtM02() != null) {
					total = total.add(entity.getPlanAmtM02());
				}
				if (entity.getPlanAmtM03() != null) {
					total = total.add(entity.getPlanAmtM03());
				}
				if (entity.getPlanAmtM04() != null) {
					total = total.add(entity.getPlanAmtM04());
				}
				if (entity.getPlanAmtM05() != null) {
					total = total.add(entity.getPlanAmtM05());
				}
				if (entity.getPlanAmtM06() != null) {
					total = total.add(entity.getPlanAmtM06());
				}
				if (entity.getPlanAmtM07() != null) {
					total = total.add(entity.getPlanAmtM07());
				}
				if (entity.getPlanAmtM08() != null) {
					total = total.add(entity.getPlanAmtM08());
				}
				if (entity.getPlanAmtM09() != null) {
					total = total.add(entity.getPlanAmtM09());
				}
				if (entity.getPlanAmtM10() != null) {
					total = total.add(entity.getPlanAmtM10());
				}
				if (entity.getPlanAmtM11() != null) {
					total = total.add(entity.getPlanAmtM11());
				}
				if (entity.getPlanAmtM12() != null) {
					total = total.add(entity.getPlanAmtM12());
				}
				// 如果加和大于"本年度预算批准额"
				//本年度预算批准额   :    不可编辑，其值为 1-12月份 自动汇总
				/*if (entity.getGroupTotalAmt()!=null&&entity.getGroupTotalAmt().doubleValue() < total.doubleValue()) {
					Struts2Utils.renderText("error,月度资金预算之和不允许大于年度资金预算");
					return null;
				}else{
					entity.setGroupTotalAmt(total);
				}*/
				
				entity.setGroupTotalAmt(total);
//			}
//		}
		if (entity != null) {				
			costBudgetYearManager.saveCostBudgetYear(entity);
			Struts2Utils.renderText("success,"+total.setScale(2, RoundingMode.HALF_UP));
		}
		
		
		return null;
	}

	/**
	 * 搜索是否已经存在此项目的数据
	 * 
	 * @return
	 */
	public String wthereExist() {
		
		String planYear = Struts2Utils.getParameter("planYear");
		// 项目CD
		// String projectCd=Struts2Utils.getParameter("projectCd");
		// if(StringUtils.isNotBlank(projectCd)&&StringUtils.isNotBlank(year)){
		if (StringUtils.isNotBlank(planYear)) {
			// 查看条件构造
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("budgetYear", Long.valueOf(planYear));
			// tmpMap.put("costProjectSectionId", projectCd);
			// 搜索已经授权的项目
			// 如果存在
			List<String> projectCds = costBudgetAuthManager.hasAuthedUnnewProject(SpringSecurityUtils.getCurrentPlasUser().getUiid(), Long.valueOf(planYear));
			if (projectCds != null && projectCds.size() > 0) {
				Struts2Utils.renderText("none,已经授权的" + projectCds.size() + "个项目");
			} else {
				Struts2Utils.renderText("has,");
			}
		} else {
			Struts2Utils.renderText("has,");
		}

		return null;
	}
	
	/**
	 * 修改年度表某一个月份的偏差说明
	 * @param costBudgetYearId 年度表ID
	 * @param desc 偏差内容
	 * @param planYearMonth 年月(2012-01)
	 * @return
	 */
	public String updateMonthDesc() throws Exception{
		
		String tCostYearId = Struts2Utils.getParameter("costBudgetYearId");
		String tDesc = Struts2Utils.getParameter("desc");
		String tYearMonth = Struts2Utils.getParameter("planYearMonth");
		if(StringUtils.isNotBlank(tCostYearId) && StringUtils.isNotBlank(tYearMonth)){
			CostBudgetYear tBudgetYear = costBudgetYearManager.getEntity(tCostYearId);
			String[] ym = tYearMonth.split("-");
			String month = ym[1];
			if (StringUtils.isNotBlank(month)) {
				if ("01".equals(month)) {
					tBudgetYear.setBasesDescM01(tDesc);
				}else if ("02".equals(month)) {
					tBudgetYear.setBasesDescM02(tDesc);
				}else if ("03".equals(month)) {
					tBudgetYear.setBasesDescM03(tDesc);
				}else if ("04".equals(month)) {
					tBudgetYear.setBasesDescM04(tDesc);
				}else if ("05".equals(month)) {
					tBudgetYear.setBasesDescM05(tDesc);
				}else if ("06".equals(month)) {
					tBudgetYear.setBasesDescM06(tDesc);
				}else if ("07".equals(month)) {
					tBudgetYear.setBasesDescM07(tDesc);
				}else if ("08".equals(month)) {
					tBudgetYear.setBasesDescM08(tDesc);
				}else if ("09".equals(month)) {
					tBudgetYear.setBasesDescM09(tDesc);
				}else if ("10".equals(month)) {
					tBudgetYear.setBasesDescM10(tDesc);
				}else if ("11".equals(month)) {
					tBudgetYear.setBasesDescM11(tDesc);
				}else if ("12".equals(month)) {
					tBudgetYear.setBasesDescM12(tDesc);
				}
				costBudgetYearManager.saveCostBudgetYear(tBudgetYear);
				Struts2Utils.renderText("success");
			}
		}else{
			Struts2Utils.renderText("参数不对!");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
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
	public String save() throws Exception {
		return null;
	}

	@Override
	public Page<CostBudgetYear> getPage() {
		return page;
	}

	public void setPage(Page<CostBudgetYear> page) {
		this.page = page;
	}

	public CostBudgetYear getEntity() {
		return entity;
	}

	public void setEntity(CostBudgetYear entity) {
		this.entity = entity;
	}
 
	public List<CostProjectSection> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(List<CostProjectSection> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public List<UncreatedBudgetYearVo> getUncreateYears() {
		return uncreateYears;
	}

	public void setUncreateYears(List<UncreatedBudgetYearVo> uncreateYears) {
		this.uncreateYears = uncreateYears;
	}

	public CostBudgetYear getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(CostBudgetYear totalRow) {
		this.totalRow = totalRow;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getSectionIds() {
		return sectionIds;
	}

	public void setSectionIds(String sectionIds) {
		this.sectionIds = sectionIds;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	

}
