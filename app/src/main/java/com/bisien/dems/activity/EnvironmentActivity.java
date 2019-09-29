package com.bisien.dems.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.List;

public class EnvironmentActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        findTitle("环境系统");
        recyclerView = findViewById(R.id.recyclerView);
        initData();

    }

    private EquipmentBean.DataBean dataBeanList;

    private void initData() {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst()+ "rest/equipment/get_list";
//        String url = "http://192.168.1.145:8080/gledeye/rest/equipment/get_list";
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {

                dismissLoading();

                Gson gson = new Gson();
                EquipmentBean equipmentBean = gson.fromJson(response, EquipmentBean.class);
                List<EquipmentBean.DataBean> data = equipmentBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    EquipmentBean.DataBean dataBean = data.get(i);
                    String name = dataBean.getName();
                    if (name.equalsIgnoreCase("空调")) {
                        dataBeanList = dataBean;
//                        找到空调 对应的数据，跳出循环
                        break;
                    }
                }
                setData();
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                System.out.println("PowerSystemActivity :onNotOk :" + msg);
            }
        });
    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(16));
        recyclerView.setAdapter(new EnvironmentSystemAdapter());
    }

    class EnvironmentSystemAdapter extends RecyclerView.Adapter<PowerSystemViewHolder> {

        @NonNull
        @Override
        public PowerSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_power_system, null);
            PowerSystemViewHolder powerSystemViewHolder = new PowerSystemViewHolder(view);
            return powerSystemViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PowerSystemViewHolder holder, int position) {
            EquipmentBean.DataBean.EquipmentSignalsBean equipmentSignalsBean = dataBeanList.getEquipmentSignals().get(position);
            holder.tvDeviceName.setText(equipmentSignalsBean.getName());
            holder.tvCurrentValue.setText(equipmentSignalsBean.getCurrentValue() + equipmentSignalsBean.getUnit());
//            设置第一个元素的背景，
            if (position == 0) {
                ViewGroup.LayoutParams layoutParams = holder.viewDevide.getLayoutParams();
                layoutParams.height = UiUtils.dip2px(11);
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                holder.viewDevide.setLayoutParams(layoutParams);
                holder.viewDevide.setBackgroundResource(R.drawable.shap_background_top);
            } else {
                ViewGroup.LayoutParams layoutParams = holder.viewDevide.getLayoutParams();
                layoutParams.height = UiUtils.dip2px(5);
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                holder.viewDevide.setLayoutParams(layoutParams);
                holder.viewDevide.setBackgroundColor(UiUtils.getColor(R.color.mid_blue));
            }
//            表示最后一个元素
            if (position == dataBeanList.getEquipmentSignals().size() - 1) {
                holder.bottomDevide.setVisibility(View.VISIBLE);
            } else {
                holder.bottomDevide.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return dataBeanList == null ? 0 : dataBeanList.getEquipmentSignals().size();
        }
    }

    static class PowerSystemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDeviceName;
        private TextView tvCurrentValue;
        private View bottomDevide;
        private View viewDevide;

        public PowerSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
            tvCurrentValue = itemView.findViewById(R.id.tvCurrentValue);
            bottomDevide = itemView.findViewById(R.id.bottomDevide);
            viewDevide = itemView.findViewById(R.id.viewDevide);
        }
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;//元素与元素之间的间隔

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            //获取当前item的位置
            int position = parent.getChildAdapterPosition(view);

//            if (position == 0){
//                outRect.top =  UiUtils.dip2px(space);
//                outRect.bottom =  UiUtils.dip2px(space);
//            }else {
//                outRect.bottom =  UiUtils.dip2px(space);
//            }
            outRect.left = UiUtils.dip2px(space);
            outRect.right = UiUtils.dip2px(space);


        }
    }
}
