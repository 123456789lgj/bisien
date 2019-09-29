package com.bisien.dems.activity.application;

import android.app.Application;
import android.content.Context;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.bean.HouseInfoBean;
import com.bisien.dems.activity.bean.LocationBean;
import com.bisien.dems.activity.utils.UiUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    public static MyApplication instance;
    public static int userId;// 根据userId来修改密码
    public static LocationBean locationBean;
    public Handler handler;
    public Context context;
    public static String TAG = MyApplication.class + " lgj";
    public int mainThreadId;
    public static int toastHeight;
//    存放的站id和对应的站名称，比如局站A
    public static HashMap<Long,String> stationMap = new HashMap<>();
//    key 为局站id ，局站id对应的是局站房间： 存放的是房间id和房间名称，比如二楼实验室
    public static HashMap<Long, ArrayList<HouseInfoBean>> houseMap = new HashMap<>();
//    设备类型的type对应的设备名称
    public static HashMap<Integer,String> equipmentTypeName = new HashMap<>();

    //    设备类型的类型对应的那些具体设备
    public static HashMap<Integer, List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean>> equipments = new HashMap<>();
    public static HashMap<String,String> filterContitions = new HashMap<>();
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;

        handler = new Handler();
        //判断当前线程是主线程还是子线程
        mainThreadId = Process.myTid();
//初始化Toast的高度
        int height = UiUtils.getWindowWidthAndHeight()[1];
        Log.i(TAG,"height : " +height);
        toastHeight = height/4;

        //配置超时时间等信息
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
//        Glide.get(this)


    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(1);
    }
}
