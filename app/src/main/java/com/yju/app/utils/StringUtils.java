package com.yju.app.utils;

import android.text.TextUtils;

/**
 * Created by user on 2016/8/22.
 */
public class StringUtils {
    public static String addRedHTML(String str) {
        String htmlStr = "<font color=\"#cc3333\">" + str + "</font>";
        return htmlStr;
    }

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
}
