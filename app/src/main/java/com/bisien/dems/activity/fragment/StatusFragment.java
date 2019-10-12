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
import com.bisien.dems.activity.StatusEnvActivity;
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
//    ViewPager的缩放比例，然后根据缩放比例进行添加view
    private float[] scaleX = {0.84f,0.777f,0.715f,0.653f};
    private float[] scaleY = {0.78f,0.722f,0.664f,0.606f};
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
//        ac:空调，sts:烟感，ths:温湿度，fs:水浸，ab:警铃，dc:配电柜，env:环境设备
        if(dataBean.getThs() != null){
            CustomBean customBean = new CustomBean();
            customBean.setEquipmentName("环境");//环境包括，温湿度，水浸，烟感
            customBean.setEquipmentCounts(dataBean.getThs().getCount()+dataBean.getSts().getCategory() + dataBean.getFs().getCount()+ "");//设备总数
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
//        initSource();
        for (int i=0;i<cardViewList.size();i++){
            addChildView(i);
        }
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
            cardViewList.get(i).llComponent.setTag(i);
            cardViewList.get(i).llComponent.setOnClickListener(clickListener);
        }
    }
//  动态添加view 防止图片压缩，规则就是先压缩然后进行放大，那么给用户的体验就是图片没有被进行缩放
    private void addChildView(int position) {
        ImageView imageView = new ImageView(UiUtils.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UiUtils.dip2px(68), UiUtils.dip2px(68));
        layoutParams.topMargin = UiUtils.dip2px(77);
        imageView.setImageResource(arrImage[position]);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleX(1/scaleX[position]);
        imageView.setScaleY(1/scaleY[position]);
        cardViewList.get(position).llComponent.addView(imageView);

        TextView textView = new TextView(UiUtils.getContext());
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvParams.topMargin = UiUtils.dip2px(43);
        textView.setText(dataList.get(position).getEquipmentName());
        textView.setTextColor(UiUtils.getColor(arrColor[position]));
        textView.setTextSize(23);
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);//设置
        textView.setLayoutParams(tvParams);
        cardViewList.get(position).llComponent.addView(textView);

        TextView textView2 = new TextView(UiUtils.getContext());
        LinearLayout.LayoutParams tvParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvParams2.topMargin = UiUtils.dip2px(43);
        textView2.setText("设备总数");
        textView2.setTextColor(UiUtils.getColor(arrColor[position]));
        textView2.setTextSize(23);
        TextPaint paint2 = textView2.getPaint();
        paint2.setFakeBoldText(true);//设置粗体显示
        textView2.setLayoutParams(tvParams2);
        cardViewList.get(position).llComponent.addView(textView2);

        TextView textView3 = new TextView(UiUtils.getContext());
        LinearLayout.LayoutParams tvParams3= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvParams3.topMargin = UiUtils.dip2px(43);
        textView3.setText(dataList.get(position).getEquipmentCounts());
        textView3.setTextColor(UiUtils.getColor(arrColor[position]));
        textView3.setTextSize(76);
        textView3.setLayoutParams(tvParams3);
        cardViewList.get(position).llComponent.addView(textView3);


        cardViewList.get(position).llComponent.setTag(position);
        cardViewList.get(position).llComponent.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index = (int) v.getTag();
            Intent intent = new Intent();
            switch (index) {
                case 0:
                    intent.setClass(UiUtils.getContext(), CondiditioningActivity.class);
                    break;
                case 1:
                    intent.setClass(UiUtils.getContext(), UpsActivity.class);
                    break;
                case 2:
                    intent.setClass(UiUtils.getContext(), PowerDisttibuteActivity.class);
                    break;
                case 3:
                    intent.setClass(UiUtils.getContext(), StatusEnvActivity.class);
                    break;
            }
            intent.putExtra("category",dataList.get(index).getCategory());
            startActivity(intent);
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
