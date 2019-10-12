package com.bisien.dems.activity.global;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bisien.dems.activity.SplashActivity;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.EquipmentTypeBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.bean.HouseInfoBean;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlobalDataInit {
    public static String TAG = GlobalDataInit.class + " lgj";

    public void setListener(DataListener dataListener){
        this.dataListener = dataListener;
    }
    public static DataListener dataListener;
    public interface DataListener{
        void complete();
    }
    public static void initGloalData() {
        new Thread(){
            @Override
            public void run() {
//                System.out.println("stationMap 1:" + MyApplication.stationMap.size());
                MyHttpUtils myHttpUtils = new MyHttpUtils();
                String url = GlobalConstants.getUrlFirst() + "rest/station/get_list";


                myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
                    @SuppressLint("UseSparseArrays")
                    @Override
                    public void onOk(String response) {
                        Log.i(TAG, "onOk :" + "response :" + response);
                        GlobalDataBean globalDataBean = new Gson().fromJson(response, GlobalDataBean.class);
                        List<GlobalDataBean.DataBean> data = globalDataBean.getData();

                        if (data != null) {
                            for (int i = 0; i < data.size(); i++) {
                                long stationId = data.get(i).getId();
                                String stationName = data.get(i).getName();
//                        存放的是局站id和局站id对应的局站名称
                                MyApplication.stationMap.put(stationId, stationName);
                                List<GlobalDataBean.DataBean.HousesBean> houses = data.get(i).getHouses();
                                for (int j = 0; j < houses.size(); j++) {
//                          key 存放的是局站id，值为该局站id对应的 局站房间
                                    long housesId = houses.get(j).getId();
                                    String houseName = houses.get(j).getName();
//                                  存放的是局站id 对应的机房id和机房名称
                                    if(MyApplication.houseMap.get(stationId) != null){
                                        ArrayList<HouseInfoBean> houseInfoBeans = MyApplication.houseMap.get(stationId);
                                        houseInfoBeans.add(new HouseInfoBean(housesId,houseName));
                                        MyApplication.houseMap.put(stationId,houseInfoBeans);
                                    }else {
                                        ArrayList<HouseInfoBean> houseInfoBeans = new ArrayList<>();
                                        houseInfoBeans.add(new HouseInfoBean(housesId,houseName));
                                        MyApplication.houseMap.put(stationId,houseInfoBeans);
                                    }

//                                    下面这个遍历是机房下的所有设备-------------------------------------
                                    List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipments = houses.get(j).getEquipments();
                                    for (int s = 0; s < equipments.size(); s++) {
                                        int category = equipments.get(s).getCategory();
//                                String name = equipments.get(s).getName();
                                        if (MyApplication.equipments.get(category) != null){
                                            List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> listEquipment = MyApplication.equipments.get(category);
                                            listEquipment.add(equipments.get(s));
                                            MyApplication.equipments.put(category,listEquipment);
                                        }else {
                                            ArrayList<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = new ArrayList<>();
                                            equipments.add(equipments.get(s));
                                            MyApplication.equipments.put(category,equipmentsBeans);
                                        }
                                    }
                                }
                            }
                            System.out.println("stationMap :" + MyApplication.stationMap.size());
                            System.out.println("houseMap :" + MyApplication.houseMap.size());
                            System.out.println("equipments :" + MyApplication.equipments.size());
                            if(dataListener != null){
                                dataListener.complete();
                            }
                        }
                    }

                    @Override
                    public void onNotOk(String msg) {
                        Log.i(TAG, "onNotOk get_list :" + msg);
                    }
                });
//        获取设备对应的设备类型
                MyHttpUtils myHttpUtils2 = new MyHttpUtils();
                String url2 = GlobalConstants.getUrlFirst() + "rest/basicdata/get_equipmenttypes";
                myHttpUtils2.getDataFromServiceByGet(url2, new MyHttpUtils.OnNetResponseListener() {
                    @SuppressLint("UseSparseArrays")
                    @Override
                    public void onOk(String response) {
                        Log.i(TAG, "onOk deviceType:" + "response :" + response);
                        EquipmentTypeBean equipmentTypeBean = new Gson().fromJson(response, EquipmentTypeBean.class);
                        if (equipmentTypeBean != null && equipmentTypeBean.getData() != null) {
                            List<EquipmentTypeBean.DataBean> data = equipmentTypeBean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                int idType = data.get(i).getId();
                                String name = data.get(i).getName();
                                MyApplication.equipmentTypeName.put(idType, name);
                            }
                            System.out.println("equipmentTypeName :" + MyApplication.equipmentTypeName.size());
                        }
                    }

                    @Override
                    public void onNotOk(String msg) {
                        Log.i(TAG, "onNotOk :" + msg);
                    }
                });
            }
        }.start();
    }
}
