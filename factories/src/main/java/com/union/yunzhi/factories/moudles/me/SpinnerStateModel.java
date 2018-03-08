package com.union.yunzhi.factories.moudles.me;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/7.
 */

public class SpinnerStateModel {
    private List<String> mStates;

    public SpinnerStateModel(List<String> states) {
        mStates = states;
    }

    public List<String> getStates() {
        return mStates;
    }

    public void setStates(List<String> states) {
        mStates = states;
    }
}
