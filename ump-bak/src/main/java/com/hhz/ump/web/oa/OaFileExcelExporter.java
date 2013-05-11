package com.hhz.ump.web.oa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.stereotype.Service;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.entity.oa.OaFileFollowed;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.IExcelExporter;
import com.hhz.ump.util.JXLExcelUtil;

import common.Logger;

/**
 * 
 * 类名 SpecialTaskExcelExporter 创建者 李劲 创建 日期 2010-5-13 描述 专项任务的Excel导出类
 */
@Service
public class OaFileExcelExporter implements IExcelExporter {

	private static final Logger logger = Logger.getLogger(OaFileExcelExporter.class);
	private List<OaFileFollowed> fileList;

	public OaFileExcelExporter(List<OaFileFollowed> fileList) {
		this.fileList = fileList;
	}

	@Override
	public InputStream buildExportedExcelInputStream() {
		if (fileList == null) {
			logger.warn("待导出的数据为null.");
			return null;
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {
			populateData(wbook);
			JXLExcelUtil.closeWorkBook(wbook);
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		return bis;
	}

	private void populateData(WritableWorkbook wbook) {
		Date now = Calendar.getInstance().getTime();
		String d = DateOperator.formatDate(now, "yyyy-MM-dd");
		WritableSheet wsheet = wbook.createSheet("文件跟踪_" + d, 0);

		HashMap<String, WritableCellFormat> font = buildFontMap();
		WritableCellFormat wcfN_gray_Bold = font.get("wcfN_gray_Bold");
		WritableCellFormat wcfN_noBold = font.get("wcfN_noBold");

		wsheet.setColumnView(0, 15);
		wsheet.setColumnView(1, 40);
		wsheet.setColumnView(2, 20);
		wsheet.setColumnView(3, 20);
		wsheet.setColumnView(4, 20);
		wsheet.setColumnView(5, 10);
		wsheet.setColumnView(6, 10);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 40);
		wsheet.setColumnView(9, 40);
		wsheet.setColumnView(10, 10);

		try {
			wsheet.addCell(new Label(0, 0, "编号", wcfN_gray_Bold));
			wsheet.addCell(new Label(1, 0, "来源", wcfN_gray_Bold));
			wsheet.addCell(new Label(2, 0, "文件名", wcfN_gray_Bold));
			wsheet.addCell(new Label(3, 0, "中心", wcfN_gray_Bold));
			wsheet.addCell(new Label(4, 0, "状态", wcfN_gray_Bold));
			wsheet.addCell(new Label(5, 0, "开始", wcfN_gray_Bold));
			wsheet.addCell(new Label(6, 0, "更新", wcfN_gray_Bold));
			wsheet.setRowView(0, 500);

			int currow = 1;
			for (OaFileFollowed t : fileList) {

				wsheet.addCell(new Label(0, currow, t.getSerialNumber() + t.getSerialNumberNum(), wcfN_noBold));
				String orgName = CodeNameUtil.getDeptNameByCd(t.getProjectSn());
				wsheet.addCell(new Label(1, currow, orgName, wcfN_noBold));
				wsheet.addCell(new Label(2, currow, t.getContent(), wcfN_noBold));
				String centerName = CodeNameUtil.getDeptNameByCd(t.getUpdatedDeptCd());
				wsheet.addCell(new Label(3, currow, centerName, wcfN_noBold));
				String describe = "";// t.getScheduleDescribe2();
				if (describe != null && !"".equals(describe)) {
					String[] desStr = describe.split("<BR>");
					wsheet.addCell(new Label(4, currow, desStr[desStr.length - 1], wcfN_noBold));
				} else {
					wsheet.addCell(new Label(4, currow, "", wcfN_noBold));
				}
				wsheet.addCell(new Label(5, currow, DateOperator.formatDate(t.getCreatedDate(), "MM-dd"), wcfN_noBold));
				wsheet.addCell(new Label(6, currow, DateOperator.formatDate(t.getUpdatedDate(), "MM-dd"), wcfN_noBold));
				wsheet.setRowView(currow, 400);
				currow++;
			}
		} catch (Exception e) {
			throw new RuntimeException("构造Excel内容出错", e);
		}
	}

	/**
	 * 构造Excel内容样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private HashMap<String, WritableCellFormat> buildFontMap() {
		HashMap<String, WritableCellFormat> map = new HashMap<String, WritableCellFormat>();
		// 字体：黑体，背景颜色：灰色
		WritableFont nf_Bold = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		WritableCellFormat wcfN_gray_Bold;
		try {
			nf_Bold.setBoldStyle(WritableFont.BOLD);
			wcfN_gray_Bold = new WritableCellFormat(nf_Bold);
			wcfN_gray_Bold.setBackground(Colour.GRAY_25);
			wcfN_gray_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcfN_gray_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcfN_gray_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
			wcfN_gray_Bold.setWrap(true); // 是否换行
			map.put("wcfN_gray_Bold", wcfN_gray_Bold);

			// 字体：普通
			WritableCellFormat wcfN_noBold;
			nf_Bold.setBoldStyle(WritableFont.NO_BOLD);
			wcfN_noBold = new WritableCellFormat(nf_Bold);
			wcfN_noBold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcfN_noBold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcfN_noBold.setAlignment(Alignment.CENTRE);// 水平对齐
			wcfN_noBold.setWrap(true); // 是否换行
			map.put("wcfN_noBold", wcfN_noBold);
		} catch (WriteException e) {
			throw new RuntimeException("设置Excel文件样式出错", e);
		}
		return map;
	}
}
