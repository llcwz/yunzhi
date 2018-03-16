package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.CommentAdapter;
import com.union.yunzhi.yunzhi.communicationutils.CommentUtils;
import com.union.yunzhi.yunzhi.communicationutils.LikeUtils;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailsActivity extends ActivityM implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "PostDetailsActivity";
    private UserManager mUserManager;
    private UserModel mUser;
    private PostModel mPostModel;
    private List<CommentModel> mCommentModels; // 评论
    private CommentAdapter mAdapter;
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTitle;
    private CircleImageView mIcon;
    private TextView mAuthor;
    private TextView mTime;
    private TextView mContent;
    private RecyclerView mRecyclerView;
    private ImageView mLike; // 点赞图标
    private TextView mLikeCounts; // 点赞数
    private EditText mComment; // 自己编辑的评论内容
    private TextView mSendComment; // 发送评论

    public static void newInstance(Context context, PostModel postModel) {
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(TAG, postModel);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_communication_post_details;
    }

    @Override
    protected void initWidget() {

        mUserManager = UserManager.getInstance();
        mUser = MeUtils.getUser();

        mPostModel = getIntent().getParcelableExtra(TAG);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mTitle = (TextView) findViewById(R.id.tv_post_title);
        mIcon = (CircleImageView) findViewById(R.id.ci_post_icon);
        mAuthor = (TextView) findViewById(R.id.tv_communication_author);
        mTime = (TextView) findViewById(R.id.tv_post_time);
        mContent = (TextView) findViewById(R.id.tv_post_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLike = (ImageView) findViewById(R.id.iv_post_like);
        mLikeCounts = (TextView) findViewById(R.id.tv_post_like);
        mComment = (EditText) findViewById(R.id.et_send_comment);
        mSendComment = (TextView) findViewById(R.id.tv_send_comment);
    }

    // 初始化数据
    private void initAdapter() {
        mAdapter = new CommentAdapter(this, mCommentModels,new MyAdapter.AdapterListener<CommentModel>() {
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
        initAdapter();

        mTitle.setText(mPostModel.getTitle());
        Glide.with(this).load(mPostModel.getPhotoUrl()).into(mIcon);
        mAuthor.setText(mPostModel.getName());
        mTime.setText(mPostModel.getTime());
        mContent.setText(mPostModel.getContent());
        mSendComment.setOnClickListener(this);
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getApplication());
        linearLayoutManager.setScrollEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mLikeCounts.setText("" + mPostModel.getFavour());
        mLike.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View view) {

        // 用户登录才能操作
        if (mUserManager.hasLogined()) { // 如果用户登录了
            switch (view.getId()) {
                case R.id.tv_send_comment: // 发表评论
                    String comment = mComment.getText().toString();
                    if (TextUtils.isEmpty(comment)) {
                        Toast.makeText(this, "请先评论", Toast.LENGTH_SHORT).show();
                    } else {
                        mComment.setText("");
                        CommentUtils commentUtils =CommentUtils.newInstance(mUser, this, mPostModel.getId(), comment);
                        commentUtils.addComment(CommunicationConstant.COMMENT_TAG_POST,mAdapter); // 刷新
                    }
                    break;
                case R.id.iv_post_like: // 点赞帖子
                    LikeUtils likeUtils = LikeUtils.newInstance(mPostModel.getId(),mUser, this, mLike, mLikeCounts);
//                    likeUtils.checkedPostLike(mPostModel);
                    break;
                default:
            }
        } else { // 如果用户没有登录
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }


    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 0){
                if(mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            }
            return false;
        }
    });

    @Override
    public void onRefresh() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
            }).start();

    }
}
