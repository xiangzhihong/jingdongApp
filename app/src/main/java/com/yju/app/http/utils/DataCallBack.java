package com.yju.app.http.utils;

/**
 * Created by user on 2016/7/12.
 */
public interface DataCallBack<T> {
        void onSuccessResult(T result);
//        void onFailed(String errorMessage);
        void onNetFailed();
}
