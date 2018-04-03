package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.communicationutils.OpinionUtils;
import com.union.yunzhi.yunzhi.meutils.MeUtils;

public class AddPostActivity extends ActivityM implements Toolbar.OnMenuItemClickListener {
    public static final int REQUEST = 1;
    public static final String RESULT_POST = "addPost";
    public static final String TAG = "tag";

    private int mTag; // fragment标签
    private Intent mIntent;
    private UserModel mUser;
    private Toolbar mToolbar;
    private EditText mTitle;
    private EditText mContent;


    private static OnGetPostContentListener mListener;
    public interface OnGetPostContentListener {
        void getPost(String title, String content);
    }

    public static void setGetPostListener(OnGetPostContentListener listener) {
        mListener = listener;
    }


    public static void newInstance(Context context, int tag) {
        Intent intent = new Intent(context, AddPostActivity.class);
        intent.putExtra(AddPostActivity.TAG, tag);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void initWidget() {
        mIntent = getIntent();
        mTag = mIntent.getIntExtra(TAG, -1);
        mUser = MeUtils.getUser();
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
                mListener.getPost(title, content);
                finish();
            }
        return false;
    }

}
