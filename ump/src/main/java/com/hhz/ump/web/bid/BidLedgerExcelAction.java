package com.hhz.ump.web.bid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidDivisionSupRelManager;
import com.hhz.ump.dao.bid.BidDivisitonManager;
import com.hhz.ump.dao.bid.BidFeesSupRelManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidMeasureSupRel2Manager;
import com.hhz.ump.dao.bid.BidMeasureSupRelManager;
import com.hhz.ump.dao.bid.BidOtherItemRelManager;
import com.hhz.ump.dao.bid.BidProjectManager;
import com.hhz.ump.dao.bid.BidSporadicSupRelManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.bid.BidSupOtherRelManager;
import com.hhz.ump.dao.bid.BidTaxsSupRelManager;
import com.hhz.ump.entity.bid.BidDivisiton;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidProject;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.util.JXLExcelUtil;

@Namespace("/bid")
@Results( { @Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel","inputName", "excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }) })
public class BidLedgerExcelAction extends CrudActionSupport<BidLedger> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5373817502788963479L;
	private static final Log log = LogFactory.getLog(BidLedgerExcelAction.class);
	/**
	 * 标段ID
	 */
	private String bidLedgerId;
	/**
	 * 标段
	 */
	private BidLedger bidLedger;
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 工程ID
	 */
	private String projectId;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;

	/**
	 * 导入Excel需要的文件
	 */
	private File importFile;
	/**
	 * 数字格式化实例
	 */
	NumberFormat nf = NumberFormat.getInstance();
	/**
	 * 是否下载清单
	 */

	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected BidProjectManager bidProjectManager;
	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected BidDivisionSupRelManager bidDivisionSupRelManager;
	@Autowired
	protected BidMeasureSupRelManager bidMeasureSupRelManager;
	@Autowired
	protected BidMeasureSupRel2Manager bidMeasureSupRel2Manager;
	@Autowired
	protected BidOtherItemRelManager bidOtherItemRelManager;
	@Autowired
	protected BidSporadicSupRelManager bidSporadicSupRelManager;
	@Autowired
	protected BidFeesSupRelManager bidFeesSupRelManager;
	@Autowired
	protected BidTaxsSupRelManager bidTaxsSupRelManager;
	@Autowired
	protected BidSupOtherRelManager bidSupOtherRelManager;

	/**
	 * EXCEL样式
	 */
	private WritableFont font_Bold_10 = null;
	/**
	 * 输入框样式
	 */
	private WritableCellFormat format_unlock = null;
	/**
	 * 普通框样式
	 */
	private WritableCellFormat title_format_label = null;
	/**
	 * 普通框样式
	 */
	private WritableCellFormat format_label = null;
	/**
	 * 一级标题样式
	 */
	private WritableCellFormat format_head1 = null;
	/**
	 * 二级标题样式
	 */
	private WritableCellFormat format_head2 = null;
	/**
	 * 内容锁定
	 */
	private WritableCellFormat locked_normal_format_label = null;
	/**
	 * 内容不锁定
	 */
	private WritableCellFormat unlocked_normal_format_label = null;
	/**
	 * 显示的招标台帐列表
	 */
	private List<BidLedger> loadBids;
	/**
	 * 工程
	 */
	private List<BidProject> bidPros;
	/**
	 * 供应商
	 */
	private List<BidSup> bidSups;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BidLedger getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 导出EXCEL页面时请求的方法
	 */
	public String bisexcel() {
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			if (bidLedger != null) {
				bidSups = bidSupManager.getReceivedSup(bidLedger.getBidSups());
				bidPros = bidProjectManager.getEnableProjectList(getBidLedgerId());
			}
		}
		return "bisexcel";
	}

	/**
	 * 
	 * buildExcel:(构建EXCEL文件)
	 * 
	 * @param @param sheetNames
	 * @param @param bidProject
	 * @param @param sup
	 * @param @param bidName
	 * @param @return 设定文件
	 * @return InputStream DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	private InputStream buildExcel(String[] sheetNames, BidProject bidProject, BidSup sup, String bidName) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);
		if (wbook != null) {
			try {
				//导出数据
				populateData(sup.getTypeCd(), wbook, sheetNames, bidProject, sup, bidName);
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
	 * 
	 * populateData:(为文件生成数据)
	 * 
	 * @param @param typeCd
	 * @param @param wbook
	 * @param @param sheetNames
	 * @param @param bidProject
	 * @param @param sup
	 * @param @param bidName
	 * @param @throws WriteException 设定文件
	 * @return void DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	private void populateData(String typeCd, WritableWorkbook wbook, String[] sheetNames, BidProject bidProject,
			BidSup sup, String bidName) throws WriteException {
		String batchNo=Struts2Utils.getParameter("batchNo");
		List<BidDivisiton> bidDivisitons = bidDivisitonManager.getBidDivisitons(bidProject,batchNo);
		//设置格式
		initStyle();
		//执行的导出
		for (int i = sheetNames.length - 1; i >= 0; i--) {
			WritableSheet wsheet = wbook.createSheet(sheetNames[i], 0);
			// 设置冻结格式
			wsheet.getSettings().setVerticalFreeze(5);		

			// 分部分项工程量清单与计价表
			if (i == 0) {
				initBidDivisitonSheet(wsheet, sheetNames[i], bidProject, sup,bidDivisitons);
			}
			// 增添项目
			if (i == 1) {
				initBidDivisitonSheet(wsheet, sheetNames[i], bidProject, sup,null);
			}
			// 措施项目清单与计价表(一)
			if (i == 2) {
				initBidMeasureSupRel(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());
			}
			// 措施项目清单与计价表(二)
			if (i == 3) {
				initBidMeasureSupRel2(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());
			}
			// 其他项目清单与计价汇总表
			if (i == 4) {
				initBidOtherItemRel(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());
			}
			// 零星工程费用表
			if (i == 5) {
				initBidSporadicSupRel(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());
			}
			// 规费项目清单与计价表
			if (i == 6) {
				initBidFeesSupRel(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());
			}
			// 税金项目清单与计价表
			if (i == 7) {
				initBidTaxsSupRel(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());
			}
			// 标段其他费用
			if (i == 8) {
				initBidSupOtherRel(wsheet, sheetNames[i], bidProject.getProjectName(), sup.getSupName());

			}
		}

	}
	
	/**
	 * 
	 * initStyle:(初始化格式)
	 *  
	 * @param  @throws WriteException    设定文件  
	 * @return void    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	private void initStyle() throws WriteException{
		// 字体设置
		font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);
		// 输入框样式
		format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 普通框样式
		title_format_label = new WritableCellFormat(NumberFormats.TEXT);
		title_format_label.setAlignment(Alignment.LEFT);
		title_format_label.setLocked(true);
		// title_format_label.setBackground(Colour.BLUE_GREY);

		// 普通框样式
		format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.LEFT);

		// 内容锁定
		locked_normal_format_label = new WritableCellFormat(NumberFormats.TEXT);
		locked_normal_format_label.setAlignment(Alignment.LEFT);
		locked_normal_format_label.setLocked(true);

		// 内容不锁定
		unlocked_normal_format_label = new WritableCellFormat(NumberFormats.TEXT);
		unlocked_normal_format_label.setAlignment(Alignment.LEFT);
		unlocked_normal_format_label.setLocked(false);
		

		// 一级标题样式
		format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);
		format_head1.setLocked(true);

		// 二级标题样式
		format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2.setLocked(true);
	}

	private void initBidSupOtherRel(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws RowsExceededException, WriteException {
		// 设置列宽
		wsheet.setColumnView(0, 8);
		wsheet.setColumnView(1, 30);
		wsheet.setColumnView(2, 15);
		wsheet.setColumnView(3, 50);
		Label lb = null;
		// 1列1行
		lb = new Label(0, 0, sheetName, format_head1);
		wsheet.addCell(lb);
		wsheet.setRowView(0, 500);
		wsheet.mergeCells(0, 0, 3, 0);
		// 1列2行
		lb = new Label(0, 1, "工程名称:", format_head2);
		wsheet.addCell(lb);
		// 2列2行
		lb = new Label(1, 1, projectName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 1, 3, 1);
		// 1列3行
		lb = new Label(0, 2, "投标单位:", format_head2);
		wsheet.addCell(lb);
		// 3列3行
		lb = new Label(1, 2, supName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 2, 3, 2);
		// 标题
		// 1列4行
		lb = new Label(0, 3, "序号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 3, 0, 4);
		// 2列4行
		lb = new Label(1, 3, "费用名称", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 3, 1, 4);
		// 3列4行
		lb = new Label(2, 3, "费用（元）", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 3, 2, 4);
		// 4列4行
		lb = new Label(3, 3, "备注", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 3, 3, 4);

	}

	private void initBidTaxsSupRel(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws WriteException {
		initBidFeesSupRel(wsheet, sheetName, projectName, supName);

	}

	private void initBidFeesSupRel(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws WriteException {
		initBidMeasureSupRel(wsheet, sheetName, projectName, supName);

	}

	private void initBidSporadicSupRel(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws RowsExceededException, WriteException {
		// 设置列宽
		wsheet.setColumnView(0, 10);
		wsheet.setColumnView(1, 50);
		wsheet.setColumnView(2, 15);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 15);
		wsheet.setColumnView(5, 15);
		Label lb = null;
		// 1列1行
		lb = new Label(0, 0, sheetName, format_head1);
		wsheet.addCell(lb);
		wsheet.setRowView(0, 500);
		wsheet.mergeCells(0, 0, 5, 0);
		// 1列2行
		lb = new Label(0, 1, "工程名称:", format_head2);
		wsheet.addCell(lb);
		// 2列2行
		lb = new Label(1, 1, projectName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 1, 5, 1);
		// 1列3行
		lb = new Label(0, 2, "投标单位:", format_head2);
		wsheet.addCell(lb);
		// 3列3行
		lb = new Label(1, 2, supName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 2, 5, 2);
		// 标题
		// 1列4行
		lb = new Label(0, 3, "序号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 3, 0, 4);
		// 2列4行
		lb = new Label(1, 3, "项目名称", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 3, 1, 4);
		// 3列4行
		lb = new Label(2, 3, "单位", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 3, 2, 4);
		// 4列4行
		lb = new Label(3, 3, "暂定数量", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 3, 3, 4);
		// 4列4行
		lb = new Label(4, 3, "综合单价", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(4, 3, 4, 4);
		// 5列4行
		lb = new Label(5, 3, "合价", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(5, 3, 5, 4);

	}

	private void initBidOtherItemRel(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws WriteException {
		// 设置列宽
		wsheet.setColumnView(0, 10);
		wsheet.setColumnView(1, 30);
		wsheet.setColumnView(2, 10);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 50);
		Label lb = null;
		// 1列1行
		lb = new Label(0, 0, sheetName, format_head1);
		wsheet.addCell(lb);
		wsheet.setRowView(0, 500);
		wsheet.mergeCells(0, 0, 4, 0);
		// 1列2行
		lb = new Label(0, 1, "工程名称:", format_head2);
		wsheet.addCell(lb);
		// 2列2行
		lb = new Label(1, 1, projectName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 1, 4, 1);
		// 1列3行
		lb = new Label(0, 2, "投标单位:", format_head2);
		wsheet.addCell(lb);
		// 3列3行
		lb = new Label(1, 2, supName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 2, 4, 2);
		// 标题
		// 1列4行
		lb = new Label(0, 3, "序号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 3, 0, 4);
		// 2列4行
		lb = new Label(1, 3, "项目名称", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 3, 1, 4);
		// 3列4行
		lb = new Label(2, 3, "计算单位", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 3, 2, 4);
		// 4列4行
		lb = new Label(3, 3, "金额", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 3, 3, 4);
		// 5列4行
		lb = new Label(4, 3, "备注", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(4, 3, 4, 4);

	}

	private void initBidMeasureSupRel2(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws WriteException {
		// 设置列宽
		wsheet.setColumnView(0, 8);
		wsheet.setColumnView(1, 25);
		wsheet.setColumnView(2, 50);
		wsheet.setColumnView(3, 10);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);
		wsheet.setColumnView(6, 10);

		Label lb = null;
		// 1列1行
		lb = new Label(0, 0, sheetName, format_head1);
		wsheet.addCell(lb);
		wsheet.setRowView(0, 500);
		wsheet.mergeCells(0, 0, 6, 0);
		// 2列2行
		lb = new Label(0, 1, "工程名称:", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 1, 1, 1);
		// 4列2行
		lb = new Label(2, 1, projectName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 1, 6, 1);
		// 2列3行
		lb = new Label(0, 2, "投标单位:", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 2, 1, 2);
		// 4列3行
		lb = new Label(2, 2, supName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 2, 6, 2);
		// 标题
		// 1列4行
		lb = new Label(0, 3, "序号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 3, 0, 4);
		// 2列4行
		lb = new Label(1, 3, "项目编号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 3, 1, 4);
		// 3列4行
		lb = new Label(2, 3, "项目名称", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 3, 2, 4);
		// 4列4行
		lb = new Label(3, 3, "计量单位", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 3, 3, 4);
		// 5列4行
		lb = new Label(4, 3, "工程量", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(4, 3, 4, 4);
		// 6列4行
		lb = new Label(5, 3, "金额(元)", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(5, 3, 6, 3);
		// 6列5行
		lb = new Label(5, 4, "综合单价", format_head2);
		wsheet.addCell(lb);
		// 7列5行
		lb = new Label(6, 4, "合价", format_head2);
		wsheet.addCell(lb);

	}

	private void initBidMeasureSupRel(WritableSheet wsheet, String sheetName, String projectName, String supName)
			throws WriteException {
		// 设置列宽
		wsheet.setColumnView(0, 8);
		wsheet.setColumnView(1, 50);
		wsheet.setColumnView(2, 30);
		wsheet.setColumnView(3, 10);
		wsheet.setColumnView(4, 10);

		Label lb = null;
		// 1列1行
		lb = new Label(0, 0, sheetName, format_head1);
		wsheet.addCell(lb);
		wsheet.setRowView(0, 500);
		wsheet.mergeCells(0, 0, 4, 0);
		// 2列2行
		lb = new Label(0, 1, "工程名称:", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 1, 1, 1);
		// 4列2行
		lb = new Label(2, 1, projectName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 1, 4, 1);
		// 2列3行
		lb = new Label(0, 2, "投标单位:", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 2, 1, 2);
		// 4列3行
		lb = new Label(2, 2, supName, title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 2, 4, 2);
		// 标题
		// 1列4行
		lb = new Label(0, 3, "序号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 3, 0, 4);
		// 2列4行
		lb = new Label(1, 3, "项目名称", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 3, 1, 4);
		// 3列4行
		lb = new Label(2, 3, "计算基础", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 3, 2, 4);
		// 4列4行
		lb = new Label(3, 3, "费率", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 3, 3, 4);
		// 5列4行
		lb = new Label(4, 3, "金额（元）", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(4, 3, 4, 4);

	}

	/**
	 * (标底)分部分项工程量清单与计价表
	 * 
	 * @param wsheet
	 * @throws WriteException
	 */
	public void initBidDivisitonSheet(WritableSheet wsheet, String sheetName, BidProject bidProject, BidSup sup,List<BidDivisiton> bidDivisitons)
			throws WriteException {

		// 设置列宽
		wsheet.setColumnView(0, 8);
		wsheet.setColumnView(1, 0);
		wsheet.setColumnView(1, 18);
		wsheet.setColumnView(2, 25);
		wsheet.setColumnView(3, 25);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);
		wsheet.setColumnView(6, 10);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 10);

		Label lb = null;
		jxl.write.Number quantitieCell = null;
		// 1列1行
		lb = new Label(0, 0, sheetName, format_head1);
		wsheet.addCell(lb);
		wsheet.setRowView(0, 500);
		wsheet.mergeCells(0, 0, 7, 0);
		// 2列2行
		lb = new Label(0, 1, "工程名称:", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 1, 2, 1);
		// 2列9行
		WritableCellFormat wrappedText = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT);
        wrappedText.setWrap(true);
		WritableFont font = new WritableFont(WritableFont.ARIAL,8,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
		wrappedText.setFont(font);
        lb=new Label(8, 0, "友情提示：\012 1、请在此Excel文件中填写投标信息，并导入系统\012 2、当前页为‘分部分项’，下方右侧还有其他页(如：措施项、规费税金等)需要进行填写，请勿遗漏", wrappedText);       
        wsheet.addCell(lb);
        //4行
		//wsheet.setRowView(5, 10);
		//wsheet.setColumnView(5, 10);
		wsheet.mergeCells(8, 0, 12, 3);
		
		// 4列2行
		lb = new Label(3, 1, bidProject.getProjectName(), title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 1, 7, 1);
		// 2列3行
		lb = new Label(0, 2, "投标单位:", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 2, 2, 2);
		// 4列3行
		lb = new Label(3, 2, sup.getSupName(), title_format_label);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 2, 7, 2);
		// 标题
		// 1列4行
		lb = new Label(0, 3, "序号", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(0, 3, 0, 4);		
		// 2列4行
		lb = new Label(1, 3, "项目编号", format_head2);	
		wsheet.addCell(lb);
		wsheet.mergeCells(1, 3, 1, 4);
		// 2列4行
		lb = new Label(2, 3, "项目名称", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(2, 3, 2, 4);
		// 3列4行
		lb = new Label(3, 3, "项目特征描述", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(3, 3, 3, 4);
		// 4列4行
		lb = new Label(4, 3, "计量单位", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(4, 3, 4, 4);
		// 5列4行
		lb = new Label(5, 3, "工程量", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(5, 3, 5, 4);
		// 6列4行
		lb = new Label(6, 3, "金额(元)", format_head2);
		wsheet.addCell(lb);
		wsheet.mergeCells(6, 3, 7, 3);
		// 6列5行
		lb = new Label(6, 4, "综合单价", format_head2);
		wsheet.addCell(lb);
		// 7列5行
		lb = new Label(7, 4, "合价", format_head2);
		wsheet.addCell(lb);

		//如果为供应商,则需要导出此工程标公司的模板数据
		//补充，由于标底单位允许后期投标时导入复盖,故可将已有数据导出
		//搜索指定批次的数据
		//统统使用项目编号关联,故不再需要导出清单数据,只需要提供表头模板
		//如果是导出清单,则导出已经导入的清单
		String downList=Struts2Utils.getParameter("downList");
//		if(StringUtils.isNotBlank(downList)){
			List<BidDivisiton> expBidDivisitons=new ArrayList<BidDivisiton>();
			Integer rowNumberStart = 5;	
			//下载清单
			if("downList".equals(downList)||StringUtils.isBlank(downList)){
				if(bidDivisitons!=null) {
					expBidDivisitons=bidDivisitons;
				}
			}else{
			//获取未导入清单项
				expBidDivisitons=this.unimportedData();
			}
			for (BidDivisiton bidDivisiton : expBidDivisitons){				
				quantitieCell = new jxl.write.Number(0, rowNumberStart, rowNumberStart-4, locked_normal_format_label);
				wsheet.addCell(quantitieCell);
				// 项目编号[2列]
				lb = new Label(1, rowNumberStart, bidDivisiton.getItemNo(), locked_normal_format_label);
				wsheet.addCell(lb);
				// 项目名称[3列]
				lb = new Label(2, rowNumberStart, bidDivisiton.getItemName(), locked_normal_format_label);
				wsheet.addCell(lb);
				// 项目特征描述[4列]
				lb = new Label(3, rowNumberStart, bidDivisiton.getItemDesc(), locked_normal_format_label);
				wsheet.addCell(lb);
				// 计量单位[5列]
				lb = new Label(4, rowNumberStart, bidDivisiton.getUnitDesc(), locked_normal_format_label);
				wsheet.addCell(lb);
				// 工程量[6列]
				if (bidDivisiton.getQuantitie() != null) {
//					BigDecimal qty=BigDecimal.ZERO;
					String qty = "";
					if(bidDivisiton.getQuantitie()!=null){
						qty = bidDivisiton.getQuantitie();
					}
//					quantitieCell = new jxl.write.Number(5, rowNumberStart, qty.doubleValue(), locked_normal_format_label);
					lb = new Label(5,rowNumberStart,qty,locked_normal_format_label);
					wsheet.addCell(lb);
				}				
				//综合单价
				lb = new Label(6, rowNumberStart, null, unlocked_normal_format_label);
				wsheet.addCell(lb);
				//合价
				lb = new Label(7, rowNumberStart, null, unlocked_normal_format_label);			
				wsheet.addCell(lb);
				rowNumberStart++;

			}
//		}
	  if("分部分项工程量清单与计价表".equals(sheetName)) {
//		wsheet.setProtected(true);
	  }

	}

	public String exportExcel() throws UnsupportedEncodingException {
		
		String batchNo=Struts2Utils.getParameter("batchNo");
		String downList=Struts2Utils.getParameter("downList");
		String[] sheetNames = { "分部分项工程量清单与计价表", "增添目","措施项目清单与计价表(一)", "措施项目清单与计价表(二)", "其他项目清单与计价汇总表", "零星工程费用表",
				"规费项目清单与计价表", "税金项目清单与计价表", "标段其他费用" };
		for (int i = sheetNames.length - 1; i >= 0; i--) {
			String sheetName = sheetNames[i];
		}
		// 工程
		BidProject bidProject = null;
		// 供应商
		BidSup bidSup = null;
		// 工程名
		String projectName = "";
		// 标段名
		String bidName = "";
		// 搜索工程
		if (StringUtils.isNotBlank(this.getProjectId())) {
			bidProject = bidProjectManager.getEntity(this.getProjectId());
			if (bidProject != null) {
				projectName = bidProject.getProjectName();
				if(StringUtils.isNotBlank(projectName)) {
					projectName=projectName.replace(" ","");
				}
			}
		}
		// 搜索供应商构建文件名
		if (StringUtils.isNotBlank(this.getBidSupId())) {
			bidSup = bidSupManager.getEntity(this.getBidSupId());
			//项目名称
			String orgCdName=bidSup.getBidLedger().getOrgName();
			if (bidSup != null) {
				bidName = bidSup.getBidLedger().getBidSectionName();
				if(StringUtils.isNotBlank(bidName)) {
					bidName=bidName.replace(" ","");
				}
				if (bidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {	
					excelFileName = "宝龙公司[" + orgCdName + "]项目[" + bidName + "]标段["+projectName+"]工程-商务标(咨询公司)";
					
				} else {
					excelFileName = "宝龙公司[" + orgCdName + "]项目[" + bidName + "]标段["+projectName+"]工程-商务标(投标公司)";
				}
				//文件名后缀
				if(StringUtils.isNotBlank(downList)){
					//投标清单
					if("downList".equals(downList)){
						excelFileName=excelFileName+"_投标清单";
					}else{
						excelFileName=excelFileName+"_未导入清单项";
					}						
				}
				//投标文件模板
				else{
					excelFileName=excelFileName+"_投标文件模板";
				}
				excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
			}

		}
		// 执行导出
		excelFile = buildExcel(sheetNames, bidProject, bidSup, bidName);
		return "export";
	}

	/**
	 * 
	 * importTip:(导入前验证及提示)
	 * 
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public String importTip() {
		// 工程
		BidProject bidProject = null;
		// 供应商
		BidSup bidSup = null;
		// 如果工程和供应商不为空
		if (StringUtils.isNotBlank(this.getProjectId()) && StringUtils.isNotBlank(this.getBidSupId())) {
			bidProject = bidProjectManager.getEntity(this.getProjectId());
			bidSup = bidSupManager.getEntity(this.getBidSupId());
		}
		// 如果为投标单位
		if (BidSupManager.SUP_PROVIDOR.equals(bidSup.getTypeCd())) {
			// 获取标段
			BidLedger bidLedger = bidProject.getBidLedger();
			// 根据工程轮次号及供应商搜索 是否已经导入数据,且处于投标状态,则提示已经存在数据,是否清除数据重导入
			if (bidLedgerManager.supHasImportedData(bidSup, bidProject, bidLedger.getBatchNo())
					&& BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())) {
				Struts2Utils.renderText("1,此供应商在" + bidLedger.getBatchNo() + "轮已经导入数据，是否需要删除原有数据重新导入?");
				return null;
			} else {
				Struts2Utils.renderText("0,还没有导入过");
			}
		}// 如果是标底公司
		else {
			// 根据工程轮次号及供应商搜索 是否已经导入数据,且处于邀标状态,则提示已经存在数据,是否清除数据重导入
			// 因为标底单位的轮次默认为0
			if (bidLedgerManager.supHasImportedData(bidSup, bidProject, Long.parseLong("0"))
					&& BidLedgerManager.BID_STATUS_INVITE.equals(bidProject.getBidLedger().getBidStatusCd())) {
				Struts2Utils.renderText("1,此标底单位已经导入过数据，是否需要删除原有数据重新导入?");
				return null;
			} else {
				Struts2Utils.renderText("0,还没有导入过");
			}
		}

		return null;
	}

	

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
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

	/**
	 * 
	 * getLoadBids:(加载标段)
	 * 
	 * @param @return 设定文件
	 * @return List<BidLedger> DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public List<BidLedger> getLoadBids() {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		loadBids = bidLedgerManager.find(filters);
		return loadBids;
	}

	public void setLoadBids(List<BidLedger> loadBids) {
		this.loadBids = loadBids;
	}

	public List<BidProject> getBidPros() {
		return bidPros;
	}

	public void setBidPros(List<BidProject> bidPros) {
		this.bidPros = bidPros;
	}

	public List<BidSup> getBidSups() {
		return bidSups;
	}

	public void setBidSups(List<BidSup> bidSups) {
		this.bidSups = bidSups;
	}

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}
	/**
	 * 
	 * unimportedData:(获取在清单里面未导入的数据)  
	 *  
	 * @param  @return    设定文件  
	 * @return String    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public List<BidDivisiton>  unimportedData(){
		
		//搜索条件
		Map<String, Object> map = new HashMap<String, Object>();		
		String projectId = Struts2Utils.getParameter("projectId");
		String bidSupId = Struts2Utils.getParameter("bidSupId");
		String batchno = Struts2Utils.getParameter("batchNo");
		map.put("projectId", projectId);
		map.put("bidSupId", bidSupId);
		map.put("batchno", Long.parseLong(batchno));		
		 List<BidDivisiton>  bds=bidDivisionSupRelManager.findUnimportedData(map);
		return bds;
	}

}
