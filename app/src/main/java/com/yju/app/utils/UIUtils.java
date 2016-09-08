package com.yju.app.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

public class UIUtils {

    public static <T extends View> T getView(View rootView, int id) {
        return (T) rootView.findViewById(id);
    }

    public static <T extends Object> T getT(Object obj) {
        return (T) obj;
    }


    /**
     * 获取屏幕宽度的像素
     */
    public static int getScreenWidth(Context context) {
        if (context == null){
            return 0;
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度像素
     */
    public static int getScreenHeight(Context context) {
        if (context == null){
            return 0;
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int dp2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

   //listview动态设置显示高度
    public static void setListViewHeight(ListView listView) {
        Adapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int viewCount = listAdapter.getCount();
        for (int i = 0; i < viewCount; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount()-1));
        listView.setLayoutParams(params);
    }

    public static int getListViewHeight(ListView listView) {
        Adapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        int viewCount = listAdapter.getCount();
        for (int i = 0; i < viewCount; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        totalHeight = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount()-1));
        return totalHeight;
    }


    public static Drawable getCompoundDrawable(@NonNull Context context, @DrawableRes int resId) {
        return getCompoundDrawable(context, resId, 1);
    }

    public static Drawable getCompoundDrawable(@NonNull Context context, @DrawableRes int resId, float scale) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, (int) (drawable.getMinimumWidth() * scale), (int) (drawable.getMinimumHeight() * scale));
        return drawable;
    }

    public static void setBackground(View view, Drawable drawable) {
        int left = view.getPaddingLeft();
        int right = view.getPaddingRight();
        int top = view.getPaddingTop();
        int bottom = view.getPaddingBottom();
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        view.setPadding(left, top, right, bottom);
    }

    //设置中划线
    public static void setTextFlag(TextView textView,boolean midFlag) {
        textView.getPaint().setAntiAlias(true);//抗锯齿
        if (midFlag)
        textView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        else
        textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }

   //获取某个控件在屏幕X的位置
    public static int getPositionOnScreenX(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return x;
    }
    //获取某个控件在屏幕Y的位置
    public static int getPositionOnScreenY(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return y;
    }

}
