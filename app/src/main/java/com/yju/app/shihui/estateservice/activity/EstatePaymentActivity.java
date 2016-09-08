package com.yju.app.shihui.estateservice.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.base.BaseActivity;
import com.yju.app.shihui.estateservice.adapter.EstateFeeAdapter;
import com.yju.app.shihui.estateservice.adapter.EstatePayExpandableAdapter;
import com.yju.app.shihui.estateservice.entity.EstatePayEntity;
import com.yju.app.utils.FileUtils;
import com.yju.app.utils.JsonUtils;
import com.yju.app.utils.ToastUtils;
import com.yju.app.widght.MeasureExpandableListView;
import com.yju.app.widght.MeasureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/9/2.
 * 物业费账单
 */
public class EstatePaymentActivity extends BaseActivity {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_next)
    TextView titleNext;
    @BindView(R.id.estate_listView)
    MeasureExpandableListView estateListView;
    @BindView(R.id.estate_address_view)
    LinearLayout estateAddressView;

    private EstatePayExpandableAdapter adapter = null;
    private List<EstatePayEntity.DataBean> estateList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estate_payment);
        ButterKnife.bind(this);
        init();
    }


    @OnClick(R.id.back)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.title_next)
    public void moreClick() {
        ToastUtils.showToast("缴费记录");
    }

    private void init() {
//        initFouse();
        initTitle();
        initData();
        initView();
    }

    private void initFouse() {
        estateAddressView.setFocusable(true);
        estateAddressView.setFocusableInTouchMode(true);
        estateAddressView.requestFocus();
    }

    private void initTitle() {
        titleName.setText("物业费账单");
        titleNext.setVisibility(View.VISIBLE);
        titleNext.setText("缴费记录");
    }

    private void initData() {
        String estate = FileUtils.readAssert(this, "estate.json");
        EstatePayEntity entity = JsonUtils.parseJson(estate, EstatePayEntity.class);
        if (entity != null && entity.result != null && entity.result.advance != null && entity.result.advance.data != null)
            estateList = entity.result.advance.data;
    }

    private void initView() {
        adapter = new EstatePayExpandableAdapter(this, estateList);
        estateListView.setAdapter(adapter);
        for (int i=0;i<estateList.size();i++){
            estateListView.expandGroup(i);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, EstatePaymentActivity.class);
        context.startActivity(intent);
    }


    @OnClick(R.id.estate_address_view)
    public void addressClick() {
        EstateAddressActivity.open(this);
    }
}
