package com.yju.app.shihui.special.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yju.app.R;
import com.yju.app.base.BaseFragment;
import com.yju.app.shihui.detail.ProductDetailsActivity;
import com.yju.app.shihui.detail.adapter.ProductDetailPagerAdapter;
import com.yju.app.shihui.special.adapter.ValueSaleAdapter;
import com.yju.app.shihui.special.bean.ValueSaleEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValueSaleFragment extends BaseFragment implements  PullToRefreshListView.OnRefreshListener2,AdapterView.OnItemClickListener{

    @BindView(R.id.refreshListView)
    PullToRefreshListView refreshListView;

    private int type = 1;
    private ValueSaleAdapter valueSaleAdapter=null;
    private int page=0;

    public static ValueSaleFragment newInstance(int type) {
        ValueSaleFragment fragment = new ValueSaleFragment();
        fragment.setType(type);
        return fragment;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_value_sale_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        valueSaleAdapter=new ValueSaleAdapter(getActivity());
        refreshListView.setAdapter(valueSaleAdapter);
        refreshListView.setOnRefreshListener(this);
        refreshListView.setOnItemClickListener(this);
        refreshListView.setScrollingWhileRefreshingEnabled(true);
    }

    private void initData() {
        List<ValueSaleEntity> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            ValueSaleEntity entity=new ValueSaleEntity();
            entity.endtime=10000;
            entity.name="澳洲进口牛奶德运牛奶1L";
            entity.discount= (int) (Math.random() * 10);
            entity.originPrice=50;

            list.add(entity);
        }
        if (refreshListView.isRefreshing())
        refreshListView.onRefreshComplete();
        valueSaleAdapter.addList(list);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //下拉
        page++;
        initData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //上拉
        page=0;
        initData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ProductDetailsActivity.open(getActivity());
    }
}
