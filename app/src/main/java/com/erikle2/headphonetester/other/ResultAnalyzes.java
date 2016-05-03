package com.erikle2.headphonetester.other;

/**
 * Created by Erik on 25/04/2016.
 */
public class ResultAnalyzes {

    static final int SCORE_HIGH = 5;
    static final int SCORE_PASSABLE = 3;
    static final int SCORE_FAIL = 1;


    public static int getScore(int testIndex, int value){

        switch (testIndex){
            case 1: return lowestFrequency(value);
            case 2: return highestFrequency(value);
        }

        return 0;
    }


    private static int lowestFrequency(int value){
        if(value <= 20){
            return SCORE_HIGH;
        }else if(value < 30){
            return  SCORE_PASSABLE;
        }else{
            return SCORE_FAIL;
        }

    }
    private static int highestFrequency(int value) {

        if(value >= 20000){
            return SCORE_HIGH;
        }else if(value > 18000){
            return  SCORE_PASSABLE;
        }else{
            return SCORE_FAIL;
        }
    }
}
