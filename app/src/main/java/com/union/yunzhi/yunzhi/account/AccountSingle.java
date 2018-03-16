package com.union.yunzhi.yunzhi.account;

import android.content.Context;

import com.union.yunzhi.factories.moudles.me.UserModel;

/**
 * Created by CrazyGZ on 2018/3/7.
 */

public class AccountSingle {
    private static AccountSingle sAccountSingle = null;
    private UserModel mPerson;
    private Context mContext;
    public static AccountSingle getInstance(Context context) {
        if (sAccountSingle == null) {
            sAccountSingle = new AccountSingle(context);
        }
        return sAccountSingle;
    }
    private AccountSingle(Context context) {
        mContext = context;
    }

    public void setPerson(UserModel person) {
        mPerson = person;
    }
    public UserModel getPerson() {
        return mPerson;
    }
}
