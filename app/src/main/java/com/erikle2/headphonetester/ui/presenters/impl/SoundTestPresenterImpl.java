package com.erikle2.headphonetester.ui.presenters.impl;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.other.TimeToValueConverter;
import com.erikle2.headphonetester.ui.views.ITalkToMain;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;
import com.erikle2.headphonetester.ui.fragments.ResultFragment;
import com.erikle2.headphonetester.ui.presenters.interfaces.SoundTestFragmentPresenter;
import com.erikle2.headphonetester.ui.views.SoundTestFragmentView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Erik on 12/02/2016.
 */
public class SoundTestPresenterImpl implements SoundTestFragmentPresenter {

    private SoundTestFragmentView view;
    private Activity mActivity;
    private SoundPlayer soundPlayer;
    private int index;
    private ITalkToMain activityCallback;
    private Context context;
    private int MAX;
    private int TEST_TYPE;
    private int counter = 1;


    public SoundTestPresenterImpl(SoundTestFragmentView mView, Activity activity, ITalkToMain activityCallback, int index) {
        this.view = mView;
        this.soundPlayer = SoundPlayer.getInstance();
        this.mActivity = activity;
        this.index = index;
        this.activityCallback = activityCallback;
        this.context = activity;

        Resources r = mActivity.getResources();
        MAX = r.getStringArray(R.array.test_titles).length - 1;
        TEST_TYPE = r.getIntArray(R.array.test_type)[index];
    }

    @Override
    public void validateTest() {
        // Testfragement done and not last testfragment
        if (activityCallback.getTest().hasValue(index) && index < MAX ) {
            view.nextView();

        } else if (TEST_TYPE == 2 && index < MAX) {
            view.nextView();
        }
        //if this is the last test, show resultfragment
        else if (index == MAX) {
            FragmentManager fm = mActivity.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            // Check that fragment is not out of bounds
            ft.replace(R.id.fragment_container, ResultFragment.newInstance());
            ft.addToBackStack(null);
            ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }
    }

    @Override
    public void playOrPauseAudio() {


        //Test have never been played
        if (soundPlayer.isNull()) {

            soundPlayer.playMP3(mActivity, index);

            if (TEST_TYPE == 1) {
                view.tooglePlaybutton();
            } else {
                view.count(counter = 0);
                soundPlayer.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        view.showNavigationbuttions();
                        view.flashNext();
                        setValue(counter);


                    }
                });
            }
            view.hideNavigationbuttons();
            return;
        }
        //Spelar upp just nu
        if (soundPlayer.isPlaying()) {

            //TODO uncomment
//            if (TEST_TYPE == 2) {
//                Log.d("TESTTYPE ", " " + TEST_TYPE);
//                view.count(counter = counter + 1);
//            }

//            else {

                setValue(TimeToValueConverter.getFrequency(index,soundPlayer.getMediaPlayer().getCurrentPosition()));
                soundPlayer.stop();
                view.flashNext();
                view.tooglePlaybutton();
                view.showNavigationbuttions();
//            }


        }
        //Redo test
        //TODO Fix redo for test type 2
        else {
            soundPlayer.playMP3(mActivity, index);
            view.hideNavigationbuttons();
            if (TEST_TYPE == 1) {
                view.tooglePlaybutton();
            } else {
                view.count(counter = 0);
                soundPlayer.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        view.showNavigationbuttions();
                        view.flashNext();
                        setValue(counter);

                        soundPlayer.stop();
                    }
                });
            }

        }
    }

    @Override
    public void setValue(int value) {
        view.setResult("Input " + value + "Hz");
        activityCallback.getTest().addResult(value, index);
    }


}
