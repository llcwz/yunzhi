package com.union.yunzhi.yunzhi.fragment.communication;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    public static final String TAG = "CommentDialogFragment";
    public static final String EXTRA_ID = "id";
    private String mId;
    private View mView;
    private EditText mContent;
    private TextView mSend;

    private OnAddCommentListener mOnAddCommentListener;

    public static CommentDialogFragment newInstance(String id) {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ID, id);
        commentDialogFragment.setArguments(bundle);
        return commentDialogFragment;
    }

    // 数据回传
    public interface OnAddCommentListener {
        void getContent(String id,String content);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mOnAddCommentListener = (OnAddCommentListener) getActivity();
        mId = (String) getArguments().get(EXTRA_ID);
        mView = View.inflate(getActivity(), R.layout.communication_fragment_comment, null);
        mContent = (EditText) mView.findViewById(R.id.et_content);
        mSend = (TextView) mView.findViewById(R.id.tv_send);
        mSend.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(mView);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        String content = mContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(getActivity(), "请先输入文字", Toast.LENGTH_SHORT).show();
            dismiss();
        } else {
            mOnAddCommentListener.getContent(mId,content);
        }
    }
}
