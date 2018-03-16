package com.union.yunzhi.yunzhi.manager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.union.yunzhi.common.util.Utils;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.utils.Util;

/**
 * Created by meng on 2018/3/12.
 */

public class MyQrCodeDialog extends Dialog {


    private Context mContext;

    /**
     * UI
     */
    private ImageView mQrCodeView;
    private TextView mTickView;
    private TextView mCloseView;


    public MyQrCodeDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dalog_mycode_layout);

        initView();
    }

    private void initView() {

        mQrCodeView = (ImageView) findViewById(R.id.qrcode_view);
        mTickView = (TextView) findViewById(R.id.tick_view);
        mCloseView = (TextView) findViewById(R.id.close_view);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


       // String name = UserManager.getInstance().getUser().data.name;
        String name = "zhengemng";
        mQrCodeView.setImageBitmap(Util.createQRCode(
                Utils.dip2px(mContext, 200),
                Utils.dip2px(mContext, 200),
                "123456&0034"));
        mTickView.setText(name +"hellow");

    }
}
