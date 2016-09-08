package com.yju.app.shihui.special;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;
import com.yju.app.shihui.main.UIFrameHelper;
import com.yju.app.shihui.main.UIPager;
import com.yju.app.shihui.special.fragment.ValueSaleFragment;
import com.yju.app.shihui.special.view.MorePopupWindow;
import com.yju.app.widght.tabbar.TabBar;
import com.yju.app.widght.tabbar.bean.TabEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/8/29.
 */
public class ValueSaleListActivity extends BaseActivity {

    @BindView(R.id.tab_bar)
    TabBar tabBar;
    @BindView(R.id.title_more)
    ImageView titleMore;

    private MorePopupWindow popupWindow = null;
    private  ArrayList<String> popList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_sale_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTab();
    }

    private void initTab() {
        List<TabEntity> tabs = new ArrayList<>();
        tabs.add(new TabEntity(1, "实惠精选"));
        tabs.add(new TabEntity(2, "清仓甩卖"));
        tabBar.addTabViews(tabs);
        initTab(1);
        tabBar.setOnClickTabListener(new TabBar.OnClickTabListener() {
            @Override
            public void onClickTab(int type) {
                if (type == 1) {
                    initTab(1);
                } else if (type == 2) {
                    initTab(2);
                }
            }
        });
    }

    private void initTab(int type) {
        ValueSaleFragment fragment = ValueSaleFragment.newInstance(type);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .commit();
    }

    public static void open(Context context) {
        Intent in = new Intent(context, ValueSaleListActivity.class);
        context.startActivity(in);

    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            popupWindow.dismiss();
           jumpPop(position);
        }
    };

    private void jumpPop(int position) {
       switch (position){
           case 0:
               UIFrameHelper.openUIPager(this, UIPager.WELFARE);
               break;
           case 1:
               UIFrameHelper.openUIPager(this, UIPager.SERVICE);
               break;
           case 2:
               UIFrameHelper.openUIPager(this, UIPager.CITY);
               break;
           case 3:
               UIFrameHelper.openUIPager(this, UIPager.CART);
               break;

       }
    }

    @OnClick(R.id.back)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.title_more)
    public void moreClick() {
        popList = new ArrayList<String>();
        popList.add("福利");
        popList.add("服务社");
        popList.add("购物车");
        popList.add("我的");
        popupWindow = new MorePopupWindow(this, onItemClickListener);
        popupWindow.setList(popList);

        popupWindow.show(titleMore,20);
    }
}
