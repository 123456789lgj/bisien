package com.bisien.dems.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.LocationBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.global.GlobalDataInit;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.PrefUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

public class SplashActivity extends Activity {

    private ImageView imageView;
    public static String TAG = SplashActivity.class + " lgj";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //集成AppCompatActivity去掉标题栏
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置成全屏，这种设置会出现短暂的状态栏，
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
//                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.image_view);
        tv = findViewById(R.id.tv);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(2000);


        //2、缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2000);

        //动画集合
        //参数代表的含义：true 表示使用AnimationSet 的动画插值器，false 表示使用各自的动画插值器
        //什么是动画的插入器：动画变化的速率设置
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateInterpolator());//先慢后快
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        imageView.setAnimation(animationSet);
        tv.setAnimation(animationSet);
        animationSet.start();
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "onAnimationEnd");
//                MyApplication.instance.handler.se
                UiUtils.getMainThreadHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 500);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        tv.startAnimation(alphaAnimation);
//        imageView.startAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Log.i(TAG,"onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Log.i(TAG,"onAnimationEnd");
//                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

//        GifUtils.gifStart(this, R.mipmap.splash, imageView, 1, new GifUtils.GifListener() {
//            @Override
//            public void gifPlayComplete() {
//                System.out.println("gif time222" + System.currentTimeMillis());
//                Log.i(TAG,"gifPlayComplete");
//            }
//        });
        String ip = PrefUtils.getString(UiUtils.getContext(), "setIp", "");

//        没事设置IP的时候，不能够获取locationInfo的信息
        if (ip == null || "".equals(ip)) {

        } else {
            GlobalConstants.ip = ip;
//            在Application 中早点初始化全局数据

            if (MyApplication.stationMap.size() == 0){
                GlobalDataInit.initGloalData();
            }
//            增加App使用期限
//            GlobalConstants.ip = ip;
//            String url = GlobalConstants.getUrlFirst() + "rest/location/info";
//            MyHttpUtils myHttpUtils = new MyHttpUtils();
////            访问获取LocationId
//            myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
//                @Override
//                public void onOk(String response) {
//                    Log.i(TAG, "onOk :" + "response :" + response);
//                    Gson gson = new Gson();
//                    LocationBean locationBean = gson.fromJson(response, LocationBean.class);
//                    MyApplication.locationBean = locationBean;
//                }
//
//                @Override
//                public void onNotOk(String msg) {
//                    MyApplication.locationBean = null;
//                    Log.i(TAG, "onNotOk :" + msg);
//                }
//            });

        }


    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.from_right_in, R.anim.to_left_out);
    }

}
