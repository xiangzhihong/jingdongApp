package com.yju.app.shihui.main;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.shihui.main.fragment.CityWideFragment;
import com.yju.app.shihui.main.fragment.MineFragment;
import com.yju.app.shihui.main.fragment.ServiceCenterFragment;
import com.yju.app.shihui.main.fragment.ShopCartFragment;
import com.yju.app.shihui.main.fragment.WelfareFragment;
import com.yju.app.utils.UIUtils;

public enum UIPager {

    WELFARE(0, "福利", WelfareFragment.class, R.drawable.tab_welfare_selector),
    SERVICE(1, "福利社", ServiceCenterFragment.class, R.drawable.tab_servicecenter_selector),
    CITY(2, "同城", CityWideFragment.class, R.drawable.tab_citywide_selector),
    CART(3, "购物车", ShopCartFragment.class, R.drawable.tab_shopcart_selector),
    MINE(4, "我的", MineFragment.class, R.drawable.tab_mine_selector);

    private int pagerIndex = 0;
    private int subPagerIndex = 0;
    private String label = null;
    private Class pager = null;
    private int icon = -1;

    UIPager(int pagerIndex, String label, Class pager, int icon) {
        this.pagerIndex = pagerIndex;
        this.label = label;
        this.pager = pager;
        this.icon = icon;
    }

    public int getPagerIndex() {
        return pagerIndex;
    }

    public String getLabel() {
        return label;
    }

    public Class getPager() {
        return pager;
    }

    public int getPagerIcon() {
        return icon;
    }

    public View getTabView(Activity context) {
        View tabView = context.getLayoutInflater().inflate(R.layout.layout_tab_item, null);
        TextView labelTxt = (TextView) tabView.findViewById(R.id.label);
        labelTxt.setText(getLabel());
        labelTxt.setCompoundDrawables(null, UIUtils.getCompoundDrawable(context, getPagerIcon()), null, null);
        return tabView;
    }

    public int getSubPagerIndex() {
        return subPagerIndex;
    }

    public void setSubPagerIndex(int subPagerIndex) {
        this.subPagerIndex = subPagerIndex;
    }

    public static UIPager getType(int key) {
        for (UIPager value : values()) {
            if (key == value.pagerIndex) {
                return value;
            }
        }
        return WELFARE;
    }
    public static UIPager getType(String name) {
        for (UIPager value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return WELFARE;
    }
}
