package com.yju.app.shihui.welfare.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.yju.app.R;
import com.yju.app.shihui.detail.ProductDetailsActivity;
import com.yju.app.shihui.welfare.bean.SpeedHourEntity;
import com.yju.app.shihui.welfare.view.adapter.SpeedHourAdapter;
import com.yju.app.utils.FileUtils;
import com.yju.app.utils.JsonUtils;
import com.yju.app.utils.ToastUtils;
import com.yju.app.widght.SimpleLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 时速达
 */
public class SpeedHourView extends SimpleLinearLayout {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SpeedHourAdapter speedHourAdapter=null;
    private SpeedHourEntity entity=null;

    public SpeedHourView(Context context) {
        this(context, null);
    }

    public SpeedHourView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        contentView = inflate(mContext, R.layout.layout_speed_per_hour, this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initData();
        initView();
        initAdapter();
    }

    private void initData() {
        String data = FileUtils.readAssert(mContext, "speenhour.txt");
        entity = JsonUtils.parseJson(data, SpeedHourEntity.class);
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initAdapter() {
        speedHourAdapter=new SpeedHourAdapter(mContext);
        recyclerView.setAdapter(speedHourAdapter);
        if (entity!=null&&entity.topic!=null&&entity.topic.items!=null&&entity.topic.items.size()>0){
            List<SpeedHourEntity.TopicBean.ItemsBean.ListBean> listBeen=entity.topic.items.get(0).list;
             if (listBeen!=null&&listBeen.size()>0)
             speedHourAdapter.setList(listBeen);
        }

        speedHourAdapter.setOnItemClickListener(new SpeedHourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ProductDetailsActivity.open(mContext);
            }
        });
    }

    @OnClick(R.id.more_view)
    public void moreClick() {
        ToastUtils.showToast("更多时速达");
    }
}
