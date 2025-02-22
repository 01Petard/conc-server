package com.hzx.conc.common.utils.date;


import com.google.common.collect.Lists;
import com.hzx.conc.common.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author cqh
 */
public class DateUtil {

    /**
     * 一天的开始与结束
     */
    public static final String DAY_BEGIN_TIME = "00:00:00";
    public static final String DAY_END_TIME = "23:59:59";
    /**
     * 日期默认格式
     */
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 计算两个日期相差年数<br>
     * 在非重置情况下，如果起始日期的月小于结束日期的月，年数要少算1（不足1年）
     *
     * @return 相差年数
     * @since 3.0.8
     */
    public static long betweenYear(Date begin, Date end) {
        final Calendar beginCal = CalendarUtil.calendar(begin);
        final Calendar endCal = CalendarUtil.calendar(end);

        int result = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
        // 考虑闰年的2月情况
        if (Calendar.FEBRUARY == beginCal.get(Calendar.MONTH) && Calendar.FEBRUARY == endCal.get(Calendar.MONTH)) {
            if (beginCal.get(Calendar.DAY_OF_MONTH) == beginCal.getActualMaximum(Calendar.DAY_OF_MONTH) && endCal.get(Calendar.DAY_OF_MONTH) == endCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                // 两个日期都位于2月的最后一天，此时月数按照相等对待，此时都设置为1号
                beginCal.set(Calendar.DAY_OF_MONTH, 1);
                endCal.set(Calendar.DAY_OF_MONTH, 1);
            }
        }

        endCal.set(Calendar.YEAR, beginCal.get(Calendar.YEAR));
        long between = endCal.getTimeInMillis() - beginCal.getTimeInMillis();
        if (between < 0) {
            return result - 1;
        }
        return result;
    }

    /**
     * 计算相对于dateToCompare的年龄，长用于计算指定生日在某年的年龄
     *
     * @param birthDay      生日
     * @param dateToCompare 需要对比的日期
     * @return 计算后的年龄
     */
    public static int age(Date birthDay, Date dateToCompare) {
        return CalendarUtil.age(birthDay.getTime(), dateToCompare.getTime());
    }

    public static String format(String date, String srcPattern, String resultPattern) {
        if (StringUtil.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(srcPattern);
        try {
            return DateFormatUtils.format(sdf.parse(date), resultPattern);
        } catch (ParseException e) {
            return date;
        }
    }

    public static Date strToDate(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateFormat(String date, String pattern) {
        if (StringUtil.isNotEmpty(date)) {
            String format;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Date parse = sdf.parse(date);
                format = sdf.format(parse);
                return format;
            } catch (ParseException e) {
                return "";
            }

        }
        return "";
    }

    /**
     * 日期类型格式化字符串
     *
     * @param date    待格式化日期
     * @param pattern 字符格式
     * @return 格式化结果
     */
    public static String dateFormat(Date date, String pattern) {
        if (date != null && StringUtil.isNotBlank(pattern)) {
            return new SimpleDateFormat(pattern).format(date);
        }
        return "";
    }


    public static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        String format = sdf.format(new Date());
        return format;
    }

    public static String nowPureDatetime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.PURE_DATETIME_PATTERN);
        String format = sdf.format(new Date());
        return format;
    }

    public static int getMealTime(int mealTime) {
        switch (mealTime) {
            case 363:
                return 0;

            case 365:
                return 1;
            case 367:
                return 2;
            case 368:
                return 3;
            case 369:
                return 4;
            default:
                return 0;
        }

    }

    /**
     * 两个时间相差多少小时
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return Double 返回值为：xx
     */
    public static Double getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        BigDecimal hour = BigDecimal.ZERO;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            hour = new BigDecimal(diff).divide(new BigDecimal((60 * 60 * 1000)), 5, BigDecimal.ROUND_HALF_UP);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour.doubleValue();
    }


    public static Date getNextTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return calendar.getTime();
    }


    /**
     * 功能描述:通过当前时间查询一周的周一和周日日期
     *
     * @param:
     * @author: wwf
     * @date: 2019/1/20 14:45
     */
    public static Map<String, String> getWeekDate() {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        //System.out.println("所在周星期一的日期：" + weekBegin);


        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
        //System.out.println("所在周星期日的日期：" + weekEnd);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }


    /**
     * 功能描述:通过当前时间查询上一周的周一和周日日期
     *
     * @param:
     * @author: wwf
     * @date: 2019/1/20 14:45
     */
    public static Map<String, String> getLastWeekDate() {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -7 * 24 * 3600);
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        //System.out.println("所在周星期一的日期：" + weekBegin);


        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
        //System.out.println("所在周星期日的日期：" + weekEnd);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }


    /**
     * 功能描述:通过传入的日期查询该日期的周一和周日
     *
     * @param:
     * @author: wwf
     * @date: 2019/1/20 14:45
     */
    public static Map<String, String> getWeekDateByTime(String date) {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date timeDate = null;
        try {
            timeDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeDate);

        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        //System.out.println("所在周星期一的日期：" + weekBegin);


        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
        //System.out.println("所在周星期日的日期：" + weekEnd);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }

    public static String HOUR = "hour";
    public static String MIN = "min";
    public static String SECOND = "second";

    /**
     * 两个时间相差多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return Double 返回值为：xx
     */
    public static Long getDistanceSeconds(String str1, String str2) {
        LocalDateTime startDate = LocalDateTime.parse(str1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(str2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return (Duration.between(startDate, endDate).getSeconds());
    }

    /**
     * 两个时间相差多少秒
     *
     * @param date1 时间参数 1
     * @param date2 时间参数 2
     * @return Double 返回值为：xx
     */
    public static Long getDistanceSeconds(Date date1, Date date2) {
        LocalDateTime startDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDate = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return (Duration.between(startDate, endDate).getSeconds());
    }

    /**
     * 根据秒转换对应时间
     */
    public static double getTimeBySecond(Long seconds, String type) {
        if (HOUR.equals(type)) {
            return Double.valueOf(String.format("%.2f", (seconds.doubleValue() / 3600)));

        } else if (MIN.equals(type)) {
            return Double.valueOf(String.format("%.2f", (seconds.doubleValue() / 60)));
        }
        return 0;
    }

    public static String addDate(String day, int hour, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        //System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        //System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    /**
     * 给定日期 增加分钟数
     *
     * @param date    给定日期
     * @param minutes 增加的分钟
     * @return 结果日期
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowStr 当前时间
     * @param endStr 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(String nowStr, String endStr) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowTime = sdf.parse(nowStr);
            Date endTime = sdf.parse(endStr);

            if (nowTime.getTime() == endTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);


            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (date.before(end)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTimeStr
     * @param startTimeStr
     * @param endTimeStr
     * @return
     * @throws Exception
     */
    public static boolean isEffectiveDate(String nowTimeStr, String startTimeStr, String endTimeStr) throws Exception {

        String format = "HH:mm";
        Date nowTime = new SimpleDateFormat(format).parse(nowTimeStr);
        Date startTime = new SimpleDateFormat(format).parse(startTimeStr);
        Date endTime = new SimpleDateFormat(format).parse(endTimeStr);

        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    //判断是否为日期
    public static boolean isDate(String date, String format) {
        try {
            if (date == null || format == null) {
                return false;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取LocalDate日期
     *
     * @param date
     * @param format
     * @return
     */
    public static LocalDate getLocalDate(String date, String format) {
        try {
            if (date == null || format == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取LocalDateTime日期
     *
     * @param date
     * @param format
     * @return
     */
    public static LocalDateTime getLocalDateTime(String date, String format) {
        try {
            if (date == null || format == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取日期的星期
    public static Integer getWeekByDate(String date, String format) {
        Integer week = null;
        try {
            if (date == null || format == null) {
                return week;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            week = LocalDate.parse(date, formatter).getDayOfWeek().getValue();
        } catch (Exception e) {
            return week;
        }
        return week;
    }


    public static Long diffDayStr(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24;
    }

    /**
     * 获取两个时间相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Long diffDayStr(String date1, String date2) {
        long beginTime = org.apache.poi.ss.usermodel.DateUtil.parseYYYYMMDDDate(date1).getTime();
        long endTime = org.apache.poi.ss.usermodel.DateUtil.parseYYYYMMDDDate(date2).getTime();
        long diff = beginTime - endTime;
        return (diff) / 1000 / 60 / 60 / 24;
    }

    /**
     * 获取格式日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDate(LocalDate date, String format) {
        try {
            if (date == null || format == null) {
                return "";
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return date.format(formatter);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据星期几（Integer） 获取对应的字符串
     *
     * @param number
     * @return
     */
    public static String getWeekNameByNumber(Integer number) {
        switch (number) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期天";
            default:
                return "未知";
        }
    }

    public static String getWeekByNumber(Integer number) {
        switch (number) {
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            case 7:
                return "周日";
            default:
                return "未知";
        }
    }

    public static String dealDateFormat(String oldDate) {
        if (StringUtil.isEmpty(oldDate)) {
            return "";
        }
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return df2.format(date1);
    }

    public static Date dateStrFormat(String dateStr, String format) {
        Date now = null;
        try {
            now = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 判断时间是否在时间段内(时：分)
     *
     * @param nowTime   00:00
     * @param beginTime 12:00
     * @param endTime   12:30
     */
    public static boolean belongCalendar(String nowTime, String beginTime, String endTime) {
        if (nowTime == null || beginTime == null || endTime == null) {
            return false;
        }

        int n = Integer.valueOf(nowTime.replaceAll(":", "")); //当前时间
        int s = Integer.valueOf(beginTime.replaceAll(":", ""));//结束时间
        int e = Integer.valueOf(endTime.replaceAll(":", "")); //开始时间
        if (n >= s && n <= e) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStringDate() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 当天起始时间
     *
     * @return 起始时间
     */
    public static Date getDateBegin() {
        return getDateBegin(new Date());
    }

    /**
     * 给定日期转当天起始时间
     *
     * @param date 指定日期
     * @return 起始时间
     */
    public static Date getDateBegin(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天结束时间
     *
     * @return 起始时间
     */
    public static Date getDateEnd() {
        return getDateEnd(new Date());
    }

    /**
     * 给定日期转当天结束时间
     *
     * @param date 指定日期
     * @return 起始时间
     */
    public static Date getDateEnd(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 当月月初
     *
     * @return 月初
     */
    public static Date getMonthBegin() {
        return getMonthBegin(new Date());
    }

    /**
     * 给定日期月初
     *
     * @param date 指定日期
     * @return 指定日期月初
     */
    public static Date getMonthBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDateBegin(calendar.getTime());
    }

    /**
     * 当月月末
     *
     * @return 当月月末
     */
    public static Date getMonthEnd() {
        return getMonthEnd(new Date());
    }

    /**
     * 给定日期转当月末
     *
     * @param date 指定日期
     * @return 指定日期月末
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return getDateEnd(calendar.getTime());
    }

    /**
     * 获取给定月数间隔的月份列表
     *
     * @param month 给定月份
     * @return 月份列表
     */
    public static List<Date> rangMonthList(int month) {
        return rangMonthList(month, new Date());
    }

    /**
     * 获取指定日期下 给定月数间隔的月份列表
     *
     * @param month 给定月份
     * @param date  指定日期
     * @return 月份列表
     */
    public static List<Date> rangMonthList(int month, Date date) {
        List<Date> list = Lists.newArrayList();
        for (int i = month - 1; i >= 0; i--) {
            list.add(getMonthBegin(DateUtils.addMonths(date, -i)));
        }
        return list;
    }

    /**
     * 获取给定天数间隔的天数列表
     *
     * @param day 给定天数
     * @return 天数列表
     */
    public static List<Date> rangDayList(int day) {
        return rangDayList(day, new Date());
    }

    /**
     * 获取指定日期下 给定天数间隔的天数列表
     *
     * @param day  给定天数
     * @param date 指定日期
     * @return 天数列表
     */
    public static List<Date> rangDayList(int day, Date date) {
        List<Date> list = Lists.newArrayList();
        for (int i = day - 1; i >= 0; i--) {
            list.add(getDateBegin(DateUtils.addDays(date, -i)));
        }
        return list;
    }

    public static String getWeekDay(int dayWeek) {
        switch (dayWeek) {
            case 1:
                return "(星期一)";
            case 2:
                return "(星期二)";
            case 3:
                return "(星期三)";
            case 4:
                return "(星期四)";
            case 5:
                return "(星期五)";
            case 6:
                return "(星期六)";
            case 7:
                return "(星期日)";
            default:
                return "";
        }
    }

    /**
     * 指定时间，秒数置0
     *
     * @param date 给定日期
     */
    public static Date resetSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取年初
     */
    public static Date getYearStart() {
        return getYearStart(new Date());
    }

    /**
     * 获取指定日期年初
     */
    public static Date getYearStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDateBegin(calendar.getTime());
    }

    /**
     * 获取年末
     */
    public static Date getYearEnd() {
        return getYearEnd(new Date());
    }

    /**
     * 获取指定日期年末
     */
    public static Date getYearEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return getDateEnd(calendar.getTime());
    }

    /**
     * 获取本周第一天
     */
    public static Date getWeekBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateEnd());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getDateBegin(calendar.getTime());
    }

    /**
     * 获取给定天数间隔的天数 起始日期Map对 列表
     *
     * @param day 给定天数
     * @return 天数列表
     */
    public static List<DateMap> rangDayMap(int day) {
        return rangDayMap(day, new Date());
    }

    /**
     * 获取指定日期下 给定天数间隔的天数 起始日期Map对 列表
     *
     * @param day  给定天数
     * @param date 指定日期
     * @return 天数列表
     */
    public static List<DateMap> rangDayMap(int day, Date date) {
        List<DateMap> list = new ArrayList<>(day);
        for (int i = day - 1; i >= 0; i--) {
            DateMap dateMap = new DateMap();
            dateMap.setStartDate(getDateBegin(DateUtils.addDays(date, -i)));
            dateMap.setEndDate(getDateEnd(dateMap.getStartDate()));
            list.add(dateMap);
        }
        return list;
    }

    /**
     * 获取给定月数间隔的月份 起始日期Map对 列表 (主要用于按天统计，比如：近一个月每天的变化量)
     *
     * @param month 给定月份
     * @return 月份列表
     */
    public static List<DateMap> rangMonthMap(int month) {
        return rangMonthMap(month, new Date());
    }

    /**
     * 获取指定日期下 给定月数间隔的月份 起始日期Map对 列表 (主要用于按月统计，比如：近一年每月的变化量)
     *
     * @param month 给定月份
     * @param date  指定日期
     * @return 天数列表
     */
    public static List<DateMap> rangMonthMap(int month, Date date) {
        List<DateMap> list = new ArrayList<>(month);
        for (int i = month - 1; i >= 0; i--) {
            DateMap dateMap = new DateMap();
            dateMap.setStartDate(getMonthBegin(DateUtils.addMonths(date, -i)));
            dateMap.setEndDate(getMonthEnd(dateMap.getStartDate()));
            list.add(dateMap);
        }
        return list;
    }


    /**
     * 判断传入的时间是否跟现在的超过  hour  小时 小于返回true
     *
     * @param firstTime
     * @return
     * @throws ParseException
     */
    public static boolean isMoreThanOneHours(String firstTime, Integer hour) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long now = System.currentTimeMillis();
        Date start = sdf.parse(firstTime);
        long cha = now - start.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);
        if (result < hour) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取日期前n个月，包括当月
     *
     * @param startDate 当前日期minusMonths
     * @param lastNum   倒推月份数量
     */
    public static List<MonthVo> getForwardMonthMonthVo(Date startDate, Integer lastNum) {
        List<MonthVo> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("M");
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = start.plusMonths(1);
        list.add(new MonthVo(start.format(dtf), end.format(dtf), start.format(dtf1) + "月"));
        for (int i = 0; i < (lastNum - 1); i++) {
            start = start.minusMonths(1);
            end = end.minusMonths(1);
            list.add(new MonthVo(start.format(dtf), end.format(dtf), start.format(dtf1) + "月"));
        }
        return list;
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static String getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static String getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 23:59:59";
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static String getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
    }

    /**
     * 获取本月最后一天
     *
     * @return String
     **/
    public static String getThisMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 23:59:59";
    }


    /**
     * 获取当前年的第一天
     *
     * @return
     */
    public static String getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time) + " 00:00:00";
    }

    /**
     * 获取当前年的最后一天
     *
     * @return
     */
    public static String getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time) + " 23:59:59";
    }

    /**
     * 计算时间相隔月数
     *
     * @param date1 d1
     * @param date2 d2
     * @return int
     */
    public static Integer getDifferMonths(Date date1, Date date2) {
        if (Objects.isNull(date1) || Objects.isNull(date2)) {
            return 0;
        }
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(date1);
        aft.setTime(date2);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return month + result;
    }


    /**
     * 获取日期前n个月，包括当月
     *
     * @param startDate 当前日期minusMonths
     * @param lastNum   倒推月份数量
     *                  返回 排序
     */
    public static List<MonthVo> getForwardMonthMonthVoBySort(Date startDate, Integer lastNum) {
        List<MonthVo> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("M");
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = start.plusMonths(1);
        list.add(new MonthVo(start.format(dtf), end.format(dtf), start.format(dtf1) + "月"));
        for (int i = 0; i < (lastNum - 1); i++) {
            start = start.minusMonths(1);
            end = end.minusMonths(1);
            list.add(new MonthVo(start.format(dtf), end.format(dtf), start.format(dtf1) + "月"));
        }


        //排序方法
        Collections.sort(list, new Comparator<MonthVo>() {
            @Override
            public int compare(MonthVo o1, MonthVo o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                try {
                    Date dt1 = format.parse(o1.getStartDate());
                    Date dt2 = format.parse(o2.getStartDate());
                    // 这是由大向小排序   如果要由小向大转换比较符号就可以
                    if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }

        });

        return list;
    }

    /**
     * 获取指定月份的开始日期 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getMonthBeginDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return LocalDate.of(date.getYear(), date.getMonthValue(), 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 获取开始时间参数，根据传入的相隔单位
     *
     * @param dateType
     * @return
     */
    public static String getBeginDateByType(String dateType) {
        String beginTime = null;
        if (dateType != null) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate today = LocalDate.now();
            String week = "week", month = "month", year = "year";
            if (week.equals(dateType)) {
                beginTime = today.minusDays(7).format(format);
            } else if (month.equals(dateType)) {
                beginTime = today.minusDays(30).format(format);
            } else if (year.equals(dateType)) {
                beginTime = today.minusDays(365).format(format);
            }
        }
        return beginTime;
    }

    /**
     * 获取当前时间前几天 或后几天的时间
     *
     * @param date
     * @param addDay 正数 next  负数 before
     * @return
     */
    public static Date getNextOrBeforeTime(Date date, Integer addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + addDay);
        return calendar.getTime();
    }

    /**
     * 一段时间的所有日期
     *
     * @param beginDay
     * @param endDay
     * @param format
     * @return
     */
    public static List<String> getDateBetweenList(LocalDate beginDay, LocalDate endDay, String format) {
        List<String> dateStrList = new ArrayList<>();
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd";
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        for (LocalDate day = beginDay; day.isBefore(endDay) || day.isEqual(endDay); day = day.plusDays(1)) {
            dateStrList.add(dateFormat.format(day));
        }
        return dateStrList;
    }

    public static List<String> getDateBetweenList(LocalDate beginDay, LocalDate endDay) {
        String format = "yyyy-MM-dd";
        return getDateBetweenList(beginDay, endDay, format);
    }

    /**
     * 获取区间内（包含开始和结束）所有日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findSectionDates(Date dBegin, Date dEnd) {
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter formatNew = DateTimeFormatter.ofPattern(("yyyy-MM-dd"));
        LocalDate dBeginNew = LocalDate.parse(format.format(dBegin), formatNew);
        LocalDate dEndNew = LocalDate.parse(format.format(dEnd), formatNew);
        try {
            for (LocalDate start = dBeginNew; !start.isAfter(dEndNew); start = start.plusDays(1)) {
                dates.add(format.parse(formatNew.format(start)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public static boolean isOverlap(String startdate1, String enddate1, String startdate2, String enddate2) {

        Date leftStartDate = null;

        Date leftEndDate = null;

        Date rightStartDate = null;

        Date rightEndDate = null;

        try {

            leftStartDate = format.parse(startdate1);

            leftEndDate = format.parse(enddate1);

            rightStartDate = format.parse(startdate2);

            rightEndDate = format.parse(enddate2);

        } catch (ParseException e) {

            return false;

        }

        return

                ((leftStartDate.getTime() >= rightStartDate.getTime())

                        && leftStartDate.getTime() < rightEndDate.getTime())

                        ||

                        ((leftStartDate.getTime() > rightStartDate.getTime())

                                && leftStartDate.getTime() <= rightEndDate.getTime())

                        ||

                        ((rightStartDate.getTime() >= leftStartDate.getTime())

                                && rightStartDate.getTime() < leftEndDate.getTime())

                        ||

                        ((rightStartDate.getTime() > leftStartDate.getTime())

                                && rightStartDate.getTime() <= leftEndDate.getTime());

    }

    /**
     * 字符串转换为java.util.Date<br>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
     *
     * @param time String 字符串<br>
     * @param s
     * @return Date 日期<br>
     */
    public static Date stringToDate(String time, String s) throws ParseException {
        SimpleDateFormat formatter;
        int tempPos = time.indexOf("AD");
        time = time.trim();

        if (time.length() == 10) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date ctime = formatter.parse(time);
            return ctime;
        }

        formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        if (tempPos > -1) {
            time = time.substring(0, tempPos) + "公元" + time.substring(tempPos + "AD".length());//china
            formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        tempPos = time.indexOf("-");
        if (tempPos > -1 && (time.indexOf(" ") < 0)) {
            formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
        } else if ((time.indexOf("/") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if ((time.indexOf("-") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if ((time.indexOf("/") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        } else if ((time.indexOf("-") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        }
        ParsePosition pos = new ParsePosition(0);
        Date ctime = formatter.parse(time, pos);

        return ctime;
    }

    public static String formatDateToString(Date datetime, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(datetime);
    }

    public static String LocalDateStringToFormatDate(String datetime) {
        if (StringUtil.isEmpty(datetime)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDateToString(date, "YYYY-MM-dd");
    }

    public static List<String> findDates(String dBegin, String dEnd) {
        //日期工具类准备
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //设置开始时间
        Calendar calBegin = Calendar.getInstance();
        List<String> Datelist = null;
        try {
            calBegin.setTime(format.parse(dBegin));

            //设置结束时间
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(format.parse(dEnd));

            //装返回的日期集合容器
            Datelist = new ArrayList<String>();
            //将第一个月添加里面去
            Datelist.add(format.format(calBegin.getTime()) + " 00:00:00");
            // 每次循环给calBegin日期加一天，直到calBegin.getTime()时间等于dEnd
            while (format.parse(dEnd).after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                Datelist.add(format.format(calBegin.getTime()) + " 00:00:00");
            }
        } catch (ParseException e) {
            return Collections.emptyList();
        }
        return Datelist;
    }

    public static String getYesterDay() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        return df.format(calendar.getTime());
    }

    public static String getToday() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static Date getYesterDayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.HOUR_OF_DAY,-24);
//        String format = df.format(calendar.getTime());
//        System.out.println(format);
        System.out.println(getToday());
    }
}
