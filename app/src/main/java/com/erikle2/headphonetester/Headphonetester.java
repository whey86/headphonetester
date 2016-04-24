package com.erikle2.headphonetester;

import com.firebase.client.Firebase;

/**
 * Created by Erik on 24/04/2016.
 */
public class Headphonetester extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
