package com.zhiyu.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author wzy
 */
@Slf4j
public class DateUtil {
    private DateUtil() {

    }

    private static final String COMMUNICATION_FORMAT = "yyyyMMddHHmmssSSS";
    private static final String CHINA_DATE_FORMAT = "yyyyMMdd";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final long ONEDAY_MILLISECONDS = 86400000L;
    private static final long ONEHOUR_MILLISECONDS = 3600000L;
    private static final long ONEMINUS_MILLISECONDS = 60000L;
    private static final int YEARS = 365;

    /**
     * 得到几天前的时间
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        now.add(Calendar.MINUTE, -1);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static String getJavaScriptString(Date source) {
        if (source == null) {
            return "";
        }
        SimpleDateFormat ymrFormat = new SimpleDateFormat(COMMUNICATION_FORMAT);

        return ymrFormat.format(source);
    }

    public static String getChinaDateString(Date source) {
        if (source == null) {
            return "";
        }
        SimpleDateFormat ymrFormat = new SimpleDateFormat(CHINA_DATE_FORMAT);

        return ymrFormat.format(source);
    }

    public static String formatDateString(Date d) {
        SimpleDateFormat ymrFormat = new SimpleDateFormat(DATE_FORMAT);
        return ymrFormat.format(d);
    }

    public static String formatDateTimeString(Date d) {
        SimpleDateFormat ymrFormat = new SimpleDateFormat(DATETIME_FORMAT);
        return ymrFormat.format(d);
    }
    // 获取当前年

    public static int getYear() {
        Calendar startTime = Calendar.getInstance();
        return startTime.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getMonth() {
        Calendar startTime = Calendar.getInstance();
        return startTime.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getDay() {
        Calendar startTime = Calendar.getInstance();
        return startTime.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 从日期对象中获取年
     *
     * @param d
     * @return
     */
    public static int getYearFromDate(Date d) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(d);
        return startTime.get(Calendar.YEAR);
    }

    /**
     * 从日期中获取月份
     *
     * @param d
     * @return
     */
    public static int getMonthFromDate(Date d) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(d);
        return startTime.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日历对象，仅包含年月日
     *
     * @return
     */
    public static Calendar getDateCalendar() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime;
    }

    /**
     * 仅根据年月创建日期对象
     *
     * @param year
     * @param month
     * @return
     */
    public static Date createFirstDayOfMonth(int year, int month) {
        return createDate(year, month, 1);
    }

    /**
     * 创建下月1号对象
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfNextMonth(int year, int month) {
        month++;
        int value = 12;
        if (month > value) {
            month = 1;
            year++;
        }
        return createDate(year, month, 1);
    }

    /**
     * 判断是否第一个日期是否在后两个日期中间（包括两头日期）
     *
     * @param cd
     * @param sd
     * @param ed
     * @return
     */
    public static boolean isBetween(Date cd, Date sd, Date ed) {
        long currentTime = cd.getTime();
        long t1 = getDateTimeInMillis(sd);
        long t2 = getDateTimeInMillis(ed);
        if (t1 == 0 && t2 == 0) {
            return false;
        }
        if (t1 > 0 && t2 > 0) {
            return currentTime >= t1 && currentTime <= t2;
        }
        if (t2 == 0) {
            return currentTime >= t1;
        }
        return currentTime <= t2;
    }


    /**
     * 获取日期的长整型值
     *
     * @param d
     * @return
     */
    public static long getDateTimeInMillis(Date d) {
        if (d == null) {
            return 0L;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        return c1.getTimeInMillis();
    }


    /**
     * 得到下一个固定几点的日期，比如想让一个线程在每天的凌晨1点开始运行
     * 那么第一次运行的时间就可以用该方法获取
     *
     * @param h
     * @return
     */
    public static Date getNextFixedHourDate(int h) {
        return getNextFixedHourDate(h, 0);
    }

    public static Date getNextFixedHourDate(int h, int m) {
        Calendar startTime = Calendar.getInstance();
        if (startTime.get(Calendar.HOUR_OF_DAY) >= h) {
            if (startTime.get(Calendar.HOUR_OF_DAY) == h) {
                if (startTime.get(Calendar.MINUTE) >= m) {
                    startTime.add(Calendar.DAY_OF_MONTH, 1);
                }
            } else {
                startTime.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        startTime.set(Calendar.HOUR_OF_DAY, h);
        startTime.set(Calendar.MINUTE, m);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    public static Date getNextFixedHourDate(int h, int m, int hours) {
        Calendar startTime = Calendar.getInstance();
        if (startTime.get(Calendar.HOUR_OF_DAY) >= h) {
            if (startTime.get(Calendar.HOUR_OF_DAY) == h) {
                if (startTime.get(Calendar.MINUTE) >= m) {
                    startTime.add(Calendar.HOUR_OF_DAY, hours);
                }
            } else {
                startTime.add(Calendar.HOUR_OF_DAY, hours);
            }
        }

        startTime.set(Calendar.HOUR_OF_DAY, h);
        startTime.set(Calendar.MINUTE, m);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 获取给定日期下个月1号
     *
     * @param d
     * @return
     */
    public static Date getFirstDayOfNextMonth(Date d) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(d);
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.add(Calendar.MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 今天是不是1号
     *
     * @return
     */
    public static boolean isFirstDayOfMonthToday() {
        Calendar startTime = Calendar.getInstance();
        return startTime.get(Calendar.DAY_OF_MONTH) == 1;
    }

    /**
     * 今天是不是本月的最后一天
     *
     * @return
     */
    public static boolean isLastDayOfMonthToday() {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, 1);
        return startTime.get(Calendar.DAY_OF_MONTH) == 1;
    }

    /**
     * 获取明天的日期
     *
     * @return
     */
    public static Date getNextDayOfToday() {
        return getNextDayOfDay(new Date(), 1);
    }

    /**
     * 获取下个月这一天
     *
     * @param d
     * @return
     */
    public static Date getNextMonthDay(Date d) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(d);
        startTime.add(Calendar.MONTH, 1);
        return startTime.getTime();
    }

    public static BigDecimal generateYearsBetween(Date t1, Date t2) {
        long time = t1.getTime() - t2.getTime();
        BigDecimal days = BigDecimal.valueOf(time / ONEDAY_MILLISECONDS);
        return days.divideAndRemainder(BigDecimal.valueOf(YEARS))[0];
    }

    public static int generateMonthsBetween(Date t1, Date t2) {
        long time = t1.getTime() - t2.getTime();
        long days = time / ONEDAY_MILLISECONDS;
        return (int) days / 30;
    }

    public static int generateDaysBetween(Date t1, Date t2) {
        long time = t1.getTime() - t2.getTime();
        long days = time / ONEDAY_MILLISECONDS;
        return (int) days;
    }

    public static double generateHoursBetween(Date t1, Date t2) {
        long time = t1.getTime() - t2.getTime();
        long h = time / ONEHOUR_MILLISECONDS;
        long m1 = time % ONEHOUR_MILLISECONDS;
        long m = m1 / ONEMINUS_MILLISECONDS;
        int value = 45;
        int values = 15;
        if (m > value) {
            return (double) h + 1;
        } else if (m < values) {
            return (double) h;
        } else {
            return h + 0.5;
        }
    }

    public static int generateMinusBetween(Date t1, Date t2) {
        long time = t1.getTime() - t2.getTime();
        return (int) (time / 60000L);
    }

    /**
     * 生成两个日期之间相差的中文描述
     *
     * @param t1
     * @param t2
     * @return
     */
    public static String generateStringTimeBetween(Date t1, Date t2) {
        StringBuilder sb = new StringBuilder();
        long time = t1.getTime() - t2.getTime();
        long i1;
        long i2;
        i1 = time / ONEDAY_MILLISECONDS;
        i2 = time % ONEDAY_MILLISECONDS;
        if (i1 > 0) {
            sb.append(i1);
            sb.append("天");
        }
        i1 = i2 / ONEHOUR_MILLISECONDS;
        i2 = i2 % ONEHOUR_MILLISECONDS;
        if (i1 > 0) {
            sb.append(i1);
            sb.append("小时");
        }
        i1 = i2 / ONEMINUS_MILLISECONDS;
        if (i1 > 0) {
            sb.append(i1);
            sb.append("分钟");
        }
        return sb.toString();
    }


    /**
     * 给定日期加多少天
     *
     * @param d
     * @param i
     * @return
     */
    public static Date getNextDayOfDay(Date d, int i) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(d);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        startTime.add(Calendar.DAY_OF_MONTH, i);
        return startTime.getTime();
    }

    public static Date getFixedHoursBefore(int h) {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.HOUR_OF_DAY, h);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    public static Date getToday() {
        return getCurrentDate();
    }

    /**
     * 今天，仅包含月日年
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 昨天
     *
     * @return
     */
    public static Date getYesterday() {
        return getDayBefore(-1);
    }

    /**
     * 天数加法
     *
     * @param i
     * @return
     */
    public static Date getDayBefore(int i) {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, i);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    public static Date parseJavascriptDate(String source) {
        if (source == null || source.trim().length() == 0) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(COMMUNICATION_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    /**
     * 指定的日期是星期几
     *
     * @param d
     * @return
     */
    public static int getDayOfWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        return weekDay;
    }

    /**
     * 指定的日期是第几周
     *
     * @param d
     * @return
     */
    public static int getWeekOfYear(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 日期类型转换，从sql.Date转成util.Date
     *
     * @param d
     * @return
     */
    public static Date generateDateFromSqlDate(java.sql.Date d) {
        if (d == null) {
            return null;
        }
        return new Date(d.getTime());
    }

    /**
     * 日期类型转换，从util.Date转成sql.Date
     *
     * @param d
     * @return
     */
    public static java.sql.Date generateSqlDateFromDate(Date d) {
        if (d == null) {
            return null;
        }
        return new java.sql.Date(d.getTime());
    }

    /**
     * 日期类型转换，从sql.TimeStamp转成util.Date
     *
     * @param d
     * @return
     */
    public static Date generateDateFromSqlTimestamp(java.sql.Timestamp d) {
        if (d == null) {
            return null;
        }
        return new Date(d.getTime());
    }

    /**
     * 根据年月日生成日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static java.sql.Date generateDate(int year, int month, int day) {
        Date d = createDate(year, month, day);
        return new java.sql.Date(d.getTime());
    }

    /**
     * 根据年月日生成日期
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    public static Date createDate(int year, int month, int day, int hour) {
        return createDate(year, month, day, hour, 0);
    }

    public static Date createDate(int year, int month, int day, int hour, int m) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.DAY_OF_MONTH, day);
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, m);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        return time.getTime();
    }

    /**
     * 根据年月日生成日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date createDate(int year, int month, int day) {
        return createDate(year, month, day, 0);
    }


    /**
     * 获取去掉时分秒的日期
     *
     * @param date
     * @return
     */
    public static Date getDateOfDay(Date date) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(date);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 生成指定小时的日期
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date getDateOfSpecialHour(Date d, int hour) {
        return getDateOfSpecialHour(d, hour, 0);
    }

    public static Date getDateOfSpecialHour(Date d, int hour, int m) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(d);
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, m);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 两个日期是否是同一个月份的
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    }

    /**
     * 获取指定日期的日
     *
     * @param date
     * @return
     */
    public static int getDayOfDate(Date date) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(date);
        return startTime.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 两个日期是否为同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDay(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return false;
        }
        if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
            return false;
        }
        return cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 两个日期要精确相等
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameTime(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.MILLISECOND) == cal2.get(Calendar.MILLISECOND);
    }

    /**
     * 类型转换，把字符串表示的类型解析成日期对象
     *
     * @param str
     * @return
     */
    public static Date getDateOfString(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return date;
    }

    /**
     * 前天
     *
     * @return
     */
    public static Date theDayBeforeYesterday() {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, -2);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 3天以前
     *
     * @return
     */
    public static Date threeDaysAgo() {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, -3);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 4天以前
     *
     * @return
     */
    public static Date fourDaysAgo() {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, -4);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        return startTime.getTime();
    }

    /**
     * 比较两个日期大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        return date1.getTime() >= date2.getTime();
    }

    /**
     * 获取指定日期的最后一天的日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getLastDay(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String lastDay = format.format(calendar.getTime());
        return format.parse(lastDay);
    }

    /**
     * 获取指定日期的第一天的日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getFirstDay(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String firstDay = format.format(calendar.getTime());
        return format.parse(firstDay);
    }

    /**
     * 获取当前日期的下一个月的日期
     *
     * @param date
     * @return
     */
    public static Date getNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getCompareMonths(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)) * 12 + calendar2.get(Calendar.MONTH)
                - calendar1.get(Calendar.MONTH);
    }

    /**
     * 获取指定日期的年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(date);
        return startTime.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(date);
        return startTime.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期的当前天
     *
     * @param
     * @return
     */
    public static int getDayOfCureent(Date dates) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(dates);
        return startTime.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个月的总天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        return gCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个时间相差多少分
     *
     * @param endDate
     * @param startDate
     * @return
     * @throws ParseException
     */
    public static long getTimeDiffer(String endDate, String startDate) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        Date onDate = sf.parse(endDate);
        Date ofDate = sf.parse(startDate);
        Calendar onc = Calendar.getInstance();
        onc.setTime(onDate);
        //结束时间
        long offdutyValue = onc.getTimeInMillis();
        Calendar onf = Calendar.getInstance();
        onf.setTime(ofDate);
        //开始时间
        long ondutyValue = onf.getTimeInMillis();
        return (offdutyValue - ondutyValue) / (1000 * 60);
    }

    /**
     * String 转换为long
     * yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static long paseLong(String dateStr) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
        sf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = sf.parse(dateStr);
        return date.getTime();
    }

    /**
     * long 转换为 String
     * yyyy-MM-dd
     *
     * @param value
     * @return
     */
    public static String paseString(long value) {
        Date date = new Date(value);
        return formatDateString(date);
    }


    /**
     * 得到几个月之后的时间
     *
     * @param date
     * @param month
     * @return
     */
    public static String addMonthDate(String date, int month) {
        String nowDate = null;
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date parse = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.add(Calendar.MONTH, month);
            nowDate = format.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowDate;
    }

    /**
     * 日期相减 计算天数
     *
     * @param beforeDate
     * @param currentDate
     * @return
     */
    public static int differentDays(Date beforeDate, Date currentDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beforeDate);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(currentDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        //同一年
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                //
                Boolean leapYear = i % 4 == 0 && i % 100 != 0 || i % 400 == 0;
                if (leapYear) {
                    timeDistance += 366;
                    //不是闰年
                } else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        }
        //不同年
        else {
            return day2 - day1;
        }
    }


    /**
     * 类型转换，把字符串表示的类型解析成日期对象
     * yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static Date getDateOfStringYmh(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            log.error("字符串表示的类型解析成日期对象错误", e);
        }
        return date;
    }

    /**
     * 得到指定月份第一天的日期
     *
     * @param date
     * @param number
     * @return
     */
    public static Date getFirstDayMonth(Date date, int number) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, number);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String day = format.format(ca.getTime());
        try {
            return format.parse(day);
        } catch (ParseException e) {
            log.error("得到指定月份第一天的日期错误", e);
        }
        return null;
    }

    /**
     * 检查日期
     *
     * @param str
     * @return
     */
    public static boolean checkDateTime(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        boolean flag = true;
        try {
            LocalDate.parse(str, dtf);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 得到几小时之后的时间(包含分钟)
     *
     * @param date
     * @param time
     * @param pattern
     * @return
     */
    public static String addHourMinuteDate(String date, String time, String pattern) {
        String dateStr = null;
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        String zeroStr = "0";
        String nullStr = "null";
        try {
            int hour = 0;
            BigDecimal minute = BigDecimal.ZERO;
            if (!zeroStr.equals(time) && time != null && !nullStr.equals(time)) {
                int status = time.indexOf(".");
                hour = status > 0 ? DataHelpUtil.getIntValue(time.substring(0, status)) : DataHelpUtil.getIntValue(time);
                minute = status > 0 ? DataHelpUtil.getBigDecimalValue(time.substring(status).replace(".", "0.")) : BigDecimal.ZERO;
            }
            Date parse = sd.parse(date);
            Calendar ca = Calendar.getInstance();
            ca.setTime(parse);
            ca.add(Calendar.HOUR_OF_DAY, hour);
            if (minute.compareTo(BigDecimal.ZERO) > 0) {
                ca.add(Calendar.MINUTE, DataHelpUtil.getIntValue(minute.multiply(BigDecimal.valueOf(60L))));
            }
            dateStr = sd.format(ca.getTime());
        } catch (ParseException e) {
            log.error("得到几小时之后的时间(包含分钟)错误", e);
        }
        return dateStr;
    }


    /**
     * 得到几小时之后的时间
     *
     * @param date
     * @param hour
     * @param pattern
     * @return
     */
    public static String addHourDate(String date, int hour, String pattern) {
        String dateStr = null;
        SimpleDateFormat formats = new SimpleDateFormat(pattern);
        try {
            Date parse = formats.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.add(Calendar.HOUR_OF_DAY, hour);
            dateStr = formats.format(calendar.getTime());
        } catch (ParseException e) {
            log.error("得到几小时之后的时间错误", e);
        }
        return dateStr;
    }

    /**
     * 得到几分之后的时间
     *
     * @param date
     * @param minute
     * @param pattern
     * @return
     */
    public static String addMinuteDate(String date, int minute, String pattern) {
        String nowDates = null;
        SimpleDateFormat formats = new SimpleDateFormat(pattern);
        try {
            Date parse = formats.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.add(Calendar.MINUTE, minute);
            nowDates = formats.format(calendar.getTime());
        } catch (ParseException e) {
            log.error("得到几分之后的时间错误", e);
        }
        return nowDates;
    }

}
