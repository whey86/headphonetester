package com.erikle2.headphonetester.model.entities;


/**
 * Created by Erik on 12/02/2016.
 */
public class HeadPhoneTest {
    /**
     * Required
     * Name of headphones being tested
     */
     String myHeadphones;

     String device;
    /**
     * Name of test
     */
    private String [] testname;
    /**
     * Number of tests
     */
    private int SIZE = 6;
    /**
     * Array storing testresult
     */
    private int [] result = new int[SIZE];

    private int score;

    /**
     * Constructor
     * @param device
     */
    public HeadPhoneTest(String device,String headphones, String [] testnames){

        this.myHeadphones = headphones;
        this.device = device;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
