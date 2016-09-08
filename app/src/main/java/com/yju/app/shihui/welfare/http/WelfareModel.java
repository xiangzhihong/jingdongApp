package com.yju.app.shihui.welfare.http;

import android.content.Context;

import com.yju.app.shihui.welfare.bean.SpecialSaleEntity;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by Xiho on 2016/3/14.
 */
public class WelfareModel {

    private static WelfareModel famousInfoModel;
    private WelfareService service;

    public static WelfareModel getInstance(Context context) {
        if (famousInfoModel == null) {
            famousInfoModel = new WelfareModel(context);
        }
        return famousInfoModel;
    }


    private WelfareModel(Context context) {
        service = (WelfareService) RetrofitFactory.getInstance().create(WelfareService.class);
    }

    //获取特卖列表
    public Call<SpecialSaleEntity> getSpecialData(HashMap<String,String> url) {
        Call<SpecialSaleEntity> infoCall = service.getSpecail(url);
        return infoCall;
    }
}
