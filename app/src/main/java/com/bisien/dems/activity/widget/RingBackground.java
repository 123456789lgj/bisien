package com.bisien.dems.activity.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.bisien.dems.R;
import com.bisien.dems.activity.utils.UiUtils;

public class RingBackground extends View {
    public RingBackground(Context context) {
        super(context);
        init();
    }

    public RingBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RingBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private Paint paint;
    private void init() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(UiUtils.getColor(R.color.mid_blue));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制中蓝的背景图片，
        paint.setColor(UiUtils.getColor(R.color.mid_blue));
        canvas.drawCircle(UiUtils.dip2px(100), UiUtils.dip2px(100), UiUtils.dip2px(100), paint);
//        绘制浅蓝的背景图片，目的是让中蓝的背景图片变成一个圆环，但是CustomArcView 最终也要绘制这个浅蓝的背景图片，所以不必要再次绘制
//        paint.setColor(UiUtils.getColor(R.color.min_blue));
//        canvas.drawCircle(UiUtils.dip2px(100), UiUtils.dip2px(100), UiUtils.dip2px(75), paint);
    }
}
