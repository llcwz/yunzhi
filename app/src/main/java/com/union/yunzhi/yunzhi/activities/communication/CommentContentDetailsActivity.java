package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.ReplyModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.ReplyAdapter;
import com.union.yunzhi.yunzhi.communicationutils.CommentUtils;
import com.union.yunzhi.yunzhi.fragment.communication.CommentDialogFragment;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentContentDetailsActivity extends ActivityM implements CommentDialogFragment.OnGetCommentContentListener, View.OnClickListener {

    public static final String EXTRA_POST_ID = "postId";
    public static final String EXTRA_COMMENT_MODEL = "commentModel";
    private UserManager mUserManager;
    private UserModel mUser;
    private String mPostId;
    private CommentModel mCommentModel;
    private RelativeLayout mLayout;
    private CircleImageView mIcon;
    private TextView mName;
    private TextView mTime;
    private TextView mContent;

    private RecyclerView mRecyclerView;
    private ReplyAdapter mAdapter;

    public static void newInstance(Context context,String postId,CommentModel commentModel) {
        Intent intent = new Intent(context, CommentContentDetailsActivity.class);
        intent.putExtra(EXTRA_POST_ID, postId);
        intent.putExtra(EXTRA_COMMENT_MODEL, commentModel);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_comment_details;
    }

    @Override
    protected void initWidget() {
        mUserManager = UserManager.getInstance();
        mPostId = getIntent().getStringExtra(EXTRA_POST_ID);
        mCommentModel = getIntent().getParcelableExtra(EXTRA_COMMENT_MODEL);

        mLayout = (RelativeLayout) findViewById(R.id.layout);
        mIcon = (CircleImageView) findViewById(R.id.ci_comment_icon);
        mName = (TextView) findViewById(R.id.tv_comment_author);
        mTime = (TextView) findViewById(R.id.tv_comment_time);
        mContent = (TextView) findViewById(R.id.tv_comment_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

    }

    private void getData() {

        // 根据评论id获取的评论回复数据
        CommentUtils.newInstance(mUser, this).getReply(mCommentModel.getNoteid(), new CommentUtils.OnRequestReplyListener() {
            @Override
            public void getReplys(List<ReplyModel> replyModels) {
                if (replyModels == null || replyModels.size() == 0) {

                } else {
                    if (mAdapter == null) {
                        initAdapter(replyModels);
                    } else {
                        mAdapter.clear();
                        mAdapter.add(replyModels);
                    }
                }
            }
        });
    }

    /**
     * 获取回复
     * @param data
     */
    private void initAdapter(List<ReplyModel> data) {

        mAdapter = new ReplyAdapter(this, data, new MyAdapter.AdapterListener<ReplyModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, ReplyModel data) {
//                if (mUserManager.hasLogined()) {
//                    mUser = MeUtils.getUser();
//                    LogUtils.d("回复",mCommentModel.getNoteid() + "\n" + data.toString());
//
//                    CommentDialogFragment commentDialogFragment = CommentDialogFragment.newInstance(data.getNoteid(), data.getUserid(), data.getName());
//                    commentDialogFragment.show(getSupportFragmentManager(), CommentDialogFragment.TAG);
//                } else {
//                    Toast.makeText(CommentContentDetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, ReplyModel data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CommentContentDetailsActivity.this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        getData();
        Glide.with(this).load(mCommentModel.getPhotourl()).into(mIcon);
        mName.setText(mCommentModel.getName());
        mTime.setText(mCommentModel.getTime());
        mContent.setText(mCommentModel.getContent());
        mLayout.setOnClickListener(this);

    }


    // 添加评论并且刷新列表
    @Override
    public void getContent(final String id,String replyId, String name, String content) {
        content = "回复 " + name + " 的评论：" + content;
        LogUtils.d("回复",mCommentModel.getNoteid() + "\n" +  id + "\n" + replyId +"\n"
                + name+ "\n" + content);
        CommentUtils.newInstance(mUser, this)
                .addReply(mPostId,
                        id,
                        replyId,
                        content,
                        new CommentUtils.OnAddCommentListener() {
                            @Override
                            public void getComment(Boolean result) {
                                if (result) {
                                    if (id.equals(mCommentModel.getNoteid())) {
                                        getData();
                                    }
                                }
                            }
                        });
    }

    @Override
    public void onClick(View v) {
        if (mUserManager.hasLogined()) {
            mUser = MeUtils.getUser();
            CommentDialogFragment commentDialogFragment = CommentDialogFragment.newInstance(mCommentModel.getNoteid(), mCommentModel.getUserid(), mCommentModel.getName());
            commentDialogFragment.show(getSupportFragmentManager(), CommentDialogFragment.TAG);
        } else {
            Toast.makeText(CommentContentDetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }
}
