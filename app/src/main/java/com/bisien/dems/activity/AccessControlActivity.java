package com.bisien.dems.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.ToastUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.List;


public class AccessControlActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private static String TAG = "AccessControlActivity";
    private EquipmentBean.DataBean dataBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_control);
        findTitle("门禁系统");
        recyclerView = findViewById(R.id.recyclerView);
        initData();

    }

    private void initData() {
//        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = MyApplication.equipments.get(Integer.parseInt(8));

        showLoading("加载中...");
        MyHttpUtils myHttpUtils = new MyHttpUtils();
        String url = GlobalConstants.getUrlFirst() + "rest/equipment/get_list";

//        String url = "http://localhost:8080/gledeye/rest/basicdata/get_cabinetsiganl";
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {

            @Override
            public void onOk(String response) {
//                Log.i(TAG, "response :" + response);
                dismissLoading();
                Gson gson = new Gson();
                EquipmentBean equipmentBean = gson.fromJson(response, EquipmentBean.class);
                List<EquipmentBean.DataBean> data = equipmentBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    String name = data.get(i).getName();
                    if (name.equalsIgnoreCase("机柜")) {
                        dataBeanList = data.get(i);
//                        找到机柜信息跳出循环
                        break;
                    }
                }
                setData();
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                Log.i(TAG, "onNotOk :" + msg);
            }
        });

    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1,UiUtils.dip2px(8),true));
        recyclerView.setAdapter(new AccessControlAdapter());

    }

    class AccessControlAdapter extends RecyclerView.Adapter<AccessControlViewHolder> {
        @NonNull
        @Override
        public AccessControlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_access_control, null);
            AccessControlViewHolder accessControlViewHolder = new AccessControlViewHolder(view);
            return accessControlViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AccessControlViewHolder holder, int position) {
            EquipmentBean.DataBean.EquipmentSignalsBean equipmentSignalsBean = dataBeanList.getEquipmentSignals().get(position);
            holder.tvName.setText(equipmentSignalsBean.getName());
            if (equipmentSignalsBean.getCurrentValue() == 0) {
//当前门处于关闭状态
                holder.tvCurrentStatusValue.setText("关闭");
                holder.tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.count_green));
                holder.ivSwitch.setChecked(false);
                holder.ivSwitch.setSwitchTextAppearance(AccessControlActivity.this, R.style.s_false);
            } else if (equipmentSignalsBean.getCurrentValue() == 1) {
//                当前门处于打开状态
                holder.tvCurrentStatusValue.setText("打开");
                holder.ivSwitch.setChecked(true);
                holder.tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.em_alarm));
//                设置off on 字的颜色
                holder.ivSwitch.setSwitchTextAppearance(AccessControlActivity.this, R.style.s_true);
            } else {
                holder.tvCurrentStatusValue.setText("未知");
                holder.ivSwitch.setChecked(false);
                holder.tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.count_green));
                holder.ivSwitch.setSwitchTextAppearance(AccessControlActivity.this, R.style.s_false);
            }
            holder.ivSwitch.setTag(equipmentSignalsBean.getCurrentValue());
            final AccessControlViewHolder holderSwitch = holder;
//状态发生变化后调用
            holderSwitch.ivSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    这里是只有点击时才会调用
                    if (isChecked) {
//                        状态发生变化时调用
                        System.out.println("onCheckedChanged -------------------------- isChecked true");
                        holderSwitch.ivSwitch.setSwitchTextAppearance(AccessControlActivity.this, R.style.s_true);
                        holderSwitch.tvCurrentStatusValue.setText("打开");
                        holderSwitch.tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.em_alarm));
                    } else {
                        System.out.println("onCheckedChanged -------------------------- isChecked false");
                        holderSwitch.ivSwitch.setSwitchTextAppearance(AccessControlActivity.this, R.style.s_false);
                        holderSwitch.tvCurrentStatusValue.setText("关闭");
                        holderSwitch.tvCurrentStatusValue.setTextColor(UiUtils.getColor(R.color.count_green));
                    }
                }
            });
            holderSwitch.ivSwitch.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
//                        再次进行事件的相应
                        case MotionEvent.ACTION_DOWN:
//                            控制当前门是打开状态时，让门不能再次打开
                            if (holderSwitch.ivSwitch.isChecked()){
                                UiUtils.toast("当前门处于打开状态，只能通过手动进行关闭");
                                return true;
                            }else {

                            }
                            break;
                    }
                    return false;
                }
            });
                holderSwitch.ivSwitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        这里来通过手机控制门禁
                        UiUtils.toast("通过手机来操作门禁");

                    }
                });
        }

        @Override
        public int getItemCount() {
            return dataBeanList == null ? 0 : dataBeanList.getEquipmentSignals().size();
//            return 2;
        }
    }

    static class AccessControlViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvCurrentStatusValue;
        private Switch ivSwitch;

        public AccessControlViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCurrentStatusValue = itemView.findViewById(R.id.tvCurrentStatusValue);
            ivSwitch = itemView.findViewById(R.id.s_w);
        }
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public SpacesItemDecoration(int spanCount, int space, boolean includeEdge) {
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
