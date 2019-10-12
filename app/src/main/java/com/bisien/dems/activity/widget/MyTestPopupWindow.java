package com.bisien.dems.activity.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.fragment.AlarmFragment;
import com.bisien.dems.activity.utils.UiUtils;

public class MyTestPopupWindow extends PopupWindow {
    private  TextView tvRealTime;
    private  TextView tvGeneral;
    private  TextView tvImportant;
    private  TextView tvUrgent;
    public static int currentSelect = 3;//记录 到警告级别： 一般、重要、紧急
    private  AlarmFragment alarmFragment;
//    public void setBackgroundAlpha(float bgAlpha,final Activity context) {
//        WindowManager.LayoutParams lp = context.getWindow()
//                .getAttributes();
//        lp.alpha = bgAlpha;//设置透明度（这是窗体本身的透明度，非背景）
//        lp.dimAmount = bgAlpha;//设置灰度
//        if (bgAlpha == 1) {
//            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
//        } else {
//            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为 红米米手机上半透明效果无效的bug
//        }
//        context.getWindow().setAttributes(lp);
//    }
    public MyTestPopupWindow(View locationView,final Activity context, final AlarmFragment alarmFragment) {
        super(context);
//        设置透明背景
//        setBackgroundAlpha(0.5f,context);
        this.alarmFragment = alarmFragment;

//        设置点击外面进行消失
        setOutsideTouchable(true);
        setFocusable(true);
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_window,
                null, false);
        tvRealTime = contentView.findViewById(R.id.tvRealTime);
        tvGeneral = contentView.findViewById(R.id.tvGeneral);
        tvImportant = contentView.findViewById(R.id.tvImportant);
        tvUrgent = contentView.findViewById(R.id.tvUrgent);

        tvRealTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelect == 3){//防止再次刷新数据
                    dismiss();
                    return;
                }
                currentSelect = 3;
                obtainFocus(context);
                MyApplication.filterContitions.put("alarmGrade","-1");
                alarmFragment.requestAlarmData(tvRealTime.getText().toString());
                dismiss();
            }
        });

        tvUrgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelect == 0){//防止再次刷新数据
                    dismiss();
                    return;
                }
                currentSelect = 0;
                obtainFocus(context);
                MyApplication.filterContitions.put("alarmGrade",(String) tvUrgent.getTag());
                alarmFragment.requestAlarmData(tvUrgent.getText().toString());
                dismiss();
            }
        });

        tvImportant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelect == 1){
                    dismiss();
                    return;
                }
                currentSelect = 1;
                obtainFocus(context);
//                requestData(1);
                MyApplication.filterContitions.put("alarmGrade",(String) tvImportant.getTag());
                alarmFragment.requestAlarmData(tvImportant.getText().toString());
                dismiss();
            }
        });
        tvGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelect == 2){
                    dismiss();
                    return;
                }
                currentSelect = 2;
                obtainFocus(context);
                MyApplication.filterContitions.put("alarmGrade",(String) tvGeneral.getTag());
                alarmFragment.requestAlarmData(tvGeneral.getText().toString());
                dismiss();
            }
        });
        int[] location = new int[2];
        locationView.getLocationInWindow(location);

        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        设置popupWindow 的高度，减去坐标的高度和自身的高度
        setHeight(UiUtils.getWindowWidthAndHeight()[1] - location[1] - locationView.getHeight());
//        设置背景的透明度
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7F000000")));
//      防止点击  UiUtils.dip2px(50) 一般警告时取消掉 弹框
        final int outLocation = location[1] + locationView.getHeight() + UiUtils.dip2px(50);
        System.out.println("outLocation :" + outLocation);//354
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                获取的是距离屏幕的大小
                float rawY = event.getRawY();

                if (event.getAction() == MotionEvent.ACTION_UP){
                    if(rawY > outLocation){
                        dismiss();
                    }
                }
                System.out.println("rawY :" + rawY + "MotionEvent :" + event.getAction());
                return true;// 如果返回false 那么就收不到up事件
            }
        });
        obtainFocus(context);
    }

    private void obtainFocus(Context context) {
        if (currentSelect == 0){
            System.out.println(" currentSelect :" + currentSelect);
            tvUrgent.setTextColor(context.getResources().getColor(R.color.text_press));
            tvImportant.setTextColor(context.getResources().getColor(R.color.black));
            tvGeneral.setTextColor(context.getResources().getColor(R.color.black));
            tvRealTime.setTextColor(context.getResources().getColor(R.color.black));
            tvRealTime.setBackgroundResource(R.drawable.pop_normal);
            tvUrgent.setBackgroundResource(R.drawable.pop_select);
            tvImportant.setBackgroundResource(R.drawable.pop_normal);
            tvGeneral.setBackgroundResource(R.drawable.pop_normal);
        }else if(currentSelect == 1){
            tvUrgent.setTextColor(context.getResources().getColor(R.color.black));
            tvImportant.setTextColor(context.getResources().getColor(R.color.text_press));
            tvGeneral.setTextColor(context.getResources().getColor(R.color.black));
            tvRealTime.setTextColor(context.getResources().getColor(R.color.black));
            tvRealTime.setBackgroundResource(R.drawable.pop_normal);
            tvUrgent.setBackgroundResource(R.drawable.pop_normal);
            tvImportant.setBackgroundResource(R.drawable.pop_select);
            tvGeneral.setBackgroundResource(R.drawable.pop_normal);
        }else if(currentSelect == 2){
            tvUrgent.setTextColor(context.getResources().getColor(R.color.black));
            tvImportant.setTextColor(context.getResources().getColor(R.color.black));
            tvGeneral.setTextColor(context.getResources().getColor(R.color.text_press));
            tvRealTime.setTextColor(context.getResources().getColor(R.color.black));
            tvRealTime.setBackgroundResource(R.drawable.pop_normal);
            tvUrgent.setBackgroundResource(R.drawable.pop_normal);
            tvImportant.setBackgroundResource(R.drawable.pop_normal);
            tvGeneral.setBackgroundResource(R.drawable.pop_select);
        }else if(currentSelect == 3){
            tvUrgent.setTextColor(context.getResources().getColor(R.color.black));
            tvImportant.setTextColor(context.getResources().getColor(R.color.black));
            tvGeneral.setTextColor(context.getResources().getColor(R.color.black));
            tvRealTime.setTextColor(context.getResources().getColor(R.color.text_press));

            tvUrgent.setBackgroundResource(R.drawable.pop_normal);
            tvImportant.setBackgroundResource(R.drawable.pop_normal);
            tvGeneral.setBackgroundResource(R.drawable.pop_normal);
            tvRealTime.setBackgroundResource(R.drawable.pop_select);
        }
    }
}
