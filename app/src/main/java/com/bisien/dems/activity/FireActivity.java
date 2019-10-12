package com.bisien.dems.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
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
                    initData(equipmentsBean.getId(),"烟感",2);
                    break;
                }
            }
        }
//        6 表示警铃
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> alarmList = MyApplication.equipments.get(6);
        System.out.println("alarmList :"+alarmList.size());
        if (alarmList != null) {
            for (int i = 0; i < alarmList.size(); i++) {
                GlobalDataBean.DataBean.HousesBean.EquipmentsBean equipmentsBean = alarmList.get(i);
                String name = equipmentsBean.getName();
                System.out.println("nameDevice " + name);
                if (equipmentsBean.getName().toUpperCase().contains("警铃")) {
                    initData(equipmentsBean.getId(),"警铃",6);
                    break;
                }
            }
        }

    }
    private void initView() {
        tvName = findViewById(R.id.tvName);
        tvCurrentStatusValue = findViewById(R.id.tvCurrentStatusValue);

        tvNameAlarm = findViewById(R.id.tvNameAlarm);
        tvCurrentStatusValueAlarm = findViewById(R.id.tvCurrentStatusValueAlarm);
        alarmSwitch = findViewById(R.id.s_wAlarm);
    }


    private void initData(long deviceId, final String deviceName,int deviceType) {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/status/get_list_rdata_byequipid/" + deviceId + "/" + deviceType;  //消防的type值为2
//        String url = "http://192.168.1.145:8080/gledeye/rest/equipment/get_list";
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                dismissLoading();
                parseJson(response,deviceName);
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                System.out.println("FireActivity :onNotOk :" + msg);
            }
        });
    }

    private void parseJson(String response,String deviceName) {

        CondiditioningBean condiditioningBean = new Gson().fromJson(response, CondiditioningBean.class);
        if (condiditioningBean != null){
            List<CondiditioningBean.DataBean> data = condiditioningBean.getData();
            if (deviceName.equals("烟感")){
                for (int i = 0; i < data.size(); i++) {//烟感就只有一个信号
                    tvName.setText(data.get(i).getName());
                    double currentValue = data.get(i).getCurrentValue();
                    if (currentValue > 0){
                        tvCurrentStatusValue.setText("异常");
                    }
                    if (currentValue == 0){
                        tvCurrentStatusValue.setText("正常");
                    }
                }
            }else if(deviceName.equals("警铃")){
                for (int i = 0; i < data.size(); i++) {//烟感就只有一个信号
//                    表示警铃的开关状态，
                    if ("d_status".equals(data.get(i).getCode())){
                        tvNameAlarm.setText(data.get(i).getName());
                        double currentValue = data.get(i).getCurrentValue();
                        if (currentValue > 0){
                            tvCurrentStatusValueAlarm.setText("异常");
                            alarmSwitch.setChecked(true);
                        }
                        if (currentValue == 0){
                            tvCurrentStatusValueAlarm.setText("正常");
                        }
                    }
                }
            }

        }
    }

}
