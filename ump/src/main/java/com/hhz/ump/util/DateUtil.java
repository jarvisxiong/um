package com.hhz.ump.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	/** 年-月-日 */
	public static SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 根据年月周 获取 周一和 周日 
	 * @param year 年
	 * @param month 月 
	 * @param weekOfMonth 一月中的第几周
	 * @return    0：周一 、1：周日 
	 */
	public static String[] getWeekStartEndDay(int year, int month, int weekOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date lastDay = getCurMonthLastDay(calendar.getTime());
		Date tempDate = null;
		while (calendar.getTime().before(lastDay)
				|| calendar.getTime().equals(lastDay)) {
			if (calendar.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
				tempDate = calendar.getTime();
				break;
			}
			calendar.add(Calendar.DATE, 1);
		}
		String start_end_date[] =null;
		if (tempDate != null) {
			start_end_date = new String[2];
			start_end_date[0] = getMonday(tempDate);
			start_end_date[1] = getSunday(tempDate);
//			System.out.println("周一：" + start_end_date[0]);
//			System.out.println("周日：" + start_end_date[1]);
		}
		return start_end_date;
	}
	 /***
	   * 获取特殊报表一周第一天
	   * 周六
	   * @param calendar
	   * @return
	   */
	  public static String getFirstDayOfWeek_rpt(Calendar calendar)
	  {
	    calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	    int curDay = calendar.get(7);
	    if (curDay == 1) {
			calendar.add(5, -1);
		} else {
			calendar.add(5, - curDay);
		}
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    return format.format(calendar.getTime());
	  }
	  /***
	   * 获取特殊报表一周最后一天
	   * 周五
	   * @param calendar
	   * @return
	   */
	  public static String getLastDayWeek_rpt(Calendar calendar)
	  {
	    calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	    int curDay = calendar.get(7);
	    if (curDay != 6) {
			calendar.add(5, 6 - curDay);
		}
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    return format.format(calendar.getTime());
	  }
	/**
	 * 根据年月周 获取 周一和 周日 
	 * @param year 年
	 * @param month 月 
	 * @param weekOfMonth 一月中的第几周
	 * @return    0：周六 、1：下周五 
	 */
	public static String[] getWeekStartEndDay_rpt(int year, int month, int weekOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date lastDay = getCurMonthLastDay(calendar.getTime());
		Date tdate = null;
		String start_end_date[] =null;
		start_end_date = new String[2];
		while (calendar.getTime().before(lastDay)
				|| calendar.getTime().equals(lastDay)) {
			System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
			if (calendar.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
				start_end_date[0] = getFirstDayOfWeek_rpt(calendar);
				calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+6);
				start_end_date[1] = getLastDayWeek_rpt(calendar);
				break;
			}
			calendar.add(Calendar.DATE, 1);
		}
		return start_end_date;
	}
	/**
	 * 获取双周日期
	 * 一个月分为2个双周
	 * @param year
	 * @param month
	 * @param weekOfMonth
	 * @return
	 */
	public static String[] getDoubleWeekStartEndDay(int year, int month, int weekOfMonth){
		String smonth = month<10?"0"+month:month+"";
		String start_end_date[] = new String[2];
		if(weekOfMonth==1){//第一个双周
			StringBuffer sb =new StringBuffer();
			start_end_date[0] = sb.append(year).append("-").append(smonth).append("-").append("01").toString();
			sb.setLength(0);
			start_end_date[1] = sb.append(year).append("-").append(smonth).append("-").append("15").toString();
		}else{//第二个双周
			StringBuffer sb =new StringBuffer();
			start_end_date[0] = sb.append(year).append("-").append(smonth).append("-").append("16").toString();
			sb.setLength(0);
			start_end_date[1] = sb.append(year).append("-").append(smonth).append("-").append("31").toString();
		}
		return start_end_date;
	}
	/***
	 * 取这个月最后一天
	 * @param date
	 * @return
	 */
	public static Date getCurMonthLastDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 返回周一
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return DateUtil.yyyy_mm_dd.format(cal.getTime());
	}

	/**
	 * 星期日
	 * 
	 * @param date
	 * @return
	 */
	public static String getSunday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE, 6);
		return DateUtil.yyyy_mm_dd.format(cal.getTime());
	}

	/**
	 * 取得某日期接下来一天的日期
	 * 
	 * @param strDate
	 *            yyyy-mm-dd
	 * @return yyyy-mm-dd
	 */
	public static String getNextDay(String strDate) {
		String tmp = strDate + " " + "00:00";
		Calendar calendar = parseStringToCalendar(tmp);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String nextDate = parseDateToString(calendar.getTime());
		return nextDate;
	}

	/**
	 * 把日历字符串转换为日历对象
	 * 
	 * @param strCal
	 *            格式:"yyyy-mm-dd hh:ss"
	 * @return
	 */
	public static Calendar parseStringToCalendar(String strCal) {
		if (strCal == null || "".equals(strCal))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = dateFormat.parse(strCal);
			calendar.setTime(date);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return calendar;
	}

	/**
	 * 把日期类型转换为字符串
	 * 
	 * @param date
	 * @return yyyy-mm-dd
	 */
	public static String parseDateToString(Date date) {
		if (date == null)
			return "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = "";
		String day = "";
		int imonth = c.get(Calendar.MONTH) + 1;
		if (imonth < 10) {
			month = "0" + String.valueOf(imonth);
		} else {
			month = String.valueOf(imonth);
		}
		int iday = c.get(Calendar.DATE);
		if (iday < 10) {
			day = "0" + String.valueOf(iday);
		} else {
			day = String.valueOf(iday);
		}
		return year + "-" + month + "-" + day;
	}

	/**
	 * 取得本周周一日期
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getNowWeekDay(String strDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(parseStringToDate(strDate));
		int day = c.get(Calendar.DAY_OF_WEEK);
		int i;
		if (day == 1) {
			i = -6;
		} else {
			i = 2 - day;
		}
		c.add(Calendar.DAY_OF_MONTH, i);
		String date = parseDateToString(c.getTime());
		return date;
	}

	/**
	 * 取得本周周日日期
	 */
	public static String getEndWeekDay(String strDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(parseStringToDate(strDate));
		int day = c.get(Calendar.DAY_OF_WEEK);
		int i;
		if (day == 1) {
			i = 0;
		} else {
			i = 8 - day;
		}
		c.add(Calendar.DAY_OF_MONTH, i);
		String date = parseDateToString(c.getTime());
		return date;
	}

	/**
	 * 字符串日期,转换为java.util.Date类型
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date parseStringToDate(String strDate) {
		if (strDate == null || "".equals(strDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date parseStringToDate1(String strDate) {
		if (strDate == null || "".equals(strDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 取这个月第一天
	public static String getCurMonthFirstDay() {

		Calendar gc = Calendar.getInstance();
		gc.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(gc
				.getTime());
		System.out.println("day_first:" + firstDay10);

		return firstDay10;
	}

	// 取这个月最后一天
	public static String getCurMonthLastDay() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		System.out.println("day_end:" + firstDay10);

		return firstDay10;
	}

	// 取上个月第一天
	public static String getPreMonthFirstDay() {

		Calendar gc = Calendar.getInstance();
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.add(Calendar.MONTH, -1);
		String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(gc
				.getTime());
		System.out.println("day_first:" + firstDay10);

		return "";
	}

	// 取上个月最后一天
	public static String getPreMonthLastDay() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		System.out.println("day_end:" + firstDay10);

		return "";
	}

	// 取下个月第一天
	public static String getNextMonthFirstDay() {

		// 获取下月
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		Date theDate = calendar.getTime();

		// 下月的第一天
		calendar.setTime(theDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(calendar
				.getTime());
		System.out.println("day_first_nextM:" + firstDay10);

		return firstDay10;
	}

	// 去下个月最后一天
	public static String getNextMonthLastDay() {

		// 获取下月
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);

		// 下月的最后一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		String lastDay10 = new SimpleDateFormat("yyyy-MM-dd").format(calendar
				.getTime());
		System.out.println("day_last_nextM:" + lastDay10);
		return lastDay10;
	}

	/**
	 * 判断两段日期是否冲突 false:A时间段与B时间段冲突 true：A时间段与B时间段不冲突
	 */
	public static boolean checkDateConflict(Date beginA, Date endA,
			Date beginB, Date endB) {
		boolean flag = false;
		if (beginA.after(endB) || beginB.after(endA))
			return true;
		else
			return false;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getCurrtDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 获取当前年
	 */
	public static String getCurrYear() {
		Calendar gc = Calendar.getInstance();
		gc.set(Calendar.DAY_OF_YEAR, 1);
		String year = new SimpleDateFormat("yyyy").format(gc.getTime());
		System.out.println("day_first:" + year);
		return year;
	}

	public static String getCurrMonth() {
		Calendar gc = Calendar.getInstance();
		gc.set(Calendar.DAY_OF_YEAR, 1);
		gc.set(Calendar.DAY_OF_MONTH, 2);
		String month = new SimpleDateFormat("yyyy-MM").format(gc.getTime());
		month = month.substring(5, 7);
		int m = Integer.valueOf(month);
		System.out.println(m);
		return String.valueOf(m);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateUtil.getCurrMonth();
		DateUtil.getCurMonthFirstDay();
		DateUtil.getCurMonthLastDay();
		DateUtil.getPreMonthFirstDay();
		DateUtil.getPreMonthLastDay();
		DateUtil.getNextMonthFirstDay();
		System.out.println(DateUtil.parseStringToDate("2011-7-1"));
		System.out.println(getCurrtDate());
		System.out.println(DateUtil.getLastYearAndMon("2011-12"));
		System.out.println(DateUtil.getLastMonth("2011-12"));
		System.out.println(DateUtil.getCurrentTime());
	}

	/**
	 * 获取上一年当月当日
	 */
	public static String getLastYearAndMon(String ym) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(f.parse(ym));
		} catch (ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.YEAR, -1);

		return f.format(gc.getTime());
	}

	/**
	 * 获取上一月
	 */
	public static String getLastMonth(String ym) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try {
			gc.setTime(f.parse(ym));
		} catch (ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.MONTH, -1);
		return new SimpleDateFormat("yyyy-MM").format(gc.getTime());
	}
	/**
	 * 获取前一周的第一天
	 * @return
	 */
	 public static String getFirstDayOfPreWeek()
	  {
	    return getFirstDayOfWeek(getCalendar(-7));
	  }
	 /***
	  * 获取前一周的最后一天
	  * @return
	  */
	  public static String getLastDayOfPreWeek()
	  {
	    return getLastDayWeek(getCalendar(-7));
	  }
	  /***
	   * 时间计算
	   * @param day
	   * @return
	   */
	  public static Calendar getCalendar(int day)
	  {
	    Calendar calendar = GregorianCalendar.getInstance();
	    calendar.add(5, day);
	    return calendar;
	  }
	  /***
	   * 获取一周第一天
	   * @param calendar
	   * @return
	   */
	  public static String getFirstDayOfWeek(Calendar calendar)
	  {
	    calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	    int curDay = calendar.get(7);
	    if (curDay == 1) {
			calendar.add(5, -6);
		} else {
			calendar.add(5, 2 - curDay);
		}
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    return format.format(calendar.getTime());
	  }
	  /***
	   * 获取一周最后一天
	   * @param calendar
	   * @return
	   */
	  public static String getLastDayWeek(Calendar calendar)
	  {
	    calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	    int curDay = calendar.get(7);
	    if (curDay != 1) {
			calendar.add(5, 8 - curDay);
		}

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    return format.format(calendar.getTime());
	  }
	

		/***
		 * 获得上个半月信息
		 * @param date
		 * @return
		 */
		public static String[] getLastHalfMonth(String args){
			String date = "";
			String[] strArray = new String[4];
			Calendar calendar =  parseStringToCalendar(args+" 01:01");
			calendar.add(Calendar.DATE, -13);
			if(calendar.getTime().getDate()<15){
				String date1 =  DateUtil.parseDateToString(calendar.getTime());
				date = date1.substring(0,date1.lastIndexOf("-")+1)+"01";
				strArray[0] = date;
				date = date1.substring(0,date1.lastIndexOf("-")+1)+"15";
				strArray[1] = date;
			}
			if(calendar.getTime().getDate()>15){
				String date1 = DateUtil.parseDateToString(calendar.getTime());
				date = date1.substring(0,date1.lastIndexOf("-")+1)+"16";
				strArray[0] = date;
				date = date1.substring(0,date1.lastIndexOf("-")+1)+"31";
				strArray[1] = date;
			}
			return strArray;
		}
	public static String getCurrentTime() {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		return f.format(gc.getTime());
	}

	
	/**
	 * 计算两个时间的相差月份数（参数为字符串格式）
	 * 
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @return
	 * @throws Exception
	 */
	public static int getMonthCountStr(String date1, String date2){
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date begin = sdf.parse(date1);
			Date end = sdf.parse(date2);
		    result = getMonthCountDate(begin, end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 计算两个时间的相差月份数（参数为日期格式）
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static int getMonthCountDate(Date begin, Date end){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String beginStr = sdf.format(begin);
		String endStr = sdf.format(end);
		try {
			c1.setTime(sdf.parse(beginStr));
			c2.setTime(sdf.parse(endStr));
			if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)) {
				result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			} else {
				result = 12*(c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
