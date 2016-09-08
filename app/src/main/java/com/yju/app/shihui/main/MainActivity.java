package com.yju.app.shihui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;
import com.yju.app.base.manager.ActivityManager;
import com.yju.app.utils.Constant;
import com.yju.app.widght.CountView;
import com.yju.app.widght.FragmentTabHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private long lastBackTime = 0;
    private Intent intent = null;
    private CountView  cartTabCount = null,mineTabCount=null;
    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        intent = getIntent();
        initView();
        initParams();
        initCount();
    }

    private void initView() {
        tabhost.setup(this, getSupportFragmentManager(), R.id.frameLayout);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        }
        tabhost.setOnTabChangedListener(this);
        for (UIPager pager : UIPager.values()) {
            View tabView = pager.getTabView(this);
            initTabCountView(pager.name(), (CountView) tabView.findViewById(R.id.count));
            tabhost.addTab(tabhost.newTabSpec(pager.name()).setIndicator(tabView), pager.getPager(), null);
        }
    }

    private void initParams() {
        if (intent == null) return;
        int pagerIndex = intent.getIntExtra(Constant.CURRENT_FRAME_PAGER, -1);
        if (pagerIndex != -1) {
            tabhost.setCurrentTab(pagerIndex);
        }
    }

    private void initTabCountView(String tag, CountView countView) {
        if (UIPager.CART.name().equals(tag)) {
            cartTabCount = countView;
        } else if (UIPager.MINE.name().equals(tag)) {
            mineTabCount = countView;
        }
    }

    private void initCount() {
        int msgCount = 2;
        if (cartTabCount != null) {
            if (msgCount > 0) {
                cartTabCount.setCount(msgCount);
            } else {
                cartTabCount.setVisibility(View.GONE);
            }
        }

        int mineCount = 20;
        if (mineTabCount != null) {
            if (mineCount>0) {
                mineTabCount.setCount("...");
            } else {
                mineTabCount.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        currentFragment = getSupportFragmentManager().findFragmentByTag(tabId);
        UIPager type = UIPager.getType(tabId);
//        if (type == UIPager.MSG) {
//
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (currentFragment != null) {
            currentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        long currentBackTime = System.currentTimeMillis();
        if (currentBackTime - lastBackTime > 2000) {
            Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            lastBackTime = currentBackTime;
        } else {
            super.onBackPressed();
            ActivityManager.popAllActivity();
            System.exit(0);
        }
    }
}
