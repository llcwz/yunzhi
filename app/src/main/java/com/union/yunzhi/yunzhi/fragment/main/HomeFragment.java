package com.union.yunzhi.yunzhi.fragment.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.union.yunzhi.common.app.AdBrowserActivity;
import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.app.PermissionsFragment;
import com.union.yunzhi.common.constant.Constant;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.hometest.BaseHomeModle;
import com.union.yunzhi.factories.moudles.hometest.HomeBodyModle;
import com.union.yunzhi.factories.moudles.hometest.HomeHeadModle;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.SearchActivity;
import com.union.yunzhi.yunzhi.activities.classfication.ClassCourseDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.HomeAdapter;
import com.union.yunzhi.yunzhi.manager.MyQrCodeDialog;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.union.yunzhi.yunzhi.zxing.app.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.union.yunzhi.yunzhi.activities.LoginActivity.LOGIN_ACTION;

/**
 * A simple {@link FragmentM} subclass.
 */
public class HomeFragment extends PermissionsFragment implements View.OnClickListener ,OnRefreshListener,OnLoadMoreListener{

    private static final int REQUEST_QRCODE = 0x01;

    private RecyclerView recyclerView;

    private LinearLayout toolbarLayout;

    private LinearLayout mSearchLayout;

    private SmartRefreshLayout mRefreshLayout;


    private Banner mBanner;


    //扫码按钮
    private CircleImageView mQRcode;

    private CircleImageView Test;


    private HomeAdapter mHomeAdapter;
    List<HomeBodyModle> list = new ArrayList<>();

    private final String TGA = "HomeFragment";

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initWidget(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        toolbarLayout = (LinearLayout) view.findViewById(R.id.toolbar_layout);
        mQRcode = (CircleImageView) toolbarLayout.findViewById(R.id.cv_qrcode);
        mQRcode.setOnClickListener(this);
        Test = (CircleImageView) view.findViewById(R.id.cv_load);
        Test.setOnClickListener(this);
        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);



      //  mBanner = (Banner) view.findViewById(R.id.banner);


        mSearchLayout = (LinearLayout) view.findViewById(R.id.ll_search);
        mSearchLayout.setOnClickListener(this);


        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i("onSuccess", responseObj.toString());
                datas(responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i("onFailure", "error");
            }
        });

    }

    @Override
    protected void initData() {


        LogUtils.i(TGA, "initWidget");


        // mHomeAdapter = new HomeAdapter(getContext(),4);
        data();

        recyclerView.setAdapter(mHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.i("source", list.size() + "");





    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mHomeAdapter = new HomeAdapter(getContext(), 4);
    }


    public void data() {

    }

    public void datas(Object object) {




        BaseHomeModle data = (BaseHomeModle) object;

        HomeHeadModle head = data.data.head;

        mHomeAdapter.add(data.data.list);


        // list.add(data);

       // mHomeAdapter.add(list);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_qrcode:
                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
                    doOpenCamera();
                } else {
                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
                }
                break;

            case R.id.ll_search:
                startActivity(new Intent(getContext(),
                        SearchActivity.class));
                break;

            case R.id.cv_load:
                MyQrCodeDialog dialog = new MyQrCodeDialog(getContext());
                dialog.show();

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    String code = data.getStringExtra("SCAN_RESULT");

                    Log.i("REQUEST_QRCODE", code);

                    if (code.contains("http") || code.contains("https")) {
                        //跳转到相应的地方
                        Intent intent = new Intent(getContext(), AdBrowserActivity.class);
                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
                        startActivity(intent);
                    } else {
                        //视频界面二维码跳转逻辑
                        if(code.contains("&")){
                            String courseId;

                            courseId = code.substring(0,code.indexOf("&"));

                            String teacherId;

                            teacherId = code.substring(code.indexOf("&")+1,code.length());

                            Intent intent = new Intent(getContext(),ClassCourseDetailsActivity.class);

                            //intent.putExtra(ClassConst.CO,courseId);
                            //intent.putExtra("teacherId",teacherId);

                            startActivity(intent);


                        }
                        Toast.makeText(getContext(), code, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }//end switch
    }


    @Override
    public void doOpenCamera() {
        super.doOpenCamera();
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }


    @Override
    public void initRefreshData() {
        super.initRefreshData();
    }

    public void initBanner(ArrayList<String> list) {
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(list);

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);

        ArrayList<String> tilles = new ArrayList<>();
        tilles.add("aa");
        tilles.add("bb");
        tilles.add("cc");
        tilles.add("dd");

        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(tilles);

        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);

        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);


        mBanner.start();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                    RequestCenter.requestHomeData("", "", new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            BaseHomeModle data = (BaseHomeModle) responseObj;
                            if(data!=null){
                               // mHomeAdapter.clear();
                                mHomeAdapter.add(data.data.list);
                                mRefreshLayout.finishLoadMore(true);
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {

                        }
                    });
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        Log.i("onRefresh","onRefresh");
        refreshLayout.autoRefresh();

        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                BaseHomeModle data = (BaseHomeModle) responseObj;
                if(data!=null){
                    mHomeAdapter.clear();
                    mHomeAdapter.add(data.data.list);
                    mRefreshLayout.finishRefresh(2000,true);//传入false表示刷新失败
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
            }
        });
    }


    public void RefreshData(){
        LogUtils.i("initRefreshData","initRefreshData");
        onRefresh(mRefreshLayout);
    }
    private void sendLoginBroadcast() {
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(LOGIN_ACTION));
    }


}
