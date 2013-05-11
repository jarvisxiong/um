package com.hhz.ump.util;

import java.io.InputStream;

/**
 * 
 * 类名		BaseExcelExporter
 * 创建者	李劲
 * 创建日期	2010-5-13
 */
public interface IExcelExporter {
    /**
     * 将待导出的Excel转换为InputStream流供客户下载
     * @return
     */
    public InputStream buildExportedExcelInputStream();
}
