package com.yju.app.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.yju.app.app.SHApplication;


public class ToastUtils {

    public static void showToast(CharSequence text) {
        Toast(SHApplication.getInstance(), text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context ct,CharSequence text) {
        Toast(ct, text, Toast.LENGTH_SHORT);
    }

    public static void Toast(Context context, CharSequence text, int duration) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        TextView v = new TextView(context);
        v.setBackgroundColor(0x88000000);
        v.setTextColor(Color.WHITE);
        v.setText(text);
        v.setSingleLine(false);
        v.setPadding(20, 10, 20, 10);
        v.setGravity(Gravity.CENTER);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
