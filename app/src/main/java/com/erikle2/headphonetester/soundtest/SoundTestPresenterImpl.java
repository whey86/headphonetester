package com.erikle2.headphonetester.soundtest;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.erikle2.headphonetester.mediaplayer.SoundPlayer;

/**
 * Created by Erik on 12/02/2016.
 */
public class SoundTestPresenterImpl implements SoundTestFragmentPresenter {

    private SoundTestFragmentView view;
    private Activity mActivity;
    private SoundPlayer soundPlayer;
    private int index;

    public SoundTestPresenterImpl(SoundTestFragmentView mView, Activity activity, int index) {
        this.view = mView;
        this.soundPlayer = SoundPlayer.getInstance();
        this.mActivity = activity;
        this.index = index;
    }

    @Override
    public void validateTest() {
        //TODO check if test is done by requesting value
    }

    @Override
    public void playOrPauseAudio() {
        if (soundPlayer.isNull()){
            soundPlayer.playMP3(mActivity, index);
            return;
        }
        if(soundPlayer.isPlaying()){
            Toast.makeText(mActivity, "Duration : " + soundPlayer.getMediaPlayer().getCurrentPosition(), Toast.LENGTH_LONG).show();
            soundPlayer.stop();
        }else{
            soundPlayer.playMP3(mActivity, index);
        }

    }
}
