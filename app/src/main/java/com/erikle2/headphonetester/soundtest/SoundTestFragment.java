package com.erikle2.headphonetester.soundtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.erikle2.headphonetester.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Erik on 16/01/2016.
 */
public class SoundTestFragment extends Fragment {
    @Bind(R.id.button)
    Button button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.soundtest_layout, container, false);
        ButterKnife.bind(this, view);
        button.setText("SIDA 1");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @OnClick(R.id.button)
    void onNext(){
        Toast.makeText(getActivity(), "CLICKING TEST", Toast.LENGTH_SHORT).show();

        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment f = new SoundTestFragment2();
        ft.replace(R.id.fragment_container,f);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static SoundTestFragment newInstance() {
        
        Bundle args = new Bundle();
        SoundTestFragment fragment = new SoundTestFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
