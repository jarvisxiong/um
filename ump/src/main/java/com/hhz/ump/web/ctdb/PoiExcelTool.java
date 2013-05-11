/**  
 * PoiExcelTool.java  
 * com.hhz.ump.web.ctdb  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2012-2-6        zhongyubing  
 *  
 * Copyright (c) 2012, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.ctdb;  

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellRangeAddress;

/**  
 * ClassName:PoiExcelTool
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2012-2-6        下午02:48:52  
 * 
 */
public class PoiExcelTool {

	/**
	 * 
	 *插入单元格
	 */
	public static void addCell(HSSFRow row,HSSFCell cell,HSSFRichTextString text,int colIndex,HSSFCellStyle style,String contentType,String strContent,Double douContent){
		//根据下标创建单元格
		cell= row.createCell(colIndex);
		//设置单元格格式
		cell.setCellStyle(style);
		//设置单行号
		row.setHeightInPoints(25);
		//如果为字符类型单元格,则创建字符串型单元格格式
		if("1".equals(contentType)){
			text= new HSSFRichTextString(strContent);
			cell.setCellValue(text);
		}else{
			cell.setCellValue(douContent);
		}
	}
	
	/**
	 * 
	 *合并单元格
	 */
	public static  void mergeCell(HSSFSheet sheet,CellRangeAddress address,int col1,int col2,int col3,int col4){
		//合并区域
		address=new CellRangeAddress(col1,col2,col3,col4);
		//执行合并
        sheet.addMergedRegion(address);
	}
}
  
