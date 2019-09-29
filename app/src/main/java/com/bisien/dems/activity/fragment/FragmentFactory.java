package com.bisien.dems.activity.fragment;

import java.util.HashMap;

public class FragmentFactory {
    public static HashMap<Integer, BaseFragment> sSavedFragment = new HashMap<>();

    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = sSavedFragment.get(position);
        if(fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AlarmFragment();
                    break;
                case 2:
                    fragment = new StatusFragment();
                    break;
                case 3:
                    fragment = new MyFragment();
                    break;
            }
            sSavedFragment.put(position, fragment);
        }
        return fragment;
    }
}
