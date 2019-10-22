package com.bisien.dems.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.ChangePasswordBean;
import com.bisien.dems.activity.bean.CondiditioningBean;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.tabfragment.BaseTabFragment;
import com.bisien.dems.activity.tabfragment.TabLayoutFragment;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FireActivity extends BaseActivity {
    private TextView tvName;
    private TextView tvNameAlarm;
    private TextView tvCurrentStatusValue;
    private TextView tvCurrentStatusValueAlarm;
    private Switch alarmSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        findTitle("消防系统");
        initView();
//        设备类型2 表示消防的信息，比如说烟感
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = MyApplication.equipments.get(2);
        if (equipmentsBeans != null) {
            for (int i = 0; i < equipmentsBeans.size(); i++) {
                GlobalDataBean.DataBean.HousesBean.EquipmentsBean equipmentsBean = equipmentsBeans.get(i);
                if (equipmentsBean.getName().toUpperCase().contains("烟")) {
                    initData(equipmentsBean.getId(), "烟感", 2);
                    break;
                }
            }
        }
//        6 表示警铃
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> alarmList = MyApplication.equipments.get(6);
        if (alarmList != null) {
            System.out.println("alarmList :" + alarmList.size());
            for (int i = 0; i < alarmList.size(); i++) {
                GlobalDataBean.DataBean.HousesBean.EquipmentsBean equipmentsBean = alarmList.get(i);
                String name = equipmentsBean.getName();
                System.out.println("nameDevice " + name);
                if (equipmentsBean.getName().toUpperCase().contains("警铃")) {
                    deviceId = equipmentsBean.getId();
                    initData(equipmentsBean.getId(), "警铃", 6);
                    break;
                }
            }
        }

    }

    private long deviceId;

    private void initView() {
        tvName = findViewById(R.id.tvName);
        tvCurrentStatusValue = findViewById(R.id.tvCurrentStatusValue);

        tvNameAlarm = findViewById(R.id.tvNameAlarm);
        tvCurrentStatusValueAlarm = findViewById(R.id.tvCurrentStatusValueAlarm);
        alarmSwitch = findViewById(R.id.s_wAlarm);

        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
//                        状态发生变化时调用
                    alarmSwitch.setSwitchTextAppearance(FireActivity.this, R.style.s_true);
                    tvCurrentStatusValueAlarm.setText("开启");
                    tvCurrentStatusValueAlarm.setTextColor(UiUtils.getColor(R.color.em_alarm));
                } else {
                    System.out.println("onCheckedChanged -------------------------- isChecked false");
                    alarmSwitch.setSwitchTextAppearance(FireActivity.this, R.style.s_false);
                    tvCurrentStatusValueAlarm.setText("关闭");
                    tvCurrentStatusValueAlarm.setTextColor(UiUtils.getColor(R.color.count_green));
                }
            }
        });
        alarmSwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
//                        再次进行事件的相应
                    case MotionEvent.ACTION_DOWN:
                        if (alarmSwitch.isChecked()) {
//                            UiUtils.toast("当前处于开启状态");
//                            才可以进行关闭
                            closeAlarm();
                        } else {
//                            如果当前处于关闭状态，那么警铃是无法关闭的
                            UiUtils.toast("警铃当处于关闭状态，无法通过APP进行开启");
                            return true;
                        }
                        return true;
                }
                return false;
            }
        });
//        alarmSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeAlarm();
//            }
//        });
    }

    public void closeAlarm() {
        showLoading("关闭中...");
        CondiditioningBean.DataBean dataBean = (CondiditioningBean.DataBean) alarmSwitch.getTag();
        HashMap<String, String> paramsHashMap = new HashMap<>();
//        需要传递通道号
        int channelNo = 0;
        if (alarmSwitchData != null) {
            channelNo = alarmSwitchData.getChannelNo();
        }else {
            channelNo = dataBean.getChannelNo();
        }
        paramsHashMap.put("channelNo", channelNo + "");
//        具体的状态名称
        String name = dataBean.getName();
        paramsHashMap.put("name", name + "");
        paramsHashMap.put("equipmentId", deviceId + "");
        paramsHashMap.put("parameter", 1 + "");
        paramsHashMap.put("operator", MyApplication.userName + "");
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//
        String url = GlobalConstants.getUrlFirst() + "rest/status/control";
        myHttpUtils.getDataFromServiceByPostByJson(url, paramsHashMap, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                dismissLoading();
                ChangePasswordBean changePasswordBean = new Gson().fromJson(response, ChangePasswordBean.class);
                if (changePasswordBean != null) {
                    if ("0".equals(changePasswordBean.getCode())) {
                        UiUtils.toast("关闭成功");
                        alarmSwitch.setChecked(false);
                    } else if ("-1".equals(changePasswordBean.getCode())) {
                        alarmSwitch.setChecked(true);
                        UiUtils.toast("关闭失败");
                    }
                }
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                alarmSwitch.setChecked(false);
                System.out.println("FireActivity :onNotOk :" + msg);
            }
        });
    }

    private void initData(long deviceId, final String deviceName, int deviceType) {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/status/get_list_rdata_byequipid/" + deviceId + "/" + deviceType;  //消防的type值为2
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                dismissLoading();
                parseJson(response, deviceName);
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                System.out.println("FireActivity :onNotOk :" + msg);
            }
        });
    }

    public CondiditioningBean.DataBean alarmSwitchData;

    private void parseJson(String response, String deviceName) {
        CondiditioningBean condiditioningBean = new Gson().fromJson(response, CondiditioningBean.class);
        if (condiditioningBean != null) {
            List<CondiditioningBean.DataBean> data = condiditioningBean.getData();
            if (deviceName.equals("烟感")) {
                for (int i = 0; i < data.size(); i++) {//烟感就只有一个信号
                    tvName.setText(data.get(i).getName());
                    double currentValue = data.get(i).getCurrentValue();
//                    烟感的状态为正常或告警
                    if (currentValue > 0) {
                        tvCurrentStatusValue.setText("告警");
                        tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.count_red));
                    }
                    if (currentValue == 0) {
                        tvCurrentStatusValue.setText("正常");
                        tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.count_green));
                    }
                }
            } else if (deviceName.equals("警铃")) {
                for (int i = 0; i < data.size(); i++) {//烟感就只有一个信号
//                    表示警铃的开关状态，
                    if ("d_status".equals(data.get(i).getCode())) {
                        tvNameAlarm.setText(data.get(i).getName());
                        double currentValue = data.get(i).getCurrentValue();
                        alarmSwitch.setTag(data.get(i));
                        if (currentValue > 0) {
                            tvCurrentStatusValueAlarm.setText("开启");
                            tvCurrentStatusValueAlarm.setTextColor(UiUtils.getColor(R.color.count_red));
                            alarmSwitch.setSwitchTextAppearance(FireActivity.this, R.style.s_true);
                            alarmSwitch.setChecked(true);
                        }
                        if (currentValue == 0) {
                            tvCurrentStatusValueAlarm.setText("关闭");
                            alarmSwitch.setSwitchTextAppearance(FireActivity.this, R.style.s_false);
                            tvCurrentStatusValueAlarm.setTextColor(UiUtils.getColor(R.color.count_green));

                        }
                    } else if ("c_switch".equals(data.get(i).getCode())) {
                        alarmSwitchData = data.get(i);
                    }
                }
            }

        }
    }

}
