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
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.yunzhi.R;

public class ChangePasswordActivity extends ActivityM {

    private String mAccount = null;
    private String mPassword = null;
    private TextInputLayout mOld;
    private TextInputLayout mNewOne;
    private TextInputLayout mNewTwo;
    private TextInputEditText mOldPassword;
    private TextInputEditText mNewPasswordOne;
    private TextInputEditText mNewPasswordTwo;
    private Button mSubmit;

    public static void newInstance(Context context,String account, String password) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        intent.putExtra(MeConstant.KEY_ACCOUNT, account);
        intent.putExtra(MeConstant.KEY_PASSWORD, password);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_change_password;
    }


    @Override
    protected void initWidget() {
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
        mAccount = getIntent().getStringExtra(MeConstant.KEY_ACCOUNT);
        mPassword = getIntent().getStringExtra(MeConstant.KEY_PASSWORD);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordOld = mOldPassword.getText().toString();
                String passwordNewOne = mNewPasswordOne.getText().toString();
                String passwordNewTwo = mNewPasswordTwo.getText().toString();
                if (validateNull(passwordOld)) {
                    showError(mOld, "请输入原密码");
                } else{
                    showError(mOld, "");
                    if (!validateSame(mPassword, passwordOld)) {
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
                                    Toast.makeText(ChangePasswordActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                                    // TODO: 2018/2/26 提交新密码到服务器
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
