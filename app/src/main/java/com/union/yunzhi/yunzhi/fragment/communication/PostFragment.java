package com.union.yunzhi.yunzhi.fragment.communication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostFragment extends FragmentM implements AddPostActivity.OnAddPostListener {

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
                        BaseCommunicationModel baseCommunicationModel = (BaseCommunicationModel) responseObj;
                        if (baseCommunicationModel.ecode == CommunicationConstant.ECODE) {
                            mPostModels = baseCommunicationModel.data;
                            initAdapter(mPostModels);
                            noPost(mPostModels);
                            for (PostModel postModel : mPostModels) {
                                LogUtils.d("postMessage", postModel.toString());
                            }
                        } else {
                            Toast.makeText(getActivity(), "" + baseCommunicationModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        noPost(mPostModels);
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
                        if (okHttpException.getEcode() == 1) {
                            Toast.makeText(getActivity(), "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -1){
                            Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -2) {
                            Toast.makeText(getActivity(), "解析错误" , Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -3) {
                            Toast.makeText(getActivity(), "未知错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void getPost(int tag, PostModel postModel) {
        FragmentManager fragmentManager = getChildFragmentManager();
        PostFragment fragment = (PostFragment) fragmentManager.findFragmentById(tag);
        fragment.mAdapter.add(postModel);
    }

    private void noPost(List<PostModel> postModels) {
        if (postModels.size() == 0) {
            mNoPost.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mNoPost.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
