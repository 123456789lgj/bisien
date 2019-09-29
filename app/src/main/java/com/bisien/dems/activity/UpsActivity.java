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

public class UpsActivity extends BaseActivity implements View.OnClickListener {
    private String category;

    private ArrayList<CondiditioningActivity.Bean> deviceList = new ArrayList<>();//获取所有设备的id 和 对应的房间信息
    private TextView tvUpsName;
    private TextView tvUpsHouseName;
    private ImageView upsDownArrow;
    private RecyclerView upsRecyclerView;
    private RecyclerView upsAlarmRecyclerView;
    ArrayList<CondiditioningBean.DataBean> listOne = new ArrayList<>();
    ArrayList<CondiditioningBean.DataBean> listTwo = new ArrayList<>();
    ArrayList<CondiditioningBean.DataBean> listThree = new ArrayList<>();
    private UpsAlarmAdapter upsAlarmAdapter;
    private UpsAdapter upsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ups);
        findTitle("状态");
        Intent intent = getIntent();
        if (intent != null) {
            category = intent.getStringExtra("category");
            System.out.println("category :" + category);
        }
        //        根据类型拿出对应的设备，设备有对应的房间id，然后根据id找出对应的房间名称
        // 类型对应的有那些设备
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = MyApplication.equipments.get(Integer.parseInt(category));
        if (equipmentsBeans != null) {
            for (int i = 0; i < equipmentsBeans.size(); i++) {
                CondiditioningActivity.Bean bean = new CondiditioningActivity.Bean();
                bean.deviceId = equipmentsBeans.get(i).getId();
                bean.name = equipmentsBeans.get(i).getName();
                bean.houseId = equipmentsBeans.get(i).getHouseId();
//                System.out.println(" id : " + bean.deviceId + " name : " + bean.name  );
                HashMap<Long, ArrayList<HouseInfoBean>> houseMap = MyApplication.houseMap;
//                遍历所有的value
                for (ArrayList<HouseInfoBean> houseInfoBean : houseMap.values()) {
                    for (int j = 0; j < houseInfoBean.size(); j++) {
                        long housesId = houseInfoBean.get(j).getHousesId();
//                        System.out.println("housesid :" + housesId);
                        if (bean.houseId == housesId) {
                            bean.houseName = houseInfoBean.get(j).getHouseName();
                            System.out.println("houseName :" + houseInfoBean.get(j).getHouseName());
                            break;
                        }
                    }
                }
                deviceList.add(bean);
            }
        }

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upsDownArrow:
                DeviceSelectPopupWindow deviceSelectPopupWindow = new DeviceSelectPopupWindow(this, deviceList, UiUtils.getColor(R.color.status_ups));
                PopupWindowCompat.showAsDropDown(deviceSelectPopupWindow, tvUpsName, 0, 0, Gravity.START);
                ObjectAnimator.ofFloat(upsDownArrow, "rotation", 0f, 180f).setDuration(100).start();
                deviceSelectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ObjectAnimator.ofFloat(upsDownArrow, "rotation", 180f, 0).setDuration(100).start();
                    }
                });
                break;
        }
    }

    @Override
    public void refreshSelectData(CondiditioningActivity.Bean bean) {
        tvUpsName.setText(bean.name);
        tvUpsHouseName.setText(bean.houseName);
        initGetDataFromService(bean.deviceId + "", category);
    }

    private void initView() {
        tvUpsName = findViewById(R.id.tvUpsName);
        tvUpsHouseName = findViewById(R.id.tvUpsHouseName);
        upsRecyclerView = findViewById(R.id.upsRecyclerView);
        upsDownArrow = findViewById(R.id.upsDownArrow);
        upsAlarmRecyclerView = findViewById(R.id.upsAlarmRecyclerView);
        upsDownArrow.setOnClickListener(this);
        if (deviceList.size() != 0) {
            tvUpsName.setText(deviceList.get(0).name);
            tvUpsHouseName.setText(deviceList.get(0).houseName);
            initGetDataFromService(deviceList.get(0).deviceId + "", category);
        }
//        initRecyclerData();

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
                System.out.println("UpsActivity :onNotOk :" + msg);
                dismissLoading();
            }
        });

    }

    private void parseJson(String response) {
        listOne.clear();
        listTwo.clear();
        listThree.clear();
        CondiditioningBean condiditioningBean = new Gson().fromJson(response, CondiditioningBean.class);
        List<CondiditioningBean.DataBean> data = condiditioningBean.getData();
        for (int i = 0; i < data.size(); i++) {
            CondiditioningBean.DataBean dataBean = data.get(i);
            if (dataBean.isVisible()) {//需要展示的信息
                int channelType = dataBean.getChannelType();
                if (channelType == 1) {// channelType 等于0 的话，表示数字量
                    Object code = dataBean.getCode();
                    if (code != null && !"".equals(code)) {//压缩机
                        listOne.add(dataBean);
                    } else {
                        listThree.add(dataBean);
                    }
                } else {// channelType=2 的话，表示模拟量
                    listTwo.add(dataBean);
                }
            }
        }
        dismissLoading();
        initRecyclerData();
    }

    private void initRecyclerData() {
// 设置GridLayout
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
        if (upsAdapter != null && upsAlarmAdapter != null) {
            upsAdapter.notifyDataSetChanged();
            upsAlarmAdapter.notifyDataSetChanged();
            return;
        }
        upsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        禁用recyclerView 滑动
        upsRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        upsRecyclerView.setFocusable(false);
        upsRecyclerView.addItemDecoration(new UpsSpaceItemDecoration(1, UiUtils.dip2px(6), true));
        upsAdapter = new UpsAdapter();
        upsRecyclerView.setAdapter(upsAdapter);

        upsAlarmRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        禁用recyclerView 滑动
        upsAlarmRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        upsAlarmRecyclerView.setFocusable(false);

        upsAlarmRecyclerView.addItemDecoration(new UpsSpaceItemDecoration(3, UiUtils.dip2px(8), true));
        upsAlarmAdapter = new UpsAlarmAdapter();
        upsAlarmRecyclerView.setAdapter(upsAlarmAdapter);


    }

    class UpsAdapter extends RecyclerView.Adapter<UpsViewHolder> {

        @NonNull
        @Override
        public UpsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air, null);
            UpsViewHolder airViewHolder = new UpsViewHolder(view);
            return airViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UpsViewHolder holder, int position) {
            holder.tvSampleName.setText(listTwo.get(position).getName());
//            设置当前的值--------------------------------------和单位
            holder.tvSampleValue.setText(listTwo.get(position).getCurrentValue() + " " + listTwo.get(position).getUnit());
        }

        @Override
        public int getItemCount() {
            return listTwo.size();
        }
    }

    static class UpsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSampleName;
        private TextView tvSampleValue;

        public UpsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSampleName = itemView.findViewById(R.id.tvSampleName);
            tvSampleValue = itemView.findViewById(R.id.tvSampleValue);
        }
    }

    class UpsAlarmAdapter extends RecyclerView.Adapter<UpsAlarmHolder> {

        @NonNull
        @Override
        public UpsAlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air_alarm, null);
            UpsAlarmHolder upsAlarmHolder = new UpsAlarmHolder(view);
            return upsAlarmHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UpsAlarmHolder holder, int position) {
            CondiditioningBean.DataBean dataBean = listThree.get(position);
            double currentValue = dataBean.getCurrentValue();
            if (currentValue >= 1) {//1代表有告警
                holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_unnormal);
            } else {
                holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_normal);
            }
            holder.ivAlarmName.setText(dataBean.getName());
        }

        @Override
        public int getItemCount() {
            return listThree.size();
        }
    }

    static class UpsAlarmHolder extends RecyclerView.ViewHolder {
        private ImageView ivAlarmStatus;
        private TextView ivAlarmName;

        public UpsAlarmHolder(@NonNull View itemView) {
            super(itemView);
            ivAlarmStatus = itemView.findViewById(R.id.ivAlarmStatus);
            ivAlarmName = itemView.findViewById(R.id.ivAlarmName);
        }
    }

    class UpsSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public UpsSpaceItemDecoration(int spanCount, int space, boolean includeEdge) {
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
