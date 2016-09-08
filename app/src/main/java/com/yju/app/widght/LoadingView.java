package com.yju.app.widght;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yju.app.R;


public class LoadingView extends RelativeLayout {

    private LayoutInflater inflater;
    private View rlLoading;
    private ImageView ivLoading;
    private AnimationDrawable animationDrawable;
    private boolean isShow;

    public LoadingView(Context context) {
        this(context, null, 0);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        rlLoading = inflater.inflate(R.layout.loading_layout, this, true);
        ivLoading = (ImageView) rlLoading.findViewById(R.id.ivProgress);
//        showLoading();
        startAnimation();
    }

    public void showLoading() {
        if (!isShow) {
            isShow = true;
            startAnimation();
            rlLoading.setVisibility(View.VISIBLE);
        }
    }

    private void startAnimation() {
        ivLoading.setImageResource(R.drawable.anim_loading);
        animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        animationDrawable.start();
    }

    public void hideLoading() {
        animationDrawable.stop();
        rlLoading.setVisibility(View.GONE);
        isShow = false;
    }

    public boolean isShowing() {
        return isShow;
    }
}
