/**  
 * CtdbListContentAction.java  
 * com.hhz.ump.web.ctdb  
 *  
 * Function： 清单数据库   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-22        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
 */

package com.hhz.ump.web.ctdb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.ctdb.CtdbListContentManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.ctdb.CtdbListContent;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.web.bid.PoiStyle;

/**
 * ClassName:CtdbListContentAction Function: 清单数据库 Reason: 清单数据库
 * 
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2011-12-22 上午09:43:46
 * 
 */
@Namespace("/ctdb")
@Results( { @Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile", "contentDisposition",
		"attachment;filename=${excelFileName}.xls" }) })
public class CtdbListContentAction extends CrudActionSupport<CtdbListContent> {

	private static final long serialVersionUID = -2713160403979223478L;

	@Autowired
	protected CtdbListContentManager ctdbListContentManager;
	@Autowired
	protected AppAttachFileManager appAttachFileManager;

	/**
	 * 清单数据
	 */
	private CtdbListContent entity;
	/**
	 * 搜索Map
	 */
	private Map<String, Object> map;
	/**
	 * 搜索SQL
	 */
	private StringBuffer hql;
	/**
	 * 小类
	 */
	private Map secOndCats;
	/**
	 * 导入Excel需要的文件
	 */
	private File importFile;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;
	/**
	 * 是否需要转码
	 */
	private String ecode;
	/**
	 * 表单类型
	 */
	private String formType;
	
	
	/**
	 * 附件列表状态
	 */
	public void validateAttach() {
		String bizModuleCd = Struts2Utils.getParameter("bizModuleCd");
		String listId = Struts2Utils.getParameter("id");
		if (listId != null) {
			if("ctdblistContent".equals(bizModuleCd)){
				// 判断是否有该附件
				List<AppAttachFile> fileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(listId, "ctdblistContent");
				if (fileList == null || fileList.size() == 0) {
					ctdbListContentManager.attacheFlgModify(listId, CtdbListContentManager.ATTACHE_NONE);
					Struts2Utils.renderText("0");
				} else {
					ctdbListContentManager.attacheFlgModify(listId, CtdbListContentManager.ATTACHE_HAS);
					Struts2Utils.renderText("1");
				}
			} 
			else{
				Struts2Utils.renderText("bidModuleCd is unkowned!");
			}			
		}else{
			Struts2Utils.renderText("bid project is not founded!");
		}
	}
	
	/**
	 * 提交清单状态前的预备动作
	 * @throws Exception 
	 * 
	 */
	public void prepareCommitList() throws Exception{
		prepareModel();
	}
	
	/**
	 * 提交清单状态
	 * @throws Exception 
	 */
	public String commitList() throws Exception{
		if(entity!=null){
			ctdbListContentManager.saveCtdbListContent(entity);
		}		
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 保存清单数据
	 */
	@Override
	public String save() throws Exception {
		// 数据库大类
		String firstCat = Struts2Utils.getParameter("firstCat");
		// 小类
		String secCate = Struts2Utils.getParameter("secCat");
		// 数量
		String qty = Struts2Utils.getParameter("entity.qty");
		// 单价
		String unitPrice = Struts2Utils.getParameter("entity.unitPrice");
		// 设置数量
		if (StringUtils.isNotBlank(qty)) {
			try {
				entity.setQty(BigDecimal.valueOf(Double.parseDouble(qty.replaceAll(",", ""))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 设置单价
		if (StringUtils.isNotBlank(unitPrice)) {
			try {
				entity.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(unitPrice.replaceAll(",", ""))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		entity.setFirstCat(firstCat);
		entity.setSecCate(secCate);
		//初始化状态为新增状态“0”
		if(StringUtils.isBlank(entity.getStatusCd())) {
			entity.setStatusCd(CtdbListContentManager.NEW_STATUS);
		}
		// 保存清单数据库
		ctdbListContentManager.saveCtdbListContent(entity);
		// 返回成功
		Struts2Utils.renderText("success,"+entity.getListContentId());
		return null;

	}

	/**
	 * 导入清单
	 */
	public String importList() {
		FileInputStream inpFile = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		String firstCat = Struts2Utils.getParameter("firstCat");
		String secCate = Struts2Utils.getParameter("secCat");
		try {
			// 读取导入文件
			inpFile = new FileInputStream(importFile);
			// 导入文件以work book形式呈现
			workbook = new HSSFWorkbook(inpFile);
			// 获取work book第一张 标
			sheet = workbook.getSheetAt(0);
			// 验证大类小类
			// 验证大类小类
			String firstCatTmp = "";
			if (sheet.getRow(0).getLastCellNum() > 0 && sheet.getRow(0).getCell(1) != null) {
				firstCatTmp = sheet.getRow(0).getCell(1).getStringCellValue();
			}
			String secCateTmp = "";
			if (sheet.getRow(0).getLastCellNum() > 3 && sheet.getRow(0).getCell(4) != null) {
				secCateTmp = sheet.getRow(0).getCell(4).getStringCellValue();
			}
			if (StringUtils.isNotBlank(firstCat) && StringUtils.isNotBlank(secCate)) {
				if (DictMapUtil.getMapCtdb(firstCat.trim()).get(secCate).equals(secCateTmp)) {

				} else {
					// 返回成功
					Struts2Utils.renderText("error,导入失败！大类或者小类不匹配,");
					return null;
				}
			}
			// 执行读入和导入操作
			readAndImpSheet(sheet);
			if (sheet != null) {
				sheet = null;
			}
			if (workbook != null) {
				workbook = null;
			}
			if (inpFile != null) {
				inpFile.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("error,导入失败！文件异常！,");
			return null;
		}
		// 返回成功
		Struts2Utils.renderText("success,导入成功！,");
		return null;
	}

	/**
	 * 
	 * 执行EXCEL读入和导入数据库操作
	 */
	private void readAndImpSheet(HSSFSheet sheet) {
		// 大类小类
		String firstCat = Struts2Utils.getParameter("firstCat");
		String secCate = Struts2Utils.getParameter("secCat");

		// 单元格
		HSSFRow cells = null;
		if (sheet != null) {
			int maxNo = sheet.getLastRowNum() + 1;
			// 清单数据
			CtdbListContent clc = null;
			// 清单数据列表
			List<CtdbListContent> clcs = new ArrayList<CtdbListContent>();
			// 以第2行开始读取
			for (int i = 3; i < maxNo; i++) {
				cells = sheet.getRow(i);
				// 如果某行第一列为空，则不导入
				if (cells != null && cells.getCell(0) != null && StringUtils.isNotBlank(cells.getCell(0).toString())) {
				} else {
					continue;
				}
				// 逐行读入并导入
				clc = new CtdbListContent();
				//初始化为新增状态"0"
				clc.setStatusCd(CtdbListContentManager.NEW_STATUS);
				// 清单编号
				if (cells.getCell(0) != null) {
					clc.setListCd(cells.getCell(0).toString());
				}
				// 清单名称
				if (cells.getCell(1) != null) {
					clc.setListName(cells.getCell(1).toString());
				}
				// 说明
				if (cells.getCell(2) != null) {
					clc.setListDesc(cells.getCell(2).toString());
				}
				// 数量
				if (cells.getCell(3) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(3).getCellType()) {
					clc.setQty(BigDecimal.valueOf(cells.getCell(3).getNumericCellValue()));
				}
				// 单位
				if (cells.getCell(4) != null) {
					clc.setMeasurement(cells.getCell(4).toString());
				}
				// 单价（元）
				if (cells.getCell(5) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(5).getCellType()) {
					clc.setUnitPrice(BigDecimal.valueOf(cells.getCell(5).getNumericCellValue()));
				}
				// 人工费
				if (cells.getCell(6) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(6).getCellType()) {
					clc.setPersonalPrice(BigDecimal.valueOf(cells.getCell(6).getNumericCellValue()));
				}
				// 主材费
				if (cells.getCell(7) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(7).getCellType()) {
					clc.setMainMatePrice(BigDecimal.valueOf(cells.getCell(7).getNumericCellValue()));
				}
				// 辅材费
				if (cells.getCell(8) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(8).getCellType()) {
					clc.setAidMatePrice(BigDecimal.valueOf(cells.getCell(8).getNumericCellValue()));
				}
				// 机械费
				if (cells.getCell(9) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(9).getCellType()) {
					clc.setMachinePrice(BigDecimal.valueOf(cells.getCell(9).getNumericCellValue()));
				}
				// 管理费及利润
				if (cells.getCell(10) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(10).getCellType()) {
					clc.setManageFees(BigDecimal.valueOf(cells.getCell(10).getNumericCellValue()));
				}
				// 税金
				if (cells.getCell(11) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(11).getCellType()) {
					clc.setTax(BigDecimal.valueOf(cells.getCell(11).getNumericCellValue()));
				}
				// 主材耗损率
				if (cells.getCell(12) != null && HSSFCell.CELL_TYPE_NUMERIC == cells.getCell(12).getCellType()) {
					clc.setMainMateRate(BigDecimal.valueOf(cells.getCell(12).getNumericCellValue()));
				}
				// 价格来源
				if (cells.getCell(13) != null) {
					clc.setPriceFrom(cells.getCell(13).toString());
				}
				clc.setFirstCat(firstCat);
				clc.setSecCate(secCate);
				// 导入标记
				clc.setImportFlg("1");
				clcs.add(clc);
			}
			// 保存数据清单列表
			ctdbListContentManager.batchSave(clcs);
		}

	}

	/**
	 * 
	 * 准备搜索条件，包括搜索条件，搜索语句，分页条件
	 */
	public void prepareLoadList() {
		
		// 搜索SQL
		hql = new StringBuffer();
		hql.append(" from CtdbListContent clc where 1=1 ");
		// 搜索条件
		map = new HashMap<String, Object>();
		// 清单数据库小类
		String secCate = Struts2Utils.getParameter("selectedItems");
		// 清单编号
		String listCd = Struts2Utils.getParameter("listCd");
		//状态
		String statusCd = Struts2Utils.getParameter("statusCd");
		// 清单名称
		String listName = Struts2Utils.getParameter("listName");
		// 录入时间从
		String createdDate = Struts2Utils.getParameter("createdDate");
		// 录入时间到
		String createdDateTo = Struts2Utils.getParameter("createdDateTo");

		if (StringUtils.isNotBlank(secCate)) {
			hql.append(" and clc.secCate in( :secCate ) ");
			map.put("secCate", secCate.split(","));
		}
		if (StringUtils.isNotBlank(listCd)) {
			hql.append(" and clc.listCd like '%" + listCd + "%' ");
			map.put("listCd", listCd);
		}
		if (StringUtils.isNotBlank(listName)) {
			hql.append(" and clc.listName like '%" + listName + "%'");
			map.put("listName", listName);
		}
		if (StringUtils.isNotBlank(createdDate)) {
			map.put("createdDate", createdDate);
			hql.append("and clc.createdDate >= to_date(:createdDate,'yyyy-mm-dd')");
		}
		if (StringUtils.isNotBlank(createdDateTo)) {
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			Calendar c=Calendar.getInstance();
			try {
				c.setTime(f.parse(createdDateTo));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.add(Calendar.DAY_OF_MONTH, 1);
			map.put("createdDateTo", f.format(c.getTime()));
			hql.append("and clc.createdDate <= to_date(:createdDateTo,'yyyy-mm-dd')");
		}
		if(StringUtils.isNotBlank(statusCd)){
			if("2".equals(statusCd)){
				hql.append(" and clc.statusCd =  :statusCd");
				map.put("statusCd", statusCd);
			}else{
				hql.append(" and clc.statusCd =  :statusCd and clc.creator = :creator ");
				map.put("statusCd", statusCd);
				map.put("creator", SpringSecurityUtils.getCurrentPlasUser().getUiid());
			}			
		}else{
			//只能导出和搜索属于自己的0，1，3状态自己的数据和2状态的所有数据
			//0-暂存1-待确认2-确认3-驳回
			hql.append(" and (clc.statusCd = 2 ")
			.append("  or ((clc.statusCd=0) and clc.creator = :creator)")
			.append("  or ((clc.statusCd=1) and clc.creator = :creator)")
			.append("  or ((clc.statusCd=3) and clc.creator = :creator))");
			map.put("creator", SpringSecurityUtils.getCurrentPlasUser().getUiid());
		}
		

		//页码信息
		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		if(StringUtils.isNotBlank(rows)){
			page.setPageSize(Integer.parseInt(rows));
		}
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		
	}

	/**
	 * 
	 * 按照条件搜索数据列表
	 */
	public String loadList() {

		page = ctdbListContentManager.findPage(page, hql.toString(), map);		
		return "loadList";
	}

	/**
	 * 获取表单类型
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String getForm() throws Exception {
		prepareModel();
		String formTypeCat = Struts2Utils.getParameter("formTypeCat");		
		if (StringUtils.isNotBlank(formTypeCat)) {
			System.out.println(formType);
			if ("impform".equals(formTypeCat))
				return "impform";
		}
		//如果是对象不为空，则获取
		if(entity!=null){
			secOndCats = DictMapUtil.getMapCtdb(entity.getFirstCat());
		}
		//如果存在页码信息
		String pageNo=Struts2Utils.getParameter("pageNo");
		if(StringUtils.isNotBlank(pageNo)){
			Struts2Utils.getRequest().setAttribute("pageNo", pageNo);
		}
		return "form";
	}

	/**
	 * 
	 * 根据大类获取小类
	 */
	public String getSecondCat() {
		String firstCat = Struts2Utils.getParameter("firstCat");
		if (StringUtils.isNotBlank(firstCat)) {
			secOndCats = DictMapUtil.getMapCtdb(firstCat.trim());
			/*
			 * if(secOndCats!=null){ java.util.Iterator
			 * it=secOndCats.keySet().iterator(); while(it.hasNext()){
			 * it.next(); } }
			 */
		}
		return "secCat";
	}

	/**
	 * 
	 * 清单数据库导出EXCEL
	 */
	public String exportToExcel() {
		prepareLoadList();
		// 文件名
		String sheetName = "清单数据库";
		// 输出文件流
		ByteArrayOutputStream outExcelFile = null;
		try {
			excelFileName = sheetName;
			outExcelFile = new ByteArrayOutputStream();
			// 将分析数据填入excel
			fillInExcel(sheetName, outExcelFile);
			// 将数据读出到数组
			byte[] data = outExcelFile.toByteArray();
			// 将数据写入文件，执行导出
			excelFile = new ByteArrayInputStream(data);
			// 文件名编码转换，防止乱码
			excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "export";
	}

	/**
	 * 填充EXCEL内容
	 * 
	 * @param sheetName
	 * @param outExcelFile
	 */
	private void fillInExcel(String sheetName, ByteArrayOutputStream outExcelFile) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 22);
		sheet.setDefaultRowHeight((short) 25);
		// 冻结
		// sheet.createFreezePane(6, 2);
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
		// mergeCell(sheet,address,0,1,0,0);
		// 第1行
		HSSFRow row = null;
		// 第1行
		row = sheet.createRow(0);
		// 序号
		// addCell(row,cell,text,0,style4,"1","序号",null);
		// 清单编号
		PoiExcelTool.addCell(row, cell, text, 0, style4, "1", "清单编号", null);
		// 清单名称
		PoiExcelTool.addCell(row, cell, text, 1, style4, "1", "清单名称", null);
		// 说明
		PoiExcelTool.addCell(row, cell, text, 2, style4, "1", "说明", null);
		// 数量
		PoiExcelTool.addCell(row, cell, text, 3, style4, "1", "数量", null);
		// 单位
		PoiExcelTool.addCell(row, cell, text, 4, style4, "1", "单位", null);
		// 单价（元）
		PoiExcelTool.addCell(row, cell, text, 5, style4, "1", "单价（元）", null);
		// 单价组价明细
		PoiExcelTool.addCell(row, cell, text, 6, style4, "1", "单价组价明细", null);
		// 新建5列空表格
		PoiExcelTool.addCell(row, cell, text, 7, style4, "1", "", null);
		PoiExcelTool.addCell(row, cell, text, 8, style4, "1", "", null);
		PoiExcelTool.addCell(row, cell, text, 9, style4, "1", "", null);
		PoiExcelTool.addCell(row, cell, text, 10, style4, "1", "", null);
		PoiExcelTool.addCell(row, cell, text, 11, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 0, 6, 11);
		// 主材耗损率
		PoiExcelTool.addCell(row, cell, text, 12, style4, "1", "主材耗损率", null);
		// 价格来源
		PoiExcelTool.addCell(row, cell, text, 13, style4, "1", "价格来源", null);
		//附件标记
		PoiExcelTool.addCell(row, cell, text, 14, style4, "1", "附件标记", null);
		//状态
		PoiExcelTool.addCell(row, cell, text, 15, style4, "1", "状态", null);
		// 第2行
		row = sheet.createRow(1);
		// 先建6个空表格
		PoiExcelTool.addCell(row, cell, text, 0, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 0, 0);
		PoiExcelTool.addCell(row, cell, text, 1, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 1, 1);
		PoiExcelTool.addCell(row, cell, text, 2, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 2, 2);
		PoiExcelTool.addCell(row, cell, text, 3, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 3, 3);
		PoiExcelTool.addCell(row, cell, text, 4, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 4, 4);
		PoiExcelTool.addCell(row, cell, text, 5, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 5, 5);
		// 人工费
		PoiExcelTool.addCell(row, cell, text, 6, style4, "1", "人工费", null);
		// 主材费
		PoiExcelTool.addCell(row, cell, text, 7, style4, "1", "主材费", null);
		// 辅材费
		PoiExcelTool.addCell(row, cell, text, 8, style4, "1", "辅材费", null);
		// 机械费
		PoiExcelTool.addCell(row, cell, text, 9, style4, "1", "机械费", null);
		// 管理费及利润
		PoiExcelTool.addCell(row, cell, text, 10, style4, "1", "管理费及利润", null);
		// 税金
		PoiExcelTool.addCell(row, cell, text, 11, style4, "1", "税金", null);
		// 主材耗损率
		PoiExcelTool.addCell(row, cell, text, 12, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 12, 12);
		// 价格来源
		PoiExcelTool.addCell(row, cell, text, 13, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 13, 13);
		//附件标记
		PoiExcelTool.addCell(row, cell, text, 14, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 14, 14);
		//状态
		PoiExcelTool.addCell(row, cell, text, 15, style4, "1", "", null);
		PoiExcelTool.mergeCell(sheet, address, 0, 1, 15, 15);

		// 表体数据
		List<CtdbListContent> ctdbListContents = ctdbListContentManager.find(hql.toString(), map);
		if (ctdbListContents != null && ctdbListContents.size() > 0) {
			for (int i = 0; i < ctdbListContents.size(); i++) {
				CtdbListContent clc = ctdbListContents.get(i);
				row = sheet.createRow(i + 2);
				// 清单编号
				if (StringUtils.isNotBlank(clc.getListCd())) {
					PoiExcelTool.addCell(row, cell, text, 0, style2, "1", clc.getListCd(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 0, style2, "1", "", null);
				}
				// 清单名称
				if (StringUtils.isNotBlank(clc.getListName())) {
					PoiExcelTool.addCell(row, cell, text, 1, style2, "1", clc.getListName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 1, style2, "1", "", null);
				}
				// 说明
				if (StringUtils.isNotBlank(clc.getListDesc())) {
					PoiExcelTool.addCell(row, cell, text, 2, style2, "1", clc.getListDesc(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 2, style2, "1", "", null);
				}
				// 数量
				if (clc.getQty() != null) {
					PoiExcelTool.addCell(row, cell, text, 3, style2, "0", null, clc.getQty().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 3, style2, "1", "", null);
				}
				// 单位
				if (StringUtils.isNotBlank(clc.getMeasurement())) {
					PoiExcelTool.addCell(row, cell, text, 4, style2, "1", clc.getMeasurement(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 4, style2, "1", "", null);
				}
				// 单价（元）
				if (clc.getUnitPrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 5, style2, "0", null, clc.getUnitPrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 5, style2, "1", "", null);
				}
				// 人工费
				if (clc.getPersonalPrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 6, style2, "0", null, clc.getPersonalPrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 6, style2, "1", "", null);
				}
				// 主材费
				if (clc.getMainMatePrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 7, style2, "0", null, clc.getMainMatePrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 7, style2, "1", "", null);
				}
				// 辅材费
				if (clc.getAidMatePrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 8, style2, "0", null, clc.getAidMatePrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 8, style2, "1", "", null);
				}
				// 机械费
				if (clc.getMachinePrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 9, style2, "0", null, clc.getMachinePrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 9, style2, "1", "", null);
				}
				// 管理费及利润
				if (clc.getManageFees() != null) {
					PoiExcelTool.addCell(row, cell, text, 10, style2, "0", null, clc.getManageFees().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 10, style2, "1", "", null);
				}
				// 税金
				if (clc.getTax() != null) {
					PoiExcelTool.addCell(row, cell, text, 11, style2, "0", null, clc.getTax().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 11, style2, "1", "", null);
				}
				// 主材耗损率
				if (clc.getMainMateRate() != null) {
					PoiExcelTool.addCell(row, cell, text, 12, style2, "0", null, clc.getMainMateRate().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 12, style2, "1", "", null);
				}
				// 价格来源
				if (StringUtils.isNotBlank(clc.getPriceFrom())) {
					PoiExcelTool.addCell(row, cell, text, 13, style2, "1", clc.getPriceFrom(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 13, style2, "1", "", null);
				}
				// 附件标记
				if (StringUtils.isNotBlank(clc.getAttachFlg())) {
					if("1".equals(clc.getAttachFlg())) {
						PoiExcelTool.addCell(row, cell, text, 14, style2, "1", "有", null);
					} else {
						PoiExcelTool.addCell(row, cell, text, 14, style2, "1", "无", null);
					}
				} else {
					PoiExcelTool.addCell(row, cell, text, 14, style2, "1", "无", null);
				}
				// 状态
				if (StringUtils.isNotBlank(clc.getStatusCd())) {
					if("0".equals(clc.getStatusCd())) {
						PoiExcelTool.addCell(row, cell, text, 15, style2, "1", "暂存", null);
					} else if("1".equals(clc.getStatusCd())) {
						PoiExcelTool.addCell(row, cell, text, 15, style2, "1", "待确认", null);
					} else if("2".equals(clc.getStatusCd())) {
						PoiExcelTool.addCell(row, cell, text, 15, style2, "1", "已确认", null);
					} else if("3".equals(clc.getStatusCd())) {
						PoiExcelTool.addCell(row, cell, text, 15, style2, "1", "驳回", null);
					}
					
				} 

			}
		}
		try {
			workbook.write(outExcelFile);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook = null;
			}
		}

	}

	/**
	 * 删除清单
	 */
	@Override
	public String delete() throws Exception {
		String listId = Struts2Utils.getParameter("listId");
		if(StringUtils.isNotBlank(listId)){
			ctdbListContentManager.deleteCtdbListContent(listId);
		}
		Struts2Utils.renderText("success");
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
		String listId = Struts2Utils.getParameter("listId");
		if (StringUtils.isNotBlank(listId)) {
			entity=ctdbListContentManager.getEntity(listId);
		}
	}

	public Map getSecOndCats() {
		return secOndCats;
	}

	public void setSecOndCats(Map secOndCats) {
		this.secOndCats = secOndCats;
	}

	@Override
	public CtdbListContent getModel() {
		return null;

	}

	public CtdbListContent getEntity() {
		return entity;
	}

	public void setEntity(CtdbListContent entity) {
		this.entity = entity;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
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

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

}
