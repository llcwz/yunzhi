package com.union.yunzhi.yunzhi.account;

import android.content.Context;

import com.union.yunzhi.factories.moudles.me.PersonModel;

/**
 * Created by CrazyGZ on 2018/3/7.
 */

public class AccountSingle {
    private static AccountSingle sAccountSingle = null;
    private PersonModel mPerson;
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

    public void setPerson(PersonModel person) {
        mPerson = person;
    }
    public PersonModel getPerson() {
        return mPerson;
    }
}
