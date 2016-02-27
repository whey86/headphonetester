package com.erikle2.headphonetester.result;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erikle2.headphonetester.R;

/**
 * Created by Erik on 23/02/2016.
 */
public class ResultFragment extends Fragment implements ResultView{

    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ResultFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_layout,container,false);
        mRecyclerview = (RecyclerView) v.findViewById(R.id.rv_result);
        mRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(layoutManager);
        adapter = new ResultAdapter(new String[]{"test"});
        mRecyclerview.setAdapter(adapter);


        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void close() {
        //TODO return to main screen
    }
    public static ResultFragment newInstance(){
        return new ResultFragment();
    }

}

