package com.union.yunzhi.yunzhi.fragment.main;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.factories.moudles.home.homeModle;
import com.union.yunzhi.factories.moudles.home.videoClassModle;
import com.union.yunzhi.factories.moudles.home.videoModle;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends FragmentM {


    private RecyclerView recyclerView;

    private LinearLayout toolbarLayout;

    private HomeAdapter mHomeAdapter;
    List<homeModle> list = new ArrayList<>();

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initWidget(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        toolbarLayout = (LinearLayout) view.findViewById(R.id.toolbar_layout);
    }

    @Override
    protected void initData() {


        mHomeAdapter = new HomeAdapter(getContext(),4);
        data();

        recyclerView.setAdapter(mHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.i("source",list.size()+"");


    }

    public void data(){


        homeModle homeModle = new homeModle();
        homeModle.viewType = 1;
        homeModle.mVideoClassModle = new videoClassModle();
        homeModle.mVideoClassModle.iconUrl = "http://pic25.nipic.com/20121111/10204421_222218120176_2.jpg";
        homeModle.mVideoClassModle.videoClass = "test1";
        homeModle.mVideoClassModle.videoModle = new ArrayList<>();
        videoModle video = new videoModle();
        video.PhotoUrl = "http://pic25.nipic.com/20121111/10204421_222218120176_2.jpg";
        video.PortraitUrl = "http://pic25.nipic.com/20121111/10204421_222218120176_2.jpg";
        video.Title = "test_01";
        homeModle.mVideoClassModle.videoModle.add(video);
        homeModle.mVideoClassModle.videoModle.add(video);
        homeModle.mVideoClassModle.videoModle.add(video);
        homeModle.mVideoClassModle.videoModle.add(video);
        list.add(homeModle);

        mHomeAdapter.add(list);


    }

}
