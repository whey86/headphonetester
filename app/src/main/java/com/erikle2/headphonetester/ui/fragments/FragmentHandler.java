package com.erikle2.headphonetester.ui.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.ui.activities.MainActivity;

/**
 * Created by Erik on 16/01/2016.
 */
public class FragmentHandler {

    private MainActivity mActivity;

    private final int FRAGMENT_MAIN = R.id.fragment_container;

    private String FRAGMENT_TEST = "soundtest";
    private String FRAGMENT_COMPARE = "compare";
    private String FRAGMENT_PROFILE = "profile";

    private String[] TAGS = {FRAGMENT_TEST, FRAGMENT_COMPARE, FRAGMENT_PROFILE};

    private ITalkToFragmentControl mSoundTestFragmentsControl;

    public FragmentHandler(MainActivity activity) {
        mActivity = activity;

        mSoundTestFragmentsControl = new SoundfragmentHandler(activity);
    }

    /**
     *  Method sets fragment for the view container by the index of the
     *  navigationdrawer
     *  * @param index
     */
    public void setNaviFragment(int index) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Toast.makeText(mActivity, "Fragment " + index, Toast.LENGTH_SHORT).show();

        //Icon in navigation(recyclerview) was clicked
        if (index < 1) {
            return;
        }

        ft.replace(FRAGMENT_MAIN, createFragment(index), TAGS[index - 1]);
        ft.addToBackStack(null);
        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
        ft.commit();


    }

    /**
     * Get starting fragment for the navigation
     *
     * @param position
     * @return
     */
    private Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return  ChooseHeadphonesFragment.newInstance();
//                return SoundTestFragment.newInstance(0);
        }
        return null;
    }
}
