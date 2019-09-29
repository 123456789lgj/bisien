package com.bisien.dems.activity.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.HouseInfoBean;
import com.bisien.dems.activity.fragment.AlarmFragment;
import com.bisien.dems.activity.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static com.bisien.dems.activity.application.MyApplication.stationMap;

public class FilterPopupWindow extends PopupWindow {
    private ArrayList<TextView> textViewsStation = new ArrayList<>();
    private ArrayList<TextView> textViewsHouse = new ArrayList<>();
    private LinearLayout linearLayout;
    TextView tvStation;
    TextView tvHouse;
    boolean isStation;

    public FilterPopupWindow(View locationView, final Activity context, TextView tvStation, TextView tvHouse, boolean isStation) {
        super(context);
        this.tvStation = tvStation;
        this.tvHouse = tvHouse;
        this.isStation = isStation;
//        设置点击外面进行消失
        setOutsideTouchable(true);
        setFocusable(true);
        ScrollView scrollView = new ScrollView(context);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
        setContentView(scrollView);

        setWidth(locationView.getWidth());
        setHeight(UiUtils.dip2px(80));
//        设置popupWindow 的高度
//        设置背景的透明度
        setBackgroundDrawable(new ColorDrawable(UiUtils.getColor(R.color.min_blue)));
//      防止点击  这个是station 的scrollview

        if (isStation) {
            HashMap<Long, String> stationMap = MyApplication.stationMap;
            for (Long key : stationMap.keySet()) {
                TextView textView = new TextView(UiUtils.getContext());
                textView.setTag(key);
                textView.setTextColor(UiUtils.getColor(R.color.filter_condations));
                textView.setText(stationMap.get(key));
                textView.setTextSize(14);
                textView.setPadding(UiUtils.dip2px(8), UiUtils.dip2px(5), UiUtils.dip2px(15), UiUtils.dip2px(5));
                textView.setBottom(UiUtils.dip2px(5));
                textView.setGravity(Gravity.LEFT);
                textView.setOnClickListener(stationListener);
//            所有的类型
                textView.setBackgroundColor(UiUtils.getColor(R.color.min_blue));
                textViewsStation.add(textView);
                linearLayout.addView(textView);
            }
        } else {
//        初始化房间信息
            HashMap<Long, ArrayList<HouseInfoBean>> houseMap = MyApplication.houseMap;
            for (Long key : houseMap.keySet()) {
                ArrayList<HouseInfoBean> longStringHashMap = houseMap.get(key);
                for (int i = 0; i < longStringHashMap.size(); i++) {
                    HouseBean houseBean = new HouseBean();
                    TextView textView = new TextView(UiUtils.getContext());
                    houseBean.stationId = key + "";
                    houseBean.houseId = longStringHashMap.get(i).getHousesId() + "";
                    textView.setTag(houseBean);//这个表示局站id  ，key2 表示房间id
                    textView.setTextColor(UiUtils.getColor(R.color.filter_condations));
                    textView.setText(longStringHashMap.get(i).getHouseName());
                    textView.setTextSize(14);
                    textView.setPadding(UiUtils.dip2px(8), UiUtils.dip2px(5), UiUtils.dip2px(15), UiUtils.dip2px(5));
                    textView.setBottom(UiUtils.dip2px(5));
                    textView.setGravity(Gravity.LEFT);
                    textView.setOnClickListener(houseListener);
//            所有的类型
                    textView.setBackgroundColor(UiUtils.getColor(R.color.min_blue));
                    textViewsHouse.add(textView);
                }
            }
            //根据stationId 进行选择
            houseByStation();
        }
    }

    private View.OnClickListener stationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MyApplication.filterContitions.put("stationId", v.getTag() + "");
            TextView textView = (TextView) v;
//            tvStation 存放这个Station
            tvStation.setTag(v.getTag());
            tvStation.setText(textView.getText());
            tvStation.setTextColor(UiUtils.getColor(R.color.text_press));
            MyApplication.filterContitions.put("houseId", "-1");
            dismiss();
            tvHouse.setText("全部");
            tvHouse.setTextColor(UiUtils.getColor(R.color.filter_condations));
        }
    };
// 根据stationId 进行选择
    private void houseByStation() {
        String tag = String.valueOf(tvStation.getTag());//本来等于null 但是又转化成null的字符串形式
        System.out.println("houseByStation :" + tag);
        if (tag == null || tag.equals("null") || tag.equals("")){
            System.out.println("textViewsHouse size:" + textViewsHouse.size());

            for (int i = 0; i < textViewsHouse.size(); i++) {
                linearLayout.addView(textViewsHouse.get(i));
            }
        }else {
            linearLayout.removeAllViews();
            for (int i = 0; i < textViewsHouse.size(); i++) {
                TextView textView = textViewsHouse.get(i);
                HouseBean houseBean = (HouseBean) textView.getTag();
                if (houseBean.stationId.equals(tag)){
                    linearLayout.addView(textView);
                }
            }
        }
    }

    private View.OnClickListener houseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MyApplication.filterContitions.put("houseId", ((HouseBean)v.getTag()).houseId);
            TextView textView = (TextView) v;
            tvHouse.setText(textView.getText());
            tvHouse.setTextColor(UiUtils.getColor(R.color.text_press));
            dismiss();
        }
    };

    class HouseBean {
        public String stationId;
        public String houseId;
    }
}
