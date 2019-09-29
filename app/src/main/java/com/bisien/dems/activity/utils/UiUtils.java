package com.bisien.dems.activity.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.bisien.dems.activity.application.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UiUtils {
    public static String TAG = UiUtils.class + " lgj";

    //获取全局Context对象
    public static Context getContext() {
        return MyApplication.instance.context;
    }

    //获取主线程的Handler对象
    public static Handler getMainThreadHandler() {
        return MyApplication.instance.handler;
    }

    //获取主线程的线程id
    public static int getMainThreadId() {
        return MyApplication.instance.mainThreadId;
    }

    //获取字符串
    public static String getString(int resId) {
        return getContext().getResources().getString(resId);
    }

    //获取字符串数组
    public static String[] getStringArray(int resId) {
        return getContext().getResources().getStringArray(resId);
    }

    //获取drawable
    public static Drawable getDrawable(int resId) {
        return getContext().getResources().getDrawable(resId);
    }

    public static int getColor(int resId) {
        return getContext().getResources().getColor(resId);
    }

    public static int getDimen(int resId) {
        return getContext().getResources().getDimensionPixelSize(resId);
    }

    //dip2px
    public static int dip2px(int dip) {
        //屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    //px2dip
    public static int px2dip(int px) {
        //屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px/density + 0.5f);
    }


    public static View inflateView(int resId) {
        return View.inflate(getContext(), resId, null);
    }

    public static void toast(String msg) {
//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        ToastUtils.showToast(getContext(),msg,Toast.LENGTH_SHORT);
    }

    //判断是否是在主线程
    public static boolean isRunOnUiThread() {
        //1、获取当前线程的id
        int currentThreadId = android.os.Process.myTid();
        //2、获取主线程的id
        int mainThreadId = getMainThreadId();
        //3、做比较
        return currentThreadId == mainThreadId;
    }

    /**
     * 保证r这个任务一定是在主线程中执行
     *
     * Process:进程
     * Thread：线程
     * Runnable：任务
     */
    public static void runOnUiThread(Runnable r) {
        if (isRunOnUiThread()) {
            //主线程
            //new Thread(r).start();
            r.run();
        } else {
            //子线程
            getMainThreadHandler().post(r);//将任务r丢到了主线程的消息队列
        }
    }

    public static int getScreenWidth() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *（DisplayMetrics类中属性scaledDensity）
     *  float density = getContext().getResources().getDisplayMetrics().density;
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    private static int[] arr = new int[2];
    private static int[] initWindow(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        arr[0] =  width;
        arr[1] =  height;
        System.out.println("width :" + width + "height :" + height);
        return arr;
    }
    public static int[] getWindowWidthAndHeight(){
        if (arr[0] == 0 || arr[1] == 0){
            return initWindow();
        }
        return arr;
    }
    public static String getTime(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        return format;
    }

}
