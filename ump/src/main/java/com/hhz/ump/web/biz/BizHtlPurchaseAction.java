package com.hhz.ump.web.biz;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.biz.BizHtlPurchaseManager;
import com.hhz.ump.entity.biz.BizHtlPurchase;
import com.hhz.ump.entity.biz.BizRelaLib;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.FormatUtil;

public class BizHtlPurchaseAction extends CrudActionSupport<BizHtlPurchase> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1734631208208212942L;

	@Autowired
	private BizHtlPurchaseManager bizHtlPurchaseManager;

	private BizHtlPurchase entity;
	/**
	 * 搜索条件
	 */
	private String h_hotelName;
	private String hotelName;
	private String itemTypeCd;
	private String itemName;
	private String fromDate;
	private String toDate;
	/**
	 * 上传文件名
	 */
	private File[] upload;
	private String[] uploadFileName;
	private String[] uploadContentType;

	@Override
	public BizHtlPurchase getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer().append(" delete from BizHtlPurchase where 1=1 ");
		if(StringUtils.isNotBlank(hotelName)){
			hql.append(" and hotelCd =:hotelName ");
			param.put("hotelName", hotelName);
		}
		if(StringUtils.isNotBlank(itemTypeCd)){
			hql.append(" and itemTypeCd =:itemTypeCd ");
			param.put("itemTypeCd", itemTypeCd);
		}
		if(StringUtils.isNotBlank(itemName)){
			hql.append(" and itemName  like :itemName");
			param.put("itemName", "%"+itemName+"%");
		}
		if(StringUtils.isNotBlank(fromDate)){
			hql.append(" and fromDate >= to_date(:fromDate,'yyyy-mm-dd')");
			param.put("fromDate",  fromDate);
		} 
		if(StringUtils.isNotBlank(toDate)){
			hql.append(" and toDate <= to_date(:toDate,'yyyy-mm-dd')");
			param.put("toDate",  toDate);
		}
		bizHtlPurchaseManager.getDao().batchExecute(hql.toString(), param);
		Struts2Utils.renderHtml("success");
		return null;
	}

	/**
	 * 采购数据列表
	 */
	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		StringBuffer hql = new StringBuffer().append(" from BizHtlPurchase where 1=1 ");
		if (StringUtils.isNotBlank(hotelName)) {
			hql.append(" and hotelCd =:hotelName ");
			param.put("hotelName", hotelName);
		}
		if (StringUtils.isNotBlank(itemTypeCd)) {
			hql.append(" and itemTypeCd =:itemTypeCd ");
			param.put("itemTypeCd", itemTypeCd);
		}
		if (StringUtils.isNotBlank(itemName)) {
			hql.append(" and itemName  like :itemName");
			param.put("itemName", "%" + itemName + "%");
		}
		if (StringUtils.isNotBlank(fromDate)) {
			hql.append(" and fromDate >= to_date(:fromDate,'yyyy-mm-dd')");
			param.put("fromDate", fromDate);
		}
		if (StringUtils.isNotBlank(toDate)) {
			hql.append(" and toDate <= to_date(:toDate,'yyyy-mm-dd')");
			param.put("toDate", toDate);
		}
		hql.append(" order by hotelName,itemTypeCd,itemName ");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} else {
			page.setPageSize(10);
		}
		// // 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate");
			// page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			// page.setOrder(order + "," + Page.DESC);
		}
		page = bizHtlPurchaseManager.findPage(page, hql.toString(), param);
		return "list";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		bizHtlPurchaseManager.saveBizHtlPurchase(entity);
		Struts2Utils.renderHtml("success");
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			bizHtlPurchaseManager.deleteBizHtlPurchase(getId());
			Struts2Utils.renderHtml("success");
		}

		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = bizHtlPurchaseManager.getEntity(getId());
		} else {
			entity = new BizHtlPurchase();
		}

	}

	/**
	 * 编辑采购数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bizPurchaseDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bizHtlPurchaseManager.getEntity(getId());
		}
		return "bizPurchaseDetail";
	}

	/**
	 * 导入采购数据页面
	 * 
	 * @return
	 */
	public String bizPurchaseImport() throws Exception {
		return "import";
	}

	/**
	 * 验证并导入采购数据
	 * 
	 * @return
	 */
	private String importExl() {

		long begin = new Date().getTime();
		StringBuffer error = new StringBuffer().append("");
		Workbook book = null;
		Sheet se = null;
		Cell[] cells = null;
		BizRelaLib info = null;
		// 指定开始解析行号
		int overlookno = 0;
		// 指定解析的工作薄
		Integer sheetNow = 0;
		//酒店CD
		String importHotelCd = Struts2Utils.getParameter("hotelCd");
		//酒店名称
		String importHotelName = CodeNameUtil.getDictNameByCd(DictContants.BIZ_HOTEL_NAME, importHotelCd);
		String fromDateAll = null;
		String toDateAll = null;
		String importItemCd = null;
		// 记录失败
		try {
			book = Workbook.getWorkbook(upload[0]);
			int sheetArg = book.getNumberOfSheets();
			// String sheetName=CodeNameUtil.getDictNameByCd(DictContants.BIZ_PURCHASE_ITEM_TYPE, itemTypeCd);
			for (int j = 0; j < sheetArg; j++) {
				se = book.getSheet(j);// 得到第1个sheet(Excel通常会有3个Sheet)
				int rownum = se.getRows();// 得到总行数
				for (int i = overlookno; i < rownum; i++) {
					cells = se.getRow(i);
					if (i == 0) {
						importItemCd = CodeNameUtil.getDictCdByName(DictContants.BIZ_PURCHASE_ITEM_TYPE, cells[3]
								.getContents().trim());
						continue;
					}
					if (i == 1) {
						fromDateAll = cells[1].getContents();
						toDateAll = cells[3].getContents();
						continue;
					}
					if (i == 2) {
						continue;
					}
					entity = new BizHtlPurchase();
					entity.setHotelName(importHotelName);
					entity.setHotelCd(importHotelCd);
					entity.setFromDate(DateUtil.parseStringToDate(fromDateAll));
					entity.setToDate(DateUtil.parseStringToDate(toDateAll));
					entity.setItemTypeCd(importItemCd);
					if (cells[0].getContents().trim() == null || "".equals(cells[0].getContents().trim())) {
						break;
					}
					entity.setItemName(cells[0].getContents());
					entity.setUnitName(cells[1].getContents());
					String cells2 = StringUtils.trim(cells[2].getContents());
					String cells3 = StringUtils.trim(cells[3].getContents());
					String cells4 = StringUtils.trim(cells[4].getContents());
					Double  fltCell2=FormatUtil.formatDouble(cells2);
					entity.setMarketPrice(new BigDecimal(fltCell2));
					entity.setLastPrice(new BigDecimal(FormatUtil.formatDouble(cells3)));
					entity.setPrice(new BigDecimal(FormatUtil.formatDouble(cells4)));
					bizHtlPurchaseManager.saveBizHtlPurchase(entity);
				}
			}
			long end = new Date().getTime();
			System.out.println("导入用时-JXL：" + (end - begin) + "毫秒");//12733 305
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("导入失败");
			return null;
		} finally {
			if (book != null) {
				book.close();
			}
			upload[0].delete();
		}
		if (error.toString().equals("")) {
			Struts2Utils.renderHtml("success");
		} else {
			Struts2Utils.renderHtml("导入成功;请重新进入");
		}
		return null;
	}
	
	/**
	 * 验证并导入采购数据-POI
	 * @param hotelCd 酒店CD
	 * @return
	 * 
	 * @author liuzhihui 2012-02-17
	 */
	public String importExlPoi() {
		long begin = new Date().getTime();
		//酒店CD
		String importHotelCd = Struts2Utils.getParameter("hotelCd");
		//酒店名称
		String importHotelName = CodeNameUtil.getDictNameByCd(DictContants.BIZ_HOTEL_NAME, importHotelCd);
		HSSFWorkbook hssfWorkbook = null;
		HSSFSheet hssfSheet = null;
		HSSFRow hssfRow = null;
		Date fromDateAll = null;
		Date toDateAll = null;
		String importItemCd = null;
		try {
			hssfWorkbook = new HSSFWorkbook(new FileInputStream(upload[0]));
			// sheet总数
			int sheetCount = hssfWorkbook.getNumberOfSheets();
			for (int i = 0; i < sheetCount; i++) {
				hssfSheet = hssfWorkbook.getSheetAt(i);
				//一个sheet下的总行数
				int countRow = hssfSheet.getLastRowNum();
				for (int row = 0; row <= countRow; row++) {
					hssfRow = hssfSheet.getRow(row);
					if (row == 0) {//excel第1行
						importItemCd = CodeNameUtil.getDictCdByName(DictContants.BIZ_PURCHASE_ITEM_TYPE, hssfRow.getCell(3).getStringCellValue().trim());
						continue;
					}
					if (row == 1) {//excel第2行
						fromDateAll = getDateCellValuePoi(hssfRow.getCell(1).getDateCellValue());
						toDateAll = getDateCellValuePoi(hssfRow.getCell(3).getDateCellValue());
						continue;
					}
					if (row == 2) {//excel第3行
						continue;
					}
					if (hssfRow.getCell(0) != null && !"".equals(hssfRow.getCell(0))) {
						if(StringUtils.isNotBlank(hssfRow.getCell(0).getStringCellValue())){
							entity = new BizHtlPurchase();
							entity.setHotelCd(importHotelCd);
							entity.setHotelName(importHotelName);
							entity.setFromDate(fromDateAll);
							entity.setToDate(toDateAll);
							entity.setItemTypeCd(importItemCd);
							if(hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
								String cells0 = getStringCellValuePoi(hssfRow.getCell(0).getStringCellValue());
								entity.setItemName(cells0);
							}
							if(hssfRow.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING){
								String cells1 = getStringCellValuePoi(hssfRow.getCell(1).getStringCellValue());
								entity.setUnitName(cells1);
							}
							if(hssfRow.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								BigDecimal cells2 = new BigDecimal(hssfRow.getCell(2).getNumericCellValue());
								entity.setMarketPrice(cells2);
							}else if(hssfRow.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING){
								String cells2Str = getStringCellValuePoi(hssfRow.getCell(2).getStringCellValue());
								if(StringUtils.isNotBlank(cells2Str)){
									entity.setMarketPrice(new BigDecimal(cells2Str));
								}
							}
							if(hssfRow.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								BigDecimal cells3 = new BigDecimal(hssfRow.getCell(3).getNumericCellValue());
								entity.setLastPrice(cells3);
							}else if(hssfRow.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING){
								String cells3Str = getStringCellValuePoi(hssfRow.getCell(3).getStringCellValue());
								if(StringUtils.isNotBlank(cells3Str)){
									entity.setLastPrice(new BigDecimal(cells3Str));
								}
							}
							if(hssfRow.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								BigDecimal cells4 = new BigDecimal(hssfRow.getCell(4).getNumericCellValue());
								entity.setPrice(cells4);
							}else if(hssfRow.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING){
								String cells4Str = getStringCellValuePoi(hssfRow.getCell(4).getStringCellValue());
								if(StringUtils.isNotBlank(cells4Str)){
									entity.setPrice(new BigDecimal(cells4Str));
								}
							}
							bizHtlPurchaseManager.saveBizHtlPurchase(entity);
						}
					}else{
						break;
					}
				}
			}
			long end = new Date().getTime();
			System.out.println("导入用时-POI：" + (end - begin) + "毫秒");//比jxl快2-4倍
			Struts2Utils.renderHtml("success");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderHtml("error");
		}finally{
			upload[0].delete();
		}
		return null;
	}

	
	public Date getDateCellValuePoi(Date date){
		if(date!=null && !"".equals(String.valueOf(date)))
			return date ;
		return null;
	}
	public String getStringCellValuePoi(String str){
		if(StringUtils.isNotBlank(str.trim()))
			return str ;
		return "";
	}

	public void setEntity(BizHtlPurchase entity) {
		this.entity = entity;
	}

	public BizHtlPurchase getEntity() {
		return entity;
	}

	public String getH_hotelName() {
		return h_hotelName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setH_hotelName(String h_hotelName) {
		this.h_hotelName = h_hotelName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getItemTypeCd() {
		return itemTypeCd;
	}

	public void setItemTypeCd(String itemTypeCd) {
		this.itemTypeCd = itemTypeCd;
	}

	public File[] getUpload() {
		return upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

}
