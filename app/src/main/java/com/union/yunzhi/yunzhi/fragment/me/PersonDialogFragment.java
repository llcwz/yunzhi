package com.union.yunzhi.yunzhi.fragment.me;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.me.ChangePasswordActivity;

/**
 * Created by CrazyGZ on 2018/2/25.
 */

public class PersonDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG_PERSON_DIALOG_FRAGMENT = "PersonDialogFragment";
    private String mAccount;
    private String mPassword;
    private TextView mChangeIcon; // 更换头像
    private TextView mChangePassword; // 修改密码
    private TextView mLogOut; // 退出平台

    public static PersonDialogFragment newInstance(String account,String password) {
        Bundle bundle = new Bundle();
        // 传入账号和密码
        bundle.putString(MeConstant.KEY_ACCOUNT, account);
        bundle.putString(MeConstant.KEY_PASSWORD, password);
        PersonDialogFragment personDialogFragment = new PersonDialogFragment();
        personDialogFragment.setArguments(bundle);
        return personDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 获取账号和密码
        mAccount = getArguments().getString(MeConstant.KEY_ACCOUNT);
        mPassword = getArguments().getString(MeConstant.KEY_PASSWORD);

        // 创建dialog布局
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.me_fragment_person, null);

        // 绑定控件及其监听
        mChangeIcon = (TextView) view.findViewById(R.id.tv_person_change_icon);
        mChangePassword = (TextView) view.findViewById(R.id.tv_person_change_password);
        mLogOut = (TextView) view.findViewById(R.id.tv_person_log_out);
        mChangeIcon.setOnClickListener(this);
        mChangePassword.setOnClickListener(this);
        mLogOut.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        Log.d("PersonDialogClick", "onClick: ");
        switch (view.getId()) {
            case R.id.tv_person_change_icon:
                // TODO: 2018/2/25 更换头像
                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getChildFragmentManager();
                ChangeIconDialogFragment.newInstance().show(fragmentManager, ChangeIconDialogFragment.TAG_CHANGE_ICON_DIALOG_FRAGMENT);
                break;
            case R.id.tv_person_change_password:
                // TODO: 2018/2/26  
                ChangePasswordActivity.newInstance(getActivity(), mAccount,mPassword);
                break;
            case R.id.tv_person_log_out:
                // TODO: 2018/2/25 退出平台
                break;
            default:
        }
        dismiss();
    }
}
