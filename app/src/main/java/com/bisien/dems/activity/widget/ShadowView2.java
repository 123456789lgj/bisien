package com.bisien.dems.activity.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.bisien.dems.activity.utils.UiUtils;

public class ShadowView2 extends Drawable {
    private Paint mShadowPaint;
    private Paint mBgPaint;
    private int mShapeRadius = UiUtils.dip2px(10);
    private int mShadowRadius = UiUtils.dip2px(10);//  即是 模糊半径又是图片切得圆角部分
    private View view;
    public ShadowView2(View view){
        this.view = view;
        mBgPaint = new Paint();
        mShadowPaint = new Paint();
//        阴影的颜色
        mShadowPaint.setColor(Color.TRANSPARENT);
        mShadowPaint.setAntiAlias(true);
//
//        radius:模糊半径，radius越大越模糊，越小越清晰，但是如果radius设置为0，则阴影消失不见
//        dx:阴影的横向偏移距离，正值向右偏移，负值向左偏移
//        dy:阴影的纵向偏移距离，正值向下偏移，负值向上偏移
//        color: 绘制阴影的画笔颜色，即阴影的颜色（对图片阴影无效）

        mShadowPaint.setShadowLayer(mShadowRadius, 0, 0, Color.parseColor("#A5000000"));
        mShadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

//   背景颜色
        mBgPaint.setColor(Color.parseColor("#ff0000"));
        mBgPaint.setAntiAlias(true);
    }
    @Override
    public void draw(Canvas canvas) {
//        rx  和 ry 是控制最底层绘制阴影的倒角
//        这个只是绘制倒角
        canvas.drawRoundRect(mRect, mShapeRadius, mShapeRadius, mShadowPaint);// 最下面是阴影部分
//        canvas.drawRoundRect(mRect, mShapeRadius, mShapeRadius, mBgPaint);// 这个是给内容设置颜色
    }
    private RectF mRect;
    private RectF mRectShadow;
//    setBounds 这个是确定总体的背景大小，根据是谁要设置这个背景，也就是真正的view
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top , right , bottom);
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();

        System.out.println("left :" + left + "top :" + top + "right :" + right + "bottom :" + bottom);
        System.out.println("paddingLeft :" + paddingLeft + "paddingTop :" +paddingTop+ "paddingRight :" + paddingRight + "paddingBottom :" + paddingBottom);
//        mRect = new RectF(left  + paddingLeft,top  + paddingTop,right- paddingRight,bottom-paddingBottom);
        mRect = new RectF(left,top,right,bottom);
    }

    @Override
    public void setAlpha(int i) {
        System.out.println("setAlpha :" + i);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        System.out.println("ColorFilter :" + colorFilter.toString());
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
