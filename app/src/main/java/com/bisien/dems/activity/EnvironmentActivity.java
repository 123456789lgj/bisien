package com.bisien.dems.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bisien.dems.R;
import com.bisien.dems.activity.application.MyApplication;
import com.bisien.dems.activity.bean.CondiditioningBean;
import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.bean.GlobalDataBean;
import com.bisien.dems.activity.global.GlobalConstants;
import com.bisien.dems.activity.utils.MyHttpUtils;
import com.bisien.dems.activity.utils.UiUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnvironmentActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        findTitle("环境系统");
        recyclerView = findViewById(R.id.recyclerView);
//        设备类型1 表示空调
        List<GlobalDataBean.DataBean.HousesBean.EquipmentsBean> equipmentsBeans = MyApplication.equipments.get(1);
//        环境系统用的是空调的数据
        if (equipmentsBeans != null) {
            for (int i = 0; i < equipmentsBeans.size(); i++) {
                GlobalDataBean.DataBean.HousesBean.EquipmentsBean equipmentsBean = equipmentsBeans.get(i);
                if (equipmentsBean.getName().toUpperCase().contains("空调")) {
                    initData(equipmentsBean.getId());
                    break;
                }
            }
        }
    }

    ArrayList<CondiditioningBean.DataBean> listTwo = new ArrayList<>();

    private void initData(long deviceId) {
        MyHttpUtils myHttpUtils = new MyHttpUtils();
//        此接口是获取所用的设备信息和设备的运行状态
            String url = GlobalConstants.getUrlFirst() + "rest/status/get_list_rdata_byequipid/" + deviceId + "/" + 1;  //空调的type值为 1
//        String url = "http://192.168.1.145:8080/gledeye/rest/equipment/get_list";
        showLoading("加载中...");
        myHttpUtils.getDataFromServiceByGet(url, new MyHttpUtils.OnNetResponseListener() {
            @Override
            public void onOk(String response) {
                dismissLoading();
                CondiditioningBean condiditioningBean = new Gson().fromJson(response, CondiditioningBean.class);
                List<CondiditioningBean.DataBean> data = condiditioningBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    CondiditioningBean.DataBean dataBean = data.get(i);
//                    if (dataBean.isVisible()) {//需要展示的信息
//                        int channelType = dataBean.getChannelType();
//                        if (channelType == 1) {// channelType 等于0 的话，表示数字量
//                        } else {// channelType=2 的话，表示模拟量
//                            listTwo.add(dataBean);
//                        }
//                    }
                    if (dataBean.getCode() != null){
//                        先取送风温度设定值，如果能取到就用送风温度，如果没有取到就有 回风温度设定
//                        送风温度设定，如果条件成立表示能取到相对应的值
                        if("d_sendtemp".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }
//                        回风温度设定值
                        else if("d_recvtemp".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }

//                      送风湿度设定
                        else if("d_sendhumi".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }
//                      回风湿度设定
                        else if("d_recvhumi".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }

//                        表示实际的温度
                        else if("d_tempertrue_real".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }
//                        表示实际的湿度
                        else if("d_humidity_real".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }
//                      表示除湿
                        else if("d_dehumi".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }
//                        空调的运行模式，表示除湿
                        else if("d_cold".equals(dataBean.getCode())){
                            listTwo.add(dataBean);
                        }

                    }
                }
                setData();
            }

            @Override
            public void onNotOk(String msg) {
                dismissLoading();
                System.out.println("PowerSystemActivity :onNotOk :" + msg);
            }
        });
    }
    static class EviBean{
        public String infoName;
        public String infoValue;
        public EviBean(String infoName,String infoValue){
            this.infoName = infoName;
            this.infoValue = infoValue;
        }
    }
    private ArrayList<EviBean> newList = new ArrayList<EviBean>();
    public void filterData(){
        for (int i = 0; i < listTwo.size(); i++) {
            String code = (String)listTwo.get(i).getCode();
            if (code.equals("d_sendtemp")){//送风温度设定
                String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                newList.add(new EviBean("设定温度",value));
                booleans[0] = true;// 表示设定温度
            }else if (code.equals("d_sendhumi")){
                String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                newList.add(new EviBean("设定湿度",value));
                booleans[1] = true;// 表示设定湿度
            }else if (code.equals("d_dehumi")){//表示除湿
                CondiditioningBean.DataBean dataBean = listTwo.get(i);
                double currentValue = dataBean.getCurrentValue();
                if (currentValue >= 1){//大于等于1表示开启的状态
                    newList.add(0,new EviBean("运行模式","空调除湿"));
                    newList.add(1,new EviBean("应急风扇","停机"));
                }
            }else if (code.equals("d_cold")){//表示制冷
                CondiditioningBean.DataBean dataBean = listTwo.get(i);
                double currentValue = dataBean.getCurrentValue();
                if (currentValue >= 1){//大于等于1表示开启的状态
                    newList.add(0,new EviBean("运行模式","空调制冷"));
                    newList.add(1,new EviBean("应急风扇","停机"));
                }
            }else if (code.equals("d_tempertrue_real")){
                String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                newList.add(new EviBean("实际温度",value));
            }else if (code.equals("d_humidity_real")){
                String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                newList.add(new EviBean("实际湿度",value));
            }
        }
//        -----------------------------先从里面取送风温度和送风湿度----------------------------------------------------------
//        表示没有取到送风温度,和送风湿度
        if (!booleans[0] && !booleans[1]){
            for (int i = 0; i < listTwo.size(); i++) {
                if (listTwo.get(i).getCode().equals("d_recvtemp")){//回风温度设定
                    String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                    newList.add(new EviBean("设定温度",value));
                }else if(listTwo.get(i).getCode().equals("d_recvhumi")){
                    String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                    newList.add(new EviBean("设定湿度",value));
                }
            }
        }else if (!booleans[0] && booleans[1]){//没有取到送风温度，但是取到送风湿度
            for (int i = 0; i < listTwo.size(); i++) {
                if (listTwo.get(i).getCode().equals("d_recvtemp")){//回风温度设定
                    String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                    newList.add(new EviBean("设定温度",value));
                }
            }
        }else if (booleans[0] && !booleans[1]){//取到送风温度，但没取到送风湿度
            for (int i = 0; i < listTwo.size(); i++) {
                if (listTwo.get(i).getCode().equals("d_recvhumi")){//回风湿度设定
                    String value = listTwo.get(i).getCurrentValue() + " " + listTwo.get(i).getUnit();
                    newList.add(new EviBean("设定湿度",value));
                }
            }
        }
    }
//    SELECT * FROM tbl_config_signal WHERE equipmentId =
//    boolean 类型的数组，第一个表示设定温度，第二个表示设定湿度，第三个表示除湿（除湿表示加热），第四个表示制冷
    private boolean[] booleans = {false,false,false,false};
    private void setData() {

        filterData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        recyclerView.setAdapter(new EnvironmentSystemAdapter());
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        private View headView;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            headView = itemView;
        }
    }

    class EnvironmentSystemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private int TYPE_HEADER = 1001;

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                ImageView imageView = new ImageView(UiUtils.getContext());
                imageView.setImageResource(R.mipmap.ic_status_environment);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtils.dip2px(LinearLayout.LayoutParams.WRAP_CONTENT));
                layoutParams.setMargins(0,UiUtils.dip2px(30),0,UiUtils.dip2px(30));
                imageView.setLayoutParams(layoutParams);
                return new HeadViewHolder(imageView);
            }
            View view = View.inflate(parent.getContext(), R.layout.adapter_recycler_power_system, null);
            PowerSystemViewHolder powerSystemViewHolder = new PowerSystemViewHolder(view);
            return powerSystemViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof PowerSystemViewHolder) {
//                减一是为了防止数组索引越界
//                EquipmentBean.DataBean.EquipmentSignalsBean equipmentSignalsBean = listTwo.getEquipmentSignals().get(position - 1);
                EviBean eviBean = newList.get(position - 1);
                PowerSystemViewHolder holder2 = (PowerSystemViewHolder) holder;
                holder2.tvDeviceName.setText(eviBean.infoName);
                holder2.tvCurrentValue.setText(eviBean.infoValue);
//            表示最后一个元素
                if (position == newList.size()) {
                    holder2.bottomDevide.setVisibility(View.VISIBLE);
                } else {
                    holder2.bottomDevide.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return  newList.size() + 1;
        }
    }

    static class PowerSystemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDeviceName;
        private TextView tvCurrentValue;
        private View bottomDevide;
        private View viewDevide;

        public PowerSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
            tvCurrentValue = itemView.findViewById(R.id.tvCurrentValue);
            bottomDevide = itemView.findViewById(R.id.bottomDevide);
            viewDevide = itemView.findViewById(R.id.viewDevide);
        }
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;//元素与元素之间的间隔

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            //获取当前item的位置
            int position = parent.getChildAdapterPosition(view);

            outRect.right = UiUtils.dip2px(space);


        }
    }
}
