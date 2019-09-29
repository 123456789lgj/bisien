package com.bisien.dems.activity.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bisien.dems.R;
import com.bisien.dems.activity.utils.UiUtils;

public class MyArcView extends View {

    private int measuredWidth;
    private int measuredHeight;

    public MyArcView(Context context) {
        this(context,null);
    }

    public MyArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private Paint paint;
    private RectF rectF;
    private Paint textPain;
    private void init() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(UiUtils.getColor(R.color.mid_blue));
        rectF = new RectF(0, 0, UiUtils.dip2px(240), UiUtils.dip2px(240));
        textPain = new Paint();
        textPain.setColor(getResources().getColor(R.color.text_press));
        textPain.setAntiAlias(true);
        textPain.setTextSize(UiUtils.sp2px(UiUtils.getContext(),14));
        TextView textView = new TextView(getContext());
//        textView.setTextSize(UiUtils.sp2px(UiUtils.getContext(),14));
        textView.setText("30%");
        textView.measure(0,0);
        measuredWidth = textView.getMeasuredWidth();
        measuredHeight = textView.getMeasuredHeight();
        Log.i("measuredWidth :" , measuredWidth +"");
        Log.i("measuredHeight :" , measuredHeight +"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制中蓝的背景图片，
        paint.setColor(UiUtils.getColor(R.color.mid_blue));
//
        canvas.drawArc(rectF,0, 270, true, paint);
        paint.setColor(UiUtils.getColor(R.color.text_press));
        canvas.drawArc(rectF,270, 90, true, paint);
//        textPain.setColor(Color.GREEN);

        canvas.drawText("25%",UiUtils.dip2px(240) - measuredWidth,measuredHeight,textPain);
        canvas.drawText("75%",0,UiUtils.dip2px(240),textPain);
    }
}
