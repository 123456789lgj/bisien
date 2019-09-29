package com.bisien.dems.activity.widget;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;

/**
 * Created by LGJ on 2019/6/22.
 */

public class CustomAnimal extends Animation {
    public static String TAG = CustomAnimal.class + " ; lgj";
    private ArrayList<ViewBean> list;
    private View view;
    public CustomAnimal(View view,ArrayList<ViewBean> list){
        this.view = view;
        this.list = list;

    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Log.i(TAG,"interpolatedTime " + interpolatedTime);
        if (interpolatedTime <= 1.0f){
            for (int i = 0; i < list.size();i++){
                ViewBean viewBean = list.get(i);
                float data = viewBean.getData();
                float percent  = (data / 360) * 360 * interpolatedTime;
                viewBean.setAngle(percent);//在此时所绘制的弧度数
                if (interpolatedTime > 0){
                    view.postInvalidate();
                }
            }
        }
    }

}
