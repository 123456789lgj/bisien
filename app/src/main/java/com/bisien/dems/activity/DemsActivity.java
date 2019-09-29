package com.bisien.dems.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Rect;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.bean.CustomBean;
import com.bisien.dems.activity.bean.DemsHomeBean;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.tabfragment.BaseTabFragment;
import com.bisien.dems.activity.tabfragment.TabLayoutFragment;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DemsActivity extends BaseActivity {
    private ArrayList<BaseTabFragment> arrayList = new ArrayList<>();
    private ArrayList<String> topNavigation = new ArrayList<>();
    private RecyclerView recyclerViewDems;
    private DemsHomeBean.DataBean dataHomeDemsList;
    private ArrayList<CustomBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dems);
        findTitle("动力环境监控系统");
        recyclerViewDems = findViewById(R.id.recyclerViewDems);
        initData();

    }

    private void initData() {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/basicdata/get_homepagedata_forweb";
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                dismissLoading();
                Gson gson = new Gson();
                DemsHomeBean demsHomeBean = gson.fromJson(response, DemsHomeBean.class);
                dataHomeDemsList = demsHomeBean.getData();
                nextVisableUI();
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                System.out.println("DemsActivity :onNotOk :" + msg);
            }
        });
    }
    int[] arr = new int[]{
            R.mipmap.dems_air_conditioning,
            R.mipmap.dems_ups,

            R.mipmap.dems_temperature,
            R.mipmap.dems_smoking,
            R.mipmap.dems_flooding
            };
    private void nextVisableUI() {
//        获取数据封装数据
        CustomBean customBean = new CustomBean();
        if(dataHomeDemsList.getAc() != null){
            customBean.setEquipmentName("空调");//空调
            customBean.setEquipmentCounts(dataHomeDemsList.getAc().getCount()+"");//设备总数
            customBean.setRunEquipmentCounts(dataHomeDemsList.getAc().getParam2()+"");//运行数量
            customBean.setEquipmentAlarm(dataHomeDemsList.getAcAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            dataList.add(customBean);
        }
//        CustomBean customBean2 = new CustomBean();
//        if(dataHomeDemsList.getDc() != null){
//            customBean2.setEquipmentName("dc");//配电柜
//            customBean2.setEquipmentCounts(dataHomeDemsList.getDc().getCount()+"");//设备总数
//            customBean2.setRunEquipmentCounts(dataHomeDemsList.getDc().getParam2()+"");//运行数量
//            customBean2.setEquipmentAlarm(dataHomeDemsList.getDcAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
//            dataList.add(customBean2);
//        }
        CustomBean customBean3 = new CustomBean();
        if(dataHomeDemsList.getUps() != null){
            customBean3.setEquipmentName("UPS");//ups
            customBean3.setEquipmentCounts(dataHomeDemsList.getUps().getCount()+"");//设备总数
            customBean3.setRunEquipmentCounts(dataHomeDemsList.getUps().getParam2()+"");//运行数量
            customBean3.setEquipmentAlarm(dataHomeDemsList.getUpsAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            dataList.add(customBean3);
        }
        CustomBean customBean4 = new CustomBean();
        if(dataHomeDemsList.getThs() != null){
            customBean4.setEquipmentName("温湿度感应器");//温湿度
            customBean4.setEquipmentCounts(dataHomeDemsList.getThs().getCount()+"");//设备总数
            customBean4.setRunEquipmentCounts(dataHomeDemsList.getThs().getParam2()+"");//运行数量
            customBean4.setEquipmentAlarm(dataHomeDemsList.getThsAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            dataList.add(customBean4);
        }
        CustomBean customBean5 = new CustomBean();
        if(dataHomeDemsList.getSts() != null){
            customBean5.setEquipmentName("烟雾感应器");//消防 就是烟感吧
            customBean5.setEquipmentCounts(dataHomeDemsList.getSts().getCount()+"");//设备总数
            customBean5.setRunEquipmentCounts(dataHomeDemsList.getSts().getParam2()+"");//运行数量
            customBean5.setEquipmentAlarm(dataHomeDemsList.getStsAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            dataList.add(customBean5);
        }
        CustomBean customBean6 = new CustomBean();
        if(dataHomeDemsList.getFs() != null){
            customBean6.setEquipmentName("水浸感应器");//水浸
            customBean6.setEquipmentCounts(dataHomeDemsList.getFs().getCount()+"");//设备总数
            customBean6.setRunEquipmentCounts(dataHomeDemsList.getFs().getParam2()+"");//运行数量
            customBean6.setEquipmentAlarm(dataHomeDemsList.getFsAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            dataList.add(customBean6);
        }


//        CustomBean customBean7 = new CustomBean();
//        if(dataHomeDemsList.getDc() != null){
//            customBean7.setEquipmentName("ab");//警铃
//            customBean7.setEquipmentCounts(dataHomeDemsList.getAb().getCount()+"");//设备总数
//            customBean7.setRunEquipmentCounts(dataHomeDemsList.getAb().getParam2()+"");//运行数量
//            customBean7.setEquipmentAlarm(dataHomeDemsList.getAbAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
//            dataList.add(customBean7);
//        }
//        CustomBean customBean8 = new CustomBean();
//        if(dataHomeDemsList.getDc() != null){
//            customBean8.setEquipmentName("other");//其他设备
//            customBean8.setEquipmentCounts(dataHomeDemsList.getOther().getCount()+"");//设备总数
//            customBean8.setRunEquipmentCounts(dataHomeDemsList.getOther().getParam2()+"");//运行数量
//            customBean8.setEquipmentAlarm(dataHomeDemsList.getOtherAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
//            dataList.add(customBean8);
//        }




        DemsHomeAdapter demsHomeAdapter = new DemsHomeAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewDems.setLayoutManager(linearLayoutManager);
        recyclerViewDems.addItemDecoration(new SpacesItemDecoration(1,UiUtils.dip2px(8),true));
        recyclerViewDems.setAdapter(demsHomeAdapter);

    }
    class DemsHomeAdapter extends RecyclerView.Adapter<DemsHomeViewHolder>{

        @NonNull
        @Override
        public DemsHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_home_dems, null);
            return new DemsHomeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DemsHomeViewHolder holder, int position) {
            holder.ivLogo.setImageResource(arr[position]);
            holder.tvName.setText(dataList.get(position).getEquipmentName());
            holder.tvEquipmentCount.setText(dataList.get(position).getEquipmentCounts());
            String runEquipmentCounts = dataList.get(position).getRunEquipmentCounts();
            holder.tvRunCount.setText((int)Double.parseDouble(runEquipmentCounts) + "");
//            设备总数减去 运行正常数等于0 ，表示没有故障设备数
            double v = Integer.parseInt(dataList.get(position).getEquipmentCounts()) - Double.parseDouble(dataList.get(position).getRunEquipmentCounts());
            System.out.println("v :" + v);
            if (v == 0){
//                表示没有故障
                holder.tvAlarmCount.setText(0+"");
                holder.tvAlarmCount.setTextColor(UiUtils.getColor(R.color.count_green));
            }else{
                double errorCounts = Integer.parseInt(dataList.get(position).getEquipmentCounts()) - Double.parseDouble(dataList.get(position).getRunEquipmentCounts());
                holder.tvAlarmCount.setText((int) errorCounts+"");
                holder.tvAlarmCount.setTextColor(UiUtils.getColor(R.color.count_red));
            }

        }

        @Override
        public int getItemCount() {

            return dataList.size();
        }
    }
    static class DemsHomeViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivLogo;
        private TextView tvName;
        private TextView tvEquipmentCount;
        private TextView tvRunCount;
        private TextView tvAlarmCount;
        public DemsHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvName = itemView.findViewById(R.id.tvName);
            tvEquipmentCount = itemView.findViewById(R.id.tvEquipmentCount);
            tvRunCount = itemView.findViewById(R.id.tvRunCount);
            tvAlarmCount = itemView.findViewById(R.id.tvAlarmCount);
        }
    }
    class SpacesItemDecoration extends RecyclerView.ItemDecoration{
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
