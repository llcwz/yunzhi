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
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.communicationutils.OpinionUtils;
import com.union.yunzhi.yunzhi.fragment.main.CommunicationFragment;
import com.union.yunzhi.yunzhi.manager.UserManager;

public class AddPostActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {
    public static final int KEY_POST = 1;
    public static final String RESULT_POST = "addPost";
    public static final String TAG = "tag";
    private int mTag; // fragment标签
    private Intent mIntent;
    private UserManager mUserManager;
    private Toolbar mToolbar;
    private EditText mTitle;
    private EditText mContent;
    private PostModel mPostModel;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void initWidget() {
        mIntent = getIntent();
        mTag = mIntent.getIntExtra(CommunicationConstant.KEY_TAG, -1);
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
            String title = mTitle.getText().toString();
            String content = mContent.getText().toString();
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(this, "嘿，你的标题呢", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(content)) {
                Toast.makeText(this, "快写些话吧", Toast.LENGTH_SHORT).show();
            } else {
                OpinionUtils opinionUtils = OpinionUtils.newInstance(mUserManager, this, title,content);
                opinionUtils.postComment(mTag, new OpinionUtils.NotifyPostListener() {
                    @Override
                    public void getPost(PostModel postModel) {
                        mPostModel = postModel;
                        mIntent.putExtra(TAG, mTag);
                        mIntent.putExtra(RESULT_POST, postModel);
                        setResult(RESULT_OK, mIntent);
                    }
                });
            }
        return false;
    }
}
