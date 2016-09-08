package com.yju.app.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String timeFormat(long timeMillis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(new Date(timeMillis));
    }


     static String leftTime = null;
    public static void setLeftTime(long endtime, final TextView textView) {
        if (endtime <= 0) return ;
        Date date = convertTimeToDate(endtime+ "");
        long timeDiff = (date.getTime() - getExactlyCurrentTime());
        if (timeDiff>0){
            CountDownTimer mCountDownTimer = new CountDownTimer(timeDiff, 1000) {
                @Override
                public void onTick(long l) {
                    leftTime= formatTimeLeft(l);
                    textView.setText("剩余 "+leftTime);
                }

                @Override
                public void onFinish() {
                    leftTime="活动已结束";
                }
            };
            mCountDownTimer.start();
        }

    }

    //单位秒
    public static String formatTimeLeft(long ms) {
        long day = ms / (1000 * 60 * 60 * 24);
        long hour = (ms % (1000 * 60 * 60 * 24) + day * 24 * 60 * 60 * 1000) / (1000 * 60 * 60);
        long min = (ms % (1000 * 60 * 60)) / (1000 * 60);
        long sec = (ms % (1000 * 60)) / 1000;
        if (day > 0) {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (hour > 0) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (min > 0) {
            return min + "分钟" + sec + "秒";
        }
        return sec + "秒";
    }

    public static String convertTimeLeft(long ms) {
        long day = ms / (1000 * 60 * 60 * 24);
        long hours = (ms % (1000 * 60 * 60 * 24) + day * 24 * 60 * 60 * 1000) / (1000 * 60 * 60);
        long minutes = (ms % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (ms % (1000 * 60)) / 1000;
        return hours + "时" + minutes + "分"
                + seconds+"秒";
    }

    public static Date convertTimeToDate(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        return date;
    }

    public static long delta = -1;
    public static long getExactlyCurrentTime() {
        return System.currentTimeMillis() - delta;
    }
}
