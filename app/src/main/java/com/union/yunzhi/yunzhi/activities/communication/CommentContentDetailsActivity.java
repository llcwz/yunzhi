package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.ReplyAdapter;
import com.union.yunzhi.yunzhi.communicationutils.CommentUtils;
import com.union.yunzhi.yunzhi.fragment.communication.CommentDialogFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

public class CommentContentDetailsActivity extends ActivityM implements CommentDialogFragment.OnGetCommentContentListener {

    public static final String EXTRA_COMMENT_MODEL = "commentModel";
    private String mNoteId;
    private String mReplyId;
    private UserManager mUserManager;
    private UserModel mUser;
    private CommentModel mCommentModel;
    private List<CommentModel> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReplyAdapter mAdapter;

    public static void newInstance(Context context, CommentModel commentModel) {
        Intent intent = new Intent(context, CommentContentDetailsActivity.class);
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
        mCommentModel = getIntent().getParcelableExtra(EXTRA_COMMENT_MODEL);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

    }


    private void getData() {
        // 根据评论id获取的评论回复数据
        CommentUtils.newInstance(mUser, this).getComment(mCommentModel.getId(), new CommentUtils.OnRequestCommentListener() {
            @Override
            public void getComments(List<CommentModel> commentModels) {
                commentModels.add(0, mCommentModel); // 就算没有回复，也确保有这条评论
                if (mAdapter == null) {
                    mData = commentModels;
                    initAdapter(mData);
                } else { // 重新请求页面
                    mData.clear();
                    mData = commentModels;
                    mAdapter.add(mData);
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(CommentContentDetailsActivity.this));
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    /**
     * 获取回复
     * @param data
     */
    private void initAdapter(List<CommentModel> data) {
        mAdapter = new ReplyAdapter(this, data, new MyAdapter.AdapterListener<CommentModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, CommentModel data) {
                if (mUserManager.hasLogined()) {
                    mUser = MeUtils.getUser();
                    mNoteId = data.getId();
                    mReplyId = data.getUserId();
                    CommentDialogFragment.newInstance(data.getId(), data.getName());
                } else {
                    Toast.makeText(CommentContentDetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
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
        getData();

    }


    // 添加评论并且刷新列表
    @Override
    public void getContent(final String id, String name, String content) {
        content = "回复 " + name + " 的评论：" + content;
        CommentUtils.newInstance(mUser, this).addReply(id,
                mNoteId,
                mReplyId,
                content,
                new CommentUtils.OnAddCommentListener() {
                    @Override
                    public void getComment(Boolean result) {
                        if (result) {
                            getData();
                        }
                    }
                });
    }
}
