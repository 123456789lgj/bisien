package com.bisien.dems.activity.tabfragment;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bisien.dems.R;

import com.bisien.dems.activity.bean.EquipmentBean;
import com.bisien.dems.activity.utils.UiUtils;

import java.util.ArrayList;


public class TabLayoutFragment extends BaseTabFragment {

    private RecyclerView recyclerView;
    public ArrayList<EquipmentBean.DataBean> listData;
    public TabLayoutFragment(ArrayList<EquipmentBean.DataBean> listData){
        this.listData = listData;
    }

    @Override
    public View initView() {

        View view = View.inflate(getContext(), R.layout.fragment_temperature, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        TempperatureAdapter tempperatureAdapter = new TempperatureAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(16));
        recyclerView.setAdapter(tempperatureAdapter);
        return view;
    }
    class TempperatureAdapter extends RecyclerView.Adapter<TemperatureViewHolder>{

        @NonNull
        @Override
        public TemperatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = View.inflate(getContext(), R.layout.adapter_recycler_temperature, null);
            TemperatureViewHolder temperatureViewHolder = new TemperatureViewHolder(itemView);
            return temperatureViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TemperatureViewHolder holder, int position) {
            EquipmentBean.DataBean dataBean = listData.get(position);
            holder.tvName.setText(dataBean.getName());
            int state = dataBean.getState();
//            0 表示正常，1 表示不正常
            if (state == 0){
                holder.tvNormal.setText("正常");
                holder.tvNormal.setTextColor(UiUtils.getColor(R.color.text_press));
                holder.tvNotNormal.setText("不正常");
                holder.tvNotNormal.setTextColor(UiUtils.getColor(R.color.white));
            }else {
                holder.tvNormal.setText("正常");
                holder.tvNormal.setTextColor(UiUtils.getColor(R.color.white));
                holder.tvNotNormal.setText("不正常");
                holder.tvNotNormal.setTextColor(UiUtils.getColor(R.color.text_press));
            }


        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }
    static class TemperatureViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvNormal;
        private TextView tvNotNormal;
        public TemperatureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNormal = itemView.findViewById(R.id.tvNormal);
            tvNotNormal = itemView.findViewById(R.id.tvNotNormal);
        }
    }
    class SpacesItemDecoration extends RecyclerView.ItemDecoration{
        private int space;//元素与元素之间的间隔

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            //获取当前item的位置
            int position = parent.getChildAdapterPosition(view);

            if (position == 0){
                outRect.top =  UiUtils.dip2px(space);
                outRect.bottom =  UiUtils.dip2px(space);
            }else {
                outRect.bottom =  UiUtils.dip2px(space);
            }
            outRect.left = UiUtils.dip2px(space);
            outRect.right = UiUtils.dip2px(space);


        }
    }
}
