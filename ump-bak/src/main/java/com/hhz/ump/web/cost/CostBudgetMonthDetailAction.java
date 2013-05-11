package com.hhz.ump.web.cost;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostBudgetMonthDetailManager;
import com.hhz.ump.dao.cost.CostBudgetMonthManager;
import com.hhz.ump.entity.cost.CostBudgetMonthDetail;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.bid.PoiStyle;
import com.hhz.ump.web.ctdb.PoiExcelTool;

@Namespace("/cost")
@Results( { @Result(name = "exportExcel", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile","contentDisposition", "attachment;filename=${excelFileName}.xls" }) ,
		@Result(name = "exportMonthList", type = "stream",params = { "contentType", "application/vnd.ms-excel", "inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
})
public class CostBudgetMonthDetailAction extends CrudActionSupport<CostBudgetMonthDetail> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3251863929944308093L;
	@Autowired
	private CostBudgetMonthDetailManager costBudgetMonthDetailManager;
	@Autowired
	private CostBudgetMonthManager costBudgetMonthManager;

	/**
	 * 月度资金预算明细汇总数据
	 */
	private CostBudgetMonthDetail totalRow;
	/**
	 * 搜索Map
	 */
	private Map<String, Object> values;
	/**
	 * 月度资金预算列表
	 */
	private List<CostBudgetMonthDetail> cbcpList = new ArrayList<CostBudgetMonthDetail>();
	/**
	 * 年月
	 */
	private String ym;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;

	//导出“月度资金预算清单”
	private InputStream is;
	private String downFileName;
	
	
	/**
	 *导出Excel
	 * @param yearMonth 
	 * @return
	 */
	public String exportExcel() {
//		// 执行搜索
//		try {
//			String fileName = "月度预算表";
//			buildExcel(fileName);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Log.error(e);
//		}
//		return "exportExcel";
		 

			String tmpYear = Struts2Utils.getParameter("yearMonth");
			if(StringUtils.isBlank(tmpYear))
				return null;
			
			try{ 

				String title = "月度资金预算表" + (StringUtils.isBlank(tmpYear)?"": ("（"+ tmpYear +"年）"));
				String fileName = "导出年度预算表" + (StringUtils.isBlank(tmpYear)?"": ("[" + tmpYear + "]"));
				
				
//				long l1 = System.currentTimeMillis();
				Map<String,Object> beanMap = new HashMap<String,Object>();
				beanMap.put("title", title);
//				beanMap.put("result", voList);
		
				is = JXLExcelUtil.initJxlsInputStream(beanMap, "exportCostBudgetYearList.xls");
				downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");

//				long l2 = System.currentTimeMillis();
//				log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileName +", 耗时 " + (l2-l1)/1000.00 + " 秒");
			}catch(Exception e){
//				log.error("导出年度预算表异常!", e);
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
			excelFileName = fileName;
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
		sheet.createFreezePane(5, 2);
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
		// 第1行，表头
		HSSFRow row = sheet.createRow(0);
		// 列头
		PoiExcelTool.addCell(row, cell, text, 0, style4, "1", "序号", null);
		PoiExcelTool.addCell(row, cell, text, 1, style4, "1", "成本科目", null);
		PoiExcelTool.addCell(row, cell, text, 2, style4, "1", "合同编号", null);
		PoiExcelTool.addCell(row, cell, text, 3, style4, "1", "合同名称", null);
		PoiExcelTool.addCell(row, cell, text, 4, style4, "1", "施工单位（乙方）", null);
		PoiExcelTool.addCell(row, cell, text, 5, style4, "1", "合同总价", null);
		PoiExcelTool.addCell(row, cell, text, 6, style4, "1", "实际合同总价", null);
		PoiExcelTool.addCell(row, cell, text, 7, style4, "1", "结算价", null);
		PoiExcelTool.addCell(row, cell, text, 8, style4, "1", "已完产值合计（元）", null);
		PoiExcelTool.addCell(row, cell, text, 9, style4, "1", "其中甲供料产值", null);
		PoiExcelTool.addCell(row, cell, text, 10, style4, "1", "累计应付款合计(元)", null);
		PoiExcelTool.addCell(row, cell, text, 11, style4, "1", "累计实际支付", null);
		PoiExcelTool.addCell(row, cell, text, 12, style4, "1", "累计支付比率%", null);
		PoiExcelTool.addCell(row, cell, text, 13, style4, "1", "本期拟确认产值", null);
		PoiExcelTool.addCell(row, cell, text, 14, style4, "1", "本期产值内甲供料", null);
		PoiExcelTool.addCell(row, cell, text, 15, style4, "1", "本期：资金应付预算", null);
		PoiExcelTool.addCell(row, cell, text, 16, style4, "1", "累计：应付未付", null);
		PoiExcelTool.addCell(row, cell, text, 17, style4, "1", "本期资金预算", null);
		PoiExcelTool.addCell(row, cell, text, 18, style4, "1", "本期资金预算(批准)", null);
		PoiExcelTool.addCell(row, cell, text, 19, style4, "1", "备注", null);
		// 执行搜索
		searchMonthBudget();
		// 如果搜索结果不为空
		if (cbcpList != null && cbcpList.size() > 0) {
			Integer line = 1;
			for (CostBudgetMonthDetail detail : cbcpList) {
				row = sheet.createRow(line);
				// 序号
				PoiExcelTool.addCell(row, cell, text, 0, style2, "1", line.toString(), null);
				// 成本科目
				if (StringUtils.isNotBlank(detail.getSubjectName())) {
					PoiExcelTool.addCell(row, cell, text, 1, style2, "1", detail.getSubjectName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 1, style2, "1", "", null);
				}
				// 合同编号
				if (StringUtils.isNotBlank(detail.getContactNo())) {
					PoiExcelTool.addCell(row, cell, text, 2, style2, "1", detail.getContactNo(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 2, style2, "1", "", null);
				}
				// 合同名称
				if (StringUtils.isNotBlank(detail.getContactName())) {
					PoiExcelTool.addCell(row, cell, text, 3, style2, "1", detail.getContactName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 3, style2, "1", "", null);
				}
				// 施工单位（乙方）
				if (StringUtils.isNotBlank(detail.getPartb())) {
					PoiExcelTool.addCell(row, cell, text, 4, style2, "1", detail.getPartb(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 4, style2, "1", "", null);
				}
				// 合同总价
				if (detail.getContactTotalAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 5, style2, "0", "合同总价", detail.getContactTotalAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 5, style2, "1", "", null);
				}

				// 实际合同总价
				if (detail.getContactRealTotalAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 6, style2, "0", "实际合同总价", detail.getContactRealTotalAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 6, style2, "1", "", null);
				}
				// 结算价
				if (detail.getSettleAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 7, style2, "0", "结算价", detail.getSettleAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 7, style2, "1", "", null);
				}
				// 已完产值合计（元）
				if (detail.getFinishProdTotalAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 8, style2, "0", "已完产值合计（元）", detail.getFinishProdTotalAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 8, style2, "1", "", null);
				}
				// 其中甲供料产值
				if (detail.getNailFeedWorthAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 9, style2, "0", "其中甲供料产值", detail.getNailFeedWorthAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 9, style2, "1", "", null);
				}
				// 累计应付款合计(元)
				if (detail.getCumuMustPayTotalAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 10, style2, "0", "累计应付款合计(元)", detail.getCumuMustPayTotalAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 10, style2, "1", "", null);
				}
				// 累计实际支付
				if (detail.getCumuRealPayTotalAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 11, style2, "0", "累计实际支付", detail.getCumuRealPayTotalAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 11, style2, "1", "", null);
				}
				// 累计支付比率%
				if (detail.getCumuPaiedRate() != null) {
					PoiExcelTool.addCell(row, cell, text, 12, style2, "0", "累计支付比率%", detail.getCumuPaiedRate().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 12, style2, "1", "", null);
				}
				// 本期拟确认产值
				if (detail.getCurPeriodPlanConfmAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 13, style2, "0", "本期拟确认产值", detail.getCurPeriodPlanConfmAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 13, style2, "1", "", null);
				}
				// 本期产值内甲供料
				if (detail.getCurPeriodNailFeedAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 14, style2, "0", "本期产值内甲供料", detail.getCurPeriodNailFeedAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 14, style2, "1", "", null);
				}
				// 本期：资金应付预算
				if (detail.getCurPeriodFundMpayAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 15, style2, "0", "本期：资金应付预算", detail.getCurPeriodFundMpayAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 15, style2, "1", "", null);
				}
				// 累计：应付未付
				if (detail.getCumuMustNoPayAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 16, style2, "0", "累计：应付未付", detail.getCumuMustNoPayAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 16, style2, "1", "", null);
				}
				// 本期资金预算
				if (detail.getCurPeriodFundBudgetAmt() != null) {
					PoiExcelTool.addCell(row, cell, text, 17, style2, "0", "本期资金预算", detail.getCurPeriodFundBudgetAmt().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 17, style2, "1", "", null);
				}
				// 本期资金预算(批准)
				if (detail.getCurPeriodFundBudgetAmt3() != null) {
					PoiExcelTool.addCell(row, cell, text, 18, style2, "0", "本期资金预算(批准)", detail.getCurPeriodFundBudgetAmt3().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 18, style2, "1", "", null);
				}
				// 备注
				if (detail.getMemoDesc() != null) {
					PoiExcelTool.addCell(row, cell, text, 19, style2, "1", detail.getMemoDesc(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 19, style2, "1", "", null);
				}
				line++;
			}
		}
		try {
			if (workbook != null) {
				workbook.write(outExcelFileTmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook = null;
			}
		}
	}

	/**
	 * 搜索月度资金计划列表
	 * 
	 * @return
	 */
	public String searchMonthBudget() {
		// 科目名称
		String subjectName = Struts2Utils.getParameter("subjectName");
		// 合同编号
		String contactNo = Struts2Utils.getParameter("contactNo");
		// 合同名称
		String contactName = Struts2Utils.getParameter("contactName");
		// 施工单位
		String partb = Struts2Utils.getParameter("partb");
		//项目ID
		String sectionBizCd=Struts2Utils.getParameter("orgCd");
		
		// 准备构造搜索语句及搜索条件
		values = new HashMap<String, Object>();
		// 准备年月搜索
		if (StringUtils.isNotBlank(ym)) {
			// 年月搜索条件 yyyy-MM
			String[] yms = ym.split("-");
			values.put("budegetYear", Long.valueOf(yms[0]));
			values.put("budegetMonth", Long.valueOf(yms[1]));
		}
		// 准备科目名称搜索
		if (StringUtils.isNotBlank(subjectName)) {
			String subjectNameTmp=subjectName;
			if(StringUtils.isNotBlank(subjectName)){
				try {
					subjectNameTmp=ChangeCharset.decode2UTF_8(subjectName);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					
				}
			}
			if(!"--选择--".equals(subjectNameTmp)) {
				values.put("subjectName", "%"+subjectNameTmp+"%");
			}
		}
		if (StringUtils.isNotBlank(contactNo)) {
			String contactNoTmp=contactNo;
			if(StringUtils.isNotBlank(contactNo)){
				try {
					contactNoTmp=ChangeCharset.decode2UTF_8(contactNo);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					
				}
			}
			values.put("contactNo", "%"+contactNoTmp+"%");
		}
		if (StringUtils.isNotBlank(contactName)) {
			String contactNameTmp=contactName;
			if(StringUtils.isNotBlank(contactName)){
				try {
					contactNameTmp=ChangeCharset.decode2UTF_8(contactName);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					
				}
			}
			values.put("contactName", "%"+contactNameTmp+"%");
		}
		if (StringUtils.isNotBlank(partb)) {
			String partbTmp=partb;
			if(StringUtils.isNotBlank(partb)){
				try {
					partbTmp=ChangeCharset.decode2UTF_8(partb);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					
				}
			}
			values.put("partb", "%"+partbTmp+"%");
		}
		if (StringUtils.isNotBlank(sectionBizCd)) {
			String sectionBizCdTmp=sectionBizCd;
			if(StringUtils.isNotBlank(sectionBizCd)){
				try {
					sectionBizCdTmp=ChangeCharset.decode2UTF_8(sectionBizCd);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					
				}
			}
			values.put("sectionBizCd", Arrays.asList(sectionBizCdTmp.split(",")));
		}
		values.put("authUiid", SpringSecurityUtils.getCurrentPlasUser().getUiid());
		
		
		// 根据条件分页搜索
		cbcpList = costBudgetMonthDetailManager.listAllByMap(values);
		return "searchList";
	}

	/**
	 * 月度计划明细统计值汇总
	 * 
	 * @param cbcpList2
	 */
	private void totalCostBudgetMonth(List<CostBudgetMonthDetail> tmpList) {
		// 汇总行数据
		totalRow = new CostBudgetMonthDetail();
		for (CostBudgetMonthDetail detail : tmpList) {
			// 合同总价contactTotalAmt
			if (totalRow.getContactTotalAmt() == null) {
				if (detail.getContactTotalAmt() == null) {
					totalRow.setContactTotalAmt(BigDecimal.ZERO);
				} else {
					totalRow.setContactTotalAmt(detail.getContactTotalAmt());
				}
			} else {
				if (detail.getContactTotalAmt() != null) {
					totalRow.setContactTotalAmt(totalRow.getContactTotalAmt().add(detail.getContactTotalAmt()));
				}
			}
			// 实际合同总价contactRealTotalAmt
			if (totalRow.getContactRealTotalAmt() == null) {
				if (detail.getContactRealTotalAmt() == null) {
					totalRow.setContactRealTotalAmt(BigDecimal.ZERO);
				} else {
					totalRow.setContactRealTotalAmt(detail.getContactRealTotalAmt());
				}
			} else {
				if (detail.getContactRealTotalAmt() != null) {
					totalRow.setContactRealTotalAmt(totalRow.getContactRealTotalAmt().add(detail.getContactRealTotalAmt()));
				}
			}
			// 结算价settleAmt
			if (totalRow.getSettleAmt() == null) {
				if (detail.getSettleAmt() == null) {
					totalRow.setSettleAmt(BigDecimal.ZERO);
				} else {
					totalRow.setSettleAmt(detail.getSettleAmt());
				}
			} else {
				if (detail.getSettleAmt() != null) {
					totalRow.setSettleAmt(totalRow.getSettleAmt().add(detail.getSettleAmt()));
				}
			}
			// 已完产值合计（元）finishProdTotalAmt
			if (totalRow.getFinishProdTotalAmt() == null) {
				if (detail.getFinishProdTotalAmt() == null) {
					totalRow.setFinishProdTotalAmt(BigDecimal.ZERO);
				} else {
					totalRow.setFinishProdTotalAmt(detail.getFinishProdTotalAmt());
				}
			} else {
				if (detail.getFinishProdTotalAmt() != null) {
					totalRow.setFinishProdTotalAmt(totalRow.getFinishProdTotalAmt().add(detail.getFinishProdTotalAmt()));
				}
			}
			// 其中甲供料产值nailFeedWorthAmt
			if (totalRow.getNailFeedWorthAmt() == null) {
				if (detail.getNailFeedWorthAmt() == null) {
					totalRow.setNailFeedWorthAmt(BigDecimal.ZERO);
				} else {
					totalRow.setNailFeedWorthAmt(detail.getNailFeedWorthAmt());
				}
			} else {
				if (detail.getNailFeedWorthAmt() != null) {
					totalRow.setNailFeedWorthAmt(totalRow.getNailFeedWorthAmt().add(detail.getNailFeedWorthAmt()));
				}
			}
			// 累计应付款合计(元)cumuMustPayTotalAmt
			if (totalRow.getCumuMustPayTotalAmt() == null) {
				if (detail.getCumuMustPayTotalAmt() == null) {
					totalRow.setCumuMustPayTotalAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCumuMustPayTotalAmt(detail.getCumuMustPayTotalAmt());
				}
			} else {
				if (detail.getCumuMustPayTotalAmt() != null) {
					totalRow.setCumuMustPayTotalAmt(totalRow.getCumuMustPayTotalAmt().add(detail.getCumuMustPayTotalAmt()));
				}
			}
			// 累计实际支付cumuRealPayTotalAmt
			if (totalRow.getCumuRealPayTotalAmt() == null) {
				if (detail.getCumuRealPayTotalAmt() == null) {
					totalRow.setCumuRealPayTotalAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCumuRealPayTotalAmt(detail.getCumuRealPayTotalAmt());
				}
			} else {
				if (detail.getCumuRealPayTotalAmt() != null) {
					totalRow.setCumuRealPayTotalAmt(totalRow.getCumuRealPayTotalAmt().add(detail.getCumuRealPayTotalAmt()));
				}
			}
			// 累计支付比率%cumuPaiedRate
			if (totalRow.getCumuPaiedRate() == null) {
				if (detail.getCumuPaiedRate() == null) {
					totalRow.setCumuPaiedRate(BigDecimal.ZERO);
				} else {
					totalRow.setCumuPaiedRate(detail.getCumuPaiedRate());
				}
			} else {
				if (detail.getCumuPaiedRate() != null) {
					totalRow.setCumuPaiedRate(totalRow.getCumuPaiedRate().add(detail.getCumuPaiedRate()));
				}
			}
			// 本期拟确认产值curPeriodPlanConfmAmt
			if (totalRow.getCurPeriodPlanConfmAmt() == null) {
				if (detail.getCurPeriodPlanConfmAmt() == null) {
					totalRow.setCurPeriodPlanConfmAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCurPeriodPlanConfmAmt(detail.getCurPeriodPlanConfmAmt());
				}
			} else {
				if (detail.getCurPeriodPlanConfmAmt() != null) {
					totalRow.setCurPeriodPlanConfmAmt(totalRow.getCurPeriodPlanConfmAmt().add(detail.getCurPeriodPlanConfmAmt()));
				}
			}
			// 本期产值内甲供料curPeriodNailFeedAmt
			if (totalRow.getCurPeriodNailFeedAmt() == null) {
				if (detail.getCurPeriodNailFeedAmt() == null) {
					totalRow.setCurPeriodNailFeedAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCurPeriodNailFeedAmt(detail.getCurPeriodNailFeedAmt());
				}
			} else {
				if (detail.getCurPeriodNailFeedAmt() != null) {
					totalRow.setCurPeriodNailFeedAmt(totalRow.getCurPeriodNailFeedAmt().add(detail.getCurPeriodNailFeedAmt()));
				}
			}
			// 本期：资金应付预算curPeriodFundMpayAmt
			if (totalRow.getCurPeriodFundMpayAmt() == null) {
				if (detail.getCurPeriodFundMpayAmt() == null) {
					totalRow.setCurPeriodFundMpayAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCurPeriodFundMpayAmt(detail.getCurPeriodFundMpayAmt());
				}
			} else {
				if (detail.getCurPeriodFundMpayAmt() != null) {
					totalRow.setCurPeriodFundMpayAmt(totalRow.getCurPeriodFundMpayAmt().add(detail.getCurPeriodFundMpayAmt()));
				}
			}
			// 累计：应付未付cumuMustNoPayAmt
			if (totalRow.getCumuMustNoPayAmt() == null) {
				if (detail.getCumuMustNoPayAmt() == null) {
					totalRow.setCumuMustNoPayAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCumuMustNoPayAmt(detail.getCumuMustNoPayAmt());
				}
			} else {
				if (detail.getCumuMustNoPayAmt() != null) {
					totalRow.setCumuMustNoPayAmt(totalRow.getCumuMustNoPayAmt().add(detail.getCumuMustNoPayAmt()));
				}
			}
			// 本期资金预算curPeriodFundBudgetAmt
			if (totalRow.getCurPeriodFundBudgetAmt() == null) {
				if (detail.getCurPeriodFundBudgetAmt() == null) {
					totalRow.setCurPeriodFundBudgetAmt(BigDecimal.ZERO);
				} else {
					totalRow.setCurPeriodFundBudgetAmt(detail.getCurPeriodFundBudgetAmt());
				}
			} else {
				if (detail.getCurPeriodFundBudgetAmt() != null) {
					totalRow.setCurPeriodFundBudgetAmt(totalRow.getCurPeriodFundBudgetAmt().add(detail.getCurPeriodFundBudgetAmt()));
				}
			}
			// 本期资金预算curPeriodFundBudgetAmt3
			if (totalRow.getCurPeriodFundBudgetAmt3() == null) {
				if (detail.getCurPeriodFundBudgetAmt3() == null) {
					totalRow.setCurPeriodFundBudgetAmt3(BigDecimal.ZERO);
				} else {
					totalRow.setCurPeriodFundBudgetAmt3(detail.getCurPeriodFundBudgetAmt3());
				}
			} else {
				if (detail.getCurPeriodFundBudgetAmt3() != null) {
					totalRow.setCurPeriodFundBudgetAmt3(totalRow.getCurPeriodFundBudgetAmt3().add(detail.getCurPeriodFundBudgetAmt3()));
				}
			}
		}
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
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public CostBudgetMonthDetail getModel() {
		return null;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
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

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
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

}
