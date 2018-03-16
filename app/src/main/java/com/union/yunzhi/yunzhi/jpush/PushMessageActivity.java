package com.union.yunzhi.yunzhi.jpush;

import android.content.Intent;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.jpush.PushMessage;
import com.union.yunzhi.yunzhi.R;

public class PushMessageActivity extends ActivityM {


    private TextView mShow;

    /**
     * data
     */
    private PushMessage mPushMessage;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_push_message;
    }

    @Override
    protected void initWidget() {
        mShow = (TextView) findViewById(R.id.tv_show);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mPushMessage = (PushMessage) intent.getSerializableExtra("pushMessage");

        mShow.setText(mPushMessage.messageContent);
    }
}
