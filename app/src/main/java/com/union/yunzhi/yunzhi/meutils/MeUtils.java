package com.union.yunzhi.yunzhi.meutils;

import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.yunzhi.manager.UserManager;

/**
 * Created by CrazyGZ on 2018/3/13.
 */

public class MeUtils {
    public static UserModel getUser() {
        if (UserManager.getInstance().hasLogined()) {
            return UserManager.getInstance().getUser();
        } else {
            return null;
        }
    }
}
