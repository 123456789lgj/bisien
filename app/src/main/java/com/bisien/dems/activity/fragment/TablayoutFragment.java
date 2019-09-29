package com.bisien.dems.activity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bisien.dems.R;
import com.bisien.dems.activity.utils.UiUtils;

public class TablayoutFragment extends Fragment {

    public static String TAG = TablayoutFragment.class + " lgj";
    private int position;
    public TablayoutFragment (int position){
        this.position = position;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate.....");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(UiUtils.getContext(), R.layout.table_layout_fragment, null);
        TextView mTextView = (TextView) view.findViewById(R.id.fragment_textView);
        mTextView.setText("Page" + (position + 1));
        Log.i(TAG,"onCreateView.....");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
