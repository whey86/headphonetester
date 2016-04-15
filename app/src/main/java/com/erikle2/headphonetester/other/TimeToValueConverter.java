package com.erikle2.headphonetester.other;

/**
 * TimeToValueConverter converts the breakpoint in the test soundfiles
 * into responding frequency.
 */
public class TimeToValueConverter {

    public static int getFrequency(int index, int time){
    int sec = time/1000;
        switch (index){
            case 1 : return 10 +(200/30)*sec;
            case 2 : return 22000 - sec*1000/2;
        }

        return -1;
    }


}
