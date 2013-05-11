/**
 * 
 */
package com.hhz.uums.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.CoreContants;
import com.hhz.uums.dao.app.AppAttachFileManager;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.entity.app.AppParam;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 在JSP页面中调用的取值方法
 * 
 * @author huangj 2010-2-6
 */
public class JspUtil {
	private static AppAttachFileManager appAttachFileManager = SpringContextHolder.getBean("appAttachFileManager");

	private static AppParamManager appParamManager = SpringContextHolder.getBean("appParamManager");

	public static String findString(String key) {
		String value = null;
		ValueStack vs = ActionContext.getContext().getValueStack();
		value = vs.findString(key);
		if (value == null) {
			value = "";
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public static <T> T findValue(String key) {
		T value = null;
		ValueStack vs = ActionContext.getContext().getValueStack();
		value = (T) vs.findValue(key);
		return value;
	}

	@SuppressWarnings("unchecked")
	public static <T> T findValue(String key, Class clazz) {
		T value = null;
		ValueStack vs = ActionContext.getContext().getValueStack();
		value = (T) vs.findValue(key, clazz);
		return value;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getMACAddress() {
		String str = "";
		String find = "Physical Address. . . . . . . . . :";

		StringBuffer macAddress = new StringBuffer();
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					int findIndex = str.indexOf(find);
					if (findIndex != -1) {
						macAddress = macAddress.append(str.substring(findIndex + find.length() + 1, findIndex + find.length() + 18)).append(",");
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress.toString();
	}

	public static String getAttaSize(String bizEntityId) {
		List<AppAttachFile> list = appAttachFileManager.getAttaFileByBizEntityId(bizEntityId);
		BigDecimal fileSize = new BigDecimal(0);
		for (AppAttachFile appAttachFile : list) {
			fileSize = fileSize.add(appAttachFile.getFileSize());
		}
		return fileSize.divide(new BigDecimal(1024), 0, BigDecimal.ROUND_HALF_UP).toString() + "k";
	}

	/**
	 * 格式化金额
	 * 
	 * @param value
	 * @return
	 */
	public static String formatMoney(String value) {
		return formatNumber(value, "###,###,##0.00");
	}

	public static String formatMoney2(String value) {
		return formatNumber(value, "########0.00");
	}

	/**
	 * 格式化数字
	 * 
	 * @param value
	 * @param formatter
	 * @return
	 */
	public static String formatNumber(String value, String formatter) {
		String rtValue = "";
		if (!StringUtils.isEmpty(value)) {
			DecimalFormat formattor = new DecimalFormat(formatter);
			if (Pattern.matches(CoreContants.PATTERN_NUM, value)) {
				BigDecimal bdValue = new BigDecimal(value.toString());
				rtValue = formattor.format(bdValue);
			} else {
				// throw new NumberFormatException();
				rtValue = value.toString();
			}
		}
		return rtValue;
	}
	//获取网站标题
	public static String getSiteTitle() {
		String siteTitle = "";
		try {
			AppParam appParam = appParamManager.getAppParamByCd(Constants.PARAM_SITE_TITLE);
			siteTitle = appParam.getParamValue();
		} catch (Exception e) {
		}
		return siteTitle;
	}
}
