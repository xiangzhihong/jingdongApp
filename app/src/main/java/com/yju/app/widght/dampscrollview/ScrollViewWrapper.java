package com.yju.app.widght.dampscrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.yju.app.R;
import com.yju.app.widght.dampscrollview.util.OnScrollViewChangeListener;
import com.yju.app.widght.dampscrollview.util.Utils;


public class ScrollViewWrapper extends ScrollView {

    private int heitht;
    private PageOneScrollView pageOne;
    private View pageTwo;
    private boolean isSetted = false;
    private boolean ispageOne = true;
    private OnScrollViewChangeListener listener=null;

    public ScrollViewWrapper(Context context) {
        super(context);
        init(context, null, 0);

    }

    public ScrollViewWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public ScrollViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollViewWrapper);
        int padding = a.getInteger(R.styleable.ScrollViewWrapper_wrapper_padding, 0);
        heitht = Utils.getScreenHeight(context) - Utils.getStatusBarHeight(context) - Utils.dp2px(context, padding);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isSetted) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);

            pageOne = (PageOneScrollView) wrapper.getChildAt(0);
            pageTwo = wrapper.getChildAt(1);

            pageOne.getLayoutParams().height = heitht;
            pageTwo.getLayoutParams().height = heitht;
            isSetted = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                //滑动距离,然后才翻页
                int creteria = heitht / 5;
                if (ispageOne) {
                    if (scrollY <= creteria) {
                        //返回PageOne
                        this.smoothScrollTo(0, 0);
                        if (listener!=null){
                            listener.change(0);
                        }
                    } else {
                        //显示PageTwo
                        this.smoothScrollTo(0, heitht);
                        this.setFocusable(false);
                        ispageOne = false;
                        //返回PageTwo
                        if (listener!=null){
                            listener.change(1);
                        }
                    }
                } else {
                    int pading = heitht - scrollY;
                    if (pading >= creteria) {
                        this.smoothScrollTo(0, 0);
                        ispageOne = true;
                        if (listener!=null){
                            listener.change(0);
                        }
                    } else {
                        this.smoothScrollTo(0, heitht);
                        //返回PageOne
                        if (listener!=null){
                            listener.change(1);
                        }
                    }
                }

                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnScrollChangeListener(OnScrollViewChangeListener listener) {
        this.listener = listener;
    }

    public void closeMenu() {
        if (ispageOne) return;
        this.smoothScrollTo(0, 0);
        ispageOne = true;
    }

    public void openMenu() {
        if (!ispageOne) return;
        this.smoothScrollTo(0, heitht);
        ispageOne = false;
    }

}
