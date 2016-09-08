package com.yju.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by：user on 2016/7/4 11:48
 */
public class Utils {


    public static int getLocationOnScreenX(View view){
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return x;
    }

    public static int getLocationOnScreenY(View view){
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return y;
    }

    public static float getTextWidth(String tv){
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        float textWidth = mTextPaint.measureText(tv);
        return textWidth;
    }

    // 隐藏键盘
    public static void hideInput(Activity context) {
        if (context!= null && context.getCurrentFocus() != null
                && context.getCurrentFocus().getWindowToken() != null) {
            ((InputMethodManager) context.getSystemService(
                    Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(context.getCurrentFocus()
                            .getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    // 显示键盘
    public static void showInput(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager)context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
    // 隐藏键盘
    public static void hideInput(Context context,View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // 显示键盘
    public static void showInput(Context context,View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }


    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //textview做图片
    public static void setDrawableLeft(TextView textView, Drawable drawable) {
        Drawable mdrawable = drawable;
        mdrawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     */
    public static int getCharLength(String s) {
        if (TextUtils.isEmpty(s))
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }
    /**
     * 每三位加一个逗号，常用于金额,去掉￥字符,适合处理各种数字金额，如213.11,213,213.0
     * @param str
     * @return
     */
    private static String spiltMoney(String str) {
        String dotStr = null;
        //如果含有小数两位
        int dot=str.indexOf(".");
        if(dot!=-1){
            dotStr=str.substring(dot);
            str=str.substring(0,dot);
        }
        int l=str.indexOf("￥");
        str=str.substring(l+1,str.length());
        String reverseStr = new StringBuilder(str).reverse().toString();
        String strTemp = "";
        for (int i=0; i<reverseStr.length(); i++) {
            if (i*3+3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i*3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i*3, i*3+3)+",";
        }
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length()-1);
        }
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        resultStr="￥"+resultStr;
        if(dot!=-1){
            resultStr=resultStr+dotStr;
        }
        return resultStr;
    }

}
