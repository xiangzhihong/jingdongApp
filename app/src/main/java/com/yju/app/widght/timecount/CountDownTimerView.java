/*
 * Copyright (c) 2014, �ൺ˾ͨ�Ƽ����޹�˾ All rights reserved.
 * File Name��RushBuyCountDownTimerView.java
 * Version��V1.0
 * Author��zhaokaiqiang
 * Date��2014-9-26
 */
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


public class CountDownTimerView extends LinearLayout {

    @BindView(R.id.hour_decade)
    TextView hourDecade;
    @BindView(R.id.hour_unit)
    TextView hourUnit;
    @BindView(R.id.min_decade)
    TextView minDecade;
    @BindView(R.id.min_unit)
    TextView minUnit;
    @BindView(R.id.sec_decade)
    TextView secDecade;
    @BindView(R.id.sec_unit)
    TextView secUnit;


    private Context context;

    private long hDecade;
    private long hUnit;
    private long mDecade;
    private long mUnit;
    private long sDecade;
    private long sUnit;

    private Timer timer;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            countDown();
        }
    };

    public CountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_countdown_timer, this);
        ButterKnife.bind(view);
    }

    private long testTime = (10 * 3600 + 18 * 60 + 53) * 1000;
    public void setTime() {
        long time=testTime/1000;
        long hours= time / ( 60 * 60 );
        long minutes=(time-hours*3600)/60;
        long seconds=time-hours*3600-minutes*60;

        setTime(hours,minutes,seconds);
    }

    public void setTime(long hour, long min, long sec) {
        if (hour >= 60 || min >= 60 || sec >= 60 || hour < 0 || min < 0
                || sec < 0) {
            throw new RuntimeException("Time format is error");
        }

        hDecade = hour / 10;
        hUnit = hour - hDecade * 10;

        mDecade = min / 10;
        mUnit = min - mDecade * 10;

        sDecade = sec / 10;
        sUnit = sec - sDecade * 10;

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

    private void setTextTime() {
        hourDecade.setText(hDecade + "");
        hourUnit.setText(hUnit + "");
        minDecade.setText(mDecade + "");
        minUnit.setText(mUnit + "");
        secDecade.setText(sDecade + "");
        secUnit.setText(sUnit + "");
    }

    private void countDown() {
        if (isCarry4Unit(secUnit)) {
            if (isCarry4Decade(secDecade)) {
                if (isCarry4Unit(minUnit)) {
                    if (isCarry4Decade(minDecade)) {
                        if (isCarry4Unit(hourUnit)) {
                            if (isCarry4Decade(hourDecade)) {
                                ToastUtils.showToast("倒计时结束了");
                                stop();
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isCarry4Decade(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 5;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    private boolean isCarry4Unit(TextView tv) {
        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 9;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }
}
