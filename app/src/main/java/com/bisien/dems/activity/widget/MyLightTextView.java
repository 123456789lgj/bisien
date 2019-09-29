package com.bisien.dems.activity.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import static com.bisien.dems.activity.utils.UiUtils.getContext;

public class MyLightTextView extends AppCompatTextView {
    public MyLightTextView(Context context) {
        this(context, null);
    }

    public MyLightTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface typeFaceHold = Typeface.createFromAsset(getContext().getAssets(), "fonts/SourceHanSansCN-ExtraLight.otf");
        setTypeface(typeFaceHold);
    }
}
