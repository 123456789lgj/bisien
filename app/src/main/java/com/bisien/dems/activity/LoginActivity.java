package com.bisien.dems.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.LoginUserBean;
import com.bisien.dems.activity.bean.ValidateTimeBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.global.GlobalDataInit;
import com.bisien.dems.activity.utils.Base64;
import com.bisien.dems.activity.utils.MD5Utils;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.PrefUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    public static String TAG = LoginActivity.class + " lgj";
    private EditText etUserName;
    private EditText etPassword;
    private EditText etIp;
    private Button btnLogin;
    private Dialog dialog;
    private ImageView ivClearIp;
    private ImageView ivClearAccount;
    private ImageView ivSwitch;
    private CheckBox cbPassword;
    private CheckBox cbIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etIp = findViewById(R.id.etIp);
        btnLogin = findViewById(R.id.btnLogin);

        //之前的交互方式，就是new 了三个对象
        etIp.addTextChangedListener(new MyTextWatcher(etIp));
        etUserName.addTextChangedListener(new MyTextWatcher(etUserName));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));

        ivClearIp = findViewById(R.id.iv_clear_ip);
        ivClearAccount = findViewById(R.id.iv_clear_account);
        ivSwitch = findViewById(R.id.iv_switch);
        cbPassword = findViewById(R.id.cbPassword);
        cbIp = findViewById(R.id.cbIp);
//给edittext后面的图片添加点击事件
        ivClearIp.setOnClickListener(this);
        ivClearAccount.setOnClickListener(this);
        ivSwitch.setOnClickListener(this);
        cbPassword.setOnClickListener(this);
        cbIp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initStatus();
    }

    private void initStatus() {
        String setIp = PrefUtils.getString(UiUtils.getContext(), "setIp", "");
        String name = PrefUtils.getString(UiUtils.getContext(), "name", "");
        String password = PrefUtils.getString(UiUtils.getContext(), "password", "");
//        ip 不为空
        if(!TextUtils.isEmpty(setIp)){
//            把当前IP保存在全局变量里面
            GlobalConstants.ip = setIp;
            cbIp.setChecked(true);
            ivClearIp.setVisibility(View.VISIBLE);
            etIp.setText(setIp);
//            刚进来就让光标定位在字的后面
            etIp.setSelection(setIp.length());
        }else{
            cbIp.setChecked(false);
            ivClearIp.setVisibility(View.GONE);
        }
//        显示用户名和密码
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){
            cbPassword.setChecked(true);
            ivClearAccount.setVisibility(View.VISIBLE);
            etUserName.setText(name);
            etPassword.setText(password);
        }else{
            cbPassword.setChecked(false);
            ivClearAccount.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(setIp) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){

            btnLogin.setSelected(true);
        }else{
            btnLogin.setSelected(false);
        }
    }
    class MyTextWatcher implements TextWatcher{
        public EditText editTextWatcher;
        public int editTextId;
        public MyTextWatcher(EditText editTextWatcher){
            this.editTextWatcher = editTextWatcher;
            editTextId = editTextWatcher.getId();
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG,"editTextId "+ editTextId);
            CharSequence content = "";
            if(editTextId == R.id.etIp){
                if(s != null){
                    content = s.toString();
                    if(TextUtils.isEmpty(content)){
                        ivClearIp.setVisibility(View.GONE);
                    }else {
                        ivClearIp.setVisibility(View.VISIBLE);
                    }
                }
            }else if(editTextId == R.id.etUserName){
                if(s != null){
                    content = s.toString();
                    if(TextUtils.isEmpty(content)){
                        ivClearAccount.setVisibility(View.GONE);
                    }else {
                        ivClearAccount.setVisibility(View.VISIBLE);
                    }
                }
            }
//            改变btn的状态
            changeBtnStatus();
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    }
//
    public  void changeBtnStatus(){
        String[] editTextContent = getEditTextContent();
        if (!TextUtils.isEmpty(editTextContent[0]) && !TextUtils.isEmpty(editTextContent[1]) && !TextUtils.isEmpty(editTextContent[2])){
            btnLogin.setSelected(true);
        }else{
            btnLogin.setSelected(false);
        }
    }
    private void startLogin() {
        String editTextIp = getEditTextContent()[0];
        String username = getEditTextContent()[1];
        String password = getEditTextContent()[2];

        if (username == null || "".equals(username) || password == null || "".equals(password) || editTextIp == null || "".equals(editTextIp) ) {
            //账户或者密码为空
            UiUtils.toast("用户名、密码、IP不能为空");
        } else {
            GlobalConstants.ip = editTextIp;//设置IP给全局变量
            //访问服务器，进行用户名和密码校验
            showLoading("登陆中...");
            MyHttpUtils myHttpUtils = new MyHttpUtils();
            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("request", "check_login")
//            访问网络的话，需要md5 加密;
            String md5 = MD5Utils.getMd5(password);
            hashMap.put("name", username);
            hashMap.put("password", md5);
            Log.i(TAG,"md5 :" + md5);
            Log.i(TAG,"IP :" + GlobalConstants.ip);
            String url = GlobalConstants.getUrlFirst()+ "rest/user/check_login";

            myHttpUtils.getDataFromServiceByPostByJson(url, hashMap, new MyHttpUtils.OnNetResponseListener() {
                @Override
                public void onOk(String response) {
                    dismissLoading();
//                    saveAccount(username, password);
                    Gson gson = new Gson();
                    LoginUserBean loginUserBean = gson.fromJson(response, LoginUserBean.class);

                    if (loginUserBean != null){
                        String code = loginUserBean.getCode();
                        if ("0".equals(code)){
                            if (MyApplication.stationMap.size() == 0){
                                GlobalDataInit.initGloalData();
                            }
                            int id = loginUserBean.getData().getId();
//                            登录成功之后判断当前，用户名和IP是不是保存状态，如果是就记住
                            if(cbIp.isChecked()){
                                saveIp();
                            }
                            if(cbPassword.isChecked()){
                                Log.i(TAG,"cbPassword :" + true);
                                saveAccount();
                            }
//                            把用户id保存到全局变量里面，然后根据这个id进行修改密码
                            MyApplication.userId = id;
                            MyApplication.userName = (String) loginUserBean.getData().getName();
                            MyApplication.userPassword = (String) loginUserBean.getData().getPassword();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

//                            -1 表示用户名或者密码错误
                        }else if("-1".equals(code)){
                            UiUtils.toast(loginUserBean.getData().getDescription()+"");
                        }
                    }
//                    登陆结束后把这个值修改成false，表示没有正在登陆
                }

                @Override
                public void onNotOk(String msg) {
                    dismissLoading();
                    Log.i(TAG,"onNotOk :" + msg);
                    UiUtils.toast("访问失败 :" + msg);
                }
            });

        }


    }
    @Override
    public void finish() {
        super.finish();
        //去除掉这个退出动画，
//        overridePendingTransition(R.anim.from_left_in,R.anim.to_right_out);
    }
    private String[] arr = new String[3];
    public String[] getEditTextContent(){
        String editTextIp = etIp.getText().toString().trim();
        String username = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        arr[0] = editTextIp;
        arr[1] = username;
        arr[2] = password;
        return arr;
    }
    private void getValidateTime(){
        final String username = getEditTextContent()[1];
        final String password = getEditTextContent()[2];
//        这个说明已经拿到对应的站id
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            //账户或者密码为空
            UiUtils.toast("用户名或者密码不能为空");
            return;
        }
        showLoading("登录中...");
        if(MyApplication.locationBean != null){
            MyHttpUtils myHttpUtils = new MyHttpUtils();
            String locationId = MyApplication.locationBean.getLocationId();
            String locationName = MyApplication.locationBean.getLocationName();

            String url = "http://192.168.1.145:8080/LocationInfo/bisien/validity?"+"locationId="+locationId+"&locationName="+locationName;
            Log.i(TAG,"url :" + url);
            myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
                @Override
                public void onOk(String response) {
                    if(response != null){
                        ValidateTimeBean validateTimeBean = new Gson().fromJson(response, ValidateTimeBean.class);
                        String code = validateTimeBean.getCode();
                        if ("0".equals(code)){
//                            在有效期内可以继续使用
                            startLogin();
                        }else{
//                            没在有效期内，不可以继续使用
                            dismissLoading();
                            UiUtils.toast(validateTimeBean.getMessage());
                        }
                    }
                }

                @Override
                public void onNotOk(String msg) {
                    UiUtils.toast("访问失败");
                    dismissLoading();
                }
            });
        }else {
            Log.i(TAG,"MyApplication.locationBean :" + MyApplication.locationBean);
        }
    }

    //防止快速连续点击
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
//                这里就是分发事件，如果进来就不再进行往下分发，然后自己进行消费
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    private long lastClickTime;
    private int SPACE_TIME = 200;//间隔时间
    public  boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
//        当前时间赋值给最后的时间
        lastClickTime = time;
        return timeD <= SPACE_TIME;
    }

//    这个默认是明文显示为false，那么就是密文显示
    private boolean clearText = false;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                startLogin();
//                    getValidateTime();
//                 这个是屏蔽登录接口
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.iv_clear_ip:
                etIp.getText().clear();
                break;
            case R.id.iv_clear_account:
                etUserName.getText().clear();
                break;
            case R.id.iv_switch:
                String passsword = etPassword.getText().toString();
                if (!clearText){
                    //让密码明文显示
                    ivSwitch.setImageResource(R.mipmap.ic_display);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //让密码密文显示
                    ivSwitch.setImageResource(R.mipmap.ic_hide);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
//                让光标移到文字后面进行显示
                if (!TextUtils.isEmpty(passsword)){
                    etPassword.setSelection(passsword.length());
                }
                clearText = !clearText;
                break;
            case R.id.cbPassword:
                if(cbPassword.isChecked()){
                    saveAccount();
                }else{
                    clearAccount();
                }
                break;
            case R.id.cbIp:
                if (cbIp.isChecked()){
                    saveIp();
                }else{
                    clearIp();
                }
                break;
        }
    }


    private void clearIp() {
        PrefUtils.putString(UiUtils.getContext(), "setIp", "");
    }

    private void clearAccount() {
        PrefUtils.putString(UiUtils.getContext(), "name", "");
//           password 为数组的第2个元素
        PrefUtils.putString(UiUtils.getContext(), "password", "");
    }

    private void saveAccount() {
        //                name 为数组的第1个元素
        PrefUtils.putString(UiUtils.getContext(), "name", getEditTextContent()[1]);
//           password 为数组的第2个元素
        PrefUtils.putString(UiUtils.getContext(), "password", getEditTextContent()[2]);
//        PrefUtils.putString(UiUtils.getContext(), "isLogin", "logined");
    }
    private void saveIp(){
        //                setIp 为数组的第0个元素
        PrefUtils.putString(UiUtils.getContext(), "setIp", getEditTextContent()[0]);
    }
}
