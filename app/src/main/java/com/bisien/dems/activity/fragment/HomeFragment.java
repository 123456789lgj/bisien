package com.bisien.dems.activity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bisien.dems.R;
import com.bisien.dems.activity.AccessControlActivity;
import com.bisien.dems.activity.AssetsManagerActivity;
import com.bisien.dems.activity.DemsActivity;
import com.bisien.dems.activity.EnvironmentActivity;
import com.bisien.dems.activity.FireActivity;
import com.bisien.dems.activity.LoginActivity;
import com.bisien.dems.activity.PowerSystemActivity;
import com.bisien.dems.activity.SplashActivity;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.CustomArcView;
import com.bisien.dems.activity.widget.ShadowDrawable;
import com.bisien.dems.activity.widget.ShadowView2;
import com.bisien.dems.activity.widget.ViewBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment {
    public static String TAG = HomeFragment.class + " lgj";
    private ArrayList<ViewBean> list;
    private RecyclerView recyclerView;
    int[] arr = new int[]{
            R.mipmap.home_dems_system,
            R.mipmap.home_asset_my,
            R.mipmap.home_access_control,
            R.mipmap.home_fire_system,
            R.mipmap.home_power_system,
            R.mipmap.home_environmental_system};


    @Override
    public View initView() {
        Log.i(TAG, "initView ");
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        recyclerView = view.findViewById(R.id.recyclerView);
//        list = new ArrayList<>();
//        //设置真正需要显示的颜色
//        ViewBean viewBean = new ViewBean();
//        viewBean.setColor(getResources().getColor(R.color.text_press));
//        viewBean.setData(270f);
//        list.add(viewBean);
        //设置底层背景
//        ViewBean viewBeanBackgroud = new ViewBean();
//        viewBeanBackgroud.setColor(getResources().getColor(R.color.mid_blue));
//        viewBeanBackgroud.setData(90f);
//        list.add(viewBeanBackgroud);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        //展示recycleView 列表
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(2, UiUtils.dip2px(16), true));
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(getContext(), R.layout.adapter_recycler_home, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //设置图片ID
            holder.imageView.setImageResource(arr[position]);
//            holder.llBackground.setBackgroundResource(arrBg[position]);
            holder.llBackground.setTag(position);
            holder.llBackground.setOnClickListener(clickListener);
//            ShadowDrawable.setShadowDrawable(holder.llBackground, Color.parseColor("#00000000"), UiUtils.dip2px(10),
//                    Color.parseColor("#8C000000"), UiUtils.dip2px(10), 0, 0);
        }

        @Override
        public int getItemCount() {
            return arr.length;
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private LinearLayout llBackground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            llBackground = itemView.findViewById(R.id.home_bg);
        }
    }

    public void startAnimal(final View view, final Intent intent) {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.1f, 1f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(false);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setDuration(200);
        view.startAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "onAnimationEnd");
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();

            Intent intent = new Intent();
            switch (tag) {
                case 0:
                    intent.setClass(getContext(), DemsActivity.class);
                    break;
                case 1:
                    intent.setClass(getContext(), AssetsManagerActivity.class);
                    break;
                case 2:
                    intent.setClass(getContext(), AccessControlActivity.class);
                    break;
                case 3:
                    intent.setClass(getContext(), FireActivity.class);
                    break;
                case 4:
                    intent.setClass(getContext(), PowerSystemActivity.class);
                    break;
                case 5:
                    intent.setClass(getContext(), EnvironmentActivity.class);
                    break;
            }
//            UiUtils.toast("第" + tag + "条目");
//            if (tag == 0 || tag == 1 || tag == 2 || tag == 4 || tag == 5){
//            startAnimal(v,intent);
//            }
            startActivity(intent);
        }
    };

    //    参考这篇博客https://www.jianshu.com/p/e372cec819db
    class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public SpaceItemDecoration(int spanCount, int space, boolean includeEdge) {
            this.spacing = space;
            this.spanCount = spanCount;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //这里是关键，需要根据你有几列来判断
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
//            Log.i(TAG, "position :" + position);
//            Log.i(TAG, "column :" + column);
//            if (column == 0){
//                outRect.left = spacing;
//                outRect.right = spacing /spanCount;
//            }else if (column == 1){
//                outRect.left = spacing /spanCount;
//                outRect.right = spacing;
//            }
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
