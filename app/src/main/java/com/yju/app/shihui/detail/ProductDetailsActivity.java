package com.yju.app.shihui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;
import com.yju.app.shihui.detail.adapter.ProductDetailPagerAdapter;
import com.yju.app.shihui.detail.fragment.ProductDetailFragment;
import com.yju.app.shihui.detail.fragment.ProductFragment;
import com.yju.app.shihui.special.view.MorePopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseActivity implements ProductFragment.OnScrollTabChangeListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar_more)
    ImageView toolbarMore;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private List<Fragment> mFragments;
    private String[] titles = new String[]{"商品", "详情"};
    private ProductDetailPagerAdapter productPagerAdapter = null;
    private MorePopupWindow popupWindow = null;
    public final static String tag = "ProductDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initViewPager();
    }


    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new ProductFragment());
        mFragments.add(new ProductDetailFragment());

        productPagerAdapter = new ProductDetailPagerAdapter(getSupportFragmentManager(), mFragments, Arrays.asList(titles));
        viewPager.setOffscreenPageLimit(productPagerAdapter.getCount());
        viewPager.setAdapter(productPagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.back)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.toolbar_more)
    public void moreClick() {
        List list = new ArrayList<String>();
        list.add("消息");
        list.add("服务社");
        list.add("购物车");
        popupWindow = new MorePopupWindow(this, onItemClickListener);
        popupWindow.setList(list);

        popupWindow.show(toolbarMore, 20);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            popupWindow.dismiss();
        }
    };


    public static void open(Context context) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void currentTab(int tabId) {
        viewPager.setCurrentItem(tabId);
    }
}
