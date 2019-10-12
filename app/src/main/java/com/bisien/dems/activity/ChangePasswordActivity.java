package com.bisien.dems.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.ChangePasswordBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MD5Utils;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.PrefUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class ChangePasswordActivity extends BaseActivity {

    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    private Button etDetermine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findTitle("修改密码");
        etOldPassword = findViewById(R.id.oldPassword);
        etNewPassword = findViewById(R.id.newPassword);
        etConfirmPassword = findViewById(R.id.confirmPassword);
        etDetermine = findViewById(R.id.determine);
        etDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
//        判断新密码和确认密码是否一致
        final String newPassword = etNewPassword.getText().toString().trim();
        final String confirmPassword = etConfirmPassword.getText().toString().trim();
        String oldPassword = etOldPassword.getText().toString().trim();
        if (newPassword == null || "".equals(newPassword) || confirmPassword == null || "".equals(confirmPassword) || oldPassword == null || "".equals(oldPassword)) {
            //账户或者密码为空
            UiUtils.toast("原始密码、新密码、确认密码都不能为空");
        } else if (!newPassword.equals(confirmPassword)) {
            UiUtils.toast("新密码和确认密码不相同");
        } else if (newPassword.equals(confirmPassword)) {
            String md5OldPassword = MD5Utils.getMd5(oldPassword);
            if (!md5OldPassword.equals(MyApplication.userPassword)){
                UiUtils.toast("您输入的原始密码错误");
                return;
            }
            showLoading("修改中...");
            MyHttpUtils myHttpUtils = new MyHttpUtils();
            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("request", "check_login")
//            访问网络的话，需要md5 加密;
            String md5 = MD5Utils.getMd5(confirmPassword);
            hashMap.put("password", md5);
            hashMap.put("id", MyApplication.userId + "");
            String url = GlobalConstants.getUrlFirst() + "rest/user/modify_password";
            ;
            myHttpUtils.getDataFromServiceByPostByJson(url, hashMap, new MyHttpUtils.OnNetResponseListener() {
                @Override
                public void onOk(String response) {
                    dismissLoading();
//                    saveAccount(username, password);
                    Gson gson = new Gson();
                    ChangePasswordBean loginUserBean = gson.fromJson(response, ChangePasswordBean.class);
                    if (loginUserBean != null) {
                        String code = loginUserBean.getCode();
                        if ("0".equals(code)) {
                            String setIp = PrefUtils.getString(UiUtils.getContext(), "password", "");
//                            修改密码成功之后存放密码
                            if (!TextUtils.isEmpty(setIp)) {
                                PrefUtils.putString(UiUtils.getContext(), "password", confirmPassword);
                            }
                            MyApplication.userPassword =  MD5Utils.getMd5(confirmPassword);
                            UiUtils.toast("修改成功");
                        } else {
                            UiUtils.toast("修改失败");
                        }
                    } else {
                        UiUtils.toast("修改失败");
                    }
                }

                @Override
                public void onNotOk(String msg) {
                    UiUtils.toast("修改失败");
                }
            });
        }

    }

}
