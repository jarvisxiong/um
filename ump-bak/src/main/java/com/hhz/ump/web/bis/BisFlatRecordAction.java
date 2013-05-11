package com.hhz.ump.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisFlatRecordManager;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFlatRecord;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.vo.BisFlatRecordVo;
import com.hhz.ump.web.vo.VoFlatCount;

/**
 * 公寓（住宅底商）收费记录
 * 
 * @author liuzhihui 2012-05-29
 * @version 1.0
 */
@Namespace("/bis")
@Results( {@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile", "contentDisposition",
				"attachment;filename=${excelFileName}.xls" }) 
})
public class BisFlatRecordAction extends CrudActionSupport<BisFlatRecord> {

	@Autowired
	private BisFlatRecordManager bisFlatRecordManager;
	@Autowired
	private BisFlatManager bisFlatManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisFloorManager bisFloorManager;

	private Page<BisFlat> pageFlat = new Page<BisFlat>(20);
	
	private BisFlatRecord entity;
	private BisFlat bisFlat;
	private String bisProjectId;
	private String bisProjectName;
	private String bisFlatId;
	private String costType;
	private String yearMonth;
	private String bisFloorId;
	private String bisFloorName;
	private String flatNo;
	
	//合计费用
	private VoFlatCount voFlatCount;
	private BisFlatRecordVo voBisFlatRecord;
	
	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;
	private File importFile;

	

	/**
	 * 查询公寓数据
	 */
	@Override
	public String list() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();//and a.flatNo like '%test%'
		hql.append(" from BisFlat a  where 1=1");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and a.bisProjectId =:bisProjectId");
			params.put("bisProjectId", bisProjectId.trim());
		}
		if (StringUtils.isNotBlank(flatNo)) {
			hql.append(" and a.flatNo like :flatNo");
			params.put("flatNo", "%"+flatNo.trim()+"%");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] floorIds = bisFloorId.split(",");
			List<String> list = new ArrayList<String>(); 
			if(floorIds != null && floorIds.length > 0){
				for (int i = 0; i < floorIds.length; i++) {
					list.add(floorIds[i]);
				}
			}
			String[] bisFloorIds = list.toArray(new String[list.size()]); 
			hql.append(" and a.bisFloor.bisFloorId in(:bisFloorIds)");
			if(list.size() > 0){
				params.put("bisFloorIds", bisFloorIds);
			}else{
				params.put("bisFloorIds", "");
			}
		}
		hql.append(" order by a.bisFloor.bisFloorId desc,flatNo asc");
		pageFlat = bisFlatManager.findPage(pageFlat, hql.toString(), params);
		//查询4个累计值总和
		voFlatCount = bisFlatManager.getAnnualCount(bisProjectId,costType,bisFloorId,flatNo);
		return "list";
	}
	
	/**
	 * 根据公寓ID和收费类型得到收费记录
	 * @return
	 * @throws Exception
	 */
	public String loadRecordByFlatId() throws Exception{
		String year = Struts2Utils.getParameter("recordYear");
		String month = Struts2Utils.getParameter("recordMonth");
		if (StringUtils.isNotBlank(bisFlatId)) {
			bisFlat =  bisFlatManager.getEntity(bisFlatId);
		}
		StringBuffer hql = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("from BisFlatRecord t  where 1=1 ");
		if (StringUtils.isNotBlank(bisFlatId)) {
			hql.append(" and t.bisFlat.bisFlatId =:bisFlatId");
			params.put("bisFlatId", bisFlatId.trim());
		}
		if (StringUtils.isNotBlank(costType)) {
			hql.append(" and t.costType =:costType");
			params.put("costType", costType.trim());
		}
		if (StringUtils.isNotBlank(year)) {
			hql.append(" and t.recordYear =:recordYear");
			params.put("recordYear", Long.valueOf(year));
		}
		if (StringUtils.isNotBlank(month)) {
			hql.append(" and t.recordMonth =:recordMonth");
			params.put("recordMonth", Long.valueOf(month));
		}
		hql.append(" order by t.recordYear desc,t.recordMonth desc");
		page.setPageSize(20);
		page = bisFlatRecordManager.findPage(page, hql.toString(), params);
		return "recordList";
	}
	
	/**
	 * 查看公寓收费记录总计
	 * @return
	 * @throws Exception
	 */
	public String loadRecordCount() throws Exception{
		if(StringUtils.isNotBlank(bisProjectId) && StringUtils.isNotBlank(costType) && StringUtils.isNotBlank(yearMonth)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bisProjectId", bisProjectId);
			params.put("costType", costType);
			params.put("yearMonth", yearMonth);
			params.put("bisFloorId", bisFloorId);
			params.put("flatNo", flatNo);
			voBisFlatRecord = bisFlatRecordManager.getBisFlatRecordCount(params);
		}
		return "count";
	}
	
	
	/**
	 * 导入数据模板
	 * @return
	 * @throws Exception
	 */
	public String importDataPoi() throws Exception{
		long begin = new Date().getTime();
		String tmpCostType = null; //费用类别
		int insert = 0;
		int update = 0;
		int r = 0;
		int c = 0;

		int row = 0;
		int col = 0;
		try {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			String sheetName = hssfSheet.getSheetName();
			//得到费用类别  （数据字典中：4、5、6分别为物管费、水费、电费）
			if(StringUtils.isNotBlank(sheetName) && "物管费".equals(sheetName.trim())){
				tmpCostType = "4";
			}else if(StringUtils.isNotBlank(sheetName) && "水费".equals(sheetName.trim())){
				tmpCostType = "5";
			}else if(StringUtils.isNotBlank(sheetName) && "电费".equals(sheetName.trim())){
				tmpCostType = "6";
			}
			this.costType=tmpCostType;
			HSSFRow firstrow = hssfSheet.getRow(0);
			//得到年月
			String ym = firstrow.getCell(14).getStringCellValue();
			String[] date = ym.split("-");
			String year = date[0];
			String month = date[1];
			if (Integer.valueOf(month) < 10) {
				month = date[1].replace("0", "");
			}
			List<BisFlatRecord> flatRecordList = new ArrayList<BisFlatRecord>();
			List<BisFlat> bisFlatList = new ArrayList<BisFlat>();
			//开始读取数据
			for (row = 2; row <= hssfSheet.getLastRowNum(); row++) {
				HSSFRow hssfRow = hssfSheet.getRow(row);
				//某些有空格或者改动过的空行直接跳过
				if(hssfRow != null){
					if(hssfRow.getCell(0) != null){
						if (hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							continue;
						}
					}else{
						continue;
					}
				}else{
					continue;
				}
				//得到第一列影藏的’公寓ID‘
				String hiddenFlatIdId = hssfRow.getCell(0).getStringCellValue();
				if(StringUtils.isNotBlank(hiddenFlatIdId)){
					//得到公寓
					BisFlat tmpBisFlat = bisFlatManager.getEntity(hiddenFlatIdId);
					if(tmpBisFlat == null){
						Struts2Utils.renderText(",error, 未导入成功，第" + (row + 1) + "行的公寓信息已在基础数据中被删除，请删除该行在导入数据,");
						return null;
					}
					// 从第13列取得公寓收费记录
					BisFlatRecord bisFlatRecord = bisFlatRecordManager.getExistsFlatRecord(hiddenFlatIdId,tmpCostType,year,month);
					//查询数据是否存在(存在则直接覆盖，不存在则添加)
					if(bisFlatRecord == null){
						insert++;
						bisFlatRecord = new BisFlatRecord();
					}else{
						update++;
					}
					for (col = 10; col < hssfRow.getLastCellNum(); col++) {
						c = col;
						HSSFCell hssfCell = hssfRow.getCell(col);
						if(hssfCell!=null){
							// 15、16列为‘缴费跨期’、‘本月是否起收’列 ，可为字符串，否则非字符串
							if(col != 15 && col != 16){
								String cellValue = null;
								if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
										|| hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
									cellValue = hssfCell.getStringCellValue();
								}
								if(StringUtils.isNotBlank(cellValue)){
									if (hssfCell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC){
										Struts2Utils.renderText(",error, 未导入成功，请于第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列检查是否为数字类型,");
										return null;
									}
								}
							}
							if(col >= 14){
								//收费记录列
								bisFlatRecord.setCostType(tmpCostType);
								bisFlatRecord.setRecordYear(Long.valueOf(year));
								bisFlatRecord.setRecordMonth(Long.valueOf(month));
								if(tmpBisFlat!=null){
									bisFlatRecord.setBisFlat(tmpBisFlat);
								}
								this.doSetRecordValue(bisFlatRecord, col, hssfCell);
							}else{
								//公寓信息列
								this.doSetBisFlatValue(tmpBisFlat, col, hssfCell);
							}
						}
					}
					bisFlatList.add(tmpBisFlat);
					flatRecordList.add(bisFlatRecord);
				}
			}
			bisFlatRecordManager.batchExecute(bisFlatList,flatRecordList);
			long end = new Date().getTime();
			Struts2Utils.renderText(",success," + insert + "," + update + "," + (end - begin) / 1000 + ",");
		} catch (Exception e) {
			e.printStackTrace();
			String errorLocation = (r + 1) + "行 " + BisConstants.letters.get(c + 1) + "列";
			Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
		}
		return null;
	}
	
	/**
	 * 导出Excel模板
	 * @return
	 * @throws Exception
	 */
	public String exportTemplate() throws Exception{
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		excelFile = buildTemplateExcel();
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	//构建导出excel文件流
	private InputStream buildTemplateExcel() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);
		if (wbook != null) {
			try {
				initTemplateData(wbook);
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
	private void initTemplateData(WritableWorkbook wbook) throws Exception {
		String costTypeName = null;
		if(StringUtils.isNotBlank(costType)){
			if("4".equals(costType)){
				costTypeName = "物管费";
			}else if("5".equals(costType)){
				costTypeName = "水费";
			}else if("6".equals(costType)){
				costTypeName = "电费";
			}else{
				costTypeName = "费用";
			}
		}else{
			costTypeName = "费用";
		}
		//模板名称
		excelFileName = "公寓收费记录模板-" + costTypeName + "收费台账("+yearMonth+")";
		//设置工作表名称
		WritableSheet wSheet = wbook.createSheet(costTypeName, 0);
		//冻结第几行第几列
		wSheet.getSettings().setHorizontalFreeze(5); //冻结列
		wSheet.getSettings().setVerticalFreeze(2); //冻结行
		wSheet.getSettings().setProtected(true);  //设置是否受保护
		initRecordSheet(wSheet);
	}
	
	/**
	 * 公寓（住宅底商）收费记录-导出模板
	 */
	public void initRecordSheet(WritableSheet wsheet) throws Exception {
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);
		
		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2.setBorder(Border.ALL, BorderLineStyle.THIN);//边框为实体线
		format_head2.setWrap(true);//自动换行
		
		// 二级标题样式（黄色）
		WritableCellFormat format_head2_1 = new WritableCellFormat(font_Bold_10);
		format_head2_1.setAlignment(Alignment.CENTRE);
		format_head2_1.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2_1.setBackground(Colour.YELLOW);
		format_head2_1.setBorder(Border.ALL, BorderLineStyle.THIN); 
		format_head2_1.setWrap(true); 
		
		// 数字输入框（加锁）
		WritableCellFormat format_lock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_lock.setLocked(true); // 加锁
		format_lock.setAlignment(Alignment.RIGHT);
		format_lock.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 数字输入框(不加锁)
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);
		format_unlock.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 数字输入框(不加锁-黄色)
		WritableCellFormat format_unlock_1 = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock_1.setLocked(false); // 不加锁
		format_unlock_1.setAlignment(Alignment.RIGHT);
		format_unlock_1.setBackground(Colour.YELLOW);
		format_unlock_1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 数字输入框(加锁-黄色)
		WritableCellFormat format_lock_1 = new WritableCellFormat(NumberFormats.FORMAT3);
		format_lock_1.setLocked(true); // 不加锁
		format_lock_1.setAlignment(Alignment.RIGHT);
		format_lock_1.setBackground(Colour.YELLOW);
		format_lock_1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.CENTRE);
		format_label.setBorder(Border.ALL, BorderLineStyle.THIN);
		format_label.setWrap(true); 
		
		StringBuffer firstColStr = new StringBuffer(bisProjectName+"_公寓/住宅底商");
		if("4".equals(costType)){
			firstColStr.append("物管费");
		}else if("5".equals(costType)){
			firstColStr.append("水费");
		}else if("6".equals(costType)){
			firstColStr.append("电费");
		}
		firstColStr.append("收费记录("+yearMonth+")");
		//添加第一行数据
		wsheet.addCell(new Label(0, 0, firstColStr.toString(), format_head1));
		//添加第二行列头数据
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.addCell(new Label(1, 1, "序号", format_head2));
		wsheet.addCell(new Label(2, 1, "客户名称", format_head2));
		wsheet.addCell(new Label(3, 1, "楼栋名称", format_head2));
		wsheet.addCell(new Label(4, 1, "房号", format_head2));
		wsheet.addCell(new Label(5, 1, "建筑面积", format_head2));
		wsheet.addCell(new Label(6, 1, "套内面积", format_head2));
		wsheet.addCell(new Label(7, 1, "实测建筑面积", format_head2));
		wsheet.addCell(new Label(8, 1, "实测套内面积", format_head2));
		wsheet.addCell(new Label(9, 1, "月物管费单价", format_head2));
		wsheet.addCell(new Label(10, 1, "以前年度累计应收", format_head2_1));
		wsheet.addCell(new Label(11, 1, "以前年度累计实收", format_head2_1));
		wsheet.addCell(new Label(12, 1, "以前年度累计预收", format_head2_1));
		wsheet.addCell(new Label(13, 1, "以前年度累计欠收", format_head2_1));

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 5);
		wsheet.setColumnView(2, 10);
		wsheet.setColumnView(3, 10);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);
		wsheet.setColumnView(6, 10);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 10);
		wsheet.setColumnView(9, 10);
		wsheet.setColumnView(10, 10);
		wsheet.setColumnView(11, 10);
		wsheet.setColumnView(12, 10);
		wsheet.setColumnView(13, 10);

		int col = 13;
		Map<String, String> recordMap = this.getMapRecordCols();
		for (Entry<String, String> entry : recordMap.entrySet()) {
			wsheet.addCell(new Label(++col, 1, entry.getValue(), format_head2_1));
			wsheet.setColumnView(col, 15);
		}
		//设置行高
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		//wsheet.setRowView(2, 1);
		//设置第一行合并列
		wsheet.mergeCells(0, 0, 13, 0);
		wsheet.addCell(new Label(14, 0, yearMonth, format_head1));
		wsheet.mergeCells(14, 0, recordMap.size()+13, 0);
		
		// 查询公寓表数据
		List<BisFlat> flatList = this.getBisFlatList();
		int row = 2;
		BigDecimal square = new BigDecimal(0);
		BigDecimal innerSquare = new BigDecimal(0);
		BigDecimal actualSquare = new BigDecimal(0);
		BigDecimal actualInnerSquare = new BigDecimal(0);
		BigDecimal monPromanfeePrice = new BigDecimal(0);
		for (int i = 0; i < flatList.size(); i++) {
			BisFlat tmpBisFlat = flatList.get(i);
			if(tmpBisFlat != null){
				square = square.add(getBigDecimalValue(tmpBisFlat.getSquare()));
				innerSquare = innerSquare.add(getBigDecimalValue(tmpBisFlat.getInnerSquare()));
				actualSquare = actualSquare.add(getBigDecimalValue(tmpBisFlat.getActualSquare()));
				actualInnerSquare = actualInnerSquare.add(getBigDecimalValue(tmpBisFlat.getActualInnerSquare()));
				monPromanfeePrice = monPromanfeePrice.add(getBigDecimalValue(tmpBisFlat.getMonPromanfeePrice()));
				
				String hiddenIds = tmpBisFlat.getBisFlatId();
				wsheet.addCell(new Label(0, row, hiddenIds, format_label)); //影藏列（公寓ID）
				wsheet.addCell(new Label(1, row, (i+1)+"", format_label)); //序号
				wsheet.addCell(new Label(2, row, tmpBisFlat.getCustomerName(), format_label));
				wsheet.addCell(new Label(3, row, tmpBisFlat.getBisFloor().getBuildingNum(), format_label));
				wsheet.addCell(new Label(4, row, tmpBisFlat.getFlatNo(), format_label));
				wsheet.addCell(new Label(5, row, formateBigDecimal(tmpBisFlat.getSquare()), format_lock));
				wsheet.addCell(new Label(6, row, formateBigDecimal(tmpBisFlat.getInnerSquare()), format_lock));
				wsheet.addCell(new Label(7, row, formateBigDecimal(tmpBisFlat.getActualSquare()), format_lock));
				wsheet.addCell(new Label(8, row, formateBigDecimal(tmpBisFlat.getActualInnerSquare()), format_lock));
				wsheet.addCell(new Label(9, row, formateBigDecimal(tmpBisFlat.getMonPromanfeePrice()), format_lock));
				//公寓信息后4个字段可编辑、导入时也要导入到公寓表对应字段
				wsheet.addCell(new Label(10, row, "", format_unlock_1));
				wsheet.addCell(new Label(11, row, "", format_unlock_1));
				wsheet.addCell(new Label(12, row, "", format_unlock_1));
				wsheet.addCell(new Label(13, row, "", format_unlock_1));
				int col2 = 13;
				for (int j = 0; j < recordMap.size(); j++) {
					wsheet.addCell(new Label(++col2, row, "", format_unlock_1));
				}
				wsheet.setRowView(row++, 600);
			}
		}
		
		//合计行
		int rowCount = wsheet.getRows();
		wsheet.addCell(new Label(0, rowCount, "", format_unlock_1));
		wsheet.addCell(new Label(1, rowCount, "合计", format_unlock_1));
		wsheet.addCell(new Label(2, rowCount, "", format_unlock_1));
		wsheet.addCell(new Label(3, rowCount, "", format_unlock_1));
		wsheet.addCell(new Label(4, rowCount, "", format_unlock_1));
		wsheet.addCell(new Label(5, rowCount, square.toString(), format_lock_1));
		wsheet.addCell(new Label(6, rowCount, innerSquare.toString(), format_lock_1));
		wsheet.addCell(new Label(7, rowCount, actualSquare.toString(), format_lock_1));
		wsheet.addCell(new Label(8, rowCount, actualInnerSquare.toString(), format_lock_1));
		wsheet.addCell(new Label(9, rowCount, monPromanfeePrice.toString(), format_lock_1));
		wsheet.addCell(getFormula(10, rowCount, format_unlock_1));
		wsheet.addCell(getFormula(11, rowCount, format_unlock_1));
		wsheet.addCell(getFormula(12, rowCount, format_unlock_1));
		wsheet.addCell(getFormula(13, rowCount, format_unlock_1));
		int col3 = 14;
		for (int j = 0; j < recordMap.size(); j++) {
			int tmpCol = col3++;
			//’缴费跨期‘、’本月是否起收‘列是字符串，无总和
			if(tmpCol == 15 || tmpCol ==16){
				wsheet.addCell(new Label(tmpCol, rowCount, "", format_unlock_1));
			}else{
				wsheet.addCell(getFormula(tmpCol, rowCount, format_unlock_1));
			}
		}
		wsheet.setRowView(rowCount, 500);
	}
	
	/**
	 * 导出模板-查询公寓数据
	 * @return
	 * @throws Exception
	 */
	private List<BisFlat> getBisFlatList() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisFlat t where t.bisProjectId=:bisProjectId");
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] floorIds = bisFloorId.split(",");
			List<String> list = new ArrayList<String>(); 
			if(floorIds != null && floorIds.length > 0){
				for (int i = 0; i < floorIds.length; i++) {
					list.add(floorIds[i]);
				}
			}
			String[] bisFloorIds = list.toArray(new String[list.size()]); 
			hql.append(" and t.bisFloor.bisFloorId in(:bisFloorIds)");
			if(list.size() > 0){
				params.put("bisFloorIds", bisFloorIds);
			}else{
				params.put("bisFloorIds", "");
			}
		}
		hql.append(" order by t.bisFloor.buildingNum asc , t.flatNo asc");
		params.put("bisProjectId", bisProjectId);
		List<BisFlat> flatList = bisFlatManager.find(hql.toString(), params);
		return flatList;
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
	protected Formula getFormula(int col, int rowCount,WritableCellFormat cellFormat) throws Exception{
		String colName = BisConstants.letters.get(col + 1);
		if(StringUtils.isNotBlank(colName)){ 
			//计算某一列总和公式(该excel从第3列开始)   SUM(A1:A10)
			String formulaStr = "SUM("+colName+"3:"+colName+rowCount+")";
			Formula f = new Formula(col, rowCount, formulaStr, cellFormat);
			return f;
		}
		return null;
	}
		
	/**
	 * 
	 *本月实收	缴费跨期	本月是否起收	 本月水费吨数 	 本月应收 	 本月已收 	 本月收回本月 	 本月预收 	 本月收回本年度欠款 	 
	 *本月收回以前年度欠款 	 本月欠收水费 	 本年累计应收 	 累计应收 	 本年累计实收 	 累计实收 	 本年收回本年水费 	 本年度预收水费 	
	 *累计收回以前年度欠费 	 累计预收 	 本年累计欠收 	 累计欠收 
	 *
	 * 记录表列
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getMapRecordCols() throws Exception{
		//4、5、6分别为物管费、水费、电费
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "本月实收");
		map.put("2", "缴费跨期");
		map.put("3", "本月是否起收");
		if("5".equals(costType)){
			map.put("4", "本月水费吨数");
		}else if("6".equals(costType)){
			map.put("4", "本月电费度数");
		}
		map.put("5", "本月应收");
		map.put("6", "本月已收");
		map.put("7", "本月收回本月");
		map.put("8", "本月预收");
		map.put("9", "本月收回本年度欠款");
		map.put("10", "本月收回以前年度欠款");
		if("4".equals(costType)){ 
			map.put("11", "本月欠收物管费");
		}else if("5".equals(costType)){
			map.put("11", "本月欠收水费");
		}else if("6".equals(costType)){
			map.put("11", "本月欠收电费");
		}
		map.put("12", "本年累计应收");
		map.put("13", "累计应收");
		map.put("14", "本年累计实收");
		map.put("15", "累计实收");
		if("4".equals(costType)){ 
			map.put("16", "本年收回本年物管费");
			map.put("17", "本年度预收物管费");
		}else if("5".equals(costType)){
			map.put("16", "本年收回本年水费");
			map.put("17", "本年度预收水费");
		}else if("6".equals(costType)){
			map.put("16", "本年收回本年电费");
			map.put("17", "本年度预收电费");
		}
		map.put("18", "累计收回以前年度欠费");
		map.put("19", "累计预收");
		map.put("20", "本年累计欠收");
		map.put("21", "累计欠收");
		return map;
	}
	/**
	 * 格式化BigDecimal数据为字符串
	 * @param num
	 * @return
	 * @throws Exception
	 */
	protected String formateBigDecimal(BigDecimal num) throws Exception{
		String retStr = null;
		BigDecimal defaultVlaue = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(num != null){
			defaultVlaue = num;
			defaultVlaue.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		retStr = defaultVlaue.toString();
		return retStr;
	}
	/**
	 * 格式化BigDecimal数据
	 * @param num
	 * @return
	 * @throws Exception
	 */
	protected BigDecimal getBigDecimalValue(BigDecimal num) throws Exception{
		BigDecimal defaultVlaue = new BigDecimal(0);
		if(num == null)
			return defaultVlaue;
		num.setScale(2, BigDecimal.ROUND_HALF_UP);
		return num;
	}
	


	//设置公寓记录值
	protected BisFlat doSetBisFlatValue(BisFlat tmpBisFlat, int col, HSSFCell hssfCell) {
		BigDecimal cellValueBig = new BigDecimal(0);
		if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			cellValueBig = new BigDecimal(hssfCell.getNumericCellValue());
		}
		//物管费（以前年度累计应收、以前年度累计实收、以前年度累计预收、以前年度累计欠收）
		if("4".equals(costType)){
			if(col == 10){
				tmpBisFlat.setAnnualYsMan(cellValueBig);
			}if(col == 11){
				tmpBisFlat.setAnnualSsMan(cellValueBig);
			}if(col == 12){
				tmpBisFlat.setAnnualYusMan(cellValueBig);
			}if(col == 13){
				tmpBisFlat.setAnnualQsMan(cellValueBig);
			}
		}else if("5".equals(costType)){//水费
			if(col == 10){
				tmpBisFlat.setAnnualYsWater(cellValueBig);
			}if(col == 11){
				tmpBisFlat.setAnnualSsWater(cellValueBig);
			}if(col == 12){
				tmpBisFlat.setAnnualYusWater(cellValueBig);
			}if(col == 13){
				tmpBisFlat.setAnnualQsWater(cellValueBig);
			}
		}else if("6".equals(costType)){//电费
			if(col == 10){
				tmpBisFlat.setAnnualYsElec(cellValueBig);
			}if(col == 11){
				tmpBisFlat.setAnnualSsElec(cellValueBig);
			}if(col == 12){
				tmpBisFlat.setAnnualYusElec(cellValueBig);
			}if(col == 13){
				tmpBisFlat.setAnnualQsElec(cellValueBig);
			}
		}
		return tmpBisFlat;
	}

	//设置收费记录的值
	protected BisFlatRecord doSetRecordValue(BisFlatRecord bisFlatRecord,int col,HSSFCell hssfCell) throws Exception{
		BigDecimal cellValueBig = new BigDecimal(0);
		String cellValueStr = "";
		if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			cellValueBig = new BigDecimal(hssfCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
			cellValueStr = hssfCell.getStringCellValue().trim();
		}
		if(StringUtils.isBlank(cellValueStr)){
			cellValueStr=cellValueBig.toString();
		}
		if(col == 14){
			bisFlatRecord.setMonthSs(cellValueBig); //本月实收
		}if(col == 15){
			bisFlatRecord.setPaymentIntertemporal(cellValueStr); //缴费跨期
		}if(col == 16){
			bisFlatRecord.setChargeFlg(cellValueStr); //本月是否起收
		}
		//物管费
		if("4".equals(costType)){
			if(col == 17){
				bisFlatRecord.setMonthYs(cellValueBig); //本月应收
			}if(col == 18){
				bisFlatRecord.setMonthYis(cellValueBig); //本月已收
			}if(col == 19){
				bisFlatRecord.setMonthMonth(cellValueBig); //本月收回本月 
			}if(col == 20){
				bisFlatRecord.setMonthYus(cellValueBig); //本月预收
			}if(col == 21){
				bisFlatRecord.setCurrYearDebt(cellValueBig); //本月收回本年度欠款
			}if(col == 22){
				bisFlatRecord.setAgoYearDebt(cellValueBig); //本月收回以前年度欠款
			}if(col == 23){
				bisFlatRecord.setOwePropRate(cellValueBig); //本月欠收物管费
			}if(col == 24){
				bisFlatRecord.setCurrYearYsTotal(cellValueBig); //本年累计应收
			}if(col == 25){
				bisFlatRecord.setYsTotal(cellValueBig);//累计应收
			}if(col == 26){
				bisFlatRecord.setCurrYearSsTotal(cellValueBig);//本年累计实收
			}if(col == 27){
				bisFlatRecord.setSsTotal(cellValueBig); //累计实收
			}if(col == 28){
				bisFlatRecord.setCurrYearPropRate(cellValueBig);// 本年收回本年物管费
			}if(col == 29){
				bisFlatRecord.setCurrYearYusPropRate(cellValueBig);//本年度预收物管费
			}if(col == 30){
				bisFlatRecord.setAgoYearOwnfeeTotal(cellValueBig); //累计收回以前年度欠费
			}if(col == 31){
				bisFlatRecord.setYusTotal(cellValueBig); // 累计预收
			}if(col == 32){
				bisFlatRecord.setCurrYearQsTotal(cellValueBig); // 本年累计欠收
			}if(col == 33){
				bisFlatRecord.setQsTotal(cellValueBig); //累计欠收
			}
		}else{
			//水费、电费
			if(col == 17){
				if("5".equals(costType)){
					bisFlatRecord.setWaterTonnage(cellValueBig);//本月水费吨数
				}
				if("6".equals(costType)){
					bisFlatRecord.setElecDegree(cellValueBig);//本月电费度数
				}
			}
			if(col == 18){
				bisFlatRecord.setMonthYs(cellValueBig); //本月应收
			}if(col == 19){
				bisFlatRecord.setMonthYis(cellValueBig); //本月已收
			}if(col == 20){
				bisFlatRecord.setMonthMonth(cellValueBig); //本月收回本月 
			}if(col == 21){
				bisFlatRecord.setMonthYus(cellValueBig); //本月预收
			}if(col == 22){
				bisFlatRecord.setCurrYearDebt(cellValueBig); //本月收回本年度欠款
			}if(col == 23){
				bisFlatRecord.setAgoYearDebt(cellValueBig); //本月收回以前年度欠款
			}if(col == 24){
				if("5".equals(costType)){
					bisFlatRecord.setOweWaterRate(cellValueBig); //本月欠收水费
				}
				if("6".equals(costType)){
					bisFlatRecord.setOweElecRate(cellValueBig); //本月欠收电费
				}
			}if(col == 25){
				bisFlatRecord.setCurrYearYsTotal(cellValueBig); //本年累计应收
			}if(col == 26){
				bisFlatRecord.setYsTotal(cellValueBig);//累计应收
			}if(col == 27){
				bisFlatRecord.setCurrYearSsTotal(cellValueBig);//本年累计实收
			}if(col == 28){
				bisFlatRecord.setSsTotal(cellValueBig); //累计实收
			}
			if("5".equals(costType)){
				if(col == 29){
					bisFlatRecord.setCurrYearWaterRate(cellValueBig);// 本年收回本年水费
				}if(col == 30){
					bisFlatRecord.setCurrYearYusWaterRate(cellValueBig);//本年度预收水费
				}
			}
			if("6".equals(costType)){
				if(col == 29){
					bisFlatRecord.setCurrYearElecRate(cellValueBig);// 本年收回本年电费
				}if(col == 30){
					bisFlatRecord.setCurrYearYusElecRate(cellValueBig);//本年度预收电费
				}
			}
			if(col == 31){
				bisFlatRecord.setAgoYearOwnfeeTotal(cellValueBig); //累计收回以前年度欠费
			}if(col == 32){
				bisFlatRecord.setYusTotal(cellValueBig); // 累计预收
			}if(col == 33){
				bisFlatRecord.setCurrYearQsTotal(cellValueBig); // 本年累计欠收
			}if(col == 34){
				bisFlatRecord.setQsTotal(cellValueBig); //累计欠收
			}
		}
		return bisFlatRecord;
	}
	

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFlatRecordManager.getEntity(getId());
		} else {
			entity = new BisFlatRecord();
		}
	}
	/**
	 * 初始化公寓楼号列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapBuilding() {
		Map<String, String> mapBuilding = new LinkedHashMap<String, String>();
		String hql = "from BisFloor where floorType='2' order by sequenceNo,bisFloorId ";
		List<BisFloor> list = bisFloorManager.find(hql);
		for (BisFloor bisBuilding : list) {
			mapBuilding.put(bisBuilding.getBisFloorId(),bisBuilding.getBuildingNum());
		}
		return mapBuilding;
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
	public BisFlatRecord getModel() {
		return entity;
	}

	public BisFlatRecord getEntity() {
		return entity;
	}

	public void setEntity(BisFlatRecord entity) {
		this.entity = entity;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
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

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public Page<BisFlat> getPageFlat() {
		return pageFlat;
	}

	public void setPageFlat(Page<BisFlat> pageFlat) {
		this.pageFlat = pageFlat;
	}

	public String getBisFlatId() {
		return bisFlatId;
	}

	public void setBisFlatId(String bisFlatId) {
		this.bisFlatId = bisFlatId;
	}

	public BisFlat getBisFlat() {
		return bisFlat;
	}

	public void setBisFlat(BisFlat bisFlat) {
		this.bisFlat = bisFlat;
	}

	public String getBisFloorId() {
		return bisFloorId;
	}

	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public VoFlatCount getVoFlatCount() {
		return voFlatCount;
	}

	public void setVoFlatCount(VoFlatCount voFlatCount) {
		this.voFlatCount = voFlatCount;
	}

	public BisFlatRecordVo getVoBisFlatRecord() {
		return voBisFlatRecord;
	}

	public void setVoBisFlatRecord(BisFlatRecordVo voBisFlatRecord) {
		this.voBisFlatRecord = voBisFlatRecord;
	}

	public String getBisFloorName() {
		return bisFloorName;
	}

	public void setBisFloorName(String bisFloorName) {
		this.bisFloorName = bisFloorName;
	}

}
