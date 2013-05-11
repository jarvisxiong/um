/**  
 * PoiStyle.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2012-1-6        zhongyubing  
 *  
 * Copyright (c) 2012, TNT All Rights Reserved.  
 */

package com.hhz.ump.web.bid;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * ClassName:PoiStyle Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2012-1-6 上午09:10:08
 */
public class PoiStyle {

	

	public static HSSFCellStyle buildStyle(HSSFWorkbook workbook) {
		 // 生成一个样式
	      HSSFCellStyle style = workbook.createCellStyle();
	      // 设置这些样式
	      //蓝色底色
	      style.setFillForegroundColor(HSSFColor.WHITE.index);
	      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      // 生成一个字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.BLACK.index);
	      font.setFontHeightInPoints((short) 10);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style.setFont(font);
	      return style;
	}
	
	public static HSSFCellStyle buildStyle2(HSSFWorkbook workbook) {
		 // 生成一个样式
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);	      
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      font2.setFontHeightInPoints((short)10);
	      font2.setFontName("宋体");
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	      return style2;
	}
	
	public static HSSFCellStyle buildStyle3(HSSFWorkbook workbook) {
		// 生成一个样式
		 HSSFCellStyle style3 = workbook.createCellStyle();
	      style3.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	      style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font3 = workbook.createFont();
	      font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style3.setFont(font3);
		return style3;
	}
	public static HSSFCellStyle buildStyle4(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style4 = workbook.createCellStyle();
		// 设置这些样式
	      //蓝色底色
	      style4.setFillForegroundColor(HSSFColor.WHITE.index);
	      style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      style4.setBottomBorderColor(HSSFColor.BLACK.index);
	      
	      // 生成一个字体
	      HSSFFont font4 = workbook.createFont();
	      font4.setColor(HSSFColor.BLACK.index);
	      font4.setFontHeightInPoints((short) 10);
	      font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      style4.setFont(font4);
		return style4;
	}
	
	/*// 声明一个画图的顶级管理器
    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
    // 定义注释的大小和位置,详见文档
    HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
    // 设置注释内容
    comment.setString(new HSSFRichTextString("序号"));	      
    // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
    comment.setAuthor("sys");*/

	
}
