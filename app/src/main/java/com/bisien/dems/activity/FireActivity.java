package com.bisien.dems.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bisien.dems.R;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.tabfragment.BaseTabFragment;
import com.bisien.dems.activity.tabfragment.TabLayoutFragment;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FireActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<BaseTabFragment> arrayList = new ArrayList<>();
    private ArrayList<String> topNavigation = new ArrayList<>();
    private List<EquipmentBean.DataBean> dataList;
    private TextView oneItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        findTitle("消防系统");
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        oneItem = findViewById(R.id.oneItem);
        initData();
    }

    private void initData() {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/equipment/get_list";
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {

                Gson gson = new Gson();
                EquipmentBean equipmentBean = gson.fromJson(response, EquipmentBean.class);
                dataList = equipmentBean.getData();
                nextVisableUI();
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                System.out.println("DemsActivity :onNotOk :" + msg);
            }
        });
    }
    //    集合进行分类
    private ArrayList<EquipmentBean.DataBean> listSmoke =  new ArrayList<EquipmentBean.DataBean>();
    private ArrayList<EquipmentBean.DataBean> listSkylight =  new ArrayList<EquipmentBean.DataBean>();//天窗
    private ArrayList<EquipmentBean.DataBean> listConditioning =  new ArrayList<EquipmentBean.DataBean>();//空调
    private ArrayList<EquipmentBean.DataBean> listUPS =  new ArrayList<EquipmentBean.DataBean>();//空调
    private void nextVisableUI() {
//      topNavigation 顶部导航栏
        for (int i = 0; i < dataList.size(); i++) {
            EquipmentBean.DataBean dataBean = dataList.get(i);
            String name = dataBean.getName();
            if (name.contains("烟")){
                if (listSmoke.size() == 0){
                    topNavigation.add(name);
                }
                listSmoke.add(dataBean);
            }else if (name.contains("天窗")){
                if (listSkylight.size() == 0){
                    topNavigation.add(name);
                }
                topNavigation.add(name);
            }else if (name.contains("空调")){
                if (listConditioning.size() == 0){
                    topNavigation.add(name);
                }
                listConditioning.add(dataBean);
            }else if (name.contains("UPS")){
                if (listUPS.size() == 0){
                    topNavigation.add(name);
                }
                listUPS.add(dataBean);
            }
        }
        for (int i = 0; i < topNavigation.size(); i++) {
            if (topNavigation.get(i).contains("天窗")){
                TabLayoutFragment tabLayoutFragment = new TabLayoutFragment(listSkylight);
                arrayList.add(tabLayoutFragment);
            }else if(topNavigation.get(i).contains("烟")){
                TabLayoutFragment tabLayoutFragment = new TabLayoutFragment(listSmoke);
                arrayList.add(tabLayoutFragment);
            }else if(topNavigation.get(i).contains("空调")){
                TabLayoutFragment tabLayoutFragment = new TabLayoutFragment(listConditioning);
                arrayList.add(tabLayoutFragment);
            }else if(topNavigation.get(i).contains("UPS")){
                TabLayoutFragment tabLayoutFragment = new TabLayoutFragment(listUPS);
                arrayList.add(tabLayoutFragment);
            }

        }
        Display display = getWindowManager().getDefaultDisplay();
        final int width = display.getWidth();
        Log.i("width",width + "");
        viewPager.setAdapter(new FireActivity.MyAdapter(getSupportFragmentManager()));
        dismissLoading();
        if(topNavigation.size() == 1){

            oneItem.setText(topNavigation.get(0));
            oneItem.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
//            tabLayout.setMinimumWidth(width);
//            tabLayout.invalidate();
        }else if(topNavigation.size() == 2){
//            tabLayout.setMinimumWidth(width/2);
//            tabLayout.invalidate();
            oneItem.setVisibility(View.GONE);
            tabLayout.setVisibility(View.VISIBLE);
//        将TabLayout和ViewPager联动
            tabLayout.setupWithViewPager(viewPager);
            setLayoutWidth();
        }



//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        设置TabLayout的模式为滑动模式
//        TabLayout 默认的模式为Fixed



    }
    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return topNavigation.get(position);
        }
    }
    public void setLayoutWidth(){
            tabLayout.post(new Runnable() {
            @Override
            public void run() {
                // 拿到tabLayout的slidingTabIndicator属性
                try {
                    Field slidingTabIndicatorField = tabLayout.getClass().getDeclaredField("slidingTabIndicator");
                    slidingTabIndicatorField.setAccessible(true);

                    LinearLayout mTabStrip = (LinearLayout) slidingTabIndicatorField.get(tabLayout);
                    Log.i("tabLayout", mTabStrip.getChildCount() +"   --");
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
//                        拿到tabview的textview属性
                        Field textViewField = tabView.getClass().getDeclaredField("textView");
                        textViewField.setAccessible(true);
                        TextView mTextView = (TextView) textViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);

//                        int width = mTextView.getWidth();
//                        if (width == 0) {
//                            mTextView.measure(0, 0);
//                            width = mTextView.getMeasuredWidth();
//                        }
//                        控制Item条目的宽度，这种设置的话就是等分
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        layoutParams.width = 0;
                        layoutParams.weight = 1;
                        tabView.setLayoutParams(layoutParams);
                        tabView.invalidate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
