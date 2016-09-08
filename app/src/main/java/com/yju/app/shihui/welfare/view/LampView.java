package com.yju.app.shihui.welfare.view;

import android.content.Context;
import android.util.AttributeSet;

import com.yju.app.R;
import com.yju.app.shihui.welfare.view.lamp.HorizonScrollTextView;
import com.yju.app.utils.ToastUtils;
import com.yju.app.widght.SimpleLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 跑马灯
 */
public class LampView extends SimpleLinearLayout {

    @BindView(R.id.horizonScrollTextView)
    HorizonScrollTextView horizonScrollTextView;

    public LampView(Context context) {
        super(context);
    }

    public LampView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        contentView = inflate(mContext, R.layout.layout_lamp, this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        horizonScrollTextView.setTextSize(14);
    }

    public void setLampData(List<String> listStr) {
        String lampStr="";
      for (String str:listStr){
          lampStr+=str+"               ";
      }
      horizonScrollTextView.setText(lampStr);
    }

    @OnClick(R.id.lamp_more)
    public void moreClick() {
        ToastUtils.showToast("更多消息");
    }
}
