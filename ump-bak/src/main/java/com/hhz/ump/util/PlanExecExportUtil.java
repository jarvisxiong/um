package com.hhz.ump.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.CellType;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.plan.ExecPlanNode;
import com.hhz.ump.entity.plan.PlanExecPlanDetail;
import com.hhz.ump.entity.plan.PlanExecutionPlan;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.entity.planold.PlanExecPlanDetailOld;
import com.hhz.ump.entity.planold.PlanExecutionPlanOld;
import com.hhz.ump.entity.planold.PlanProjectNodeRelOld;
import com.hhz.ump.web.vo.ExecStatVO;

public class PlanExecExportUtil {

	
	/**
	 * 按项目,累加数据,显示为行
	 * @param map  {projectName: vo}
	 * @param vo
	 */
	private void addRowExecVo(Map<String,ExecStatVO> map, ExecStatVO vo){
		if(vo == null)
			return;
		String projectName = vo.getProjectName();
		if(StringUtils.isBlank(projectName))
			return;
		projectName = projectName.trim();

		if(map.keySet().contains(projectName)){
			ExecStatVO tVo = map.get(projectName);
			tVo.setPc(BigDecimal.valueOf(tVo.getPc().doubleValue()+vo.getPc().doubleValue()));
			tVo.setPd(BigDecimal.valueOf(tVo.getPd().doubleValue()+vo.getPd().doubleValue()));
			tVo.setCc(BigDecimal.valueOf(tVo.getCc().doubleValue()+vo.getCc().doubleValue()));
			tVo.setCd(BigDecimal.valueOf(tVo.getCd().doubleValue()+vo.getCd().doubleValue()));
			tVo.setHc(BigDecimal.valueOf(tVo.getHc().doubleValue()+vo.getHc().doubleValue()));
			tVo.setMc(BigDecimal.valueOf(tVo.getMc().doubleValue()+vo.getMc().doubleValue()));
			tVo.setRc(BigDecimal.valueOf(tVo.getRc().doubleValue()+vo.getRc().doubleValue()));
		}else{
			ExecStatVO tVo = new ExecStatVO();
			tVo.setPc(vo.getPc());
			tVo.setPd(vo.getPd());
			tVo.setCc(vo.getCd());
			tVo.setCd(vo.getCd());
			tVo.setHc(vo.getHc());
			tVo.setMc(vo.getMc());
			tVo.setRc(vo.getRc());
			map.put(projectName, tVo);
		}
	}
	/**
	 * 按中心,累加数据,显示为列
	 * @param map {centerName: vo}
	 * @param vo
	 */
	private void addColExecVo(Map<String,ExecStatVO> map, ExecStatVO vo){
		if(vo == null)
			return;
		String centerName  = vo.getResOrgName();
		if(StringUtils.isBlank(centerName))
			return;
		centerName = centerName.trim();
		
		if(map.keySet().contains(centerName)){
			ExecStatVO tVo = map.get(centerName);
			tVo.setPc(BigDecimal.valueOf(tVo.getPc().doubleValue()+vo.getPc().doubleValue()));
			tVo.setPd(BigDecimal.valueOf(tVo.getPd().doubleValue()+vo.getPd().doubleValue()));
			tVo.setCc(BigDecimal.valueOf(tVo.getCc().doubleValue()+vo.getCc().doubleValue()));
			tVo.setCd(BigDecimal.valueOf(tVo.getCd().doubleValue()+vo.getCd().doubleValue()));
			tVo.setHc(BigDecimal.valueOf(tVo.getHc().doubleValue()+vo.getHc().doubleValue()));
			tVo.setMc(BigDecimal.valueOf(tVo.getMc().doubleValue()+vo.getMc().doubleValue()));
			tVo.setRc(BigDecimal.valueOf(tVo.getRc().doubleValue()+vo.getRc().doubleValue()));
		}else{
			ExecStatVO tVo = new ExecStatVO();
			tVo.setPc(vo.getPc());
			tVo.setPd(vo.getPd());
			tVo.setCc(vo.getCd());
			tVo.setCd(vo.getCd());
			tVo.setHc(vo.getHc());
			tVo.setMc(vo.getMc());
			tVo.setRc(vo.getRc());
			map.put(centerName, tVo);
		}
	}
	/**
	 * 按年、月,导出项目执行情况统计
	 * 
	 * @param path
	 *            文件路径
	 * @param sheetName
	 *            工作表名称
	 * @param dataTitles
	 *            数据标题
	 */
	public InputStream createExcelPlanFile(List<Object> list,String year,String month) {

		
		String sheetName = year+"年"+month+"月各地产公司按执行计划节点对各集团中心完成节点率评判";
		try {
			// 创建 HSSFWorkbook 对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(sheetName);
			// 将第一个编码设置成 UTF-16 避免中文乱码
			workbook.setSheetName(0, sheetName);
			//## 冻结首3行,1列 ##//   
			sheet.createFreezePane(1, 3);   
			

	         HSSFPatriarch patr = sheet.createDrawingPatriarch();   

			// 设置表格默认列宽度为16个字节
			sheet.setDefaultColumnWidth((short) 8);
			sheet.setColumnWidth(0, (int)(35.7*120));

			// -----------产生表格抬头标题行 开始-----------
			HSSFCellStyle headStyle = workbook.createCellStyle();
			// 设置抬头背景颜色和相关样式
			headStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
			headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			headStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			headStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
			headStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
			headStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// 设置字体样式 抬头字体
			HSSFFont font = workbook.createFont();
			// 设置字体颜色
			font.setColor(HSSFColor.WHITE.index);
			// 设置字体大小
			font.setFontHeightInPoints((short) 14);
			// 设置字体加粗
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			// 把字体应用
			headStyle.setFont(font);
			// -----------产生表格抬头标题行 结束-----------

			// ----------设置内容表格样式行 开始-----------
			HSSFCellStyle contentStyle = workbook.createCellStyle();
			contentStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
			contentStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
			contentStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
			contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 生成另一个字体
			HSSFFont font2 = workbook.createFont();
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			// 把字体应用到当前的样式
			contentStyle.setFont(font2);
			// ----------设置内容表格样式行 结束-----------
			

			// 数据映射 {projectCd_orgCd:ExecStatVO}
			Map<String,ExecStatVO> dataMap = new HashMap<String, ExecStatVO>();
			// 机构映射{orgName:orgCd}
			Map<String,String> orgMap = new HashMap<String,String>();
			// 机构集合{projectCd}
			List<String> projectCdList = new ArrayList<String>();
			// 机构集合{projectName}
			List<String> projectNameList = new ArrayList<String>();

			// 按项目累计
			Map<String,ExecStatVO> rowCountMap = new HashMap<String,ExecStatVO>();
			// 按中心累计
			Map<String,ExecStatVO> colCountMap = new HashMap<String,ExecStatVO>();
			
			
			ExecStatVO vo = null;
			if(list != null){
				for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
					Object[] arr = (Object[]) iterator.next();
					vo = new ExecStatVO();
					vo.setPlanMainId(getVal((String)arr[0]));
					vo.setProjectCd(getVal(((String)arr[1])));
					vo.setProjectName(getVal(((String)arr[2])));
					vo.setResOrgCd(getVal((String)arr[3]));
					vo.setResOrgName(getVal((String)arr[4]));
					vo.setPc((BigDecimal)arr[5]);
					vo.setPd((BigDecimal)arr[6]);
					vo.setCc((BigDecimal)arr[7]);
					vo.setCd((BigDecimal)arr[8]);
					vo.setMc((BigDecimal)arr[9]);
					vo.setHc((BigDecimal)arr[10]);
					vo.setNc((BigDecimal)arr[11]);
					vo.setRc((BigDecimal)arr[12]);
					vo.setRate((BigDecimal)arr[13]); 
					
					if(StringUtils.isBlank(vo.getResOrgName())){
						continue;
					}
					//加入项目
					if(!projectCdList.contains(vo.getProjectCd())){
						projectCdList.add(vo.getProjectCd());
						projectNameList.add(vo.getProjectName());
					}
					dataMap.put(vo.getProjectCd()+"_"+vo.getResOrgCd(), vo);
					orgMap.put(vo.getResOrgName(),vo.getResOrgCd());
					
					addRowExecVo(rowCountMap, vo);
					addColExecVo(colCountMap, vo);
				}
			}
			
//			InputStream in = ClassLoader.getSystemResourceAsStream("centerseq.properties");
//			Properties p = new Properties(); 
//			p.load(in);
//			String centerseq = p.getProperty("centerseq","");
//			String[] arrOrgs = StringUtils.split(centerseq,",");
//			if(arrOrgs.length == 0){
			String[] centerOrgs = new String[orgMap.keySet().size()];
			int keyIndex=0;
			for (Iterator iterator = orgMap.keySet().iterator(); iterator.hasNext();) {
				centerOrgs[keyIndex++] = (String)iterator.next();
			}
//			}
			
			// ----------生成表的抬头----------开始
			
			HSSFCellStyle tCellStyle = workbook.createCellStyle();
			tCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			tCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
	        // 用于格式化单元格的数据 
	        HSSFDataFormat format = workbook.createDataFormat();
			
			HSSFRow row0 = sheet.createRow(0);
			row0.setHeightInPoints((float)30);
			HSSFCell cell = null;
			cell = row0.createCell(0);
			cell.setCellValue(sheetName);
			cell.setCellStyle(tCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,(centerOrgs.length*3)));
			sheet.addMergedRegion(new CellRangeAddress(0,0,(centerOrgs.length*3+1),(centerOrgs.length*3+3)));
			
			HSSFRow row1 = sheet.createRow(1);
			cell = row1.createCell(0);
			cell.setCellValue("项目名称");
			cell.setCellStyle(tCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(1,2,0,0));

			HSSFRow row2 = sheet.createRow(2);
			HSSFRow tRow = null;
			HSSFCellStyle tCellStyleR = workbook.createCellStyle();
			tCellStyleR.setAlignment(CellStyle.ALIGN_RIGHT);

			HSSFCellStyle tCellStyleRate = workbook.createCellStyle();
			tCellStyleRate.setAlignment(CellStyle.ALIGN_RIGHT);
			tCellStyleRate.setDataFormat(format.getFormat( "0.00%" ));
			
			String orgName = null;
			String orgCd = null;
			ExecStatVO tVo = null;
			String projectCd = null;
			String projectName = null;
			for (int i = 0; i < centerOrgs.length; i++) {
				
				orgName = centerOrgs[i];
				orgCd = orgMap.get(orgName);
				
				//中心
				cell = row1.createCell(3*i+1);
				cell.setCellStyle(tCellStyle);
				cell.setCellValue(centerOrgs[i]);
				
				//3列标题
				cell = row2.createCell(3*i+1);
				cell.setCellStyle(tCellStyle);
				cell.setCellValue("应完成");

				cell = row2.createCell(3*i+2);
				cell.setCellStyle(tCellStyle);
				cell.setCellValue("已完成");

				cell = row2.createCell(3*i+3);
				cell.setCellStyle(tCellStyle);
				cell.setCellValue("完成率");
				
				sheet.addMergedRegion(new CellRangeAddress(1,1,(3*i+1),(3*i+3)));
			} 
			// ----------生成表抬头----------结束

			cell = row1.createCell(3*centerOrgs.length+1);
			cell.setCellStyle(tCellStyle);
			cell.setCellValue("按项目合计");
			sheet.addMergedRegion(new CellRangeAddress(1,1,3*centerOrgs.length+1,3*centerOrgs.length+3));

			//3列标题
			cell = row2.createCell(3*centerOrgs.length+1);
			cell.setCellStyle(tCellStyle);
			cell.setCellValue("应完成");

			cell = row2.createCell(3*centerOrgs.length+2);
			cell.setCellStyle(tCellStyle);
			cell.setCellValue("已完成");

			cell = row2.createCell(3*centerOrgs.length+3);
			cell.setCellStyle(tCellStyle);
			cell.setCellValue("完成率");
			
			 
			// ----------生成表数据----------开始
			for(int i=0; i<projectCdList.size();i++){
				
				//第一列:项目名称
				projectCd = projectCdList.get(i);
				projectName = projectNameList.get(i);
				tRow = sheet.createRow(3+i);
				cell = tRow.createCell(0);
				cell.setCellValue(projectName);

				//各个中心对应的数据(3列)
				for (int j = 0; j < centerOrgs.length; j++) {

					double pc = 0;
					double pd = 0;
					double cc = 0;
					double cd = 0;
					double hc = 0;
					double mc = 0;
					double rc = 0;
					
					orgName = centerOrgs[j];
					orgCd = orgMap.get(orgName);
					tVo = dataMap.get(projectCd+"_"+orgCd);
					 
					if(tVo != null){
						pc = tVo.getPc().doubleValue();
						pd = tVo.getPd().doubleValue();
						cc = tVo.getCc().doubleValue();
						cd = tVo.getCd().doubleValue();
						hc = tVo.getHc().doubleValue();
						mc = tVo.getMc().doubleValue();
						rc = tVo.getRc().doubleValue();
					}
					
					//应完成
					cell = tRow.createCell(3*j+1);
					cell.setCellStyle(tCellStyleR);
					if(tVo != null){
						if(mc == 0){
							cell.setCellValue(0);
						}else{
							cell.setCellValue(mc);
						}
					}else{
						cell.setCellValue(0);
					}
					
					//已完成
					cell = tRow.createCell(3*j+2);
					cell.setCellStyle(tCellStyleR);
					if(tVo != null){
						cell.setCellValue(hc);
					}else{
						cell.setCellValue(0);
					}
	
					//完成率
					cell = tRow.createCell(3*j+3);
					
					cell.setCellStyle(tCellStyleRate);
					if(tVo != null){
						if(mc == 0){
							cell.setCellValue(0);
						}else{
							cell.setCellValue(rc / mc);
						}
					}else{
						cell.setCellValue(0);
					}
					
					
					// 添加单元格注释   
					int row = tRow.getRowNum()+1;
					int col = (3*j+3+1);
			         // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
			         HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
			         // 定义注释的大小和位置,详见文档   
			         HSSFComment comment = patr.createCellComment(t);   
			         // 设置注释内容   
			         comment.setString( new HSSFRichTextString( "[非关键节点]已完成: "+ (int)pc +" ,延期:" + (int)pd +" \n[   关键节点]已完成: "+ (int)cc+" ,延期:" + (int)cd));   
			         // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
			         //comment.setAuthor( " PD" ); 
			         comment.setRow(3);
			         comment.setLineWidth(250);
			         cell.setCellComment(comment);
				}

				//横向合计
				tVo = rowCountMap.get(projectName);
				if(tVo != null){

					cell = tRow.createCell(3*(centerOrgs.length)+1);
					cell.setCellStyle(tCellStyleR);
					cell.setCellValue(tVo.getMc().doubleValue());
					
					cell = tRow.createCell(3*(centerOrgs.length)+2);
					cell.setCellStyle(tCellStyleR);
					cell.setCellValue(tVo.getHc().doubleValue());

					cell = tRow.createCell(3*(centerOrgs.length)+3);
					cell.setCellStyle(tCellStyleRate);
					if(tVo.getMc().doubleValue() == 0){
						cell.setCellValue(0);
					}else{
						cell.setCellValue(tVo.getRc().doubleValue() / tVo.getMc().doubleValue());
					}
					 // 添加单元格注释   
					int row = tRow.getRowNum()+1;
					int col = (3*centerOrgs.length+3+1);
			         // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
			         HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
			         // 定义注释的大小和位置,详见文档   
			         HSSFComment comment = patr.createCellComment(t);   
			         // 设置注释内容   
			         comment.setString( new HSSFRichTextString( "[非关键节点]已完成: "+ (int)tVo.getPc().doubleValue() +" ,延期:" + (int)tVo.getPd().doubleValue() +" \n[   关键节点]已完成: "+ (int)tVo.getCc().doubleValue()+" ,延期:" + (int)tVo.getCd().doubleValue()));   
			         // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
			         //comment.setAuthor( " PD" ); 
			         comment.setRow(3);
			         comment.setLineWidth(250);
			         cell.setCellComment(comment);
				}
			}
			// ----------生成表数据----------结束
			
			

			// ----------生成合计数据----------开始
			tRow = sheet.createRow(3+projectNameList.size());
			cell = tRow.createCell(0);
			cell.setCellValue("按中心合计");
			for (int j = 0; j < centerOrgs.length; j++) {
				orgName = centerOrgs[j];
				orgCd = orgMap.get(orgName);
				tVo = colCountMap.get(orgName);
				

				double pc = 0;
				double pd = 0;
				double cc = 0;
				double cd = 0;
				double hc = 0;
				double mc = 0;
				double rc = 0;
				 
				if(tVo != null){
					pc = tVo.getPc().doubleValue();
					pd = tVo.getPd().doubleValue();
					cc = tVo.getCc().doubleValue();
					cd = tVo.getCd().doubleValue();
					hc = tVo.getHc().doubleValue();
					mc = tVo.getMc().doubleValue();
					rc = tVo.getRc().doubleValue();
				}
				
				//应完成
				cell = tRow.createCell(3*j+1);
				cell.setCellStyle(tCellStyleR);
				if(tVo != null){
					cell.setCellValue(mc);
				}else{
					cell.setCellValue(0);
				}
				
				//已完成
				cell = tRow.createCell(3*j+2);
				cell.setCellStyle(tCellStyleR);
				if(tVo != null){
					cell.setCellValue(hc);
				}else{
					cell.setCellValue(0);
				}

				//完成率
				cell = tRow.createCell(3*j+3);
				
				cell.setCellStyle(tCellStyleRate);
				if(tVo != null){
					if(tVo.getMc().doubleValue() == 0){
						cell.setCellValue(0);
					}else{
						cell.setCellValue(rc /mc);
					}
				}else{
					cell.setCellValue(0);
				}

				 // 添加单元格注释   
				int row = tRow.getRowNum()+1;
				int col = (3*j+2);
				// 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
				HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
				// 定义注释的大小和位置,详见文档   
				HSSFComment comment = patr.createCellComment(t);   
				// 设置注释内容   
				comment.setString( new HSSFRichTextString( "[非关键节点]已完成: "+ (int)pc +" ,延期:" + (int)pd +" \n[   关键节点]已完成: "+ (int)cc+" ,延期:" + (int)cd));   
				// 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
				//comment.setAuthor( " PD" ); 
				comment.setRow(3);
				comment.setLineWidth(250);
				cell.setCellComment(comment);
			}
			// ----------生成合计数据----------结束
			 
			// 创建一个新的字节数组输出流
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			try {
				// 将封装的excel写入output流
				workbook.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 创建一个新分配的byte字节数组。
			byte[] content = os.toByteArray();
			// 创建一个新的字节数组输出流。
			InputStream inputstr = new ByteArrayInputStream(content);

			// 返回流
			return inputstr;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 初始化表格属性
	 * 
	 * @param sheet
	 */
	public void initialSheetSetting(HSSFSheet sheet) {
		try {
			// sheet.getSettings().setProtected(true); //设置xls的保护，单元格为只读的
			sheet.setDefaultColumnWidth(10); // 设置列的默认宽度
			// sheet.setRowView(2,false);//行高自动扩展
			// setRowView(int row, int height);--行高
			// setColumnView(int col,int width); --列宽
			// sheet.setColumnView(0, 20);// 设置第一列宽度
			// sheet.getSettings().setVerticalFreeze(3);
			// sheet.getSettings().setHorizontalFreeze(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入公式
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param formula
	 * @param format
	 */
	public void insertFormula(WritableSheet sheet, Integer col, Integer row, String formula, WritableCellFormat format) {
		try {
			Formula f = new Formula(col, row, formula, format);
			sheet.addCell(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一行数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param row
	 *            行号
	 * @param content
	 *            内容
	 * @param format
	 *            风格
	 */
	public void insertRowData(WritableSheet sheet, Integer row, String[] dataArr, WritableCellFormat format) {
		try {
			Label label;
			for (int i = 0; i < dataArr.length; i++) {
				label = new Label(i, row, dataArr[i], format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入单元格数据
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param data
	 */
	public void insertOneCellData(WritableSheet sheet, Integer col, Integer row, Object data, WritableCellFormat format) {
		try {
			if (data instanceof Double) {
				jxl.write.Number labelNF = new jxl.write.Number(col, row, (Double) data, format);
				sheet.addCell(labelNF);
			} else if (data instanceof Boolean) {
				jxl.write.Boolean labelB = new jxl.write.Boolean(col, row, (Boolean) data, format);
				sheet.addCell(labelB);
			} else if (data instanceof Date) {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(col, row, (Date) data, format);
				sheet.addCell(labelDT);
				setCellComments(labelDT, "这是个创建表的日期说明！");
			} else {
				Label label = new Label(col, row, data.toString(), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 合并单元格，并插入数据
	 * 
	 * @param sheet
	 * @param col_start
	 * @param row_start
	 * @param col_end
	 * @param row_end
	 * @param data
	 * @param format
	 */
	public void mergeCellsAndInsertData(WritableSheet sheet, Integer col_start, Integer row_start, Integer col_end,
			Integer row_end, Object data, WritableCellFormat format) {
		try {
			sheet.mergeCells(col_start, row_start, col_end, row_end);// 左上角到右下角
			insertOneCellData(sheet, col_start, row_start, data, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给单元格加注释
	 * 
	 * @param label
	 * @param comments
	 */
	public void setCellComments(Object label, String comments) {
		WritableCellFeatures cellFeatures = new WritableCellFeatures();
		cellFeatures.setComment(comments);
		if (label instanceof jxl.write.Number) {
			jxl.write.Number num = (jxl.write.Number) label;
			num.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.Boolean) {
			jxl.write.Boolean bool = (jxl.write.Boolean) label;
			bool.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.DateTime) {
			jxl.write.DateTime dt = (jxl.write.DateTime) label;
			dt.setCellFeatures(cellFeatures);
		} else {
			Label _label = (Label) label;
			_label.setCellFeatures(cellFeatures);
		}
	}


	/**
	 * 得到数据表头格式
	 * 
	 * @return
	 */
	public WritableCellFormat getTitleCellFormat() {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD, false);// 最后一个为是否italic
			wf.setColour(Colour.RED);
			wcf = new WritableCellFormat(wf);
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 背景色
			wcf.setBackground(Colour.GREY_25_PERCENT);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	/**
	 * 得到数据格式
	 * 
	 * @return
	 */
	public WritableCellFormat getDataCellFormat(CellType type) {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			if (type == CellType.NUMBER || type == CellType.NUMBER_FORMULA) {// 数字
				NumberFormat nf = new NumberFormat("#.00");
				wcf = new WritableCellFormat(nf);
			} else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {// 日期
				jxl.write.DateFormat df = new jxl.write.DateFormat("yyyy-MM-dd hh:mm:ss");
				wcf = new jxl.write.WritableCellFormat(df);
			} else {
				WritableFont wf = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false);// 最后一个为是否italic
				wcf = new WritableCellFormat(wf);
			}
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.LEFT, BorderLineStyle.THIN);
			wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			wcf.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// 背景色
			wcf.setBackground(Colour.WHITE);
			wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	/**
	 * 打开文件看看
	 * 
	 * @param exePath
	 * @param filePath
	 */
	public void openExcel(String exePath, String filePath) {
		Runtime r = Runtime.getRuntime();
		String cmd[] = { exePath, filePath };
		try {
			r.exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取格式化后的值,默认空值
	 * @param val
	 * @return
	 */
	private static String getVal(String val) {
		return getVal(val, "");
	}

	/**
	 * 获取格式化后的值
	 * @param val  实际值
	 * @param defaultVal 若val为空,则返回默认值
	 * @return
	 */
	private static String getVal(String val, String defaultVal) {

		if (StringUtils.isBlank(val) && StringUtils.isNotBlank(defaultVal))
			return defaultVal;
		return val;
	}
	
	private int getIndex(int colNum){
		return (colNum-1);
	}

	/**
	 *  导出项目执行计划明细(新模块)
	 * @param sheetName
	 * @param planNodes
	 * @param plans
	 * @param planDetailMap
	 * @param statusMap
	 * @return
	 */
	public InputStream createExcelPlanDetailNew(String sheetName, List<ExecPlanNode> planNodes, List<ExecPlanLayout> plans, Map<String, ExecPlanDetail> planDetailMap, Map<String, String> statusMap,Map<String,String> nodeMap) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 将第一个编码设置成 UTF-16 避免中文乱码
		workbook.setSheetName(0, sheetName);
		//## 冻结首3行,1列 ##//   
		sheet.createFreezePane(2, 3);   
		
		// 设置表格默认列宽度为16个字节
		sheet.setDefaultColumnWidth((short) 8);
		sheet.setColumnWidth((short)0,(short)7000);
		sheet.setColumnWidth((short)1,(short)5000);

		// -----------产生表格抬头标题行 开始-----------
		HSSFCellStyle headStyle = workbook.createCellStyle();
		// 设置抬头背景颜色和相关样式
		headStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 设置字体样式 抬头字体
		HSSFFont font = workbook.createFont();
		// 设置字体颜色
		font.setColor(HSSFColor.WHITE.index);
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用
		headStyle.setFont(font);
		// -----------产生表格抬头标题行 结束-----------

		// ----------设置内容表格样式行 开始-----------
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		contentStyle.setFont(font2);
		// ----------设置内容表格样式行 结束-----------

		// ----------生成表的抬头----------开始
		
		HSSFCellStyle tCellStyle = workbook.createCellStyle();
		tCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle tCellStyleLeft = workbook.createCellStyle();
		tCellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tCellStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
        // 用于格式化单元格的数据 
        HSSFCellStyle cellStyleDate = workbook.createCellStyle();//建立新的cell样式    
        cellStyleDate.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));//设置cell样式为定制的日期格式    


        HSSFPatriarch patr = sheet.createDrawingPatriarch();   
        HSSFFont hfRed=workbook.createFont();
        hfRed.setColor((short)2); //1:白色,2:红色,3:绿色,4:蓝色,5:黄色,6:紫色
        
        int FIX_COLS = 3;
		
        //第一行
		HSSFRow row1 = sheet.createRow(getIndex(1));
		row1.setHeightInPoints((float)30);
		HSSFCell cell = null;
		cell = row1.createCell(getIndex(1));
		cell.setCellValue("项目 执行计划 节点");
		cell.setCellStyle(tCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(1),getIndex(2)));

		cell = row1.createCell(getIndex(3));
		cell.setCellValue("项目业态");
		cell.setCellStyle(tCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(3),getIndex(plans.size()*FIX_COLS+2)));
		
		//第二行
		HSSFRow row2 = sheet.createRow(getIndex(2));
		cell = row2.createCell(getIndex(1));
		cell.setCellValue("节点名称");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(1),getIndex(1)));
		 
		cell = row2.createCell(getIndex(2));
		cell.setCellValue("主责方");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(2),getIndex(2)));

		
		//第三行:开始时间、计划时间、当前状态
		HSSFRow row3 = sheet.createRow(getIndex(3));
		for(int i=0;i<plans.size();i++){
			//业态名称
			cell = row2.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue(plans.get(i).getExecutionPlanName());
			cell.setCellStyle(tCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(2),getIndex(3+FIX_COLS*i),getIndex(3+FIX_COLS*i+2)));
			
			//开始时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue("计划时间");
			cell.setCellStyle(tCellStyle);
			
			//计划时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i+1));
			cell.setCellValue("开始时间");
			cell.setCellStyle(tCellStyle);
			
			//当前状态
			cell = row3.createCell(getIndex(3+FIX_COLS*i+2));
			cell.setCellValue("状态");
			cell.setCellStyle(tCellStyle);
		}
		
		HSSFRow tmpRow = null;
		ExecPlanNode tmpRel = null;
		String tmpRelId = null;
		String tmpPlanCd = null;
		String tmpRelDisplayId = null;
		ExecPlanDetail tmpDetail = null;
		boolean tmpFlag = false;
		
		//明细数据
		for (int i=0; i<planNodes.size(); i++) {
			tmpRel = planNodes.get(i);
			tmpRow = sheet.createRow(getIndex(4+i));
			
			//节点名称
			cell = tmpRow.createCell(getIndex(1));
			cell.setCellValue(getSpanChars(String.valueOf(tmpRel.getPointLevel())) + tmpRel.getNodeName());
			cell.setCellStyle(tCellStyleLeft);
			 
			//主责方
			cell = tmpRow.createCell(getIndex(2));
			cell.setCellValue(tmpRel.getResOrgName());
			cell.setCellStyle(tCellStyleLeft);
			
			tmpRelId = tmpRel.getExecPlanNodeId();
			tmpRelDisplayId = tmpRel.getExecPlanNodeId();
			
			tmpFlag = false;
			for (int j=0;j< plans.size();j++) {

				if(StringUtils.isBlank(tmpRelDisplayId)){
					tmpDetail = planDetailMap.get(tmpRelId+"_"+plans.get(j).getExecutionPlanCd());
				}else{
					tmpDetail = planDetailMap.get(tmpRelDisplayId+"_"+plans.get(j).getExecutionPlanCd());
					tmpFlag = true;
				}
				if(tmpDetail != null){
					HSSFCellStyle titleStyle = workbook.createCellStyle();
					titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

					//开始时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j));
//					cell.setCellValue(tmpDetail.getScheduleStartDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleStartDate()));
					cell.setCellStyle(tCellStyle);
					
					//计划时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+1));
//					cell.setCellValue(tmpDetail.getScheduleEndDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleEndDate()));
					cell.setCellStyle(tCellStyle);
					
					//当前状态
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+2));
//					String colorStatus = getProcessDescNew(tmpDetail);//3=预完成, 2=完成, 1=预完成, 0=未完成
					String colorStatus = getPrintStatusCn(tmpDetail);
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+2));
					cell.setCellValue(colorStatus);
					if(colorStatus.equals("未确认")){
						titleStyle.setFillForegroundColor((short)2);//1:白色,2:红色,3:绿色,4:蓝色,5:黄色,6:紫色
					}else if(colorStatus.equals("进行中")){
						titleStyle.setFillForegroundColor((short)4);
					}else if(colorStatus.equals("延期")){
						titleStyle.setFillForegroundColor((short)2);
					}else if(colorStatus.equals("已完成")){
						titleStyle.setFillForegroundColor((short)3);
						titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
						titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					}else if(colorStatus.equals("未启用")){
						titleStyle.setFillForegroundColor((short)5);
					}
					cell.setCellStyle(titleStyle);
					
//					if(tmpFlag){
					// 添加单元格注释   
//					int row = tmpRow.getRowNum()+1;
//					int col = (3*j+3+1);
			         // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
//			         HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
			         // 定义注释的大小和位置,详见文档   
//			         HSSFComment comment = patr.createCellComment(t);   
			         // 设置注释内容   
//			         comment.setString( new HSSFRichTextString( "[关联节点显示]"+nodeMap.get(tmpRelDisplayId)));   
			         // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
			         //comment.setAuthor( " PD" ); 
//			         comment.setRow(3);
//			         comment.setLineWidth(250);
//			         cell.setCellComment(comment);
//					}
				}
			}
		}
		// ----------生成合计数据----------结束
		 
		// 创建一个新的字节数组输出流
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			// 将封装的excel写入output流
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建一个新分配的byte字节数组。
		byte[] content = os.toByteArray();
		// 创建一个新的字节数组输出流。
		InputStream inputstr = new ByteArrayInputStream(content);

		// 返回流
		return inputstr;
	}
	
public InputStream createExcelBiz4(String sheetName, List<ExecPlanNode> planNodes, List<ExecPlanLayout> plans, Map<String, ExecPlanDetail> planDetailMap, Map<String, String> statusMap,Map<String,String> nodeMap) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 将第一个编码设置成 UTF-16 避免中文乱码
		workbook.setSheetName(0, sheetName);
		//## 冻结首3行,1列 ##//   
		sheet.createFreezePane(2, 3);   
		
		// 设置表格默认列宽度为16个字节
		sheet.setDefaultColumnWidth((short) 8);
		sheet.setColumnWidth((short)0,(short)7000);
		sheet.setColumnWidth((short)1,(short)5000);

		// -----------产生表格抬头标题行 开始-----------
		HSSFCellStyle headStyle = workbook.createCellStyle();
		// 设置抬头背景颜色和相关样式
		headStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 设置字体样式 抬头字体
		HSSFFont font = workbook.createFont();
		// 设置字体颜色
		font.setColor(HSSFColor.WHITE.index);
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用
		headStyle.setFont(font);
		// -----------产生表格抬头标题行 结束-----------

		// ----------设置内容表格样式行 开始-----------
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		contentStyle.setFont(font2);
		// ----------设置内容表格样式行 结束-----------

		// ----------生成表的抬头----------开始
		
		HSSFCellStyle tCellStyle = workbook.createCellStyle();
		tCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle tCellStyleLeft = workbook.createCellStyle();
		tCellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tCellStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
        // 用于格式化单元格的数据 
        HSSFCellStyle cellStyleDate = workbook.createCellStyle();//建立新的cell样式    
        cellStyleDate.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));//设置cell样式为定制的日期格式    


        HSSFPatriarch patr = sheet.createDrawingPatriarch();   
        HSSFFont hfRed=workbook.createFont();
        hfRed.setColor((short)2); //1:白色,2:红色,3:绿色,4:蓝色,5:黄色,6:紫色
        
        int FIX_COLS = 3;
		
        //第一行
//		HSSFRow row1 = sheet.createRow(getIndex(1));
//		row1.setHeightInPoints((float)30);
		HSSFCell cell = null;
//		cell = row1.createCell(getIndex(1));
//		cell.setCellValue("项目 执行计划 节点");
//		cell.setCellStyle(tCellStyle);
//		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(1),getIndex(2)));
//
//		cell = row1.createCell(getIndex(3));
//		cell.setCellValue("项目业态");
//		cell.setCellStyle(tCellStyle);
//		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(3),getIndex(plans.size()*FIX_COLS+2)));
		
		//第二行
		HSSFRow row2 = sheet.createRow(getIndex(2));
		cell = row2.createCell(getIndex(1));
		cell.setCellValue("商业四级计划");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(2),getIndex(1),getIndex(1)));
		 
		cell = row2.createCell(getIndex(2));
		cell.setCellValue("开始时间");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(2),getIndex(2)));
		
		cell = row2.createCell(getIndex(3));
		cell.setCellValue("完成时间");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(3),getIndex(3)));
		
//		cell = row2.createCell(getIndex(4));
//		cell.setCellValue("状态");
//		cell.setCellStyle(tCellStyleLeft);
//		sheet.addMergedRegion(new CellRangeAddress(getIndex(4),getIndex(3),getIndex(4),getIndex(4)));
//		
//		cell = row2.createCell(getIndex(5));
//		cell.setCellValue("主责方");
//		cell.setCellStyle(tCellStyleLeft);
//		sheet.addMergedRegion(new CellRangeAddress(getIndex(5),getIndex(3),getIndex(5),getIndex(5)));

		
		//第三行:开始时间、计划时间、当前状态
		HSSFRow row3 = sheet.createRow(getIndex(3));
		for(int i=0;i<plans.size();i++){
			//业态名称
			cell = row2.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue(plans.get(i).getExecutionPlanName());
			cell.setCellStyle(tCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(2),getIndex(3+FIX_COLS*i),getIndex(3+FIX_COLS*i+2)));
			
			//开始时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue("开始时间");
			cell.setCellStyle(tCellStyle);
			
			//计划时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i+1));
			cell.setCellValue("计划时间");
			cell.setCellStyle(tCellStyle);
			
			//当前状态
			cell = row3.createCell(getIndex(3+FIX_COLS*i+2));
			cell.setCellValue("当前状态");
			cell.setCellStyle(tCellStyle);
		}
		
		HSSFRow tmpRow = null;
		ExecPlanNode tmpRel = null;
		String tmpRelId = null;
		String tmpPlanCd = null;
		String tmpRelDisplayId = null;
		ExecPlanDetail tmpDetail = null;
		boolean tmpFlag = false;
		
		//明细数据
		for (int i=0; i<planNodes.size(); i++) {
			tmpRel = planNodes.get(i);
			tmpRow = sheet.createRow(getIndex(4+i));
			
			//节点名称
			cell = tmpRow.createCell(getIndex(1));
			cell.setCellValue(getSpanChars(String.valueOf(tmpRel.getPointLevel())) + tmpRel.getNodeName());
			cell.setCellStyle(tCellStyleLeft);
			 
			//主责方
			cell = tmpRow.createCell(getIndex(2));
			cell.setCellValue(tmpRel.getResOrgName());
			cell.setCellStyle(tCellStyleLeft);
			
			tmpRelId = tmpRel.getExecPlanNodeId();
			tmpRelDisplayId = tmpRel.getExecPlanNodeId();
			
			tmpFlag = false;
			for (int j=0;j< plans.size();j++) {

				if(StringUtils.isBlank(tmpRelDisplayId)){
					tmpDetail = planDetailMap.get(tmpRelId+"_"+plans.get(j).getExecutionPlanCd());
				}else{
					tmpDetail = planDetailMap.get(tmpRelDisplayId+"_"+plans.get(j).getExecutionPlanCd());
					tmpFlag = true;
				}
				if(tmpDetail != null){
					HSSFCellStyle titleStyle = workbook.createCellStyle();
					titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

					//开始时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j));
//					cell.setCellValue(tmpDetail.getScheduleStartDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleStartDate()));
					cell.setCellStyle(tCellStyle);
					
					//计划时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+1));
//					cell.setCellValue(tmpDetail.getScheduleEndDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleEndDate()));
					cell.setCellStyle(tCellStyle);
					
					//当前状态
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+2));
					String colorStatus = getProcessDescNew(tmpDetail);//3=预完成, 2=完成, 1=预完成, 0=未完成
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+2));
					cell.setCellValue(colorStatus);
					if(colorStatus.equals("[延期]")){
						cell.setCellValue("未完成");
						titleStyle.setFillForegroundColor((short)2);//1:白色,2:红色,3:绿色,4:蓝色,5:黄色,6:紫色
					}else if(colorStatus.equals("[确认]")){
						cell.setCellValue("预完成");
						titleStyle.setFillForegroundColor((short)4);
					}else if(colorStatus.equals("[预完成]")){
						cell.setCellValue("预完成");
						titleStyle.setFillForegroundColor((short)4);
					}else if(colorStatus.equals("[完成]")){
						titleStyle.setFillForegroundColor((short)1);
						titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
						titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					}else {
						titleStyle.setFillForegroundColor((short)1);
						titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
						titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					}
					cell.setCellStyle(titleStyle);
					
					if(tmpFlag){
					// 添加单元格注释   
					int row = tmpRow.getRowNum()+1;
					int col = (3*j+3+1);
			         // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
			         HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
			         // 定义注释的大小和位置,详见文档   
			         HSSFComment comment = patr.createCellComment(t);   
			         // 设置注释内容   
			         comment.setString( new HSSFRichTextString( "[关联节点显示]"+nodeMap.get(tmpRelDisplayId)));   
			         // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
			         //comment.setAuthor( " PD" ); 
			         comment.setRow(3);
			         comment.setLineWidth(250);
			         cell.setCellComment(comment);
					}
				}
			}
		}
		// ----------生成合计数据----------结束
		 
		// 创建一个新的字节数组输出流
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			// 将封装的excel写入output流
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建一个新分配的byte字节数组。
		byte[] content = os.toByteArray();
		// 创建一个新的字节数组输出流。
		InputStream inputstr = new ByteArrayInputStream(content);

		// 返回流
		return inputstr;
	}
	
	private String getFormatDateDesc(Date date){
		if(date == null)
			return "";
		else
			return DateParser.formatDate(date, "yyyy-MM-dd");
	}
	
	private String getSpanChars(String spans){
		if("1".equals(spans))
			return "";
		else if("2".equals(spans))
			return "    ";
		else if("3".equals(spans))
			return "        ";
		else
			return "";
	}
	/**
	 * 状态描述
	 * @param planDetail
	 * @return
	 */
	private String getProcessDesc(PlanExecPlanDetail planDetail){

		if(planDetail == null)
			return "";
		String tmpStatus = planDetail.getStatus();
		Date tmpSEndDate = planDetail.getScheduleEndDate();
		Date tmpREndDate = planDetail.getRealEndDate();
		String tmpComFlg = planDetail.getInfoConfirmedFlg();
		
		if("0".equals(tmpStatus) && "1".equals(tmpComFlg)){
			if((tmpSEndDate!= null && tmpREndDate == null) && DateOperator.compareDays(tmpSEndDate, new Date()) < 0)
				return "[延期]";
			else
				return "[确认]";
		}else{
			if("0".equals(tmpStatus) && (StringUtils.isNotBlank(tmpComFlg)) && "0".equals(tmpComFlg))
				return "[延期]";
			else if("0".equals(tmpStatus) && (StringUtils.isNotBlank(tmpComFlg)) && "1".equals(tmpComFlg))
				return "[确认]";
			else if("1".equals(tmpStatus)){
				String tmp = "";
				if(tmpREndDate != null){
					tmp = DateParser.formatDate(tmpREndDate, "yy-MM-dd");
				}
				return tmp + "[预完成]";
			}else if ("2".equals("预完成")){
				String tmp = "";
				if(tmpREndDate != null){
					tmp = DateParser.formatDate(tmpREndDate, "yy-MM-dd");
				}
				return tmp + "[完成]";
			}
		}
		return ".";
	}

	
	/**
	 * 状态描述
	 * @param planDetail
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getProcessDescNew(ExecPlanDetail planDetail){

		if(planDetail == null)
			return "";
		String tmpStatus = planDetail.getStatus();
		Date tmpSEndDate = planDetail.getScheduleEndDate();
		Date tmpREndDate = planDetail.getRealEndDate();
		String tmpComFlg = planDetail.getInfoConfirmedFlg();
		
		if("0".equals(tmpStatus) && "1".equals(tmpComFlg)){
			if((tmpSEndDate!= null && tmpREndDate == null) && DateOperator.compareDays(tmpSEndDate, new Date()) < 0)
				return "[延期]";
			else
				return "[确认]";
		}else{
			if("0".equals(tmpStatus) && (StringUtils.isNotBlank(tmpComFlg)) && "0".equals(tmpComFlg))
				return "[延期]";
			else if("0".equals(tmpStatus) && (StringUtils.isNotBlank(tmpComFlg)) && "1".equals(tmpComFlg))
				return "[确认]";
			else if("1".equals(tmpStatus)){
				String tmp = "";
				if(tmpREndDate != null){
					tmp = DateParser.formatDate(tmpREndDate, "yy-MM-dd");
				}
				return tmp + "[预完成]";
			}else if ("2".equals("预完成")){
				String tmp = "";
				if(tmpREndDate != null){
					tmp = DateParser.formatDate(tmpREndDate, "yy-MM-dd");
				}
				return tmp + "[完成]";
			}
		}
		return "";
	}
	

	/**
	 *  导出项目执行计划明细(旧模块)
	 * @param sheetName
	 * @param planNodes
	 * @param plans
	 * @param planDetailMap
	 * @param statusMap
	 * @return
	 */
	public InputStream createExcelPlanDetailOld(String sheetName, List<PlanProjectNodeRelOld> planNodes, List<PlanExecutionPlanOld> plans, Map<String, PlanExecPlanDetailOld> planDetailMap, Map<String, String> statusMap,Map<String,String> nodeMap) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 将第一个编码设置成 UTF-16 避免中文乱码
		workbook.setSheetName(0, sheetName);
		//## 冻结首3行,1列 ##//   
		sheet.createFreezePane(2, 3);   
		
		// 设置表格默认列宽度为16个字节
		sheet.setDefaultColumnWidth((short) 8);
		sheet.setColumnWidth((short)0,(short)7000);
		sheet.setColumnWidth((short)1,(short)5000);

		// -----------产生表格抬头标题行 开始-----------
		HSSFCellStyle headStyle = workbook.createCellStyle();
		// 设置抬头背景颜色和相关样式
		headStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 设置字体样式 抬头字体
		HSSFFont font = workbook.createFont();
		// 设置字体颜色
		font.setColor(HSSFColor.WHITE.index);
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用
		headStyle.setFont(font);
		// -----------产生表格抬头标题行 结束-----------

		// ----------设置内容表格样式行 开始-----------
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		contentStyle.setFont(font2);
		// ----------设置内容表格样式行 结束-----------

		// ----------生成表的抬头----------开始
		
		HSSFCellStyle tCellStyle = workbook.createCellStyle();
		tCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle tCellStyleLeft = workbook.createCellStyle();
		tCellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tCellStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
        // 用于格式化单元格的数据 
        HSSFCellStyle cellStyleDate = workbook.createCellStyle();//建立新的cell样式    
        cellStyleDate.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));//设置cell样式为定制的日期格式    


        HSSFPatriarch patr = sheet.createDrawingPatriarch();   
        HSSFFont hfRed=workbook.createFont();
        hfRed.setColor((short)2); //1:白色,2:红色,3:绿色,4:蓝色,5:黄色,6:紫色
        
        int FIX_COLS = 3;
		
        //第一行
		HSSFRow row1 = sheet.createRow(getIndex(1));
		row1.setHeightInPoints((float)30);
		HSSFCell cell = null;
		cell = row1.createCell(getIndex(1));
		cell.setCellValue("项目 执行计划 节点");
		cell.setCellStyle(tCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(1),getIndex(2)));

		cell = row1.createCell(getIndex(3));
		cell.setCellValue("项目业态");
		cell.setCellStyle(tCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(3),getIndex(plans.size()*FIX_COLS+2)));
		
		//第二行
		HSSFRow row2 = sheet.createRow(getIndex(2));
		cell = row2.createCell(getIndex(1));
		cell.setCellValue("节点名称");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(1),getIndex(1)));
		 
		cell = row2.createCell(getIndex(2));
		cell.setCellValue("主责方");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(2),getIndex(2)));

		
		//第三行:开始时间、计划时间、当前状态
		HSSFRow row3 = sheet.createRow(getIndex(3));
		for(int i=0;i<plans.size();i++){
			//业态名称
			cell = row2.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue(plans.get(i).getExecutionPlanName());
			cell.setCellStyle(tCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(2),getIndex(3+FIX_COLS*i),getIndex(3+FIX_COLS*i+2)));
			
			//开始时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue("开始时间");
			cell.setCellStyle(tCellStyle);
			
			//计划时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i+1));
			cell.setCellValue("计划时间");
			cell.setCellStyle(tCellStyle);
			
			//当前状态
			cell = row3.createCell(getIndex(3+FIX_COLS*i+2));
			cell.setCellValue("当前状态");
			cell.setCellStyle(tCellStyle);
		}
		
		HSSFRow tmpRow = null;
		PlanProjectNodeRelOld tmpRel = null;
		String tmpRelId = null;
		String tmpPlanCd = null;
		String tmpRelDisplayId = null;
		PlanExecPlanDetailOld tmpDetail = null;
		boolean tmpFlag = false;
		
		//明细数据
		for (int i=0; i<planNodes.size(); i++) {
			tmpRel = planNodes.get(i);
			tmpRow = sheet.createRow(getIndex(4+i));
			
			//节点名称
			cell = tmpRow.createCell(getIndex(1));
			cell.setCellValue(getSpanChars(tmpRel.getTreeType()) + tmpRel.getNodeName());
			cell.setCellStyle(tCellStyleLeft);
			 
			//主责方
			cell = tmpRow.createCell(getIndex(2));
			cell.setCellValue(tmpRel.getResOrgName());
			cell.setCellStyle(tCellStyleLeft);
			
			tmpRelId = tmpRel.getPlanProjectNodeRelId();
			tmpRelDisplayId = tmpRel.getRelDisplayNodeId();
			
			tmpFlag = false;
			for (int j=0;j< plans.size();j++) {

				if(StringUtils.isBlank(tmpRelDisplayId)){
					tmpDetail = planDetailMap.get(tmpRelId+"_"+plans.get(j).getExecutionPlanCd());
				}else{
					tmpDetail = planDetailMap.get(tmpRelDisplayId+"_"+plans.get(j).getExecutionPlanCd());
					tmpFlag = true;
				}
				if(tmpDetail != null){

					//开始时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j));
//					cell.setCellValue(tmpDetail.getScheduleStartDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleStartDate()));
					cell.setCellStyle(tCellStyle);
					
					//计划时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+1));
//					cell.setCellValue(tmpDetail.getScheduleEndDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleEndDate()));
					cell.setCellStyle(tCellStyle);
					
					//当前状态
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+2));
					cell.setCellValue(getProcessDescOld(tmpDetail));
					cell.setCellStyle(tCellStyle);
					
					if(tmpFlag){
					// 添加单元格注释   
					int row = tmpRow.getRowNum()+1;
					int col = (3*j+3+1);
			         // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
			         HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
			         // 定义注释的大小和位置,详见文档   
			         HSSFComment comment = patr.createCellComment(t);   
			         // 设置注释内容   
			         comment.setString( new HSSFRichTextString( "[关联节点显示]"+nodeMap.get(tmpRelDisplayId)));   
			         // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
			         //comment.setAuthor( " PD" ); 
			         comment.setRow(3);
			         comment.setLineWidth(250);
			         cell.setCellComment(comment);
					}
				}
			}
		}
		// ----------生成合计数据----------结束
		 
		// 创建一个新的字节数组输出流
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			// 将封装的excel写入output流
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建一个新分配的byte字节数组。
		byte[] content = os.toByteArray();
		// 创建一个新的字节数组输出流。
		InputStream inputstr = new ByteArrayInputStream(content);

		// 返回流
		return inputstr;
	}
	/**
	 * 状态描述
	 * @param planDetail
	 * @return
	 */
	private String getProcessDescOld(PlanExecPlanDetailOld planDetail){

		if(planDetail == null)
			return "";
		String tmpStatus = planDetail.getStatus();
		Date tmpSEndDate = planDetail.getScheduleEndDate();
		Date tmpREndDate = planDetail.getRealEndDate();
		String tmpComFlg = planDetail.getInfoConfirmedFlg();
		
		if("0".equals(tmpStatus) && "1".equals(tmpComFlg)){
			if((tmpSEndDate!= null && tmpREndDate == null) && DateOperator.compareDays(tmpSEndDate, new Date()) < 0)
				return "[延期]";
			else
				return "[确认]";
		}else{
			if("0".equals(tmpStatus) && (StringUtils.isNotBlank(tmpComFlg)) && "0".equals(tmpComFlg))
				return "[延期]";
			else if("0".equals(tmpStatus) && (StringUtils.isNotBlank(tmpComFlg)) && "1".equals(tmpComFlg))
				return "[确认]";
			else if("1".equals(tmpStatus)){
				String tmp = "";
				if(tmpREndDate != null){
					tmp = DateParser.formatDate(tmpREndDate, "yy-MM-dd");
				}
				return tmp + "[预完成]";
			}else if ("2".equals("预完成")){
				String tmp = "";
				if(tmpREndDate != null){
					tmp = DateParser.formatDate(tmpREndDate, "yy-MM-dd");
				}
				return tmp + "[完成]";
			} 
		}
		return ".";
	}

	
	/**
	 *  导出项目执行计划明细(新模块)
	 * @param sheetName
	 * @param planNodes
	 * @param plans
	 * @param planDetailMap
	 * @param statusMap
	 * @return
	 */
	public InputStream createExcelPlanDetail(String sheetName, List<PlanProjectNodeRel> planNodes, List<PlanExecutionPlan> plans, Map<String, PlanExecPlanDetail> planDetailMap, Map<String, String> statusMap,Map<String,String> nodeMap) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 将第一个编码设置成 UTF-16 避免中文乱码
		workbook.setSheetName(0, sheetName);
		//## 冻结首3行,1列 ##//   
		sheet.createFreezePane(2, 3);   
		
		// 设置表格默认列宽度为16个字节
		sheet.setDefaultColumnWidth((short) 8);
		sheet.setColumnWidth((short)0,(short)7000);
		sheet.setColumnWidth((short)1,(short)5000);

		// -----------产生表格抬头标题行 开始-----------
		HSSFCellStyle headStyle = workbook.createCellStyle();
		// 设置抬头背景颜色和相关样式
		headStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 设置字体样式 抬头字体
		HSSFFont font = workbook.createFont();
		// 设置字体颜色
		font.setColor(HSSFColor.WHITE.index);
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用
		headStyle.setFont(font);
		// -----------产生表格抬头标题行 结束-----------

		// ----------设置内容表格样式行 开始-----------
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		contentStyle.setFont(font2);
		// ----------设置内容表格样式行 结束-----------

		// ----------生成表的抬头----------开始
		
		HSSFCellStyle tCellStyle = workbook.createCellStyle();
		tCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle tCellStyleLeft = workbook.createCellStyle();
		tCellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
		tCellStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
        // 用于格式化单元格的数据 
        HSSFCellStyle cellStyleDate = workbook.createCellStyle();//建立新的cell样式    
        cellStyleDate.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));//设置cell样式为定制的日期格式    


        HSSFPatriarch patr = sheet.createDrawingPatriarch();   
        HSSFFont hfRed=workbook.createFont();
        hfRed.setColor((short)2); //1:白色,2:红色,3:绿色,4:蓝色,5:黄色,6:紫色
        
        int FIX_COLS = 3;
		
        //第一行
		HSSFRow row1 = sheet.createRow(getIndex(1));
		row1.setHeightInPoints((float)30);
		HSSFCell cell = null;
		cell = row1.createCell(getIndex(1));
		cell.setCellValue("项目 执行计划 节点");
		cell.setCellStyle(tCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(1),getIndex(2)));

		cell = row1.createCell(getIndex(3));
		cell.setCellValue("项目业态");
		cell.setCellStyle(tCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(1),getIndex(1),getIndex(3),getIndex(plans.size()*FIX_COLS+2)));
		
		//第二行
		HSSFRow row2 = sheet.createRow(getIndex(2));
		cell = row2.createCell(getIndex(1));
		cell.setCellValue("节点名称");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(1),getIndex(1)));
		 
		cell = row2.createCell(getIndex(2));
		cell.setCellValue("主责方");
		cell.setCellStyle(tCellStyleLeft);
		sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(3),getIndex(2),getIndex(2)));

		
		//第三行:开始时间、计划时间、当前状态
		HSSFRow row3 = sheet.createRow(getIndex(3));
		for(int i=0;i<plans.size();i++){
			//业态名称
			cell = row2.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue(plans.get(i).getExecutionPlanName());
			cell.setCellStyle(tCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(getIndex(2),getIndex(2),getIndex(3+FIX_COLS*i),getIndex(3+FIX_COLS*i+2)));
			
			//开始时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i));
			cell.setCellValue("开始时间");
			cell.setCellStyle(tCellStyle);
			
			//计划时间
			cell = row3.createCell(getIndex(3+FIX_COLS*i+1));
			cell.setCellValue("计划时间");
			cell.setCellStyle(tCellStyle);
			
			//当前状态
			cell = row3.createCell(getIndex(3+FIX_COLS*i+2));
			cell.setCellValue("当前状态");
			cell.setCellStyle(tCellStyle);
		}
		
		HSSFRow tmpRow = null;
		PlanProjectNodeRel tmpRel = null;
		String tmpRelId = null;
		String tmpPlanCd = null;
		String tmpRelDisplayId = null;
		PlanExecPlanDetail tmpDetail = null;
		boolean tmpFlag = false;
		
		//明细数据
		for (int i=0; i<planNodes.size(); i++) {
			tmpRel = planNodes.get(i);
			tmpRow = sheet.createRow(getIndex(4+i));
			
			//节点名称
			cell = tmpRow.createCell(getIndex(1));
			cell.setCellValue(getSpanChars(tmpRel.getTreeType()) + tmpRel.getNodeName());
			cell.setCellStyle(tCellStyleLeft);
			 
			//主责方
			cell = tmpRow.createCell(getIndex(2));
			cell.setCellValue(tmpRel.getResOrgName());
			cell.setCellStyle(tCellStyleLeft);
			
			tmpRelId = tmpRel.getPlanProjectNodeRelId();
			tmpRelDisplayId = tmpRel.getRelDisplayNodeId();
			
			tmpFlag = false;
			for (int j=0;j< plans.size();j++) {

				if(StringUtils.isBlank(tmpRelDisplayId)){
					tmpDetail = planDetailMap.get(tmpRelId+"_"+plans.get(j).getExecutionPlanCd());
				}else{
					tmpDetail = planDetailMap.get(tmpRelDisplayId+"_"+plans.get(j).getExecutionPlanCd());
					tmpFlag = true;
				}
				if(tmpDetail != null){

					//开始时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j));
//					cell.setCellValue(tmpDetail.getScheduleStartDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleStartDate()));
					cell.setCellStyle(tCellStyle);
					
					//计划时间
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+1));
//					cell.setCellValue(tmpDetail.getScheduleEndDate());
//					cell.setCellStyle(cellStyleDate);
					cell.setCellValue(getFormatDateDesc(tmpDetail.getScheduleEndDate()));
					cell.setCellStyle(tCellStyle);
					
					//当前状态
					cell = tmpRow.createCell(getIndex(3+FIX_COLS*j+2));
					cell.setCellValue(getProcessDesc(tmpDetail));
					cell.setCellStyle(tCellStyle);
					
					if(tmpFlag){
					// 添加单元格注释   
					int row = tmpRow.getRowNum()+1;
					int col = (3*j+3+1);
			         // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
			         HSSFClientAnchor t = new HSSFClientAnchor( 0 , 0 , 0 , 0 , ( short ) col , row , ( short ) (col+4) , row+3 );
			         // 定义注释的大小和位置,详见文档   
			         HSSFComment comment = patr.createCellComment(t);   
			         // 设置注释内容   
			         comment.setString( new HSSFRichTextString( "[关联节点显示]"+nodeMap.get(tmpRelDisplayId)));   
			         // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.   
			         //comment.setAuthor( " PD" ); 
			         comment.setRow(3);
			         comment.setLineWidth(250);
			         cell.setCellComment(comment);
					}
				}
			}
		}
		// ----------生成合计数据----------结束
		 
		// 创建一个新的字节数组输出流
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			// 将封装的excel写入output流
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建一个新分配的byte字节数组。
		byte[] content = os.toByteArray();
		// 创建一个新的字节数组输出流。
		InputStream inputstr = new ByteArrayInputStream(content);

		// 返回流
		return inputstr;
	}

	/*
	 * 获取单个节点的显示的状态
	 */
	public String getPrintStatusCn(ExecPlanDetail pepd) {
		String returnStr = "";
		if(null==pepd || new ExecPlanDetail()==pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "未启用";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "未确认";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().before(pepd.getScheduleEndDate())) {
				returnStr = "进行中";
			} else {
				returnStr = "延期";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "已完成";
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "预完成";
		}
		return returnStr;
	}
	
	public static void main(String[] args) {
//		PlanExecExportUtil.createExcelPlanFile();
		// jxl.readDataFromExcel(new File(filePath), 0);
		// jxl.openExcel("C:/Program Files/Microsoft Office/OFFICE11/EXCEL.EXE",filePath);

	}
}