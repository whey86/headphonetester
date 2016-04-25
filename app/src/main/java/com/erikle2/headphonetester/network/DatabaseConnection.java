package com.erikle2.headphonetester.network;

import com.erikle2.headphonetester.model.entities.HeadPhoneTest;
import com.firebase.client.Firebase;

/**
 * Created by Erik on 25/04/2016.
 */
public class DatabaseConnection {

    public static final String ROOT = "https://headphonetester.firebaseio.com/";
    public static final String DEVICES = "https://headphonetester.firebaseio.com/devices";
    public static final String TESTS = "https://headphonetester.firebaseio.com/tests";
    public static final String USERS = "https://headphonetester.firebaseio.com/users";

    private String source;
    private Firebase mRef;


    public DatabaseConnection(String URL) {
        source = URL;
        mRef = new Firebase(source);

    }

    public Firebase getFirebase(){
       return mRef;
    }

    public void saveTest(HeadPhoneTest mTest){
        mRef.push().setValue(mTest);

    }

    public static DatabaseConnection newInstance(String URL){
        return new DatabaseConnection(URL);
    }
}
