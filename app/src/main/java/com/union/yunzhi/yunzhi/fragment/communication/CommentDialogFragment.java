package com.union.yunzhi.yunzhi.fragment.communication;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.yunzhi.R;

/**
 * Created by CrazyGZ on 2018/3/17.
 */

public class CommentDialogFragment extends DialogFragment implements View.OnClickListener {
    private View mView;
    private EditText mContent;
    private TextView mSend;

    private OnAddCommentListener mOnAddCommentListener;
    public static CommentDialogFragment newInstance() {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        return commentDialogFragment;
    }

    @Override
    public void onClick(View v) {
        String content = mContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(getActivity(), "请先输入文字", Toast.LENGTH_SHORT).show();
        } else {
            mOnAddCommentListener.getContent(content);
        }
        dismiss();
    }

    // 数据回传
    public interface OnAddCommentListener {
        void getContent(String content);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mOnAddCommentListener = (OnAddCommentListener) getActivity();
        mView = View.inflate(getActivity(), R.layout.communication_fragment_comment, null);
        mContent = (EditText) mView.findViewById(R.id.et_content);
        mSend = (TextView) mView.findViewById(R.id.tv_send);
        mSend.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(mView);
        return builder.create();
    }
}
