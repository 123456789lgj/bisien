package com.bisien.dems.activity.fragment;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bisien.dems.R;
//
public class CardFragment extends BaseFragment {

    public ImageView ivCardViewLogo;
    public TextView cardViewDeviceName;
    public TextView cardViewDeviceSum;
    public TextView cardViewDeviceSumName;
    public View view;

    @Override
    public View initView() {
        System.out.println("initView :" + this);
        view = View.inflate(getContext(), R.layout.fragment_cardview, null);
        ivCardViewLogo = view.findViewById(R.id.ivCardViewLogo);
        cardViewDeviceName = view.findViewById(R.id.cardViewDeviceName);
        cardViewDeviceSum = view.findViewById(R.id.cardViewDeviceSum);
        cardViewDeviceSumName = view.findViewById(R.id.cardViewDeviceSumName);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
