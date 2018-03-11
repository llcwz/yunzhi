package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.UserManager;

public class AddPostActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {
    private int mTag; // fragment标签
    private UserManager mUserManager;
    private Toolbar mToolbar;
    private EditText mTitle;
    private EditText mContent;

    public static void newInstance(Context context, int tag) {
        Intent intent = new Intent(context, AddPostActivity.class);
        intent.putExtra(CommunicationConstant.KEY_TAG, tag);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void initWidget() {
        mTag = getIntent().getIntExtra(CommunicationConstant.KEY_TAG, -1);
        mUserManager = UserManager.getInstance();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.communication_add_post_item);
        mToolbar.setOnMenuItemClickListener(this);
        mTitle = (EditText) findViewById(R.id.et_post_title);
        mContent = (EditText) findViewById(R.id.et_post_content);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // 这里需要加上是否登录了，不登录的话不给发表
//        if (mUserManager.hasLogined()) {
//
//        } else {
//            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
//        }
        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "嘿，你的标题呢", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "快写些话吧", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: 2018/3/10 处理发帖逻辑
            Toast.makeText(this, "发布成功" + mTag, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private String getTime() {
        return null;
    }
}
