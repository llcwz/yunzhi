package com.union.yunzhi.yunzhi.manager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.util.Utils;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.utils.Util;

import de.hdodenhof.circleimageview.CircleImageView;

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
    private CircleImageView mPortail;


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
        mPortail = (CircleImageView) findViewById(R.id.photo_view);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        String[] courses = getContext().getResources().getStringArray(R.array.crouseId);
        int postion = (int) (Math.random()*(courses.length-1));
        String course = courses[postion];
        String[] teachers = getContext().getResources().getStringArray(R.array.teacherId);
        String teacher = teachers[postion];

        mQrCodeView.setImageBitmap(Util.createQRCode(
                Utils.dip2px(mContext, 200),
                Utils.dip2px(mContext, 200),
                course+"&"+teacher));


        Glide.with(getContext())
                .load(UserManager.getInstance().getUser().getPhotourl())
                .into(mPortail);

        mTickView.setText(UserManager.getInstance().getUser().getName().substring(0,1)+"教授");

//        String[] titles = context.getResources().getStringArray(R.array.introduce);
//        int id = (int) (Math.random()*(titles.length-1));//随机产生一个index索引
//        mShow.setText(titles[id]);

    }
}
