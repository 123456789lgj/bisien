package com.bisien.dems.activity.fragment;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.PopupWindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.AlarmsBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.DialogLoading;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.AlarmFilterDialog;
import com.bisien.dems.activity.widget.MyTestPopupWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class AlarmFragment extends BaseFragment implements View.OnClickListener{

    private TextView tvCurrentAlarm;
    private TextView tvHistoryAlarm;
    private ImageView ivFilterLevel;
    private ImageView filterDeviceAndManyMore;
    private ViewPager viewPagerAlarm;
    private RelativeLayout rlAnchor;
    private int currentSelect = 0;
    private int start = 0;
    private RealTimeAlarmFragment realTimeAlarm;
    private HistoryAlarmFragment historyAlarmFragment;
    private LinearLayout currentFilterIm;
    private View scrollDivide;
    private int[] startScrollDivide;
    private int[] endScrollDivide;
    private int dy;//总共滑动的距离

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_alarm, null);

        tvCurrentAlarm = view.findViewById(R.id.tvCurrentAlarm);
        ivFilterLevel = view.findViewById(R.id.ivFilterLevel);
        tvHistoryAlarm = view.findViewById(R.id.tvHistoryAlarm);
        viewPagerAlarm = view.findViewById(R.id.viewPagerAlarm);
        currentFilterIm = view.findViewById(R.id.currentFilterIm);
        scrollDivide = view.findViewById(R.id.scrollDivide);
        filterDeviceAndManyMore = view.findViewById(R.id.filterDeviceAndManyMore);
        tvCurrentAlarm.measure(0,0);
        int measuredWidth = tvCurrentAlarm.getMeasuredWidth();
        ViewGroup.LayoutParams layoutParams = scrollDivide.getLayoutParams();
        layoutParams.width = measuredWidth;
        scrollDivide.setLayoutParams(layoutParams);
        rlAnchor = view.findViewById(R.id.rlAnchor);
//        tvCurrentAlarm.setOnClickListener(this);
//        ivFilterLevel.setOnClickListener(this);
        tvHistoryAlarm.setOnClickListener(this);
        currentFilterIm.setOnClickListener(this);
        filterDeviceAndManyMore.setOnClickListener(this);
        initStatus();
        return view;
    }

    private void initStatus() {
        if (currentSelect == 0){
            tvCurrentAlarm.setTextColor(UiUtils.getColor(R.color.text_press));
            tvHistoryAlarm.setTextColor(UiUtils.getColor(R.color.black));
            ivFilterLevel.setVisibility(View.VISIBLE);
            filterDeviceAndManyMore.setVisibility(View.VISIBLE);

        }else if(currentSelect == 1){
            tvCurrentAlarm.setTextColor(UiUtils.getColor(R.color.black));
            tvHistoryAlarm.setTextColor(UiUtils.getColor(R.color.text_press));
            ivFilterLevel.setVisibility(View.INVISIBLE);
            filterDeviceAndManyMore.setVisibility(View.GONE);
        }
    }

//    根据设备检测报警信息  {1} 最后一个参数应该是设备类型，设备类型的表名：tbl_base_equipmenttype
//http://localhost:8080/gledeye/rest/equipment/get_list_bycategory/{1}
// 根据设备id获取实时告警信息
//    http://localhost:8080/gledeye/rest/alarm/get_byequipmentid/{id}

// 获取全部的实时告警信息
    //http://localhost:8080/gledeye/rest/alarm/get_list
//    http://localhost:8080/gledeye/rest/alarm/get_list
//    public boolean switchNavigationBar = false;//标记当前是切换 导航栏进行选择，
    public void init(){
//        switchNavigationBar = true;
        tvCurrentAlarm.setText("实时告警");
        refreshData();
//        realTimeAlarm.
    }
// 第一次进来初始化对应的数据
    @Override
    protected void initData() {
//        refreshData();
    }
    public void clearFilterCondations(){
        alarmInfo = "";
        MyTestPopupWindow.currentSelect = 3;
        MyApplication.filterContitions.clear();
    }
    private ArrayList<BaseFragment> list = new ArrayList<>();
    private void parseJson(String response) {

        Gson gson = new Gson();
        AlarmsBean alarmsBean = gson.fromJson(response, AlarmsBean.class);
        if (realTimeAlarm != null){
//            第二次切换过来之后调用
//            从新赋值
            System.out.println("refreshData ---------------222222222");
            realTimeAlarm.alarmsBean = alarmsBean;
            realTimeAlarm.switchNav();
            if (alarmFilterDialog != null){
                alarmFilterDialog.clearAllSelectStatus();// 导航栏切换过来之后进行
            }
            return;
        }
        //--------------------------------------下面这些只初始化一次-------------------------------------------------------------
        realTimeAlarm = new RealTimeAlarmFragment(alarmsBean);
        historyAlarmFragment = new HistoryAlarmFragment();
        System.out.println("realTimeAlarm :" + realTimeAlarm);
//        警告级别选择时的回调
        realTimeAlarm.setFilterDataObtion(new RealTimeAlarmFragment.FilterDataObtion() {
            @Override
            public void complete() {
//                防止
                if (alarmInfo != null && !alarmInfo.equals("")){
                    tvCurrentAlarm.setText(alarmInfo);
                }
                DialogLoading.getInstance().dismissLoading();
            }
        });
        list.add(realTimeAlarm);
        list.add(historyAlarmFragment);
        System.out.println("realTimeAlarm :" + list.size());
        MyFragmentPagerAdapter myViewPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
//        没有显示就不会调用 pagerAdapter中的 instantiateItem 这个方法
        viewPagerAlarm.setAdapter(myViewPagerAdapter);
        viewPagerAlarm.setCurrentItem(0);


        startScrollDivide = new int[2];
        endScrollDivide = new int[2];



        viewPagerAlarm.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (startScrollDivide[0] == 0){
                    tvCurrentAlarm.getLocationInWindow(startScrollDivide);
                    tvHistoryAlarm.getLocationInWindow(endScrollDivide);
                    int startLocation = startScrollDivide[0];
                    int endLocation = endScrollDivide[0];
                    // 总共滑动的距离
                    dy = endLocation - startLocation;
                    System.out.println("--------------------dy :"+ dy);
                }
                System.out.println("dy :" + positionOffset);
//                控制页签 divide 联动
//                scrollDivide.scrollBy((int) (positionOffset * dy),0);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollDivide.getLayoutParams();
                layoutParams.leftMargin = (int) (UiUtils.dip2px(90) + dy * (position + positionOffset));
                scrollDivide.setLayoutParams(layoutParams);
            }
//最后选中监听
            @Override
            public void onPageSelected(int position) {
                currentSelect = position;
                initStatus();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //  值初始化一次，刚进来时调用
    public void refreshData(){
        DialogLoading.getInstance().showLoading(getActivity(),"加载中...");
        System.out.println("refreshData ---------------");
        //请求告警信息
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//      获取所有实时告警信息
        String url = GlobalConstants.getUrlFirst() + "rest/alarm/get_list_forweb";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("draw","622");
//        第一次start 分页请求 为0
        hashMap.put("start",start + "");
        hashMap.put("length","20");
//        {"stationId":-1,"houseId":-1,"equipmentId":-1,"alarmGrade":1,"startTime":"2010-01-01+00:00:00","endTime":"2019-09-11+14:02:33","equipmenttype":"-1"}
        HashMap<String, String> paramstrHashMap = new HashMap<>();
        paramstrHashMap.put("stationId","-1");
        paramstrHashMap.put("houseId","-1");
        paramstrHashMap.put("equipmentId","-1");
        paramstrHashMap.put("alarmGrade","-1");
        paramstrHashMap.put("startTime","-1");
        paramstrHashMap.put("endTime","-1");
        paramstrHashMap.put("equipmentType","-1");
        String paramstr = new Gson().toJson(paramstrHashMap);

        hashMap.put("paramstr",paramstr);
        myHttpUtils.getDataFromServiceByPost(url, hashMap, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                DialogLoading.getInstance().dismissLoading();
                parseJson(response);
            }

            @Override
            public void onNotOk(String msg) {
                DialogLoading.getInstance().dismissLoading();
                System.out.println("AlarmFragment onNotOk :" + msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHistoryAlarm:
                currentSelect = 1;
                initStatus();
                viewPagerAlarm.setCurrentItem(currentSelect,true);
                break;
            case R.id.currentFilterIm:
                if (currentSelect == 1){
                    currentSelect = 0;
                    initStatus();
//                    true 表示可以平滑的滚动到新选中的条目
                    viewPagerAlarm.setCurrentItem(currentSelect,true);
                    return;
                }
                showPopupWindow();
                break;
            case R.id.filterDeviceAndManyMore:
                if (currentSelect == 0){
                    showFilterDialog();
                }else if(currentSelect ==1){
//                    showFilterDialogHistory();
                }
                break;
        }
    }

    private void showFilterDialogHistory() {

    }

    AlarmFilterDialog alarmFilterDialog;
    private void showFilterDialog() {
//      许多告警条件的选择器，在屏幕右边的窗口
        if (alarmFilterDialog == null){
            alarmFilterDialog = new AlarmFilterDialog(getActivity(),this);
        }
        alarmFilterDialog.show();
    }

    private void showPopupWindow() {
//       告警级别的窗口
        MyTestPopupWindow mWindow = new MyTestPopupWindow(rlAnchor,getActivity(),this);
//        第一个参数是PopupWindow 第二个参数是显示的锚点，以哪个控件为基准点，第三和第四是以锚点为基准点进行偏移，
//        第五个参数是：弹出窗口相对于锚点的对齐方式
        PopupWindowCompat.showAsDropDown(mWindow, rlAnchor, 0, 0, Gravity.START);
//        判断popupWindow 消失的监听
        mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//               mWindow
                System.out.println("setOnDismissListener" + "   消失 ------");
                ObjectAnimator.ofFloat(ivFilterLevel,"rotation",180f, 0).setDuration(100).start();
            }
        });
        startAnimalArrow();
    }
//    alarmGrade :过滤警告级别  1表示一般，2表示重要，3表示紧急，如果没有级别那么就是  -1
//  paramstr	{"stationId":-1,"houseId":-1,"equipmentId":-1,"alarmGrade":1,"startTime":"2010-01-01+00:00:00","endTime":"2019-09-11+14:02:33","equipmenttype":"-1"}
//  stationId 是局站id    houseId 是房间信息比如二楼实验室，如果都是选择全部的话，那么就是   -1
//  equipmenttype

//    请求过滤的数据
    public String alarmInfo;
//    所有过滤条件请求都会调用这方法， alarmInfo 是 显示筛选那个告警级别
    public void requestAlarmData(String alarmInfo){
        if (!"".equals(alarmInfo)){
            this.alarmInfo = alarmInfo;
        }
        DialogLoading.getInstance().showLoading(getActivity(),"加载中...");
//        修改此变量是保证是当前是刷新数据而不是加载更多
        realTimeAlarm.loadMore = false;
//        过滤筛选条件
        realTimeAlarm.refreshData(0);
    }
    private void startAnimalArrow() {
        ObjectAnimator.ofFloat(ivFilterLevel,"rotation",0f, 180f).setDuration(100).start();
    }
    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
