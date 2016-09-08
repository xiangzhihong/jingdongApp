package com.yju.app.shihui.welfare.http;

import android.content.Context;

import com.yju.app.http.ApiConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory instance;
    private Retrofit retrofit;

    private RetrofitFactory() {
        retrofit = new Retrofit.Builder().baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class){
                if (instance==null){
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }


    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
