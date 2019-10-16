package com.bisien.dems.activity.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bisien.dems.R;
import com.bisien.dems.activity.AccessControlActivity;
import com.bisien.dems.activity.DemsActivity;
import com.bisien.dems.activity.EnvironmentActivity;
import com.bisien.dems.activity.FireActivity;
import com.bisien.dems.activity.PowerSystemActivity;
import com.bisien.dems.activity.utils.UiUtils;
import com.bisien.dems.activity.widget.ViewBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


public class HomeFragment extends BaseFragment {
    public static String TAG = HomeFragment.class + " lgj";
    private ArrayList<ViewBean> list;
    private RecyclerView recyclerView;
    private LinearLayout pointContainer;
    int[] arr = new int[]{
            R.mipmap.home_dems_system,
            R.mipmap.home_access_control,
            R.mipmap.home_fire_system,
            R.mipmap.home_power_system,
            R.mipmap.home_environmental_system,
            R.mipmap.home_asset_my
    };
    private ViewPager adViewPager;
    //    private ImageView ivRed;
    private int distance;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //切换到下一个页面
            int currentItem = adViewPager.getCurrentItem();
            adViewPager.setCurrentItem(++currentItem);
//            adViewPager.setOnTouchListener();
            //继续发送消息, 形成类似递归的循环
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessageDelayed(1002, 3000);
        }
    };

    @Override
    public View initView() {
        Log.i(TAG, "initView ");
        final View view = View.inflate(getContext(), R.layout.fragment_home, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        adViewPager = view.findViewById(R.id.adViewPager);
        view.findViewById(R.id.myNestedScrollView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //停止轮播
                        //可以清除handler中的所有消息
//                        mHandler.removeCallbacksAndMessages(null);
                        System.out.println("initView NestedScrollView  ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("initView NestedScrollView  ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        //继续轮播
                        System.out.println("initView NestedScrollView  ACTION_UP");
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        adViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //停止轮播
                        //可以清除handler中的所有消息
                        System.out.println("initView ViewPager  ACTION_DOWN");
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("initView ViewPager  ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        //继续轮播
                        System.out.println("initView ViewPager  ACTION_UP");
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    default:
                        break;
                }
                return false;//返回false 代表viewpager
            }
        });
        pointContainer = view.findViewById(R.id.pointContainer);
//        ivRed = view.findViewById(R.id.ivRed);
        //展示recycleView 列表
        adViewPager.setAdapter(new PagerAdapter());
//        setCurrentItem必须在setAdapter 之后执行否则无效
        initPointContainer();
//        ivRed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @Override
//            public void onGlobalLayout() {
//                View viewFirst = pointContainer.getChildAt(0);
//                View viewSecond = pointContainer.getChildAt(1);
//                System.out.println("secondLeft : " + viewSecond.getLeft() + " firstLeft :" + viewFirst.getLeft());
//                distance = viewSecond.getLeft() - viewFirst.getLeft();
//                ivRed.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
        adViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//              当前位置对集合大小区域，返回 0 到1
//                position = position % adImage.length;
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivRed.getLayoutParams();
//                layoutParams.leftMargin = (int) ((positionOffset + position) * distance);
//                ivRed.setLayoutParams(layoutParams);
            }

            // 当viewpager 滑动到某一个界面的瞬时回调
            @Override
            public void onPageSelected(int position) {
                position = position % adImage.length;
                for (int i = 0; i < adImage.length; i++) {
                    ImageView point = (ImageView) pointContainer.getChildAt(i);
                    if (position != i) {
                        point.setImageResource(R.drawable.shap_point_gray);
                    } else {
                        point.setImageResource(R.drawable.shap_point_white);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adViewPager.setCurrentItem(adImage.length * 100000);
        mHandler.sendEmptyMessageDelayed(1002, 3000);
        return view;
    }

    private void initPointContainer() {
        for (int i = 0; i < adImage.length; i++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (LinearLayout.LayoutParams.WRAP_CONTENT));
            params.setMargins(UiUtils.dip2px(8), 0, 0, 0);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.shap_point_gray);
            pointContainer.addView(imageView);
        }
    }

    @Override
    protected void initData() {
        super.initData();

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(2, UiUtils.dip2px(16), true));
        //        禁用recyclerView 滑动
        recyclerView.setNestedScrollingEnabled(false);
//        防止进入界面后，recyclerView 滑动到顶端
        recyclerView.setFocusable(false);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
    }

    private int[] adImage = {R.mipmap.ad_one, R.mipmap.ad_two};

    class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            % 取余数
            ImageView imageView = new ImageView(getContext());
            position = position % adImage.length;
            imageView.setImageResource(adImage[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
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
                    intent.setClass(getContext(), AccessControlActivity.class);
                    break;
                case 2:
                    intent.setClass(getContext(), FireActivity.class);
                    break;
                case 3:
                    intent.setClass(getContext(), PowerSystemActivity.class);
                    break;
                case 4:
                    intent.setClass(getContext(), EnvironmentActivity.class);
                    break;
                case 5:
                    UiUtils.toast("后续开发中，敬请期待...");
                    return;
            }
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
