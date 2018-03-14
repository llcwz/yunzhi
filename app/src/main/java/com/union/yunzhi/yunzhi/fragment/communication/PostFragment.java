package com.union.yunzhi.yunzhi.fragment.communication;

import android.os.Bundle;
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
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.communication.PostDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.PostAdapter;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostFragment extends FragmentM {

    private static final String FRAGMENT_TAG = "TAG";
    private int mTag; // 标记fragment的生成以及相应的帖子
    private List<PostModel> mPostModels = new ArrayList<>();
    private TextView mNoPost;
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
        initAdapter();
        mNoPost = (TextView) view.findViewById(R.id.tv_no_post);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        getData(mTag);
    }

    // 根据tag获取不同的帖子数据
    private void getData(int tag) {
        DialogManager.getInstnce().showProgressDialog(getActivity());
        RequestCenter.requestPost(tag,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("getPostData", responseObj.toString());
                        BaseCommunicationModel baseCommunicationModel = (BaseCommunicationModel) responseObj;
                        if (baseCommunicationModel.ecode == CommunicationConstant.ECODE) {
                            mPostModels = baseCommunicationModel.data;
                        } else {
                            Toast.makeText(getActivity(), "" + baseCommunicationModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 初始化适配器和数据
    private void initAdapter() {
        mAdapter = new PostAdapter(getContext(), mPostModels, new MyAdapter.AdapterListener<PostModel>() {

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


    @Override
    protected void initData() {
        initAdapter();
        if (mPostModels.size() == 0) {
            mNoPost.setVisibility(View.VISIBLE); // 没有帖子显示
        } else {
            mNoPost.setVisibility(View.GONE); // 有帖子隐藏
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }


    // 用于添加帖子后的刷新
    public void notifyList (PostModel postModel) {
        mAdapter.add(postModel);
        mAdapter.notify();
    }
}
