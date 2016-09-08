package com.yju.app.shihui.estateservice.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;

/**
 * Created by user on 2016/9/5.
 * 创建缴费地址
 */
public class EstateCreateAddressActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estate_create_address);
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, EstateCreateAddressActivity.class);
        context.startActivity(intent);
    }
}
