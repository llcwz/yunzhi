package com.union.yunzhi.yunzhi.fragment.me;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.yunzhi.R;

/**
 * @function 提供日期选择
 * Created by CrazyGZ on 2018/3/8.
 */

public class DatePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "DatePickerDialogFragment";

    private DatePickerListener mDatePickerListener;
    public interface DatePickerListener { // 回调接口
        void getDate(String date);
    }

    private DatePicker mDatePicker; // 选择日期
    private TextView mSubmit; // 确定时间
    private TextView mCancel; // 取消选择

    public static DatePickerDialogFragment newInstance() {
        return new DatePickerDialogFragment();
    }

    public void setDatePickerListener(DatePickerListener datePickerListener) {
        mDatePickerListener = datePickerListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.me_fragment_date_picker, null);
        mDatePicker = (DatePicker) view.findViewById(R.id.dtPk_date_picker);
        mSubmit = (TextView) view.findViewById(R.id.tv_date_picker_submit);
        mCancel = (TextView) view.findViewById(R.id.tv_date_picker_cancel);
        mSubmit.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_date_picker_submit) {
            String date = mDatePicker.getYear() + "."
                    + mDatePicker.getMonth() + "."
                    + mDatePicker.getDayOfMonth();
            mDatePickerListener.getDate(date);
        }
        dismiss();
    }

}
