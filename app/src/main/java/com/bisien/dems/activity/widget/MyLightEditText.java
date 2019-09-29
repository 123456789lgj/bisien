package com.bisien.dems.activity.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

public class MyLightEditText extends AppCompatEditText {
    public MyLightEditText(Context context) {
        this(context,null);
    }

    public MyLightEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        Typeface typeFaceHold = Typeface.createFromAsset(getContext().getAssets(), "fonts/SourceHanSansCN-ExtraLight.otf");
        setTypeface(typeFaceHold);
    }
}
