package com.xbz.vpase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Function Description
 */
public class DateUtil {


	/**
	 * 把字符串转为固定时间格式的字符串(xx:xx)
	 * @param s
	 * @return
	 * @version
	 */
	public static String fmString(String s){
		Date dDate;
		String reTime = "";
		try {
			dDate = new SimpleDateFormat("HH:mm").parse(s);
			reTime = new SimpleDateFormat("HH:mm").format(dDate);
		} catch (ParseException e) {
			reTime = "00:00";
		}
		return reTime;
	}


	public static Date dateFormatDate(Date date){
		Date fDate = new Date();
		String reDate="";
		reDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		try {
			fDate = new SimpleDateFormat("yyyy-MM-dd").parse(reDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fDate;
	}


	/**
	 * 把Date转为固定时间格式的字符串(yyyy-MM-dd hh:mm)
	 * @return
	 * @version
	 */
	public static String fmYYS(Date d){
		String reTime = "";
		if(d!=null){
			reTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d);
		}else{
			reTime="1970-01-01 00:00";
		}
		return reTime;
	}

	public static String fmPattern(Date d,String pattern){
		String reTime = "";
		if(d!=null){
			reTime = new SimpleDateFormat(pattern).format(d);
		}else{
			reTime="1970-01-01 00:00";
		}
		return reTime;
	}

	public static Date StrToDate(String str) {
		SimpleDateFormat format;
		if(str==null||str.length()==0){
			return null;
		}


		if(str.split(":").length==3){
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    		  
		}else if(str.split(":").length==2){
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}else{
			format = new SimpleDateFormat("yyyy-MM-dd");
		}
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date StrToDateWithPattern(String str,String pattern) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取这个月的第一天/最后一天
	 * @param date
	 * @return
	 */
	public static Map<String, String> getFirstday_Lastday_Month(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//		calendar.add(Calendar.MONTH, -1);
		Date theDate = calendar.getTime();

		//上个月第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		day_first = str.toString();

		//上个月最后一天
		calendar.add(Calendar.MONTH, 1);    //加一个月
		calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, String> map = new HashMap<String, String>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}

	/**
	 * 当月第一天
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();

	}
	
    /**
     * 当月最后一天
     * @return
     */
	public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }

	/**
	 * 获取当前日期是星期几<br>
	 *
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取某个日期的前几天的日期
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date getNextDay(Date date,Integer amount) {
		if(date==null){
			date = new Date();
		}
		if(amount==null){
			amount=1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		date = calendar.getTime();
		return date;
	}

	public static boolean isTheSameDay(Date first,Date second){
		if(first==null||second==null){
			return false;
		}
		String firstStr = fmPattern(first,"yyyy-MM-dd");
		String secondStr = fmPattern(second,"yyyy-MM-dd");
		if(firstStr.equals(secondStr)){
			return true;
		}
		return false;
	}

	/**
	 * 获取两个日期相差几个月
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	public static Integer getYear(String str)  {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d= null;
		try {
			d = sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar=Calendar.getInstance();
			calendar.setTime(d);
			return calendar.get(Calendar.YEAR);
		}

	public static Integer getMonth2(String str) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d= null;
		try {
			d = sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH)+1;
	}

	public static Integer getMonthDate(Date d){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH)+1;
	}

	public static Integer getYearDate(Date d){
		if(d==null){
			return 1970;
		}else {
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(d);
			return calendar.get(Calendar.YEAR);
		}
	}

	public static String getYearMonthDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd"); // 时间字符串产生方式
		String uid_pfix = format.format(new Date());
		return uid_pfix;
	}
}
