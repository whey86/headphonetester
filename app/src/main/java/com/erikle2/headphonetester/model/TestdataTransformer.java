package com.erikle2.headphonetester.model;

/**
 * Created by Erik on 15/03/2016.
 */
public class TestdataTransformer {

    public static int timeToFrequency(int frequency, long time){
        //TODO Make this method return frequency instead of time
        return (int)(time/4 % 60);
    }
}
