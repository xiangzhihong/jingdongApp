package com.yju.app.shihui.welfare.view;

import android.content.Context;
import android.util.AttributeSet;

import com.yju.app.R;
import com.yju.app.shihui.estateservice.activity.EstatePaymentActivity;
import com.yju.app.widght.SimpleLinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 物业服务
 */
public class EstateView extends SimpleLinearLayout {

    public EstateView(Context context) {
        super(context);
    }

    public EstateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        contentView = inflate(mContext, R.layout.layout_estate, this);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.estate_payment_view)
    public void payClick() {
        EstatePaymentActivity.open(mContext);
    }
}
