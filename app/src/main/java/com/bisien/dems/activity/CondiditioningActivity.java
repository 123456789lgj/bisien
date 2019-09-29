package com.bisien.dems.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.CondiditioningBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.bean.HouseInfoBean;
import com.bisien.dems.activity.fragment.HomeFragment;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.DeviceSelectPopupWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CondiditioningActivity extends BaseActivity implements View.OnClickListener {

    private String category;
    private TextView tvAirName;
    private TextView tvAirHouseName;
    private TextView airCompress;
    private TextView airFan;
    private TextView airPlusWet;
    private TextView airDivideWet;
    private TextView airCold;
    private RecyclerView airRecyclerView;
    private ImageView airDownArrow;
    private RecyclerView airAlarmRecyclerView;
    ArrayList<CondiditioningBean.DataBean> listOne = new ArrayList<>();
    ArrayList<CondiditioningBean.DataBean> listTwo = new ArrayList<>();
    ArrayList<CondiditioningBean.DataBean> listThree = new ArrayList<>();
    private AirAdapter myAdapter;
    private AirAlarmAdapter airAlarmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiditioning);
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
                Bean bean = new Bean();
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

    ArrayList<Bean> deviceList = new ArrayList<>();//获取所有设备的id 和 对应的房间信息

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.airDownArrow:
                DeviceSelectPopupWindow deviceSelectPopupWindow = new DeviceSelectPopupWindow(this, deviceList, UiUtils.getColor(R.color.status_conditioning));
                PopupWindowCompat.showAsDropDown(deviceSelectPopupWindow, tvAirName, 0, 0, Gravity.START);
                startAnimalArrow();
                deviceSelectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ObjectAnimator.ofFloat(airDownArrow,"rotation",180f, 0).setDuration(100).start();
                    }
                });
                break;
        }
    }
    private void startAnimalArrow() {
        ObjectAnimator.ofFloat(airDownArrow,"rotation",0f, 180f).setDuration(100).start();
    }

    @Override
    public void refreshSelectData(Bean bean) {
//        super.refreshSelectData(bean);
        tvAirName.setText(bean.name);
        tvAirHouseName.setText(bean.houseName);
        initGetDataFromService(bean.deviceId + "",category);
    }

    public static class Bean {
        public long houseId;//设备id
        public long deviceId;//设备id
        public String name;//设备名称
        public String houseName = "";//房间名称

    }

    private void initView() {
        tvAirName = findViewById(R.id.tvAirName);
        tvAirHouseName = findViewById(R.id.tvAirHouseName);
        airCompress = findViewById(R.id.airCompress);
        airFan = findViewById(R.id.airFan);
        airPlusWet = findViewById(R.id.airPlusWet);
        airDivideWet = findViewById(R.id.airDivideWet);
        airCold = findViewById(R.id.airCold);
        airRecyclerView = findViewById(R.id.airRecyclerView);
        airDownArrow = findViewById(R.id.airDownArrow);
        airAlarmRecyclerView = findViewById(R.id.airAlarmRecyclerView);

        airDownArrow.setOnClickListener(this);

        if (deviceList.size() != 0) {
            tvAirName.setText(deviceList.get(0).name);
            tvAirHouseName.setText(deviceList.get(0).houseName);
        }
//        initRecyclerData();
        if (deviceList.size() != 0) {
            initGetDataFromService(deviceList.get(0).deviceId + "", category);
        }
    }

    private void initGetDataFromService(String deviceId, String deviceType) {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
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
                System.out.println("CondiditioningActivity :onNotOk :" + msg);
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


//        上面数据已经初始化完成
        initSelectStatus();   //初始化风机加湿等状态
        dismissLoading();
        initRecyclerData();
    }

    private void initRecyclerData() {
        if (airAlarmAdapter != null &&  myAdapter!= null){
            myAdapter.notifyDataSetChanged();
            airAlarmAdapter.notifyDataSetChanged();
            return;
        }
        airRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        禁用recyclerView 滑动
        airRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        airRecyclerView.setFocusable(false);
        airRecyclerView.addItemDecoration(new AirSpaceItemDecoration(1, UiUtils.dip2px(6), true));
        myAdapter = new AirAdapter();
        airRecyclerView.setAdapter(myAdapter);

        airAlarmRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        禁用recyclerView 滑动
        airAlarmRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        airAlarmRecyclerView.setFocusable(false);

        airAlarmRecyclerView.addItemDecoration(new AirSpaceItemDecoration(3, UiUtils.dip2px(8), true));
        airAlarmAdapter = new AirAlarmAdapter();
        airAlarmRecyclerView.setAdapter(airAlarmAdapter);


    }

    public void initSelectStatus() {
        for (int i = 0; i < listOne.size(); i++) {
            CondiditioningBean.DataBean dataBean = listOne.get(i);
            double currentValue = dataBean.getCurrentValue();
            if (dataBean.getCode().equals("d_cop")) {//压缩机
                if (currentValue >= 1) {// 1 表示开启
                    setDrawable(airCompress, R.mipmap.air_compress_select);
                } else {
                    setDrawable(airCompress, R.mipmap.air_compress);
                }
//                String h = currentValue >= 1 ? setDrawable(airCompress, R.mipmap.air_compress_select) : setDrawable(airCompress, R.mipmap.air_compress);
            } else if (dataBean.getCode().equals("d_fan")) {//风机
                if (currentValue >= 1) {
                    setDrawable(airFan, R.mipmap.air_fan_select);
                } else {
                    setDrawable(airFan, R.mipmap.air_fan);
                }
            } else if (dataBean.getCode().equals("d_humi")) {//加湿
                if (currentValue >= 1) {
                    setDrawable(airPlusWet, R.mipmap.air_plus_wet_select);
                } else {
                    setDrawable(airPlusWet, R.mipmap.air_plus_wet);
                }
            } else if (dataBean.getCode().equals("d_dehumi")) {//除湿
                if (currentValue >= 1) {
                    setDrawable(airDivideWet, R.mipmap.air_divide_wet_select);
                } else {
                    setDrawable(airDivideWet, R.mipmap.air_divide_wet);
                }
            } else if (dataBean.getCode().equals("d_cold")) {//制冷
                if (currentValue >= 1) {
                    setDrawable(airCold, R.mipmap.air_cold_select);
                } else {
                    setDrawable(airCold, R.mipmap.air_cold);
                }
            }
        }
    }

    public void setDrawable(TextView textView, int drawableInt) {
        Drawable drawable = getResources().getDrawable(drawableInt);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    class AirAlarmAdapter extends RecyclerView.Adapter<AirAlarmHolder> {

        @NonNull
        @Override
        public AirAlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air_alarm, null);
            AirAlarmHolder airAlarmHolder = new AirAlarmHolder(view);
            return airAlarmHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AirAlarmHolder holder, int position) {
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

    static class AirAlarmHolder extends RecyclerView.ViewHolder {
        private ImageView ivAlarmStatus;
        private TextView ivAlarmName;

        public AirAlarmHolder(@NonNull View itemView) {
            super(itemView);
            ivAlarmStatus = itemView.findViewById(R.id.ivAlarmStatus);
            ivAlarmName = itemView.findViewById(R.id.ivAlarmName);
        }
    }

    class AirAdapter extends RecyclerView.Adapter<AirViewHolder> {

        @NonNull
        @Override
        public AirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air, null);
            AirViewHolder airViewHolder = new AirViewHolder(view);
            return airViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AirViewHolder holder, int position) {
            holder.tvSampleName.setText(listTwo.get(position).getName());
//            设置当前的值--------------------------------------和单位
            holder.tvSampleValue.setText(listTwo.get(position).getCurrentValue() + " " + listTwo.get(position).getUnit());
        }

        @Override
        public int getItemCount() {
            return listTwo.size();
        }
    }

    static class AirViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSampleName;
        private TextView tvSampleValue;

        public AirViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSampleName = itemView.findViewById(R.id.tvSampleName);
            tvSampleValue = itemView.findViewById(R.id.tvSampleValue);
        }
    }

    class AirSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public AirSpaceItemDecoration(int spanCount, int space, boolean includeEdge) {
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
