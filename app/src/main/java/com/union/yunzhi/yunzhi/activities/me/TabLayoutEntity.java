package com.union.yunzhi.yunzhi.activities.me;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class TabLayoutEntity implements CustomTabEntity {
    public String title;

    public int selectedIcon;

    public int unSelectedIcon;

    public TabLayoutEntity(String title, int selectedIcon, int unSelectedIcon) {

        this.title = title;

        this.selectedIcon = selectedIcon;

        this.unSelectedIcon = unSelectedIcon;

    }

    @Override

    public String getTabTitle() {

        return title;

    }

    @Override

    public int getTabSelectedIcon() {

        return selectedIcon;

    }

    @Override

    public int getTabUnselectedIcon() {

        return unSelectedIcon;

    }
}
