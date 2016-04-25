package com.erikle2.headphonetester.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.ui.views.ChooseHeadphonesView;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by Erik on 19/04/2016.
 */
public class ChooseHeadphonesFragment extends Fragment implements ChooseHeadphonesView {

    RecyclerView mRecyclerView;
     FirebaseRecyclerAdapter<String, HeadphoneViewHolder> adapter;
    Firebase mFirebase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  root = inflater.inflate(R.layout.chooseheadphones_layout,container, false);
        mRecyclerView = (RecyclerView)root.findViewById(R.id.rv_headphones);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebase = new Firebase("https://headphonetester.firebaseio.com/devices");

         adapter =
                new FirebaseRecyclerAdapter<String, HeadphoneViewHolder>(
                        String.class,
                        R.layout.headphone_item,
                        HeadphoneViewHolder.class,
                        mFirebase
                )
                {
                    @Override
                    protected void populateViewHolder(HeadphoneViewHolder headphoneViewHolder, String s, int i) {
                        final String myHeadphones = s;
                        headphoneViewHolder.mTextView.setText(myHeadphones);

                        headphoneViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView tv=  (TextView)v;
                                Toast.makeText(getActivity(),"CLICKY " + tv.getText(),Toast.LENGTH_SHORT).show();

                                FragmentManager fm = getActivity().getSupportFragmentManager();

                                FragmentTransaction ft = fm.beginTransaction();

                                Bundle b = new Bundle();
                                b.putString("headphones", myHeadphones);


                                Fragment fragment = SoundTestFragment.newInstance(0);
                                fragment.setArguments(b);
                                // Check that fragment is not out of bounds
                                ft.replace(R.id.fragment_container, fragment);
                                ft.addToBackStack(null);
                                ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
                                ft.commit();
                            }
                        });
                    }
                };

        mRecyclerView.setAdapter(adapter);


    }

    private void updateList(){

    }

    public static ChooseHeadphonesFragment newInstance(){
        return new ChooseHeadphonesFragment();
    }

    public static class HeadphoneViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        public HeadphoneViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }

    }
}
