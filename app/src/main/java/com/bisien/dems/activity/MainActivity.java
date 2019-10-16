package com.bisien.dems.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.fragment.AlarmFragment;
import com.bisien.dems.activity.fragment.BaseFragment;
import com.bisien.dems.activity.fragment.FragmentFactory;
import com.bisien.dems.activity.fragment.HomeFragment;
import com.bisien.dems.activity.fragment.StatusFragment;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.MyTestPopupWindow;

public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private FrameLayout mainFrameLayout;
    public static String TAG = MainActivity.class + " lgj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.rgRadioGroup);
//        radioGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int height = radioGroup.getHeight();
//                System.out.println("height :" + height);
//            }
//        });
        mainFrameLayout = findViewById(R.id.mainFrameLayout);
        initFragment();
        switchFragment(0);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
//                Log.i(TAG, "checkId = " + checkId);
                switch (checkId) {
                    case R.id.rbHome:
                        switchFragment(0);
                        break;
                    case R.id.rbAlarm:
                        switchFragment(1);
                        break;
                    case R.id.rbStatus:
                        switchFragment(2);
                        break;
                    case R.id.rbMy:
                        switchFragment(3);
                        break;
                }
            }
        });
        radioGroup.check(R.id.rbHome);
    }

    //初始化所有的Fragment
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(0));
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(1));
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(2));
        transaction.add(R.id.mainFrameLayout, FragmentFactory.getFragment(3));
        transaction.commit();
    }

    //显示选中的Fragment
    private void switchFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        BaseFragment fragment = FragmentFactory.getFragment(position);
        //当切换Fragment的时候，先把所有的Fragment隐藏起来
        transaction.hide(FragmentFactory.getFragment(0));
        transaction.hide(FragmentFactory.getFragment(1));
        transaction.hide(FragmentFactory.getFragment(2));
        transaction.hide(FragmentFactory.getFragment(3));
//        transaction.re
        //显示点中的Fragment对象
        transaction.show(fragment);
        AlarmFragment alarmFragment = (AlarmFragment) FragmentFactory.getFragment(1);
        if(position == 1){
            alarmFragment.init();
        }else {
            alarmFragment.clearFilterCondations();
        }
        if (position == 2){
            StatusFragment statusFragment  = (StatusFragment) FragmentFactory.getFragment(2);
            statusFragment.getServiceData();
        }

        transaction.commit();
    }
//    点击两次返回到桌面
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                UiUtils.toast( "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
//        退出应用程序清空当前的过滤条件
//        清空缓存保证，每次进来之后都是新的fragment，防止
//        清空Handler中的消息
        HomeFragment homeFragment = (HomeFragment) FragmentFactory.getFragment(0);
        homeFragment.mHandler.removeCallbacksAndMessages(null);
        FragmentFactory.sSavedFragment.clear();
        MyApplication.filterContitions.clear();
//        清理全局数据
        MyApplication.houseMap.clear();
        MyApplication.stationMap.clear();
        MyApplication.equipments.clear();
        MyApplication.equipmentTypeName.clear();

        super.onDestroy();
    }
}
