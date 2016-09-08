package com.yju.app.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public final class DateUtil {
	
    public static final long MILLIS_PER_SECOND = 1000;
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
    public static final String ISO_DATETIME_FORMAT_SORT = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    private static ConcurrentHashMap<String, SimpleDateFormat> cInstanceCache =
    		new ConcurrentHashMap<String, SimpleDateFormat>(3);
    
    private DateUtil() {
        super();
    }
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }


    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    

    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }


    public static Date addWeeks(Date date, int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }


    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }


    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
    

    public static String format(long millis, String pattern) {
        return format(new Date(millis), pattern, null);
    }


    public static String format(Date date, String pattern) {
        return format(date, pattern, null);
    }
    


    public static String format(Date date, String pattern, Locale locale) {
		if (date == null) {
			return "";
		}
    	
    	SimpleDateFormat format = null;
		if (locale == null) {
			format = cInstanceCache.get(pattern);
			if (format == null) {
				format = new SimpleDateFormat(pattern);
				cInstanceCache.put(pattern, format);
			}
		} else {
			format = cInstanceCache.get(pattern + locale);
			if (format == null) {
				format = new SimpleDateFormat(pattern, locale);
				cInstanceCache.put(pattern + locale, format);
			}
		}

        return format.format(date);
    }
    

    public static Date parseDate(String str, String[] parsePatterns) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        
        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);
        for (int i = 0; i < parsePatterns.length; i++) {
            if (i == 0) {
                parser = new SimpleDateFormat(parsePatterns[0]);
            } else {
                parser.applyPattern(parsePatterns[i]);
            }
            pos.setIndex(0);//重新设置文本解析起始点
            Date date = parser.parse(str, pos);
            if (date != null && pos.getIndex() == str.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }
    
  	public static Date formatString(String time, String format){
  		try{
  			SimpleDateFormat formater = new SimpleDateFormat(format);
  			return formater.parse(time);
  		}catch(Exception e){
  			return null;
  		}
  	}
    //String time to long
    public static long toLong(String timestr){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = df.parse(timestr);
        } catch (ParseException e) {
            return 0;
        }
        return date.getTime();
    }

    /* 获得当前的时间 */
    public static String getCurrentTime() {
        Date currentTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(currentTime);
        return dateString;
    }
    private final static long yearLevelValue = 365*24*60*60*1000 ;
    private final static long monthLevelValue = 30*24*60*60*1000 ;
    private final static long dayLevelValue = 24*60*60*1000 ;
    private final static long hourLevelValue = 60*60*1000 ;
    private final static long minuteLevelValue = 60*1000 ;
    private final static long secondLevelValue = 1000 ;
    private static String[] getDifference(long period) {//根据毫秒差计算时间差
        String[] result = new String[6];
        /*******计算出时间差中的年、月、日、天、时、分、秒*******/
//        int year = getYear(period) ;
//        int month = getMonth(period - year*yearLevelValue) ;
//        int day = getDay(period - year*yearLevelValue - month*monthLevelValue) ;
        int hour = getHour(period) ;
        int minute = getMinute(period - hour*hourLevelValue) ;
        int second = getSecond(period - hour*hourLevelValue - minute*minuteLevelValue) ;
        if(hour>0&&hour<10){
            result[0] = "0";
            result[1] = Integer.toString(hour);
        }else{
            result[0] = Integer.toString(hour/10);
            result[1] = Integer.toString(hour%10);
        }
        if(minute>0&&minute<10){
            result[2] = "0";
            result[3] = Integer.toString(minute);
        }else{
            result[2] = Integer.toString(minute/10);
            result[3] = Integer.toString(minute%10);
        }
        if(second>0&&second<10){
            result[4] = "0";
            result[5] = Integer.toString(second);
        }else{
            result[4] = Integer.toString(second);
            result[5] = Integer.toString(second);
        }
        return result;
    }
    public static int getYear(long period){
        return (int) (period/yearLevelValue);
    }
    public static int getMonth(long period){
        return (int) (period/monthLevelValue);
    }
    public static int getDay(long period){
        return (int) (period/dayLevelValue);
    }
    public static int getHour(long period){
        return (int) (period/hourLevelValue);
    }
    public static int getMinute(long period){
        return (int) (period/minuteLevelValue);
    }
    public static int getSecond(long period){
        return (int) (period/secondLevelValue);
    }


    /**
     * 时间格式化
     * @param str
     * @return
     */
    public static Date toDate(String str) {
        String pattern =null;
        SimpleDateFormat format = null;
        try {
            if (str.contains(".")) {
                if (str.contains(" ") && str.contains(":"))
                    pattern = "yyyy.MM.dd HH:mm:ss";
                else
                    pattern = "yyyy.MM.dd";
            } else if (str.contains("-")) {
                if (str.contains(" ") && str.contains(":"))
                    if(str.split(":").length>1){
                        pattern = "yyyy-MM-dd HH:mm";
                    }else
                        pattern = "yyyy-MM-dd HH:mm:ss";
                else{
                    pattern = "yyyy-MM-dd";
                }
            } else if (str.contains("/")) {
                if (str.contains(" ") && str.contains(":"))
                    pattern = "yyyy/MM/dd HH:mm:ss";
                else
                    pattern = "yyyy/MM/dd";
            } else if(str.contains(":")&&str.split(":").length>1){
                pattern = "yyyy年MM月dd日 HH:mm:ss";
            }else {
                long milliseconds = Long.parseLong(str);
                return new Date(milliseconds * 1000);
            }
            format = new SimpleDateFormat(pattern);
            return format.parse(str);
        } catch (Exception e) {
            return new Date();
        }
    }

    public static String formatDate(String str, String pattern) {
        String fromatStr=null;
        Date date= toDate(str);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        fromatStr=df.format(date);
        return fromatStr;
    }

    public static String formatDate(String str) {
        String pattern = "yyyy-MM-dd";
        String fromatStr=null;
        Date date= toDate(str);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        fromatStr=df.format(date);
        return fromatStr;
    }

  //只保留月和天，如2015-12-5--->12-5
    public static String formatDateToMonth(String str) {
        String pattern = "MM月dd日";
        String fromatStr=null;
        Date date= toDate(str);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        fromatStr=df.format(date);
        return fromatStr;
    }

    /**
     * 是否在当前时间后的多少天
     * @param dateChoose(选择返回用户选择的时间)
     * @param days
     * @return
     */
    public static boolean isInCurrentTime(Date dateChoose, int days){
        boolean flag=false;
        Calendar calendar = Calendar.getInstance();
        Date currDate=new Date(calendar.getTimeInMillis());
        calendar.setTime(currDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date currDate30after = new Date(calendar.getTimeInMillis());
        flag = dateChoose.before(currDate30after);
          return flag;
    }


    public static Date weeHours(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static String dateToString(Date date){
        String fromatStr=null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fromatStr=df.format(date);
        return fromatStr;
    }

    /**
     * 当前时间的第二天凌晨
     * @return
     */
    public static Date toNextDawn(){
         Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String nextDawn(){
        String fromatStr=null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        fromatStr=df.format(toNextDawn());
        return fromatStr;
    }

    public static String getFriendlyTimeDiff(Date time, long timeNow) {
        if (time == null)
            return "";
        long diff = timeNow - time.getTime();
        String str = "刚刚";
        if (diff > 0) {
            long s = diff / (60 * 1000);
            long h = s / 60;
            long d = h / 24;
            if (d > 0) {
                str = DateUtil.format(time, DateUtil.ISO_DATE_FORMAT);
            } else if (h > 0) {
                str = h + "小时前";
            } else if (s > 0) {
                str = s + "分钟前";
            } else {
                str = "刚刚";
            }
        }
        return str;
    }

    public static String getFriendlyDistanceToDate(Date date) {
        long distance = date.getTime() - new Date().getTime();
        String result = null;

        if(distance > 0) {
            long m = distance / (60 * 1000);
            long h = m / 60;
            long d = h / 24;

            if(d > 0) {
                result = d + "天";
            } else if (h > 0) {
                result = h + "小时";
            } else if(m > 0) {
                result = m + "分钟";
            } else {
                result = "0分钟";
            }
        }
        return result;
    }


    //重置DatePicker大小
    public static void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }

    public static List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0; i < viewGroup.getChildCount(); i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                } else if(child instanceof LinearLayout){
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    public static void resizeNumberPicker(NumberPicker np){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(75, WindowManager.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0,5, 0);
        np.setLayoutParams(params);
    }

}
