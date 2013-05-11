package com.hhz.ump.web.biz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.biz.BizBankAccountManager;
import com.hhz.ump.entity.biz.BizBankAccount;
import com.hhz.ump.util.JXLExcelUtil;

/**
 * @author liwei 2012-6-27
 * 
 * 银行账户
 */
@Namespace("/biz")
@Results( {@Result(name = CrudActionSupport.RELOAD, location = "biz-bank-account!bankEnter.action", type="redirect"),
			@Result(name = "export", type = "stream", 
					params = { "contentType", "application/vnd.ms-excel",
					"inputName", "excelFile",
					"contentDisposition", "attachment;filename=${excelFileName}.xls" })
		})
public class BizBankAccountAction extends CrudActionSupport<BizBankAccount> {

	@Autowired
	private BizBankAccountManager bizBankAccountManager;
	
	private BizBankAccount entity;

	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;
	// 导入Excel的文件
	private String importFile;
		
	@Override
	public BizBankAccount getModel() {
		if(entity == null) {
			entity = new BizBankAccount();
		}
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/***
	 * 总入口
	 * @return
	 */
	public String bankEnter() {
		return SUCCESS;
	}

	/***
	 * 获取银行账户列表
	 */
	@Override
	public String list() throws Exception {
		page = bizBankAccountManager.findPage(page, entity);
		return "list";
	}
	
	/***
	 * 导入Excel数据
	 * @return
	 * @throws Exception
	 */
	public String importData() throws Exception {
		long begin = new Date().getTime();
		StringBuffer render = new StringBuffer();
		try {
			HSSFWorkbook hssfWorkBook = new HSSFWorkbook(new FileInputStream(importFile));
			// 取excel第一个sheet
			HSSFSheet firstSheet = hssfWorkBook.getSheetAt(0);
			
			HSSFRow firstRow = firstSheet.getRow(0);
			String col1 = firstRow.getCell(0).getStringCellValue(); // 第一列，列头名称
			String col2 = firstRow.getCell(1).getStringCellValue(); // 第二列，列头名称
			String col3 = firstRow.getCell(2).getStringCellValue(); // 第三列，列头名称
			
			//String msg = ""; // 返回的提示信息
			List<BizBankAccount> accountList = new ArrayList<BizBankAccount>();
			if("开户公司".equals(col1.trim()) && "银行名称".equals(col2.trim()) && "银行账号".equals(col3.trim())) {
				// 读取Excel数据
				for(int i=1; i<=firstSheet.getLastRowNum(); i++) {
					HSSFRow row = firstSheet.getRow(i);
					if(row.getCell(0) != null && StringUtils.isNotBlank(row.getCell(0).getStringCellValue())
							&& row.getCell(1) != null && StringUtils.isNotBlank(row.getCell(1).getStringCellValue())
							&& row.getCell(2) != null && StringUtils.isNotBlank(row.getCell(2).getStringCellValue())) {
						BizBankAccount account = new BizBankAccount();
						account.setCompanyName(row.getCell(0).getStringCellValue());
						account.setBankName(row.getCell(1).getStringCellValue());
						account.setBankNo(row.getCell(2).getStringCellValue());
						
						accountList.add(account);
					}
				}
				
				// 清空所有数据
				bizBankAccountManager.batchDelete();
				// 批量保存
				bizBankAccountManager.batchSave(accountList);
				
				long end = new Date().getTime();
				BigDecimal bd = new BigDecimal(end - begin);
				bd = bd.divide(new BigDecimal(1000), 3, RoundingMode.HALF_UP); // 四舍五入，取两位小数
				render.append("{'second': '" + bd.toString()+ "', "); // 使用时间(秒)
				render.append("'count': '" + accountList.size()+ "', "); // 插入条数
				render.append("'success': 'success'}");
			} else {
				//msg = "请确认标题列的顺序依次是：\n1：\"开户公司\"  2：\"银行名称\"  3：\"银行账号\"！";
				render.append("{'format_error': 'format_error'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			render.append("{'error': '" + e.toString() + "'}");
		}
		Struts2Utils.renderText(render.toString());
		return null;
	}
	
	/**
	 * 导出Excel模板
	 * @return
	 * @throws Exception
	 */
	public String exportTemplate() throws Exception {

		excelFile = buildTemplateExcel();
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	
	/***
	 * 构建导出excel文件流
	 * @return
	 */
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
	 * 制定模板样式
	 * @param wbook
	 * @throws Exception
	 */
	private void initTemplateData(WritableWorkbook wbook) throws Exception {
		String fileName = "银行账户";
		//模板名称
		excelFileName = fileName;
		//设置工作表名称
		WritableSheet wSheet = wbook.createSheet(fileName, 0);
		initRecordSheet(wSheet);
	}

	/**
	 * 银行账户-导出模板
	 */
	public void initRecordSheet(WritableSheet wsheet) throws Exception {
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("宋体"), 10);
		font_Bold_10.setBoldStyle(WritableFont.NO_BOLD);
				
		// 标题样式
		WritableCellFormat format_head = new WritableCellFormat(font_Bold_10);
		format_head.setAlignment(Alignment.CENTRE);
		format_head.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head.setBackground(Colour.GRAY_25);
		format_head.setBorder(Border.ALL, BorderLineStyle.THIN);//边框为实体线
		format_head.setWrap(true);//自动换行
		
		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.LEFT);
		format_label.setBorder(Border.ALL, BorderLineStyle.THIN);
		format_label.setWrap(true); 
		
		//添加第一行数据
		wsheet.addCell(new Label(0, 0, "开户公司", format_head));
		wsheet.addCell(new Label(1, 0, "银行名称", format_head));
		wsheet.addCell(new Label(2, 0, "银行账号", format_head));
		
		// 每一列宽度
		wsheet.setColumnView(0, 55);
		wsheet.setColumnView(1, 45);
		wsheet.setColumnView(2, 35);

		//设置行高
		wsheet.setRowView(0, 600);
		
		// 输出 10 行空值，作为模板
		for (int i=0; i<10; i++) {
			// 设置每行的值
			wsheet.addCell(new Label(0, i+1, "", format_label));
			wsheet.addCell(new Label(1, i+1, "", format_label));
			wsheet.addCell(new Label(2, i+1, "", format_label));
			
			// 设置行高
			wsheet.setRowView(i+1, 500);
		}
	}
	
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {
		if(StringUtils.isBlank(getId())) { // 新增银行账户
			
			bizBankAccountManager.saveBizBankAccount(entity);
			return RELOAD;
		} else { // 修改银行账户
			
			bizBankAccountManager.saveBizBankAccount(entity);
			Map<String, String> map = new HashMap<String, String>();
			map.put("bankNo", entity.getBankNo());
			map.put("bankName", entity.getBankName());
			map.put("companyName", entity.getCompanyName());
			Struts2Utils.renderJson(map);
			return null;
		}
	}

	public void prepareDelete() throws Exception {
		prepareModel();
	}
	
	@Override
	public String delete() throws Exception {
		BizBankAccount account = bizBankAccountManager.getEntity(entity.getBizBankAccountId());
		bizBankAccountManager.delete(account);
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		entity = new BizBankAccount();
	}

	/**
	 * @return the entity
	 */
	public BizBankAccount getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(BizBankAccount entity) {
		this.entity = entity;
	}

	/**
	 * @return the excelFile
	 */
	public InputStream getExcelFile() {
		return excelFile;
	}

	/**
	 * @param excelFile the excelFile to set
	 */
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	/**
	 * @return the excelFileName
	 */
	public String getExcelFileName() {
		return excelFileName;
	}

	/**
	 * @param excelFileName the excelFileName to set
	 */
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	/**
	 * @return the importFile
	 */
	public String getImportFile() {
		return importFile;
	}

	/**
	 * @param importFile the importFile to set
	 */
	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

	
}
