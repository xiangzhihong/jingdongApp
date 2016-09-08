package com.yju.app.shihui.welfare.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.shihui.welfare.adapter.SpecailSaleAdapter;
import com.yju.app.shihui.welfare.bean.SpecialSaleEntity.LiveBeanEityty.LivesBean;
import com.yju.app.widght.MeasureListView;
import com.yju.app.widght.SimpleLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/8/26.
 * 特卖
 */
public class SpecialSaleView extends SimpleLinearLayout {

    @BindView(R.id.special_more)
    TextView specialMore;
    @BindView(R.id.special_listview)
    MeasureListView specialListview;

    private SpecailSaleAdapter specialAdapter=null;

    public SpecialSaleView(Context context) {
        super(context);
    }

    public SpecialSaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        contentView = inflate(mContext, R.layout.layout_special_sale, this);
        ButterKnife.bind(this);
        init();
        initTouch();
    }

    private void init() {

    }

    //这里要把父类的touch事件传给子类，不然边上的会滑不动
    private void initTouch() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return specialListview.dispatchTouchEvent(event);
            }
        });
    }

    public void setSpecialData(List<LivesBean> list) {
        specialAdapter=new SpecailSaleAdapter(mContext);
        specialListview.setAdapter(specialAdapter);
          if (specialAdapter!=null){
              specialAdapter.setList(list);
          }
    }
}
