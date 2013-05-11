/**
 * 
 */
package com.hhz.core.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huangj 2009-12-7
 */
public class CoreContants {

	public static final String[] FILE_TYPE_VIDEO = { "audio/x-ms-wma", "video/x-ms-wmv", "video/x-ms-asf", "audio/mpeg", "video/avi", "audio/wav",
			"video/mpeg" };

	public static final String[] FILE_SUFFIX_VIDEO = { ".wma", ".wmv", ".asf", ".mpeg", ".avi", ".wav", ".mpeg" };

	public static final String[] FILE_TYPE_IMAGE = { "image/jpg", "image/gif", "image/png", "image/x-png", "image/pjpeg", "image/bmp", "image/jpeg" };

	public static final String[] FILE_SUFFIX_IMAGE = { ".jpg", ".gif", ".png", ".png", ".jpg", ".bmp", ".jpg" };

	public static final String[] FILE_TYPE_OFFICE = { "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword", "application/vnd.ms-excel",
 "application/pdf", "text/plain", "image/tiff", "application/x-zip-compressed",
			"application/octet-stream", "application/vnd.openxmlformats-officedocument.presentationml.presentation",
			"application/vnd.ms-powerpoint" };

	public static final String[] FILE_SUFFIX_OFFICE = { ".xlsx", ".docx", ".doc", ".xls", ".pdf", ".txt", ".tif",
			".zip", ".rar", ".pptx", ".ppt" };

	public static final String[] FILE_TYPE = PowerUtils.uniteArrays(FILE_TYPE_OFFICE, FILE_TYPE_IMAGE, FILE_TYPE_VIDEO);

	public static final String[] FILE_SUFFIX = PowerUtils.uniteArrays(FILE_SUFFIX_OFFICE, FILE_SUFFIX_IMAGE, FILE_SUFFIX_VIDEO);

	private static Map<String, String> mapEnable = new LinkedHashMap<String, String>(4);

	public static Map<String, String> getMapEnable() {
		mapEnable.put("", "");
		mapEnable.put("0", MessageUtils.getMessage("mapEnable.disable"));
		mapEnable.put("1", MessageUtils.getMessage("mapEnable.enable"));
		return mapEnable;
	}

	public static final String PATTERN_NUM = "^[-]{0,1}[0-9]+(\\.[0-9]{0,6}){0,1}$";
}
