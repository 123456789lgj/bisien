package com.bisien.dems.activity.fragment;

import android.annotation.SuppressLint;
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
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.HistoryAlarmDialog;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RealTimeAlarmFragment extends BaseFragment {
    private View pagerView;
    private RecyclerView alarmRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    public AlarmsBean alarmsBean;
    private ArrayList<AlarmsBean.DataBean> totalList = new ArrayList<>();
    private int start = 0;
    private AlarmAdapter alarmAdapter;
    private int totalSize;

    public RealTimeAlarmFragment(AlarmsBean alarmsBean) {
        this.alarmsBean = alarmsBean;
        totalSize = alarmsBean.getRecordsTotal();
        totalList.addAll(alarmsBean.getData());
    }

    //点击切换的时候才执行
    @Override
    public View initView() {
        pagerView = View.inflate(getContext(), R.layout.adapter_pager_alarm, null);
        alarmRecyclerView = pagerView.findViewById(R.id.alarmRecyclerView);
        smartRefreshLayout = pagerView.findViewById(R.id.refreshView);
        return pagerView;
    }

    //onActivityCreated
    @Override
    protected void initData() {
        setData();
    }

    public boolean loadMore = false;

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        alarmRecyclerView.setLayoutManager(linearLayoutManager);
        alarmAdapter = new AlarmAdapter();
        alarmRecyclerView.setAdapter(alarmAdapter);

//        System.out.println("position "+this.smartRefreshLayout);
//            设置下拉刷新监听
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
//                刷新数据
                loadMore = false;
                refreshData(0);
            }
        });
//设置加载更多监听
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                //请求下一页数据
                loadMore = true;
//              表明有下页数据
                if (totalList.size() < totalSize) {
                    refreshData(++start);
                } else {
                    smartRefreshLayout.finishLoadMore();
                    UiUtils.toast("没有更多数据");
                }
            }
        });
    }

    //    {
//        "id": 1881563857627057,
//            "description": null,
//            "name": "烟感告警",
//            "stationName": "局站A",
//            "houseName": "二楼实验机房",
//            "equipmentName": "烟温采集器",
//            "stationId": 8546069125419008,
//            "houseId": 8622681966107648,
//            "equipmentId": 1068538191433335,
//            "alarmId": 1066560585003365,
//            "triggerValue": 0,
//            "alarmGrade": 2,
//            "category": 2,
//            "startTime": "2019-07-23 12:53:47",
//            "endTime": null,
//            "confirmTime": "2019-09-17 10:07:11",
//            "confirmer": "admin",
//            "confirmRemark": "dsadas"
//    }
    class AlarmAdapter extends RecyclerView.Adapter<AlarmViewHolder> {
        @NonNull
        @Override
        public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_alarm, null);
            AlarmViewHolder alarmViewHolder = new AlarmViewHolder(view);
            return alarmViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
            AlarmsBean.DataBean dataBean = totalList.get(position);
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
            if (endTime != null) {
                holder.alarmTime.setText("结束时间: " + endTime);
            } else {
                holder.alarmTime.setText("开始时间: " + dataBean.getStartTime());
            }
            Object description = dataBean.getConfirmRemark();
//            System.out.println("description :" + description + " position :");
            if (description != null && !"".equals(description)) {
                holder.alarmNotBeenProcessed.setText((String) description);
                holder.alarmNotBeenProcessed.setTextColor(UiUtils.getColor(R.color.black));
            } else {
                holder.alarmNotBeenProcessed.setText("告警尚未处理");
                holder.alarmNotBeenProcessed.setTextColor(UiUtils.getColor(R.color.em_alarm));
            }
            holder.alarmInfo.setText(dataBean.getStationName() + "," + dataBean.getHouseName() + "," + dataBean.getEquipmentName() + "," + dataBean.getName());
            holder.ivOptions.setTag(position);
//            System.out.println("ivOptions position :" + position);
            holder.ivOptions.setOnClickListener(listener);
        }

        @Override
        public int getItemCount() {
            return totalList.size();
        }
    }

    int updatePosition = -1;
    HistoryAlarmDialog historyAlarmDialog;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
//            System.out.println("iv :" + v.getTag());
            updatePosition = (int) v.getTag();
            if (historyAlarmDialog == null) {
                historyAlarmDialog = new HistoryAlarmDialog(getActivity(), totalList.get((int) v.getTag()), new HistoryAlarmDialog.UpdateStatus() {
                    @Override
                    public void upDateItemStatus() {
//                        为什么iv 和 iv2的值不一样，是因为 iv 是每次点击的正确值,iv2 是第一次初始化点击的值
//                        System.out.println("iv2 :" + v.getTag());
                        alarmAdapter.notifyItemChanged(updatePosition);
                    }
                });
                historyAlarmDialog.show();
            } else {
                historyAlarmDialog.dataBean = totalList.get((int) v.getTag());
//                第二次进来初始化和第一次进来的位置信息一样
                historyAlarmDialog.second();
                historyAlarmDialog.show();
            }
//            HistoryAlarmDialog historyAlarmDialog = new HistoryAlarmDialog(getActivity(), totalList.get((int) v.getTag()), new HistoryAlarmDialog.UpdateStatus() {
//                @Override
//                public void upDateItemStatus() {
//                    System.out.println("v :" + v.getTag());
//                    alarmAdapter.notifyItemChanged((Integer) v.getTag());
//                }
//            });
//            historyAlarmDialog.show();
        }
    };

    static class AlarmViewHolder extends RecyclerView.ViewHolder {
        private TextView alarmNotBeenProcessed;
        private TextView alarmInfo;
        private TextView alarmGrade;
        private TextView alarmTime;
        private ImageView ivOptions;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmNotBeenProcessed = itemView.findViewById(R.id.alarmNotBeenProcessed);
            alarmInfo = itemView.findViewById(R.id.alarmInfo);
            alarmGrade = itemView.findViewById(R.id.alarmGrade);
            alarmTime = itemView.findViewById(R.id.alarmTime);
            ivOptions = itemView.findViewById(R.id.ivOptions);
        }
    }

    interface FilterDataObtion {
        void complete();
    }

    private FilterDataObtion filterDataObtion;

    public void setFilterDataObtion(FilterDataObtion filterDataObtion) {
        this.filterDataObtion = filterDataObtion;
    }

    public void refreshData(int start) {
        System.out.println("alarmAdapter :" + alarmAdapter);
        this.start = start;
        //请求告警信息
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//      获取所有实时告警信息
        String url = GlobalConstants.getUrlFirst() + "rest/alarm/get_list_forweb";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("draw", "622");
//        第一次start 分页请求 为0
        hashMap.put("start", start + "");
        hashMap.put("length", "20");
//        {"stationId":-1,"houseId":-1,"equipmentId":-1,"alarmGrade":1,"startTime":"2010-01-01+00:00:00","endTime":"2019-09-11+14:02:33","equipmenttype":"-1"}
        HashMap<String, String> paramstrHashMap = new HashMap<>();

        HashMap<String, String> filterContitions = MyApplication.filterContitions;
        paramstrHashMap.put("stationId", filterContitions.get("stationId") != null ? filterContitions.get("stationId") : "-1");
        paramstrHashMap.put("houseId", filterContitions.get("houseId") != null ? filterContitions.get("houseId") : "-1");
        paramstrHashMap.put("equipmentId", filterContitions.get("equipmentId") != null ? filterContitions.get("equipmentId") : "-1");
        paramstrHashMap.put("equipmenttype", filterContitions.get("equipmenttype") != null ? filterContitions.get("equipmenttype") : "-1");
        paramstrHashMap.put("alarmGrade", filterContitions.get("alarmGrade") != null ? filterContitions.get("alarmGrade") : "-1");
        paramstrHashMap.put("startTime",filterContitions.get("startTime") != null ? filterContitions.get("startTime") : "-1");
        paramstrHashMap.put("endTime", filterContitions.get("endTime") != null ? filterContitions.get("endTime") : "-1");
        String paramstr = new Gson().toJson(paramstrHashMap);

        hashMap.put("paramstr", paramstr);
        System.out.println("paramstr :" + paramstr);
        myHttpUtils.getDataFromServiceByPost(url, hashMap, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                if (filterDataObtion != null) {
                    filterDataObtion.complete();
                }
                parseJson(response);
            }

            @Override
            public void onNotOk(String msg) {
                System.out.println("AlarmFragment onNotOk :" + msg);
                if (loadMore) {
                    smartRefreshLayout.finishLoadMore();
                } else {
                    smartRefreshLayout.finishRefresh();
                }
            }
        });
    }

    //    导航栏切换时重新请求，然后获取新的全局数据
    public void switchNav() {
//        清空之前的集合的数据
        totalList.clear();
//        获取总共多少条警告
        totalSize = alarmsBean.getRecordsTotal();
        totalList.addAll(alarmsBean.getData());
        alarmAdapter.notifyDataSetChanged();
    }

    private void parseJson(String response) {
        Gson gson = new Gson();
        AlarmsBean alarmsBean = gson.fromJson(response, AlarmsBean.class);
//       重新初始化，当前对应级别的警告数量
        totalSize = alarmsBean.getRecordsTotal();
        List<AlarmsBean.DataBean> data = alarmsBean.getData();
        if (loadMore) {
            totalList.addAll(data);
            alarmAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        } else {
            totalList.clear();
            totalList.addAll(data);
            alarmAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishRefresh();
        }
    }
}
