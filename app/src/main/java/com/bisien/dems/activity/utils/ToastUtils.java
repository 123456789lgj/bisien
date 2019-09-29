package com.bisien.dems.activity.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;

public class ToastUtils {
    /**
     * refreshToast:在屏幕下部显示Toast提示信息. <br/>
     *
     * @param context  -    上下文
     * @param msg      -    提示消息
     * @param lastTime -    持续时间，0-短时间，LENGTH_SHORT；1-长时间，LENGTH_LONG；
     */
    public static void showToast(Context context, String msg, int lastTime) {
        int resId = R.mipmap.ic_display;
        View view = View.inflate(context, R.layout.toast, null);
        Toast toast = new Toast(context);
        toast.setView(view);
//        Gravity.CENTER  居中显示
//        xOffset : 是距离中间横坐标的偏移量
//        yOffset : 是距离中间的纵坐标偏移量  在屏幕下方的四分之一进行显示
        toast.setGravity(Gravity.CENTER, 0, MyApplication.toastHeight);
        ((TextView) view.findViewById(R.id.common_toast_tv)).setText(msg);
        toast.setDuration(lastTime);
        toast.show();
    }
}
