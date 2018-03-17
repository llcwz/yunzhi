package com.union.yunzhi.yunzhi.activities.communication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.CommentAdapter;
import com.union.yunzhi.yunzhi.communicationutils.CommentUtils;
import com.union.yunzhi.yunzhi.communicationutils.LikeUtils;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailsActivity extends ActivityM implements View.OnClickListener {
    private static final String TAG = "PostDetailsActivity";
    private UserManager mUserManager;
    private UserModel mUser;
    private PostModel mPostModel;
    private List<CommentModel> mCommentModels; // 评论
    private CommentAdapter mAdapter;
    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private CircleImageView mIcon;
    private TextView mAuthor;
    private TextView mTime;
    private TextView mContent;
    private TextView mNoComment;
    private RecyclerView mRecyclerView;
    private ImageView mLike; // 点赞图标
    private TextView mLikeCounts; // 点赞数
    private ImageView mSendComment; // 发送评论

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

        mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mIcon = (CircleImageView) findViewById(R.id.ci_post_icon);
        mAuthor = (TextView) findViewById(R.id.tv_communication_author);
        mTime = (TextView) findViewById(R.id.tv_post_time);
        mContent = (TextView) findViewById(R.id.tv_post_content);
        mNoComment = (TextView) findViewById(R.id.tv_no_comment);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLike = (ImageView) findViewById(R.id.iv_post_like);
        mLikeCounts = (TextView) findViewById(R.id.tv_post_like);
        mSendComment = (ImageView) findViewById(R.id.iv_post_comment);

        getData();
    }

    // 根据tag获取不同的帖子数据
    private void getData() {
        DialogManager.getInstnce().showProgressDialog(this);
        RequestCenter.requestComment(mPostModel.getId(),
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();

                        BaseCommentModel baseCommentModel = (BaseCommentModel) responseObj;
                        if (baseCommentModel.ecode == CommunicationConstant.ECODE) {
                            mCommentModels = baseCommentModel.data;
                            MeUtils.showNoMessage(mCommentModels.size(),mNoComment, "抢沙发~~");
                            initAdapter(mCommentModels);
                            for (CommentModel commentModel : mCommentModels) {
                                LogUtils.d("commentMessage", commentModel.toString());
                            }
                        } else {
                            Toast.makeText(PostDetailsActivity.this, "" + baseCommentModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
                        if (okHttpException.getEcode() == 1) {
                            Toast.makeText(PostDetailsActivity.this, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -1){
                            Toast.makeText(PostDetailsActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -2) {
                            Toast.makeText(PostDetailsActivity.this, "解析错误" , Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -3) {
                            Toast.makeText(PostDetailsActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 初始化数据
    private void initAdapter(List<CommentModel> commentModels) {
        mAdapter = new CommentAdapter(this, commentModels,new MyAdapter.AdapterListener<CommentModel>() {
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

        mToolbar.setTitle(getString(R.string.me_navigation_news));
        // 根据头像改变背景颜色
        MeUtils.showPalette(this, mPostModel.getPhotoUrl(), new MeUtils.OnShowPalleteListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void show(Bitmap bitmap, List<Palette.Swatch> swatches) {
                if (bitmap != null) {
                    mIcon.setImageBitmap(bitmap);
                    Window window = getWindow();
                    for (int i = 0; i < swatches.size(); i++) {
                        try {
                            if (swatches.get(i) != null) {
                                mAppBar.setBackgroundColor(swatches.get(i).getRgb());
                                window.setStatusBarColor(swatches.get(i).getRgb());
                                mToolbar.setTitleTextColor(swatches.get(i).getTitleTextColor());
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        mAuthor.setText(mPostModel.getName());
        mTime.setText(mPostModel.getTime());
        mContent.setText(mPostModel.getContent());
        mSendComment.setOnClickListener(this);
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getApplication());
        linearLayoutManager.setScrollEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mLikeCounts.setText("" + mPostModel.getFavour());
        mLike.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // 用户登录才能操作
        if (mUserManager.hasLogined()) { // 如果用户登录了
            switch (view.getId()) {
                case R.id.tv_send_comment: // 发表评论
//                    if (TextUtils.isEmpty(comment)) {
//                        Toast.makeText(this, "请先评论", Toast.LENGTH_SHORT).show();
//                    } else {
//                        mComment.setText("");
//                        CommentUtils commentUtils =CommentUtils.newInstance(mUser, this, mPostModel.getId(), comment);
//                        commentUtils.addComment(CommunicationConstant.COMMENT_TAG_POST,mAdapter); // 刷新
//                    }
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


//    Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message message) {
//            if (message.what == 0){
//                if(mSwipeRefreshLayout.isRefreshing()) {
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//            }
//            return false;
//        }
//    });
//
//    @Override
//    public void onRefresh() {
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Message message = new Message();
//                    message.what = 0;
//                    mHandler.sendMessage(message);
//                }
//            }).start();
//
//    }
}
