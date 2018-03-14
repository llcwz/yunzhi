package com.union.yunzhi.yunzhi.fragment.me;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.union.yunzhi.yunzhi.R;

/**
 * @function 提供时间选择
 * Created by CrazyGZ on 2018/3/8.
 */

public class TimePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "TimePickerDialogFragment";

    private TimePickerListener mTimePickerListener;
    public interface TimePickerListener { // 回调接口
        void getTime(String time);
    }

    private TimePicker mTimePicker; // 选择日期
    private TextView mSubmit; // 确定时间
    private TextView mCancel; // 取消选择

    public static TimePickerDialogFragment newInstance() {
        return new TimePickerDialogFragment();
    }

    public void setTimePickerListener(TimePickerListener datePickerListener) {
        mTimePickerListener = datePickerListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.me_fragment_time_picker, null);
        mTimePicker = (TimePicker) view.findViewById(R.id.tmPk_time_picker);
        mSubmit = (TextView) view.findViewById(R.id.tv_time_picker_submit);
        mCancel = (TextView) view.findViewById(R.id.tv_time_picker_cancel);
        mCancel.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_time_picker_submit) {
            String time = mTimePicker.getHour() + ":" + mTimePicker.getMinute();
            mTimePickerListener.getTime(time);
        }
        dismiss();
    }

}
