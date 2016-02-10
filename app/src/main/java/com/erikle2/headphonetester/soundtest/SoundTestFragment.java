package com.erikle2.headphonetester.soundtest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Erik on 16/01/2016.
 */
public class SoundTestFragment extends SoundFragmentBase {

    private SoundPlayer soundPlayer;
    public SoundTestFragment() {
        this.soundPlayer = SoundPlayer.getInstance();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.index = getArguments().getInt("index");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.soundtest_layout, container, false);
        ButterKnife.bind(this, view);

        TextView tvTitle = (TextView)view.findViewById(R.id.tvTestTitle);
        tvTitle.setText(getActivity().getResources().getStringArray(R.array.test_titles)[super.index]);

        TextView tvText = (TextView)view.findViewById(R.id.tvTestInfo);
        tvText.setText(getActivity().getResources().getStringArray(R.array.test_info)[super.index]);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @OnClick(R.id.btnNext)
    void onNext(){
        Toast.makeText(getActivity(), "CLICKING TEST", Toast.LENGTH_SHORT).show();
        FragmentManager fm  = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Check that fragment is not out of bounds
        ft.replace(R.id.fragment_container,SoundTestFragment.newInstance(index + 1));
        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
    @OnClick(R.id.btnStartStop)
    void onPlayStop(){
        if (soundPlayer.isNull()){
            soundPlayer.playMP3(getActivity(),R.raw.audio1);
            return;
        }
        if(soundPlayer.isPlaying()){
            Toast.makeText(this.getActivity(),"Duration : " + soundPlayer.getMediaPlayer().getCurrentPosition(),Toast.LENGTH_LONG).show();
            soundPlayer.stop();
        }else{
            soundPlayer.playMP3(getActivity(),R.raw.audio1);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static SoundTestFragment newInstance(int fragmentIndex ) {


        Bundle args = new Bundle();
        args.putInt("index",fragmentIndex);
        SoundTestFragment fragment = new SoundTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
