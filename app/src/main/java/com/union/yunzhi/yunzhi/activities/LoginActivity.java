package com.union.yunzhi.yunzhi.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.me.BaseMeModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.union.yunzhi.yunzhi.network.mina.MinaService;

/**
 * Created by meng on 2018/3/7.
 * @function 登陆界面逻辑处理
 */

public class LoginActivity extends ActivityM implements View.OnClickListener{

    //自定义登陆广播Action
    public static final String LOGIN_ACTION = "com.union.yunzhi.LOGIN_ACTION";

    private EditText mAccount;
    private EditText mPasswordView;
    private TextView mLoginView;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initWidget() {
        mAccount = (EditText) findViewById(R.id.associate_email_input);
        mPasswordView = (EditText) findViewById(R.id.login_input_password);
        mLoginView = (TextView) findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                login();
                break;
        }
    }

    private void login() {
        String userName = mAccount.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            return;
        }

        if (TextUtils.isEmpty(password)) {
            return;
        }

        DialogManager.getInstnce().showProgressDialog(this);

        RequestCenter.login(userName, password, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                //取消加载框
                DialogManager.getInstnce().dismissProgressDialog();

                /**
                 * 这部分可以封装起来，封装为到一个登陆流程类中
                 */
                Log.d("Test", "onSuccess: " + responseObj.toString());
                BaseMeModel user = (BaseMeModel) responseObj;
                UserManager.getInstance().setUser(user);//保存当前用户单例对象
                connectToSever();

                sendLoginBroadcast();
                /**
                 * 还应该将用户信息存入数据库，这样可以保证用户打开应用后总是登陆状态
                 * 只有用户手动退出登陆时候，将用户数据从数据库中删除。
                 */
                insertUserInfoIntoDB();

                finish();//销毁当前登陆页面
            }



            @Override
            public void onFailure(Object reasonObj) {
                //失败的时候也要去销毁他
                DialogManager.getInstnce().dismissProgressDialog();
                Log.d("Test", "onFailure: " + reasonObj.toString());
            }
        });




    }

    private void sendLoginBroadcast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }

    private void insertUserInfoIntoDB() {

    }

    //启动长连接
    private void connectToSever() {
        startService(new Intent(LoginActivity.this, MinaService.class));
    }
}
