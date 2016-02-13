package com.erikle2.headphonetester.soundtest;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.erikle2.headphonetester.main.ITalkToMain;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;

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


    public SoundTestPresenterImpl(SoundTestFragmentView mView, Context context, Activity activity, ITalkToMain activityCallback, int index) {
        this.view = mView;
        this.soundPlayer = SoundPlayer.getInstance();
        this.mActivity = activity;
        this.index = index;
        this.activityCallback = activityCallback;
        this.context = context;
    }

    @Override
    public void validateTest() {
        if (activityCallback.getTest().hasValue(index)) {
            view.nextView();
        }
    }

    @Override
    public void playOrPauseAudio() {
        if (soundPlayer.isNull()) {
            soundPlayer.playMP3(mActivity, index);
            return;
        }
        if (soundPlayer.isPlaying()) {
            Toast.makeText(mActivity, "Duration : " + soundPlayer.getMediaPlayer().getCurrentPosition(), Toast.LENGTH_LONG).show();
            activityCallback.getTest().addResult(soundPlayer.getMediaPlayer().getCurrentPosition(), index);
            soundPlayer.stop();
        } else {
            soundPlayer.playMP3(mActivity, index);
        }

    }

    @Override
    public void setValue(int value) {
        activityCallback.getTest().addResult(value,index);
    }
}
