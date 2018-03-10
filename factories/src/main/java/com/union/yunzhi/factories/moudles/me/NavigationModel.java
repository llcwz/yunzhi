package com.union.yunzhi.factories.moudles.me;

import android.media.Image;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class NavigationModel {
    private int mNavigationIcon;
    private String mNavigationName;

    public NavigationModel() {}

    public NavigationModel(int navigationIcon, String navigationName) {
        mNavigationIcon = navigationIcon;
        mNavigationName = navigationName;
    }

    public int getNavigationIcon() {
        return mNavigationIcon;
    }

    public void setNavigationIcon(int navigationIcon) {
        mNavigationIcon = navigationIcon;
    }

    public String getNavigationName() {
        return mNavigationName;
    }

    public void setNavigationName(String navigationName) {
        mNavigationName = navigationName;
    }
}
