package com.erikle2.headphonetester;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Erik on 16/01/2016.
 */
public class FragmentHandler {

    private MainActivity mActivity;

    private final int FRAGMENT_MAIN = R.id.fragment_container;

    public FragmentHandler(MainActivity activity){
        mActivity = activity;
    }

    void setFragment(int index){
        FragmentManager fm  = mActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(index == 0){
            Fragment f = SoundTestFragment.newInstance();
            ft.add(FRAGMENT_MAIN,f);
        }

        ft.commit();
    }
}
