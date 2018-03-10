package com.union.yunzhi.yunzhi.fragment.me;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.union.yunzhi.yunzhi.R;

/**
 * Created by CrazyGZ on 2018/2/25.
 */

public class ChangeIconDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "ChangeIconDialogFragment";

    private TextView mCamera; // 拍照上传
    private TextView mAlbum; // 相册上传

    public static ChangeIconDialogFragment newInstance() {
        return new ChangeIconDialogFragment();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = View.inflate(getActivity(), R.layout.me_fragment_change_icon, null);
        mCamera = (TextView) view.findViewById(R.id.tv_change_icon_camera);
        mAlbum = (TextView) view.findViewById(R.id.tv_change_icon_album);
        mCamera.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        builder.setView(view);
        AlertDialog iconDialogFragment = builder.create();
        Window window = iconDialogFragment.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_change_icon_camera) { // 拍照上传
            camera();
        } else if (view.getId() == R.id.tv_change_icon_album){ // 相册上传
            album();
        }
    }

    /**
     * 执行拍照上传头像逻辑
     */
    private void camera() {
        // TODO: 2018/3/10 拍照上传头像
    }

    /**
     * 执行从本地相册上传头像逻辑
     */
    private void album() {
        // TODO: 2018/3/10 本地上传头像
    }

}
