package com.bisien.dems.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.DeviceSelectPopupWindow;

import java.util.ArrayList;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_env);
        findTitle("环境");
        initView();
    }

    private void initView() {
        tvTempertrueName =  findViewById(R.id.tvTempertrueName);
        tvTempertrueHouseName =  findViewById(R.id.tvTempertrueHouseName);
        tempertrueDownArrow =  findViewById(R.id.tempertrueDownArrow);
        tempertrueDownArrow.setOnClickListener(this);
        tempertrueRecyclerView = findViewById(R.id.tempertrueRecyclerView);
//        水浸
        tvFloodingName =  findViewById(R.id.tvFloodingName);
        tvFloodingHouseName =  findViewById(R.id.tvFloodingHouseName);
        floodingDownArrow =  findViewById(R.id.floodingDownArrow);
        floodingDownArrow.setOnClickListener(this);
        floodingRecyclerView = findViewById(R.id.tempertrueRecyclerView);
//        烟感
        tvSmookingName =  findViewById(R.id.tvSmookingName);
        tvSmookingHouseName =  findViewById(R.id.tvSmookingHouseName);
        smookingDownArrow =  findViewById(R.id.smookingDownArrow);
        smookingDownArrow.setOnClickListener(this);
        smookingRecyclerView = findViewById(R.id.tempertrueRecyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tempertrueDownArrow:
//                showPopupWindow(null,tvTempertrueName,tempertrueDownArrow);
                break;
            case R.id.floodingDownArrow:
//                showPopupWindow(null,tvTempertrueName,tempertrueDownArrow);
                break;
            case R.id.smookingDownArrow:
//                showPopupWindow(null,tvTempertrueName,tempertrueDownArrow);
                break;
        }
    }
    public void showPopupWindow(ArrayList<CondiditioningActivity.Bean> deviceList, View locationView, final View downArrow){
        DeviceSelectPopupWindow deviceSelectPopupWindow = new DeviceSelectPopupWindow(this, deviceList, UiUtils.getColor(R.color.status_environment));
        PopupWindowCompat.showAsDropDown(deviceSelectPopupWindow, locationView, 0, 0, Gravity.START);
        ObjectAnimator.ofFloat(downArrow, "rotation", 0f, 180f).setDuration(100).start();
        deviceSelectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ObjectAnimator.ofFloat(downArrow, "rotation", 180f, 0).setDuration(100).start();
            }
        });
    }

    @Override
    public void refreshSelectData(CondiditioningActivity.Bean bean) {

    }
}
