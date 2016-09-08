package com.yju.app.shihui.main;

import android.content.Context;
import android.content.Intent;

import com.yju.app.utils.Constant;

/**
 * Created by user on 2016/8/29.
 */
public class UIFrameHelper {

    public static void openUIPager(Context context, UIPager pager) {
        if (pager != null) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(Constant.CURRENT_FRAME_PAGER, pager.getPagerIndex());
            context.startActivity(intent);
        } else {
            openUIPager(context);
        }
    }

    public static void openUIPager(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
