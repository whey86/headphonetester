package com.erikle2.headphonetester.ui.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.ui.adapters.MyDividerRecycler;
import com.erikle2.headphonetester.ui.presenters.impl.ResultPresenterImpl;
import com.erikle2.headphonetester.ui.presenters.interfaces.ResultPresenter;
import com.erikle2.headphonetester.ui.views.ITalkToMain;
import com.erikle2.headphonetester.ui.adapters.ResultAdapter;
import com.erikle2.headphonetester.ui.views.ResultView;


/**
 * Created by Erik on 23/02/2016.
 */
public class ResultFragment extends Fragment implements ResultView {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ITalkToMain mActivityCallback;
    private ResultPresenter mPresenter;

    private TextView textViewScore;
    RecyclerView mRecycler;
    /**
     *
     *          LIFECYCLE METHODS
     *
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_layout,container,false);
         mRecycler = (RecyclerView) v.findViewById(R.id.rv_result);
        TextView device = (TextView) v.findViewById(R.id.tvDeviceName);
        device.setText(Build.MODEL);


        textViewScore = (TextView) v.findViewById(R.id.tvScore);
//
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);
        adapter = new ResultAdapter(mActivityCallback.getTest());
        mRecycler.addItemDecoration(new MyDividerRecycler(getActivity()));


        mRecycler.setAdapter(adapter);

        mPresenter = new ResultPresenterImpl(mActivityCallback.getTest(), this);

        //Save test to firebase
        mPresenter.saveTest();

    }

    /**
     *
     *          ONCLICK METHODS
     *
     */
    @Override
    public void close() {
        //TODO return to main screen
    }

    @Override
    public void setResult(int score) {
        textViewScore.setText("Total score: " + score );
    }

    @Override
    public void drawScore(int score) {

    }

    /**
     *Factory method
     *
     * @return
     */
    public static ResultFragment newInstance(){
        return new ResultFragment();
    }

}

