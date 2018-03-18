package com.union.yunzhi.yunzhi.fragment.main;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.union.yunzhi.common.app.AdBrowserActivity;
import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.app.PermissionsFragment;
import com.union.yunzhi.common.constant.Constant;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.util.NetWorkUtil;
import com.union.yunzhi.common.util.ToastUtils;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.home.bodyModle;
import com.union.yunzhi.factories.moudles.home.homeBaseModle;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.LoginActivity;
import com.union.yunzhi.yunzhi.activities.SearchActivity;
import com.union.yunzhi.yunzhi.activities.classfication.ClassCourseDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.HomeAdapter;
import com.union.yunzhi.yunzhi.fragment.me.PersonDialogFragment;
import com.union.yunzhi.yunzhi.manager.MyQrCodeDialog;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.union.yunzhi.yunzhi.zxing.app.CaptureActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link FragmentM} subclass.
 */
public class HomeFragment extends PermissionsFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {

    private static final int REQUEST_QRCODE = 0x01;

    private RecyclerView recyclerView;

    private LinearLayout toolbarLayout;

    private LinearLayout mSearchLayout;

    private SmartRefreshLayout mRefreshLayout;

    private LinearLayout mShowSuccessLayout;

    /**
     * 错误界面
     */
    private FrameLayout mShowErrorLayout;
    private TextView mRefreshButton;
    private ImageView mGIF;

    //登录和注销广播接收器
    //自定义了一个广播接收器
    private LoginBroadcastReceiver receiver = new LoginBroadcastReceiver();
    private LogoutBroadcastReceiver mLogoutReceiver = new LogoutBroadcastReceiver();


    private Banner mBanner;


    //扫码按钮
    private CircleImageView mQRcode;

    private CircleImageView mPortrait;


    private HomeAdapter mHomeAdapter;
    private final String TGA = "HomeFragment";

    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (UserManager.getInstance().hasLogined()) {
                //更新我们的fragment

                if (UserManager.getInstance().hasLogined()) {
                    Glide.with(getContext())
                            .load(UserManager.getInstance().getUser().getPhotourl())
                            .into(mPortrait);
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }

            }
        }
    }
    /**
     * 接受PersonDialogFragment发来的注销广播
     */
    private class LogoutBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
           // visitUI();
            Glide.with(getContext())
                    .load(R.drawable.default_user_avatar)
                    .into(mPortrait);
        }
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initWidget(View view) {
        registerBroadcast();

        /**
         * 隐藏界面控件初始化
         */
        mShowErrorLayout = (FrameLayout) view.findViewById(R.id.show_error);
        mRefreshButton = (TextView) view.findViewById(R.id.tv_refresh);
        mRefreshButton.setOnClickListener(this);
        mGIF = (ImageView) view.findViewById(R.id.img_error);

        mShowSuccessLayout = (LinearLayout) view.findViewById(R.id.show_success);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        toolbarLayout = (LinearLayout) view.findViewById(R.id.toolbar_layout);
        mQRcode = (CircleImageView) toolbarLayout.findViewById(R.id.cv_qrcode);
        mQRcode.setOnClickListener(this);
        mQRcode.setColorFilter(R.color.home_qrcode);

        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mPortrait = (CircleImageView) view.findViewById(R.id.cv_portrait);
        mPortrait.setOnClickListener(this);


        //  mBanner = (Banner) view.findViewById(R.id.banner);


        mSearchLayout = (LinearLayout) view.findViewById(R.id.ll_search);
        mSearchLayout.setOnClickListener(this);


        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i("onSuccess", responseObj.toString());
                mShowErrorLayout.setVisibility(View.GONE);
                mShowSuccessLayout.setVisibility(View.VISIBLE);
                datas(responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                LogUtils.i("onFailure", "error");
                mShowErrorLayout.setVisibility(View.VISIBLE);
                mShowSuccessLayout.setVisibility(View.GONE);
                Glide.with(getContext())
                        .load(R.drawable.error)
                        .asGif()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(mGIF);
            }
        });

    }

    @Override
    protected void initData() {
        recyclerView.setAdapter(mHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mHomeAdapter = new HomeAdapter(getContext(), 4);
    }


    public void datas(Object object) {

        homeBaseModle data = (homeBaseModle) object;

        mHomeAdapter.add(data.data.list);
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

            case R.id.cv_portrait:
                if(UserManager.getInstance().hasLogined()){
                    MyQrCodeDialog dialog = new MyQrCodeDialog(getContext());
                    dialog.show();
                }else{
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }
                break;

            case R.id.tv_refresh:
                Toast.makeText(getContext(),"请求失败，请检查网络状况",Toast.LENGTH_SHORT).show();

                RefreshData();
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
                        if (code.contains("&")) {
                            String courseId;

                            courseId = code.substring(0, code.indexOf("&"));

                            String teacherId;

                            teacherId = code.substring(code.indexOf("&") + 1, code.length());

                            Intent intent = new Intent(getContext(), ClassCourseDetailsActivity.class);

                            intent.putExtra(ClassConst.COURSEID, courseId);
                            intent.putExtra(ClassConst.TEACHERID, teacherId);

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
        changeStatusBarColor(R.color.home_page_color);

        super.initRefreshData();
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                RequestCenter.requestHomeData("", "", new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        homeBaseModle data = (homeBaseModle) responseObj;
                        if (data != null) {
                            // mHomeAdapter.clear();
                            ArrayList<bodyModle> lists = new ArrayList<bodyModle>();
                            for (int i = 1; i < data.data.list.size(); i++) {
                                lists.add(data.data.list.get(i));
                            }
                            mHomeAdapter.add(lists);
                            //mHomeAdapter.add(data.data.list);
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

        Log.i("onRefresh", "onRefresh");
        refreshLayout.autoRefresh();

        RequestCenter.requestHomeData("", "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mShowErrorLayout.setVisibility(View.GONE);
                mShowSuccessLayout.setVisibility(View.VISIBLE);
                homeBaseModle data = (homeBaseModle) responseObj;
                if (data != null) {
                    mHomeAdapter.clear();
                    mHomeAdapter.add(data.data.list);
                    mRefreshLayout.finishRefresh(2000, true);//传入false表示刷新失败
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
//                Toast.makeText(getContext(),"请求失败，请检查网络状况",Toast.LENGTH_SHORT).show();
//                mShowErrorLayout.setVisibility(View.VISIBLE);
//                mShowSuccessLayout.setVisibility(View.GONE);
//                Glide.with(getContext())
//                        .load(R.drawable.error)
//                        .asGif()
//                        .centerCrop()
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .into(mGIF);
//                mRefreshLayout.finishRefresh(2000,false);
                if(!NetWorkUtil.isNetworkConn(getContext())){

                    ToastUtils.showSnackbar(toolbarLayout,"无法连接到网络,请检查网络设置");


                    mRefreshLayout.finishRefresh(1000,false);

                }
            }
        });
    }


    public void RefreshData() {
        onRefresh(mRefreshLayout);
    }


    /**
     * 注销广播
     */
    private void unregisterBroadcast() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mLogoutReceiver);
    }

    /**
     * 注册广播，监听用户登录状态
     */
    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(LoginActivity.LOGIN_ACTION);
        IntentFilter logoutFilter = new IntentFilter(PersonDialogFragment.LOGOUT_ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, filter);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mLogoutReceiver, logoutFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }
}
