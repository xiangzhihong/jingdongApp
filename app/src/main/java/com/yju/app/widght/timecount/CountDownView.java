package com.yju.app.widght.timecount;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 有天倒计时的View
 */
public class CountDownView extends LinearLayout {

    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;

    private Context context;
    private long mDay;
    private long mHour;
    private long mMinute;
    private long mSecond;

    private Timer timer = null;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            countDown();
        }
    };

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_countdown_layout, this);
        ButterKnife.bind(view);
    }


    public void setTime(long leftTime) {
        long time = leftTime / 1000;
        long day = time/(3600*24);
        long hours = (time-day*3600*24)/ 3600;
        long minutes = (time - day*3600*24-hours * 3600) / 60;
        long seconds = time - day*3600*24-hours * 3600 - minutes * 60;

        setTime(day, hours, minutes, seconds);
    }

    public void setTime(long day, long hour, long min, long sec) {
        if (hour >= 60 || min >= 60 || sec >= 60 || hour < 0 || min < 0
                || sec < 0) {
            throw new RuntimeException("Time format is error");
        }
        mDay = day;
        mHour = hour;
        mMinute = min;
        mSecond = sec;

        setTextTime();
    }

    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    //保证天，时，分，秒都两位显示，不足的补0
    private void setTextTime() {
        if (mDay<10){
            tvDay.setText("0"+mDay);
        }else {
            tvDay.setText(mDay+"");
        }

        if (mHour<10){
            tvHour.setText("0"+mHour);
        }else {
            tvHour.setText(mHour + "");
        }

        if (mMinute<10){
            tvMinute.setText("0"+mMinute );
        }else {
            tvMinute.setText(mMinute + "");
        }

        if (mSecond<10){
            tvSecond.setText("0"+mSecond );
        }else {
            tvSecond.setText(mSecond + "");
        }
    }

    private void countDown() {
        if (isCarry4Unit(tvSecond)) {
                if (isCarry4Unit(tvMinute)) {
                        if (isCarry4Unit(tvHour)) {
                            if (isCarry4Unit(tvDay)) {
                                ToastUtils.showToast("倒计时结束了");
                                stop();
                            }
                }
            }
        }
    }

    private boolean isCarry4Unit(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 59;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }
    }
}
