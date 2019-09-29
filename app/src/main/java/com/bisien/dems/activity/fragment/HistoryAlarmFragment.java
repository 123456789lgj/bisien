package com.bisien.dems.activity.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.AlarmsBean;
import com.bisien.dems.activity.bean.AlarmsHistoryBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HistoryAlarmFragment extends BaseFragment {
    private View pagerView;
    private RecyclerView alarmRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    int start = 0;
    private boolean loadMore;
    private int totalSize;
    private ArrayList<AlarmsHistoryBean.DataBean> totalList = new ArrayList<>();
    @Override
    public View initView() {
        pagerView = View.inflate(getContext(), R.layout.adapter_pager_alarm, null);
        alarmRecyclerView = pagerView.findViewById(R.id.alarmRecyclerView);
        smartRefreshLayout = pagerView.findViewById(R.id.refreshView);
        return pagerView;
    }
//  onActivityCreated ,当第一次切换到告警状态的时候，才加载
    @Override
    protected void initData() {
        super.initData();
        setData();

    }
    private void setData() {
//            设置下拉刷新监听
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                loadMore = false;
                getServiceHistoryData(0);
            }
        });
//设置加载更多监听
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadMore = true;
//              表明有下页数据
                if (totalList.size() < totalSize) {
                    getServiceHistoryData(++start);
                } else {
                    smartRefreshLayout.finishLoadMore();
                    UiUtils.toast("没有更多数据");
                }
            }
        });
        getServiceHistoryData(0);

    }

//{"startTime":"2000-09-23+11:05:52","endTime":"2019-09-23+11:05:38","param1":"-1","param2":"","param3":""}
    private void getServiceHistoryData(int start) {
        this.start = start;
        System.out.println("HistoryAlarmFragment start：" + start);
//        http://localhost:8080/gledeye/rest/reports/get_historydata
        MyHttpUtils myHttpUtils = new MyHttpUtils();
        String url = GlobalConstants.getUrlFirst() + "rest/reports/get_historyalarm";

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("draw", "10");
//        第一次start 分页请求 为0
        hashMap.put("start", start + "");
        hashMap.put("length", "30");

        HashMap<String, String> paramstrHashMap = new HashMap<>();
        paramstrHashMap.put("param1", "-1"); //是设备类型
        paramstrHashMap.put("param2", "");
        paramstrHashMap.put("param3", "");// 这个是关键字的参数
        paramstrHashMap.put("startTime", "2000-09-23 11:31:18");
        String time = UiUtils.getTime(new Date());
        paramstrHashMap.put("endTime", time);
        String paramstr = new Gson().toJson(paramstrHashMap);
        hashMap.put("paramstr",paramstr);
        myHttpUtils.getDataFromServiceByPost(url, hashMap, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                System.out.println("HistoryAlarmFragment response :" + response);
                parseJson(response);
            }

            @Override
            public void onNotOk(String msg) {
                System.out.println("HistoryAlarmFragment onNotOk :" + msg);

            }
        });

    }
    private AlarmHistoryAdapter alarmHistoryAdapter;
    private void parseJson(String response) {
        if(alarmHistoryAdapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            alarmRecyclerView.setLayoutManager(linearLayoutManager);
            alarmHistoryAdapter = new AlarmHistoryAdapter();
            alarmRecyclerView.setAdapter(alarmHistoryAdapter);
        }
        Gson gson = new Gson();
        AlarmsHistoryBean alarmsBean = gson.fromJson(response, AlarmsHistoryBean.class);
//       重新初始化，当前对应级别的警告数量
        totalSize = alarmsBean.getRecordsTotal();
        List<AlarmsHistoryBean.DataBean> data = alarmsBean.getData();
        if (loadMore) {
            totalList.addAll(data);
            alarmHistoryAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        } else {
            totalList.clear();
            totalList.addAll(data);
            alarmHistoryAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishRefresh();
        }
    }
    class AlarmHistoryAdapter extends RecyclerView.Adapter<AlarmHistoryViewHolder> {
        @NonNull
        @Override
        public AlarmHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_alarm, null);
            AlarmHistoryViewHolder alarmViewHolder = new AlarmHistoryViewHolder(view);
            return alarmViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AlarmHistoryViewHolder holder, int position) {
            AlarmsHistoryBean.DataBean dataBean = totalList.get(position);
            int alarmGrade = dataBean.getAlarmGrade();
//            alarmGrade :过滤警告级别  1表示一般，2表示重要，3表示紧急，如果没有级别那么就是  -1
            if (alarmGrade == 1) {
                holder.alarmGrade.setText("一般");
                holder.alarmGrade.setTextColor(UiUtils.getColor(R.color.general_alarm));
            } else if (alarmGrade == 2) {
                holder.alarmGrade.setText("重要");
                holder.alarmGrade.setTextColor(UiUtils.getColor(R.color.important_alarm));
            } else if (alarmGrade == 3) {
                holder.alarmGrade.setText("紧急");
                holder.alarmGrade.setTextColor(UiUtils.getColor(R.color.em_alarm));
            }
            String endTime = dataBean.getEndTime();
            holder.alarmTime.setText("结束时间: " + endTime);

            String description = dataBean.getConfirmRemark();
//            System.out.println("description :" + description + " position :");
            if (description != null && !"".equals(description)) {
                holder.alarmNotBeenProcessed.setText(description);
            } else {
                holder.alarmNotBeenProcessed.setText("告警已处理");
            }
            holder.alarmNotBeenProcessed.setTextColor(UiUtils.getColor(R.color.black));
            holder.alarmInfo.setText(dataBean.getStationName() + "," + dataBean.getHouseName() + "," + dataBean.getEquipmentName() + "," + dataBean.getName());
            holder.ivOptions.setVisibility(View.INVISIBLE);
//            System.out.println("ivOptions position :" + position);
//            holder.ivOptions.setOnClickListener(listener);
        }

        @Override
        public int getItemCount() {
            return totalList.size();
        }
    }
    static class AlarmHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView alarmNotBeenProcessed;
        private TextView alarmInfo;
        private TextView alarmGrade;
        private TextView alarmTime;
        private ImageView ivOptions;

        public AlarmHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmNotBeenProcessed = itemView.findViewById(R.id.alarmNotBeenProcessed);
            alarmInfo = itemView.findViewById(R.id.alarmInfo);
            alarmGrade = itemView.findViewById(R.id.alarmGrade);
            alarmTime = itemView.findViewById(R.id.alarmTime);
            ivOptions = itemView.findViewById(R.id.ivOptions);
        }
    }
}
