package com.bisien.dems.activity.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bisien.dems.R;
import com.bisien.dems.activity.AppServiceActivity;
import com.bisien.dems.activity.ChangePasswordActivity;
import com.bisien.dems.activity.LoginActivity;
import com.bisien.dems.activity.VersionInfoActivity;
import com.bisien.dems.activity.utils.PrefUtils;
import com.bisien.dems.activity.utils.UiUtils;

public class MyFragment extends BaseFragment implements View.OnClickListener {
    public static String TAG = MyFragment.class + " lgj";
    private TextView tvPersonalCenter;
    private TextView tvChangePassword;
    private TextView tvService;
    private TextView tvVersionInformation;
    private TextView tvDropOut;

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_my, null);
        tvPersonalCenter = view.findViewById(R.id.personalCenter);
        tvChangePassword = view.findViewById(R.id.changePassword);
        tvService = view.findViewById(R.id.service);
        tvVersionInformation = view.findViewById(R.id.versionInformation);
        tvDropOut = view.findViewById(R.id.dropOut);
        tvPersonalCenter.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvVersionInformation.setOnClickListener(this);
        tvDropOut.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personalCenter:
                break;
            case R.id.changePassword:
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
                break;
            case R.id.service:
                startActivity(new Intent(getContext(), AppServiceActivity.class));
                break;
            case R.id.versionInformation:
                startActivity(new Intent(getContext(), VersionInfoActivity.class));
                break;
            case R.id.dropOut:
                //退出登录
                showDropOutDialog();

                break;
        }
    }

    private void showDropOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("温馨提示");
//        builder.setIcon()
        builder.setMessage("退出登录之后，需要重新登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PrefUtils.putString(UiUtils.getContext(),"isLogin","");
                UiUtils.toast("退出成功");
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
