package com.yju.app.shihui.welfare.manager;

import android.content.Context;
import android.util.Log;

import com.yju.app.http.utils.DataCallBack;
import com.yju.app.shihui.welfare.bean.SpecialSaleEntity;
import com.yju.app.shihui.welfare.http.WelfareModel;
import com.yju.app.utils.JsonUtils;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2016/8/25.
 * 微福利网络请求manager
 */
public class WelfareHttpManager {


    public static void getSpecialData(Context context, HashMap<String,String> map, final DataCallBack dataCallBack) {

        WelfareModel model=WelfareModel.getInstance(context);
        Call<SpecialSaleEntity> call=model.getSpecialData(map);

          call.enqueue(new Callback<SpecialSaleEntity>() {
              @Override
              public void onResponse(Call<SpecialSaleEntity> call, Response<SpecialSaleEntity> response) {
                  SpecialSaleEntity bean = response.body();
                  Log.d("Response",response.body().toString());
                  if (bean!=null){
                      dataCallBack.onSuccessResult(bean);
                  }
              }

              @Override
              public void onFailure(Call<SpecialSaleEntity> call, Throwable t) {
                  dataCallBack.onSuccessResult("数据错误");
              }
          });
    }




}
