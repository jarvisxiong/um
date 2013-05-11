package com.hhz.uums.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class JXLExcelUtil {

	/**
	 * 构造一个Excel文件实例
	 * 
	 * @param outputStream
	 *            Excel内容待写入的输出流
	 * @return
	 */
	public static WritableWorkbook createWorkBook(OutputStream outputStream) {
		WritableWorkbook wbook = null;
		try {
			wbook = Workbook.createWorkbook(outputStream);
		} catch (IOException ie) {
			throw new RuntimeException("实例化Excel文件失败", ie);
		}

		return wbook;
	}

	/**
	 * 关闭一个Excel文件实例，关闭前把内容写入输出流
	 * 
	 * @param wbook
	 */
	public static void closeWorkBook(WritableWorkbook wbook) {
		if (wbook == null)
			return;

		try {
			wbook.write();
		} catch (IOException e) {
			throw new RuntimeException("将内容写入Excel文件失败", e);
		}

		try {
			wbook.close();
		} catch (Exception e) {
			throw new RuntimeException("关闭Excel文件失败", e);
		}
	}

	/**
	 * 构造Jxls数据流
	 * 
	 * @param beanMap
	 * @param templateName
	 * @return
	 * @throws Exception
	 */
	public static InputStream initJxlsInputStream(Map<String,Object> beanMap, String templateName) throws Exception {

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = JXLExcelUtil.class.getClassLoader().getResourceAsStream("jxlsTemplates/" + templateName);
		HSSFWorkbook hssfWorkbook = transformer.transformXLS(isTemplate, beanMap);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}
}
