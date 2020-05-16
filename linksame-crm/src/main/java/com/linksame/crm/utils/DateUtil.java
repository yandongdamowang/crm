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
}
