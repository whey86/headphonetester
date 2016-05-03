package com.erikle2.headphonetester.ui.fragments;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.ui.views.ITalkToMain;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;
import com.erikle2.headphonetester.ui.presenters.interfaces.SoundTestFragmentPresenter;
import com.erikle2.headphonetester.ui.views.SoundTestFragmentView;
import com.erikle2.headphonetester.ui.presenters.impl.SoundTestPresenterImpl;
import com.erikle2.progressdots.ProgressDotBar;
import com.pascalwelsch.holocircularprogressbar.HoloCircularProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Erik on 16/01/2016.
 */
public class SoundTestFragment extends Fragment implements SoundTestFragmentView {


    private SoundTestFragmentPresenter mPresenter;
    private ITalkToMain mActivityCallback;
    ProgressDotBar mProgressBar;


    @Bind(R.id.btnBack)
    Button btnPrevious;
    @Bind(R.id.btnNext)
    Button btnNext;
    @Bind(R.id.btnStartStop)
    Button btnStartAndStop;

//    private HoloCircularProgressBar btnStartAndStop;
    private int index;

    private String myHeadphones;

    Animation animationFlash;

    /**
     * LIFECYCLE METHODS
     */

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);

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

        Bundle b = getArguments();
        myHeadphones = (String) b.get("headphones");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        // If infofragment (Before test)
        if (index == 0) {
            view = inflater.inflate(R.layout.soundtest_layout_start, container, false);
            Button b = (Button) view.findViewById(R.id.btnStartTest);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivityCallback.startNewTest(myHeadphones);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
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
            hideNextbutton();

//            btnStartAndStop = (HoloCircularProgressBar) view.findViewById(R.id.btnStartStop);
//            btnStartAndStop.setProgressColor(getResources().getColor(R.color.colorAccent));
//            btnStartAndStop.setProgressBackgroundColor(getResources().getColor(R.color.colorPrimaryLighter));

        }

        mProgressBar = (ProgressDotBar)view.findViewById(R.id.mybar);
        mProgressBar.setIndex(index);
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

    /**
     * IMPLEMENTED METHODS
     */

    @Override
    public void flashNext() {
        btnNext.startAnimation(animationFlash);
    }

    @Override
    public void abortFlash() {
        btnNext.clearAnimation();
    }

    @Override
    public void count(int value) {
        btnStartAndStop.setBackgroundResource(R.drawable.circle);
        btnStartAndStop.setText(value + "");
    }

    @Override
    public void nextView() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

        // Check that fragment is not out of bounds
        ft.replace(R.id.fragment_container, SoundTestFragment.newInstance(index + 1));
        ft.addToBackStack(null);
        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }


    @Override
    public void tooglePlaybutton() {
        Resources r = getResources();
        if (btnStartAndStop.getBackground().getConstantState().equals(r.getDrawable(R.drawable.btn_play).getConstantState())) {
            btnStartAndStop.setBackgroundResource(R.drawable.btn_stop);
        } else {
            btnStartAndStop.setBackgroundResource(R.drawable.btn_play);
        }
    }

    @Override
    public void setResult(String result) {
        TextView tvResult = (TextView) getActivity().findViewById(R.id.tvTestresult);
        tvResult.setText(result);
    }

    @Override
    public void hideBackbutton() {
        btnPrevious.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideNextbutton() {
        btnNext.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNextbutton() {
        btnNext.setVisibility(View.VISIBLE);

    }

    @Override
    public void showBackbutton() {
        btnPrevious.setVisibility(View.VISIBLE);
    }


    @Override
    public void previousView() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }
    @Override
    public void setPlaybackProgress(float f)
    {
//        btnStartAndStop.setProgress(f);
//        btnStartAndStop.invalidate();
    }

    /**
     * ONCLICK METHODS
     */


    @OnClick(R.id.btnNext)
    public void onNext() {
        mPresenter.validateTest();
    }

    @OnClick(R.id.btnStartStop)
    void onPlayStop() {
        mPresenter.playOrPauseAudio();
    }
    @Override
    public void showNavigationbuttions() {
        showBackbutton();
        showNextbutton();
    }



    @Override
    public void hideNavigationbuttons() {
        hideBackbutton();
        hideNextbutton();
    }

    @OnClick(R.id.btnBack)
    void onBack() {
        this.previousView();
    }


    /**
     * Factory method
     *
     * @param fragmentIndex
     * @return
     */

    public static SoundTestFragment newInstance(int fragmentIndex) {
        Bundle args = new Bundle();
        args.putInt("index", fragmentIndex);
        SoundTestFragment fragment = new SoundTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
