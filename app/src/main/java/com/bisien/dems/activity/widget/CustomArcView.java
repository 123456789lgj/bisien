package com.bisien.dems.activity.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.bisien.dems.R;
import com.bisien.dems.activity.utils.UiUtils;

import java.util.ArrayList;

/**
 * Created by LGJ on 2019/6/22.
 * 参考下面博客
 * https://blog.csdn.net/zjb12316/article/details/65635577
 */

public class CustomArcView extends View {
    public static String TAG = CustomArcView.class + " ; lgj";
    RectF rectF;
    private Paint paint;
    private Paint textPain;

    public CustomArcView(Context context) {
        this(context, null);
    }

    public CustomArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void init() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
//        paint.setColor(Color.RED);
        rectF = new RectF(0, 0, UiUtils.dip2px(200), UiUtils.dip2px(200));
        textPain = new Paint();
        textPain.setColor(getResources().getColor(R.color.text_press));
        textPain.setAntiAlias(true);
        textPain.setStyle(Paint.Style.STROKE);
        textPain.setFakeBoldText(true); //true为粗体，false为非粗体
        textPain.setTextSize(UiUtils.sp2px(UiUtils.getContext(),30));

        //设置画笔类型为划桨，简单的画上一笔
//        paint.setStyle(Paint.Style.STROKE);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        float start = -90;//绘制开始的弧度
//        new Rect();
        //true代表包括圆心会绘制出来，否则的话不会绘制圆心
        for (int i = 0; i < list.size(); i++) {
            ViewBean viewBean = list.get(i);
            paint.setColor(viewBean.getColor());
            Log.i(TAG, "Start = " + start);
            canvas.drawArc(rectF, start, -viewBean.getAngle(), true, paint);

            start += viewBean.getAngle();//第二个view绘制的话直接从第一个view的起始位置开始
        }
        //绘制里面最浅颜色的圆
        paint.setColor(getResources().getColor(R.color.min_blue));
        canvas.drawCircle(UiUtils.dip2px(100), UiUtils.dip2px(100), UiUtils.dip2px(75), paint);
        canvas.drawText("1.5",UiUtils.dip2px(78), UiUtils.dip2px(90), textPain);
        canvas.drawText("PUE",UiUtils.dip2px(73), UiUtils.dip2px(130), textPain);
    }

    public CustomAnimal customAnimal;

    public void initAnimal() {
        customAnimal = new CustomAnimal(this, list);
        customAnimal.setDuration(1000);
        customAnimal.setFillAfter(true);
        customAnimal.setInterpolator(new AccelerateDecelerateInterpolator());
        startAnimation(customAnimal);

    }

    private ArrayList<ViewBean> list;

    public void viewBeanList(ArrayList<ViewBean> list) {
        this.list = list;
        initAnimal();

    }
}
