package com.yju.app.widght.dampscrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.yju.app.R;
import com.yju.app.widght.dampscrollview.util.Utils;


public class PageOneScrollView extends ScrollView {
    private int height;
    public float oldY;
    private int t;
    private float oldX;

    public PageOneScrollView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PageOneScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public PageOneScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Damp);
        int padding = a.getInteger(R.styleable.Damp_pageone_padding, 0);
        height = Utils.getScreenHeight(context) - Utils.getStatusBarHeight(context) - Utils.dp2px(context, padding);
        a.recycle();
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float Y = ev.getY();
                float gap = Y - oldY;
                float X = ev.getX();
                float gapHorizontal = X - oldX;

                if (Math.abs(gapHorizontal) > 120) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }


                //得到scrollview里面空间的高度
                int childHeight = this.getChildAt(0).getMeasuredHeight();
                //子控件高度减去scrollview向上滑动的距离
                int padding = childHeight - t;
                //gap<0表示手指正在向上滑动，padding==mScreenHeight表示本scrollview已经滑动到了底部
                if (gap < 0 && padding == height) {
                    //让顶级的scrollview重新获得滑动事件
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                oldY = ev.getY();
                oldX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }


}
