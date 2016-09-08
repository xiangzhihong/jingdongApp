package com.yju.app.http.service;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 请求包装类
 */
public interface RetrofitService {

    @GET("http://app.ymatou.com/api/Home/GetHomeLive?DeviceId=869437023502325&AppName=Buyer&UserId=2168427&guid=0c50a09a-d0fe-4dfa-be69-60edba34a100&hometype=2&AccessToken=4F137368CAC21F23A81D31AD992591044153CEBE2EB8089E1787F36958BED81375EA0DBB245726510CE1A1A727AF9E897036EB18E4D5AEFB&userid=2168427&ClientId=22c8334d29dd613784147a5fff1d57cf&yid=cac078e9-3e3d-444b-ae43-18dea934349c&currentVersion=3.1.2&ClientUserId=2168427&imei=869437023502325&ClientType=2&cookieid=00000000-5ad4-68f4-1b3a-dcf30033c587&DeviceToken=00000000-5ad4-68f4-1b3a-dcf30033c587")
    Call<ResponseBody> getSpecail(@Url String url);


}
