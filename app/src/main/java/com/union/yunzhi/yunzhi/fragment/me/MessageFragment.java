package com.union.yunzhi.yunzhi.fragment.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.CommentMeModel;
import com.union.yunzhi.factories.moudles.me.LikeMeModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MessageModel;
import com.union.yunzhi.factories.moudles.me.SystemInformModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.CommentMeAdapter;
import com.union.yunzhi.yunzhi.adapter.LikeMeAdapter;
import com.union.yunzhi.yunzhi.adapter.SystemInformAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class MessageFragment extends FragmentM {

    private static final String FRAGMENT_TAG = "KEY";
    private static final String FRAGMENT_DATA = "messageModel";
    private MessageModel mMessageModel;
//    private List<CommentMeModel> mCommentMeModelList;
//    private List<LikeMeModel> mLikeMeModels;
//    private List<SystemInformModel> mSystemInformModels;
    private CommentMeAdapter mCommentMeAdapter;
    private LikeMeAdapter mLikeMeAdapter;
    private SystemInformAdapter mSystemInformAdapter;
    private int mTag; // 标记是哪一个fragment的信息

    private TextView mNoMessage; // 如果没有消息则显示这个
    private RecyclerView mRecyclerView;

    public static MessageFragment newInstance(int tag, MessageModel messageModel) {
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_TAG,tag);
        bundle.putParcelable(FRAGMENT_DATA, messageModel);
        MessageFragment messageFragment =new MessageFragment();
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mTag = bundle.getInt(FRAGMENT_TAG);
        mMessageModel = bundle.getParcelable(FRAGMENT_DATA);

        // 这些数据在MyMessageActivity中已经请求好了
//        if (mMessageModel != null) {
//            mCommentMeModelList = mMessageModel.getCommentMeModels();
//            mLikeMeModels = mMessageModel.getLikeMeModels();
//            mSystemInformModels = mMessageModel.getSystemInformModels();
//        }
//        for (CommentMeModel commentMeModel : mCommentMeModelList) {
//            LogUtils.d("我的", commentMeModel.toString());
//        }

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.me_fragment_message;
    }

    @Override
    protected void initWidget(View view) {
        mNoMessage = (TextView) view.findViewById(R.id.tv_no_message);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    }
    

    @Override
    protected void initData() {
        // 根据tag来为recyclerView加载相应的适配器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        switch (mTag) {
            case MeConstant.MESSAGE_FRAGMENT_TAG_COMMENT:
                if (mMessageModel.getCommentMeModels() == null) {
                    mNoMessage.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    mNoMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    initCommentMeAdapter(mMessageModel.getCommentMeModels());
                }
                break;
            case MeConstant.MESSAGE_FRAGMENT_TAG_LIKE:
                if (mMessageModel.getLikeMeModels() == null) {
                    mNoMessage.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    mNoMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    initLikeAdapter(mMessageModel.getLikeMeModels());
                }
                break;
            case MeConstant.MESSAGE_FRAGMENT_TAG_INFORM:
                if (mMessageModel.getSystemInformModels() == null) {
                    mNoMessage.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    mNoMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    initSystemAdapter(mMessageModel.getSystemInformModels());
                }
                break;
            default:
        }
    }

    // 初始化系统通知适配器
    private void initSystemAdapter(List<SystemInformModel> systemInformModels) {
        mSystemInformAdapter = new SystemInformAdapter(getActivity(), systemInformModels, new MyAdapter.AdapterListener<SystemInformModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, SystemInformModel data) {

            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, SystemInformModel data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setAdapter(mSystemInformAdapter);
    }

    // 初始化赞我适配器
    private void initLikeAdapter(List<LikeMeModel> likeMeModels) {
        mLikeMeAdapter = new LikeMeAdapter(getActivity(), likeMeModels, new MyAdapter.AdapterListener<LikeMeModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, LikeMeModel data) {

            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, LikeMeModel data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setAdapter(mLikeMeAdapter);
    }
    // 初始化评论我适配器
    private void initCommentMeAdapter(List<CommentMeModel> commentMeModels) {
        mCommentMeAdapter = new CommentMeAdapter(getActivity(), commentMeModels, new MyAdapter.AdapterListener<CommentMeModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, CommentMeModel data) {

            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, CommentMeModel data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setAdapter(mCommentMeAdapter);
    }
}
