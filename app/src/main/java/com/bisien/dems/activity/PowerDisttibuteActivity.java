package com.bisien.dems.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.CondiditioningBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.bean.HouseInfoBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.DeviceSelectPopupWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PowerDisttibuteActivity extends BaseActivity implements View.OnClickListener {
    private String category;
    private ArrayList<CondiditioningActivity.Bean> pduList = new ArrayList<>();//获取所有设备的id 和 对应的房间信息
    private ArrayList<CondiditioningActivity.Bean> electricList = new ArrayList<>();//获取所有设备的id 和 对应的房间信息
    private TextView tvPduName;
    private TextView tvPduHouseName;
    private ImageView pduDownArrow;
    private RecyclerView pduRecyclerView;
    private RecyclerView electricRecyclerView;
    private TextView tvElectricName;
    private TextView tvElectricHouseName;
    private RelativeLayout rlPdu;
    private PowerDisSpaceItemDecoration powerDisSpaceItemDecoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_disttibute);
        findTitle("状态");
        Intent intent = getIntent();
        if (intent != null) {
            category = intent.getStringExtra("category");
            System.out.println("category :" + category);
        }
//        设备类型对应的那些设备，配电，包含 pdu 和 智能电表
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = MyApplication.equipments.get(Integer.parseInt(category));
        if (equipmentsBeans != null) {
            for (int i = 0; i < equipmentsBeans.size(); i++) {
                CondiditioningActivity.Bean bean = new CondiditioningActivity.Bean();
                System.out.println(" 设备名称 ：" + equipmentsBeans.get(i).getName() + " category :" + equipmentsBeans.get(i).getCategory());
                bean.deviceId = equipmentsBeans.get(i).getId();
                bean.name = equipmentsBeans.get(i).getName();
                bean.houseId = equipmentsBeans.get(i).getHouseId();
                System.out.println(" id : " + bean.deviceId + " name : " + bean.name);
//
//                key 为局站id， 值为局站对应的房间信息
                HashMap<Long, ArrayList<HouseInfoBean>> houseMap = MyApplication.houseMap;
//                遍历所有的value , 下面这段代码是根据设备的id 找到设备对应的房间信息进行显示
                for (ArrayList<HouseInfoBean> houseInfoBean : houseMap.values()) {
                    for (int j = 0; j < houseInfoBean.size(); j++) {
                        long housesId = houseInfoBean.get(j).getHousesId();
                        System.out.println("housesid :" + housesId);
                        if (bean.houseId == housesId) {
                            bean.houseName = houseInfoBean.get(j).getHouseName();
                            System.out.println("houseName :" + houseInfoBean.get(j).getHouseName());
                            break;
                        }
                    }
                }
//                区分当前设备是pdu 还是只能电表
                if (bean.name != null && bean.name.toUpperCase().contains("PDU")) {
                    pduList.add(bean);
                } else {
                    electricList.add(bean);
                }
            }
        }
        initView();

    }

    private void initView() {
        tvPduName = findViewById(R.id.tvPduName);
        tvPduHouseName = findViewById(R.id.tvPduHouseName);
        pduDownArrow = findViewById(R.id.pduDownArrow);
        pduRecyclerView = findViewById(R.id.pduRecyclerView);
        pduDownArrow.setOnClickListener(this);

        tvElectricName = findViewById(R.id.tvElectricName);
        tvElectricHouseName = findViewById(R.id.tvElectricHouseName);
        rlPdu = findViewById(R.id.rlPdu);
        electricRecyclerView = findViewById(R.id.electricRecyclerView);

        //        先是智能电表进行网络请求，因为智能只有一个
        System.out.println("electricList :" + electricList.size() + "pduList " + pduList.size());

        if (electricList.size() > 0) {
            String name = electricList.get(0).name;
            tvElectricName.setText(electricList.get(0).name);
            tvElectricHouseName.setText(electricList.get(0).houseName);
            initGetDataFromService(electricList.get(0).deviceId + "", category);
        } else {
            if (pduList.size() > 0) {  //
                isPdu = true;//接下来就请求pdu 的信息
//          这个表示是智能电表，默认取第一个进行访问
                tvPduName.setText(pduList.get(0).name);
                tvPduHouseName.setText(pduList.get(0).houseName);
                rlPdu.setVisibility(View.VISIBLE);
                pduRecyclerView.setVisibility(View.VISIBLE);
                initGetDataFromService(pduList.get(0).deviceId + "", category);
            }
        }

    }

    boolean isPdu = false;

    @Override
    public void refreshSelectData(CondiditioningActivity.Bean bean) {
//        super.refreshSelectData(bean);
        isPdu = true;
        tvPduName.setText(bean.name);
        tvPduHouseName.setText(bean.houseName);
        initGetDataFromService(bean.deviceId + "", category);
    }


    private void initGetDataFromService(String deviceId, String deviceType) {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        System.out.println("deviceId :" + deviceId + " deviceType :" + deviceType);
        String url = GlobalConstants.getUrlFirst() + "rest/status/get_list_rdata_byequipid/" + deviceId + "/" + deviceType;
//        String url = "http://192.168.1.145:8080/gledeye/rest/equipment/get_list";
        showLoading("加载中...");

        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                parseJson(response);
            }

            @Override
            public void onNotOk(String msg) {
                System.out.println("PowerDisttibuteActivity :onNotOk :" + msg);
                dismissLoading();
            }
        });
    }

    private void parseJson(String response) {
        CondiditioningBean condiditioningBean = new Gson().fromJson(response, CondiditioningBean.class);
        List<CondiditioningBean.DataBean> data = condiditioningBean.getData();
        ArrayList<CondiditioningBean.DataBean> listTwo = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            CondiditioningBean.DataBean dataBean = data.get(i);
            if (dataBean.isVisible()) {//需要展示的信息
                int channelType = dataBean.getChannelType();
                if (channelType == 1) {// channelType 等于0 的话，表示数字量
//                    Object code = dataBean.getCode();
//                    if (code != null && !"".equals(code)) {
////                        listOne.add(dataBean);
//                    } else {
////                        listThree.add(dataBean);
//                    }
                } else {// channelType=2 的话，表示模拟量
                    listTwo.add(dataBean);
                }
            }
        }

        if (isPdu) {
//            这个表示是pdu进行初始化
            initPduRecyclerData(listTwo);
        } else {
            initElectricRecyclerData(listTwo);
        }
    }

    private void initElectricRecyclerData(ArrayList<CondiditioningBean.DataBean> listTwo) {

        electricRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        禁用recyclerView 滑动
        electricRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        electricRecyclerView.setFocusable(false);
        electricRecyclerView.addItemDecoration(new PowerDisSpaceItemDecoration(1, UiUtils.dip2px(6), true));
        PowerDisAdapter powerDisAdapter = new PowerDisAdapter(listTwo);
        electricRecyclerView.setAdapter(powerDisAdapter);

        //        但是pdu存在多个
        if (pduList.size() > 0) {
            isPdu = true;//接下来就请求pdu 的信息
//          这个表示是智能电表，默认取第一个进行访问
            tvPduName.setText(pduList.get(0).name);
            tvPduHouseName.setText(pduList.get(0).houseName);
            rlPdu.setVisibility(View.VISIBLE);
            pduRecyclerView.setVisibility(View.VISIBLE);
            initGetDataFromService(pduList.get(0).deviceId + "", category);
        } else {
            dismissLoading();
        }


    }

    private void initPduRecyclerData(ArrayList<CondiditioningBean.DataBean> listTwo) {
        dismissLoading();
        pduRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        禁用recyclerView 滑动
        pduRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        pduRecyclerView.setFocusable(false);
        if (powerDisSpaceItemDecoration == null){
            powerDisSpaceItemDecoration = new PowerDisSpaceItemDecoration(1, UiUtils.dip2px(6), true);
            pduRecyclerView.addItemDecoration(powerDisSpaceItemDecoration);
        }
        PowerDisAdapter powerDisAdapter = new PowerDisAdapter(listTwo);
        pduRecyclerView.setAdapter(powerDisAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pduDownArrow:
                DeviceSelectPopupWindow deviceSelectPopupWindow = new DeviceSelectPopupWindow(this, pduList, UiUtils.getColor(R.color.status_power));
                PopupWindowCompat.showAsDropDown(deviceSelectPopupWindow, tvPduName, 0, 0, Gravity.START);
                ObjectAnimator.ofFloat(pduDownArrow, "rotation", 0f, 180f).setDuration(100).start();
                deviceSelectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ObjectAnimator.ofFloat(pduDownArrow, "rotation", 180f, 0).setDuration(100).start();
                    }
                });
                break;
        }
    }

    class PowerDisAdapter extends RecyclerView.Adapter<PowerDisViewHolder> {
        private ArrayList<CondiditioningBean.DataBean> listTwo;

        public PowerDisAdapter(ArrayList<CondiditioningBean.DataBean> listTwo) {
            this.listTwo = listTwo;
        }

        @NonNull
        @Override
        public PowerDisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air, null);
            PowerDisViewHolder powerDisViewHolder = new PowerDisViewHolder(view);
            return powerDisViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PowerDisViewHolder holder, int position) {
            holder.tvSampleName.setText(listTwo.get(position).getName());
//            设置当前的值--------------------------------------和单位
            holder.tvSampleValue.setText(listTwo.get(position).getCurrentValue() + " " + listTwo.get(position).getUnit());
        }

        @Override
        public int getItemCount() {
            return listTwo.size();
        }
    }

    static class PowerDisViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSampleName;
        private TextView tvSampleValue;

        public PowerDisViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSampleName = itemView.findViewById(R.id.tvSampleName);
            tvSampleValue = itemView.findViewById(R.id.tvSampleValue);
        }
    }

    class PowerDisSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public PowerDisSpaceItemDecoration(int spanCount, int space, boolean includeEdge) {
            this.spacing = space;
            this.spanCount = spanCount;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //这里是关键，需要根据你有几列来判断
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            //设置外围边框
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing) 16
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                //不设置外围边框
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
