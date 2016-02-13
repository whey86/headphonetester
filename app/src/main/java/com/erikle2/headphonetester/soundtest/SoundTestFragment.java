package com.erikle2.headphonetester.soundtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.main.ITalkToMain;
import com.erikle2.headphonetester.mediaplayer.SoundPlayer;

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

    public SoundTestFragment() {
        mPresenter = new SoundTestPresenterImpl(this, this.getActivity(), getActivity(), mActivityCallback, index);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mActivityCallback = (ITalkToMain) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
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


    @OnClick(R.id.btnStartStop)
    void onPlayStop() {
        if (soundPlayer.isNull()) {
            soundPlayer.playMP3(getActivity(), index);
            return;
        }
        if (soundPlayer.isPlaying()) {
            Toast.makeText(getActivity(), "Duration : " + soundPlayer.getMediaPlayer().getCurrentPosition(), Toast.LENGTH_LONG).show();
            mPresenter.setValue(soundPlayer.getMediaPlayer().getCurrentPosition());
            soundPlayer.stop();
        } else {
            soundPlayer.playMP3(getActivity(), index);
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
