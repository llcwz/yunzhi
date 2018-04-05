package com.union.yunzhi.yunzhi.fragment.classfication;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.union.yunzhi.yunzhi.R;

/**
 * Created by CrazyGZ on 2018/3/17.
 */

public class ClassAddQuestionDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "ClassAddQuestionDialogFragment";
    private View mView;
    private EditText mQuestion;
    private EditText mContent;
    private Button mSubmit;
    private static OnGetQuestionContentListener mOnGetQuestionContentListener;

    public static ClassAddQuestionDialogFragment newInstance() {
        return new ClassAddQuestionDialogFragment();
    }
    public interface OnGetQuestionContentListener {
        void getQuestion(String question, String details);
    }

    public static void setOnGetQuestionListener(OnGetQuestionContentListener listener) {
        mOnGetQuestionContentListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mView = View.inflate(getActivity(), R.layout.class_fragment_add_question, null);
        mQuestion = (EditText) mView.findViewById(R.id.et_question);
        mContent = (EditText) mView.findViewById(R.id.et_details);
        mSubmit = (Button) mView.findViewById(R.id.btn_submit);
        mSubmit.setOnClickListener(this);
        builder.setView(mView);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            String question = mQuestion.getText().toString();
            String content = mContent.getText().toString();
            if (TextUtils.isEmpty(question) || TextUtils.isEmpty(content)) {
                Toast.makeText(getActivity(), "把问题描述详细喔", Toast.LENGTH_SHORT).show();
            } else {
                mOnGetQuestionContentListener.getQuestion(question,content);
                dismiss();
            }
        }
    }
}
