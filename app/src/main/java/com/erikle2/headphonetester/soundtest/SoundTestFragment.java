package com.erikle2.headphonetester.soundtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.erikle2.headphonetester.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Erik on 16/01/2016.
 */
public class SoundTestFragment extends SoundFragmentBase {

    public SoundTestFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.soundtest_layout, container, false);
        ButterKnife.bind(this, view);
        Button b = (Button) view.findViewById(R.id.button);
        b.setText(getArguments().getString("name"));
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
        ft.replace(R.id.fragment_container,SoundTestFragment.newInstance("Test 2", "New info string"));
        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static SoundTestFragment newInstance(String name, String info ) {
        
        Bundle args = new Bundle();
        args.putString("info","info");
        args.putString("name","test1");
        args.putInt("sound",0);
        SoundTestFragment fragment = new SoundTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
