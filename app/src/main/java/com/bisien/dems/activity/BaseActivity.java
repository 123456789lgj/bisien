package com.bisien.dems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bisien.dems.R;
import com.bisien.dems.activity.utils.DialogLoading;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.from_right_in,R.anim.to_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        if (this instanceof LoginActivity){

        }else if(this instanceof MainActivity){

        }else {
            overridePendingTransition(R.anim.from_left_in,R.anim.to_right_out);
        }
    }
//    处理返回箭头和设置标题文字
    public void findTitle(String headTitle){
        findViewById(R.id.backArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(headTitle);
    }
    public void showLoading(String message){

        DialogLoading.getInstance().showLoading(this,message);
    }
    public void dismissLoading(){
        DialogLoading.getInstance().dismissLoading();
    }
    public void refreshSelectData(CondiditioningActivity.Bean bean){}


}
