package com.erikle2.headphonetester.soundtest;

import android.app.Fragment;
import android.widget.Button;

/**
 * Created by Erik on 26/01/2016.
 */
public abstract class SoundFragmentBase extends Fragment {

    /**
     * Buttons to navigate between tests
     */
    Button btnNext, btnPrevious;

    /**
     * Name of fragment
     */
    String name;

    /**
     *  Contains info about the test madein the fragment
     */
    String info;

    void play() {

    }
    void stop(){

    }
}
