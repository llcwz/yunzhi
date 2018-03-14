package com.union.yunzhi.yunzhi.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.jpush.PushMessage;
import com.union.yunzhi.factories.moudles.me.BaseUserModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.jpush.PushMessageActivity;
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

    /**
     * data
     */
    private PushMessage mPushMessage; // 推送过来的消息
    private boolean fromPush; // 是否从推送到此页面

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        transparencyBar();
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
        Intent intent = getIntent();
        if (intent.hasExtra("pushMessage")) {
            mPushMessage = (PushMessage) intent.getSerializableExtra("pushMessage");
        }
        fromPush = intent.getBooleanExtra("fromPush", false);
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
                BaseUserModel baseUserModel = (BaseUserModel) responseObj;
                if (baseUserModel.ecode == MeConstant.ECODE) {
/**
 * 这部分可以封装起来，封装为到一个登陆流程类中
 */
                    LogUtils.d("login", "onSuccess: " + responseObj.toString());
                    UserManager.getInstance().setUser(baseUserModel.data);//保存当前用户单例对象
                    connectToSever();

                    sendLoginBroadcast();
                    /**
                     * 还应该将用户信息存入数据库，这样可以保证用户打开应用后总是登陆状态
                     * 只有用户手动退出登陆时候，将用户数据从数据库中删除。
                     */
                    insertUserInfoIntoDB();
                    if (fromPush) {
                        Intent intent = new Intent(LoginActivity.this, PushMessageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("pushMessage", mPushMessage);
                        startActivity(intent);
                    }
                    finish();//销毁当前登陆页面
                } else {
                    Toast.makeText(LoginActivity.this, "" + baseUserModel.emsg, Toast.LENGTH_SHORT).show();
                }

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
