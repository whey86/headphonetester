package com.erikle2.headphonetester.ui.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.erikle2.headphonetester.ui.activities.MainActivity;
import com.erikle2.headphonetester.R;

/**
 * Created by Erik on 24/01/2016.
 */
public class SoundfragmentHandler implements ITalkToFragmentControl {

    private int CURRENT_FRAGMENT = 0;
    private int NUMBER_OF_FRAGMENTS = 3;

    private final int FRAGMENT_MAIN = R.id.fragment_container;

    private AppCompatActivity mActivity;

    public SoundfragmentHandler(MainActivity activity) {
        this.mActivity = activity;
    }

    /**
     *
     *
     * Move to next stage of the sound test.
     * Replaces the main view with next testfragment
     */
    @Override
    public void nextFragment() {
        FragmentManager fm  = mActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        CURRENT_FRAGMENT = CURRENT_FRAGMENT +1;
        // Check that fragment is not out of bounds
        if(CURRENT_FRAGMENT > NUMBER_OF_FRAGMENTS){
            Log.e("SoundFragmenthandler", "Fragments out of bounds");
            return;
        }
        ft.replace(FRAGMENT_MAIN, createFragment(CURRENT_FRAGMENT + 1));

        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void previousFragment() {

    }

    private Fragment createFragment(int index)
    {
        switch (index){
            case 0 : return new SoundTestFragment();
        }
        return null;
    }
}
