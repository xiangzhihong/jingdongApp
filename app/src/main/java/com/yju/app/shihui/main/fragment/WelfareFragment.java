package com.yju.app.shihui.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.yju.app.R;
import com.yju.app.base.BaseFragment;
import com.yju.app.http.utils.DataCallBack;
import com.yju.app.shihui.welfare.bean.BannerEntity;
import com.yju.app.shihui.welfare.bean.FineFareEntity;
import com.yju.app.shihui.welfare.bean.SpecialSaleEntity;
import com.yju.app.shihui.welfare.manager.WelfareHttpManager;
import com.yju.app.shihui.welfare.view.LampView;
import com.yju.app.shihui.welfare.view.SpecialSaleView;
import com.yju.app.shihui.welfare.view.WelfareView;
import com.yju.app.utils.FileUtils;
import com.yju.app.utils.JsonUtils;
import com.yju.app.utils.NetworkUtil;
import com.yju.app.utils.ToastUtils;
import com.yju.app.widght.banner.BannerConfig;
import com.yju.app.widght.banner.BannerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/8/23.
 * 福利
 */
public class WelfareFragment extends BaseFragment implements PullToRefreshListView.OnRefreshListener2 {

    @BindView(R.id.pull_refresh_scrollview)
    PullToRefreshScrollView pullRefreshScrollview;
    @BindView(R.id.code_iv)
    ImageView codeIv;
    @BindView(R.id.toobar_name)
    TextView toobarName;
    @BindView(R.id.welfare_bar_view)
    RelativeLayout welfareBarView;
    //Banner
    @BindView(R.id.banner_view)
    BannerView bannerView;
    //精品福利
    @BindView(R.id.welfare_view)
    WelfareView welfareView;
    //特賣
    @BindView(R.id.special_view)
    SpecialSaleView specialView;
    //跑马灯
    @BindView(R.id.lamp_view)
    LampView lampView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_welfare, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initView();
        initBanner();
        initLamp();
        initWelfare();
        initSpecail();
    }

    private void initLamp() {
        List<String> list=new ArrayList<>();
        list.add("关于小区清理楼道内杂物的通知");
        list.add("与商品系统、订单系统进行的同步更改");
        list.add("增加特卖活动报名功能");
        list.add("服务社门店系统不显示供应商信息");
        lampView.setLampData(list);
    }

    private void initSpecail() {
        if (NetworkUtil.isWifiEnabled(getActivity())){
            getSpecail();
        }else {
            initLocalData();
        }
    }

    //特卖
    private void getSpecail() {
        HashMap<String,String> map=new HashMap<>();
        map.put("DeviceId","869437023502325");
        map.put("AppName","Buyer");
        map.put("UserId","2168427");
        map.put("guid","0c50a09a-d0fe-4dfa-be69-60edba34a100");
        map.put("hometype","2");
        map.put("AccessToken","4F137368CAC21F23A81D31AD992591044153CEBE2EB8089E1787F36958BED81375EA0DBB245726510CE1A1A727AF9E897036EB18E4D5AEFB");
        map.put("userid","2168427");
        map.put("ClientId","22c8334d29dd613784147a5fff1d57cf");
        map.put("yid","cac078e9-3e3d-444b-ae43-18dea934349c");
        map.put("currentVersion","3.1.2");
        map.put("ClientUserId","2168427");
        map.put("ClientType","2");
        map.put("cookieid","00000000-5ad4-68f4-1b3a-dcf30033c587");
        map.put("DeviceToken","00000000-5ad4-68f4-1b3a-dcf30033c587");

        WelfareHttpManager.getSpecialData(getActivity(), map, new DataCallBack() {
            @Override
            public void onSuccessResult(Object result) {
                SpecialSaleEntity entity = (SpecialSaleEntity)result;
                if (entity != null && entity.live != null && entity.live.lives.size() > 0) {
                    specialView.setSpecialData(entity.live.lives);
                }
            }

            @Override
            public void onNetFailed() {
                 ToastUtils.showToast("数据错误");
            }
        });
    }

    //特卖专题
    private void initLocalData() {
        String welfare = FileUtils.readAssert(getActivity(), "specail.txt");
        SpecialSaleEntity entity = JsonUtils.parseJson(welfare, SpecialSaleEntity.class);
        if (entity != null) {
            specialView.setSpecialData(entity.live.lives);
        }
    }

    //精品福利
    private void initWelfare() {
        String welfare = FileUtils.readAssert(getActivity(), "welfare.txt");
        FineFareEntity entity = JsonUtils.parseJson(welfare, FineFareEntity.class);
        if (entity != null) {
            welfareView.setWelfareData(entity.panic);
        }
    }

    private void initView() {
        welfareBarView.getBackground().mutate().setAlpha(0);
        pullRefreshScrollview.getRefreshableView().getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int alpha = pullRefreshScrollview.getRefreshableView().getScrollY() / 4;
                welfareBarView.getBackground().mutate().setAlpha(alpha <= 255 ? alpha < 10 ? 0 : alpha : 255);
            }
        });
        toobarName.setText("新梅共和城小区");

        pullRefreshScrollview.setFocusableInTouchMode(true);
        pullRefreshScrollview.setOnRefreshListener(this);
        pullRefreshScrollview.setScrollingWhileRefreshingEnabled(true);
    }

    private void initBanner() {
        bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        String banner = FileUtils.readAssert(getActivity(), "banner.txt");
        final BannerEntity entity = JsonUtils.parseJson(banner, BannerEntity.class);
        if (entity != null && entity.banner != null && entity.banner.size() > 0) {
            List<String> bannerList = new ArrayList<>();
            for (int i = 0; i < entity.banner.size(); i++) {
                bannerList.add(entity.banner.get(i).pic);
            }
            bannerView.setImages(bannerList);
            bannerView.setDelayTime(5000);
        }

        bannerView.setOnBannerClickListener(new BannerView.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                ToastUtils.showToast("你点击了：" + entity.banner.get(position).sharelink);
            }
        });
    }

    //下拉加载更多
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
         init();
         refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        refreshView.onRefreshComplete();
    }

   //模拟刷新
    private void onRefreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pullRefreshScrollview != null) {
                    pullRefreshScrollview.setRefreshing();
                }
            }
        }, 500);
    }

}
