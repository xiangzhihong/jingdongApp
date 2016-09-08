package com.yju.app.shihui.welfare.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.shihui.special.ValueSaleListActivity;
import com.yju.app.utils.PriceUtils;
import com.yju.app.utils.UIUtils;
import com.yju.app.widght.SimpleLinearLayout;
import com.yju.app.widght.timecount.CountDownTimerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 超值特卖
 */
public class ValueSaleView extends SimpleLinearLayout {

    @BindView(R.id.countdown_timer)
    CountDownTimerView countdownTimer;
    @BindView(R.id.new_price)
    TextView newPrice;
    @BindView(R.id.original_price)
    TextView originalPrice;



    public ValueSaleView(Context context) {
        super(context);
    }

    public ValueSaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        contentView = inflate(mContext, R.layout.layout_value_sale, this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PriceUtils.convertPriceSize(mContext,newPrice,"￥50", UIUtils.dp2px(mContext,20));
        UIUtils.setTextFlag(originalPrice,true);
        countdownTimer.setTime();
        countdownTimer.start();
    }

    @OnClick(R.id.value_more)
    public void moreClick() {
        ValueSaleListActivity.open(mContext);
    }
}
