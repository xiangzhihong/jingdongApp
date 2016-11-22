package com.yju.app.shihui.welfare.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.shihui.welfare.adapter.WelfareAdapter;
import com.yju.app.shihui.welfare.bean.FineFareEntity.PanicBean;
import com.yju.app.shihui.welfare.utis.ScalePagerTransformer;
import com.yju.app.utils.UIUtils;
import com.yju.app.widght.SimpleLinearLayout;
import com.yju.app.widght.viewpager.LoopViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 精品福利
 */
public class WelfareView extends SimpleLinearLayout {

    @BindView(R.id.finefare_count)
    TextView finefareCount;
    @BindView(R.id.viewPager)
    LoopViewPager viewPager;
    @BindView(R.id.finefare_name)
    TextView finefareName;
    @BindView(R.id.welfare_view)
    LinearLayout welfareView;

    private WelfareAdapter adapter = null;
    private List<PanicBean> welfareList = null;
    private int lastItemIndex = 0;

    public WelfareView(Context context) {
        super(context);
    }

    public WelfareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        contentView = inflate(mContext, R.layout.layout_welfare, this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initViewPager();
        initTouch();
        initListener();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                applyTransform(position);
                finefareName.setText(welfareList.get(getCurrentDisplayItem()).id);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTouch() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
    }

    private void initViewPager() {
        viewPager.setPageTransformer(true, new ScalePagerTransformer());
        //设置Pager之间的间距
        viewPager.setPageMargin(UIUtils.dp2px(mContext, 10));

        adapter = new WelfareAdapter(mContext);
        viewPager.setAdapter(adapter);
    }

    public void setWelfareData(List<PanicBean> datas) {
        this.welfareList = datas;
        welfareView.setVisibility(datas.size()>0?VISIBLE:GONE);
        finefareCount.setText("共有" + datas.size() + "个福利");
        finefareName.setText(welfareList.get(getCurrentDisplayItem()).id);

        adapter = new WelfareAdapter(mContext);
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(getCount());
        viewPager.setCurrentItem(adapter.getCount() > 0 ? 1 : 0, true);
    }

    private int getCount() {
        return adapter!=null?adapter.getCount():0;
    }

    public int getCurrentDisplayItem() {
        return viewPager!=null?viewPager.getCurrentItem():0;
    }

    private void applyTransform(int currentPosition) {
        if (currentPosition>getCount()){
            currentPosition=getCount();
        }
        if (lastItemIndex == currentPosition) {
            if (viewPager != null && viewPager.beginFakeDrag()) {
                viewPager.fakeDragBy(0f);
                viewPager.endFakeDrag();
            }
        }
        lastItemIndex = currentPosition;
    }

}
