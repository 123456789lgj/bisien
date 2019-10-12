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
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.CondiditioningBean;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PowerSystemActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_system);
        findTitle("电力系统");
        recyclerView = findViewById(R.id.recyclerView);
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = MyApplication.equipments.get(3);
        if (equipmentsBeans != null){
            for (int i = 0; i < equipmentsBeans.size(); i++) {
                GlobalDataBean.DataBean.HousesBean.EquipmentsBean equipmentsBean = equipmentsBeans.get(i);
                if (!equipmentsBean.getName().toUpperCase().contains("PDU")) {
                    initData(equipmentsBean.getId());
                    break;
                }
            }
        }

    }

    ArrayList<CondiditioningBean.DataBean> listTwo = new ArrayList<>();
    private void initData(long deviceId) {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/status/get_list_rdata_byequipid/" + deviceId + "/" + 3;
//        String url = "http://192.168.1.145:8080/gledeye/rest/equipment/get_list";
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {

                dismissLoading();
                CondiditioningBean condiditioningBean = new Gson().fromJson(response, CondiditioningBean.class);
                List<CondiditioningBean.DataBean> data = condiditioningBean.getData();

                for (int i = 0; i < data.size(); i++) {
                    CondiditioningBean.DataBean dataBean = data.get(i);
                    if (dataBean.isVisible()) {//需要展示的信息
                        int channelType = dataBean.getChannelType();
                        if (channelType == 1) {// channelType 等于0 的话，表示数字量
                        } else {// channelType=2 的话，表示模拟量
                            listTwo.add(dataBean);
                        }
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
//        recyclerView.addItemDecoration(new SpacesItemDecoration(16));
        PowerSystemAdapter powerSystemAdapter = new PowerSystemAdapter();
        recyclerView.setAdapter(powerSystemAdapter);
    }

    class PowerSystemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private int TYPE_HEADER = 1001;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            添加头布局
            if (viewType == TYPE_HEADER) {
                ImageView imageView = new ImageView(UiUtils.getContext());
                imageView.setImageResource(R.mipmap.ic_power);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtils.dip2px(LinearLayout.LayoutParams.WRAP_CONTENT));
                imageView.setLayoutParams(layoutParams);
                return new HeadViewHolder(imageView);
            }
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_power_system, null);
            PowerSystemViewHolder powerSystemViewHolder = new PowerSystemViewHolder(view);
            return powerSystemViewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            }
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder2, int position) {
            if (holder2 instanceof PowerSystemViewHolder) {
//                减一是为了防止数组索引越界
//                EquipmentBean.DataBean.EquipmentSignalsBean equipmentSignalsBean = listTwo.getEquipmentSignals().get(position - 1);
                CondiditioningBean.DataBean dataBean = listTwo.get(position - 1);
                PowerSystemViewHolder holder = (PowerSystemViewHolder) holder2;
                holder.tvDeviceName.setText(dataBean.getName());
                holder.tvCurrentValue.setText(dataBean.getCurrentValue() + dataBean.getUnit());
//            表示最后一个元素
                if (position == listTwo.size()) {
                    holder.bottomDevide.setVisibility(View.VISIBLE);
                } else {
                    holder.bottomDevide.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return  listTwo.size() + 1;
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

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        private View headView;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            headView = itemView;
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
