package com.union.yunzhi.yunzhi.fragment.communication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArraySet;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.BaseCommunicationModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.communication.AddPostActivity;
import com.union.yunzhi.yunzhi.activities.communication.PostDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.PostAdapter;
import com.union.yunzhi.yunzhi.communicationutils.OpinionUtils;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostFragment extends FragmentM  {

    private static final String FRAGMENT_TAG = "TAG";
    private int mTag; // 标记fragment的生成以及相应的帖子
    private List<PostModel> mPostModels = new ArrayList<>();
    private NestedScrollView mNoPost;
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;

    public static PostFragment newInstance(int tag) {
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_TAG,tag);
        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mTag = bundle.getInt(FRAGMENT_TAG, -1);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.communication_fragment_post;
    }

    @Override
    protected void initWidget(View view) {
        mNoPost = (NestedScrollView) view.findViewById(R.id.layout_no_post);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);

    }

    // 初始化适配器和数据
    private void initAdapter(List<PostModel> postModels) {
        mAdapter = new PostAdapter(getContext(), postModels, new MyAdapter.AdapterListener<PostModel>() {

            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, PostModel data) {
                // TODO: 2018/3/9  跳转到详情
                PostDetailsActivity.newInstance(getActivity(), data);
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, PostModel data) {

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


    // 获取帖子
    private void getData() {
        OpinionUtils.newInstance(null, getActivity()).getPosts(mTag, new OpinionUtils.OnRequestPostListener() {
            @Override
            public void getPosts(List<PostModel> postModels) {
                if (postModels.size() != 0) {
                    mPostModels = postModels;
                    initAdapter(mPostModels);
                } else {
                    noPost(mPostModels);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }


    // 提示没有帖子
    private void noPost(List<PostModel> postModels) {
        if (postModels.size() == 0) {
            mNoPost.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mNoPost.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void notifyList(PostModel postModel) {
        mAdapter.add(postModel);
    }
}
