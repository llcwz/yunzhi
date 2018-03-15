package com.union.yunzhi.yunzhi.activities;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.Utils;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.utils.Util;

public class MyQRCodeActivity extends ActivityM {

    /**
     * UI
     */
    private ImageView mQrCodeView;
    private ImageView mPhoto;
    private TextView mTickView;
    private TextView mShow;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_qrcode;
    }

    @Override
    protected void initWidget() {
        mQrCodeView = (ImageView) findViewById(R.id.qrcode_view);
        mTickView = (TextView) findViewById(R.id.tick_view);
        mShow = (TextView) findViewById(R.id.tv_showInf);
        mPhoto = (ImageView) findViewById(R.id.photo_view);
        changeStatusBarColor(R.color.grey_900);
    }

    @Override
    protected void initData() {


        mTickView.setText(UserManager.getInstance().getUser().getName());

        mQrCodeView.setImageBitmap(Util.createQRCode(
                Utils.dip2px(this, 200),
                Utils.dip2px(this, 200),
                UserManager.getInstance().getUser().getAccount()));

        Glide.with(this)
                .load(UserManager.getInstance().getUser().getPhotourl())
                .into(mPhoto);

    }
}
