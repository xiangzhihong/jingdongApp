package com.yju.app.shihui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yju.app.R;
import com.yju.app.base.BaseFragment;
import com.yju.app.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/8/23.
 * 我的
 */
public class MineFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        init();
        ButterKnife.bind(this, view);
        return view;
    }

    private void init() {

    }


    @OnClick(R.id.tel_service)
    public void businessClick() {
        UIHelper.callServiceDialog(getActivity(),"4006-611-388");
    }

}
