package com.bisien.dems.activity.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.BaseActivity;
import com.bisien.dems.activity.CondiditioningActivity;
import com.bisien.dems.activity.utils.UiUtils;

import java.util.ArrayList;


public class DeviceSelectPopupWindow extends PopupWindow {
    private BaseActivity activity;
    public DeviceSelectPopupWindow(BaseActivity context, ArrayList<CondiditioningActivity.Bean> list, int textColor) {
        super(context);
        this.activity = context;
        setOutsideTouchable(true);
        setFocusable(true);
        View view = View.inflate(context, R.layout.device_select_popup_window, null);
        LinearLayout llComponent = view.findViewById(R.id.llComponent);
        for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(list.get(i).houseName + "-" + list.get(i).name);
            textView.setTextColor(textColor);
            textView.setTextSize(14);
            textView.setTag(list.get(i));
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setPadding(UiUtils.dip2px(10),UiUtils.dip2px(5),UiUtils.dip2px(10),UiUtils.dip2px(5));
//            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(listener);
            llComponent.addView(textView);
        }
        setContentView(view);
//        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

    }
    private View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            dismiss();
            CondiditioningActivity.Bean dataBean = (CondiditioningActivity.Bean) v.getTag();
            activity.refreshSelectData(dataBean);
        }
    };
}
