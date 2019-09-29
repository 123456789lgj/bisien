package com.bisien.dems.activity.utils;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyHttpUtils {
    public void getDataFromServiceByGet(String url,final OnNetResponseListener listener){
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if(listener != null) {
                    listener.onNotOk(e.toString());
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if(listener != null) {
                    listener.onOk(response);
                }
            }
        });

    }
    public void getDataFromServiceByPost(String url, HashMap<String,String> params,final OnNetResponseListener listener){

        PostFormBuilder builder = OkHttpUtils.post().url(url);
        //遍历Hashmap，然后把参数添加builder中
        for(String key : params.keySet()){
            String value = params.get(key);
            builder.addParams(key,value);
        }
        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if(listener != null) {
                    listener.onNotOk(e.toString());
                }
            }
            @Override
            public void onResponse(String response, int id) {
                if(listener != null) {
                    listener.onOk(response);
                }
            }
        });
//       ---------------------------------------上面是一般的请求方式------------------------------------------------------------------------------------------------


    }
//    该方法是提交json类型的字符串数据
    public void getDataFromServiceByPostByJson(String url, HashMap<String,String> params,final OnNetResponseListener listener){
        Gson gson = new Gson();
//        把hashmap 转成json数据
        String json = gson.toJson(params);
        Log.i("json ",json);
        PostStringBuilder builder = OkHttpUtils.postString().url(url).content(json).mediaType(MediaType.parse("application/json; charset=utf-8"));
        builder.addHeader("user","-1");
        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (listener != null){
                    listener.onNotOk(e.toString());
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if(listener != null) {
                    listener.onOk(response);
                }
            }
        });

    }
    public interface OnNetResponseListener{
        public void onOk(String response);

        public void onNotOk(String msg);
    }
}
