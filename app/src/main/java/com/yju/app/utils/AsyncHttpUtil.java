package com.yju.app.utils;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Map;

public class AsyncHttpUtil {

    public static final int STATUS_OK = 200;
    private static String BASE_URL = ""; // request base url
    private static AsyncHttpClient client = new AsyncHttpClient(); // async mode
    private static SyncHttpClient syncClient = new SyncHttpClient(); // sync mode

    static {
        syncClient.setTimeout(25000); // sync mode, 15 seconds is enough
        client.setTimeout(30000);
    }

    public static String[] mAllowedContentTypes = new String[]{
            RequestParams.APPLICATION_OCTET_STREAM,
            "application/x-javascript.*",
            "application/x-jpg.*",
            "application/x-png.*",
            "application/javascript.*",
            "image/jpeg.*",
            "image/png.*",
            "image/gif.*",
            "text/javascript.*",
            "text/css.*",
            "text/html.*"
    };

    public static void setTimeout(int time) {
        client.setTimeout(time);
    }

    public static void resetTimeout() {
        client.setTimeout(20000);
    }

    public static void setBaseUrl(String url) {
        BASE_URL = url;
    }

    // 带参、上下文请求 via TextHttpResponseHandler
    public static void get(Context context, String url, RequestParams params,
                           TextHttpResponseHandler res) {
        get(context, url, params, null, res);
    }

    // 带参、上下文请求 via JsonHttpResponseHandler
    public static void get(Context context, String url, RequestParams params,
                           JsonHttpResponseHandler res) {
        get(context, url, params, null, res);
    }

    // 带参、上下文请求 via AsyncHttpResponseHandler
    public static void get(Context context, String url, RequestParams params,
                           AsyncHttpResponseHandler res) {
        get(context, url, params, null, res);
    }

    // 带参、上下文、Headers请求 via AsyncHttpResponseHandler base
    public static void get(Context context, String url, RequestParams params,
                           Map<String, String> headers,
                           AsyncHttpResponseHandler res) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                client.addHeader(key, headers.get(key));
            }
        }
        client.get(context, getAbsoluteUrl(url), params, res);
    }

    // 用一个完整url获取一个string对象
    public static void get(String url, AsyncHttpResponseHandler res) {
        client.get(getAbsoluteUrl(url), res);
    }

    // url里面带参数
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler res) {
        client.get(getAbsoluteUrl(url), params, res);
    }

    // 不带参数，获取json对象或者数组
    public static void get(String url, JsonHttpResponseHandler res) {
        client.get(getAbsoluteUrl(url), res);
    }

    // 带参数，获取json对象或者数组
    public static void get(String url, RequestParams params, JsonHttpResponseHandler res) {
        client.get(getAbsoluteUrl(url), params, res);
    }

    // 不带参数，获取String对象或者数组
    public static void get(String url, TextHttpResponseHandler res) {
        client.get(getAbsoluteUrl(url), res);
    }

    // 带参数，获取String对象或者数组
    public static void get(String url, RequestParams params, TextHttpResponseHandler res) {
        client.get(getAbsoluteUrl(url), params, res);
    }

    // 不带参数，下载数据使用，会返回byte数据
    public static void get(String url, BinaryHttpResponseHandler bHandler) {
        client.get(getAbsoluteUrl(url), bHandler);
    }

    public static void get(Context context, String url, BinaryHttpResponseHandler res) {
        client.get(context, getAbsoluteUrl(url), res);
    }


    public static void get(Context context, String url, FileAsyncHttpResponseHandler res) {
        client.get(context, getAbsoluteUrl(url), res);
    }

    // 带参数，下载数据使用，会返回byte数据
    public static void get(String url, RequestParams params, BinaryHttpResponseHandler bHandler) {
        client.get(getAbsoluteUrl(url), params, bHandler);
    }

    // post用一个完整url获取一个string对象
    public static void post(String url, AsyncHttpResponseHandler res) {
        client.post(getAbsoluteUrl(url), res);
    }

    // post url里面带参数
    public static RequestHandle post(String url, RequestParams params, AsyncHttpResponseHandler res) {
        return client.post(getAbsoluteUrl(url), params, res);
    }

    // post不带参数，获取json对象或者数组
    public static void post(String url, JsonHttpResponseHandler res) {
        client.post(getAbsoluteUrl(url), res);
    }

    // post不带参数，获取String对象或者数组
    public static void post(String url, TextHttpResponseHandler res) {
        client.post(getAbsoluteUrl(url), res);
    }

    // post带参数，获取json对象或者数组
    public static void post(String url, RequestParams params, JsonHttpResponseHandler res) {
        client.post(getAbsoluteUrl(url), params, res);
    }

    // post带参数，获取String对象或者数组
    public static void post(String url, RequestParams params, TextHttpResponseHandler res) {
        client.post(getAbsoluteUrl(url), params, res);
    }

    // post不带参数，下载数据使用，会返回byte数据
    public static void post(String url, BinaryHttpResponseHandler bHandler) {
        client.post(getAbsoluteUrl(url), bHandler);
    }

    // post带参数，下载数据使用，会返回byte数据
    public static void post(String url, RequestParams params, BinaryHttpResponseHandler bHandler) {
        client.post(getAbsoluteUrl(url), params, bHandler);
    }


    // post带参、上下文请求 via TextHttpResponseHandler
    public static void post(Context context, String url, RequestParams params,
                            TextHttpResponseHandler res) {
        post(context, url, params, null, res);
    }

    // post带参、上下文请求 via JsonHttpResponseHandler
    public static void post(Context context, String url, RequestParams params,
                            JsonHttpResponseHandler res) {
        post(context, url, params, null, res);
    }

    // post带参、上下文请求 via AsyncHttpResponseHandler
    public static void post(Context context, String url, RequestParams params,
                            AsyncHttpResponseHandler res) {
        post(context, url, params, null, res);
    }

    // post带参、上下文、Headers请求 via AsyncHttpResponseHandler base
    public static void post(Context context, String url, RequestParams params,
                            Map<String, String> headers,
                            AsyncHttpResponseHandler res) {
        for (String key : headers.keySet()) {
            client.addHeader(key, headers.get(key));
        }
        client.get(context, getAbsoluteUrl(url), params, res);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        if (relativeUrl.startsWith("http://"))
            return relativeUrl;
        return BASE_URL + relativeUrl;
    }

    public static AsyncHttpClient getClient() {
        return client;
    }

    public static SyncHttpClient getSyncClient() {
        return syncClient;
    }

    public static void setAsyncUserAgent(String userAgent) {
        if (!TextUtils.isEmpty(userAgent))
            getClient().setUserAgent(userAgent);
    }

    public static void setSyncUserAgent(String userAgent) {
        if (!TextUtils.isEmpty(userAgent))
            getSyncClient().setUserAgent(userAgent);
    }
}