package com.hhz.ump.web.oa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import com.hhz.ump.dao.oa.SpecialTaskManager;
import com.hhz.ump.entity.oa.SpecialTask;
import com.hhz.ump.entity.oa.SpecialTaskComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.IExcelExporter;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.Util;

import common.Logger;

/**
 * 
 * 类名 		SpecialTaskExcelExporter 
 * 创建者 	李劲 
 * 创建		日期 2010-5-13 
 * 描述 		专项任务的Excel导出类
 */
@Service
public class SpecialTaskExcelExporter implements IExcelExporter {

    private static final Logger logger = Logger.getLogger(SpecialTaskExcelExporter.class);
    private List<SpecialTask> taskList;

    public SpecialTaskExcelExporter(List<SpecialTask> taskList) {
	this.taskList = taskList;
    }
    
    public InputStream buildExportedExcelInputStream() {
	if (taskList == null) {
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
	WritableSheet wsheet = wbook.createSheet("专项任务列表_" + d, 0);

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
	    wsheet.addCell(new Label(1, 0, "标题", wcfN_gray_Bold));
	    wsheet.addCell(new Label(2, 0, "发出人", wcfN_gray_Bold));
	    wsheet.addCell(new Label(3, 0, "负责人", wcfN_gray_Bold));
	    wsheet.addCell(new Label(4, 0, "配合人", wcfN_gray_Bold));
	    wsheet.addCell(new Label(5, 0, "目标时间", wcfN_gray_Bold));
	    wsheet.addCell(new Label(6, 0, "目标时间", wcfN_gray_Bold));
	    wsheet.addCell(new Label(7, 0, "更新时间", wcfN_gray_Bold));
	    wsheet.addCell(new Label(8, 0, "详情", wcfN_gray_Bold));
	    wsheet.addCell(new Label(9, 0, "最后评论", wcfN_gray_Bold));
	    wsheet.addCell(new Label(10, 0, "当前状态", wcfN_gray_Bold));

	    int currow = 1;
	    String delimiter = SpecialTaskManager.DELIMITER;
	    for (SpecialTask t : taskList) {
		wsheet.addCell(new Label(0, currow, t.getWaterNum(), wcfN_noBold));
		wsheet.addCell(new Label(1, currow, t.getSubject(), wcfN_noBold));
		wsheet.addCell(new Label(2, currow, CodeNameUtil.getUserNamesByUiids(t.getSendPerson(), delimiter), wcfN_noBold));
		wsheet.addCell(new Label(3, currow, CodeNameUtil.getUserNamesByUiids(t.getResponsiblePerson(), delimiter), wcfN_noBold));
		wsheet.addCell(new Label(4, currow, CodeNameUtil.getUserNamesByUiids(t.getCoordinatePerson(), delimiter), wcfN_noBold));
		wsheet.addCell(new Label(5, currow, DateOperator.formatDate(t.getCreatedDate(), "MM-dd"), wcfN_noBold));
		wsheet.addCell(new Label(6, currow, DateOperator.formatDate(t.getTargetDate(), "MM-dd"), wcfN_noBold));
		wsheet.addCell(new Label(7, currow, DateOperator.formatDate(t.getUpdatedDate(), "MM-dd"), wcfN_noBold));

		String content = null;
		try {
		    content = Util.clob2String(t.getDetail());
		} catch (Exception e) {
		    logger.error("获取任务详细信息失败id=" + t.getSpecialTaskId(), e);
		}
		wsheet.addCell(new Label(8, currow, content, wcfN_noBold));

		String com = "";
		List<SpecialTaskComment> comments = t.getSpecialTaskComments();
		if (comments.size() > 2) {
		    orderComments(comments);
		    SpecialTaskComment lastCom = comments.get(0);
		    try {
			String comInfo = CodeNameUtil.getUserNameByCd(lastCom.getCreator()) 
				+ "(" + DateOperator.formatDate(lastCom.getCreatedDate(), "yyyy-MM-dd") + "):";
			String comment = Util.clob2String(lastCom.getContent());
			com = comInfo + comment;
		    } catch (Exception e) {
			logger.error("获取最后一条评论信息失败id=" + t.getSpecialTaskId(), e);
		    }
		}
		wsheet.addCell(new Label(9, currow, com, wcfN_noBold));

		String statusCd = t.getStatus();
		String status = "";

		if (DictContants.SPECIAL_TASK_STATUS_NEW.equals(statusCd)) {
		    status = "进行中";
		} else if (DictContants.SPECIAL_TASK_STATUS_PRECOMPLETE
			.equals(statusCd)) {
		    status = "预完成";
		} else if (DictContants.SPECIAL_TASK_STATUS_COMPLETE.equals(statusCd)) {
		    status = "完成";
		}
		wsheet.addCell(new Label(10, currow, status, wcfN_noBold));
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

    /**
     * 对评论进行排序
     * 
     * @param comments
     */
    private void orderComments(List<SpecialTaskComment> comments) {
	if (comments == null || comments.size() == 0)
	    return;
	Comparator<SpecialTaskComment> c = new Comparator<SpecialTaskComment>() {
	    public int compare(SpecialTaskComment c1, SpecialTaskComment c2) {
		// 按时间降序排列
		return c2.getCreatedDate().compareTo(c1.getCreatedDate());
	    }
	};
	Collections.sort(comments, c);
    }
}
