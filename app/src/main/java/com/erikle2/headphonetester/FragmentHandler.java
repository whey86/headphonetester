package com.erikle2.headphonetester;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

import com.erikle2.headphonetester.soundtest.SoundTestFragment;

/**
 * Created by Erik on 16/01/2016.
 */
public class FragmentHandler {

    private MainActivity mActivity;

    private final int FRAGMENT_MAIN = R.id.fragment_container;

    private String FRAGMENT_TEST = "soundtest";
    private String FRAGMENT_COMPARE ="compare";
    private String FRAGMENT_PROFILE = "profile";

    private Fragment [] fragmentHolder = new Fragment[3];

    public FragmentHandler(MainActivity activity){
        mActivity = activity;
    }


    void setFragment(int index){
        FragmentManager fm  = mActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Toast.makeText(mActivity,"Fragment " + index, Toast.LENGTH_SHORT ).show();

        Fragment f = null;
        if(fragmentHolder[index-1] == null){
            f = createFragment(index);
        }
        if(f != null){
            ft.replace(FRAGMENT_MAIN,f);
            ft.commit();
        }
    }

    private Fragment createFragment(int position){
        if(position == 1){
            return SoundTestFragment.newInstance();
        }

        return null;
    }
}
