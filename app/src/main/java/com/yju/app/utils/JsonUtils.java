package com.yju.app.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static Gson gson = new Gson();

    public static <T> T parseJson(String response, Class<T> clazz) {
        try {
            return gson.fromJson(response, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseJson(String response, Type type) {
        try {
            return gson.fromJson(response, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <T> List<T> jsonToList(final String jsonString, final Type type) {
        try {
            return gson.fromJson(jsonString, type);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> jsonToList(final JsonArray jsonArray, final Class<T> classOfT) {
        List<T> list = new ArrayList<T>();

        for (int i = 0, size = jsonArray.size(); i < size; i++) {
            list.add(gson.fromJson(jsonArray.get(i), classOfT));
        }

        return list;
    }

    public static <T> T parseJson(final JsonObject jsonObject, final Class<T> classOfT) {
        try {
            return gson.fromJson(jsonObject, classOfT);
        } catch (Exception e) {
            return null;
        }
    }

    public static Map toMap(Object jsonString) {
        if (TextUtils.isEmpty(jsonString.toString())) {
            return new HashMap();
        }
        String js = jsonString.toString();
        Gson gson = new Gson();
        Type type = new TypeToken<Map>(){
        }.getType();
        Map map = gson.fromJson(js,type);
        return map;
    }

}
