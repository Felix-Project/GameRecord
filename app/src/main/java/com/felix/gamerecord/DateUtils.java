package com.felix.gamerecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 日期转换类
 * Created by Jamie on 2015/11/26.
 */
public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();

    private DateUtils() {
        //do nothing
    }

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_2 = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_CHINESE = "yyyy年MM月dd日";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDD_DOT = "yyyy.MM.dd";
    public static final String HHMM = "HHMM";
    public static final String HH_COLONMM = "HH:mm";
    public static final String MMDDHH_COLONMM = "MM/dd HH:mm";
    public static final String MMDD_COLONMM = "MM-dd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 得到当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {
        return getCustomTime(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 得到当前日期格式
     *
     * @return yyyyMMddHHmmssSSS
     */
    public static String getLongDateTime() {
        return getCustomTime("yyyyMMddHHmmssSSS");
    }

    /**
     * 得到当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getDate() {
        return getCustomTime(YYYY_MM_DD);
    }

    /**
     * 得到当前时间
     *
     * @return HH:mm:ss
     */
    public static String getTime() {
        return getCustomTime("HH:mm:ss");
    }

    /**
     * 得到当前时间 - 月
     *
     * @return MM
     */
    public static String getDateOfMonth() {
        return getCustomTime("MM");
    }

    /**
     * 得到当前时间 - 年
     *
     * @return yyyy
     */
    public static String getDateOfYear() {
        return getCustomTime("yyyy");
    }

    /**
     * 得到当前时间 - 日
     *
     * @return dd
     */
    public static String getDateOfDay() {
        return getCustomTime("dd");
    }

    /**
     * 得到当前时间 - 小时
     *
     * @return HH:mm:ss
     */
    public static String getTimeOfHour() {
        return getCustomTime("HH");
    }

    /**
     * 得到当前时间 - 分钟
     *
     * @return HH:mm:ss
     */
    public static String getTimeOfMinutes() {
        return getCustomTime("mm");
    }

    /**
     * 获得cx更改时间
     *
     * @param time
     * @return
     */
    public static String getCXChangeTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        return sdf.format(new Date(time));
    }

    /**
     * 时间戳转换 - 小时:分钟
     *
     * @return HH:mm
     */
    public static String getTimeOfHourAndMinute(long ccTime) {
        String reStrTime;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            // 例如：ccTime=1291778220
            reStrTime = sdf.format(new Date(ccTime));
        } catch (Exception e) {
            e.printStackTrace();
            reStrTime = "";
        }
        return reStrTime;
    }

    /**
     * 时间戳转换 - 天
     *
     * @return HH:mm:ss
     */
    public static String getTimeOfDay(long ccTime) {
        String reStrTime;
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        // 例如：ccTime=1291778220
        reStrTime = sdf.format(new Date(ccTime));
        return reStrTime;
    }

    /**
     * 获取某天最后一刻
     *
     * @param date
     * @return
     */
    public static Date getLastMomentOfDate(Date date) {
        String customTime = getCustomTime(date, YYYY_MM_DD);
        customTime += " 23:59:59";
        Date dateTime = getDateByFormat(customTime, YYYY_MM_DD_HH_MM_SS);
        return dateTime;
    }

    public static Date getInitialMomentOfDate(Date date) {
        String customTime = getCustomTime(date, YYYY_MM_DD);
        customTime += " 00:00:01";
        Date dateTime = getDateByFormat(customTime, YYYY_MM_DD_HH_MM_SS);
        return dateTime;
    }

    /**
     * 时间戳转换  月-日 小时:分钟
     *
     * @return MM-dd HH:mm
     */
    public static String getTimeOfDateAndTime(long ccTime) {
        String reStrTime;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        // 例如：cc_time=1291778220
        reStrTime = sdf.format(new Date(ccTime));
        return reStrTime;
    }

    /**
     * 时间戳转换 - 年 月 日 小时:分钟
     *
     * @return HH:mm:ss
     */
    public static String getDateAndTime(long ccTime) {
        String reStrTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 例如：cc_time=1291778220
        reStrTime = sdf.format(new Date(ccTime));
        return reStrTime;
    }

    /**
     * 时间戳转换 - 年 月 日 小时:分钟:秒
     *
     * @param time
     * @return
     */
    public static String longConvertToStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(new Date(time));
    }

    /**
     * 转换到指定格式
     *
     * @param time
     * @param formate
     * @return
     */
    public static String longConvertToFormateStr(long time, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(new Date(time));
    }

    /**
     * 时间戳转换 - x月x日
     *
     * @return HH:mm:ss
     */
    public static String getDate(long ccTime) {
        String reStrTime;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        // 例如：ccTime=1291778220
        reStrTime = sdf.format(new Date(ccTime));
        return reStrTime;
    }

    /**
     * 日期格式 yyyy-MM-dd 到 yyyyMMdd
     */
    public static String changeDateFormat1(String strDate) {
        String strPost = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
            Date date = new SimpleDateFormat(YYYY_MM_DD).parse(strDate);
            strPost = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strPost;
    }

    /**
     * 日期格式 yyyyMMdd 到 yyyy/MM/dd
     */
    public static String changeDateFormat2(String strDate) {
        String strDes = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new SimpleDateFormat(YYYYMMDD).parse(strDate);
            strDes = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strDes;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @return
     */
    public static String date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取自定义格式时间
     *
     * @param format 格式：yyyy年、MM月、dd日、HH小时、mm分钟、ss秒
     * @return
     */
    public static String getCustomTime(String format) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取自定义格式时间
     *
     * @param date
     * @param format 格式：yyyy年、MM月、dd日、HH小时、mm分钟、ss秒
     * @return
     */
    public static String getCustomTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取自定义格式时间
     *
     * @param time
     * @return
     */
    public static String getCustomTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(MMDDHH_COLONMM);
        return sdf.format(time);
    }

    /**
     * 获取自定义格式时间
     *
     * @param time
     * @return
     */
    public static String getCustomTimeMMDD(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(MMDD_COLONMM);
        return sdf.format(time);
    }

    public static String getCustomTimeHHmm(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(HH_COLONMM);
        return sdf.format(time);
    }

    /**
     * 传入String，获得一个Date对象
     *
     * @param strDate
     * @return yyyy-MM-dd HH:mm:ss 返回null为字符串错误
     */
    public static Date getDateTime(String strDate) {
        return getDateByFormat(strDate, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 传入String(例20130101210000)，获得一个自定义格式的Date(yyyy-MM-dd HH:mm:ss)对象
     *
     * @param strDate
     * @param format  格式
     * @return 返回null为字符串错误
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 传入如yyyyMMddHHmmss格式字符串，计算与当前时间距离多久
     *
     * @param timer yyyyMMddHHmmss 格式
     * @return 时间
     * @throws ParseException
     */
    public static String getComputeNowTimeStr(String timer, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(timer);
        Calendar today = Calendar.getInstance();
        long interval = today.getTimeInMillis() - date.getTime();
        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        boolean isSameDay = (interval / 86400000) == 0;
        if (isSameDay) {
            if (interval < 60 * 60 * 1000) {
                return String.valueOf((interval / (60 * 1000)));
            } else {
                return String.valueOf((interval / (60 * 60 * 1000)));
            }
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd hh:mm");
            return dateFormat.format(date);
        }
    }

    /**
     * 计算倒计时时间
     *
     * @param leftTime
     * @return
     */
    public static String calculateCountdown(long leftTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date(leftTime));
    }

    public static String getDateSS(long cc_time) {
        String re_StrTime;
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        // 例如：cc_time=1291778220
        re_StrTime = sdf.format(new Date(cc_time));
        return re_StrTime;
    }

    /**
     * 传入一个时间，判断是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekDayShort(Date date) {
        Calendar calendar = Calendar.getInstance();
        return getWeekDayShort(calendar);
    }

    /**
     * 传入一个时间，判断是星期几
     *
     * @param calendar
     * @return
     */
    public static String getWeekDayShort(Calendar calendar) {
        int x = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = new String[]{"天", "一", "二", "三", "四",
                "五", "六"};
        if (x > 7) {
            return "error";
        }
        return days[x - 1];
    }

    /**
     * 获得给出的日期前后多少天之间的日期集合
     *
     * @param calendar
     * @param before
     * @param after
     * @return
     */
    public static List<Calendar> getAreaDate(Calendar calendar, int before, int after) {
        List<Calendar> calendarList = new ArrayList<>();
        Calendar newCalendar;
        for (int i = 0 - before; i <= after; i++) {
            newCalendar = Calendar.getInstance();
            newCalendar.setTime(calendar.getTime());
            newCalendar.add(Calendar.DATE, i);
            calendarList.add(newCalendar);
        }
        return calendarList;
    }

    /**
     * 获得给出的日期当前月的日期集合
     * yyyy-MM-dd
     *
     * @return
     */
    public static List<String> getMonthDate(Calendar calendar) {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
        int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < dayCount; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);
            dateList.add(dateFormat.format(calendar.getTime()));
        }
        return dateList;
    }

    /**
     * 获取开始时间与结束时间之间的时间集合
     * 传入与返回的时间格式为HHmm
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> getAreaHour(String beginTime, String endTime) {
        List<String> hourList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(HHMM);
        try {
            Date beginDate = dateFormat.parse(beginTime);
            Date endDate = dateFormat.parse(endTime);
            dateFormat = new SimpleDateFormat(HH_COLONMM);
            Calendar calendar = Calendar.getInstance();
            int interval = (int) ((endDate.getTime() - beginDate.getTime()) / 1000 / 3600);
            for (int i = 0; i <= interval; i++) {
                calendar.setTimeInMillis(beginDate.getTime());
                calendar.add(Calendar.HOUR_OF_DAY, i);
                hourList.add(dateFormat.format(calendar.getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hourList;
    }

    /**
     * 比较时间大小，传入日期格式
     * tianfenfen
     */
    public static boolean timeComparison(String timeStart, String timeEnd, String format) {
        java.text.DateFormat df = new SimpleDateFormat(format);
        Calendar dateStart = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        try {
            dateStart.setTime(df.parse(timeStart));
            dateEnd.setTime(df.parse(timeEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = dateStart.compareTo(dateEnd);
        return result > 0;
    }

    /**
     * 计算大月
     *
     * @param month
     * @return
     */
    private static boolean isLeapMonth(int month) {
        boolean firstCondition = month == 1 || month == 3;
        boolean secondCondition = month == 5 || month == 7;
        boolean thirdCondition = month == 8 || month == 10 || month == 12;
        return firstCondition || secondCondition || thirdCondition;
    }

//    public static long transferStringToLong(String time) {
//        if (StringUtils.isNumeric(time)) {
//            return Long.parseLong(time);
//        }
//        try {
//            SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.CHINA);
//            format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//            Date date = format.parse(time);
//            return date.getTime();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return System.currentTimeMillis();
//    }

    /**
     * 判断是否是当天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time, String dateFormat) {
        return isThisTime(time, dateFormat);
    }

    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    /**
     * 把 2018-02-17 14:47:54 转换成long类型毫秒值；
     *
     * @param str
     * @return
     */
    public static long formatDate2Long(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * ========================================
     *
     * @Author Jinhuan.Li
     * @Params
     * @Desc TODO: 获取当前时间的当天的0时时间戳
     * ========================================
     */
    public static long getTodayZeroTime() {
        long current = System.currentTimeMillis();
        return current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }
}
