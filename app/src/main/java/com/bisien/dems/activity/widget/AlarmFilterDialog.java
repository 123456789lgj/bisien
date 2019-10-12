package com.bisien.dems.activity.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.fragment.AlarmFragment;
import com.bisien.dems.activity.global.GlobalDataInit;
import com.bisien.dems.activity.utils.DialogLoading;
import com.bisien.dems.activity.utils.UiUtils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AlarmFilterDialog extends Dialog implements View.OnClickListener {
    private ImageView arrowDownStation;
    private ImageView arrowDownHouse;
    private TextView tvStation;
    private TextView tvHouse;
    private MyFlowLayout myFlowLayoutCategory;
    private MyFlowLayout myFlowLayoutDevice;
    private LinearLayout measurePopupWidth;
    private Activity activity;
    private LinearLayout tvHouseLocation;
    private Button btComplete;
    private Button btReset;
    AlarmFragment alarmFragment;
    private FilterPopupWindow housePopupWindow;
    private FilterPopupWindow stationPopupWindow;
    private TextView tvStartTime;
    private TextView tvEndTime;

    public AlarmFilterDialog(@NonNull Activity context, AlarmFragment alarmFragment) {
//        指定dialog的样式
        super(context, R.style.AlarmFilterDialogManyMore);
        this.activity = context;
        this.alarmFragment = alarmFragment;
//        指定dialog的布局
        setContentView(R.layout.dialog_alarm_filter);
        initView();
//        获取dialog所在的窗口对象
        Window window = getWindow();
//        获取当前窗口的属性（也就是布局参数）
        WindowManager.LayoutParams attributes = window.getAttributes();
//        在布局文件中配置match_parent 不管用
//        必须在
        int width = UiUtils.getWindowWidthAndHeight()[0];
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.width = (int) (width * 0.56);
        attributes.gravity = Gravity.RIGHT;
        window.setAttributes(attributes);


    }

    //2019-09-23+09:54:21
    //{"stationId":-1,"houseId":-1,"equipmentId":-1,"alarmGrade":1,"startTime":"2010-01-01+00:00:00","endTime":"2019-09-11+14:02:33","equipmenttype":"-1"}
    private int currentType = -1;

    private View.OnClickListener categroyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = String.valueOf(v.getTag());
            currentType = Integer.parseInt(msg);
            System.out.println("equipmenttype :" + currentType);
            clearCategroy();
            TextView textView = (TextView) v;
            textView.setBackgroundResource(R.drawable.pop_select);
            textView.setTextColor(UiUtils.getColor(R.color.text_press));
            MyApplication.filterContitions.put("equipmenttype", currentType + "");
//  根据设备类型展示对应的设备
            deviceByType();
        }
    };

    private void deviceByType() {
//        int childCount = myFlowLayoutDevice.getChildCount();
        clearSelectStatus();//先清空选中的状态，然后再进行添加
        myFlowLayoutDevice.removeAllViews();

        for (int i = 0; i < textViewList.size(); i++) {
            TextView childAt = textViewList.get(i);
            GlobalDataBean.DataBean.HousesBean.EquipmentsBean tag = (GlobalDataBean.DataBean.HousesBean.EquipmentsBean) childAt.getTag();
            int category = tag.getCategory();
            if (category == currentType) {
                myFlowLayoutDevice.addView(childAt);
            }
        }
//        clearSelectStatus();
    }

    //           清空选中的状态
    private void clearSelectStatus() {
        int childCount = myFlowLayoutDevice.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView childAt = (TextView) myFlowLayoutDevice.getChildAt(i);
            childAt.setBackgroundResource(R.drawable.shap_dialog_content_bg);
            childAt.setTextColor(UiUtils.getColor(R.color.filter_condations));
        }
    }

    private View.OnClickListener deviceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//           清空选中的状态
            clearSelectStatus();
            TextView textView = (TextView) v;
            textView.setBackgroundResource(R.drawable.pop_select);
            textView.setTextColor(UiUtils.getColor(R.color.text_press));
            GlobalDataBean.DataBean.HousesBean.EquipmentsBean tag = (GlobalDataBean.DataBean.HousesBean.EquipmentsBean) v.getTag();
            MyApplication.filterContitions.put("equipmentId", tag.getId() + "");
        }
    };
    private ArrayList<TextView> textViewList = new ArrayList<>();

    private void initView() {
        arrowDownStation = findViewById(R.id.arrowDownStation);
        measurePopupWidth = findViewById(R.id.measurePopupWidth);
        tvHouseLocation = findViewById(R.id.tvHouseLocation);
        arrowDownStation.setOnClickListener(this);
        tvStation = findViewById(R.id.tvStation);
        arrowDownHouse = findViewById(R.id.arrowDownHouse);
        arrowDownHouse.setOnClickListener(this);
        tvHouse = findViewById(R.id.tvHouse);
        myFlowLayoutCategory = findViewById(R.id.myFlowLayoutCategory);
        myFlowLayoutDevice = findViewById(R.id.myFlowLayoutDevice);
        btComplete = findViewById(R.id.btComplete);
        btReset = findViewById(R.id.btReset);
        btComplete.setOnClickListener(this);
        btReset.setOnClickListener(this);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        if (MyApplication.equipments.size() == 0 || MyApplication.equipmentTypeName.size() == 0) {
//            UiUtils.getMainThreadHandler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    DialogLoading.getInstance().showLoading(activity, "加载中...");
//                    GlobalDataInit globalDataInit = new GlobalDataInit();
//                    globalDataInit.setListener(new GlobalDataInit.DataListener() {
//                        @Override
//                        public void complete() {
//                            UiUtils.getMainThreadHandler().post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    DialogLoading.getInstance().dismissLoading();
//                                    showInitView();
//                                }
//                            });
//                        }
//                    });
//                    GlobalDataInit.initGloalData();
//                }
//            },200);
        }else {
            showInitView();
        }
    }
    public void showInitView(){
        //        -----------------------------------------------------------------------------------------------------
        HashMap<Integer, String> equipmentTypeName = MyApplication.equipmentTypeName;
        System.out.println("equipmentTypeName 111111111:" + equipmentTypeName.size());
        for (Integer key : equipmentTypeName.keySet()) {
            TextView textView = new TextView(UiUtils.getContext());
            textView.setTag(key);
            textView.setTextColor(UiUtils.getColor(R.color.filter_condations));
            textView.setText(equipmentTypeName.get(key));
            textView.setTextSize(14);
            textView.setPadding(UiUtils.dip2px(15), UiUtils.dip2px(5), UiUtils.dip2px(15), UiUtils.dip2px(5));
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(categroyListener);
//            所有的类型
            textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);

            myFlowLayoutCategory.addView(textView);
        }
        //        ---------------------------------------------device--------------------------------------------------------
//        设备类型对应的具体哪些设备
        HashMap<Integer, List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean>> equipments = MyApplication.equipments;
        for (Integer key : equipments.keySet()) {
            List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = equipments.get(key);
            System.out.println("key :" + key + "  List :" + equipmentsBeans.size());
            for (int i = 0; i < equipmentsBeans.size(); i++) {
                TextView textView = new TextView(UiUtils.getContext());
//                String tag = key + ":"+ equipmentsBeans.get(i).getId();
//                equipmentsBeans.get(i).getc
                textView.setTag(equipmentsBeans.get(i));
                textView.setTextColor(UiUtils.getColor(R.color.filter_condations));
                textView.setText(equipmentsBeans.get(i).getName());
                textView.setTextSize(14);
                textView.setPadding(UiUtils.dip2px(15), UiUtils.dip2px(5), UiUtils.dip2px(15), UiUtils.dip2px(5));
                textView.setGravity(Gravity.CENTER);
                textView.setOnClickListener(deviceListener);
//            所有的类型
                textView.setBackgroundResource(R.drawable.shap_dialog_content_bg);
                textViewList.add(textView);
                myFlowLayoutDevice.addView(textView);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrowDownStation:
                if (stationPopupWindow != null && stationPopupWindow.isShowing()) {
                    stationPopupWindow.dismiss();
                    return;
                }
                ObjectAnimator.ofFloat(arrowDownStation, "rotation", 0f, 180f).setDuration(100).start();
                showPopupWindowStation();
                break;
            case R.id.arrowDownHouse:
                if (housePopupWindow != null && housePopupWindow.isShowing()) {
                    housePopupWindow.dismiss();
                    return;
                }
                ObjectAnimator.ofFloat(arrowDownHouse, "rotation", 0f, 180f).setDuration(100).start();
                showPopupWindowHouse();
                break;
            case R.id.btComplete:
//              请求筛选过后的数据
                dismiss();
                alarmFragment.requestAlarmData("");
                break;
            case R.id.btReset:
                clearAllSelectStatus();
                break;
            case R.id.tvStartTime:
                showTimeDialog(true);
                break;
            case R.id.tvEndTime:
                showTimeDialog(false);
                break;
        }
    }

    private void showTimeDialog(final boolean startTime) {
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (date != null) {
                    String time = UiUtils.getTime(date);
                    String[] s = time.split(" ");
                    String formatTime = s[0] + "+" + s[1];
                    if (startTime) {
                        tvStartTime.setText(time);
                        MyApplication.filterContitions.put("startTime", formatTime);
                    } else {
                        tvEndTime.setText(time);
                        MyApplication.filterContitions.put("endTime", formatTime);
                    }
                    System.out.println("formatTime :" + formatTime);

                }
            }
        }).setType(new boolean[]{true, true, true, true, true, true})
                .setSubmitColor(UiUtils.getColor(R.color.text_press))
                .setCancelColor(UiUtils.getColor(R.color.text_press))
//                .setContentTextSize(14)
                .isDialog(true)
                .setGravity(Gravity.BOTTOM)
                .build();
        pvTime.show();
    }

    public void clearAllSelectStatus() {
        HashMap<String, String> filterContitions = MyApplication.filterContitions;
        for (String key : filterContitions.keySet()) {
            if (!"alarmGrade".equals(key)) {
                filterContitions.put(key, "-1");//重置所有的筛选条件
            }
            System.out.println("key :" + key + "value :" + filterContitions.get(key));
        }
//        上面这个是清空所有的选择-----------------------
        tvStation.setText("全部");
        tvStation.setTag(null);
        tvStation.setTextColor(UiUtils.getColor(R.color.filter_condations));
        tvHouse.setText("全部");
        tvHouse.setTextColor(UiUtils.getColor(R.color.filter_condations));
        tvStartTime.setText(null);
        tvEndTime.setText(null);
//        清空所有的类别
        clearCategroy();
//        清空之前已经选择的设备
        clearSelectStatus();
//      清空设备并且重新添加
        clearDevice();
    }

    private void clearDevice() {
        myFlowLayoutDevice.removeAllViews();
        for (int i = 0; i < textViewList.size(); i++) {
            myFlowLayoutDevice.addView(textViewList.get(i));
        }
    }

    private void clearCategroy() {//清空所有的类别选择
        int childCount = myFlowLayoutCategory.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView childAt = (TextView) myFlowLayoutCategory.getChildAt(i);
            childAt.setBackgroundResource(R.drawable.shap_dialog_content_bg);
            childAt.setTextColor(UiUtils.getColor(R.color.filter_condations));
        }
    }

    //true表示站信息
    private void showPopupWindowStation() {
        stationPopupWindow = new FilterPopupWindow(measurePopupWidth, activity, tvStation, tvHouse, true);
        PopupWindowCompat.showAsDropDown(stationPopupWindow, measurePopupWidth, 0, 0, Gravity.START);
        stationPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ObjectAnimator.ofFloat(arrowDownStation, "rotation", 180f, 0).setDuration(100).start();
            }
        });

    }

    //false表示房间信息
    private void showPopupWindowHouse() {
        housePopupWindow = new FilterPopupWindow(measurePopupWidth, activity, tvStation, tvHouse, false);
        PopupWindowCompat.showAsDropDown(housePopupWindow, tvHouseLocation, 0, 0, Gravity.START);
        housePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ObjectAnimator.ofFloat(arrowDownHouse, "rotation", 180f, 0).setDuration(100).start();
            }
        });
    }

}
