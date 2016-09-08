package com.yju.app.shihui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;
import com.yju.app.shihui.main.MainActivity;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this, this);
        postDelay();
    }

    private void postDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 3000);

    }


}
