package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.BaseCommunicationModel;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.CommentAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailsActivity extends ActivityM implements View.OnClickListener {
    private PostModel mPostModel;
    private CommentAdapter mAdapter;
    private Toolbar mToolbar;
    private TextView mTitle;
    private CircleImageView mIcon;
    private TextView mAuthor;
    private TextView mTime;
    private TextView mContent;
    private RecyclerView mRecyclerView;
    private EditText mComment; // 自己编辑的评论内容
    private TextView mSendComment; // 发送评论

    public static void newInstance(Context context, PostModel postModel) {
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra("post", postModel);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_communication_post_details;
    }

    @Override
    protected void initWidget() {
        mPostModel = getIntent().getParcelableExtra("post");
        data();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.tv_communication_title);
        mIcon = (CircleImageView) findViewById(R.id.ci_communication_icon);
        mAuthor = (TextView) findViewById(R.id.tv_communication_author);
        mTime = (TextView) findViewById(R.id.tv_communication_time);
        mContent = (TextView) findViewById(R.id.tv_communication_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mComment = (EditText) findViewById(R.id.et_send_comment);
        mSendComment = (TextView) findViewById(R.id.tv_send_comment);
    }

    // 初始化数据
    private void data() {
        mAdapter = new CommentAdapter(this, mPostModel.getCommentModels(), new MyAdapter.AdapterListener<CommentModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, CommentModel data) {

            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, CommentModel data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
    }

    @Override
    protected void initData() {
        mTitle.setText(mPostModel.getTitle());
        Glide.with(this).load(mPostModel.getIcon()).into(mIcon);
        mAuthor.setText(mPostModel.getAuthor());
        mTime.setText(mPostModel.getTime());
        mContent.setText(mPostModel.getContent());
        mSendComment.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_send_comment) {
            String comment = mComment.getText().toString();
            if (TextUtils.isEmpty(comment)) {
                Toast.makeText(this, "请先评论", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: 2018/3/9 处理发表评论逻辑
                Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
