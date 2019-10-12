package com.bisien.dems.activity.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.BaseActivity;
import com.bisien.dems.activity.CondiditioningActivity;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.HouseInfoBean;
import com.bisien.dems.activity.bean.StatusEnvBean;
import com.bisien.dems.activity.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;


public class StatusEnvPopupWindow extends PopupWindow {
    private BaseActivity activity;

    public StatusEnvPopupWindow(BaseActivity context, ArrayList<StatusEnvBean.DataBean> list, int textColor) {
        super(context);
        this.activity = context;
        setOutsideTouchable(true);
        setFocusable(true);
        View view = View.inflate(context, R.layout.device_select_popup_window, null);
        LinearLayout llComponent = view.findViewById(R.id.llComponent);
        for (int i = 0; i < list.size(); i++) {
            int category = list.get(i).getCategory();
            TextView textView = new TextView(context);
            String houseName = UiUtils.getHouseName(list.get(i).getHouseId());
            textView.setText(houseName + "-" + list.get(i).getName());
            //                        第一个参数是设备类型，第二个参数是当前选中的那个设备，第三个参数是设备对应的房间信息
            textView.setTag(category + "=" + i + "=" +houseName);
            textView.setTextColor(textColor);
            textView.setTextSize(14);
            textView.setPadding(UiUtils.dip2px(10), UiUtils.dip2px(5), UiUtils.dip2px(10), UiUtils.dip2px(5));
            textView.setOnClickListener(listener);
            llComponent.addView(textView);
        }
        setContentView(view);
//        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismiss();
            String split = (String) v.getTag();
            String[] splitStr = split.split("=");
            activity.refreshSelectData(Integer.parseInt(splitStr[0]),Integer.parseInt(splitStr[1]),splitStr[2]);
        }
    };
}
