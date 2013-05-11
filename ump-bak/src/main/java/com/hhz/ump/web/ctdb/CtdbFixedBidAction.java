/**  
 * CtdbFixedBidAction.java  
 * com.hhz.ump.web.ctdb  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-21        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
 */

package com.hhz.ump.web.ctdb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ctdb.CtdbFixedBidManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.ctdb.CtdbFixedBid;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.bid.PoiStyle;

/**
 * ClassName:CtdbFixedBidAction Function: TODO 定标审批表生成的定标审批数据库 Reason: TODO 定标审批表生成定标审批数据库
 * 
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2011-12-21 下午03:30:02
 * 
 */
@Namespace("/ctdb")
@Results({ @Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
		"excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }) })
public class CtdbFixedBidAction extends CrudActionSupport<CtdbFixedBid> {

	private static final long serialVersionUID = 1L;
	@Autowired
	protected CtdbFixedBidManager ctdbFixedBidManager;
	@Autowired
	protected ResApproveInfoManager resApproveInfoManager;
	
	/**
	 * 搜索Map
	 */
	private Map<String, Object> map;
	/**
	 * 搜索SQL
	 */
	private StringBuffer hql;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;
	private String inputName;
	private String ecode;
	 
	public void prepareLoadList() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		// 根据请求参数构建搜索语句和搜索条件
		map = new HashMap<String, Object>();
		hql = new StringBuffer().append(" from CtdbFixedBid cfb where 1=1 ");
		// 项目ID
		String projectCds = Struts2Utils.getParameter("selectedItems");
		// 项目名称
		String orgName = Struts2Utils.getParameter("orgName");
		// 工程名称
		String projectName = Struts2Utils.getParameter("projectName");
		// 中标单位
		String bidSupName = Struts2Utils.getParameter("bidSupName");
		// 计价模式
		String calcuMode = Struts2Utils.getParameter("calcuMode");
		// 定标日期从
		String bidDate = Struts2Utils.getParameter("bidDate");
		// 定标日期到
		String bidDateTo = Struts2Utils.getParameter("bidDateTo");

		// 如果项目ID不为空
		if (StringUtils.isNotBlank(projectCds)) {
			String[] tt = projectCds.split(",");
			map.put("projectIds", tt);
			hql.append(" and orgCd in ( :projectIds )");
		}
		// 如果项目名称不为空
		if (StringUtils.isNotBlank(orgName)) {
			String orgNameTmp = orgName;
			try {
				if (StringUtils.isNotBlank(ecode)) {
					orgNameTmp = ChangeCharset.decode2UTF_8(orgName);
				}
				map.put("orgName", orgNameTmp);
			} catch (Exception e) {
				e.printStackTrace();

			}
			hql.append(" and orgName like  '%" + orgNameTmp + "%' ");
		}
		// 如果工程名称不为空
		if (StringUtils.isNotBlank(projectName)) {
			String projectNameTmp = projectName;
			if (StringUtils.isNotBlank(ecode)) {
				try {
					projectNameTmp = ChangeCharset.decode2UTF_8(projectNameTmp);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			map.put("projectName", projectNameTmp);
			hql.append(" and projectName  like  '%" + projectNameTmp + "%' ");
		}
		// 如果中标单位不为空
		if (StringUtils.isNotBlank(bidSupName)) {
			String bidSupNameTmp = bidSupName;
			if (StringUtils.isNotBlank(ecode)) {
				try {
					bidSupNameTmp = ChangeCharset.decode2UTF_8(bidSupName);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			map.put("bidSupName", bidSupNameTmp);
			hql.append(" and bidSupCd like '%" + bidSupNameTmp + "%' ");
		}
		// 如果计价模式不为空
		if (StringUtils.isNotBlank(calcuMode)) {
			String calcuModeTmp = calcuMode;
			if (StringUtils.isNotBlank(ecode)) {
				try {
					calcuModeTmp = ChangeCharset.decode2UTF_8(calcuMode);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
			map.put("calcuMode", calcuModeTmp);
			hql.append(" and calcuMode like '%" + calcuModeTmp + "%' ");
		}
		try {
			// 如果定标日期不为空
			if (StringUtils.isNotBlank(bidDate)) {
				// hql.append(" and bidDate = :bidDate");
				map.put("bidDate", bidDate);
				hql.append("and bidDate >= to_date(:bidDate,'yyyy-mm-dd') ");
			}
			// 如果定标日期不为空
			if (StringUtils.isNotBlank(bidDateTo)) {
				// hql.append(" and bidDate = :bidDate");
				Calendar c = Calendar.getInstance();
				c.setTime(f.parse(bidDateTo));
				c.add(Calendar.DAY_OF_MONTH, 1);
				map.put("bidDateTo", f.format(c.getTime()));
				hql.append("and  bidDate <= to_date(:bidDateTo,'yyyy-mm-dd') ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 页码信息
		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		// 默认设置为10行每页
		if (StringUtils.isNotBlank(rows)) {
			page.setPageSize(Integer.parseInt(rows));
		}
		if (StringUtils.isNotBlank(pageNo)) {
			page.setPageNo(Integer.parseInt(pageNo));
		}

	} 
	public String loadList() {
		// 前面的步骤参见prepareLoadList方法
		// 分页搜索
		page = ctdbFixedBidManager.findPage(page, hql.toString(), map);
		
		
		// 处理网批查询号
		if(page != null && page.getResult()!= null && page.getResult().size() > 0){
			String resId = null;
			ResApproveInfo res = null;
			for (CtdbFixedBid bid : page.getResult()) {
				resId = bid.getResCd();
				if(StringUtils.isNotBlank(resId)){
					res = resApproveInfoManager.getResApproveInfoById(resId);
					if(res != null &&  res.getDisplayNo() != null){
						bid.setApproveCd(String.valueOf(res.getDisplayNo()));
					}
				}
			}
		}
		
		return "loadList";
	}

	/**
	 * 导出EXCEL搜索准备
	 */
	/*
	 * public void prepareExportToExcel(){ prepareLoadList(); }
	 */

	public String exportToExcel() {
		prepareLoadList();
		// 文件名
		String sheetName = "定标数据库";
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
	 * 导出填充EXCEL
	 */
	private void fillInExcel(String sheetName, ByteArrayOutputStream outExcelFileTmp) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 22);
		sheet.setDefaultRowHeight((short) 25);
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
		// 项目
		PoiExcelTool.addCell(row, cell, text, 0, style4, "1", "项目", null);
		// 工程
		PoiExcelTool.addCell(row, cell, text, 1, style4, "1", "工程", null);
		// 中标单位
		PoiExcelTool.addCell(row, cell, text, 2, style4, "1", "中标单位 ", null);
		// 招标范围
		PoiExcelTool.addCell(row, cell, text, 3, style4, "1", "招标范围", null);
		// 计价模式
		PoiExcelTool.addCell(row, cell, text, 4, style4, "1", "计价模式", null);
		// 施工面积(m2)
		PoiExcelTool.addCell(row, cell, text, 5, style4, "1", "施工面积(m2)", null);
		// 中标价
		PoiExcelTool.addCell(row, cell, text, 6, style4, "1", "中标价", null);
		// 单位造价
		PoiExcelTool.addCell(row, cell, text, 7, style4, "1", "单位造价 ", null);
		// 定标日期
		PoiExcelTool.addCell(row, cell, text, 8, style4, "1", "定标日期", null);
		// 网批编号
		PoiExcelTool.addCell(row, cell, text, 9, style4, "1", "网批编号", null);
		// 搜索
		List<CtdbFixedBid> ctdbFixedBids = ctdbFixedBidManager.find(hql.toString(), map);
		if (ctdbFixedBids != null && ctdbFixedBids.size() > 0) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < ctdbFixedBids.size(); i++) {
				CtdbFixedBid cfb = ctdbFixedBids.get(i);
				row = sheet.createRow(i + 1);
				// 项目
				if (cfb.getOrgName() != null) {
					PoiExcelTool.addCell(row, cell, text, 0, style2, "1", cfb.getOrgName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 0, style2, "1", "", null);
				}
				// 工程
				if (cfb.getProjectName() != null) {
					PoiExcelTool.addCell(row, cell, text, 1, style2, "1", cfb.getProjectName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 1, style2, "1", "", null);
				}
				// 中标单位
				if (cfb.getBidSupCd() != null) {
					PoiExcelTool.addCell(row, cell, text, 2, style2, "1", cfb.getBidSupCd(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 2, style2, "1", "", null);
				}
				// 招标范围
				if (cfb.getBidArea() != null) {
					PoiExcelTool.addCell(row, cell, text, 3, style2, "1", cfb.getBidArea(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 3, style2, "1", "", null);
				}
				// 计价模式
				if (cfb.getCalcuMode() != null) {
					PoiExcelTool.addCell(row, cell, text, 4, style2, "1", cfb.getCalcuMode(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 4, style2, "1", "", null);
				}
				// 施工面积(m2)
				if (cfb.getConstructArea() != null) {
					PoiExcelTool.addCell(row, cell, text, 5, style2, "0", null, cfb.getConstructArea().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 5, style2, "1", "", null);
				}
				// 中标价
				if (cfb.getBidedPrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 6, style2, "0", null, cfb.getBidedPrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 6, style2, "1", "", null);
				}
				// 单位造价
				if (cfb.getUnitPrice() != null) {
					PoiExcelTool.addCell(row, cell, text, 7, style2, "0", null, cfb.getUnitPrice().doubleValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 7, style2, "1", "", null);
				}
				// 定标日期
				if (cfb.getBidDate() != null) {
					PoiExcelTool.addCell(row, cell, text, 8, style2, "1", f.format(cfb.getBidDate()), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 8, style2, "1", "", null);
				}
				// 网批编号
				if (cfb.getApproveCd() != null) {
					PoiExcelTool.addCell(row, cell, text, 9, style2, "1", cfb.getApproveCd(), null);
				}
			}
		}
		try {
			workbook.write(outExcelFileTmp);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook = null;
			}
		}
	}

	/**
	 * 历史定标数据数据导入
	 * 
	 * @return
	 */
	public String importHistory() {
		// 一下为需要导入的定标数据,1、2、3条公用bean和方法
		// 1、定标审批表(战略)----- 定标审批表（项目招标） bid-approve-projectbid.jsp com.hhz.ump.web.res.bean.BidApproveProjectSheet ZCGL_HTQS_80
		// 2、定标审批表（项目招标） bid-approve-projectbid.jsp com.hhz.ump.web.res.bean.BidApproveProjectSheet ZCGL_HTQS_60
		// 3、定标审批表（工程类） bid-approve-project-sheet.jsp com.hhz.ump.web.res.bean.BidApproveProjectSheet ZCGL_HTQS_30
		// 4、定标审批表（设计类） bid-approve-design-sheet.jsp com.hhz.ump.web.res.bean.BidApproveDesignSheet ZCGL_HTQS_50
		// 5、定标审批表（材料及设备类） bid-approve-material-device-sheet.jsp com.hhz.ump.web.res.bean.BidApproveMaterialDeviceSheet ZCGL_HTQS_40
		ctdbFixedBidManager.importHistory();
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
	public CtdbFixedBid getModel() {
		return null;
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

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

}
