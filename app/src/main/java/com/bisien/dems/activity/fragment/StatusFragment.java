package com.bisien.dems.activity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bisien.dems.R;
import com.bisien.dems.activity.CondiditioningActivity;
import com.bisien.dems.activity.PowerDisttibuteActivity;
import com.bisien.dems.activity.UpsActivity;
import com.bisien.dems.activity.animal.CardTransformer;
import com.bisien.dems.activity.bean.CustomBean;
import com.bisien.dems.activity.bean.DemsHomeBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.DialogLoading;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StatusFragment extends BaseFragment {

    private ViewPager cardViewPager;
    private ArrayList<CardFragment> cardViewList = new ArrayList<>();
    int[] arrImage = new int[]{
            R.mipmap.ic_status_conditioning,
            R.mipmap.ic_status_ups,
            R.mipmap.ic_status_power,
            R.mipmap.ic_status_environment
    };
    int[] arrColor = new int[]{
        R.color.status_conditioning,
        R.color.status_ups,
        R.color.status_power,
        R.color.status_environment,
    };
    private ArrayList<CustomBean> dataList = new ArrayList<>();
    /**
     * 切换动画
     */
    private CardTransformer mTransformer;
    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_status, null);
        cardViewPager = view.findViewById(R.id.cardViewPager);
        return view;
    }
//    这边只是第一次切换过来的时候回出现
    public void getServiceData(){
        if (cardViewList.size() != 0){
            System.out.println("cardViewList size :" + cardViewList.size());
            return;
        }
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所有的设备信息和设备的运行状态
        String url = GlobalConstants.getUrlFirst() + "rest/basicdata/get_homepagedata_forweb";
        DialogLoading.getInstance().showLoading(getActivity(),"加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                Gson gson = new Gson();
                DemsHomeBean demsHomeBean = gson.fromJson(response, DemsHomeBean.class);
                DemsHomeBean.DataBean data = demsHomeBean.getData();
                parseData(data);

            }

            @Override
            public void onNotOk(String msg) {
                DialogLoading.getInstance().dismissLoading();
                System.out.println("DemsActivity :onNotOk :" + msg);
            }
        });
    }

    private void parseData(DemsHomeBean.DataBean  dataBean) {
        if(dataList.size() != 0){//防止快速点击出现数据错乱
            return;
        }
        if(dataBean.getAc() != null){
            CustomBean customBean = new CustomBean();
            customBean.setEquipmentName("空调");//空调
            customBean.setEquipmentCounts(dataBean.getAc().getCount()+"");//设备总数
            customBean.setRunEquipmentCounts(dataBean.getAc().getParam2()+"");//运行数量
            customBean.setEquipmentAlarm(dataBean.getAcAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            customBean.setCategory(dataBean.getAc().getCategory()+"");
            dataList.add(customBean);
        }
        if(dataBean.getUps() != null){
            CustomBean customBean = new CustomBean();
            customBean.setEquipmentName("UPS");//空调
            customBean.setEquipmentCounts(dataBean.getUps().getCount()+"");//设备总数
            customBean.setRunEquipmentCounts(dataBean.getUps().getParam2()+"");//运行数量
            customBean.setEquipmentAlarm(dataBean.getUpsAlarm() + "");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            customBean.setCategory(dataBean.getUps().getCategory()+"");
            dataList.add(customBean);
        }
        if(dataBean.getDc() != null){
            CustomBean customBean = new CustomBean();
            customBean.setEquipmentName("配电");//空调
            customBean.setEquipmentCounts(dataBean.getDc().getCount()+"");//设备总数
            customBean.setRunEquipmentCounts(dataBean.getDc().getParam2()+"");//运行数量
            customBean.setEquipmentAlarm(dataBean.getDcAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            customBean.setCategory(dataBean.getDc().getCategory()+"");
            dataList.add(customBean);
        }
        if(dataBean.getThs() != null){
            CustomBean customBean = new CustomBean();
            customBean.setEquipmentName("温湿度感应器");//空调
            customBean.setEquipmentCounts(dataBean.getThs().getCount()+"");//设备总数
            customBean.setRunEquipmentCounts(dataBean.getThs().getParam2()+"");//运行数量
            customBean.setEquipmentAlarm(dataBean.getThsAlarm()+"");//报警数量，如果为0，web端，就显示正常，否则就显示异常
            customBean.setCategory(dataBean.getThs().getCategory()+"");
            dataList.add(customBean);
        }
        DialogLoading.getInstance().dismissLoading();
        initSwitchNav();
    }

    public void initSwitchNav(){
        for (int i= 0;i<4;i++){
            cardViewList.add(new CardFragment());
        }
        cardViewPager.setAdapter(new CardPagerAdapter(getFragmentManager()));
        // 实例化ViewPager切换动画类
        mTransformer = new CardTransformer();
        cardViewPager.setPageTransformer(true,mTransformer);
        // 设置切换动画为 层叠效果，并获取预加载数量
        int offscreen = mTransformer.setTransformerType(CardTransformer.ANIM_TYPE_STACK);
        // 设置ViewPager的预加载数量
        cardViewPager.setOffscreenPageLimit(4);
//      如果没有切换的话，就执行不了
        cardViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                System.out.println("setOnPageChangeListener position: " + position);

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        如果当前的ViewPager 没有出现的话，调用setCurrentItem 也是没有用的
//        cardViewPager.setCurrentItem(0);
        initSource();
    }
    @Override
    protected void initData() {
        super.initData();

    }
    public void initSource(){
        for (int i = 0; i < cardViewList.size(); i++) {
            cardViewList.get(i).ivCardViewLogo.setImageResource(arrImage[i]);
            cardViewList.get(i).cardViewDeviceName.setTextColor(UiUtils.getColor(arrColor[i]));
            cardViewList.get(i).cardViewDeviceSum.setTextColor(UiUtils.getColor(arrColor[i]));
            cardViewList.get(i).cardViewDeviceSumName.setTextColor(UiUtils.getColor(arrColor[i]));

            cardViewList.get(i).cardViewDeviceName.setText(dataList.get(i).getEquipmentName());
            cardViewList.get(i).cardViewDeviceSum.setText(dataList.get(i).getEquipmentCounts());
            cardViewList.get(i).view.setTag(i);
            cardViewList.get(i).view.setOnClickListener(clickListener);

        }
    }

//    private void addChildView(int position) {
//        ImageView imageView = new ImageView(UiUtils.getContext());
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.topMargin = UiUtils.dip2px(60);
//        imageView.setImageResource(arrImage[position]);
//        imageView.setLayoutParams(layoutParams);
//
//
//
//        TextView textView = new TextView(UiUtils.getContext());
//        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        tvParams.topMargin = UiUtils.dip2px(34);
//        textView.setText(dataList.get(position).getEquipmentName());
//        textView.setTextColor(UiUtils.getColor(arrColor[position]));
//        textView.setTextSize(18);
//        TextPaint paint = textView.getPaint();
//        paint.setFakeBoldText(true);//设置
//        textView.setLayoutParams(tvParams);
//
//        TextView textView2 = new TextView(UiUtils.getContext());
//        LinearLayout.LayoutParams tvParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        tvParams2.topMargin = UiUtils.dip2px(34);
//        textView2.setText("设备总数");
//        textView2.setTextColor(UiUtils.getColor(arrColor[position]));
//        textView2.setTextSize(18);
//        TextPaint paint2 = textView.getPaint();
//        paint2.setFakeBoldText(true);//设置
//        textView2.setLayoutParams(tvParams2);
//
//        TextView textView3 = new TextView(UiUtils.getContext());
//        LinearLayout.LayoutParams tvParams3= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        tvParams3.topMargin = UiUtils.dip2px(34);
//        textView3.setText(dataList.get(position).getEquipmentCounts());
//        textView3.setTextColor(UiUtils.getColor(arrColor[position]));
//        textView3.setTextSize(60);
//        textView3.setLayoutParams(tvParams3);
//    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index = (int) v.getTag();
//            UiUtils.toast(dataList.get(tag).getCategory());
            Intent intent = new Intent();
            switch (index) {
                case 0:
                    intent.setClass(UiUtils.getContext(), CondiditioningActivity.class);
                    intent.putExtra("category",dataList.get(index).getCategory());
                    break;
                case 1:
                    intent.setClass(UiUtils.getContext(), UpsActivity.class);
                    intent.putExtra("category",dataList.get(index).getCategory());
                    break;
                case 2:
                    intent.setClass(UiUtils.getContext(), PowerDisttibuteActivity.class);
                    intent.putExtra("category",dataList.get(index).getCategory());
                    break;
                case 3:
                    break;
            }
            if (index == 0 || index == 1 || index == 2){
                startActivity(intent);
            }
        }
    };

    //   只要继承FragmentPagerAdapter 的fragment  它们的生命周期是切换到当前的fragment才会触发
    class CardPagerAdapter extends FragmentPagerAdapter{

        public CardPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return cardViewList.get(position);
        }

        @Override
        public int getCount() {
            return cardViewList.size();
        }
    }
}
