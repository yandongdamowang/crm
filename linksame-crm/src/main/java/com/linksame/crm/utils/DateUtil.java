package com.linksame.crm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/17 15:10
 * @Description 日期工具类
 */
public class DateUtil {
    /**
     * 获取当前日期与周一相差的天数
     * @return
     */
    public static int getMondayPlus(){
        Calendar day = Calendar.getInstance();
        int dayOfWeek=day.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==1){ //一周中第一天（周日）
            return -6;
        }else{
            return 2-dayOfWeek;
        }
    }
    /**
     * 获得当天的起始时间
     * @return
     */
    public static Calendar getStartDate(Calendar today){
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    /**
     * 获取当天截止时间
     * @return
     */
    public static Calendar getEndDate(Calendar endToday){
        endToday.set(Calendar.HOUR_OF_DAY, 23);
        endToday.set(Calendar.MINUTE, 59);
        endToday.set(Calendar.SECOND, 59);
        endToday.set(Calendar.MILLISECOND, 59);
        return endToday;
    }

    /**
     * 获得当月起始时间
     * @return
     */
    public static Calendar getStartMounth(Calendar today){
        Calendar calendar = getStartDate(today);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * 获得当月结束时间
     * @return
     */
    public static Calendar getEndMounth(Calendar endToday){
        Calendar endMounth=getEndDate(endToday);
        endMounth.set(Calendar.DAY_OF_MONTH, endMounth.getActualMaximum(endMounth.DAY_OF_MONTH));
        return endMounth;
    }

    /**
     * 获取当前季度 起始时间
     * @return
     */
    public static Calendar getStartQuarter(Calendar today){
        int currentMonth = today.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                today.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                today.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                today.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                today.set(Calendar.MONTH, 9);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    /**
     * 获取当季的结束时间
     */
    public static Calendar getEndQuarter(Calendar today){
        int currentMonth = today.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                today.set(Calendar.MONTH, 2);
                today.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                today.set(Calendar.MONTH, 5);
                today.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                today.set(Calendar.MONTH,8);
                today.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                today.set(Calendar.MONTH, 11);
                today.set(Calendar.DATE, 31);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    /**
     * 获取当年起始时间
     */
    public static Calendar getStartYear(Calendar today){
        try {
            today.set(Calendar.MONTH, 0);
            today.set(Calendar.DAY_OF_MONTH, today.getActualMinimum(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    /**
     * 获取当年结束时间
     */
    public static Calendar getEndYear(Calendar today){
        try {
            today.set(Calendar.MONTH, 11);
            today.set(Calendar.DAY_OF_MONTH, today.getMaximum(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
    * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
    *
    * @param dateDate
    * @return
    */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
     public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
     }

    /**
    * 将短时间格式字符串转换为时间 yyyy-MM-dd
    *
    * @param strDate
    * @return
    */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取两个时间之间的日期集合
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }

    /**
     * 获取传入日期后面几天的日期
     * @param date  日期
     * @param day   天数
     * @return
     */
    public static Date getNextDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, + day);
        date = calendar.getTime();
        return date;
    }

    /**
     * 根据字符日期返回星期几
     * @param dateTime  日期字符串
     * @return          周几
     */
    public static Integer getWeek(String dateTime){
        Integer weekCode = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateTime);
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            String week = dateFm.format(date);
            if(week.equals("星期一")){
                weekCode = 1;
            } else if(week.equals("星期二")){
                weekCode = 2;
            } else if(week.equals("星期三")){
                weekCode = 3;
            } else if(week.equals("星期四")){
                weekCode = 4;
            } else if(week.equals("星期五")){
                weekCode = 5;
            } else if(week.equals("星期六")){
                weekCode = 6;
            } else if(week.equals("星期日")){
                weekCode = 7;
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return weekCode;
    }

    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    /**
     * 获取某段时间内的周一（二等等）的日期
     * @param dataBegin 开始日期
     * @param dataEnd 结束日期
     * @param weekDays 获取周几，1－6代表周一到周六。0代表周日
     * @return 返回日期List
     */
    public static List<String> getDayOfWeekWithinDateInterval(String dataBegin, String dataEnd, int weekDays) {
        if(weekDays == 7){
            weekDays = 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateResult = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        String[] dateInterval = {dataBegin, dataEnd};
        Date[] dates = new Date[dateInterval.length];
        for (int i = 0; i < dateInterval.length; i++) {
            String[] ymd = dateInterval[i].split("[^\\d]+");
            cal.set(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]) - 1, Integer.parseInt(ymd[2]));
            dates[i] = cal.getTime();
        }
        for (Date date = dates[0]; date.compareTo(dates[1]) <= 0; ) {
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK) - 1 == weekDays) {
                String format = sdf.format(date);
                dateResult.add(format);
            }
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
        }
        return dateResult;
    }

    /**
     * 判断传入日期是否在当前日期之前
     * @param str   日期字符串
     * @return      1=是, -1=否
     */
    public static int compDate(String str) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return date.compareTo(sd.parse(str));
    }

    /**
     * 在传入日期基础上增加天数
     * @param option    日期
     * @param day       天数
     * @return
     */
    public static String checkOption(String option, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        Date date = null;
        try {
            date = (Date) sdf.parse(option);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cl.setTime(date);
        cl.add(Calendar.DATE, day);
        date = cl.getTime();
        return sdf.format(date);
    }

    /**
     * 替换传入时间的时分秒
     * @param datetime      传入日期
     * @param toDatetime    需要替换的日期(yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String handleDateTime(String datetime, String toDatetime) {
        Date date = strToDateLong(datetime);
        Date toDate = strToDateLong(toDatetime);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, toDate.getHours());
        calendar.set(Calendar.MINUTE, toDate.getMinutes());
        calendar.set(Calendar.SECOND, toDate.getSeconds());
        Date dates = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), date.getHours(), date.getMinutes(), date.getSeconds());

        return dateToStrLong(dates);
    }

    /**
     * 替换传入时间的时分秒
     * @param datetime      传入日期
     * @param toDate        需要替换的日期(yyyy-MM-dd)
     * @return
     */
    public static String handleDate(String datetime, String toDate) {
        Date date = strToDateLong(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDateLong(toDate));
        calendar.add(Calendar.HOUR_OF_DAY, 11);
        Date dates = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), date.getHours(), date.getMinutes(), date.getSeconds());

        return dateToStrLong(dates);
    }

    /**
     * 获取某日期与周一相差的天数(该日期所在周)
     * @param date  日期
     * @return
     */
    public static int getWeekMonday(Date date){
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        int dayOfWeek=day.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==1){
            return -6;
        }else{
            return 2-dayOfWeek;
        }
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        //不同一年
        if(year1 != year2) {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++) {
                //闰年
                if(i%4==0 && i%100!=0 || i%400==0) {
                    timeDistance += 366;
                } else {    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        } else {            //同一年
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int dateToInterval(Date date1,Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 根据传入日期字符串和日期组装成新的日期字符串
     * @return
     */
    public static String setDate(String dateStr, int day){
        String datetime;
        Calendar cal = Calendar.getInstance();
        try{
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            datetime = year + "-" + month + "-" + day;
        } catch (Exception e) {
            dateStr = dateStr + " 00:00:00";
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            datetime = year + "-" + month + "-" + day;
        }
        return datetime;
    }

    /**
     * 根据传入日期字符串和月份组装成新的日期字符串
     * @return
     */
    public static String setMonth(String dateStr, int month){
        String datetime;
        Calendar cal = Calendar.getInstance();
        try{
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            datetime = year + "-" + month + "-" + day;
        } catch (Exception e) {
            dateStr = dateStr + " 00:00:00";
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            datetime = year + "-" + month + "-" + day;
        }

        return datetime;
    }

    /**
     * 获取传入日期的年份
     * @param dateStr
     */
    public static int getYear(String dateStr){
        int datetime;
        Calendar cal = Calendar.getInstance();
        try{
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            datetime = cal.get(Calendar.YEAR);
        } catch (Exception e) {
            dateStr = dateStr + " 00:00:00";
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            datetime = cal.get(Calendar.YEAR);
        }

        return datetime;
    }

    /**
     * 获取传入日期的月份
     * @param dateStr
     */
    public static int getMonth(String dateStr){
        int datetime;
        Calendar cal = Calendar.getInstance();
        try{
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            datetime = cal.get(Calendar.MONTH) + 1;
        } catch (Exception e) {
            dateStr = dateStr + " 00:00:00";
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            datetime = cal.get(Calendar.MONTH) + 1;
        }

        return datetime;
    }

    /**
     * 获取传入日期的日
     * @param dateStr
     */
    public static int getDate(String dateStr){
        int datetime;
        Calendar cal = Calendar.getInstance();
        try{
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            datetime = cal.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            dateStr = dateStr + " 00:00:00";
            Date date = DateUtil.strToDateLong(dateStr);
            cal.setTime(date);
            datetime = cal.get(Calendar.DAY_OF_MONTH);
        }

        return datetime;
    }

    /**
     * 获取某个月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

}
