package com.erikle2.headphonetester.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.ui.views.ChooseHeadphonesView;

/**
 * Created by Erik on 19/04/2016.
 */
public class ChooseHeadphonesFragment extends Fragment implements ChooseHeadphonesView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  root = inflater.inflate(R.layout.chooseheadphones_layout,container, false);

        return root;
    }

    public static ChooseHeadphonesFragment newInstance(){
        return new ChooseHeadphonesFragment();
    }
}
