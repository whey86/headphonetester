package com.erikle2.headphonetester.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.erikle2.headphonetester.R;

import java.io.IOException;

/**
 * Created by Erik on 28/01/2016.
 */
public class SoundPlayer {

    private static SoundPlayer soundPlayer = null;
    private MediaPlayer mediaPlayer;

    private int [] AUDIOFILES = {   1111,
                                    R.raw.frequencycheckhigh_44100,
                                    R.raw.frequencychecklow,
                                    R.raw.sweep20_20klog_perceptual,
                                    R.raw.dynamiccheck,
                                    R.raw.headphoneshaker,
                                    R.raw.headphonesweep20hz_10khz,
                                    R.raw.left,
                                    R.raw.right,
                                    R.raw.polarity_lowrumbleok,
                                    R.raw.polarity_lowrumbleko,
                                    R.raw.binaural_knocking,
                                    };

    public SoundPlayer() {
    }

    public void playMP3(Context context,int id){
//        Toast.makeText(context,"Playing MP3",Toast.LENGTH_SHORT).show();
//        mediaPlayer = MediaPlayer.create(context, AUDIOFILES[id]);
//        mediaPlayer.start(); // no need to call prepare(); create() does that for you


            mediaPlayer = MediaPlayer.create(context, AUDIOFILES[id]);
            mediaPlayer.start();

    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
    public void stop(){
        mediaPlayer.stop();
    }

    public boolean isPlaying(){
       return mediaPlayer.isPlaying();
    }

    public boolean isNull(){
        return mediaPlayer == null ? true : false;
    }

    public static SoundPlayer getInstance(){
        if(soundPlayer == null){
            soundPlayer = new SoundPlayer();
        }
        return soundPlayer;
    }
}
