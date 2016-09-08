package com.yju.app.utils;

import android.content.Context;

import java.io.InputStream;

public class FileUtils {

    public static String readAssert(Context context, String fileName){
        String resultString="";
        try {
            InputStream inputStream=context.getResources().getAssets().open(fileName);
            byte[] buffer=new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString=new String(buffer,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
