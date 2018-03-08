package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.me.BaseMeModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.PersonModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

public class ChangePasswordActivity extends ActivityM {

    private UserManager mUserManager;
    private TextInputLayout mOld;
    private TextInputLayout mNewOne;
    private TextInputLayout mNewTwo;
    private TextInputEditText mOldPassword;
    private TextInputEditText mNewPasswordOne;
    private TextInputEditText mNewPasswordTwo;
    private Button mSubmit;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_change_password;
    }


    @Override
    protected void initWidget() {
        mUserManager = UserManager.getInstance();

        mOld = (TextInputLayout) findViewById(R.id.til_old_password);
        mNewOne = (TextInputLayout) findViewById(R.id.til_new_password_one);
        mNewTwo = (TextInputLayout) findViewById(R.id.til_new_password_two);
        mOldPassword = (TextInputEditText) findViewById(R.id.tie_old_password);
        mNewPasswordOne = (TextInputEditText) findViewById(R.id.tie_new_password_one);
        mNewPasswordTwo = (TextInputEditText) findViewById(R.id.tie_new_password_two);
        mSubmit = (Button) findViewById(R.id.btn_submit);

    }

    @Override
    protected void initData() {

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 输入的原密码
                String passwordOld = mOldPassword.getText().toString();
                // 输入的新密码
                String passwordNewOne = mNewPasswordOne.getText().toString();
                String passwordNewTwo = mNewPasswordTwo.getText().toString();

                if (validateNull(passwordOld)) {
                    showError(mOld, "请输入原密码");
                } else{
                    showError(mOld, "");
                    if (!validateSame(mUserManager.getPerson().getPassword(), passwordOld)) {
                        showError(mOld, "原密码不正确");
                    } else {
                        if (validateNull(passwordNewOne)) {
                            showError(mNewOne, "请输入新密码");
                        } else {
                            showError(mNewOne, "");
                            if (validateNull(passwordNewTwo)) {
                                showError(mNewTwo, "请输入新密码");
                            } else {
                                showError(mNewTwo, "");
                                if (!validateSame(passwordNewOne, passwordNewTwo)) {
                                    showError(mNewTwo, "密码不一致，请重新输入");
                                } else {
                                    showError(mNewTwo, "");
                                    if (passwordOld.equals(passwordNewOne)) {
                                        Toast.makeText(ChangePasswordActivity.this, "原密码和新密码相同", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // 用户可以提交修改后的密码了
                                        DialogManager.getInstnce().showProgressDialog(getApplicationContext()); // 显示等待进度
                                        RequestCenter.requestChangePassword(mUserManager.getPerson().getAccount(), passwordNewOne, new DisposeDataListener() {
                                            @Override
                                            public void onSuccess(Object responseObj) {
                                                BaseMeModel baseMeModel = (BaseMeModel) responseObj;
                                                if (baseMeModel.ecode == 0) {
                                                    mUserManager.getPerson().setPassword(baseMeModel.data.getPersonModel().getPassword());
                                                    Toast.makeText(ChangePasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(ChangePasswordActivity.this, "" + baseMeModel.emsg, Toast.LENGTH_SHORT).show();
                                                }
                                                DialogManager.getInstnce().dismissProgressDialog();
                                            }

                                            @Override
                                            public void onFailure(Object reasonObj) {
                                                Toast.makeText(ChangePasswordActivity.this, "连接网络失败", Toast.LENGTH_SHORT).show();
                                                DialogManager.getInstnce().dismissProgressDialog();
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }


    /**
     * 提示信息
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 判断是否为空,为空返回真，不空返回假
     * @param password
     * @return
     */
    private boolean validateNull(String password) {
        if (TextUtils.isEmpty(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断密码是否一致
     * @param passwordOne
     * @param passwordTwo
     * @return
     */
    private boolean validateSame(String passwordOne, String passwordTwo) {
        if (passwordOne.equals(passwordTwo)) {
            return true;
         } else {
            return false;
        }
    }

}
