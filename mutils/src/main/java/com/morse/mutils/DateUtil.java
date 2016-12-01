package com.morse.mutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libiao
 * @ClassName: DateUtil
 * @Description:格式化时间助手类
 * @date 2016-4-8 上午10:44:22
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
    private static String[] weekDaysName = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private static int ONE_WEEK_MILLIONS = 7 * 24 * 60 * 60 * 1000;
    private static int ONE_DAY_MILLIONS = 24 * 60 * 60 * 1000;

    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String Y_M_D_H_M_FORMAT = "yyyy-MM-dd HH:mm";

    private static String Y_M_D_H_FORMAT = "yyyy-MM-dd HH";

    private static String DATE_FORMAT = "yyyy-MM-dd";

    private static String TIME_FORMATE = "HH:mm:ss";

    private static String HM_FORMATE = "HH:mm";
    private static String MD_FORMATE = "MM-dd";

    private static String Y_M_CHINESE = "yyyy年MM月";

    private static String Y_M_FORMAT = "yyyy-MM";

    private static long APP_ONLINE_TIME = 1464710400 * 1000L;

    private static int ACTIVITY_STATUS_RUNNING = 1;
    private static int ACTIVITY_STATUS_FINISHED = 0;

    /**
     * @param @return
     * @return String yyyy-MM-dd格式的时间
     * @throws
     * @Title: getCurrenTime
     * @Description: 得到当前系统时间
     */
    public static String getCurrenDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * @param @return
     * @return String yyyy-MM格式的时间
     * @throws
     * @Title: getCurrenTime
     * @Description: 得到当前系统时间
     */
    public static String getCurrenMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String getCurrenMonth2() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String dayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return weekDaysName[dayForWeek - 1];
    }

    public static String getDateTime(String curSecendsStr) {
        String time = "";
        try {
            if (RegexUtils.isRegx(curSecendsStr, "^\\d+$")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time = sdf.format(new Date(Long.parseLong(curSecendsStr)));
            } else {
                time = curSecendsStr;
            }
        } catch (Exception e) {

        }
        return time;
    }

    public static String getDateDay(String curSecendsStr) {
        String time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            time = sdf.format(new Date(Long.parseLong(curSecendsStr)));
        } catch (Exception e) {

        }
        return time;
    }

    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    /**
     * @param @return
     * @return String yyyy-MM-dd HH格式的时间
     * @throws
     * @Title: getCurrenTime
     * @Description: 得到当前当前系统时间
     */
    public static String getActivityDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * @param @return
     * @return String yyyy-MM-dd HH格式的时间
     * @throws
     * @Title: getCurrenTime
     * @Description: 得到当前系统时间
     */
    public static String getActivityEndDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        Date curDate = new Date(System.currentTimeMillis() + 24 * 3600000);
        String str = formatter.format(curDate);
        return str;
    }

    public static int getCurrYear() {
        int year = 0;
        Calendar cd = Calendar.getInstance();
        year = cd.get(Calendar.YEAR);
        return year;
    }

    public static int getCurrMonth() {
        int year = 0;
        Calendar cd = Calendar.getInstance();
        year = cd.get(Calendar.MONTH);
        return year;
    }

    public static int getCurrDay() {
        int year = 0;
        Calendar cd = Calendar.getInstance();
        year = cd.get(Calendar.DAY_OF_MONTH);
        return year;
    }

    public static int getCurrHour() {
        int year = 0;
        Calendar cd = Calendar.getInstance();
        year = cd.get(Calendar.HOUR_OF_DAY);
        return year;
    }

    public static int getCurrMinute() {
        int year = 0;
        Calendar cd = Calendar.getInstance();
        year = cd.get(Calendar.MINUTE);
        return year;
    }

    public static int getCurrSecond() {
        int year = 0;
        Calendar cd = Calendar.getInstance();
        year = cd.get(Calendar.SECOND);
        return year;
    }

    public static String getFormatDateTime(int year, int month, int date, int hour, int minute, int second, String format) {
        String val = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, minute, second);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        val = formatter.format(calendar.getTime());
        return val;
    }

    public static Calendar getFormatStrDateTime(String dateTime, String format) {
        Calendar calendar = Calendar.getInstance();
        if (TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(format)) {
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date = sdf.parse(dateTime);
                calendar.setTime(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return calendar;
    }

    public static String getFormatDateTime(String dateTime) {
        return getFormatDateTime(dateTime, DATE_TIME_FORMAT);
    }

    public static String getFormatDateTime(String dateTime, String format) {
        String time = null;
        if (TextUtils.isEmpty(dateTime)) {
            return "";
        }
        try {
            SimpleDateFormat sdf = null;
//            if (RegexUtils.isRegx(dateTime, "^\\d+$")) {
            if (!TextUtils.isEmpty(format) && RegexUtils.checkInteger(dateTime)) {
                sdf = new SimpleDateFormat(format);
                time = sdf.format(new Date(Long.parseLong(dateTime)));
            } else {
                sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
                Date date = sdf.parse(dateTime);
                sdf = new SimpleDateFormat(format);
                time = sdf.format(date);
            }
        } catch (Exception e) {
            time = dateTime;
        }
        return time;
    }

    public static String getFormatDateTime(String dateTime, String oldFormat, String targetFormat) {
        String time = null;
        try {
            SimpleDateFormat sdf = null;
            if (RegexUtils.isRegx(dateTime, "^\\d+$")) {
                Date date = new Date(Long.parseLong(dateTime));
                sdf = new SimpleDateFormat(targetFormat);
                time = sdf.format(date);
            } else {
                if (TextUtils.isEmpty(oldFormat)) {
                    oldFormat = DATE_TIME_FORMAT;
                }
                sdf = new SimpleDateFormat(oldFormat);
                Date date = sdf.parse(dateTime);
                sdf = new SimpleDateFormat(targetFormat);
                time = sdf.format(date);
            }
        } catch (Exception e) {
            time = dateTime;
        }
        return time;
    }

    public static String getStartedDay(String dateTime, String format) {
        Calendar calendar = Calendar.getInstance();
        long currTime = calendar.getTimeInMillis();
        if (TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(format)) {
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date = sdf.parse(dateTime);

                long targetTime = date.getTime();
                long distance = currTime - targetTime;
                if (distance <= 0) {
                    return "0 天";
                }
                LogUtils.d("getStartedDay distance = " + distance + "; currTime = " + currTime + ";target = " + targetTime);

                float day = 0;
                String result = null;
                if (distance >= 24 * 3600000f) {
                    day = (distance / (24 * 3600000f));
                    int intVal = (int) day;
                    if (intVal == day) {
                        result = intVal + "";
                    } else {
                        // result = new DecimalFormat("0.0").format(day);
                        result = intVal + "";
                    }
                    result = result + " 天";
                } else {
                    day = distance / 3600000f;
                    int intVal = (int) day;
                    if (intVal == day) {
                        result = intVal + "";
                    } else {
                        // result = new DecimalFormat("0.0").format(day);
                        result = intVal + "";
                    }
                    result = result + " 小时";
                }
                return result;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "0 天";
    }

    public static String getDistanceEndDay(Context context, String dateTime, String format) {
        Calendar calendar = Calendar.getInstance();
        long currTime = calendar.getTimeInMillis();
        if (TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(format)) {
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date = sdf.parse(dateTime);
                calendar.setTime(date);
                long targetTime = calendar.getTimeInMillis();// + 24 * 3600000;
                long distance = targetTime - currTime;
                /*
                 * if(distance <= 0){ return "0"; }
				 */
                float day = 0;
                String result = null;
                /*
                 * if(distance >= 24 * 3600000f){ day = (distance / (24 *
				 * 3600000f)); int intVal = (int) day; if(intVal == day){ result
				 * = intVal + ""; }else{ //result = new
				 * DecimalFormat("0.0").format(day); result = intVal + ""; }
				 * result = "<font color=\"" +
				 * context.getResources().getColor(R.color.red) + "\">" + result
				 * + "</font>" + " 天"; }else{ day = distance / 3600000f; int
				 * intVal = (int) day; if(intVal == day){ result = intVal + "";
				 * }else{ //result = new DecimalFormat("0.0").format(day);
				 * result = intVal + ""; } result = "<font color=\"" +
				 * context.getResources().getColor(R.color.red) + "\">" + result
				 * + "</font>" + " 小时"; }
				 */
                day = (distance / (24 * 3600000f));
                int intVal = (int) (day + 1);
                if (day <= 0) {
                    return "0";
                } else if (intVal == day) {
                    result = intVal + "";
                } else {
                    // result = new DecimalFormat("0.0").format(day);
                    result = intVal + "";
                }
                result = "<font color=#00ffff>" + result + "</font>" + " 天";
                return result;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "0";
    }

    public static int getActStateByDateTime(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        long currTime = calendar.getTimeInMillis();
        String format = "yyyy-MM-dd HH";
        int state = ACTIVITY_STATUS_RUNNING;
        if (TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(format)) {
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date = sdf.parse(dateTime);
                calendar.setTime(date);
                long targetTime = calendar.getTimeInMillis();
                if (targetTime <= currTime) {
                    state = ACTIVITY_STATUS_FINISHED;
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return state;
    }

    public static String getDateTimeByLongStr(String dateTime) {
        String time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Integer.parseInt(dateTime, 10));
            time = sdf.format(calendar.getTime());
        } catch (Exception e) {
        }
        return time;
    }

    public static String getBillTime() {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        time = sdf.format(calendar.getTime());
        return time;
    }

    /**
     * @param DATE1
     * @param DATE2
     * @return
     * @Title: compare_date
     * @Description: 比较两个日期的大小，如果返回1：D1>D2，0：D1=D2, -1：D1<D2，-2发生异常
     * @return: int
     */
    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -2;
    }

    public static boolean isTargetDay(String dateTime, int day) {
        try {
            DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(format.parse(dateTime));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, day);
            int yesDay = calendar.get(Calendar.DAY_OF_MONTH);
            int yesDay1 = calendar1.get(Calendar.DAY_OF_MONTH);
            LogUtils.d("yesDay = " + yesDay + ";yesDay1 = " + yesDay1);
            if (yesDay == yesDay1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getHM(String dateTime) {
        try {
            DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = format.parse(dateTime);
            format = new SimpleDateFormat(HM_FORMATE);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getYMD(String dateTime) {
        try {
            DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = format.parse(dateTime);
            format = new SimpleDateFormat(DATE_FORMAT);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String formatShowLately(String dateTime) {
        if (TextUtils.isEmpty(dateTime)) return dateTime;
        dateTime = dateTime.trim();
        if (isTargetDay(dateTime, 0)) { //今天
            return getHM(dateTime);
        } else if (isTargetDay(dateTime, -1)) {
            return "昨天";
        } else {
            return getYMD(dateTime);
        }

    }

    public static String formatShowLatelyWithTime(String dateTime) {
        if (TextUtils.isEmpty(dateTime)) return dateTime;
        dateTime = getFormatDateTime(dateTime, DATE_TIME_FORMAT);
        dateTime = dateTime.trim();
        if (isTargetDay(dateTime, 0)) { //今天
            return getHM(dateTime);
        } else if (isTargetDay(dateTime, -1)) {
            return "昨天 " + getFormatDateTime(dateTime, HM_FORMATE);
        } else {
            return getFormatDateTime(dateTime, Y_M_D_H_M_FORMAT);
        }

    }

    public static String getTargetDateTime(int day) {
        return getTargetDateTime(day, DATE_TIME_FORMAT);
    }

    public static String getTargetDateTime(int day, String formatStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(calendar.getTime());
    }

    public static String getTargetDateTimeByMonth(int month, String formatStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(calendar.getTime());
    }

    public static String getFormatTime(int sceonds, String format) {
        //"yyyy-MM-dd HH:mm:ss"
        if (sceonds <= 0) {
            return "";
        }
        Date d = new Date(sceonds);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    public static List<String> getMonthList(String start, String end, long startMill) {
        List<String> list = new ArrayList<String>();
        if (startMill < APP_ONLINE_TIME) {
            list.add(start);
            return list;
        }
        while (!start.equals(end)) {
            list.add(start);

            String[] arrays = start.split("-");
            String year = arrays[0];
            String month = arrays[1];
            if (month.equals("12")) {
                year = Integer.parseInt(year) + 1 + "";
                month = "01";
                start = year + "-" + month;
                continue;
            }

            month = Integer.parseInt(month) + 1 + "";
            if (month.length() < 2) {
                month = "0" + month;
            }
            start = year + "-" + month;
        }

        list.add(end);
        Collections.reverse(list);
        return list;
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    public static long getMondayTime() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return c.getTimeInMillis();
    }


    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    public static List<String> getWeekList(int count) {
        List<String> list = new ArrayList<String>();
        long mondayTime = getMondayTime();
        String monday = getDateDay(mondayTime + "");
        String sunday = getDateDay(mondayTime + ONE_WEEK_MILLIONS - ONE_DAY_MILLIONS + "");
        list.add(monday + " 至 " + sunday);
        for (int i = 1; i < count; i++) {
            long newMondayTime = mondayTime - ONE_WEEK_MILLIONS * i;
            String start = getDateDay(newMondayTime + "");
            String end = getDateDay(newMondayTime + ONE_WEEK_MILLIONS - ONE_DAY_MILLIONS + "");
            list.add(start + " 至 " + end);
        }
        return list;
    }

    public static String getMonthLastDay(String yearMonth) {
        try {
            Calendar cal = Calendar.getInstance();
            // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
            String[] arrays = yearMonth.split("-");
            cal.set(Calendar.YEAR, Integer.parseInt(arrays[0]));
            cal.set(Calendar.MONTH, Integer.parseInt(arrays[1]));
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(cal.getTime());
        } catch (Exception e) {

        }

        return yearMonth + "-30";
    }

    public static String getMDHM(String time) {
        if (time.length() < 18) {
            return time;
        }

        int start = time.indexOf("-") + 1;
        int end = time.lastIndexOf(":");

        if (end - start < 8) {
            return time;
        }

        return time.substring(start, end);
    }

    public static boolean isDateTimeout(String date) {
        if (!TextUtils.isEmpty(date)) {
            String currDate = getCurrenDay();
            if (!TextUtils.isEmpty(currDate)) {
                if (date.compareTo(currDate) < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getCurrDateTime() {
        return getCurrDateTimeByFormat(DATE_TIME_FORMAT);
    }

    public static String getCurrDateTimeByFormat(String formatStr) {
        String dateTime = TextUtils.isEmpty(formatStr) ? DATE_TIME_FORMAT : formatStr;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            dateTime = sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    public static String getMonthDay(String yyMMdd) {
        try {
            String[] split = yyMMdd.split("-");
            if (split.length > 2) {
                return split[1] + "月" + split[2] + "日";
            }

        } catch (Exception e) {

        }
        return "";
    }

    public static String getFormatDateTimeWithWeek(String dateTime, String lastFormat, String targetFormat) {
        String time = null;
        if (TextUtils.isEmpty(dateTime)) {
            return "";
        }
        int dayForWeek = 0;
        try {
            SimpleDateFormat sdf = null;
            Date date = null;
            if (!TextUtils.isEmpty(targetFormat) && RegexUtils.checkInteger(dateTime)) {
                sdf = new SimpleDateFormat(targetFormat);
                date = new Date(Long.parseLong(dateTime));
                time = sdf.format(date);
            } else {
                sdf = new SimpleDateFormat(lastFormat);
                date = sdf.parse(dateTime);
                sdf = new SimpleDateFormat(targetFormat);
                time = sdf.format(date);
            }

            Calendar c = Calendar.getInstance();
            try {
                c.setTime(date);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            }

        } catch (Exception e) {
            time = dateTime;
        }

        time += "\t\t" + weekDaysName[dayForWeek - 1];

        return time;


    }

    public static String getTransactTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(time);
        calendar.setTime(date);
        String week;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                week = "星期一";
                break;
            case Calendar.TUESDAY:
                week = "星期二";
                break;
            case Calendar.WEDNESDAY:
                week = "星期三";
                break;
            case Calendar.THURSDAY:
                week = "星期四";
                break;
            case Calendar.FRIDAY:
                week = "星期五";
                break;
            case Calendar.SATURDAY:
                week = "星期六";
                break;
            case Calendar.SUNDAY:
                week = "星期日";
                break;
            default:
                week = "星期一";
                break;
        }
        String str = formatter.format(date);

        return str + week;
    }

    /**
     * 获取某月的第一天和最后一天
     *
     * @param time 格式为yyyy-MM
     */
    public static Map<String, String> getMonthFLDay(String time) {
        Map<String, String> map = new HashMap<>();

        String[] times = time.split("-");
        int year = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int tempMonth = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        if (month - 1 >= tempMonth) {
            map.put("last_day", getCurrenDay());
        } else {
            //month月最后一天的时间
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, lastDay);
            Date date = calendar.getTime();
            map.put("last_day", df.format(date));
            LogUtils.d(df.format(date));
        }

        //month月第一天的时间
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        Date date1 = calendar.getTime();

        LogUtils.d(df.format(date1));

        map.put("first_day", df.format(date1));

        return map;
    }


    public static int getYearDays() {
        boolean leayyear = false;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        if (year % 4 == 0 && year % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        if (leayyear) {
            return 366;
        }
        return 365;
    }

    public static int getHalfYearDays() {
        return getYearDays() / 2;
    }
}
