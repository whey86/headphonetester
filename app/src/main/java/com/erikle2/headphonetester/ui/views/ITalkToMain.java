package com.erikle2.headphonetester.ui.views;

import com.erikle2.headphonetester.model.entities.HeadPhoneTest;

/**
 * Created by Erik on 14/01/2016.
 */
public interface ITalkToMain {
    void onNavigationSelected(int position);
    HeadPhoneTest startNewTest();
    HeadPhoneTest getTest();
}
