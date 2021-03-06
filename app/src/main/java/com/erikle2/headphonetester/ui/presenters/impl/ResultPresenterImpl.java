package com.erikle2.headphonetester.ui.presenters.impl;

import com.erikle2.headphonetester.model.entities.HeadPhoneTest;
import com.erikle2.headphonetester.network.DatabaseConnection;
import com.erikle2.headphonetester.other.ResultAnalyzes;
import com.erikle2.headphonetester.ui.presenters.interfaces.ResultPresenter;
import com.erikle2.headphonetester.ui.views.ResultView;

/**
 * Created by Erik on 25/04/2016.
 */
public class ResultPresenterImpl implements ResultPresenter {


    private DatabaseConnection dbc;
    private HeadPhoneTest mTest;
    private ResultView mView;

    public ResultPresenterImpl(HeadPhoneTest headPhoneTest, ResultView resultView) {
        this.mTest = headPhoneTest;
        this.dbc = DatabaseConnection.newInstance(DatabaseConnection.TESTS);
        this.mView = resultView;

        int total = getTotalScore();
        mView.setResult(total);
    }

    @Override
    public void saveTest() {
        dbc.saveTest(mTest);
    }

    @Override
    public int getTotalScore() {
        int total = 0;
        total = ResultAnalyzes.getScore(1,mTest.getResult()[1]) + ResultAnalyzes.getScore(2,mTest.getResult()[2]);
        return total/2;
    }
}
