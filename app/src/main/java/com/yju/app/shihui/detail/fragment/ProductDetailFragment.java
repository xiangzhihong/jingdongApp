package com.yju.app.shihui.detail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yju.app.R;
import com.yju.app.base.BaseFragment;
import com.yju.app.shihui.welfare.bean.BannerEntity;
import com.yju.app.utils.FileUtils;
import com.yju.app.utils.JsonUtils;
import com.yju.app.widght.banner.BannerConfig;
import com.yju.app.widght.banner.BannerView;
import com.yju.app.widght.dampscrollview.ScrollViewWrapper;
import com.yju.app.widght.dampscrollview.util.OnScrollViewChangeListener;
import com.yju.app.widght.timecount.CountDownView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品详情的详情页
 */
public class ProductDetailFragment extends BaseFragment {

    @BindView(R.id.detail_banner)
    BannerView detailBanner;
    @BindView(R.id.scrollViewWrapper)
    ScrollViewWrapper scrollViewWrapper;
    @BindView(R.id.countdown_view)
    CountDownView countdownView;

    private long testTime = (2 * 24 * 3600 + 10 * 3600 + 18 * 60 + 53) * 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initBanner();
        initData();
    }

    private void initData() {
        countdownView.setTime(testTime);
        countdownView.start();
    }

    private void initBanner() {
        String banner = FileUtils.readAssert(getActivity(), "banner.txt");
        final BannerEntity entity = JsonUtils.parseJson(banner, BannerEntity.class);
        if (entity != null && entity.banner != null && entity.banner.size() > 0) {
            List<String> bannerList = new ArrayList<>();
            for (int i = 0; i < entity.banner.size(); i++) {
                bannerList.add(entity.banner.get(i).pic);
            }
            detailBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
            detailBanner.setImages(bannerList);
            detailBanner.setDelayTime(5000);
        }
    }


}
