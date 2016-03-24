package com.erikle2.headphonetester.ui.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.main.ITalkToMain;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;
import com.erikle2.headphonetester.soundtest.SoundTestFragmentPresenter;
import com.erikle2.headphonetester.soundtest.SoundTestFragmentView;
import com.erikle2.headphonetester.soundtest.SoundTestPresenterImpl;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Erik on 16/01/2016.
 */
public class SoundTestFragment extends Fragment implements SoundTestFragmentView {


    private SoundTestFragmentPresenter mPresenter;
    private ITalkToMain mActivityCallback;
    private String headphoneName;
    private SoundPlayer soundPlayer = SoundPlayer.getInstance();

    private int index;

    Animation animationFlash;

    public SoundTestFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mActivityCallback = (ITalkToMain) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
        mPresenter = new SoundTestPresenterImpl(this, getActivity(), mActivityCallback, index);

        animationFlash = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animationFlash.setDuration(500); // duration - half a second
        animationFlash.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animationFlash.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animationFlash.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        // If informationview
        if (index == 0) {
            view = inflater.inflate(R.layout.soundtest_layout_start, container, false);
            Button b = (Button) view.findViewById(R.id.btnStartTest);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mActivityCallback.startNewTest("Supra headphones");

                    FragmentManager fm = getActivity().getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    // Check that fragment is not out of bounds
                    ft.replace(R.id.fragment_container, SoundTestFragment.newInstance(index + 1));
                    ft.addToBackStack(null);
                    ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }
            });
            //Test started
        } else {
            view = inflater.inflate(R.layout.soundtest_layout, container, false);
            ButterKnife.bind(this, view);
        }


        TextView tvTitle = (TextView) view.findViewById(R.id.tvTestTitle);
        tvTitle.setText(getActivity().getResources().getStringArray(R.array.test_titles)[index]);

        TextView tvText = (TextView) view.findViewById(R.id.tvTestInfo);
        tvText.setText(getActivity().getResources().getStringArray(R.array.test_info)[index]);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateResult() {

    }

    @Override
    public void flashNext() {
        Button btnNext = (Button) getActivity().findViewById(R.id.btnNext);

        btnNext.startAnimation(animationFlash);
    }

    @OnClick(R.id.btnNext)
    public void onNext() {
        mPresenter.validateTest();
    }

    @Override
    public void nextView() {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Check that fragment is not out of bounds
        ft.replace(R.id.fragment_container, SoundTestFragment.newInstance(index + 1));
        ft.addToBackStack(null);
        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @OnClick(R.id.btnBack)
    @Override
    public void previousView() {
        FragmentManager fm = getActivity().getFragmentManager();
        fm.popBackStack();
    }


    @Override
    public void tooglePlaybutton() {
        Button btn = (Button) this.getActivity().findViewById(R.id.btnStartStop);
        Resources r = getResources();
        if(btn.getBackground().getConstantState().equals(r.getDrawable(R.drawable.btn_play).getConstantState())){
            btn.setBackgroundResource(R.drawable.btn_stop);
        }
        else {
            btn.setBackgroundResource(R.drawable.btn_play);
        }
    }

    @OnClick(R.id.btnStartStop)
    void onPlayStop() {
        if (soundPlayer.isNull()) {
            soundPlayer.playMP3(getActivity(),index);
            this.tooglePlaybutton();
            return;
        }
        if (soundPlayer.isPlaying()) {
            Toast.makeText(getActivity(), "Duration : " + soundPlayer.getMediaPlayer().getCurrentPosition(), Toast.LENGTH_LONG).show();
            mPresenter.setValue(soundPlayer.getMediaPlayer().getCurrentPosition());
            soundPlayer.stop();
            this.flashNext();
            this.tooglePlaybutton();
        } else {
            soundPlayer.playMP3(getActivity(), index);
            this.tooglePlaybutton();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    public static SoundTestFragment newInstance(int fragmentIndex) {
        Bundle args = new Bundle();
        args.putInt("index", fragmentIndex);
        SoundTestFragment fragment = new SoundTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
