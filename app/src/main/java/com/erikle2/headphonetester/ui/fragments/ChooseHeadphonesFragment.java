package com.erikle2.headphonetester.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.model.entities.Headphone;
import com.erikle2.headphonetester.ui.presenters.interfaces.ChooseHeadphonesPresenter;
import com.erikle2.headphonetester.ui.views.ChooseHeadphonesView;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Erik on 19/04/2016.
 */
public class ChooseHeadphonesFragment extends Fragment implements ChooseHeadphonesView {

    RecyclerView mRecyclerView;
    FirebaseRecyclerAdapter<Headphone, HeadphoneViewHolder> adapter;
    Firebase mFirebase;

    EditText etSearch;
    Button btnSearch;
    FloatingActionButton fab;

    ChooseHeadphonesPresenter mPresenter;
     Query mQuery;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebase = new Firebase("https://headphonetester.firebaseio.com/devices");
        mPresenter = new ChooseHeadphonesPresenterImpl(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View  root = inflater.inflate(R.layout.chooseheadphones_layout,container, false);
        mRecyclerView = (RecyclerView)root.findViewById(R.id.rv_headphones);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        etSearch = (EditText) root.findViewById(R.id.et_searchfield);
        btnSearch = (Button)root.findViewById(R.id.btnSearch);
        fab = (FloatingActionButton) root.findViewById(R.id.fab_new_headphones);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateQuery(etSearch.getText().toString());


            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        updateQuery("");

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }



    @Override
    public void search(String value) {
    }


    private void updateQuery(String searchword){
        mQuery = null;
        if(searchword.equals("") || searchword == null){
            mQuery = mFirebase.orderByKey();
        }else{


        mQuery = mFirebase.orderByKey().startAt(searchword).endAt(searchword + "\uf8ff");

//        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot child: dataSnapshot.getChildren()){
//                    Headphone hp = child.getValue(Headphone.class);
//                    Log.d("CHILD ", hp.getBrand() + " " + hp.getName());
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Toast.makeText(getActivity(),"no data found",Toast.LENGTH_SHORT).show();
//            }
//        });
        }

        adapter =
                new FirebaseRecyclerAdapter<Headphone, HeadphoneViewHolder>(
                        Headphone.class,
                        R.layout.headphone_item,
                        HeadphoneViewHolder.class,
                        mQuery
                )
                {
                    @Override
                    protected void populateViewHolder(HeadphoneViewHolder headphoneViewHolder, Headphone headphone, int i) {

                        final String myHeadphones = headphone.getBrand() + " " + headphone.getName();

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
