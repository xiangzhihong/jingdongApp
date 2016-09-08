package com.yju.app.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.yju.app.widght.CustomerAlertDialog;

/**
 * Created by user on 2016/9/2.
 */
public class UIHelper {

    private static CustomerAlertDialog dialog=null;
    public static void callServiceDialog(final Context context, final String phoneNum) {
        dialog = new CustomerAlertDialog(context);
        dialog.setTitle("呼叫");
        dialog.setContent(phoneNum);
        dialog.setOnClickListener(new CustomerAlertDialog.OnClickButtonListener() {
            @Override
            public void onClick(View v, CustomerAlertDialog.ClickType type) {
                if (type == CustomerAlertDialog.ClickType.CONFIRM) {
                    Intent in = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNum));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    context.startActivity(in);
                }
            }
        });
        dialog.show();
    }
}
