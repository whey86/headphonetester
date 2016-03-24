package com.erikle2.headphonetester.model.entities;

import com.erikle2.headphonetester.R;

/**
 * Created by Erik on 12/02/2016.
 */
public class HeadPhoneTest {
    /**
     * Required
     * Name of headphones being tested
     */
    protected String headphonesName;
    /**
     * Name of test
     */
    private String [] testname;
    /**
     * Number of tests
     */
    protected int SIZE = 6;
    /**
     * Array storing testresult
     */
    private int [] result = new int[SIZE];

    /**
     * Constructor
     * @param name
     */
    public HeadPhoneTest(String name, String [] testnames){

        headphonesName = name;
        this.testname = testnames;
    }

    /**
     * Method to add result to testmodel.
     * @param value
     * @param index
     */
    public void addResult(int value, int index){
        this.result [index] = value;
    }

    /**
     * Returns if test value has been set for index
     * @param index
     * @return
     */
    public boolean hasValue(int index){
        return this.result[index]== 0 ? false : true;
    }

    public int [] getResult(){
        return result;
    }

    public String [] getName(){ return testname;}
}
