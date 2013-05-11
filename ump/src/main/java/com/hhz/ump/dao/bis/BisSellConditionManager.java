package com.hhz.ump.dao.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisSellCondition;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.bis.BisDecisionAction;
import com.hhz.ump.web.vo.VoSellConditionCount;

@Service
@Transactional
public class BisSellConditionManager extends BaseService<BisSellCondition, String> {
	@Autowired
	private BisSellConditionDao bisSellConditionDao;
	@Autowired
	private BisProjectDao bisProjectDao;

	public void saveBisSellCondition(BisSellCondition bisSellCondition) {
		PowerUtils.setEmptyStr2Null(bisSellCondition);
		bisSellConditionDao.save(bisSellCondition);
	}

	public void deleteBisSellCondition(String id) {
		bisSellConditionDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSellCondition, String> getDao() {
		return bisSellConditionDao;
	}

	/**
	 * 查询销售情况记录
	 * @param projectId 项目ID
	 * @param year 年
	 * @param month 月
	 * @return
	 */
	public BisSellCondition getExistsSellCondition(String projectId,String year, String month) {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from BisSellCondition t where 1=1");
		if (StringUtils.isNotBlank(projectId) && StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			hql.append(" and t.bisProjectId =:projectId and t.sellYear =:sellYear and t.sellMonth =:sellMonth");
			params.put("projectId", projectId);
			params.put("sellYear", Long.valueOf(year));
			params.put("sellMonth", Long.valueOf(month));
			List<BisSellCondition> list = bisSellConditionDao.find(hql.toString(), params);
			if(list != null && list.size() > 0)
				return list.get(0);
		}
		return null;
	}
	
	/**
	 * 批量修改、增加公寓收费记录
	 * @param bisFlatList
	 */
	public void batchExecute(List<BisSellCondition> bisSellConditionList){
		Session session = bisSellConditionDao.getSession();
		for (int i = 0; i < bisSellConditionList.size(); i++) {
			session.saveOrUpdate(bisSellConditionList.get(i));
			if(i%50 == 0){
				session.flush();
				session.clear();
			}
		}
	}

	/**
	 * 查询销售情况报表各个值得总计
	 * @param sellYear
	 * @param sellMonth
	 * @return
	 */
	public VoSellConditionCount getSellCount(String sellYear, String sellMonth,String projectIds) {
		if(StringUtils.isNotBlank(sellYear) && StringUtils.isNotBlank(sellMonth)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(t.SIGN_MONEY_JH_MONTH) as signMoneyJhMonthCount, sum(t.SIGN_MONEY_SJ_MONTH) as signMoneySjMonthCount,")
			   .append("sum(t.RETURN_MONEY_JH_MONTH) as returnMoneyJhMonthCount, sum(t.RETURN_MONEY_SJ_MONTH) as returnMoneySjMonthCount,")
			   .append("sum(t.SIGN_MONEY_JH_YEAR) as signMoneyJhYearCount, sum(t.SIGN_MONEY_SJ_YEAR) as signMoneySjYearCount,")
			   .append("sum(t.RETURN_MONEY_JH_YEAR) as returnMoneyJhYearCount, sum(t.RETURN_MONEY_SJ_YEAR) as returnMoneySjYearCount,")
			   .append("sum(t.DS_MONEY) as dsMoneyCount, sum(t.DS_AREA) as dsAreaCount,")
			   .append("sum(t.DS_SUITE_NUM) as dsSuiteNumCount, sum(t.XK_MONEY) as xkMoneyCount,")
			   .append("sum(t.XK_AREA) as xkAreaCount, sum(t.XK_SUITE_NUM) as xkSuiteNumCount,")
			   .append("sum(t.NOT_SIGN_MONEY) as notSignMoneyCount, sum(t.NOT_SIGN_SUITE_NUM) as notSignSuiteNumCount,")
			   .append("sum(t.MORTGAGE_ARREARS) as mortgageArrearsCount, sum(t.OTHER_ARREARS) as otherArrearsCount");
				sql.append(" from BIS_SELL_CONDITION t where 1=1");
			if (StringUtils.isNotBlank(sellYear)) {
				sql.append(" and t.SELL_YEAR =:sellYear");
				params.put("sellYear", Long.valueOf(sellYear.trim()));
			}
			if (StringUtils.isNotBlank(sellMonth)) {
				sql.append(" and t.SELL_MONTH =:sellMonth");
				params.put("sellMonth",  Long.valueOf(sellMonth.trim()));
			}
			if(StringUtils.isNotBlank(projectIds)){
				sql.append(" and t.BIS_PROJECT_ID in(:projectIds)");
				String[] pIds = projectIds.split(",");
				params.put("projectIds", pIds);
			}else{
				sql.append(" and t.BIS_PROJECT_ID = :projectIds");
				params.put("projectIds", "-1");
			}
			List list = bisSellConditionDao.findBySql(sql.toString(), params);
			Object[] obj = null;
			VoSellConditionCount vo = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[])list.get(0);
				vo = new VoSellConditionCount();
				vo.setSignMoneyJhMonthCount(getMoneyValue((BigDecimal)obj[0]));
				vo.setSignMoneySjMonthCount(getMoneyValue((BigDecimal)obj[1]));
				vo.setMonthQyPercentCount(BisDecisionAction.calcuPercent((BigDecimal)obj[1],(BigDecimal)obj[0]));
				vo.setReturnMoneyJhMonthCount(getMoneyValue((BigDecimal)obj[2]));
				vo.setReturnMoneySjMonthCount(getMoneyValue((BigDecimal)obj[3]));
				vo.setMonthHkPercentCount(BisDecisionAction.calcuPercent((BigDecimal)obj[3],(BigDecimal)obj[2]));
				vo.setSignMoneyJhYearCount(getMoneyValue((BigDecimal)obj[4]));
				vo.setSignMoneySjYearCount(getMoneyValue((BigDecimal)obj[5]));
				vo.setYearQyPercentCount(BisDecisionAction.calcuPercent((BigDecimal)obj[5],(BigDecimal)obj[4]));
				vo.setReturnMoneyJhYearCount(getMoneyValue((BigDecimal)obj[6]));
				vo.setReturnMoneySjYearCount(getMoneyValue((BigDecimal)obj[7]));
				vo.setYearHkPercentCount(BisDecisionAction.calcuPercent((BigDecimal)obj[7],(BigDecimal)obj[6]));
				vo.setDsMoneyCount(getMoneyValue((BigDecimal)obj[8]));
				vo.setDsAreaCount(getMoneyValue((BigDecimal)obj[9]));
				vo.setDsSuiteNumCount(getMoneyValue((BigDecimal)obj[10]));
				vo.setXkMoneyCount(getMoneyValue((BigDecimal)obj[11]));
				vo.setXkAreaCount(getMoneyValue((BigDecimal)obj[12]));
				vo.setXkSuiteNumCount(getMoneyValue((BigDecimal)obj[13]));
				vo.setNotSignMoneyCount(getMoneyValue((BigDecimal)obj[14]));
				vo.setNotSignSuiteNumCount(getMoneyValue((BigDecimal)obj[15]));
				vo.setMortgageArrearsCount(getMoneyValue((BigDecimal)obj[16]));
				vo.setOtherArrearsCount(getMoneyValue((BigDecimal)obj[17]));
			}
			return vo;
		}
		return null;
	}
	/**
	 * 格式化金额数据
	 * @param value
	 * @return
	 */
	private String getMoneyValue(BigDecimal value){
		String retStr = null;
		if(value == null) {
			retStr = "0";
		} else{
			retStr = FormatUtil.formatMoney(value, 2);
		}
		return retStr;
	}
	
	/**
	 * 构建导出excel文件流
	 * @return
	 */
	public InputStream buildTemplateExcel(Map<String, Object> params) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);
		if (wbook != null) {
			try {
				this.initTemplateData(wbook,params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JXLExcelUtil.closeWorkBook(wbook);
		}
		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}
	
	/**
	 * 制定模板样式等
	 * @param wbook
	 * @throws Exception
	 */
	private void initTemplateData(WritableWorkbook wbook,Map<String, Object> params) throws Exception {
		String excelFileName = null;
		String reportType = null;
		if(null != params.get("reportType")){
			reportType = params.get("reportType").toString();
		}
		//销售情况报表
		if("1".equals(reportType)){
			String year = null;
			String month = null;
			if(null != params.get("year") && null != params.get("month")){
				year = params.get("year").toString();
				month = params.get("month").toString();
				String m = null;
				if(Integer.valueOf(params.get("month").toString()) < 10){
					m = "0"+params.get("month").toString();//1-9月转成01-09
				}else{
					m = params.get("month").toString();
				}
				excelFileName="销售情况汇报表"+"("+year+"-"+m+")";
			}else{
				excelFileName="销售情况汇报表";
			}
			
			//设置工作表名称
			WritableSheet wSheet = wbook.createSheet(excelFileName, 0);
			//冻结第几行第几列
			wSheet.getSettings().setHorizontalFreeze(2); //冻结列
			wSheet.getSettings().setVerticalFreeze(4); //冻结行
			wSheet.getSettings().setProtected(true);  //设置是否受保护
			this.initSellReportSheet(wSheet,year,month);
		}
		//商业收费情况报表
		else if("2".equals(reportType)){
			if(null != params.get("startDate") && null != params.get("endDate")){
				if(StringUtils.isNotBlank(params.get("endDate").toString())){
					excelFileName="商业收费情况报表("+params.get("startDate").toString()+"~"+params.get("endDate").toString()+")";
				}else {
					excelFileName="商业收费情况报表("+params.get("startDate").toString()+")";
				}
			}else{
				excelFileName="商业收费情况报表";
			}
			//设置工作表名称
			WritableSheet wSheet = wbook.createSheet(excelFileName, 0);
			//冻结第几行第几列
			wSheet.getSettings().setHorizontalFreeze(2); //冻结列
			wSheet.getSettings().setVerticalFreeze(3); //冻结行
			wSheet.getSettings().setProtected(true);  //设置是否受保护
			this.initChargeReportSheet(wSheet,params);
		}
	}
	
	/**
	 * 商业收费情况报表-导出模板
	 * @param wsheet
	 * @throws Exception
	 */
	private void initChargeReportSheet(WritableSheet wsheet,Map<String, Object> params) throws Exception {
		WritableFont font_Bold_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Bold_11.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Bold_11_1 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		WritableFont font_Bold_18 = new WritableFont(WritableFont.createFont("Times New Roman"), 18);
		font_Bold_18.setBoldStyle(WritableFont.BOLD);
		
		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_18);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_11);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2.setBorder(Border.ALL, BorderLineStyle.THIN);//边框为实体线
		format_head2.setWrap(true);//自动换行
		
		// 二级标题样式（不加粗）
		WritableCellFormat format_head2_1 = new WritableCellFormat(font_Bold_11_1);
		format_head2_1.setAlignment(Alignment.CENTRE);
		format_head2_1.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2_1.setBorder(Border.ALL, BorderLineStyle.THIN); 
		format_head2_1.setWrap(true); 
		
		// 数字输入框（加锁-灰色）
		WritableCellFormat format_lock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_lock.setLocked(true); // 加锁
		format_lock.setAlignment(Alignment.RIGHT);
		format_lock.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 数字输入框(不加锁-黄色)
		WritableCellFormat format_unlock_1 = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock_1.setLocked(false); // 不加锁
		format_unlock_1.setAlignment(Alignment.RIGHT);
		format_unlock_1.setBackground(Colour.YELLOW);
		format_unlock_1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 普通框样式(加锁)
		WritableCellFormat format_label = new WritableCellFormat(font_Bold_11_1);
		format_label.setLocked(true); // 加锁
		format_label.setAlignment(Alignment.CENTRE);
		format_label.setBorder(Border.ALL, BorderLineStyle.THIN);
		format_label.setWrap(true);
		
		//-----------------------------------------------------------//
		String firstName = null;
		int monthSpace = 1;
		String startDate = "";
		String endDate = "";
		if(null != params.get("startDate")){
			startDate = params.get("startDate").toString();
		}
		if(null != params.get("endDate")){
			endDate = params.get("endDate").toString();
		}
		if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
			firstName="商业收费情况报表("+startDate+"~"+endDate+")";
			monthSpace = DateUtil.getMonthCountStr(startDate+"-01", endDate+"-01")+1;
		}else if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endDate)){
			firstName="商业收费情况报表("+startDate+")";
		}else{
			firstName="商业收费情况报表";
		}
		//添加第一行数据
		wsheet.addCell(new Label(0, 0, firstName, format_head1));
		int firstColCount = (monthSpace*4)+1;//第一行合并列数
		wsheet.mergeCells(0, 0, firstColCount, 0); //第一行合并
		
		//添加第二行列头数据
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.addCell(new Label(1, 1, "项目", format_head2));
		//合并列：参数格式（开始列，开始行，结束列，结束行） 
		wsheet.mergeCells(1, 1, 1, 2);
		
		int stratMergeCol = 2;
		int endMergeCol = 5;
		if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
			//按时间段导出
			int e1 = Integer.valueOf(startDate.substring(0, startDate.indexOf("-")));
			int e2 = Integer.valueOf(startDate.substring(startDate.indexOf("-") + 1, startDate.length()));
			int s1 = Integer.valueOf(endDate.substring(0, endDate.indexOf("-")));
			int s2 = Integer.valueOf(endDate.substring(endDate.indexOf("-") + 1, endDate.length()));
			String colName = "";
			for (int i = 0; i < monthSpace; i++) {
				String e2_rs = String.valueOf(e2);
				if (e2_rs.length() == 1) {
					e2_rs = "0" + e2_rs;
				}
				colName = String.valueOf(e1) + "-" + e2_rs;
				wsheet.addCell(new Label(stratMergeCol, 1, colName+"(能耗)", format_head2));
				wsheet.mergeCells(stratMergeCol, 1, endMergeCol, 1);
				stratMergeCol = stratMergeCol+4;
				endMergeCol = endMergeCol+4;
				if (e1 == s1 && e2 == s2) {
					break;
				} else if (e1 == s1) {
					if (e2 == s2) {
						break;
					} else {
						e2++;
					}
				} else if (e1 < s1) {
					if (e2 == 12) {
						e1++;
						e2 = 1;
					} else {
						e2++;
					}
				}
			}
		}else if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endDate)){
			//按月导出
			wsheet.addCell(new Label(stratMergeCol, 1, startDate+"(能耗)", format_head2));
			wsheet.mergeCells(stratMergeCol, 1, endMergeCol, 1);
			endMergeCol = endMergeCol+4;
		}
		
		//添加第三行列头数据
		wsheet.addCell(new Label(0, 2, "", format_head2));
		int c = 2;
		for (int i = 2; i < (monthSpace+2); i++) {
			wsheet.addCell(new Label(c, 2, "累计应收额", format_head2));
			wsheet.addCell(new Label((c+1), 2, "累计实收额", format_head2));
			wsheet.addCell(new Label((c+2), 2, "本月应收额", format_head2));
			wsheet.addCell(new Label((c+3), 2, "本月实收额", format_head2));
			c = (c+3)+1;
		}
		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 20);
		for (int i = 2; i <= (endMergeCol-4); i++) {
			wsheet.setColumnView(i, 20);
		}
		//设置行高
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 600);
		
		// 得到所有项目公司
		List<BisProject> bisProjectList = this.getProjectListCon();
		int row = 3;
		for (int i = 0; i < bisProjectList.size(); i++) {
			BisProject tmpBisProject = bisProjectList.get(i);
			if(tmpBisProject != null){
				String hiddenIds = tmpBisProject.getBisProjectId();
				wsheet.addCell(new Label(0, row, hiddenIds, format_label)); //影藏列（项目ID）
				wsheet.addCell(new Label(1, row, tmpBisProject.getCity(), format_label));
				int col = 1;
				for (int j = 0; j < (monthSpace*4); j++) {
					wsheet.addCell(new Label(++col, row, "", format_unlock_1));
				}
				wsheet.setRowView(row++, 600);
			}
		}
		
		//合计行
		int rowCount = wsheet.getRows(); //总行数
		wsheet.addCell(new Label(0, rowCount, "", format_unlock_1));
		wsheet.addCell(new Label(1, rowCount, "合计", format_label));
		for (int i = 2; i <= (endMergeCol-4); i++) {
			Formula formula = getFormula("4", i, rowCount, format_lock);
			if(formula != null){
				wsheet.addCell(formula);
			}
		}
		wsheet.setRowView(rowCount, 600);
	}
	
	/**
	 * 销售情况报表-导出模板
	 * @param wsheet
	 * @throws Exception
	 */
	private void initSellReportSheet(WritableSheet wsheet,String year,String month) throws Exception {
		WritableFont font_Bold_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Bold_11.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Bold_11_1 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		WritableFont font_Bold_18 = new WritableFont(WritableFont.createFont("Times New Roman"), 18);
		font_Bold_18.setBoldStyle(WritableFont.BOLD);
		
		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_18);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_11);
		format_head2.setAlignment(Alignment.CENTRE);
		//format_head2.setBackground(Colour.GRAY_50);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2.setBorder(Border.ALL, BorderLineStyle.THIN);//边框为实体线
		format_head2.setWrap(true);//自动换行
		
		// 二级标题样式（不加粗）
		WritableCellFormat format_head2_1 = new WritableCellFormat(font_Bold_11_1);
		format_head2_1.setAlignment(Alignment.CENTRE);
		format_head2_1.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2_1.setBorder(Border.ALL, BorderLineStyle.THIN); 
		format_head2_1.setWrap(true); 
		
		// 数字输入框（加锁-灰色）
		WritableCellFormat format_lock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_lock.setLocked(true); // 加锁
		format_lock.setAlignment(Alignment.RIGHT);
		//format_lock.setBackground(Colour.YELLOW);
		format_lock.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 数字输入框(不加锁-黄色)
		WritableCellFormat format_unlock_1 = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock_1.setLocked(false); // 不加锁
		format_unlock_1.setAlignment(Alignment.RIGHT);
		format_unlock_1.setBackground(Colour.YELLOW);
		format_unlock_1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 普通框样式(加锁)
		WritableCellFormat format_label = new WritableCellFormat(font_Bold_11_1);
		format_label.setLocked(true); // 加锁
		format_label.setAlignment(Alignment.CENTRE);
		format_label.setBorder(Border.ALL, BorderLineStyle.THIN);
		format_label.setWrap(true); 
		
		
		//添加第一行数据
		wsheet.addCell(new Label(0, 0, "销售情况汇报("+year+"年"+month+"月)", format_head1));
		wsheet.mergeCells(0, 0, 19, 0); //第一行合并
		//添加第二行列头数据
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.addCell(new Label(1, 1, "项目", format_head2));
		wsheet.addCell(new Label(2, 1, "月度销售(万元)", format_head2));
		wsheet.addCell(new Label(6, 1, "年度销售(万元)", format_head2));
		wsheet.addCell(new Label(10, 1, "剩余总量(万元)", format_head2));
		wsheet.addCell(new Label(16, 1, "认购未签约", format_head2));
		wsheet.addCell(new Label(18, 1, "未回款(万元)", format_head2));
		//合并列：参数格式（开始列，开始行，结束列，结束行） 
		wsheet.mergeCells(1, 1, 1, 3);
		wsheet.mergeCells(2, 1, 5, 1);
		wsheet.mergeCells(6, 1, 9, 1);
		wsheet.mergeCells(10, 1, 15, 1);
		wsheet.mergeCells(16, 1, 17, 2);
		wsheet.mergeCells(18, 1, 19, 1);
		//添加第三行列头数据
		wsheet.addCell(new Label(0, 2, "", format_head2));
		wsheet.addCell(new Label(2, 2, "签约金额", format_head2));
		wsheet.addCell(new Label(4, 2, "回款金额", format_head2));
		wsheet.addCell(new Label(6, 2, "签约金额", format_head2));
		wsheet.addCell(new Label(8, 2, "回款金额", format_head2));
		wsheet.addCell(new Label(10, 2, "待售", format_head2));
		wsheet.addCell(new Label(13, 2, "销控", format_head2));
		wsheet.addCell(new Label(18, 2, "按揭欠款", format_head2));
		wsheet.addCell(new Label(19, 2, "其他欠款", format_head2));
		//参数格式（开始列，开始行，结束列，结束行） 
		wsheet.mergeCells(2, 2, 3, 2);
		wsheet.mergeCells(4, 2, 5, 2);
		wsheet.mergeCells(6, 2, 7, 2);
		wsheet.mergeCells(8, 2, 9, 2);
		wsheet.mergeCells(10, 2, 12, 2);
		wsheet.mergeCells(13, 2, 15, 2);
		//添加第四行列头数据
		wsheet.addCell(new Label(0, 3, "", format_head2_1));
		wsheet.addCell(new Label(1, 3, "", format_head2_1));
		wsheet.addCell(new Label(2, 3, "计划", format_head2_1));
		wsheet.addCell(new Label(3, 3, "实际", format_head2_1));
		wsheet.addCell(new Label(4, 3, "计划", format_head2_1));
		wsheet.addCell(new Label(5, 3, "实际", format_head2_1));
		wsheet.addCell(new Label(6, 3, "计划", format_head2_1));
		wsheet.addCell(new Label(7, 3, "实际", format_head2_1));
		wsheet.addCell(new Label(8, 3, "计划", format_head2_1));
		wsheet.addCell(new Label(9, 3, "实际", format_head2_1));
		wsheet.addCell(new Label(10, 3, "金额", format_head2_1));
		wsheet.addCell(new Label(11, 3, "面积", format_head2_1));
		wsheet.addCell(new Label(12, 3, "套数", format_head2_1));
		wsheet.addCell(new Label(13, 3, "金额", format_head2_1));
		wsheet.addCell(new Label(14, 3, "面积", format_head2_1));
		wsheet.addCell(new Label(15, 3, "套数", format_head2_1));
		wsheet.addCell(new Label(16, 3, "金额", format_head2_1));
		wsheet.addCell(new Label(17, 3, "套数", format_head2_1));
		wsheet.addCell(new Label(18, 3, "", format_head2_1));
		wsheet.addCell(new Label(19, 3, "", format_head2_1));
		
		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 20);
		for (int i = 2; i <= 19; i++) {
			wsheet.setColumnView(i, 15);
		}
		//设置行高
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 600);
		wsheet.setRowView(3, 600);
		
		// 得到所有项目公司
		List<BisProject> bisProjectList = this.getProjectList();
		int row = 4;
		for (int i = 0; i < bisProjectList.size(); i++) {
			BisProject tmpBisProject = bisProjectList.get(i);
			if(tmpBisProject != null){
				String hiddenIds = tmpBisProject.getBisProjectId();
				wsheet.addCell(new Label(0, row, hiddenIds, format_label)); //影藏列（项目ID）
				wsheet.addCell(new Label(1, row, tmpBisProject.getCity(), format_label));
				int col = 1;
				for (int j = 0; j < 18; j++) {
					wsheet.addCell(new Label(++col, row, "", format_unlock_1));
				}
				wsheet.setRowView(row++, 600);
			}
		}
		
		//合计行
		int rowCount = wsheet.getRows(); //总行数
		wsheet.addCell(new Label(0, rowCount, "", format_unlock_1));
		wsheet.addCell(new Label(1, rowCount, "合计", format_label));
		wsheet.addCell(getFormula("5", 2, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 3, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 4, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 5, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 6, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 7, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 8, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 9, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 10, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 11, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 12, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 13, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 14, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 15, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 16, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 17, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 18, rowCount, format_lock));
		wsheet.addCell(getFormula("5", 19, rowCount, format_lock));
		wsheet.setRowView(rowCount, 600);
	}
	
	/**
	 * 得到项目公司
	 * @return
	 * @throws Exception
	 */
	private List<BisProject> getProjectList() throws Exception{
		List<BisProject> list = new ArrayList<BisProject>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisProject t where t.sellCondition =:sellCondition order by t.city asc");
		params.put("sellCondition", "1");
		list = bisProjectDao.find(hql.toString(), params);
		return list;
	}
	/**
	 * 得到符合条的项目公司
	 * @return
	 * @throws Exception
	 */
	private List<BisProject> getProjectListCon() throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisProject where rentalCollectionRate = :rentalCollectionRate and openDate is not null ");
		hql.append(" order by city asc sequenceNo desc ");
		param.put("rentalCollectionRate", "1");
		List<BisProject> bisProjects = bisProjectDao.find(hql.toString(),param);
		return bisProjects;
	}
	
	
	/**
	 * 构建excel计算总和的公式
	 * 
	 * @param col 列
	 * @param rowCount 最后行
	 * @param cellFormat 格式
	 * @return
	 * @throws Exception
	 */
	private Formula getFormula(String startCol,int col, int rowCount,WritableCellFormat cellFormat) throws Exception{
		String colName = BisConstants.letters.get(col + 1);
		if(StringUtils.isNotBlank(colName)){ 
			//计算某一列总和公式(该excel从第5列开始)   SUM(A1:A10)
			String formulaStr = "SUM("+colName+startCol+":"+colName+rowCount+")";
			Formula f = new Formula(col, rowCount, formulaStr, cellFormat);
			return f;
		}
		return null;
	}
}

