package com.bisien.dems.activity.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bisien.dems.R;
import com.bisien.dems.activity.bean.AlarmConfirmBean;
import com.bisien.dems.activity.bean.AlarmsBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.DialogLoading;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OtherRequestBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class HistoryAlarmDialog extends Dialog implements View.OnClickListener{

    private  ImageView arrowDown;
    private  LinearLayout llGone;
    private EditText etContent;
    private TextView tvPlanOne;
    private TextView tvPlanTwo;
    private TextView tvPlanThree;
    private TextView tvConfirm;
    private TextView tvCancel;
    public AlarmsBean.DataBean dataBean;
    private Activity activity;
    private UpdateStatus updateStatus;
    public HistoryAlarmDialog(@NonNull Activity context, AlarmsBean.DataBean dataBean,UpdateStatus updateStatus) {
//        指定dialog的样式
        super(context,R.style.AlarmFilterDialog);
        this.dataBean = dataBean;
        this.activity = context;
        this.updateStatus = updateStatus;
//        指定dialog的布局
        setContentView(R.layout.dialog_alarm_history);
//        获取dialog所在的窗口对象
        Window window = getWindow();
//        获取当前窗口的属性（也就是布局参数）
        WindowManager.LayoutParams attributes = window.getAttributes();
//        在布局文件中配置match_parent 不管用
//        必须在
        int width = UiUtils.getWindowWidthAndHeight()[0];
        int height = UiUtils.getWindowWidthAndHeight()[1];
//        attributes.height = (int) (height * 0.3);
        attributes.width = (int) (width * 0.95);
        attributes.gravity = Gravity.CENTER ;
        window.setAttributes(attributes);
        initView();
    }
    public boolean llGoneIsShow = false;
    public void second(){
        System.out.println("clearAnimatiom .......");
//        arrowDown.clearAnimation();
//         arrowDown.clearAnimation() 清除不了属性动画，只能通过下面的这种方式进行清除
        System.out.println("llGone :" + llGone.isShown());
        if (llGoneIsShow){
            ObjectAnimator.ofFloat(arrowDown,"rotation",180f, 0).setDuration(0).start();
        }
        etContent.setText(null);
        etContent.setHint("输入说明内容");
        llGone.setVisibility(View.GONE);
    }

    public interface UpdateStatus{
        void upDateItemStatus();
    }
    private void initView() {
        arrowDown = findViewById(R.id.arrowDown);
        llGone = findViewById(R.id.llGone);
        etContent = findViewById(R.id.etContent);
        tvPlanOne = findViewById(R.id.tvPlanOne);
        tvPlanTwo = findViewById(R.id.tvPlanTwo);
        tvPlanThree = findViewById(R.id.tvPlanThree);
        tvConfirm = findViewById(R.id.tvConfirm);
        tvCancel = findViewById(R.id.tvCancel);

        arrowDown.setOnClickListener(this);
        tvPlanOne.setOnClickListener(this);
        tvPlanTwo.setOnClickListener(this);
        tvPlanThree.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        etContent.setSelection(etContent.getText().length());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvPlanOne:
                setContent(tvPlanOne.getText()+"");
                break;
            case R.id.tvPlanTwo:
                setContent(tvPlanTwo.getText()+"");
                break;
            case R.id.tvPlanThree:
                setContent(tvPlanThree.getText()+"");
                break;
            case R.id.tvConfirm:
//             请求网络进行修改备注
                putHttp();
                break;
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.arrowDown:
                if (llGone.isShown()){
                    llGone.setVisibility(View.GONE);
                    ObjectAnimator.ofFloat(arrowDown,"rotation",180f, 0).setDuration(100).start();
                    llGoneIsShow = false;
                }else {
                    llGone.setVisibility(View.VISIBLE);
                    ObjectAnimator.ofFloat(arrowDown,"rotation",0f, 180f).setDuration(100).start();
                    llGoneIsShow = true;
                }

                break;
        }
    }

    private void putHttp() {
        String content = etContent.getText().toString();
        if (content != null && !content.equals("")){
            dismiss();
            DialogLoading.getInstance().showLoading(activity,"告警信息确认中...");
//            [{"id":1862563857495035,"description":null,"name":"烟感告警",
//            "stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器",
//            "stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,
//            "alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,
//            "category":2,"startTime":"2019-07-23 12:51:35","endTime":null,"confirmTime":"2019-09-19 11:25:19",
//            "confirmer":"admin","confirmRemark":"fadsfa"}]
            dataBean.setConfirmRemark(content);
//            System.out.println(dataBean.toString());
            Gson gson = new Gson();
            String contentStr = gson.toJson(dataBean);
            String newStr = "[" + contentStr + "]";
            final String url = GlobalConstants.getUrlFirst() + "rest/alarm/confirm_batch";
//requestBody 中第一个参数是请求头的类型为json ，第二个参数为json字符串
            RequestCall build  = OkHttpUtils.put().url(url).requestBody(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), newStr)).addHeader("user","-1").build();
//            下面这种返回时415，还是类型不对
//            RequestCall build = OkHttpUtils.put().url(url).requestBody(newStr).addHeader("Content-Type", "application/json;charset=utf-8").addHeader("user","-1").build();
            build.execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    System.out.println("HistoryAlarmDialog onError :" + e.getMessage());
                    DialogLoading.getInstance().dismissLoading();
                    UiUtils.toast("确认告警信息失败");
                }

                @Override
                public void onResponse(String response, int id) {
                    DialogLoading.getInstance().dismissLoading();
                    System.out.println("HistoryAlarmDialog onResponse :" + response);
                    AlarmConfirmBean alarmConfirmBean = new Gson().fromJson(response, AlarmConfirmBean.class);
                    if (alarmConfirmBean.getCode() != null && alarmConfirmBean.getCode().equals("0")){
                        if (updateStatus != null){
                            System.out.println("HistoryAlarmDialog upDateItemStatus-----");
                            updateStatus.upDateItemStatus();
                        }
                        UiUtils.toast("确认告警信息成功");
                    }else {
                        UiUtils.toast("确认告警信息失败");
                    }
                }
            });

        }else {
            UiUtils.toast("备注信息不能为空");
        }
    }

    private void setContent(String content){
        etContent.setText(content);
        llGone.setVisibility(View.GONE);
        // 刚进来就让光标定位在字的后面
        etContent.setSelection(content.length());
    }
}
