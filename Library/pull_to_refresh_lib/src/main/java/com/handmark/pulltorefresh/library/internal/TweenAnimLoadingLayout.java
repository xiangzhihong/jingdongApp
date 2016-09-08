package com.handmark.pulltorefresh.library.internal;

import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

/**
 * @date 2015/1/8
 * @author wuwenjie
 * @desc 帧动画加载布局
 */
public class TweenAnimLoadingLayout extends LoadingLayout {

	private AnimationDrawable animationDrawable;
	int duration = 0;
	protected Handler handler = new Handler();

	public TweenAnimLoadingLayout(Context context, Mode mode,
			Orientation scrollDirection, TypedArray attrs,AnimationStyle animationStyle) {
		super(context, mode, scrollDirection, attrs,animationStyle);
		// 初始化
		
	}

	// 默认图片
	@Override
	protected int getDefaultDrawableResId() {
		return R.drawable.head_loading;
	}

	@Override
	protected void onLoadingDrawableSet(Drawable imageDrawable) {
		// NO-OP
	}

	@Override
	protected void onPullImpl(float scaleOfLayout) {
		// NO-OP
		mHeaderImage.setImageResource(R.drawable.head_loading);
	}

	// 下拉以刷新
	@Override
	protected void pullToRefreshImpl() {
		// NO-OP
	}

	// 正在刷新时回调
	@Override
	protected void refreshingImpl() {
		// 播放帧动画
		mHeaderImage.setImageResource(R.drawable.start_animation);

		animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
		mHeaderImage.setVisibility(View.VISIBLE);  
		animationDrawable.start();
		duration = 0;
		for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
			duration += animationDrawable.getDuration(i);
		}
		handler.postDelayed(new Runnable() {
			public void run() {
				circulaAnimation();
			}
		}, duration);
	}

	// 释放以刷新
	@Override
	protected void releaseToRefreshImpl() {
		// NO-OP
	}
	// 重新设置
	@Override
	protected void resetImpl() {
		mHeaderImage.setVisibility(View.VISIBLE);
		mHeaderImage.clearAnimation();
		mHeaderImage.setImageResource(R.drawable.head_loading3);
	}

	private void circulaAnimation() {
		mHeaderImage.setImageResource(R.drawable.circulation_animation);
		animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
		animationDrawable.start();
	}

}