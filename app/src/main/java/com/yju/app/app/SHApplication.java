package com.yju.app.app;


import android.support.multidex.MultiDexApplication;

/**
 * Created by user on 2016/8/22.
 */
public class SHApplication extends MultiDexApplication {

    private static SHApplication mInstance = null;

    public static synchronized SHApplication getInstance() {
        if (mInstance == null) {
            mInstance = new SHApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {

    }
}
