package com.bisien.dems.activity;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bisien.dems.R;
import com.bisien.dems.activity.bean.StatusEnvBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.StatusEnvPopupWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StatusEnvActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvTempertrueName;
    private TextView tvTempertrueHouseName;
    private ImageView tempertrueDownArrow;
    private RecyclerView tempertrueRecyclerView;

    private TextView tvFloodingName;
    private TextView tvFloodingHouseName;
    private ImageView floodingDownArrow;
    private RecyclerView floodingRecyclerView;

    private TextView tvSmookingName;
    private TextView tvSmookingHouseName;
    private ImageView smookingDownArrow;
    private RecyclerView smookingRecyclerView;
    private ItemDecoration tempertrueItemDecoration;
    private TempertureAdapter tempertureAdapter;
    private List<StatusEnvBean.DataBean.SignalsBean> tempertureSignals;
    private List<StatusEnvBean.DataBean.SignalsBean> floodingSignals;
    private List<StatusEnvBean.DataBean.SignalsBean> smokingSignals;
    private FloodingAlarmAdapter floodingAlarmAdapter;
    private SmookingAlarmAdapter smookingAlarmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_env);
        findTitle("环境");
        initView();
    }

    private void initView() {
        tvTempertrueName = findViewById(R.id.tvTempertrueName);
        tvTempertrueHouseName = findViewById(R.id.tvTempertrueHouseName);
        tempertrueDownArrow = findViewById(R.id.tempertrueDownArrow);
        tempertrueDownArrow.setOnClickListener(this);
        tempertrueRecyclerView = findViewById(R.id.tempertrueRecyclerView);
//        水浸
        tvFloodingName = findViewById(R.id.tvFloodingName);
        tvFloodingHouseName = findViewById(R.id.tvFloodingHouseName);
        floodingDownArrow = findViewById(R.id.floodingDownArrow);
        floodingDownArrow.setOnClickListener(this);
        floodingRecyclerView = findViewById(R.id.floodingRecyclerView);
//        烟感
        tvSmookingName = findViewById(R.id.tvSmookingName);
        tvSmookingHouseName = findViewById(R.id.tvSmookingHouseName);
        smookingDownArrow = findViewById(R.id.smookingDownArrow);
        smookingDownArrow.setOnClickListener(this);
        smookingRecyclerView = findViewById(R.id.smookingRecyclerView);

        initGetDataFromService();
    }

    private void initGetDataFromService() {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/status/get_app_byequiptype/2";
        String url2 = "http://192.168.1.118:8080/gledeye_v1.0/rest/status/get_app_byequiptype/2";
        showLoading("加载中...");

        myHttpUtils.getDataFromServiceByGet(url2, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                dismissLoading();
                parseJson(response);
            }

            @Override
            public void onNotOk(String msg) {
                System.out.println("StatusEnvActivity :onNotOk :" + msg);
                dismissLoading();
            }
        });
    }

    ArrayList<StatusEnvBean.DataBean> tempertureList = new ArrayList<StatusEnvBean.DataBean>();
    ArrayList<StatusEnvBean.DataBean> floodingList = new ArrayList<StatusEnvBean.DataBean>();
    ArrayList<StatusEnvBean.DataBean> smokingList = new ArrayList<StatusEnvBean.DataBean>();


    public void parseJson(String jsonResponse) {
        StatusEnvBean statusEnvBean = new Gson().fromJson(jsonResponse, StatusEnvBean.class);
        List<StatusEnvBean.DataBean> data = statusEnvBean.getData();
        for (int i = 0; i < data.size(); i++) {
            int category = data.get(i).getCategory();
            if (category == 4) {
                tempertureList.add(data.get(i));
            } else if (category == 5) {
                floodingList.add(data.get(i));
            } else if (category == 2) {
                smokingList.add(data.get(i));
            }
        }
        if (tempertureList.size() != 0) {
            tempertureSignals = tempertureList.get(0).getSignals();
            tvTempertrueName.setText(tempertureList.get(0).getName());
            String houseName = UiUtils.getHouseName(tempertureList.get(0).getHouseId());
            tvTempertrueHouseName.setText(houseName);
        }
        if (floodingList.size() != 0) {
            floodingSignals = floodingList.get(0).getSignals();
            tvFloodingName.setText(floodingList.get(0).getName());
            String houseName = UiUtils.getHouseName(floodingList.get(0).getHouseId());
            tvFloodingHouseName.setText(houseName);
        }
        if (smokingList.size() != 0) {
            smokingSignals = smokingList.get(0).getSignals();
            tvSmookingName.setText(smokingList.get(0).getName());
            String houseName = UiUtils.getHouseName(smokingList.get(0).getHouseId());
            tvSmookingHouseName.setText(houseName);
        }
        initRecycler();
    }

    private void initRecycler() {
        tempertrueRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        //        禁用recyclerView 滑动
        tempertrueRecyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        tempertrueRecyclerView.setFocusable(false);
        if (tempertrueItemDecoration == null) {
            tempertrueItemDecoration = new ItemDecoration(1, UiUtils.dip2px(6), true);
            tempertrueRecyclerView.addItemDecoration(tempertrueItemDecoration);
        }
        tempertureAdapter = new TempertureAdapter();
        tempertrueRecyclerView.setAdapter(tempertureAdapter);
//--------------------------------------------------------------------------------------
        floodingRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        floodingRecyclerView.setNestedScrollingEnabled(false);
        floodingRecyclerView.setFocusable(false);
        floodingAlarmAdapter = new FloodingAlarmAdapter();
        floodingRecyclerView.addItemDecoration(new ItemDecoration(2, UiUtils.dip2px(6), true));
        floodingRecyclerView.setAdapter(floodingAlarmAdapter);
        //--------------------------------------------------------------------------------------
        smookingRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        smookingRecyclerView.setNestedScrollingEnabled(false);
        smookingRecyclerView.setFocusable(false);
        smookingAlarmAdapter = new SmookingAlarmAdapter();
        smookingRecyclerView.addItemDecoration(new ItemDecoration(2, UiUtils.dip2px(6), true));
        smookingRecyclerView.setAdapter(smookingAlarmAdapter);
    }

    class SmookingAlarmAdapter extends RecyclerView.Adapter<SmookingAlarmHolder> {

        @NonNull
        @Override
        public SmookingAlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air_alarm, null);
            SmookingAlarmHolder smokingAlarmHolder = new SmookingAlarmHolder(view);
            return smokingAlarmHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SmookingAlarmHolder holder, int position) {
            StatusEnvBean.DataBean.SignalsBean signalsBean = smokingSignals.get(position);
            double currentValue = 0;
            if (signalsBean.getCurrentValue() != null) {
                currentValue = (double) signalsBean.getCurrentValue();
            }
            if (currentValue >= 1) {//1代表有告警
                holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_unnormal);
            } else {
                holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_normal);
            }
            holder.ivAlarmName.setText(signalsBean.getName());
            holder.ivAlarmName.setVisibility(View.VISIBLE);
            holder.ivAlarmStatus.setVisibility(View.VISIBLE);
        }

        @Override
        public int getItemCount() {
            return smokingSignals == null ? 0 : smokingSignals.size();
        }
    }

    static class SmookingAlarmHolder extends RecyclerView.ViewHolder {
        private ImageView ivAlarmStatus;
        private TextView ivAlarmName;

        public SmookingAlarmHolder(@NonNull View itemView) {
            super(itemView);
            ivAlarmStatus = itemView.findViewById(R.id.ivAlarmStatus);
            ivAlarmName = itemView.findViewById(R.id.ivAlarmName);
        }
    }

    class FloodingAlarmAdapter extends RecyclerView.Adapter<FloodingAlarmHolder> {

        @NonNull
        @Override
        public FloodingAlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air_alarm, null);
            FloodingAlarmHolder airAlarmHolder = new FloodingAlarmHolder(view);
            return airAlarmHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FloodingAlarmHolder holder, int position) {
            StatusEnvBean.DataBean.SignalsBean signalsBean = floodingSignals.get(position);
            int channelType = signalsBean.getChannelType();
            double currentValue = 0;
            if (signalsBean.getCurrentValue() != null) {
                currentValue = (double) signalsBean.getCurrentValue();
            }
            if (channelType == 1) {// 表示数字量
                if (currentValue >= 1) {//1代表有告警
                    holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_unnormal);
                } else {
                    holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_normal);
                }
                holder.ivAlarmName.setText(signalsBean.getName());
//                holder.ivAlarmName.setVisibility(View.VISIBLE);
//                holder.ivAlarmStatus.setVisibility(View.VISIBLE);
            } else {
                holder.ivAlarmStatus.setImageResource(R.drawable.shap_air_alarm_normal);
                holder.ivAlarmName.setText(signalsBean.getName());
//                holder.ivAlarmStatus.setVisibility(View.GONE);
//                holder.ivAlarmName.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return floodingSignals == null ? 0 : floodingSignals.size();
        }
    }

    static class FloodingAlarmHolder extends RecyclerView.ViewHolder {
        private ImageView ivAlarmStatus;
        private TextView ivAlarmName;

        public FloodingAlarmHolder(@NonNull View itemView) {
            super(itemView);
            ivAlarmStatus = itemView.findViewById(R.id.ivAlarmStatus);
            ivAlarmName = itemView.findViewById(R.id.ivAlarmName);
        }
    }

    class TempertureAdapter extends RecyclerView.Adapter<TempertureViewHolder> {

        @NonNull
        @Override
        public TempertureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_air, null);
            TempertureViewHolder tempertureViewHolder = new TempertureViewHolder(view);
            return tempertureViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TempertureViewHolder holder, int position) {
            holder.tvSampleName.setText(tempertureSignals.get(position).getName());
//            设置当前的值--------------------------------------和单位
            holder.tvSampleValue.setText(tempertureSignals.get(position).getCurrentValue() + " " + tempertureSignals.get(position).getUnit());
        }

        @Override
        public int getItemCount() {
            return tempertureSignals == null ? 0 : tempertureSignals.size();
        }
    }

    static class TempertureViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSampleName;
        private TextView tvSampleValue;

        public TempertureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSampleName = itemView.findViewById(R.id.tvSampleName);
            tvSampleValue = itemView.findViewById(R.id.tvSampleValue);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tempertrueDownArrow:
                showPopupWindow(tempertureList, tvTempertrueName, tempertrueDownArrow);//温湿度的设备类型是4
                break;
            case R.id.floodingDownArrow:
                showPopupWindow(floodingList, tvFloodingName, floodingDownArrow);
                break;
            case R.id.smookingDownArrow:
                showPopupWindow(smokingList,tvSmookingName,smookingDownArrow);
                break;
        }
    }

    public void showPopupWindow(ArrayList<StatusEnvBean.DataBean> deviceList, View locationView, final View downArrow) {
        StatusEnvPopupWindow statusEnvPopupWindow = new StatusEnvPopupWindow(this, deviceList, UiUtils.getColor(R.color.status_environment));
        PopupWindowCompat.showAsDropDown(statusEnvPopupWindow, locationView, 0, 0, Gravity.START);
        ObjectAnimator.ofFloat(downArrow, "rotation", 0f, 180f).setDuration(100).start();
        statusEnvPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ObjectAnimator.ofFloat(downArrow, "rotation", 180f, 0).setDuration(100).start();
            }
        });
    }

    @Override
    public void refreshSelectData(int categroy, int currentPosition, String houseName) {
        switch (categroy) {
            case 2:
                tvSmookingName.setText(smokingList.get(currentPosition).getName());
                tvSmookingHouseName.setText(houseName);
                smokingSignals = smokingList.get(currentPosition).getSignals();
                smookingAlarmAdapter.notifyDataSetChanged();
                break;
            case 4:
                tvTempertrueName.setText(tempertureList.get(currentPosition).getName());
                tvTempertrueHouseName.setText(houseName);
                tempertureSignals = tempertureList.get(currentPosition).getSignals();
                tempertureAdapter.notifyDataSetChanged();
                break;
            case 5:
                tvFloodingName.setText(floodingList.get(currentPosition).getName());
                tvTempertrueHouseName.setText(houseName);
                floodingSignals = floodingList.get(currentPosition).getSignals();
                floodingAlarmAdapter.notifyDataSetChanged();
                break;
        }
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public ItemDecoration(int spanCount, int space, boolean includeEdge) {
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
