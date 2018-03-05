package com.union.yunzhi.yunzhi.fragment.me;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.union.yunzhi.yunzhi.R;

/**
 * Created by CrazyGZ on 2018/2/25.
 */

public class ChangeIconDialogFragment extends DialogFragment {
    public static final String TAG_CHANGE_ICON_DIALOG_FRAGMENT = "ChangeIconDialogFragment";

    public static ChangeIconDialogFragment newInstance() {
        return new ChangeIconDialogFragment();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = View.inflate(getActivity(), R.layout.me_fragment_change_icon, null);
        builder.setView(view);
        AlertDialog iconDialogFragment = builder.create();
//        Window window = iconDialogFragment.getWindow();
//        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        return builder.create();
    }
}
