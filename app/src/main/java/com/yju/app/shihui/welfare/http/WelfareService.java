package com.yju.app.shihui.welfare.http;

import com.yju.app.shihui.welfare.bean.SpecialSaleEntity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface WelfareService {

    @GET("Home/GetHomeLive")
    Call<SpecialSaleEntity> getSpecail(@QueryMap HashMap<String,String> url);

}
