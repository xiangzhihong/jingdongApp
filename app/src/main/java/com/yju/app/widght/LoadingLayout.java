package com.yju.app.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yju.app.R;

/**
 * 默认的加载view(底图)
 */
public class LoadingLayout extends FrameLayout  {
    private View loadingPage = null;
    private View emptyPage = null;
    private View failedPage = null;
    private ImageView loadingImage=null;

    private TextView failedText = null;
    private TextView emptyText = null;
    private OnClickListener onRetryClickListener = null;

    private Context context = null;
    private AnimationDrawable animationDrawable=null;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        context = getContext();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingLayout, defStyle, 0);
        int loadingPagerId = typedArray.getResourceId(R.styleable.LoadingLayout_loading_pager_layout, R.layout.layout_default_loading);
        loadingPage = inflate(context, loadingPagerId, null);
        loadingImage=getView(loadingPage,R.id.ivProgress);


        int failedPagerId = typedArray.getResourceId(R.styleable.LoadingLayout_failed_pager_layout, R.layout.layout_default_loading);
        failedPage = inflate(context, failedPagerId, null);
        int emptyPagerId = typedArray.getResourceId(R.styleable.LoadingLayout_empty_pager_layout, R.layout.layout_default_empty);
        emptyPage = inflate(context, emptyPagerId, null);

        int emptyTextId = typedArray.getResourceId(R.styleable.LoadingLayout_empty_text_id, R.id.empty_tv);
        emptyText = getView(emptyPage, emptyTextId);
        int failedTextId = typedArray.getResourceId(R.styleable.LoadingLayout_failed_text_id, R.id.empty_tv);
        failedText = getView(failedPage, failedTextId);

        //重试这里关闭，以后再加上
//        setReTry();
        typedArray.recycle();
    }

    private void startAnimation() {
        loadingImage.setImageResource(R.drawable.anim_loading);
        animationDrawable = (AnimationDrawable) loadingImage.getDrawable();
        animationDrawable.start();
    }

    public void hideLoading() {
        animationDrawable.stop();
        loadingImage.setVisibility(View.GONE);
    }

    private void setReTry() {
        if(emptyPage != null && onRetryClickListener != null){
            emptyPage.setOnClickListener(onRetryClickListener);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void start() {
        loadingPage.setVisibility(View.VISIBLE);
        loadingPage.bringToFront();
        failedPage.setVisibility(View.GONE);
        emptyPage.setVisibility(View.GONE);
    }

    public void showContentView() {
        hideLoading();
        loadingPage.setVisibility(View.GONE);
        failedPage.setVisibility(View.GONE);
        emptyPage.setVisibility(View.GONE);
    }

    public void showLoadingView() {
        startAnimation();
        loadingPage.setVisibility(View.VISIBLE);
        loadingPage.bringToFront();
        failedPage.setVisibility(View.GONE);
        emptyPage.setVisibility(View.GONE);
    }

    public void showEmptyView() {
        hideLoading();
        emptyPage.setVisibility(View.VISIBLE);
        emptyPage.bringToFront();
        failedPage.setVisibility(View.GONE);
        loadingPage.setVisibility(View.GONE);
    }

    public void showFailedView(String failedMsg) {
        hideLoading();
        failedPage.setVisibility(View.VISIBLE);
        failedPage.bringToFront();
        emptyPage.setVisibility(View.GONE);
        loadingPage.setVisibility(View.GONE);
        if (failedText != null && !TextUtils.isEmpty(failedMsg)) {
            failedText.setText(failedMsg);
        }
    }

    public void setEmptyText(String text) {
        if (emptyText != null && text != null) {
            emptyText.setText(text);
        }
    }

    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    public <T extends View> T getView(View rootView, int id) {
        return (T) rootView.findViewById(id);
    }

}
