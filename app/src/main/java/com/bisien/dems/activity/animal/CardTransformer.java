package com.bisien.dems.activity.animal;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Bamboy on 2019/3/6.
 */
public class CardTransformer implements ViewPager.PageTransformer {
    /**
     * 动画类型 =》层叠
     */
    public final static int ANIM_TYPE_STACK = 1;
    /**
     * 动画类型 =》缩放
     */
    public final static int ANIM_TYPE_SCALE = 2;//页面缩放效果
    /**
     * 动画类型 =》风车
     */
    public final static int ANIM_TYPE_WINDMILL = 3;

    /**
     * 动画类型
     * 【ANIM_TYPE_STACK：层叠】
     * 【ANIM_TYPE_STACK：缩放】
     * 【ANIM_TYPE_WINDMILL：风车】
     */
    private int mTransformerType = ANIM_TYPE_STACK;// 确定当前到底使用哪种动画的型

    /**
     * 初始位移量
     */
    private int mTranslation = 61;

    /**
     * 初始缩放比例
     */
    private int mScale = 80;

    /**
     * 初始旋转角度_风车
     */
    private float mRotation = -40;
// ViewPager系统调用
    @Override
    public void transformPage(@NonNull View view, float position) { //实现ViewPager transformer这个接口
        System.out.println("transformPage position : " + position + " view :" + view);
//        view 表示当前应用动画的那个控件，

        switch (mTransformerType) {

            // 动画类型 =》层叠
            case ANIM_TYPE_STACK:
                // 层叠
                animStack(view, position);
                break;

            // 动画类型 =》缩放
            case ANIM_TYPE_SCALE:
                // 缩放
                animScale(view, position);
                break;

            // 动画类型 =》风车
            case ANIM_TYPE_WINDMILL:
                // 风车
                animWindmill(view, position);
                break;
        }

    }

    /**
     * 设置动画类型
     *
     * @param type 动画类型
     *             【ANIM_TYPE_STACK：层叠】
     *             【ANIM_TYPE_SCALE：缩放】
     *             【ANIM_TYPE_WINDMILL：风车】
     * @return 需要预加载的页数
     */
    public int setTransformerType(int type) {
        mTransformerType = type;

        switch (type) {

            // 动画类型 =》层叠
            case ANIM_TYPE_STACK://1
                // 层叠
                return 4;

            // 动画类型 =》缩放
            case ANIM_TYPE_SCALE:  //2
                // 缩放
                return 2;

            // 动画类型 =》风车
            case ANIM_TYPE_WINDMILL://3
                // 风车
                return 2;

            default:
                return 0;
        }
    }
//f709aac 当前
//    f1a7375  后一个
    /**
     * 层叠
     *
     * @param view
     * @param position
     */
    private void animStack(View view, float position) {
        //缩放比例
        System.out.println("animStack width :" + view.getWidth() + "-----" + position);
//        左滑，当前view的position的变化是 ： 0 -> -1
//              后一个view的position的变化是： 1 -> 0
//        右滑：
//              当前view的position变化是： 0 -> 1
//                前一个view的position变化是： -1 -> 0
        float scale = (view.getWidth() - mScale * position) / (float) (view.getWidth());
        System.out.println("animStack scale :" + scale);
        System.out.println("animStack height :" + view.getHeight());
        /*
        * 最开始的变化scale 的值为： 当前position值为0时 ：1
        *                            当前position值为1时 ：0.9259259
        *                            当前position值为2时 ：0.8518519
        *                            当前position值为3时 ：0.7777778
        *                            当前position值为4时 ：0.7037037
         * */

        // 设置卡片背景色
        if (view.getBackground() == null) {
            view.setBackgroundColor(0xFFFFFFFF);
        }
        // 设置Z轴及阴影
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTranslationZ(scale * 20);
        }
        // 设置横向缩放
        view.setScaleX(scale * 0.84f);
        // 设置纵向缩放
        view.setScaleY(scale * 0.78f);
//        我感觉这里处理的是向左滑动的view 的偏移量

        if (position <= 0.0f) {         // 当前页

            //X轴偏移
            view.setTranslationX((view.getWidth() / 3f * position));//当前位置偏移为0
            System.out.println("animStack setTranslationX :" + (view.getWidth() / 3f * position) + "positon :" + position);

            //打开点击事件
            view.setClickable(true);

        } else if (position > 0) {      // 后一页

            //X轴偏移
            view.setTranslationX((-view.getWidth() * position) + (mTranslation * position));
//          X 轴的偏移先从 -1080 +
//            mTranslation  2个卡片之间的偏移量
            //屏蔽点击事件
            view.setClickable(false);
        }
    }

    /**
     * 缩放
     *
     * @param view
     * @param position
     */
    private void animScale(View view, float position) {
        //缩放比例
        float scale = 1;

        if (position <= 0.0f) {         // 当前页

            // 计算缩放比例
            scale = (view.getWidth() + mScale * 5 * position) / (float) (view.getWidth());

            //X轴偏移
            view.setTranslationX(-(view.getWidth() / 3f * position));

            //打开点击事件
            view.setClickable(true);

        } else if (position > 0) {      // 后一页

            // 计算缩放比例
            scale = (view.getWidth() - mScale * 5 * position) / (float) (view.getWidth());

            //X轴偏移
            view.setTranslationX(-(view.getWidth() / 3f * position));

            //屏蔽点击事件
            view.setClickable(false);
        }

        // 设置卡片背景色
        if (view.getBackground() == null) {
            view.setBackgroundColor(0xFFFFFFFF);
        }
        // 设置Z轴及阴影
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTranslationZ(scale * 20);
        }
        // 设置横向缩放
        view.setScaleX(scale * 0.85f);
        // 设置纵向缩放
        view.setScaleY(scale * 0.85f);

    }

    /**
     * 风车
     *
     * @param view
     * @param position
     */
    private void animWindmill(View view, float position) {
        if (view.getBackground() == null) {
            // 设置卡片背景色
            view.setBackgroundColor(0xFFFFFFFF);
            // 设置横向缩放
            view.setScaleX(0.85f);
            // 设置纵向缩放
            view.setScaleY(0.85f);
        }

        if (position <= 0.0f) {         // 当前页

            // 旋转
            view.setRotation(mRotation * Math.abs(position));

            // Y轴位移
            view.setTranslationY(-(view.getHeight() / 10f * position));

            // Z轴 阴影
            float translationZ = (view.getWidth() + mScale * 5 * position) / (float) (view.getWidth());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setTranslationZ(translationZ * 20);
            }

            //打开点击事件
            view.setClickable(true);
        } else if (position > 0) {      // 后一页

            // 旋转
            view.setRotation(-(mRotation * Math.abs(position)));

            // Y轴位移
            view.setTranslationY((view.getHeight() / 10f * position));

            // Z轴 阴影
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                float translationZ = (view.getWidth() - mScale * 5 * position) / (float) (view.getWidth());
                view.setTranslationZ(translationZ * 20);
            }

            //屏蔽点击事件
            view.setClickable(false);
        }

        // 前后两页是否露角
        boolean showAngle = true;
        if (showAngle) {
            // X轴位移
            view.setTranslationX(-(view.getWidth() / 10f * position));
        } else {
            // X轴位移
            view.setTranslationX((view.getWidth() / 3f * position));
        }
    }

}
