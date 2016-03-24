package com.erikle2.headphonetester.ui.views;

import android.app.Fragment;
import android.widget.Button;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;

/**
 * Created by Erik on 26/01/2016.
 */
public interface SoundTestFragmentView {

    /**
     * Make to next button flash, tells the user to go on to the next step
     */
    void flashNext();

    void abortFlash();

    void nextView();

    void previousView();

    void tooglePlaybutton();

    void setResult(String result);

    void hideBackbutton();
    void hideNextbutton();

    void showNextbutton();
    void showBackbutton();




}
