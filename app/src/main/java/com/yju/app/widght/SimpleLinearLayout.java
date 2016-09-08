package com.yju.app.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleLinearLayout extends LinearLayout {

    protected Context mContext;
    protected View contentView;
    protected AtomicBoolean isPreparingData;

    public SimpleLinearLayout(Context context) {
        super(context);
        this.mContext = context;
        isPreparingData = new AtomicBoolean(false);
        initViews();
    }

    public SimpleLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        isPreparingData = new AtomicBoolean(false);
        initViews();
    }

    protected void initViews() {

    }

}
