package com.erikle2.headphonetester.ui.fragments;

import com.erikle2.headphonetester.ui.presenters.interfaces.ChooseHeadphonesPresenter;
import com.erikle2.headphonetester.ui.views.ChooseHeadphonesView;
import com.firebase.client.Query;

/**
 * Created by Erik on 03/05/2016.
 */
public class ChooseHeadphonesPresenterImpl implements ChooseHeadphonesPresenter {

    ChooseHeadphonesView mView;

    public ChooseHeadphonesPresenterImpl(ChooseHeadphonesView view) {
        this.mView = view;
    }

    @Override
    public void onSearch(String value) {
    }
}
