/**
 * 
 */
package com.hhz.ump.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 在JSP页面中调用的取值方法
 * 
 * @author huangj 2010-2-6
 */
public class JspUtil {
	private static AppAttachFileManager appAttachFileManager = SpringContextHolder.getBean("appAttachFileManager");

	public static String findString(String key) {
		String value = null;
		ValueStack vs = ActionContext.getContext().getValueStack();
		value = vs.findString(key);
		if (value == null) {
			value="";
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
		List<AppAttachFile> list = appAttachFileManager.getAttaFileByBizEntityId(bizEntityId,false);
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
			if (Pattern.matches(Constants.PATTERN_NUM, value)) {
				BigDecimal bdValue = new BigDecimal(value.toString());
				rtValue = formattor.format(bdValue);
			} else {
				// throw new NumberFormatException();
				rtValue = value.toString();
			}
		}
		return rtValue;
	}

	/**
	 * 判断当前用户是否存在于字符串中
	 * 
	 * @param str
	 * @param fullStr
	 * @param regex
	 * @return
	 */
	public static boolean indexOf(String fullStr) {
		String currUiid = SpringSecurityUtils.getCurrentUiid() + ";";
		if (fullStr.indexOf(currUiid) > -1)
			return true;
		return false;
	}
	
	/**
	 * 判断用户是否可以查看总裁预约记录
	 * 参会人员、管理员、助理秘书可以查看
	 * @param fullStr
	 * @return
	 */
	public static boolean hasCEOResRole(String fullStr){
		boolean isParter = indexOf(fullStr);
		boolean isChairmanResAdmin = SpringSecurityUtils.hasRole(Constants.CHAIRMAN_RES);
		boolean isCEOResAdmin = SpringSecurityUtils.hasRole(Constants.CEO_RES);
		boolean isCEOResBrowse = SpringSecurityUtils.hasRole(Constants.CEO_RES_BROWSER);

		return isParter || isChairmanResAdmin || isCEOResAdmin || isCEOResBrowse;
	}
	
	/**
	 * 判断总裁/执行总裁是否有参与该会议
	 * @param type
	 *   0  总裁
	 *   1 执行总裁
	 * @return
	 */
	public static boolean isBossInMeeting(String compere,String participators, String type){
		String uiid;
		if(type.equals("0")){
			uiid = Constants.CHAIRMAN;
		}else{
			uiid = Constants.CEO;
		}
		if(compere.equals(uiid))
			return true;
		if(participators.indexOf(uiid+";")>-1)
			return true;
		return false;
	}
	
	/**
	 * 判断日期是否小于当前日期
	 * @param date
	 * @return
	 */
	public static boolean compareCurDate(String dateKey){
		Date date = (Date)JspUtil.findValue(dateKey);
		Date currDate = DateUtils.truncate(new Date(), Calendar.DATE);
		if(date == null)
			return false;
		if(currDate.after(date))
			return true;
		return false;
	}

	/**
	 * 判断是否小于加天数后的日期
	 * @param day 天数
	 * @return
	 */
	public static boolean getCompareDate(String dateKey,int day){
		Date date = (Date)JspUtil.findValue(dateKey);
		Date currDate = DateUtils.truncate(new Date(), Calendar.DATE);
		if(date == null)
			return false;
		Date comDate =DateOperator.addDays(date, day);
		if(currDate.after(comDate))
			return true;
		return false;
	}
}
