package com.erikle2.headphonetester.ui.adapters;

import com.erikle2.headphonetester.model.entities.Headphone;
import com.erikle2.headphonetester.ui.fragments.ChooseHeadphonesFragment;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by Erik on 03/05/2016.
 */
public class MyFirebaseAdapter extends FirebaseRecyclerAdapter<Headphone,ChooseHeadphonesFragment.HeadphoneViewHolder> {

    public MyFirebaseAdapter(Class<Headphone> modelClass, int modelLayout, Class<ChooseHeadphonesFragment.HeadphoneViewHolder> viewHolderClass, Firebase ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ChooseHeadphonesFragment.HeadphoneViewHolder headphoneViewHolder, Headphone headphone, int i) {

    }

}
