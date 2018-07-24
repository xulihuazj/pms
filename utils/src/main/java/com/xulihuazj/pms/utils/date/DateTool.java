package com.xulihuazj.pms.utils.date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTool {
    public static final String SIMPLE_FORMAT = "yyyy-MM-dd";
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String CAREFUL_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String NUMBER_FORMAT = "yyyyMMdd";
    public static String WEEKS[] = {"日", "一", "二", "三", "四", "五", "六"};
    
    public static long getDayDifference(String s, String e, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date sd = sdf.parse(s);
            Date ed = sdf.parse(e);

            return getDayDifference(sd, ed);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return -1;
    }

    private static long getDayDifference(Date s, Date e) {
        long day = Math.abs((e.getTime() - s.getTime()) / (24 * 60 * 60 * 1000));
        return day == 0 ? 1 : day;
    }

    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 获取系统时间
     *
     * @return Date
     */
    public static Date getNewDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date getNewDate(String pattern) {
        try {
            SimpleDateFormat sdf = getSimpleDateFormat(pattern);
            String dateStr = sdf.format(getNewDate());
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getHours(Date date){
    	int hours = 0;
    	if(date != null){
    		Calendar c = getCalendar();
            c.setTime(date);
            hours = c.get(Calendar.HOUR_OF_DAY);
    	}
        return hours;
    }
    /**
     * 获取系统时间
     *
     * @return Calendar
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 获取系统时间
     *
     * @return Calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar c = getCalendar();
        c.setTime(date);
        return c;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 时间格式化
     *
     * @param calendar
     * @param pattern
     * @return
     */
    public static String format(Calendar calendar, String pattern) {
        return DateFormatUtils.format(calendar, pattern);
    }

    /**
     * 时间格式化
     *
     * @param millis
     * @param pattern
     * @return
     */
    public static String format(long millis, String pattern) {
        return DateFormatUtils.format(millis, pattern);
    }

	/**
	 * 根据时间返回周几
	 * @param date
	 * @return "日", "一", "二", "三", "四", "五", "六"
	 */
	public static String getWeek(Date date) {
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return WEEKS[dayOfWeek];
	}
	
	/**
	 * 根据时间返回周几
	 * @param date
	 * @return 
	 */
	public static int getDayOfWeek(Date date) {
		if(date == null){
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

    /**
     * 根据时间返回几月
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        if(date == null){
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //外国月份为0-11所以月份加一
        int month = calendar.get(Calendar.MONTH)+1;
        return month;
    }

    /**
     * 得到年
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        if(date == null){
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }


    /**
	 * 添加指定的天
	 * @param days
	 * @return
	 */
	public static Date plusDays(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		DateTime dt = new DateTime(c);
		return dt.plusDays(days).toDate();
	}
	
	/**
	 * 添加指定的月
	 * @param days
	 * @return
	*/
	public static Date plusMonths(Date date,int month){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		DateTime dt = new DateTime(c);
		return dt.plusMonths(month).toDate();
	}
		
	/**
	 * 添加指定的分钟
	 * @param days
	 * @return
	 */
	public static Date plusMillis(Date date,int millis){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		DateTime dt = new DateTime(c);
		return dt.plusMillis(millis).toDate();
	} 

    /**
     * 时间格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date format(String date, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(date).toDate();
    }

    /**
     * 两时间相隔的自然年
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalYears(Date start, Date end) {
        Period period = new Period(start.getTime(), end.getTime(), PeriodType.years());
        return period.getYears();
    }

    /**
     * 两时间相隔的自然月
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalMonths(Date start, Date end) {
        Period period = new Period(start.getTime(), end.getTime(), PeriodType.months());
        return period.getMonths();
    }

    /**
     * 两时间相隔的自然日
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDays(Date start, Date end) {
        Period period = new Period(start.getTime(), end.getTime(), PeriodType.days());
        return period.getDays();
    }

    /**
     * 两时间日期相隔的自然日
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDays2(Date start, Date end) throws ParseException {
    	SimpleDateFormat sdf = getSimpleDateFormat(SIMPLE_FORMAT);
    	start = sdf.parse(sdf.format(start));  
    	end = sdf.parse(sdf.format(end));  
        Period period = new Period(start.getTime(), end.getTime(), PeriodType.days());
        return period.getDays();
    }
    
    
    /**
     * 两时间相隔的自然日
     *
     * @param start
     * @param end
     * @return [years, months, days]
     */
    public static int[] getInterval(Date start, Date end) {
        Period period = new Period(start.getTime(), end.getTime(), PeriodType.yearMonthDay());
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        int[] result = {years, months, days};
        return result;
    }
    /**
     * 计算两时间差  分钟
     * @param ms
     * @return
     */
    public static String formatDateTime(Integer ms) {
    	  if(null == ms || ms.equals(0)){
    		  return 0 + "分钟";
    	  }
    	  String DateTimes = null;
    	  long days = ms / (60 * 24);
    	  long hours = (ms % ( 60 * 24)) / 60;
    	  long minutes = (ms % ( 60));
    	  if(days>0){
    	   DateTimes= days + "天" + hours + "小时" + minutes + "分钟"; 
    	  }else if(hours>0){
    	   DateTimes=hours + "小时" + minutes + "分钟"; 
    	  }else{
    	   DateTimes=minutes + "分钟"; 
    	  }
    	  return DateTimes;
    }
    /**
     * 获取月的第一天
     * @param date
     * @return
     */
    public static Date firstday(Date date){
    	date = format(format(date, "yyyyMMdd"),"yyyyMMdd");
    	//获取前月的第一天
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return calendar.getTime();
    }
    /**
     * 获取月的最后一天
     * @param date
     * @return
     */
    public static Date lastday(Date date){
    	date = format(format(date, "yyyyMMdd"),"yyyyMMdd");
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);    
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    
    /**
     * 两时间相隔的分钟
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalMinutes(Date start, Date end) throws ParseException {
        SimpleDateFormat sdf = getSimpleDateFormat(FORMAT);
        start = sdf.parse(sdf.format(start));  
        end = sdf.parse(sdf.format(end));  
        Period period = new Period(start.getTime(), end.getTime(), PeriodType.minutes());
        return period.getMinutes();
    }
    
    /**
     * 两日期之间所有的 日期（yyyy-MM-dd）
     * 不包含 结束日期
     * @param start
     * @param end
     * @return
     * @throws ParseException
     * @autho zsy
     * @time 2017年10月11日 下午4:05:03
     */
    public static List<Date> getBetweenDates(Date start, Date end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startString = sdf.format(start);
        String endString = sdf.format(end);
        Date startDate = sdf.parse(startString);
        Date endDate = sdf.parse(endString);
        
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(startDate);
        
        while(startDate.getTime()<endDate.getTime()){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            startDate = tempStart.getTime();
        }
        return result;
    }
    
    /**
     * 格式化时间
     * @param date
     * @return
     * @autho zsy
     * @time 2017年12月21日 上午11:37:13
     */
    public static Date retFormatDate(Date date){
        String datestr = format(date, SIMPLE_FORMAT);
        Date rdate = format(datestr, SIMPLE_FORMAT);
        return rdate;
    }

    
    /*public static void main(String[] args) {
    	 Date startDate = DateTool.format("20180315", "yyyyMMdd");
         Date endDate = DateTool.format("20180410", "yyyyMMdd");
         // 相差的月数
         int month = DateTool.getIntervalMonths(startDate, endDate);
         // 相差的天数
         try {
			int day = DateTool.getIntervalDays2(DateTool.plusMonths(startDate, month), endDate);
			Date last = DateTool.format(DateTool.format(endDate, "yyyyMM"), "yyyyMM");
	        // 结束时间所在月的天数
	        int lastDay = Integer.parseInt(DateTool.format(DateTool.lastday(last), "dd"));
	        int endDd = Integer.parseInt(DateTool.format(endDate, "dd"));
	        System.out.println("相差的月:"+month);
	        System.out.println("相差的天:"+day);
	        if(day > endDd){
	        	// 使用上一个自然月的天数
	        	String onDate = DateTool.format(DateTool.plusMonths(endDate, -1),"yyyyMM");
	        	Date t = DateTool.format(onDate,"yyyyMM");
	        	lastDay = Integer.parseInt(DateTool.format(DateTool.lastday(t), "dd"));
	        }
	        System.out.println("所在月分最大天數："+lastDay);
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
