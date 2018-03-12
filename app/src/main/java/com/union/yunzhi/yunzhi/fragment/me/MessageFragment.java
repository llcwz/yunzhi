package com.union.yunzhi.yunzhi.fragment.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.app.FragmentM;
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

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class MessageFragment extends FragmentM {

    private static final String FRAGMENT_TAG = "TAG";
    private static final String FRAGMENT_DATA = "messageModel";
    private MessageModel mMessageModel;
    private List<CommentMeModel> mCommentMeModelList;
    private List<LikeMeModel> mLikeMeModels;
    private List<SystemInformModel> mSystemInformModels;
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
        return new MessageFragment();
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mTag = bundle.getInt(FRAGMENT_TAG);
        mMessageModel = bundle.getParcelable(FRAGMENT_DATA);
        mCommentMeModelList = mMessageModel.getCommentMeModels();
        mLikeMeModels = mMessageModel.getLikeMeModels();
        mSystemInformModels = mMessageModel.getSystemInformModels();

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
        switch (mTag) {
            case MeConstant.MESSAGE_FRAGMENT_TAG_COMMENT:
                if (mCommentMeModelList == null) {
                    mNoMessage.setVisibility(View.VISIBLE);
                } else {
                    mNoMessage.setVisibility(View.GONE);
                    initCommentMeAdapter();
                }
                break;
            case MeConstant.MESSAGE_FRAGMENT_TAG_LIKE:
                if (mLikeMeModels == null) {
                    mNoMessage.setVisibility(View.VISIBLE);
                } else {
                    mNoMessage.setVisibility(View.GONE);
                    initLikeAdapter();
                }
                break;
            case MeConstant.MESSAGE_FRAGMENT_TAG_INFORM:
                if (mSystemInformModels == null) {
                    mNoMessage.setVisibility(View.VISIBLE);
                } else {
                    mNoMessage.setVisibility(View.GONE);
                    initSystemAdapter();
                }
                break;
            default:
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initSystemAdapter() {
        mSystemInformAdapter = new SystemInformAdapter(getActivity(), mSystemInformModels, new MyAdapter.AdapterListener<SystemInformModel>() {
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

    private void initLikeAdapter() {
        mLikeMeAdapter = new LikeMeAdapter(getActivity(), mLikeMeModels, new MyAdapter.AdapterListener<LikeMeModel>() {
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
        mRecyclerView.setAdapter(mSystemInformAdapter);
    }

    private void initCommentMeAdapter() {
        mCommentMeAdapter = new CommentMeAdapter(getActivity(), mCommentMeModelList, new MyAdapter.AdapterListener<CommentMeModel>() {
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
        mRecyclerView.setAdapter(mSystemInformAdapter);
    }
}
